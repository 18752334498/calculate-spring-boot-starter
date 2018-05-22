package com.yucong.calculate;

import java.math.BigDecimal;

public class CalculateService {

    public static final String DEFAULT_SCALE = "2";

    private String scale = DEFAULT_SCALE; //如果不做配置,默认精度是 2; 如果scale是int,默认精度是 0;

    public CalculateService(String scale) {
        this.scale = scale == null ? this.scale : scale;
    }

    public double add(double a, double b) {
        return convert(a + b);
    }

    public double sub(double a, double b) {
        return convert(a - b);
    }

    private double convert(double tem) {
        return new BigDecimal(tem).setScale(Integer.valueOf(scale), BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
