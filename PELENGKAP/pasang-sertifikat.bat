@echo off
setlocal
cd /d "%~dp0"

net session >nul 2>&1
if errorlevel 1 (
    echo Klik kanan file ini, lalu pilih Run as administrator.
    pause
    exit /b 1
)

if not exist "%~dp0sertifikat\absensi-root.crt" (
    echo File sertifikat\absensi-root.crt tidak ditemukan.
    pause
    exit /b 1
)

certutil -addstore Root "%~dp0sertifikat\absensi-root.crt"
if errorlevel 1 (
    echo Pemasangan sertifikat gagal.
    pause
    exit /b 1
)

echo Sertifikat absensi berhasil dipasang.
echo Tutup lalu buka kembali SIMRS.
pause
