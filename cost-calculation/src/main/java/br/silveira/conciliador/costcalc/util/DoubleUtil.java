package br.silveira.conciliador.costcalc.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleUtil {

	public static Double round2decimals(Double value) {
		return (new BigDecimal(value).setScale(2, RoundingMode.HALF_UP)).doubleValue();
	}

}
