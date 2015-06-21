package utility;

public class Statistics {

	private static int cnt_Province = 0;
	private static int[] stats_holding = new int[4];
	private static int[] stats_culture = new int[92];
	private static int[] stats_religion = new int[14];
	private static int[] stats_pagans = new int[10];
	private static int[] stats_heresy = new int[19];

	public static void countProvince() {
		cnt_Province++;
	}
	
	public static int getProvince() {
		return cnt_Province;
	}
	
	public static void countHolding(int i) {
		stats_holding[i]++;
	}
	
	public static void countCulture(int i) {
		stats_culture[i]++;
	}
	
	public static void countReligion(int i) {
		stats_religion[i]++;
	}
	
	public static void countPagans(int i) {
		stats_pagans[i]++;
	}
	
	public static void countHeresy(int i) {
		stats_heresy[i]++;
	}
}
