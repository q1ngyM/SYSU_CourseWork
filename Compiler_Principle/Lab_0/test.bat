@echo off
echo ===== Regression Test Lauching =====

:: 1. 编译代码
call compile.bat
if %ERRORLEVEL% NEQ 0 (
    echo Compilation FAILED
    exit /b 1
)

:: 2. 运行 JUnit 测试
echo >> Unit Testing...
java -cp "bin;lib/*" org.junit.platform.console.ConsoleLauncher ^
    --select-class TaxCalculatorSystem.TaxCalculatorTest ^
    --select-class TaxCalculatorSystem.TaxCLITest
if %ERRORLEVEL% NEQ 0 (
    echo Some tests FAILED!
    exit /b 1
)

echo All PASSED!
exit /b 0
