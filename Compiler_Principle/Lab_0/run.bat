@echo off

:: 1. 编译代码
call compile.bat

:: 2. 运行
java -cp "bin;lib/*" TaxCalculatorSystem.TaxCLI