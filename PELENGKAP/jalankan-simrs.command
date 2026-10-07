#!/bin/bash
set -e

LOKASI_SIMRS="$(cd -- "$(dirname -- "$0")" && pwd)"
cd "$LOKASI_SIMRS"

SIMRS_JAVA_HOME="$(/usr/libexec/java_home -v 17 2>/dev/null)" || {
    echo "Java 17 belum ditemukan. Pasang Java 17 terlebih dahulu."
    exit 1
}

if [ ! -f "SIMRSKhanza.jar" ]; then
    echo "File SIMRSKhanza.jar tidak ditemukan. Sesuaikan nama JAR pada skrip."
    exit 1
fi

"$SIMRS_JAVA_HOME/bin/java" -jar "SIMRSKhanza.jar"
read -r -p "Tekan Enter untuk menutup..."
