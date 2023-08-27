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
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Ar-Rahman%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Ar-Rahman%' and bangsal2.nm_bangsal not like '%Ar-Rahman%')
order by kamar_inap.tgl_keluar desc"));


$out_bedah=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Al-Muiz%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Al-Muiz%' and bangsal2.nm_bangsal not like '%Al-Muiz%')
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
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Ar-Razaq%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Ar-Razaq%' and bangsal2.nm_bangsal not like '%Ar-Razaq%')
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

$out_arraudah_mthtkl=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Ar-Raudah/Mata%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Ar-Raudah/Mata%' and bangsal2.nm_bangsal not like '%Ar-Raudah/Mata%')
order by kamar_inap.tgl_keluar desc"));

$out_arraudah_syrf=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Ar-Raudah/Sya%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Ar-Raudah/Sya%' and bangsal2.nm_bangsal not like '%Ar-Raudah/Sya%')
order by kamar_inap.tgl_keluar desc"));

$out_bersalin=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Al-Khaliq%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Al-Khaliq%' and bangsal2.nm_bangsal not like '%Al-Khaliq%')
order by kamar_inap.tgl_keluar desc"));


$out_paru=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap2.kd_kamar,bangsal2.nm_bangsal, kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Al-Hakim%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Al-Hakim%' and bangsal2.nm_bangsal not like '%Al-Hakim%')
order by kamar_inap.tgl_keluar desc"));

/*
$out_anak=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Ar-Rahman%' group by kamar_inap.no_rawat"));
$out_bedah=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Al-Muiz%' group by kamar_inap.no_rawat"));
$out_icu=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%ICCU%' group by kamar_inap.no_rawat"));
$out_assami=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%As-Sami%' group by kamar_inap.no_rawat"));
$out_vip=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%VIP-%' group by kamar_inap.no_rawat"));
$out_rkpd=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Ar-Razaq%' group by kamar_inap.no_rawat"));
$out_zaal=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Zaal%' group by kamar_inap.no_rawat"));
$out_arraudah=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Ar-Raudah%' group by kamar_inap.no_rawat"));
$out_bersalin=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Al-Khaliq%' group by kamar_inap.no_rawat"));
$out_paru=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Al-Hakim%' group by kamar_inap.no_rawat"));
*/

$m_anak=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Ar-Rahman%' and kamar_inap.stts_pulang like '%Meninggal%'"));
$m_bedah=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Al-Muiz%' and kamar_inap.stts_pulang like '%Meninggal%'"));
$m_icu=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%ICCU%' and kamar_inap.stts_pulang like '%Meninggal%'"));
$m_assami=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%As-Sami%' and kamar_inap.stts_pulang like '%Meninggal%'"));
$m_vip=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%VIP-%' and kamar_inap.stts_pulang like '%Meninggal%'"));
$m_rkpd=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Ar-Razaq%' and kamar_inap.stts_pulang like '%Meninggal%'"));
$m_zaal=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Zaal%' and kamar_inap.stts_pulang like '%Meninggal%'"));
$m_arraudah_mthtkl=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Ar-Raudah/Mata%' and kamar_inap.stts_pulang like '%Meninggal%'"));
$m_arraudah_syrf=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Ar-Raudah/Sya%' and kamar_inap.stts_pulang like '%Meninggal%'"));
$m_bersalin=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Al-Khaliq%' and kamar_inap.stts_pulang like '%Meninggal%'"));
$m_paru=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Al-Hakim%' and kamar_inap.stts_pulang like '%Meninggal%'"));

$m48_anak=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Ar-Rahman%' and kamar_inap.stts_pulang like '%Meninggal >%'"));
$m48_bedah=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Al-Muiz%' and kamar_inap.stts_pulang like '%Meninggal >%'"));
$m48_icu=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%ICCU%' and kamar_inap.stts_pulang like '%Meninggal >%'"));
$m48_assami=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%As-Sami%' and kamar_inap.stts_pulang like '%Meninggal >%'"));
$m48_vip=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%VIP-%' and kamar_inap.stts_pulang like '%Meninggal >%'"));
$m48_rkpd=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Ar-Razaq%' and kamar_inap.stts_pulang like '%Meninggal >%'"));
$m48_zaal=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Zaal%' and kamar_inap.stts_pulang like '%Meninggal >%'"));
$m48_arraudah_mthtkl=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Ar-Raudah/Mata%' and kamar_inap.stts_pulang like '%Meninggal >%'"));
$m48_arraudah_syrf=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Ar-Raudah/Sya%' and kamar_inap.stts_pulang like '%Meninggal >%'"));
$m48_bersalin=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Al-Khaliq%' and kamar_inap.stts_pulang like '%Meninggal >%'"));
$m48_paru=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Al-Hakim%' and kamar_inap.stts_pulang like '%Meninggal >%'"));

if (isset($_GET['bayi']))
{
$m_nicu=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%NICU%' and kamar_inap.stts_pulang like '%Meninggal%'"));
$m48_nicu=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%NICU%' and kamar_inap.stts_pulang like '%Meninggal >%'"));

$m_bayi=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Bayi%' and kamar_inap.stts_pulang like '%Meninggal%'"));
$m48_bayi=mysql_num_rows(mysql_query("select kamar_inap.no_rawat from kamar_inap left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where kamar_inap.tgl_keluar between '".$tgl1."' and '".$tgl2."' and bangsal.nm_bangsal like '%Bayi%' and kamar_inap.stts_pulang like '%Meninggal >%'"));

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
	<Center><h3>Data NDR, GDR<br/>Periode <?php echo $tgl1; ?> s/d <?php echo $tgl2; ?></h3></center>
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
		<th align="center" style="vertical-align: middle;text-align: center;">Ar-Raudah<br/>MTHTKL</th>
		<th align="center" style="vertical-align: middle;text-align: center;">Ar-Raudah<br/>Syaraf</th>
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
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_arraudah_mthtkl; ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_arraudah_syrf; ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_bersalin; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_paru; ?></td>
<?php 
$out_total=mysql_num_rows(mysql_query("select kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.tgl_keluar,kamar_inap.stts_pulang from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal not like '%Bayi%' and bangsal.nm_bangsal not like '%Perinatologi%'
and kamar_inap.stts_pulang <> 'Pindah Kamar'"));
//$out_total=$out_anak+$out_bedah+$out_icu+$out_assami+$out_vip+$out_rkpd+$out_zaal+$out_arraudah+$out_bersalin+$out_paru;
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

		<tr>
		<td align="center" style="vertical-align: middle;text-align: center;"><b>&Sigma; Meninggal</b></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m_anak; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m_bedah; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m_icu; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m_assami; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m_vip; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m_rkpd; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m_zaal; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m_arraudah_mthtkl; ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m_arraudah_syrf; ?></td>	
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m_bersalin; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m_paru; ?></td>
<?php 
$m_total=$m_anak+$m_bedah+$m_icu+$m_assami+$m_vip+$m_rkpd+$m_zaal+$m_arraudah_mthtkl+$m_arraudah_syrf+$m_bersalin+$m_paru;
?>		
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-info"><?php echo $m_total; ?></td>
<?php
if (isset($_GET['bayi']))
{		
?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m_nicu; ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m_bayi; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-info"><?php $m_bayitotal=$m_nicu+$m_bayi; echo $m_bayitotal; ?></td>			
<?php
}
?>			
		</tr>	

		<tr>
		<td align="center" style="vertical-align: middle;text-align: center;"><b>&Sigma; Mati > 48 jam</b></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m48_anak; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m48_bedah; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m48_icu; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m48_assami; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m48_vip; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m48_rkpd; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m48_zaal; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m48_arraudah_mthtkl; ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m48_arraudah_syrf; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m48_bersalin; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m48_paru; ?></td>
<?php 
$m48_total=$m48_anak+$m48_bedah+$m48_icu+$m48_assami+$m48_vip+$m48_rkpd+$m48_zaal+$m48_arraudah_mthtkl+$m48_arraudah_syrf+$m48_bersalin+$m48_paru;
?>		
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-info"><?php echo $m48_total; ?></td>
<?php
if (isset($_GET['bayi']))
{		
?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m48_nicu; ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $m48_bayi; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-info"><?php $m48_bayitotal=$m48_nicu+$m48_bayi; echo $m48_bayitotal; ?></td>			
<?php
}
?>		
		</tr>		
		
		<tr class="bg-success">
		<td align="center" style="vertical-align: middle;text-align: center;"><b>GDR</b></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $gdr_anak=($m_anak/$out_anak)*1000; echo round($gdr_anak,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $gdr_bedah=($m_bedah/$out_bedah)*1000; echo round($gdr_bedah,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $gdr_icu=($m_icu/$out_icu)*1000; echo round($gdr_icu,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $gdr_assami=($m_assami/$out_assami)*1000; echo round($gdr_assami,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $gdr_vip=($m_vip/$out_vip)*1000; echo round($gdr_vip,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $gdr_rkpd=($m_rkpd/$out_rkpd)*1000; echo round($gdr_rkpd,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $gdr_zaal=($m_zaal/$out_zaal)*1000; echo round($gdr_zaal,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $gdr_arraudah_mthtkl=($m_arraudah_mthtkl/$out_arraudah_mthtkl)*1000; echo round($gdr_arraudah_mthtkl,2); ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $gdr_arraudah_syrf=($m_arraudah_syrf/$out_arraudah_syrf)*1000; echo round($gdr_arraudah_syrf,2); ?></td>	
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $gdr_bersalin=($m_bersalin/$out_bersalin)*1000; echo round($gdr_bersalin,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $gdr_paru=($m_paru/$out_paru)*1000; echo round($gdr_paru,2); ?></td>
<?php 
$gdr_total=($m_total/$out_total)*1000;
?>		
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-primary"><?php echo round($gdr_total,2); ?></td>
<?php
if (isset($_GET['bayi']))
{		
?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $gdr_nicu=($m_nicu/$out_nicu)*1000; echo round($gdr_nicu,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $gdr_bayi=($m_bayi/$out_bayi)*1000; echo round($gdr_bayi,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-primary"><?php $gdr_bayitotal=($m_bayitotal/$out_totalbayi)*1000; echo round($gdr_bayitotal,2); ?></td>			
<?php
}
?>			
		</tr>

		<tr class="bg-success">
		<td align="center" style="vertical-align: middle;text-align: center;"><b>NDR</b></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $ndr_anak=($m48_anak/$out_anak)*1000; echo round($ndr_anak,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $ndr_bedah=($m48_bedah/$out_bedah)*1000; echo round($ndr_bedah,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $ndr_icu=($m48_icu/$out_icu)*1000; echo round($ndr_icu,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $ndr_assami=($m48_assami/$out_assami)*1000; echo round($ndr_assami,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $ndr_vip=($m48_vip/$out_vip)*1000; echo round($ndr_vip,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $ndr_rkpd=($m48_rkpd/$out_rkpd)*1000; echo round($ndr_rkpd,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $ndr_zaal=($m48_zaal/$out_zaal)*1000; echo round($ndr_zaal,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $ndr_arraudah_mthtkl=($m48_arraudah_mthtkl/$out_arraudah_mthtkl)*1000; echo round($ndr_arraudah_mthtkl,2); ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $ndr_arraudah_syrf=($m48_arraudah_syrf/$out_arraudah_syrf)*1000; echo round($ndr_arraudah_syrf,2); ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $ndr_bersalin=($m48_bersalin/$out_bersalin)*1000; echo round($ndr_bersalin,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $ndr_paru=($m48_paru/$out_paru)*1000; echo round($ndr_paru,2); ?></td>
<?php 
$ndr_total=($m48_total/$out_total)*1000;
?>		
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-primary"><?php echo round($ndr_total,2); ?></td>
<?php
if (isset($_GET['bayi']))
{		
?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $ndr_nicu=($m48_nicu/$out_nicu)*1000; echo round($ndr_nicu,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php $ndr_bayi=($m48_bayi/$out_bayi)*1000; echo round($ndr_bayi,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-primary"><?php $ndr_bayitotal=($m48_bayitotal/$out_totalbayi)*1000; echo round($ndr_bayitotal,2); ?></td>			
<?php
}
?>				
		</tr>
		
	</table>
	</div>
</div>
<div class="row">
	<div class="col-lg-4">
	<b>GDR (Gross Death Rate)</b><br/>
	Depkes RI (2005)<br/>
	- Angka kematian umum untuk setiap 1000 penderita keluar.<br/>
	</div>	
	<div class="col-lg-4">
	<b>NDR (Net Death Rate)</b><br/>
	Depkes RI (2005)<br/>
	- Angka kematian 48 jam setelah dirawat untuk tiap-tiap 1000 penderita keluar.<br/>
	</div>		
</div>
<div class="row">
<br/>
</div>
<?php 
}
?>
        
<?php
include('bawah.php');
?>