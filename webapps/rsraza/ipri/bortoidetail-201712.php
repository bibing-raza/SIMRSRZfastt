<?php
include('inc/config.inc.php');
include('atas.php');

$r_anak=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%Anak%'"));
$r_bedah=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%Bedah%'"));
$r_icu=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%ICCU%'"));
$r_assami=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%As-Sami%'"));
$r_vip=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%VIP-%'"));
$r_rkpd=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%internist%' and  bangsal.nm_bangsal not like '%Zaal%'"));
$r_zaal=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%Zaal%'"));
$r_arraudah=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%Ar-Raudah%'"));
$r_bersalin=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%Obgyn%'"));
$r_paru=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%Paru%'"));
$r_nicu=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where bangsal.nm_bangsal like '%NICU%'"));

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
	<thead>
		<tr>
		<th align="center" style="vertical-align: middle;text-align: center;">Tanggal</th>
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
		<th align="center" style="vertical-align: middle;text-align: center;">NICU</th>
		<th align="center" style="vertical-align: middle;text-align: center;">&Sigma; HP</th>		
		</tr>
	</thead>
	<tbody>
<?php
$qhp_anak=0;
$qhp_bedah=0;
$qhp_icu=0;
$qhp_assami=0;
$qhp_vip=0;
$qhp_rkpd=0;
$qhp_zaal=0;
$qhp_arraudah=0;
$qhp_bersalin=0;
$qhp_paru=0;
$qhp_nicu=0;
$periode=0;
while(strtotime($tgl1) <= strtotime($tgl2) ) {
$periode++;	
?>
		<tr>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $tgl1; ?></td>
<?php
$hp_anak=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and bangsal.nm_bangsal like '%Anak%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and bangsal.nm_bangsal like '%Anak%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_anak=$qhp_anak+$hp_anak;
?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $hp_anak; ?></td>

<?php
$hp_bedah=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and bangsal.nm_bangsal like '%Bedah%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and bangsal.nm_bangsal like '%Bedah%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_bedah=$qhp_bedah+$hp_bedah;
?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $hp_bedah; ?></td>

<?php
$hp_icu=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and bangsal.nm_bangsal like '%ICCU%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and bangsal.nm_bangsal like '%ICCU%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_icu=$qhp_icu+$hp_icu;
?>	
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $hp_icu; ?></td>

<?php
$hp_assami=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and bangsal.nm_bangsal like '%As-Sami%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and bangsal.nm_bangsal like '%As-Sami%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_assami=$qhp_assami+$hp_assami;
?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $hp_assami; ?></td>
		
<?php
$hp_vip=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and bangsal.nm_bangsal like '%VIP-%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and bangsal.nm_bangsal like '%VIP-%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_vip=$qhp_vip+$hp_vip;
?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $hp_vip; ?></td>
		
		
<?php
$hp_rkpd=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and nm_bangsal like '%internist%' and  bangsal.nm_bangsal not like '%Zaal%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and nm_bangsal like '%internist%' and  bangsal.nm_bangsal not like '%Zaal%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_rkpd=$qhp_rkpd+$hp_rkpd;
?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $hp_rkpd; ?></td>	

<?php
$hp_zaal=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and nm_bangsal like '%zaal%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and nm_bangsal like '%zaal%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_zaal=$qhp_zaal+$hp_zaal;
?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $hp_zaal; ?></td>

<?php
$hp_arraudah=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and nm_bangsal like '%Ar-Raudah%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and nm_bangsal like '%Ar-Raudah%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_arraudah=$qhp_arraudah+$hp_arraudah;
?>	
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $hp_arraudah; ?></td>

<?php
$hp_bersalin=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and nm_bangsal like '%Obgyn%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and nm_bangsal like '%Obgyn%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_bersalin=$qhp_bersalin+$hp_bersalin;
?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $hp_bersalin; ?></td>	

<?php
$hp_paru=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and nm_bangsal like '%Paru%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and nm_bangsal like '%Paru%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_paru=$qhp_paru+$hp_paru;
?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $hp_paru; ?></td>

<?php
$hp_nicu=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <='".$tgl1."' and kamar_inap.tgl_keluar >'".$tgl1."' and nm_bangsal like '%nicu%') 
or (kamar_inap.tgl_masuk ='".$tgl1."' and nm_bangsal like '%nicu%')
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));
$qhp_nicu=$qhp_nicu+$hp_nicu;
?>	
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $hp_nicu; ?></td>
<?php 
$hp_total=$hp_anak+$hp_bedah+$hp_icu+$hp_assami+$hp_vip+$hp_rkpd+$hp_zaal+$hp_arraudah+$hp_bersalin+$hp_paru+$hp_nicu;
?>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $hp_total; ?></td>
		</tr>
<?php
	$tgl1 = date("Y-m-d", strtotime("+1 day", strtotime($tgl1)));
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
		<th align="center" style="vertical-align: middle;text-align: center;">Ar-Raudah<br>( <?php echo $r_arraudah;?> )</th>
		<th align="center" style="vertical-align: middle;text-align: center;">Bersalin<br>( <?php echo $r_bersalin;?> )</th>
		<th align="center" style="vertical-align: middle;text-align: center;">Paru<br>( <?php echo $r_paru;?> )</th>
		<th align="center" style="vertical-align: middle;text-align: center;">NICU<br>( <?php echo $r_nicu;?> )</th>
		<?php $qtybed=$r_anak+$r_bedah+$r_icu+$r_assami+$r_vip+$r_rkpd+$r_zaal+$r_arraudah+$r_bersalin+$r_paru+$r_nicu; ?>
		<th align="center" style="vertical-align: middle;text-align: center;">&Sigma; BED<br/>( <?php echo $qtybed;?> )</th>
		</tr>
		<tr>
		<td align="center" style="vertical-align: middle;text-align: center;"><b>Periode (Hari)</b></td>
		<td align="center" style="vertical-align: middle;text-align: center;" colspan="12"><?php echo $periode; ?></td>
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
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_arraudah; ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_bersalin; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_paru; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_nicu; ?></td>
<?php 
$qhp_total=$qhp_anak+$qhp_bedah+$qhp_icu+$qhp_assami+$qhp_vip+$qhp_rkpd+$qhp_zaal+$qhp_arraudah+$qhp_bersalin+$qhp_paru+$qhp_nicu;
?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $qhp_total; ?></td>
		</tr>
<?php
$out_anak=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Anak%'
group by kamar_inap.no_rawat
order by kamar_inap.tgl_keluar desc"));

$out_bedah=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Bedah%'
group by kamar_inap.no_rawat
order by kamar_inap.tgl_keluar desc"));

$out_icu=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%ICCU%'
group by kamar_inap.no_rawat
order by kamar_inap.tgl_keluar desc"));

$out_assami=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%As-Sami%'
group by kamar_inap.no_rawat
order by kamar_inap.tgl_keluar desc"));

$out_vip=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%VIP-%'
group by kamar_inap.no_rawat
order by kamar_inap.tgl_keluar desc"));

$out_rkpd=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%internist%' and bangsal.nm_bangsal not like '%zaal%'
group by kamar_inap.no_rawat
order by kamar_inap.tgl_keluar desc"));

$out_zaal=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Zaal%'
group by kamar_inap.no_rawat
order by kamar_inap.tgl_keluar desc"));

$out_arraudah=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Ar-Raudah%'
group by kamar_inap.no_rawat
order by kamar_inap.tgl_keluar desc"));

$out_bersalin=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Obgyn%'
group by kamar_inap.no_rawat
order by kamar_inap.tgl_keluar desc"));

$out_paru=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%Paru%'
group by kamar_inap.no_rawat
order by kamar_inap.tgl_keluar desc"));

$out_nicu=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.stts_pulang
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where kamar_inap.tgl_keluar between '".$_GET['tgl_awal']."' and '".$_GET['tgl_akhir']."' and bangsal.nm_bangsal like '%NICU%'
group by kamar_inap.no_rawat
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
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_arraudah; ?></td>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_bersalin; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_paru; ?></td>
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_nicu; ?></td>
<?php 
$out_total=$out_anak+$out_bedah+$out_icu+$out_assami+$out_vip+$out_rkpd+$out_zaal+$out_arraudah+$out_bersalin+$out_paru+$out_nicu;
?>		
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo $out_total; ?></td>
		</tr>		
		
		<tr>
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
		
<?php $bor_arraudah=($qhp_arraudah/($r_arraudah*$periode))*100; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_arraudah,2); ?></td>
		
<?php $bor_bersalin=($qhp_bersalin/($r_bersalin*$periode))*100; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_bersalin,2); ?></td>
		
<?php $bor_paru=($qhp_paru/($r_paru*$periode))*100; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_paru,2); ?></td>
		
<?php $bor_nicu=($qhp_nicu/($r_nicu*$periode))*100; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_nicu,2); ?></td>
		
<?php $bor_total=($qhp_total/($qtybed*$periode))*100; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bor_total,2); ?></td>
		</tr>
		<tr>
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
		
<?php $toi_arraudah=(($r_arraudah*$periode)-$qhp_arraudah)/$out_arraudah; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_arraudah,2); ?></td>
		
<?php $toi_bersalin=(($r_bersalin*$periode)-$qhp_bersalin)/$out_bersalin; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_bersalin,2); ?></td>
		
<?php $toi_paru=(($r_paru*$periode)-$qhp_paru)/$out_paru; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_paru,2); ?></td>
		
<?php $toi_nicu=(($r_nicu*$periode)-$qhp_nicu)/$out_nicu; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_nicu,2); ?></td>
		
<?php $toi_total=(($qtybed*$periode)-$qhp_total)/$out_total; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($toi_total,2); ?></td>
		</tr>
		<tr>
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
		
<?php $bto_arraudah=$out_arraudah/$r_arraudah; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_arraudah,2); ?></td>
		
<?php $bto_bersalin=$out_bersalin/$r_bersalin; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_bersalin,2); ?></td>
		
<?php $bto_paru=$out_paru/$r_paru; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_paru,2); ?></td>
		
<?php $bto_nicu=$out_nicu/$r_nicu; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_nicu,2); ?></td>
		
<?php $bto_total=$out_total/$qtybed; ?>			
		<td align="center" style="vertical-align: middle;text-align: center;"><?php echo round($bto_total,2); ?></td>
		</tr>			
	</tbody>	
	</table>
	</div>
</div>
<?php 
}
?>
        
<?php
include('bawah.php');
?>