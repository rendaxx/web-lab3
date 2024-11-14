package com.rendaxx.dto;

public record HitResultResponseDto(Double x, Double y, Integer r, Boolean isAreaHit, Long executionTimeInMillis,
                                   String processingStartTime) {
}
