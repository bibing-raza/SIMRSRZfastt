<?php
include('inc/config.inc.php');

$ruangx = explode("|", $_GET['ruangan']);
if ($ruangx[0]=="Internist")
{
$qnmruangan="bangsal.nm_bangsal like '%".$ruangx[0]."%' and  bangsal.nm_bangsal not like '%Zaal%'";
}
else
{
$qnmruangan="bangsal.nm_bangsal like '%".$ruangx[0]."%'";
}

$bed=mysql_num_rows(mysql_query("select kamar.kd_kamar,bangsal.nm_bangsal from kamar left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal where ".$qnmruangan." and kamar.statusdata='1'"));

$hp_ruang=mysql_num_rows(mysql_query("select kamar_inap.no_rawat,kamar_inap.kd_kamar,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar 
from kamar_inap
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
where (kamar_inap.tgl_masuk <'".$_GET['tgl_awal']."' and kamar_inap.tgl_keluar='0000-00-00' and ".$qnmruangan." )
or (kamar_inap.tgl_masuk <='".$_GET['tgl_awal']."' and kamar_inap.tgl_keluar >'".$_GET['tgl_awal']."' and ".$qnmruangan.") 
or (kamar_inap.tgl_masuk ='".$_GET['tgl_awal']."' and ".$qnmruangan.")
group by kamar_inap.no_rawat
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar"));

$qsensus1=mysql_query("select kamar_inap.no_rawat,pasien.nm_pasien,reg_periksa.no_rkm_medis,bangsal.nm_bangsal,kamar_inap.kd_kamar,bangsal2.nm_bangsal nm_bangsal2,kamar_inap2.kd_kamar kd_kamar2,
penjab.png_jawab,rujuk_masuk.perujuk,kamar_inap.diagnosa_awal,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.lama,kamar_inap.stts_pulang,kamar_inap.diagnosa_akhir
from kamar_inap
left join reg_periksa on kamar_inap.no_rawat=reg_periksa.no_rawat
left join kamar_inap kamar_inap2 on kamar_inap.tgl_masuk = kamar_inap2.tgl_keluar and kamar_inap.jam_masuk = kamar_inap2.jam_keluar and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
left join penjab on reg_periksa.kd_pj=penjab.kd_pj
left join rujuk_masuk on kamar_inap.no_rawat=rujuk_masuk.no_rawat
where kamar_inap.tgl_masuk ='".$_GET['tgl_awal']."' and  ".$qnmruangan."
order by kamar_inap.tgl_masuk,kamar_inap.tgl_keluar desc");

$qsensus2=mysql_query("select kamar_inap.no_rawat,pasien.nm_pasien,reg_periksa.no_rkm_medis,bangsal.nm_bangsal,kamar_inap.kd_kamar,bangsal2.nm_bangsal nm_bangsal2,kamar_inap2.kd_kamar kd_kamar2,
penjab.png_jawab,rujuk_masuk.perujuk,kamar_inap.diagnosa_awal,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar,kamar_inap.lama,kamar_inap.stts_pulang,kamar_inap.diagnosa_akhir
from kamar_inap
left join reg_periksa on kamar_inap.no_rawat=reg_periksa.no_rawat
left join kamar_inap kamar_inap2 on kamar_inap.tgl_keluar=kamar_inap2.tgl_masuk and kamar_inap.jam_keluar=kamar_inap2.jam_masuk and kamar_inap.no_rawat=kamar_inap2.no_rawat
left join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis
left join kamar on kamar_inap.kd_kamar=kamar.kd_kamar
left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar
left join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal
left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal
left join penjab on reg_periksa.kd_pj=penjab.kd_pj
left join rujuk_masuk on kamar_inap.no_rawat=rujuk_masuk.no_rawat
where kamar_inap.tgl_keluar ='".$_GET['tgl_awal']."' and  ".$qnmruangan."
order by kamar_inap.tgl_masuk, kamar_inap.tgl_keluar desc");

?>
<html>
<head>
	<style>
    body {
        font-family: verdana;
		font-size: 11px;
    }

	table {
		font-family: verdana;
		font-size: 10px;
		border-collapse: collapse;
		width: 100%;
	}

	table,td {
		border: 1px solid black;
		padding : 2px;
	}
	</style>	
</head>
<body>
<p align="center"><b>SENSUS HARIAN PASIEN RAWAT INAP</b></p>
<?php
$hari=date('l', strtotime($_GET['tgl_awal'])); 
if ($hari=="Sunday") {$hari="Minggu";};
if ($hari=="Monday") {$hari="Senin";};
if ($hari=="Tuesday") {$hari="Selasa";};
if ($hari=="Wednesday") {$hari="Rabu";};
if ($hari=="Thursday") {$hari="Kamis";};
if ($hari=="Friday") {$hari="Jumat";};
if ($hari=="Saturday") {$hari="Sabtu";};
?>
<p><b>Tanggal:</b> <?php echo $hari.", ".date('d-m-Y', strtotime($_GET['tgl_awal']));?> (Sampai Jam 24.00) &nbsp; <b>Ruang:</b> <?php echo $ruangx[1];?> &nbsp; <b>Jumlah TT:</b> <?php echo $bed;?></p>
<p><b>A. Pasien Masuk Melalui SO dan Pasien Pindahan</b></p>
<table>
    <tr>
        <td align="center"><b>NO</b></td>
        <td align="center"><b>NAMA PASIEN</b></td>
        <td align="center"><b>NO. RM</b></td>
        <td align="center"><b>RUANG PERAWATAN</b></td>
        <td align="center"><b>JENIS BAYAR</b></td>
        <td align="center"><b>RUJUKAN</b></td>
        <td align="center"><b>DIAGNOSA AWAL</b></td>
        <td align="center"><b>KETERANGAN</b></td>		
    </tr>
<?php 
$i=0;
while ($qsensus=mysql_fetch_array($qsensus1))
{	
	if (strpos($qsensus['nm_bangsal2'], $ruangx[0]) === false)
	{
	$i++;
    echo"<tr>";	
	echo"<td align='center'>".$i."</td>";
    echo"<td>".$qsensus['nm_pasien']."</td>";
    echo"<td>".$qsensus['no_rkm_medis']."</td>";
    echo"<td>".$qsensus['kd_kamar']."</td>";
    echo"<td>".$qsensus['png_jawab']."</td>";
    echo"<td>".$qsensus['perujuk']."</td>";
    echo"<td>".$qsensus['diagnosa_awal']."</td>";
    echo"<td>".$qsensus['kd_kamar2']."</td>";		
    echo"</tr>";
	}
	
	if ($ruangx[0]=="Internist")
	{
		if (strpos($qsensus['nm_bangsal2'], "Zaal")!==false)
		{
		$i++;			
		echo"<tr>";	
		echo"<td align='center'>".$i."</td>";
		echo"<td>".$qsensus['nm_pasien']."</td>";
		echo"<td>".$qsensus['no_rkm_medis']."</td>";
		echo"<td>".$qsensus['kd_kamar']."</td>";
		echo"<td>".$qsensus['png_jawab']."</td>";
		echo"<td>".$qsensus['perujuk']."</td>";
		echo"<td>".$qsensus['diagnosa_awal']."</td>";
		echo"<td>".$qsensus['kd_kamar2']."</td>";		
		echo"</tr>";			
		}
	}
}	
?>
</table>

<?php if ($i==0) {echo "Tidak ada pasien masuk.";} ?>
<p><b>B. Pasien Keluar</b></p>
<table>
    <tr>
        <td align="center"><b>NO</b></td>
        <td align="center"><b>NAMA PASIEN</b></td>
        <td align="center"><b>NO. RM</b></td>
        <td align="center"><b>TGL.MASUK</b></td>
        <td align="center"><b>RUANG PERAWATAN</b></td>		
        <td align="center"><b>STATUS PULANG</b></td>	
        <td align="center"><b>LAMA RAWAT</b></td>			
        <td align="center"><b>JENIS BAYAR</b></td>
        <td align="center"><b>DIAGNOSA AKHIR</b></td>		
    </tr>
<?php 
$x=0;
$pxpindah="";
$pxmati47="";
$pxmati48="";
$pxsembuh="";
$pxaps="";
$pxdirujuk="";
$pxkabur="";
$pxinout="";
while ($qsensus=mysql_fetch_array($qsensus2))
{	
	if ((strpos($qsensus['nm_bangsal2'], $ruangx[0]) === false))
	{
	$x++;		
    echo"<tr>";	
	echo"<td align='center'>".$x."</td>";
    echo"<td>".$qsensus['nm_pasien']."</td>";
    echo"<td>".$qsensus['no_rkm_medis']."</td>";
    echo"<td>".$qsensus['tgl_masuk']."</td>";
    echo"<td>".$qsensus['kd_kamar']."</td>";	
	$kmr_tujuan="";
	if ($qsensus['stts_pulang']=="Pindah Kamar") {$pxpindah++; $kmr_tujuan="(".$qsensus['kd_kamar2'].")";};
	if ($qsensus['stts_pulang']=="Meninggal < 48 Jam") {$pxmati47++;};
	if ($qsensus['stts_pulang']=="Meninggal >= 48 Jam") {$pxmati48++;};
	if ($qsensus['stts_pulang']=="Sembuh/BLPL") {$pxsembuh++;};
	if ($qsensus['stts_pulang']=="APS") {$pxaps++;};
	if ($qsensus['stts_pulang']=="Dirujuk") {$pxdirujuk++;};
	if ($qsensus['stts_pulang']=="Kabur") {$pxkabur++;};
	if ($qsensus['tgl_masuk']==$qsensus['tgl_keluar']) {$pxinout++;};
    echo"<td>".$qsensus['stts_pulang']."".$kmr_tujuan."</td>";		
    echo"<td align='center'>".$qsensus['lama']."</td>";		
    echo"<td>".$qsensus['png_jawab']."</td>";
    echo"<td>".$qsensus['diagnosa_akhir']."</td>";		
    echo"</tr>";
	}
	if ($ruangx[0]=="Internist")
	{
		if (strpos($qsensus['nm_bangsal2'], "Zaal")!==false)
		{
		$x++;		
		echo"<tr>";	
		echo"<td align='center'>".$x."</td>";
		echo"<td>".$qsensus['nm_pasien']."</td>";
		echo"<td>".$qsensus['no_rkm_medis']."</td>";
		echo"<td>".$qsensus['tgl_masuk']."</td>";
		echo"<td>".$qsensus['kd_kamar']."</td>";	
		$kmr_tujuan="";
		if ($qsensus['stts_pulang']=="Pindah Kamar") {$pxpindah++; $kmr_tujuan="(".$qsensus['kd_kamar2'].")";};
		if ($qsensus['tgl_masuk']==$qsensus['tgl_keluar']) {$pxinout++;};
		echo"<td>".$qsensus['stts_pulang']."".$kmr_tujuan."</td>";		
		echo"<td align='center'>".$qsensus['lama']."</td>";		
		echo"<td >".$qsensus['png_jawab']."</td>";
		echo"<td>".$qsensus['diagnosa_akhir']."</td>";		
		echo"</tr>";			
		}
	}	
}
$pxhidup=$pxsembuh+$pxaps+$pxdirujuk;
?>
</table>
<?php 
if ($x==0) {echo "Tidak ada pasien keluar.";} 


$px_masih=$hp_ruang-$pxinout;
$px_ruang=$px_masih+$x;
$px_awal=$px_ruang-$i;
?>
<hr/>
<table>
	<tr>
		<td><b>Pasien Awal</b></td><td align="right"><?php echo $px_awal; ?>  Org</td><td><b>Pasien Keluar Sembuh</b></td><td align="right"><?php echo $pxsembuh; ?> Org</td><td><b>Pasien dipindahkan</b></td><td align="right"><?php echo $pxpindah; ?> Org</td><td><b>&Sigma; PASIEN MASIH DIRAWAT</b></td><td align="right"><?php echo $px_masih; ?> Org</td>
	</tr>
	<tr>	
		<td><b>Pasien Masuk</b></td><td align="right"><?php echo $i; ?> Org</td><td><b>Pasien Keluar APS</b></td><td align="right"><?php echo $pxaps; ?> Org</td><td><b>Pasien Meninggal < 48 Jam</b></td><td align="right"><?php echo $pxmati47; ?> Org</td><td><b>Pasien Masuk & Keluar Dihari yg Sama </b></td><td align="right"><?php echo $pxinout; ?> Org</td>
	</tr>	
	<tr>	
		<td><b>&Sigma; PASIEN DIRAWAT</b></td><td align="right"><?php echo $px_ruang;?> Org</td><td><b>Pasien Keluar Dirujuk</b></td><td align="right"><?php echo $pxdirujuk; ?> Org</td><td><b>Pasien Meninggal >= 48 Jam</b></td><td align="right"><?php echo $pxmati48; ?> Org</td><td><b>&Sigma; HARI PERAWATAN</b></td><td align="right"><?php echo $hp_ruang; ?></td>		
	</tr>
	<tr>	
		<td></td><td></td><td><b>&Sigma; PASIEN KELUAR HIDUP</b></td><td align="right"><?php echo $pxhidup; ?> Org</td><td><b>Pasien Keluar Kabur</b></td><td align="right"><?php echo $pxkabur; ?> Org</td><td></td><td></td>		
	</tr>
	<tr>	
		<td><b></b></td><td></td><td></td><td></td><td><b>&Sigma; PASIEN KELUAR</b></td><td align="right"><?php echo $x; ?> Org</td><td></td><td></td>		
	</tr>	

</table>
</body>
</html>