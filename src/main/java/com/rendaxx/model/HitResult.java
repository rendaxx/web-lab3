package com.rendaxx.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="hit_results")
public class HitResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double x;

    private Double y;

    private Integer r;

    @Column(name = "is_area_hit")
    private Boolean isAreaHit;

    @Column(name = "execution_time_in_millis")
    private Long executionTimeInMillis;

    @Column(name = "processing_start_time")
    private LocalDateTime processingStartTime;
}

