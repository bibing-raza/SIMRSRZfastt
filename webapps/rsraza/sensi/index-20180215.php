<?php
include('inc/config.inc.php');
include('atas.php');
?>

	<form action="sensusinap.php" method="GET" name="hitungipri">
<div class="row">
	<div class="col-lg-3">
		<div class="form-group">
		<label for="sel1">Pilih Ruangan:</label>
			<select class="form-control" id="sel1" name="ruangan">
			<option value="Anak|Anak">Anak</option>
			<option value="Bedah|Bedah">Bedah</option>
			<option value="ICCU|ICU">ICU</option>
			<option value="As-Sami|As-Sami">As-Sami</option>
			<option value="VIP-|VIP/SVIP">VIP/SVIP</option>
			<option value="Internist|RKPD">RKPD</option>
			<option value="ZAAL|ZAAL">ZAAL</option>
			<option value="Syaraf|Ar-Raudah Syaraf">Arraudah Syaraf</option>
			<option value="Mata-THT-KK|Ar-Raudah Mata-THT-KK">Arraudah Mata-THT-KK</option>
			<option value="Obgyn|Bersalin">Bersalin</option>
			<option value="Paru|Paru">Paru</option>
			<option value="NICU|Perinatologi NICU">Perinatologi NICU</option>
			<option value="Bayi|NICU">Perinatologi Bayi</option>
			</select>
		</div>	
	</div>	
	
	<div class="col-lg-3">
		<div class="form-group">
		<label for="tgla">Tanggal</label>
				<div class="input-group date " data-date="" data-date-format="yyyy-mm-dd">
	                <input class="form-control" type="text" name="tgl_awal" readonly="readonly" value="<?php echo date('Y-m-d'); ?>">
	                <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
				</div>						
		</div>		
	</div>
</div>	
<div class="row">
	<div class="col-lg-6">	
		<div class="form-group">
		<input type="submit" class="btn btn-primary" value="Submit">
		</div>
	</div>
</div>	
	</form>			
	

        
<?php
include('bawah.php');
?>