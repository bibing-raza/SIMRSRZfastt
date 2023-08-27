<?php
include('inc/config.inc.php');
include('atas.php');
?>
<div class="row">

	<div class="col-lg-12">
	<h4>Menghitung Indikator Pelayanan Rawat Inap</h4><hr>
	</div>
</div>	

	<form action="ipri.php" method="GET" name="hitungipri">
<div class="row">	
	<div class="col-lg-3">
		<div class="form-group">
		<label for="tgla">Periode dari Tanggal</label>
				<div class="input-group date " data-date="" data-date-format="yyyy-mm-dd">
	                <input class="form-control" type="text" name="tgl_awal" readonly="readonly" value="<?php echo date('Y-m-01'); ?>">
	                <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
				</div>						
		</div>		
	</div>
	<div class="col-lg-3">
		<div class="form-group">
		<label for="tglb">s/d Tanggal</label>
				<div class="input-group date " data-date="" data-date-format="yyyy-mm-dd">
	                <input class="form-control" type="text" name="tgl_akhir" readonly="readonly" value="<?php echo date('Y-m-d'); ?>">
	                <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
				</div>			
		</div>	
	</div>
</div>	
<div class="row">
	<div class="col-lg-6">
		<div class="checkbox">
		<label class="checkbox-inline"><input type="checkbox" name="bor" value="bor">BOR & TOI & BTO</label>
		<label class="checkbox-inline"><input type="checkbox" name="los" value="los">LOS & AVLOS</label>
		<label class="checkbox-inline"><input type="checkbox" name="gdr" value="gdr">GDR & NDR</label>
		</div>	
		<div class="checkbox">
		<label class="checkbox-inline"><input type="checkbox" name="bayi" value="bayi">Bayi</label>
		</div>		
		<div class="form-group">
		<input type="submit" class="btn btn-primary" value="Submit">
		</div>
	</div>
</div>	
	</form>			
	

        
<?php
include('bawah.php');
?>