package whitebrains.core;

import java.text.NumberFormat;

public class Math {
	private static NumberFormat numberFormat;

	public static double percent(Long sum, Long subtrahend) {
		numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(1);
		double percent = (((double) subtrahend * 100D) / (double) sum) / 100D;
		return percent;
	}
}
