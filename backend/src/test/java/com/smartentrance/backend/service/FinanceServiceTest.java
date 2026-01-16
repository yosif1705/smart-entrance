package com.smartentrance.backend.service;

import com.smartentrance.backend.mapper.BuildingExpenseMapper;
import com.smartentrance.backend.mapper.TransactionMapper;
import com.smartentrance.backend.model.*;
import com.smartentrance.backend.model.enums.*;
import com.smartentrance.backend.repository.BuildingExpenseRepository;
import com.smartentrance.backend.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FinanceServiceTest {

    @Mock TransactionRepository transactionRepository;
    @Mock BuildingExpenseRepository expenseRepository;
    @Mock TransactionMapper transactionMapper;
    @Mock BuildingExpenseMapper expenseMapper;
    @Mock UnitService unitService;
    @Mock BuildingService buildingService;
    @Mock PdfReceiptService pdfReceiptService;
    @Mock FileStorageService fileStorageService;

    @InjectMocks FinanceService financeService;

    private Unit unit;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        unit = new Unit();
        unit.setId(10L);
        unit.setResponsibleUser(new User());
        unit.getResponsibleUser().setId(1);

        transaction = new Transaction();
        transaction.setId(100L);
        transaction.setUnit(unit);
        transaction.setAmount(new BigDecimal("100.00"));
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setFundType(FundType.GENERAL);
    }

    @Test
    void testApproveTransaction_Waterfall_CoversDebtsFirst() {
        when(transactionRepository.findById(100L)).thenReturn(Optional.of(transaction));
        when(unitService.findById(10L)).thenReturn(Optional.of(unit));

        when(transactionRepository.sumFeesByUserAndFund(10L, 1, FundType.REPAIR)).thenReturn(new BigDecimal("-40.00"));
        when(transactionRepository.sumSplitsByUserAndFund(10L, 1, FundType.REPAIR)).thenReturn(BigDecimal.ZERO);

        when(transactionRepository.sumFeesByUserAndFund(10L, 1, FundType.MAINTENANCE)).thenReturn(BigDecimal.ZERO);
        when(transactionRepository.sumSplitsByUserAndFund(10L, 1, FundType.MAINTENANCE)).thenReturn(BigDecimal.ZERO);

        financeService.approveTransaction(100L, new User());

        assertEquals(TransactionStatus.CONFIRMED, transaction.getStatus());

        boolean coveredRepair = transaction.getSplit().stream()
                .anyMatch(s -> s.getFundType() == FundType.REPAIR && s.getAmount().compareTo(new BigDecimal("40.00")) == 0);
        boolean restToGeneral = transaction.getSplit().stream()
                .anyMatch(s -> s.getFundType() == FundType.GENERAL && s.getAmount().compareTo(new BigDecimal("60.00")) == 0);

        assertTrue(coveredRepair, "Should cover the 40 repair debt");
        assertTrue(restToGeneral, "Remaining 60 should go to general fund");
    }

    @Test
    void testProcessMonthlyFees_CalculatesCorrectly() {
        Building building = new Building();
        building.setId(1);
        building.setRepairBudget(new BigDecimal("100.00"));
        building.setMaintenanceBudget(new BigDecimal("200.00"));

        Unit u1 = new Unit(); u1.setId(1L); u1.setVerified(true); u1.setArea(new BigDecimal("50")); u1.setResidentsCount(1);

        Unit u2 = new Unit(); u2.setId(2L); u2.setVerified(true); u2.setArea(new BigDecimal("50")); u2.setResidentsCount(1);

        when(unitService.findAllByBuildingId(1)).thenReturn(List.of(u1, u2));
        when(unitService.findById(any())).thenReturn(Optional.of(u1));

        financeService.processMonthlyFeesForBuilding(building, "Oct");

        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);

        verify(transactionRepository, atLeast(4)).save(captor.capture());

        List<Transaction> saved = captor.getAllValues();

        boolean correctRepair = saved.stream().anyMatch(t ->
                t.getFundType() == FundType.REPAIR && t.getAmount().compareTo(new BigDecimal("-50.00")) == 0);

        boolean correctMaint = saved.stream().anyMatch(t ->
                t.getFundType() == FundType.MAINTENANCE && t.getAmount().compareTo(new BigDecimal("-100.00")) == 0);

        assertTrue(correctRepair, "Repair fee calculation wrong");
        assertTrue(correctMaint, "Maintenance fee calculation wrong");
    }
}