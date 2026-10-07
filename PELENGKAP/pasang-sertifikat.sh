#!/usr/bin/env bash
set -e

LOKASI_SIMRS="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
SERTIFIKAT="$LOKASI_SIMRS/sertifikat/absensi-root.crt"

if [ "$(id -u)" -eq 0 ]; then
    echo "Jalankan sebagai pengguna biasa, bukan dengan sudo."
    exit 1
fi

if [ ! -f "$SERTIFIKAT" ]; then
    echo "File sertifikat tidak ditemukan: $SERTIFIKAT"
    exit 1
fi

if ! command -v certutil >/dev/null 2>&1; then
    sudo apt update
    sudo apt install -y libnss3-tools
fi

if [ -d "$HOME/.pki/nssdb" ]; then
    ABSENSI_NSS_DIR="$HOME/.pki/nssdb"
else
    ABSENSI_NSS_DIR="${XDG_DATA_HOME:-$HOME/.local/share}/pki/nssdb"
fi

mkdir -p "$ABSENSI_NSS_DIR"

if [ ! -f "$ABSENSI_NSS_DIR/cert9.db" ]; then
    certutil -N --empty-password -d "sql:$ABSENSI_NSS_DIR"
fi

certutil -A \
    -d "sql:$ABSENSI_NSS_DIR" \
    -n "Absensi RSUD RAZA CA" \
    -t "C,," \
    -i "$SERTIFIKAT"

echo "Sertifikat berhasil dipasang. Tutup lalu buka kembali SIMRS."
