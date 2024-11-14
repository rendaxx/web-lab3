package com.rendaxx.beans;

import com.rendaxx.dto.HitResultRequestDto;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
@Getter
@Setter
public class FormBean implements Serializable {
    private static final List<String> xValues = List.of("-2", "-1.5", "-1", "-0.5", "0", "0.5", "1", "1.5", "2");

    @Inject
    private HitResultBean hitResultBean;

    private Map<String, Boolean> selectedX;
    private Double y;
    private Integer r = 1;

    public FormBean() {
        selectedX = new HashMap<>();
        for (String str : xValues) {
            selectedX.put(str, false);
        }
    }

    public void submit() {
        for (var e : selectedX.entrySet()) {
            if (e.getValue()) {
                double x = Double.parseDouble(e.getKey());
                hitResultBean.addHit(new HitResultRequestDto(x, y, r));
            }
        }
    }
}
