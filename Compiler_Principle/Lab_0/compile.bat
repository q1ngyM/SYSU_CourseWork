@echo off
echo ===== COMPILING =====

:: 编译 Java 代码
javac -d bin -cp ".;lib/*" src/TaxCalculatorSystem/*.java test/*.java
if %ERRORLEVEL% NEQ 0 (
    exit /b 1
)

echo Compilation done.
exit /b 0