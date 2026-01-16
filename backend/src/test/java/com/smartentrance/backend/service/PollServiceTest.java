package com.smartentrance.backend.service;

import com.smartentrance.backend.dto.poll.PollUpdateRequest;
import com.smartentrance.backend.model.VotesPoll;
import com.smartentrance.backend.repository.VotesPollRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PollServiceTest {

    @Mock VotesPollRepository pollRepository;
    @InjectMocks PollService pollService;

    @Test
    void testUpdatePoll_PreventTimeParadox() {
        Integer pollId = 1;
        VotesPoll existingPoll = new VotesPoll();
        existingPoll.setId(pollId);
        existingPoll.setStartAt(Instant.now().plus(1, ChronoUnit.DAYS));
        existingPoll.setEndAt(Instant.now().plus(5, ChronoUnit.DAYS));

        when(pollRepository.findById(pollId)).thenReturn(Optional.of(existingPoll));

        PollUpdateRequest sabotageReq = new PollUpdateRequest(
                null, null,
                Instant.now().plus(1, ChronoUnit.DAYS),
                Instant.now().minus(1, ChronoUnit.DAYS)
        );

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                pollService.updatePoll(pollId, sabotageReq)
        );

        assertEquals("End date cannot be before start date", ex.getMessage());
        verify(pollRepository, never()).save(any());
    }

    @Test
    void testUpdatePoll_ImmutableActivePolls() {
        Integer pollId = 1;
        VotesPoll activePoll = new VotesPoll();
        activePoll.setStartAt(Instant.now().minus(1, ChronoUnit.HOURS)); // ВЕЧЕ Е ПОЧНАЛА
        activePoll.setEndAt(Instant.now().plus(1, ChronoUnit.DAYS));

        when(pollRepository.findById(pollId)).thenReturn(Optional.of(activePoll));

        PollUpdateRequest req = new PollUpdateRequest("Changed Title", null, null, null);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () ->
                pollService.updatePoll(pollId, req)
        );

        assertEquals("Cannot update polls that have already started.", ex.getMessage());
    }
}