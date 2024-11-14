package com.rendaxx.beans;

import com.rendaxx.dto.HitMapper;
import com.rendaxx.dto.HitResultRequestDto;
import com.rendaxx.dto.HitResultResponseDto;
import com.rendaxx.model.HitResult;
import com.rendaxx.model.HitResultRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named
@SessionScoped
public class HitResultBean implements Serializable {

    @Inject
    private HitResultRepository hitResultRepository;

    public void addHit(HitResultRequestDto dto) {
        LocalDateTime processingStartTime = LocalDateTime.now();
        long inMillisStart = System.currentTimeMillis();
        Boolean isAreaHit = checkArea(dto.x(), dto.y(), dto.r());
        Long inMillisEnd = System.currentTimeMillis() - inMillisStart;
        hitResultRepository.saveHit(new HitResult(null, dto.x(), dto.y(), dto.r(), isAreaHit, inMillisEnd, processingStartTime));
    }

    public List<HitResultResponseDto> getResults() {
        return hitResultRepository.getAllHits().stream().map(HitMapper::toResponseDto).toList();
    }

    private boolean checkArea(double x, double y, int r) {
        if (x > 0 && y > 0) {
            return false;
        }
        if (x < 0 && y > 0) {
            return y >= x/2 + r/2.;
        }
        if (x <= 0 && y <= 0) {
            return x*x + y*y <= r*r/4.;
        }
        return x <= r && y <= -r;
    }
}
