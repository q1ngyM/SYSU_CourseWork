package TaxCalculatorSystem;

import java.util.TreeMap;

/*
 * 修改个人所得税
 *  * 修改起征点
 *  * 修改税率表
 */
public class TaxConfig {
    // 初始化默认起征点
    private static double taxThreshold = 5000; 
    private static final TreeMap<Double, Double> TAX_RATE_TABLE = new TreeMap<>();

    static {
        // 初始化默认税率表
        TAX_RATE_TABLE.put(3000.0, 0.03);
        TAX_RATE_TABLE.put(12000.0, 0.10);
        TAX_RATE_TABLE.put(25000.0, 0.20);
        TAX_RATE_TABLE.put(35000.0, 0.25);
        TAX_RATE_TABLE.put(55000.0, 0.30);
        TAX_RATE_TABLE.put(80000.0, 0.35);
        TAX_RATE_TABLE.put(Double.MAX_VALUE, 0.45);
    }

    public static double getTaxThreshold() {
        return taxThreshold;
    }

    public static void setTaxThreshold(double newThreshold) {
        taxThreshold = newThreshold;
    }

    public static TreeMap<Double, Double> getTaxRateTable() {
        return TAX_RATE_TABLE;
    }

    public static void updateTaxRateTable(double income, double rate) {
        TAX_RATE_TABLE.put(income, rate);
    }

    public static void resetToDefault() {
        taxThreshold = 5000;
        TAX_RATE_TABLE.clear();
        TAX_RATE_TABLE.put(3000.0, 0.03);
        TAX_RATE_TABLE.put(12000.0, 0.10);
        TAX_RATE_TABLE.put(25000.0, 0.20);
        TAX_RATE_TABLE.put(35000.0, 0.25);
        TAX_RATE_TABLE.put(55000.0, 0.30);
        TAX_RATE_TABLE.put(80000.0, 0.35);
        TAX_RATE_TABLE.put(Double.MAX_VALUE, 0.45);
    }
}
