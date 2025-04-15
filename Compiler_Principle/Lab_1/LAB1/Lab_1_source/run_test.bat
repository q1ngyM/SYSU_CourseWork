@echo off

echo Running Unit Tests...

java -cp "bin;lib/*" org.junit.platform.console.ConsoleLauncher --select-class test.PostfixTest

if %ERRORLEVEL% NEQ 0 (
    echo Some tests FAILED!
    exit /b 1
)

echo All tests PASSED!
