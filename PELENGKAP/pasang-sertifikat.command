#!/bin/bash
set -e

LOKASI_SIMRS="$(cd -- "$(dirname -- "$0")" && pwd)"
SERTIFIKAT="$LOKASI_SIMRS/sertifikat/absensi-root.crt"

if [ ! -f "$SERTIFIKAT" ]; then
    echo "File sertifikat tidak ditemukan: $SERTIFIKAT"
    exit 1
fi

sudo /usr/bin/security add-trusted-cert \
    -d \
    -r trustRoot \
    -k /Library/Keychains/System.keychain \
    "$SERTIFIKAT"

echo "Sertifikat berhasil dipasang. Tutup lalu buka kembali SIMRS."
read -r -p "Tekan Enter untuk menutup..."
