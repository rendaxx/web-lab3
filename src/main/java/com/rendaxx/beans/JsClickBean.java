package com.rendaxx.beans;

import com.rendaxx.dto.HitResultRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Named
@SessionScoped
@Getter
@Setter
public class JsClickBean implements Serializable {
    private Double x;
    private Double y;
    private Integer r;

    @Inject
    HitResultBean hitResultBean;

    public void submit() {
        hitResultBean.addHit(new HitResultRequestDto(x, y, r));
    }
}
