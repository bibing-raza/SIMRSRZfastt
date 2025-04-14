package laporan;

import bridging.INACBGDaftarKlaim;
import laporan.DlgPenyakit;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import laporan.DlgICD9;
import simrskhanza.DlgPasien;

/**
 *
 * @author perpustakaan
 */
public class DlgDiagnosaPenyakit extends javax.swing.JDialog {
    private final DefaultTableModel TabModeDiagnosaPasien, tabModeDiagnosa, tabModeProsedur,
            TabModeTindakanPasien, tabModeDiagnosaSekunder, tabMode1, tabMode2, tabMode3;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgPasien pasien = new DlgPasien(null, false);
    private PreparedStatement pspenyakit, psdiagnosapasien, psprosedur, pstindakanpasien, psralan, ps2, ps3, ps4, psreg, pskamar, psanak,
            pspenyakitsekunder, pspasien, psdiagnosa, pstindakan, psdokter, psinadrg, psTINinadrg, pscekbilling, pscarirm, pscaripasien, pspros,
            pscarialamat, pscaridpjp, psdokterranap, psdokterralan, pskamarin, pstamkur, psbiayasekali, psbiayaharian, pskategori, psralandokter,
            psralandrpr, psranapdokter, psranapdrpr, psralanperawat, psranapperawat, psperiksalab, psdetaillab, psperiksarad, psoperasi, pscariobat,
            psobatlangsung, psobatoperasi, psreturobat, psreseppulang, pstambahanbiaya, pspotonganbiaya, psservice, pssudahmasuk, psRujuk, pscaripoli,
            pscariralandokter, pscariralandrpr, pscariralanperawat, pscarilab, pscariradiologi, pstambahan, pspotongan, psbilling, psLaprm, psdiag;
    private ResultSet rs, rs1, rs2, rs3, rs4, rspasien, rsdiagnosa, rstindakan, rsdokter, rsralan, rsrad, rshslRad, rsreg, rskamar, rscaripoli,
            rsLISMaster, rsLIS1, rsLIS2, rsLIS3, rscekbilling, rscarirm, rscaripasien, rsanak, rscarialamat, rscaridpjp, rsdokterranap, rspros,
            rsdokterralan, rskamarin, rstamkur, rsbiayasekali, rsbiayaharian, rskategori, rsralandokter, rsralandrpr, rsranapdokter, rsranapdrpr,
            rsralanperawat, rsranapperawat, rsperiksalab, rsdetaillab, rsperiksarad, rsoperasi, rscariobat, rsobatlangsung, rsobatoperasi, 
            rsreturobat, rsreseppulang, rstambahanbiaya, rspotonganbiaya, rsservice, rssudahmasuk, rsRujuk, rscariralandokter, rscariralandrpr,
            rscariralanperawat, rscarilab, rscariradiologi, rstambahan, rspotongan, rsbilling, rsLaprm, rsdiag;
    private int jml = 0, i = 0, index = 0, jml1 = 0, s = 0, index1 = 0, cek = 0, cekINADRG = 0, r = 0,
            cekPremier = 0, cekPremierINADRG = 0, lis1 = 0, lis2 = 0, lisM = 0, x = 0, z = 0;
    private double Jasa_Medik_Dokter_Tindakan_Ralan = 0, Jasa_Medik_Paramedis_Tindakan_Ralan = 0, KSO_Tindakan_Ralan = 0, Jasa_Medik_Dokter_Laborat_Ralan = 0,
            Jasa_Medik_Petugas_Laborat_Ralan = 0, so_Laborat_Ralan = 0, Persediaan_Laborat_Rawat_Jalan = 0, Jasa_Medik_Dokter_Radiologi_Ralan = 0,
            Jasa_Medik_Petugas_Radiologi_Ralan = 0, Kso_Radiologi_Ralan = 0, Persediaan_Radiologi_Rawat_Jalan = 0, Obat_Rawat_Jalan = 0, ttlRalan_Dokter_Param = 0,
            Jasa_Medik_Dokter_Operasi_Ralan = 0, Jasa_Medik_Paramedis_Operasi_Ralan = 0, Obat_Operasi_Ralan = 0, Kso_Laborat_Ralan = 0, ralanparamedis = 0;
    private String[] kode, nama, ciripny, keterangan, kategori, cirium, kode2, panjang, pendek,
            kode1, nama1, ciripny1, keterangan1, kategori1, cirium1;
    private boolean[] pilih, pilih2, pilih3;
    private String tglklaim = "", drdpjp = "", poli = "", crBayar = "", cekKlaim = "", jlhTindakan = "", noRWTerakhir = "", nmPoli = "", centangdokterralan = "",
            rinciandokterralan = "", centangobatralan = "", tampilkan_ppnobat_ralan = "", diagsekunder = "", tindakan = "";
    private String biaya = "", tambahan = "", totals = "", norawatbayi = "", centangdokterranap = "", kd_pj = "", jamplgRS1 = "",
            rinciandokterranap = "", rincianoperasi = "", hariawal = "", notaranap = "", tampilkan_administrasi_di_billingranap = "",
            Tindakan_Ranap = "", Laborat_Ranap = "", Radiologi_Ranap = "", Obat_Ranap = "", Registrasi_Ranap = "",
            Tambahan_Ranap = "", Potongan_Ranap = "", Retur_Obat_Ranap = "", Resep_Pulang_Ranap = "", Kamar_Inap = "", Operasi_Ranap = "",
            Harian_Ranap = "", Uang_Muka_Ranap = "", Piutang_Pasien_Ranap = "", tampilkan_ppnobat_ranap = "", tglmskRS = "", tglklrRS1 = "",
            Service_Ranap = "", status = "", diagnosa_ok = "", cekdokter = "", kdkamar = "", data_pasien = "", tglklrRS2 = "", jamplgRS2 = "",
            sqlpscekbilling = "select count(billing.no_rawat) from billing where billing.no_rawat=?",
            sqlpscarirm = "select r.no_rkm_medis, pj.png_jawab from reg_periksa r inner join penjab pj on pj.kd_pj=r.kd_pj where r.no_rawat=?",
            sqlpscaripoli = "select nm_poli from poliklinik where kd_poli=?",
            sqlpscaripasien = "select p.nm_pasien, concat(r.umurdaftar,' ',r.sttsumur) umur from pasien p "
            + "inner join reg_periksa r on r.no_rkm_medis=p.no_rkm_medis where p.no_rkm_medis=? ",
            sqlpsreg = "select reg_periksa.no_rkm_medis,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,"
            + "reg_periksa.no_rkm_medis,reg_periksa.kd_poli,reg_periksa.no_rawat,date_format(reg_periksa.jam_reg,'%H:%i:%s') jam, "
            + "reg_periksa.biaya_reg from reg_periksa where reg_periksa.no_rawat=?",
            sqlpscarialamat = "select concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) from pasien "
            + "inner join kelurahan inner join kecamatan inner join kabupaten on pasien.kd_kel=kelurahan.kd_kel "
            + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "
            + "where pasien.no_rkm_medis=?",
            sqlpscarilab = "select jns_perawatan_lab.nm_perawatan, count(periksa_lab.kd_jenis_prw) as jml,periksa_lab.biaya as biaya, "
            + "sum(periksa_lab.biaya) as total,jns_perawatan_lab.kd_jenis_prw,sum(periksa_lab.tarif_perujuk+periksa_lab.tarif_tindakan_dokter) as totaldokter, "
            + "sum(periksa_lab.tarif_tindakan_petugas) as totalpetugas,sum(periksa_lab.kso) as totalkso,sum(periksa_lab.bhp) as totalbhp "
            + " from periksa_lab inner join jns_perawatan_lab on jns_perawatan_lab.kd_jenis_prw=periksa_lab.kd_jenis_prw where periksa_lab.stts_bayar='Belum' and"
            + " periksa_lab.no_rawat=? group by periksa_lab.kd_jenis_prw ",
            sqlpsdokterranap = "select dokter.nm_dokter from rawat_inap_dr "
            + "inner join dokter on rawat_inap_dr.kd_dokter=dokter.kd_dokter "
            + "where no_rawat=? group by rawat_inap_dr.kd_dokter",
            sqlpstambahan = "select nama_biaya, besar_biaya from tambahan_biaya where no_rawat=? ",
            sqlpspotongan = "select nama_pengurangan,besar_pengurangan from pengurangan_biaya where no_rawat=?",
            sqlpsdokterralan = "select dokter.nm_dokter from rawat_jl_dr "
            + "inner join dokter on rawat_jl_dr.kd_dokter=dokter.kd_dokter "
            + "where no_rawat=? group by rawat_jl_dr.kd_dokter",
            sqlpsdokterrawatjalan = "select dokter.nm_dokter from reg_periksa "
            + "inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "
            + "where no_rawat=? group by reg_periksa.kd_dokter",
            sqlpsdetaillab = "select sum(detail_periksa_lab.biaya_item) as total,sum(detail_periksa_lab.bagian_perujuk+detail_periksa_lab.bagian_dokter) as totaldokter, "
            + "sum(detail_periksa_lab.bagian_laborat) as totalpetugas,sum(detail_periksa_lab.kso) as totalkso,sum(detail_periksa_lab.bhp) as totalbhp "
            + "from detail_periksa_lab where detail_periksa_lab.no_rawat=? "
            + "and detail_periksa_lab.kd_jenis_prw=? and detail_periksa_lab.no_nota='-' ",
            sqlpscariradiologi = "select jns_perawatan_radiologi.nm_perawatan, count(periksa_radiologi.kd_jenis_prw) as jml,periksa_radiologi.biaya as biaya, "
            + "sum(periksa_radiologi.biaya) as total,jns_perawatan_radiologi.kd_jenis_prw,sum(periksa_radiologi.tarif_perujuk+periksa_radiologi.tarif_tindakan_dokter) as totaldokter, "
            + "sum(periksa_radiologi.tarif_tindakan_petugas) as totalpetugas,sum(periksa_radiologi.kso) as totalkso,sum(periksa_radiologi.bhp) as totalbhp "
            + " from periksa_radiologi inner join jns_perawatan_radiologi on jns_perawatan_radiologi.kd_jenis_prw=periksa_radiologi.kd_jenis_prw where periksa_radiologi.stts_bayar = 'Belum' and "
            + " periksa_radiologi.no_rawat=? group by periksa_radiologi.kd_jenis_prw ",
            sqlpscariralanperawat = "select jns_perawatan.nm_perawatan,rawat_jl_pr.biaya_rawat as total_byrpr,"
            + "count(rawat_jl_pr.kd_jenis_prw) as jml, "
            + "sum(rawat_jl_pr.biaya_rawat) as biaya, "
            + "sum(rawat_jl_pr.bhp) as totalbhp,"
            + "sum(rawat_jl_pr.material) as totalmaterial,"
            + "sum(rawat_jl_pr.tarif_tindakanpr) as totaltarif_tindakanpr "
            + "from rawat_jl_pr inner join jns_perawatan "
            + "on rawat_jl_pr.kd_jenis_prw=jns_perawatan.kd_jenis_prw where "
            + "rawat_jl_pr.no_rawat=? group by jns_perawatan.nm_perawatan ",
            sqlpscariralandrpr = "select jns_perawatan.nm_perawatan,rawat_jl_drpr.biaya_rawat as total_byrdrpr,"
            + "count(rawat_jl_drpr.kd_jenis_prw) as jml, "
            + "sum(rawat_jl_drpr.biaya_rawat) as biaya,"
            + "sum(rawat_jl_drpr.bhp) as totalbhp,"
            + "sum(rawat_jl_drpr.material) as totalmaterial,"
            + "rawat_jl_drpr.tarif_tindakandr,"
            + "sum(rawat_jl_drpr.tarif_tindakanpr) as totaltarif_tindakanpr, "
            + "sum(rawat_jl_drpr.tarif_tindakandr) as totaltarif_tindakandr "
            + "from rawat_jl_drpr inner join jns_perawatan "
            + "on rawat_jl_drpr.kd_jenis_prw=jns_perawatan.kd_jenis_prw where rawat_jl_drpr.stts_bayar = 'Belum' and "
            + "rawat_jl_drpr.no_rawat=? group by jns_perawatan.nm_perawatan",
            sqlpscariobat = "select databarang.nama_brng,detail_pemberian_obat.biaya_obat,"
            + "sum(detail_pemberian_obat.jml) as jml,sum(detail_pemberian_obat.embalase+detail_pemberian_obat.tuslah) as tambahan,"
            + "(sum(detail_pemberian_obat.total)-sum(detail_pemberian_obat.embalase+detail_pemberian_obat.tuslah)) as total, "
            + "sum((detail_pemberian_obat.h_beli*detail_pemberian_obat.jml)) as totalbeli "
            + "from detail_pemberian_obat inner join databarang "
            + "on detail_pemberian_obat.kode_brng=databarang.kode_brng where stts_bayar = 'Belum' "
            + "and detail_pemberian_obat.no_rawat=? group by databarang.nama_brng",
            sqlpscariralandokter = "select jns_perawatan.nm_perawatan,rawat_jl_dr.biaya_rawat as total_byrdr,"
            + "count(rawat_jl_dr.kd_jenis_prw) as jml, "
            + "sum(rawat_jl_dr.biaya_rawat) as biaya,"
            + "sum(rawat_jl_dr.bhp) as totalbhp,"
            + "sum(rawat_jl_dr.material) as totalmaterial,"
            + "rawat_jl_dr.tarif_tindakandr,"
            + "sum(rawat_jl_dr.tarif_tindakandr) as totaltarif_tindakandr "
            + "from rawat_jl_dr inner join jns_perawatan "
            + "on rawat_jl_dr.kd_jenis_prw=jns_perawatan.kd_jenis_prw where "
            + "rawat_jl_dr.no_rawat=? group by jns_perawatan.nm_perawatan",
            sqlpsobatoperasi = "select obatbhp_ok.nm_obat,beri_obat_operasi.hargasatuan,beri_obat_operasi.jumlah, "
            + "(beri_obat_operasi.hargasatuan*beri_obat_operasi.jumlah) as total "
            + "from obatbhp_ok inner join beri_obat_operasi "
            + "on beri_obat_operasi.kd_obat=obatbhp_ok.kd_obat where "
            + "beri_obat_operasi.no_rawat=? group by obatbhp_ok.nm_obat",
            sqlpsreturobat = "select databarang.nama_brng,detreturjual.h_retur, "
            + "sum(detreturjual.jml_retur * -1) as jml, "
            + "sum(detreturjual.subtotal * -1) as ttl from detreturjual inner join databarang inner join returjual "
            + "on detreturjual.kode_brng=databarang.kode_brng "
            + "and returjual.no_retur_jual=detreturjual.no_retur_jual and returjual.tgl_retur=detreturjual.tgl_retur "
            + "where returjual.no_retur_jual=? group by databarang.nama_brng",
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
            sqlpsoperasiralan = "select paket_operasi.nm_perawatan,(operasi.biayaoperator1+operasi.biayaoperator2+"
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
            + " where operasi.stts_bayar = 'Belum' and operasi.no_rawat=?",
            sqlpsnota = "insert into nota_inap values(?,?,?,?,?)",
            sqlpsbiling = "insert into billing values('0',?,?,?,?,?,?,?,?,?,?,?)",
            sqlpssudahmasuk = "select no,nm_perawatan, if(biaya<>0,biaya,null) as satu, if(jumlah<>0,jumlah,null) as dua,"
            + "if(tambahan<>0,tambahan,null) as tiga, if(totalbiaya<>0,totalbiaya,null) as empat,pemisah,status "
            + "from billing where no_rawat=? order by noindex",
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

    /**
     * Creates new form DlgPemberianObat
     *
     * @param parent
     * @param modal
     */
    public DlgDiagnosaPenyakit(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        TabModeDiagnosaPasien = new DefaultTableModel(null, new Object[]{
            "P", "Tgl.Rawat", "No.Rawat", "No.R.M.", "Nama Pasien", "Kode", "Nama Penyakit", "Status", "Jenis Diagnosa", "Petugas Coding RM"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbDiagnosaPasien.setModel(TabModeDiagnosaPasien);
        tbDiagnosaPasien.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosaPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbDiagnosaPasien.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(55);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(220);
            }
        }
        tbDiagnosaPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "P", "Tgl.Rawat", "No.Rawat", "No.R.M.", "Nama Pasien", "Kode", "Nama Penyakit", "Status", "Jenis Diagnosa", "Petugas Coding RM"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbDiagnosaPasien1.setModel(tabMode1);
        tbDiagnosaPasien1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosaPasien1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbDiagnosaPasien1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(55);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(220);
            }
        }
        tbDiagnosaPasien1.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeDiagnosa = new DefaultTableModel(null, new Object[]{
            "P", "Kode", "Nama Penyakit", "Ciri-ciri Penyakit", "Keterangan", "Ktg.Penyakit", "Ciri-ciri Umum"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbDiagnosa.setModel(tabModeDiagnosa);
        tbDiagnosa.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 7; i++) {
            TableColumn column = tbDiagnosa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setPreferredWidth(315);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDiagnosa.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeDiagnosaSekunder = new DefaultTableModel(null, new Object[]{
            "P", "Kode", "Nama Penyakit", "Ciri-ciri Penyakit", "Keterangan", "Ktg.Penyakit", "Ciri-ciri Umum"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbDiagnosa1.setModel(tabModeDiagnosaSekunder);
        tbDiagnosa1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosa1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 7; i++) {
            TableColumn column = tbDiagnosa1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setPreferredWidth(315);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDiagnosa1.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeProsedur = new DefaultTableModel(null, new Object[]{
            "P", "Kode", "Deskripsi Panjang", "Deskripsi Pendek"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbProsedur.setModel(tabModeProsedur);
        tbProsedur.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbProsedur.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbProsedur.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            }
        }
        tbProsedur.setDefaultRenderer(Object.class, new WarnaTable());

        TabModeTindakanPasien = new DefaultTableModel(null, new Object[]{
            "P", "Tgl.Rawat", "No.Rawat", "No.R.M.", "Nama Pasien", "Kode", "Nama Prosedur", "Status", "Petugas Coding RM"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbTindakanPasien.setModel(TabModeTindakanPasien);
        tbTindakanPasien.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakanPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbTindakanPasien.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            }
        }
        tbTindakanPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "P", "Tgl.Rawat", "No.Rawat", "No.R.M.", "Nama Pasien", "Kode", "Nama Prosedur", "Status","Jlh.", "Petugas Coding RM"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 0) || (colIndex == 8)) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbTindakanPasien1.setModel(tabMode2);
        tbTindakanPasien1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakanPasien1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbTindakanPasien1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            } else if (i == 8) {
                column.setPreferredWidth(40);
            } else if (i == 9) {
                column.setPreferredWidth(220);
            }
        }
        tbTindakanPasien1.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new Object[]{
            "Keterangan", "Tagihan/Tindakan/Terapi", "", "Biaya", "Jumlah", "Tambahan", "Total Biaya", ""
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 6) || (colIndex == 0)) {
                    a = true;
                }
                return a;
            }

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, 
                java.lang.Double.class, java.lang.Double.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbBilling.setModel(tabMode3);
        tbBilling.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBilling.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbBilling.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(185);
            } else if (i == 1) {
                column.setPreferredWidth(700);
            } else if (i == 2) {
                column.setPreferredWidth(15);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbBilling.setDefaultRenderer(Object.class, new WarnaTable()); 

        this.setLocation(8, 1);
        setSize(885, 674);

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        Diagnosa.setDocument(new batasInput((byte) 100).getKata(Diagnosa));
        Diagnosa1.setDocument(new batasInput((byte) 100).getKata(Diagnosa));
        Prosedur.setDocument(new batasInput((byte) 100).getKata(Prosedur));
        TCariPasien.setDocument(new batasInput((byte) 20).getKata(TCariPasien));
        
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampilDiagStatistik();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampilDiagStatistik();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampilDiagStatistik();
                }
            });
        }

        if (koneksiDB.cariCepat().equals("aktif")) {
            Diagnosa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampildiagnosa();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampildiagnosa();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampildiagnosa();
                }
            });
        }

        if (koneksiDB.cariCepat().equals("aktif")) {
            Diagnosa1.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampildiagnosaSekunder();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampildiagnosaSekunder();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampildiagnosaSekunder();
                }
            });
        }
        ChkInput.setSelected(false);
        isForm();

        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (pasien.getTable().getSelectedRow() != -1) {
                    TCariPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                }
                TCariPasien.requestFocus();
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

        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    pasien.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        LoadHTML1.setEditable(true);
        LoadHTML2.setEditable(true);
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML1.setEditorKit(kit);
        LoadHTML2.setEditorKit(kit);
        
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(".isi td{border-right: 1px solid #edf2e8;font: 10px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}");
        Document doc = kit.createDefaultDocument();
        LoadHTML1.setDocument(doc);
        LoadHTML2.setDocument(doc);
        
        LoadHTML1.setEditable(false);
        LoadHTML2.setEditable(false);
        
        LoadHTML1.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        LoadHTML2.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    int w = 0, urut;

    //private DlgCariObatPenyakit dlgobtpny=new DlgCariObatPenyakit(null,false);
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnSimpanQTYinadrg = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnContengSemua = new javax.swing.JMenuItem();
        MnHapusConteng = new javax.swing.JMenuItem();
        kdpoli = new widget.TextBox();
        nmpoli = new widget.TextBox();
        DTPTgl = new widget.Tanggal();
        TNoNota = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKlaim = new widget.Button();
        BtnResume = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel14 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel19 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel16 = new widget.Label();
        TCariPasien = new widget.TextBox();
        BtnSeek4 = new widget.Button();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        TabData = new javax.swing.JTabbedPane();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel17 = new widget.Label();
        Scroll1 = new widget.ScrollPane();
        tbDiagnosa = new widget.Table();
        Diagnosa = new widget.TextBox();
        BtnCariPenyakit = new widget.Button();
        btnTambahPenyakit = new widget.Button();
        btnTambahProsedur = new widget.Button();
        BtnCariProsedur = new widget.Button();
        Prosedur = new widget.TextBox();
        jLabel15 = new widget.Label();
        Scroll2 = new widget.ScrollPane();
        tbProsedur = new widget.Table();
        Status = new widget.ComboBox();
        jLabel18 = new widget.Label();
        Diagnosa1 = new widget.TextBox();
        Scroll4 = new widget.ScrollPane();
        tbDiagnosa1 = new widget.Table();
        BtnCariPenyakit1 = new widget.Button();
        btnTambahPenyakit1 = new widget.Button();
        cmbDiagPro = new widget.ComboBox();
        jLabel20 = new widget.Label();
        chkDiagnosa = new widget.CekBox();
        Scroll43 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        Scroll44 = new widget.ScrollPane();
        LoadHTML2 = new widget.editorpane();
        internalFrame10 = new widget.InternalFrame();
        panelGlass10 = new widget.panelisi();
        jLabel26 = new widget.Label();
        TtlSemua = new widget.TextBox();
        BtnCari1 = new widget.Button();
        chkBayar = new widget.CekBox();
        lbl_jns_byr = new widget.Label();
        Scroll14 = new widget.ScrollPane();
        tbBilling = new widget.Table();
        ChkInput = new widget.CekBox();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        internalFrame9 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbDiagnosaPasien = new widget.Table();
        Scroll5 = new widget.ScrollPane();
        tbDiagnosaPasien1 = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        internalFrame4 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        tbTindakanPasien = new widget.Table();
        Scroll6 = new widget.ScrollPane();
        tbTindakanPasien1 = new widget.Table();
        Scroll7 = new widget.ScrollPane();
        FormInput1 = new widget.PanelBiasa();
        jLabel12 = new widget.Label();
        Scroll9 = new widget.ScrollPane();
        TDiagDokter = new widget.TextArea();
        jLabel21 = new widget.Label();
        Scroll10 = new widget.ScrollPane();
        TKeluhan1 = new widget.TextArea();
        jLabel22 = new widget.Label();
        Scroll11 = new widget.ScrollPane();
        TRincianTindakan1 = new widget.TextArea();
        jLabel23 = new widget.Label();
        Scroll8 = new widget.ScrollPane();
        TDiagPerawat = new widget.TextArea();
        jLabel24 = new widget.Label();
        Scroll12 = new widget.ScrollPane();
        TKeluhan2 = new widget.TextArea();
        jLabel25 = new widget.Label();
        Scroll13 = new widget.ScrollPane();
        TRincianTindakan2 = new widget.TextArea();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSimpanQTYinadrg.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSimpanQTYinadrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSimpanQTYinadrg.setText("Simpan Jumlah Prosedur INADRG");
        MnSimpanQTYinadrg.setName("MnSimpanQTYinadrg"); // NOI18N
        MnSimpanQTYinadrg.setPreferredSize(new java.awt.Dimension(240, 26));
        MnSimpanQTYinadrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSimpanQTYinadrgActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSimpanQTYinadrg);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnContengSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnContengSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnContengSemua.setText("Conteng Semua");
        MnContengSemua.setName("MnContengSemua"); // NOI18N
        MnContengSemua.setPreferredSize(new java.awt.Dimension(155, 26));
        MnContengSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnContengSemuaActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnContengSemua);

        MnHapusConteng.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusConteng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusConteng.setText("Hapus Conteng Semua");
        MnHapusConteng.setName("MnHapusConteng"); // NOI18N
        MnHapusConteng.setPreferredSize(new java.awt.Dimension(155, 26));
        MnHapusConteng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusContengActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHapusConteng);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N

        nmpoli.setEditable(false);
        nmpoli.setForeground(new java.awt.Color(0, 0, 0));
        nmpoli.setHighlighter(null);
        nmpoli.setName("nmpoli"); // NOI18N

        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-04-2025 17:38:34" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.setPreferredSize(new java.awt.Dimension(140, 23));
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });

        TNoNota.setEditable(false);
        TNoNota.setForeground(new java.awt.Color(0, 0, 0));
        TNoNota.setName("TNoNota"); // NOI18N
        TNoNota.setPreferredSize(new java.awt.Dimension(150, 23));
        TNoNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoNotaKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Riwayat Diagnosa & Prosedur Tindakan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
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
        panelGlass8.add(BtnSimpan);

        BtnBatal.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

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
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('P');
        BtnPrint.setText("Formulir Klaim Pasien");
        BtnPrint.setToolTipText("Alt+P");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(170, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        BtnKlaim.setForeground(new java.awt.Color(0, 0, 0));
        BtnKlaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnKlaim.setMnemonic('P');
        BtnKlaim.setText("Pengajuan Klaim JKN");
        BtnKlaim.setToolTipText("Alt+P");
        BtnKlaim.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnKlaim.setName("BtnKlaim"); // NOI18N
        BtnKlaim.setPreferredSize(new java.awt.Dimension(170, 30));
        BtnKlaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKlaimActionPerformed(evt);
            }
        });
        BtnKlaim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKlaimKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKlaim);

        BtnResume.setForeground(new java.awt.Color(0, 0, 0));
        BtnResume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PatientFile.png"))); // NOI18N
        BtnResume.setMnemonic('R');
        BtnResume.setText("Rngksn. Pulang / Resume");
        BtnResume.setToolTipText("Alt+R");
        BtnResume.setName("BtnResume"); // NOI18N
        BtnResume.setPreferredSize(new java.awt.Dimension(200, 30));
        BtnResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResumeActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnResume);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnAll);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass8.add(jLabel10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass8.add(LCount);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
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
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tgl. Rawat :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(63, 23));
        panelGlass9.add(jLabel14);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-04-2025" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(DTPCari1);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("s.d");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(18, 23));
        panelGlass9.add(jLabel19);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-04-2025" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(DTPCari2);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("No.RM :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(jLabel16);

        TCariPasien.setForeground(new java.awt.Color(0, 0, 0));
        TCariPasien.setName("TCariPasien"); // NOI18N
        TCariPasien.setPreferredSize(new java.awt.Dimension(130, 23));
        TCariPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariPasienKeyPressed(evt);
            }
        });
        panelGlass9.add(TCariPasien);

        BtnSeek4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnSeek4.setMnemonic('5');
        BtnSeek4.setToolTipText("Alt+5");
        BtnSeek4.setName("BtnSeek4"); // NOI18N
        BtnSeek4.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek4ActionPerformed(evt);
            }
        });
        BtnSeek4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek4KeyPressed(evt);
            }
        });
        panelGlass9.add(BtnSeek4);

        jSeparator5.setBackground(new java.awt.Color(220, 225, 215));
        jSeparator5.setForeground(new java.awt.Color(220, 225, 215));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setName("jSeparator5"); // NOI18N
        jSeparator5.setOpaque(true);
        jSeparator5.setPreferredSize(new java.awt.Dimension(1, 23));
        panelGlass9.add(jSeparator5);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(190, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+6");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 23));
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
        panelGlass9.add(BtnCari);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(865, 420));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        TabData.setBackground(new java.awt.Color(255, 255, 254));
        TabData.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabData.setName("TabData"); // NOI18N
        TabData.setPreferredSize(new java.awt.Dimension(270, 106));
        TabData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabDataMouseClicked(evt);
            }
        });

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(865, 217));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No. Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 12, 130, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(135, 12, 140, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(280, 12, 90, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.setPreferredSize(new java.awt.Dimension(25, 28));
        FormInput.add(TPasien);
        TPasien.setBounds(375, 12, 330, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Diagnosa Primer :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 42, 130, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Status :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(710, 12, 50, 23);

        Scroll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbDiagnosa.setName("tbDiagnosa"); // NOI18N
        tbDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosaKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbDiagnosa);

        FormInput.add(Scroll1);
        Scroll1.setBounds(30, 69, 720, 90);

        Diagnosa.setForeground(new java.awt.Color(0, 0, 0));
        Diagnosa.setName("Diagnosa"); // NOI18N
        Diagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(Diagnosa);
        Diagnosa.setBounds(135, 42, 230, 23);

        BtnCariPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariPenyakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariPenyakit.setMnemonic('1');
        BtnCariPenyakit.setToolTipText("Alt+1");
        BtnCariPenyakit.setName("BtnCariPenyakit"); // NOI18N
        BtnCariPenyakit.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariPenyakitActionPerformed(evt);
            }
        });
        FormInput.add(BtnCariPenyakit);
        BtnCariPenyakit.setBounds(373, 42, 28, 23);

        btnTambahPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahPenyakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnTambahPenyakit.setMnemonic('2');
        btnTambahPenyakit.setToolTipText("Alt+2");
        btnTambahPenyakit.setEnabled(false);
        btnTambahPenyakit.setName("btnTambahPenyakit"); // NOI18N
        btnTambahPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahPenyakitActionPerformed(evt);
            }
        });
        FormInput.add(btnTambahPenyakit);
        btnTambahPenyakit.setBounds(403, 42, 28, 23);

        btnTambahProsedur.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahProsedur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnTambahProsedur.setMnemonic('2');
        btnTambahProsedur.setToolTipText("Alt+2");
        btnTambahProsedur.setEnabled(false);
        btnTambahProsedur.setName("btnTambahProsedur"); // NOI18N
        btnTambahProsedur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahProsedurActionPerformed(evt);
            }
        });
        FormInput.add(btnTambahProsedur);
        btnTambahProsedur.setBounds(1100, 42, 28, 23);

        BtnCariProsedur.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariProsedur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariProsedur.setMnemonic('1');
        BtnCariProsedur.setToolTipText("Alt+1");
        BtnCariProsedur.setName("BtnCariProsedur"); // NOI18N
        BtnCariProsedur.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariProsedur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariProsedurActionPerformed(evt);
            }
        });
        FormInput.add(BtnCariProsedur);
        BtnCariProsedur.setBounds(1065, 42, 28, 23);

        Prosedur.setForeground(new java.awt.Color(0, 0, 0));
        Prosedur.setName("Prosedur"); // NOI18N
        Prosedur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurKeyPressed(evt);
            }
        });
        FormInput.add(Prosedur);
        Prosedur.setBounds(825, 42, 230, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Prosedur :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(758, 42, 60, 23);

        Scroll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbProsedur.setName("tbProsedur"); // NOI18N
        tbProsedur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbProsedurKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbProsedur);

        FormInput.add(Scroll2);
        Scroll2.setBounds(758, 67, 620, 290);

        Status.setForeground(new java.awt.Color(0, 0, 0));
        Status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ralan", "Ranap" }));
        Status.setEnabled(false);
        Status.setName("Status"); // NOI18N
        Status.setPreferredSize(new java.awt.Dimension(308, 23));
        FormInput.add(Status);
        Status.setBounds(765, 12, 70, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Diagnosa Sekunder :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 165, 130, 23);

        Diagnosa1.setForeground(new java.awt.Color(0, 0, 0));
        Diagnosa1.setName("Diagnosa1"); // NOI18N
        Diagnosa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Diagnosa1KeyPressed(evt);
            }
        });
        FormInput.add(Diagnosa1);
        Diagnosa1.setBounds(135, 165, 230, 23);

        Scroll4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbDiagnosa1.setName("tbDiagnosa1"); // NOI18N
        tbDiagnosa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosa1KeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbDiagnosa1);

        FormInput.add(Scroll4);
        Scroll4.setBounds(30, 195, 720, 162);

        BtnCariPenyakit1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariPenyakit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariPenyakit1.setMnemonic('1');
        BtnCariPenyakit1.setToolTipText("Alt+1");
        BtnCariPenyakit1.setName("BtnCariPenyakit1"); // NOI18N
        BtnCariPenyakit1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariPenyakit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariPenyakit1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnCariPenyakit1);
        BtnCariPenyakit1.setBounds(373, 165, 28, 23);

        btnTambahPenyakit1.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahPenyakit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnTambahPenyakit1.setMnemonic('2');
        btnTambahPenyakit1.setToolTipText("Alt+2");
        btnTambahPenyakit1.setEnabled(false);
        btnTambahPenyakit1.setName("btnTambahPenyakit1"); // NOI18N
        btnTambahPenyakit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahPenyakit1ActionPerformed(evt);
            }
        });
        FormInput.add(btnTambahPenyakit1);
        btnTambahPenyakit1.setBounds(403, 165, 28, 23);

        cmbDiagPro.setForeground(new java.awt.Color(0, 0, 0));
        cmbDiagPro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "STATISTIK (V5)", "INADRG (V6)" }));
        cmbDiagPro.setName("cmbDiagPro"); // NOI18N
        cmbDiagPro.setPreferredSize(new java.awt.Dimension(308, 23));
        FormInput.add(cmbDiagPro);
        cmbDiagPro.setBounds(993, 12, 108, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jns. Diagnosa & Prosedur : ");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(840, 12, 150, 23);

        chkDiagnosa.setBackground(new java.awt.Color(255, 255, 250));
        chkDiagnosa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        chkDiagnosa.setText("Cek Diagnosa & Prosedur Sesuai Kunjungan Terakhir");
        chkDiagnosa.setBorderPainted(true);
        chkDiagnosa.setBorderPaintedFlat(true);
        chkDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDiagnosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDiagnosa.setName("chkDiagnosa"); // NOI18N
        chkDiagnosa.setOpaque(false);
        chkDiagnosa.setPreferredSize(new java.awt.Dimension(175, 23));
        chkDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDiagnosaActionPerformed(evt);
            }
        });
        FormInput.add(chkDiagnosa);
        chkDiagnosa.setBounds(445, 42, 290, 23);

        TabData.addTab("Input Diagnosa & Prosedur (Tindakan)", FormInput);

        Scroll43.setName("Scroll43"); // NOI18N
        Scroll43.setOpaque(true);

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll43.setViewportView(LoadHTML1);

        TabData.addTab("Hasil Pemeriksaan Radiologi", Scroll43);

        Scroll44.setName("Scroll44"); // NOI18N
        Scroll44.setOpaque(true);

        LoadHTML2.setBorder(null);
        LoadHTML2.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML2.setName("LoadHTML2"); // NOI18N
        Scroll44.setViewportView(LoadHTML2);

        TabData.addTab("Hasil Pemeriksaan Lab.", Scroll44);

        internalFrame10.setBorder(null);
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setLayout(new java.awt.BorderLayout());

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Total Tagihan : Rp. ");
        jLabel26.setName("jLabel26"); // NOI18N
        jLabel26.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass10.add(jLabel26);

        TtlSemua.setEditable(false);
        TtlSemua.setForeground(new java.awt.Color(0, 0, 0));
        TtlSemua.setText("0");
        TtlSemua.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        TtlSemua.setName("TtlSemua"); // NOI18N
        TtlSemua.setPreferredSize(new java.awt.Dimension(190, 23));
        panelGlass10.add(TtlSemua);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('6');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+6");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnCari1);

        chkBayar.setForeground(new java.awt.Color(0, 0, 0));
        chkBayar.setSelected(true);
        chkBayar.setText("Sudah Bayar");
        chkBayar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBayar.setName("chkBayar"); // NOI18N
        chkBayar.setOpaque(false);
        chkBayar.setPreferredSize(new java.awt.Dimension(200, 23));
        chkBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBayarActionPerformed(evt);
            }
        });
        panelGlass10.add(chkBayar);

        lbl_jns_byr.setForeground(new java.awt.Color(0, 0, 0));
        lbl_jns_byr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_jns_byr.setText("lbl_jns_byr");
        lbl_jns_byr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_jns_byr.setName("lbl_jns_byr"); // NOI18N
        lbl_jns_byr.setPreferredSize(new java.awt.Dimension(260, 23));
        panelGlass10.add(lbl_jns_byr);

        internalFrame10.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        Scroll14.setName("Scroll14"); // NOI18N

        tbBilling.setName("tbBilling"); // NOI18N
        Scroll14.setViewportView(tbBilling);

        internalFrame10.add(Scroll14, java.awt.BorderLayout.CENTER);

        TabData.addTab("Billing/Pembayaran", internalFrame10);

        PanelInput.add(TabData, java.awt.BorderLayout.CENTER);

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        TabRawat.setBackground(new java.awt.Color(250, 255, 245));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRawat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        internalFrame9.setBorder(null);
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.GridLayout(1, 2));

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Diagnosa Statistik (V5) ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N

        tbDiagnosaPasien.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiagnosaPasien.setComponentPopupMenu(jPopupMenu2);
        tbDiagnosaPasien.setName("tbDiagnosaPasien"); // NOI18N
        tbDiagnosaPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDiagnosaPasienMouseClicked(evt);
            }
        });
        tbDiagnosaPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosaPasienKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbDiagnosaPasien);

        internalFrame9.add(Scroll);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Diagnosa INADRG (V6) ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N

        tbDiagnosaPasien1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiagnosaPasien1.setName("tbDiagnosaPasien1"); // NOI18N
        tbDiagnosaPasien1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDiagnosaPasien1MouseClicked(evt);
            }
        });
        tbDiagnosaPasien1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosaPasien1KeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbDiagnosaPasien1);

        internalFrame9.add(Scroll5);

        internalFrame2.add(internalFrame9, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Diagnosa", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.GridLayout(1, 2));

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Prosedur Statistik (V5) ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N

        tbTindakanPasien.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTindakanPasien.setComponentPopupMenu(jPopupMenu2);
        tbTindakanPasien.setName("tbTindakanPasien"); // NOI18N
        tbTindakanPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTindakanPasienMouseClicked(evt);
            }
        });
        tbTindakanPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTindakanPasienKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbTindakanPasien);

        internalFrame4.add(Scroll3);

        Scroll6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Prosedur INADRG (V6) ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll6.setName("Scroll6"); // NOI18N

        tbTindakanPasien1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTindakanPasien1.setComponentPopupMenu(jPopupMenu1);
        tbTindakanPasien1.setName("tbTindakanPasien1"); // NOI18N
        tbTindakanPasien1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTindakanPasien1MouseClicked(evt);
            }
        });
        tbTindakanPasien1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTindakanPasien1KeyPressed(evt);
            }
        });
        Scroll6.setViewportView(tbTindakanPasien1);

        internalFrame4.add(Scroll6);

        internalFrame3.add(internalFrame4, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Prosedur", internalFrame3);

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);
        Scroll7.setPreferredSize(new java.awt.Dimension(192, 220));

        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(190, 400));
        FormInput1.setLayout(null);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Diagnosa Resume dari Dokter : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput1.add(jLabel12);
        jLabel12.setBounds(0, 10, 208, 23);

        Scroll9.setName("Scroll9"); // NOI18N

        TDiagDokter.setEditable(false);
        TDiagDokter.setColumns(20);
        TDiagDokter.setRows(5);
        TDiagDokter.setName("TDiagDokter"); // NOI18N
        TDiagDokter.setPreferredSize(new java.awt.Dimension(170, 600));
        Scroll9.setViewportView(TDiagDokter);

        FormInput1.add(Scroll9);
        Scroll9.setBounds(212, 10, 430, 55);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Catatan Keluhan Dari Dokter : ");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput1.add(jLabel21);
        jLabel21.setBounds(0, 73, 208, 23);

        Scroll10.setName("Scroll10"); // NOI18N

        TKeluhan1.setEditable(false);
        TKeluhan1.setColumns(20);
        TKeluhan1.setRows(5);
        TKeluhan1.setName("TKeluhan1"); // NOI18N
        TKeluhan1.setPreferredSize(new java.awt.Dimension(170, 600));
        Scroll10.setViewportView(TKeluhan1);

        FormInput1.add(Scroll10);
        Scroll10.setBounds(212, 73, 430, 55);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Catatan Rincian Tindakan Dari Dokter :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput1.add(jLabel22);
        jLabel22.setBounds(0, 135, 208, 23);

        Scroll11.setName("Scroll11"); // NOI18N

        TRincianTindakan1.setEditable(false);
        TRincianTindakan1.setColumns(20);
        TRincianTindakan1.setRows(5);
        TRincianTindakan1.setName("TRincianTindakan1"); // NOI18N
        TRincianTindakan1.setPreferredSize(new java.awt.Dimension(170, 600));
        Scroll11.setViewportView(TRincianTindakan1);

        FormInput1.add(Scroll11);
        Scroll11.setBounds(212, 135, 430, 55);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Diagnosa Resume dari Perawat/Bidan : ");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput1.add(jLabel23);
        jLabel23.setBounds(640, 10, 240, 23);

        Scroll8.setName("Scroll8"); // NOI18N

        TDiagPerawat.setEditable(false);
        TDiagPerawat.setColumns(20);
        TDiagPerawat.setRows(5);
        TDiagPerawat.setName("TDiagPerawat"); // NOI18N
        TDiagPerawat.setPreferredSize(new java.awt.Dimension(170, 600));
        Scroll8.setViewportView(TDiagPerawat);

        FormInput1.add(Scroll8);
        Scroll8.setBounds(883, 10, 430, 55);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Catatan Keluhan Dari Perawat/Bidan : ");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput1.add(jLabel24);
        jLabel24.setBounds(640, 73, 240, 23);

        Scroll12.setName("Scroll12"); // NOI18N

        TKeluhan2.setEditable(false);
        TKeluhan2.setColumns(20);
        TKeluhan2.setRows(5);
        TKeluhan2.setName("TKeluhan2"); // NOI18N
        TKeluhan2.setPreferredSize(new java.awt.Dimension(170, 600));
        Scroll12.setViewportView(TKeluhan2);

        FormInput1.add(Scroll12);
        Scroll12.setBounds(883, 73, 430, 55);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Catatan Rincian Tindakan Dari Perawat/Bidan : ");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput1.add(jLabel25);
        jLabel25.setBounds(640, 135, 240, 23);

        Scroll13.setName("Scroll13"); // NOI18N

        TRincianTindakan2.setEditable(false);
        TRincianTindakan2.setColumns(20);
        TRincianTindakan2.setRows(5);
        TRincianTindakan2.setName("TRincianTindakan2"); // NOI18N
        TRincianTindakan2.setPreferredSize(new java.awt.Dimension(170, 600));
        Scroll13.setViewportView(TRincianTindakan2);

        FormInput1.add(Scroll13);
        Scroll13.setBounds(883, 135, 430, 55);

        Scroll7.setViewportView(FormInput1);

        TabRawat.addTab("Pemeriksaan Poliklinik / IGD", Scroll7);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            tampilDiagStatistik();
            tampilDiagInadrg();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampilProsStatistik();
            tampilProsInadrg();
        } else if (TabRawat.getSelectedIndex() == 2) {
            if (Status.getSelectedIndex() == 0) {
                tampilPemeriksaan();
            } else {
                TDiagDokter.setText("-");
                TKeluhan1.setText("-");
                TRincianTindakan1.setText("-");

                TDiagPerawat.setText("-");
                TKeluhan2.setText("-");
                TRincianTindakan2.setText("-");
            }
        }
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        cekKlaim = "";
        
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            cekKlaim = Sequel.cariIsi("SELECT klaim_final FROM eklaim_new_claim WHERE no_rawat='" + TNoRw.getText() + "'");

            if (cekKlaim.equals("Final")) {
                JOptionPane.showMessageDialog(null, "Proses pengajuan klaim selesai dilakukan, data ICD-10 atau ICD-9-CM pasien ini tdk. dapat disimpan...!!");
            } else {
                if (cmbDiagPro.getSelectedIndex() == 0) {
                    simpan_diagproStatistik();
                    simpan_diagproINADRG();
                } else if (cmbDiagPro.getSelectedIndex() == 1) {
                    simpan_diagproStatistik();
                } else if (cmbDiagPro.getSelectedIndex() == 2) {
                    simpan_diagproINADRG();
                }
           
                BtnCariActionPerformed(null);
                BtnBatalActionPerformed(null);
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Diagnosa, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        Diagnosa.setText("");
        Diagnosa1.setText("");
        kdpoli.setText("");
        nmpoli.setText("");
        Prosedur.setText("");
        cmbDiagPro.setSelectedIndex(0);
        TNoRw.requestFocus();        
        chkDiagnosa.setSelected(false);
        
        if (Status.getSelectedIndex() == 0) {
            chkDiagnosa.setEnabled(true);
            bersihkanConteng();
        } else {
            chkDiagnosa.setEnabled(false);
            bersihkanConteng();
            tampildiagnosa();
            tampildiagnosaSekunder();
            tampilprosedure();
        }
        
        ChkInput.setSelected(true);
        isForm();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBatalActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        cekKlaim = "";
       
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
        } else if (!(TPasien.getText().trim().equals(""))) {
            cekKlaim = Sequel.cariIsi("SELECT klaim_final FROM eklaim_new_claim WHERE no_rawat='" + TNoRw.getText() + "'");

            if (cekKlaim.equals("Final")) {
                JOptionPane.showMessageDialog(null, "Proses pengajuan klaim selesai dilakukan, data ICD-10 atau ICD-9-CM pasien ini tdk. dapat dihapus...!!");
            } else {
                if (cmbDiagPro.getSelectedIndex() == 0) {
                    hapus_diagproStatistik();
                    hapus_diagproINADRG();
                } else if (cmbDiagPro.getSelectedIndex() == 1) {
                    hapus_diagproStatistik();
                } else if (cmbDiagPro.getSelectedIndex() == 2) {
                    hapus_diagproINADRG();
                }
            }

            BtnCariActionPerformed(null);
            BtnBatalActionPerformed(null);
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        cekPremier = 0;
        cekPremier = Sequel.cariInteger("select count(-1) from diagnosa_pasien where no_rawat='" + TNoRw.getText() + "' and prioritas=1");

        if (TabRawat.getSelectedIndex() == 0) {
            if (TabModeDiagnosaPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Belum ada diagnosa pasien yang tersimpan...!!!!");
                BtnBatal.requestFocus();
            } else if (TabModeDiagnosaPasien.getRowCount() != 0 && (Status.getSelectedItem().equals("Ralan"))) {
                if (cekPremier == 0) {
                    JOptionPane.showMessageDialog(null, "Diagnosa primer belum tersimpan untuk kunjungan pasien saat ini...");
                } else {
                    formulirKlaim();
                }
            } else if (!Status.getSelectedItem().equals("Ralan")) {
                JOptionPane.showMessageDialog(null, "Untuk saat ini hanya mencetak formulir klaim pasien rawat jalan saja...!!!!");
            }

        } else if (TabRawat.getSelectedIndex() == 1) {
            if (cekPremier == 0 && (Status.getSelectedItem().equals("Ralan"))) {
                JOptionPane.showMessageDialog(null, "Diagnosa primer belum tersimpan untuk kunjungan pasien saat ini...");
            } else if (!Status.getSelectedItem().equals("Ralan")) {
                JOptionPane.showMessageDialog(null, "Untuk saat ini hanya mencetak formulir klaim pasien rawat jalan saja...!!!!");
            } else {
                formulirKlaim();
            }
        }
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        
        if (Status.getSelectedIndex() == 0) {
            bersihkanConteng();
        } else if (Status.getSelectedIndex() == 1) {
            bersihkanConteng();
            tampildiagnosa();
            tampildiagnosaSekunder();
            tampilprosedure();
        }
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        TCariPasien.setText("");
        BtnCariActionPerformed(null);
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TCariPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (TabRawat.getSelectedIndex() == 0) {
                tampilDiagStatistik();
                tampilDiagInadrg();
            } else if (TabRawat.getSelectedIndex() == 1) {
                tampilProsStatistik();
                tampilProsInadrg();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSeek4.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            DTPCari2.requestFocus();
        }
    }//GEN-LAST:event_TCariPasienKeyPressed

    private void BtnSeek4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek4ActionPerformed
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
    }//GEN-LAST:event_BtnSeek4ActionPerformed

    private void BtnSeek4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek4KeyPressed
        Valid.pindah(evt, TCariPasien, DTPCari1);
    }//GEN-LAST:event_BtnSeek4KeyPressed

    private void tbDiagnosaPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosaPasienMouseClicked
        if (TabModeDiagnosaPasien.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }

        }
}//GEN-LAST:event_tbDiagnosaPasienMouseClicked

    private void tbDiagnosaPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosaPasienKeyPressed
        if (TabModeDiagnosaPasien.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbDiagnosaPasienKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void DiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!TNoRw.getText().equals("")) {
                tampildiagnosa();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (akses.getpenyakit() == true) {
                btnTambahPenyakitActionPerformed(null);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbDiagnosa.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TNoRw.requestFocus();
        }
    }//GEN-LAST:event_DiagnosaKeyPressed

    private void BtnCariPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariPenyakitActionPerformed
        if (!TNoRw.getText().equals("")) {
            tampildiagnosa();
        }
    }//GEN-LAST:event_BtnCariPenyakitActionPerformed

    private void btnTambahPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPenyakitActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenyakit tariflab = new DlgPenyakit(null, false);
        tariflab.emptTeks();
        tariflab.isCek();
        tariflab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        tariflab.setLocationRelativeTo(internalFrame1);
        tariflab.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTambahPenyakitActionPerformed

    private void tbDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosaKeyPressed
        if (tbDiagnosa.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    i = tbDiagnosa.getSelectedColumn();
                    if (i == 1) {
                        if (tbDiagnosa.getSelectedRow() > -1) {
                            tbDiagnosa.setValueAt(true, tbDiagnosa.getSelectedRow(), 0);
                        }
                        Diagnosa.setText("");
                        Diagnosa.requestFocus();
                    }
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                Diagnosa.setText("");
                Diagnosa.requestFocus();
            }
        }
    }//GEN-LAST:event_tbDiagnosaKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        TabData.setSelectedIndex(0);
        tampildiagnosa();
        tampildiagnosaSekunder();
        tampilprosedure();
    }//GEN-LAST:event_formWindowOpened

    private void btnTambahProsedurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahProsedurActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgICD9 tariflab = new DlgICD9(null, false);
        tariflab.emptTeks();
        tariflab.isCek();
        tariflab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        tariflab.setLocationRelativeTo(internalFrame1);
        tariflab.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTambahProsedurActionPerformed

    private void BtnCariProsedurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariProsedurActionPerformed
        if (!TNoRw.getText().equals("")) {
            tampilprosedure();
        }
    }//GEN-LAST:event_BtnCariProsedurActionPerformed

    private void ProsedurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!TNoRw.getText().equals("")) {
                tampilprosedure();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (akses.geticd9() == true) {
                btnTambahProsedurActionPerformed(null);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbProsedur.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TNoRw.requestFocus();
        }
    }//GEN-LAST:event_ProsedurKeyPressed

    private void tbProsedurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProsedurKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProsedurKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        BtnCariActionPerformed(null);
    }//GEN-LAST:event_TabRawatMouseClicked

    private void tbTindakanPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTindakanPasienMouseClicked
        if (TabModeTindakanPasien.getRowCount() != 0) {
            try {
                getData2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTindakanPasienMouseClicked

    private void tbTindakanPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTindakanPasienKeyPressed
        if (TabModeTindakanPasien.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData2();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTindakanPasienKeyPressed

    private void Diagnosa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Diagnosa1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!TNoRw.getText().equals("")) {
                tampildiagnosaSekunder();
            }
        } //        else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        //            if (var.getpenyakit1() == true) {
        //                btnTambahPenyakit1ActionPerformed(null);
        //            }
        //        } 
        else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbDiagnosa1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TNoRw.requestFocus();
        }
    }//GEN-LAST:event_Diagnosa1KeyPressed

    private void tbDiagnosa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosa1KeyPressed
        if (tbDiagnosa1.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    i = tbDiagnosa1.getSelectedColumn();
                    if (i == 1) {
                        if (tbDiagnosa1.getSelectedRow() > -1) {
                            tbDiagnosa1.setValueAt(true, tbDiagnosa1.getSelectedRow(), 0);
                        }
                        Diagnosa1.setText("");
                        Diagnosa1.requestFocus();
                    }
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                Diagnosa1.setText("");
                Diagnosa1.requestFocus();
            }
        }
    }//GEN-LAST:event_tbDiagnosa1KeyPressed

    private void BtnCariPenyakit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariPenyakit1ActionPerformed
        if (!TNoRw.getText().equals("")) {
            tampildiagnosaSekunder();
        }
    }//GEN-LAST:event_BtnCariPenyakit1ActionPerformed

    private void btnTambahPenyakit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPenyakit1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenyakit diagsekunder = new DlgPenyakit(null, false);
        diagsekunder.emptTeks();
        diagsekunder.isCek();
        diagsekunder.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        diagsekunder.setLocationRelativeTo(internalFrame1);
        diagsekunder.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTambahPenyakit1ActionPerformed

    private void tbDiagnosaPasien1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosaPasien1MouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getData3();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbDiagnosaPasien1MouseClicked

    private void tbDiagnosaPasien1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosaPasien1KeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData3();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDiagnosaPasien1KeyPressed

    private void tbTindakanPasien1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTindakanPasien1MouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getData4();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbTindakanPasien1MouseClicked

    private void tbTindakanPasien1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTindakanPasien1KeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData4();
                } catch (java.lang.NullPointerException e) {
                }
            }

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
                    if (tbTindakanPasien1.getValueAt(i, 8).toString().equals("") || Integer.parseInt(tbTindakanPasien1.getValueAt(i, 8).toString()) == 0) {
                        tabMode2.setValueAt("1", i, 8);
                    }
                }
            }
        }
    }//GEN-LAST:event_tbTindakanPasien1KeyPressed

    private void MnSimpanQTYinadrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSimpanQTYinadrgActionPerformed
        simpanQTYprosedurINADRG();
        for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
            tbTindakanPasien1.setValueAt(false, i, 0);
        }
        tampilProsInadrg();
    }//GEN-LAST:event_MnSimpanQTYinadrgActionPerformed

    private void BtnKlaimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKlaimActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (!Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals("B01")) {
            JOptionPane.showMessageDialog(null, "Hanya untuk pengajuan klaim pasien BPJS saja...!!!");
        } else if (tbDiagnosaPasien.getRowCount() == 0 && tbDiagnosaPasien1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Diagnosa utk. proses pengajuan klaim belum tersimpan...!!!");
        } else if (Sequel.cariInteger("select count(-1) from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='1'") == 0) {
            JOptionPane.showMessageDialog(null, "SEP nya belum ada utk. proses pengajuan klaim...!!!");
        } else {
            INACBGDaftarKlaim diklaim = new INACBGDaftarKlaim(null, false);
            diklaim.isCek();
            diklaim.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            diklaim.setLocationRelativeTo(internalFrame1);
            diklaim.verifData();
            diklaim.KlaimRAZA(TNoRw.getText(), Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + TNoRw.getText() + "'"),
                    "JKN", "3", Sequel.cariIsi("select tglsep from bridging_sep where no_rawat='" + TNoRw.getText() + "'"));
            diklaim.setVisible(true);
        }
    }//GEN-LAST:event_BtnKlaimActionPerformed

    private void BtnKlaimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKlaimKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKlaimKeyPressed

    private void MnContengSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnContengSemuaActionPerformed
        BtnCariActionPerformed(null);
        if (TabRawat.getSelectedIndex() == 0) {
            if (tbDiagnosaPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Belum ada diagnosa statistik (V5) yang tersimpan...!!!");
            } else {                
                for (i = 0; i < tbDiagnosaPasien.getRowCount(); i++) {
                    tbDiagnosaPasien.setValueAt(Boolean.TRUE, i, 0);
                }
                
                for (i = 0; i < tbDiagnosaPasien1.getRowCount(); i++) {
                    tbDiagnosaPasien1.setValueAt(Boolean.TRUE, i, 0);
                }
            }
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (tbTindakanPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Belum ada prosedur statistik (V5) yang tersimpan...!!!");
            } else {                
                for (i = 0; i < tbTindakanPasien.getRowCount(); i++) {
                    tbTindakanPasien.setValueAt(Boolean.TRUE, i, 0);
                }
                
                for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
                    tbTindakanPasien1.setValueAt(Boolean.TRUE, i, 0);
                }
            }
        }
    }//GEN-LAST:event_MnContengSemuaActionPerformed

    private void MnHapusContengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusContengActionPerformed
        BtnCariActionPerformed(null);
        if (TabRawat.getSelectedIndex() == 0) {
            if (tbDiagnosaPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Belum ada diagnosa statistik (V5) yang tersimpan...!!!");
            } else {                
                for (i = 0; i < tbDiagnosaPasien.getRowCount(); i++) {
                    tbDiagnosaPasien.setValueAt(Boolean.FALSE, i, 0);
                }

                for (i = 0; i < tbDiagnosaPasien1.getRowCount(); i++) {
                    tbDiagnosaPasien1.setValueAt(Boolean.FALSE, i, 0);
                }
            }
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (tbTindakanPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Belum ada prosedur statistik (V5) yang tersimpan...!!!");
            } else {                
                for (i = 0; i < tbTindakanPasien.getRowCount(); i++) {
                    tbTindakanPasien.setValueAt(Boolean.FALSE, i, 0);
                }
                
                for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
                    tbTindakanPasien1.setValueAt(Boolean.FALSE, i, 0);
                }
            }
        }
    }//GEN-LAST:event_MnHapusContengActionPerformed

    private void chkDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDiagnosaActionPerformed
        noRWTerakhir = "";
        noRWTerakhir = Sequel.cariIsi("select dp.no_rawat from reg_periksa rp inner join diagnosa_pasien dp on dp.no_rawat=rp.no_rawat "
                + "where rp.no_rkm_medis='" + TNoRM.getText() + "' and rp.status_lanjut='Ralan' order by rp.tgl_registrasi desc limit 1");

        if (chkDiagnosa.isSelected() == true) {
            tampilRiwDiagnosaPrimer(noRWTerakhir);
            tampilRiwDiagnosaSekunder(noRWTerakhir);
            tampilRiwProsedur(noRWTerakhir);
        } else {
            bersihkanConteng();
            tampildiagnosa();
//            tampildiagnosaSekunder();
//            tampilprosedure();
        }
    }//GEN-LAST:event_chkDiagnosaActionPerformed

    private void TabDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabDataMouseClicked
        if (TabData.getSelectedIndex() == 1) {
            tampilHasilRadiologi();
        } else if (TabData.getSelectedIndex() == 2) {
            tampilHasilLaboratorium();
        } else if (TabData.getSelectedIndex() == 3) {
            if (Status.getSelectedIndex() == 0) {
                chkBayar.setVisible(true);
                tampilBilingRalan();
            } else {
                chkBayar.setVisible(false);
                tampilBilingRanap();
            }
        }
    }//GEN-LAST:event_TabDataMouseClicked

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        if (Status.getSelectedIndex() == 0) {
            chkBayar.setVisible(true);
            tampilBilingRalan();
        } else {
            chkBayar.setVisible(false);
            tampilBilingRanap();
        }
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
        Valid.pindah(evt, TNoRw, BtnSimpan);
    }//GEN-LAST:event_DTPTglKeyPressed

    private void chkBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBayarActionPerformed
        tampilBilingRalan();

        if (chkBayar.isSelected() == false) {
            chkBayar.setText("Transaksi BELUM DIBAYAR");
        } else if (chkBayar.isSelected() == true) {
            chkBayar.setText("Transaksi SUDAH DIBAYAR");
        }
    }//GEN-LAST:event_chkBayarActionPerformed

    private void TNoNotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoNotaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            isRawat();
        }
    }//GEN-LAST:event_TNoNotaKeyPressed

    private void BtnResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResumeActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (Sequel.cariInteger("select count(-1) from ringkasan_pulang_ranap where no_rawat='" + TNoRw.getText() + "'") > 0) {
                cetakRingkasanRanap();
            } else {
                JOptionPane.showMessageDialog(null, "Data ringkasan pulang rawat inap tidak ditemukan...!!!");
            }
        }
    }//GEN-LAST:event_BtnResumeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgDiagnosaPenyakit dialog = new DlgDiagnosaPenyakit(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCariPenyakit;
    private widget.Button BtnCariPenyakit1;
    private widget.Button BtnCariProsedur;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKlaim;
    private widget.Button BtnPrint;
    private widget.Button BtnResume;
    private widget.Button BtnSeek4;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTgl;
    private widget.TextBox Diagnosa;
    private widget.TextBox Diagnosa1;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.Label LCount;
    private widget.editorpane LoadHTML1;
    private widget.editorpane LoadHTML2;
    private javax.swing.JMenuItem MnContengSemua;
    private javax.swing.JMenuItem MnHapusConteng;
    private javax.swing.JMenuItem MnSimpanQTYinadrg;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox Prosedur;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll13;
    private widget.ScrollPane Scroll14;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll43;
    private widget.ScrollPane Scroll44;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.ComboBox Status;
    private widget.TextBox TCari;
    private widget.TextBox TCariPasien;
    private widget.TextArea TDiagDokter;
    private widget.TextArea TDiagPerawat;
    private widget.TextArea TKeluhan1;
    private widget.TextArea TKeluhan2;
    public widget.TextBox TNoNota;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea TRincianTindakan1;
    private widget.TextArea TRincianTindakan2;
    private javax.swing.JTabbedPane TabData;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox TtlSemua;
    private widget.Button btnTambahPenyakit;
    private widget.Button btnTambahPenyakit1;
    private widget.Button btnTambahProsedur;
    private widget.CekBox chkBayar;
    public widget.CekBox chkDiagnosa;
    private widget.ComboBox cmbDiagPro;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel10;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel3;
    private widget.Label jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JSeparator jSeparator5;
    private widget.TextBox kdpoli;
    private widget.Label lbl_jns_byr;
    private widget.TextBox nmpoli;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbBilling;
    private widget.Table tbDiagnosa;
    private widget.Table tbDiagnosa1;
    private widget.Table tbDiagnosaPasien;
    private widget.Table tbDiagnosaPasien1;
    private widget.Table tbProsedur;
    private widget.Table tbTindakanPasien;
    private widget.Table tbTindakanPasien1;
    // End of variables declaration//GEN-END:variables

    public void tampilDiagStatistik() {
        Valid.tabelKosong(TabModeDiagnosaPasien);
        try {
            psdiagnosapasien = koneksi.prepareStatement("select reg_periksa.tgl_registrasi,diagnosa_pasien.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien.status, if(diagnosa_pasien.prioritas='1','Primer','Sekunder') prior, "
                    + "if(diagnosa_pasien.nip_petugas='Admin Utama','Admin Utama',ifnull(pg.nama,'-')) nmPetugas from diagnosa_pasien "
                    + "inner join reg_periksa inner join pasien inner join penyakit "
                    + "on diagnosa_pasien.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit left join pegawai pg on pg.nik = diagnosa_pasien.nip_petugas "
                    + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.tgl_registrasi like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien.no_rawat like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien.kd_penyakit like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and penyakit.nm_penyakit like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien.status like ? "
                    + "order by reg_periksa.tgl_registrasi,diagnosa_pasien.prioritas ");
            try {
                psdiagnosapasien.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(3, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(4, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(7, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(8, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(11, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(12, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(15, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(16, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(19, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(20, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(23, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(24, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(27, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(28, "%" + TCari.getText().trim() + "%");
                rs = psdiagnosapasien.executeQuery();
                while (rs.next()) {
                    TabModeDiagnosaPasien.addRow(new Object[]{
                        false, 
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)                        
                    });
                }
                LCount.setText("" + TabModeDiagnosaPasien.getRowCount());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psdiagnosapasien != null) {
                    psdiagnosapasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void tampilDiagInadrg() {
        Valid.tabelKosong(tabMode1);
        try {
            psinadrg = koneksi.prepareStatement("select reg_periksa.tgl_registrasi,diagnosa_pasien_inadrg.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien, "
                    + "diagnosa_pasien_inadrg.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien_inadrg.status, if(diagnosa_pasien_inadrg.prioritas='1','Primer','Sekunder') prior, "
                    + "if(diagnosa_pasien_inadrg.nip_petugas='Admin Utama','Admin Utama',ifnull(pg.nama,'-')) nmPetugas from diagnosa_pasien_inadrg "
                    + "inner join reg_periksa inner join pasien inner join penyakit on diagnosa_pasien_inadrg.no_rawat=reg_periksa.no_rawat "
                    + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and diagnosa_pasien_inadrg.kd_penyakit=penyakit.kd_penyakit "
                    + "left join pegawai pg on pg.nik = diagnosa_pasien_inadrg.nip_petugas where "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.tgl_registrasi like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien_inadrg.no_rawat like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien_inadrg.kd_penyakit like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and penyakit.nm_penyakit like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien_inadrg.status like ? "
                    + "order by reg_periksa.tgl_registrasi,diagnosa_pasien_inadrg.prioritas");
            try {
                psinadrg.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(3, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(4, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(7, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(8, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(11, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(12, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(15, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(16, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(19, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(20, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(23, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(24, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(27, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(28, "%" + TCari.getText().trim() + "%");
                rs = psinadrg.executeQuery();
                while (rs.next()) {
                    tabMode1.addRow(new Object[]{
                        false, 
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)                        
                    });
                }
                
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psinadrg != null) {
                    psinadrg.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void isRawat() {
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRM, TNoRw.getText());
    }

    private void isPsien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());
    }

    private void getData() {
        if (tbDiagnosaPasien.getSelectedRow() != -1) {
            TNoRw.setText(tbDiagnosaPasien.getValueAt(tbDiagnosaPasien.getSelectedRow(), 2).toString());
            isRawat();
            isPsien();
            Status.setSelectedItem(tbDiagnosaPasien.getValueAt(tbDiagnosaPasien.getSelectedRow(), 7).toString());
        }
    }

    private void getData2() {
        if (tbTindakanPasien.getSelectedRow() != -1) {
            TNoRw.setText(tbTindakanPasien.getValueAt(tbTindakanPasien.getSelectedRow(), 2).toString());
            isRawat();
            isPsien();
            Status.setSelectedItem(tbTindakanPasien.getValueAt(tbTindakanPasien.getSelectedRow(), 7).toString());
        }
    }
    
    private void getData3() {
        if (tbDiagnosaPasien1.getSelectedRow() != -1) {
            TNoRw.setText(tbDiagnosaPasien1.getValueAt(tbDiagnosaPasien1.getSelectedRow(), 2).toString());
            isRawat();
            isPsien();
            Status.setSelectedItem(tbDiagnosaPasien1.getValueAt(tbDiagnosaPasien1.getSelectedRow(), 7).toString());
        }
    }
    
    private void getData4() {
        if (tbTindakanPasien1.getSelectedRow() != -1) {
            TNoRw.setText(tbTindakanPasien1.getValueAt(tbTindakanPasien1.getSelectedRow(), 2).toString());
            isRawat();
            isPsien();
            Status.setSelectedItem(tbTindakanPasien1.getValueAt(tbTindakanPasien1.getSelectedRow(), 7).toString());
        }
    }

    public void setNoRm(String norwt, Date tgl, String status) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        Status.setSelectedItem(status);
        isRawat();
        isPsien();
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(tgl);
        ChkInput.setSelected(true);
        cmbDiagPro.setSelectedIndex(0);
        isForm();
        
        chkDiagnosa.setSelected(false);
        if (Status.getSelectedIndex() == 0) {
            chkDiagnosa.setEnabled(true);
            BtnResume.setEnabled(false);
        } else {
            chkDiagnosa.setEnabled(false);
            BtnResume.setEnabled(true);
        }
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 420));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
            TabData.setSelectedIndex(0);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getdiagnosa_pasien());
        BtnHapus.setEnabled(akses.getdiagnosa_pasien());
//        btnTambahPenyakit.setEnabled(var.getpenyakit());
        BtnPrint.setEnabled(akses.getdiagnosa_pasien());
        BtnKlaim.setEnabled(akses.getinacbg_klaim_raza());
    }

    private void tampildiagnosa() {
        try {
            jml = 0;
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                    jml++;
                }
            }

            pilih = null;
            pilih = new boolean[jml];
            kode = null;
            kode = new String[jml];
            nama = null;
            nama = new String[jml];
            ciripny = null;
            ciripny = new String[jml];
            keterangan = null;
            keterangan = new String[jml];
            kategori = null;
            kategori = new String[jml];
            cirium = null;
            cirium = new String[jml];

            index = 0;
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                    pilih[index] = true;
                    kode[index] = tbDiagnosa.getValueAt(i, 1).toString();
                    nama[index] = tbDiagnosa.getValueAt(i, 2).toString();
                    ciripny[index] = tbDiagnosa.getValueAt(i, 3).toString();
                    keterangan[index] = tbDiagnosa.getValueAt(i, 4).toString();
                    kategori[index] = tbDiagnosa.getValueAt(i, 5).toString();
                    cirium[index] = tbDiagnosa.getValueAt(i, 6).toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeDiagnosa);
            for (i = 0; i < jml; i++) {
                tabModeDiagnosa.addRow(new Object[]{pilih[i], kode[i], nama[i], ciripny[i], keterangan[i], kategori[i], cirium[i]});
            }

            pspenyakit = koneksi.prepareStatement("select penyakit.kd_penyakit,penyakit.nm_penyakit,penyakit.ciri_ciri,penyakit.keterangan, "
                    + "kategori_penyakit.nm_kategori,kategori_penyakit.ciri_umum "
                    + "from kategori_penyakit inner join penyakit "
                    + "on penyakit.kd_ktg=kategori_penyakit.kd_ktg where  "
                    + " (penyakit.kd_penyakit like ? or "
                    + " penyakit.nm_penyakit like ? or "
                    + " penyakit.ciri_ciri like ? or "
                    + " penyakit.keterangan like ? or "
                    + " kategori_penyakit.nm_kategori like ? or "
                    + " kategori_penyakit.ciri_umum like ? ) and penyakit.kd_penyakit <> '-' "
                    + "order by penyakit.kd_penyakit LIMIT 100");
            try {
                pspenyakit.setString(1, "%" + Diagnosa.getText().trim() + "%");
                pspenyakit.setString(2, "%" + Diagnosa.getText().trim() + "%");
                pspenyakit.setString(3, "%" + Diagnosa.getText().trim() + "%");
                pspenyakit.setString(4, "%" + Diagnosa.getText().trim() + "%");
                pspenyakit.setString(5, "%" + Diagnosa.getText().trim() + "%");
                pspenyakit.setString(6, "%" + Diagnosa.getText().trim() + "%");
                rs = pspenyakit.executeQuery();
                while (rs.next()) {
                    tabModeDiagnosa.addRow(new Object[]{false, rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pspenyakit != null) {
                    pspenyakit.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampildiagnosaSekunder() {
        try {
            jml1 = 0;
            for (s = 0; s < tbDiagnosa1.getRowCount(); s++) {
                if (tbDiagnosa1.getValueAt(s, 0).toString().equals("true")) {
                    jml1++;
                }
            }

            pilih3 = null;
            pilih3 = new boolean[jml1];
            kode1 = null;
            kode1 = new String[jml1];
            nama1 = null;
            nama1 = new String[jml1];
            ciripny1 = null;
            ciripny1 = new String[jml1];
            keterangan1 = null;
            keterangan1 = new String[jml1];
            kategori1 = null;
            kategori1 = new String[jml1];
            cirium1 = null;
            cirium1 = new String[jml1];

            index1 = 0;
            for (s = 0; s < tbDiagnosa1.getRowCount(); s++) {
                if (tbDiagnosa1.getValueAt(s, 0).toString().equals("true")) {
                    pilih3[index1] = true;
                    kode1[index1] = tbDiagnosa1.getValueAt(s, 1).toString();
                    nama1[index1] = tbDiagnosa1.getValueAt(s, 2).toString();
                    ciripny1[index1] = tbDiagnosa1.getValueAt(s, 3).toString();
                    keterangan1[index1] = tbDiagnosa1.getValueAt(s, 4).toString();
                    kategori1[index1] = tbDiagnosa1.getValueAt(s, 5).toString();
                    cirium1[index1] = tbDiagnosa1.getValueAt(s, 6).toString();
                    index1++;
                }
            }

            Valid.tabelKosong(tabModeDiagnosaSekunder);
            for (s = 0; s < jml1; s++) {
                tabModeDiagnosaSekunder.addRow(new Object[]{pilih3[s], kode1[s], nama1[s], ciripny1[s], keterangan1[s], kategori1[s], cirium1[s]});
            }

            pspenyakitsekunder = koneksi.prepareStatement("select penyakit.kd_penyakit,penyakit.nm_penyakit,penyakit.ciri_ciri,penyakit.keterangan, "
                    + "kategori_penyakit.nm_kategori,kategori_penyakit.ciri_umum "
                    + "from kategori_penyakit inner join penyakit "
                    + "on penyakit.kd_ktg=kategori_penyakit.kd_ktg where  "
                    + " (penyakit.kd_penyakit like ? or "
                    + " penyakit.nm_penyakit like ? or "
                    + " penyakit.ciri_ciri like ? or "
                    + " penyakit.keterangan like ? or "
                    + " kategori_penyakit.nm_kategori like ? or "
                    + " kategori_penyakit.ciri_umum like ? ) and penyakit.kd_penyakit <> '-' "
                    + "order by penyakit.kd_penyakit LIMIT 100");
            try {
                pspenyakitsekunder.setString(1, "%" + Diagnosa1.getText().trim() + "%");
                pspenyakitsekunder.setString(2, "%" + Diagnosa1.getText().trim() + "%");
                pspenyakitsekunder.setString(3, "%" + Diagnosa1.getText().trim() + "%");
                pspenyakitsekunder.setString(4, "%" + Diagnosa1.getText().trim() + "%");
                pspenyakitsekunder.setString(5, "%" + Diagnosa1.getText().trim() + "%");
                pspenyakitsekunder.setString(6, "%" + Diagnosa1.getText().trim() + "%");
                rs1 = pspenyakitsekunder.executeQuery();
                while (rs1.next()) {
                    tabModeDiagnosaSekunder.addRow(new Object[]{false, rs1.getString(1),
                        rs1.getString(2),
                        rs1.getString(3),
                        rs1.getString(4),
                        rs1.getString(5),
                        rs1.getString(6)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (pspenyakitsekunder != null) {
                    pspenyakitsekunder.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampilprosedure() {
        try {
            jml = 0;
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    jml++;
                }
            }

            pilih = null;
            pilih = new boolean[jml];
            kode2 = null;
            kode2 = new String[jml];
            panjang = null;
            panjang = new String[jml];
            pendek = null;
            pendek = new String[jml];

            index = 0;
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    pilih[index] = true;
                    kode2[index] = tbProsedur.getValueAt(i, 1).toString();
                    panjang[index] = tbProsedur.getValueAt(i, 2).toString();
                    pendek[index] = tbProsedur.getValueAt(i, 3).toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeProsedur);
            for (i = 0; i < jml; i++) {
                tabModeProsedur.addRow(new Object[]{pilih[i], kode2[i], panjang[i], pendek[i]});
            }

            psprosedur = koneksi.prepareStatement("select * from icd9 where kode like ? or "
                    + " deskripsi_panjang like ? or  deskripsi_pendek like ? order by kode");
            try {
                psprosedur.setString(1, "%" + Prosedur.getText().trim() + "%");
                psprosedur.setString(2, "%" + Prosedur.getText().trim() + "%");
                psprosedur.setString(3, "%" + Prosedur.getText().trim() + "%");
                rs = psprosedur.executeQuery();
                while (rs.next()) {
                    tabModeProsedur.addRow(new Object[]{
                        false, rs.getString(1), rs.getString(2), rs.getString(3)});
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psprosedur != null) {
                    psprosedur.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void tampilProsStatistik() {
        Valid.tabelKosong(TabModeTindakanPasien);
        try {
            pstindakanpasien = koneksi.prepareStatement("select reg_periksa.tgl_registrasi,prosedur_pasien.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.status, if(prosedur_pasien.nip_petugas='Admin Utama','Admin Utama',ifnull(pg.nama,'-')) nmPetugas "
                    + "from prosedur_pasien inner join reg_periksa inner join pasien inner join icd9 "
                    + "on prosedur_pasien.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and prosedur_pasien.kode=icd9.kode left join pegawai pg on pg.nik = prosedur_pasien.nip_petugas "
                    + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.tgl_registrasi like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and prosedur_pasien.no_rawat like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and prosedur_pasien.kode like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and icd9.deskripsi_panjang like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and prosedur_pasien.status like ? "
                    + "order by reg_periksa.tgl_registrasi,prosedur_pasien.prioritas ");
            try {
                pstindakanpasien.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(3, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(4, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(7, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(8, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(11, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(12, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(15, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(16, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(19, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(20, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(23, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(24, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(27, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(28, "%" + TCari.getText().trim() + "%");
                rs = pstindakanpasien.executeQuery();
                while (rs.next()) {
                    TabModeTindakanPasien.addRow(new Object[]{
                        false, 
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)                       
                    });
                }
                LCount.setText("" + TabModeTindakanPasien.getRowCount());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pstindakanpasien != null) {
                    pstindakanpasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void tampilProsInadrg() {
        Valid.tabelKosong(tabMode2);
        try {
            psTINinadrg = koneksi.prepareStatement("SELECT reg_periksa.tgl_registrasi,prosedur_pasien_inadrg.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien, "
                    + "prosedur_pasien_inadrg.kode,icd9.deskripsi_panjang, prosedur_pasien_inadrg.status, prosedur_pasien_inadrg.qty, "
                    + "if(prosedur_pasien_inadrg.nip_petugas='Admin Utama','Admin Utama',ifnull(pg.nama,'-')) nmPetugas "
                    + "FROM prosedur_pasien_inadrg INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN icd9 "
                    + "ON prosedur_pasien_inadrg.no_rawat=reg_periksa.no_rawat AND reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "AND prosedur_pasien_inadrg.kode=icd9.kode left join pegawai pg on pg.nik = prosedur_pasien_inadrg.nip_petugas "
                    + "WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND reg_periksa.tgl_registrasi LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND prosedur_pasien_inadrg.no_rawat LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND reg_periksa.no_rkm_medis LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND pasien.nm_pasien LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND prosedur_pasien_inadrg.kode LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND icd9.deskripsi_panjang LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND prosedur_pasien_inadrg.status LIKE ? "
                    + "ORDER BY reg_periksa.tgl_registrasi,prosedur_pasien_inadrg.prioritas");
            try {
                psTINinadrg.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(3, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(4, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(7, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(8, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(11, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(12, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(15, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(16, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(19, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(20, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(23, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(24, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(27, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(28, "%" + TCari.getText().trim() + "%");
                rs = psTINinadrg.executeQuery();
                while (rs.next()) {
                    tabMode2.addRow(new Object[]{
                        false, 
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)                        
                    });
                }
                
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psTINinadrg != null) {
                    psTINinadrg.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void formulirKlaim() {
        tglklaim = "";
        drdpjp = "";
        poli = "";
        crBayar = "";
        
        try {            
            Sequel.queryu("delete from temporary_formulir_klaim");
            pspasien = koneksi.prepareStatement("SELECT rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, IF (p.jk = 'L','LAKI-LAKI','PEREMPUAN') jk, "
                    + "IF (rp.status_lanjut = 'Ralan','RAWAT JALAN','-') status_kunjungan, DATE_FORMAT(p.tgl_lahir,'%d') tgl_lhr, "
                    + "CONCAT(rp.umurdaftar,' ',rp.sttsumur,'.') umur, DATE_FORMAT(rp.tgl_registrasi,'%d') tgl_kunj, d.nm_dokter, pl.nm_poli, "
                    + "pj.png_jawab, IFNULL(pr.diagnosa,'-') diag_resum, IFNULL(pr.keluhan,'-') keluhan, IFNULL(pr.pemeriksaan,'-') pemeriksaan, "
                    + "IFNULL(pr.alergi,'-') alergi, IFNULL(pr.terapi,'-') terapi, IFNULL(pr.rincian_tindakan,'-') tindakan FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli "
                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj LEFT JOIN pemeriksaan_ralan pr ON rp.no_rawat = pr.no_rawat "
                    + "WHERE rp.status_lanjut = 'Ralan' AND rp.no_rawat ='" + TNoRw.getText().trim() + "'");

            try {
                rspasien = pspasien.executeQuery();
                while (rspasien.next()) {
                    tglklaim = rspasien.getString("tgl_kunj") + " "+ Sequel.bulanINDONESIA("select month(tgl_registrasi) from reg_periksa where no_rawat='" + rspasien.getString("no_rawat") + "'") + " "
                            + Sequel.cariIsi("select year(tgl_registrasi) from reg_periksa where no_rawat='" + rspasien.getString("no_rawat") + "'");
                    drdpjp = rspasien.getString("nm_dokter");
                    poli = rspasien.getString("nm_poli");
                    crBayar = rspasien.getString("png_jawab");

                    Sequel.menyimpan("temporary_formulir_klaim", "'Kode RS',': 6303015','Nama RS',': RSUD Ratu Zalecha Martapura',"
                            + "'1. No. RM',': " + rspasien.getString("no_rkm_medis") + "',"
                            + "'2. Nama Pasien',': " + rspasien.getString("nm_pasien") + "',"
                            + "'3. Jenis Kelamin',': " + rspasien.getString("jk") + "',"
                            + "'4. Jenis Perawatan',': " + rspasien.getString("status_kunjungan") + "',"
                            + "'5. Tgl. Lahir',': " + rspasien.getString("tgl_lhr") + " " 
                            + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + rspasien.getString("no_rkm_medis") + "'") + " "
                            + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + rspasien.getString("no_rkm_medis") + "'") + "',"
                            + "'6. Umur',': " + rspasien.getString("umur") + "',"
                            + "'7. Tgl. Kunjungan',': " + rspasien.getString("tgl_kunj") + " "
                            + Sequel.bulanINDONESIA("select month(tgl_registrasi) from reg_periksa where no_rawat='" + rspasien.getString("no_rawat") + "'") + " "
                            + Sequel.cariIsi("select year(tgl_registrasi) from reg_periksa where no_rawat='" + rspasien.getString("no_rawat") + "'") + "',"
                            + "'8. Resume Medis',':','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Keluhan',':','" + rspasien.getString("keluhan") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Pemeriksaan',':','" + rspasien.getString("pemeriksaan") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");

                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Alergi',':','" + rspasien.getString("alergi") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Terapi',':','" + rspasien.getString("terapi") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Rincian Tindakan',':','" + rspasien.getString("tindakan") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Diagnosa Resume',':','" + rspasien.getString("diag_resum") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");

                    //ngambil data diagnosa icd 10     
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'9. Diagnosa ICD-10',':','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    psdiagnosa = koneksi.prepareStatement("SELECT IF (d.prioritas = '1','Primer','Sekunder') stts_diagnosa, IFNULL(d.kd_penyakit,'-') AS ICD_10, "
                            + "IFNULL(p.ciri_ciri, '-') deskripsi_diagnosa FROM diagnosa_pasien d "
                            + "INNER JOIN reg_periksa r ON r.no_rawat=d.no_rawat INNER JOIN penyakit p ON p.kd_penyakit=d.kd_penyakit "
                            + "WHERE d.status='ralan' and r.no_rawat='" + TNoRw.getText().trim() + "' order by d.prioritas");
                    try {
                        rsdiagnosa = psdiagnosa.executeQuery();
                        while (rsdiagnosa.next()) {
                            Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                                    + "'      " + rsdiagnosa.getString("stts_diagnosa") + "',':','" + rsdiagnosa.getString("deskripsi_diagnosa") + "','Kode : " + rsdiagnosa.getString("ICD_10") + "',"
                                    + "'','','','','','','','','','','','','','',''", "Diagnosa Klaim Rawat Jalan");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi Formulir Klaim : " + e);
                    } finally {
                        if (rsdiagnosa != null) {
                            rsdiagnosa.close();
                        }
                        if (psdiagnosa != null) {
                            psdiagnosa.close();
                        }
                    }
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");

                    //ngambil data tindakan icd 9
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'10. Tindakan ICD-9-CM',':','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    pstindakan = koneksi.prepareStatement(
                            "SELECT IFNULL(pp.kode,'-') ICD_9, IFNULL(i.deskripsi_panjang,'-') des_prosedur "
                            + "FROM reg_periksa rp INNER JOIN prosedur_pasien pp on pp.no_rawat=rp.no_rawat INNER JOIN icd9 i on i.kode=pp.kode "
                            + "WHERE rp.status_lanjut = 'Ralan' AND rp.no_rawat ='" + TNoRw.getText().trim() + "'");
                    try {
                        rstindakan = pstindakan.executeQuery();
                        while (rstindakan.next()) {
                            Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                                    + "'      Deskripsi',':','" + rstindakan.getString("des_prosedur") + "','Kode : " + rstindakan.getString("ICD_9") + "',"
                                    + "'','','','','','','','','','','','','','',''", "Tindakan Klaim Rawat Jalan");
                        }
                    } catch (Exception e) {
                        System.out.println("Notif Registrasi : " + e);
                    } finally {
                        if (rstindakan != null) {
                            rstindakan.close();
                        }
                        if (pstindakan != null) {
                            pstindakan.close();
                        }
                    }
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi Cari Pasien : " + e);
            } finally {
                if (rspasien != null) {
                    rspasien.close();
                }
                if (pspasien != null) {
                    pspasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tglKlaim", "Martapura, " + tglklaim);
        param.put("drDPJP", "( " + drdpjp + " )");
        param.put("poli", poli);
        param.put("caraBayar", crBayar);
        Valid.MyReport("rptFormulirKlaim.jasper", "report", "::[ Lembar Formulir Klaim Pasien Rawat Jalan ]::",
                "select * from temporary_formulir_klaim", param);
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void simpan_diagproStatistik() {
        try {
            cek = 0;
            cekPremier = 0;
            koneksi.setAutoCommit(false);
            cek = Sequel.cariInteger("select count(-1) from diagnosa_pasien where no_rawat='" + TNoRw.getText() + "' and prioritas=1");
            if (cek > 0) {
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        JOptionPane.showMessageDialog(null, "Diagnosa primer sudah tersimpan sebelumnya...");
                    }
                }
                for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                    if (tbDiagnosa1.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.menyimpanPesanGagalnyaDiTerminal("diagnosa_pasien", "?,?,?,?,?", "Penyakit", 5, new String[]{
                            TNoRw.getText(), tbDiagnosa1.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), 
                            Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText()), 
                            akses.getkode()
                        });
                    }
                }
            } else {
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        cekPremier++;
                    }
                }

                if (cekPremier > 1) {
                    JOptionPane.showMessageDialog(null, "Diagnosa primer hanya boleh ada 1 saja,...");
                } else {
                    for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                        if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpanPesanGagalnyaDiTerminal("diagnosa_pasien", "?,?,?,?,?", "Penyakit", 5, new String[]{
                                TNoRw.getText(), tbDiagnosa.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), "1", akses.getkode()
                            });
                        }
                    }
                }

                if (cekPremier == 1) {
                    for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                        if (tbDiagnosa1.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpanPesanGagalnyaDiTerminal("diagnosa_pasien", "?,?,?,?,?", "Penyakit", 5, new String[]{
                                TNoRw.getText(), tbDiagnosa1.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), 
                                Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText()), 
                                akses.getkode()
                            });
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Silakan input diagnosa primer terlebih dulu...");
                }
            }

            koneksi.setAutoCommit(true);            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data diagnosa yang sama dimasukkan sebelumnya...!");
        }

        //---------------------------------
        try {
            koneksi.setAutoCommit(false);
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpanPesanGagalnyaDiTerminal("prosedur_pasien", "?,?,?,?,?", "ICD 9", 5, new String[]{
                        TNoRw.getText(), tbProsedur.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), 
                        Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from prosedur_pasien where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText()), 
                        akses.getkode()
                    });
                }
            }
            koneksi.setAutoCommit(true);            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data prosedur/ICD9 yang sama dimasukkan sebelumnya...!");
        }        
    }
    
    private void simpan_diagproINADRG() {
        try {
            cekINADRG = 0;
            cekPremierINADRG = 0;
            koneksi.setAutoCommit(false);
            cekINADRG = Sequel.cariInteger("select count(-1) from diagnosa_pasien_inadrg where no_rawat='" + TNoRw.getText() + "' and prioritas=1");
            if (cekINADRG > 0) {
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        JOptionPane.showMessageDialog(null, "Diagnosa primer INADRG sudah tersimpan sebelumnya...");
                    }
                }
                for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                    if (tbDiagnosa1.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.menyimpanPesanGagalnyaDiTerminal("diagnosa_pasien_inadrg", "?,?,?,?,?", "Penyakit", 5, new String[]{
                            TNoRw.getText(), tbDiagnosa1.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), 
                            Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien_inadrg where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText()),
                            akses.getkode()
                        });
                    }
                }
            } else {
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        cekPremierINADRG++;
                    }
                }

                if (cekPremierINADRG > 1) {
                    JOptionPane.showMessageDialog(null, "Diagnosa primer INADRG hanya boleh ada 1 saja,...");
                } else {
                    for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                        if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpanPesanGagalnyaDiTerminal("diagnosa_pasien_inadrg", "?,?,?,?,?", "Penyakit", 5, new String[]{
                                TNoRw.getText(), tbDiagnosa.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), "1", akses.getkode()
                            });
                        }
                    }
                }

                if (cekPremierINADRG == 1) {
                    for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                        if (tbDiagnosa1.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpanPesanGagalnyaDiTerminal("diagnosa_pasien_inadrg", "?,?,?,?,?", "Penyakit", 5, new String[]{
                                TNoRw.getText(), tbDiagnosa1.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), 
                                Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien_inadrg where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText()),
                                akses.getkode()
                            });
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Silakan input diagnosa primer INADRG terlebih dulu...");
                }
            }

            koneksi.setAutoCommit(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data diagnosa INADRG yang sama dimasukkan sebelumnya...!");
        }

        //---------------------------------
        try {
            koneksi.setAutoCommit(false);
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpanPesanGagalnyaDiTerminal("prosedur_pasien_inadrg", "?,?,?,?,?,?", "ICD 9", 6, new String[]{
                        TNoRw.getText(), tbProsedur.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), 
                        Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from prosedur_pasien_inadrg where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText()), 
                        "1", akses.getkode()
                    });
                }
            }
            koneksi.setAutoCommit(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data prosedur/ICD9 INADRG yang sama dimasukkan sebelumnya...!");
        }
    }
    
    private void hapus_diagproStatistik() {
        if (TabRawat.getSelectedIndex() == 0) {
            if (TabModeDiagnosaPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                TNoRw.requestFocus();
            } else {
                for (i = 0; i < tbDiagnosaPasien.getRowCount(); i++) {
                    if (tbDiagnosaPasien.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from diagnosa_pasien where no_rawat=? and kd_penyakit=?", 2, new String[]{
                            tbDiagnosaPasien.getValueAt(i, 2).toString(), tbDiagnosaPasien.getValueAt(i, 5).toString()
                        });
                    }
                }
            }
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (TabModeTindakanPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                TNoRw.requestFocus();
            } else {
                for (i = 0; i < tbTindakanPasien.getRowCount(); i++) {
                    if (tbTindakanPasien.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from prosedur_pasien where no_rawat=? and kode=?", 2, new String[]{
                            tbTindakanPasien.getValueAt(i, 2).toString(), tbTindakanPasien.getValueAt(i, 5).toString()
                        });
                    }
                }
            }
        }
    }
    
    private void hapus_diagproINADRG() {
        if (TabRawat.getSelectedIndex() == 0) {
            if (tabMode1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data diagnosa INADRG sudah habis...!!!!");
                TNoRw.requestFocus();
            } else {
                for (i = 0; i < tbDiagnosaPasien1.getRowCount(); i++) {
                    if (tbDiagnosaPasien1.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from diagnosa_pasien_inadrg where no_rawat=? and kd_penyakit=?", 2, new String[]{
                            tbDiagnosaPasien1.getValueAt(i, 2).toString(), tbDiagnosaPasien1.getValueAt(i, 5).toString()
                        });
                    }
                }
            }
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (tabMode2.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data prosedur/tindakan/ICD 9 INADRG sudah habis...!!!!");
                TNoRw.requestFocus();
            } else {
                for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
                    if (tbTindakanPasien1.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from prosedur_pasien_inadrg where no_rawat=? and kode=?", 2, new String[]{
                            tbTindakanPasien1.getValueAt(i, 2).toString(), tbTindakanPasien1.getValueAt(i, 5).toString()
                        });
                    }
                }
            }
        }
    }   
    
    private void simpanQTYprosedurINADRG() {
        jlhTindakan = ""; 

        for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
            if (tbTindakanPasien1.getValueAt(i, 0).toString().equals("true")) {                
                if (!tbTindakanPasien1.getValueAt(i, 8).toString().equals("") && Integer.parseInt(tbTindakanPasien1.getValueAt(i, 8).toString()) > 1) {
                    jlhTindakan = tbTindakanPasien1.getValueAt(i, 8).toString();
                    Sequel.mengedit("prosedur_pasien_inadrg",
                            "no_rawat='" + TNoRw.getText() + "' and kode='" + tbTindakanPasien1.getValueAt(i, 5).toString() + "' and status='" + tbTindakanPasien1.getValueAt(i, 7).toString() + "'",
                            "qty='" + jlhTindakan + "'");
                } else if (Integer.parseInt(tbTindakanPasien1.getValueAt(i, 8).toString()) == 1) {
                    jlhTindakan = tbTindakanPasien1.getValueAt(i, 8).toString();
                    Sequel.mengedit("prosedur_pasien_inadrg",
                            "no_rawat='" + TNoRw.getText() + "' and kode='" + tbTindakanPasien1.getValueAt(i, 5).toString() + "' and status='" + tbTindakanPasien1.getValueAt(i, 7).toString() + "'",
                            "qty='" + jlhTindakan + "'");
                }          
            }
        }
    }
    
    private void tampilPemeriksaan() {
        try {
            psralan = koneksi.prepareStatement("SELECT IFNULL(pr1.diagnosa, '-') diag_resum_dr, IFNULL(pr2.diagnosa, '-') diag_resum_pr, "
                    + "IFNULL(pr1.keluhan, '-') keluhan_dr, IFNULL(pr2.keluhan, '-') keluhan_pr, IFNULL(pr1.rincian_tindakan, '-') tindakan_dr, "
                    + "IFNULL(pr2.rincian_tindakan, '-') tindakan_pr FROM reg_periksa rp "
                    + "LEFT JOIN pemeriksaan_ralan pr1 ON pr1.no_rawat = rp.no_rawat "
                    + "LEFT JOIN pemeriksaan_ralan_petugas pr2 ON pr2.no_rawat = rp.no_rawat "
                    + "WHERE rp.status_lanjut = 'Ralan' AND rp.kd_poli NOT IN ('laa', 'lab', 'rad', '-') AND rp.no_rawat = '" + TNoRw.getText() + "'");
            try {
                rsralan = psralan.executeQuery();
                while (rsralan.next()) {
                    TDiagDokter.setText(rsralan.getString("diag_resum_dr"));
                    TDiagPerawat.setText(rsralan.getString("diag_resum_pr"));
                    TKeluhan1.setText(rsralan.getString("keluhan_dr"));
                    TKeluhan2.setText(rsralan.getString("keluhan_pr"));                    
                    TRincianTindakan1.setText(rsralan.getString("tindakan_dr"));
                    TRincianTindakan2.setText(rsralan.getString("tindakan_pr"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsralan != null) {
                    rsralan.close();
                }
                if (psralan != null) {
                    psralan.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilHasilRadiologi() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rsrad = koneksi.prepareStatement("SELECT rp.no_rawat, concat(date_format(rp.tgl_registrasi, '%d-%m-%Y'),', Jam : ',date_format(rp.jam_reg,'%H:%i %p')) tglKun, "
                        + "if(rp.status_lanjut='ralan', concat(ifnull(pl.nm_poli,'-'),' (',ifnull(d.nm_dokter,'-'),')'), "
                        + "CONCAT((SELECT l.nm_bangsal FROM kamar_inap k INNER JOIN kamar b ON b.kd_kamar=k.kd_kamar INNER JOIN bangsal l ON l.kd_bangsal=b.kd_bangsal "
                        + "WHERE k.no_rawat=rp.no_rawat AND k.stts_pulang<>'Pindah Kamar'),' (',dd.nm_dokter,')')) polidokter, concat(ifnull(pj.png_jawab,'-')) crbyr, "
                        + "if(rp.status_lanjut='ralan','Rawat Jalan','Rawat Inap') stts_rawat FROM reg_periksa rp "
                        + "INNER JOIN poliklinik pl on pl.kd_poli=rp.kd_poli INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter INNER JOIN periksa_radiologi pr on pr.no_rawat=rp.no_rawat INNER JOIN dokter dd ON dd.kd_dokter=pr.dokter_perujuk "
                        + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.kd_jenis_prw=pr.kd_jenis_prw AND hr.tgl_periksa=pr.tgl_periksa AND hr.jam=pr.jam WHERE "
                        + "rp.no_rkm_medis = '" + TNoRM.getText() + "' AND pr.tgl_periksa BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + "GROUP BY rp.no_rawat ORDER BY pr.tgl_periksa, pr.jam").executeQuery();

                urut = 1;
                while (rsrad.next()) {
                    htmlContent.append(                            
                            "<table width='100%' class='isi'>"
                            + "<thead>"
                            + "<tr class='isi'>"
                            + "    <td valign='top' rowspan='5' width='20px'>" + urut + ".</td>"
                            + "    <td width='160px'>No. Rawat</td>"
                            + "    <td colspan='4'>: " + rsrad.getString("no_rawat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "    <td>Tgl. Kunj./Tgl. MRS</td>"
                            + "    <td colspan='4'>: " + rsrad.getString("tglKun") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "    <td>Jenis Perawatan</td>"
                            + "    <td colspan='4'>: " + rsrad.getString("stts_rawat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "    <td>Poliklinik/Inst./Rg. Rawat</td>"
                            + "    <td colspan='4'>: " + rsrad.getString("polidokter") + "</td>"
                            + "</tr>"
                            +"<tr class='isi'>"
                            + "    <td>Cara Bayar</td>"
                            + "    <td colspan='4'>: " + rsrad.getString("crbyr") + "</td>"
                            + "</tr>"
                            + "</thead>"       
                            + "</table>"
                    );
                    urut++;
                    
                    //hasil pemeriksaan radiolgi
                    try {
                        rshslRad = koneksi.prepareStatement("SELECT date_format(pr.tgl_periksa, '%d-%m-%Y') tgl_periksa, date_format(pr.jam, '%h:%i %p') jam, "
                                + "ifnull(jpr.nm_perawatan,'-') nm_pemeriksaan, ifnull(d.nm_dokter,'-') drRad, ifnull(hr.hasil, '-') hasil FROM periksa_radiologi pr "
                                + "INNER JOIN dokter d on d.kd_dokter=pr.kd_dokter INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                                + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.kd_jenis_prw=pr.kd_jenis_prw AND hr.tgl_periksa=pr.tgl_periksa AND hr.jam=pr.jam "
                                + "WHERE pr.no_rawat='" + rsrad.getString("no_rawat") + "' ORDER BY pr.tgl_periksa, pr.jam").executeQuery();

                        if (rshslRad.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='isi'>"
                                    + "<thead>"
                                    + "<tr class='isi'>"
                                    + "    <td width='20px'></td>"
                                    + "    <td width='100px' bgcolor='#f8fdf3'><b>Tgl. Pemeriksaan</b></td>"
                                    + "    <td width='50px' bgcolor='#f8fdf3'><b>Jam</b></td>"                                    
                                    + "    <td width='150px' bgcolor='#f8fdf3'><b>Nama Pemeriksaan</b></td>"
                                    + "    <td width='180px' bgcolor='#f8fdf3'><b>Dokter Radiologi</b></td>"
                                    + "    <td bgcolor='#f8fdf3'><b>Bacaan/Hasil Pemeriksaan</b></td>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                            );
                            
                            rshslRad.beforeFirst();
                            while (rshslRad.next()) {
                                htmlContent.append(
                                        "<tr class='isi'>"
                                        + "<td width='20px'></td>"
                                        + "<td valign='top' width='100px'>" + rshslRad.getString("tgl_periksa") + "</td>"
                                        + "<td valign='top' width='50px'>" + rshslRad.getString("jam") + "</td>"                                        
                                        + "<td valign='top' width='150px'>" + rshslRad.getString("nm_pemeriksaan") + "</td>"
                                        + "<td valign='top' width='180px'>" + rshslRad.getString("drRad") + "</td>"
                                        + "<td valign='top'>" + rshslRad.getString("hasil").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                        + "</tr>"                                        
                                );
                            }
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td colspan='6' bgcolor='#7eccb9'></td>"
                                    + "</tr>"
                                    + "</tbody>"                                          
                                    + "</table>"
                            );
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rshslRad != null) {
                            rshslRad.close();
                        }
                    }
                }
                
                LoadHTML1.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsrad != null) {
                    rsrad.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void tampilHasilLaboratorium() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            rs = koneksi.prepareStatement("select * from ("
                    + "(select *, waktu_reg_lab 'waktu' ,date_format(waktu_reg_lab,'%d-%m-%Y') tgl, time_format(waktu_reg_lab,'%H:%i') jam, date_format(pasien_tgl_lahir,'%d-%m-%Y') tgllhr "
                    + "from lis_hasil_data_pasien where pasien_no_rm='" + TNoRM.getText() + "' and date(waktu_reg_lab) between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                    + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ORDER BY waktu_reg_lab desc, no_lab desc) as a ) order by a.waktu").executeQuery();

            try {
                urut = 1;
                while (rs.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. RM</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("pasien_no_rm") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Lahir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tgllhr") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar/Penjamin</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("penjamin") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rg. Rawat/Poli./Inst.</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("unit_asal") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dokter Pengirim</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("dokter_pengirim") + "</td>"
                            + "</tr>"
                    );
                    urut++;

                    //hasil pemeriksaan laboratorium LIS
                    try {
                        rsLISMaster = koneksi.prepareStatement(
                                "select *, date_format(waktu_reg_lab,'%d-%m-%Y') tgl, time_format(waktu_reg_lab,'%H:%i') jam from lis_hasil_data_pasien where "
                                + "no_lab='" + rs.getString("no_lab") + "' and date(waktu_reg_lab) between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                                + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ORDER BY waktu_reg_lab desc, no_lab desc").executeQuery();

                        if (rsLISMaster.next()) {
                            rsLISMaster.beforeFirst();
                            lisM = 1;
                            while (rsLISMaster.next()) {
                                htmlContent.append(
                                        "<tr class='isi'>"
                                        + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Laboratorium</td>"
                                        + "<td valign='top' width='1%' align='center'>:</td>"
                                        + "<td valign='top' width='79%'>"
                                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                        + "<tr><td valign='top' colspan='6'>No. Lab. : " + rsLISMaster.getString("no_lab") + ", Tgl. Periksa : " + rsLISMaster.getString("tgl") + ", Jam : " + rsLISMaster.getString("jam") + "</td></td></tr>"
                                        + "<tr align='center'>"
                                        + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Jenis Pemeriksaan/Item</td>"
                                        + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Metode Pemeriksaan</td>"
                                        + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Hasil</td>"
                                        + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Rujukan</td>"
                                        + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Satuan</td>"
                                        + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Flag Kode</td>"
                                        + "</tr>"
                                );

                                rsLIS1 = koneksi.prepareStatement(
                                        "SELECT ifnull(kategori_pemeriksaan_nama,'') kategori_pemeriksaan_nama FROM lis_hasil_periksa_lab "
                                        + "WHERE no_lab ='" + rsLISMaster.getString("no_lab") + "' GROUP BY kategori_pemeriksaan_nama "
                                        + "ORDER BY kategori_pemeriksaan_no_urut, sub_kategori_pemeriksaan_no_urut, pemeriksaan_no_urut").executeQuery();

                                if (rsLIS1.next()) {
                                    rsLIS1.beforeFirst();
                                    w = 1;
                                    while (rsLIS1.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rsLIS1.getString("kategori_pemeriksaan_nama") + "</td>"
                                                + "</tr>");

                                        rsLIS2 = koneksi.prepareStatement("SELECT ifnull(sub_kategori_pemeriksaan_nama,'') sub_kategori_pemeriksaan_nama FROM lis_hasil_periksa_lab "
                                                + "WHERE no_lab='" + rsLISMaster.getString("no_lab") + "' and kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' "
                                                + "GROUP BY sub_kategori_pemeriksaan_nama ORDER BY kategori_pemeriksaan_no_urut, sub_kategori_pemeriksaan_no_urut, "
                                                + "sub_kategori_pemeriksaan_nama desc, pemeriksaan_no_urut").executeQuery();
                                        if (rsLIS2.next()) {
                                            rsLIS2.beforeFirst();
                                            lis1 = 1;
                                            while (rsLIS2.next()) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top'>&emsp;" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "</td>"
                                                        + "</tr>");

                                                rsLIS3 = koneksi.prepareStatement("SELECT ifnull(pemeriksaan_nama,'') pemeriksaan_nama, metode, nilai_hasil, nilai_rujukan, "
                                                        + "satuan, flag_kode FROM lis_hasil_periksa_lab WHERE no_lab='" + rsLISMaster.getString("no_lab") + "' and "
                                                        + "sub_kategori_pemeriksaan_nama='" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "' and "
                                                        + "kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' GROUP BY pemeriksaan_nama "
                                                        + "ORDER BY kategori_pemeriksaan_no_urut, sub_kategori_pemeriksaan_no_urut, pemeriksaan_no_urut").executeQuery();
                                                if (rsLIS3.next()) {
                                                    rsLIS3.beforeFirst();
                                                    lis2 = 1;
                                                    while (rsLIS3.next()) {
                                                        htmlContent.append(
                                                                "<tr>"
                                                                + "<td valign='top'>&emsp;&emsp;" + rsLIS3.getString("pemeriksaan_nama") + "</td>"
                                                                + "<td valign='top'>" + rsLIS3.getString("metode") + "</td>"
                                                                + "<td valign='top'>" + rsLIS3.getString("nilai_hasil") + "</td>"
                                                                + "<td valign='top'>" + rsLIS3.getString("nilai_rujukan") + "</td>"
                                                                + "<td valign='top'>" + rsLIS3.getString("satuan") + "</td>"
                                                                + "<td valign='top'>" + rsLIS3.getString("flag_kode") + "</td>"
                                                                + "</tr>");
                                                        lis2++;
                                                    }
                                                }
                                                lis1++;
                                            }
                                        }
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table><br/>");
                                }
                            }
                        }

                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsLIS1 != null) {
                            rsLIS1.close();
                        }
                    }
                    htmlContent.append(
                            "</td>"
                            + "</tr>"
                    );
                    htmlContent.append("<tr class='isi'><td colspan='3' bgcolor='#7eccb9'>&nbsp;</td></tr>");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }
            LoadHTML2.setText(
                    "<html>"
                    + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString()
                    + "</table>"
                    + "</html>");
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void tampilRiwDiagnosaPrimer(String norwt) {
        Valid.tabelKosong(tabModeDiagnosa);
        try {
            ps2 = koneksi.prepareStatement("SELECT py.kd_penyakit, py.nm_penyakit, py.ciri_ciri, py.keterangan, kp.nm_kategori, kp.ciri_umum "
                    + "FROM diagnosa_pasien dp INNER JOIN penyakit py ON py.kd_penyakit=dp.kd_penyakit INNER JOIN kategori_penyakit kp ON kp.kd_ktg=py.kd_ktg "
                    + "WHERE dp.no_rawat='" + norwt + "' and dp.prioritas=1 and dp.status='Ralan' LIMIT 1");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabModeDiagnosa.addRow(new Object[]{
                        true,
                        rs2.getString(1),
                        rs2.getString(2),
                        rs2.getString(3),
                        rs2.getString(4),
                        rs2.getString(5),
                        rs2.getString(6)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRiwDiagnosaSekunder(String norwt) {
        Valid.tabelKosong(tabModeDiagnosaSekunder);
        try {
            ps3 = koneksi.prepareStatement("SELECT py.kd_penyakit, py.nm_penyakit, py.ciri_ciri, py.keterangan, kp.nm_kategori, kp.ciri_umum "
                    + "FROM diagnosa_pasien dp INNER JOIN penyakit py ON py.kd_penyakit=dp.kd_penyakit "
                    + "INNER JOIN kategori_penyakit kp ON kp.kd_ktg=py.kd_ktg "
                    + "WHERE dp.no_rawat='" + norwt + "' and dp.status='Ralan' order by dp.prioritas");
            try {
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    tabModeDiagnosaSekunder.addRow(new Object[]{
                        true,
                        rs3.getString(1),
                        rs3.getString(2),
                        rs3.getString(3),
                        rs3.getString(4),
                        rs3.getString(5),
                        rs3.getString(6)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs3 != null) {
                    rs3.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRiwProsedur(String norwt) {
        Valid.tabelKosong(tabModeProsedur);
        try {
            ps4 = koneksi.prepareStatement("SELECT i.* FROM prosedur_pasien pp INNER JOIN icd9 i ON i.kode=pp.kode "
                    + "WHERE pp.no_rawat='" + norwt + "' and pp.status='Ralan' ORDER BY pp.prioritas");
            try {
                rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    tabModeProsedur.addRow(new Object[]{
                        true,
                        rs4.getString(1),
                        rs4.getString(2),
                        rs4.getString(3)
                    });
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void bersihkanConteng() {
        for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
            tbDiagnosa.setValueAt(Boolean.FALSE, i, 0);
        }
        
        for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
            tbDiagnosa1.setValueAt(Boolean.FALSE, i, 0);
        }
        
        for (i = 0; i < tbProsedur.getRowCount(); i++) {
            tbProsedur.setValueAt(Boolean.FALSE, i, 0);
        }
    }
    
    private void tampilBilingRanap() {
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
                prosesCariRegRanap();
                prosesCariKamarRanap();

                if (!norawatbayi.equals("")) {                    
                    tabMode3.addRow(new Object[]{"Biaya Perawatan Ibu", ":", "", null, null, null, null, "-"});
                }

                prosesCariTindakanRanap(TNoRw.getText());
                prosesCariOperasiRanap(TNoRw.getText());
                prosesCariObatRanap(TNoRw.getText());
                prosesResepPulangRanap(TNoRw.getText());
                prosesCariTambahanRanap(TNoRw.getText());
                prosesCariPotonganRanap(TNoRw.getText());
                if (!norawatbayi.equals("")) {
                    tabMode3.addRow(new Object[]{"", "", "", null, null, null, null, "-"});
                    tabMode3.addRow(new Object[]{"Biaya Perawatan Bayi", ":", "", null, null, null, null, "-"});
                    prosesCariTindakanRanap(norawatbayi);
                    prosesCariOperasiRanap(norawatbayi);
                    prosesCariObatRanap(norawatbayi);
                    prosesResepPulangRanap(norawatbayi);
                    prosesCariTambahanRanap(norawatbayi);
                    prosesCariPotonganRanap(norawatbayi);
                }
                TCari.setText("");
                isHitungRanap();
                status = "belum";
            } else if (i > 0) {
                uangdeposit = Sequel.cariIsiAngka("select ifnull(sum(Uang_Muka),0) from nota_inap where no_rawat=?", TNoRw.getText());
                Valid.SetTgl2(DTPTgl, Sequel.cariIsi("select concat(tanggal,' ',jam) from nota_inap where no_rawat='" + TNoRw.getText() + "'"));
                Valid.tabelKosong(tabMode3);
                pssudahmasuk = koneksi.prepareStatement(sqlpssudahmasuk);
                try {
                    pssudahmasuk.setString(1, TNoRw.getText());
                    rsreg = pssudahmasuk.executeQuery();
                    while (rsreg.next()) {
                        if (!rsreg.getString("status").equals("Tagihan")) {
                            tabMode3.addRow(new Object[]{rsreg.getString("no"),
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
                isHitungRanap();
                prosesCariPenjaminPiutangRanap(TNoRw.getText());
                status = "sudah";
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private void prosesCariRegRanap() {
        tglmskRS = "";
        tglklrRS1 = "";
        tglklrRS2 = "";
        jamplgRS1 = "";
        jamplgRS2 = "";
        Valid.tabelKosong(tabMode3);

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
                    tabMode3.addRow(new Object[]{"No.Nota", ": " + Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_nota,6),signed)),0) from nota_inap where left(tanggal,7)='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "' ", Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7).replaceAll("-", "/") + "/RI/", 6), "", null, null, null, null, "-"});

                    pskamar = koneksi.prepareStatement("select concat(kamar.kd_kamar,', ',bangsal.nm_bangsal) from bangsal inner join kamar "
                            + "on kamar.kd_bangsal=bangsal.kd_bangsal where kamar.kd_kamar=?");
                    try {
                        pskamar.setString(1, rsreg.getString("kd_kamar"));
                        rskamar = pskamar.executeQuery();
                        if (rskamar.next()) {
                            tabMode3.addRow(new Object[]{"Bangsal/Kamar", ": " + rskamar.getString(1), "", null, null, null, null, "-"});
                            tabMode3.addRow(new Object[]{"Tgl. Perawatan", ": " + rsreg.getString("registrasi") + " s.d. " + rsreg.getString("keluar") + " (" + rsreg.getString("lama") + " Hari)", "", null, null, null, null, "-"});
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
                    norawatbayi = "";
                    psanak = koneksi.prepareStatement(sqlpsanak);
                    try {
                        psanak.setString(1, TNoRw.getText());
                        rsanak = psanak.executeQuery();
                        if (rsanak.next()) {
                            norawatbayi = rsanak.getString("no_rawat2");
                            tabMode3.addRow(new Object[]{"No.R.M. Ibu", ": " + TNoRM.getText(), "", null, null, null, null, "-"});
                            tabMode3.addRow(new Object[]{"Nama Ibu", ": " + TPasien.getText(), "", null, null, null, null, "-"});
                            tabMode3.addRow(new Object[]{"No.R.M. Bayi", ": " + rsanak.getString("no_rkm_medis"), "", null, null, null, null, "-"});
                            tabMode3.addRow(new Object[]{"Nama Bayi", ": " + rsanak.getString("nm_pasien"), "", null, null, null, null, "-"});
                        } else {
                            tabMode3.addRow(new Object[]{"Pasien", ": " + data_pasien, "", null, null, null, null, "-"});
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
                            tabMode3.addRow(new Object[]{"Alamat Pasien", ": " + rscarialamat.getString(1), "", null, null, null, null, "-"});
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
                            tabMode3.addRow(new Object[]{"DPJP Rawat Inap", ": " + rscaridpjp.getString(1), "", null, null, null, null, "-"});
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
                                tabMode3.addRow(new Object[]{"Dokter Visite", ":", "", null, null, null, null, "-"});
                            }
                            x = 1;
                            rsdokterranap.beforeFirst();
                            while (rsdokterranap.next()) {
                                tabMode3.addRow(new Object[]{"                           ", rsdokterranap.getString("nm_dokter"), "", null, null, null, null, "Dokter"});
                                x++;
                            }
                            //rs2.close();
                            rsdokterralan.beforeFirst();
                            while (rsdokterralan.next()) {
                                tabMode3.addRow(new Object[]{"                           ", rsdokterralan.getString("nm_dokter"), "", null, null, null, null, "Dokter"});
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
                                tabMode3.addRow(new Object[]{"Dokter ", ":", "", null, null, null, null, "-"});
                            }
                            x = 1;
                            rsdokterranap.beforeFirst();
                            while (rsdokterranap.next()) {
                                tabMode3.addRow(new Object[]{"                           ", rsdokterranap.getString("nm_dokter"), "", null, null, null, null, "Dokter"});
                                x++;
                            }
                            //rs2.close();
                            rsdokterralan.beforeFirst();
                            while (rsdokterralan.next()) {
                                tabMode3.addRow(new Object[]{"                           ", rsdokterralan.getString("nm_dokter"), "", null, null, null, null, "Dokter"});
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
                        tabMode3.addRow(new Object[]{"Registrasi", ":", "", null, null, null, rsreg.getDouble("biaya_reg"), "Registrasi"});
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
    
    private void prosesCariKamarRanap() {
        tabMode3.addRow(new Object[]{"Ruang Perawatan Inap", ":", "", null, null, null, null, "Kamar"});
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
                            tabMode3.addRow(new Object[]{"                           ", rskamarin.getString("kd_kamar") + ", " + rskamarin.getString("nm_bangsal") + " (Ibu)", ":",
                                rskamarin.getDouble("trf_kamar"), rskamarin.getDouble("lama"), tamkur, (rskamarin.getDouble("total") + tamkur), "Kamar"});
                            subttl = subttl + rskamarin.getDouble("total") + tamkur;

                            tabMode3.addRow(new Object[]{"                           ", rskamarin.getString("kd_kamar") + ", " + rskamarin.getString("nm_bangsal") + " (Bayi)", ":",
                                (rskamarin.getDouble("trf_kamar") * (persenbayi / 100)), rskamarin.getDouble("lama"), tamkur, ((rskamarin.getDouble("total") * (persenbayi / 100)) + tamkur), "Kamar"});
                            subttl = subttl + (rskamarin.getDouble("total") * (persenbayi / 100)) + tamkur;
                        } else {
                            tabMode3.addRow(new Object[]{"                           ", rskamarin.getString("kd_kamar") + ", " + rskamarin.getString("nm_bangsal"), ":",
                                rskamarin.getDouble("trf_kamar"), rskamarin.getDouble("lama"), tamkur, (rskamarin.getDouble("total") + tamkur), "Kamar"});
                            subttl = subttl + rskamarin.getDouble("total") + tamkur;
                        }
                    } else {
                        tabMode3.addRow(new Object[]{"                           ", rskamarin.getString("kd_kamar") + ", " + rskamarin.getString("nm_bangsal"), ":",
                            rskamarin.getDouble("trf_kamar"), rskamarin.getDouble("lama"), tamkur, (rskamarin.getDouble("total") + tamkur), "Kamar"});
                        subttl = subttl + rskamarin.getDouble("total") + tamkur;
                    }

                    psbiayasekali = koneksi.prepareStatement(sqlpsbiayasekali);
                    try {
                        psbiayasekali.setString(1, rskamarin.getString("kd_kamar"));
                        rsbiayasekali = psbiayasekali.executeQuery();
                        if (rsbiayasekali.next()) {
                            tabMode3.addRow(new Object[]{"-", "Biaya Kamar :", "", null, null, null, null, "Kamar"});
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
                            tabMode3.addRow(new Object[]{"                           ", rsbiayasekali.getString("nama_biaya"), ":",
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
                            tabMode3.addRow(new Object[]{"-", "Biaya Harian :", "", null, null, null, null, "Harian"});
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

                            tabMode3.addRow(new Object[]{"                           ", rsbiayaharian.getString("nama_biaya"), ":",
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
            tabMode3.addRow(new Object[]{"", "Total Kamar Inap : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlKamar"});
        }
    }
    
    private void prosesCariTindakanRanap(String norawat) {
        detailjs = 0;
        detailbhp = 0;
        try {
            tabMode3.addRow(new Object[]{"Rincian Biaya", ":", "", null, null, null, null, "Ranap Dokter"});
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
                        if (Status.getSelectedIndex() == 0) {
                            if (rsralandrpr.next() || rsralandokter.next() || rsralanperawat.next()) {
                                tabMode3.addRow(new Object[]{x + ". " + rskategori.getString(2), ":", "", null, null, null, null, "Ranap Dokter"});
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
                                    tabMode3.addRow(new Object[]{"", rsralandrpr.getString("nm_perawatan"), ":",
                                        rsralandrpr.getDouble("tarif_tindakandr"), rsralandrpr.getDouble("jml"), tamkur, (rsralandrpr.getDouble("totaltarif_tindakandr") + tamkur), "Ralan Dokter Paramedis"});
                                    subttl = subttl + rsralandrpr.getDouble("totaltarif_tindakandr") + tamkur;
                                } else {
                                    tabMode3.addRow(new Object[]{"                           ", rsralandrpr.getString("nm_perawatan"), ":",
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
                                    tabMode3.addRow(new Object[]{"", rsralandokter.getString("nm_perawatan"), ":",
                                        rsralandokter.getDouble("tarif_tindakandr"), rsralandokter.getDouble("jml"), tamkur, (rsralandokter.getDouble("totaltarif_tindakandr") + tamkur), "Ralan Dokter"});
                                    subttl = subttl + rsralandokter.getDouble("totaltarif_tindakandr") + tamkur;
                                } else {
                                    tabMode3.addRow(new Object[]{"                           ", rsralandokter.getString("nm_perawatan"), ":",
                                        rsralandokter.getDouble("total_byrdr"), rsralandokter.getDouble("jml"), tamkur, (tamkur + rsralandokter.getDouble("biaya")), "Ralan Dokter"});
                                    subttl = subttl + rsralandokter.getDouble("biaya") + tamkur;
                                }
                            }

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

                                tabMode3.addRow(new Object[]{"                           ", rsralanperawat.getString("nm_perawatan"), ":",
                                    rsralanperawat.getDouble("total_byrpr"), rsralanperawat.getDouble("jml"), tamkur, (tamkur + rsralanperawat.getDouble("biaya")), "Ralan Paramedis"});
                                subttl = subttl + rsralanperawat.getDouble("biaya") + tamkur;
                            }
                        }

                        if (Status.getSelectedIndex() == 1) {
                            if (rsranapdrpr.next() || rsranapdokter.next() || rsranapperawat.next()) {
                                tabMode3.addRow(new Object[]{x + ". " + rskategori.getString(2), ":", "", null, null, null, null, "Ranap Dokter"});
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
                                    tabMode3.addRow(new Object[]{"", rsranapdokter.getString("nm_perawatan"), ":",
                                        rsranapdokter.getDouble("tarif_tindakandr"), rsranapdokter.getDouble("jml"), tamkur, (rsranapdokter.getDouble("totaltarif_tindakandr") + tamkur), "Ranap Dokter"});
                                    subttl = subttl + rsranapdokter.getDouble("totaltarif_tindakandr") + tamkur;
                                } else {
                                    tabMode3.addRow(new Object[]{"                           ", rsranapdokter.getString("nm_perawatan"), ":",
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
                                    tabMode3.addRow(new Object[]{"", rsranapdrpr.getString("nm_perawatan"), ":",
                                        rsranapdrpr.getDouble("tarif_tindakandr"), rsranapdrpr.getDouble("jml"), tamkur, (rsranapdrpr.getDouble("totaltarif_tindakandr") + tamkur), "Ranap Dokter Paramedis"});
                                    subttl = subttl + rsranapdrpr.getDouble("totaltarif_tindakandr") + tamkur;
                                } else {
                                    tabMode3.addRow(new Object[]{"                           ", rsranapdrpr.getString("nm_perawatan"), ":",
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
                                tabMode3.addRow(new Object[]{"                           ", rsranapperawat.getString("nm_perawatan"), ":",
                                    rsranapperawat.getDouble("total_byrpr"), rsranapperawat.getDouble("jml"), tamkur, (tamkur + rsranapperawat.getDouble("biaya")), "Ranap Paramedis"});
                                subttl = subttl + rsranapperawat.getDouble("biaya") + tamkur;
                            }
                        }

                        if (subttl > 1) {
                            tabMode3.addRow(new Object[]{"", "Total " + rskategori.getString(2) + " : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlRanap Dokter"});
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
                if ((Status.getSelectedIndex() == 0) && (Status.getSelectedIndex() == 1)) {
                    psperiksalab.setString(2, "%%");
                } else if ((Status.getSelectedIndex() == 0) && (Status.getSelectedIndex() != 1)) {
                    psperiksalab.setString(2, "%Ralan%");
                } else if ((Status.getSelectedIndex() != 0) && (Status.getSelectedIndex() == 1)) {
                    psperiksalab.setString(2, "%Ranap%");
                } else if ((Status.getSelectedIndex() != 0) && (Status.getSelectedIndex() != 1)) {
                    psperiksalab.setString(2, "%Kosong%");
                }
                rsperiksalab = psperiksalab.executeQuery();
                if (rsperiksalab.next()) {
                    tabMode3.addRow(new Object[]{x + ". Pemeriksaan Lab", ":", "", null, null, null, null, "Laborat"});
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
                            tabMode3.addRow(new Object[]{"                           ", rsdetaillab.getString("Pemeriksaan"), ":",
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

                if (subttl > 1) {
                    tabMode3.addRow(new Object[]{"", "Total Periksa Lab : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlLaborat"});
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
                if ((Status.getSelectedIndex() == 0) && (Status.getSelectedIndex() == 1)) {
                    psperiksarad.setString(2, "%%");
                } else if ((Status.getSelectedIndex() == 0) && (Status.getSelectedIndex() != 1)) {
                    psperiksarad.setString(2, "%Ralan%");
                } else if ((Status.getSelectedIndex() != 0) && (Status.getSelectedIndex() == 1)) {
                    psperiksarad.setString(2, "%Ranap%");
                } else if ((Status.getSelectedIndex() != 0) && (Status.getSelectedIndex() != 1)) {
                    psperiksarad.setString(2, "%Kosong%");
                }
                rsperiksarad = psperiksarad.executeQuery();
                if (rsperiksarad.next()) {
                    tabMode3.addRow(new Object[]{x + ". Pemeriksaan Radiologi", ":", "", null, null, null, null, "Radiologi"});
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
                    tabMode3.addRow(new Object[]{"                           ", rsperiksarad.getString("nm_perawatan"), ":",
                        rsperiksarad.getDouble("biaya"), rsperiksarad.getDouble("jml"), tamkur, (tamkur + rsperiksarad.getDouble("total")), "Radiologi"});
                    subttl = subttl + rsperiksarad.getDouble("total") + tamkur;
                }

                if (subttl > 1) {
                    tabMode3.addRow(new Object[]{"", "Total Periksa Radiologi : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlRadiologi"});
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
                tabMode3.addRow(new Object[]{x + ". Jasa Sarpras", ":", "", null, null, null, detailjs, "Ralan Dokter"});
                x++;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private void prosesCariOperasiRanap(String norawat) {
        try {
            subttl = 0;
            psoperasi = koneksi.prepareStatement(sqlpsoperasi);
            try {
                psoperasi.setString(1, norawat);
                if ((Status.getSelectedIndex() == 0) && (Status.getSelectedIndex() == 1)) {
                    psoperasi.setString(2, "%%");
                } else if ((Status.getSelectedIndex() == 0) && (Status.getSelectedIndex() != 1)) {
                    psoperasi.setString(2, "%Ralan%");
                } else if ((Status.getSelectedIndex() != 0) && (Status.getSelectedIndex() == 1)) {
                    psoperasi.setString(2, "%Ranap%");
                } else if ((Status.getSelectedIndex() != 0) && (Status.getSelectedIndex() != 1)) {
                    psoperasi.setString(2, "%Kosong%");
                }
                rsoperasi = psoperasi.executeQuery();
                if (rsoperasi.next()) {
                    tabMode3.addRow(new Object[]{x + ". Operasi", ":", "", null, null, null, null, "Operasi"});
                    x++;
                }
                rsoperasi.beforeFirst();
                if (rincianoperasi.equals("Yes")) {
                    while (rsoperasi.next()) {
                        tabMode3.addRow(new Object[]{"                           ", rsoperasi.getString("nm_perawatan"), ":", null, null, null, null, "Operasi"});
                        if (rsoperasi.getDouble("biayaoperator1") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Operator 1 (" + rsoperasi.getString("dokter_operator1") + ")", ":", rsoperasi.getDouble("biayaoperator1"), 1, 0, rsoperasi.getDouble("biayaoperator1"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaoperator2") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Operator 2 (" + rsoperasi.getString("dokter_operator2") + ")", ":", rsoperasi.getDouble("biayaoperator2"), 1, 0, rsoperasi.getDouble("biayaoperator2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaoperator3") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Operator 3 (" + rsoperasi.getString("dokter_operator3") + ")", ":", rsoperasi.getDouble("biayaoperator3"), 1, 0, rsoperasi.getDouble("biayaoperator3"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_operator1") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Asisten Operator 1 (" + rsoperasi.getString("asisten_operator1") + ")", ":", rsoperasi.getDouble("biayaasisten_operator1"), 1, 0, rsoperasi.getDouble("biayaasisten_operator1"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_operator2") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Asisten Operator 2 (" + rsoperasi.getString("asisten_operator2") + ")", ":", rsoperasi.getDouble("biayaasisten_operator2"), 1, 0, rsoperasi.getDouble("biayaasisten_operator2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_operator3") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Asisten Operator 3 (" + rsoperasi.getString("asisten_operator3") + ")", ":", rsoperasi.getDouble("biayaasisten_operator3"), 1, 0, rsoperasi.getDouble("biayaasisten_operator3"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayainstrumen") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Instrumen (" + rsoperasi.getString("instrumen") + ")", ":", rsoperasi.getDouble("biayainstrumen"), 1, 0, rsoperasi.getDouble("biayainstrumen"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayadokter_anak") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Dokter Anak (" + rsoperasi.getString("dokter_anak") + ")", ":", rsoperasi.getDouble("biayadokter_anak"), 1, 0, rsoperasi.getDouble("biayadokter_anak"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaperawaat_resusitas") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Perawat Resusitas (" + rsoperasi.getString("perawaat_resusitas") + ")", ":", rsoperasi.getDouble("biayaperawaat_resusitas"), 1, 0, rsoperasi.getDouble("biayaperawaat_resusitas"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayadokter_anestesi") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Dokter Anastesi (" + rsoperasi.getString("dokter_anestesi") + ")", ":", rsoperasi.getDouble("biayadokter_anestesi"), 1, 0, rsoperasi.getDouble("biayadokter_anestesi"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_anestesi") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Asisten Anastesi 1 (" + rsoperasi.getString("asisten_anestesi") + ")", ":", rsoperasi.getDouble("biayaasisten_anestesi"), 1, 0, rsoperasi.getDouble("biayaasisten_anestesi"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_anestesi2") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Asisten Anastesi 2 (" + rsoperasi.getString("asisten_anestesi2") + ")", ":", rsoperasi.getDouble("biayaasisten_anestesi2"), 1, 0, rsoperasi.getDouble("biayaasisten_anestesi2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayabidan") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Bidan 1 (" + rsoperasi.getString("bidan") + ")", ":", rsoperasi.getDouble("biayabidan"), 1, 0, rsoperasi.getDouble("biayabidan"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayabidan2") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Bidan 2 (" + rsoperasi.getString("bidan2") + ")", ":", rsoperasi.getDouble("biayabidan2"), 1, 0, rsoperasi.getDouble("biayabidan2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayabidan3") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Bidan 3 (" + rsoperasi.getString("bidan3") + ")", ":", rsoperasi.getDouble("biayabidan3"), 1, 0, rsoperasi.getDouble("biayabidan3"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaperawat_luar") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Perawat Luar (" + rsoperasi.getString("perawat_luar") + ")", ":", rsoperasi.getDouble("biayaperawat_luar"), 1, 0, rsoperasi.getDouble("biayaperawat_luar"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaalat") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Alat", ":", rsoperasi.getDouble("biayaalat"), 1, 0, rsoperasi.getDouble("biayaalat"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayasewaok") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Sewa OK/VK", ":", rsoperasi.getDouble("biayasewaok"), 1, 0, rsoperasi.getDouble("biayasewaok"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("akomodasi") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Akomodasi", ":", rsoperasi.getDouble("akomodasi"), 1, 0, rsoperasi.getDouble("akomodasi"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Onloop 1 (" + rsoperasi.getString("omloop") + ")", ":", rsoperasi.getDouble("biaya_omloop"), 1, 0, rsoperasi.getDouble("biaya_omloop"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop2") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Onloop 2 (" + rsoperasi.getString("omloop2") + ")", ":", rsoperasi.getDouble("biaya_omloop2"), 1, 0, rsoperasi.getDouble("biaya_omloop2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop3") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Onloop 3 (" + rsoperasi.getString("omloop3") + ")", ":", rsoperasi.getDouble("biaya_omloop3"), 1, 0, rsoperasi.getDouble("biaya_omloop3"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop4") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Onloop 4 (" + rsoperasi.getString("omloop4") + ")", ":", rsoperasi.getDouble("biaya_omloop4"), 1, 0, rsoperasi.getDouble("biaya_omloop4"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop5") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Onloop 5 (" + rsoperasi.getString("omloop5") + ")", ":", rsoperasi.getDouble("biaya_omloop5"), 1, 0, rsoperasi.getDouble("biaya_omloop5"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("bagian_rs") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  N.M.S.", ":", rsoperasi.getDouble("bagian_rs"), 1, 0, rsoperasi.getDouble("bagian_rs"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayasarpras") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Operasional RS", ":", rsoperasi.getDouble("biayasarpras"), 1, 0, rsoperasi.getDouble("biayasarpras"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_dokter_pjanak") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Dokter PJ Anak", ":", rsoperasi.getDouble("biaya_dokter_pjanak"), 1, 0, rsoperasi.getDouble("biaya_dokter_pjanak"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_dokter_umum") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Dokter Umum", ":", rsoperasi.getDouble("biaya_dokter_umum"), 1, 0, rsoperasi.getDouble("biaya_dokter_umum"), "Operasi"});
                        }
                        subttl = subttl + rsoperasi.getDouble("biaya");
                    }
                } else {
                    while (rsoperasi.next()) {
                        tabMode3.addRow(new Object[]{"                           ", rsoperasi.getString("nm_perawatan"), ":", rsoperasi.getDouble("biaya"), 1, 0, rsoperasi.getDouble("biaya"), "Operasi"});
                        subttl = subttl + rsoperasi.getDouble("biaya");
                    }
                }

                if (subttl > 0) {
                    tabMode3.addRow(new Object[]{"", "Total Operasi : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlOperasi"});
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
    
    private void prosesCariObatRanap(String norawat) {
        tabMode3.addRow(new Object[]{x + ". Obat & BHP", ":", "", null, null, null, null, "Obat"});
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
                if ((Status.getSelectedIndex() == 0) && (Status.getSelectedIndex() == 1)) {
                    pscariobat.setString(2, "%%");
                } else if ((Status.getSelectedIndex() == 0) && (Status.getSelectedIndex() != 1)) {
                    pscariobat.setString(2, "%Ralan%");
                } else if ((Status.getSelectedIndex() != 0) && (Status.getSelectedIndex() == 1)) {
                    pscariobat.setString(2, "%Ranap%");
                } else if ((Status.getSelectedIndex() != 0) && (Status.getSelectedIndex() != 1)) {
                    pscariobat.setString(2, "%Kosong%");
                }
                rscariobat = pscariobat.executeQuery();
                while (rscariobat.next()) {                    
                        tabMode3.addRow(new Object[]{"                           ", rscariobat.getString("nama_brng"), ":",
                            rscariobat.getDouble("biaya_obat"), rscariobat.getDouble("jml"), rscariobat.getDouble("tambahan"),
                            (rscariobat.getDouble("total") + rscariobat.getDouble("tambahan")), "Obat"});
                        subttl = subttl + rscariobat.getDouble("total") + rscariobat.getDouble("tambahan");
                        //embalase=embalase+rscariobat.getDouble("tambahan");
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
            
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        try {
            psobatlangsung = koneksi.prepareStatement(sqlpsobatlangsung);
            try {
                psobatlangsung.setString(1, norawat);
                rsobatlangsung = psobatlangsung.executeQuery();
                if (rsobatlangsung.next()) {
                    tabMode3.addRow(new Object[]{"                           ", "Obat & BHP", ":", rsobatlangsung.getDouble("besar_tagihan"), 1, 0, rsobatlangsung.getDouble("besar_tagihan"), "Obat"});
                    subttl = subttl + rsobatlangsung.getDouble("besar_tagihan");
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
                    tabMode3.addRow(new Object[]{"                           ", rsobatoperasi.getString("nm_obat"), ":",
                        rsobatoperasi.getDouble("hargasatuan"), rsobatoperasi.getDouble("jumlah"), 0,
                        rsobatoperasi.getDouble("total"), "Obat"});
                    subttl = subttl + rsobatoperasi.getDouble("total");
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
            ttlobat = subttl;
            tabMode3.addRow(new Object[]{"", "Total Obat & BHP : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlObat"});
        }

        subttl = 0;
        try {
            psreturobat = koneksi.prepareStatement(sqlpsreturobat);
            try {
                psreturobat.setString(1, norawat);
                rsreturobat = psreturobat.executeQuery();
                if (rsreturobat.next()) {
                    tabMode3.addRow(new Object[]{"", "Retur Obat :", "", null, null, null, null, "Retur Obat"});
                }
                rsreturobat.beforeFirst();
                while (rsreturobat.next()) {
                    tabMode3.addRow(new Object[]{
                        false, "                           ", rsreturobat.getString("nama_brng"), ":",
                        rsreturobat.getDouble("h_retur"), rsreturobat.getDouble("jml"), 0,
                        rsreturobat.getDouble("ttl"), "Retur Obat"
                    });
                    subttl = subttl + rsreturobat.getDouble("ttl");
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
            ttlretur = subttl;
            tabMode3.addRow(new Object[]{"", "Total Retur Obat : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlRetur Obat"});
        }

        if ((ttlobat - ttlretur) > 0) {
            if (tampilkan_ppnobat_ranap.equals("Yes")) {
                ppnobat = Valid.roundUp((ttlobat + ttlretur) * 0.1, 100);
                tabMode3.addRow(new Object[]{"", "PPN Obat", ":", ppnobat, 1, 0, ppnobat, "Obat"});
                tabMode3.addRow(new Object[]{"", "Total Obat Bersih : " + Valid.SetAngka3(ttlobat + ttlretur + ppnobat), "", null, null, null, null, "TtlRetur Obat"});
            } else {
                tabMode3.addRow(new Object[]{"", "Total Obat Bersih : " + Valid.SetAngka3(ttlobat + ttlretur), "", null, null, null, null, "TtlRetur Obat"});
            }
        }

        if (detailbhp > 0) {
            tabMode3.addRow(new Object[]{x + ". Paket Obat/BHP", ":", "", null, null, null, detailbhp, "Ralan Dokter"});
            x++;
        }
    }
    
    private void prosesResepPulangRanap(String norawat) {
        if (Sequel.cariInteger("select count(resep_pulang.kode_brng) from resep_pulang where resep_pulang.no_rawat=?", norawat) > 0) {
            tabMode3.addRow(new Object[]{"Resep Pulang", ":", "", null, null, null, null, "Resep Pulang"});
        } else {
            tabMode3.addRow(new Object[]{"Resep Pulang", ":", "", null, null, null, null, "Resep Pulang"});
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
                    tabMode3.addRow(new Object[]{"                           ", rsreseppulang.getString("nama_brng") + " " + rsreseppulang.getString("dosis"), ":",
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
            tabMode3.addRow(new Object[]{"", "Total Resep Pulang : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlResep Pulang"});
        }
    }
    
    private void prosesCariTambahanRanap(String norawat) {
        x++;
        subttl = 0;
        try {
            pstambahanbiaya = koneksi.prepareStatement(sqlpstambahanbiaya);
            try {
                pstambahanbiaya.setString(1, norawat);
                rstambahanbiaya = pstambahanbiaya.executeQuery();
                rstambahanbiaya.last();
                if (rstambahanbiaya.getRow() > 0) {
                    tabMode3.addRow(new Object[]{"Tambahan Biaya", ":", "", null, null, null, null, "Tambahan"});
                } else {
                    tabMode3.addRow(new Object[]{"Tambahan Biaya", ":", "", null, null, null, null, "Tambahan"});
                }
                rstambahanbiaya.beforeFirst();
                while (rstambahanbiaya.next()) {
                    tabMode3.addRow(new Object[]{"                           ", rstambahanbiaya.getString("nama_biaya"), ":",
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
            tabMode3.addRow(new Object[]{"", "Total Tambahan : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlTambahan"});
        }
    }
    
    private void prosesCariPotonganRanap(String norawat) {
        x++;
        subttl = 0;
        try {
            pspotonganbiaya = koneksi.prepareStatement(sqlpspotonganbiaya);
            try {
                pspotonganbiaya.setString(1, norawat);
                rspotonganbiaya = pspotonganbiaya.executeQuery();
                rspotonganbiaya.last();
                if (rspotonganbiaya.getRow() > 0) {
                    tabMode3.addRow(new Object[]{"Potongan Biaya", ":", "", null, null, null, null, "Potongan"});
                } else {
                    tabMode3.addRow(new Object[]{"Potongan Biaya", ":", "", null, null, null, null, "Potongan"});
                }
                rspotonganbiaya.beforeFirst();
                while (rspotonganbiaya.next()) {
                    tabMode3.addRow(new Object[]{"                           ", rspotonganbiaya.getString("nama_pengurangan"), ":",
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
            tabMode3.addRow(new Object[]{"", "Total Potongan : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlPotongan"});
        }
    }
    
    private void isHitungRanap() {
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
        int row = tabMode3.getRowCount();
        if (row > 0) {
            for (int r = 0; r < row; r++) {
                y = 0;
                try {
                    y = Double.parseDouble(tabMode3.getValueAt(r, 6).toString());
                } catch (Exception e) {
                    y = 0;
                }
                switch (tabMode3.getValueAt(r, 7).toString()) {
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
                    psservice = koneksi.prepareStatement("select * from set_service_ranap");

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
                            tabMode3.addRow(new Object[]{rsservice.getString("nama_service"), ":", "", null, null, null, ttlService, "Service"});
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
    
    private void prosesCariPenjaminPiutangRanap(String norawat) {
        if (Sequel.cariInteger("select count(-1) from piutang_pasien where no_rawat='" + norawat + "'") > 0) {
            x++;
            try {
                tabMode3.addRow(new Object[]{"Penjamin Piutang", ": "
                    + Sequel.cariIsi("select if(penjamin='-',penjamin,concat(penjamin,' (',ket_penjamin,')')) from piutang_pasien where no_rawat='" + norawat + "'") + "",
                    "", null, null, null, null, "Penjamin Piutang"});
            } catch (Exception ex) {
                System.out.println("Notifikasi : " + ex);
            }
        }
    }
    
    private void tampilBilingRalan() {
        try {
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

            pscarirm = koneksi.prepareStatement(sqlpscarirm);
            try {
                pscarirm.setString(1, TNoRw.getText());
                rscarirm = pscarirm.executeQuery();
                if (rscarirm.next()) {
                    TNoRM.setText(rscarirm.getString(1));
                    lbl_jns_byr.setText(" Cara Bayar : " + rscarirm.getString("png_jawab"));
                }
            } catch (Exception e) {
                TNoRM.setText("");
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscarirm != null) {
                    rscarirm.close();
                }
                if (pscarirm != null) {
                    pscarirm.close();
                }
            }

            pscaripasien = koneksi.prepareStatement(sqlpscaripasien);
            try {
                pscaripasien.setString(1, TNoRM.getText());
                rscaripasien = pscaripasien.executeQuery();
                if (rscaripasien.next()) {
                    TPasien.setText(rscaripasien.getString(1) + " (" + rscaripasien.getString(2) + ".)");
                }
            } catch (Exception e) {
                TPasien.setText("");
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscaripasien != null) {
                    rscaripasien.close();
                }
                if (pscaripasien != null) {
                    pscaripasien.close();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        Jasa_Medik_Dokter_Tindakan_Ralan = 0;
        Jasa_Medik_Paramedis_Tindakan_Ralan = 0;
        KSO_Tindakan_Ralan = 0;
        Jasa_Medik_Dokter_Laborat_Ralan = 0;
        Jasa_Medik_Petugas_Laborat_Ralan = 0;
        Kso_Laborat_Ralan = 0;
        Persediaan_Laborat_Rawat_Jalan = 0;
        Jasa_Medik_Dokter_Radiologi_Ralan = 0;
        Jasa_Medik_Petugas_Radiologi_Ralan = 0;
        Kso_Radiologi_Ralan = 0;
        Persediaan_Radiologi_Rawat_Jalan = 0;
        Obat_Rawat_Jalan = 0;
        Jasa_Medik_Dokter_Operasi_Ralan = 0;
        Jasa_Medik_Paramedis_Operasi_Ralan = 0;
        Obat_Operasi_Ralan = 0;

//        if (i <= 0) {
        if (chkBayar.isSelected() == false) {
            chkBayar.setText("Transaksi BELUM DIBAYAR");
            prosesCariRegRalan();
            tabMode3.addRow(new Object[]{"Tindakan", ":", "", null, null, null, null, "Ralan Dokter"});
            prosesCariRwJlDrRalan();
            prosesCariRwJlDrPrRalan();
            prosesCariRwJlPrRalan();
            prosesCariPeriksaLabRalan();
            prosesCariRadiologiRalan();
            prosesCariOperasiRalan();

            if (detailjs > 0) {
                tabMode3.addRow(new Object[]{"", "Jasa Sarana dan Prasarana", ":", null, null, null, detailjs, "Ralan Dokter"});
            }

            tabMode3.addRow(new Object[]{"Obat & BHP", ":", "", null, null, null, null, "Obat"});
            prosesCariObatRalan();
            if (detailbhp > 0) {
                tabMode3.addRow(new Object[]{"", "Paket Obat/BHP", ":", null, null, null, detailbhp, "Ralan Dokter"});
            }

            try {
                pstambahan = koneksi.prepareStatement(sqlpstambahan);
                try {
                    pstambahan.setString(1, TNoRw.getText());
                    rstambahan = pstambahan.executeQuery();
                    rstambahan.last();
                    if (rstambahan.getRow() > 0) {
                        tabMode3.addRow(new Object[]{"Tambahan Biaya", ":", "", null, null, null, null, "Tambahan"});
                    } else {
                        tabMode3.addRow(new Object[]{"Tambahan Biaya", ":", "", null, null, null, null, "Tambahan"});
                    }
                    rstambahan.beforeFirst();
                    while (rstambahan.next()) {
                        tabMode3.addRow(new Object[]{"", rstambahan.getString("nama_biaya"), ":",
                            rstambahan.getDouble("besar_biaya"), 1, null, rstambahan.getDouble("besar_biaya"), "Tambahan"});
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rstambahan != null) {
                        rstambahan.close();
                    }
                    if (pstambahan != null) {
                        pstambahan.close();
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Notifikasi : " + ex);
            }

            try {
                pspotongan = koneksi.prepareStatement(sqlpspotongan);
                try {
                    pspotongan.setString(1, TNoRw.getText());
                    rspotongan = pspotongan.executeQuery();
                    rspotongan.last();
                    if (rspotongan.getRow() > 0) {
                        tabMode3.addRow(new Object[]{"Potongan Biaya", ":", "", null, null, null, null, "Potongan"});
                    } else {
                        tabMode3.addRow(new Object[]{"Potongan Biaya", ":", "", null, null, null, null, "Potongan"});
                    }
                    rspotongan.beforeFirst();
                    while (rspotongan.next()) {
                        tabMode3.addRow(new Object[]{"", rspotongan.getString("nama_pengurangan"), ":",
                            rspotongan.getDouble("besar_pengurangan"), 1, null, (-1 * rspotongan.getDouble("besar_pengurangan")), "Potongan"});
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rspotongan != null) {
                        rspotongan.close();
                    }
                    if (pspotongan != null) {
                        pspotongan.close();
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Notifikasi : " + ex);
            }

            TNoNota.setText("");
            isHitungRalan();
            status = "belum";

//        } else if (i > 0) {
        } else if (chkBayar.isSelected() == true) {
            chkBayar.setText("Transaksi SUDAH DIBAYAR");
            Valid.SetTgl2(DTPTgl, Sequel.cariIsi("select concat(tanggal,' ',jam) from nota_jalan where no_rawat='" + TNoRw.getText() + "'"));
            Valid.tabelKosong(tabMode3);
            try {
                psbilling = koneksi.prepareStatement("select no,nm_perawatan, if(biaya<>0,biaya,null) as satu, if(jumlah<>0,jumlah,null) as dua,"
                        + "if(tambahan<>0,tambahan,null) as tiga, if(totalbiaya<>0,totalbiaya,null) as empat,pemisah,status "
                        + "from billing where no_rawat='" + TNoRw.getText() + "' and no_nota like '%" + TNoNota.getText() + "%' order by noindex");
                try {
//                    psbilling.setString(1, TNoRw.getText());                    
                    rsbilling = psbilling.executeQuery();
                    while (rsbilling.next()) {
                        if (!rsbilling.getString("status").equals("Tagihan")) {
                            tabMode3.addRow(new Object[]{rsbilling.getString("no"),
                                rsbilling.getString("nm_perawatan"),
                                rsbilling.getString("pemisah"),
                                rsbilling.getObject("satu"),
                                rsbilling.getObject("dua"),
                                rsbilling.getObject("tiga"),
                                rsbilling.getObject("empat"),
                                rsbilling.getString("status")});
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rsbilling != null) {
                        rsbilling.close();
                    }
                    if (psbilling != null) {
                        psbilling.close();
                    }
                }

                isHitungRalan();
                prosesCariPenjaminPiutangRalan(TNoRw.getText());
                status = "sudah";
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }
    
    private void prosesCariRegRalan() {
        Valid.tabelKosong(tabMode3);
        nmPoli = "";
        try {
            psreg = koneksi.prepareStatement(sqlpsreg);
            try {
                psreg.setString(1, TNoRw.getText());
                rsreg = psreg.executeQuery();
                if (rsreg.next()) {
                    psRujuk = koneksi.prepareStatement("select p.nm_poli from rujukan_internal_poli r "
                            + "inner join poliklinik p on p.kd_poli = r.kd_poli where r.no_rawat = '" + TNoRw.getText() + "'");
                    try {
                        rsRujuk = psRujuk.executeQuery();
                        if (rsRujuk.next()) {
                            nmPoli = nmPoli + rsRujuk.getString("nm_poli") + ", ";
                        }

                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }

                    //DTPTgl.setDate(rsreg.getDate("tgl_registrasi"));
                    tabMode3.addRow(new Object[]{"No. Nota", ": " + Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_nota,6),signed)),0) from nota_jalan where left(tanggal,7)='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7) + "' ", Valid.SetTgl(DTPTgl.getSelectedItem() + "").substring(0, 7).replaceAll("-", "/") + "/RJ/", 6), "", null, null, null, null, "-"});
                    pscaripoli = koneksi.prepareStatement(sqlpscaripoli);
                    try {
                        pscaripoli.setString(1, rsreg.getString("kd_poli"));
                        rscaripoli = pscaripoli.executeQuery();
                        if (rscaripoli.next()) {
                            tabMode3.addRow(new Object[]{"Poliklinik/Inst.", ": " + rscaripoli.getString(1) + " (" + nmPoli + ")", "", null, null, null, null, "-"});
                        }
                    } catch (Exception e) {
                        tabMode3.addRow(new Object[]{"Poliklinik/Inst.", ": ", "", null, null, null, null, "-"});
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rscaripoli != null) {
                            rscaripoli.close();
                        }
                        if (pscaripoli != null) {
                            pscaripoli.close();
                        }
                    }

                    tabMode3.addRow(new Object[]{"Tanggal & Jam", ": " + rsreg.getString("tgl_registrasi") + " " + rsreg.getString("jam"), "", null, null, null, null, "-"});
                    tabMode3.addRow(new Object[]{"Pasien", ": " + TPasien.getText() + " No. RM. " + TNoRM.getText(), "", null, null, null, null, "-"});
                    pscarialamat = koneksi.prepareStatement(sqlpscarialamat);
                    try {
                        pscarialamat.setString(1, TNoRM.getText());
                        rscarialamat = pscarialamat.executeQuery();
                        if (rscarialamat.next()) {
                            tabMode3.addRow(new Object[]{"Alamat Pasien", ": " + rscarialamat.getString(1), "", null, null, null, null, "-"});
                        }
                    } catch (Exception e) {
                        tabMode3.addRow(new Object[]{"Alamat Pasien", ": ", "", null, null, null, null, "-"});
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rscarialamat != null) {
                            rscarialamat.close();
                        }
                        if (pscarialamat != null) {
                            pscarialamat.close();
                        }
                    }

                    //cari dokter yang menangani 
                    if (centangdokterralan.equals("Yes")) {
                        psdokterralan = koneksi.prepareStatement(sqlpsdokterrawatjalan);
                        try {
                            psdokterralan.setString(1, TNoRw.getText());
                            rsdokterralan = psdokterralan.executeQuery();
                            if (rsdokterralan.next()) {
                                tabMode3.addRow(new Object[]{"Dokter ", ":", "", null, null, null, null, "-"});
                            }
                            rsdokterralan.beforeFirst();
                            while (rsdokterralan.next()) {
                                tabMode3.addRow(new Object[]{"", rsdokterralan.getString("nm_dokter"), "", null, null, null, null, "Dokter"});
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        } finally {
                            if (rsdokterralan != null) {
                                rsdokterralan.close();
                            }
                            if (psdokterralan != null) {
                                psdokterralan.close();
                            }
                        }
                    } else {
                        psdokterralan = koneksi.prepareStatement(sqlpsdokterrawatjalan);
                        try {
                            psdokterralan.setString(1, TNoRw.getText());
                            rsdokterralan = psdokterralan.executeQuery();
                            if (rsdokterralan.next()) {
                                tabMode3.addRow(new Object[]{"Dokter ", ":", "", null, null, null, null, "-"});
                            }
                            rsdokterralan.beforeFirst();
                            while (rsdokterralan.next()) {
                                tabMode3.addRow(new Object[]{"", rsdokterralan.getString("nm_dokter"), "", null, null, null, null, "Dokter"});
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        } finally {
                            if (rsdokterralan != null) {
                                rsdokterralan.close();
                            }
                            if (psdokterralan != null) {
                                psdokterralan.close();
                            }
                        }
                    }

                    tabMode3.addRow(new Object[]{"Registrasi", ":", "", null, null, null, rsreg.getDouble("biaya_reg"), "Registrasi"});

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
    
    private void prosesCariRwJlDrRalan() {
        try {
            pscariralandokter = koneksi.prepareStatement(sqlpscariralandokter);
            try {
                pscariralandokter.setString(1, TNoRw.getText());
                rscariralandokter = pscariralandokter.executeQuery();
                subttl = 0;
                detailbhp = 0;
                detailjs = 0;
                while (rscariralandokter.next()) {
                    Jasa_Medik_Dokter_Tindakan_Ralan = Jasa_Medik_Dokter_Tindakan_Ralan + rscariralandokter.getDouble("totaltarif_tindakandr");
                    tamkur = 0;
                    pstamkur = koneksi.prepareStatement(sqlpstamkur);
                    try {
                        pstamkur.setString(1, TNoRw.getText());
                        pstamkur.setString(2, rscariralandokter.getString("nm_perawatan"));
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
                    if (rinciandokterralan.equals("Yes")) {
                        detailbhp = detailbhp + rscariralandokter.getDouble("totalbhp");
                        detailjs = detailjs + rscariralandokter.getDouble("totalmaterial");
                        tabMode3.addRow(new Object[]{"", rscariralandokter.getString("nm_perawatan"), ":",
                            rscariralandokter.getDouble("tarif_tindakandr"), rscariralandokter.getDouble("jml"), tamkur, (rscariralandokter.getDouble("totaltarif_tindakandr") + tamkur), "Ralan Dokter"});
                        subttl = subttl + rscariralandokter.getDouble("totaltarif_tindakandr") + tamkur;
                    } else {
                        tabMode3.addRow(new Object[]{"", rscariralandokter.getString("nm_perawatan"), ":",
                            rscariralandokter.getDouble("total_byrdr"), rscariralandokter.getDouble("jml"), tamkur, (rscariralandokter.getDouble("biaya") + tamkur), "Ralan Dokter"});
                        subttl = subttl + rscariralandokter.getDouble("biaya") + tamkur;
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscariralandokter != null) {
                    rscariralandokter.close();
                }
                if (pscariralandokter != null) {
                    pscariralandokter.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void prosesCariRwJlDrPrRalan() {
        try {
            pscariralandrpr = koneksi.prepareStatement(sqlpscariralandrpr);
            try {
                pscariralandrpr.setString(1, TNoRw.getText());
                rscariralandrpr = pscariralandrpr.executeQuery();
                subttl = 0;
                while (rscariralandrpr.next()) {
                    Jasa_Medik_Dokter_Tindakan_Ralan = Jasa_Medik_Dokter_Tindakan_Ralan + rscariralandrpr.getDouble("totaltarif_tindakandr");
                    Jasa_Medik_Paramedis_Tindakan_Ralan = Jasa_Medik_Paramedis_Tindakan_Ralan + rscariralandrpr.getDouble("totaltarif_tindakanpr");
                    tamkur = 0;
                    pstamkur = koneksi.prepareStatement(sqlpstamkur);
                    try {
                        pstamkur.setString(1, TNoRw.getText());
                        pstamkur.setString(2, rscariralandrpr.getString("nm_perawatan"));
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

                    if (rinciandokterralan.equals("Yes")) {
                        detailbhp = detailbhp + rscariralandrpr.getDouble("totalbhp");
                        detailjs = detailjs + rscariralandrpr.getDouble("totalmaterial") + rscariralandrpr.getDouble("totaltarif_tindakanpr");
                        tabMode3.addRow(new Object[]{"", rscariralandrpr.getString("nm_perawatan"), ":",
                            rscariralandrpr.getDouble("tarif_tindakandr"), rscariralandrpr.getDouble("jml"), tamkur, (rscariralandrpr.getDouble("totaltarif_tindakandr") + tamkur), "Ralan Dokter Paramedis"});
                        subttl = subttl + rscariralandrpr.getDouble("totaltarif_tindakandr") + tamkur;
                    } else {
                        tabMode3.addRow(new Object[]{"", rscariralandrpr.getString("nm_perawatan"), ":",
                            rscariralandrpr.getDouble("total_byrdrpr"), rscariralandrpr.getDouble("jml"), tamkur, (rscariralandrpr.getDouble("biaya") + tamkur), "Ralan Dokter Paramedis"});
                        subttl = subttl + rscariralandrpr.getDouble("biaya") + tamkur;
                    }

                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscariralandrpr != null) {
                    rscariralandrpr.close();
                }
                if (pscariralandrpr != null) {
                    pscariralandrpr.close();
                }
            }
            //rs.close();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void prosesCariRwJlPrRalan() {
        try {
            pscariralanperawat = koneksi.prepareStatement(sqlpscariralanperawat);
            try {
                pscariralanperawat.setString(1, TNoRw.getText());
                rscariralanperawat = pscariralanperawat.executeQuery();
                subttl = 0;
                while (rscariralanperawat.next()) {
                    Jasa_Medik_Paramedis_Tindakan_Ralan = Jasa_Medik_Paramedis_Tindakan_Ralan + rscariralanperawat.getDouble("totaltarif_tindakanpr");
                    tamkur = 0;
                    pstamkur = koneksi.prepareStatement(sqlpstamkur);
                    try {
                        pstamkur.setString(1, TNoRw.getText());
                        pstamkur.setString(2, rscariralanperawat.getString("nm_perawatan"));
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

                    tabMode3.addRow(new Object[]{"", rscariralanperawat.getString("nm_perawatan"), ":",
                        rscariralanperawat.getDouble("total_byrpr"), rscariralanperawat.getDouble("jml"), tamkur, (rscariralanperawat.getDouble("biaya") + tamkur), "Ralan Paramedis"});
                    subttl = subttl + rscariralanperawat.getDouble("biaya") + tamkur;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscariralanperawat != null) {
                    rscariralanperawat.close();
                }
                if (pscariralanperawat != null) {
                    pscariralanperawat.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void prosesCariPeriksaLabRalan() {
        try {
            pscarilab = koneksi.prepareStatement(sqlpscarilab);
            try {
                pscarilab.setString(1, TNoRw.getText());
                rscarilab = pscarilab.executeQuery();
                subttl = 0;
                while (rscarilab.next()) {
                    Jasa_Medik_Dokter_Laborat_Ralan = Jasa_Medik_Dokter_Laborat_Ralan + rscarilab.getDouble("totaldokter");
                    Jasa_Medik_Petugas_Laborat_Ralan = Jasa_Medik_Petugas_Laborat_Ralan + rscarilab.getDouble("totalpetugas");
                    Kso_Laborat_Ralan = Kso_Laborat_Ralan + rscarilab.getDouble("totalkso");
                    Persediaan_Laborat_Rawat_Jalan = Persediaan_Laborat_Rawat_Jalan + rscarilab.getDouble("totalbhp");
                    psdetaillab = koneksi.prepareStatement(sqlpsdetaillab);
                    try {
                        psdetaillab.setString(1, TNoRw.getText());
                        psdetaillab.setString(2, rscarilab.getString("kd_jenis_prw"));
                        rsdetaillab = psdetaillab.executeQuery();
                        ralanparamedis = 0;
                        while (rsdetaillab.next()) {
                            Jasa_Medik_Dokter_Laborat_Ralan = Jasa_Medik_Dokter_Laborat_Ralan + rsdetaillab.getDouble("totaldokter");
                            Jasa_Medik_Petugas_Laborat_Ralan = Jasa_Medik_Petugas_Laborat_Ralan + rsdetaillab.getDouble("totalpetugas");
                            Kso_Laborat_Ralan = Kso_Laborat_Ralan + rsdetaillab.getDouble("totalkso");
                            Persediaan_Laborat_Rawat_Jalan = Persediaan_Laborat_Rawat_Jalan + rsdetaillab.getDouble("totalbhp");
                            ralanparamedis = rsdetaillab.getDouble("total");
                        }
                    } catch (Exception e) {
                        ralanparamedis = 0;
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsdetaillab != null) {
                            rsdetaillab.close();
                        }
                        if (psdetaillab != null) {
                            psdetaillab.close();
                        }
                    }
//                    tabModeRwJlDr.addRow(new Object[]{true, "", rscarilab.getString("nm_perawatan"), ":",
//                        rscarilab.getDouble("biaya"), rscarilab.getDouble("jml"), ralanparamedis, (rscarilab.getDouble("total") + ralanparamedis), "Laborat"});
                    subttl = subttl + rscarilab.getDouble("total") + ralanparamedis;
                }

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscarilab != null) {
                    rscarilab.close();
                }
                if (pscarilab != null) {
                    pscarilab.close();
                }
            }

            pscarilab = koneksi.prepareStatement(sqlpscarilab);
            pscarilab.setString(1, TNoRw.getText());
            rscarilab = pscarilab.executeQuery();
            if (rscarilab.next()) {
                psdetaillab = koneksi.prepareStatement(
                        "SELECT tl.Pemeriksaan,dpl.biaya_item,count(tl.Pemeriksaan) jumlah,sum(dpl.biaya_item) total "
                        + "FROM detail_periksa_lab dpl INNER JOIN template_laboratorium tl ON tl.id_template = dpl.id_template "
                        + "WHERE dpl.no_rawat = ? group by tl.Pemeriksaan ");
                try {
                    psdetaillab.setString(1, TNoRw.getText());
//                        psdetaillab.setString(2, rsperiksalab.getString("kd_jenis_prw"));
                    rsdetaillab = psdetaillab.executeQuery();
                    lab = 0;
                    while (rsdetaillab.next()) {
                        lab = 0;
                        tabMode3.addRow(new Object[]{"                           ", rsdetaillab.getString("Pemeriksaan"), ":",
                            rsdetaillab.getDouble("biaya_item"), rsdetaillab.getDouble("jumlah"), lab, (rsdetaillab.getDouble("total") + lab), "Laborat"});
//                        subttl = subttl + rsdetaillab.getDouble("total") + lab;
                    }

                    if (subttl > 1) {
                        tabMode3.addRow(new Object[]{"", "Total Periksa Lab : " + Valid.SetAngka(subttl), "", null, null, null, null, "TtlLaborat"});
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

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void prosesCariRadiologiRalan() {
        try {
            pscariradiologi = koneksi.prepareStatement(sqlpscariradiologi);
            try {
                pscariradiologi.setString(1, TNoRw.getText());
                rscariradiologi = pscariradiologi.executeQuery();
                subttl = 0;
                while (rscariradiologi.next()) {
                    Jasa_Medik_Dokter_Radiologi_Ralan = Jasa_Medik_Dokter_Radiologi_Ralan + rscariradiologi.getDouble("totaldokter");
                    Jasa_Medik_Petugas_Radiologi_Ralan = Jasa_Medik_Petugas_Radiologi_Ralan + rscariradiologi.getDouble("totalpetugas");
                    Kso_Radiologi_Ralan = Kso_Radiologi_Ralan + rscariradiologi.getDouble("totalkso");
                    Persediaan_Radiologi_Rawat_Jalan = Persediaan_Radiologi_Rawat_Jalan + rscariradiologi.getDouble("totalbhp");
                    tamkur = 0;
                    pstamkur = koneksi.prepareStatement(sqlpstamkur);
                    try {
                        pstamkur.setString(1, TNoRw.getText());
                        pstamkur.setString(2, rscariradiologi.getString("nm_perawatan"));
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

                    tabMode3.addRow(new Object[]{"", rscariradiologi.getString("nm_perawatan"), ":",
                        rscariradiologi.getDouble("biaya"), rscariradiologi.getDouble("jml"), tamkur, (rscariradiologi.getDouble("total") + tamkur), "Radiologi"});
                    subttl = subttl + rscariradiologi.getDouble("total") + tamkur;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscariradiologi != null) {
                    rscariradiologi.close();
                }
                if (pscariradiologi != null) {
                    pscariradiologi.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void prosesCariOperasiRalan() {
        try {
            subttl = 0;
            psoperasi = koneksi.prepareStatement(sqlpsoperasiralan);
            try {
                psoperasi.setString(1, TNoRw.getText());
                rsoperasi = psoperasi.executeQuery();
                if (rsoperasi.next()) {
                    tabMode3.addRow(new Object[]{"Operasi", ":", "", null, null, null, null, "Operasi"});
                }
                rsoperasi.beforeFirst();
                if (rincianoperasi.equals("Yes")) {
                    while (rsoperasi.next()) {
                        Jasa_Medik_Dokter_Operasi_Ralan = Jasa_Medik_Dokter_Operasi_Ralan + rsoperasi.getDouble("biayaoperator1")
                                + rsoperasi.getDouble("biayaoperator2") + rsoperasi.getDouble("biayaoperator3") + rsoperasi.getDouble("biayadokter_anak")
                                + rsoperasi.getDouble("biayadokter_anestesi") + rsoperasi.getDouble("biaya_dokter_pjanak") + rsoperasi.getDouble("biaya_dokter_umum");
                        Jasa_Medik_Paramedis_Operasi_Ralan = Jasa_Medik_Paramedis_Operasi_Ralan + rsoperasi.getDouble("biayaasisten_operator1")
                                + rsoperasi.getDouble("biayaasisten_operator2") + rsoperasi.getDouble("biayaasisten_operator3") + rsoperasi.getDouble("biayainstrumen") + rsoperasi.getDouble("biayaperawaat_resusitas")
                                + rsoperasi.getDouble("biayaasisten_anestesi") + rsoperasi.getDouble("biayaasisten_anestesi2") + rsoperasi.getDouble("biayabidan") + rsoperasi.getDouble("biayabidan2")
                                + rsoperasi.getDouble("biayabidan3") + rsoperasi.getDouble("biayaperawat_luar") + rsoperasi.getDouble("biaya_omloop")
                                + rsoperasi.getDouble("biaya_omloop2") + rsoperasi.getDouble("biaya_omloop3") + rsoperasi.getDouble("biaya_omloop4") + rsoperasi.getDouble("biaya_omloop5");

                        tabMode3.addRow(new Object[]{"                           ", rsoperasi.getString("nm_perawatan"), ":", null, null, null, null, "Operasi"});

                        if (rsoperasi.getDouble("biayaoperator1") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Operator 1 (" + rsoperasi.getString("dokter_operator1") + ")", ":", rsoperasi.getDouble("biayaoperator1"), 1, 0, rsoperasi.getDouble("biayaoperator1"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaoperator2") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Operator 2 (" + rsoperasi.getString("dokter_operator2") + ")", ":", rsoperasi.getDouble("biayaoperator2"), 1, 0, rsoperasi.getDouble("biayaoperator2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaoperator3") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Operator 3 (" + rsoperasi.getString("dokter_operator3") + ")", ":", rsoperasi.getDouble("biayaoperator3"), 1, 0, rsoperasi.getDouble("biayaoperator3"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_operator1") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Asisten Operator 1 (" + rsoperasi.getString("asisten_operator1") + ")", ":", rsoperasi.getDouble("biayaasisten_operator1"), 1, 0, rsoperasi.getDouble("biayaasisten_operator1"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_operator2") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Asisten Operator 2 (" + rsoperasi.getString("asisten_operator2") + ")", ":", rsoperasi.getDouble("biayaasisten_operator2"), 1, 0, rsoperasi.getDouble("biayaasisten_operator2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_operator3") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Asisten Operator 3 (" + rsoperasi.getString("asisten_operator3") + ")", ":", rsoperasi.getDouble("biayaasisten_operator3"), 1, 0, rsoperasi.getDouble("biayaasisten_operator3"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayainstrumen") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Instrumen (" + rsoperasi.getString("instrumen") + ")", ":", rsoperasi.getDouble("biayainstrumen"), 1, 0, rsoperasi.getDouble("biayainstrumen"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayadokter_anak") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Dokter Anak (" + rsoperasi.getString("dokter_anak") + ")", ":", rsoperasi.getDouble("biayadokter_anak"), 1, 0, rsoperasi.getDouble("biayadokter_anak"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaperawaat_resusitas") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Perawat Resusitas (" + rsoperasi.getString("perawaat_resusitas") + ")", ":", rsoperasi.getDouble("biayaperawaat_resusitas"), 1, 0, rsoperasi.getDouble("biayaperawaat_resusitas"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayadokter_anestesi") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Dokter Anastesi (" + rsoperasi.getString("dokter_anestesi") + ")", ":", rsoperasi.getDouble("biayadokter_anestesi"), 1, 0, rsoperasi.getDouble("biayadokter_anestesi"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_anestesi") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Asisten Anastesi 1 (" + rsoperasi.getString("asisten_anestesi") + ")", ":", rsoperasi.getDouble("biayaasisten_anestesi"), 1, 0, rsoperasi.getDouble("biayaasisten_anestesi"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaasisten_anestesi2") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Asisten Anastesi 2 (" + rsoperasi.getString("asisten_anestesi2") + ")", ":", rsoperasi.getDouble("biayaasisten_anestesi2"), 1, 0, rsoperasi.getDouble("biayaasisten_anestesi2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayabidan") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Bidan 1 (" + rsoperasi.getString("bidan") + ")", ":", rsoperasi.getDouble("biayabidan"), 1, 0, rsoperasi.getDouble("biayabidan"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayabidan2") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Bidan 2 (" + rsoperasi.getString("bidan2") + ")", ":", rsoperasi.getDouble("biayabidan2"), 1, 0, rsoperasi.getDouble("biayabidan2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayabidan3") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Bidan 3 (" + rsoperasi.getString("bidan3") + ")", ":", rsoperasi.getDouble("biayabidan3"), 1, 0, rsoperasi.getDouble("biayabidan3"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaperawat_luar") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Perawat Luar (" + rsoperasi.getString("perawat_luar") + ")", ":", rsoperasi.getDouble("biayaperawat_luar"), 1, 0, rsoperasi.getDouble("biayaperawat_luar"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayaalat") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Alat", ":", rsoperasi.getDouble("biayaalat"), 1, 0, rsoperasi.getDouble("biayaalat"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayasewaok") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Sewa OK/VK", ":", rsoperasi.getDouble("biayasewaok"), 1, 0, rsoperasi.getDouble("biayasewaok"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("akomodasi") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Akomodasi", ":", rsoperasi.getDouble("akomodasi"), 1, 0, rsoperasi.getDouble("akomodasi"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Onloop 1 (" + rsoperasi.getString("omloop") + ")", ":", rsoperasi.getDouble("biaya_omloop"), 1, 0, rsoperasi.getDouble("biaya_omloop"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop2") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Onloop 2 (" + rsoperasi.getString("omloop2") + ")", ":", rsoperasi.getDouble("biaya_omloop2"), 1, 0, rsoperasi.getDouble("biaya_omloop2"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop3") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Onloop 3 (" + rsoperasi.getString("omloop3") + ")", ":", rsoperasi.getDouble("biaya_omloop3"), 1, 0, rsoperasi.getDouble("biaya_omloop3"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop4") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Onloop 4 (" + rsoperasi.getString("omloop4") + ")", ":", rsoperasi.getDouble("biaya_omloop4"), 1, 0, rsoperasi.getDouble("biaya_omloop4"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_omloop5") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Onloop 5 (" + rsoperasi.getString("omloop5") + ")", ":", rsoperasi.getDouble("biaya_omloop5"), 1, 0, rsoperasi.getDouble("biaya_omloop5"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("bagian_rs") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  N.M.S.", ":", rsoperasi.getDouble("bagian_rs"), 1, 0, rsoperasi.getDouble("bagian_rs"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biayasarpras") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Sarpras", ":", rsoperasi.getDouble("biayasarpras"), 1, 0, rsoperasi.getDouble("biayasarpras"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_dokter_pjanak") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Dokter PJ Anak", ":", rsoperasi.getDouble("biaya_dokter_pjanak"), 1, 0, rsoperasi.getDouble("biaya_dokter_pjanak"), "Operasi"});
                        }

                        if (rsoperasi.getDouble("biaya_dokter_umum") > 0) {
                            tabMode3.addRow(new Object[]{"                           ", "  Biaya Dokter Umum", ":", rsoperasi.getDouble("biaya_dokter_umum"), 1, 0, rsoperasi.getDouble("biaya_dokter_umum"), "Operasi"});
                        }
                        subttl = subttl + rsoperasi.getDouble("biaya");
                    }
                } else {
                    while (rsoperasi.next()) {
                        Jasa_Medik_Dokter_Operasi_Ralan = Jasa_Medik_Dokter_Operasi_Ralan + rsoperasi.getDouble("biayaoperator1")
                                + rsoperasi.getDouble("biayaoperator2") + rsoperasi.getDouble("biayaoperator3") + rsoperasi.getDouble("biayadokter_anak")
                                + rsoperasi.getDouble("biayadokter_anestesi") + rsoperasi.getDouble("biaya_dokter_pjanak") + rsoperasi.getDouble("biaya_dokter_umum");
                        Jasa_Medik_Paramedis_Operasi_Ralan = Jasa_Medik_Paramedis_Operasi_Ralan + rsoperasi.getDouble("biayaasisten_operator1")
                                + rsoperasi.getDouble("biayaasisten_operator2") + rsoperasi.getDouble("biayaasisten_operator3") + rsoperasi.getDouble("biayainstrumen") + rsoperasi.getDouble("biayaperawaat_resusitas")
                                + rsoperasi.getDouble("biayaasisten_anestesi") + rsoperasi.getDouble("biayaasisten_anestesi2") + rsoperasi.getDouble("biayabidan") + rsoperasi.getDouble("biayabidan2")
                                + rsoperasi.getDouble("biayabidan3") + rsoperasi.getDouble("biayaperawat_luar") + rsoperasi.getDouble("biaya_omloop")
                                + rsoperasi.getDouble("biaya_omloop2") + rsoperasi.getDouble("biaya_omloop3") + rsoperasi.getDouble("biaya_omloop4") + rsoperasi.getDouble("biaya_omloop5");
                        tabMode3.addRow(new Object[]{"                           ", rsoperasi.getString("nm_perawatan"), ":", rsoperasi.getDouble("biaya"), 1, 0, rsoperasi.getDouble("biaya"), "Operasi"});
                        subttl = subttl + rsoperasi.getDouble("biaya");
                    }
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
    
    private void prosesCariObatRalan() {
        subttl = 0;
        try {
            psobatlangsung = koneksi.prepareStatement(sqlpsobatlangsung);
            try {
                psobatlangsung.setString(1, TNoRw.getText());
                rsobatlangsung = psobatlangsung.executeQuery();
                if (rsobatlangsung.next()) {
                    tabMode3.addRow(new Object[]{"", "Obat & BHP ", ":", rsobatlangsung.getDouble("besar_tagihan"), 1, 0, rsobatlangsung.getDouble("besar_tagihan"), "Obat"});
                    subttl = subttl + rsobatlangsung.getDouble("besar_tagihan");
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
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        try {
            pscariobat = koneksi.prepareStatement(sqlpscariobat);
            try {
                pscariobat.setString(1, TNoRw.getText());
                rscariobat = pscariobat.executeQuery();
                //embalase=0;
                if (centangobatralan.equals("Yes")) {
                    while (rscariobat.next()) {
                        Obat_Rawat_Jalan = Obat_Rawat_Jalan + rscariobat.getDouble("totalbeli");
                        tabMode3.addRow(new Object[]{"", rscariobat.getString("nama_brng"), ":",
                            rscariobat.getDouble("biaya_obat"), rscariobat.getDouble("jml"), rscariobat.getDouble("tambahan"),
                            (rscariobat.getDouble("total") + rscariobat.getDouble("tambahan")), "Obat"});
                        subttl = subttl + rscariobat.getDouble("total") + rscariobat.getDouble("tambahan");
                    }
                } else {
                    while (rscariobat.next()) {
                        Obat_Rawat_Jalan = Obat_Rawat_Jalan + rscariobat.getDouble("totalbeli");
                        tabMode3.addRow(new Object[]{"", rscariobat.getString("nama_brng"), ":",
                            rscariobat.getDouble("biaya_obat"), rscariobat.getDouble("jml"), rscariobat.getDouble("tambahan"),
                            (rscariobat.getDouble("total") + rscariobat.getDouble("tambahan")), "Obat"});
                        subttl = subttl + rscariobat.getDouble("total") + rscariobat.getDouble("tambahan");
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
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        try {
            psobatoperasi = koneksi.prepareStatement(sqlpsobatoperasi);
            try {
                psobatoperasi.setString(1, TNoRw.getText());
                rsobatoperasi = psobatoperasi.executeQuery();
                if (centangobatralan.equals("Yes")) {
                    while (rsobatoperasi.next()) {
                        Obat_Operasi_Ralan = Obat_Operasi_Ralan + rsobatoperasi.getDouble("total");
                        tabMode3.addRow(new Object[]{"                           ", rsobatoperasi.getString("nm_obat"), ":",
                            rsobatoperasi.getDouble("hargasatuan"), rsobatoperasi.getDouble("jumlah"), 0,
                            rsobatoperasi.getDouble("total"), "Obat"});
                        subttl = subttl + rsobatoperasi.getDouble("total");
                    }
                } else {
                    while (rsobatoperasi.next()) {
                        Obat_Operasi_Ralan = Obat_Operasi_Ralan + rsobatoperasi.getDouble("total");
                        tabMode3.addRow(new Object[]{"                           ", rsobatoperasi.getString("nm_obat"), ":",
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

        try {
            psreturobat = koneksi.prepareStatement(sqlpsreturobat);
            try {
                psreturobat.setString(1, TNoRw.getText());

                rsreturobat = psreturobat.executeQuery();
                if (rsreturobat.next()) {
                    tabMode3.addRow(new Object[]{"", "Retur Obat :", "", null, null, null, null, "Retur Obat"});
                }
                rsreturobat.beforeFirst();
                while (rsreturobat.next()) {
                    Object[] data = {true, "", rsreturobat.getString("nama_brng"), ":",
                        rsreturobat.getDouble("h_retur"), rsreturobat.getDouble("jml"), null,
                        rsreturobat.getDouble("ttl"), "Retur Obat"};
                    tabMode3.addRow(data);
                    subttl = subttl + rsreturobat.getDouble("ttl");
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

        if (subttl > 0) {
            if (tampilkan_ppnobat_ralan.equals("Yes")) {
                ppnobat = Valid.roundUp(subttl * 0.1, 100);
                if (centangobatralan.equals("Yes")) {
                    tabMode3.addRow(new Object[]{"", "PPN Obat", ":", ppnobat, 1, 0, ppnobat, "Obat"});
                } else {
                    tabMode3.addRow(new Object[]{"", "PPN Obat", ":", ppnobat, 1, 0, ppnobat, "Obat"});
                }
                tabMode3.addRow(new Object[]{"", "" + Valid.SetAngka3(subttl + ppnobat), "", null, null, null, null, "TtlObat"});
            } else {
                tabMode3.addRow(new Object[]{"Tot. Biaya Farm.", ": " + Valid.SetAngka3(subttl), "", null, null, null, null, "TtlObat"});
            }
        }
    }
    
    private void prosesCariPenjaminPiutangRalan(String norawat) {
        if (Sequel.cariInteger("select count(-1) from piutang_pasien where no_rawat='" + norawat + "'") > 0) {
            try {
                tabMode3.addRow(new Object[]{"Penjamin Piutang", ": "
                    + Sequel.cariIsi("select if(penjamin='-',penjamin,concat(penjamin,' (',ket_penjamin,')')) from piutang_pasien where no_rawat='" + norawat + "'") + "",
                    "", null, null, null, null, "Penjamin Piutang"});
            } catch (Exception ex) {
                System.out.println("Notifikasi : " + ex);
            }
        }
    }
    
    private void isHitungRalan() {
        ttl = 0;
        y = 0;
        ttlLaborat = 0;
        ttlRadiologi = 0;
        ttlObat = 0;
        ttlRalan_Dokter = 0;
        ttlRalan_Paramedis = 0;
        ttlTambahan = 0;
        ttlPotongan = 0;
        ttlRegistrasi = 0;
        ttlRalan_Dokter_Param = 0;
        ttlOperasi = 0;
        int a = tbBilling.getRowCount();
        for (r = 0; r < a; r++) {
            try {
                y = Double.parseDouble(tabMode3.getValueAt(r, 6).toString());
            } catch (Exception e) {
                y = 0;
            }
            switch (tabMode3.getValueAt(r, 7).toString()) {
                case "Laborat":
                    ttlLaborat = ttlLaborat + y;
                    break;
                case "Radiologi":
                    ttlRadiologi = ttlRadiologi + y;
                    break;
                case "Obat":
                    ttlObat = ttlObat + y;
                    break;
                case "Ralan Dokter":
                    ttlRalan_Dokter = ttlRalan_Dokter + y;
                    break;
                case "Ralan Dokter Paramedis":
                    ttlRalan_Dokter_Param = ttlRalan_Dokter_Param + y;
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
                case "Registrasi":
                    ttlRegistrasi = ttlRegistrasi + y;
                    break;
                case "Operasi":
                    ttlOperasi = ttlOperasi + y;
                    break;
            }
            ttl = ttl + y;
        }
        //TtlSemua.setText(Valid.SetAngka3(ttl));
        TtlSemua.setText(Valid.SetAngka3(Valid.roundUp(ttl, 0)));
        ttl = Valid.roundUp(ttl, 0);
    }
    
    private void cetakRingkasanRanap() {
        try {
            psLaprm = koneksi.prepareStatement("select *, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, if(p.jk='L','Laki-laki','Perempuan') jenkel, "
                    + "date_format(rp.tgl_registrasi,'%d-%m-%Y') tgl_msk from ringkasan_pulang_ranap r "
                    + "inner join reg_periksa rp on rp.no_rawat = r.no_rawat inner join pasien p on p.no_rkm_medis = rp.no_rkm_medis where r.no_rawat='" + TNoRw.getText() + "'");
            try {
                rsLaprm = psLaprm.executeQuery();
                while (rsLaprm.next()) {
                    diagsekunder = "";
                    tindakan = "";

                    //simpan diagnosa sekunder ICD-10------------->>
                    try {
                        psdiag = koneksi.prepareStatement("SELECT dp.kd_penyakit icd_sekunder, py.ciri_ciri diag_sekunder FROM diagnosa_pasien dp "
                                + "INNER JOIN penyakit py ON py.kd_penyakit = dp.kd_penyakit "
                                + "WHERE dp.no_rawat like '%" + rsLaprm.getString("no_rawat") + "%' AND dp.prioritas <> 1 AND dp. STATUS = 'ranap'");
                        try {
                            rsdiag = psdiag.executeQuery();
                            i = 1;
                            while (rsdiag.next()) {
                                if (diagsekunder.equals("")) {
                                    diagsekunder = i + ". " + rsdiag.getString("diag_sekunder") + " (ICD 10 : " + rsdiag.getString("icd_sekunder") + ")";
                                } else {
                                    diagsekunder = diagsekunder + "\n" + i + ". " + rsdiag.getString("diag_sekunder") + " (ICD 10 : " + rsdiag.getString("icd_sekunder") + ")";
                                }
                                i++;
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }

                    //simpan tindakan prosedur ------------->>
                    try {
                        pspros = koneksi.prepareStatement("SELECT pp.kode, i.deskripsi_panjang FROM prosedur_pasien pp INNER JOIN icd9 i ON i.kode = pp.kode "
                                + "WHERE pp.no_rawat like '%" + rsLaprm.getString("no_rawat") + "%' AND pp. STATUS = 'ranap'");
                        try {
                            rspros = pspros.executeQuery();
                            i = 1;
                            while (rspros.next()) {
                                if (tindakan.equals("")) {
                                    tindakan = i + ". " + rspros.getString("deskripsi_panjang") + " (ICD 9 CM : " + rspros.getString("kode") + ")";
                                } else {
                                    tindakan = tindakan + "\n" + i + ". " + rspros.getString("deskripsi_panjang") + " (ICD 9 CM : " + rspros.getString("kode") + ")";
                                }
                                i++;
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }

                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    param.put("norm", rsLaprm.getString("no_rkm_medis"));
                    param.put("nmpasien", rsLaprm.getString("nm_pasien"));
                    param.put("tgllahir", rsLaprm.getString("tgllahir"));
                    param.put("jk", rsLaprm.getString("jenkel"));
                    param.put("tglmsk", rsLaprm.getString("tgl_msk"));
                    param.put("tglplg", Sequel.cariIsi("select date_format(tgl_keluar,'%d-%m-%Y') from kamar_inap where stts_pulang not in ('-','Pindah Kamar') and no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    param.put("rgrawat", Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                            + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' "
                            + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));
                    param.put("crbayar", Sequel.cariIsi("select pj.png_jawab from reg_periksa r inner join penjab pj on pj.kd_pj=r.kd_pj where r.no_rawat='" + TNoRw.getText() + "'"));
                    param.put("drDPJP", Sequel.cariIsi("select ifnull(d.nm_dokter,'-') from dpjp_ranap dr inner join dokter d on d.kd_dokter=dr.kd_dokter where dr.no_rawat='" + TNoRw.getText() + "'"));
                    param.put("nmdokter", rsLaprm.getString("nm_dokter_pengirim"));
                    param.put("alasan", rsLaprm.getString("alasan_masuk_dirawat"));
                    param.put("ringkasan", rsLaprm.getString("ringkasan_riwayat_penyakit"));
                    param.put("fisik", rsLaprm.getString("pemeriksaan_fisik"));
                    param.put("penunjang", rsLaprm.getString("pemeriksaan_penunjang"));
                    param.put("terapi", rsLaprm.getString("terapi_pengobatan"));
                    param.put("diagnosaUtama", rsLaprm.getString("diagnosa_utama"));
                    param.put("diagnosaSekunder", rsLaprm.getString("diagnosa_sekunder"));
                    param.put("diagnosaSekunderList", diagsekunder);
                    param.put("tindakan", rsLaprm.getString("tindakan_prosedur"));
                    param.put("tindakanList", tindakan);
                    param.put("png_jawab_px", rsLaprm.getString("penanggung_jwb_pasien"));
                    param.put("kondisiPlg", Sequel.cariIsi("select stts_pulang from kamar_inap where no_rawat='" + TNoRw.getText() + "' order by tgl_masuk desc, jam_masuk desc limit 1"));
                    param.put("keadaanumum", rsLaprm.getString("keadaan_umum"));
                    param.put("kesadaran", rsLaprm.getString("kesadaran") + ", GCS : " + rsLaprm.getString("GCS"));
                    param.put("tandavital", "Tekanan Darah : " + rsLaprm.getString("tekanan_darah") + " mmHg, Suhu : " + rsLaprm.getString("suhu") + " °C, Nadi : " + rsLaprm.getString("nadi") + " x/mnt, Frekuensi Nafas : " + rsLaprm.getString("frekuensi_nafas") + " x/mnt");
                    param.put("edukasi", rsLaprm.getString("edukasi"));
                    param.put("catatanPenting", rsLaprm.getString("catatan_penting"));
                    param.put("terapiPlg", rsLaprm.getString("terapi_pulang"));
                    param.put("pengobatan", rsLaprm.getString("pengobatan_dilanjutkan") + " " + rsLaprm.getString("dokter_luar_lanjutan"));

                    if (rsLaprm.getString("cek_tgl_kontrol").equals("tidak")) {
                        param.put("tglkontrolpoli", "-");
                    } else {
                        param.put("tglkontrolpoli", Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_kontrol_poliklinik from ringkasan_pulang_ranap where no_rawat='" + rsLaprm.getString("no_rawat") + "'")));
                    }

                    param.put("tglRingkasan", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_keluar from kamar_inap where "
                            + "no_rawat='" + rsLaprm.getString("no_rawat") + "' and stts_pulang<>'Pindah Kamar' order by tgl_masuk desc, jam_masuk desc limit 1")));
                    param.put("jamRingkasan", "Jam          : " + Sequel.cariIsi("select time_format(jam_keluar,'%H:%i') from kamar_inap where "
                            + "no_rawat='" + rsLaprm.getString("no_rawat") + "' and stts_pulang<>'Pindah Kamar' order by tgl_masuk desc, jam_masuk desc limit 1") + " WITA");

                    Valid.MyReport("rptRingkasanPulangRanap.jasper", "report", "::[ Lembar Ringkasan Pulang Pasien Rawat Inap ]::",
                            "select date(now())", param);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsLaprm != null) {
                    rsLaprm.close();
                }
                if (psLaprm != null) {
                    psLaprm.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
