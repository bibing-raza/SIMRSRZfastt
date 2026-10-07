@echo off
setlocal
cd /d "%~dp0"

if not exist "SIMRSKhanza.jar" (
    echo File JAR SIMRS tidak ditemukan.
    pause
    exit /b 1
)

java -jar "SIMRSKhanza.jar"
pause
