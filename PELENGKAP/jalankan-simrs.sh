#!/usr/bin/env bash
set -e

LOKASI_SIMRS="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
cd "$LOKASI_SIMRS"

if ! command -v java >/dev/null 2>&1; then
    echo "Java belum tersedia. Pasang dan aktifkan Java 17 dahulu."
    exit 1
fi

if [ ! -f "SIMRSKhanza.jar" ]; then
    echo "File SIMRSKhanza.jar tidak ditemukan. Sesuaikan nama JAR pada skrip."
    exit 1
fi

exec java -jar "SIMRSKhanza.jar"
