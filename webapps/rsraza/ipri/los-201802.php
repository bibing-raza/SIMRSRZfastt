<?php
include('inc/config.inc.php');
include('atas.php');

if (isset($_GET['tgl_awal']) and isset($_GET['tgl_akhir']))
{
$tgl1=$_GET['tgl_awal'];
$tgl2=$_GET['tgl_akhir'];

$out_anak=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Anak%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Anak%' and bangsal2.nm_bangsal not like '%Anak%')
order by kamar_inap.tgl_keluar desc"));


$out_bedah=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Bedah%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Bedah%' and bangsal2.nm_bangsal not like '%Bedah%')
order by kamar_inap.tgl_keluar desc"));

$out_icu=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%ICCU%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%ICCU%' and bangsal2.nm_bangsal not like '%ICCU%')
order by kamar_inap.tgl_keluar desc"));

$out_assami=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%As-Sami%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%As-Sami%' and bangsal2.nm_bangsal not like '%As-Sami%')
order by kamar_inap.tgl_keluar desc"));

$out_vip=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%VIP-%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%VIP-%' and bangsal2.nm_bangsal not like '%VIP-%')
order by kamar_inap.tgl_keluar desc"));

$out_rkpd=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%internist%' and bangsal.nm_bangsal not like '%zaal%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%internist%' and bangsal2.nm_bangsal not like '%internist%')
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%internist%' and bangsal2.nm_bangsal like '%zaal%')
order by kamar_inap.tgl_keluar desc"));

$out_zaal=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Zaal%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Zaal%' and bangsal2.nm_bangsal not like '%Zaal%')
order by kamar_inap.tgl_keluar desc"));

$out_arraudah=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Ar-Raudah%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Ar-Raudah%' and bangsal2.nm_bangsal not like '%Ar-Raudah%')
order by kamar_inap.tgl_keluar desc"));

$out_bersalin=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Obgyn%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Obgyn%' and bangsal2.nm_bangsal not like '%Obgyn%')
order by kamar_inap.tgl_keluar desc"));


$out_paru=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Paru%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Paru%' and bangsal2.nm_bangsal not like '%Paru%')
order by kamar_inap.tgl_keluar desc"));

/*
$out_anak=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Anak%' group by kamar_inap.no_rawat"));
$out_bedah=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Bedah%' group by kamar_inap.no_rawat"));
$out_icu=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%ICCU%' group by kamar_inap.no_rawat"));
$out_assami=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%As-Sami%' group by kamar_inap.no_rawat"));
$out_vip=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%VIP-%' group by kamar_inap.no_rawat"));
$out_rkpd=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%internist%' and  bangsal.nm_bangsal not like '%Zaal%' group by kamar_inap.no_rawat"));
$out_zaal=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Zaal%' group by kamar_inap.no_rawat"));
$out_arraudah=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Ar-Raudah%' group by kamar_inap.no_rawat"));
$out_bersalin=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Obgyn%' group by kamar_inap.no_rawat"));
$out_paru=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Paru%' group by kamar_inap.no_rawat"));
*/

$qlos_anak=mysql_query("select sum(kamar_inap.lama) los from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Anak%'");
$qlos_bedah=mysql_query("select sum(kamar_inap.lama) los from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Bedah%'");
$qlos_icu=mysql_query("select sum(kamar_inap.lama) los from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%ICCU%'");
$qlos_assami=mysql_query("select sum(kamar_inap.lama) los from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%As-Sami%'");
$qlos_vip=mysql_query("select sum(kamar_inap.lama) los from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%VIP-%'");
$qlos_rkpd=mysql_query("select sum(kamar_inap.lama) los from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%internist%' and  bangsal.nm_bangsal not like '%Zaal%'");
$qlos_zaal=mysql_query("select sum(kamar_inap.lama) los from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Zaal%'");
$qlos_arraudah=mysql_query("select sum(kamar_inap.lama) los from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Ar-Raudah%'");
$qlos_bersalin=mysql_query("select sum(kamar_inap.lama) los from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Obgyn%'");
$qlos_paru=mysql_query("select sum(kamar_inap.lama) los from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Paru%'");

if (isset($_GET['bayi']))
{
$qlos_nicu=mysql_query("select sum(kamar_inap.lama) los from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%NICU%'");
$qlos_bayi=mysql_query("select sum(kamar_inap.lama) los from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Bayi%'");

$out_nicu=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%nicu%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%nicu%' and bangsal2.nm_bangsal not like '%nicu%')
order by kamar_inap.tgl_keluar desc"));	

$out_bayi=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%bayi%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%bayi%' and bangsal2.nm_bangsal not like '%bayi%')
order by kamar_inap.tgl_keluar desc"));	

$out_totalbayi=mysql_num_rows(mysql_query("select kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.tgl_keluar,kamar_inap.stts_pulang from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and kamar_inap.kd_kamar like '%BY/%'
and kamar_inap.stts_pulang <> 'Pindah Kamar'"));
}
?>


<div class="row">
	<div class="col-lg-12">
	<Center><h3>Data LOS, AVLOS<br/>Periode <?php echo $tgl1; ?> s/d <?php echo $tgl2; ?></h3></center>
	</div>
	<div class="col-lg-12">
	<table class="table table-responsive">
		<tr>
		<th align="center" style="vertical-align: middle;text-align: center;">Ruangan</th>
		<th align="center" style="vertical-align: middle;text-align: center;">Anak</th>
		<th align="center" style="vertical-align: middle;text-align: center;">Bedah</th>
		<th align="center" style="vertical-align: middle;text-align: center;">ICU/ICCU</th>
		<th align="center" style="vertical-align: middle;text-align: center;">As-Sami</th>
		<th align="center" style="vertical-align: middle;text-align: center;">VIP/SVIP Intan</th>
		<th align="center" style="vertical-align: middle;text-align: center;">RKPD</th>
		<th align="center" style="vertical-align: middle;text-align: center;">ZAAL</th>		
		<th align="center" style="vertical-align: middle;text-align: center;">Ar-Raudah</th>
		<th align="center" style="vertical-align: middle;text-align: center;">Bersalin</th>
		<th align="center" style="vertical-align: middle;text-align: center;">Paru</th>
		<th align="center" style="vertical-align: middle;text-align: center;">&Sigma; Dewasa</th>
<?php
if (isset($_GET['bayi']))
{		
?>
		<th align="center" style="vertical-align: middle;text-align: center;">NICU</th>
		<th align="center" style="vertical-align: middle;text-align: center;">Bayi</th>
		<th align="center" style="vertical-align: middle;text-align: center;">&Sigma; Perinatologi</th>
<?php
}
?>			
		</tr>		
		<tr>
		<td align="center" style="vertical-align: middle;text-align: center;"><b>&Sigma; Pasien Keluar</b></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_anak; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_bedah; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_icu; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_assami; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_vip; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_rkpd; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_zaal; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_arraudah; ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_bersalin; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_paru; ?></td>

<?php 
//$out_total=$out_anak+$out_bedah+$out_icu+$out_assami+$out_vip+$out_rkpd+$out_zaal+$out_arraudah+$out_bersalin+$out_paru;
$out_total=mysql_num_rows(mysql_query("select kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.tgl_keluar,kamar_inap.stts_pulang from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal not like '%Bayi%' and bangsal.nm_bangsal not like '%Perinatologi%'
and kamar_inap.stts_pulang <> 'Pindah Kamar'"));
?>		
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-info"><?php echo $out_total; ?></td>
		
<?php
if (isset($_GET['bayi']))
{		
?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_nicu; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_bayi; ?></td>	
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-info"><?php echo $out_totalbayi; ?></td>			
<?php
}
?>		
		
		</tr>		
		<tr class="bg-success">
		<td align="center" style="vertical-align: middle;text-align: center;"><b>LOS</b></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $los_anak=mysql_fetch_row($qlos_anak); echo $los_anak[0]; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $los_bedah=mysql_fetch_row($qlos_bedah); echo $los_bedah[0]; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $los_icu=mysql_fetch_row($qlos_icu); echo $los_icu[0]; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $los_assami=mysql_fetch_row($qlos_assami); echo $los_assami[0]; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $los_vip=mysql_fetch_row($qlos_vip); echo $los_vip[0]; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $los_rkpd=mysql_fetch_row($qlos_rkpd); echo $los_rkpd[0]; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $los_zaal=mysql_fetch_row($qlos_zaal); echo $los_zaal[0]; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $los_arraudah=mysql_fetch_row($qlos_arraudah); echo $los_arraudah[0]; ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $los_bersalin=mysql_fetch_row($qlos_bersalin); echo $los_bersalin[0]; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $los_paru=mysql_fetch_row($qlos_paru); echo $los_paru[0]; ?></td>

<?php 
$los_total=$los_anak[0]+$los_bedah[0]+$los_icu[0]+$los_assami[0]+$los_vip[0]+$los_rkpd[0]+$los_zaal[0]+$los_arraudah[0]+$los_bersalin[0]+$los_paru[0]; 
?>		
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-primary"><?php echo $los_total; ?></td>
<?php
if (isset($_GET['bayi']))
{		
?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $los_nicu=mysql_fetch_row($qlos_nicu); echo $los_nicu[0]; ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $los_bayi=mysql_fetch_row($qlos_bayi); echo $los_bayi[0]; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-primary"><?php $los_bayitotal=$los_nicu[0]+$los_bayi[0]; echo $los_bayitotal; ?></td>			
<?php
}
?>			
		</tr>		

		<tr class="bg-success">
		<td align="center" style="vertical-align: middle;text-align: center;"><b>AVLOS</b></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $avlos_anak=$los_anak[0]/$out_anak; echo round($avlos_anak,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $avlos_bedah=$los_bedah[0]/$out_bedah; echo round($avlos_bedah,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $avlos_icu=$los_icu[0]/$out_icu; echo round($avlos_icu,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $avlos_assami=$los_assami[0]/$out_assami; echo round($avlos_assami,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $avlos_vip=$los_vip[0]/$out_vip; echo round($avlos_vip,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $avlos_rkpd=$los_rkpd[0]/$out_rkpd; echo round($avlos_rkpd,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $avlos_zaal=$los_zaal[0]/$out_zaal; echo round($avlos_zaal,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $avlos_arraudah=$los_arraudah[0]/$out_arraudah; echo round($avlos_arraudah,2); ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $avlos_bersalin=$los_bersalin[0]/$out_bersalin; echo round($avlos_bersalin,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $avlos_paru=$los_paru[0]/$out_paru; echo round($avlos_paru,2); ?></td>

<?php 
$avlos_total=$los_total/$out_total; 
?>		
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-primary"><?php echo round($avlos_total,2); ?></td>
<?php
if (isset($_GET['bayi']))
{		
?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $avlos_nicu=$los_nicu[0]/$out_nicu; echo round($avlos_nicu,2); ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $avlos_bayi=$los_bayi[0]/$out_bayi; echo round($avlos_bayi,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-primary"><?php $avlos_bayitotal=$los_bayitotal/$out_totalbayi; echo round($avlos_bayitotal,2); ?></td>			
<?php
}
?>			
		</tr>
			
	</table>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
	<b>AVLOS (Average Length of Stay = Rata-rata lamanya pasien dirawat)</b><br/>
	Depkes RI (2005)<br/>
	- Rata-rata lama rawat seorang pasien.<br/>
	- Nilai ideal AVLOS = 6 – 9 hari.
	</div>	
</div>
<?php 
}
?>
        
<?php
include('bawah.php');
?>