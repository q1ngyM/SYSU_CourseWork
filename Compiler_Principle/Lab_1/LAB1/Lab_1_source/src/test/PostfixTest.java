package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

public class PostfixTest {

    private String runPostfixProgram(String input) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("java", "lab1.Postfix");
        pb.redirectErrorStream(true);
        Process process = pb.start();

        // 写入输入数据
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        writer.write(input);
        writer.newLine(); // 模拟回车结束
        writer.flush();
        writer.close();

        // 读取程序输出
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        process.waitFor();
        return output.toString().trim();
    }

    @Test
    public void testCorrectInput1() throws IOException, InterruptedException {
        String input = "9-5+2";
        String output = runPostfixProgram(input);
        System.out.println("Actual output:\n" + output);
        assertTrue(output.contains("95-2+"));
    }
    


    @Test
    public void testCorrectInput2() throws IOException, InterruptedException {
        String input = "1-2+3-4+5-6+7-8+9-0";
        String output = runPostfixProgram(input);
        assertTrue(output.contains("12-3+4-5+6-7+8-9+0-"));
    }

    @Test
    public void testSyntaxAndLexicalErrors() throws IOException, InterruptedException {
        String input = "95+2";
        String output = runPostfixProgram(input);
        assertTrue(output.contains("Error Type: SYNTAX"));
        assertTrue(output.contains("Error Type: LEXICAL"));
        assertTrue(output.contains("9"));
        assertTrue(output.contains("2"));
    }

    @Test
    public void testMissingOperand() throws IOException, InterruptedException {
        String input = "9-5+-2";
        String output = runPostfixProgram(input);
        assertTrue(output.contains("Error Type: SYNTAX"));
        assertTrue(output.contains("95-"));
        assertTrue(output.contains("2"));
    }

    @Test
    public void testUnexpectedCharacters() throws IOException, InterruptedException {
        String input = "1 8+4*9+4-6";
        String output = runPostfixProgram(input);
        assertTrue(output.contains("Error Type: LEXICAL"));
        assertTrue(output.contains("84+"));
        assertTrue(output.contains("94+6-"));
    }
}
