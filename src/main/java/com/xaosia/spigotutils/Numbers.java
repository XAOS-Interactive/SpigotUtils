package com.xaosia.spigotutils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Numbers {
	public static double round(Double number, int digits){
		return new BigDecimal(number.toString()).setScale(digits,RoundingMode.HALF_UP).doubleValue();
	}
}