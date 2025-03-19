package TaxCalculatorSystem;

import java.util.Map;

/*
 * 计算个人所得税
 */
public class TaxCalculator {
    public static double calculateTax(double salary){
        double taxableIncome = salary - TaxConfig.getTaxThreshold();
        if (taxableIncome <= 0) return 0; // 低于起征点，不收税

        double tax = 0;
        double previousBracket = 0;

        for (Map.Entry<Double, Double> bracket : TaxConfig.getTaxRateTable().entrySet()) {
            double upperLimit = bracket.getKey();
            double rate = bracket.getValue();

            if (taxableIncome > previousBracket) {
                double taxableAmount = Math.min(taxableIncome - previousBracket, upperLimit - previousBracket);
                tax += taxableAmount * rate;
            } else {
                break;
            }
            previousBracket = upperLimit;
        }
        return tax;
    }
}
