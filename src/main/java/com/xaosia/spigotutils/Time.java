package com.xaosia.spigotutils;

public class Time {
	public static String getFormattedTime(long time){
		int d,h,m;
		d = (int) time / (60 * 60 * 24);
		time -= d * ( 60 * 60 * 24);
		h = (int) time / (60 * 60);
		time -= h * (60 * 60);
		m = (int) time / 60;
		time -= m * 60;
		
		
		if(d == 0){
			if(h==0){
				if(m==0){
					return time+"s";
				}else{
					return m+"m "+time+"s";
				}
			}else{
				return h+"h "+m+"m "+time+"s";
			}
		}else{
			return d+"j "+h+"h "+m+"m "+time+"s";
		}
	}
}
