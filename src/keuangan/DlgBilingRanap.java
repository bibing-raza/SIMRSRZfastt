
/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */
package keuangan;

import fungsi.WarnaTable;
import fungsi.WarnaTable2;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgCariObat2;
import inventory.DlgReturJual;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariPeriksaLab;
import simrskhanza.DlgCariPeriksaRadiologi;
import simrskhanza.DlgDeposit;
import inventory.DlgPemberianObat;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import keuangan.DlgLhtBiaya;
import keuangan.DlgLhtPiutang;
import keuangan.Jurnal;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariPerawatanRanap;
import simrskhanza.DlgInputResepPulang;
import simrskhanza.DlgPenanggungJawab;
import simrskhanza.DlgPeriksaLaboratorium;
import simrskhanza.DlgPeriksaRadiologi;
import simrskhanza.DlgRawatInap;
import simrskhanza.DlgRawatJalan;
import simrskhanza.DlgResepPulang;
import simrskhanza.DlgTagihanOperasi;

/**
 *
 * @author perpustakaan
 */
public class DlgBilingRanap extends javax.swing.JDialog {
    private final DefaultTableModel tabModeRwJlDr, tabModeTambahan, tabModePotongan, tabModeKamIn, tabModeAkunBayar, tabModeAkunPiutang;
    public DlgPenanggungJawab penjab = new DlgPenanggungJawab(null, false);
    public DlgDeposit deposit = new DlgDeposit(null, false);
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Jurnal jur = new Jurnal();
    private int diagnosa_cek = 0, data_a = 0;
    private PreparedStatement pscekbilling, pscarirm, pscaripasien, psreg, pskamar, pscarialamat, psbiling,
            psdokterranap, psdokterralan, pscariobat, psobatlangsung, psobatoperasi,
            psreturobat, psdetaillab, pstamkur, psrekening, psakunbayar, psakunpiutang,
            pskamarin, psbiayasekali, psbiayaharian, psreseppulang, pstambahanbiaya, pspotonganbiaya, pstemporary,
            psralandokter, psralandrpr, psranapdrpr, psranapdokter, pssep,
            psoperasi, psralanperawat, psranapperawat, pscaridpjp, 
            psperiksalab, pssudahmasuk, pskategori, psubahpenjab, psperiksarad, psanak, psnota, psservice;
    private ResultSet rscekbilling, rscarirm, rscaripasien, rsreg, rskamar, rscarialamat, rsdetaillab, 
            rsdokterranap, rsranapdrpr, rsdokterralan, rscariobat, rsobatlangsung, rsobatoperasi, rsreturobat, rsubahpenjab,
            rskamarin, rsbiayasekali, rsbiayaharian, rsreseppulang, rstambahanbiaya, rspotonganbiaya, rssep,
            rsralandokter, rsralandrpr, rsranapdokter, rsoperasi, rsralanperawat, rsranapperawat, rsperiksalab, rskategori,
            rsperiksarad, rsanak, rstamkur, rsrekening, rsservice, rsakunbayar, rsakunpiutang, rscaridpjp;
    private String biaya = "", tambahan = "", totals = "", norawatbayi = "", centangdokterranap = "", kd_pj = "", jamplgRS1 = "",
            rinciandokterranap = "", rincianoperasi = "", hariawal = "", notaranap = "", tampilkan_administrasi_di_billingranap = "",
            Tindakan_Ranap = "", Laborat_Ranap = "", Radiologi_Ranap = "", Obat_Ranap = "", Registrasi_Ranap = "",
            Tambahan_Ranap = "", Potongan_Ranap = "", Retur_Obat_Ranap = "", Resep_Pulang_Ranap = "", Kamar_Inap = "", Operasi_Ranap = "",
            Harian_Ranap = "", Uang_Muka_Ranap = "", Piutang_Pasien_Ranap = "", tampilkan_ppnobat_ranap = "", tglmskRS = "", tglklrRS1 = "",
            Service_Ranap = "", status = "", diagnosa_ok = "", cekdokter = "", kdkamar = "", data_pasien = "", tglklrRS2 = "", jamplgRS2 = "",
            sqlpscekbilling = "select count(billing.no_rawat) from billing where billing.no_rawat=?",
            sqlpsdokterranap = "select dokter.nm_dokter from rawat_inap_dr "
            + "inner join dokter on rawat_inap_dr.kd_dokter=dokter.kd_dokter "
            + "where no_rawat=? group by rawat_inap_dr.kd_dokter",
            sqlpsdokterralan = "select dokter.nm_dokter from rawat_jl_dr "
            + "inner join dokter on rawat_jl_dr.kd_dokter=dokter.kd_dokter "
            + "where no_rawat=? group by rawat_jl_dr.kd_dokter",
            sqlpsobatoperasi = "select obatbhp_ok.nm_obat,beri_obat_operasi.hargasatuan,beri_obat_operasi.jumlah, "
            + "(beri_obat_operasi.hargasatuan*beri_obat_operasi.jumlah) as total "
            + "from obatbhp_ok inner join beri_obat_operasi "
            + "on beri_obat_operasi.kd_obat=obatbhp_ok.kd_obat where "
            + "beri_obat_operasi.no_rawat=? group by obatbhp_ok.nm_obat",
            sqlpsreturobat = "select databarang.nama_brng,detreturjual.h_retur, "
            + "sum(detreturjual.jml_retur * -1) as jml, "
            + "sum(detreturjual.subtotal * -1) as ttl from detreturjual inner join databarang inner join returjual "
            + "on detreturjual.kode_brng=databarang.kode_brng "
            + "and returjual.no_retur_jual=detreturjual.no_retur_jual where returjual.no_retur_jual=? group by databarang.nama_brng",
            sqlpsobatlangsung = "select besar_tagihan from tagihan_obat_langsung where no_rawat=? ",
            sqlpskamarin = "select kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.trf_kamar,"
            + "kamar_inap.lama,kamar_inap.ttl_biaya as total,kamar_inap.tgl_masuk, "
            + "kamar_inap.jam_masuk,if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar) as tgl_keluar,"
            + "if(kamar_inap.jam_keluar='00:00:00',current_time(),kamar_inap.jam_keluar) as jam_keluar "
            + "from kamar_inap inner join bangsal inner join kamar "
            + "on kamar_inap.kd_kamar=kamar.kd_kamar "
            + "and kamar.kd_bangsal=bangsal.kd_bangsal where "
            + "kamar_inap.no_rawat=? order by kamar_inap.tgl_masuk,kamar_inap.kd_kamar",
            sqlpsbiayasekali = "select nama_biaya,besar_biaya,(besar_biaya*1) as total from biaya_sekali "
            + " where kd_kamar=? order by nama_biaya",
            sqlpsbiayaharian = "select nama_biaya,besar_biaya,jml,(jml*besar_biaya*?) as total from biaya_harian "
            + " where kd_kamar=? order by nama_biaya",
            sqlpsreseppulang = "select databarang.nama_brng,resep_pulang.harga,"
            + "resep_pulang.jml_barang,resep_pulang.dosis,resep_pulang.total "
            + "from resep_pulang inner join databarang "
            + "on resep_pulang.kode_brng=databarang.kode_brng where "
            + "resep_pulang.no_rawat=? order by databarang.nama_brng",
            sqlpstambahanbiaya = "select nama_biaya, besar_biaya from tambahan_biaya where no_rawat=? ",
            sqlpspotonganbiaya = "select nama_pengurangan, besar_pengurangan from pengurangan_biaya where no_rawat=? ",
            sqlpsralandokter = "select jns_perawatan.nm_perawatan,rawat_jl_dr.biaya_rawat as total_byrdr,count(rawat_jl_dr.kd_jenis_prw) as jml, "
            + "sum(rawat_jl_dr.biaya_rawat) as biaya,"
            + "sum(rawat_jl_dr.bhp) as totalbhp,"
            + "sum(rawat_jl_dr.material) as totalmaterial,"
            + "rawat_jl_dr.tarif_tindakandr,"
            + "sum(rawat_jl_dr.tarif_tindakandr) as totaltarif_tindakandr  "
            + "from rawat_jl_dr inner join jns_perawatan inner join kategori_perawatan "
            + "on rawat_jl_dr.kd_jenis_prw=jns_perawatan.kd_jenis_prw and "
            + "jns_perawatan.kd_kategori=kategori_perawatan.kd_kategori where "
            + "rawat_jl_dr.no_rawat=? and kategori_perawatan.kd_kategori=? group by rawat_jl_dr.kd_jenis_prw",
            sqlpsralandrpr = "SELECT concat(jp.nm_perawatan,' (',d.nm_dokter,')') nm_perawatan, r.biaya_rawat AS total_byrdr, count(r.kd_jenis_prw) AS jml, "
            + "sum(r.biaya_rawat) AS biaya, sum(r.bhp) AS totalbhp, sum(r.material) AS totalmaterial, "
            + "r.tarif_tindakandr, sum(r.tarif_tindakanpr) AS totaltarif_tindakanpr, "
            + "sum(r.tarif_tindakandr) AS totaltarif_tindakandr "
            + "FROM rawat_jl_drpr r INNER JOIN jns_perawatan jp ON jp.kd_jenis_prw=r.kd_jenis_prw "
            + "INNER JOIN kategori_perawatan kp ON kp.kd_kategori=jp.kd_kategori "
            + "INNER JOIN dokter d ON d.kd_dokter=r.kd_dokter WHERE r.no_rawat =? AND kp.kd_kategori =? GROUP BY r.kd_jenis_prw, r.kd_dokter",
            sqlpsranapdokter = "select CONCAT(jns_perawatan_inap.nm_perawatan,' (',dokter.nm_dokter,')') nm_perawatan,rawat_inap_dr.biaya_rawat as total_byrdr,count(rawat_inap_dr.kd_jenis_prw) as jml, "
            + "sum(rawat_inap_dr.biaya_rawat) as biaya,"
            + "sum(rawat_inap_dr.bhp) as totalbhp,"
            + "sum(rawat_inap_dr.material) as totalmaterial,"
            + "rawat_inap_dr.tarif_tindakandr,"
            + "sum(rawat_inap_dr.tarif_tindakandr) as totaltarif_tindakandr "
            + "from rawat_inap_dr inner join jns_perawatan_inap inner join kategori_perawatan INNER JOIN dokter "
            + "on rawat_inap_dr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw and "
            + "jns_perawatan_inap.kd_kategori=kategori_perawatan.kd_kategori where dokter.kd_dokter = rawat_inap_dr.kd_dokter and  "
            + "rawat_inap_dr.no_rawat=? and kategori_perawatan.kd_kategori=? group by rawat_inap_dr.kd_jenis_prw, rawat_inap_dr.kd_dokter",
            sqlpsranapdrpr = "select concat(jns_perawatan_inap.nm_perawatan,' (',dokter.nm_dokter,') (',IF(petugas.nama like 'PETUGAS%',replace(petugas.nama,'PETUGAS ',''),petugas.nama),')') nm_perawatan,rawat_inap_drpr.biaya_rawat as total_byrdr,count(rawat_inap_drpr.kd_jenis_prw) as jml, "
            + "sum(rawat_inap_drpr.biaya_rawat) as biaya,"
            + "sum(rawat_inap_drpr.bhp) as totalbhp,"
            + "sum(rawat_inap_drpr.material) as totalmaterial,"
            + "rawat_inap_drpr.tarif_tindakandr,"
            + "sum(rawat_inap_drpr.tarif_tindakanpr) as totaltarif_tindakanpr, "
            + "sum(rawat_inap_drpr.tarif_tindakandr) as totaltarif_tindakandr "
            + "from rawat_inap_drpr inner join jns_perawatan_inap inner join kategori_perawatan INNER JOIN dokter INNER JOIN petugas "
            + "on rawat_inap_drpr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw and "
            + "jns_perawatan_inap.kd_kategori=kategori_perawatan.kd_kategori and dokter.kd_dokter = rawat_inap_drpr.kd_dokter and petugas.nip = rawat_inap_drpr.nip where "
            + "rawat_inap_drpr.no_rawat=? and kategori_perawatan.kd_kategori=? group by rawat_inap_drpr.kd_jenis_prw,rawat_inap_drpr.kd_dokter, rawat_inap_drpr.nip",
            sqlpsralanperawat = "select jns_perawatan.nm_perawatan,jns_perawatan.total_byrpr,count(jns_perawatan.nm_perawatan) as jml, "
            + "jns_perawatan.total_byrpr*count(jns_perawatan.nm_perawatan) as biaya "
            + "from rawat_jl_pr inner join jns_perawatan inner join kategori_perawatan  "
            + "on rawat_jl_pr.kd_jenis_prw=jns_perawatan.kd_jenis_prw  and "
            + "jns_perawatan.kd_kategori=kategori_perawatan.kd_kategori where "
            + "rawat_jl_pr.no_rawat=? and kategori_perawatan.kd_kategori=? group by rawat_jl_pr.kd_jenis_prw ",
            sqlpsranapperawat = "select concat(jns_perawatan_inap.nm_perawatan,' (',IF(petugas.nama like 'PETUGAS%',replace(petugas.nama,'PETUGAS ',''),petugas.nama),')') nm_perawatan,jns_perawatan_inap.total_byrpr,count(jns_perawatan_inap.nm_perawatan) as jml, "
            + "jns_perawatan_inap.total_byrpr*count(jns_perawatan_inap.nm_perawatan) as biaya "
            + "from rawat_inap_pr inner join jns_perawatan_inap inner join petugas inner join kategori_perawatan "
            + "on rawat_inap_pr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw  and "
            + "jns_perawatan_inap.kd_kategori=kategori_perawatan.kd_kategori where petugas.nip = rawat_inap_pr.nip and "
            + "rawat_inap_pr.no_rawat=? and kategori_perawatan.kd_kategori=?  group by rawat_inap_pr.kd_jenis_prw,rawat_inap_pr.nip",
            //            sqlpsoperasi = "select paket_operasi.nm_perawatan,(operasi.biayaoperator1+operasi.biayaoperator2+"
            //            + "operasi.biayaoperator3+operasi.biayaasisten_operator1+operasi.biayaasisten_operator2+"
            //            + "operasi.biayaasisten_operator3+operasi.biayainstrumen+operasi.biayadokter_anak+"
            //            + "operasi.biayaperawaat_resusitas+operasi.biayadokter_anestesi+operasi.biayaasisten_anestesi+"
            //            + "operasi.biayaasisten_anestesi2+operasi.biayabidan+operasi.biayabidan2+operasi.biayabidan3+"
            //            + "operasi.biayaperawat_luar+operasi.biayaalat+operasi.biayasewaok+operasi.akomodasi+"
            //            + "operasi.bagian_rs+operasi.biaya_omloop+operasi.biaya_omloop2+operasi.biaya_omloop3+"
            //            + "operasi.biaya_omloop4+operasi.biaya_omloop5+operasi.biayasarpras+operasi.biaya_dokter_pjanak+"
            //            + "operasi.biaya_dokter_umum) as biaya,operasi.biayaoperator1,"
            //            + "operasi.biayaoperator2,operasi.biayaoperator3,operasi.biayaasisten_operator1,operasi.biayaasisten_operator2,operasi.biayaasisten_operator3,"
            //            + "operasi.biayainstrumen,operasi.biayadokter_anak,operasi.biayaperawaat_resusitas,"
            //            + "operasi.biayadokter_anestesi,operasi.biayaasisten_anestesi,operasi.biayaasisten_anestesi2,operasi.biayabidan,operasi.biayabidan2,operasi.biayabidan3,operasi.biayaperawat_luar,"
            //            + "operasi.biayaalat,operasi.biayasewaok,operasi.akomodasi,operasi.bagian_rs,operasi.biaya_omloop,operasi.biaya_omloop2,operasi.biaya_omloop3,operasi.biaya_omloop4,operasi.biaya_omloop5,"
            //            + "operasi.biayasarpras,operasi.biaya_dokter_pjanak,operasi.biaya_dokter_umum "
            //            + "from operasi inner join paket_operasi "
            //            + "on operasi.kode_paket=paket_operasi.kode_paket where "
            //            + "operasi.no_rawat=? and operasi.status like ?",
            sqlpsoperasi = "select paket_operasi.nm_perawatan,(operasi.biayaoperator1+operasi.biayaoperator2+"
            + "operasi.biayaoperator3+operasi.biayaasisten_operator1+operasi.biayaasisten_operator2+"
            + "operasi.biayaasisten_operator3+operasi.biayainstrumen+operasi.biayadokter_anak+"
            + "operasi.biayaperawaat_resusitas+operasi.biayadokter_anestesi+operasi.biayaasisten_anestesi+"
            + "operasi.biayaasisten_anestesi2+operasi.biayabidan+operasi.biayabidan2+operasi.biayabidan3+"
            + "operasi.biayaperawat_luar+operasi.biayaalat+operasi.biayasewaok+operasi.akomodasi+"
            + "operasi.bagian_rs+operasi.biaya_omloop+operasi.biaya_omloop2+operasi.biaya_omloop3+"
            + "operasi.biaya_omloop4+operasi.biaya_omloop5+operasi.biayasarpras+operasi.biaya_dokter_pjanak+"
            + "operasi.biaya_dokter_umum) as biaya,operasi.biayaoperator1,"
            + "operasi.biayaoperator2,operasi.biayaoperator3,operasi.biayaasisten_operator1,operasi.biayaasisten_operator2,operasi.biayaasisten_operator3,"
            + "operasi.biayainstrumen,operasi.biayadokter_anak,operasi.biayaperawaat_resusitas,"
            + "operasi.biayadokter_anestesi,operasi.biayaasisten_anestesi,operasi.biayaasisten_anestesi2,operasi.biayabidan,operasi.biayabidan2,operasi.biayabidan3,operasi.biayaperawat_luar,"
            + "operasi.biayaalat,operasi.biayasewaok,operasi.akomodasi,operasi.bagian_rs,operasi.biaya_omloop,operasi.biaya_omloop2,operasi.biaya_omloop3,operasi.biaya_omloop4,operasi.biaya_omloop5,"
            + "operasi.biayasarpras,operasi.biaya_dokter_pjanak,operasi.biaya_dokter_umum,"
            + " ifnull(d1.nm_dokter,'-') dokter_operator1,"
            + " ifnull(d2.nm_dokter,'-') dokter_operator2,"
            + " ifnull(d3.nm_dokter,'-') dokter_operator3,"
            + " ifnull(p1.nama,'-') asisten_operator1,"
            + " ifnull(p2.nama,'-') asisten_operator2,"
            + " ifnull(d8.nm_dokter,'-') asisten_operator3,"
            + " ifnull(p3.nama,'-') instrumen,"
            + " ifnull(d4.nm_dokter,'-') dokter_anak,"
            + " ifnull(p4.nama,'-') perawaat_resusitas,"
            + " ifnull(d5.nm_dokter,'-') dokter_anestesi,"
            + " ifnull(p5.nama,'-') asisten_anestesi,"
            + " ifnull(d9.nm_dokter,'-') asisten_anestesi2,"
            + " ifnull(p6.nama,'-') bidan,"
            + " ifnull(p7.nama,'-') bidan2,"
            + " ifnull(p8.nama,'-') bidan3,"
            + " ifnull(p9.nama,'-') perawat_luar,"
            + " ifnull(p10.nama,'-') omloop,"
            + " ifnull(p11.nama,'-') omloop2,"
            + " ifnull(p12.nama,'-') omloop3,"
            + " ifnull(d10.nm_dokter,'-') omloop4,"
            + " ifnull(d11.nm_dokter,'-') omloop5 "
            + "from operasi inner join paket_operasi "
            + "on operasi.kode_paket=paket_operasi.kode_paket"
            + " LEFT JOIN dokter d1 on d1.kd_dokter = operasi.operator1"
            + " LEFT JOIN dokter d2 on d2.kd_dokter = operasi.operator2"
            + " LEFT JOIN dokter d3 on d3.kd_dokter = operasi.operator3"
            + " LEFT JOIN dokter d4 on d4.kd_dokter = operasi.dokter_anak"
            + " LEFT JOIN dokter d5 on d5.kd_dokter = operasi.dokter_anestesi"
            + " LEFT JOIN dokter d6 on d6.kd_dokter = operasi.dokter_pjanak"
            + " LEFT JOIN dokter d7 on d7.kd_dokter = operasi.dokter_umum"
            + " LEFT JOIN dokter d8 on d8.kd_dokter = operasi.asisten_operator3"
            + " LEFT JOIN dokter d9 on d9.kd_dokter = operasi.asisten_anestesi2"
            + " LEFT JOIN dokter d10 on d10.kd_dokter = operasi.omloop4"
            + " LEFT JOIN dokter d11 on d11.kd_dokter = operasi.omloop5"
            + " LEFT JOIN petugas p1 on p1.nip = operasi.asisten_operator1"
            + " LEFT JOIN petugas p2 on p2.nip = operasi.asisten_operator2"
            + " LEFT JOIN petugas p3 on p3.nip = operasi.instrumen"
            + " LEFT JOIN petugas p4 on p4.nip = operasi.perawaat_resusitas"
            + " LEFT JOIN petugas p5 on p5.nip = operasi.asisten_anestesi"
            + " LEFT JOIN petugas p6 on p6.nip = operasi.bidan"
            + " LEFT JOIN petugas p7 on p7.nip = operasi.bidan2"
            + " LEFT JOIN petugas p8 on p8.nip = operasi.bidan3"
            + " LEFT JOIN petugas p9 on p9.nip = operasi.perawat_luar"
            + " LEFT JOIN petugas p10 on p10.nip = operasi.omloop"
            + " LEFT JOIN petugas p11 on p11.nip = operasi.omloop2"
            + " LEFT JOIN petugas p12 on p12.nip = operasi.omloop3"
            + " where operasi.no_rawat=? and operasi.status like ?",
            sqlpsnota = "insert into nota_inap values(?,?,?,?,?)",
            sqlpsbiling = "insert into billing values('0',?,?,?,?,?,?,?,?,?,?,?)",
            sqlpssudahmasuk = "select no,nm_perawatan, if(biaya<>0,biaya,null) as satu, if(jumlah<>0,jumlah,null) as dua,"
            + "if(tambahan<>0,tambahan,null) as tiga, if(totalbiaya<>0,totalbiaya,null) as empat,pemisah,status "
            + "from billing where no_rawat=?  order by noindex",
            sqlpskategori = "SELECT kd_kategori, nm_kategori FROM kategori_perawatan order by urut",
            sqlpstamkur = "select biaya from temporary_tambahan_potongan where no_rawat=? and nama_tambahan=? and status=?",
            sqlpsanak = "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2 from reg_periksa inner join pasien inner join ranap_gabung on "
            + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?",
            sqlpstemporary = "insert into temporary_bayar_ranap values('0',?,?,?,?,?,?,?,?,'','','','','','','','','')",
            sqlpsubahpenjab = "select tgl_ubah,kd_pj1,kd_pj2 from ubah_penjab where no_rawat=?", no_nota = "";
    private double ttl = 0, y = 0, subttl = 0, lab, ttl1, ttl2, ttlobat, ttlretur, ppnobat, piutang = 0, kekurangan = 0, itembayar = 0, itempiutang = 0,
            tamkur = 0, detailjs = 0, detailbhp = 0, ppn = 0, besarppn = 0, tagihanppn = 0, bayar = 0, total = 0, uangdeposit = 0,
            ttlLaborat = 0, ttlRadiologi = 0, ttlOperasi = 0, ttlObat = 0, ttlRanap_Dokter = 0, ttlRanap_Paramedis = 0, ttlRalan_Dokter = 0,
            ttlRalan_Paramedis = 0, ttlTambahan = 0, ttlPotongan = 0, ttlKamar = 0, ttlRegistrasi = 0, ttlHarian = 0, ttlRetur_Obat = 0, ttlResep_Pulang = 0,
            laboratserv = 0, radiologiserv = 0, operasiserv = 0, obatserv = 0, rumus1 = 0, rumus2 = 0, rumus3 = 0, hasilrumus = 0, hasilmaksimal = 0,
            ranap_dokterserv = 0, ranap_paramedisserv = 0, ralan_dokterserv = 0,
            ralan_paramedisserv = 0, tambahanserv = 0, potonganserv = 0,
            kamarserv = 0, registrasiserv = 0, harianserv = 0, retur_Obatserv = 0, resep_Pulangserv = 0, ttlService = 0,
            persenbayi = Sequel.cariInteger("select bayi from set_jam_minimal");
    private int x = 0, z = 0, i = 0, countbayar = 0, jml = 0, r = 0, row2 = 0;
    private WarnaTable2 warna = new WarnaTable2();
    private WarnaTable2 warna2 = new WarnaTable2();
    private String[] Nama_Akun_Piutang, Kode_Rek_Piutang, Kd_PJ, Besar_Piutang, Jatuh_Tempo,
            Nama_Akun_Bayar, Kode_Rek_Bayar, Bayar, PPN_Persen, PPN_Besar;

    /**
     * Creates new form DlgBiling
     *
     * @param parent
     * @param modal
     */
    public DlgBilingRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //this.setLocation(8,1);
        //setSize(891,640);

        Object[] rowRwJlDr = {"Pilih", "Keterangan", "Tagihan/Tindakan/Terapi", "", "Biaya", "Jumlah", "Tambahan", "Total Biaya", ""};
        tabModeRwJlDr = new DefaultTableModel(null, rowRwJlDr) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 6) || (colIndex == 0)) {
                    a = true;
                }
                return a;
            }

            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbBilling.setModel(tabModeRwJlDr);

        //tbPetugas.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPetugas.getBackground()));
        tbBilling.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbBilling.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbBilling.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(40);
            } else if (i == 1) {
                column.setPreferredWidth(185);
            } else if (i == 2) {
                column.setPreferredWidth(570);
            } else if (i == 3) {
                column.setPreferredWidth(15);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(60);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }

        tbBilling.setDefaultRenderer(Object.class, new WarnaTable());

        //tambahan biaya
        Object[] rowTambahan = {"Tambahan Biaya", "Besar Biaya"};
        tabModeTambahan = new DefaultTableModel(null, rowTambahan) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return true;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbTambahan.setModel(tabModeTambahan);

        tbTambahan.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbTambahan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbTambahan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(300);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            }
        }
        tbTambahan.setDefaultRenderer(Object.class, new WarnaTable());

        //potongan biaya
        Object[] rowPotongan = {"Potongan Biaya", "Besar Potongan"};
        tabModePotongan = new DefaultTableModel(null, rowPotongan) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return true;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbPotongan.setModel(tabModePotongan);

        tbPotongan.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbPotongan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbPotongan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(300);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            }
        }
        tbPotongan.setDefaultRenderer(Object.class, new WarnaTable());

        //ubah lama inap
        Object[] rowUbahLama = {"Kode Kamar", "Nama Kamar", "Tgl.Masuk", "Jam Masuk", "Tgl.Keluar", "Jam Keluar", "Lama Inap"};
        tabModeKamIn = new DefaultTableModel(null, rowUbahLama) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 2) || (colIndex == 3) || (colIndex == 4) || (colIndex == 5 || (colIndex == 6))) {
                    a = true;
                }
                return a;
            }

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbUbahLama.setModel(tabModeKamIn);

        tbUbahLama.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbUbahLama.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbUbahLama.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(90);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else {
                column.setPreferredWidth(80);
            }
        }

        tbUbahLama.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));

        tabModeAkunBayar = new DefaultTableModel(null, new Object[]{"Nama Akun", "Kode Rek", "Bayar", "PPN(%)", "PPN(Rp)"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 2)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbAkunBayar.setModel(tabModeAkunBayar);

        tbAkunBayar.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbAkunBayar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbAkunBayar.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(345);
            } else if (i == 1) {
                //column.setPreferredWidth(70);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(112);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            }
        }
        warna.kolom = 2;
        tbAkunBayar.setDefaultRenderer(Object.class, warna);

        tabModeAkunPiutang = new DefaultTableModel(null, new Object[]{"Nama Akun", "Kode Rek", "Kd PJ", "Piutang", "Jatuh Tempo"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 3) || (colIndex == 4)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbAkunPiutang.setModel(tabModeAkunPiutang);

        tbAkunPiutang.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbAkunPiutang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbAkunPiutang.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(405);
            } else if (i == 1) {
                //column.setPreferredWidth(70);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                //column.setPreferredWidth(70);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setPreferredWidth(112);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            }
        }
        warna2.kolom = 3;
        tbAkunPiutang.setDefaultRenderer(Object.class, warna2);

        deposit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DLgBilingRanap")) {
                    if (status.equals("belum")) {
                        uangdeposit = Sequel.cariIsiAngka("select ifnull(sum(besar_deposit),0) from deposit where no_rawat=?", TNoRw.getText());
                    } else {
                        uangdeposit = Sequel.cariIsiAngka("select ifnull(sum(Uang_Muka),0) from nota_inap where no_rawat=?", TNoRw.getText());
                    }
                    Deposit.setText(Valid.SetAngka(uangdeposit));
                    isKembali();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DLgBilingRanap")) {
                    if (penjab.getTable().getSelectedRow() != -1) {
                        kdpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 1).toString());
                        nmpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 2).toString());
                    }
                    kdpenjab.requestFocus();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DLgBilingRanap")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        notaranap = Sequel.cariIsi("select cetaknotasimpanranap from set_nota");
        centangdokterranap = Sequel.cariIsi("select centangdokterranap from set_nota");
        rinciandokterranap = Sequel.cariIsi("select rinciandokterranap from set_nota");
        rincianoperasi = Sequel.cariIsi("select rincianoperasi from set_nota");
        tampilkan_administrasi_di_billingranap = Sequel.cariIsi("select tampilkan_administrasi_di_billingranap from set_nota");
        tampilkan_ppnobat_ranap = Sequel.cariIsi("select tampilkan_ppnobat_ranap from set_nota");
        hakkelas.setDocument(new batasInput((byte) 2).getOnlyAngka(hakkelas));
        
        try {
            psrekening = koneksi.prepareStatement("select * from set_akun_ranap");
            try {
                rsrekening = psrekening.executeQuery();
                if (rsrekening.next()) {
                    Tindakan_Ranap = rsrekening.getString("Suspen_Piutang_Tindakan_Ranap");
                    Laborat_Ranap = rsrekening.getString("Suspen_Piutang_Laborat_Ranap");
                    Radiologi_Ranap = rsrekening.getString("Suspen_Piutang_Radiologi_Ranap");
                    Obat_Ranap = rsrekening.getString("Suspen_Piutang_Obat_Ranap");
                    Registrasi_Ranap = rsrekening.getString("Registrasi_Ranap");
                    Tambahan_Ranap = rsrekening.getString("Tambahan_Ranap");
                    Potongan_Ranap = rsrekening.getString("Potongan_Ranap");
                    Retur_Obat_Ranap = rsrekening.getString("Retur_Obat_Ranap");
                    Resep_Pulang_Ranap = rsrekening.getString("Resep_Pulang_Ranap");
                    Kamar_Inap = rsrekening.getString("Kamar_Inap");
                    Operasi_Ranap = rsrekening.getString("Operasi_Ranap");
                    Harian_Ranap = rsrekening.getString("Harian_Ranap");
                    Uang_Muka_Ranap = rsrekening.getString("Uang_Muka_Ranap");
                    Piutang_Pasien_Ranap = rsrekening.getString("Piutang_Pasien_Ranap");
                    Service_Ranap = rsrekening.getString("Service_Ranap");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsrekening != null) {
                    rsrekening.close();
                }
                if (psrekening != null) {
                    psrekening.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnRawatJalan = new javax.swing.JMenuItem();
        MnSelisihTarif = new javax.swing.JMenuItem();
        MnGanti = new javax.swing.JMenu();
        MnNamaTelahTerima = new javax.swing.JMenuItem();
        MnPenjab = new javax.swing.JMenuItem();
        MnUbahLamaInap = new javax.swing.JMenuItem();
        MnBayi = new javax.swing.JMenu();
        MnRawatJalan1 = new javax.swing.JMenuItem();
        MnRawatInap1 = new javax.swing.JMenuItem();
        MnInputObat1 = new javax.swing.JMenuItem();
        MnInputResepPulang1 = new javax.swing.JMenuItem();
        MnPeriksaRadiologi1 = new javax.swing.JMenuItem();
        MnPeriksaLab1 = new javax.swing.JMenuItem();
        MnTambahan1 = new javax.swing.JMenuItem();
        MnPotongan1 = new javax.swing.JMenuItem();
        MnObatLangsung1 = new javax.swing.JMenuItem();
        MnDataObat1 = new javax.swing.JMenuItem();
        MnDataResepPulang1 = new javax.swing.JMenuItem();
        MnReturJual1 = new javax.swing.JMenuItem();
        MnCariPeriksaLab1 = new javax.swing.JMenuItem();
        MnCariRadiologi1 = new javax.swing.JMenuItem();
        MnPiutangPasien = new javax.swing.JMenuItem();
        MnInputObat = new javax.swing.JMenuItem();
        MnInputResepPulang = new javax.swing.JMenuItem();
        MnPeriksaLab = new javax.swing.JMenuItem();
        MnPeriksaRadiologi = new javax.swing.JMenuItem();
        MnTagihanOperasi = new javax.swing.JMenuItem();
        MnTambahan = new javax.swing.JMenuItem();
        MnPotongan = new javax.swing.JMenuItem();
        MnObatLangsung = new javax.swing.JMenuItem();
        MnReturJual = new javax.swing.JMenuItem();
        MnDataObat = new javax.swing.JMenuItem();
        MnDataResepPulang = new javax.swing.JMenuItem();
        MnCariPeriksaLab = new javax.swing.JMenuItem();
        MnCariRadiologi = new javax.swing.JMenuItem();
        MnHapusTagihan = new javax.swing.JMenuItem();
        Popup2 = new javax.swing.JPopupMenu();
        ppPerbaikiHakKelas = new javax.swing.JMenuItem();
        WindowInput = new javax.swing.JDialog();
        internalFrame2 = new widget.InternalFrame();
        TotalObat = new widget.TextBox();
        jLabel8 = new widget.Label();
        BtnCloseIn = new widget.Button();
        BtnSimpan2 = new widget.Button();
        BtnBatal1 = new widget.Button();
        WindowInput3 = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbTambahan = new widget.Table();
        panelisi1 = new widget.panelisi();
        label15 = new widget.Label();
        norawattambahan = new widget.TextBox();
        BtnTambah = new widget.Button();
        BtnSimpan3 = new widget.Button();
        BtnHapus = new widget.Button();
        BtnKeluar1 = new widget.Button();
        WindowInput4 = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        scrollPane2 = new widget.ScrollPane();
        tbPotongan = new widget.Table();
        panelisi2 = new widget.panelisi();
        label16 = new widget.Label();
        norawatpotongan = new widget.TextBox();
        BtnTambahPotongan = new widget.Button();
        BtnSimpanPotongan = new widget.Button();
        BtnHapusPotongan = new widget.Button();
        BtnKeluarPotongan = new widget.Button();
        WindowInput5 = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        scrollPane3 = new widget.ScrollPane();
        tbUbahLama = new widget.Table();
        panelisi3 = new widget.panelisi();
        label17 = new widget.Label();
        norawatubahlama = new widget.TextBox();
        label18 = new widget.Label();
        BtnSimpanUbahLama = new widget.Button();
        BtnKeluarUbahLama = new widget.Button();
        WindowInput6 = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        BtnSimpan4 = new widget.Button();
        jLabel17 = new widget.Label();
        kdpenjab = new widget.TextBox();
        nmpenjab = new widget.TextBox();
        btnPenjab = new widget.Button();
        WindowInput7 = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        BtnCloseIn5 = new widget.Button();
        BtnSimpan5 = new widget.Button();
        jLabel19 = new widget.Label();
        nm_telah_terima = new widget.TextBox();
        WindowSelisihTarif = new javax.swing.JDialog();
        internalFrame10 = new widget.InternalFrame();
        internalFrame11 = new widget.InternalFrame();
        jLabel42 = new widget.Label();
        NoSEP = new widget.TextBox();
        jLabel47 = new widget.Label();
        norm = new widget.TextBox();
        nmpasien = new widget.TextBox();
        nokartu = new widget.TextBox();
        jLabel50 = new widget.Label();
        jLabel52 = new widget.Label();
        rginap = new widget.TextBox();
        jLabel54 = new widget.Label();
        kdINACBG = new widget.TextBox();
        deskripsiKD = new widget.TextBox();
        jLabel56 = new widget.Label();
        tarifkls1 = new widget.TextBox();
        jLabel57 = new widget.Label();
        tarifkls2 = new widget.TextBox();
        jLabel58 = new widget.Label();
        tarifkls3 = new widget.TextBox();
        jLabel48 = new widget.Label();
        jLabel63 = new widget.Label();
        tarifRC = new widget.TextBox();
        internalFrame12 = new widget.InternalFrame();
        jLabel53 = new widget.Label();
        hakkelas = new widget.TextBox();
        jLabel59 = new widget.Label();
        naikKLS = new widget.TextBox();
        jLabel60 = new widget.Label();
        lmrawat = new widget.TextBox();
        jLabel61 = new widget.Label();
        persenSELISIH = new widget.TextBox();
        jLabel62 = new widget.Label();
        jLabel55 = new widget.Label();
        Scroll39 = new widget.ScrollPane();
        TKalkulasi = new widget.TextArea();
        jLabel64 = new widget.Label();
        internalFrame13 = new widget.InternalFrame();
        BtnSimpan6 = new widget.Button();
        BtnGantikode = new widget.Button();
        BtnCloseIn6 = new widget.Button();
        panelGlass8 = new widget.panelisi();
        PopupPiutang = new javax.swing.JPopupMenu();
        ppBersihkan1 = new javax.swing.JMenuItem();
        PopupBayar = new javax.swing.JPopupMenu();
        ppBersihkan = new javax.swing.JMenuItem();
        kddokter = new widget.TextBox();
        nmdokter = new widget.TextBox();
        hasilLM = new widget.TextBox();
        realcostRS = new widget.TextBox();
        tglPiutang = new widget.Tanggal();
        internalFrame1 = new widget.InternalFrame();
        panelGlass1 = new widget.panelisi();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel4 = new widget.Label();
        DTPTgl = new widget.Tanggal();
        lbl_jns_byr = new widget.Label();
        panelGlass2 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnNota = new widget.Button();
        BtnView = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel23 = new widget.Label();
        tglNota = new widget.Tanggal();
        cekLR = new widget.CekBox();
        cekObat = new widget.CekBox();
        TabRawat = new javax.swing.JTabbedPane();
        Scroll = new widget.ScrollPane();
        tbBilling = new widget.Table();
        panelBayar = new widget.panelisi();
        TtlSemua = new widget.TextBox();
        TKembali = new widget.TextBox();
        jLabel5 = new widget.Label();
        jLabel15 = new widget.Label();
        TagihanPPn = new widget.TextBox();
        scrollPane4 = new widget.ScrollPane();
        tbAkunBayar = new widget.Table();
        jLabel6 = new widget.Label();
        scrollPane5 = new widget.ScrollPane();
        tbAkunPiutang = new widget.Table();
        TCari = new widget.TextBox();
        BtnCariBayar = new widget.Button();
        TCari1 = new widget.TextBox();
        btnCariPiutang = new widget.Button();
        jLabel13 = new widget.Label();
        Deposit = new widget.TextBox();
        BtnSeek2 = new widget.Button();
        ChkPiutang = new widget.CekBox();
        chkRalan = new widget.CekBox();
        chkRanap = new widget.CekBox();
        jLabel18 = new widget.Label();
        jLabel7 = new widget.Label();
        cmbPenjamin = new widget.ComboBox();
        jLabel9 = new widget.Label();
        TketPenjamin = new widget.TextBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnRawatJalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRawatJalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRawatJalan.setText("Tagihan/Tindakan Rawat Jalan");
        MnRawatJalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRawatJalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRawatJalan.setIconTextGap(5);
        MnRawatJalan.setName("MnRawatJalan"); // NOI18N
        MnRawatJalan.setPreferredSize(new java.awt.Dimension(250, 28));
        MnRawatJalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRawatJalanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRawatJalan);

        MnSelisihTarif.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSelisihTarif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSelisihTarif.setText("Selisih Tarif INACBG");
        MnSelisihTarif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSelisihTarif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSelisihTarif.setIconTextGap(5);
        MnSelisihTarif.setName("MnSelisihTarif"); // NOI18N
        MnSelisihTarif.setPreferredSize(new java.awt.Dimension(250, 28));
        MnSelisihTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSelisihTarifActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSelisihTarif);

        MnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGanti.setText("Ganti Data");
        MnGanti.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGanti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGanti.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGanti.setIconTextGap(5);
        MnGanti.setName("MnGanti"); // NOI18N
        MnGanti.setPreferredSize(new java.awt.Dimension(250, 28));

        MnNamaTelahTerima.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnNamaTelahTerima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnNamaTelahTerima.setText("Nama Org. Telah Terima Di Kwitansi");
        MnNamaTelahTerima.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnNamaTelahTerima.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnNamaTelahTerima.setIconTextGap(5);
        MnNamaTelahTerima.setName("MnNamaTelahTerima"); // NOI18N
        MnNamaTelahTerima.setPreferredSize(new java.awt.Dimension(230, 28));
        MnNamaTelahTerima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnNamaTelahTerimaActionPerformed(evt);
            }
        });
        MnGanti.add(MnNamaTelahTerima);

        MnPenjab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenjab.setText("Jenis Bayar");
        MnPenjab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPenjab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPenjab.setIconTextGap(5);
        MnPenjab.setName("MnPenjab"); // NOI18N
        MnPenjab.setPreferredSize(new java.awt.Dimension(230, 28));
        MnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenjabActionPerformed(evt);
            }
        });
        MnGanti.add(MnPenjab);

        MnUbahLamaInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUbahLamaInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUbahLamaInap.setText("Ubah Lama Inap");
        MnUbahLamaInap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUbahLamaInap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUbahLamaInap.setIconTextGap(5);
        MnUbahLamaInap.setName("MnUbahLamaInap"); // NOI18N
        MnUbahLamaInap.setPreferredSize(new java.awt.Dimension(230, 28));
        MnUbahLamaInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUbahLamaInapActionPerformed(evt);
            }
        });
        MnGanti.add(MnUbahLamaInap);

        jPopupMenu1.add(MnGanti);

        MnBayi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBayi.setText("Tagihan Bayi");
        MnBayi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBayi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBayi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBayi.setIconTextGap(5);
        MnBayi.setName("MnBayi"); // NOI18N
        MnBayi.setPreferredSize(new java.awt.Dimension(250, 28));

        MnRawatJalan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRawatJalan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRawatJalan1.setText("Tagihan/Tindakan Rawat Jalan");
        MnRawatJalan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRawatJalan1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRawatJalan1.setIconTextGap(5);
        MnRawatJalan1.setName("MnRawatJalan1"); // NOI18N
        MnRawatJalan1.setPreferredSize(new java.awt.Dimension(250, 28));
        MnRawatJalan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRawatJalan1ActionPerformed(evt);
            }
        });
        MnBayi.add(MnRawatJalan1);

        MnRawatInap1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRawatInap1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRawatInap1.setText("Tagihan/Tindakan Rawat Inap");
        MnRawatInap1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRawatInap1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRawatInap1.setIconTextGap(5);
        MnRawatInap1.setName("MnRawatInap1"); // NOI18N
        MnRawatInap1.setPreferredSize(new java.awt.Dimension(250, 28));
        MnRawatInap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRawatInap1ActionPerformed(evt);
            }
        });
        MnBayi.add(MnRawatInap1);

        MnInputObat1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputObat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputObat1.setText("Input Pemberian Obat");
        MnInputObat1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputObat1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputObat1.setIconTextGap(5);
        MnInputObat1.setName("MnInputObat1"); // NOI18N
        MnInputObat1.setPreferredSize(new java.awt.Dimension(250, 28));
        MnInputObat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputObat1ActionPerformed(evt);
            }
        });
        MnBayi.add(MnInputObat1);

        MnInputResepPulang1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputResepPulang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputResepPulang1.setText("Input Resep Pulang");
        MnInputResepPulang1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputResepPulang1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputResepPulang1.setIconTextGap(5);
        MnInputResepPulang1.setName("MnInputResepPulang1"); // NOI18N
        MnInputResepPulang1.setPreferredSize(new java.awt.Dimension(250, 28));
        MnInputResepPulang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputResepPulang1ActionPerformed(evt);
            }
        });
        MnBayi.add(MnInputResepPulang1);

        MnPeriksaRadiologi1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaRadiologi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaRadiologi1.setText("Input Periksa Radiologi");
        MnPeriksaRadiologi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaRadiologi1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaRadiologi1.setIconTextGap(5);
        MnPeriksaRadiologi1.setName("MnPeriksaRadiologi1"); // NOI18N
        MnPeriksaRadiologi1.setPreferredSize(new java.awt.Dimension(250, 28));
        MnPeriksaRadiologi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaRadiologi1ActionPerformed(evt);
            }
        });
        MnBayi.add(MnPeriksaRadiologi1);

        MnPeriksaLab1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaLab1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaLab1.setText("Input Periksa Lab");
        MnPeriksaLab1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaLab1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaLab1.setIconTextGap(5);
        MnPeriksaLab1.setName("MnPeriksaLab1"); // NOI18N
        MnPeriksaLab1.setPreferredSize(new java.awt.Dimension(250, 28));
        MnPeriksaLab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaLab1ActionPerformed(evt);
            }
        });
        MnBayi.add(MnPeriksaLab1);

        MnTambahan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTambahan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTambahan1.setText("Tambahan Biaya");
        MnTambahan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTambahan1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTambahan1.setIconTextGap(5);
        MnTambahan1.setName("MnTambahan1"); // NOI18N
        MnTambahan1.setPreferredSize(new java.awt.Dimension(250, 25));
        MnTambahan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTambahan1ActionPerformed(evt);
            }
        });
        MnBayi.add(MnTambahan1);

        MnPotongan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPotongan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPotongan1.setText("Potongan Biaya");
        MnPotongan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPotongan1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPotongan1.setIconTextGap(5);
        MnPotongan1.setName("MnPotongan1"); // NOI18N
        MnPotongan1.setPreferredSize(new java.awt.Dimension(250, 25));
        MnPotongan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPotongan1ActionPerformed(evt);
            }
        });
        MnBayi.add(MnPotongan1);

        MnObatLangsung1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnObatLangsung1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnObatLangsung1.setText("Tagihan BHP & Obat Langsung");
        MnObatLangsung1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnObatLangsung1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnObatLangsung1.setIconTextGap(5);
        MnObatLangsung1.setName("MnObatLangsung1"); // NOI18N
        MnObatLangsung1.setPreferredSize(new java.awt.Dimension(250, 25));
        MnObatLangsung1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnObatLangsung1ActionPerformed(evt);
            }
        });
        MnBayi.add(MnObatLangsung1);

        MnDataObat1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataObat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataObat1.setText("Data Pemberian Obat");
        MnDataObat1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataObat1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataObat1.setIconTextGap(5);
        MnDataObat1.setName("MnDataObat1"); // NOI18N
        MnDataObat1.setPreferredSize(new java.awt.Dimension(250, 28));
        MnDataObat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataObat1ActionPerformed(evt);
            }
        });
        MnBayi.add(MnDataObat1);

        MnDataResepPulang1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataResepPulang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataResepPulang1.setText("Data Resep Pulang");
        MnDataResepPulang1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataResepPulang1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataResepPulang1.setIconTextGap(5);
        MnDataResepPulang1.setName("MnDataResepPulang1"); // NOI18N
        MnDataResepPulang1.setPreferredSize(new java.awt.Dimension(250, 28));
        MnDataResepPulang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataResepPulang1ActionPerformed(evt);
            }
        });
        MnBayi.add(MnDataResepPulang1);

        MnReturJual1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnReturJual1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnReturJual1.setText("Retur Obat/Barang/Alkes");
        MnReturJual1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnReturJual1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnReturJual1.setIconTextGap(5);
        MnReturJual1.setName("MnReturJual1"); // NOI18N
        MnReturJual1.setPreferredSize(new java.awt.Dimension(250, 25));
        MnReturJual1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnReturJual1ActionPerformed(evt);
            }
        });
        MnBayi.add(MnReturJual1);

        MnCariPeriksaLab1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCariPeriksaLab1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCariPeriksaLab1.setText("Data Pemeriksaan Lab");
        MnCariPeriksaLab1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCariPeriksaLab1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCariPeriksaLab1.setIconTextGap(5);
        MnCariPeriksaLab1.setName("MnCariPeriksaLab1"); // NOI18N
        MnCariPeriksaLab1.setPreferredSize(new java.awt.Dimension(250, 25));
        MnCariPeriksaLab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCariPeriksaLab1ActionPerformed(evt);
            }
        });
        MnBayi.add(MnCariPeriksaLab1);

        MnCariRadiologi1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCariRadiologi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCariRadiologi1.setText("Data Pemeriksaan Radiologi");
        MnCariRadiologi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCariRadiologi1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCariRadiologi1.setIconTextGap(5);
        MnCariRadiologi1.setName("MnCariRadiologi1"); // NOI18N
        MnCariRadiologi1.setPreferredSize(new java.awt.Dimension(250, 25));
        MnCariRadiologi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCariRadiologi1ActionPerformed(evt);
            }
        });
        MnBayi.add(MnCariRadiologi1);

        jPopupMenu1.add(MnBayi);

        MnPiutangPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPiutangPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPiutangPasien.setText("Piutang Pasien");
        MnPiutangPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPiutangPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPiutangPasien.setIconTextGap(5);
        MnPiutangPasien.setName("MnPiutangPasien"); // NOI18N
        MnPiutangPasien.setPreferredSize(new java.awt.Dimension(250, 28));
        MnPiutangPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPiutangPasienActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPiutangPasien);

        MnInputObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputObat.setText("Input Pemberian Obat");
        MnInputObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputObat.setIconTextGap(5);
        MnInputObat.setName("MnInputObat"); // NOI18N
        MnInputObat.setPreferredSize(new java.awt.Dimension(250, 28));
        MnInputObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputObatActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnInputObat);

        MnInputResepPulang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputResepPulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputResepPulang.setText("Input Resep Pulang");
        MnInputResepPulang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputResepPulang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputResepPulang.setIconTextGap(5);
        MnInputResepPulang.setName("MnInputResepPulang"); // NOI18N
        MnInputResepPulang.setPreferredSize(new java.awt.Dimension(250, 28));
        MnInputResepPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputResepPulangActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnInputResepPulang);

        MnPeriksaLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaLab.setText("Input Periksa Lab");
        MnPeriksaLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaLab.setIconTextGap(5);
        MnPeriksaLab.setName("MnPeriksaLab"); // NOI18N
        MnPeriksaLab.setPreferredSize(new java.awt.Dimension(250, 28));
        MnPeriksaLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaLabActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPeriksaLab);

        MnPeriksaRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaRadiologi.setText("Input Periksa Radiologi");
        MnPeriksaRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaRadiologi.setIconTextGap(5);
        MnPeriksaRadiologi.setName("MnPeriksaRadiologi"); // NOI18N
        MnPeriksaRadiologi.setPreferredSize(new java.awt.Dimension(250, 28));
        MnPeriksaRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaRadiologiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPeriksaRadiologi);

        MnTagihanOperasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTagihanOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTagihanOperasi.setText("Tagihan Operasi/VK");
        MnTagihanOperasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTagihanOperasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTagihanOperasi.setIconTextGap(5);
        MnTagihanOperasi.setName("MnTagihanOperasi"); // NOI18N
        MnTagihanOperasi.setPreferredSize(new java.awt.Dimension(250, 28));
        MnTagihanOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTagihanOperasiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnTagihanOperasi);

        MnTambahan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTambahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTambahan.setText("Tambahan Biaya");
        MnTambahan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTambahan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTambahan.setIconTextGap(5);
        MnTambahan.setName("MnTambahan"); // NOI18N
        MnTambahan.setPreferredSize(new java.awt.Dimension(250, 25));
        MnTambahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTambahanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnTambahan);

        MnPotongan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPotongan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPotongan.setText("Potongan Biaya");
        MnPotongan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPotongan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPotongan.setIconTextGap(5);
        MnPotongan.setName("MnPotongan"); // NOI18N
        MnPotongan.setPreferredSize(new java.awt.Dimension(250, 25));
        MnPotongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPotonganActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPotongan);

        MnObatLangsung.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnObatLangsung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnObatLangsung.setText("Tagihan BHP & Obat Langsung");
        MnObatLangsung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnObatLangsung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnObatLangsung.setIconTextGap(5);
        MnObatLangsung.setName("MnObatLangsung"); // NOI18N
        MnObatLangsung.setPreferredSize(new java.awt.Dimension(250, 25));
        MnObatLangsung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnObatLangsungActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnObatLangsung);

        MnReturJual.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnReturJual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnReturJual.setText("Retur Obat/Barang/Alkes");
        MnReturJual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnReturJual.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnReturJual.setIconTextGap(5);
        MnReturJual.setName("MnReturJual"); // NOI18N
        MnReturJual.setPreferredSize(new java.awt.Dimension(250, 25));
        MnReturJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnReturJualActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnReturJual);

        MnDataObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataObat.setText("Data Pemberian Obat");
        MnDataObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataObat.setIconTextGap(5);
        MnDataObat.setName("MnDataObat"); // NOI18N
        MnDataObat.setPreferredSize(new java.awt.Dimension(250, 28));
        MnDataObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataObatActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDataObat);

        MnDataResepPulang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataResepPulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataResepPulang.setText("Data Resep Pulang");
        MnDataResepPulang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataResepPulang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataResepPulang.setIconTextGap(5);
        MnDataResepPulang.setName("MnDataResepPulang"); // NOI18N
        MnDataResepPulang.setPreferredSize(new java.awt.Dimension(250, 28));
        MnDataResepPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataResepPulangActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDataResepPulang);

        MnCariPeriksaLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCariPeriksaLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCariPeriksaLab.setText("Data Pemeriksaan Lab");
        MnCariPeriksaLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCariPeriksaLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCariPeriksaLab.setIconTextGap(5);
        MnCariPeriksaLab.setName("MnCariPeriksaLab"); // NOI18N
        MnCariPeriksaLab.setPreferredSize(new java.awt.Dimension(250, 25));
        MnCariPeriksaLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCariPeriksaLabActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCariPeriksaLab);

        MnCariRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCariRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCariRadiologi.setText("Data Pemeriksaan Radiologi");
        MnCariRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCariRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCariRadiologi.setIconTextGap(5);
        MnCariRadiologi.setName("MnCariRadiologi"); // NOI18N
        MnCariRadiologi.setPreferredSize(new java.awt.Dimension(250, 25));
        MnCariRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCariRadiologiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCariRadiologi);

        MnHapusTagihan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusTagihan.setText("Hapus Nota Salah");
        MnHapusTagihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTagihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTagihan.setIconTextGap(5);
        MnHapusTagihan.setName("MnHapusTagihan"); // NOI18N
        MnHapusTagihan.setPreferredSize(new java.awt.Dimension(250, 28));
        MnHapusTagihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTagihanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusTagihan);

        Popup2.setName("Popup2"); // NOI18N

        ppPerbaikiHakKelas.setBackground(new java.awt.Color(242, 242, 242));
        ppPerbaikiHakKelas.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPerbaikiHakKelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPerbaikiHakKelas.setText("Perbaiki Hak Kelas");
        ppPerbaikiHakKelas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPerbaikiHakKelas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPerbaikiHakKelas.setIconTextGap(8);
        ppPerbaikiHakKelas.setName("ppPerbaikiHakKelas"); // NOI18N
        ppPerbaikiHakKelas.setPreferredSize(new java.awt.Dimension(140, 25));
        ppPerbaikiHakKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPerbaikiHakKelasActionPerformed(evt);
            }
        });
        Popup2.add(ppPerbaikiHakKelas);

        WindowInput.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowInput.setName("WindowInput"); // NOI18N
        WindowInput.setUndecorated(true);
        WindowInput.setResizable(false);

        internalFrame2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Input Total BHP & Obat]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame2.setLayout(null);

        TotalObat.setForeground(new java.awt.Color(0, 0, 0));
        TotalObat.setName("TotalObat"); // NOI18N
        TotalObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TotalObatKeyPressed(evt);
            }
        });
        internalFrame2.add(TotalObat);
        TotalObat.setBounds(60, 30, 180, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Total :");
        jLabel8.setName("jLabel8"); // NOI18N
        internalFrame2.add(jLabel8);
        jLabel8.setBounds(0, 30, 57, 23);

        BtnCloseIn.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn.setMnemonic('3');
        BtnCloseIn.setText("Tutup");
        BtnCloseIn.setToolTipText("Alt+3");
        BtnCloseIn.setName("BtnCloseIn"); // NOI18N
        BtnCloseIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseInActionPerformed(evt);
            }
        });
        BtnCloseIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseInKeyPressed(evt);
            }
        });
        internalFrame2.add(BtnCloseIn);
        BtnCloseIn.setBounds(465, 30, 100, 30);

        BtnSimpan2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan2.setMnemonic('1');
        BtnSimpan2.setText("Simpan");
        BtnSimpan2.setToolTipText("Alt+1");
        BtnSimpan2.setName("BtnSimpan2"); // NOI18N
        BtnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan2ActionPerformed(evt);
            }
        });
        BtnSimpan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan2KeyPressed(evt);
            }
        });
        internalFrame2.add(BtnSimpan2);
        BtnSimpan2.setBounds(255, 30, 100, 30);

        BtnBatal1.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnBatal1.setMnemonic('2');
        BtnBatal1.setText("Hapus");
        BtnBatal1.setToolTipText("Alt+2");
        BtnBatal1.setName("BtnBatal1"); // NOI18N
        BtnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatal1ActionPerformed(evt);
            }
        });
        BtnBatal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatal1KeyPressed(evt);
            }
        });
        internalFrame2.add(BtnBatal1);
        BtnBatal1.setBounds(360, 30, 100, 30);

        WindowInput.getContentPane().add(internalFrame2, java.awt.BorderLayout.CENTER);

        WindowInput3.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowInput3.setName("WindowInput3"); // NOI18N
        WindowInput3.setUndecorated(true);
        WindowInput3.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Tambah Biaya ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbTambahan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbTambahan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTambahan.setName("tbTambahan"); // NOI18N
        scrollPane1.setViewportView(tbTambahan);

        internalFrame4.add(scrollPane1, java.awt.BorderLayout.CENTER);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("No.Rawat :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi1.add(label15);

        norawattambahan.setEditable(false);
        norawattambahan.setForeground(new java.awt.Color(0, 0, 0));
        norawattambahan.setName("norawattambahan"); // NOI18N
        norawattambahan.setPreferredSize(new java.awt.Dimension(150, 23));
        norawattambahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norawattambahanKeyPressed(evt);
            }
        });
        panelisi1.add(norawattambahan);

        BtnTambah.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/add-file-16x16.png"))); // NOI18N
        BtnTambah.setMnemonic('T');
        BtnTambah.setText("Tambah");
        BtnTambah.setToolTipText("Alt+T");
        BtnTambah.setName("BtnTambah"); // NOI18N
        BtnTambah.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });
        panelisi1.add(BtnTambah);

        BtnSimpan3.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan3.setMnemonic('S');
        BtnSimpan3.setText("Simpan");
        BtnSimpan3.setToolTipText("Alt+S");
        BtnSimpan3.setName("BtnSimpan3"); // NOI18N
        BtnSimpan3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan3ActionPerformed(evt);
            }
        });
        panelisi1.add(BtnSimpan3);

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        panelisi1.add(BtnHapus);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        panelisi1.add(BtnKeluar1);

        internalFrame4.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        WindowInput3.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        WindowInput4.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowInput4.setName("WindowInput4"); // NOI18N
        WindowInput4.setUndecorated(true);
        WindowInput4.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Potongan Biaya ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setOpaque(true);

        tbPotongan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbPotongan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPotongan.setName("tbPotongan"); // NOI18N
        scrollPane2.setViewportView(tbPotongan);

        internalFrame5.add(scrollPane2, java.awt.BorderLayout.CENTER);

        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("No.Rawat :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi2.add(label16);

        norawatpotongan.setEditable(false);
        norawatpotongan.setForeground(new java.awt.Color(0, 0, 0));
        norawatpotongan.setName("norawatpotongan"); // NOI18N
        norawatpotongan.setPreferredSize(new java.awt.Dimension(150, 23));
        norawatpotongan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norawatpotonganKeyPressed(evt);
            }
        });
        panelisi2.add(norawatpotongan);

        BtnTambahPotongan.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahPotongan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/add-file-16x16.png"))); // NOI18N
        BtnTambahPotongan.setMnemonic('T');
        BtnTambahPotongan.setText("Tambah");
        BtnTambahPotongan.setToolTipText("Alt+T");
        BtnTambahPotongan.setName("BtnTambahPotongan"); // NOI18N
        BtnTambahPotongan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnTambahPotongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahPotonganActionPerformed(evt);
            }
        });
        panelisi2.add(BtnTambahPotongan);

        BtnSimpanPotongan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanPotongan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanPotongan.setMnemonic('S');
        BtnSimpanPotongan.setText("Simpan");
        BtnSimpanPotongan.setToolTipText("Alt+S");
        BtnSimpanPotongan.setName("BtnSimpanPotongan"); // NOI18N
        BtnSimpanPotongan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpanPotongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanPotonganActionPerformed(evt);
            }
        });
        panelisi2.add(BtnSimpanPotongan);

        BtnHapusPotongan.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusPotongan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusPotongan.setMnemonic('H');
        BtnHapusPotongan.setText("Hapus");
        BtnHapusPotongan.setToolTipText("Alt+H");
        BtnHapusPotongan.setName("BtnHapusPotongan"); // NOI18N
        BtnHapusPotongan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapusPotongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusPotonganActionPerformed(evt);
            }
        });
        panelisi2.add(BtnHapusPotongan);

        BtnKeluarPotongan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluarPotongan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluarPotongan.setMnemonic('K');
        BtnKeluarPotongan.setText("Keluar");
        BtnKeluarPotongan.setToolTipText("Alt+K");
        BtnKeluarPotongan.setName("BtnKeluarPotongan"); // NOI18N
        BtnKeluarPotongan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluarPotongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarPotonganActionPerformed(evt);
            }
        });
        panelisi2.add(BtnKeluarPotongan);

        internalFrame5.add(panelisi2, java.awt.BorderLayout.PAGE_END);

        WindowInput4.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        WindowInput5.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowInput5.setName("WindowInput5"); // NOI18N
        WindowInput5.setUndecorated(true);
        WindowInput5.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ubah Manual Lama Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane3.setName("scrollPane3"); // NOI18N
        scrollPane3.setOpaque(true);

        tbUbahLama.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbUbahLama.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbUbahLama.setName("tbUbahLama"); // NOI18N
        scrollPane3.setViewportView(tbUbahLama);

        internalFrame6.add(scrollPane3, java.awt.BorderLayout.CENTER);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("No.Rawat :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label17);

        norawatubahlama.setEditable(false);
        norawatubahlama.setForeground(new java.awt.Color(0, 0, 0));
        norawatubahlama.setName("norawatubahlama"); // NOI18N
        norawatubahlama.setPreferredSize(new java.awt.Dimension(200, 23));
        norawatubahlama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norawatubahlamaKeyPressed(evt);
            }
        });
        panelisi3.add(norawatubahlama);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(50, 23));
        panelisi3.add(label18);

        BtnSimpanUbahLama.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanUbahLama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanUbahLama.setMnemonic('S');
        BtnSimpanUbahLama.setText("Simpan");
        BtnSimpanUbahLama.setToolTipText("Alt+S");
        BtnSimpanUbahLama.setName("BtnSimpanUbahLama"); // NOI18N
        BtnSimpanUbahLama.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpanUbahLama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanUbahLamaActionPerformed(evt);
            }
        });
        panelisi3.add(BtnSimpanUbahLama);

        BtnKeluarUbahLama.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluarUbahLama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluarUbahLama.setMnemonic('K');
        BtnKeluarUbahLama.setText("Keluar");
        BtnKeluarUbahLama.setToolTipText("Alt+K");
        BtnKeluarUbahLama.setName("BtnKeluarUbahLama"); // NOI18N
        BtnKeluarUbahLama.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluarUbahLama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarUbahLamaActionPerformed(evt);
            }
        });
        panelisi3.add(BtnKeluarUbahLama);

        internalFrame6.add(panelisi3, java.awt.BorderLayout.PAGE_END);

        WindowInput5.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        WindowInput6.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowInput6.setName("WindowInput6"); // NOI18N
        WindowInput6.setUndecorated(true);
        WindowInput6.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Jenis Bayar ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame7.setLayout(null);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('P');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+P");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(510, 30, 100, 30);

        BtnSimpan4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan4.setMnemonic('S');
        BtnSimpan4.setText("Simpan");
        BtnSimpan4.setToolTipText("Alt+S");
        BtnSimpan4.setName("BtnSimpan4"); // NOI18N
        BtnSimpan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan4ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnSimpan4);
        BtnSimpan4.setBounds(405, 30, 100, 30);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Jenis Bayar :");
        jLabel17.setName("jLabel17"); // NOI18N
        internalFrame7.add(jLabel17);
        jLabel17.setBounds(0, 32, 77, 23);

        kdpenjab.setEditable(false);
        kdpenjab.setForeground(new java.awt.Color(0, 0, 0));
        kdpenjab.setName("kdpenjab"); // NOI18N
        kdpenjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpenjabKeyPressed(evt);
            }
        });
        internalFrame7.add(kdpenjab);
        kdpenjab.setBounds(81, 32, 60, 23);

        nmpenjab.setEditable(false);
        nmpenjab.setForeground(new java.awt.Color(0, 0, 0));
        nmpenjab.setName("nmpenjab"); // NOI18N
        internalFrame7.add(nmpenjab);
        nmpenjab.setBounds(144, 32, 220, 23);

        btnPenjab.setForeground(new java.awt.Color(0, 0, 0));
        btnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenjab.setMnemonic('7');
        btnPenjab.setToolTipText("ALt+7");
        btnPenjab.setName("btnPenjab"); // NOI18N
        btnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjabActionPerformed(evt);
            }
        });
        internalFrame7.add(btnPenjab);
        btnPenjab.setBounds(366, 32, 28, 23);

        WindowInput6.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        WindowInput7.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowInput7.setName("WindowInput7"); // NOI18N
        WindowInput7.setUndecorated(true);
        WindowInput7.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Nama Orang Telah Terima Di Kwitansi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame8.setLayout(null);

        BtnCloseIn5.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn5.setMnemonic('P');
        BtnCloseIn5.setText("Tutup");
        BtnCloseIn5.setToolTipText("Alt+P");
        BtnCloseIn5.setName("BtnCloseIn5"); // NOI18N
        BtnCloseIn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn5ActionPerformed(evt);
            }
        });
        internalFrame8.add(BtnCloseIn5);
        BtnCloseIn5.setBounds(620, 30, 100, 30);

        BtnSimpan5.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan5.setMnemonic('S');
        BtnSimpan5.setText("Simpan");
        BtnSimpan5.setToolTipText("Alt+S");
        BtnSimpan5.setName("BtnSimpan5"); // NOI18N
        BtnSimpan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan5ActionPerformed(evt);
            }
        });
        internalFrame8.add(BtnSimpan5);
        BtnSimpan5.setBounds(510, 30, 100, 30);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Nama Org. Sebagai Telah Terima : ");
        jLabel19.setName("jLabel19"); // NOI18N
        internalFrame8.add(jLabel19);
        jLabel19.setBounds(0, 32, 180, 23);

        nm_telah_terima.setForeground(new java.awt.Color(0, 0, 0));
        nm_telah_terima.setName("nm_telah_terima"); // NOI18N
        internalFrame8.add(nm_telah_terima);
        nm_telah_terima.setBounds(183, 32, 320, 23);

        WindowInput7.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        WindowSelisihTarif.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowSelisihTarif.setName("WindowSelisihTarif"); // NOI18N
        WindowSelisihTarif.setUndecorated(true);
        WindowSelisihTarif.setResizable(false);

        internalFrame10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Selisih Tarif Pasien R. Inap BPJS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame10.setLayout(new java.awt.BorderLayout());

        internalFrame11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setPreferredSize(new java.awt.Dimension(0, 160));
        internalFrame11.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame11.setLayout(null);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("No. SEP : ");
        jLabel42.setName("jLabel42"); // NOI18N
        internalFrame11.add(jLabel42);
        jLabel42.setBounds(0, 10, 102, 23);

        NoSEP.setEditable(false);
        NoSEP.setForeground(new java.awt.Color(0, 0, 0));
        NoSEP.setName("NoSEP"); // NOI18N
        NoSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NoSEPKeyTyped(evt);
            }
        });
        internalFrame11.add(NoSEP);
        NoSEP.setBounds(106, 10, 150, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("No. RM : ");
        jLabel47.setName("jLabel47"); // NOI18N
        internalFrame11.add(jLabel47);
        jLabel47.setBounds(260, 10, 50, 23);

        norm.setEditable(false);
        norm.setForeground(new java.awt.Color(0, 0, 0));
        norm.setName("norm"); // NOI18N
        internalFrame11.add(norm);
        norm.setBounds(310, 10, 80, 23);

        nmpasien.setEditable(false);
        nmpasien.setForeground(new java.awt.Color(0, 0, 0));
        nmpasien.setHighlighter(null);
        nmpasien.setName("nmpasien"); // NOI18N
        internalFrame11.add(nmpasien);
        nmpasien.setBounds(106, 38, 680, 23);

        nokartu.setEditable(false);
        nokartu.setForeground(new java.awt.Color(0, 0, 0));
        nokartu.setName("nokartu"); // NOI18N
        internalFrame11.add(nokartu);
        nokartu.setBounds(461, 10, 120, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Nama Pasien : ");
        jLabel50.setName("jLabel50"); // NOI18N
        internalFrame11.add(jLabel50);
        jLabel50.setBounds(0, 38, 102, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Ruang Rawat : ");
        jLabel52.setName("jLabel52"); // NOI18N
        internalFrame11.add(jLabel52);
        jLabel52.setBounds(0, 66, 102, 23);

        rginap.setEditable(false);
        rginap.setForeground(new java.awt.Color(0, 0, 0));
        rginap.setHighlighter(null);
        rginap.setName("rginap"); // NOI18N
        internalFrame11.add(rginap);
        rginap.setBounds(106, 66, 680, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Tarif : Kelas 1 Rp.");
        jLabel54.setName("jLabel54"); // NOI18N
        internalFrame11.add(jLabel54);
        jLabel54.setBounds(0, 122, 102, 23);

        kdINACBG.setForeground(new java.awt.Color(0, 0, 0));
        kdINACBG.setMaxLenth(15);
        kdINACBG.setName("kdINACBG"); // NOI18N
        kdINACBG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdINACBGKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kdINACBGKeyTyped(evt);
            }
        });
        internalFrame11.add(kdINACBG);
        kdINACBG.setBounds(106, 94, 90, 23);

        deskripsiKD.setEditable(false);
        deskripsiKD.setForeground(new java.awt.Color(0, 0, 0));
        deskripsiKD.setHighlighter(null);
        deskripsiKD.setName("deskripsiKD"); // NOI18N
        internalFrame11.add(deskripsiKD);
        deskripsiKD.setBounds(198, 94, 588, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 250));
        jLabel56.setText("Kode INACBG : ");
        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel56.setName("jLabel56"); // NOI18N
        internalFrame11.add(jLabel56);
        jLabel56.setBounds(0, 94, 102, 23);

        tarifkls1.setEditable(false);
        tarifkls1.setForeground(new java.awt.Color(0, 0, 0));
        tarifkls1.setText("0");
        tarifkls1.setHighlighter(null);
        tarifkls1.setName("tarifkls1"); // NOI18N
        internalFrame11.add(tarifkls1);
        tarifkls1.setBounds(106, 122, 100, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Kelas 2 Rp.");
        jLabel57.setName("jLabel57"); // NOI18N
        internalFrame11.add(jLabel57);
        jLabel57.setBounds(205, 122, 66, 23);

        tarifkls2.setEditable(false);
        tarifkls2.setForeground(new java.awt.Color(0, 0, 0));
        tarifkls2.setText("0");
        tarifkls2.setHighlighter(null);
        tarifkls2.setName("tarifkls2"); // NOI18N
        internalFrame11.add(tarifkls2);
        tarifkls2.setBounds(275, 122, 100, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Kelas 3 Rp.");
        jLabel58.setName("jLabel58"); // NOI18N
        internalFrame11.add(jLabel58);
        jLabel58.setBounds(375, 122, 67, 23);

        tarifkls3.setEditable(false);
        tarifkls3.setForeground(new java.awt.Color(0, 0, 0));
        tarifkls3.setText("0");
        tarifkls3.setHighlighter(null);
        tarifkls3.setName("tarifkls3"); // NOI18N
        internalFrame11.add(tarifkls3);
        tarifkls3.setBounds(446, 122, 100, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("No. Kartu : ");
        jLabel48.setName("jLabel48"); // NOI18N
        internalFrame11.add(jLabel48);
        jLabel48.setBounds(390, 10, 70, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Real Cost RS Rp. ");
        jLabel63.setName("jLabel63"); // NOI18N
        internalFrame11.add(jLabel63);
        jLabel63.setBounds(545, 122, 100, 23);

        tarifRC.setEditable(false);
        tarifRC.setForeground(new java.awt.Color(0, 0, 0));
        tarifRC.setText("0");
        tarifRC.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tarifRC.setHighlighter(null);
        tarifRC.setName("tarifRC"); // NOI18N
        internalFrame11.add(tarifRC);
        tarifRC.setBounds(646, 122, 140, 23);

        internalFrame10.add(internalFrame11, java.awt.BorderLayout.PAGE_START);

        internalFrame12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Penghitungan selisih tarif (Permenkes RI No. 3 Tahun 2023) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame12.setName("internalFrame12"); // NOI18N
        internalFrame12.setPreferredSize(new java.awt.Dimension(0, 370));
        internalFrame12.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame12.setLayout(null);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Hak Kelas BPJS : ");
        jLabel53.setName("jLabel53"); // NOI18N
        internalFrame12.add(jLabel53);
        jLabel53.setBounds(0, 25, 102, 23);

        hakkelas.setForeground(new java.awt.Color(0, 0, 0));
        hakkelas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        hakkelas.setComponentPopupMenu(Popup2);
        hakkelas.setName("hakkelas"); // NOI18N
        internalFrame12.add(hakkelas);
        hakkelas.setBounds(106, 25, 40, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Naik ke : ");
        jLabel59.setName("jLabel59"); // NOI18N
        internalFrame12.add(jLabel59);
        jLabel59.setBounds(152, 25, 46, 23);

        naikKLS.setEditable(false);
        naikKLS.setForeground(new java.awt.Color(0, 0, 0));
        naikKLS.setHighlighter(null);
        naikKLS.setName("naikKLS"); // NOI18N
        internalFrame12.add(naikKLS);
        naikKLS.setBounds(199, 25, 83, 24);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Lama rawat Rg. VIP : ");
        jLabel60.setName("jLabel60"); // NOI18N
        internalFrame12.add(jLabel60);
        jLabel60.setBounds(290, 25, 110, 23);

        lmrawat.setEditable(false);
        lmrawat.setForeground(new java.awt.Color(0, 0, 0));
        lmrawat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lmrawat.setText("0");
        lmrawat.setHighlighter(null);
        lmrawat.setName("lmrawat"); // NOI18N
        internalFrame12.add(lmrawat);
        lmrawat.setBounds(400, 25, 40, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Persentase tambahan kelas VIP : ");
        jLabel61.setName("jLabel61"); // NOI18N
        internalFrame12.add(jLabel61);
        jLabel61.setBounds(482, 25, 170, 23);

        persenSELISIH.setEditable(false);
        persenSELISIH.setForeground(new java.awt.Color(0, 0, 0));
        persenSELISIH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        persenSELISIH.setText("0");
        persenSELISIH.setHighlighter(null);
        persenSELISIH.setName("persenSELISIH"); // NOI18N
        internalFrame12.add(persenSELISIH);
        persenSELISIH.setBounds(655, 25, 45, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("%");
        jLabel62.setName("jLabel62"); // NOI18N
        internalFrame12.add(jLabel62);
        jLabel62.setBounds(705, 25, 20, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Kalkulasi Tarif : ");
        jLabel55.setName("jLabel55"); // NOI18N
        internalFrame12.add(jLabel55);
        jLabel55.setBounds(0, 53, 102, 23);

        Scroll39.setName("Scroll39"); // NOI18N

        TKalkulasi.setEditable(false);
        TKalkulasi.setColumns(20);
        TKalkulasi.setRows(5);
        TKalkulasi.setName("TKalkulasi"); // NOI18N
        TKalkulasi.setPreferredSize(new java.awt.Dimension(250, 150));
        Scroll39.setViewportView(TKalkulasi);

        internalFrame12.add(Scroll39);
        Scroll39.setBounds(106, 53, 670, 165);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText("hari.");
        jLabel64.setName("jLabel64"); // NOI18N
        internalFrame12.add(jLabel64);
        jLabel64.setBounds(446, 25, 25, 23);

        internalFrame10.add(internalFrame12, java.awt.BorderLayout.CENTER);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setPreferredSize(new java.awt.Dimension(0, 44));
        internalFrame13.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 9, 9));

        BtnSimpan6.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan6.setMnemonic('S');
        BtnSimpan6.setText("Simpan");
        BtnSimpan6.setToolTipText("Alt+S");
        BtnSimpan6.setName("BtnSimpan6"); // NOI18N
        BtnSimpan6.setPreferredSize(new java.awt.Dimension(90, 26));
        BtnSimpan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan6ActionPerformed(evt);
            }
        });
        internalFrame13.add(BtnSimpan6);

        BtnGantikode.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantikode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantikode.setMnemonic('S');
        BtnGantikode.setText("Ganti");
        BtnGantikode.setToolTipText("Alt+S");
        BtnGantikode.setName("BtnGantikode"); // NOI18N
        BtnGantikode.setPreferredSize(new java.awt.Dimension(90, 26));
        BtnGantikode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantikodeActionPerformed(evt);
            }
        });
        internalFrame13.add(BtnGantikode);

        BtnCloseIn6.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn6.setMnemonic('U');
        BtnCloseIn6.setText("Tutup");
        BtnCloseIn6.setToolTipText("Alt+U");
        BtnCloseIn6.setName("BtnCloseIn6"); // NOI18N
        BtnCloseIn6.setPreferredSize(new java.awt.Dimension(90, 26));
        BtnCloseIn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn6ActionPerformed(evt);
            }
        });
        internalFrame13.add(BtnCloseIn6);

        internalFrame10.add(internalFrame13, java.awt.BorderLayout.PAGE_END);

        WindowSelisihTarif.getContentPane().add(internalFrame10, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        PopupPiutang.setName("PopupPiutang"); // NOI18N

        ppBersihkan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan1.setText("Bersihkan Piutang");
        ppBersihkan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan1.setIconTextGap(8);
        ppBersihkan1.setName("ppBersihkan1"); // NOI18N
        ppBersihkan1.setPreferredSize(new java.awt.Dimension(200, 25));
        ppBersihkan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkan1ActionPerformed(evt);
            }
        });
        PopupPiutang.add(ppBersihkan1);

        PopupBayar.setName("PopupBayar"); // NOI18N

        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan.setText("Bersihkan Pembayaran");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setIconTextGap(8);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(200, 25));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        PopupBayar.add(ppBersihkan);

        kddokter.setEditable(false);
        kddokter.setForeground(new java.awt.Color(0, 0, 0));
        kddokter.setName("kddokter"); // NOI18N
        kddokter.setPreferredSize(new java.awt.Dimension(90, 23));

        nmdokter.setEditable(false);
        nmdokter.setForeground(new java.awt.Color(0, 0, 0));
        nmdokter.setName("nmdokter"); // NOI18N
        nmdokter.setPreferredSize(new java.awt.Dimension(90, 23));

        hasilLM.setForeground(new java.awt.Color(0, 0, 0));
        hasilLM.setHighlighter(null);
        hasilLM.setName("hasilLM"); // NOI18N
        hasilLM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hasilLMKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hasilLMKeyPressed(evt);
            }
        });

        realcostRS.setForeground(new java.awt.Color(0, 0, 0));
        realcostRS.setHighlighter(null);
        realcostRS.setName("realcostRS"); // NOI18N
        realcostRS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                realcostRSKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                realcostRSKeyPressed(evt);
            }
        });

        tglPiutang.setEditable(false);
        tglPiutang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-06-2024" }));
        tglPiutang.setDisplayFormat("dd-MM-yyyy");
        tglPiutang.setName("tglPiutang"); // NOI18N
        tglPiutang.setOpaque(false);
        tglPiutang.setPreferredSize(new java.awt.Dimension(100, 23));
        tglPiutang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tglPiutangItemStateChanged(evt);
            }
        });
        tglPiutang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglPiutangKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Billing/Pembayaran Ranap Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass1.setPreferredSize(new java.awt.Dimension(100, 45));
        panelGlass1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 10));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass1.add(jLabel3);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.setPreferredSize(new java.awt.Dimension(150, 23));
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        panelGlass1.add(TNoRw);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass1.add(TNoRM);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        TPasien.setPreferredSize(new java.awt.Dimension(330, 23));
        panelGlass1.add(TPasien);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('R');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+R");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(140, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass1.add(BtnCari);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tanggal :");
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass1.add(jLabel4);

        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-06-2024 22:41:48" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.setPreferredSize(new java.awt.Dimension(140, 23));
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        panelGlass1.add(DTPTgl);

        lbl_jns_byr.setForeground(new java.awt.Color(0, 0, 0));
        lbl_jns_byr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_jns_byr.setText("lbl_jns_byr");
        lbl_jns_byr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_jns_byr.setName("lbl_jns_byr"); // NOI18N
        lbl_jns_byr.setPreferredSize(new java.awt.Dimension(260, 23));
        panelGlass1.add(lbl_jns_byr);

        internalFrame1.add(panelGlass1, java.awt.BorderLayout.PAGE_START);

        panelGlass2.setForeground(new java.awt.Color(153, 0, 51));
        panelGlass2.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass2.add(BtnSimpan);

        BtnNota.setForeground(new java.awt.Color(0, 0, 0));
        BtnNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Agenda-1-16x16.png"))); // NOI18N
        BtnNota.setMnemonic('B');
        BtnNota.setText(" Nota");
        BtnNota.setToolTipText("Alt+B");
        BtnNota.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnNota.setName("BtnNota"); // NOI18N
        BtnNota.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNotaActionPerformed(evt);
            }
        });
        BtnNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnNotaKeyPressed(evt);
            }
        });
        panelGlass2.add(BtnNota);

        BtnView.setForeground(new java.awt.Color(0, 0, 0));
        BtnView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnView.setMnemonic('L');
        BtnView.setText("Lihat");
        BtnView.setToolTipText("Alt+L");
        BtnView.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnView.setName("BtnView"); // NOI18N
        BtnView.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnViewActionPerformed(evt);
            }
        });
        BtnView.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnViewKeyPressed(evt);
            }
        });
        panelGlass2.add(BtnView);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass2.add(BtnKeluar);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tgl. Nota/Kwitansi : ");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass2.add(jLabel23);

        tglNota.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-06-2024" }));
        tglNota.setDisplayFormat("dd-MM-yyyy");
        tglNota.setName("tglNota"); // NOI18N
        tglNota.setOpaque(false);
        panelGlass2.add(tglNota);

        cekLR.setBorder(null);
        cekLR.setForeground(new java.awt.Color(0, 0, 255));
        cekLR.setSelected(true);
        cekLR.setText("Kwitansi/Nota DENGAN lama rawat");
        cekLR.setBorderPainted(true);
        cekLR.setBorderPaintedFlat(true);
        cekLR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cekLR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cekLR.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekLR.setName("cekLR"); // NOI18N
        cekLR.setOpaque(false);
        cekLR.setPreferredSize(new java.awt.Dimension(250, 23));
        cekLR.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cekLRItemStateChanged(evt);
            }
        });
        panelGlass2.add(cekLR);

        cekObat.setBorder(null);
        cekObat.setForeground(new java.awt.Color(0, 0, 0));
        cekObat.setText("Conteng SEMUA Obat & BHP (TIDAK)");
        cekObat.setBorderPainted(true);
        cekObat.setBorderPaintedFlat(true);
        cekObat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cekObat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cekObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekObat.setName("cekObat"); // NOI18N
        cekObat.setOpaque(false);
        cekObat.setPreferredSize(new java.awt.Dimension(230, 23));
        cekObat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cekObatItemStateChanged(evt);
            }
        });
        panelGlass2.add(cekObat);

        internalFrame1.add(panelGlass2, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(250, 255, 245));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRawat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbBilling.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbBilling.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbBilling.setComponentPopupMenu(jPopupMenu1);
        tbBilling.setName("tbBilling"); // NOI18N
        tbBilling.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBillingMouseClicked(evt);
            }
        });
        tbBilling.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBillingKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbBilling);

        TabRawat.addTab(".: Data Tagihan ", Scroll);

        panelBayar.setForeground(new java.awt.Color(153, 0, 51));
        panelBayar.setName("panelBayar"); // NOI18N
        panelBayar.setPreferredSize(new java.awt.Dimension(100, 137));
        panelBayar.setLayout(null);

        TtlSemua.setEditable(false);
        TtlSemua.setForeground(new java.awt.Color(0, 0, 0));
        TtlSemua.setText("0");
        TtlSemua.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        TtlSemua.setHighlighter(null);
        TtlSemua.setName("TtlSemua"); // NOI18N
        TtlSemua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtlSemuaKeyPressed(evt);
            }
        });
        panelBayar.add(TtlSemua);
        TtlSemua.setBounds(340, 10, 220, 23);

        TKembali.setEditable(false);
        TKembali.setForeground(new java.awt.Color(0, 0, 0));
        TKembali.setText("0");
        TKembali.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        TKembali.setHighlighter(null);
        TKembali.setName("TKembali"); // NOI18N
        panelBayar.add(TKembali);
        TKembali.setBounds(680, 415, 220, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Bayar : Rp. ");
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(95, 23));
        panelBayar.add(jLabel5);
        jLabel5.setBounds(20, 40, 90, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Total Tagihan : Rp. ");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(95, 23));
        panelBayar.add(jLabel15);
        jLabel15.setBounds(230, 10, 110, 23);

        TagihanPPn.setEditable(false);
        TagihanPPn.setForeground(new java.awt.Color(0, 0, 0));
        TagihanPPn.setText("0");
        TagihanPPn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        TagihanPPn.setHighlighter(null);
        TagihanPPn.setName("TagihanPPn"); // NOI18N
        panelBayar.add(TagihanPPn);
        TagihanPPn.setBounds(680, 10, 220, 23);

        scrollPane4.setComponentPopupMenu(PopupBayar);
        scrollPane4.setName("scrollPane4"); // NOI18N
        scrollPane4.setOpaque(true);

        tbAkunBayar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbAkunBayar.setToolTipText("");
        tbAkunBayar.setComponentPopupMenu(PopupBayar);
        tbAkunBayar.setName("tbAkunBayar"); // NOI18N
        tbAkunBayar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbAkunBayarPropertyChange(evt);
            }
        });
        tbAkunBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAkunBayarKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(tbAkunBayar);

        panelBayar.add(scrollPane4);
        scrollPane4.setBounds(110, 68, 790, 140);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Kembali : Rp. ");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(95, 23));
        panelBayar.add(jLabel6);
        jLabel6.setBounds(590, 415, 90, 23);

        scrollPane5.setComponentPopupMenu(PopupPiutang);
        scrollPane5.setName("scrollPane5"); // NOI18N
        scrollPane5.setOpaque(true);

        tbAkunPiutang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbAkunPiutang.setToolTipText("");
        tbAkunPiutang.setComponentPopupMenu(PopupPiutang);
        tbAkunPiutang.setName("tbAkunPiutang"); // NOI18N
        tbAkunPiutang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAkunPiutangMouseClicked(evt);
            }
        });
        tbAkunPiutang.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbAkunPiutangPropertyChange(evt);
            }
        });
        tbAkunPiutang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAkunPiutangKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(tbAkunPiutang);

        panelBayar.add(scrollPane5);
        scrollPane5.setBounds(110, 241, 790, 140);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(340, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelBayar.add(TCari);
        TCari.setBounds(110, 40, 762, 23);

        BtnCariBayar.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariBayar.setMnemonic('3');
        BtnCariBayar.setToolTipText("Alt+3");
        BtnCariBayar.setName("BtnCariBayar"); // NOI18N
        BtnCariBayar.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariBayarActionPerformed(evt);
            }
        });
        BtnCariBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariBayarKeyPressed(evt);
            }
        });
        panelBayar.add(BtnCariBayar);
        BtnCariBayar.setBounds(875, 40, 25, 23);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(340, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelBayar.add(TCari1);
        TCari1.setBounds(110, 213, 762, 23);

        btnCariPiutang.setForeground(new java.awt.Color(0, 0, 0));
        btnCariPiutang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        btnCariPiutang.setMnemonic('3');
        btnCariPiutang.setToolTipText("Alt+3");
        btnCariPiutang.setName("btnCariPiutang"); // NOI18N
        btnCariPiutang.setPreferredSize(new java.awt.Dimension(28, 23));
        btnCariPiutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPiutangActionPerformed(evt);
            }
        });
        btnCariPiutang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCariPiutangKeyPressed(evt);
            }
        });
        panelBayar.add(btnCariPiutang);
        btnCariPiutang.setBounds(875, 213, 25, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Deposit : Rp. ");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(95, 23));
        panelBayar.add(jLabel13);
        jLabel13.setBounds(0, 415, 110, 23);

        Deposit.setEditable(false);
        Deposit.setForeground(new java.awt.Color(0, 0, 0));
        Deposit.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        Deposit.setName("Deposit"); // NOI18N
        panelBayar.add(Deposit);
        Deposit.setBounds(110, 415, 220, 23);

        BtnSeek2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek2.setMnemonic('3');
        BtnSeek2.setToolTipText("Alt+3");
        BtnSeek2.setName("BtnSeek2"); // NOI18N
        BtnSeek2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek2ActionPerformed(evt);
            }
        });
        panelBayar.add(BtnSeek2);
        BtnSeek2.setBounds(332, 415, 25, 23);

        ChkPiutang.setForeground(new java.awt.Color(0, 0, 0));
        ChkPiutang.setText("Piutang : Rp. ");
        ChkPiutang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkPiutang.setName("ChkPiutang"); // NOI18N
        ChkPiutang.setOpaque(false);
        ChkPiutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPiutangActionPerformed(evt);
            }
        });
        panelBayar.add(ChkPiutang);
        ChkPiutang.setBounds(0, 213, 110, 23);

        chkRalan.setForeground(new java.awt.Color(0, 0, 0));
        chkRalan.setSelected(true);
        chkRalan.setText("Rawat Jalan");
        chkRalan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkRalan.setName("chkRalan"); // NOI18N
        chkRalan.setOpaque(false);
        chkRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRalanActionPerformed(evt);
            }
        });
        panelBayar.add(chkRalan);
        chkRalan.setBounds(0, 10, 110, 23);

        chkRanap.setForeground(new java.awt.Color(0, 0, 0));
        chkRanap.setSelected(true);
        chkRanap.setText("Rawat Inap");
        chkRanap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkRanap.setName("chkRanap"); // NOI18N
        chkRanap.setOpaque(false);
        chkRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRanapActionPerformed(evt);
            }
        });
        panelBayar.add(chkRanap);
        chkRanap.setBounds(120, 10, 95, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tagihan + PPN : Rp. ");
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(95, 23));
        panelBayar.add(jLabel18);
        jLabel18.setBounds(570, 10, 110, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Penjamin Piutang : ");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(95, 23));
        panelBayar.add(jLabel7);
        jLabel7.setBounds(0, 387, 110, 23);

        cmbPenjamin.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenjamin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Keluarga Pasien", "Pasien Sendiri", "Manajemen RSRAZA", "Karyawan RSRAZA", "Asuransi/Pihak 3", "Aparat Desa", "Anggota DPRD", "Ulama/Pemuka Agama", "Tokoh Masyarakat", "Pemerintah Daerah", "Dinas Sosial Kabupaten", "Dinas Kesehatan Kabupaten", "Pemerintah Provinsi", "Dinas Sosial Provinsi", "Dinas Kesehatan Provinsi", "Yayasan", "Panti Asuhan", "Panti Sosial", "Lain-lain" }));
        cmbPenjamin.setName("cmbPenjamin"); // NOI18N
        cmbPenjamin.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbPenjamin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbPenjaminMouseReleased(evt);
            }
        });
        panelBayar.add(cmbPenjamin);
        cmbPenjamin.setBounds(110, 387, 170, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Ket. Penjamin : ");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(95, 23));
        panelBayar.add(jLabel9);
        jLabel9.setBounds(280, 387, 95, 23);

        TketPenjamin.setForeground(new java.awt.Color(0, 0, 0));
        TketPenjamin.setName("TketPenjamin"); // NOI18N
        TketPenjamin.setPreferredSize(new java.awt.Dimension(340, 23));
        panelBayar.add(TketPenjamin);
        TketPenjamin.setBounds(377, 387, 523, 23);

        TabRawat.addTab(".: Pembayaran ", panelBayar);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            isRawat();
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
//        if (var.getbilling_ranap() == true) {
//            try {
//                pscekbilling=koneksi.prepareStatement(sqlpscekbilling);
//                try {
//                    pscekbilling.setString(1,TNoRw.getText());
//                    rscekbilling=pscekbilling.executeQuery();
//                    if(rscekbilling.next()){
//                        i=rscekbilling.getInt(1);
//                    }
//                } catch (Exception e) {
//                    System.out.println("Notifikasi : "+e);
//                } finally{
//                    if(rscekbilling!=null){
//                        rscekbilling.close();
//                    }
//                    if(pscekbilling!=null){
//                        pscekbilling.close();
//                    }
//                }
//                    
//
//                if(i<=0){
//                    int jawab=JOptionPane.showConfirmDialog(null, "Data pembayaran belum tersimpan, apa anda mau menyimpannya...????","Konfirmasi",JOptionPane.YES_NO_OPTION);
//                    if(jawab==JOptionPane.YES_OPTION){
//                        BtnSimpanActionPerformed(evt);
//                        dispose();
//                    }else{
//                        WindowInput.dispose();
//                        WindowInput3.dispose();
//                        WindowInput4.dispose();
//                        WindowInput5.dispose();
//                        WindowInput6.dispose();
//                        dispose(); 
//                    }                
//                }else if(i>0){
//                    WindowInput.dispose();
//                    WindowInput3.dispose();
//                    WindowInput4.dispose();
//                    WindowInput5.dispose();
//                    WindowInput6.dispose();
//                    dispose();                
//                }
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }else{
        cekObat.setSelected(false);
        WindowInput.dispose();
        WindowInput3.dispose();
        WindowInput4.dispose();
        WindowInput5.dispose();
        WindowInput6.dispose();
        this.dispose();
//        }
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnView, BtnSimpan);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnKeluar, BtnView);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnViewActionPerformed
        Object[] options = {"Tagihan Masuk", "Piutang Pasien"};

        String input;
        int pilih = 0;
        try {
            input = (String) JOptionPane.showInputDialog(null, "Silahkan pilih yang mau ditampilkan!", "Keuangan", JOptionPane.QUESTION_MESSAGE, null, options, "Nota 1");
            switch (input) {
                case "Tagihan Masuk":
                    pilih = 1;
                    break;
                case "Piutang Pasien":
                    pilih = 2;
                    break;
            }
        } catch (Exception e) {
            pilih = 0;
        }

        if (pilih > 0) {
            if (pilih == 1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgLhtBiaya billing = new DlgLhtBiaya(null, false);
                billing.setSize(this.getWidth() - 40, this.getHeight() - 40);
                billing.setLocationRelativeTo(this);
                billing.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else if (pilih == 2) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgLhtPiutang billing = new DlgLhtPiutang(null, false);
                billing.tampil();
                billing.setSize(this.getWidth() - 40, this.getHeight() - 40);
                billing.setLocationRelativeTo(this);
                billing.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_BtnViewActionPerformed

    private void BtnViewKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnViewKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnViewActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnKeluar);
        }
    }//GEN-LAST:event_BtnViewKeyPressed

private void tbBillingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBillingMouseClicked
    if (tabModeRwJlDr.getRowCount() != 0) {
        if (evt.getClickCount() == 1) {
            int kolom = tbBilling.getSelectedColumn();
            if (kolom == 1) {
                try {
                    akses.setform("DLgBilingRanap");
                    if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Rincian Biaya")) {
                        if (akses.gettindakan_ranap() == true) {
                            DlgCariPerawatanRanap perawatan = new DlgCariPerawatanRanap(null, false);
                            perawatan.setNoRm(TNoRw.getText(), "rawat_inap_dr", DTPTgl.getDate(), "00", "00", "00", true, TPasien.getText());
                            perawatan.setPetugas("", "", "", "", "", "", "", "", "", "", "", "", "", "");
                            perawatan.isCek();
                            perawatan.tampil();
                            perawatan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            perawatan.setLocationRelativeTo(internalFrame1);
                            perawatan.setVisible(true);
                        }
                    } else if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Resep Pulang")) {
                        if (akses.getresep_pulang() == true) {
                            DlgInputResepPulang inputresep=new DlgInputResepPulang(null,false);
                            inputresep.setNoRm(TNoRw.getText(), "-", Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?", TNoRw.getText()),
                                    Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat=?", TNoRw.getText()));
                            inputresep.isCek();
                            inputresep.tampil();
                            inputresep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            inputresep.setLocationRelativeTo(internalFrame1);
                            inputresep.setVisible(true);
                        }
                    } else if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Obat & BHP")) {
                        if (akses.getberi_obat() == true) {
                            DlgCariObat2 dlgobt = new DlgCariObat2(null, false);
                            dlgobt.isCek();
                            dlgobt.setNoRm(TNoRw.getText(), DTPTgl.getDate(), "00", "00", "00", true);
                            dlgobt.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            dlgobt.tampil();
                            dlgobt.setLocationRelativeTo(internalFrame1);
                            dlgobt.setVisible(true);
                        }
                    } else if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Tambahan Biaya")) {
                        if (akses.gettambahan_biaya() == true) {
                            MnTambahanActionPerformed(null);
                        }
                    } else if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Potongan Biaya")) {
                        if (akses.getpotongan_biaya() == true) {
                            MnPotonganActionPerformed(null);
                        }
                    }
                } catch (Exception e) {
                    akses.setform("DLgBilingRanap");
                    if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Rincian Biaya")) {
                        if (akses.gettindakan_ranap() == true) {
                            DlgCariPerawatanRanap perawatan = new DlgCariPerawatanRanap(null, false);
                            perawatan.setNoRm(TNoRw.getText(), "rawat_inap_dr", DTPTgl.getDate(), "00", "00", "00", true, TPasien.getText());
                            perawatan.setPetugas("", "", "", "", "", "", "", "", "", "", "", "", "", "");
                            perawatan.isCek();
                            perawatan.tampil();
                            perawatan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            perawatan.setLocationRelativeTo(internalFrame1);
                            perawatan.setVisible(true);
                        }
                    } else if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Resep Pulang")) {
                        if (akses.getresep_pulang() == true) {
                            DlgInputResepPulang inputresep=new DlgInputResepPulang(null,false);
                            inputresep.setNoRm(TNoRw.getText(), "-", Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?", TNoRw.getText()),
                                    Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat=?", TNoRw.getText()));
                            inputresep.isCek();
                            inputresep.tampil();
                            inputresep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            inputresep.setLocationRelativeTo(internalFrame1);
                            inputresep.setVisible(true);
                        }
                    } else if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Obat & BHP")) {
                        if (akses.getberi_obat() == true) {
                            DlgCariObat2 dlgobt = new DlgCariObat2(null, false);
                            dlgobt.isCek();
                            dlgobt.setNoRm(TNoRw.getText(), DTPTgl.getDate(), "00", "00", "00", true);
                            dlgobt.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            dlgobt.tampil();
                            dlgobt.setLocationRelativeTo(internalFrame1);
                            dlgobt.setVisible(true);
                        }
                    } else if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Tambahan Biaya")) {
                        if (akses.gettambahan_biaya() == true) {
                            MnTambahanActionPerformed(null);
                        }
                    } else if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Potongan Biaya")) {
                        if (akses.getpotongan_biaya() == true) {
                            MnPotonganActionPerformed(null);
                        }
                    }
                }

            }
        }
    }
}//GEN-LAST:event_tbBillingMouseClicked

private void tbBillingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBillingKeyPressed
    if (tbBilling.getRowCount() != 0) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            i = tbBilling.getSelectedColumn();
            if (i == 6) {
                if (akses.getbilling_ranap() == true) {
                    try {
                        switch (tbBilling.getValueAt(tbBilling.getSelectedRow(), 8).toString()) {
                            case "Laborat":
                                JOptionPane.showMessageDialog(rootPane, "Maaf, untuk tambahan/potongan laborat gunakan pada Tambahan/Potongan Biaya");
                                isRawat();
                                break;
                            case "Operasi":
                                JOptionPane.showMessageDialog(rootPane, "Maaf, untuk tambahan/potongan Operasi gunakan pada Tambahan/Potongan Biaya");
                                isRawat();
                                break;
                            case "Obat":
                                JOptionPane.showMessageDialog(rootPane, "Maaf, untuk tambahan obat hanya bisa diisi embalase. Gunakan Tambahan Biaya jika ingin tambahan lain");
                                isRawat();
                                break;
                            default:
                                //if(chkPj1.isVisible()==true){
                                //    JOptionPane.showMessageDialog(rootPane,"Maaf, untuk tambahan gunakan pada Tambahan Biaya");
                                //    isRawat();
                                //}else{
                                try {
                                if (Double.parseDouble(tbBilling.getValueAt(tbBilling.getSelectedRow(), 6).toString()) != 0) {
                                    Sequel.queryu2("delete from temporary_tambahan_potongan where no_rawat=? and nama_tambahan=? and status=?", 3, new String[]{
                                        TNoRw.getText(), tbBilling.getValueAt(tbBilling.getSelectedRow(), 2).toString(), tbBilling.getValueAt(tbBilling.getSelectedRow(), 8).toString()
                                    });
                                    Sequel.menyimpan("temporary_tambahan_potongan", "?,?,?,?", 4, new String[]{
                                        TNoRw.getText(), tbBilling.getValueAt(tbBilling.getSelectedRow(), 2).toString(), tbBilling.getValueAt(tbBilling.getSelectedRow(), 6).toString(),
                                        tbBilling.getValueAt(tbBilling.getSelectedRow(), 8).toString()
                                    });
                                } else {
                                    Sequel.queryu2("delete from temporary_tambahan_potongan where no_rawat=? and nama_tambahan=? and status=?", 3, new String[]{
                                        TNoRw.getText(), tbBilling.getValueAt(tbBilling.getSelectedRow(), 2).toString(), tbBilling.getValueAt(tbBilling.getSelectedRow(), 8).toString()
                                    });
                                    tbBilling.setValueAt(0, tbBilling.getSelectedRow(), 0);
                                }
                            } catch (Exception e) {
                                Sequel.queryu2("delete from temporary_tambahan_potongan where no_rawat=? and nama_tambahan=? and status=?", 3, new String[]{
                                    TNoRw.getText(), tbBilling.getValueAt(tbBilling.getSelectedRow(), 2).toString(), tbBilling.getValueAt(tbBilling.getSelectedRow(), 8).toString()
                                });
                                tbBilling.setValueAt(0, tbBilling.getSelectedRow(), 0);
                            }

                            isRawat();
                            //}                                        
                            break;
                        }
                    } catch (Exception e) {
                        isRawat();
                    }
                }
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            int kolom = tbBilling.getSelectedColumn();
            if (kolom == 1) {
                try {
                    akses.setform("DLgBilingRanap");
                    if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Rincian Biaya")) {
                        if (akses.gettindakan_ranap() == true) {
                            DlgCariPerawatanRanap perawatan = new DlgCariPerawatanRanap(null, false);
                            perawatan.setNoRm(TNoRw.getText(), "rawat_inap_dr", DTPTgl.getDate(), "00", "00", "00", true, TPasien.getText());
                            perawatan.setPetugas("", "", "", "", "", "", "", "", "", "", "", "", "", "");
                            perawatan.isCek();
                            perawatan.tampil();
                            perawatan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            perawatan.setLocationRelativeTo(internalFrame1);
                            perawatan.setVisible(true);
                        }
                    } else if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Resep Pulang")) {
                        if (akses.getresep_pulang() == true) {
                            DlgInputResepPulang inputresep=new DlgInputResepPulang(null,false);
                            inputresep.setNoRm(TNoRw.getText(), "-", Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?", TNoRw.getText()),
                                    Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat=?", TNoRw.getText()));
                            inputresep.isCek();
                            inputresep.tampil();
                            inputresep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            inputresep.setLocationRelativeTo(internalFrame1);
                            inputresep.setVisible(true);
                        }
                    } else if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Obat & BHP")) {
                        if (akses.getberi_obat() == true) {
                            DlgCariObat2 dlgobt = new DlgCariObat2(null, false);
                            dlgobt.isCek();
                            dlgobt.setNoRm(TNoRw.getText(), DTPTgl.getDate(), "00", "00", "00", true);
                            dlgobt.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            dlgobt.tampil();
                            dlgobt.setLocationRelativeTo(internalFrame1);
                            dlgobt.setVisible(true);
                        }
                    } else if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Tambahan Biaya")) {
                        if (akses.gettambahan_biaya() == true) {
                            MnTambahanActionPerformed(null);
                        }
                    } else if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Potongan Biaya")) {
                        if (akses.getpotongan_biaya() == true) {
                            MnPotonganActionPerformed(null);
                        }
                    }
                } catch (Exception e) {
                    akses.setform("DLgBilingRanap");
                    if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Rincian Biaya")) {
                        if (akses.gettindakan_ranap() == true) {
                            DlgCariPerawatanRanap perawatan = new DlgCariPerawatanRanap(null, false);
                            perawatan.setNoRm(TNoRw.getText(), "rawat_inap_dr", DTPTgl.getDate(), "00", "00", "00", true, TPasien.getText());
                            perawatan.setPetugas("", "", "", "", "", "", "", "", "", "", "", "", "", "");
                            perawatan.isCek();
                            perawatan.tampil();
                            perawatan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            perawatan.setLocationRelativeTo(internalFrame1);
                            perawatan.setVisible(true);
                        }
                    } else if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Resep Pulang")) {
                        if (akses.getresep_pulang() == true) {
                            DlgInputResepPulang inputresep=new DlgInputResepPulang(null,false);
                            inputresep.setNoRm(TNoRw.getText(), "-", Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?", TNoRw.getText()),
                                    Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat=?", TNoRw.getText()));
                            inputresep.isCek();
                            inputresep.tampil();
                            inputresep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            inputresep.setLocationRelativeTo(internalFrame1);
                            inputresep.setVisible(true);
                        }
                    } else if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Obat & BHP")) {
                        if (akses.getberi_obat() == true) {
                            DlgCariObat2 dlgobt = new DlgCariObat2(null, false);
                            dlgobt.isCek();
                            dlgobt.setNoRm(TNoRw.getText(), DTPTgl.getDate(), "00", "00", "00", true);
                            dlgobt.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            dlgobt.tampil();
                            dlgobt.setLocationRelativeTo(internalFrame1);
                            dlgobt.setVisible(true);
                        }
                    } else if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Tambahan Biaya")) {
                        if (akses.gettambahan_biaya() == true) {
                            MnTambahanActionPerformed(null);
                        }
                    } else if (tbBilling.getValueAt(tbBilling.getSelectedRow(), kolom).toString().contains("Potongan Biaya")) {
                        if (akses.getpotongan_biaya() == true) {
                            MnPotonganActionPerformed(null);
                        }
                    }
                }
            }
        }
    }
}//GEN-LAST:event_tbBillingKeyPressed

private void MnRawatJalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRawatJalanActionPerformed
    Sequel.cariIsi("select d.nm_dokter from reg_periksa r inner join dokter d where r.no_rawat=? ", nmdokter, TNoRw.getText());
    Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=? ", kddokter, TNoRw.getText());

    if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
        TNoRw.requestFocus();
    } else {
        DlgRawatJalan dlgrwjl = new DlgRawatJalan(null, false);
        dlgrwjl.isCek();
        dlgrwjl.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dlgrwjl.setLocationRelativeTo(internalFrame1);

        dlgrwjl.setNoRm(TNoRw.getText(), DTPTgl.getDate(), new Date());
        dlgrwjl.tampilDrPr();
        dlgrwjl.TotalNominal();
        dlgrwjl.setVisible(true);
        dlgrwjl.fokus();
    }
}//GEN-LAST:event_MnRawatJalanActionPerformed

private void MnInputResepPulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputResepPulangActionPerformed
    if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
        TNoRw.requestFocus();
    } else {
        DlgInputResepPulang inputresep=new DlgInputResepPulang(null,false);
        akses.setform("DLgBilingRanap");
        inputresep.isCek();
        inputresep.setNoRm(TNoRw.getText(), "-", Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?", TNoRw.getText()),
                Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat=?", TNoRw.getText()));
        inputresep.tampil();
        inputresep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        inputresep.setLocationRelativeTo(internalFrame1);
        inputresep.setVisible(true);
    }
}//GEN-LAST:event_MnInputResepPulangActionPerformed

private void MnInputObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputObatActionPerformed
    if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
        TNoRw.requestFocus();
    } else {
        if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
        } else {
            DlgCariObat2 dlgobt = new DlgCariObat2(null, false);
            akses.setform("DLgBilingRanap");
            dlgobt.isCek();
            dlgobt.setNoRm(TNoRw.getText(), DTPTgl.getDate(), "00", "00", "00", true);
            dlgobt.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            dlgobt.tampil();
            dlgobt.setLocationRelativeTo(internalFrame1);
            dlgobt.setVisible(true);
        }

    }
}//GEN-LAST:event_MnInputObatActionPerformed

private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
    Valid.pindah(evt, TNoRw, BtnSimpan);
}//GEN-LAST:event_DTPTglKeyPressed

private void MnHapusTagihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTagihanActionPerformed
//    JOptionPane.showMessageDialog(null, tbBilling.getValueAt(tbBilling.getSelectedRow(),2).toString());
    try {
        i = 0;
        pscekbilling = koneksi.prepareStatement(sqlpscekbilling);
        try {
            pscekbilling.setString(1, TNoRw.getText());
            rscekbilling = pscekbilling.executeQuery();
            if (rscekbilling.next()) {
                i = rscekbilling.getInt(1);
            }
        } catch (Exception e) {
            i = 0;
            System.out.println("Notifikasi : " + e);
        } finally {
            if (rscekbilling != null) {
                rscekbilling.close();
            }
            if (pscekbilling != null) {
                pscekbilling.close();
            }
        }
        if (i > 0) {
            Sequel.AutoComitFalse();
            Valid.editTable(tabModeRwJlDr, "reg_periksa", "no_rawat", TNoRw, "stts='Sudah'");

            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            Sequel.queryu2("delete from tampjurnal");

            if ((-1 * ttlPotongan) > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Potongan_Ranap + "','Potongan Ranap','0','" + (-1 * ttlPotongan) + "'", "Rekening");
            }

            if ((-1 * ttlRetur_Obat) > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Retur_Obat_Ranap + "','Retur Obat Ranap','0','" + (-1 * ttlRetur_Obat) + "'", "Rekening");
            }

            if (ttlRegistrasi > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Registrasi_Ranap + "','Registrasi Ranap','" + ttlRegistrasi + "','0'", "Rekening");
            }

            if (ttlTambahan > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Tambahan_Ranap + "','Tambahan Ranap','" + ttlTambahan + "','0'", "Rekening");
            }

            if (ttlResep_Pulang > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Resep_Pulang_Ranap + "','Resep Pulang Ranap','" + ttlResep_Pulang + "','0'", "Rekening");
            }

            if (ttlKamar > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Kamar_Inap + "','Kamar Inap','" + ttlKamar + "','0'", "Rekening");
            }

            if (ttlHarian > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Harian_Ranap + "','Harian Ranap','" + ttlHarian + "','0'", "Rekening");
            }

            if ((ttlRanap_Dokter + ttlRanap_Paramedis + ttlRalan_Dokter + ttlRalan_Paramedis) > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Tindakan_Ranap + "','Tindakan Ranap','" + (ttlRanap_Dokter + ttlRanap_Paramedis + ttlRalan_Dokter + ttlRalan_Paramedis) + "','0'",
                        "debet=debet+" + (ttlRanap_Dokter + ttlRanap_Paramedis + ttlRalan_Dokter + ttlRalan_Paramedis), "kd_rek='" + Tindakan_Ranap + "'");
            }

            if (ttlLaborat > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Laborat_Ranap + "','Laborat','" + ttlLaborat + "','0'",
                        "debet=debet+" + ttlLaborat, "kd_rek='" + Laborat_Ranap + "'");
            }

            if (ttlRadiologi > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Radiologi_Ranap + "','Radiologi','" + ttlRadiologi + "','0'",
                        "debet=debet+" + ttlRadiologi, "kd_rek='" + Radiologi_Ranap + "'");
            }

            if (ttlObat > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Obat_Ranap + "','Obat','" + ttlObat + "','0'",
                        "debet=debet+" + ttlObat, "kd_rek='" + Obat_Ranap + "'");
            }

            if (ttlOperasi > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Operasi_Ranap + "','Operasi','" + ttlOperasi + "','0'",
                        "debet=debet+" + ttlOperasi, "kd_rek='" + Operasi_Ranap + "'");
            }

            if (uangdeposit > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Uang_Muka_Ranap + "','Kontra Akun Uang Muka','0','" + uangdeposit + "'",
                        "kredit=kredit+" + uangdeposit, "kd_rek='" + Uang_Muka_Ranap + "'");
            }

            if (ttlService > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Service_Ranap + "','Biaya Service Ranap','" + ttlService + "','0'", "Rekening");
            }

            psakunbayar = koneksi.prepareStatement(
                    "select akun_bayar.nama_bayar,akun_bayar.kd_rek,detail_nota_inap.besar_bayar,"
                    + "akun_bayar.ppn,detail_nota_inap.besarppn from akun_bayar inner join detail_nota_inap "
                    + "on akun_bayar.nama_bayar=detail_nota_inap.nama_bayar where detail_nota_inap.no_rawat=? order by nama_bayar");
            try {
                psakunbayar.setString(1, TNoRw.getText());
                rsakunbayar = psakunbayar.executeQuery();
                while (rsakunbayar.next()) {
                    Sequel.menyimpan("tampjurnal", "'" + rsakunbayar.getString("kd_rek") + "','" + rsakunbayar.getString("nama_bayar") + "','0','" + rsakunbayar.getString("besar_bayar") + "'",
                            "kredit=kredit+" + rsakunbayar.getString("besar_bayar"), "kd_rek='" + rsakunbayar.getString("kd_rek") + "'");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi Akun Bayar Tersimpan : " + e);
            } finally {
                if (rsakunbayar != null) {
                    rsakunbayar.close();
                }
                if (psakunbayar != null) {
                    psakunbayar.close();
                }
            }

            psakunpiutang = koneksi.prepareStatement(
                    "select akun_piutang.nama_bayar,akun_piutang.kd_rek,akun_piutang.kd_pj, "
                    + "detail_piutang_pasien.totalpiutang,date_format(detail_piutang_pasien.tgltempo,'%d/%m/%Y') from "
                    + "akun_piutang inner join detail_piutang_pasien on akun_piutang.nama_bayar=detail_piutang_pasien.nama_bayar "
                    + "where detail_piutang_pasien.no_rawat=? order by nama_bayar");
            try {
                psakunpiutang.setString(1, TNoRw.getText());
                rsakunpiutang = psakunpiutang.executeQuery();
                while (rsakunpiutang.next()) {
                    Sequel.menyimpan("tampjurnal", "'" + rsakunpiutang.getString("kd_rek") + "','" + rsakunpiutang.getString("nama_bayar") + "','0','" + rsakunpiutang.getString("totalpiutang") + "'",
                            "kredit=kredit+" + rsakunpiutang.getString("totalpiutang"), "kd_rek='" + rsakunpiutang.getString("kd_rek") + "'");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi Akun Piutang Tersimpan : " + e);
            } finally {
                if (rsakunpiutang != null) {
                    rsakunpiutang.close();
                }
                if (psakunpiutang != null) {
                    psakunpiutang.close();
                }
            }

            if (piutang > 0) {
                jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "PEMBATALAN PIUTANG PASIEN RAWAT INAP, DIBATALKAN OLEH " + akses.getkode());
            } else if (piutang <= 0) {
                jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "PEMBATALAN PEMBAYARAN PASIEN RAWAT INAP, DIBATALKAN OLEH " + akses.getkode());
            }

            Sequel.queryu2("delete from piutang_pasien where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu2("delete from detail_piutang_pasien where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu2("delete from nota_inap where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu2("delete from detail_nota_inap where no_rawat='" + TNoRw.getText() + "'");
            Valid.hapusTable(tabModeRwJlDr, TNoRw, "billing", "no_rawat");
//            Valid.hapusTable(tabModeRwJlDr, TNoRw, "tagihan_sadewa", "no_nota");
            this.setCursor(Cursor.getDefaultCursor());
            Sequel.AutoComitTrue();
            JOptionPane.showMessageDialog(rootPane, "Proses hapus data Nota Salah selesai..!!");
            cekLR.setSelected(true);
            Valid.tabelKosong(tabModeAkunBayar);
            Valid.tabelKosong(tabModeAkunPiutang);
            isRawat();
        } else if (i <= 0) {
            JOptionPane.showMessageDialog(rootPane, "Data belum pernah disimpan/diverifikasi. "
                    + "Tidak perlu ada penghapusan data salah..!!");
        }
    } catch (Exception e) {
        System.out.println("Notifikasi : " + e);
    }
}//GEN-LAST:event_MnHapusTagihanActionPerformed

private void MnPeriksaLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaLabActionPerformed
    cekdokter = "";
    diagnosa_ok = "";
    diagnosa_cek = 0;
    diagnosa_cek = Sequel.cariInteger("select count(1) cek from kamar_inap where no_rawat='" + TNoRw.getText() + "'");
    cekdokter = Sequel.cariIsi("select kd_dokter from dpjp_ranap where no_rawat='" + TNoRw.getText() + "'");

    if (diagnosa_cek == 0) {
        diagnosa_ok = "-";
    } else {
        diagnosa_ok = Sequel.cariIsi("select diagnosa_awal from kamar_inap where no_rawat='" + TNoRw.getText() + "'");
    }

    if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
    } else {
        akses.setform("DLgBilingRanap");
        DlgPeriksaLaboratorium periksalab = new DlgPeriksaLaboratorium(null, false);
        periksalab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        periksalab.setLocationRelativeTo(internalFrame1);
        periksalab.emptTeks();
        periksalab.setNoRm(TNoRw.getText(), "Ranap", "-", diagnosa_ok, Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar='" + kdkamar + "'"));
        periksalab.KodePerujuk.setText(cekdokter);
        periksalab.tampiltarif();
        periksalab.tampil();
        periksalab.isCek();
        periksalab.setVisible(true);
        periksalab.fokus();
    }
}//GEN-LAST:event_MnPeriksaLabActionPerformed

private void MnObatLangsungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnObatLangsungActionPerformed
    TotalObat.setText(Sequel.cariIsi("select besar_tagihan from tagihan_obat_langsung where no_rawat=?", TNoRw.getText()));
    WindowInput.setSize(590, 80);
    WindowInput.setLocationRelativeTo(internalFrame1);
    WindowInput.setVisible(true);
}//GEN-LAST:event_MnObatLangsungActionPerformed

private void TotalObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TotalObatKeyPressed
    Valid.pindah(evt, BtnCloseIn, BtnSimpan);
}//GEN-LAST:event_TotalObatKeyPressed

private void BtnCloseInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseInActionPerformed
    WindowInput.dispose();
}//GEN-LAST:event_BtnCloseInActionPerformed

private void BtnCloseInKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseInKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        WindowInput.dispose();
    } else {
        Valid.pindah(evt, BtnBatal1, TotalObat);
    }
}//GEN-LAST:event_BtnCloseInKeyPressed

private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
    if (TNoRw.getText().trim().equals("")) {
        Valid.textKosong(TNoRw, "No.Rawat");
    } else {
        Sequel.menyimpan("tagihan_obat_langsung", "'" + TNoRw.getText() + "','" + TotalObat.getText() + "'", "No.Rawat");
        WindowInput.setVisible(false);
        isRawat();
        isKembali();
    }
}//GEN-LAST:event_BtnSimpan2ActionPerformed

private void BtnSimpan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan2KeyPressed
    Valid.pindah(evt, TotalObat, BtnBatal1);
}//GEN-LAST:event_BtnSimpan2KeyPressed

private void BtnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal1ActionPerformed
    if (TNoRw.getText().trim().equals("")) {
        Valid.textKosong(TNoRw, "No.Rawat");
    } else {
        Sequel.queryu("delete from tagihan_obat_langsung where no_rawat=? ", TNoRw.getText());
        WindowInput.setVisible(false);
        isRawat();
        isKembali();
    }
}//GEN-LAST:event_BtnBatal1ActionPerformed

private void BtnBatal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatal1KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnBatal1ActionPerformed(null);
    } else {
        Valid.pindah(evt, BtnSimpan, BtnCloseIn);
    }
}//GEN-LAST:event_BtnBatal1KeyPressed

private void norawattambahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norawattambahanKeyPressed
    Valid.pindah(evt, BtnKeluar, BtnSimpan);
}//GEN-LAST:event_norawattambahanKeyPressed

private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
    tabModeTambahan.addRow(new Object[]{"", ""});
}//GEN-LAST:event_BtnTambahActionPerformed

private void BtnSimpan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan3ActionPerformed
    if (norawattambahan.getText().trim().equals("") || (tbTambahan.getRowCount() <= 0)) {
        Valid.textKosong(norawattambahan, "Data");
    } else {
        for (int r = 0; r < tbTambahan.getRowCount(); r++) {
            if (Valid.SetAngka(tbTambahan.getValueAt(r, 1).toString()) > 0) {
                Sequel.menyimpan("tambahan_biaya", "'" + norawattambahan.getText() + "','" + tbTambahan.getValueAt(r, 0).toString()
                        + "','" + tbTambahan.getValueAt(r, 1).toString() + "','" + akses.getkode() + "',"
                        + "'" + Sequel.cariIsi("SELECT DATE(NOW())") + "','" + Sequel.cariIsi("SELECT TIME(NOW())") + "'", "Tambahan Biaya");
            }
        }
        isRawat();
        isKembali();
        WindowInput3.dispose();
    }
}//GEN-LAST:event_BtnSimpan3ActionPerformed

private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
    Sequel.queryu("delete from tambahan_biaya where no_rawat='" + norawattambahan.getText()
            + "' and nama_biaya='" + tbTambahan.getValueAt(tbTambahan.getSelectedRow(), 0).toString() + "'");
    tabModeTambahan.removeRow(tbTambahan.getSelectedRow());
    isRawat();
    isKembali();
}//GEN-LAST:event_BtnHapusActionPerformed

private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
    WindowInput3.dispose();
}//GEN-LAST:event_BtnKeluar1ActionPerformed

private void MnTambahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTambahanActionPerformed
    norawattambahan.setText(TNoRw.getText());
    tampilTambahan(norawattambahan.getText());
    WindowInput3.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    WindowInput3.setLocationRelativeTo(internalFrame1);
    WindowInput3.setVisible(true);
}//GEN-LAST:event_MnTambahanActionPerformed

private void MnReturJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnReturJualActionPerformed
    if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
        JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
    } else {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("DlgBilingRanap");
        DlgReturJual returjual = new DlgReturJual(null, false);
        returjual.emptTeks();
        returjual.isCek();
        returjual.setPasien(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRw.getText()), TNoRw.getText());
        returjual.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        returjual.setLocationRelativeTo(internalFrame1);
        returjual.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());

    }


}//GEN-LAST:event_MnReturJualActionPerformed

private void norawatpotonganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norawatpotonganKeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_norawatpotonganKeyPressed

private void BtnTambahPotonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahPotonganActionPerformed
    tabModePotongan.addRow(new Object[]{"", ""});
}//GEN-LAST:event_BtnTambahPotonganActionPerformed

private void BtnSimpanPotonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanPotonganActionPerformed
    if (norawatpotongan.getText().trim().equals("") || (tbPotongan.getRowCount() <= 0)) {
        Valid.textKosong(norawatpotongan, "Data");
    } else {
        Sequel.queryu("delete from pengurangan_biaya where no_rawat='" + norawatpotongan.getText() + "'");
        for (int r = 0; r < tbPotongan.getRowCount(); r++) {
            if (Valid.SetAngka(tbPotongan.getValueAt(r, 1).toString()) > 0) {
                Sequel.menyimpanInsertIgnore("pengurangan_biaya", "'" + norawatpotongan.getText() + "','" + tbPotongan.getValueAt(r, 0).toString()
                        + "','" + tbPotongan.getValueAt(r, 1).toString() + "'", "Potongan Biaya");
            }
        }
        isRawat();
        isKembali();
        WindowInput4.dispose();
    }
}//GEN-LAST:event_BtnSimpanPotonganActionPerformed

private void BtnHapusPotonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusPotonganActionPerformed
    Sequel.queryu("delete from pengurangan_biaya where no_rawat='" + norawatpotongan.getText()
            + "' and nama_pengurangan='" + tbPotongan.getValueAt(tbPotongan.getSelectedRow(), 0).toString() + "'");
    tabModePotongan.removeRow(tbPotongan.getSelectedRow());
    isRawat();
    isKembali();
}//GEN-LAST:event_BtnHapusPotonganActionPerformed

private void BtnKeluarPotonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarPotonganActionPerformed
    WindowInput4.dispose();
}//GEN-LAST:event_BtnKeluarPotonganActionPerformed

private void MnPotonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPotonganActionPerformed
    norawatpotongan.setText(TNoRw.getText());
    tampilPotongan(norawatpotongan.getText());
    WindowInput4.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    WindowInput4.setLocationRelativeTo(internalFrame1);
    WindowInput4.setVisible(true);
}//GEN-LAST:event_MnPotonganActionPerformed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    isRawat();
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnCariActionPerformed(null);
    } else {
        Valid.pindah(evt, BtnSimpan, BtnKeluar);
    }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        data_a = Sequel.cariInteger("SELECT count(-1) FROM kamar_inap WHERE no_rawat='" + TNoRw.getText() + "' and tgl_keluar='0000-00-00' and stts_pulang='-'");

        if (data_a == 1) {
            JOptionPane.showMessageDialog(null, "Maaf tanggal pulang pasien belum diisi, proses pembayaran tidak tersimpan & belum bisa dilanjutkan...");
        } else {
            int jawab = JOptionPane.showConfirmDialog(null, "Apakah sudah mengambil resep terakhir ? Mohon konfirmasi ulang kepada pasien", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (jawab == JOptionPane.YES_OPTION) {
                try {
                    pscekbilling = koneksi.prepareStatement(sqlpscekbilling);
                    try {
                        pscekbilling.setString(1, TNoRw.getText());
                        rscekbilling = pscekbilling.executeQuery();
                        if (rscekbilling.next()) {
                            i = rscekbilling.getInt(1);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rscekbilling != null) {
                            rscekbilling.close();
                        }
                        if (pscekbilling != null) {
                            pscekbilling.close();
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

                if (TNoRw.getText().trim().equals("") || TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                    Valid.textKosong(TNoRw, "Pasien");
                } else if (i > 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data tagihan pasien dengan No.Rawat tersebut sudah pernah disimpan...!!!");
                } else if (i == 0) {
                    if (piutang <= 0) {
                        if (kekurangan < 0) {
                            JOptionPane.showMessageDialog(null, "Maaf, pembayaran pasien masih kurang ...!!!");
                        } else if (kekurangan > 0) {
                            if (countbayar > 1) {
                                JOptionPane.showMessageDialog(null, "Maaf, kembali harus bernilai 0 untuk cara bayar lebih dari 1...!!!");
                            } else {
                                if (ChkPiutang.isSelected() == true) {
                                    JOptionPane.showMessageDialog(null, "Transaksi belum tersimpan, hilangkan dulu centang di PIUTANG, kemudian simpan ulang...!!!");
                                } else {
                                    isSimpan();
                                }
                            }
                        } else if (kekurangan == 0) {
                            if (ChkPiutang.isSelected() == true) {
                                JOptionPane.showMessageDialog(null, "Transaksi belum tersimpan, hilangkan dulu centang di PIUTANG, kemudian simpan ulang...!!!");
                            } else {
                                isSimpan();
                            }
                        }
                    } else if (piutang >= 1) {
                        if (ChkPiutang.isSelected() == true) {
                            cmbPenjamin.setEnabled(true);
                            TketPenjamin.setEnabled(true);
                            
                            if (kekurangan < 0) {
                                JOptionPane.showMessageDialog(null, "Maaf, piutang belum genap. Silahkan isi di jumlah piutang ...!!!");
                            } else if (kekurangan > 0) {
                                JOptionPane.showMessageDialog(null, "Maaf, terjadi kelebihan piutang ...!!!");
                            } else {
                                isSimpan();
                            }
                        } else if (ChkPiutang.isSelected() == false) {
                            JOptionPane.showMessageDialog(rootPane, "Silahkan centang terlebih dahulu pada pilihan piutang...!!");
                        }
                    }
                }
            } else {
                WindowInput.dispose();
                WindowInput3.dispose();
                WindowInput4.dispose();
                WindowInput5.dispose();
                WindowInput6.dispose();
                dispose();
            }
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void norawatubahlamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norawatubahlamaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_norawatubahlamaKeyPressed

    private void BtnSimpanUbahLamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanUbahLamaActionPerformed
        if (norawatubahlama.getText().trim().equals("") || (tbUbahLama.getRowCount() <= 0)) {
            Valid.textKosong(norawatubahlama, "Data");
        } else {
            Sequel.AutoComitFalse();
            for (int r = 0; r < tbUbahLama.getRowCount(); r++) {
                if (Valid.SetAngka(tbUbahLama.getValueAt(r, 6).toString()) > -1) {
                    Sequel.mengedit("kamar_inap", "no_rawat='" + norawatubahlama.getText() + "' and kd_kamar='" + tbUbahLama.getValueAt(r, 0) + "'",
                            "tgl_keluar='" + tbUbahLama.getValueAt(r, 4).toString() + "',jam_keluar='" + tbUbahLama.getValueAt(r, 5).toString() + "',"
                            + "tgl_masuk='" + tbUbahLama.getValueAt(r, 2).toString() + "',jam_masuk='" + tbUbahLama.getValueAt(r, 3).toString() + "',"
                            + "lama='" + tbUbahLama.getValueAt(r, 6).toString() + "',"
                            + "ttl_biaya=" + tbUbahLama.getValueAt(r, 6).toString() + "*trf_kamar");
                }
            }
            Sequel.AutoComitTrue();

            isRawat();
            isKembali();
            WindowInput5.dispose();
        }
    }//GEN-LAST:event_BtnSimpanUbahLamaActionPerformed

    private void BtnKeluarUbahLamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarUbahLamaActionPerformed
        WindowInput5.dispose();
    }//GEN-LAST:event_BtnKeluarUbahLamaActionPerformed

    private void MnUbahLamaInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUbahLamaInapActionPerformed
        norawatubahlama.setText(TNoRw.getText());
        tampilUbahLama(norawatubahlama.getText());
        WindowInput5.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        WindowInput5.setLocationRelativeTo(internalFrame1);
        WindowInput5.setAlwaysOnTop(false);
        WindowInput5.setVisible(true);
    }//GEN-LAST:event_MnUbahLamaInapActionPerformed

    private void MnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenjabActionPerformed
        Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=? ", kdpenjab, TNoRw.getText());
        Sequel.cariIsi("select png_jawab from penjab where kd_pj=? ", nmpenjab, kdpenjab.getText());

        WindowInput6.setSize(630, 80);
        WindowInput6.setLocationRelativeTo(internalFrame1);
        WindowInput6.setAlwaysOnTop(false);
        WindowInput6.setVisible(true);
        btnPenjab.requestFocus();
    }//GEN-LAST:event_MnPenjabActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowInput6.dispose();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnSimpan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan4ActionPerformed
        if (kdpenjab.getText().trim().equals("") || nmpenjab.getText().trim().equals("")) {
            Valid.textKosong(kdpenjab, "Jenis Bayar");
        } else {
            Sequel.mengedit("reg_periksa", "no_rawat=?", " kd_pj=?", 2, new String[]{kdpenjab.getText(), TNoRw.getText()});
            isRawat();
            WindowInput6.dispose();
        }
    }//GEN-LAST:event_BtnSimpan4ActionPerformed

    private void kdpenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpenjabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", nmpenjab, kdpenjab.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnPenjabActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCloseIn4, BtnSimpan4);
        }
    }//GEN-LAST:event_kdpenjabKeyPressed

    private void btnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjabActionPerformed
        akses.setform("DLgBilingRanap");
        penjab.isCek();
        penjab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penjab.setLocationRelativeTo(internalFrame1);
        penjab.setAlwaysOnTop(false);
        penjab.setVisible(true);
    }//GEN-LAST:event_btnPenjabActionPerformed

    private void BtnSeek2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek2ActionPerformed
        akses.setform("DLgBilingRanap");
        deposit.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        deposit.setLocationRelativeTo(internalFrame1);
        deposit.isCek();
        deposit.setNoRm(TNoRw.getText(), DTPTgl.getDate());
        deposit.setAlwaysOnTop(false);
        deposit.setVisible(true);
    }//GEN-LAST:event_BtnSeek2ActionPerformed

    private void MnDataObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataObatActionPerformed
        DlgPemberianObat beriobat = new DlgPemberianObat(null, false);
        akses.setform("DLgBilingRanap");
        beriobat.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        beriobat.setLocationRelativeTo(internalFrame1);
        beriobat.isCek();
        beriobat.setNoRm(TNoRw.getText(), DTPTgl.getDate(), new Date(), "ranap");
        beriobat.setVisible(true);
    }//GEN-LAST:event_MnDataObatActionPerformed

    private void MnDataResepPulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataResepPulangActionPerformed
        DlgResepPulang reseppulang = new DlgResepPulang(null, false);
        akses.setform("DLgBilingRanap");        
        reseppulang.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        reseppulang.setLocationRelativeTo(internalFrame1);
        reseppulang.isCek();
        reseppulang.setNoRm(TNoRw.getText(), DTPTgl.getDate(), new Date());
        reseppulang.setVisible(true);
    }//GEN-LAST:event_MnDataResepPulangActionPerformed

    private void MnCariPeriksaLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCariPeriksaLabActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        } else {
            DlgCariPeriksaLab cperiksalab = new DlgCariPeriksaLab(null, false);
            cperiksalab.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            cperiksalab.setLocationRelativeTo(internalFrame1);
            cperiksalab.SetNoRw(TNoRw.getText());
            cperiksalab.setAlwaysOnTop(false);
            cperiksalab.setVisible(true);
        }
    }//GEN-LAST:event_MnCariPeriksaLabActionPerformed

    private void MnPeriksaRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaRadiologiActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        } else {
            akses.setform("DLgBilingRanap");
            DlgPeriksaRadiologi periksarad = new DlgPeriksaRadiologi(null, false);
            periksarad.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            periksarad.setLocationRelativeTo(internalFrame1);
            periksarad.emptTeks();
            periksarad.setNoRm(TNoRw.getText(), "Ranap");
            periksarad.tampil();
            periksarad.isCek();
            periksarad.setAlwaysOnTop(false);
            periksarad.setVisible(true);
        }
    }//GEN-LAST:event_MnPeriksaRadiologiActionPerformed

    private void MnCariRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCariRadiologiActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        } else {
            DlgCariPeriksaRadiologi cperiksalab = new DlgCariPeriksaRadiologi(null, false);
            cperiksalab.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            cperiksalab.setLocationRelativeTo(internalFrame1);
            cperiksalab.SetNoRw(TNoRw.getText());
            cperiksalab.setAlwaysOnTop(false);
            cperiksalab.setVisible(true);
        }
    }//GEN-LAST:event_MnCariRadiologiActionPerformed

    private void BtnNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotaActionPerformed
        data_a = Sequel.cariInteger("SELECT count(-1) FROM kamar_inap WHERE no_rawat='" + TNoRw.getText() + "' and tgl_keluar='0000-00-00' and stts_pulang='-'");
        i = Sequel.cariInteger("select count(billing.no_rawat) from billing where billing.no_rawat=?", TNoRw.getText());

        if (TNoRw.getText().trim().equals("") || TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (tbBilling.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        } else if (data_a == 1) {
            JOptionPane.showMessageDialog(null, "Maaf proses pembayaran gagal, tanggal pulang pasien belum diisi & nota pembayaran belum bisa dicetak...");
        } else if (tbBilling.getRowCount() != 0) {
            try {
                koneksi.setAutoCommit(false);
                Sequel.queryu2("delete from temporary_bayar_ranap");
                for (i = 0; i < tbBilling.getRowCount(); i++) {
                    if (tbBilling.getValueAt(i, 0).toString().equals("true")) {
                        biaya = "";
                        try {
                            biaya = Valid.SetAngka(Double.parseDouble(tbBilling.getValueAt(i, 4).toString()));
                        } catch (Exception e) {
                            biaya = "";
                        }
                        tambahan = "";
                        try {
                            tambahan = Valid.SetAngka(Double.parseDouble(tbBilling.getValueAt(i, 6).toString()));
                        } catch (Exception e) {
                            tambahan = "";
                        }
                        totals = "";
                        try {
                            totals = Valid.SetAngka(Double.parseDouble(tbBilling.getValueAt(i, 7).toString()));
                        } catch (Exception e) {
                            totals = "";
                        }
                        pstemporary = koneksi.prepareStatement(sqlpstemporary);
                        try {
                            pstemporary.setString(1, tbBilling.getValueAt(i, 1).toString().replaceAll("'", "`"));
                            pstemporary.setString(2, tbBilling.getValueAt(i, 2).toString().replaceAll("'", "`"));
                            pstemporary.setString(3, tbBilling.getValueAt(i, 3).toString().replaceAll("'", "`"));
                            pstemporary.setString(4, biaya);
                            try {
                                pstemporary.setString(5, tbBilling.getValueAt(i, 5).toString().replaceAll("'", "`"));
                            } catch (Exception e) {
                                pstemporary.setString(5, "");
                            }
                            pstemporary.setString(6, tambahan);
                            pstemporary.setString(7, totals);
                            try {
                                pstemporary.setString(8, tbBilling.getValueAt(i, 8).toString().replaceAll("'", "`"));
                            } catch (Exception e) {
                                pstemporary.setString(8, "");
                            }
                            pstemporary.executeUpdate();
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        } finally {
                            if (pstemporary != null) {
                                pstemporary.close();
                            }
                        }
                    }
                }

//                if (ChkPiutang.isSelected() == false) {
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','','','','','','','','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','TOTAL TAGIHAN',':','','','','','<b>" + TtlSemua.getText() + "</b>','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','PPN',':','','','','','<b>" + Valid.SetAngka(besarppn) + "</b>','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','TAGIHAN+PPN',':','','','','','<b>" + TagihanPPn.getText() + "</b>','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','','','','','','','','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','DEPOSIT',':','','','','','<b>" + Deposit.getText() + "</b>','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','BAYAR',':','','','','','<b>" + Valid.SetAngka(bayar) + "</b>','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','Kembali',':','','','','','<b>" + TKembali.getText() + "</b>','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                } else if (ChkPiutang.isSelected() == true) {
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','','','','','','','','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','TOTAL TAGIHAN',':','','','','','<b>" + TtlSemua.getText() + "</b>','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','PPN',':','','','','','<b>" + Valid.SetAngka(besarppn) + "</b>','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','TAGIHAN + PPN',':','','','','','<b>" + TagihanPPn.getText() + "</b>','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','','','','','','','','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','DEPOSIT',':','','','','','<b>" + Deposit.getText() + "</b>','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','UANG MUKA',':','','','','','<b>" + Valid.SetAngka(bayar) + "</b>','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','SISA PIUTANG',':','','','','','<b>" + Valid.SetAngka(piutang) + "</b>','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                }
                if (ChkPiutang.isSelected() == false) {
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','','','','','','','','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
                    Sequel.menyimpan("temporary_bayar_ranap", "'0','TOTAL TAGIHAN',':','','','','','" + TtlSemua.getText() + "','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
                    Sequel.menyimpan("temporary_bayar_ranap", "'0','PPN',':','','','','','" + Valid.SetAngka(besarppn) + "','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
                    Sequel.menyimpan("temporary_bayar_ranap", "'0','TAGIHAN+PPN',':','','','','','" + TagihanPPn.getText() + "','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','','','','','','','','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
                    Sequel.menyimpan("temporary_bayar_ranap", "'0','DEPOSIT',':','','','','','" + Deposit.getText() + "','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
                    Sequel.menyimpan("temporary_bayar_ranap", "'0','BAYAR',':','','','','','" + Valid.SetAngka(bayar) + "','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
                    Sequel.menyimpan("temporary_bayar_ranap", "'0','Kembali',':','','','','','" + TKembali.getText() + "','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
                } else if (ChkPiutang.isSelected() == true) {
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','','','','','','','','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
                    Sequel.menyimpan("temporary_bayar_ranap", "'0','TOTAL TAGIHAN',':','','','','','" + TtlSemua.getText() + "','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
                    Sequel.menyimpan("temporary_bayar_ranap", "'0','PPN',':','','','','','" + Valid.SetAngka(besarppn) + "','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
                    Sequel.menyimpan("temporary_bayar_ranap", "'0','TAGIHAN + PPN',':','','','','','" + TagihanPPn.getText() + "','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
//                    Sequel.menyimpan("temporary_bayar_ranap", "'0','','','','','','','','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
                    Sequel.menyimpan("temporary_bayar_ranap", "'0','DEPOSIT',':','','','','','" + Deposit.getText() + "','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
                    Sequel.menyimpan("temporary_bayar_ranap", "'0','UANG MUKA',':','','','','','" + Valid.SetAngka(bayar) + "','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
                    Sequel.menyimpan("temporary_bayar_ranap", "'0','SISA PIUTANG',':','','','','','" + Valid.SetAngka(piutang) + "','Tagihan','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
                }

                i = 0;
                try {
                    biaya = (String) JOptionPane.showInputDialog(null, "Silahkan pilih nota/kwitansi yang mau dicetak..!", "Nota", JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Nota", "Kwitansi", "Nota & Kwitansi"}, "Nota");
                    switch (biaya) {
                        case "Nota":
                            i = 1;
                            break;
//                        case "Nota 2":
//                            i = 2;
//                            break;
                        case "Kwitansi":
                            i = 2;
                            break;
                        case "Nota & Kwitansi":
                            i = 3;
                            break;
                    }
                } catch (Exception e) {
                    i = 0;
                }

                if (i > 0) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    kd_pj = Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", TNoRw.getText());
                    if (i == 1) {
                        ttl = (ttlLaborat + ttlRadiologi + ttlOperasi + ttlObat + ttlRanap_Dokter + ttlRanap_Paramedis + ttlRalan_Dokter
                                + ttlRalan_Paramedis + ttlTambahan + ttlKamar + ttlRegistrasi + ttlHarian + ttlRetur_Obat + ttlResep_Pulang + ttlService);
                        if (piutang > 0) {
                            cetakNotaPiutangAJA();
                        } else if (piutang <= 0) {
                            if (ChkPiutang.isSelected() == true) {
                                JOptionPane.showMessageDialog(null, "Hilangkan dulu conteng pada PIUTANG, kemudian klik tombol nota lagi...!!!");
                                ChkPiutang.requestFocus();
                            } else {
                                cetakNotaAJA();
                            }
                        }

//                        Valid.panggilUrl("billing/LaporanBilling2.php?petugas=" + var.getkode().replaceAll(" ", "_") + "&ttl=" + ttl + "&tanggal=" + DTPTgl.getSelectedItem().toString().replaceAll(" ", "_"));
//                    } 
//                    else if (i == 2) {
//                        Valid.panggilUrl("billing/LaporanBilling3.php?petugas=" + var.getkode().replaceAll(" ", "_") + "&tanggal=" + DTPTgl.getSelectedItem().toString().replaceAll(" ", "_"));
//                    } else if (i == 2) {
//                        if (piutang > 0) {
//                            Valid.panggilUrl("billing/LaporanBilling8.php?petugas=" + var.getkode().replaceAll(" ", "_") + "&nonota=" + Sequel.cariIsi("select count(kamar_inap.no_rawat) from kamar_inap inner join reg_periksa "
//                                    + "on kamar_inap.no_rawat=reg_periksa.no_rawat where reg_periksa.kd_pj='" + kd_pj + "' and kamar_inap.tgl_keluar like '%" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "%'") + "/RI/" + kd_pj + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(5, 7) + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 4));
//                        } else if (piutang <= 0) {
//                            Valid.panggilUrl("billing/LaporanBilling4.php?petugas=" + var.getkode().replaceAll(" ", "_") + "&nonota=" + Sequel.cariIsi("select count(kamar_inap.no_rawat) from kamar_inap inner join reg_periksa "
//                                    + "on kamar_inap.no_rawat=reg_periksa.no_rawat where reg_periksa.kd_pj='" + kd_pj + "' and kamar_inap.tgl_keluar like '%" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "%'") + "/RI/" + kd_pj + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(5, 7) + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 4) + "&tanggal=" + DTPTgl.getSelectedItem().toString().replaceAll(" ", "_"));
//                        }
                    } else if (i == 2) {
                        if (piutang > 0) {
                            cetakKwitansiPIUTANG();
                        } else if (piutang <= 0) {
                            if (ChkPiutang.isSelected() == true) {
                                JOptionPane.showMessageDialog(null, "Hilangkan dulu conteng pada PIUTANG, kemudian klik tombol nota lagi...!!!");
                                ChkPiutang.requestFocus();
                            } else {
                                cetakKwitansiLUNAS();
                            }
                        }
                    } else if (i == 3) {
                        ttl = (ttlLaborat + ttlRadiologi + ttlOperasi + ttlObat + ttlRanap_Dokter + ttlRanap_Paramedis + ttlRalan_Dokter
                                + ttlRalan_Paramedis + ttlTambahan + ttlKamar + ttlRegistrasi + ttlHarian + ttlRetur_Obat + ttlResep_Pulang + ttlService);
//                        Valid.panggilUrl("billing/LaporanBilling2.php?petugas=" + var.getkode().replaceAll(" ", "_") + "&ttl=" + ttl + "&tanggal=" + DTPTgl.getSelectedItem().toString().replaceAll(" ", "_"));
//                        Valid.panggilUrl("billing/LaporanBilling3.php?petugas=" + var.getkode().replaceAll(" ", "_") + "&tanggal=" + DTPTgl.getSelectedItem().toString().replaceAll(" ", "_"));
                        if (piutang > 0) {
                            cetakNotaPiutangAJA();
                            cetakKwitansiPIUTANG();
//                            Valid.panggilUrl("billing/LaporanBilling8.php?petugas=" + var.getkode().replaceAll(" ", "_") + "&nonota=" + Sequel.cariIsi("select count(kamar_inap.no_rawat) from kamar_inap inner join reg_periksa "
//                                    + "on kamar_inap.no_rawat=reg_periksa.no_rawat where reg_periksa.kd_pj='" + kd_pj + "' and kamar_inap.tgl_keluar like '%" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "%'") + "/RI/" + kd_pj + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(5, 7) + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 4));
                        } else if (piutang <= 0) {
                            if (ChkPiutang.isSelected() == true) {
                                JOptionPane.showMessageDialog(null, "Hilangkan dulu conteng pada PIUTANG, kemudian klik tombol nota lagi...!!!");
                                ChkPiutang.requestFocus();
                            } else {
                                cetakNotaAJA();
                                cetakKwitansiLUNAS();
                            }
//                            Valid.panggilUrl("billing/LaporanBilling4.php?petugas=" + var.getkode().replaceAll(" ", "_") + "&nonota=" + Sequel.cariIsi("select count(kamar_inap.no_rawat) from kamar_inap inner join reg_periksa "
//                                    + "on kamar_inap.no_rawat=reg_periksa.no_rawat where reg_periksa.kd_pj='" + kd_pj + "' and kamar_inap.tgl_keluar like '%" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "%'") + "/RI/" + kd_pj + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(5, 7) + "/" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 4) + "&tanggal=" + DTPTgl.getSelectedItem().toString().replaceAll(" ", "_"));
                        }
                    }
                    this.setCursor(Cursor.getDefaultCursor());
                }

                koneksi.setAutoCommit(true);
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }
    }//GEN-LAST:event_BtnNotaActionPerformed

    private void BtnNotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnNotaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnKeluar, BtnView);
        }
    }//GEN-LAST:event_BtnNotaKeyPressed

    private void MnInputObat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputObat1ActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
            TNoRw.requestFocus();
        } else {
            DlgCariObat2 dlgobt = new DlgCariObat2(null, false);
            akses.setform("DLgBilingRanap");
            dlgobt.isCek();
            dlgobt.setNoRm(norawatbayi, DTPTgl.getDate(), "00", "00", "00", true);
            dlgobt.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            dlgobt.tampil();
            dlgobt.setLocationRelativeTo(internalFrame1);
            dlgobt.setVisible(true);
        }
    }//GEN-LAST:event_MnInputObat1ActionPerformed

    private void MnPotongan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPotongan1ActionPerformed
        norawatpotongan.setText(norawatbayi);
        tampilPotongan(norawatpotongan.getText());
        WindowInput4.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        WindowInput4.setLocationRelativeTo(internalFrame1);
        WindowInput4.setVisible(true);
    }//GEN-LAST:event_MnPotongan1ActionPerformed

    private void MnCariPeriksaLab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCariPeriksaLab1ActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        } else {
            DlgCariPeriksaLab cperiksalab = new DlgCariPeriksaLab(null, false);
            cperiksalab.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            cperiksalab.setLocationRelativeTo(internalFrame1);
            cperiksalab.SetNoRw(norawatbayi);
            cperiksalab.setAlwaysOnTop(false);
            cperiksalab.setVisible(true);
        }
    }//GEN-LAST:event_MnCariPeriksaLab1ActionPerformed

    private void MnTambahan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTambahan1ActionPerformed
        norawattambahan.setText(norawatbayi);
        tampilTambahan(norawattambahan.getText());
        WindowInput3.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        WindowInput3.setLocationRelativeTo(internalFrame1);
        WindowInput3.setVisible(true);
    }//GEN-LAST:event_MnTambahan1ActionPerformed

    private void MnPeriksaRadiologi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaRadiologi1ActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        } else {
            akses.setform("DLgBilingRanap");
            DlgPeriksaRadiologi periksarad = new DlgPeriksaRadiologi(null, false);
            periksarad.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            periksarad.setLocationRelativeTo(internalFrame1);
            periksarad.emptTeks();
            periksarad.setNoRm(TNoRw.getText(), "Ranap");
            periksarad.tampil();
            periksarad.isCek();
            periksarad.setAlwaysOnTop(false);
            periksarad.setVisible(true);
        }
    }//GEN-LAST:event_MnPeriksaRadiologi1ActionPerformed

    private void MnRawatInap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRawatInap1ActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
            TNoRw.requestFocus();
        } else {
            DlgRawatInap rawatinap = new DlgRawatInap(null, false);
            akses.setform("DLgBilingRanap");
            rawatinap.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            rawatinap.setLocationRelativeTo(internalFrame1);
            rawatinap.isCek();
            rawatinap.setNoRm(norawatbayi, DTPTgl.getDate(), new Date());
            rawatinap.tampilDr();
            rawatinap.setVisible(true);
        }
    }//GEN-LAST:event_MnRawatInap1ActionPerformed

    private void MnRawatJalan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRawatJalan1ActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
            TNoRw.requestFocus();
        } else {
            DlgRawatJalan dlgrwjl = new DlgRawatJalan(null, false);
            dlgrwjl.isCek();
            dlgrwjl.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            dlgrwjl.setLocationRelativeTo(internalFrame1);

            dlgrwjl.setNoRm(norawatbayi, DTPTgl.getDate(), new Date());
            dlgrwjl.tampilDrPr();
            dlgrwjl.TotalNominal();
            dlgrwjl.setVisible(true);
            dlgrwjl.fokus();
        }
    }//GEN-LAST:event_MnRawatJalan1ActionPerformed

    private void MnDataObat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataObat1ActionPerformed
        DlgPemberianObat beriobat = new DlgPemberianObat(null, false);
        akses.setform("DLgBilingRanap");
        beriobat.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        beriobat.setLocationRelativeTo(internalFrame1);
        beriobat.isCek();
        beriobat.setNoRm(norawatbayi, DTPTgl.getDate(), new Date(), "ranap");
        beriobat.setVisible(true);
    }//GEN-LAST:event_MnDataObat1ActionPerformed

    private void MnInputResepPulang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputResepPulang1ActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
            TNoRw.requestFocus();
        } else {
            DlgInputResepPulang inputresep=new DlgInputResepPulang(null,false);
            akses.setform("DLgBilingRanap");
            inputresep.isCek();
            inputresep.setNoRm(norawatbayi, "-", Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?", TNoRw.getText()),
                    Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat=?", TNoRw.getText()));
            inputresep.tampil();
            inputresep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            inputresep.setLocationRelativeTo(internalFrame1);
            inputresep.setVisible(true);
        }
    }//GEN-LAST:event_MnInputResepPulang1ActionPerformed

    private void MnReturJual1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnReturJual1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgReturJual returjual = new DlgReturJual(null, false);
        returjual.emptTeks();
        returjual.isCek();
        returjual.setPasien(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", norawatbayi), norawatbayi);
        returjual.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        returjual.setLocationRelativeTo(internalFrame1);
        returjual.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnReturJual1ActionPerformed

    private void MnPeriksaLab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaLab1ActionPerformed
        cekdokter = "";
        diagnosa_ok = "";
        diagnosa_cek = 0;
        diagnosa_cek = Sequel.cariInteger("select count(1) cek from kamar_inap where no_rawat='" + TNoRw.getText() + "'");
        cekdokter = Sequel.cariIsi("select kd_dokter from dpjp_ranap where no_rawat='" + TNoRw.getText() + "'");

        if (diagnosa_cek == 0) {
            diagnosa_ok = "-";
        } else {
            diagnosa_ok = Sequel.cariIsi("select diagnosa_awal from kamar_inap where no_rawat='" + TNoRw.getText() + "'");
        }

        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        } else {
            akses.setform("DLgBilingRanap");
            DlgPeriksaLaboratorium periksalab = new DlgPeriksaLaboratorium(null, false);
            periksalab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            periksalab.setLocationRelativeTo(internalFrame1);
            periksalab.emptTeks();
            periksalab.setNoRm(norawatbayi, "Ranap", "-", diagnosa_ok, Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar='" + kdkamar + "'"));
            periksalab.KodePerujuk.setText(cekdokter);
            periksalab.tampiltarif();
            periksalab.tampil();
            periksalab.setVisible(true);
        }
    }//GEN-LAST:event_MnPeriksaLab1ActionPerformed

    private void MnCariRadiologi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCariRadiologi1ActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        } else {
            DlgCariPeriksaRadiologi cperiksalab = new DlgCariPeriksaRadiologi(null, false);
            cperiksalab.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            cperiksalab.setLocationRelativeTo(internalFrame1);
            cperiksalab.SetNoRw(norawatbayi);
            cperiksalab.setAlwaysOnTop(false);
            cperiksalab.setVisible(true);
        }
    }//GEN-LAST:event_MnCariRadiologi1ActionPerformed

    private void MnDataResepPulang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataResepPulang1ActionPerformed
        DlgResepPulang reseppulang = new DlgResepPulang(null, false);
        akses.setform("DLgBilingRanap");
        reseppulang.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        reseppulang.setLocationRelativeTo(internalFrame1);
        reseppulang.isCek();
        reseppulang.setNoRm(norawatbayi, DTPTgl.getDate(), new Date());
        reseppulang.setVisible(true);
    }//GEN-LAST:event_MnDataResepPulang1ActionPerformed

    private void MnObatLangsung1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnObatLangsung1ActionPerformed
        TotalObat.setText(Sequel.cariIsi("select besar_tagihan from tagihan_obat_langsung where no_rawat=?", norawatbayi));
        WindowInput.setSize(590, 80);
        WindowInput.setLocationRelativeTo(internalFrame1);
        WindowInput.setVisible(true);
    }//GEN-LAST:event_MnObatLangsung1ActionPerformed

    private void MnTagihanOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTagihanOperasiActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
            TNoRw.requestFocus();
        } else {
            akses.setform("DLgBilingRanap");
            DlgTagihanOperasi dlgro = new DlgTagihanOperasi(null, false);
            dlgro.isCek();
            dlgro.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dlgro.setLocationRelativeTo(internalFrame1);
            dlgro.setNoRm(TNoRw.getText(), TNoRM.getText() + ", " + TPasien.getText(), "Ranap");
            dlgro.setVisible(true);
        }
    }//GEN-LAST:event_MnTagihanOperasiActionPerformed

    private void TtlSemuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtlSemuaKeyPressed
        Valid.pindah(evt, BtnKeluar, BtnNota);
    }//GEN-LAST:event_TtlSemuaKeyPressed

    private void tbAkunBayarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbAkunBayarPropertyChange
        if (this.isVisible() == true) {
            isRawat();
        }
    }//GEN-LAST:event_tbAkunBayarPropertyChange

    private void tbAkunBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAkunBayarKeyPressed
        if (tabModeAkunBayar.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (tbAkunBayar.getRowCount() != 0) {
                    if ((tbAkunBayar.getSelectedColumn() == 2) || (tbAkunBayar.getSelectedColumn() == 3) || (tbAkunBayar.getSelectedColumn() == 4)) {
                        try {
                            if (!tabModeAkunBayar.getValueAt(tbAkunBayar.getSelectedRow(), 2).toString().equals("")) {
                                tbAkunBayar.setValueAt(
                                        Valid.roundUp((Valid.SetAngka(tbAkunBayar.getValueAt(tbAkunBayar.getSelectedRow(), 3).toString()) / 100)
                                                * Valid.SetAngka(tbAkunBayar.getValueAt(tbAkunBayar.getSelectedRow(), 2).toString()), 100), tbAkunBayar.getSelectedRow(), 4);
                            } else {
                                tbAkunBayar.setValueAt("", tbAkunBayar.getSelectedRow(), 4);
                            }
                        } catch (Exception e) {
                        }
                    }
                }
                isRawat();
            }
        }
    }//GEN-LAST:event_tbAkunBayarKeyPressed

    private void tbAkunPiutangPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbAkunPiutangPropertyChange
        if (this.isVisible() == true) {
            isRawat();
        }
    }//GEN-LAST:event_tbAkunPiutangPropertyChange

    private void tbAkunPiutangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAkunPiutangKeyPressed
        if (tabModeAkunBayar.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (ChkPiutang.isSelected() == true) {
                    isRawat();
                } else if (ChkPiutang.isSelected() == false) {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan centang terlebih dahulu pada pilihan piutang...!!");
                }
            }
        }
    }//GEN-LAST:event_tbAkunPiutangKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariBayarActionPerformed(null);
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariBayarActionPerformed
        if (status.equals("belum")) {
            tampilAkunBayar();
        } else if (status.equals("sudah")) {
            tampilAkunBayarTersimpan();
        }
        isKembali();
    }//GEN-LAST:event_BtnCariBayarActionPerformed

    private void BtnCariBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariBayarKeyPressed

    }//GEN-LAST:event_BtnCariBayarKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCariPiutangActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void btnCariPiutangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPiutangActionPerformed
        if (ChkPiutang.isSelected() == true) {
            if (status.equals("belum")) {
                tampilAkunPiutang();
            } else if (status.equals("sudah")) {
                tampilAkunPiutangTersimpan();
            }
            isKembali();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan centang terlebih dahulu pada pilihan piutang...!!");
        }
    }//GEN-LAST:event_btnCariPiutangActionPerformed

    private void btnCariPiutangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCariPiutangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariPiutangKeyPressed

    private void ChkPiutangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPiutangActionPerformed
        if (ChkPiutang.isSelected() == false) {
            ppBersihkan1ActionPerformed(evt);
            cmbPenjamin.setSelectedIndex(0);
            TketPenjamin.setText("");
            cmbPenjamin.setEnabled(false);
            TketPenjamin.setEnabled(false);
        } else {
            cmbPenjamin.setEnabled(true);
            TketPenjamin.setEnabled(true);
        }
        isRawat();
    }//GEN-LAST:event_ChkPiutangActionPerformed

    private void ppBersihkan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkan1ActionPerformed
        Valid.tabelKosong(tabModeAkunPiutang);
        if (status.equals("belum")) {
            tampilAkunPiutang();
        } else if (status.equals("sudah")) {
            tampilAkunPiutangTersimpan();
        }
    }//GEN-LAST:event_ppBersihkan1ActionPerformed

    private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
        Valid.tabelKosong(tabModeAkunBayar);
        if (status.equals("belum")) {
            tampilAkunBayar();
        } else if (status.equals("sudah")) {
            tampilAkunBayarTersimpan();
        }
    }//GEN-LAST:event_ppBersihkanActionPerformed

    private void tbAkunPiutangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAkunPiutangMouseClicked
        if (ChkPiutang.isSelected() == false) {
            ChkPiutang.setSelected(true);
            cmbPenjamin.setEnabled(true);
            TketPenjamin.setEnabled(true);
            isRawat();
        }
    }//GEN-LAST:event_tbAkunPiutangMouseClicked

    private void chkRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRalanActionPerformed
        isRawat();
    }//GEN-LAST:event_chkRalanActionPerformed

    private void chkRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRanapActionPerformed
        isRawat();
    }//GEN-LAST:event_chkRanapActionPerformed

    private void BtnCloseIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn5ActionPerformed
        WindowInput7.dispose();
    }//GEN-LAST:event_BtnCloseIn5ActionPerformed

    private void BtnSimpan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan5ActionPerformed
        Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", "p_jawab='" + nm_telah_terima.getText() + "' ");
        WindowInput7.dispose();
    }//GEN-LAST:event_BtnSimpan5ActionPerformed

    private void MnNamaTelahTerimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnNamaTelahTerimaActionPerformed
        Sequel.cariIsi("select p_jawab from reg_periksa where no_rawat=? ", nm_telah_terima, TNoRw.getText());

        WindowInput7.setSize(731, 77);
        WindowInput7.setLocationRelativeTo(internalFrame1);
        WindowInput7.setAlwaysOnTop(false);
        WindowInput7.setVisible(true);
    }//GEN-LAST:event_MnNamaTelahTerimaActionPerformed

    private void cekLRItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cekLRItemStateChanged
        if (cekLR.isSelected() == true) {
            cekLR.setText("Kwitansi/Nota DENGAN lama rawat");
            isRawat();
        } else {
            cekLR.setText("Kwitansi/Nota TANPA lama rawat");
            isRawat();
        }
    }//GEN-LAST:event_cekLRItemStateChanged

    private void cekObatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cekObatItemStateChanged
        if (cekObat.isSelected() == true) {
            cekObat.setText("Conteng SEMUA Obat & BHP (YA)");
            isRawat();
        } else {
            cekObat.setText("Conteng SEMUA Obat & BHP (TIDAK)");
            isRawat();
        }
    }//GEN-LAST:event_cekObatItemStateChanged

    private void MnSelisihTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSelisihTarifActionPerformed
        Sequel.cariIsi("select ifnull(no_sep,'0') from bridging_sep where jnspelayanan='1' and no_rawat=? ", NoSEP, TNoRw.getText());

        if (tabModeRwJlDr.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            BtnCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan klik dulu nama pasiennya pada menu kamar inap..!!");
            BtnKeluar.requestFocus();
        } else if (!Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals("B01")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien BPJS saja...!!");
        } else if (Sequel.cariInteger("select count(-1) from bridging_sep where no_rawat='" + TNoRw.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini belum mencetak SEP, cek lagi kelengkapan berkasnya..!!");
        } else if (Sequel.cariInteger("SELECT count(-1) FROM kamar_inap ki INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                + "INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal INNER JOIN bridging_sep bs ON bs.no_rawat = ki.no_rawat "
                + "WHERE ki.stts_pulang NOT IN ('-', 'Pindah Kamar') AND bs.no_sep = '" + NoSEP.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Pulangkan dulu pasiennya dari rawat inap & selesaikan kwitansinya dikasir...!!");
        } else if (Sequel.cariIsi("select ifnull(kode_inacbg,'') from bridging_sep where no_sep='" + NoSEP.getText() + "'").equals("")) {
            cekSEP();
            cekINACBG();
            WindowSelisihTarif.setSize(824, 456);
            WindowSelisihTarif.setLocationRelativeTo(internalFrame1);
            WindowSelisihTarif.setVisible(true);
            kdINACBG.requestFocus();
            hakkelas.setEditable(akses.getadmin());
            
        } else if (!Sequel.cariIsi("select ifnull(kode_inacbg,'') from bridging_sep where no_sep='" + NoSEP.getText() + "'").equals("")) {
            cekSEP();
            cekINACBG();
            hitungSelisih();

            WindowSelisihTarif.setSize(824, 456);
            WindowSelisihTarif.setLocationRelativeTo(internalFrame1);
            WindowSelisihTarif.setVisible(true);
            kdINACBG.requestFocus();
            hakkelas.setEditable(akses.getadmin());
        }
    }//GEN-LAST:event_MnSelisihTarifActionPerformed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowSelisihTarif.dispose();
        selisihBaru();
        isRawat();
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void BtnSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan6ActionPerformed
        if (hakkelas.getText().equals("3")) {
            JOptionPane.showMessageDialog(null, "Sesuai Permenkes RI No. 3 Tahun 2023, utk. hak kelas 3 tdk. diperkenankan lagi naik kls. rawat..!!!");
            kdINACBG.requestFocus();
        } else if (kdINACBG.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Kode INACBG belum diisi..!!");
            kdINACBG.requestFocus();
        } else if (deskripsiKD.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Deskripsi diagnosa kode INACBG masih kosong..!!");
            kdINACBG.requestFocus();
        } else if (Sequel.cariIsi("select total_byr from pemasukan_lain where no_rawat='" + TNoRw.getText() + "'").equals("")) {
            Sequel.mengedit("bridging_sep", "no_sep='" + NoSEP.getText() + "' and jnspelayanan='1'", "kode_inacbg='" + kdINACBG.getText() + "' ");
            Sequel.mengedit("bridging_sep_backup", "no_sep='" + NoSEP.getText() + "' and jnspelayanan='1'", "kode_inacbg='" + kdINACBG.getText() + "' ");

            WindowSelisihTarif.dispose();
            isRawat();
            selisihBaru();
        } else {
            JOptionPane.showMessageDialog(null, "Kode " + kdINACBG.getText() + " sudah terverifikasi kasir utk. pembayaran. Data tidak bisa diubah...!!!");
            Sequel.cariIsi("select kode_inacbg from bridging_sep where jnspelayanan='1' and no_sep=? ", kdINACBG, NoSEP.getText());
            cekINACBG();
            if (hakkelas.getText().equals("1")) {
                hitungSelisih();
            } else if (hakkelas.getText().equals("2")) {
                hitungSelisih();
            }
            //            else if (hakkelas.getText().equals("3")) {
            //              hitungSelisih();
            //            }
            BtnCloseIn6.requestFocus();
        }
    }//GEN-LAST:event_BtnSimpan6ActionPerformed

    private void NoSEPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSEPKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_NoSEPKeyTyped

    private void kdINACBGKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdINACBGKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_kdINACBGKeyTyped

    private void kdINACBGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdINACBGKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (kdINACBG.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Kode INACBG belum diisi..!!");
                kdINACBG.requestFocus();
            } else {
                cekINACBG();

                if (deskripsiKD.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Kode INACBG salah, ulangi lagi..!!!");
                    kdINACBG.requestFocus();
                    kdINACBG.setText("");
                } else if (!deskripsiKD.getText().equals("")) {
                    if (hakkelas.getText().equals("1")) {
                        hitungSelisih();
                    } else if (hakkelas.getText().equals("2")) {
                        hitungSelisih();
                    } else if (hakkelas.getText().equals("3")) {
                        JOptionPane.showMessageDialog(null, "Sesuai Permenkes RI No. 3 Tahun 2023, utk. hak kelas 3 tdk. diperkenankan lagi naik kls. rawat..!!!");
                        BtnCloseIn6.requestFocus();
                    }
                }
            }
        }
    }//GEN-LAST:event_kdINACBGKeyPressed

    private void BtnGantikodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantikodeActionPerformed
        BtnSimpan6ActionPerformed(null);
    }//GEN-LAST:event_BtnGantikodeActionPerformed

    private void realcostRSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_realcostRSKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_realcostRSKeyTyped

    private void realcostRSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_realcostRSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_realcostRSKeyPressed

    private void hasilLMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hasilLMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_hasilLMKeyPressed

    private void hasilLMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hasilLMKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_hasilLMKeyTyped

    private void MnPiutangPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPiutangPasienActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
            TNoRw.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(-1) from piutang_pasien where no_rkm_medis='" + TNoRM.getText() + "' and status='Belum Lunas'") == 0) {
                tglPiutang.setDate(new Date());
            } else {
                Sequel.cariIsi("select tgl_piutang from piutang_pasien where no_rkm_medis='" + TNoRM.getText() + "' "
                        + "and status='Belum Lunas' order by tgl_piutang asc limit 1", tglPiutang);
            }

            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgBilingRanap");
            DlgLhtPiutang piutang = new DlgLhtPiutang(null, false);
            piutang.setNoRm(TNoRM.getText(), tglPiutang.getDate());
            piutang.tampil();
            piutang.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            piutang.setLocationRelativeTo(internalFrame1);
            piutang.setVisible(true);
            BtnCariActionPerformed(null);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPiutangPasienActionPerformed

    private void tglPiutangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tglPiutangItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tglPiutangItemStateChanged

    private void tglPiutangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglPiutangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglPiutangKeyPressed

    private void ppPerbaikiHakKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPerbaikiHakKelasActionPerformed
        if (hakkelas.getText().equals("1") || hakkelas.getText().equals("2") || hakkelas.getText().equals("3")) {
            Sequel.mengedit("bridging_sep", "jnspelayanan='1' and no_sep='" + NoSEP.getText() + "'", "klsrawat='" + hakkelas.getText() + "'");
            cekSEP();

            if (kdINACBG.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Kode INACBG belum diisi..!!");
                kdINACBG.requestFocus();
            } else {
                cekINACBG();

                if (deskripsiKD.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Kode INACBG salah, ulangi lagi..!!!");
                    kdINACBG.requestFocus();
                    kdINACBG.setText("");
                } else if (!deskripsiKD.getText().equals("")) {
                    if (hakkelas.getText().equals("1")) {
                        hitungSelisih();
                    } else if (hakkelas.getText().equals("2")) {
                        hitungSelisih();
                    } else if (hakkelas.getText().equals("3")) {
                        JOptionPane.showMessageDialog(null, "Sesuai Permenkes RI No. 3 Tahun 2023, utk. hak kelas 3 tdk. diperkenankan lagi naik kls. rawat..!!!");
                        BtnCloseIn6.requestFocus();
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Isilah hak kelas dengan benar..!!");
            hakkelas.requestFocus();
        }
    }//GEN-LAST:event_ppPerbaikiHakKelasActionPerformed

    private void cmbPenjaminMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPenjaminMouseReleased
        AutoCompleteDecorator.decorate(cmbPenjamin);
    }//GEN-LAST:event_cmbPenjaminMouseReleased

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            if (ChkPiutang.isSelected() == true) {
                cmbPenjamin.setEnabled(true);
                TketPenjamin.setEnabled(true);
            } else {
                cmbPenjamin.setEnabled(false);
                TketPenjamin.setEnabled(false);
            }
            
            if (Sequel.cariInteger("select count(-1) from piutang_pasien where no_rawat='" + TNoRw.getText() + "'") > 0) {
                cmbPenjamin.setSelectedItem(Sequel.cariIsi("select penjamin from piutang_pasien where no_rawat='" + TNoRw.getText() + "'"));
                TketPenjamin.setText(Sequel.cariIsi("select ket_penjamin from piutang_pasien where no_rawat='" + TNoRw.getText() + "'"));
            } else {
                cmbPenjamin.setSelectedIndex(0);
                TketPenjamin.setText("");
            }
        } else {
            cmbPenjamin.setSelectedIndex(0);
            TketPenjamin.setText("");
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgBilingRanap dialog = new DlgBilingRanap(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnBatal1;
    private widget.Button BtnCari;
    private widget.Button BtnCariBayar;
    private widget.Button BtnCloseIn;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCloseIn5;
    private widget.Button BtnCloseIn6;
    private widget.Button BtnGantikode;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusPotongan;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluarPotongan;
    private widget.Button BtnKeluarUbahLama;
    private widget.Button BtnNota;
    private widget.Button BtnSeek2;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan2;
    private widget.Button BtnSimpan3;
    private widget.Button BtnSimpan4;
    private widget.Button BtnSimpan5;
    private widget.Button BtnSimpan6;
    private widget.Button BtnSimpanPotongan;
    private widget.Button BtnSimpanUbahLama;
    private widget.Button BtnTambah;
    private widget.Button BtnTambahPotongan;
    private widget.Button BtnView;
    private widget.CekBox ChkPiutang;
    private widget.Tanggal DTPTgl;
    public widget.TextBox Deposit;
    private javax.swing.JMenu MnBayi;
    private javax.swing.JMenuItem MnCariPeriksaLab;
    private javax.swing.JMenuItem MnCariPeriksaLab1;
    private javax.swing.JMenuItem MnCariRadiologi;
    private javax.swing.JMenuItem MnCariRadiologi1;
    private javax.swing.JMenuItem MnDataObat;
    private javax.swing.JMenuItem MnDataObat1;
    private javax.swing.JMenuItem MnDataResepPulang;
    private javax.swing.JMenuItem MnDataResepPulang1;
    private javax.swing.JMenu MnGanti;
    private javax.swing.JMenuItem MnHapusTagihan;
    private javax.swing.JMenuItem MnInputObat;
    private javax.swing.JMenuItem MnInputObat1;
    private javax.swing.JMenuItem MnInputResepPulang;
    private javax.swing.JMenuItem MnInputResepPulang1;
    private javax.swing.JMenuItem MnNamaTelahTerima;
    private javax.swing.JMenuItem MnObatLangsung;
    private javax.swing.JMenuItem MnObatLangsung1;
    private javax.swing.JMenuItem MnPenjab;
    private javax.swing.JMenuItem MnPeriksaLab;
    private javax.swing.JMenuItem MnPeriksaLab1;
    private javax.swing.JMenuItem MnPeriksaRadiologi;
    private javax.swing.JMenuItem MnPeriksaRadiologi1;
    private javax.swing.JMenuItem MnPiutangPasien;
    private javax.swing.JMenuItem MnPotongan;
    private javax.swing.JMenuItem MnPotongan1;
    private javax.swing.JMenuItem MnRawatInap1;
    private javax.swing.JMenuItem MnRawatJalan;
    private javax.swing.JMenuItem MnRawatJalan1;
    private javax.swing.JMenuItem MnReturJual;
    private javax.swing.JMenuItem MnReturJual1;
    private javax.swing.JMenuItem MnSelisihTarif;
    private javax.swing.JMenuItem MnTagihanOperasi;
    private javax.swing.JMenuItem MnTambahan;
    private javax.swing.JMenuItem MnTambahan1;
    private javax.swing.JMenuItem MnUbahLamaInap;
    private widget.TextBox NoSEP;
    private javax.swing.JPopupMenu Popup2;
    private javax.swing.JPopupMenu PopupBayar;
    private javax.swing.JPopupMenu PopupPiutang;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll39;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextArea TKalkulasi;
    public widget.TextBox TKembali;
    private widget.TextBox TNoRM;
    public widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox TagihanPPn;
    private widget.TextBox TketPenjamin;
    private widget.TextBox TotalObat;
    private widget.TextBox TtlSemua;
    private javax.swing.JDialog WindowInput;
    private javax.swing.JDialog WindowInput3;
    private javax.swing.JDialog WindowInput4;
    private javax.swing.JDialog WindowInput5;
    private javax.swing.JDialog WindowInput6;
    private javax.swing.JDialog WindowInput7;
    private javax.swing.JDialog WindowSelisihTarif;
    private widget.Button btnCariPiutang;
    private widget.Button btnPenjab;
    public widget.CekBox cekLR;
    public widget.CekBox cekObat;
    private widget.CekBox chkRalan;
    private widget.CekBox chkRanap;
    private widget.ComboBox cmbPenjamin;
    private widget.TextBox deskripsiKD;
    private widget.TextBox hakkelas;
    private widget.TextBox hasilLM;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame12;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.Label jLabel13;
    private widget.Label jLabel15;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel23;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel42;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kdINACBG;
    private widget.TextBox kddokter;
    private widget.TextBox kdpenjab;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label lbl_jns_byr;
    private widget.TextBox lmrawat;
    private widget.TextBox naikKLS;
    private widget.TextBox nm_telah_terima;
    private widget.TextBox nmdokter;
    private widget.TextBox nmpasien;
    private widget.TextBox nmpenjab;
    private widget.TextBox nokartu;
    private widget.TextBox norawatpotongan;
    private widget.TextBox norawattambahan;
    private widget.TextBox norawatubahlama;
    private widget.TextBox norm;
    private widget.panelisi panelBayar;
    private widget.panelisi panelGlass1;
    private widget.panelisi panelGlass2;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private widget.panelisi panelisi3;
    private widget.TextBox persenSELISIH;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppBersihkan1;
    private javax.swing.JMenuItem ppPerbaikiHakKelas;
    private widget.TextBox realcostRS;
    private widget.TextBox rginap;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.TextBox tarifRC;
    private widget.TextBox tarifkls1;
    private widget.TextBox tarifkls2;
    private widget.TextBox tarifkls3;
    private widget.Table tbAkunBayar;
    private widget.Table tbAkunPiutang;
    private widget.Table tbBilling;
    private widget.Table tbPotongan;
    private widget.Table tbTambahan;
    private widget.Table tbUbahLama;
    private widget.Tanggal tglNota;
    private widget.Tanggal tglPiutang;
    // End of variables declaration//GEN-END:variables

    public void isRawat() {
        try {
            pscekbilling = koneksi.prepareStatement(sqlpscekbilling);
            try {
                pscekbilling.setString(1, TNoRw.getText());
                rscekbilling = pscekbilling.executeQuery();
                if (rscekbilling.next()) {
                    i = rscekbilling.getInt(1);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscekbilling != null) {
                    rscekbilling.close();
                }
                if (pscekbilling != null) {
                    pscekbilling.close();
                }
            }

            pscarirm = koneksi.prepareStatement("select r.no_rkm_medis, pj.png_jawab from reg_periksa r inner join penjab pj on pj.kd_pj=r.kd_pj where r.no_rawat=?");
            try {
                pscarirm.setString(1, TNoRw.getText());
                rscarirm = pscarirm.executeQuery();
                if (rscarirm.next()) {
                    TNoRM.setText(rscarirm.getString(1));
                    lbl_jns_byr.setText(" Cara Bayar : " + rscarirm.getString("png_jawab"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscarirm != null) {
                    rscarirm.close();
                }
                if (pscarirm != null) {
                    pscarirm.close();
                }
            }

            pscaripasien = koneksi.prepareStatement("select p.nm_pasien, concat(p.nm_pasien,' (',r.umurdaftar,' ',r.sttsumur,'.)',' RM. ',p.no_rkm_medis) pasienya from pasien p "
                    + "inner join reg_periksa r on r.no_rkm_medis=p.no_rkm_medis where r.no_rawat=? ");
            try {
                pscaripasien.setString(1, TNoRw.getText());
                rscaripasien = pscaripasien.executeQuery();
                if (rscaripasien.next()) {
                    TPasien.setText(rscaripasien.getString("nm_pasien"));
                    data_pasien = rscaripasien.getString("pasienya");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscaripasien != null) {
                    rscaripasien.close();
                }
                if (pscaripasien != null) {
                    pscaripasien.close();
                }
            }

            if (i <= 0) {
                uangdeposit = Sequel.cariIsiAngka("select ifnull(sum(besar_deposit),0) from deposit where no_rawat=?", TNoRw.getText());
                Deposit.setText(Valid.SetAngka(uangdeposit));
                prosesCariReg();
                prosesCariKamar();

                if (!norawatbayi.equals("")) {
                    MnBayi.setVisible(true);
                    tabModeRwJlDr.addRow(new Object[]{true, "Biaya Perawatan Ibu", ":", "", null, null, null, null, "-"});
                } else {
                    MnBayi.setVisible(false);
                }

                prosesCariTindakan(TNoRw.getText());
                prosesCariOperasi(TNoRw.getText());
                prosesCariObat(TNoRw.getText());
                prosesResepPulang(TNoRw.getText());
                prosesCariTambahan(TNoRw.getText());
                prosesCariPotongan(TNoRw.getText());
                if (!norawatbayi.equals("")) {
                    tabModeRwJlDr.addRow(new Object[]{false, "", "", "", null, null, null, null, "-"});
                    tabModeRwJlDr.addRow(new Object[]{true, "Biaya Perawatan Bayi", ":", "", null, null, null, null, "-"});
                    prosesCariTindakan(norawatbayi);
                    prosesCariOperasi(norawatbayi);
                    prosesCariObat(norawatbayi);
                    prosesResepPulang(norawatbayi);
                    prosesCariTambahan(norawatbayi);
                    prosesCariPotongan(norawatbayi);
                }
                TCari.setText("");
                TCari1.setText("");
                tampilAkunBayar();
                tampilAkunPiutang();
                isHitung();
                status = "belum";
            } else if (i > 0) {
                uangdeposit = Sequel.cariIsiAngka("select ifnull(sum(Uang_Muka),0) from nota_inap where no_rawat=?", TNoRw.getText());
                Deposit.setText(Valid.SetAngka(uangdeposit));
                Valid.SetTgl2(DTPTgl, Sequel.cariIsi("select concat(tanggal,' ',jam) from nota_inap where no_rawat='" + TNoRw.getText() + "'"));
                Valid.tabelKosong(tabModeRwJlDr);
                pssudahmasuk = koneksi.prepareStatement(sqlpssudahmasuk);
                try {
                    pssudahmasuk.setString(1, TNoRw.getText());
                    rsreg = pssudahmasuk.executeQuery();
                    while (rsreg.next()) {
                        if (!rsreg.getString("status").equals("Tagihan")) {
                            tabModeRwJlDr.addRow(new Object[]{true, rsreg.getString("no"),
                                rsreg.getString("nm_perawatan"),
                                rsreg.getString("pemisah"),
                                rsreg.getObject("satu"),
                                rsreg.getObject("dua"),
                                rsreg.getObject("tiga"),
                                rsreg.getObject("empat"),
                                rsreg.getString("status")});
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rsreg != null) {
                        rsreg.close();
                    }
                    if (pssudahmasuk != null) {
                        pssudahmasuk.close();
                    }
                }
                TCari.setText("");
                TCari1.setText("");
                tampilAkunBayarTersimpan();
                tampilAkunPiutangTersimpan();
                isHitung();
                prosesCariPenjaminPiutang(TNoRw.getText());
                status = "sudah";
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        isKembali();
    }

    private void isRawat2() {
        prosesCariReg();
        prosesCariKamar();

        if (!norawatbayi.equals("")) {
            MnBayi.setVisible(true);
            tabModeRwJlDr.addRow(new Object[]{true, "Biaya Perawatan Ibu", ":", "", null, null, null, null, "-"});
        } else {
            MnBayi.setVisible(false);
        }

        prosesCariTindakan(TNoRw.getText());
        prosesCariOperasi(TNoRw.getText());
        prosesCariObat(TNoRw.getText());
        prosesResepPulang(TNoRw.getText());
        prosesCariTambahan(TNoRw.getText());
        prosesCariPotongan(TNoRw.getText());
        prosesCariPenjaminPiutang(TNoRw.getText());
        if (!norawatbayi.equals("")) {
            tabModeRwJlDr.addRow(new Object[]{false, "", "", "", null, null, null, null, "-"});
            tabModeRwJlDr.addRow(new Object[]{true, "Biaya Perawatan Bayi", ":", "", null, null, null, null, "-"});
            prosesCariTindakan(norawatbayi);
            prosesCariOperasi(norawatbayi);
            prosesCariObat(norawatbayi);
            prosesResepPulang(norawatbayi);
            prosesCariTambahan(norawatbayi);
            prosesCariPotongan(norawatbayi);
        }
        isHitung();
        isKembali();
    }

    private void prosesCariReg() {
        tglmskRS = "";
        tglklrRS1 = "";
        tglklrRS2 = "";
        jamplgRS1 = "";
        jamplgRS2 = "";
        Valid.tabelKosong(tabModeRwJlDr);

        tglmskRS = Sequel.bulanINDONESIA("select date_format(tgl_registrasi,'%m') from reg_periksa where no_rawat='" + TNoRw.getText() + "'") + " " + Sequel.cariIsi("select date_format(tgl_registrasi,'%Y') from reg_periksa where no_rawat='" + TNoRw.getText() + "'");
        tglklrRS1 = Sequel.cariIsi("select DATE_FORMAT(CURDATE(), '%e')") + " " + Sequel.bulanINDONESIA("select DATE_FORMAT(CURDATE(), '%m')") + " " + Sequel.cariIsi("select DATE_FORMAT(CURDATE(), '%Y')");
        tglklrRS2 = Sequel.cariIsi("select DATE_FORMAT(tgl_keluar, '%e') from kamar_inap where no_rawat='" + TNoRw.getText() + "' order by tgl_keluar desc, jam_keluar DESC limit 1") + " "
                + Sequel.bulanINDONESIA("select DATE_FORMAT(tgl_keluar, '%m') from kamar_inap where no_rawat='" + TNoRw.getText() + "' order by tgl_keluar desc, jam_keluar DESC limit 1") + " "
                + Sequel.cariIsi("select DATE_FORMAT(tgl_keluar, '%Y') from kamar_inap where no_rawat='" + TNoRw.getText() + "' order by tgl_keluar desc, jam_keluar DESC limit 1");
        jamplgRS1 = Sequel.cariIsi("select DATE_FORMAT(CURTIME(), '%H:%i')");
        jamplgRS2 = Sequel.cariIsi("select DATE_FORMAT(jam_keluar, '%H:%i') from kamar_inap where no_rawat='" + TNoRw.getText() + "' order by tgl_keluar desc, jam_keluar DESC limit 1");

        try {
            psreg = koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis, concat('Tgl. Masuk RS ',DATE_FORMAT(reg_periksa.tgl_registrasi, '%e'),' " + tglmskRS + "',' Jam ',date_format(reg_periksa.jam_reg,'%H:%i')) as registrasi, "
                    + "kamar_inap.kd_kamar,concat('Tgl. Pulang ',if(kamar_inap.tgl_keluar='0000-00-00','" + tglklrRS1 + "','" + tglklrRS2 + "'),' Jam ',if(kamar_inap.tgl_keluar='0000-00-00','" + jamplgRS1 + "','" + jamplgRS2 + "')) as keluar, "
                    + "(select sum(kamar_inap.lama) from kamar_inap where kamar_inap.no_rawat=reg_periksa.no_rawat ) as lama,reg_periksa.biaya_reg "
                    + "from reg_periksa inner join kamar_inap on reg_periksa.no_rawat=kamar_inap.no_rawat where reg_periksa.no_rawat=? "
                    + "order by kamar_inap.tgl_keluar desc, kamar_inap.jam_keluar DESC limit 1");

            try {
                psreg.setString(1, TNoRw.getText());
                rsreg = psreg.executeQuery();
                while (rsreg.next()) {
                    tabModeRwJlDr.addRow(new Object[]{true, "No.Nota", ": " + Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_nota,6),signed)),0) from nota_inap where left(tanggal,7)='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "' ", Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7).replaceAll("-", "/") + "/RI/", 6), "", null, null, null, null, "-"});

                    pskamar = koneksi.prepareStatement("select concat(kamar.kd_kamar,', ',bangsal.nm_bangsal) from bangsal inner join kamar "
                            + "on kamar.kd_bangsal=bangsal.kd_bangsal where kamar.kd_kamar=?");
                    try {
                        pskamar.setString(1, rsreg.getString("kd_kamar"));
                        rskamar = pskamar.executeQuery();
                        if (rskamar.next()) {
                            tabModeRwJlDr.addRow(new Object[]{true, "Bangsal/Kamar", ": " + rskamar.getString(1), "", null, null, null, null, "-"});
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rskamar != null) {
                            rskamar.close();
                        }
                        if (pskamar != null) {
                            pskamar.close();
                        }
                    }

                    DTPTgl.setDate(new Date());

                    if (cekLR.isSelected() == true) {
                        tabModeRwJlDr.addRow(new Object[]{true, "Tgl. Perawatan", ": " + rsreg.getString("registrasi") + " s.d. " + rsreg.getString("keluar") + " (" + rsreg.getString("lama") + " Hari)", "", null, null, null, null, "-"});
                    } else {
                        tabModeRwJlDr.addRow(new Object[]{true, "Tgl. Perawatan", ": " + rsreg.getString("registrasi") + " s.d. " + rsreg.getString("keluar"), "", null, null, null, null, "-"});
                    }

                    norawatbayi = "";
                    psanak = koneksi.prepareStatement(sqlpsanak);
                    try {
                        psanak.setString(1, TNoRw.getText());
                        rsanak = psanak.executeQuery();
                        if (rsanak.next()) {
                            norawatbayi = rsanak.getString("no_rawat2");
                            tabModeRwJlDr.addRow(new Object[]{true, "No.R.M. Ibu", ": " + TNoRM.getText(), "", null, null, null, null, "-"});
                            tabModeRwJlDr.addRow(new Object[]{true, "Nama Ibu", ": " + TPasien.getText(), "", null, null, null, null, "-"});
                            tabModeRwJlDr.addRow(new Object[]{true, "No.R.M. Bayi", ": " + rsanak.getString("no_rkm_medis"), "", null, null, null, null, "-"});
                            tabModeRwJlDr.addRow(new Object[]{true, "Nama Bayi", ": " + rsanak.getString("nm_pasien"), "", null, null, null, null, "-"});
                        } else {
                            tabModeRwJlDr.addRow(new Object[]{true, "Pasien", ": " + data_pasien, "", null, null, null, null, "-"});
                            norawatbayi = "";
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsanak != null) {
                            rsanak.close();
                        }
                        if (psanak != null) {
                            psanak.close();
                        }
                    }

                    pscarialamat = koneksi.prepareStatement("select concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) from pasien "
                            + "inner join kelurahan inner join kecamatan inner join kabupaten on pasien.kd_kel=kelurahan.kd_kel "
                            + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "
                            + "where pasien.no_rkm_medis=?");
                    try {
                        pscarialamat.setString(1, TNoRM.getText());
                        rscarialamat = pscarialamat.executeQuery();
                        if (rscarialamat.next()) {
                            tabModeRwJlDr.addRow(new Object[]{true, "Alamat Pasien", ": " + rscarialamat.getString(1), "", null, null, null, null, "-"});
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rscarialamat != null) {
                            rscarialamat.close();
                        }
                        if (pscarialamat != null) {
                            pscarialamat.close();
                        }
                    }

                    pscaridpjp = koneksi.prepareStatement("select d.nm_dokter from dpjp_ranap dr inner join dokter d on d.kd_dokter=dr.kd_dokter "
                            + "where dr.no_rawat=? order by dr.kd_dokter limit 1");
                    try {
                        pscaridpjp.setString(1, TNoRw.getText());
                        rscaridpjp = pscaridpjp.executeQuery();
                        if (rscaridpjp.next()) {
                            tabModeRwJlDr.addRow(new Object[]{true, "DPJP Rawat Inap", ": " + rscaridpjp.getString(1), "", null, null, null, null, "-"});
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rscaridpjp != null) {
                            rscaridpjp.close();
                        }
                        if (pscaridpjp != null) {
                            pscaridpjp.close();
                        }
                    }

                    //cari dokter yang menangani  
                    if (centangdokterranap.equals("Yes")) {
                        psdokterranap = koneksi.prepareStatement(sqlpsdokterranap);
                        psdokterralan = koneksi.prepareStatement(sqlpsdokterralan);
                        try {
                            psdokterranap.setString(1, TNoRw.getText());
                            rsdokterranap = psdokterranap.executeQuery();

                            psdokterralan.setString(1, TNoRw.getText());
                            rsdokterralan = psdokterralan.executeQuery();

                            if (rsdokterralan.next() || rsdokterranap.next()) {
                                tabModeRwJlDr.addRow(new Object[]{true, "Dokter Visite", ":", "", null, null, null, null, "-"});
                            }
                            x = 1;
                            rsdokterranap.beforeFirst();
                            while (rsdokterranap.next()) {
                                tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsdokterranap.getString("nm_dokter"), "", null, null, null, null, "Dokter"});
                                x++;
                            }
                            //rs2.close();
                            rsdokterralan.beforeFirst();
                            while (rsdokterralan.next()) {
                                tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsdokterralan.getString("nm_dokter"), "", null, null, null, null, "Dokter"});
                                x++;
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        } finally {
                            if (rsdokterranap != null) {
                                rsdokterranap.close();
                            }
                            if (rsdokterralan != null) {
                                rsdokterralan.close();
                            }
                            if (psdokterranap != null) {
                                psdokterranap.close();
                            }
                            if (psdokterralan != null) {
                                psdokterralan.close();
                            }
                        }
                    } else {
                        psdokterranap = koneksi.prepareStatement(sqlpsdokterranap);
                        psdokterralan = koneksi.prepareStatement(sqlpsdokterralan);
                        try {
                            psdokterranap.setString(1, TNoRw.getText());
                            rsdokterranap = psdokterranap.executeQuery();

                            psdokterralan.setString(1, TNoRw.getText());
                            rsdokterralan = psdokterralan.executeQuery();

                            if (rsdokterralan.next() || rsdokterranap.next()) {
                                tabModeRwJlDr.addRow(new Object[]{false, "Dokter ", ":", "", null, null, null, null, "-"});
                            }
                            x = 1;
                            rsdokterranap.beforeFirst();
                            while (rsdokterranap.next()) {
                                tabModeRwJlDr.addRow(new Object[]{false, "                           ", rsdokterranap.getString("nm_dokter"), "", null, null, null, null, "Dokter"});
                                x++;
                            }
                            //rs2.close();
                            rsdokterralan.beforeFirst();
                            while (rsdokterralan.next()) {
                                tabModeRwJlDr.addRow(new Object[]{false, "                           ", rsdokterralan.getString("nm_dokter"), "", null, null, null, null, "Dokter"});
                                x++;
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        } finally {
                            if (rsdokterranap != null) {
                                rsdokterranap.close();
                            }
                            if (rsdokterralan != null) {
                                rsdokterralan.close();
                            }
                            if (psdokterranap != null) {
                                psdokterranap.close();
                            }
                            if (psdokterralan != null) {
                                psdokterralan.close();
                            }
                        }
                    }

                    if (tampilkan_administrasi_di_billingranap.equals("Yes")) {
                        tabModeRwJlDr.addRow(new Object[]{true, "Registrasi", ":", "", null, null, null, rsreg.getDouble("biaya_reg"), "Registrasi"});
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsreg != null) {
                    rsreg.close();
                }
                if (psreg != null) {
                    psreg.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

    }

    private void prosesCariObat(String norawat) {
        tabModeRwJlDr.addRow(new Object[]{true, x + ". Obat & BHP", ":", "", null, null, null, null, "Obat"});
        x++;
        subttl = 0;
        ttlobat = 0;
        ttlretur = 0;
        try {
            pscariobat = koneksi.prepareStatement(
                    "select databarang.nama_brng,detail_pemberian_obat.biaya_obat,"
                    + "sum(detail_pemberian_obat.jml) as jml,sum(detail_pemberian_obat.embalase+detail_pemberian_obat.tuslah) as tambahan,"
                    + "(sum(detail_pemberian_obat.total)-sum(detail_pemberian_obat.embalase+detail_pemberian_obat.tuslah)) as total "
                    + "from detail_pemberian_obat inner join databarang "
                    + "on detail_pemberian_obat.kode_brng=databarang.kode_brng where "
                    + "detail_pemberian_obat.no_rawat=? and detail_pemberian_obat.status like ? group by databarang.nama_brng,detail_pemberian_obat.biaya_obat");
            try {
                pscariobat.setString(1, norawat);
                if ((chkRalan.isSelected() == true) && (chkRanap.isSelected() == true)) {
                    pscariobat.setString(2, "%%");
                } else if ((chkRalan.isSelected() == true) && (chkRanap.isSelected() == false)) {
                    pscariobat.setString(2, "%Ralan%");
                } else if ((chkRalan.isSelected() == false) && (chkRanap.isSelected() == true)) {
                    pscariobat.setString(2, "%Ranap%");
                } else if ((chkRalan.isSelected() == false) && (chkRanap.isSelected() == false)) {
                    pscariobat.setString(2, "%Kosong%");
                }
                rscariobat = pscariobat.executeQuery();
                while (rscariobat.next()) {
                    if (cekObat.isSelected() == true) {
                        tabModeRwJlDr.addRow(new Object[]{true, "                           ", rscariobat.getString("nama_brng"), ":",
                            rscariobat.getDouble("biaya_obat"), rscariobat.getDouble("jml"), rscariobat.getDouble("tambahan"),
                            (rscariobat.getDouble("total") + rscariobat.getDouble("tambahan")), "Obat"});
                        subttl = subttl + rscariobat.getDouble("total") + rscariobat.getDouble("tambahan");
                    } else {
                        tabModeRwJlDr.addRow(new Object[]{false, "                           ", rscariobat.getString("nama_brng"), ":",
                            rscariobat.getDouble("biaya_obat"), rscariobat.getDouble("jml"), rscariobat.getDouble("tambahan"),
                            (rscariobat.getDouble("total") + rscariobat.getDouble("tambahan")), "Obat"});
                        subttl = subttl + rscariobat.getDouble("total") + rscariobat.getDouble("tambahan");
                        //embalase=embalase+rscariobat.getDouble("tambahan");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscariobat != null) {
                    rscariobat.close();
                }
                if (pscariobat != null) {
                    pscariobat.close();
                }
            }
            //if(embalase>0){ 
            //    tabModeRwJlDr.addRow(new Object[]{true,"","Embalase",":",embalase,1,null,embalase,"Obat"});            
            //}
            //rs.close();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        try {
            psobatlangsung = koneksi.prepareStatement(sqlpsobatlangsung);
            try {
                psobatlangsung.setString(1, norawat);
                rsobatlangsung = psobatlangsung.executeQuery();
                if (rsobatlangsung.next()) {
                    if (cekObat.isSelected() == true) {
                        tabModeRwJlDr.addRow(new Object[]{true, "                           ", "Obat & BHP", ":", rsobatlangsung.getDouble("besar_tagihan"), 1, 0, rsobatlangsung.getDouble("besar_tagihan"), "Obat"});
                        subttl = subttl + rsobatlangsung.getDouble("besar_tagihan");
                    } else {
                        tabModeRwJlDr.addRow(new Object[]{false, "                           ", "Obat & BHP", ":", rsobatlangsung.getDouble("besar_tagihan"), 1, 0, rsobatlangsung.getDouble("besar_tagihan"), "Obat"});
                        subttl = subttl + rsobatlangsung.getDouble("besar_tagihan");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsobatlangsung != null) {
                    rsobatlangsung.close();
                }
                if (psobatlangsung != null) {
                    psobatlangsung.close();
                }
            }
            //rs.close();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        try {
            psobatoperasi = koneksi.prepareStatement(sqlpsobatoperasi);
            try {
                psobatoperasi.setString(1, norawat);
                rsobatoperasi = psobatoperasi.executeQuery();
                while (rsobatoperasi.next()) {
                    if (cekObat.isSelected() == true) {
                        tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsobatoperasi.getString("nm_obat"), ":",
                            rsobatoperasi.getDouble("hargasatuan"), rsobatoperasi.getDouble("jumlah"), 0,
                            rsobatoperasi.getDouble("total"), "Obat"});
                        subttl = subttl + rsobatoperasi.getDouble("total");
                    } else {
                        tabModeRwJlDr.addRow(new Object[]{false, "                           ", rsobatoperasi.getString("nm_obat"), ":",
                            rsobatoperasi.getDouble("hargasatuan"), rsobatoperasi.getDouble("jumlah"), 0,
                            rsobatoperasi.getDouble("total"), "Obat"});
                        subttl = subttl + rsobatoperasi.getDouble("total");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsobatoperasi != null) {
                    rsobatoperasi.close();
                }
                if (psobatoperasi != null) {
                    psobatoperasi.close();
                }
            }
            //rs.close();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        if (subttl > 1) {
            if (cekObat.isSelected() == true) {
                ttlobat = subttl;
                tabModeRwJlDr.addRow(new Object[]{true, "", "Total Obat & BHP : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlObat"});
            } else {
                ttlobat = subttl;
                tabModeRwJlDr.addRow(new Object[]{false, "", "Total Obat & BHP : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlObat"});
            }
        }

        subttl = 0;
        try {
            psreturobat = koneksi.prepareStatement(sqlpsreturobat);
            try {
                psreturobat.setString(1, norawat);
                rsreturobat = psreturobat.executeQuery();
                if (rsreturobat.next()) {
                    if (cekObat.isSelected() == true) {
                        tabModeRwJlDr.addRow(new Object[]{true, "", "Retur Obat :", "", null, null, null, null, "Retur Obat"});
                    } else {
                        tabModeRwJlDr.addRow(new Object[]{false, "", "Retur Obat :", "", null, null, null, null, "Retur Obat"});
                    }
                }
                rsreturobat.beforeFirst();
                while (rsreturobat.next()) {
                    if (cekObat.isSelected() == true) {
                        tabModeRwJlDr.addRow(new Object[]{
                            true, "                           ", rsreturobat.getString("nama_brng"), ":",
                            rsreturobat.getDouble("h_retur"), rsreturobat.getDouble("jml"), 0,
                            rsreturobat.getDouble("ttl"), "Retur Obat"
                        });
                        subttl = subttl + rsreturobat.getDouble("ttl");
                    } else {
                        tabModeRwJlDr.addRow(new Object[]{
                            false, "                           ", rsreturobat.getString("nama_brng"), ":",
                            rsreturobat.getDouble("h_retur"), rsreturobat.getDouble("jml"), 0,
                            rsreturobat.getDouble("ttl"), "Retur Obat"
                        });
                        subttl = subttl + rsreturobat.getDouble("ttl");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsreturobat != null) {
                    rsreturobat.close();
                }
                if (psreturobat != null) {
                    psreturobat.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        if (subttl < 0) {
            if (cekObat.isSelected() == true) {
                ttlretur = subttl;
                tabModeRwJlDr.addRow(new Object[]{true, "", "Total Retur Obat : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlRetur Obat"});
            } else {
                ttlretur = subttl;
                tabModeRwJlDr.addRow(new Object[]{false, "", "Total Retur Obat : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlRetur Obat"});
            }
        }

        if ((ttlobat - ttlretur) > 0) {
            if (tampilkan_ppnobat_ranap.equals("Yes")) {
                ppnobat = Valid.roundUp((ttlobat + ttlretur) * 0.1, 100);
                tabModeRwJlDr.addRow(new Object[]{false, "", "PPN Obat", ":", ppnobat, 1, 0, ppnobat, "Obat"});
                tabModeRwJlDr.addRow(new Object[]{true, "", "Total Obat Bersih : " + Valid.SetAngka3(ttlobat + ttlretur + ppnobat), "", null, null, null, null, "TtlRetur Obat"});
            } else {
                tabModeRwJlDr.addRow(new Object[]{true, "", "Total Obat Bersih : " + Valid.SetAngka3(ttlobat + ttlretur), "", null, null, null, null, "TtlRetur Obat"});
            }
        }

        if (detailbhp > 0) {
            tabModeRwJlDr.addRow(new Object[]{true, x + ". Paket Obat/BHP", ":", "", null, null, null, detailbhp, "Ralan Dokter"});
            x++;
        }
    }

    private void prosesCariKamar() {
        tabModeRwJlDr.addRow(new Object[]{true, "Ruang Perawatan Inap", ":", "", null, null, null, null, "Kamar"});
        subttl = 0;
        try {
            pskamarin = koneksi.prepareStatement(sqlpskamarin);
            try {
                pskamarin.setString(1, TNoRw.getText());
                rskamarin = pskamarin.executeQuery();
                while (rskamarin.next()) {
                    tamkur = 0;
                    pstamkur = koneksi.prepareStatement(sqlpstamkur);
                    try {
                        pstamkur.setString(1, TNoRw.getText());
                        pstamkur.setString(2, rskamarin.getString("kd_kamar") + ", " + rskamarin.getString("nm_bangsal"));
                        pstamkur.setString(3, "Kamar");
                        rstamkur = pstamkur.executeQuery();
                        if (rstamkur.next()) {
                            tamkur = rstamkur.getDouble(1);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rstamkur != null) {
                            rstamkur.close();
                        }
                        if (pstamkur != null) {
                            pstamkur.close();
                        }
                    }
                    if (!norawatbayi.equals("")) {
                        if (persenbayi > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", rskamarin.getString("kd_kamar") + ", " + rskamarin.getString("nm_bangsal") + " (Ibu)", ":",
                                rskamarin.getDouble("trf_kamar"), rskamarin.getDouble("lama"), tamkur, (rskamarin.getDouble("total") + tamkur), "Kamar"});
                            subttl = subttl + rskamarin.getDouble("total") + tamkur;

                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", rskamarin.getString("kd_kamar") + ", " + rskamarin.getString("nm_bangsal") + " (Bayi)", ":",
                                (rskamarin.getDouble("trf_kamar") * (persenbayi / 100)), rskamarin.getDouble("lama"), tamkur, ((rskamarin.getDouble("total") * (persenbayi / 100)) + tamkur), "Kamar"});
                            subttl = subttl + (rskamarin.getDouble("total") * (persenbayi / 100)) + tamkur;
                        } else {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", rskamarin.getString("kd_kamar") + ", " + rskamarin.getString("nm_bangsal"), ":",
                                rskamarin.getDouble("trf_kamar"), rskamarin.getDouble("lama"), tamkur, (rskamarin.getDouble("total") + tamkur), "Kamar"});
                            subttl = subttl + rskamarin.getDouble("total") + tamkur;
                        }
                    } else {
                        tabModeRwJlDr.addRow(new Object[]{true, "                           ", rskamarin.getString("kd_kamar") + ", " + rskamarin.getString("nm_bangsal"), ":",
                            rskamarin.getDouble("trf_kamar"), rskamarin.getDouble("lama"), tamkur, (rskamarin.getDouble("total") + tamkur), "Kamar"});
                        subttl = subttl + rskamarin.getDouble("total") + tamkur;
                    }

                    psbiayasekali = koneksi.prepareStatement(sqlpsbiayasekali);
                    try {
                        psbiayasekali.setString(1, rskamarin.getString("kd_kamar"));
                        rsbiayasekali = psbiayasekali.executeQuery();
                        if (rsbiayasekali.next()) {
                            tabModeRwJlDr.addRow(new Object[]{true, "-", "Biaya Kamar :", "", null, null, null, null, "Kamar"});
                        }
                        rsbiayasekali.beforeFirst();
                        z = 1;
                        while (rsbiayasekali.next()) {
                            tamkur = 0;
                            pstamkur = koneksi.prepareStatement(sqlpstamkur);
                            try {
                                pstamkur.setString(1, TNoRw.getText());
                                pstamkur.setString(2, rsbiayasekali.getString("nama_biaya"));
                                pstamkur.setString(3, "Kamar");
                                rstamkur = pstamkur.executeQuery();
                                if (rstamkur.next()) {
                                    tamkur = rstamkur.getDouble(1);
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rstamkur != null) {
                                    rstamkur.close();
                                }
                                if (pstamkur != null) {
                                    pstamkur.close();
                                }
                            }
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsbiayasekali.getString("nama_biaya"), ":",
                                rsbiayasekali.getDouble("besar_biaya"), 1, tamkur, (rsbiayasekali.getDouble("total") + tamkur), "Kamar"});
                            z++;
                            subttl = subttl + rsbiayasekali.getDouble("total") + tamkur;
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsbiayasekali != null) {
                            rsbiayasekali.close();
                        }
                        if (psbiayasekali != null) {
                            psbiayasekali.close();
                        }
                    }

                    psbiayaharian = koneksi.prepareStatement(sqlpsbiayaharian);
                    try {
                        psbiayaharian.setDouble(1, rskamarin.getDouble("lama"));
                        psbiayaharian.setString(2, rskamarin.getString("kd_kamar"));
                        rsbiayaharian = psbiayaharian.executeQuery();
                        if (rsbiayaharian.next()) {
                            tabModeRwJlDr.addRow(new Object[]{true, "-", "Biaya Harian :", "", null, null, null, null, "Harian"});
                        }
                        rsbiayaharian.beforeFirst();
                        z = 1;
                        while (rsbiayaharian.next()) {
                            tamkur = 0;
                            pstamkur = koneksi.prepareStatement(sqlpstamkur);
                            try {
                                pstamkur.setString(1, TNoRw.getText());
                                pstamkur.setString(2, rsbiayaharian.getString("nama_biaya"));
                                pstamkur.setString(3, "Harian");
                                rstamkur = pstamkur.executeQuery();
                                if (rstamkur.next()) {
                                    tamkur = rstamkur.getDouble(1);
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rstamkur != null) {
                                    rstamkur.close();
                                }
                                if (pstamkur != null) {
                                    pstamkur.close();
                                }
                            }

                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsbiayaharian.getString("nama_biaya"), ":",
                                rsbiayaharian.getDouble("besar_biaya"), (rsbiayaharian.getDouble("jml") * rskamarin.getDouble("lama")), tamkur, (tamkur + rsbiayaharian.getDouble("total")), "Harian"});
                            z++;
                            subttl = subttl + rsbiayaharian.getDouble("total") + tamkur;
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsbiayaharian != null) {
                            rsbiayaharian.close();
                        }
                        if (psbiayaharian != null) {
                            psbiayaharian.close();
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rskamarin != null) {
                    rskamarin.close();
                }
                if (pskamarin != null) {
                    pskamarin.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        if (subttl > 1) {
            tabModeRwJlDr.addRow(new Object[]{true, "", "Total Kamar Inap : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlKamar"});
        }
    }

    private void prosesResepPulang(String norawat) {
        if (Sequel.cariInteger("select count(resep_pulang.kode_brng) from resep_pulang where resep_pulang.no_rawat=?", norawat) > 0) {
            tabModeRwJlDr.addRow(new Object[]{true, "Resep Pulang", ":", "", null, null, null, null, "Resep Pulang"});
        } else {
            tabModeRwJlDr.addRow(new Object[]{false, "Resep Pulang", ":", "", null, null, null, null, "Resep Pulang"});
        }

        x++;
        subttl = 0;
        try {
            psreseppulang = koneksi.prepareStatement(sqlpsreseppulang);
            try {
                psreseppulang.setString(1, norawat);
                rsreseppulang = psreseppulang.executeQuery();
                while (rsreseppulang.next()) {
                    tamkur = 0;
                    pstamkur = koneksi.prepareStatement(sqlpstamkur);
                    try {
                        pstamkur.setString(1, TNoRw.getText());
                        pstamkur.setString(2, rsreseppulang.getString("nama_brng") + " " + rsreseppulang.getString("dosis"));
                        pstamkur.setString(3, "Resep Pulang");
                        rstamkur = pstamkur.executeQuery();
                        if (rstamkur.next()) {
                            tamkur = rstamkur.getDouble(1);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rstamkur != null) {
                            rstamkur.close();
                        }
                        if (pstamkur != null) {
                            pstamkur.close();
                        }
                    }
                    tabModeRwJlDr.addRow(new Object[]{false, "                           ", rsreseppulang.getString("nama_brng") + " " + rsreseppulang.getString("dosis"), ":",
                        rsreseppulang.getDouble("harga"), rsreseppulang.getDouble("jml_barang"), tamkur, (tamkur + rsreseppulang.getDouble("total")), "Resep Pulang"});
                    subttl = subttl + rsreseppulang.getDouble("total") + tamkur;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsreseppulang != null) {
                    rsreseppulang.close();
                }
                if (psreseppulang != null) {
                    psreseppulang.close();
                }
            }
            //rs.close();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        if (subttl > 1) {
            tabModeRwJlDr.addRow(new Object[]{true, "", "Total Resep Pulang : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlResep Pulang"});
        }
    }

    private void isHitung() {
        ttl = 0;
        ttlLaborat = 0;
        ttlRadiologi = 0;
        ttlOperasi = 0;
        ttlObat = 0;
        ttlRanap_Dokter = 0;
        ttlRanap_Paramedis = 0;
        ttlRalan_Dokter = 0;
        ttlRalan_Paramedis = 0;
        ttlTambahan = 0;
        ttlPotongan = 0;
        ttlKamar = 0;
        ttlRegistrasi = 0;
        ttlHarian = 0;
        ttlRetur_Obat = 0;
        ttlResep_Pulang = 0;
        ttlService = 0;
        int row = tabModeRwJlDr.getRowCount();
        if (row > 0) {
            for (int r = 0; r < row; r++) {
                y = 0;
                try {
                    y = Double.parseDouble(tabModeRwJlDr.getValueAt(r, 7).toString());
                } catch (Exception e) {
                    y = 0;
                }
                switch (tabModeRwJlDr.getValueAt(r, 8).toString()) {
                    case "Laborat":
                        ttlLaborat = ttlLaborat + y;
                        break;
                    case "Radiologi":
                        ttlRadiologi = ttlRadiologi + y;
                        break;
                    case "Operasi":
                        ttlOperasi = ttlOperasi + y;
                        break;
                    case "Obat":
                        ttlObat = ttlObat + y;
                        break;
                    case "Ranap Dokter":
                        ttlRanap_Dokter = ttlRanap_Dokter + y;
                        break;
                    case "Ranap Dokter Paramedis":
                        ttlRanap_Dokter = ttlRanap_Dokter + y;
                        break;
                    case "Ranap Paramedis":
                        ttlRanap_Paramedis = ttlRanap_Paramedis + y;
                        break;
                    case "Ralan Dokter":
                        ttlRalan_Dokter = ttlRalan_Dokter + y;
                        break;
                    case "Ralan Dokter Paramedis":
                        ttlRalan_Dokter = ttlRalan_Dokter + y;
                        break;
                    case "Ralan Paramedis":
                        ttlRalan_Paramedis = ttlRalan_Paramedis + y;
                        break;
                    case "Tambahan":
                        ttlTambahan = ttlTambahan + y;
                        break;
                    case "Potongan":
                        ttlPotongan = ttlPotongan + y;
                        break;
                    case "Kamar":
                        ttlKamar = ttlKamar + y;
                        break;
                    case "Registrasi":
                        ttlRegistrasi = ttlRegistrasi + y;
                        break;
                    case "Harian":
                        ttlHarian = ttlHarian + y;
                        break;
                    case "Retur Obat":
                        ttlRetur_Obat = ttlRetur_Obat + y;
                        break;
                    case "Resep Pulang":
                        ttlResep_Pulang = ttlResep_Pulang + y;
                        break;
                    case "Service":
                        ttlService = ttlService + y;
                        break;
                }
                ttl = ttl + y;
            }

            try {
                i = 0;
                pscekbilling = koneksi.prepareStatement(sqlpscekbilling);
                try {
                    pscekbilling.setString(1, TNoRw.getText());
                    rscekbilling = pscekbilling.executeQuery();
                    if (rscekbilling.next()) {
                        i = rscekbilling.getInt(1);
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rscekbilling != null) {
                        rscekbilling.close();
                    }
                    if (pscekbilling != null) {
                        pscekbilling.close();
                    }
                }
                if (i == 0) {
                    if (ChkPiutang.isSelected() == false) {
                        psservice = koneksi.prepareStatement("select * from set_service_ranap");
                    } else {
                        psservice = koneksi.prepareStatement("select * from set_service_ranap_piutang");
                    }

                    try {
                        ttlService = 0;
                        laboratserv = 0;
                        radiologiserv = 0;
                        operasiserv = 0;
                        obatserv = 0;
                        ranap_dokterserv = 0;
                        ranap_paramedisserv = 0;
                        ralan_dokterserv = 0;
                        ralan_paramedisserv = 0;
                        tambahanserv = 0;
                        potonganserv = 0;
                        kamarserv = 0;
                        registrasiserv = 0;
                        harianserv = 0;
                        retur_Obatserv = 0;
                        resep_Pulangserv = 0;

                        rsservice = psservice.executeQuery();
                        if (rsservice.next()) {
                            if (rsservice.getString("laborat").equals("Yes")) {
                                laboratserv = ttlLaborat;
                            }
                            if (rsservice.getString("radiologi").equals("Yes")) {
                                radiologiserv = ttlRadiologi;
                            }
                            if (rsservice.getString("operasi").equals("Yes")) {
                                operasiserv = ttlOperasi;
                            }
                            if (rsservice.getString("obat").equals("Yes")) {
                                obatserv = ttlObat;
                            }
                            if (rsservice.getString("ranap_dokter").equals("Yes")) {
                                ranap_dokterserv = ttlRanap_Dokter;
                            }
                            if (rsservice.getString("ranap_paramedis").equals("Yes")) {
                                ranap_paramedisserv = ttlRanap_Paramedis;
                            }
                            if (rsservice.getString("ralan_dokter").equals("Yes")) {
                                ralan_dokterserv = ttlRalan_Dokter;
                            }
                            if (rsservice.getString("ralan_paramedis").equals("Yes")) {
                                ralan_paramedisserv = ttlRalan_Paramedis;
                            }
                            if (rsservice.getString("tambahan").equals("Yes")) {
                                tambahanserv = ttlTambahan;
                            }
                            if (rsservice.getString("potongan").equals("Yes")) {
                                potonganserv = ttlPotongan;
                            }
                            if (rsservice.getString("kamar").equals("Yes")) {
                                kamarserv = ttlKamar;
                            }
                            if (rsservice.getString("registrasi").equals("Yes")) {
                                registrasiserv = ttlRegistrasi;
                            }
                            if (rsservice.getString("harian").equals("Yes")) {
                                harianserv = ttlHarian;
                            }
                            if (rsservice.getString("retur_Obat").equals("Yes")) {
                                retur_Obatserv = ttlRetur_Obat;
                            }
                            if (rsservice.getString("resep_Pulang").equals("Yes")) {
                                resep_Pulangserv = ttlResep_Pulang;
                            }
                            ttlService = Valid.roundUp((rsservice.getDouble("besar") / 100)
                                    * (laboratserv + radiologiserv + operasiserv + obatserv
                                    + ranap_dokterserv + ranap_paramedisserv + ralan_dokterserv
                                    + ralan_paramedisserv + tambahanserv + potonganserv
                                    + kamarserv + registrasiserv + harianserv + retur_Obatserv + resep_Pulangserv), 100);
                            ttl = ttl + ttlService;
                            tabModeRwJlDr.addRow(new Object[]{true, rsservice.getString("nama_service"), ":", "", null, null, null, ttlService, "Service"});
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsservice != null) {
                            rsservice.close();
                        }
                        if (psservice != null) {
                            psservice.close();
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
            TtlSemua.setText(Valid.SetAngka3(Valid.roundUp(ttl, 0)));
            ttl = Valid.roundUp(ttl, 0);
        }
    }

    public void isCek(String kdkamarnya) {
        kdkamar = kdkamarnya;
        Valid.tabelKosong(tabModeAkunBayar);
        Valid.tabelKosong(tabModeAkunPiutang);
        DTPTgl.setDate(new Date());
        tglNota.setDate(new Date());
        BtnSimpan.setEnabled(akses.getbilling_ranap());
        BtnNota.setEnabled(akses.getbilling_ranap());
        BtnNota.setEnabled(akses.gettombolnota_billing());
        BtnView.setEnabled(akses.getbilling_ranap());
        MnRawatJalan.setEnabled(akses.gettindakan_ralan());
        MnInputObat.setEnabled(akses.getberi_obat());
        MnInputResepPulang.setEnabled(akses.getresep_pulang());
        MnPeriksaLab.setEnabled(akses.getperiksa_lab());
        MnPeriksaRadiologi.setEnabled(akses.getperiksa_radiologi());
        MnTambahan.setEnabled(akses.gettambahan_biaya());
        MnPotongan.setEnabled(akses.getpotongan_biaya());
        MnUbahLamaInap.setEnabled(akses.getkamar_inap());
        MnObatLangsung.setEnabled(akses.getberi_obat());
        MnReturJual.setEnabled(akses.getretur_dari_pembeli());
        MnHapusTagihan.setEnabled(akses.getbilling_ranap());
        MnPenjab.setEnabled(akses.getbilling_ranap());
        MnNamaTelahTerima.setEnabled(akses.getbilling_ranap());
        MnBayi.setEnabled(akses.getbilling_ranap());
        MnTagihanOperasi.setEnabled(akses.getoperasi());
        MnDataObat.setEnabled(akses.getberi_obat());
        MnDataResepPulang.setEnabled(akses.getresep_pulang());
        MnCariPeriksaLab.setEnabled(akses.getperiksa_lab());
        MnCariRadiologi.setEnabled(akses.getperiksa_radiologi());
        MnSelisihTarif.setEnabled(akses.getselisih_tarif_bpjs());
        MnPiutangPasien.setEnabled(akses.getbayar_piutang());
        ppPerbaikiHakKelas.setEnabled(akses.getadmin());

        if (Sequel.cariIsi("select tampilkan_tombol_nota_ranap from set_nota").equals("Yes")) {
            BtnNota.setVisible(true);
        } else {
            BtnNota.setVisible(akses.getadmin());            
        }
    }

    public void isKembali() {
        bayar = 0;
        total = 0;
        ppn = 0;
        besarppn = 0;
        tagihanppn = 0;
        y = 0;
        piutang = 0;
        kekurangan = 0;
        countbayar = 0;
        row2 = tabModeAkunBayar.getRowCount();
        for (r = 0; r < row2; r++) {
            if (!tabModeAkunBayar.getValueAt(r, 2).toString().equals("")) {
                countbayar++;
                try {
                    bayar = bayar + Double.parseDouble(tabModeAkunBayar.getValueAt(r, 2).toString());
                } catch (Exception e) {
                    bayar = bayar + 0;
                }
            }

            if (!tabModeAkunBayar.getValueAt(r, 4).toString().equals("")) {
                try {
                    besarppn = besarppn + Valid.roundUp(Double.parseDouble(tabModeAkunBayar.getValueAt(r, 4).toString()), 100);
                } catch (Exception e) {
                    besarppn = besarppn + 0;
                }
            }
        }

        row2 = tabModeAkunPiutang.getRowCount();
        for (r = 0; r < row2; r++) {
            if (!tabModeAkunPiutang.getValueAt(r, 3).toString().equals("")) {
                try {
                    piutang = piutang + Double.parseDouble(tabModeAkunPiutang.getValueAt(r, 3).toString());
                } catch (Exception e) {
                    piutang = piutang + 0;
                }
            }
        }

        if ((ttl) > 0) {
            total = ttl;
        }

        tagihanppn = besarppn + total;
        TagihanPPn.setText(Valid.SetAngka3(Valid.roundUp(tagihanppn, 0)));
        tagihanppn = Valid.roundUp(tagihanppn, 0);
        realcostRS.setText(Valid.SetAngka2(Valid.roundUp(tagihanppn, 0)));

        if (piutang <= 0) {
            kekurangan = (bayar + uangdeposit + besarppn) - tagihanppn;
            jLabel5.setText("Bayar : Rp.");
            if (kekurangan < 0) {
                jLabel6.setText("Kekurangan : Rp.");
            } else {
                jLabel6.setText("Kembali : Rp.");
            }

            TKembali.setText(Valid.SetAngka3(Valid.roundUp(kekurangan, 0)));
            kekurangan = Valid.roundUp(kekurangan, 0);
        } else {
            kekurangan = (tagihanppn - (bayar + uangdeposit + besarppn) - piutang) * -1;
            jLabel5.setText("Uang Muka : Rp.");
            if (kekurangan > 0) {
                jLabel6.setText("Kelebihan : Rp.");
            } else {
                jLabel6.setText("Kekurangan : Rp.");
            }

            TKembali.setText(Valid.SetAngka3(Valid.roundUp(kekurangan, 0)));
            kekurangan = Valid.roundUp(kekurangan, 0);
        }
    }

    private void prosesCariTindakan(String norawat) {
        detailjs = 0;
        detailbhp = 0;
        try {
            tabModeRwJlDr.addRow(new Object[]{true, "Rincian Biaya", ":", "", null, null, null, null, "Ranap Dokter"});
            pskategori = koneksi.prepareStatement(sqlpskategori);
            try {
                rskategori = pskategori.executeQuery();
                x = 1;
                while (rskategori.next()) {
                    psralandokter = koneksi.prepareStatement(sqlpsralandokter);
                    psralandrpr = koneksi.prepareStatement(sqlpsralandrpr);
                    psranapdokter = koneksi.prepareStatement(sqlpsranapdokter);
                    psranapdrpr = koneksi.prepareStatement(sqlpsranapdrpr);
                    psralanperawat = koneksi.prepareStatement(sqlpsralanperawat);
                    psranapperawat = koneksi.prepareStatement(sqlpsranapperawat);
                    try {
                        psralandokter.setString(1, norawat);
                        psralandokter.setString(2, rskategori.getString(1));
                        rsralandokter = psralandokter.executeQuery();

                        psralandrpr.setString(1, norawat);
                        psralandrpr.setString(2, rskategori.getString(1));
                        rsralandrpr = psralandrpr.executeQuery();

                        psranapdokter.setString(1, norawat);
                        psranapdokter.setString(2, rskategori.getString(1));
                        rsranapdokter = psranapdokter.executeQuery();

                        psranapdrpr.setString(1, norawat);
                        psranapdrpr.setString(2, rskategori.getString(1));
                        rsranapdrpr = psranapdrpr.executeQuery();

                        psralanperawat.setString(1, norawat);
                        psralanperawat.setString(2, rskategori.getString(1));
                        rsralanperawat = psralanperawat.executeQuery();

                        psranapperawat.setString(1, norawat);
                        psranapperawat.setString(2, rskategori.getString(1));
                        rsranapperawat = psranapperawat.executeQuery();

                        subttl = 0;
                        if (chkRalan.isSelected() == true) {
                            if (rsralandrpr.next() || rsralandokter.next() || rsralanperawat.next()) {
                                tabModeRwJlDr.addRow(new Object[]{true, x + ". " + rskategori.getString(2), ":", "", null, null, null, null, "Ranap Dokter"});
                                x++;
                            }
                            rsralandrpr.beforeFirst();
                            while (rsralandrpr.next()) {
                                tamkur = 0;
                                pstamkur = koneksi.prepareStatement(sqlpstamkur);
                                try {
                                    pstamkur.setString(1, TNoRw.getText());
                                    pstamkur.setString(2, rsralandrpr.getString("nm_perawatan"));
                                    pstamkur.setString(3, "Ralan Dokter Paramedis");
                                    rstamkur = pstamkur.executeQuery();
                                    if (rstamkur.next()) {
                                        tamkur = rstamkur.getDouble(1);
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (rstamkur != null) {
                                        rstamkur.close();
                                    }
                                    if (pstamkur != null) {
                                        pstamkur.close();
                                    }
                                }

                                if (rinciandokterranap.equals("Yes")) {
                                    detailbhp = detailbhp + rsralandrpr.getDouble("totalbhp");
                                    detailjs = detailjs + rsralandrpr.getDouble("totalmaterial") + rsralandrpr.getDouble("totaltarif_tindakanpr");
                                    tabModeRwJlDr.addRow(new Object[]{true, "", rsralandrpr.getString("nm_perawatan"), ":",
                                        rsralandrpr.getDouble("tarif_tindakandr"), rsralandrpr.getDouble("jml"), tamkur, (rsralandrpr.getDouble("totaltarif_tindakandr") + tamkur), "Ralan Dokter Paramedis"});
                                    subttl = subttl + rsralandrpr.getDouble("totaltarif_tindakandr") + tamkur;
                                } else {
                                    tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsralandrpr.getString("nm_perawatan"), ":",
                                        rsralandrpr.getDouble("total_byrdr"), rsralandrpr.getDouble("jml"), tamkur, (tamkur + rsralandrpr.getDouble("biaya")), "Ralan Dokter Paramedis"});
                                    subttl = subttl + rsralandrpr.getDouble("biaya") + tamkur;
                                }
                            }

                            rsralandokter.beforeFirst();
                            while (rsralandokter.next()) {
                                tamkur = 0;
                                pstamkur = koneksi.prepareStatement(sqlpstamkur);
                                try {
                                    pstamkur.setString(1, TNoRw.getText());
                                    pstamkur.setString(2, rsralandokter.getString("nm_perawatan"));
                                    pstamkur.setString(3, "Ralan Dokter");
                                    rstamkur = pstamkur.executeQuery();
                                    if (rstamkur.next()) {
                                        tamkur = rstamkur.getDouble(1);
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (rstamkur != null) {
                                        rstamkur.close();
                                    }
                                    if (pstamkur != null) {
                                        pstamkur.close();
                                    }
                                }

                                if (rinciandokterranap.equals("Yes")) {
                                    detailbhp = detailbhp + rsralandokter.getDouble("totalbhp");
                                    detailjs = detailjs + rsralandokter.getDouble("totalmaterial");
                                    tabModeRwJlDr.addRow(new Object[]{true, "", rsralandokter.getString("nm_perawatan"), ":",
                                        rsralandokter.getDouble("tarif_tindakandr"), rsralandokter.getDouble("jml"), tamkur, (rsralandokter.getDouble("totaltarif_tindakandr") + tamkur), "Ralan Dokter"});
                                    subttl = subttl + rsralandokter.getDouble("totaltarif_tindakandr") + tamkur;
                                } else {
                                    tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsralandokter.getString("nm_perawatan"), ":",
                                        rsralandokter.getDouble("total_byrdr"), rsralandokter.getDouble("jml"), tamkur, (tamkur + rsralandokter.getDouble("biaya")), "Ralan Dokter"});
                                    subttl = subttl + rsralandokter.getDouble("biaya") + tamkur;
                                }
                            }

//                            rsralandrpr.beforeFirst();
//                            while (rsralandrpr.next()) {
//                                tamkur = 0;
//                                pstamkur = koneksi.prepareStatement(sqlpstamkur);
//                                try {
//                                    pstamkur.setString(1, TNoRw.getText());
//                                    pstamkur.setString(2, rsralandrpr.getString("nm_perawatan"));
//                                    pstamkur.setString(3, "Ralan Dokter Paramedis");
//                                    rstamkur = pstamkur.executeQuery();
//                                    if (rstamkur.next()) {
//                                        tamkur = rstamkur.getDouble(1);
//                                    }
//                                } catch (Exception e) {
//                                    System.out.println("Notifikasi : " + e);
//                                } finally {
//                                    if (rstamkur != null) {
//                                        rstamkur.close();
//                                    }
//                                    if (pstamkur != null) {
//                                        pstamkur.close();
//                                    }
//                                }
//
//                                if (rinciandokterranap.equals("Yes")) {
//                                    detailbhp = detailbhp + rsralandrpr.getDouble("totalbhp");
//                                    detailjs = detailjs + rsralandrpr.getDouble("totalmaterial") + rsralandrpr.getDouble("totaltarif_tindakanpr");
//                                    tabModeRwJlDr.addRow(new Object[]{true, "", rsralandrpr.getString("nm_perawatan"), ":",
//                                        rsralandrpr.getDouble("tarif_tindakandr"), rsralandrpr.getDouble("jml"), tamkur, (rsralandrpr.getDouble("totaltarif_tindakandr") + tamkur), "Ralan Dokter Paramedis"});
//                                    subttl = subttl + rsralandrpr.getDouble("totaltarif_tindakandr") + tamkur;
//                                } else {
//                                    tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsralandrpr.getString("nm_perawatan"), ":",
//                                        rsralandrpr.getDouble("total_byrdr"), rsralandrpr.getDouble("jml"), tamkur, (tamkur + rsralandrpr.getDouble("biaya")), "Ralan Dokter Paramedis"});
//                                    subttl = subttl + rsralandrpr.getDouble("biaya") + tamkur;
//                                }
//                            }
                            rsralanperawat.beforeFirst();
                            while (rsralanperawat.next()) {
                                tamkur = 0;
                                pstamkur = koneksi.prepareStatement(sqlpstamkur);
                                try {
                                    pstamkur.setString(1, TNoRw.getText());
                                    pstamkur.setString(2, rsralanperawat.getString("nm_perawatan"));
                                    pstamkur.setString(3, "Ralan Paramedis");
                                    rstamkur = pstamkur.executeQuery();
                                    if (rstamkur.next()) {
                                        tamkur = rstamkur.getDouble(1);
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (rstamkur != null) {
                                        rstamkur.close();
                                    }
                                    if (pstamkur != null) {
                                        pstamkur.close();
                                    }
                                }

                                tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsralanperawat.getString("nm_perawatan"), ":",
                                    rsralanperawat.getDouble("total_byrpr"), rsralanperawat.getDouble("jml"), tamkur, (tamkur + rsralanperawat.getDouble("biaya")), "Ralan Paramedis"});
                                subttl = subttl + rsralanperawat.getDouble("biaya") + tamkur;
                            }
                        }

                        if (chkRanap.isSelected() == true) {
                            if (rsranapdrpr.next() || rsranapdokter.next() || rsranapperawat.next()) {
                                tabModeRwJlDr.addRow(new Object[]{true, x + ". " + rskategori.getString(2), ":", "", null, null, null, null, "Ranap Dokter"});
                                x++;
                            }
                            rsranapdokter.beforeFirst();
                            while (rsranapdokter.next()) {
                                tamkur = 0;
                                pstamkur = koneksi.prepareStatement(sqlpstamkur);
                                try {
                                    pstamkur.setString(1, TNoRw.getText());
                                    pstamkur.setString(2, rsranapdokter.getString("nm_perawatan"));
                                    pstamkur.setString(3, "Ranap Dokter");
                                    rstamkur = pstamkur.executeQuery();
                                    if (rstamkur.next()) {
                                        tamkur = rstamkur.getDouble(1);
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (rstamkur != null) {
                                        rstamkur.close();
                                    }
                                    if (pstamkur != null) {
                                        pstamkur.close();
                                    }
                                }

                                if (rinciandokterranap.equals("Yes")) {
                                    detailbhp = detailbhp + rsranapdokter.getDouble("totalbhp");
                                    detailjs = detailjs + rsranapdokter.getDouble("totalmaterial");
                                    tabModeRwJlDr.addRow(new Object[]{true, "", rsranapdokter.getString("nm_perawatan"), ":",
                                        rsranapdokter.getDouble("tarif_tindakandr"), rsranapdokter.getDouble("jml"), tamkur, (rsranapdokter.getDouble("totaltarif_tindakandr") + tamkur), "Ranap Dokter"});
                                    subttl = subttl + rsranapdokter.getDouble("totaltarif_tindakandr") + tamkur;
                                } else {
                                    tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsranapdokter.getString("nm_perawatan"), ":",
                                        rsranapdokter.getDouble("total_byrdr"), rsranapdokter.getDouble("jml"), tamkur, (tamkur + rsranapdokter.getDouble("biaya")), "Ranap Dokter"});
                                    subttl = subttl + rsranapdokter.getDouble("biaya") + tamkur;
                                }
                            }

                            rsranapdrpr.beforeFirst();
                            while (rsranapdrpr.next()) {
                                tamkur = 0;
                                pstamkur = koneksi.prepareStatement(sqlpstamkur);
                                try {
                                    pstamkur.setString(1, TNoRw.getText());
                                    pstamkur.setString(2, rsranapdrpr.getString("nm_perawatan"));
                                    pstamkur.setString(3, "Ranap Dokter Paramedis");
                                    rstamkur = pstamkur.executeQuery();
                                    if (rstamkur.next()) {
                                        tamkur = rstamkur.getDouble(1);
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (rstamkur != null) {
                                        rstamkur.close();
                                    }
                                    if (pstamkur != null) {
                                        pstamkur.close();
                                    }
                                }

                                if (rinciandokterranap.equals("Yes")) {
                                    detailbhp = detailbhp + rsranapdrpr.getDouble("totalbhp");
                                    detailjs = detailjs + rsranapdrpr.getDouble("totalmaterial") + rsranapdrpr.getDouble("totaltarif_tindakanpr");
                                    tabModeRwJlDr.addRow(new Object[]{true, "", rsranapdrpr.getString("nm_perawatan"), ":",
                                        rsranapdrpr.getDouble("tarif_tindakandr"), rsranapdrpr.getDouble("jml"), tamkur, (rsranapdrpr.getDouble("totaltarif_tindakandr") + tamkur), "Ranap Dokter Paramedis"});
                                    subttl = subttl + rsranapdrpr.getDouble("totaltarif_tindakandr") + tamkur;
                                } else {
                                    tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsranapdrpr.getString("nm_perawatan"), ":",
                                        rsranapdrpr.getDouble("total_byrdr"), rsranapdrpr.getDouble("jml"), tamkur, (tamkur + rsranapdrpr.getDouble("biaya")), "Ranap Dokter Paramedis"});
                                    subttl = subttl + rsranapdrpr.getDouble("biaya") + tamkur;
                                }

                            }

                            rsranapperawat.beforeFirst();
                            while (rsranapperawat.next()) {
                                tamkur = 0;
                                pstamkur = koneksi.prepareStatement(sqlpstamkur);
                                try {
                                    pstamkur.setString(1, TNoRw.getText());
                                    pstamkur.setString(2, rsranapperawat.getString("nm_perawatan"));
                                    pstamkur.setString(3, "Ranap Paramedis");
                                    rstamkur = pstamkur.executeQuery();
                                    if (rstamkur.next()) {
                                        tamkur = rstamkur.getDouble(1);
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (rstamkur != null) {
                                        rstamkur.close();
                                    }
                                    if (pstamkur != null) {
                                        pstamkur.close();
                                    }
                                }
                                tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsranapperawat.getString("nm_perawatan"), ":",
                                    rsranapperawat.getDouble("total_byrpr"), rsranapperawat.getDouble("jml"), tamkur, (tamkur + rsranapperawat.getDouble("biaya")), "Ranap Paramedis"});
                                subttl = subttl + rsranapperawat.getDouble("biaya") + tamkur;
                            }
                        }

                        if (subttl > 1) {
                            tabModeRwJlDr.addRow(new Object[]{true, "", "Total " + rskategori.getString(2) + " : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlRanap Dokter"});
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsralandokter != null) {
                            rsralandokter.close();
                        }
                        if (rsralandrpr != null) {
                            rsralandrpr.close();
                        }
                        if (rsranapdokter != null) {
                            rsranapdokter.close();
                        }
                        if (rsranapdrpr != null) {
                            rsranapdrpr.close();
                        }
                        if (rsralanperawat != null) {
                            rsralanperawat.close();
                        }
                        if (rsranapperawat != null) {
                            rsranapperawat.close();
                        }
                        if (psralandokter != null) {
                            psralandokter.close();
                        }
                        if (psralandrpr != null) {
                            psralandrpr.close();
                        }
                        if (psranapdokter != null) {
                            psranapdokter.close();
                        }
                        if (psranapdrpr != null) {
                            psranapdrpr.close();
                        }
                        if (psralanperawat != null) {
                            psralanperawat.close();
                        }
                        if (psranapperawat != null) {
                            psranapperawat.close();
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rskategori != null) {
                    rskategori.close();
                }
                if (pskategori != null) {
                    pskategori.close();
                }
            }

            subttl = 0;
            psperiksalab = koneksi.prepareStatement(
                    "select jns_perawatan_lab.nm_perawatan, count(periksa_lab.kd_jenis_prw) as jml,periksa_lab.biaya as biaya, "
                    + " sum(periksa_lab.biaya) as total,jns_perawatan_lab.kd_jenis_prw "
                    + " from periksa_lab inner join jns_perawatan_lab "
                    + " on jns_perawatan_lab.kd_jenis_prw=periksa_lab.kd_jenis_prw where "
                    + " periksa_lab.no_rawat=? and periksa_lab.status like ? group by periksa_lab.kd_jenis_prw  ");
            try {
                psperiksalab.setString(1, norawat);
                if ((chkRalan.isSelected() == true) && (chkRanap.isSelected() == true)) {
                    psperiksalab.setString(2, "%%");
                } else if ((chkRalan.isSelected() == true) && (chkRanap.isSelected() == false)) {
                    psperiksalab.setString(2, "%Ralan%");
                } else if ((chkRalan.isSelected() == false) && (chkRanap.isSelected() == true)) {
                    psperiksalab.setString(2, "%Ranap%");
                } else if ((chkRalan.isSelected() == false) && (chkRanap.isSelected() == false)) {
                    psperiksalab.setString(2, "%Kosong%");
                }
                rsperiksalab = psperiksalab.executeQuery();
                if (rsperiksalab.next()) {
                    tabModeRwJlDr.addRow(new Object[]{true, x + ". Pemeriksaan Lab", ":", "", null, null, null, null, "Laborat"});
                    x++;
                    psdetaillab = koneksi.prepareStatement(
                            "SELECT tl.Pemeriksaan,dpl.biaya_item,count(tl.Pemeriksaan) jumlah,sum(dpl.biaya_item) total "
                            + "FROM detail_periksa_lab dpl INNER JOIN template_laboratorium tl ON tl.id_template = dpl.id_template "
                            + "WHERE dpl.no_rawat = ? group by tl.Pemeriksaan ");
                    try {
                        psdetaillab.setString(1, norawat);
//                        psdetaillab.setString(2, rsperiksalab.getString("kd_jenis_prw"));
                        rsdetaillab = psdetaillab.executeQuery();
                        lab = 0;
                        while (rsdetaillab.next()) {
                            lab = 0;
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsdetaillab.getString("Pemeriksaan"), ":",
                                rsdetaillab.getDouble("biaya_item"), rsdetaillab.getDouble("jumlah"), lab, (rsdetaillab.getDouble("total") + lab), "Laborat"});
                            subttl = subttl + rsdetaillab.getDouble("total") + lab;
                        }
                    } catch (Exception e) {
                        System.out.println("Notif Detail Lab : " + e);
                    } finally {
                        if (rsdetaillab != null) {
                            rsdetaillab.close();
                        }
                        if (psdetaillab != null) {
                            psdetaillab.close();
                        }
                    }
                }
//                rsperiksalab.beforeFirst();
//                while (rsperiksalab.next()) {
//                    psdetaillab = koneksi.prepareStatement(
//                            "select sum(detail_periksa_lab.biaya_item) as total from detail_periksa_lab where detail_periksa_lab.no_rawat=? "
//                            + "and detail_periksa_lab.kd_jenis_prw=? ");
//                    psdetaillab = koneksi.prepareStatement(
//                            "SELECT tl.Pemeriksaan,dpl.biaya_item,count(tl.Pemeriksaan) jumlah,sum(dpl.biaya_item) total "
//                            + "FROM detail_periksa_lab dpl INNER JOIN template_laboratorium tl ON tl.id_template = dpl.id_template "
//                            + "WHERE dpl.no_rawat = ? group by tl.Pemeriksaan ");
//                    try {
//                        psdetaillab.setString(1, norawat);
////                        psdetaillab.setString(2, rsperiksalab.getString("kd_jenis_prw"));
//                        rsdetaillab = psdetaillab.executeQuery();
//                        lab = 0;
//                        while (rsdetaillab.next()) {
//                            lab = 0;
//                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsdetaillab.getString("Pemeriksaan"), ":",
//                                rsdetaillab.getDouble("biaya_item"), rsdetaillab.getDouble("jumlah"), lab, (rsdetaillab.getDouble("total") + lab), "Laborat"});
//                            subttl = subttl + rsdetaillab.getDouble("total") + lab;
//                        }
//                    } catch (Exception e) {
//                        System.out.println("Notif Detail Lab : " + e);
//                    } finally {
//                        if (rsdetaillab != null) {
//                            rsdetaillab.close();
//                        }
//                        if (psdetaillab != null) {
//                            psdetaillab.close();
//                        }
//                    }

//                    tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsdetaillab.getString("Pemeriksaan"), ":",
//                        rsdetaillab.getDouble("biaya_item"), rsdetaillab.getDouble("jumlah"), lab, (rsdetaillab.getDouble("total") + lab), "Laborat"});
//                    subttl = subttl + rsdetaillab.getDouble("total") + lab;
//                }
                if (subttl > 1) {
                    tabModeRwJlDr.addRow(new Object[]{true, "", "Total Periksa Lab : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlLaborat"});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi Periksa Lab : " + e);
            } finally {
                if (rsperiksalab != null) {
                    rsperiksalab.close();
                }
                if (psperiksalab != null) {
                    psperiksalab.close();
                }
            }

            subttl = 0;
            psperiksarad = koneksi.prepareStatement(
                    "select jns_perawatan_radiologi.nm_perawatan, count(periksa_radiologi.kd_jenis_prw) as jml,periksa_radiologi.biaya as biaya, "
                    + " sum(periksa_radiologi.biaya) as total,jns_perawatan_radiologi.kd_jenis_prw "
                    + " from periksa_radiologi inner join jns_perawatan_radiologi "
                    + " on jns_perawatan_radiologi.kd_jenis_prw=periksa_radiologi.kd_jenis_prw where "
                    + " periksa_radiologi.no_rawat=? and periksa_radiologi.status like ? group by periksa_radiologi.kd_jenis_prw  ");
            try {
                psperiksarad.setString(1, norawat);
                if ((chkRalan.isSelected() == true) && (chkRanap.isSelected() == true)) {
                    psperiksarad.setString(2, "%%");
                } else if ((chkRalan.isSelected() == true) && (chkRanap.isSelected() == false)) {
                    psperiksarad.setString(2, "%Ralan%");
                } else if ((chkRalan.isSelected() == false) && (chkRanap.isSelected() == true)) {
                    psperiksarad.setString(2, "%Ranap%");
                } else if ((chkRalan.isSelected() == false) && (chkRanap.isSelected() == false)) {
                    psperiksarad.setString(2, "%Kosong%");
                }
                rsperiksarad = psperiksarad.executeQuery();
                if (rsperiksarad.next()) {
                    tabModeRwJlDr.addRow(new Object[]{true, x + ". Pemeriksaan Radiologi", ":", "", null, null, null, null, "Radiologi"});
                    x++;
                }
                rsperiksarad.beforeFirst();
                while (rsperiksarad.next()) {
                    tamkur = 0;
                    pstamkur = koneksi.prepareStatement(sqlpstamkur);
                    try {
                        pstamkur.setString(1, TNoRw.getText());
                        pstamkur.setString(2, rsperiksarad.getString("nm_perawatan"));
                        pstamkur.setString(3, "Radiologi");
                        rstamkur = pstamkur.executeQuery();
                        if (rstamkur.next()) {
                            tamkur = rstamkur.getDouble(1);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rstamkur != null) {
                            rstamkur.close();
                        }
                        if (pstamkur != null) {
                            pstamkur.close();
                        }
                    }
                    tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsperiksarad.getString("nm_perawatan"), ":",
                        rsperiksarad.getDouble("biaya"), rsperiksarad.getDouble("jml"), tamkur, (tamkur + rsperiksarad.getDouble("total")), "Radiologi"});
                    subttl = subttl + rsperiksarad.getDouble("total") + tamkur;
                }

                if (subttl > 1) {
                    tabModeRwJlDr.addRow(new Object[]{true, "", "Total Periksa Radiologi : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlRadiologi"});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi Periksa Radiologi : " + e);
            } finally {
                if (rsperiksarad != null) {
                    rsperiksarad.close();
                }
                if (psperiksarad != null) {
                    psperiksarad.close();
                }
            }

            if (detailjs > 0) {
                tabModeRwJlDr.addRow(new Object[]{true, x + ". Jasa Sarpras", ":", "", null, null, null, detailjs, "Ralan Dokter"});
                x++;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void prosesCariOperasi(String norawat) {
        try {
            subttl = 0;
            psoperasi = koneksi.prepareStatement(sqlpsoperasi);
            try {
                psoperasi.setString(1, norawat);
                if ((chkRalan.isSelected() == true) && (chkRanap.isSelected() == true)) {
                    psoperasi.setString(2, "%%");
                } else if ((chkRalan.isSelected() == true) && (chkRanap.isSelected() == false)) {
                    psoperasi.setString(2, "%Ralan%");
                } else if ((chkRalan.isSelected() == false) && (chkRanap.isSelected() == true)) {
                    psoperasi.setString(2, "%Ranap%");
                } else if ((chkRalan.isSelected() == false) && (chkRanap.isSelected() == false)) {
                    psoperasi.setString(2, "%Kosong%");
                }
                rsoperasi = psoperasi.executeQuery();
                if (rsoperasi.next()) {
                    tabModeRwJlDr.addRow(new Object[]{true, x + ". Operasi", ":", "", null, null, null, null, "Operasi"});
                    x++;
                }
                rsoperasi.beforeFirst();
                if (rincianoperasi.equals("Yes")) {
                    while (rsoperasi.next()) {
                        tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsoperasi.getString("nm_perawatan"), ":", null, null, null, null, "Operasi"});
                        if (rsoperasi.getDouble("biayaoperator1") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Operator 1 (" + rsoperasi.getString("dokter_operator1") + ")", ":", rsoperasi.getDouble("biayaoperator1"), 1, 0, rsoperasi.getDouble("biayaoperator1"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaoperator2") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Operator 2 (" + rsoperasi.getString("dokter_operator2") + ")", ":", rsoperasi.getDouble("biayaoperator2"), 1, 0, rsoperasi.getDouble("biayaoperator2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaoperator3") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Operator 3 (" + rsoperasi.getString("dokter_operator3") + ")", ":", rsoperasi.getDouble("biayaoperator3"), 1, 0, rsoperasi.getDouble("biayaoperator3"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_operator1") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Asisten Operator 1 (" + rsoperasi.getString("asisten_operator1") + ")", ":", rsoperasi.getDouble("biayaasisten_operator1"), 1, 0, rsoperasi.getDouble("biayaasisten_operator1"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_operator2") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Asisten Operator 2 (" + rsoperasi.getString("asisten_operator2") + ")", ":", rsoperasi.getDouble("biayaasisten_operator2"), 1, 0, rsoperasi.getDouble("biayaasisten_operator2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_operator3") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Asisten Operator 3 (" + rsoperasi.getString("asisten_operator3") + ")", ":", rsoperasi.getDouble("biayaasisten_operator3"), 1, 0, rsoperasi.getDouble("biayaasisten_operator3"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayainstrumen") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Instrumen (" + rsoperasi.getString("instrumen") + ")", ":", rsoperasi.getDouble("biayainstrumen"), 1, 0, rsoperasi.getDouble("biayainstrumen"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayadokter_anak") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Dokter Anak (" + rsoperasi.getString("dokter_anak") + ")", ":", rsoperasi.getDouble("biayadokter_anak"), 1, 0, rsoperasi.getDouble("biayadokter_anak"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaperawaat_resusitas") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Perawat Resusitas (" + rsoperasi.getString("perawaat_resusitas") + ")", ":", rsoperasi.getDouble("biayaperawaat_resusitas"), 1, 0, rsoperasi.getDouble("biayaperawaat_resusitas"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayadokter_anestesi") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Dokter Anastesi (" + rsoperasi.getString("dokter_anestesi") + ")", ":", rsoperasi.getDouble("biayadokter_anestesi"), 1, 0, rsoperasi.getDouble("biayadokter_anestesi"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_anestesi") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Asisten Anastesi 1 (" + rsoperasi.getString("asisten_anestesi") + ")", ":", rsoperasi.getDouble("biayaasisten_anestesi"), 1, 0, rsoperasi.getDouble("biayaasisten_anestesi"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_anestesi2") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Asisten Anastesi 2 (" + rsoperasi.getString("asisten_anestesi2") + ")", ":", rsoperasi.getDouble("biayaasisten_anestesi2"), 1, 0, rsoperasi.getDouble("biayaasisten_anestesi2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayabidan") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Bidan 1 (" + rsoperasi.getString("bidan") + ")", ":", rsoperasi.getDouble("biayabidan"), 1, 0, rsoperasi.getDouble("biayabidan"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayabidan2") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Bidan 2 (" + rsoperasi.getString("bidan2") + ")", ":", rsoperasi.getDouble("biayabidan2"), 1, 0, rsoperasi.getDouble("biayabidan2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayabidan3") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Bidan 3 (" + rsoperasi.getString("bidan3") + ")", ":", rsoperasi.getDouble("biayabidan3"), 1, 0, rsoperasi.getDouble("biayabidan3"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaperawat_luar") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Perawat Luar (" + rsoperasi.getString("perawat_luar") + ")", ":", rsoperasi.getDouble("biayaperawat_luar"), 1, 0, rsoperasi.getDouble("biayaperawat_luar"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaalat") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Alat", ":", rsoperasi.getDouble("biayaalat"), 1, 0, rsoperasi.getDouble("biayaalat"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayasewaok") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Sewa OK/VK", ":", rsoperasi.getDouble("biayasewaok"), 1, 0, rsoperasi.getDouble("biayasewaok"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("akomodasi") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Akomodasi", ":", rsoperasi.getDouble("akomodasi"), 1, 0, rsoperasi.getDouble("akomodasi"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Onloop 1 (" + rsoperasi.getString("omloop") + ")", ":", rsoperasi.getDouble("biaya_omloop"), 1, 0, rsoperasi.getDouble("biaya_omloop"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop2") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Onloop 2 (" + rsoperasi.getString("omloop2") + ")", ":", rsoperasi.getDouble("biaya_omloop2"), 1, 0, rsoperasi.getDouble("biaya_omloop2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop3") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Onloop 3 (" + rsoperasi.getString("omloop3") + ")", ":", rsoperasi.getDouble("biaya_omloop3"), 1, 0, rsoperasi.getDouble("biaya_omloop3"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop4") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Onloop 4 (" + rsoperasi.getString("omloop4") + ")", ":", rsoperasi.getDouble("biaya_omloop4"), 1, 0, rsoperasi.getDouble("biaya_omloop4"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop5") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Onloop 5 (" + rsoperasi.getString("omloop5") + ")", ":", rsoperasi.getDouble("biaya_omloop5"), 1, 0, rsoperasi.getDouble("biaya_omloop5"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("bagian_rs") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  N.M.S.", ":", rsoperasi.getDouble("bagian_rs"), 1, 0, rsoperasi.getDouble("bagian_rs"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayasarpras") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Operasional RS", ":", rsoperasi.getDouble("biayasarpras"), 1, 0, rsoperasi.getDouble("biayasarpras"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_dokter_pjanak") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Dokter PJ Anak", ":", rsoperasi.getDouble("biaya_dokter_pjanak"), 1, 0, rsoperasi.getDouble("biaya_dokter_pjanak"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_dokter_umum") > 0) {
                            tabModeRwJlDr.addRow(new Object[]{true, "                           ", "  Biaya Dokter Umum", ":", rsoperasi.getDouble("biaya_dokter_umum"), 1, 0, rsoperasi.getDouble("biaya_dokter_umum"), "Operasi"});
                        }
                        subttl = subttl + rsoperasi.getDouble("biaya");
                    }
                } else {
                    while (rsoperasi.next()) {
                        tabModeRwJlDr.addRow(new Object[]{true, "                           ", rsoperasi.getString("nm_perawatan"), ":", rsoperasi.getDouble("biaya"), 1, 0, rsoperasi.getDouble("biaya"), "Operasi"});
                        subttl = subttl + rsoperasi.getDouble("biaya");
                    }
                }

                if (subttl > 0) {
                    tabModeRwJlDr.addRow(new Object[]{true, "", "Total Operasi : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlOperasi"});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsoperasi != null) {
                    rsoperasi.close();
                }
                if (psoperasi != null) {
                    psoperasi.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void prosesCariTambahan(String norawat) {
        x++;
        subttl = 0;
        try {
            pstambahanbiaya = koneksi.prepareStatement(sqlpstambahanbiaya);
            try {
                pstambahanbiaya.setString(1, norawat);
                rstambahanbiaya = pstambahanbiaya.executeQuery();
                rstambahanbiaya.last();
                if (rstambahanbiaya.getRow() > 0) {
                    tabModeRwJlDr.addRow(new Object[]{true, "Tambahan Biaya", ":", "", null, null, null, null, "Tambahan"});
                } else {
                    tabModeRwJlDr.addRow(new Object[]{false, "Tambahan Biaya", ":", "", null, null, null, null, "Tambahan"});
                }
                rstambahanbiaya.beforeFirst();
                while (rstambahanbiaya.next()) {
                    tabModeRwJlDr.addRow(new Object[]{true, "                           ", rstambahanbiaya.getString("nama_biaya"), ":",
                        rstambahanbiaya.getDouble("besar_biaya"), 1, 0, rstambahanbiaya.getDouble("besar_biaya"), "Tambahan"});
                    subttl = subttl + rstambahanbiaya.getDouble("besar_biaya");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rstambahanbiaya != null) {
                    rstambahanbiaya.close();
                }
                if (pstambahanbiaya != null) {
                    pstambahanbiaya.close();
                }
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }
        if (subttl > 1) {
            tabModeRwJlDr.addRow(new Object[]{true, "", "Total Tambahan : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlTambahan"});
        }
    }

    private void prosesCariPotongan(String norawat) {
        x++;
        subttl = 0;
        try {
            pspotonganbiaya = koneksi.prepareStatement(sqlpspotonganbiaya);
            try {
                pspotonganbiaya.setString(1, norawat);
                rspotonganbiaya = pspotonganbiaya.executeQuery();
                rspotonganbiaya.last();
                if (rspotonganbiaya.getRow() > 0) {
                    tabModeRwJlDr.addRow(new Object[]{true, "Potongan Biaya", ":", "", null, null, null, null, "Potongan"});
                } else {
                    tabModeRwJlDr.addRow(new Object[]{false, "Potongan Biaya", ":", "", null, null, null, null, "Potongan"});
                }
                rspotonganbiaya.beforeFirst();
                while (rspotonganbiaya.next()) {
                    tabModeRwJlDr.addRow(new Object[]{true, "                           ", rspotonganbiaya.getString("nama_pengurangan"), ":",
                        rspotonganbiaya.getDouble("besar_pengurangan"), 1, 0, (-1 * rspotonganbiaya.getDouble("besar_pengurangan")), "Potongan"});
                    subttl = subttl + rspotonganbiaya.getDouble("besar_pengurangan");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rspotonganbiaya != null) {
                    rspotonganbiaya.close();
                }
                if (pspotonganbiaya != null) {
                    pspotonganbiaya.close();
                }
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }
        if (subttl > 1) {
            tabModeRwJlDr.addRow(new Object[]{true, "", "Total Potongan : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlPotongan"});
        }
    }
    
    private void prosesCariPenjaminPiutang(String norawat) {
        if (Sequel.cariInteger("select count(-1) from piutang_pasien where no_rawat='" + norawat + "'") > 0) {
            x++;
            try {
                tabModeRwJlDr.addRow(new Object[]{true, "Penjamin Piutang", ": "
                    + Sequel.cariIsi("select if(penjamin='-',penjamin,concat(penjamin,' (',ket_penjamin,')')) from piutang_pasien where no_rawat='" + norawat + "'") + "",
                    "", null, null, null, null, "Penjamin Piutang"});
            } catch (Exception ex) {
                System.out.println("Notifikasi : " + ex);
            }
        }
    }

    public void tampilTambahan(String NoRawat) {
        norawattambahan.setText(NoRawat);
        Valid.tabelKosong(tabModeTambahan);
        try {
            pstambahanbiaya = koneksi.prepareStatement(sqlpstambahanbiaya);
            try {
                pstambahanbiaya.setString(1, norawattambahan.getText());
                rstambahanbiaya = pstambahanbiaya.executeQuery();
                while (rstambahanbiaya.next()) {
                    tabModeTambahan.addRow(new Object[]{rstambahanbiaya.getString(1), rstambahanbiaya.getString(2)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rstambahanbiaya != null) {
                    rstambahanbiaya.close();
                }
                if (pstambahanbiaya != null) {
                    pstambahanbiaya.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void tampilPotongan(String NoRawat) {
        norawatpotongan.setText(NoRawat);
        Valid.tabelKosong(tabModePotongan);
        try {
            pspotonganbiaya = koneksi.prepareStatement(sqlpspotonganbiaya);
            try {
                pspotonganbiaya.setString(1, norawatpotongan.getText());
                rspotonganbiaya = pspotonganbiaya.executeQuery();
                while (rspotonganbiaya.next()) {
                    tabModePotongan.addRow(new Object[]{rspotonganbiaya.getString(1), rspotonganbiaya.getString(2)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rspotonganbiaya != null) {
                    rspotonganbiaya.close();
                }
                if (pspotonganbiaya != null) {
                    pspotonganbiaya.close();
                }
            }
            //rs.close();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void tampilUbahLama(String NoRawat) {
        norawatubahlama.setText(NoRawat);
        Valid.tabelKosong(tabModeKamIn);
        try {
            pskamarin = koneksi.prepareStatement(sqlpskamarin);
            try {
                pskamarin.setString(1, norawatubahlama.getText());
                rskamarin = pskamarin.executeQuery();
                while (rskamarin.next()) {
                    tabModeKamIn.addRow(new Object[]{
                        rskamarin.getString("kd_kamar"), rskamarin.getString("nm_bangsal"),
                        rskamarin.getString("tgl_masuk"), rskamarin.getString("jam_masuk"),
                        rskamarin.getString("tgl_keluar"), rskamarin.getString("jam_keluar"),
                        rskamarin.getString("lama")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rskamarin != null) {
                    rskamarin.close();
                }
                if (pskamarin != null) {
                    pskamarin.close();
                }
            }
            //rs.close();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampilAkunBayar() {
        try {
            jml = 0;
            for (z = 0; z < tbAkunBayar.getRowCount(); z++) {
                if (!tbAkunBayar.getValueAt(z, 2).toString().equals("")) {
                    jml++;
                }
            }
            Nama_Akun_Bayar = null;
            Kode_Rek_Bayar = null;
            Bayar = null;
            PPN_Persen = null;
            PPN_Besar = null;
            Nama_Akun_Bayar = new String[jml];
            Kode_Rek_Bayar = new String[jml];
            Bayar = new String[jml];
            PPN_Persen = new String[jml];
            PPN_Besar = new String[jml];

            jml = 0;
            for (z = 0; z < tbAkunBayar.getRowCount(); z++) {
                if (!tbAkunBayar.getValueAt(z, 2).toString().equals("")) {
                    Nama_Akun_Bayar[jml] = tbAkunBayar.getValueAt(z, 0).toString();
                    Kode_Rek_Bayar[jml] = tbAkunBayar.getValueAt(z, 1).toString();
                    Bayar[jml] = tbAkunBayar.getValueAt(z, 2).toString();
                    PPN_Persen[jml] = tbAkunBayar.getValueAt(z, 3).toString();
                    PPN_Besar[jml] = tbAkunBayar.getValueAt(z, 4).toString();
                    jml++;
                }
            }

            Valid.tabelKosong(tabModeAkunBayar);

            for (z = 0; z < jml; z++) {
                tabModeAkunBayar.addRow(new Object[]{
                    Nama_Akun_Bayar[z], Kode_Rek_Bayar[z], Bayar[z], PPN_Persen[z], PPN_Besar[z]
                });
            }

            psakunbayar = koneksi.prepareStatement("select * from akun_bayar where nama_bayar like ? order by nama_bayar");
            try {
                psakunbayar.setString(1, "%" + TCari.getText() + "%");
                rsakunbayar = psakunbayar.executeQuery();
                while (rsakunbayar.next()) {
                    tabModeAkunBayar.addRow(new Object[]{rsakunbayar.getString(1), rsakunbayar.getString(2), "", rsakunbayar.getDouble(3), ""});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsakunbayar != null) {
                    rsakunbayar.close();
                }
                if (psakunbayar != null) {
                    psakunbayar.close();
                }
            }

        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }
    }

    private void tampilAkunBayarTersimpan() {
        try {
            Valid.tabelKosong(tabModeAkunBayar);
            psakunbayar = koneksi.prepareStatement(
                    "select akun_bayar.nama_bayar,akun_bayar.kd_rek,detail_nota_inap.besar_bayar,"
                    + "akun_bayar.ppn,detail_nota_inap.besarppn from akun_bayar inner join detail_nota_inap "
                    + "on akun_bayar.nama_bayar=detail_nota_inap.nama_bayar where detail_nota_inap.no_rawat=? and akun_bayar.nama_bayar like ? order by nama_bayar");
            try {
                psakunbayar.setString(1, TNoRw.getText());
                psakunbayar.setString(2, "%" + TCari.getText() + "%");
                rsakunbayar = psakunbayar.executeQuery();
                while (rsakunbayar.next()) {
                    tabModeAkunBayar.addRow(new Object[]{rsakunbayar.getString(1), rsakunbayar.getString(2), rsakunbayar.getString(3), rsakunbayar.getString(4), rsakunbayar.getString(5)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi Akun Bayar Tersimpan : " + e);
            } finally {
                if (rsakunbayar != null) {
                    rsakunbayar.close();
                }
                if (psakunbayar != null) {
                    psakunbayar.close();
                }
            }

        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }
    }

    private void tampilAkunPiutang() {
        try {
            jml = 0;
            for (z = 0; z < tbAkunPiutang.getRowCount(); z++) {
                if (!tbAkunPiutang.getValueAt(z, 3).toString().equals("")) {
                    jml++;
                }
            }

            Nama_Akun_Piutang = null;
            Nama_Akun_Piutang = new String[jml];
            Kode_Rek_Piutang = null;
            Kode_Rek_Piutang = new String[jml];
            Kd_PJ = null;
            Kd_PJ = new String[jml];
            Besar_Piutang = null;
            Besar_Piutang = new String[jml];
            Jatuh_Tempo = null;
            Jatuh_Tempo = new String[jml];

            jml = 0;
            for (z = 0; z < tbAkunPiutang.getRowCount(); z++) {
                if (!tbAkunPiutang.getValueAt(z, 3).toString().equals("")) {
                    Nama_Akun_Piutang[jml] = tbAkunPiutang.getValueAt(z, 0).toString();
                    Kode_Rek_Piutang[jml] = tbAkunPiutang.getValueAt(z, 1).toString();
                    Kd_PJ[jml] = tbAkunPiutang.getValueAt(z, 2).toString();
                    Besar_Piutang[jml] = tbAkunPiutang.getValueAt(z, 3).toString();
                    Jatuh_Tempo[jml] = tbAkunPiutang.getValueAt(z, 4).toString();
                    jml++;
                }
            }

            Valid.tabelKosong(tabModeAkunPiutang);

            for (z = 0; z < jml; z++) {
                tabModeAkunPiutang.addRow(new Object[]{
                    Nama_Akun_Piutang[z], Kode_Rek_Piutang[z], Kd_PJ[z], Besar_Piutang[z], Jatuh_Tempo[z]
                });
            }

            psakunpiutang = koneksi.prepareStatement("select * from akun_piutang where nama_bayar like ? order by nama_bayar");
            try {
                psakunpiutang.setString(1, "%" + TCari1.getText() + "%");
                rsakunpiutang = psakunpiutang.executeQuery();
                while (rsakunpiutang.next()) {
                    tabModeAkunPiutang.addRow(new Object[]{rsakunpiutang.getString(1), rsakunpiutang.getString(2), rsakunpiutang.getString(3), "", DTPTgl.getSelectedItem().toString().substring(0, 10)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsakunpiutang != null) {
                    rsakunpiutang.close();
                }
                if (psakunpiutang != null) {
                    psakunpiutang.close();
                }
            }

        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }
    }

    private void tampilAkunPiutangTersimpan() {
        try {
            Valid.tabelKosong(tabModeAkunPiutang);
            psakunpiutang = koneksi.prepareStatement(
                    "select akun_piutang.nama_bayar,akun_piutang.kd_rek,akun_piutang.kd_pj, "
                    + "detail_piutang_pasien.totalpiutang,date_format(detail_piutang_pasien.tgltempo,'%d/%m/%Y') from "
                    + "akun_piutang inner join detail_piutang_pasien on akun_piutang.nama_bayar=detail_piutang_pasien.nama_bayar "
                    + "where detail_piutang_pasien.no_rawat=? and akun_piutang.nama_bayar like ? order by nama_bayar");
            try {
                psakunpiutang.setString(1, TNoRw.getText());
                psakunpiutang.setString(2, "%" + TCari1.getText() + "%");
                rsakunpiutang = psakunpiutang.executeQuery();
                while (rsakunpiutang.next()) {
                    tabModeAkunPiutang.addRow(new Object[]{rsakunpiutang.getString(1), rsakunpiutang.getString(2), rsakunpiutang.getString(3), rsakunpiutang.getString(4), rsakunpiutang.getString(5)});
                }
                if (tabModeAkunPiutang.getRowCount() > 0) {
                    ChkPiutang.setSelected(true);
                    cmbPenjamin.setEnabled(true);
                    TketPenjamin.setEnabled(true);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi Akun Piutang Tersimpan : " + e);
            } finally {
                if (rsakunpiutang != null) {
                    rsakunpiutang.close();
                }
                if (psakunpiutang != null) {
                    psakunpiutang.close();
                }
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }
    }

    private void isSimpan() {
        if (notaranap.equals("Yes")) {
            BtnNotaActionPerformed(null);
        }

        try {
            try {
                Sequel.meghapus("nota_inap", "no_rawat", TNoRw.getText());
                psnota = koneksi.prepareStatement(sqlpsnota);
                no_nota = Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_nota,6),signed)),0) from nota_inap where left(tanggal,7)='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "' ", Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7).replaceAll("-", "/") + "/RI/", 6);

                try {
                    psnota.setString(1, TNoRw.getText());
                    psnota.setString(2, Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_nota,6),signed)),0) from nota_inap where left(tanggal,7)='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "' ", Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7).replaceAll("-", "/") + "/RI/", 6));
                    psnota.setString(3, Valid.SetTgl(DTPTgl.getSelectedItem() + ""));
                    psnota.setString(4, DTPTgl.getSelectedItem().toString().substring(11, 19));
                    psnota.setDouble(5, uangdeposit);
                    psnota.executeUpdate();
                } catch (Exception e) {
                    Sequel.meghapus("nota_inap", "no_rawat", TNoRw.getText());
                    tbBilling.setValueAt(": " + Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_nota,6),signed)),0) from nota_inap where left(tanggal,7)='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "' ", Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7).replaceAll("-", "/") + "/RI/", 6), 0, 2);
                    psnota = koneksi.prepareStatement(sqlpsnota);
                    try {
                        psnota.setString(1, TNoRw.getText());
                        psnota.setString(2, Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_nota,6),signed)),0) from nota_inap where left(tanggal,7)='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "' ", Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7).replaceAll("-", "/") + "/RI/", 6));
                        psnota.setString(3, Valid.SetTgl(DTPTgl.getSelectedItem() + ""));
                        psnota.setString(4, DTPTgl.getSelectedItem().toString().substring(11, 19));
                        psnota.setDouble(5, uangdeposit);
                        psnota.executeUpdate();
                    } catch (Exception ex) {
                        System.out.println("Notifikasi Nota 2 : " + ex);
                    } finally {
                        psnota.close();
                    }
                } finally {
                    psnota.close();
                }
            } catch (Exception e) {
                Sequel.meghapus("nota_inap", "no_rawat", TNoRw.getText());
                tbBilling.setValueAt(": " + Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_nota,6),signed)),0) from nota_inap where left(tanggal,7)='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "' ", Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7).replaceAll("-", "/") + "/RI/", 6), 0, 2);
                psnota = koneksi.prepareStatement(sqlpsnota);
                try {
                    psnota.setString(1, TNoRw.getText());
                    psnota.setString(2, Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_nota,6),signed)),0) from nota_inap where left(tanggal,7)='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "' ", Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7).replaceAll("-", "/") + "/RI/", 6));
                    psnota.setString(3, Valid.SetTgl(DTPTgl.getSelectedItem() + ""));
                    psnota.setString(4, DTPTgl.getSelectedItem().toString().substring(11, 19));
                    psnota.setDouble(5, uangdeposit);
                    psnota.executeUpdate();
                } catch (Exception ex) {
                    System.out.println("Notifikasi Nota 2 : " + ex);
                } finally {
                    psnota.close();
                }
            }
            koneksi.setAutoCommit(false);
            for (i = 0; i < tbBilling.getRowCount(); i++) {
                psbiling = koneksi.prepareStatement(sqlpsbiling);
                try {
                    psbiling.setString(1, TNoRw.getText());
                    psbiling.setString(2, Valid.SetTgl(DTPTgl.getSelectedItem() + ""));
                    psbiling.setString(3, tbBilling.getValueAt(i, 1).toString());
                    psbiling.setString(4, tbBilling.getValueAt(i, 2).toString().replaceAll("'", "`"));
                    psbiling.setString(5, tbBilling.getValueAt(i, 3).toString());
                    try {
                        psbiling.setDouble(6, Valid.SetAngka(tbBilling.getValueAt(i, 4).toString()));
                    } catch (Exception e) {
                        psbiling.setDouble(6, 0);
                    }
                    try {
                        psbiling.setDouble(7, Valid.SetAngka(tbBilling.getValueAt(i, 5).toString()));
                    } catch (Exception e) {
                        psbiling.setDouble(7, 0);
                    }
                    subttl = 0;
                    try {
                        if ((!tbBilling.getValueAt(i, 8).toString().equals("Laborat")) && (!tbBilling.getValueAt(i, 8).toString().equals("Obat")) && (!tbBilling.getValueAt(i, 8).toString().equals("Operasi"))) {
                            subttl = Valid.SetAngka(tbBilling.getValueAt(i, 6).toString());
                        }
                        psbiling.setDouble(8, Valid.SetAngka(tbBilling.getValueAt(i, 6).toString()));
                    } catch (Exception e) {
                        subttl = 0;
                        psbiling.setDouble(8, 0);
                    }
                    if (subttl > 0) {
                        Sequel.queryu2("delete from tambahan_biaya where no_rawat=? and nama_biaya=?", 2, new String[]{
                            TNoRw.getText(), "Tambahan " + tbBilling.getValueAt(i, 2).toString()
                        });
                        Sequel.menyimpan("tambahan_biaya", "'" + TNoRw.getText() + "','Tambahan " + tbBilling.getValueAt(i, 2).toString()
                                + "','" + tbBilling.getValueAt(i, 6).toString() + "','" + akses.getkode() + "',"
                                + "'" + Sequel.cariIsi("SELECT DATE(NOW())") + "','" + Sequel.cariIsi("SELECT TIME(NOW())") + "'", "Tambahan Biaya");
                    }
                    if (subttl < 0) {
                        Sequel.queryu2("delete from pengurangan_biaya where no_rawat=? and nama_pengurangan=?", 2, new String[]{
                            TNoRw.getText(), "Potongan " + tbBilling.getValueAt(i, 2).toString()
                        });
                        Sequel.menyimpan("pengurangan_biaya", "'" + TNoRw.getText() + "','Potongan " + tbBilling.getValueAt(i, 2).toString()
                                + "','" + tbBilling.getValueAt(i, 6).toString() + "'", "Potongan Biaya");
                    }
                    try {
                        psbiling.setDouble(9, Valid.SetAngka(tbBilling.getValueAt(i, 7).toString()));
                    } catch (Exception e) {
                        psbiling.setDouble(9, 0);
                    }
                    psbiling.setString(10, tbBilling.getValueAt(i, 8).toString());
                    psbiling.setString(11, no_nota);
                    psbiling.executeUpdate();
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (psbiling != null) {
                        psbiling.close();
                    }
                }
            }

            String alamat = Sequel.cariIsi("select alamat from pasien where no_rkm_medis=? ", TNoRM.getText());

            //lakukan jurnal
            Sequel.queryu2("delete from tampjurnal");

            itembayar = 0;
            besarppn = 0;
            row2 = tbAkunBayar.getRowCount();
            for (r = 0; r < row2; r++) {
                if (Valid.SetAngka(tbAkunBayar.getValueAt(r, 2).toString()) > 0) {
                    try {
                        itembayar = Double.parseDouble(tbAkunBayar.getValueAt(r, 2).toString());
                    } catch (Exception e) {
                        itembayar = 0;
                    }

                    if (!tbAkunBayar.getValueAt(r, 4).toString().equals("")) {
                        try {
                            besarppn = Valid.roundUp(Double.parseDouble(tbAkunBayar.getValueAt(r, 4).toString()), 100);
                        } catch (Exception e) {
                            besarppn = 0;
                        }
                    }

                    if (countbayar > 1) {
                        if (Sequel.menyimpantf2("detail_nota_inap", "?,?,?,?", "Akun bayar", 4, new String[]{
                            TNoRw.getText(), tbAkunBayar.getValueAt(r, 0).toString(), Double.toString(besarppn), Double.toString(itembayar)
                        }) == true) {
                            Sequel.menyimpan("tampjurnal", "'" + tbAkunBayar.getValueAt(r, 1).toString() + "','" + tbAkunBayar.getValueAt(r, 0).toString() + "','" + Double.toString(itembayar) + "','0'",
                                    "debet=debet+" + Double.toString(itembayar), "kd_rek='" + tbAkunBayar.getValueAt(r, 1).toString() + "'");
                        }
                    } else if (countbayar == 1) {
                        if (piutang <= 0) {
                            if (Sequel.menyimpantf2("detail_nota_inap", "?,?,?,?", "Akun bayar", 4, new String[]{
                                TNoRw.getText(), tbAkunBayar.getValueAt(r, 0).toString(), Double.toString(besarppn), Double.toString(itembayar - kekurangan)
                            }) == true) {
                                Sequel.menyimpan("tampjurnal", "'" + tbAkunBayar.getValueAt(r, 1).toString() + "','" + tbAkunBayar.getValueAt(r, 0).toString() + "','" + Double.toString(itembayar - kekurangan) + "','0'",
                                        "debet=debet+" + Double.toString(itembayar - kekurangan), "kd_rek='" + tbAkunBayar.getValueAt(r, 1).toString() + "'");
                            }
                        } else {
                            if (Sequel.menyimpantf2("detail_nota_inap", "?,?,?,?", "Akun bayar", 4, new String[]{
                                TNoRw.getText(), tbAkunBayar.getValueAt(r, 0).toString(), Double.toString(besarppn), Double.toString(itembayar)
                            }) == true) {
                                Sequel.menyimpan("tampjurnal", "'" + tbAkunBayar.getValueAt(r, 1).toString() + "','" + tbAkunBayar.getValueAt(r, 0).toString() + "','" + Double.toString(itembayar) + "','0'",
                                        "debet=debet+" + Double.toString(itembayar), "kd_rek='" + tbAkunBayar.getValueAt(r, 1).toString() + "'");
                            }
                        }
                    }
                }

            }

            itempiutang = 0;
            row2 = tabModeAkunPiutang.getRowCount();
            for (r = 0; r < row2; r++) {
                if (!tabModeAkunPiutang.getValueAt(r, 3).toString().equals("")) {
                    try {
                        itempiutang = Double.parseDouble(tabModeAkunPiutang.getValueAt(r, 3).toString());
                    } catch (Exception e) {
                        itempiutang = 0;
                    }

                    if (Sequel.menyimpantf2("detail_piutang_pasien", "?,?,?,?,?,?,?", "Akun Piutang", 7, new String[]{
                        TNoRw.getText(), tabModeAkunPiutang.getValueAt(r, 0).toString(), tabModeAkunPiutang.getValueAt(r, 2).toString(),
                        Double.toString(itempiutang), Double.toString(itempiutang), Valid.SetTgl(tabModeAkunPiutang.getValueAt(r, 4).toString()), "-"
                    }) == true) {
                        Sequel.menyimpan("tampjurnal", "'" + tabModeAkunPiutang.getValueAt(r, 1).toString() + "','" + tabModeAkunPiutang.getValueAt(r, 0).toString() + "','" + Double.toString(itempiutang) + "','0'",
                                "debet=debet+" + Double.toString(itempiutang), "kd_rek='" + tabModeAkunPiutang.getValueAt(r, 1).toString() + "'");
                    }
                }
            }

            if (uangdeposit > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Uang_Muka_Ranap + "','Kontra Akun Uang Muka','" + uangdeposit + "','0'",
                        "debet=debet+" + uangdeposit, "kd_rek='" + Uang_Muka_Ranap + "'");
            }

            if ((-1 * ttlPotongan) > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Potongan_Ranap + "','Potongan Ranap','" + (-1 * ttlPotongan) + "','0'", "Rekening");
            }

            if ((-1 * ttlRetur_Obat) > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Retur_Obat_Ranap + "','Retur Obat Ranap','" + (-1 * ttlRetur_Obat) + "','0'", "Rekening");
            }

            if (ttlRegistrasi > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Registrasi_Ranap + "','Registrasi Ranap','0','" + ttlRegistrasi + "'", "Rekening");
            }

            if (ttlTambahan > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Tambahan_Ranap + "','Tambahan Ranap','0','" + ttlTambahan + "'", "Rekening");
            }

            if (ttlResep_Pulang > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Resep_Pulang_Ranap + "','Resep Pulang Ranap','0','" + ttlResep_Pulang + "'", "Rekening");
            }

            if (ttlKamar > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Kamar_Inap + "','Kamar Inap','0','" + ttlKamar + "'", "Rekening");
            }

            if (ttlHarian > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Harian_Ranap + "','Harian Ranap','0','" + ttlHarian + "'", "Rekening");
            }

            if ((ttlRanap_Dokter + ttlRanap_Paramedis + ttlRalan_Dokter + ttlRalan_Paramedis) > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Tindakan_Ranap + "','Tindakan Ranap','0','" + (ttlRanap_Dokter + ttlRanap_Paramedis + ttlRalan_Dokter + ttlRalan_Paramedis) + "'",
                        "kredit=kredit+" + (ttlRanap_Dokter + ttlRanap_Paramedis + ttlRalan_Dokter + ttlRalan_Paramedis), "kd_rek='" + Tindakan_Ranap + "'");
            }

            if (ttlLaborat > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Laborat_Ranap + "','Laborat','0','" + ttlLaborat + "'",
                        "kredit=kredit+" + ttlLaborat, "kd_rek='" + Laborat_Ranap + "'");
            }

            if (ttlRadiologi > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Radiologi_Ranap + "','Radiologi','0','" + ttlRadiologi + "'",
                        "kredit=kredit+" + ttlRadiologi, "kd_rek='" + Radiologi_Ranap + "'");
            }

            if (ttlObat > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Obat_Ranap + "','Obat','0','" + ttlObat + "'",
                        "kredit=kredit+" + ttlObat, "kd_rek='" + Obat_Ranap + "'");
            }

            if (ttlOperasi > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Operasi_Ranap + "','Operasi','0','" + ttlOperasi + "'",
                        "kredit=kredit+" + ttlOperasi, "kd_rek='" + Operasi_Ranap + "'");
            }

            if (ttlService > 0) {
                Sequel.menyimpan("tampjurnal", "'" + Service_Ranap + "','Biaya Service Ranap','0','" + ttlService + "'", "Rekening");
            }

            if (piutang > 0) {
                jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "PIUTANG PASIEN RAWAT JALAN, DIPOSTING OLEH " + akses.getkode());
                Sequel.menyimpan2("tagihan_sadewa", "'" + TNoRw.getText() + "','" + TNoRM.getText() + "','" + TPasien.getText() + "','" + alamat + "',concat('" + Valid.SetTgl(DTPTgl.getSelectedItem() + "")
                        + "',' ',CURTIME()),'Uang Muka','" + total + "','" + bayar + "','Belum','" + akses.getkode() + "','-'", "No.Rawat");
                Sequel.queryu2("insert into piutang_pasien values ('" + TNoRw.getText() + "','" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','"
                        + TNoRM.getText() + "','Belum Lunas','" + total + "','" + bayar + "','" + piutang + "','" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                        + "'-','" + cmbPenjamin.getSelectedItem().toString() + "','" + TketPenjamin.getText() + "')");
            } else if (piutang <= 0) {
                jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "PEMBAYARAN PASIEN RAWAT JALAN, DIPOSTING OLEH " + akses.getkode());
                Sequel.menyimpan2("tagihan_sadewa", "'" + TNoRw.getText() + "','" + TNoRM.getText() + "','" + TPasien.getText() + "','" + alamat + "',concat('" + Valid.SetTgl(DTPTgl.getSelectedItem() + "")
                        + "',' ',CURTIME()),'Pelunasan','" + total + "','" + total + "','Sudah','" + akses.getkode() + "','-'", "No.Rawat");
            }

            Valid.editTable(tabModeRwJlDr, "reg_periksa", "no_rawat", TNoRw, "stts='Bayar'");
            Sequel.meghapus("temporary_tambahan_potongan", "no_rawat", TNoRw.getText());
            koneksi.setAutoCommit(true);
            JOptionPane.showMessageDialog(null, "Proses simpan selesai...!");
            if (notaranap.equals("Yes")) {
                this.dispose();
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Data yang sama dimasukkan sebelumnya...!");
        }
    }

    private void cetakNotaAJA() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("cara_byr", Sequel.cariIsi("select p.png_jawab from reg_periksa r inner join penjab p on p.kd_pj=r.kd_pj where r.no_rawat='" + TNoRw.getText() + "'"));
        param.put("tglNota", "Martapura, " + Valid.SetTglINDONESIA(Valid.SetTgl(tglNota.getSelectedItem() + "")));

        if (akses.getkode().equals("Admin Utama") || BtnSimpan.isEnabled() == false) {
            param.put("petugas_ksr", "( ................... )");
        } else if (akses.getbilling_ranap()) {
            param.put("petugas_ksr", "( " + Sequel.cariIsi("select nama from petugas where nip='" + akses.getkode() + "'") + " )");
        }
        Valid.MyReport("rptNotaRanap.jasper", "report", "::[ Nota Pembayaran - LUNAS (Rawat Inap) ]::",
                " SELECT temp1, temp2, temp3, temp4, temp5, IF(temp6='0','',temp6) temp6, temp7, "
                + "(SELECT temp2 FROM temporary_bayar_ranap WHERE temp1='No.Nota') no_nota, "
                + "(SELECT temp2 FROM temporary_bayar_ranap WHERE temp1='Bangsal/Kamar') bangsal, "
                + "(SELECT temp2 FROM temporary_bayar_ranap WHERE temp1='Tgl. Perawatan') tgl_rawat, "
                + "(SELECT temp2 FROM temporary_bayar_ranap WHERE temp1='Pasien') pasien, "
                + "(SELECT temp2 FROM temporary_bayar_ranap WHERE temp1='Alamat Pasien') alamat_pasien, "
                + "(SELECT temp7 FROM temporary_bayar_ranap WHERE temp1='total tagihan') tot_tagihan, "
                + "(SELECT temp7 FROM temporary_bayar_ranap WHERE temp1='ppn') ppn, "
                + "(SELECT temp7 FROM temporary_bayar_ranap WHERE temp1='tagihan+ppn') tagihan_ppn, "
                + "(SELECT temp7 FROM temporary_bayar_ranap WHERE temp1='deposit') deposit, "
                + "(SELECT temp7 FROM temporary_bayar_ranap WHERE temp1='bayar') bayar, "
                + "(SELECT temp7 FROM temporary_bayar_ranap WHERE temp1='kembali') kembali "
                + "FROM temporary_bayar_ranap WHERE temp1 not in ('TOTAL TAGIHAN','TAGIHAN+PPN','PPN','DEPOSIT','BAYAR','Kembali','No.Nota','Bangsal/Kamar','Tgl. Perawatan','Pasien','Alamat Pasien') ", param);
    }

    private void cetakNotaPiutangAJA() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("cara_byr", Sequel.cariIsi("select p.png_jawab from reg_periksa r inner join penjab p on p.kd_pj=r.kd_pj where r.no_rawat='" + TNoRw.getText() + "'"));
        param.put("tglNota", "Martapura, " + Valid.SetTglINDONESIA(Valid.SetTgl(tglNota.getSelectedItem() + "")));

        if (akses.getkode().equals("Admin Utama") || BtnSimpan.isEnabled() == false) {
            param.put("petugas_ksr", "( ................... )");
        } else if (akses.getbilling_ranap()) {
            param.put("petugas_ksr", "( " + Sequel.cariIsi("select nama from petugas where nip='" + akses.getkode() + "'") + " )");
        }
        Valid.MyReport("rptNotaRanapPiutang.jasper", "report", "::[ Nota Pembayaran - PIUTANG (Rawat Inap) ]::",
                " SELECT temp1, temp2, temp3, temp4, temp5, IF(temp6='0','',temp6) temp6, temp7, "
                + "(SELECT temp2 FROM temporary_bayar_ranap WHERE temp1='No.Nota') no_nota,"
                + "(SELECT temp2 FROM temporary_bayar_ranap WHERE temp1='Bangsal/Kamar') bangsal,"
                + "(SELECT temp2 FROM temporary_bayar_ranap WHERE temp1='Tgl. Perawatan') tgl_rawat,"
                + "(SELECT temp2 FROM temporary_bayar_ranap WHERE temp1='Pasien') pasien,"
                + "(SELECT temp2 FROM temporary_bayar_ranap WHERE temp1='Alamat Pasien') alamat_pasien,"
                + "(SELECT temp7 FROM temporary_bayar_ranap WHERE temp1='total tagihan') tot_tagihan,"
                + "(SELECT temp7 FROM temporary_bayar_ranap WHERE temp1='ppn') ppn,"
                + "(SELECT temp7 FROM temporary_bayar_ranap WHERE temp1='tagihan + ppn') tagihan_ppn,"
                + "(SELECT temp7 FROM temporary_bayar_ranap WHERE temp1='deposit') deposit,"
                + "(SELECT temp7 FROM temporary_bayar_ranap WHERE temp1='uang muka') uang_muka,"
                + "(SELECT temp7 FROM temporary_bayar_ranap WHERE temp1='sisa piutang') sisa_piutang FROM temporary_bayar_ranap "
                + "WHERE temp1 not in ('TOTAL TAGIHAN','TAGIHAN + PPN','PPN','DEPOSIT','UANG MUKA','SISA PIUTANG','No.Nota','Bangsal/Kamar','Tgl. Perawatan','Pasien','Alamat Pasien') ", param);
    }

    private void cetakKwitansiLUNAS() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("no_kwitansi", Sequel.cariIsi("SELECT REPLACE(temp2,': ','') no_kwitansi FROM temporary_bayar_ranap WHERE temp1='No.Nota'"));
        param.put("telah_terima", Sequel.cariIsi("select p_jawab from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
        param.put("uang_sebanyak", Sequel.Terbilang(Sequel.cariIsiAngka("SELECT REPLACE(REPLACE(temp7,'.',''),',','') tot_bayar FROM temporary_bayar_ranap WHERE temp1='BAYAR'")) + " Rupiah.");
        param.put("untuk_byr", "Pelayanan Kesehatan Rawat Inap di " + Sequel.cariIsi("select nama_instansi from setting") + " a/n "
                + Sequel.cariIsi("SELECT REPLACE(temp2,': ','') pasien FROM temporary_bayar_ranap WHERE temp1='Pasien'"));
        param.put("terbilang", Sequel.cariIsi("SELECT concat('Terbilang Rp. ',REPLACE(REPLACE(temp7,'.','.'),',','.')) terbilang FROM temporary_bayar_ranap WHERE temp1='BAYAR'"));
        param.put("tglNota", "Martapura, " + Valid.SetTglINDONESIA(Valid.SetTgl(tglNota.getSelectedItem() + "")));

        if (akses.getkode().equals("Admin Utama") || BtnSimpan.isEnabled() == false) {
            param.put("petugas_ksr", "( ____________________ )");
        } else if (akses.getbilling_ranap()) {
            param.put("petugas_ksr", "( " + Sequel.cariIsi("select nama from petugas where nip='" + akses.getkode() + "'") + " )");
        }
        Valid.MyReport("rptKwitansiRanap.jasper", "report", "::[ Kwitansi Pembayaran - LUNAS (Rawat Inap) ]::",
                " SELECT * FROM temporary_bayar_ranap ", param);
    }

    private void cetakKwitansiPIUTANG() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("no_kwitansi", Sequel.cariIsi("SELECT REPLACE(temp2,': ','') no_kwitansi FROM temporary_bayar_ranap WHERE temp1='No.Nota'"));
        param.put("telah_terima", Sequel.cariIsi("select p_jawab from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
        param.put("uang_sebanyak", Sequel.Terbilang(Sequel.cariIsiAngka("SELECT REPLACE(REPLACE(temp7,'.',''),',','') tot_bayar FROM temporary_bayar_ranap WHERE temp1='UANG MUKA'")) + " Rupiah.");
        param.put("untuk_byr", "Pelayanan Kesehatan Rawat Inap di " + Sequel.cariIsi("select nama_instansi from setting") + " a/n "
                + Sequel.cariIsi("SELECT REPLACE(temp2,': ','') pasien FROM temporary_bayar_ranap WHERE temp1='Pasien'") + " sebagai uang muka.");
        param.put("terbilang", Sequel.cariIsi("SELECT concat('Terbilang Rp. ',REPLACE(REPLACE(temp7,'.','.'),',','.')) terbilang FROM temporary_bayar_ranap WHERE temp1='UANG MUKA'"));
        param.put("tglNota", "Martapura, " + Valid.SetTglINDONESIA(Valid.SetTgl(tglNota.getSelectedItem() + "")));

        if (akses.getkode().equals("Admin Utama") || BtnSimpan.isEnabled() == false) {
            param.put("petugas_ksr", "( ____________________ )");
        } else if (akses.getbilling_ranap()) {
            param.put("petugas_ksr", "( " + Sequel.cariIsi("select nama from petugas where nip='" + akses.getkode() + "'") + " )");
        }
        Valid.MyReport("rptKwitansiRanap.jasper", "report", "::[ Kwitansi Pembayaran - PIUTANG (Rawat Inap) ]::",
                " SELECT * FROM temporary_bayar_ranap ", param);
    }

    public void cekSEP() {
        try {
            pssep = koneksi.prepareStatement("select nomr, ifnull(kode_inacbg,'') kode_inacbg, nama_pasien, no_kartu, no_rawat, "
                    + "klsrawat from bridging_sep where jnspelayanan='1' and no_sep='" + NoSEP.getText() + "'");
            try {
                rssep = pssep.executeQuery();
                while (rssep.next()) {
                    norm.setText(rssep.getString("nomr"));
                    kdINACBG.setText(rssep.getString("kode_inacbg"));
                    nmpasien.setText(rssep.getString("nama_pasien"));
                    nokartu.setText(rssep.getString("no_kartu"));
                    hakkelas.setText(rssep.getString("klsrawat"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rssep != null) {
                    rssep.close();
                }
                if (pssep != null) {
                    pssep.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        Sequel.cariIsi("SELECT k.kelas FROM kamar_inap ki INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE stts_pulang not in ('-','Pindah Kamar') and ki.no_rawat=? ", naikKLS, TNoRw.getText());
        Sequel.cariIsi("SELECT ki.lama FROM reg_periksa r INNER JOIN kamar_inap ki on ki.no_rawat=r.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal LEFT JOIN bridging_sep bs on bs.no_rawat=ki.no_rawat "
                + "WHERE ki.stts_pulang not in ('-','Pindah Kamar') and r.kd_pj='b01' and r.status_lanjut='Ranap' and r.stts_daftar <> 'batal' "
                + "and b.nm_bangsal like '%vip%' and bs.no_sep=? ", hasilLM, NoSEP.getText());
        persenSELISIH.setText(Sequel.cariIsi("select selisih_tarif_bpjs2 from set_tarif"));

        if (naikKLS.getText().equals("Intensif")) {
            naikKLS.setText("");
            hasilLM.setText("");
            Sequel.cariIsi("SELECT k.kelas FROM kamar_inap ki INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE ki.kd_kamar not like '%IC%' and ki.no_rawat=? "
                    + "order by tgl_keluar desc limit 1 ", naikKLS, TNoRw.getText());

            Sequel.cariIsi("SELECT ki.lama FROM reg_periksa r INNER JOIN kamar_inap ki on ki.no_rawat=r.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal LEFT JOIN bridging_sep bs on bs.no_rawat=ki.no_rawat "
                    + "WHERE r.kd_pj='b01' and r.status_lanjut='Ranap' and r.stts_daftar <> 'batal' "
                    + "and b.nm_bangsal like '%vip%' and bs.no_sep=? ", hasilLM, NoSEP.getText());
        }

        Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar_inap ki INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE stts_pulang not in ('-','Pindah Kamar') and no_rawat=? ", rginap, TNoRw.getText());

        if (hasilLM.getText().equals("")) {
            lmrawat.setText("0");
        } else if (!hasilLM.getText().equals("")) {
            lmrawat.setText(hasilLM.getText());
        }

        if ((hakkelas.getText().equals("1")) && (naikKLS.getText().equals("Kelas 1"))
                || (hakkelas.getText().equals("2")) && (naikKLS.getText().equals("Kelas 2"))
                || (hakkelas.getText().equals("3")) && (naikKLS.getText().equals("Kelas 3"))) {
            BtnCloseIn6ActionPerformed(null);
            JOptionPane.showMessageDialog(null, "Pasien sudah sesuai hak kelasnya...!!!");
            selisihBaru();
        } else if (naikKLS.getText().equals("Intensif")) {
            JOptionPane.showMessageDialog(null, "Ruang rawat ICU,ICCU atau NICU tidak dianggap naik kelas rawat..!!!");
            selisihBaru();
            WindowSelisihTarif.dispose();
        } else if (naikKLS.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien belum pulang, harus dipulangkan dulu...!!!");
            selisihBaru();
            WindowSelisihTarif.dispose();
        }
    }

    public void cekINACBG() {
        Sequel.cariIsi("SELECT description FROM inacbg_unucbg_2016 WHERE code=? ", deskripsiKD, kdINACBG.getText());
        Sequel.cariIsi("SELECT TARIFF FROM inacbg_tariff_20230124 WHERE REGIONAL='reg4' and KODE_TARIFF='bp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='1' and INACBG=? ", tarifkls1, kdINACBG.getText());
        Sequel.cariIsi("SELECT TARIFF FROM inacbg_tariff_20230124 WHERE REGIONAL='reg4' and KODE_TARIFF='bp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='2' and INACBG=? ", tarifkls2, kdINACBG.getText());
        Sequel.cariIsi("SELECT TARIFF FROM inacbg_tariff_20230124 WHERE REGIONAL='reg4' and KODE_TARIFF='bp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='3' and INACBG=? ", tarifkls3, kdINACBG.getText());
        tarifRC.setText(TagihanPPn.getText());

        if (kdINACBG.getText().equals("") && deskripsiKD.getText().equals("")) {
            tarifkls1.setText("0");
            tarifkls2.setText("0");
            tarifkls3.setText("0");
        } else {
            Sequel.cariIsi("SELECT TARIFF FROM inacbg_tariff_20230124 WHERE REGIONAL='reg4' and KODE_TARIFF='bp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='1' and INACBG=? ", tarifkls1, kdINACBG.getText());
            Sequel.cariIsi("SELECT TARIFF FROM inacbg_tariff_20230124 WHERE REGIONAL='reg4' and KODE_TARIFF='bp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='2' and INACBG=? ", tarifkls2, kdINACBG.getText());
            Sequel.cariIsi("SELECT TARIFF FROM inacbg_tariff_20230124 WHERE REGIONAL='reg4' and KODE_TARIFF='bp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='3' and INACBG=? ", tarifkls3, kdINACBG.getText());
        }
    }

    public void hitungSelisih() {
        rumus1 = 0;
        rumus2 = 0;
        rumus3 = 0;
        hasilrumus = 0;
        hasilmaksimal = 0;

        DecimalFormat df4 = new DecimalFormat("####");
        double a = Double.parseDouble(tarifkls1.getText().trim());
        double b = Double.parseDouble(tarifkls2.getText().trim());
        double c = Double.parseDouble(tarifkls3.getText().trim());
        double d = Double.parseDouble(persenSELISIH.getText());
        double e = Double.parseDouble(realcostRS.getText());

        if (hakkelas.getText().equals("2") && naikKLS.getText().equals("Kelas 1")) {
            TKalkulasi.setText("tarif INACBG : Kelas 1 Rp. " + Valid.SetAngka3(a) + " - Kelas 2 Rp. " + Valid.SetAngka3(b) + " = Rp. " + Valid.SetAngka3(a - b) + "\n"
                    + "Jadi yang harus dibayar pasien adalah Rp. " + Valid.SetAngka3(a - b) + "");

        } else if (hakkelas.getText().equals("2") && (naikKLS.getText().equals("Kelas VIP") || naikKLS.getText().equals("Kelas VVIP"))) {
            //tarif inacbg kelas 1 - kelas 2
            rumus1 = a - b;
            //tarif inacbg kelas 1 x 75 %
            rumus2 = d / 100 * a;
            //hasil perhitunganya
            hasilrumus = rumus1 + rumus2;
            //hitung tarif inacbg kelas 2 + hasilrumus
            hasilmaksimal = b + hasilrumus;

            //jika tarif real cost RS < tarif inacbg maka GRATIS
            if (e < b) {
                TKalkulasi.setText("Karena Real Cost RS kurang dari tarif INACBG kelas 2 maka tidak ada penambahan selisih biaya perawatan.");

                //jika tarif real cost RS > dari tarif perhitungan permenkes no. 3 tahun 2023
            } else if (e > hasilmaksimal) {
                TKalkulasi.setText("Rumus Tarif INACBG    #   (Kelas 1 - Kelas 2) + (" + persenSELISIH.getText() + " % x Kelas 1)\n"
                        + "A. Kelas 1 - Kelas 2      #   Rp. " + Valid.SetAngka3(a) + " - Rp. " + Valid.SetAngka3(b) + " = Rp. " + Valid.SetAngka3(rumus1) + "\n"
                        + "B. " + persenSELISIH.getText() + " % x Kelas 1        #   " + persenSELISIH.getText() + " % x Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus2) + "\n"
                        + "C. Jadi hasilnya adalah Rp. " + Valid.SetAngka3(hasilrumus) + "\n"
                        + "D. Tarif maksimal         #   Kelas 2 + hasil perhitungan rumus poin C.\n"
                        + "                                    #   Rp. " + Valid.SetAngka3(b) + " + Rp. " + Valid.SetAngka3(hasilrumus) + " = Rp. " + Valid.SetAngka3(hasilmaksimal) + "\n"
                        + "---------------------------------------------------------------------------------------------------------------------------------------------------------\n"
                        + "Kesimpulan : Karena tarif Real Cost RS melebihi dari tarif maksimal penghitungan sesuai Permenkes No. 3 Tahun 2023 maka,\n"
                        + "                     yang harus dibayarkan sesuai dengan Permenkes No. 3 Tahun 2023 adalah Rp. " + Valid.SetAngka3(hasilrumus) + "");

                //jika tarif inacbg kls 2 < real cost RS sampai batas maksimal tarif perhitungan permenkes no. 3 tahun 2023
            } else if ((b < e) && (e <= hasilmaksimal)) {
                rumus3 = e - b;
                TKalkulasi.setText("Rumus Tarif INACBG    #   (Kelas 1 - Kelas 2) + (" + persenSELISIH.getText() + " % x Kelas 1)\n"
                        + "A. Kelas 1 - Kelas 2      #   Rp. " + Valid.SetAngka3(a) + " - Rp. " + Valid.SetAngka3(b) + " = Rp. " + Valid.SetAngka3(rumus1) + "\n"
                        + "B. " + persenSELISIH.getText() + " % x Kelas 1        #   " + persenSELISIH.getText() + " % x Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus2) + "\n"
                        + "C. Jadi hasilnya adalah Rp. " + Valid.SetAngka3(hasilrumus) + "\n"
                        + "D. Tarif maksimal         #   Kelas 2 + hasil perhitungan rumus poin C.\n"
                        + "                                    #   Rp. " + Valid.SetAngka3(b) + " + Rp. " + Valid.SetAngka3(hasilrumus) + " = Rp. " + Valid.SetAngka3(hasilmaksimal) + "\n"
                        + "---------------------------------------------------------------------------------------------------------------------------------------------------------\n"
                        + "Kesimpulan : Karena tarif INACBG Kelas 2 kurang dari tarif Real Cost RS dan sampai batas tarif maksimal penghitungan\n"
                        + "                     sesuai Permenkes No. 3 Tahun 2023 maka biaya, yang harus dibayarkan adalah tarif real cost RS - tarif\n"
                        + "                     INACBG Kelas 2 sebagai berikut : Rp. " + Valid.SetAngka3(e) + " - Rp. " + Valid.SetAngka3(b) + " = Rp. " + Valid.SetAngka3(rumus3) + "\n"
                        + "                     Jadi yang harus dibayarkan sesuai dengan Permenkes No. 3 Tahun 2023 adalah Rp. " + Valid.SetAngka3(rumus3) + "");
            }

        } else if (hakkelas.getText().equals("1") && (naikKLS.getText().equals("Kelas VIP") || naikKLS.getText().equals("Kelas VVIP"))) {
            rumus1 = d / 100 * a;
            rumus2 = rumus1 + a;

            //jika real cost RS <= dari tarif inacbg kelas 1 
            if (e <= a) {
                TKalkulasi.setText("Karena Real Cost RS kurang dari tarif INACBG kelas 1 maka tidak ada penambahan selisih biaya perawatan.");

                //jika real cost RS > dari tarif penghitungan permenkes no. 3 tahun 2023
            } else if (e > rumus2) {
                TKalkulasi.setText("Rumus Tarif INACBG             #   (" + persenSELISIH.getText() + " % x Kelas 1) + Kelas 1\n"
                        + "A. " + persenSELISIH.getText() + " % x Kelas 1                 #   " + persenSELISIH.getText() + " % x Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus1) + "\n"
                        + "B. Rp. " + Valid.SetAngka3(rumus1) + " + Kelas 1  #   Rp. " + Valid.SetAngka3(rumus1) + " + Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus2) + "\n"
                        + "---------------------------------------------------------------------------------------------------------------------------------------------------------\n"
                        + "Kesimpulan : Karena tarif Real Cost RS melebihi dari tarif maksimal penghitungan sesuai Permenkes No. 3 Tahun 2023\n"
                        + "                     maka, yang harus dibayarkan sesuai dengan Permenkes No. 3 Tahun 2023 adalah Rp. " + Valid.SetAngka3(rumus1) + "");

                //jika tarif inacbg kelas 1 < real cost RS dan sampai batas maksimal tarif perhitungan permenkes no. 3 tahun 2023
            } else if ((a < e) && (e <= rumus2)) {
                TKalkulasi.setText("Rumus Tarif INACBG             #   (" + persenSELISIH.getText() + " % x Kelas 1) + Kelas 1\n"
                        + "A. " + persenSELISIH.getText() + " % x Kelas 1                 #   " + persenSELISIH.getText() + " % x Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus1) + "\n"
                        + "B. Rp. " + Valid.SetAngka3(rumus1) + " + Kelas 1  #   Rp. " + Valid.SetAngka3(rumus1) + " + Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus2) + "\n"
                        + "---------------------------------------------------------------------------------------------------------------------------------------------------------\n"
                        + "Kesimpulan : Karena tarif INACBG kelas 1 kurang dari tarif Real Cost RS dan sampai batas maksimal tarif penghitungan pada\n"
                        + "                     poin B/sesuai Permenkes No. 3 Tahun 2023 maka, yang dibayar adalah tarif Real Cost RS - tarif INACBG\n"
                        + "                     kelas 1, sebagai berikut : Rp. " + Valid.SetAngka3(e) + " - Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(e - a) + "\n"
                        + "                     Jadi yang harus dibayarkan sesuai dengan Permenkes No. 3 Tahun 2023 adalah Rp. " + Valid.SetAngka3(e - a) + "");
            }
        }
    }

    public void selisihBaru() {
        NoSEP.setText("");
        norm.setText("");
        nmpasien.setText("");
        nokartu.setText("");
        hakkelas.setText("");
        rginap.setText("");
        kdINACBG.setText("");
        deskripsiKD.setText("");

        tarifkls1.setText("0");
        tarifkls2.setText("0");
        tarifkls3.setText("0");
        tarifRC.setText("0");
        realcostRS.setText("0");
        persenSELISIH.setText("0");
        TKalkulasi.setText("-");
        hasilLM.setText("");
        lmrawat.setText("0");
        naikKLS.setText("");
    }
}
