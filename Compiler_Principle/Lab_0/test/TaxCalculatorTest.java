package TaxCalculatorSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

public class TaxCalculatorTest {

    @BeforeEach
    public void setUp() {
        // 在每个测试方法执行前，将配置重置为默认值
        TaxConfig.resetToDefault();
    }

    @Test
    public void testCalculateTaxBelowThreshold() {
        // 测试工资低于起征点的情况
        double salary = 3000;
        double tax = TaxCalculator.calculateTax(salary);
        assertEquals(0, tax, 0.001);
    }

    @Test
    public void testCalculateTaxAboveThreshold() {
        // 测试工资高于起征点的情况
        double salary = 8000;
        double taxableIncome = salary - TaxConfig.getTaxThreshold();
        double expectedTax = taxableIncome * 0.03;
        double tax = TaxCalculator.calculateTax(salary);
        assertEquals(expectedTax, tax, 0.001);
    }
    
    @Test
    public void testCalculateTax15000() {
    	// 不同税档计算
        double salary = 15000;
        double expectedTax = (3000 * 0.03) + (7000 * 0.10); // 90 + 700 = 790
        double tax = TaxCalculator.calculateTax(salary);
        assertEquals(expectedTax, tax, 0.001);
    }

    @Test
    public void testCalculateTax40000() {
    	// 不同税档计算
        double salary = 40000;
        double expectedTax = (3000 * 0.03) + (9000 * 0.10) + (13000 * 0.20) + (10000 * 0.25);
        double tax = TaxCalculator.calculateTax(salary);
        assertEquals(expectedTax, tax, 0.001);
    }

    @Test
    public void testCalculateTaxEdge8000() {
    	 // 边界测试
        double salary = 8000;
        double expectedTax = (3000 * 0.03); // 90
        double tax = TaxCalculator.calculateTax(salary);
        assertEquals(expectedTax, tax, 0.001);
    }

    @Test
    public void testCalculateTax_NegativeSalary() {
    	// 负面测试
        double salary = -5000;
        double tax = TaxCalculator.calculateTax(salary);
        assertEquals(0, tax, 0.001);
    }

    @Test
    public void testCalculateTax_MaxDoubleValue() {
    	// 负面测试
        double salary = Double.MAX_VALUE;
        double tax = TaxCalculator.calculateTax(salary);
        assertTrue(tax > 0);
    }
    
    @Test
    public void testSetTaxThreshold() {
        // 测试设置新的起征点
        double newThreshold = 6000;
        TaxConfig.setTaxThreshold(newThreshold);
        assertEquals(newThreshold, TaxConfig.getTaxThreshold(), 0.001);
    }

    @Test
    public void testUpdateTaxRateTable() {
        // 测试更新税率表
        double income = 5000;
        double rate = 0.05;
        TaxConfig.updateTaxRateTable(income, rate);
        TreeMap<Double, Double> taxRateTable = TaxConfig.getTaxRateTable();
        assertEquals(rate, taxRateTable.get(income), 0.001);
    }

    @Test
    public void testResetToDefault() {
        // 测试重置为默认配置
        TaxConfig.setTaxThreshold(7000);
        TaxConfig.updateTaxRateTable(4000, 0.06);
        TaxConfig.resetToDefault();
        assertEquals(5000, TaxConfig.getTaxThreshold(), 0.001);
        TreeMap<Double, Double> taxRateTable = TaxConfig.getTaxRateTable();
        assertEquals(0.03, taxRateTable.get(3000.0), 0.001);
    }
}