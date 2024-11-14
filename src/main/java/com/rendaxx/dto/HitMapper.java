package com.rendaxx.dto;

import com.rendaxx.model.HitResult;
import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;

@UtilityClass
public class HitMapper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static HitResultResponseDto toResponseDto(HitResult hitResult) {
        return new HitResultResponseDto(
                hitResult.getX(),
                hitResult.getY(),
                hitResult.getR(),
                hitResult.getIsAreaHit(),
                hitResult.getExecutionTimeInMillis(),
                hitResult.getProcessingStartTime().format(formatter)
        );
    }
}
