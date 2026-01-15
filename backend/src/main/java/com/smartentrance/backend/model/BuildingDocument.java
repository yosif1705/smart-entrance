package com.smartentrance.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartentrance.backend.model.enums.DocumentType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building_documents")
@Data
@NoArgsConstructor
public class BuildingDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "building_document_mappings",
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "building_id")
    )
    @ToString.Exclude
    @JsonIgnore
    private List<Building> buildings = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_by", nullable = false)
    private User uploadedBy;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType type;

    @Column(nullable = false)
    private String fileUrl;

    @Column(nullable = false)
    private boolean isVisibleToResidents = true;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();
}