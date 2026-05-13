<?php
require_once __DIR__ . '/config.php';

$pdo = db();

$no_rawat = isset($_GET['no_rawat'])
    ? trim($_GET['no_rawat'])
    : '';

if ($no_rawat == '') {
    die('No. Rawat tidak ditemukan.');
}

// ===  ===  ===  ===  ===  ===  == LOGO ===  ===  ===  ===  ===  ===  ==
$stmtLogo = $pdo->query('SELECT logo FROM setting LIMIT 1');
$dLogo = $stmtLogo->fetch();

// ===  ===  ===  ===  ===  ===  == DATA PASIEN ===  ===  ===  ===  ===  ===  ==
$sqlPasien = "
    SELECT 
        p.no_rkm_medis,
        p.nm_pasien,
        DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') AS tgllahir
    FROM reg_periksa rp
    INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis
    WHERE rp.no_rawat = ?
";

$stmtPasien = $pdo->prepare($sqlPasien);
$stmtPasien->execute([$no_rawat]);
$dPasien = $stmtPasien->fetch();

if (!$dPasien) {
    die('Data pasien tidak ditemukan.');
}

// ===  ===  ===  ===  ===  ===  == DATA PARTOGRAF ===  ===  ===  ===  ===  ===  ==
$sqlPartograf = "
    SELECT 
        *,
        DATE_FORMAT(tgl_masuk,'%d-%m-%Y') AS tglmasuk,
        TIME_FORMAT(jam_masuk,'%H:%i Wita') AS jammsk,
        TIME_FORMAT(jam_ketuban,'%H:%i Wita') AS jamketuban
    FROM partograf_persalinan
    WHERE no_rawat = ?
";

$stmtPartograf = $pdo->prepare($sqlPartograf);
$stmtPartograf->execute([$no_rawat]);
$dPartograf = $stmtPartograf->fetch();

if (!$dPartograf) {
    $dPartograf = [
        'gravida' => '',
        'paritas' => '',
        'abortus' => '',
        'tglmasuk' => '',
        'jammsk' => '',
        'ketuban_pecah' => '',
        'jamketuban' => ''
    ];
}

// ===  ===  ===  ===  ===  ===  == DATA DJJ ===  ===  ===  ===  ===  ===  ==
$sqlDjj = "
    SELECT nadi, (SELECT jeda_menit FROM partograf_djj d2 WHERE d2.no_rawat = d1.no_rawat ORDER BY waktu_simpan DESC LIMIT 1) AS jeda_menit,
		TIME_FORMAT(jam_djj,'%H:%i') AS jam_djj FROM partograf_djj d1
	WHERE no_rawat = ?
	ORDER BY jam_djj ASC
";

$stmtDjj = $pdo->prepare($sqlDjj);
$stmtDjj->execute([$no_rawat]);
$dataDjj = $stmtDjj->fetchAll(PDO::FETCH_ASSOC);

$jedaMenitDjj = '';

if (!empty($dataDjj)) {
    $jedaMenitDjj = $dataDjj[0]['jeda_menit'];
}

// ===  ===  ===  ===  ===  ===  == DATA AIR KETUBAN & MULASE ===  ===  ===  ===  ===  ===  ==
$sqlAirKetuban = "
    SELECT 
        LEFT(air_ketuban,1) AS airktban,
        LEFT(mulase,1) AS mulnya,
        TIME_FORMAT(pukul,'%H:%i') AS jam_airktb
    FROM partograf_air_ketuban
    WHERE no_rawat = ?
    ORDER BY pukul ASC
";

$stmtAirKetuban = $pdo->prepare($sqlAirKetuban);
$stmtAirKetuban->execute([$no_rawat]);
$dataAirKetuban = $stmtAirKetuban->fetchAll(PDO::FETCH_ASSOC);

// ===  ===  ===  ===  ===  ===  == DATA PEMBUKAAN SERVIKS ===  ===  ===  ===  ===  ===  ==
$sqlServiks = "
    SELECT 
        *,
        TIME_FORMAT(jam,'%H:%i') AS jamservik
    FROM partograf_pembukaan_serviks
    WHERE no_rawat = ?
    ORDER BY waktu_ke ASC
";

$stmtServiks = $pdo->prepare($sqlServiks);
$stmtServiks->execute([$no_rawat]);
$dataServiks = $stmtServiks->fetchAll(PDO::FETCH_ASSOC);

// ===  ===  ===  ===  ===  ===  == DATA KONTRAKSI ===  ===  ===  ===  ===  ===  ==
$sqlKontraksi = "
    SELECT * FROM partograf_kontraksi
    WHERE no_rawat = ?
    ORDER BY lajur_kontraksi ASC
";

$stmtKontraksi = $pdo->prepare($sqlKontraksi);
$stmtKontraksi->execute([$no_rawat]);
$dataKontraksi = $stmtKontraksi->fetchAll(PDO::FETCH_ASSOC);

// ===  ===  ===  ===  ===  ===  == DATA OKSITOSIN ===  ===  ===  ===  ===  ===  ==
$sqlOksitosin = "
    SELECT * FROM partograf_oksitosin
    WHERE no_rawat = ?
    ORDER BY waktu_simpan ASC
";

$stmtOksitosin = $pdo->prepare($sqlOksitosin);
$stmtOksitosin->execute([$no_rawat]);
$dataOksitosin = $stmtOksitosin->fetchAll(PDO::FETCH_ASSOC);

// ==================== DATA OBAT DAN CAIRAN IV ====================
$sqlObatCairan = "
    SELECT * FROM partograf_obat_cairan
    WHERE no_rawat = ?
    ORDER BY waktu_simpan ASC
";

$stmtObatCairan = $pdo->prepare($sqlObatCairan);
$stmtObatCairan->execute([$no_rawat]);
$dataObatCairan = $stmtObatCairan->fetchAll(PDO::FETCH_ASSOC);

// ==================== DATA NADI & TENSI ====================
$sqlNadiTensi = "
    SELECT *, TIME_FORMAT(pukul,'%H:%i') AS jamnaditd
    FROM partograf_nadi_tensi
    WHERE no_rawat = ?
    ORDER BY pukul ASC
";

$stmtNadiTensi = $pdo->prepare($sqlNadiTensi);
$stmtNadiTensi->execute([$no_rawat]);
$dataNadiTensi = $stmtNadiTensi->fetchAll(PDO::FETCH_ASSOC);

// ==================== DATA SUHU ====================
$sqlSuhu = "
    SELECT *, TIME_FORMAT(pukul,'%H:%i') AS jamsuhu
    FROM partograf_suhu
    WHERE no_rawat = ?
    ORDER BY pukul ASC
";

$stmtSuhu = $pdo->prepare($sqlSuhu);
$stmtSuhu->execute([$no_rawat]);
$dataSuhu = $stmtSuhu->fetchAll(PDO::FETCH_ASSOC);

// ==================== DATA URIN ====================
$sqlUrin = "
    SELECT *, TIME_FORMAT(pukul,'%H:%i') AS jamurin
    FROM partograf_urin
    WHERE no_rawat = ?
    ORDER BY pukul ASC
";

$stmtUrin = $pdo->prepare($sqlUrin);
$stmtUrin->execute([$no_rawat]);
$dataUrin = $stmtUrin->fetchAll(PDO::FETCH_ASSOC);

// ==================== DATA CATATAN PERSALINAN ====================
$sqlCatatanPersalinan = "
    SELECT *, date_format(pc.cttn_tanggal,'%d-%m-%Y') tglcttn, pg.nama nmBidan
    FROM partograf_catatan_persalinan pc inner join pegawai pg on pg.nik=pc.cttn_nip_bidan 
    WHERE pc.no_rawat = ?
";

$stmtCatatanPersalinan = $pdo->prepare($sqlCatatanPersalinan);
$stmtCatatanPersalinan->execute([$no_rawat]);
$dCatatanPersalinan = $stmtCatatanPersalinan->fetch(PDO::FETCH_ASSOC);

if (!$dCatatanPersalinan) {
    $dCatatanPersalinan = [];
}

// ==================== DATA PEMANTAUAN PERSALINAN KALA IV ====================
$sqlKala4 = "
    SELECT *, time_format(waktu,'%H:%i Wita') jamkala4 FROM partograf_kala_4
    WHERE no_rawat = ?
    ORDER BY urutan ASC
";

$stmtKala4 = $pdo->prepare($sqlKala4);
$stmtKala4->execute([$no_rawat]);
$dataKala4 = $stmtKala4->fetchAll(PDO::FETCH_ASSOC);

// ==================== DATA PEMANTAUAN KALA IV ====================
$sqlPemantauanKala4 = "
    SELECT * FROM partograf_pemantauan_kala4
    WHERE no_rawat = ?
";

$stmtPemantauanKala4 = $pdo->prepare($sqlPemantauanKala4);
$stmtPemantauanKala4->execute([$no_rawat]);

$dPemantauanKala4 = $stmtPemantauanKala4->fetch(PDO::FETCH_ASSOC);
?>

<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <title>Partograf Persalinan</title>

    <style>
        :root {
            --garis: 1px solid #000;
        }

        body {
            margin: 0;
            padding: 20px;
            background: #fff;
            font-family: Tahoma, sans-serif;
        }

        .container {
            width: 794px;
            margin: auto;
        }

        .kode-form {
            text-align: right;
            font-size: 16px;
            font-weight: bold;
            padding-right: 5px;
            margin-bottom: 2px;
        }

        .wrapper {
            border: none;
            padding-top: 1px;
        }

        .header {
            width: 100%;
            border-collapse: collapse;
        }

        .header td {
            vertical-align: middle;
        }

        .logo {
            width: 70px;
            text-align: center;
            border-top: var(--garis);
            border-bottom: var(--garis);
            border-left: var(--garis);
        }

        .logo img {
            width: 60px;
            height: 60px;
            object-fit: contain;
        }

        .judul-rs {
            font-size: 16px;
            font-weight: bold;
            line-height: 1.4;
            padding-left: 0px;
            border-top: var(--garis);
            border-bottom: var(--garis);
        }

        .identitas {
            width: 320px;
            padding: 10px;
            font-size: 13px;
            border: var(--garis);
        }

        .identitas table {
            width: 100%;
            border-collapse: collapse;
        }

        .identitas td {
            border: none;
            padding: 2px 0;
        }

        .label-identitas {
            width: 70px;
            white-space: nowrap;
        }

        .titik-dua {
            width: 10px;
            text-align: center;
        }

        .judul-partograf {
            background: #bfbfbf;
            color: #000;
            text-align: center;
            font-size: 18px;
            font-weight: bold;
            letter-spacing: 1px;
            padding: 6px 0;
            line-height: 1;
            border-top: var(--garis);
            border-bottom: var(--garis);
        }

        .info-persalinan {
            width: 100%;
            border-collapse: collapse;
            font-size: 13px;
            margin-top: 6px;
            margin-bottom: 6px;
            border: none;
        }

        .info-persalinan td {
            border: none !important;
            padding: 2px 2px;
            font-weight: normal;
        }

        .label-info {
            width: 85px;
            white-space: nowrap;
            font-weight: normal;
            padding-left: 20px !important;
        }

        .titik-dua-info {
            width: 10px;
            text-align: center;
        }

        .isi-info {
            width: 100px;
        }

        .grafik-djj {
            margin: -20px 10px 0px 10px;
        }

        .judul-grafik {
            text-align: left;
            font-size: 13px;
            font-weight: normal;
        }

        .grafik-djj svg {
            width: 100%;
            height: 280px;
        }

        .air-ketuban-box {
            margin: 0 30px 2px 10px;
            display: flex;
            align-items: flex-start;
        }

        .label-air-ketuban {
            width: 90px;
            font-size: 16px;
            line-height: 1.4;
            position: relative;
        }

        .text-air-ketuban {
            position: absolute;
            font-size: 13px;
            top: 2px;
            right: 10px;
            text-align: right;
            width: 100%;
        }

        .text-mulase {
            position: absolute;
            font-size: 13px;
            top: 23px;
            right: 10px;
            text-align: right;
            width: 100%;
        }

        .tabel-air-ketuban {
            border-collapse: collapse;
        }

        .tabel-air-ketuban td {
            width: 18px;
            height: 18px;
            border: var(--garis);
            text-align: center;
            vertical-align: middle;
            font-size: 12px;
            font-weight: bold;
        }

        .tabel-air-ketuban .jam-airktb {
            height: 25px;
            border: none;
            vertical-align: top;
            font-size: 10px;
            font-weight: normal;
            position: relative;
        }

        .text-jam-airktb {
            display: inline-block;
            transform: rotate(-90deg);
            transform-origin: center center;

            white-space: nowrap;
            margin-top: 10px;
        }

        .grafik-serviks-box {
            margin: 0 10px 0px 10px;

        }

        .grafik-serviks-box svg {
            width: 100%;
        }

        .judul-serviks {
            font-size: 16px;
            font-weight: normal;
            margin-bottom: 5px;
        }

        @page {
            size: A4 portrait;

        }

        @media print {

            @page {
                size: A4 portrait;
                margin: 5mm;
            }

            * {
                -webkit-print-color-adjust: exact !important;
                print-color-adjust: exact !important;
                box-sizing: border-box;
            }

            html,
            body {
                margin: 0;
                padding: 0;
            }

            .container {
                width: 195mm;
                margin: 0 auto;
            }

            .wrapper {
                border: none !important;
            }

            .print-page {
                border: 1px solid #000;
                min-height: 270mm;
                margin: 0;
                padding: 0 0 3mm 0;
            }

            .halaman-dua {
                page-break-before: always;
                break-before: page;
            }

            .obat-cairan-svg-box {
                page-break-before: auto !important;
                break-before: auto !important;
                border-top: none !important;
                padding-top: 12px !important;
            }
        }

        .print-page {
            border: var(--garis);
            min-height: 270mm;
            margin-bottom: 10px;
            background: #fff;
        }

        .grafik-kontraksi-box {
            margin: 0 10px 25px 10px;
        }

        .grafik-kontraksi-box svg {
            width: 100%;
        }

        .label-kontraksi {
            font-size: 13px;
        }

        .oksitosin-box {
            margin: -55px 10px 10px 10px;
            display: flex;
            align-items: flex-start;
        }

        .label-oksitosin {
            width: 170px;
            font-size: 13px;
            line-height: 1.4;
        }

        .tabel-oksitosin {
            border-collapse: collapse;
        }

        .tabel-oksitosin td {
            width: 16px;
            height: 26px;
            border: var(--garis);
            text-align: center;
            vertical-align: middle;
            font-size: 9px;
            padding: 2px;
            position: relative;
            overflow: hidden;
        }

        .text-oksitosin-vertikal {
            position: absolute;
            left: 50%;
            top: 50%;

            transform: translate(-50%, -50%) rotate(-90deg);
            transform-origin: center center;

            font-size: 8px;
            line-height: 1.1;
            text-align: center;

            white-space: normal;

            padding: 2px 4px;

            box-sizing: border-box;
        }

        .label-oksitosin {
            width: 75px;
            font-size: 11.5px;
            display: flex;
            flex-direction: column;
        }

        .label-oksitosin-baris {
            height: 26px;
            box-sizing: border-box;
            padding-top: 4px;
        }

        .text-oksitosin-normal {
            padding: 1px;
        }

        .obat-cairan-box {
            margin: 0 10px 0 10px;
            display: flex;
            align-items: flex-start;
        }

        .label-obat-cairan {
            width: 185px;
            font-size: 11.5px;
            line-height: 1.2;
            padding-top: 8px;
            box-sizing: border-box;
        }

        .tabel-obat-cairan {
            border-collapse: collapse;
        }

        .tabel-obat-cairan td {
            width: auto;
            height: 50px;
            border: var(--garis);
            text-align: center;
            vertical-align: middle;
            font-size: 9px;
            padding: 2px;
            position: relative;
            overflow: hidden;
        }

        .text-obat-cairan-normal {
            padding: 2px;
        }

        .text-obat-cairan-vertikal {
            position: absolute;
            left: 50%;
            top: 50%;

            transform: translate(-50%, -50%) rotate(-90deg);
            transform-origin: center center;

            font-size: 8.5px;
            line-height: 1.1;
            text-align: center;

            padding: 2px 4px;
            box-sizing: border-box;
            white-space: normal;
        }

        .grafik-nadi-box {
            margin: -1px 10px 0 10px;
        }

        .grafik-nadi-box svg {
            width: 100%;
        }

        .obat-cairan-svg-box {
            margin: 12px 10px 0 10px;
        }

        .obat-cairan-svg-box svg {
            width: 100%;
            display: block;
        }

        .grafik-suhu-box {
            margin: 0 10px 0 10px;
        }

        .grafik-suhu-box svg {
            width: 100%;
            display: block;
        }

        .grafik-urin-box {
            margin: -5px 10px 0 10px;
        }

        .grafik-urin-box svg {
            width: 100%;
            display: block;
        }

        .judul-bawah {
            margin-top: 3px;
        }

        .catatan-persalinan-box {
            display: grid;
            grid-template-columns: 1fr 1fr;
            column-gap: 16px;
            padding: 8px 10px 0 10px;
            font-size: 12px;
            line-height: 1.25;
            min-height: auto !important;
            height: auto !important;
            align-items: start;
        }

        .catatan-kolom {
            border: none;
            min-height: unset;
            height: auto;

            margin-bottom: 0 !important;
            padding-bottom: 0 !important;
        }

        .catatan-title {
            font-weight: bold;
            margin-bottom: 6px;
        }

        .catatan-row {
            display: flex;
            gap: 4px;
            margin-bottom: 3px;
        }

        .catatan-no {
            width: 22px;
            text-align: right;
        }

        .catatan-isi {
            flex: 1;
            line-height: 1.3;
            white-space: normal;
        }

        .garis-isi {
            border-bottom: 1px dotted #000;
            display: inline;
            white-space: normal;
            word-break: break-word;
            overflow-wrap: anywhere;
        }

        .halaman-berikutnya {
            page-break-before: always;
            break-before: page;
            padding-top: 8px;
        }

        .pemantauan-kala-iv {
            margin-top: 2px;
            margin-left: 10px;
            margin-bottom: 2px;

            font-size: 12px;
            font-weight: bold;

            line-height: 1.2;
        }

        .tabel-kala-iv {
            width: calc(100% - 20px);
            margin: 4px 10px 0 10px;
            border-collapse: collapse;
            font-size: 12px;
        }

        .tabel-kala-iv th,
        .tabel-kala-iv td {
            border: 1px solid #000;
            padding: 2px 4px;
        }

        .tabel-kala-iv th {
            font-weight: bold;
            text-align: center;
            vertical-align: middle;
            height: 28px;
        }

        .tabel-kala-iv td {
            vertical-align: top;
            text-align: center;
            height: 22px;
        }

        /* KHUSUS KOLOM TINGGI FUNDUS SAMPAI DARAH */
        .tabel-kala-iv td.kiri-atas {
            text-align: left;
            vertical-align: top;
            padding-left: 4px;
        }

        .tabel-kala-iv .kol-jam {
            width: 42px;
        }

        .tabel-kala-iv .kol-waktu {
            width: 70px;
        }

        .tabel-kala-iv .kol-td {
            width: 72px;
        }

        .tabel-kala-iv .kol-kecil {
            width: 42px;
        }

        .tabel-kala-iv .kol-sedang {
            width: 120px;
        }

        .keterangan-kala-iv {
            width: calc(100% - 20px);
            margin: 6px 10px 0 10px;
            font-size: 12px;
            line-height: 1.3;
        }

        .keterangan-kala-iv .catatan-row {
            margin-bottom: 3px;
        }

        .keterangan-kala-iv .catatan-isi {
            text-align: left;
            white-space: normal;
            word-break: break-word;
            overflow-wrap: anywhere;
        }
    </style>
</head>

<body>

    <div class='container'>

        <div class='wrapper'>

            <div class='kode-form'>
                RM 9.1.2 REV 02
            </div>

            <div class="print-page">

                <table class='header'>
                    <tr>
                        <td class='logo'>
                            <?php if (!empty($dLogo['logo'])): ?>
                                <img src="data:image/png;base64,<?= base64_encode($dLogo['logo']) ?>">
                            <?php endif;
                            ?>
                        </td>

                        <td class='judul-rs'>
                            RUMAH SAKIT UMUM DAERAH<br>
                            RATU ZALECHA MARTAPURA
                        </td>

                        <td class='identitas'>
                            <table>
                                <tr>
                                    <td class='label-identitas'>No. RM</td>
                                    <td class='titik-dua'>:</td>
                                    <td><?= htmlspecialchars($dPasien['no_rkm_medis']) ?></td>
                                </tr>
                                <tr>
                                    <td class='label-identitas'>Nama</td>
                                    <td class='titik-dua'>:</td>
                                    <td><?= htmlspecialchars($dPasien['nm_pasien']) ?></td>
                                </tr>
                                <tr>
                                    <td class='label-identitas'>Tgl. lahir</td>
                                    <td class='titik-dua'>:</td>
                                    <td><?= htmlspecialchars($dPasien['tgllahir']) ?></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>

                <div class='judul-partograf'>
                    PARTOGRAF PERSALINAN
                </div>

                <table class='info-persalinan' style='font-weight:normal;'>
                    <tr>
                        <td class='label-info'>Gravida</td>
                        <td class='titik-dua-info'>:</td>
                        <td class='isi-info'><?= htmlspecialchars($dPartograf['gravida']) ?></td>

                        <td class='label-info'>Paritas</td>
                        <td class='titik-dua-info'>:</td>
                        <td class='isi-info'><?= htmlspecialchars($dPartograf['paritas']) ?></td>

                        <td class='label-info'>Abortus</td>
                        <td class='titik-dua-info'>:</td>
                        <td class='isi-info'><?= htmlspecialchars($dPartograf['abortus']) ?></td>
                    </tr>

                    <tr>
                        <td class='label-info'>Tgl. Masuk</td>
                        <td class='titik-dua-info'>:</td>
                        <td class='isi-info'><?= htmlspecialchars($dPartograf['tglmasuk']) ?></td>

                        <td class='label-info'>Jam Masuk</td>
                        <td class='titik-dua-info'>:</td>
                        <td class='isi-info'><?= htmlspecialchars($dPartograf['jammsk']) ?></td>

                        <td class='label-info'>Ketuban Pecah Sejak Jam</td>
                        <td class='titik-dua-info'>:</td>
                        <td class='isi-info'><?= htmlspecialchars($dPartograf['jamketuban']) ?></td>
                    </tr>
                </table>

                <!-- GRAFIK DENYUT JANTUNG JANIN -->
                <div class='grafik-djj'>

                    <div class='judul-grafik'></div>

                    <?php
                    $width  = 900;
                    $height = 300;

                    $left   = 120;
                    $right  = 20;
                    $top    = 20;
                    $bottom = 50;

                    $minY = 80;
                    $maxY = 200;

                    $plotWidth  = $width - $left - $right;
                    $plotHeight = $height - $top - $bottom;

                    $points = [];
                    $jumlahDjj = count($dataDjj);

                    if ($jumlahDjj > 0) {
                        foreach ($dataDjj as $i => $row) {
                            $nadi = (float)$row['nadi'];

                            $x = ($jumlahDjj == 1)
                                ? $left + ($plotWidth / 2)
                                : $left + (($plotWidth / ($jumlahDjj - 1)) * $i);

                            $y = $top + (($maxY - $nadi) / ($maxY - $minY)) * $plotHeight;

                            $points[] = [
                                'x' => $x,
                                'y' => $y,
                                'nadi' => $nadi,
                                'jam' => $row['jam_djj']
                            ];
                        }
                    }
                    ?>

                    <svg viewBox="0 0 <?= $width ?> <?= $height ?>" xmlns='http://www.w3.org/2000/svg'>

                        <text x='10' y='70' font-size='13'>Denyut</text>
                        <text x='10' y='88' font-size='13'>Jantung</text>
                        <text x='10' y='106' font-size='13'>Janin</text>

                        <?php if ($jedaMenitDjj !== ''): ?>
                            <text x='10' y='124' font-size='13'>
                                ( <?= htmlspecialchars($jedaMenitDjj) ?>/menit )
                            </text>
                        <?php endif;
                        ?>

                        <?php for ($nilai = $minY; $nilai <= $maxY; $nilai += 10): ?>
                            <?php
                            $y = $top + (($maxY - $nilai) / ($maxY - $minY)) * $plotHeight;
                            ?>
                            <line x1="<?= $left ?>" y1="<?= $y ?>" x2="<?= $width - $right ?>" y2="<?= $y ?>"
                                stroke='#ccc' stroke-width='1' />
                            <text x="<?= $left - 35 ?>" y="<?= $y + 4 ?>" font-size='11'><?= $nilai ?></text>
                        <?php endfor;
                        ?>

                        <line x1="<?= $left ?>" y1="<?= $top ?>" x2="<?= $left ?>" y2="<?= $height - $bottom ?>"
                            stroke='#000' stroke-width='1' />

                        <line x1="<?= $left ?>" y1="<?= $height - $bottom ?>" x2="<?= $width - $right ?>" y2="<?= $height - $bottom ?>"
                            stroke='#000' stroke-width='1' />

                        <?php if ($jumlahDjj > 0): ?>

                            <?php
                            $path = '';

                            if (count($points) > 0) {
                                $path = 'M ' . $points[0]['x'] . ' ' . $points[0]['y'] . ' ';

                                for (
                                    $i = 1;
                                    $i < count($points);
                                    $i++
                                ) {
                                    $x1 = $points[$i - 1]['x'];
                                    $y1 = $points[$i - 1]['y'];

                                    $x2 = $points[$i]['x'];
                                    $y2 = $points[$i]['y'];

                                    $cx = ($x1 + $x2) / 2;

                                    $path .= "C $cx $y1, $cx $y2, $x2 $y2 ";
                                }
                            }
                            ?>

                            <path d="<?= trim($path) ?>"
                                fill='none'
                                stroke='#000'
                                stroke-width='2'
                                stroke-linecap='round'
                                stroke-linejoin='round' />

                            <?php foreach ($points as $p): ?>

                                <!-- GARIS BANTU VERTIKAL -->
                                <line x1="<?= $p['x'] ?>"
                                    y1="<?= $top ?>"
                                    x2="<?= $p['x'] ?>"
                                    y2="<?= $height - $bottom ?>"
                                    stroke='#ccc'
                                    stroke-width='1' />

                                <circle cx="<?= $p['x'] ?>" cy="<?= $p['y'] ?>" r='4' fill='#000' />

                                <text x="<?= $p['x'] - 12 ?>" y="<?= $p['y'] - 8 ?>" font-size='10'>
                                    <?= htmlspecialchars($p['nadi']) ?>
                                </text>

                                <text
                                    x="<?= $p['x'] ?>"
                                    y="<?= $height - 40 ?>"
                                    font-size='10'
                                    text-anchor='end'
                                    transform="rotate(-90 <?= $p['x'] ?> <?= $height - 40 ?>)">
                                    <?= htmlspecialchars($p['jam']) ?>
                                </text>
                            <?php endforeach;
                            ?>

                        <?php else: ?>

                            <text x='360' y='120' font-size='14'>
                                Data DJJ belum tersedia
                            </text>

                        <?php endif;
                        ?>

                    </svg>

                </div>

                <!-- DATA AIR KETUBAN & MULASE -->
                <div class='air-ketuban-box'>

                    <div class='label-air-ketuban'>
                        <div class='text-air-ketuban'>
                            Air ketuban
                        </div>

                        <div class='text-mulase'>
                            ( Mulase )
                        </div>
                    </div>

                    <table class='tabel-air-ketuban'>
                        <tr>
                            <?php for ($i = 0; $i < 32; $i++): ?>
                                <td>
                                    <?= isset($dataAirKetuban[$i]) ? htmlspecialchars($dataAirKetuban[$i]['airktban']) : '' ?>
                                </td>
                            <?php endfor;
                            ?>
                        </tr>

                        <tr>
                            <?php for ($i = 0; $i < 32; $i++): ?>
                                <td>
                                    <?= isset($dataAirKetuban[$i]) ? htmlspecialchars($dataAirKetuban[$i]['mulnya']) : '' ?>
                                </td>
                            <?php endfor;
                            ?>
                        </tr>

                        <tr>
                            <?php for ($i = 0; $i < 32; $i++): ?>
                                <td class='jam-airktb'>
                                    <?php if (isset($dataAirKetuban[$i])): ?>
                                        <span class='text-jam-airktb'>
                                            <?= htmlspecialchars($dataAirKetuban[$i]['jam_airktb']) ?>
                                        </span>
                                    <?php endif;
                                    ?>
                                </td>
                            <?php endfor;
                            ?>
                        </tr>
                    </table>

                </div>

                <!-- GRAFIK PEMBUKAAN SERVIKS -->
                <div class='grafik-serviks-box'>

                    <?php
                    $widthServik  = 900;
                    $heightServik = 360;

                    $leftServik   = 80;
                    $rightServik  = 20;
                    $topServik    = 25;
                    $bottomServik = 60;

                    $minServikY = 0;
                    $maxServikY = 10;

                    $jumlahKolomServik = 16;

                    $plotServikWidth  = $widthServik - $leftServik - $rightServik;
                    $plotServikHeight = $heightServik - $topServik - $bottomServik;

                    function xServikTitik($waktu, $leftServik, $plotServikWidth, $jumlahKolomServik)
                    {
                        $lebarKolom = $plotServikWidth / $jumlahKolomServik;
                        return $leftServik + (($waktu - 1) * $lebarKolom) + ($lebarKolom / 2);
                    }

                    function xServikGaris($kolom, $leftServik, $plotServikWidth, $jumlahKolomServik)
                    {
                        return $leftServik + (($kolom - 1) / $jumlahKolomServik) * $plotServikWidth;
                    }

                    function yServik($nilai, $topServik, $plotServikHeight, $minServikY, $maxServikY)
                    {
                        return $topServik + (($maxServikY - $nilai) / ($maxServikY - $minServikY)) * $plotServikHeight;
                    }

                    function teksVertikalWrap($teks, $maksKarakterPerBaris = 8)
                    {

                        $teks = trim($teks);

                        if ($teks === '') {
                            return '';
                        }

                        $potongan = str_split($teks, $maksKarakterPerBaris);

                        // Maksimal hanya 2 baris
                        $baris1 = isset($potongan[0]) ? $potongan[0] : '';
                        $baris2 = '';

                        if (count($potongan) > 1) {
                            $baris2 = implode('', array_slice($potongan, 1));
                        }

                        return htmlspecialchars($baris1)
                            . ($baris2 !== '' ? '<br>' . htmlspecialchars($baris2) : '');
                    }

                    function teksSvg2Baris($teks, $maksKarakterPerBaris = 8)
                    {
                        $teks = trim($teks);

                        if ($teks === '') {
                            return ['', ''];
                        }

                        $potongan = str_split($teks, $maksKarakterPerBaris);

                        $baris1 = isset($potongan[0]) ? $potongan[0] : '';
                        $baris2 = '';

                        if (count($potongan) > 1) {
                            $baris2 = implode('', array_slice($potongan, 1));
                        }

                        return [
                            htmlspecialchars($baris1),
                            htmlspecialchars($baris2)
                        ];
                    }

                    function valCatatan($data, $field)
                    {
                        return htmlspecialchars(isset($data[$field]) ? $data[$field] : '');
                    }

                    $pointsX = [];
                    $pointsO = [];
                    ?>

                    <svg viewBox="0 0 <?= $widthServik ?> <?= $heightServik ?>" xmlns='http://www.w3.org/2000/svg'>

                        <?php
                        $centerGrafikY = $topServik + ($plotServikHeight / 2);
                        ?>

                        <text x='20' y="<?= $centerGrafikY ?>" font-size='13' text-anchor='middle'
                            transform="rotate(-90 20 <?= $centerGrafikY ?>)">
                            Pembukaan serviks ( cm ) beri tanda X
                        </text>

                        <text x='37' y="<?= $centerGrafikY ?>" font-size='13' text-anchor='middle'
                            transform="rotate(-90 37 <?= $centerGrafikY ?>)">
                            Turunnya Kepala beri tanda O
                        </text>

                        <text x="<?= $leftServik - 28 ?>" y="<?= $centerGrafikY - 95 ?>" font-size='9' text-anchor='middle'
                            transform="rotate(-90 <?= $leftServik - 28 ?> <?= $centerGrafikY - 95 ?>)">
                            centimeter ( Cm )
                        </text>

                        <!-- GRID HORIZONTAL -->
                        <?php for ($cm = 0; $cm <= 10; $cm++): ?>
                            <?php $y = yServik($cm, $topServik, $plotServikHeight, $minServikY, $maxServikY);
                            ?>

                            <line x1="<?= $leftServik ?>" y1="<?= $y ?>"
                                x2="<?= $widthServik - $rightServik ?>" y2="<?= $y ?>"
                                stroke='#000' stroke-width='1' />

                            <text x="<?= $leftServik - 18 ?>" y="<?= $y + 4 ?>" font-size='12'>
                                <?= $cm ?>
                            </text>
                        <?php endfor;
                        ?>

                        <!-- GRID VERTIKAL BATAS KOLOM -->
                        <?php for ($batas = 1; $batas <= 17; $batas++): ?>
                            <?php $x = xServikGaris($batas, $leftServik, $plotServikWidth, $jumlahKolomServik);
                            ?>

                            <line x1="<?= $x ?>" y1="<?= $topServik ?>"
                                x2="<?= $x ?>" y2="<?= $topServik + $plotServikHeight ?>"
                                stroke='#000' stroke-width='1' />
                        <?php endfor;
                        ?>

                        <!-- GARIS VERTIKAL TITIK WAKTU -->
                        <?php for ($wk = 1; $wk <= 16; $wk++): ?>
                            <?php $x = xServikTitik($wk, $leftServik, $plotServikWidth, $jumlahKolomServik);
                            ?>

                            <line x1="<?= $x ?>" y1="<?= $topServik ?>"
                                x2="<?= $x ?>" y2="<?= $topServik + $plotServikHeight ?>"
                                stroke='#000' stroke-width='1' />
                        <?php endfor;
                        ?>

                        <rect x="<?= $leftServik ?>" y="<?= $topServik ?>"
                            width="<?= $plotServikWidth ?>" height="<?= $plotServikHeight ?>"
                            fill='none' stroke='#000' stroke-width='1' />

                        <!-- FASE AKTIF MERGE KOLOM 1 SAMPAI 3 -->
                        <?php
                        $xFaseAktif1 = xServikGaris(1, $leftServik, $plotServikWidth, $jumlahKolomServik);
                        $xFaseAktif2 = xServikGaris(4, $leftServik, $plotServikWidth, $jumlahKolomServik);

                        $yFaseAktif1 = $topServik;
                        $yFaseAktif2 = yServik(9, $topServik, $plotServikHeight, $minServikY, $maxServikY);

                        $lebarFaseAktif = $xFaseAktif2 - $xFaseAktif1;
                        $tinggiFaseAktif = $yFaseAktif2 - $yFaseAktif1;
                        ?>

                        <rect x="<?= $xFaseAktif1 ?>"
                            y="<?= $yFaseAktif1 ?>"
                            width="<?= $lebarFaseAktif ?>"
                            height="<?= $tinggiFaseAktif ?>"
                            fill='#fff'
                            stroke='#000'
                            stroke-width='1' />

                        <text x="<?= $xFaseAktif1 + ($lebarFaseAktif / 2) ?>"
                            y="<?= $yFaseAktif1 + ($tinggiFaseAktif / 2) + 5 ?>"
                            font-size='16'
                            font-weight='bold'
                            text-anchor='middle'>
                            FASE AKTIF
                        </text>

                        <!-- GARIS WASPADA -->
                        <?php
                        $xWaspada1 = xServikTitik(1, $leftServik, $plotServikWidth, $jumlahKolomServik);
                        $yWaspada1 = yServik(4, $topServik, $plotServikHeight, $minServikY, $maxServikY);

                        $xWaspada2 = xServikTitik(6, $leftServik, $plotServikWidth, $jumlahKolomServik);
                        $yWaspada2 = yServik(10, $topServik, $plotServikHeight, $minServikY, $maxServikY);

                        $sudutWaspada = rad2deg(
                            atan2(
                                ($yWaspada2 - $yWaspada1),
                                ($xWaspada2 - $xWaspada1)
                            )
                        );

                        $textWaspadaX = ($xWaspada1 + $xWaspada2) / 2;
                        $textWaspadaY = ($yWaspada1 + $yWaspada2) / 2 - 12;
                        ?>

                        <line x1="<?= $xWaspada1 ?>" y1="<?= $yWaspada1 ?>"
                            x2="<?= $xWaspada2 ?>" y2="<?= $yWaspada2 ?>"
                            stroke='#000' stroke-width='3' />

                        <text x="<?= $textWaspadaX ?>"
                            y="<?= $textWaspadaY ?>"
                            font-size='14'
                            font-weight='bold'
                            text-anchor='middle'
                            transform="rotate(<?= $sudutWaspada ?> <?= $textWaspadaX ?> <?= $textWaspadaY ?>)">
                            WASPADA
                        </text>

                        <!-- GARIS BERTINDAK -->
                        <?php
                        $xBertindak1 = xServikTitik(5, $leftServik, $plotServikWidth, $jumlahKolomServik);
                        $yBertindak1 = yServik(4, $topServik, $plotServikHeight, $minServikY, $maxServikY);

                        $xBertindak2 = xServikTitik(10, $leftServik, $plotServikWidth, $jumlahKolomServik);
                        $yBertindak2 = yServik(10, $topServik, $plotServikHeight, $minServikY, $maxServikY);

                        $sudutBertindak = rad2deg(
                            atan2(
                                ($yBertindak2 - $yBertindak1),
                                ($xBertindak2 - $xBertindak1)
                            )
                        );

                        $textBertindakX = ($xBertindak1 + $xBertindak2) / 2;
                        $textBertindakY = ($yBertindak1 + $yBertindak2) / 2 - 12;
                        ?>

                        <line x1="<?= $xBertindak1 ?>" y1="<?= $yBertindak1 ?>"
                            x2="<?= $xBertindak2 ?>" y2="<?= $yBertindak2 ?>"
                            stroke='#000' stroke-width='3' />

                        <text x="<?= $textBertindakX ?>"
                            y="<?= $textBertindakY ?>"
                            font-size='14'
                            font-weight='bold'
                            text-anchor='middle'
                            transform="rotate(<?= $sudutBertindak ?> <?= $textBertindakX ?> <?= $textBertindakY ?>)">
                            BERTINDAK
                        </text>

                        <!-- SIAPKAN TITIK X DAN O -->
                        <?php foreach ($dataServiks as $row): ?>
                            <?php
                            $waktuKe = isset($row['waktu_ke']) ? (float)$row['waktu_ke'] : 0;

                            if ($waktuKe < 1 || $waktuKe > 16) {
                                continue;
                            }

                            if (!isset($row['centimeter_servik']) || $row['centimeter_servik'] === '' || $row['centimeter_servik'] === null) {
                                continue;
                            }

                            $centimeterServik = (float)$row['centimeter_servik'];

                            if ($centimeterServik < 0 || $centimeterServik > 10) {
                                continue;
                            }

                            $xTitik = xServikTitik($waktuKe, $leftServik, $plotServikWidth, $jumlahKolomServik);
                            $yTitik = yServik($centimeterServik, $topServik, $plotServikHeight, $minServikY, $maxServikY);

                            if (isset($row['pembukaan']) && strtoupper(trim($row['pembukaan'])) == 'X') {
                                $pointsX[] = [
                                    'x' => $xTitik,
                                    'y' => $yTitik
                                ];
                            }

                            if (isset($row['turun_kepala']) && strtoupper(trim($row['turun_kepala'])) == 'O') {
                                $pointsO[] = [
                                    'x' => $xTitik,
                                    'y' => $yTitik
                                ];
                            }
                            ?>
                        <?php endforeach;
                        ?>

                        <!-- GARIS PENGHUBUNG X -->
                        <?php if (count($pointsX) > 1): ?>
                            <?php
                            $polylineX = '';

                            foreach ($pointsX as $p) {
                                $polylineX .= $p['x'] . ',' . $p['y'] . ' ';
                            }
                            ?>

                            <polyline points="<?= trim($polylineX) ?>"
                                fill='none'
                                stroke='#000'
                                stroke-width='2'
                                stroke-linecap='round'
                                stroke-linejoin='round' />
                        <?php endif;
                        ?>

                        <!-- GARIS PENGHUBUNG X TERAKHIR KE O PERTAMA -->
                        <?php if (count($pointsX) > 0 && count($pointsO) > 0): ?>

                            <?php
                            $lastX  = end($pointsX);
                            $firstO = reset($pointsO);
                            ?>

                            <line x1="<?= $lastX['x'] ?>"
                                y1="<?= $lastX['y'] ?>"
                                x2="<?= $firstO['x'] ?>"
                                y2="<?= $firstO['y'] ?>"
                                stroke='#000'
                                stroke-width='2'
                                stroke-linecap='round' />

                        <?php endif;
                        ?>

                        <!-- GARIS PENGHUBUNG O -->
                        <?php if (count($pointsO) > 1): ?>
                            <?php
                            $polylineO = '';

                            foreach ($pointsO as $p) {
                                $polylineO .= $p['x'] . ',' . $p['y'] . ' ';
                            }
                            ?>

                            <polyline points="<?= trim($polylineO) ?>"
                                fill='none'
                                stroke='#000'
                                stroke-width='2'
                                stroke-linecap='round'
                                stroke-linejoin='round' />
                        <?php endif;
                        ?>

                        <!-- TANDA X -->
                        <?php foreach ($pointsX as $p): ?>
                            <text x="<?= $p['x'] - 7 ?>"
                                y="<?= $p['y'] + 7 ?>"
                                font-size='22'
                                font-weight='bold'>
                                X
                            </text>
                        <?php endforeach;
                        ?>

                        <!-- TANDA O -->
                        <?php foreach ($pointsO as $p): ?>
                            <circle cx="<?= $p['x'] ?>"
                                cy="<?= $p['y'] ?>"
                                r='6'
                                fill='#fff'
                                stroke='#000'
                                stroke-width='2' />
                        <?php endforeach;
                        ?>

                        <!-- TABEL WAKTU -->
                        <?php
                        $tinggiBarisWaktu = 22;
                        // sebelumnya 35, makin kecil makin sempit

                        $yKotakWaktu1 = $topServik + $plotServikHeight;
                        $yKotakWaktu2 = $yKotakWaktu1 + $tinggiBarisWaktu;
                        ?>

                        <text x="<?= $leftServik - 70 ?>" y="<?= $yKotakWaktu1 + 16 ?>" font-size='13'>Waktu</text>
                        <text x="<?= $leftServik - 70 ?>" y="<?= $yKotakWaktu2 + 16 ?>" font-size='13'>( Jam )</text>

                        <?php for ($wk = 1; $wk <= 16; $wk++): ?>
                            <?php
                            $x1 = xServikGaris($wk, $leftServik, $plotServikWidth, $jumlahKolomServik);
                            $x2 = xServikGaris($wk + 1, $leftServik, $plotServikWidth, $jumlahKolomServik);
                            $lebarKotak = $x2 - $x1;

                            $jamServik = '';
                            foreach ($dataServiks as $rowJam) {
                                if ((int)$rowJam['waktu_ke'] == $wk) {
                                    $jamServik = $rowJam['jamservik'];
                                    break;
                                }
                            }
                            ?>

                            <rect x="<?= $x1 ?>"
                                y="<?= $yKotakWaktu1 ?>"
                                width="<?= $lebarKotak ?>"
                                height="<?= $tinggiBarisWaktu ?>"
                                fill='none'
                                stroke='#000'
                                stroke-width='1' />

                            <rect x="<?= $x1 ?>"
                                y="<?= $yKotakWaktu2 ?>"
                                width="<?= $lebarKotak ?>"
                                height="<?= $tinggiBarisWaktu ?>"
                                fill='none'
                                stroke='#000'
                                stroke-width='1' />

                            <text x="<?= $x1 + ($lebarKotak / 2) - 4 ?>"
                                y="<?= $yKotakWaktu1 + 15 ?>"
                                font-size='12'>
                                <?= $wk ?>
                            </text>

                            <text x="<?= $x1 + ($lebarKotak / 2) - 13 ?>"
                                y="<?= $yKotakWaktu2 + 15 ?>"
                                font-size='11'>
                                <?= htmlspecialchars($jamServik) ?>
                            </text>

                        <?php endfor;
                        ?>

                    </svg>

                </div>

                <!-- GRAFIK KONTRAKSI TIAP 10 MENIT -->
                <div class='grafik-kontraksi-box'>

                    <?php
                    $widthKontraksi  = 900;
                    $heightKontraksi = 170;

                    $leftKontraksi   = 185;
                    $rightKontraksi  = 20;
                    $topKontraksi    = 10;
                    $bottomKontraksi = 45;

                    $jumlahKolomKontraksi = 32;
                    $jumlahBarisKontraksi = 5;

                    $plotKontraksiWidth  = $widthKontraksi - $leftKontraksi - $rightKontraksi;
                    $plotKontraksiHeight = $heightKontraksi - $topKontraksi - $bottomKontraksi;

                    $lebarKolomKontraksi = $plotKontraksiWidth / $jumlahKolomKontraksi;
                    $tinggiBarisKontraksi = $plotKontraksiHeight / $jumlahBarisKontraksi;

                    function xKontraksi($kolom, $leftKontraksi, $lebarKolomKontraksi)
                    {
                        return $leftKontraksi + (($kolom - 1) * $lebarKolomKontraksi);
                    }

                    function yKontraksi($lajur, $topKontraksi, $tinggiBarisKontraksi, $jumlahBarisKontraksi)
                    {
                        return $topKontraksi + (($jumlahBarisKontraksi - $lajur) * $tinggiBarisKontraksi);
                    }
                    ?>

                    <svg viewBox="0 0 <?= $widthKontraksi ?> <?= $heightKontraksi ?>" xmlns='http://www.w3.org/2000/svg'>

                        <defs>
                            <!-- Arsiran titik-titik untuk < 20 detik -->
                            <pattern id='polaTitik' width='8' height='8' patternUnits='userSpaceOnUse'>
                                <circle cx='3' cy='3' r='1.2' fill='#000' />
                            </pattern>

                            <!-- Arsiran garis miring untuk 30 - 40 detik -->
                            <pattern id='polaMiring' width='8' height='8' patternUnits='userSpaceOnUse'
                                patternTransform='rotate(45)'>
                                <line x1='0' y1='0' x2='0' y2='8' stroke='#000' stroke-width='3' />
                            </pattern>
                        </defs>

                        <!-- LABEL KIRI -->
                        <text x='5' y='35' class='label-kontraksi'>Kontraksi</text>
                        <text x='5' y='55' class='label-kontraksi'>tiap</text>
                        <text x='5' y='75' class='label-kontraksi'>10 menit</text>

                        <!-- LEGENDA KONTRAKSI -->
                        <rect x='70' y='18' width='28' height='24'
                            fill='url(#polaTitik)' stroke='#000' stroke-width='1' />
                        <text x='110' y='35' class='label-kontraksi'>&lt;
                            20</text>

                        <rect x='70' y='42' width='28' height='24'
                            fill='url(#polaMiring)' stroke='#000' stroke-width='1' />
                        <text x='110' y='59' class='label-kontraksi'>30 - 40</text>

                        <rect x='70' y='66' width='28' height='24'
                            fill='#000' stroke='#000' stroke-width='1' />
                        <text x='110' y='83' class='label-kontraksi'>&gt;
                            40</text>

                        <text x="<?= $leftKontraksi - 75 ?>"
                            y="<?= $topKontraksi + $plotKontraksiHeight - ($tinggiBarisKontraksi / 2) + 5 ?>"

                            class='label-kontraksi'>
                            ( detik )
                        </text>

                        <!-- ANGKA VERTIKAL 1 - 5 -->
                        <?php for ($i = 1; $i <= 5; $i++): ?>
                            <?php
                            $yAngka = yKontraksi($i, $topKontraksi, $tinggiBarisKontraksi, $jumlahBarisKontraksi)
                                + ($tinggiBarisKontraksi / 2) + 4;
                            ?>
                            <text x="<?= $leftKontraksi - 18 ?>" y="<?= $yAngka ?>" font-size='13'>
                                <?= $i ?>
                            </text>
                        <?php endfor;
                        ?>

                        <!-- GRID HORIZONTAL -->
                        <?php for ($baris = 0; $baris <= $jumlahBarisKontraksi; $baris++): ?>
                            <?php $y = $topKontraksi + ($baris * $tinggiBarisKontraksi);
                            ?>

                            <line x1="<?= $leftKontraksi ?>" y1="<?= $y ?>"
                                x2="<?= $widthKontraksi - $rightKontraksi ?>" y2="<?= $y ?>"
                                stroke='#000' stroke-width='1' />
                        <?php endfor;
                        ?>

                        <!-- GRID VERTIKAL -->
                        <?php for ($kolom = 0; $kolom <= $jumlahKolomKontraksi; $kolom++): ?>
                            <?php $x = $leftKontraksi + ($kolom * $lebarKolomKontraksi);
                            ?>

                            <line x1="<?= $x ?>" y1="<?= $topKontraksi ?>"
                                x2="<?= $x ?>" y2="<?= $topKontraksi + $plotKontraksiHeight ?>"
                                stroke='#000' stroke-width='1' />
                        <?php endfor;
                        ?>

                        <!-- ISI DATA KONTRAKSI -->
                        <?php foreach ($dataKontraksi as $i => $row): ?>
                            <?php
                            $kolom = $i + 1;

                            if ($kolom > $jumlahKolomKontraksi) {
                                continue;
                            }

                            $lajurKontraksi = isset($row['lajur_kontraksi']) ? (int)$row['lajur_kontraksi'] : 0;
                            $detikKontraksi = isset($row['detik_kontraksi'])
                                ? trim($row['detik_kontraksi'])
                                : '';

                            $detikKontraksi = str_replace('  ', ' ', $detikKontraksi);

                            if ($lajurKontraksi < 1 || $lajurKontraksi > 5) {
                                continue;
                            }

                            $xKotak = xKontraksi($kolom, $leftKontraksi, $lebarKolomKontraksi);
                            $yKotak = yKontraksi($lajurKontraksi, $topKontraksi, $tinggiBarisKontraksi, $jumlahBarisKontraksi);

                            $isiKotak = '';

                            if ($detikKontraksi == '< 20') {
                                $isiKotak = 'url(#polaTitik)';
                            } else if ($detikKontraksi == '30 - 40') {
                                $isiKotak = 'url(#polaMiring)';
                            } else if ($detikKontraksi == '> 40') {
                                $isiKotak = '#000';
                            } else {
                                $isiKotak = '#fff';
                            }
                            ?>

                            <rect x="<?= $xKotak ?>"
                                y="<?= $yKotak ?>"
                                width="<?= $lebarKolomKontraksi ?>"
                                height="<?= $tinggiBarisKontraksi ?>"
                                fill="<?= $isiKotak ?>"
                                stroke='#000'
                                stroke-width='1' />

                        <?php endforeach;
                        ?>

                    </svg>

                </div>

                <!-- OKSITOSIN -->
                <div class='oksitosin-box'>

                    <div class='label-oksitosin'>
                        <div class='label-oksitosin-baris'>Oksitosin U/L</div>
                        <div class='label-oksitosin-baris'>tetes/menit</div>
                    </div>

                    <table class='tabel-oksitosin'>

                        <!-- BARIS OKSITOSIN -->
                        <tr>

                            <?php for ($i = 0; $i < 32; $i++): ?>

                                <?php
                                $oksitosin = '';

                                if (isset($dataOksitosin[$i]['oksitosin'])) {
                                    $oksitosin = trim($dataOksitosin[$i]['oksitosin']);
                                }

                                $isVertikalOksitosin = false;

                                if ($oksitosin !== '' && $oksitosin !== '-' && !is_numeric($oksitosin)) {
                                    $isVertikalOksitosin = true;
                                }
                                ?>

                                <td>

                                    <?php if ($isVertikalOksitosin): ?>

                                        <div class='text-oksitosin-vertikal'>
                                            <?= teksVertikalWrap($oksitosin, 4) ?>
                                        </div>

                                    <?php else: ?>

                                        <div class='text-oksitosin-normal'>
                                            <?= teksVertikalWrap($oksitosin, 4) ?>
                                        </div>

                                    <?php endif;
                                    ?>

                                </td>

                            <?php endfor;
                            ?>

                        </tr>

                        <!-- BARIS TETES/MENIT -->
                        <tr>

                            <?php for ($i = 0; $i < 32; $i++): ?>

                                <?php
                                $tetesMenit = '';

                                if (isset($dataOksitosin[$i]['tetes_menit'])) {
                                    $tetesMenit = trim($dataOksitosin[$i]['tetes_menit']);
                                }

                                $isVertikalTetes = false;

                                if ($tetesMenit !== '' && $tetesMenit !== '-' && !is_numeric($tetesMenit)) {
                                    $isVertikalTetes = true;
                                }
                                ?>

                                <td>

                                    <?php if ($isVertikalTetes): ?>

                                        <div class='text-oksitosin-vertikal'>
                                            <?= teksVertikalWrap($tetesMenit, 4) ?>
                                        </div>

                                    <?php else: ?>

                                        <div class='text-oksitosin-normal'>
                                            <?= teksVertikalWrap($tetesMenit, 4) ?>
                                        </div>

                                    <?php endif;
                                    ?>

                                </td>

                            <?php endfor;
                            ?>

                        </tr>

                    </table>

                </div>

            </div>

            <div class="print-page halaman-dua">
                <!-- OBAT DAN CAIRAN IV -->
                <div class="obat-cairan-svg-box">

                    <?php
                    $widthObat  = 900;
                    $heightObat = 70;

                    $leftObat   = 130;
                    $rightObat  = 20;

                    $jumlahKolomObatBesar = 16;
                    $plotObatWidth = $widthObat - $leftObat - $rightObat;
                    $lebarKotakObat = $plotObatWidth / $jumlahKolomObatBesar;
                    ?>

                    <svg viewBox="0 0 <?= $widthObat ?> <?= $heightObat ?>" xmlns="http://www.w3.org/2000/svg">

                        <text x="5" y="25" font-size="13">Obat dan</text>
                        <text x="5" y="43" font-size="13">Cairan IV</text>

                        <?php for ($i = 0; $i < $jumlahKolomObatBesar; $i++): ?>

                            <?php
                            $x = $leftObat + ($i * $lebarKotakObat);

                            $obatCairan = '';

                            if (isset($dataObatCairan[$i]['obat_cairan'])) {
                                $obatCairan = trim($dataObatCairan[$i]['obat_cairan']);
                            }

                            $isVertikalObat = ($obatCairan !== '' && $obatCairan !== '-');

                            $hasilBarisObat = teksSvg2Baris($obatCairan, 8);
                            $baris1Obat = $hasilBarisObat[0];
                            $baris2Obat = $hasilBarisObat[1];

                            $xTextObat = $x + ($lebarKotakObat / 2);
                            $yTextObat = $heightObat / 2;
                            ?>

                            <rect x="<?= $x ?>"
                                y="0"
                                width="<?= $lebarKotakObat ?>"
                                height="<?= $heightObat ?>"
                                fill="none"
                                stroke="#000"
                                stroke-width="1" />

                            <?php if ($obatCairan !== ''): ?>

                                <?php if ($isVertikalObat): ?>

                                    <text x="<?= $xTextObat ?>"
                                        y="<?= $yTextObat ?>"
                                        font-size="9"
                                        text-anchor="middle"
                                        dominant-baseline="middle"
                                        transform="rotate(-90 <?= $xTextObat ?> <?= $yTextObat ?>)">

                                        <tspan x="<?= $xTextObat ?>" dy="-5"><?= $baris1Obat ?></tspan>

                                        <?php if ($baris2Obat !== ''): ?>
                                            <tspan x="<?= $xTextObat ?>" dy="10"><?= $baris2Obat ?></tspan>
                                        <?php endif; ?>

                                    </text>

                                <?php else: ?>

                                    <text x="<?= $xTextObat ?>"
                                        y="<?= $yTextObat + 4 ?>"
                                        font-size="10"
                                        text-anchor="middle">
                                        <?= htmlspecialchars($obatCairan) ?>
                                    </text>

                                <?php endif; ?>

                            <?php endif; ?>

                        <?php endfor; ?>

                    </svg>

                </div>

                <!-- GRAFIK NADI & TEKANAN DARAH -->
                <div class="grafik-nadi-box">

                    <?php

                    $widthNadi  = 900;
                    $heightNadi = 285;

                    $leftNadi   = 130;
                    $rightNadi  = 20;
                    $topNadi    = 0;
                    $bottomNadi = 40;

                    $jumlahKolomNadi = 32;

                    $minNadiY = 60;
                    $maxNadiY = 180;

                    $plotNadiWidth  = $widthNadi - $leftNadi - $rightNadi;
                    $plotNadiHeight = $heightNadi - $topNadi - $bottomNadi;

                    $tinggiPer10 = $plotNadiHeight / (($maxNadiY - $minNadiY) / 10);

                    function xNadiKolom($kolom, $leftNadi, $plotNadiWidth, $jumlahKolomNadi)
                    {
                        return $leftNadi + (($kolom - 1) * ($plotNadiWidth / $jumlahKolomNadi));
                    }

                    function yNadi($nilai, $topNadi, $plotNadiHeight, $minNadiY, $maxNadiY)
                    {

                        return $topNadi +
                            (($maxNadiY - $nilai) / ($maxNadiY - $minNadiY))
                            * $plotNadiHeight;
                    }

                    ?>

                    <svg viewBox="0 0 <?= $widthNadi ?> <?= $heightNadi ?>"
                        xmlns="http://www.w3.org/2000/svg">

                        <!-- LABEL KIRI -->
                        <text x="5" y="35" font-size="25">●</text>

                        <text x="30" y="35" font-size="13">
                            Nadi
                        </text>

                        <?php
                        $yPanahAtas  = yNadi(140, $topNadi, $plotNadiHeight, $minNadiY, $maxNadiY);
                        $yPanahBawah = yNadi(70, $topNadi, $plotNadiHeight, $minNadiY, $maxNadiY);

                        $tengahPanah = ($yPanahAtas + $yPanahBawah) / 2;
                        ?>

                        <text x="5"
                            y="<?= $tengahPanah - 8 ?>"
                            font-size="13">
                            Tekanan
                        </text>

                        <text x="5"
                            y="<?= $tengahPanah + 8 ?>"
                            font-size="13">
                            darah
                        </text>

                        <!-- GARIS PANAH TEKANAN DARAH -->
                        <line x1="82"
                            y1="<?= $yPanahAtas ?>"
                            x2="82"
                            y2="<?= $yPanahBawah ?>"
                            stroke="#000"
                            stroke-width="2" />

                        <!-- PANAH ATAS -->
                        <line x1="82"
                            y1="<?= $yPanahAtas ?>"
                            x2="75"
                            y2="<?= $yPanahAtas + 8 ?>"
                            stroke="#000"
                            stroke-width="2" />

                        <line x1="82"
                            y1="<?= $yPanahAtas ?>"
                            x2="89"
                            y2="<?= $yPanahAtas + 8 ?>"
                            stroke="#000"
                            stroke-width="2" />

                        <!-- PANAH BAWAH -->
                        <line x1="82"
                            y1="<?= $yPanahBawah ?>"
                            x2="75"
                            y2="<?= $yPanahBawah - 8 ?>"
                            stroke="#000"
                            stroke-width="2" />

                        <line x1="82"
                            y1="<?= $yPanahBawah ?>"
                            x2="89"
                            y2="<?= $yPanahBawah - 8 ?>"
                            stroke="#000"
                            stroke-width="2" />
                        <!-- ANGKA 60 - 180 -->
                        <?php for ($nilai = 60; $nilai <= 180; $nilai += 10): ?>

                            <?php
                            $y = yNadi(
                                $nilai,
                                $topNadi,
                                $plotNadiHeight,
                                $minNadiY,
                                $maxNadiY
                            );
                            ?>

                            <text x="<?= $leftNadi - 25 ?>"
                                y="<?= $y + 4 ?>"
                                font-size="9">
                                <?= $nilai ?>
                            </text>

                            <!-- GARIS HORIZONTAL -->
                            <line x1="<?= $leftNadi ?>"
                                y1="<?= $y ?>"
                                x2="<?= $widthNadi - $rightNadi ?>"
                                y2="<?= $y ?>"
                                stroke="#000"
                                stroke-width="1" />

                        <?php endfor; ?>

                        <!-- GARIS VERTIKAL -->
                        <?php for ($i = 0; $i <= $jumlahKolomNadi; $i++): ?>

                            <?php
                            $x = $leftNadi + ($i * ($plotNadiWidth / $jumlahKolomNadi));
                            ?>

                            <line x1="<?= $x ?>"
                                y1="<?= $topNadi ?>"
                                x2="<?= $x ?>"
                                y2="<?= $plotNadiHeight ?>"
                                stroke="#000"
                                stroke-width="1" />

                        <?php endfor; ?>

                        <!-- BORDER -->
                        <rect x="<?= $leftNadi ?>"
                            y="<?= $topNadi ?>"
                            width="<?= $plotNadiWidth ?>"
                            height="<?= $plotNadiHeight ?>"
                            fill="none"
                            stroke="#000"
                            stroke-width="1" />

                        <!-- DATA NADI -->
                        <?php foreach ($dataNadiTensi as $i => $row): ?>

                            <?php
                            $kolomNadi = $i + 1;

                            if ($kolomNadi > $jumlahKolomNadi) {
                                continue;
                            }

                            $nadi     = isset($row['nadi']) ? (float)$row['nadi'] : 0;
                            $sistole  = isset($row['sistole']) ? (float)$row['sistole'] : 0;
                            $distole  = isset($row['distole']) ? (float)$row['distole'] : 0;

                            $xNadi = xNadiKolom(
                                $kolomNadi,
                                $leftNadi,
                                $plotNadiWidth,
                                $jumlahKolomNadi
                            ) + (($plotNadiWidth / $jumlahKolomNadi) / 2);

                            $yNadiPoint = yNadi(
                                $nadi,
                                $topNadi,
                                $plotNadiHeight,
                                $minNadiY,
                                $maxNadiY
                            );

                            $ySistole = yNadi(
                                $sistole,
                                $topNadi,
                                $plotNadiHeight,
                                $minNadiY,
                                $maxNadiY
                            );

                            $yDistole = yNadi(
                                $distole,
                                $topNadi,
                                $plotNadiHeight,
                                $minNadiY,
                                $maxNadiY
                            );
                            ?>

                            <!-- TITIK NADI -->
                            <circle cx="<?= $xNadi ?>"
                                cy="<?= $yNadiPoint ?>"
                                r="6"
                                fill="#000" />

                            <!-- GARIS TEKANAN DARAH -->
                            <line x1="<?= $xNadi ?>"
                                y1="<?= $ySistole ?>"
                                x2="<?= $xNadi ?>"
                                y2="<?= $yDistole ?>"
                                stroke="#000"
                                stroke-width="2" />

                            <!-- PANAH ATAS SISTOLE -->
                            <line x1="<?= $xNadi ?>"
                                y1="<?= $ySistole ?>"
                                x2="<?= $xNadi - 7 ?>"
                                y2="<?= $ySistole + 8 ?>"
                                stroke="#000"
                                stroke-width="2" />

                            <line x1="<?= $xNadi ?>"
                                y1="<?= $ySistole ?>"
                                x2="<?= $xNadi + 7 ?>"
                                y2="<?= $ySistole + 8 ?>"
                                stroke="#000"
                                stroke-width="2" />

                            <!-- PANAH BAWAH DISTOLE -->
                            <line x1="<?= $xNadi ?>"
                                y1="<?= $yDistole ?>"
                                x2="<?= $xNadi - 7 ?>"
                                y2="<?= $yDistole - 8 ?>"
                                stroke="#000"
                                stroke-width="2" />

                            <line x1="<?= $xNadi ?>"
                                y1="<?= $yDistole ?>"
                                x2="<?= $xNadi + 7 ?>"
                                y2="<?= $yDistole - 8 ?>"
                                stroke="#000"
                                stroke-width="2" />

                        <?php endforeach; ?>

                        <!-- JAM NADI/TENSI -->
                        <?php foreach ($dataNadiTensi as $i => $row): ?>

                            <?php
                            $kolomNadi = $i + 1;

                            if ($kolomNadi > $jumlahKolomNadi) {
                                continue;
                            }

                            $xJamNadi = xNadiKolom(
                                $kolomNadi,
                                $leftNadi,
                                $plotNadiWidth,
                                $jumlahKolomNadi
                            ) + (($plotNadiWidth / $jumlahKolomNadi) / 2);

                            $jamNadiTd = isset($row['jamnaditd'])
                                ? trim($row['jamnaditd'])
                                : '';
                            ?>

                            <text x="<?= $xJamNadi ?>"
                                y="<?= $plotNadiHeight + 7 ?>"
                                font-size="11"
                                text-anchor="end"
                                transform="rotate(-90 <?= $xJamNadi ?> <?= $plotNadiHeight + 7 ?>)">
                                <?= htmlspecialchars($jamNadiTd) ?>
                            </text>

                        <?php endforeach; ?>

                    </svg>

                </div>

                <!-- TABEL SUHU -->
                <div class="grafik-suhu-box">

                    <?php
                    $widthSuhu  = 900;
                    $heightSuhu = 75;

                    $leftSuhu   = 75;
                    $rightSuhu  = 17;

                    $jumlahKolomSuhu = 32;

                    $plotSuhuWidth = $widthSuhu - $leftSuhu - $rightSuhu;
                    $lebarKotakSuhu = $plotSuhuWidth / $jumlahKolomSuhu;
                    ?>

                    <svg viewBox="0 0 <?= $widthSuhu ?> <?= $heightSuhu ?>" xmlns="http://www.w3.org/2000/svg">

                        <text x="5" y="27" font-size="13">Suhu °C</text>

                        <?php for ($i = 0; $i < $jumlahKolomSuhu; $i++): ?>

                            <?php
                            $x = $leftSuhu + ($i * $lebarKotakSuhu);

                            $suhu = '';

                            if (isset($dataSuhu[$i]['suhu'])) {
                                $suhu = trim($dataSuhu[$i]['suhu']);
                            }
                            ?>

                            <rect x="<?= $x ?>"
                                y="5"
                                width="<?= $lebarKotakSuhu ?>"
                                height="28"
                                fill="none"
                                stroke="#000"
                                stroke-width="1" />

                            <?php if ($suhu !== ''): ?>
                                <text x="<?= $x + ($lebarKotakSuhu / 2) - 0.5 ?>"
                                    y="20"
                                    font-size="9.5"
                                    text-anchor="middle"
                                    dominant-baseline="middle"
                                    lengthAdjust="spacingAndGlyphs">
                                    <?= htmlspecialchars($suhu) ?>
                                </text>
                            <?php endif; ?>

                        <?php endfor; ?>

                        <!-- JAM SUHU -->
                        <?php foreach ($dataSuhu as $i => $row): ?>

                            <?php
                            $kolomSuhu = $i + 1;

                            if ($kolomSuhu > $jumlahKolomSuhu) {
                                continue;
                            }

                            $xJamSuhu = $leftSuhu + ($i * $lebarKotakSuhu) + ($lebarKotakSuhu / 2);

                            $yJamSuhu = 53;

                            $jamSuhu = isset($row['jamsuhu'])
                                ? trim($row['jamsuhu'])
                                : '';
                            ?>

                            <?php if ($jamSuhu !== ''): ?>

                                <text x="<?= $xJamSuhu ?>"
                                    y="<?= $yJamSuhu ?>"
                                    font-size="11"
                                    text-anchor="middle"
                                    dominant-baseline="middle"
                                    transform="rotate(-90 <?= $xJamSuhu ?> <?= $yJamSuhu ?>)">
                                    <?= htmlspecialchars($jamSuhu) ?>
                                </text>

                            <?php endif; ?>

                        <?php endforeach; ?>

                    </svg>

                </div>

                <!-- TABEL URIN -->
                <div class="grafik-urin-box">

                    <?php
                    $widthUrin  = 900;
                    $heightUrin = 120;

                    $leftUrin   = 125;
                    $rightUrin  = 35;

                    $jumlahKolomUrin = 16;
                    $jumlahBarisUrin = 3;

                    $plotUrinWidth  = $widthUrin - $leftUrin - $rightUrin;
                    $plotUrinHeight = 70;

                    $lebarKotakUrin = $plotUrinWidth / $jumlahKolomUrin;
                    $tinggiKotakUrin = $plotUrinHeight / $jumlahBarisUrin;

                    $yProtein = 10 + ($tinggiKotakUrin / 2);
                    $yAseton  = 10 + $tinggiKotakUrin + ($tinggiKotakUrin / 2);
                    $yVolume  = 10 + ($tinggiKotakUrin * 2) + ($tinggiKotakUrin / 2);
                    ?>

                    <svg viewBox="0 0 <?= $widthUrin ?> <?= $heightUrin ?>"
                        xmlns="http://www.w3.org/2000/svg">

                        <!-- LABEL KIRI -->
                        <text x="5" y="48" font-size="13">Urin</text>

                        <!-- GARIS PENUNJUK URIN -->
                        <?php
                        $yProtein = 10 + ($tinggiKotakUrin / 2);
                        $yAseton  = 10 + $tinggiKotakUrin + ($tinggiKotakUrin / 2);
                        $yVolume  = 10 + ($tinggiKotakUrin * 2) + ($tinggiKotakUrin / 2);
                        ?>

                        <!-- GARIS PENUNJUK URIN -->
                        <?php
                        $yProtein = 10 + ($tinggiKotakUrin / 2);
                        $yAseton  = 10 + $tinggiKotakUrin + ($tinggiKotakUrin / 2);
                        $yVolume  = 10 + ($tinggiKotakUrin * 2) + ($tinggiKotakUrin / 2);
                        ?>

                        <!-- GARIS DARI TEXT URIN -->
                        <line x1="35"
                            y1="<?= $yAseton ?>"
                            x2="52"
                            y2="<?= $yAseton ?>"
                            stroke="#000"
                            stroke-width="1" />

                        <!-- GARIS VERTIKAL -->
                        <line x1="52"
                            y1="<?= $yProtein ?>"
                            x2="52"
                            y2="<?= $yVolume ?>"
                            stroke="#000"
                            stroke-width="1" />

                        <!-- KE PROTEIN -->
                        <line x1="52"
                            y1="<?= $yProtein ?>"
                            x2="68"
                            y2="<?= $yProtein ?>"
                            stroke="#000"
                            stroke-width="1" />

                        <!-- KE ASETON -->
                        <line x1="52"
                            y1="<?= $yAseton ?>"
                            x2="68"
                            y2="<?= $yAseton ?>"
                            stroke="#000"
                            stroke-width="1" />

                        <!-- KE VOLUME -->
                        <line x1="52"
                            y1="<?= $yVolume ?>"
                            x2="68"
                            y2="<?= $yVolume ?>"
                            stroke="#000"
                            stroke-width="1" />

                        <!-- TEXT -->
                        <text x="73"
                            y="<?= $yProtein ?>"
                            font-size="13"
                            dominant-baseline="middle">
                            Protein
                        </text>

                        <text x="73"
                            y="<?= $yAseton ?>"
                            font-size="13"
                            dominant-baseline="middle">
                            Aseton
                        </text>

                        <text x="73"
                            y="<?= $yVolume ?>"
                            font-size="13"
                            dominant-baseline="middle">
                            Volume
                        </text>

                        <!-- GRID -->
                        <?php for ($baris = 0; $baris < $jumlahBarisUrin; $baris++): ?>

                            <?php for ($kolom = 0; $kolom < $jumlahKolomUrin; $kolom++): ?>

                                <?php
                                $x = $leftUrin + ($kolom * $lebarKotakUrin);
                                $y = 10 + ($baris * $tinggiKotakUrin);

                                $isi = '';

                                if (isset($dataUrin[$kolom])) {

                                    if ($baris == 0) {
                                        $isi = $dataUrin[$kolom]['protein'];
                                    }

                                    if ($baris == 1) {
                                        $isi = $dataUrin[$kolom]['aseton'];
                                    }

                                    if ($baris == 2) {
                                        $isi = $dataUrin[$kolom]['volume'];
                                    }
                                }
                                ?>

                                <rect x="<?= $x ?>"
                                    y="<?= $y ?>"
                                    width="<?= $lebarKotakUrin ?>"
                                    height="<?= $tinggiKotakUrin ?>"
                                    fill="none"
                                    stroke="#000"
                                    stroke-width="1" />

                                <?php if ($isi !== ''): ?>

                                    <text x="<?= $x + ($lebarKotakUrin / 2) ?>"
                                        y="<?= $y + ($tinggiKotakUrin / 2) ?>"
                                        font-size="12"
                                        text-anchor="middle"
                                        dominant-baseline="middle">
                                        <?= htmlspecialchars($isi) ?>
                                    </text>

                                <?php endif; ?>

                            <?php endfor; ?>

                        <?php endfor; ?>

                        <!-- JAM URIN -->
                        <?php foreach ($dataUrin as $i => $row): ?>

                            <?php
                            $kolomUrin = $i + 1;

                            if ($kolomUrin > $jumlahKolomUrin) {
                                continue;
                            }

                            $xJamUrin = $leftUrin
                                + ($i * $lebarKotakUrin)
                                + ($lebarKotakUrin / 2);

                            $yJamUrin = 100;

                            $jamUrin = isset($row['jamurin'])
                                ? trim($row['jamurin'])
                                : '';
                            ?>

                            <?php if ($jamUrin !== ''): ?>

                                <text x="<?= $xJamUrin ?>"
                                    y="<?= $yJamUrin ?>"
                                    font-size="11"
                                    text-anchor="middle"
                                    dominant-baseline="middle"
                                    transform="rotate(-90 <?= $xJamUrin ?> <?= $yJamUrin ?>)">
                                    <?= htmlspecialchars($jamUrin) ?>
                                </text>

                            <?php endif; ?>

                        <?php endforeach; ?>

                    </svg>

                </div>

                <!-- JUDUL CATATAN PERSALINAN -->
                <div class="judul-partograf judul-bawah">
                    CATATAN PERSALINAN
                </div>

                <?php
                $tempatPersalinan = isset($dCatatanPersalinan['cttn_tempat_persalinan'])
                    ? trim($dCatatanPersalinan['cttn_tempat_persalinan'])
                    : '';

                $ketLainnyaTempat = isset($dCatatanPersalinan['cttn_ket_lainya_tmpt'])
                    ? trim($dCatatanPersalinan['cttn_ket_lainya_tmpt'])
                    : '';

                if ($tempatPersalinan == 'Lainnya') {
                    if ($ketLainnyaTempat == '') {
                        $ketLainnyaTempat = '-';
                    }

                    $tampilTempatPersalinan = $tempatPersalinan . ' (' . $ketLainnyaTempat . ')';
                } else {
                    $tampilTempatPersalinan = $tempatPersalinan;
                }
                ?>

                <?php
                $pendampingRujuk = array();

                if (
                    isset($dCatatanPersalinan['cttn_pendamping_bidan']) &&
                    $dCatatanPersalinan['cttn_pendamping_bidan'] == 'ya'
                ) {
                    $pendampingRujuk[] = 'Bidan';
                }

                if (
                    isset($dCatatanPersalinan['cttn_pendamping_teman']) &&
                    $dCatatanPersalinan['cttn_pendamping_teman'] == 'ya'
                ) {
                    $pendampingRujuk[] = 'Teman';
                }

                if (
                    isset($dCatatanPersalinan['cttn_pendamping_klg']) &&
                    $dCatatanPersalinan['cttn_pendamping_klg'] == 'ya'
                ) {
                    $pendampingRujuk[] = 'Keluarga';
                }

                if (
                    isset($dCatatanPersalinan['cttn_pendamping_suami']) &&
                    $dCatatanPersalinan['cttn_pendamping_suami'] == 'ya'
                ) {
                    $pendampingRujuk[] = 'Suami';
                }

                if (
                    isset($dCatatanPersalinan['cttn_pendamping_dukun']) &&
                    $dCatatanPersalinan['cttn_pendamping_dukun'] == 'ya'
                ) {
                    $pendampingRujuk[] = 'Dukun';
                }

                if (
                    isset($dCatatanPersalinan['cttn_pendamping_tidak_ada']) &&
                    $dCatatanPersalinan['cttn_pendamping_tidak_ada'] == 'ya'
                ) {
                    $pendampingRujuk[] = 'Tidak Ada';
                }

                $tampilPendampingRujuk = '-';

                if (count($pendampingRujuk) > 0) {
                    $tampilPendampingRujuk = implode(', ', $pendampingRujuk);
                }
                ?>

                <?php
                $masalahKehamilan = array();

                if (
                    isset($dCatatanPersalinan['cttn_masalah_gawat']) &&
                    $dCatatanPersalinan['cttn_masalah_gawat'] == 'ya'
                ) {
                    $masalahKehamilan[] = 'Gawat darurat';
                }

                if (
                    isset($dCatatanPersalinan['cttn_masalah_perdarahan']) &&
                    $dCatatanPersalinan['cttn_masalah_perdarahan'] == 'ya'
                ) {
                    $masalahKehamilan[] = 'Perdarahan';
                }

                if (
                    isset($dCatatanPersalinan['cttn_masalah_infeksi']) &&
                    $dCatatanPersalinan['cttn_masalah_infeksi'] == 'ya'
                ) {
                    $masalahKehamilan[] = 'Infeksi';
                }

                if (
                    isset($dCatatanPersalinan['cttn_masalah_peb']) &&
                    $dCatatanPersalinan['cttn_masalah_peb'] == 'ya'
                ) {
                    $masalahKehamilan[] = 'PEB';
                }

                if (
                    isset($dCatatanPersalinan['cttn_masalah_hdk']) &&
                    $dCatatanPersalinan['cttn_masalah_hdk'] == 'ya'
                ) {
                    $masalahKehamilan[] = 'HDK';
                }

                if (
                    isset($dCatatanPersalinan['cttn_masalah_bidan_pmtct']) &&
                    $dCatatanPersalinan['cttn_masalah_bidan_pmtct'] == 'ya'
                ) {
                    $masalahKehamilan[] = 'Bidan PMTCT';
                }

                if (
                    isset($dCatatanPersalinan['cttn_masalah_lainya']) &&
                    $dCatatanPersalinan['cttn_masalah_lainya'] == 'ya'
                ) {

                    $ketMasalahLain = isset($dCatatanPersalinan['cttn_masalah_ket_lainya'])
                        ? trim($dCatatanPersalinan['cttn_masalah_ket_lainya'])
                        : '';

                    if ($ketMasalahLain == '') {
                        $ketMasalahLain = '-';
                    }

                    $masalahKehamilan[] = 'Lainnya (' . $ketMasalahLain . ')';
                }

                $tampilMasalahKehamilan = '-';

                if (count($masalahKehamilan) > 0) {
                    $tampilMasalahKehamilan = implode(', ', $masalahKehamilan);
                }
                ?>

                <?php
                $episiotomi = isset($dCatatanPersalinan['kala2_episiotomi'])
                    ? trim($dCatatanPersalinan['kala2_episiotomi'])
                    : '';

                $episiotomiIndikasi = isset($dCatatanPersalinan['kala2_episiotomi_indikasi'])
                    ? trim($dCatatanPersalinan['kala2_episiotomi_indikasi'])
                    : '';

                if ($episiotomi == 'Ya, indikasi') {

                    if ($episiotomiIndikasi == '') {
                        $episiotomiIndikasi = '-';
                    }

                    $tampilEpisiotomi = $episiotomi . ' ' . $episiotomiIndikasi;
                } else {

                    $tampilEpisiotomi = $episiotomi;
                }
                ?>

                <?php
                $pendampingPersalinan = array();

                if (
                    isset($dCatatanPersalinan['kala2_pendamping_suami']) &&
                    $dCatatanPersalinan['kala2_pendamping_suami'] == 'ya'
                ) {
                    $pendampingPersalinan[] = 'Suami';
                }

                if (
                    isset($dCatatanPersalinan['kala2_pendamping_klg']) &&
                    $dCatatanPersalinan['kala2_pendamping_klg'] == 'ya'
                ) {
                    $pendampingPersalinan[] = 'Keluarga';
                }

                if (
                    isset($dCatatanPersalinan['kala2_pendamping_teman']) &&
                    $dCatatanPersalinan['kala2_pendamping_teman'] == 'ya'
                ) {
                    $pendampingPersalinan[] = 'Teman';
                }

                if (
                    isset($dCatatanPersalinan['kala2_pendamping_dukun']) &&
                    $dCatatanPersalinan['kala2_pendamping_dukun'] == 'ya'
                ) {
                    $pendampingPersalinan[] = 'Dukun';
                }

                if (
                    isset($dCatatanPersalinan['kala2_pendamping_tidak_ada']) &&
                    $dCatatanPersalinan['kala2_pendamping_tidak_ada'] == 'ya'
                ) {
                    $pendampingPersalinan[] = 'Tidak Ada';
                }

                $tampilPendampingPersalinan = '-';

                if (count($pendampingPersalinan) > 0) {
                    $tampilPendampingPersalinan = implode(', ', $pendampingPersalinan);
                }
                ?>

                <?php
                $gawatJanin = isset($dCatatanPersalinan['kala2_gawat_janin'])
                    ? trim($dCatatanPersalinan['kala2_gawat_janin'])
                    : '';

                $ketGawatJanin = isset($dCatatanPersalinan['kala2_ket_ya_gawat_janin'])
                    ? trim($dCatatanPersalinan['kala2_ket_ya_gawat_janin'])
                    : '';

                if ($gawatJanin == 'Ya, tindakan yang dilakukan') {

                    if ($ketGawatJanin == '') {
                        $ketGawatJanin = '-';
                    }

                    $tampilGawatJanin = $gawatJanin . ' ' . $ketGawatJanin;
                } else {

                    $tampilGawatJanin = $gawatJanin;
                }
                ?>

                <?php
                $distosiaBahu = isset($dCatatanPersalinan['kala2_distosia_bahu'])
                    ? trim($dCatatanPersalinan['kala2_distosia_bahu'])
                    : '';

                $ketDistosiaBahu = isset($dCatatanPersalinan['kala2_ket_ya_distosia_bahu'])
                    ? trim($dCatatanPersalinan['kala2_ket_ya_distosia_bahu'])
                    : '';

                if ($distosiaBahu == 'Ya, tindakan yang dilakukan') {

                    if ($ketDistosiaBahu == '') {
                        $ketDistosiaBahu = '-';
                    }

                    $tampilDistosiaBahu = $distosiaBahu . ' ' . $ketDistosiaBahu;
                } else {

                    $tampilDistosiaBahu = $distosiaBahu;
                }
                ?>

                <?php
                $inisiasiMenyusui = isset($dCatatanPersalinan['kala3_inisiasi'])
                    ? trim($dCatatanPersalinan['kala3_inisiasi'])
                    : '';

                $ketTidakInisiasi = isset($dCatatanPersalinan['kala3_ket_tidak_alasan'])
                    ? trim($dCatatanPersalinan['kala3_ket_tidak_alasan'])
                    : '';

                if ($inisiasiMenyusui == 'Tidak, alasan') {

                    if ($ketTidakInisiasi == '') {
                        $ketTidakInisiasi = '-';
                    }

                    $tampilInisiasiMenyusui = $inisiasiMenyusui . ' ' . $ketTidakInisiasi;
                } else {

                    $tampilInisiasiMenyusui = $inisiasiMenyusui;
                }
                ?>

                <?php
                $pemberianOksitosin = isset($dCatatanPersalinan['kala3_pemberian_oksitosin'])
                    ? trim($dCatatanPersalinan['kala3_pemberian_oksitosin'])
                    : '';

                $oksitosinYa = isset($dCatatanPersalinan['kala3_pemberian_oksitosin_ya'])
                    ? trim($dCatatanPersalinan['kala3_pemberian_oksitosin_ya'])
                    : '';

                $oksitosinTidak = isset($dCatatanPersalinan['kala3_pemberian_oksitosin_tidak'])
                    ? trim($dCatatanPersalinan['kala3_pemberian_oksitosin_tidak'])
                    : '';

                $oksitosinPenjepitan = isset($dCatatanPersalinan['kala3_pemberian_oksitosin_penjepitan'])
                    ? trim($dCatatanPersalinan['kala3_pemberian_oksitosin_penjepitan'])
                    : '';

                if ($pemberianOksitosin == 'Ya, waktu') {
                    if ($oksitosinYa == '') {
                        $oksitosinYa = '-';
                    }

                    $tampilPemberianOksitosin = $pemberianOksitosin . ' ' . $oksitosinYa . ' Menit sesudah persalinan';
                } else if ($pemberianOksitosin == 'Tidak, alasan') {
                    if ($oksitosinTidak == '') {
                        $oksitosinTidak = '-';
                    }

                    $tampilPemberianOksitosin = $pemberianOksitosin . ' ' . $oksitosinTidak;
                } else if ($pemberianOksitosin == 'Penjepitan tali pusat') {
                    if ($oksitosinPenjepitan == '') {
                        $oksitosinPenjepitan = '-';
                    }

                    $tampilPemberianOksitosin = $pemberianOksitosin . ' ' . $oksitosinPenjepitan . ' Menit setelah bayi lahir';
                } else {
                    $tampilPemberianOksitosin = $pemberianOksitosin;
                }
                ?>

                <?php
                $pemberianUlangOksitosin = isset($dCatatanPersalinan['kala3_pemberian_ulang'])
                    ? trim($dCatatanPersalinan['kala3_pemberian_ulang'])
                    : '';

                $ketPemberianUlangOksitosin = isset($dCatatanPersalinan['kala3_pemberian_ulang_ya'])
                    ? trim($dCatatanPersalinan['kala3_pemberian_ulang_ya'])
                    : '';

                if ($pemberianUlangOksitosin == 'Ya, alasan') {

                    if ($ketPemberianUlangOksitosin == '') {
                        $ketPemberianUlangOksitosin = '-';
                    }

                    $tampilPemberianUlangOksitosin = $pemberianUlangOksitosin . ' ' . $ketPemberianUlangOksitosin;
                } else {

                    $tampilPemberianUlangOksitosin = $pemberianUlangOksitosin;
                }
                ?>

                <?php
                $peneganganTaliPusat = isset($dCatatanPersalinan['kala3_penegangan_tali'])
                    ? trim($dCatatanPersalinan['kala3_penegangan_tali'])
                    : '';

                $ketPeneganganTaliPusat = isset($dCatatanPersalinan['kala3_penegangan_tali_tidak'])
                    ? trim($dCatatanPersalinan['kala3_penegangan_tali_tidak'])
                    : '';

                if ($peneganganTaliPusat == 'Tidak, alasan') {

                    if ($ketPeneganganTaliPusat == '') {
                        $ketPeneganganTaliPusat = '-';
                    }

                    $tampilPeneganganTaliPusat = $peneganganTaliPusat . ' ' . $ketPeneganganTaliPusat;
                } else {

                    $tampilPeneganganTaliPusat = $peneganganTaliPusat;
                }
                ?>

                <?php
                $masaseFundus = isset($dCatatanPersalinan['kala3_masase_fundus'])
                    ? trim($dCatatanPersalinan['kala3_masase_fundus'])
                    : '';

                $ketMasaseFundus = isset($dCatatanPersalinan['kala3_masase_fundus_tidak'])
                    ? trim($dCatatanPersalinan['kala3_masase_fundus_tidak'])
                    : '';

                if ($masaseFundus == 'Tidak, alasan') {

                    if ($ketMasaseFundus == '') {
                        $ketMasaseFundus = '-';
                    }

                    $tampilMasaseFundus = $masaseFundus . ' ' . $ketMasaseFundus;
                } else {

                    $tampilMasaseFundus = $masaseFundus;
                }
                ?>

                <?php
                $plasentaTidakLahir = isset($dCatatanPersalinan['kala3_plasenta_tidak_lahir'])
                    ? trim($dCatatanPersalinan['kala3_plasenta_tidak_lahir'])
                    : '';

                $ketPlasentaTidakLahir = isset($dCatatanPersalinan['kala3_plasenta_tidak_lahir_ya'])
                    ? trim($dCatatanPersalinan['kala3_plasenta_tidak_lahir_ya'])
                    : '';

                if ($plasentaTidakLahir == 'Ya, tindakan') {

                    if ($ketPlasentaTidakLahir == '') {
                        $ketPlasentaTidakLahir = '-';
                    }

                    $tampilPlasentaTidakLahir = $plasentaTidakLahir . ' ' . $ketPlasentaTidakLahir;
                } else {

                    $tampilPlasentaTidakLahir = $plasentaTidakLahir;
                }
                ?>

                <?php
                $laserasi = isset($dCatatanPersalinan['kala3_laserasi'])
                    ? trim($dCatatanPersalinan['kala3_laserasi'])
                    : '';

                $ketLaserasi = isset($dCatatanPersalinan['kala3_laserasi_ya'])
                    ? trim($dCatatanPersalinan['kala3_laserasi_ya'])
                    : '';

                if ($laserasi == 'Ya, dimana') {

                    if ($ketLaserasi == '') {
                        $ketLaserasi = '-';
                    }

                    $tampilLaserasi = $laserasi . ' ' . $ketLaserasi;
                } else {

                    $tampilLaserasi = $laserasi;
                }
                ?>

                <?php
                $tindakanPenjahitan = isset($dCatatanPersalinan['kala3_laserasi_perineum_penjahitan'])
                    ? trim($dCatatanPersalinan['kala3_laserasi_perineum_penjahitan'])
                    : '';

                $ketTidakDijahit = isset($dCatatanPersalinan['kala3_laserasi_perineum_penjahitan_tidak'])
                    ? trim($dCatatanPersalinan['kala3_laserasi_perineum_penjahitan_tidak'])
                    : '';

                if ($tindakanPenjahitan == 'Tidak dijahit, alasan') {

                    if ($ketTidakDijahit == '') {
                        $ketTidakDijahit = '-';
                    }

                    $tampilTindakanPenjahitan = $tindakanPenjahitan . ' ' . $ketTidakDijahit;
                } else {

                    $tampilTindakanPenjahitan = $tindakanPenjahitan;
                }
                ?>

                <?php
                $atoniaUteri = isset($dCatatanPersalinan['kala3_atonia_uteri'])
                    ? trim($dCatatanPersalinan['kala3_atonia_uteri'])
                    : '';

                $ketAtoniaUteri = isset($dCatatanPersalinan['kala3_atonia_uteri_ya'])
                    ? trim($dCatatanPersalinan['kala3_atonia_uteri_ya'])
                    : '';

                if ($atoniaUteri == 'Ya, tindakan') {

                    if ($ketAtoniaUteri == '') {
                        $ketAtoniaUteri = '-';
                    }

                    $tampilAtoniaUteri = $atoniaUteri . ' ' . $ketAtoniaUteri;
                } else {

                    $tampilAtoniaUteri = $atoniaUteri;
                }
                ?>

                <?php
                $bayiLahir = isset($dCatatanPersalinan['bayi_bayi_lahir'])
                    ? trim($dCatatanPersalinan['bayi_bayi_lahir'])
                    : '';

                $bayiLahirNormal = isset($dCatatanPersalinan['bayi_bayi_lahir_normal'])
                    ? trim($dCatatanPersalinan['bayi_bayi_lahir_normal'])
                    : '';

                $bayiLahirAsfiksiaTindakan = isset($dCatatanPersalinan['bayi_bayi_lahir_asfiksia_tindakan'])
                    ? trim($dCatatanPersalinan['bayi_bayi_lahir_asfiksia_tindakan'])
                    : '';

                $bayiLahirAsfiksiaSebutkan = isset($dCatatanPersalinan['bayi_bayi_lahir_asfiksia_tindakan_sebutkan'])
                    ? trim($dCatatanPersalinan['bayi_bayi_lahir_asfiksia_tindakan_sebutkan'])
                    : '';

                $bayiLahirCacatSebutkan = isset($dCatatanPersalinan['bayi_bayi_lahir_cacat_bawaan_sebutkan'])
                    ? trim($dCatatanPersalinan['bayi_bayi_lahir_cacat_bawaan_sebutkan'])
                    : '';

                $tampilBayiLahir = $bayiLahir . ' : ';

                if ($bayiLahir == 'Normal, tindakan') {

                    if ($bayiLahirNormal == '') {
                        $bayiLahirNormal = '-';
                    }

                    $tampilBayiLahir = $bayiLahir . ' : ' . $bayiLahirNormal;
                } else if (
                    $bayiLahir == 'Asfiksia ringan' ||
                    $bayiLahir == 'Asfiksia pucat' ||
                    $bayiLahir == 'Asfiksia biru' ||
                    $bayiLahir == 'Asfiksia lemas'
                ) {

                    if ($bayiLahirAsfiksiaTindakan == '') {
                        $bayiLahirAsfiksiaTindakan = '-';
                    }

                    if ($bayiLahirAsfiksiaTindakan == 'Lain-lain, sebutkan') {

                        if ($bayiLahirAsfiksiaSebutkan == '') {
                            $bayiLahirAsfiksiaSebutkan = '-';
                        }

                        $tampilBayiLahir = $bayiLahir . ', tindakan : ' . $bayiLahirAsfiksiaTindakan . ' : ' . $bayiLahirAsfiksiaSebutkan;
                    } else {

                        $tampilBayiLahir = $bayiLahir . ', tindakan : ' . $bayiLahirAsfiksiaTindakan;
                    }
                } else if ($bayiLahir == 'Cacat bawaan, sebutkan') {

                    if ($bayiLahirCacatSebutkan == '') {
                        $bayiLahirCacatSebutkan = '-';
                    }

                    $tampilBayiLahir = $bayiLahir . ' : ' . $bayiLahirCacatSebutkan;
                }

                $hipotermiaA = valCatatan($dCatatanPersalinan, 'bayi_bayi_lahir_hipotermia_a');
                $hipotermiaB = valCatatan($dCatatanPersalinan, 'bayi_bayi_lahir_hipotermia_b');
                $hipotermiaC = valCatatan($dCatatanPersalinan, 'bayi_bayi_lahir_hipotermia_c');

                if (trim($hipotermiaA) == '') {
                    $hipotermiaA = '...............';
                }

                if (trim($hipotermiaB) == '') {
                    $hipotermiaB = '...............';
                }

                if (trim($hipotermiaC) == '') {
                    $hipotermiaC = '...............';
                }
                ?>

                <?php
                $pemberianAsi = isset($dCatatanPersalinan['bayi_pemberian_asi'])
                    ? trim($dCatatanPersalinan['bayi_pemberian_asi'])
                    : '';

                $pemberianAsiYa = isset($dCatatanPersalinan['bayi_pemberian_asi_ya'])
                    ? trim($dCatatanPersalinan['bayi_pemberian_asi_ya'])
                    : '';

                $pemberianAsiTidak = isset($dCatatanPersalinan['bayi_pemberian_asi_tidak_alasan'])
                    ? trim($dCatatanPersalinan['bayi_pemberian_asi_tidak_alasan'])
                    : '';

                if ($pemberianAsi == 'Ya, waktu') {

                    if ($pemberianAsiYa == '') {
                        $pemberianAsiYa = '............';
                    }

                    $tampilPemberianAsi = $pemberianAsi . ' : ' . $pemberianAsiYa . ' Jam Setelah Bayi Lahir';
                } else if ($pemberianAsi == 'Tidak, alasan') {

                    if ($pemberianAsiTidak == '') {
                        $pemberianAsiTidak = '............';
                    }

                    $tampilPemberianAsi = $pemberianAsi . ' ' . $pemberianAsiTidak;
                } else {
                    $tampilPemberianAsi = $pemberianAsi;
                }
                ?>

                <!-- CATATAN PERSALINAN -->
                <div class="catatan-persalinan-box">

                    <!-- KOLOM 1 -->
                    <div class="catatan-kolom">

                        <div class="catatan-title">CATATAN PERSALINAN</div>

                        <div class="catatan-row">
                            <div class="catatan-no">1.</div>
                            <div class="catatan-isi">
                                Tanggal :
                                <span class="garis-isi"><?= valCatatan($dCatatanPersalinan, 'tglcttn') ?></span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">2.</div>
                            <div class="catatan-isi">
                                Nama bidan :
                                <span class="garis-isi"><?= valCatatan($dCatatanPersalinan, 'nmBidan') ?></span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">3.</div>
                            <div class="catatan-isi">
                                Tempat persalinan :
                                <span class="garis-isi"><?= htmlspecialchars($tampilTempatPersalinan) ?></span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">4.</div>

                            <div class="catatan-isi">
                                Alamat tempat persalinan :
                                <span class="garis-isi">
                                    <?= valCatatan($dCatatanPersalinan, 'cttn_alamat_tempat') ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">5.</div>

                            <div class="catatan-isi">
                                Catatan (Rujuk Kala) :
                                <span class="garis-isi">
                                    <?= valCatatan($dCatatanPersalinan, 'cttn_rujukan') ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">6.</div>

                            <div class="catatan-isi">
                                Alasan Merujuk :
                                <span class="garis-isi">
                                    <?= valCatatan($dCatatanPersalinan, 'cttn_alasan_merujuk') ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">7.</div>

                            <div class="catatan-isi">
                                Tempat Rujukan :
                                <span class="garis-isi">
                                    <?= valCatatan($dCatatanPersalinan, 'cttn_tempat_rujukan') ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">8.</div>

                            <div class="catatan-isi">
                                Pendamping Pada Saat Merujuk :
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilPendampingRujuk) ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">9.</div>

                            <div class="catatan-isi">
                                Masalah Dalam Kehamilan / Persalinan Ini :
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilMasalahKehamilan) ?>
                                </span>
                            </div>
                        </div>

                        <br>

                        <div class="catatan-title">KALA I</div>

                        <div class="catatan-row">
                            <div class="catatan-no">10.</div>

                            <div class="catatan-isi">
                                Partogram Melewati Garis Waspada :
                                <span class="garis-isi">
                                    <?= valCatatan($dCatatanPersalinan, 'kala1_partogram') ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">11.</div>

                            <div class="catatan-isi">
                                Masalah lain :
                                <span class="garis-isi">
                                    <?= valCatatan($dCatatanPersalinan, 'kala1_masalah_lain') ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">12.</div>

                            <div class="catatan-isi">
                                Penatalaksanaan masalah tsb :
                                <span class="garis-isi">
                                    <?= valCatatan($dCatatanPersalinan, 'kala1_penatalaksanaan') ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">13.</div>

                            <div class="catatan-isi">
                                Hasilnya :
                                <span class="garis-isi">
                                    <?= valCatatan($dCatatanPersalinan, 'kala1_hasilnya') ?>
                                </span>
                            </div>
                        </div>

                        <br>

                        <div class="catatan-title">KALA II</div>

                        <div class="catatan-row">
                            <div class="catatan-no">14.</div>

                            <div class="catatan-isi">
                                Episiotomi :
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilEpisiotomi) ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">15.</div>

                            <div class="catatan-isi">
                                Pendamping Pada Saat-saat Persalinan :
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilPendampingPersalinan) ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">16.</div>

                            <div class="catatan-isi">
                                Gawat Janin :
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilGawatJanin) ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no"></div>

                            <div class="catatan-isi">
                                Pemantauan DJJ Setiap 5-10 Menit Selama Kala II, hasil :
                                <span class="garis-isi">
                                    <?php
                                    $hasilPemantauanDjj = valCatatan($dCatatanPersalinan, 'kala2_ket_hasil_pemantauan');

                                    if (trim($hasilPemantauanDjj) == '') {
                                        $hasilPemantauanDjj = '-';
                                    }

                                    echo $hasilPemantauanDjj;
                                    ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">17.</div>

                            <div class="catatan-isi">
                                Distosia Bahu :
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilDistosiaBahu) ?>
                                </span>
                            </div>
                        </div>

                    </div>

                    <!-- KOLOM 2 -->
                    <div class="catatan-kolom">

                        <div class="catatan-row">
                            <div class="catatan-no">18.</div>

                            <div class="catatan-isi">
                                Masalah Lain, Penatalaksanaan Masalah Tersebut dan Hasilnya :
                                <span class="garis-isi">
                                    <?php
                                    $masalahLainKala2 = valCatatan($dCatatanPersalinan, 'kala2_masalah_lain_hasilnya');

                                    if (trim($masalahLainKala2) == '') {
                                        $masalahLainKala2 = '-';
                                    }

                                    echo $masalahLainKala2;
                                    ?>
                                </span>
                            </div>
                        </div>

                        <br>

                        <div class="catatan-title">KALA III</div>

                        <div class="catatan-row">
                            <div class="catatan-no">19.</div>

                            <div class="catatan-isi">
                                Inisiasi Menyusui Dini :
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilInisiasiMenyusui) ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">20.</div>

                            <div class="catatan-isi">
                                Lama Kala III :
                                <span class="garis-isi">
                                    <?php
                                    $lamaKala3 = valCatatan($dCatatanPersalinan, 'kala3_lama');

                                    if (trim($lamaKala3) == '') {
                                        $lamaKala3 = '......';
                                    }

                                    echo $lamaKala3;
                                    ?>
                                </span>
                                menit
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">21.</div>

                            <div class="catatan-isi">
                                Pemberian Oksitosin 10 U im ?
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilPemberianOksitosin) ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">22.</div>

                            <div class="catatan-isi">
                                Pemberian Ulang Oksitosin (2X) ?
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilPemberianUlangOksitosin) ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">23.</div>

                            <div class="catatan-isi">
                                Penegangan Tali Pusat terkendali :
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilPeneganganTaliPusat) ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">24.</div>

                            <div class="catatan-isi">
                                Masase Fundus Uteri :
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilMasaseFundus) ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">25.</div>

                            <div class="catatan-isi">
                                Plasenta Lahir Lengkap (intact) :
                                <span class="garis-isi">
                                    <?= valCatatan($dCatatanPersalinan, 'kala3_plasenta_lahir') ?>
                                </span>
                            </div>
                        </div>

                        <?php
                        $plasentaLahir = isset($dCatatanPersalinan['kala3_plasenta_lahir'])
                            ? trim($dCatatanPersalinan['kala3_plasenta_lahir'])
                            : '';
                        ?>

                        <?php if ($plasentaLahir == 'Tidak'): ?>

                            <div class="catatan-row">
                                <div class="catatan-no"></div>

                                <div class="catatan-isi">
                                    Jika tidak lengkap, tindakan yang dilakukan :
                                </div>
                            </div>

                            <div class="catatan-row">
                                <div class="catatan-no"></div>

                                <div class="catatan-isi">
                                    a.
                                    <span class="garis-isi">
                                        <?php
                                        $plasentaTidakA = valCatatan($dCatatanPersalinan, 'kala3_plasenta_lahir_tidak_a');

                                        if (trim($plasentaTidakA) == '') {
                                            $plasentaTidakA = '-';
                                        }

                                        echo $plasentaTidakA;
                                        ?>
                                    </span>
                                </div>
                            </div>

                            <div class="catatan-row">
                                <div class="catatan-no"></div>

                                <div class="catatan-isi">
                                    b.
                                    <span class="garis-isi">
                                        <?php
                                        $plasentaTidakB = valCatatan($dCatatanPersalinan, 'kala3_plasenta_lahir_tidak_b');

                                        if (trim($plasentaTidakB) == '') {
                                            $plasentaTidakB = '-';
                                        }

                                        echo $plasentaTidakB;
                                        ?>
                                    </span>
                                </div>
                            </div>

                        <?php endif; ?>

                        <div class="catatan-row">
                            <div class="catatan-no">26.</div>

                            <div class="catatan-isi">
                                Plasenta Tidak Lahir &gt; 30 Menit :
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilPlasentaTidakLahir) ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">27.</div>

                            <div class="catatan-isi">
                                Laserasi :
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilLaserasi) ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">28.</div>

                            <div class="catatan-isi">
                                Jika Laserasi Perineum, Derajat :
                                <span class="garis-isi">
                                    <?= valCatatan($dCatatanPersalinan, 'kala3_laserasi_perineum') ?>
                                </span>
                                Tindakan :
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilTindakanPenjahitan) ?>
                                </span>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <!-- HALAMAN BARU -->
            <div class="print-page halaman-berikutnya">

                <div class="catatan-persalinan-box">

                    <!-- KOLOM 1 -->
                    <div class="catatan-kolom">

                        <div class="catatan-row">
                            <div class="catatan-no">29.</div>

                            <div class="catatan-isi">
                                Atonia Uteri :
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilAtoniaUteri) ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">30.</div>

                            <div class="catatan-isi">
                                Jumlah Darah Yang Keluar/Perdarahan :
                                <span class="garis-isi">
                                    <?php
                                    $jumlahDarah = valCatatan($dCatatanPersalinan, 'kala3_jumlah_darah');

                                    if (trim($jumlahDarah) == '') {
                                        $jumlahDarah = '.......';
                                    }

                                    echo $jumlahDarah;
                                    ?>
                                </span>
                                ml.
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">31.</div>

                            <div class="catatan-isi">
                                Masalah :
                                <span class="garis-isi">
                                    <?php
                                    $masalahKala3 = valCatatan($dCatatanPersalinan, 'kala3_masalah');

                                    if (trim($masalahKala3) == '') {
                                        $masalahKala3 = '-';
                                    }

                                    echo $masalahKala3;
                                    ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">32.</div>

                            <div class="catatan-isi">
                                Penatalaksanaan Masalah Tersebut :
                                <span class="garis-isi">
                                    <?php
                                    $penatalaksanaanMasalahKala3 = valCatatan($dCatatanPersalinan, 'kala3_penatalaksanaan_masalah');

                                    if (trim($penatalaksanaanMasalahKala3) == '') {
                                        $penatalaksanaanMasalahKala3 = '-';
                                    }

                                    echo $penatalaksanaanMasalahKala3;
                                    ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">33.</div>

                            <div class="catatan-isi">
                                Hasilnya :
                                <span class="garis-isi">
                                    <?php
                                    $hasilKala3 = valCatatan($dCatatanPersalinan, 'kala3_hasilnya');

                                    if (trim($hasilKala3) == '') {
                                        $hasilKala3 = '-';
                                    }

                                    echo $hasilKala3;
                                    ?>
                                </span>
                            </div>
                        </div>

                        <br>
                        <div class="catatan-title">BAYI BARU LAHIR</div>

                        <div class="catatan-row">
                            <div class="catatan-no">34.</div>

                            <div class="catatan-isi">
                                Berat Badan :
                                <span class="garis-isi">
                                    <?php
                                    $bbBayi = valCatatan($dCatatanPersalinan, 'bayi_bb');

                                    if (trim($bbBayi) == '') {
                                        $bbBayi = '.........';
                                    }

                                    echo $bbBayi;
                                    ?>
                                </span>
                                gram
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">35.</div>

                            <div class="catatan-isi">
                                Panjang Badan :
                                <span class="garis-isi">
                                    <?php
                                    $pbBayi = valCatatan($dCatatanPersalinan, 'bayi_pb');

                                    if (trim($pbBayi) == '') {
                                        $pbBayi = '.........';
                                    }

                                    echo $pbBayi;
                                    ?>
                                </span>
                                cm
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">36.</div>

                            <div class="catatan-isi">
                                Jenis Kelamin :
                                <span class="garis-isi">
                                    <?= valCatatan($dCatatanPersalinan, 'bayi_jenkel') ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">37.</div>

                            <div class="catatan-isi">
                                Penilaian Bayi Baru Lahir :
                                <span class="garis-isi">
                                    <?= valCatatan($dCatatanPersalinan, 'bayi_penilaian') ?>
                                </span>
                            </div>
                        </div>

                    </div>

                    <!-- KOLOM 2 -->
                    <div class="catatan-kolom">

                        <div class="catatan-row">
                            <div class="catatan-no">38.</div>

                            <div class="catatan-isi">
                                Bayi Lahir :<br>
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilBayiLahir) ?>
                                </span>
                            </div>
                        </div>

                        <?php if ($bayiLahir == 'Hipotermi, tindakan'): ?>

                            <div class="catatan-row">
                                <div class="catatan-no"></div>

                            </div>

                            <div class="catatan-row">
                                <div class="catatan-no"></div>

                                <div class="catatan-isi">
                                    a.
                                    <span class="garis-isi"><?= $hipotermiaA ?></span>
                                </div>
                            </div>

                            <div class="catatan-row">
                                <div class="catatan-no"></div>

                                <div class="catatan-isi">
                                    b.
                                    <span class="garis-isi"><?= $hipotermiaB ?></span>
                                </div>
                            </div>

                            <div class="catatan-row">
                                <div class="catatan-no"></div>

                                <div class="catatan-isi">
                                    c.
                                    <span class="garis-isi"><?= $hipotermiaC ?></span>
                                </div>
                            </div>

                        <?php endif; ?>

                        <div class="catatan-row">
                            <div class="catatan-no">39.</div>

                            <div class="catatan-isi">
                                Pemberian ASI :<br>
                                <span class="garis-isi">
                                    <?= htmlspecialchars($tampilPemberianAsi) ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no">40.</div>

                            <div class="catatan-isi">
                                Masalah Lain, Sebutkan :
                                <span class="garis-isi">
                                    <?php
                                    $masalahLainBayi = valCatatan($dCatatanPersalinan, 'bayi_masalah_lain');

                                    if (trim($masalahLainBayi) == '') {
                                        $masalahLainBayi = '..............';
                                    }

                                    echo $masalahLainBayi;
                                    ?>
                                </span>
                            </div>
                        </div>

                        <div class="catatan-row">
                            <div class="catatan-no"></div>

                            <div class="catatan-isi">
                                Hasilnya :
                                <span class="garis-isi">
                                    <?php
                                    $hasilMasalahBayi = valCatatan($dCatatanPersalinan, 'bayi_hasilnya');

                                    if (trim($hasilMasalahBayi) == '') {
                                        $hasilMasalahBayi = '..............';
                                    }

                                    echo $hasilMasalahBayi;
                                    ?>
                                </span>
                            </div>
                        </div>

                    </div> <!-- penutup KOLOM 2 -->

                </div> <!-- penutup catatan-persalinan-box -->

                <br>
                <div class="catatan-title pemantauan-kala-iv">
                    PEMANTAUAN PERSALINAN KALA IV
                </div>

                <table class="tabel-kala-iv">
                    <thead>
                        <tr>
                            <th class="kol-jam">Jam Ke</th>
                            <th class="kol-waktu">Waktu</th>
                            <th class="kol-td">Tekanan<br>darah</th>
                            <th class="kol-kecil">Nadi</th>
                            <th class="kol-kecil">Suhu</th>
                            <th class="kol-sedang">Tinggi Fundus<br>Uteri</th>
                            <th class="kol-sedang">Kontraksi<br>Uterus</th>
                            <th class="kol-sedang">Urin Output</th>
                            <th class="kol-sedang">Darah yg keluar</th>
                        </tr>
                    </thead>

                    <tbody>

                        <?php foreach ($dataKala4 as $rowKala4): ?>

                            <tr>
                                <td><?= isset($rowKala4['jam_ke']) ? htmlspecialchars($rowKala4['jam_ke']) : '' ?></td>

                                <td><?= isset($rowKala4['jamkala4']) ? htmlspecialchars($rowKala4['jamkala4']) : '' ?></td>

                                <td><?= isset($rowKala4['tekanan_darah']) ? htmlspecialchars($rowKala4['tekanan_darah']) : '' ?></td>

                                <td><?= isset($rowKala4['nadi']) ? htmlspecialchars($rowKala4['nadi']) : '' ?></td>

                                <td><?= isset($rowKala4['suhu']) ? htmlspecialchars($rowKala4['suhu']) : '' ?></td>

                                <td class="kiri-atas">
                                    <?= isset($rowKala4['tinggi_fundus_uteri']) ? htmlspecialchars($rowKala4['tinggi_fundus_uteri']) : '' ?>
                                </td>

                                <td class="kiri-atas">
                                    <?= isset($rowKala4['kontraksi_uterus']) ? htmlspecialchars($rowKala4['kontraksi_uterus']) : '' ?>
                                </td>

                                <td class="kiri-atas">
                                    <?= isset($rowKala4['urin_output']) ? htmlspecialchars($rowKala4['urin_output']) : '' ?>
                                </td>

                                <td class="kiri-atas">
                                    <?= isset($rowKala4['darah_yang_keluar']) ? htmlspecialchars($rowKala4['darah_yang_keluar']) : '' ?>
                                </td>
                            </tr>

                        <?php endforeach; ?>

                    </tbody>
                </table>

                <div class="keterangan-kala-iv">

                    <div class="catatan-row">
                        <div class="catatan-isi">
                            Masalah Kala IV :
                            <span class="garis-isi">
                                <?php
                                $masalahKala4 = valCatatan($dPemantauanKala4, 'masalah');

                                if (trim($masalahKala4) == '') {
                                    $masalahKala4 = '.............';
                                }

                                echo $masalahKala4;
                                ?>
                            </span>
                        </div>
                    </div>

                    <div class="catatan-row">
                        <div class="catatan-isi">
                            Penatalaksanaan Masalah Tersebut :
                            <span class="garis-isi">
                                <?php
                                $penatalaksanaanKala4 = valCatatan($dPemantauanKala4, 'penatalaksanaan');

                                if (trim($penatalaksanaanKala4) == '') {
                                    $penatalaksanaanKala4 = '.............';
                                }

                                echo $penatalaksanaanKala4;
                                ?>
                            </span>
                        </div>
                    </div>

                    <div class="catatan-row">
                        <div class="catatan-isi">
                            Hasilnya :
                            <span class="garis-isi">
                                <?php
                                $hasilKala4 = valCatatan($dPemantauanKala4, 'hasilnya');

                                if (trim($hasilKala4) == '') {
                                    $hasilKala4 = '.............';
                                }

                                echo $hasilKala4;
                                ?>
                            </span>
                        </div>
                    </div>

                </div>

            </div>

        </div>

    </div>

    </div>

    </div>

</body>

</html>