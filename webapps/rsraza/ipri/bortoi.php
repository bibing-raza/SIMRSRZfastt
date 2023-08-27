<?php
include('inc/config.inc.php');
include('atas.php');

$r_anak=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%Ar-Rahman%' and kamar.statusdata='1'"));
$r_bedah=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%Al-Muiz%' and kamar.statusdata='1'"));
$r_icu=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%ICCU%' and kamar.statusdata='1'"));
$r_assami=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%As-Sami%' and kamar.statusdata='1'"));
$r_vip=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%VIP-%' and kamar.statusdata='1'"));
$r_rkpd=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%Ar-Razaq%' and kamar.statusdata='1'"));
$r_zaal=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%Zaal%' and kamar.statusdata='1'"));
$r_arraudah_mthtkl=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%Ar-Raudah/Mata%' and kamar.statusdata='1'"));
$r_arraudah_syrf=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%Ar-Raudah/Sya%' and kamar.statusdata='1'"));
$r_bersalin=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%Al-Khaliq%' and kamar.statusdata='1'"));
$r_paru=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%Al-Hakim%' and kamar.statusdata='1'"));

if (isset($_GET['bayi']))
{
$r_nicu=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%NICU%'"));
//$r_bayi=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%Bayi%'"));
$r_bayi=15;
}

if (isset($_GET['tgl_awal']) and isset($_GET['tgl_akhir']))
{
$tgl1=$_GET['tgl_awal'];
$tgl2=$_GET['tgl_akhir'];
?>
<div class="row">
	<div class="col-lg-12">
	<Center><h3>Data BOR, TOI, BTO<br/>Periode <?php echo $tgl1; ?> s/d <?php echo $tgl2; ?></h3></center>
	</div>
	<div class="col-lg-12">
	<table class="table table-responsive">
<?php
$qhp_anak=0;
$qhp_bedah=0;
$qhp_icu=0;
$qhp_assami=0;
$qhp_vip=0;
$qhp_rkpd=0;
$qhp_zaal=0;
$qhp_arraudah=0;
$qhp_arraudah_mthtkl=0;
$qhp_arraudah_syrf=0;
$qhp_bersalin=0;
$qhp_paru=0;
$qhp_nicu=0;
$qhp_bayi=0;
$periode=0;
while(strtotime($tgl1) <= strtotime($tgl2) ) {
$periode++;	

$hp_anak=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <'".$tgl1."' and kamar_inap.tgl_keluar='0000-00-00' and bangsal.nm_bangsal like '%Ar-Rahman%') 
or (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and bangsal.nm_bangsal like '%Ar-Rahman%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and bangsal.nm_bangsal like '%Ar-Rahman%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_anak=$qhp_anak+$hp_anak;

$hp_bedah=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <'".$tgl1."' and kamar_inap.tgl_keluar='0000-00-00' and bangsal.nm_bangsal like '%Al-Muiz%') 
or (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and bangsal.nm_bangsal like '%Al-Muiz%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and bangsal.nm_bangsal like '%Al-Muiz%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_bedah=$qhp_bedah+$hp_bedah;

$hp_icu=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <'".$tgl1."' and kamar_inap.tgl_keluar='0000-00-00' and bangsal.nm_bangsal like '%ICCU%') 
or (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and bangsal.nm_bangsal like '%ICCU%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and bangsal.nm_bangsal like '%ICCU%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_icu=$qhp_icu+$hp_icu;

$hp_assami=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <'".$tgl1."' and kamar_inap.tgl_keluar='0000-00-00' and bangsal.nm_bangsal like '%As-Sami%') 
or (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and bangsal.nm_bangsal like '%As-Sami%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and bangsal.nm_bangsal like '%As-Sami%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_assami=$qhp_assami+$hp_assami;

$hp_vip=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <'".$tgl1."' and kamar_inap.tgl_keluar='0000-00-00' and bangsal.nm_bangsal like '%VIP-%') 
or (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and bangsal.nm_bangsal like '%VIP-%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and bangsal.nm_bangsal like '%VIP-%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_vip=$qhp_vip+$hp_vip;

$hp_rkpd=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <'".$tgl1."' and kamar_inap.tgl_keluar='0000-00-00' and nm_bangsal like '%Ar-Razaq%') 
or (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and nm_bangsal like '%Ar-Razaq%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and nm_bangsal like '%Ar-Razaq%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_rkpd=$qhp_rkpd+$hp_rkpd;

$hp_zaal=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <'".$tgl1."' and kamar_inap.tgl_keluar='0000-00-00' and bangsal.nm_bangsal like '%zaal%') 
or (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and nm_bangsal like '%zaal%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and nm_bangsal like '%zaal%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_zaal=$qhp_zaal+$hp_zaal;

$hp_arraudah_mthtkl=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <'".$tgl1."' and kamar_inap.tgl_keluar='0000-00-00' and bangsal.nm_bangsal like '%Ar-Raudah/Mata%') 
or (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and nm_bangsal like '%Ar-Raudah/Mata%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and nm_bangsal like '%Ar-Raudah/Mata%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_arraudah_mthtkl=$qhp_arraudah_mthtkl+$hp_arraudah_mthtkl;

$hp_arraudah_syrf=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <'".$tgl1."' and kamar_inap.tgl_keluar='0000-00-00' and bangsal.nm_bangsal like '%Ar-Raudah/Sya%') 
or (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and nm_bangsal like '%Ar-Raudah/Sya%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and nm_bangsal like '%Ar-Raudah/Sya%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_arraudah_syrf=$qhp_arraudah_syrf+$hp_arraudah_syrf;

$hp_bersalin=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <'".$tgl1."' and kamar_inap.tgl_keluar='0000-00-00' and bangsal.nm_bangsal like '%Al-Khaliq%') 
or (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and nm_bangsal like '%Al-Khaliq%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and nm_bangsal like '%Al-Khaliq%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_bersalin=$qhp_bersalin+$hp_bersalin;

$hp_paru=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <'".$tgl1."' and kamar_inap.tgl_keluar='0000-00-00' and bangsal.nm_bangsal like '%Al-Hakim%') 
or (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and nm_bangsal like '%Al-Hakim%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and nm_bangsal like '%Al-Hakim%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_paru=$qhp_paru+$hp_paru;

if (isset($_GET['bayi']))
{
$hp_nicu=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <'".$tgl1."' and kamar_inap.tgl_keluar='0000-00-00' and bangsal.nm_bangsal like '%NICU%') 
or (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and nm_bangsal like '%NICU%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and nm_bangsal like '%NICU%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_nicu=$qhp_nicu+$hp_nicu;

$hp_bayi=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <'".$tgl1."' and kamar_inap.tgl_keluar='0000-00-00' and bangsal.nm_bangsal like '%Bayi%') 
or (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and nm_bangsal like '%Bayi%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and nm_bangsal like '%Bayi%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_bayi=$qhp_bayi+$hp_bayi;
}

/*select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <'2018-02-01' and kamar_inap.tgl_keluar='0000-00-00' and kamar_inap.kd_kamar not like '%BY/%') 
or (kamar_inap.tgl_masuk <='2018-02-01' and kamar_inap.tgl_keluar >'2018-02-01' and kamar_inap.kd_kamar not like '%BY/%') 
or (kamar_inap.tgl_masuk ='2018-02-01' and kamar_inap.kd_kamar not like '%BY/%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar
*/

$hp_total=$hp_anak+$hp_bedah+$hp_icu+$hp_assami+$hp_vip+$hp_rkpd+$hp_zaal+$hp_arraudah_mthtkl+$hp_arraudah_syrf+$hp_bersalin+$hp_paru; //+$hp_nicu;

	$tgl1 = date("Y-m-d", strtotime("+1 day", strtotime($tgl1)));
	$row=12;
}
?>
		<tr>
		<th align="center" style="vertical-align: middle;text-align: center;">&Sigma; BED</th>
		<th align="center" style="vertical-align: middle;text-align: center;">Anak<br>( <?php echo $r_anak;?> )</th>
		<th align="center" style="vertical-align: middle;text-align: center;">Bedah<br>( <?php echo $r_bedah;?> )</th>
		<th align="center" style="vertical-align: middle;text-align: center;">ICU/ICCU<br>( <?php echo $r_icu;?> )</th>
		<th align="center" style="vertical-align: middle;text-align: center;">As-Sami<br>( <?php echo $r_assami;?> )</th>
		<th align="center" style="vertical-align: middle;text-align: center;">VIP/SVIP Intan<br>( <?php echo $r_vip;?> )</th>
		<th align="center" style="vertical-align: middle;text-align: center;">RKPD<br>( <?php echo $r_rkpd;?> )</th>
		<th align="center" style="vertical-align: middle;text-align: center;">ZAAL<br>( <?php echo $r_zaal;?> )</th>		
		<th align="center" style="vertical-align: middle;text-align: center;">Ar-Raudah<br>MTHTKL<br>( <?php echo $r_arraudah_mthtkl;?> )</th>
		<th align="center" style="vertical-align: middle;text-align: center;">Ar-Raudah<br>Syaraf<br>( <?php echo $r_arraudah_syrf;?> )</th>		
		<th align="center" style="vertical-align: middle;text-align: center;">Bersalin<br>( <?php echo $r_bersalin;?> )</th>
		<th align="center" style="vertical-align: middle;text-align: center;">Paru<br>( <?php echo $r_paru;?> )</th>
		<?php $qtybed=$r_anak+$r_bedah+$r_icu+$r_assami+$r_vip+$r_rkpd+$r_zaal+$r_arraudah_mthtkl+$r_arraudah_syrf+$r_bersalin+$r_paru; ?>
		<th align="center" style="vertical-align: middle;text-align: center;">&Sigma; Dewasa<br/>( <?php echo $qtybed;?> )</th>		
<?php
if (isset($_GET['bayi']))
{
$row=15;
?>
		<th align="center" style="vertical-align: middle;text-align: center;">NICU<br>( <?php echo $r_nicu;?> )</th>
		<th align="center" style="vertical-align: middle;text-align: center;">Bayi<br>( <?php echo $r_bayi;?> )</th>
		<th align="center" style="vertical-align: middle;text-align: center;">&Sigma; Perinatologi<br/>( <?php $qtybedbayi=$r_nicu+$r_bayi; echo $qtybedbayi;?> )</th>		
<?php
}
?>
		</tr>
		<tr>
		<td align="center" style="vertical-align: middle;text-align: center;"><b>Periode (Hari)</b></td>
		<td align="center" style="vertical-align: middle;text-align: center;" colspan="<?php echo $row; ?>"><?php echo $periode; ?></td>
		</tr>			
		<tr>
		<td align="center" style="vertical-align: middle;text-align: center;"><b>&Sigma; H P</b></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_anak; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_bedah; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_icu; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_assami; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_vip; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_rkpd; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_zaal; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_arraudah_mthtkl; ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_arraudah_syrf; ?></td>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_bersalin; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_paru; ?></td>
<?php 
$qhp_total=$qhp_anak+$qhp_bedah+$qhp_icu+$qhp_assami+$qhp_vip+$qhp_rkpd+$qhp_zaal+$qhp_arraudah_mthtkl+$qhp_arraudah_syrf+$qhp_bersalin+$qhp_paru;
?>		
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-info"><?php echo $qhp_total; ?></td>
<?php
if (isset($_GET['bayi']))
{
?>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_nicu; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_bayi; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-info"><?php $qhp_totalbayi=$qhp_nicu+$qhp_bayi; echo $qhp_totalbayi; ?></td>		
<?php
}
?>		
		</tr>
<?php

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
where (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Ar-Razaq%' and bangsal.nm_bangsal not like '%zaal%' and bangsal2.nm_bangsal is null)
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Ar-Razaq%' and bangsal2.nm_bangsal not like '%Ar-Razaq%')
or (kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Ar-Razaq%' and bangsal2.nm_bangsal like '%zaal%')
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

?>		
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
//$out_total=$out_anak+$out_bedah+$out_icu+$out_assami+$out_vip+$out_rkpd+$out_zaal+$out_arraudah+$out_bersalin+$out_paru; //+$out_nicu;
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
?>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_nicu; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_bayi; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-info"><?php echo $out_totalbayi; ?></td>
<?php
}
?>			
		</tr>		
		
		<tr class="bg-success">
		<td align="center" style="vertical-align: middle;text-align: center;"><b>BOR</b></td>
<?php $bor_anak=($qhp_anak/($r_anak*$periode))*100; ?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_anak,2); ?></td>
		
<?php $bor_bedah=($qhp_bedah/($r_bedah*$periode))*100; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_bedah,2); ?></td>
		
<?php $bor_icu=($qhp_icu/($r_icu*$periode))*100; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_icu,2); ?></td>
		
<?php $bor_assami=($qhp_assami/($r_assami*$periode))*100; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_assami,2); ?></td>
		
<?php $bor_vip=($qhp_vip/($r_vip*$periode))*100; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_vip,2); ?></td>
		
<?php $bor_rkpd=($qhp_rkpd/($r_rkpd*$periode))*100; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_rkpd,2); ?></td>
		
<?php $bor_zaal=($qhp_zaal/($r_zaal*$periode))*100; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_zaal,2); ?></td>
		
<?php $bor_arraudah_mthtkl=($qhp_arraudah_mthtkl/($r_arraudah_mthtkl*$periode))*100; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_arraudah_mthtkl,2); ?></td>

<?php $bor_arraudah_syrf=($qhp_arraudah_syrf/($r_arraudah_syrf*$periode))*100; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_arraudah_syrf,2); ?></td>
		
<?php $bor_bersalin=($qhp_bersalin/($r_bersalin*$periode))*100; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_bersalin,2); ?></td>
		
<?php $bor_paru=($qhp_paru/($r_paru*$periode))*100; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_paru,2); ?></td>
		
<?php $bor_total=($qhp_total/($qtybed*$periode))*100; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-primary"><?php echo round($bor_total,2); ?></td>
<?php
if (isset($_GET['bayi']))
{

$bor_nicu=($qhp_nicu/($r_nicu*$periode))*100;		
$bor_bayi=($qhp_bayi/($r_bayi*$periode))*100;	
$bor_totalbayi=($qhp_totalbayi/($qtybedbayi*$periode))*100;	
?>

		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_nicu,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_bayi,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-primary"><?php echo round($bor_totalbayi,2); ?></td>
<?php
}
?>			
		</tr>
		<tr class="bg-success">
		<td align="center" style="vertical-align: middle;text-align: center;"><b>TOI</b></td>
<?php $toi_anak=(($r_anak*$periode)-$qhp_anak)/$out_anak; ?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_anak,2); ?></td>
		
<?php $toi_bedah=(($r_bedah*$periode)-$qhp_bedah)/$out_bedah; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_bedah,2); ?></td>
		
<?php $toi_icu=(($r_icu*$periode)-$qhp_icu)/$out_icu; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_icu,2); ?></td>
		
<?php $toi_assami=(($r_assami*$periode)-$qhp_assami)/$out_assami; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_assami,2); ?></td>
		
<?php $toi_vip=(($r_vip*$periode)-$qhp_vip)/$out_vip; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_vip,2); ?></td>
		
<?php $toi_rkpd=(($r_rkpd*$periode)-$qhp_rkpd)/$out_rkpd; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_rkpd,2); ?></td>
		
<?php $toi_zaal=(($r_zaal*$periode)-$qhp_zaal)/$out_zaal; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_zaal,2); ?></td>
		
<?php $toi_arraudah_mthtkl=(($r_arraudah_mthtkl*$periode)-$qhp_arraudah_mthtkl)/$out_arraudah_mthtkl; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_arraudah_mthtkl,2); ?></td>
		
<?php $toi_arraudah_syrf=(($r_arraudah_syrf*$periode)-$qhp_arraudah_syrf)/$out_arraudah_syrf; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_arraudah_syrf,2); ?></td>
		
<?php $toi_bersalin=(($r_bersalin*$periode)-$qhp_bersalin)/$out_bersalin; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_bersalin,2); ?></td>
		
<?php $toi_paru=(($r_paru*$periode)-$qhp_paru)/$out_paru; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_paru,2); ?></td>
		
<?php $toi_total=(($qtybed*$periode)-$qhp_total)/$out_total; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-primary"><?php echo round($toi_total,2); ?></td>
<?php
if (isset($_GET['bayi']))
{
$toi_nicu=(($r_nicu*$periode)-$qhp_nicu)/$out_nicu;
$toi_bayi=(($r_bayi*$periode)-$qhp_bayi)/$out_bayi;	
$toi_bayitotal=(($qtybedbayi*$periode)-$qhp_totalbayi)/$out_bayi;	
?>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_nicu,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_bayi,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-primary"><?php echo round($toi_bayitotal,2); ?></td>
<?php
}
?>			
		</tr>
		<tr class="bg-success">
		<td align="center" style="vertical-align: middle;text-align: center;"><b>BTO</b></td>
<?php $bto_anak=$out_anak/$r_anak; ?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_anak,2); ?></td>
		
<?php $bto_bedah=$out_bedah/$r_bedah; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_bedah,2); ?></td>
		
<?php $bto_icu=$out_icu/$r_icu; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_icu,2); ?></td>
		
<?php $bto_assami=$out_assami/$r_assami; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_assami,2); ?></td>
		
<?php $bto_vip=$out_vip/$r_vip; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_vip,2); ?></td>
		
<?php $bto_rkpd=$out_rkpd/$r_rkpd; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_rkpd,2); ?></td>
		
<?php $bto_zaal=$out_zaal/$r_zaal; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_zaal,2); ?></td>
		
<?php $bto_arraudah_mthtkl=$out_arraudah_mthtkl/$r_arraudah_mthtkl; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_arraudah_mthtkl,2); ?></td>

<?php $bto_arraudah_syrf=$out_arraudah_syrf/$r_arraudah_syrf; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_arraudah_syrf,2); ?></td>		
		
<?php $bto_bersalin=$out_bersalin/$r_bersalin; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_bersalin,2); ?></td>
		
<?php $bto_paru=$out_paru/$r_paru; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_paru,2); ?></td>
		
<?php $bto_total=$out_total/$qtybed; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-primary"><?php echo round($bto_total,2); ?></td>
<?php
if (isset($_GET['bayi']))
{
$bto_nicu=$out_nicu/$r_nicu;
$bto_bayi=$out_bayi/$r_bayi;	
$bto_bayitotal=$out_totalbayi/$qtybedbayi;
?>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_nicu,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_bayi,2); ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;" class="bg-primary"><?php echo round($bto_bayitotal,2); ?></td>		
<?php
}
?>			
		</tr>				
	</table>
	</div>
</div>

<div class="row">
	<div class="col-lg-4">
	<b>BOR (Bed Occupation Ratio = Angka penggunaan tempat tidur)</b><br/>
	Depkes RI (2005)<br/>
	- Prosentase pemakaian tempat tidur pada satuan waktu tertentu.<br/>
	- Nilai parameter BOR yang ideal = 60 – 85%.
	</div>
	<div class="col-lg-4">
	<b>TOI (Turn Over Interval = Tenggang perputaran)</b><br/>
	Depkes RI (2005)<br/>
	- Rata-rata hari dimana tempat tidur tidak ditempati dari telah diisi ke saat terisi berikutnya.<br/>
	- Nilai ideal = 1 – 3 hari.
	</div>	
	<div class="col-lg-4">
	<b>BTO (Bed Turn Over = Angka perputaran tempat tidur)</b><br/>
	Depkes RI (2005)<br/>
	- Frekuensi pemakaian tempat tidur pada satu periode tertentu.<br/>
	- Nilai ideal dalam 1 tahun untuk 1 tempat tidur dipakai 40 -50 kali.
	</div>		
</div>
<?php 
}
?>
        
<?php
include('bawah.php');
?>