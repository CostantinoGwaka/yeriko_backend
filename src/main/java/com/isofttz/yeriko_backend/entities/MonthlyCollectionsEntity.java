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
@Table(name = "monthly_collection")
public class MonthlyCollectionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "year_id", nullable = false)
    private ChurchYearEntity churchYearEntity;

    @Column(nullable = false)
    private String monthly;

    @Column(nullable = false)
    private String registeredBy;
}
