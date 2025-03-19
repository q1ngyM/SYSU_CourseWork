package TaxCalculatorSystem;

import java.util.Map;
import java.util.Scanner;

/*
 * 命令行用户界面
 */
public class TaxCLI {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("\n==== 个人所得税计算器 ====");
                System.out.println("1. 计算个人所得税");
                System.out.println("2. 修改起征点");
                System.out.println("3. 修改税率");
                System.out.println("4. 查看当前税率表");
                System.out.println("5. 重置默认税率表");
                System.out.println("6. 退出");
                System.out.print("请输入操作: ");

                try {
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            calculateTax(scanner);
                            break;
                        case 2:
                            updateThreshold(scanner);
                            break;
                        case 3:
                            updateTaxRate(scanner);
                            break;
                        case 4:
                            displaytaxRateTable();
                            break;
                        case 5:
                            resetToDefault();
                            break; // 修复：添加 break
                        case 6:
                            System.out.println("退出程序...");
                            return;
                        default:
                            System.out.println("请输入 1-6 之间的选项");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("请输入有效的数字");
                }
            }
        } finally {
            scanner.close(); // 关闭 Scanner
        }
    }

    private static void calculateTax(Scanner scanner) {
        System.out.print("请输入您的工资: ");
        try {
            double salary = Double.parseDouble(scanner.nextLine());
            System.out.println("应缴个人所得税: " + TaxCalculator.calculateTax(salary) + " 元");
        } catch (NumberFormatException e) {
            System.out.println("无效输入，请输入数字");
        }
    }

    private static void updateThreshold(Scanner scanner) {
        System.out.print("请输入新的起征点: ");
        try {
            double newThreshold = Double.parseDouble(scanner.nextLine());
            TaxConfig.setTaxThreshold(newThreshold);
            System.out.println("起征点已更新为: " + newThreshold);
        } catch (NumberFormatException e) {
            System.out.println("请输入有效的数字！");
        }
    }

    private static void updateTaxRate(Scanner scanner) {
        System.out.print("请输入新的税率适用收入（元）: ");
        try {
            double income = Double.parseDouble(scanner.nextLine());
            System.out.print("请输入对应税率（如 0.10 代表 10%）: ");
            double rate = Double.parseDouble(scanner.nextLine());

            TaxConfig.updateTaxRateTable(income, rate);
            System.out.println("税率已更新！");
        } catch (NumberFormatException e) {
            System.out.println("请输入有效的数字！");
        }
    }

    private static void displaytaxRateTable() {
        System.out.println("当前税率表: ");
        for (Map.Entry<Double, Double> entry : TaxConfig.getTaxRateTable().entrySet()) {
            System.out.println("收入 ≤ " + entry.getKey() + " 元，税率: " + (entry.getValue() * 100) + "%");
        }
    }

    private static void resetToDefault() {
        TaxConfig.resetToDefault();
        System.out.println("重置完成！");
    }
}