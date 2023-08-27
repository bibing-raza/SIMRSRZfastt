<div id="post">
    <div class="entry">   
	<form name="frm_aturadmin" onsubmit="return validasiIsi();" method="post" action="" enctype=multipart/form-data>
        <?php
                echo "";
                $tahunawal      =isset($_GET['tahunawal'])?$_GET['tahunawal']:NULL;
                $bulanawal      =isset($_GET['bulanawal'])?$_GET['bulanawal']:NULL;
                $tanggalawal    =isset($_GET['tanggalawal'])?$_GET['tanggalawal']:NULL;
                $tahunakhir     =isset($_GET['tahunakhir'])?$_GET['tahunakhir']:NULL;
                $bulanakhir     =isset($_GET['bulanakhir'])?$_GET['bulanakhir']:NULL;
                $tanggalakhir   =isset($_GET['tanggalakhir'])?$_GET['tanggalakhir']:NULL;     
                $codernik       =isset($_GET['codernik'])?$_GET['codernik']:NULL;
                echo "<input type=hidden name=codernik  value=$codernik>";           
        ?>
    <div style="width: 100%; height: 90%; overflow: auto;">
    <?php
        $BtnCari=isset($_POST['BtnCari'])?$_POST['BtnCari']:NULL;
        if (isset($BtnCari)) {      
                $tahunawal      =trim($_POST['tahunawal']);
                $bulanawal      =trim($_POST['bulanawal']);
                $tanggalawal    =trim($_POST['tanggalawal']);
                $tahunakhir     =trim($_POST['tahunakhir']);
                $bulanakhir     =trim($_POST['bulanakhir']);
                $tanggalakhir   =trim($_POST['tanggalakhir']);
                $codernik       =trim($_POST['codernik']);
        }
if(empty($tahunawal)){
            $tahunawal=date('Y');
        }
        if(empty($bulanawal)){
            $bulanawal=date('m');
        }
        if(empty($tanggalawal)){
            $tanggalawal=date('d');
        }
        if(empty($tahunakhir)){
            $tahunakhir=date('Y');
        }
        if(empty($bulanakhir)){
            $bulanakhir=date('m');
        }
        if(empty($tanggalakhir)){
            $tanggalakhir=date('d');
        }        
        $_sql = "SELECT bs.no_sep, bs.no_rawat, bs.nomr, bs.nama_pasien, CONCAT(bs.tglsep,' 00:00:00') tglsep, bs.tglrujukan, bs.no_rujukan, bs.kdppkrujukan, bs.nmppkrujukan, bs.kdppkpelayanan, bs.nmppkpelayanan, bs.jnspelayanan,
				 IF (bs.jnspelayanan = '1','1. Rawat Inap','2. Rawat Jalan') AS jenispelayanan, bs.catatan, bs.diagawal, bs.nmdiagnosaawal, bs.kdpolitujuan, bs.nmpolitujuan, bs.klsrawat,
				 IF (bs.klsrawat = '1','1. Kelas 1', IF (bs.klsrawat = '2','2. Kelas 2','3. Kelas 3')) AS kelas, IF (bs.lakalantas = '1','1. Kasus Kecelakaan','2. Bukan Kasus Kecelakaan') AS lakalantas,
				 bs.user, bs.tanggal_lahir, bs.peserta, bs.jkel, bs.no_kartu, IF(bs.jnspelayanan='1',bs.tglpulang,CONCAT(bs.tglsep,' 00:00:00')) AS tglpulang
				 FROM bridging_sep bs WHERE bs.tglsep BETWEEN '".$tahunawal."-".$bulanawal."-".$tanggalawal." 00:00:00' AND '".$tahunakhir."-".$bulanakhir."-".$tanggalakhir." 23:59:59' ORDER BY bs.tglsep";
        $hasil=bukaquery($_sql);
        $jumlah=mysql_num_rows($hasil);
        if(mysql_num_rows($hasil)!=0) {
            echo "<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>
                    <tr class='head2'>
                        <td width='10%'><div align='center'>No.Rawat</div></td>
                        <td width='10%'><div align='center'>No.SEP</div></td>
                        <td width='10%'><div align='center'>Tanggal SEP</div></td>
                        <td width='9%'><div align='center'>No.Kartu</div></td>
                        <td width='7%'><div align='center'>No.RM</div></td>
                        <td width='18%'><div align='center'>Nama Pasien</div></td>
                        <td width='7%'><div align='center'>Tgl.Lahir</div></td>
                        <td width='7%'><div align='center'>J.Kel</div></td>
                        <td width='9%'><div align='center'>Peserta</div></td>
                        <td width='13%'><div align='center'>Status Data</div></td>
                    </tr>";
                    while($baris = mysql_fetch_array($hasil)) {
                        $status="";
                        if(getOne("select count(no_sep) from inacbg_data_terkirim where no_sep='".$baris["no_sep"]."'")>0){
                            $status="Terkirim INACBG";
                        }
                        $gender = "";
                        if($baris["jkel"]=="L"){
                            $gender="1";
                        }else{
                            $gender="2";
                        }

                        $prosedur="";
                        $a=1;
                        $hasilprosedur=bukaquery("select kode from prosedur_pasien where no_rawat='".$baris["no_rawat"]."' order by prioritas asc");
                        while($barisprosedur = mysql_fetch_array($hasilprosedur)) {
                            if($a==1){
                                $prosedur=$barisprosedur["kode"];
                            }else{
                                $prosedur=$prosedur."#".$barisprosedur["kode"];
                            }                
                            $a++;
                        }            

                        $penyakit="";
                        $a=1;
                        $hasilpenyakit=bukaquery("select kd_penyakit from diagnosa_pasien where no_rawat='".$baris["no_rawat"]."' order by prioritas asc");
                        while($barispenyakit = mysql_fetch_array($hasilpenyakit)) {
                            if($a==1){
                                $penyakit=$barispenyakit["kd_penyakit"];
                            }else{
                                $penyakit=$penyakit."#".$barispenyakit["kd_penyakit"];
                            }                
                            $a++;
                        }                 
                        
						$discharge_status="5";
						if(getOne("select count(no_rawat) from kamar_inap where stts_pulang='Sembuh/BLPL' and no_rawat='".$baris["no_rawat"]."'")>0){
  							$discharge_status="1";
						}else if(getOne("select count(no_rawat) from kamar_inap where stts_pulang='Dirujuk' and no_rawat='".$baris["no_rawat"]."'")>0){
  							$discharge_status="2";
						}else if(getOne("select count(no_rawat) from kamar_inap where stts_pulang='APS' and no_rawat='".$baris["no_rawat"]."'")>0){
  							$discharge_status="3";
						}else if(getOne("select count(no_rawat) from kamar_inap where stts_pulang='Meninggal >= 48 Jam' and no_rawat='".$baris["no_rawat"]."'")>0){
  							$discharge_status="4";
						}else if(getOne("select count(no_rawat) from kamar_inap where stts_pulang='Meninggal < 48 Jam' and no_rawat='".$baris["no_rawat"]."'")>0){
  							$discharge_status="4";
						}else if(getOne("select count(no_rawat) from kamar_inap where stts_pulang='Kabur' and no_rawat='".$baris["no_rawat"]."'")>0){
  							$discharge_status="5";
						}else{
  							$discharge_status="1";
						}
						
                        echo "<tr class='isi' title='".$baris["no_rawat"].", ".$baris["no_sep"].", ".$baris["tglsep"].", ".$baris["no_kartu"].", ".$baris["nomr"].", ".$baris["nama_pasien"]."'>
                                <td valign='top'>".$baris["no_rawat"]."</td>
                                <td valign='top'>".$baris["no_sep"]."</td>
                                <td valign='top' align='center'>".$baris["tglsep"]."</td>
                                <td valign='top'>".$baris["no_kartu"]."</td>
                                <td valign='top'>".$baris["nomr"]."</td>
                                <td valign='top'>".$baris["nama_pasien"]."</td>
                                <td valign='top' align='center'>".$baris["tanggal_lahir"]."</td>
                                <td valign='top' align='center'>".$baris["jkel"]."</td>
                                <td valign='top' align='center'>".$baris["peserta"]."</td>
                                <td valign='top' align='center'>".$status."</td>
                             </tr>";
                        $nm_dokter    = getOne("SELECT d.nm_dokter FROM reg_periksa rp INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter WHERE rp.no_rawat = '".$baris["no_rawat"]."'");
                        $nm_dokter2   = getOne("SELECT d.nm_dokter FROM dpjp_ranap dr INNER JOIN dokter d ON dr.kd_dokter = d.kd_dokter WHERE dr.no_rawat = '".$baris["no_rawat"]."'");
                        if(!empty($nm_dokter2)){
                            $nm_dokter=$nm_dokter2;
                        }
                        BuatKlaimBaru($baris["no_kartu"],$baris["no_sep"],$baris["nomr"],$baris["nama_pasien"],$baris["tanggal_lahir"]." 00:00:00", $gender);
                        EditUlangKlaim($baris["no_sep"]);
                        UpdateDataKlaim($baris["no_sep"],$baris["no_kartu"],$baris["tglsep"],$baris["tglpulang"],$baris["jnspelayanan"],$baris["klsrawat"],"","","","","","","","","",
										getOne("select berat_badan_benar from pasien_bayi where no_rkm_medis='".$baris["nomr"]."'"),$discharge_status,$penyakit,$prosedur, 
										getOne("select biaya_reg from reg_periksa where no_rawat='".$baris["no_rawat"]."'"), $nm_dokter,getKelasRS(),"","","#",$codernik,$baris["no_rawat"]);

                    }
            echo "</table>";           
        }else{
            echo "<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>
                    <tr class='head2'>
                        <td width='10%'><div align='center'>No.Rawat</div></td>
                        <td width='10%'><div align='center'>No.SEP</div></td>
                        <td width='10%'><div align='center'>Tanggal SEP</div></td>
                        <td width='9%'><div align='center'>No.Kartu</div></td>
                        <td width='7%'><div align='center'>No.RM</div></td>
                        <td width='18%'><div align='center'>Nama Pasien</div></td>
                        <td width='7%'><div align='center'>Tgl.Lahir</div></td>
                        <td width='7%'><div align='center'>J.Kel</div></td>
                        <td width='9%'><div align='center'>Peserta</div></td>
                        <td width='13%'><div align='center'>Status Data</div></td>
                    </tr>
                   </table>";
        }   
        
        $BtnKeluar=isset($_POST['BtnKeluar'])?$_POST['BtnKeluar']:NULL;
        if (isset($BtnKeluar)) {
            echo"<meta http-equiv='refresh' content='1;URL=?act=KlaimBaruOtomatis&action=Keluar'>";
        }

    ?>
    </div>
            <table width="100%" align="center" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr class="head3">					
                    <td width="490px">
                        Periode : 
                        <select name="tanggalawal" class="text" onkeydown="setDefault(this, document.getElementById('MsgIsi3'));" id="TxtIsi3">
                             <?php
                                if(!$tanggalawal==""){
                                    echo "<option id='TxtIsi3' value=$tanggalawal>$tanggalawal</option>";
                                }                                    
                                loadTglnow();
                             ?>
                        </select>                        
                        <select name="bulanawal" class="text" onkeydown="setDefault(this, document.getElementById('MsgIsi2'));" id="TxtIsi2">
                             <?php
                                if(!$bulanawal==""){
                                    echo "<option id='TxtIsi2' value=$bulanawal>$bulanawal</option>";
                                }                                    
                                loadBlnnow();
                             ?>
                        </select>                        
                        <select name="tahunawal" class="text" onkeydown="setDefault(this, document.getElementById('MsgIsi1'));" id="TxtIsi1">
                             <?php
                                if(!$tahunawal==""){
                                    echo "<option id='TxtIsi1' value=$tahunawal>$tahunawal</option>";
                                }                                    
                                loadThnnow();
                             ?>
                        </select>
                        &nbsp;&nbsp;s.d.&nbsp;&nbsp;                        
                        <select name="tanggalakhir" class="text" onkeydown="setDefault(this, document.getElementById('MsgIsi6'));" id="TxtIsi6">
                             <?php
                                if(!$tanggalakhir==""){
                                    echo "<option id='TxtIsi6' value=$tanggalakhir>$tanggalakhir</option>";
                                }                                    
                                loadTglnow();
                             ?>
                        </select>                        
                        <select name="bulanakhir" class="text" onkeydown="setDefault(this, document.getElementById('MsgIsi5'));" id="TxtIsi5">
                             <?php
                                if(!$bulanakhir==""){
                                    echo "<option id='TxtIsi5' value=$bulanakhir>$bulanakhir</option>";
                                }                                    
                                loadBlnnow();
                             ?>
                        </select>
                        <select name="tahunakhir" class="text" onkeydown="setDefault(this, document.getElementById('MsgIsi4'));" id="TxtIsi4">
                             <?php
                                if(!$tahunakhir==""){
                                    echo "<option id='TxtIsi4' value=$tahunakhir>$tahunakhir</option>";
                                }                                    
                                loadThnnow();
                             ?>
                        </select>
                        <input name=BtnCari type=submit class="button" value="&nbsp;&nbsp;Cari&nbsp;&nbsp;" />
                    </td>
                    <td width="140px" >Record : <?php echo $jumlah; ?> </td>
                    <td><input name=BtnKeluar type=submit class="button" value="&nbsp;&nbsp;&nbsp;Keluar&nbsp;&nbsp;&nbsp;" /> </td>
                </tr>
            </table>
	</form>
    </div>
</div>
<?php 
   echo "<meta http-equiv='refresh' content='60;URL=?act=KlaimBaruOtomatis&tahunawal=$tahunawal&bulanawal=$bulanawal&tanggalawal=$tanggalawal&tahunakhir=$tahunakhir&bulanakhir=$bulanakhir&tanggalakhir=$tanggalakhir&codernik=$codernik'>";
?>

