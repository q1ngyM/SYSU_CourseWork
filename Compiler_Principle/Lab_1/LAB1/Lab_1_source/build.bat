@echo off
echo Compiling...

javac -d bin src\lab1\Postfix.java

javac -d bin -cp "lib/*;bin" src\test\PostfixTest.java
pause
