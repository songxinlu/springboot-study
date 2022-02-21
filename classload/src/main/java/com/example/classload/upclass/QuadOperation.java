package com.example.classload.upclass;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;

/**
 * @Description:四合运算类
 * @Author songxl
 * @Date 2022/2/14
 */
public class QuadOperation {
    public double add(Double a,Double b){
        return NumberUtil.add(a,b);
    }

    public  BigDecimal sub(Number... values){
        return NumberUtil.sub(values);
    }
    public  BigDecimal mul(Number... values){
        return NumberUtil.mul(values);
    }
    public  double div(double dividend,double divisor,int scale){
        return NumberUtil.div(dividend,divisor,scale);
    }
}
