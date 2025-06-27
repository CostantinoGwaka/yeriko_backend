package com.isofttz.yeriko_backend.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "church_time_table")
public class ChurchTimeTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "year_id", nullable = false)
    private ChurchYearEntity churchYearEntity;

    @Column(nullable = false)
    private String datePrayer;

    @Column(nullable = true)
    private String latId;

    @Column(nullable = true)
    private String longId;

    @Column(nullable = false)
    private String registeredBy;
}
