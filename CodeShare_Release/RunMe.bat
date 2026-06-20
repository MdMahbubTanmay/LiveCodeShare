@echo off
title CodeShareJFX Launcher
echo Checking for Java environment...


java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Java is not installed or not in PATH.
    echo Please install JDK 17 or higher to run this project.
    pause
    exit
)

echo Starting CodeShareJFX...

java -jar CodeShareJFX-1.0-SNAPSHOT.jar

if %errorlevel% neq 0 (
    echo.
    echo [ERROR] The application crashed or could not start.
    pause
)
