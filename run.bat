@echo off

echo 1) build 2) run 3)both
choice /c 123

if errorlevel 3 goto br
if errorlevel 2 goto r
if errorlevel 1 goto b
:b
set JAVA_HOME="C:\Program Files\Java\jdk-17"
call gradlew build
goto lv

:r
set JAVA_HOME="C:\Program Files\Java\jdk1.8.0_202"
call java -jar build/libs/examplemod-1.0.0.jar
goto lv

:br
set JAVA_HOME="C:\Program Files\Java\jdk-17"
call gradlew build
goto r

:lv
@echo on
