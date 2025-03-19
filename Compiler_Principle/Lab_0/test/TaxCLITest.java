package TaxCalculatorSystem;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * CLI测试
 */
public class TaxCLITest {

    private String runCLI(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        // 运行 CLI
        TaxCLI.main(new String[]{});

        return out.toString();
    }

    @Test
    public void testCalculateTaxWithValidInput() {
        String output = runCLI("1\n8000\n6\n");
        Pattern pattern = Pattern.compile("应缴个人所得税:\\s*([0-9]+\\.?[0-9]*)\\s*元");
        Matcher matcher = pattern.matcher(output);
        assertTrue(matcher.find());
    }

    @Test
    public void testCalculateTaxWithInvalidInput() {
        String output = runCLI("1\n*\n6\n");
        assertTrue(output.contains("无效输入，请输入数字"));
    }

    @Test
    public void testInvalidMenuChoiceNumber() {
        String output = runCLI("7\n6\n");
        assertTrue(output.contains("请输入 1-6 之间的选项"));
    }

    @Test
    public void testInvalidMenuChoiceCharacter() {
        String output = runCLI("a\n6\n");
        assertTrue(output.contains("请输入有效的数字"));
    }

    @Test
    public void testModifyTaxThreshold() {
        String output = runCLI("2\n6000\n6\n");
        assertTrue(output.contains("起征点已更新为: 6000"));
    }

    @Test
    public void testModifyTaxRate() {
        String output = runCLI("3\n5000\n0.05\n6\n");
        assertTrue(output.contains("税率已更新！"));
    }
}
