package fungsi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author Owner
 */

/**
 *
 * @author Owner
 */
public final class akses {

    private static final Connection koneksi = koneksiDB.condb();
    private static PreparedStatement ps, ps2;
    private static ResultSet rs, rs2;

    private static String kode = "", kdbangsal = "", namars = "", alamatrs = "", kabupatenrs = "", propinsirs = "", kontakrs = "", emailrs = "",
            form = "", namauser = "", jenisLoket = "", nomorLoket = "";
    private static int jml1 = 0, jml2 = 0, lebar = 0, tinggi = 0;
    private static boolean aktif=false,admin = false, user = false, vakum = false, aplikasi = false, penyakit = false, obat_penyakit = false, dokter = false, jadwal_praktek = false, petugas = false, pasien = false, registrasi = false,
            tindakan_ralan = false, kamar_inap = false, tindakan_ranap = false, operasi = false, rujukan_keluar = false, rujukan_masuk = false, beri_obat = false,
            resep_pulang = false, pasien_meninggal = false, diet_pasien = false, kelahiran_bayi = false, periksa_lab = false, periksa_radiologi = false,
            kasir_ralan = false, deposit_pasien = false, piutang_pasien = false, peminjaman_berkas = false, barcode = false, presensi_harian = false,
            presensi_bulanan = false, pegawai_admin = false, pegawai_user = false, suplier = false, satuan_barang = false, konversi_satuan = false, jenis_barang = false,
            obat = false, stok_opname_obat = false, stok_obat_pasien = false, pengadaan_obat = false, pemesanan_obat = false, penjualan_obat = false, piutang_obat = false,
            retur_ke_suplier = false, retur_dari_pembeli = false, retur_obat_ranap = false, retur_piutang_pasien = false, keuntungan_penjualan = false, keuntungan_beri_obat = false,
            sirkulasi_obat = false, ipsrs_barang = false, ipsrs_jenis_barang = false, ipsrs_pengadaan_barang = false, ipsrs_stok_keluar = false, ipsrs_rekap_pengadaan = false, ipsrs_rekap_stok_keluar = false,
            ipsrs_pengeluaran_harian = false, inventaris_jenis = false, inventaris_kategori = false, inventaris_merk = false, inventaris_ruang = false, inventaris_produsen = false,
            inventaris_koleksi = false, inventaris_inventaris = false, inventaris_sirkulasi = false, parkir_jenis = false, parkir_in = false, parkir_out = false,
            parkir_rekap_harian = false, parkir_rekap_bulanan = false, informasi_kamar = false, harian_tindakan_poli = false, obat_per_poli = false, obat_per_kamar = false,
            obat_per_dokter_ralan = false, obat_per_dokter_ranap = false, harian_dokter = false, bulanan_dokter = false, harian_paramedis = false, bulanan_paramedis = false,
            pembayaran_ralan = false, pembayaran_ranap = false, rekap_pembayaran_ralan = false, rekap_pembayaran_ranap = false, tagihan_masuk = false, tambahan_biaya = false,
            potongan_biaya = false, resep_obat = false, resume_pasien = false, penyakit_ralan = false, penyakit_ranap = false, kamar = false, tarif_ralan = false, tarif_ranap = false,
            tarif_lab = false, tarif_radiologi = false, tarif_operasi = false, akun_rekening = false, rekening_tahun = false, posting_jurnal = false, buku_besar = false,
            cashflow = false, keuangan = false, pengeluaran = false, setup_pjlab = false, setup_otolokasi = false, setup_jam_kamin = false, setup_embalase = false, tracer_login = false,
            display = false, set_harga_obat = false, set_penggunaan_tarif = false, set_oto_ralan = false, biaya_harian = false, biaya_masuk_sekali = false, set_no_rm = false,
            billing_ralan = false, billing_ranap = false, status = false, jm_ranap_dokter = false, igd = false, barcoderalan = false, barcoderanap = false, set_harga_obat_ralan = false,
            set_harga_obat_ranap = false, penyakit_pd3i = false, surveilans_pd3i = false, surveilans_ralan = false, diagnosa_pasien = false, surveilans_ranap = false,
            pny_takmenular_ranap = false, pny_takmenular_ralan = false, kunjungan_ralan = false, rl32 = false, rl33 = false, rl37 = false, rl38 = false, harian_tindakan_dokter = false,
            sms = false, sidikjari = false, jam_masuk = false, jadwal_pegawai = false, parkir_barcode = false, set_nota = false, dpjp_ranap = false, mutasi_barang = false, rl34 = false, rl36 = false,
            fee_visit_dokter = false, fee_bacaan_ekg = false, fee_rujukan_rontgen = false, fee_rujukan_ranap = false, fee_ralan = false, akun_bayar = false, bayar_pemesanan_obat = false,
            obat_per_dokter_peresep = false, pemasukan_lain = false, pengaturan_rekening = false, closing_kasir = false, keterlambatan_presensi = false, set_harga_kamar = false,
            rekap_per_shift = false, bpjs_cek_nik = false, bpjs_cek_kartu = false, obat_per_cara_bayar = false, kunjungan_ranap = false, bayar_piutang = false,
            payment_point = false, bpjs_cek_nomor_rujukan = false, icd9 = false, darurat_stok = false, retensi_rm = false, temporary_presensi = false, jurnal_harian = false,
            sirkulasi_obat2 = false, edit_registrasi = false, bpjs_referensi_diagnosa = false, bpjs_referensi_poli = false, industrifarmasi = false, harian_js = false, bulanan_js = false,
            harian_paket_bhp = false, bulanan_paket_bhp = false, piutang_pasien2 = false, bpjs_referensi_faskes = false, bpjs_sep = false, pengambilan_utd = false, tarif_utd = false,
            pengambilan_utd2 = false, utd_medis_rusak = false, pengambilan_penunjang_utd = false, pengambilan_penunjang_utd2 = false, utd_penunjang_rusak = false,
            suplier_penunjang = false, utd_donor = false, bpjs_monitoring_klaim = false, utd_cekal_darah = false, utd_komponen_darah = false, utd_stok_darah = false,
            utd_pemisahan_darah = false, harian_kamar = false, rincian_piutang_pasien = false, keuntungan_beri_obat_nonpiutang = false, reklasifikasi_ralan = false,
            reklasifikasi_ranap = false, utd_penyerahan_darah = false, hutang_obat = false, riwayat_obat_alkes_bhp = false, sensus_harian_poli = false, rl4a = false,
            aplicare_referensi_kamar = false, aplicare_ketersediaan_kamar = false, inacbg_klaim_baru_otomatis = false, inacbg_klaim_baru_manual = false, inacbg_coder_nik = false,
            mutasi_berkas = false, akun_piutang = false, harian_kso = false, bulanan_kso = false, harian_menejemen = false, bulanan_menejemen = false, inhealth_cek_eligibilitas = false,
            inhealth_referensi_jenpel_ruang_rawat = false, inhealth_referensi_poli = false, inhealth_referensi_faskes = false, inhealth_sjp = false, piutang_ralan = false,
            piutang_ranap = false, detail_piutang_penjab = false, lama_pelayanan_ralan = false, catatan_pasien = false, rl4b = false, rl4asebab = false, rl4bsebab = false,
            data_HAIs = false, harian_HAIs = false, bulanan_HAIs = false, hitung_bor = false, perusahaan_pasien = false, resep_dokter = false, lama_pelayanan_apotek = false,
            hitung_alos = false, detail_tindakan = false, rujukan_poli_internal = false, rekap_poli_anak = false, grafik_kunjungan_poli = false, grafik_kunjungan_perdokter = false,
            grafik_kunjungan_perpekerjaan = false, grafik_kunjungan_perpendidikan = false, grafik_kunjungan_pertahun = false, berkas_digital_perawatan = false,
            penyakit_menular_ranap = false, penyakit_menular_ralan = false, grafik_kunjungan_perbulan = false, grafik_kunjungan_pertanggal = false, grafik_kunjungan_demografi = false,
            grafik_kunjungan_statusdaftartahun = false, grafik_kunjungan_statusdaftartahun2 = false, grafik_kunjungan_statusdaftarbulan = false, grafik_kunjungan_statusdaftarbulan2 = false,
            grafik_kunjungan_statusdaftartanggal = false, grafik_kunjungan_statusdaftartanggal2 = false, grafik_kunjungan_statusbataltahun = false, grafik_kunjungan_statusbatalbulan = false,
            pcare_cek_penyakit = false, grafik_kunjungan_statusbataltanggal = false, kategori_barang = false, golongan_barang = false, pemberian_obat_pertanggal = false, penjualan_obat_pertanggal = false,
	    bpjs_rujukan_vclaim=false,skdp_bpjs=false,booking_registrasi=false,bpjs_cek_riwayat_rujukan_pcare=false,bpjs_cek_riwayat_rujukan_rs=false,bpjs_cek_rujukan_kartu_rs=false,
            bpjs_cek_tgl_rujukan=false,bpjs_cek_no_rujukan_rs=false,bpjs_cek_rujukan_kartu_pcare=false,bpjs_cek_referensi_kelas_rawat=false,bpjs_cek_referensi_prosedur=false,
            bpjs_cek_referensi_dpjp=false,bpjs_cek_referensi_dokter=false,bpjs_cek_referensi_spesialistik=false,bpjs_cek_referensi_ruang_rawat=false,bpjs_cek_referensi_cara_keluar=false,
            bpjs_cek_referensi_pasca_pulang=false,bpjs_cek_referensi_propinsi=false,bpjs_cek_referensi_kabupaten=false,bpjs_cek_referensi_kecamatan=false,permintaan_lab=false,
            permintaan_radiologi=false,selisih_tarif_bpjs=false,edit_data_kematian=false,bridging_jamkesda=false,masuk_pindah_pulang_inap=false,masuk_pindah_inap=false,
            jumlah_macam_diet=false,jumlah_porsi_diet=false,status_gizi=false,gizi_buruk=false,master_faskes,setstatusralan=false,telusurpasien=false,sisrute_rujukan_keluar=false,sisrute_rujukan_masuk=false,
            sisrute_referensi_diagnosa=false,sisrute_referensi_alasanrujuk=false,sisrute_referensi_faskes=false,barang_cssd=false,status_pulang_inap=false,data_persalinan=false,data_ponek=false,
            reg_boking_kasir=false,bahasa_pasien=false,suku_bangsa=false,harian_hais_inap=false,harian_hais_jalan=false,bulanan_hais_inap=false,bulanan_hais_jalan=false,ringkasan_pulang_ranap=false,
            laporan_farmasi=false,master_masalah_keperawatan=false,penilaian_awal_keperawatan_ralan=false,master_triase_skala1=false,master_triase_skala2=false,
            master_triase_skala3=false,master_triase_skala4=false,master_triase_skala5=false, data_triase_igd = false, master_triase_pemeriksaan = false, master_triase_macamkasus = false, master_cara_bayar = false,
            status_kerja_dokter = false, pasien_corona = false, diagnosa_pasien_corona = false, perawatan_pasien_corona = false, inacbg_klaim_baru_manual2 = false, assesmen_gizi_harian = false, assesmen_gizi_ulang = false,
            tombol_nota_billing = false, tombol_simpan_hasil_rad = false, monev_asuhan_gizi = false, inacbg_klaim_raza = false, pengajuan_klaim_inacbg_raza = false,
            copy_pemeriksaan_dokter_kepetugas_ralan = false, jkn_belum_diproses_klaim = false, input_kode_icd = false, kendali_Mutu_kendali_Biaya_INACBG = false, dashboard_eResep = false, bpjs_sep_internal = false,
            kemenkes_sitt = false, rencana_kontrol_jkn = false, spri_jkn = false, hapus_sep = false, penilaian_awal_medis_ralan_kebidanan = false, penilaian_awal_keperawatan_kebidanan = false,
            ikhtisar_perawatan_hiv = false, survey_kepuasan = false, kemenkes_kanker = false, aktivasi_bridging = false, operator_antrian = false, penilaian_awal_medis_ralan_tht = false,
            rekam_psikologis = false, penilaian_pasien_geriatri = false, penilaian_awal_medis_ralan_mata = false, surat_sakit = false, surat_keterangan_kir_mcu = false, asesmen_medik_dewasa_ranap = false;

    public static void setData(String user, String pass) {
        try {
            ps = koneksi.prepareStatement("select * from admin where usere=AES_ENCRYPT(?,'nur') and passworde=AES_ENCRYPT(?,'windi')");
            ps2 = koneksi.prepareStatement("select * from user u "
                    + "left join petugas p on p.nip = AES_DECRYPT(u.id_user,'nur') or p.user_id = AES_DECRYPT(u.id_user,'nur') "
                    + "left join dokter d on d.kd_dokter = AES_DECRYPT(u.id_user, 'nur') where "
                    + "u.id_user=AES_ENCRYPT(?,'nur') and u.password=AES_ENCRYPT(?,'windi')");
            try {
                ps.setString(1, user);
                ps.setString(2, pass);
                rs = ps.executeQuery();
                rs.last();

                ps2.setString(1, user);
                ps2.setString(2, pass);
                rs2 = ps2.executeQuery();
                rs2.last();

                akses.jml1 = rs.getRow();
                akses.jml2 = rs2.getRow();

                if (user.equals("admin") && pass.equals("satu")) {
                    akses.kode = "Admin Utama";
                    akses.penyakit = true;
                    akses.obat_penyakit = true;
                    akses.dokter = true;
                    akses.jadwal_praktek = true;
                    akses.petugas = true;
                    akses.pasien = true;
                    akses.registrasi = true;
                    akses.tindakan_ralan = true;
                    akses.kamar_inap = true;
                    akses.tindakan_ranap = true;
                    akses.operasi = true;
                    akses.rujukan_keluar = true;
                    akses.rujukan_masuk = true;
                    akses.beri_obat = true;
                    akses.resep_pulang = true;
                    akses.pasien_meninggal = true;
                    akses.diet_pasien = true;
                    akses.kelahiran_bayi = true;
                    akses.periksa_lab = true;
                    akses.periksa_radiologi = true;
                    akses.kasir_ralan = true;
                    akses.deposit_pasien = true;
                    akses.piutang_pasien = true;
                    akses.peminjaman_berkas = true;
                    akses.barcode = true;
                    akses.presensi_harian = true;
                    akses.presensi_bulanan = true;
                    akses.pegawai_admin = true;
                    akses.pegawai_user = true;
                    akses.suplier = true;
                    akses.satuan_barang = true;
                    akses.konversi_satuan = true;
                    akses.jenis_barang = true;
                    akses.obat = true;
                    akses.stok_opname_obat = true;
                    akses.stok_obat_pasien = true;
                    akses.pengadaan_obat = true;
                    akses.pemesanan_obat = true;
                    akses.penjualan_obat = true;
                    akses.piutang_obat = true;
                    akses.retur_ke_suplier = true;
                    akses.retur_dari_pembeli = true;
                    akses.retur_obat_ranap = true;
                    akses.retur_piutang_pasien = true;
                    akses.keuntungan_penjualan = true;
                    akses.keuntungan_beri_obat = true;
                    akses.sirkulasi_obat = true;
                    akses.ipsrs_barang = true;
                    akses.ipsrs_jenis_barang = true;
                    akses.ipsrs_pengadaan_barang = true;
                    akses.ipsrs_stok_keluar = true;
                    akses.ipsrs_rekap_pengadaan = true;
                    akses.ipsrs_rekap_stok_keluar = true;
                    akses.ipsrs_pengeluaran_harian = true;
                    akses.inventaris_jenis = true;
                    akses.inventaris_kategori = true;
                    akses.inventaris_merk = true;
                    akses.inventaris_ruang = true;
                    akses.inventaris_produsen = true;
                    akses.inventaris_koleksi = true;
                    akses.inventaris_inventaris = true;
                    akses.inventaris_sirkulasi = true;
                    akses.parkir_jenis = true;
                    akses.parkir_in = true;
                    akses.parkir_out = true;
                    akses.parkir_rekap_harian = true;
                    akses.parkir_rekap_bulanan = true;
                    akses.informasi_kamar = true;
                    akses.harian_tindakan_poli = true;
                    akses.obat_per_poli = true;
                    akses.obat_per_kamar = true;
                    akses.obat_per_dokter_ralan = true;
                    akses.obat_per_dokter_ranap = true;
                    akses.harian_dokter = true;
                    akses.bulanan_dokter = true;
                    akses.harian_paramedis = true;
                    akses.bulanan_paramedis = true;
                    akses.pembayaran_ralan = true;
                    akses.pembayaran_ranap = true;
                    akses.rekap_pembayaran_ralan = true;
                    akses.rekap_pembayaran_ranap = true;
                    akses.tagihan_masuk = true;
                    akses.tambahan_biaya = true;
                    akses.potongan_biaya = true;
                    akses.resep_obat = true;
                    akses.resume_pasien = true;
                    akses.penyakit_ralan = true;
                    akses.penyakit_ranap = true;
                    akses.kamar = true;
                    akses.tarif_ralan = true;
                    akses.tarif_ranap = true;
                    akses.tarif_lab = true;
                    akses.tarif_radiologi = true;
                    akses.tarif_operasi = true;
                    akses.akun_rekening = true;
                    akses.rekening_tahun = true;
                    akses.posting_jurnal = true;
                    akses.buku_besar = true;
                    akses.cashflow = true;
                    akses.keuangan = true;
                    akses.pengeluaran = true;
                    akses.setup_pjlab = true;
                    akses.setup_otolokasi = true;
                    akses.setup_jam_kamin = true;
                    akses.setup_embalase = true;
                    akses.tracer_login = true;
                    akses.display = true;
                    akses.set_harga_obat = true;
                    akses.set_penggunaan_tarif = true;
                    akses.set_oto_ralan = true;
                    akses.biaya_harian = true;
                    akses.biaya_masuk_sekali = true;
                    akses.set_no_rm = true;
                    akses.billing_ralan = true;
                    akses.billing_ranap = true;
                    akses.jm_ranap_dokter = true;
                    akses.igd = true;
                    akses.barcoderalan = true;
                    akses.barcoderanap = true;
                    akses.set_harga_obat_ralan = true;
                    akses.set_harga_obat_ranap = true;
                    akses.penyakit_pd3i = true;
                    akses.surveilans_pd3i = true;
                    akses.surveilans_ralan = true;
                    akses.diagnosa_pasien = true;
                    akses.admin = true;
                    akses.user = true;
                    akses.vakum = true;
                    akses.aplikasi = true;
                    akses.surveilans_ranap = true;
                    akses.pny_takmenular_ranap = true;
                    akses.pny_takmenular_ralan = true;
                    akses.kunjungan_ralan = true;
                    akses.rl32 = true;
                    akses.rl33 = true;
                    akses.rl37 = true;
                    akses.rl38 = true;
                    akses.harian_tindakan_dokter = true;
                    akses.sms = true;
                    akses.sidikjari = true;
                    akses.jam_masuk = true;
                    akses.jadwal_pegawai = true;
                    akses.parkir_barcode = true;
                    akses.set_nota = true;
                    akses.dpjp_ranap = true;
                    akses.mutasi_barang = true;
                    akses.rl34 = true;
                    akses.rl36 = true;
                    akses.fee_visit_dokter = true;
                    akses.fee_bacaan_ekg = true;
                    akses.fee_rujukan_rontgen = true;
                    akses.fee_rujukan_ranap = true;
                    akses.fee_ralan = true;
                    akses.akun_bayar = true;
                    akses.bayar_pemesanan_obat = true;
                    akses.obat_per_dokter_peresep = true;
                    akses.pemasukan_lain = true;
                    akses.pengaturan_rekening = true;
                    akses.closing_kasir = true;
                    akses.keterlambatan_presensi = true;
                    akses.set_harga_kamar = true;
                    akses.rekap_per_shift = true;
                    akses.bpjs_cek_nik = true;
                    akses.bpjs_cek_kartu = true;
                    akses.obat_per_cara_bayar = true;
                    akses.kunjungan_ranap = true;
                    akses.bayar_piutang = true;
                    akses.payment_point = true;
                    akses.bpjs_cek_nomor_rujukan = true;
                    akses.icd9 = true;
                    akses.darurat_stok = true;
                    akses.retensi_rm = true;
                    akses.temporary_presensi = true;
                    akses.jurnal_harian = true;
                    akses.sirkulasi_obat2 = true;
                    akses.edit_registrasi = true;
                    akses.bpjs_referensi_diagnosa = true;
                    akses.bpjs_referensi_poli = true;
                    akses.industrifarmasi = true;
                    akses.harian_js = true;
                    akses.bulanan_js = true;
                    akses.harian_paket_bhp = true;
                    akses.bulanan_paket_bhp = true;
                    akses.piutang_pasien2 = true;
                    akses.bpjs_referensi_faskes = true;
                    akses.bpjs_sep = true;
                    akses.pengambilan_utd = true;
                    akses.tarif_utd = true;
                    akses.pengambilan_utd2 = true;
                    akses.utd_medis_rusak = true;
                    akses.pengambilan_penunjang_utd = true;
                    akses.pengambilan_penunjang_utd2 = true;
                    akses.utd_penunjang_rusak = true;
                    akses.suplier_penunjang = true;
                    akses.utd_donor = true;
                    akses.bpjs_monitoring_klaim = true;
                    akses.utd_cekal_darah = true;
                    akses.utd_komponen_darah = true;
                    akses.utd_stok_darah = true;
                    akses.utd_pemisahan_darah = true;
                    akses.harian_kamar = true;
                    akses.rincian_piutang_pasien = true;
                    akses.keuntungan_beri_obat_nonpiutang = true;
                    akses.reklasifikasi_ralan = true;
                    akses.reklasifikasi_ranap = true;
                    akses.utd_penyerahan_darah = true;
                    akses.hutang_obat = true;
                    akses.riwayat_obat_alkes_bhp = true;
                    akses.sensus_harian_poli = true;
                    akses.rl4a = true;
                    akses.aplicare_referensi_kamar = true;
                    akses.aplicare_ketersediaan_kamar = true;
                    akses.inacbg_klaim_baru_otomatis = true;
                    akses.inacbg_klaim_baru_manual = true;
                    akses.inacbg_coder_nik = true;
                    akses.mutasi_berkas = true;
                    akses.akun_piutang = true;
                    akses.harian_kso = true;
                    akses.bulanan_kso = true;
                    akses.harian_menejemen = true;
                    akses.bulanan_menejemen = true;
                    akses.inhealth_cek_eligibilitas = true;
                    akses.inhealth_referensi_jenpel_ruang_rawat = true;
                    akses.inhealth_referensi_poli = true;
                    akses.inhealth_referensi_faskes = true;
                    akses.inhealth_sjp = true;
                    akses.piutang_ralan = true;
                    akses.piutang_ranap = true;
                    akses.detail_piutang_penjab = true;
                    akses.lama_pelayanan_ralan = true;
                    akses.catatan_pasien = true;
                    akses.rl4b = true;
                    akses.rl4asebab = true;
                    akses.rl4bsebab = true;
                    akses.namauser = "Admin Utama";
                    akses.data_HAIs = true;
                    akses.harian_HAIs = true;
                    akses.bulanan_HAIs = true;
                    akses.hitung_bor = true;
                    akses.perusahaan_pasien = true;
                    akses.resep_dokter = true;
                    akses.lama_pelayanan_apotek = true;
                    akses.hitung_alos = true;
                    akses.detail_tindakan = true;
                    akses.rujukan_poli_internal = true;
                    akses.rekap_poli_anak = true;
                    akses.grafik_kunjungan_poli = true;
                    akses.grafik_kunjungan_perdokter = true;
                    akses.grafik_kunjungan_perpekerjaan = true;
                    akses.grafik_kunjungan_perpendidikan = true;
                    akses.grafik_kunjungan_pertahun = true;
                    akses.berkas_digital_perawatan = true;
                    akses.penyakit_menular_ranap = true;
                    akses.penyakit_menular_ralan = true;
                    akses.grafik_kunjungan_perbulan = true;
                    akses.grafik_kunjungan_pertanggal = true;
                    akses.grafik_kunjungan_demografi = true;
                    akses.grafik_kunjungan_statusdaftartahun = true;
                    akses.grafik_kunjungan_statusdaftartahun2 = true;
                    akses.grafik_kunjungan_statusdaftarbulan = true;
                    akses.grafik_kunjungan_statusdaftarbulan2 = true;
                    akses.grafik_kunjungan_statusdaftartanggal = true;
                    akses.grafik_kunjungan_statusdaftartanggal2 = true;
                    akses.grafik_kunjungan_statusbataltahun = true;
                    akses.grafik_kunjungan_statusbatalbulan = true;
                    akses.pcare_cek_penyakit = true;
                    akses.grafik_kunjungan_statusbataltanggal = true;
                    akses.kategori_barang = true;
                    akses.golongan_barang = true;
                    akses.pemberian_obat_pertanggal = true;
                    akses.penjualan_obat_pertanggal = true;
		    akses.bpjs_rujukan_vclaim=true;
                    akses.skdp_bpjs=true;
                    akses.booking_registrasi=true;
                    akses.bpjs_cek_riwayat_rujukan_pcare=true;
                    akses.bpjs_cek_riwayat_rujukan_rs=true;
                    akses.bpjs_cek_rujukan_kartu_rs=true;
                    akses.bpjs_cek_tgl_rujukan=true;
                    akses.bpjs_cek_no_rujukan_rs=true;
                    akses.bpjs_cek_rujukan_kartu_pcare=true;
                    akses.bpjs_cek_referensi_kelas_rawat=true;
                    akses.bpjs_cek_referensi_prosedur=true;
                    akses.bpjs_cek_referensi_dpjp=true;
                    akses.bpjs_cek_referensi_dokter=true;
                    akses.bpjs_cek_referensi_spesialistik=true;
                    akses.bpjs_cek_referensi_ruang_rawat=true;
                    akses.bpjs_cek_referensi_cara_keluar=true;
                    akses.bpjs_cek_referensi_pasca_pulang=true;
                    akses.bpjs_cek_referensi_propinsi=true;
                    akses.bpjs_cek_referensi_kabupaten=true;
                    akses.bpjs_cek_referensi_kecamatan=true;
                    akses.permintaan_lab=true;
                    akses.permintaan_radiologi=true;
                    akses.selisih_tarif_bpjs=true;
                    akses.edit_data_kematian=true;
                    akses.bridging_jamkesda=true;
                    akses.masuk_pindah_pulang_inap=true;
                    akses.masuk_pindah_inap=true;
                    akses.jumlah_macam_diet=true;
                    akses.jumlah_porsi_diet=true;
                    akses.status_gizi=true;
                    akses.gizi_buruk=true;
                    akses.master_faskes=true;
                    akses.setstatusralan=true;
                    akses.telusurpasien=true;
                    akses.sisrute_rujukan_keluar=true;
                    akses.sisrute_rujukan_masuk=true;
                    akses.sisrute_referensi_diagnosa=true;
                    akses.sisrute_referensi_alasanrujuk=true;
                    akses.sisrute_referensi_faskes=true;
                    akses.barang_cssd=true;
                    akses.status_pulang_inap=true;
                    akses.data_persalinan=true;
                    akses.data_ponek=true;
                    akses.reg_boking_kasir=true;
                    akses.bahasa_pasien=true;
                    akses.suku_bangsa=true;
                    akses.harian_hais_inap=true;
                    akses.harian_hais_jalan=true;
                    akses.bulanan_hais_inap=true;
                    akses.bulanan_hais_jalan=true;
                    akses.ringkasan_pulang_ranap=true;
                    akses.laporan_farmasi=true;
                    akses.master_masalah_keperawatan=true;
                    akses.penilaian_awal_keperawatan_ralan=true;
                    akses.master_triase_skala1=true;
                    akses.master_triase_skala2=true;                    
                    akses.master_triase_skala3=true;
                    akses.master_triase_skala4=true;
                    akses.master_triase_skala5=true;
                    akses.data_triase_igd=true;
                    akses.master_triase_pemeriksaan=true;
                    akses.master_triase_macamkasus=true;
                    akses.master_cara_bayar=true;
                    akses.status_kerja_dokter=true;
                    akses.pasien_corona=true;
                    akses.diagnosa_pasien_corona=true;
                    akses.perawatan_pasien_corona=true;
                    akses.inacbg_klaim_baru_manual2=true;
                    akses.assesmen_gizi_harian=true;
                    akses.assesmen_gizi_ulang=true;
                    akses.tombol_nota_billing=true;
                    akses.tombol_simpan_hasil_rad=true;
                    akses.monev_asuhan_gizi=true;
                    akses.inacbg_klaim_raza=true;
                    akses.pengajuan_klaim_inacbg_raza=true;
                    akses.copy_pemeriksaan_dokter_kepetugas_ralan=true;
                    akses.jkn_belum_diproses_klaim=true;
                    akses.input_kode_icd=true;
                    akses.kendali_Mutu_kendali_Biaya_INACBG=true;
                    akses.dashboard_eResep=true;
                    akses.bpjs_sep_internal=true;
                    akses.kemenkes_sitt=true;
                    akses.rencana_kontrol_jkn=true;
                    akses.spri_jkn=true;
                    akses.hapus_sep=true;
                    akses.penilaian_awal_medis_ralan_kebidanan=true;
                    akses.penilaian_awal_keperawatan_kebidanan=true;
                    akses.ikhtisar_perawatan_hiv=true;
                    akses.survey_kepuasan=true;
                    akses.kemenkes_kanker=true;
                    akses.aktivasi_bridging=true;
                    akses.operator_antrian=true;
                    akses.penilaian_awal_medis_ralan_tht=true;
                    akses.rekam_psikologis=true;
                    akses.penilaian_pasien_geriatri=true;
                    akses.penilaian_awal_medis_ralan_mata=true;
                    akses.surat_sakit=true;
                    akses.surat_keterangan_kir_mcu=true;
                    akses.asesmen_medik_dewasa_ranap=true;
                } else if (rs.getRow() >= 1) {
                    akses.kode = "Admin Utama";
                    akses.penyakit = true;
                    akses.obat_penyakit = true;
                    akses.dokter = true;
                    akses.jadwal_praktek = true;
                    akses.petugas = true;
                    akses.pasien = true;
                    akses.registrasi = true;
                    akses.tindakan_ralan = true;
                    akses.kamar_inap = true;
                    akses.tindakan_ranap = true;
                    akses.operasi = true;
                    akses.rujukan_keluar = true;
                    akses.rujukan_masuk = true;
                    akses.beri_obat = true;
                    akses.resep_pulang = true;
                    akses.pasien_meninggal = true;
                    akses.diet_pasien = true;
                    akses.kelahiran_bayi = true;
                    akses.periksa_lab = true;
                    akses.periksa_radiologi = true;
                    akses.kasir_ralan = true;
                    akses.deposit_pasien = true;
                    akses.piutang_pasien = true;
                    akses.peminjaman_berkas = true;
                    akses.barcode = true;
                    akses.presensi_harian = true;
                    akses.presensi_bulanan = true;
                    akses.pegawai_admin = true;
                    akses.pegawai_user = true;
                    akses.suplier = true;
                    akses.satuan_barang = true;
                    akses.konversi_satuan = true;
                    akses.jenis_barang = true;
                    akses.obat = true;
                    akses.stok_opname_obat = true;
                    akses.stok_obat_pasien = true;
                    akses.pengadaan_obat = true;
                    akses.pemesanan_obat = true;
                    akses.penjualan_obat = true;
                    akses.piutang_obat = true;
                    akses.retur_ke_suplier = true;
                    akses.retur_dari_pembeli = true;
                    akses.retur_obat_ranap = true;
                    akses.retur_piutang_pasien = true;
                    akses.keuntungan_penjualan = true;
                    akses.keuntungan_beri_obat = true;
                    akses.sirkulasi_obat = true;
                    akses.ipsrs_barang = true;
                    akses.ipsrs_pengadaan_barang = true;
                    akses.ipsrs_stok_keluar = true;
                    akses.ipsrs_rekap_pengadaan = true;
                    akses.ipsrs_rekap_stok_keluar = true;
                    akses.ipsrs_pengeluaran_harian = true;
                    akses.ipsrs_jenis_barang = true;
                    akses.inventaris_jenis = true;
                    akses.inventaris_kategori = true;
                    akses.inventaris_merk = true;
                    akses.inventaris_ruang = true;
                    akses.inventaris_produsen = true;
                    akses.inventaris_koleksi = true;
                    akses.inventaris_inventaris = true;
                    akses.inventaris_sirkulasi = true;
                    akses.parkir_jenis = true;
                    akses.parkir_in = true;
                    akses.parkir_out = true;
                    akses.parkir_rekap_harian = true;
                    akses.parkir_rekap_bulanan = true;
                    akses.informasi_kamar = true;
                    akses.harian_tindakan_poli = true;
                    akses.obat_per_poli = true;
                    akses.obat_per_kamar = true;
                    akses.obat_per_dokter_ralan = true;
                    akses.obat_per_dokter_ranap = true;
                    akses.harian_dokter = true;
                    akses.bulanan_dokter = true;
                    akses.harian_paramedis = true;
                    akses.bulanan_paramedis = true;
                    akses.pembayaran_ralan = true;
                    akses.pembayaran_ranap = true;
                    akses.rekap_pembayaran_ralan = true;
                    akses.rekap_pembayaran_ranap = true;
                    akses.tagihan_masuk = true;
                    akses.tambahan_biaya = true;
                    akses.potongan_biaya = true;
                    akses.resep_obat = true;
                    akses.resume_pasien = true;
                    akses.penyakit_ralan = true;
                    akses.penyakit_ranap = true;
                    akses.kamar = true;
                    akses.tarif_ralan = true;
                    akses.tarif_ranap = true;
                    akses.tarif_lab = true;
                    akses.tarif_radiologi = true;
                    akses.tarif_operasi = true;
                    akses.akun_rekening = true;
                    akses.rekening_tahun = true;
                    akses.posting_jurnal = true;
                    akses.buku_besar = true;
                    akses.cashflow = true;
                    akses.keuangan = true;
                    akses.pengeluaran = true;
                    akses.setup_pjlab = true;
                    akses.setup_otolokasi = true;
                    akses.setup_jam_kamin = true;
                    akses.setup_embalase = true;
                    akses.tracer_login = true;
                    akses.display = true;
                    akses.set_harga_obat = true;
                    akses.set_penggunaan_tarif = true;
                    akses.set_oto_ralan = true;
                    akses.biaya_harian = true;
                    akses.biaya_masuk_sekali = true;
                    akses.set_no_rm = true;
                    akses.billing_ralan = true;
                    akses.billing_ranap = true;
                    akses.jm_ranap_dokter = true;
                    akses.igd = true;
                    akses.barcoderalan = true;
                    akses.barcoderanap = true;
                    akses.set_harga_obat_ralan = true;
                    akses.set_harga_obat_ranap = true;
                    akses.penyakit_pd3i = true;
                    akses.surveilans_pd3i = true;
                    akses.surveilans_ralan = true;
                    akses.diagnosa_pasien = true;
                    akses.admin = true;
                    akses.user = true;
                    akses.vakum = true;
                    akses.aplikasi = true;
                    akses.surveilans_ranap = true;
                    akses.pny_takmenular_ranap = true;
                    akses.pny_takmenular_ralan = true;
                    akses.kunjungan_ralan = true;
                    akses.rl32 = true;
                    akses.rl33 = true;
                    akses.rl37 = true;
                    akses.rl38 = true;
                    akses.harian_tindakan_dokter = true;
                    akses.sms = true;
                    akses.sidikjari = true;
                    akses.jam_masuk = true;
                    akses.jadwal_pegawai = true;
                    akses.parkir_barcode = true;
                    akses.set_nota = true;
                    akses.dpjp_ranap = true;
                    akses.mutasi_barang = true;
                    akses.rl34 = true;
                    akses.rl36 = true;
                    akses.fee_visit_dokter = true;
                    akses.fee_bacaan_ekg = true;
                    akses.fee_rujukan_rontgen = true;
                    akses.fee_rujukan_ranap = true;
                    akses.fee_ralan = true;
                    akses.akun_bayar = true;
                    akses.bayar_pemesanan_obat = true;
                    akses.obat_per_dokter_peresep = true;
                    akses.pemasukan_lain = true;
                    akses.pengaturan_rekening = true;
                    akses.closing_kasir = true;
                    akses.keterlambatan_presensi = true;
                    akses.set_harga_kamar = true;
                    akses.rekap_per_shift = true;
                    akses.bpjs_cek_nik = true;
                    akses.bpjs_cek_kartu = true;
                    akses.obat_per_cara_bayar = true;
                    akses.kunjungan_ranap = true;
                    akses.bayar_piutang = true;
                    akses.payment_point = true;
                    akses.bpjs_cek_nomor_rujukan = true;
                    akses.icd9 = true;
                    akses.darurat_stok = true;
                    akses.retensi_rm = true;
                    akses.temporary_presensi = true;
                    akses.jurnal_harian = true;
                    akses.sirkulasi_obat2 = true;
                    akses.edit_registrasi = true;
                    akses.bpjs_referensi_diagnosa = true;
                    akses.bpjs_referensi_poli = true;
                    akses.industrifarmasi = true;
                    akses.harian_js = true;
                    akses.bulanan_js = true;
                    akses.harian_paket_bhp = true;
                    akses.bulanan_paket_bhp = true;
                    akses.piutang_pasien2 = true;
                    akses.bpjs_referensi_faskes = true;
                    akses.bpjs_sep = true;
                    akses.pengambilan_utd = true;
                    akses.tarif_utd = true;
                    akses.pengambilan_utd2 = true;
                    akses.utd_medis_rusak = true;
                    akses.pengambilan_penunjang_utd = true;
                    akses.pengambilan_penunjang_utd2 = true;
                    akses.utd_penunjang_rusak = true;
                    akses.suplier_penunjang = true;
                    akses.utd_donor = true;
                    akses.bpjs_monitoring_klaim = true;
                    akses.utd_cekal_darah = true;
                    akses.utd_komponen_darah = true;
                    akses.utd_stok_darah = true;
                    akses.utd_pemisahan_darah = true;
                    akses.harian_kamar = true;
                    akses.rincian_piutang_pasien = true;
                    akses.keuntungan_beri_obat_nonpiutang = true;
                    akses.reklasifikasi_ralan = true;
                    akses.reklasifikasi_ranap = true;
                    akses.utd_penyerahan_darah = true;
                    akses.hutang_obat = true;
                    akses.riwayat_obat_alkes_bhp = true;
                    akses.sensus_harian_poli = true;
                    akses.rl4a = true;
                    akses.aplicare_referensi_kamar = true;
                    akses.aplicare_ketersediaan_kamar = true;
                    akses.inacbg_klaim_baru_otomatis = true;
                    akses.inacbg_klaim_baru_manual = true;
                    akses.inacbg_coder_nik = true;
                    akses.mutasi_berkas = true;
                    akses.akun_piutang = true;
                    akses.harian_kso = true;
                    akses.bulanan_kso = true;
                    akses.harian_menejemen = true;
                    akses.bulanan_menejemen = true;
                    akses.inhealth_cek_eligibilitas = true;
                    akses.inhealth_referensi_jenpel_ruang_rawat = true;
                    akses.inhealth_referensi_poli = true;
                    akses.inhealth_referensi_faskes = true;
                    akses.inhealth_sjp = true;
                    akses.piutang_ralan = true;
                    akses.piutang_ranap = true;
                    akses.detail_piutang_penjab = true;
                    akses.lama_pelayanan_ralan = true;
                    akses.catatan_pasien = true;
                    akses.rl4b = true;
                    akses.rl4asebab = true;
                    akses.rl4bsebab = true;
                    akses.data_HAIs = true;
                    akses.harian_HAIs = true;
                    akses.bulanan_HAIs = true;
                    akses.hitung_bor = true;
                    akses.perusahaan_pasien = true;
                    akses.resep_dokter = true;
                    akses.lama_pelayanan_apotek = true;
                    akses.hitung_alos = true;
                    akses.detail_tindakan = true;
                    akses.rujukan_poli_internal = true;
                    akses.rekap_poli_anak = true;
                    akses.grafik_kunjungan_poli = true;
                    akses.grafik_kunjungan_perdokter = true;
                    akses.grafik_kunjungan_perpekerjaan = true;
                    akses.grafik_kunjungan_perpendidikan = true;
                    akses.grafik_kunjungan_pertahun = true;
                    akses.berkas_digital_perawatan = true;
                    akses.penyakit_menular_ranap = true;
                    akses.penyakit_menular_ralan = true;
                    akses.grafik_kunjungan_perbulan = true;
                    akses.grafik_kunjungan_pertanggal = true;
                    akses.grafik_kunjungan_demografi = true;
                    akses.grafik_kunjungan_statusdaftartahun = true;
                    akses.grafik_kunjungan_statusdaftartahun2 = true;
                    akses.grafik_kunjungan_statusdaftarbulan = true;
                    akses.grafik_kunjungan_statusdaftarbulan2 = true;
                    akses.grafik_kunjungan_statusdaftartanggal = true;
                    akses.grafik_kunjungan_statusdaftartanggal2 = true;
                    akses.grafik_kunjungan_statusbataltahun = true;
                    akses.grafik_kunjungan_statusbatalbulan = true;
                    akses.pcare_cek_penyakit = true;
                    akses.grafik_kunjungan_statusbataltanggal = true;
                    akses.kategori_barang = true;
                    akses.golongan_barang = true;
                    akses.pemberian_obat_pertanggal = true;
                    akses.penjualan_obat_pertanggal = true;
                    akses.bpjs_rujukan_vclaim = true;
                    akses.skdp_bpjs = true;
                    akses.booking_registrasi = true;
                    akses.bpjs_cek_riwayat_rujukan_pcare = true;
                    akses.bpjs_cek_riwayat_rujukan_rs = true;
                    akses.bpjs_cek_rujukan_kartu_rs = true;
                    akses.bpjs_cek_tgl_rujukan = true;
                    akses.bpjs_cek_no_rujukan_rs = true;
                    akses.bpjs_cek_rujukan_kartu_pcare = true;
                    akses.bpjs_cek_referensi_kelas_rawat = true;
                    akses.bpjs_cek_referensi_prosedur = true;
                    akses.bpjs_cek_referensi_dpjp = true;
                    akses.bpjs_cek_referensi_dokter = true;
                    akses.bpjs_cek_referensi_spesialistik = true;
                    akses.bpjs_cek_referensi_ruang_rawat = true;
                    akses.bpjs_cek_referensi_cara_keluar = true;
                    akses.bpjs_cek_referensi_pasca_pulang = true;
                    akses.bpjs_cek_referensi_propinsi = true;
                    akses.bpjs_cek_referensi_kabupaten = true;
                    akses.bpjs_cek_referensi_kecamatan = true;
                    akses.permintaan_lab = true;
                    akses.permintaan_radiologi = true;
                    akses.selisih_tarif_bpjs = true;
                    akses.edit_data_kematian = true;
                    akses.bridging_jamkesda = true;
                    akses.masuk_pindah_pulang_inap = true;
                    akses.masuk_pindah_inap = true;
                    akses.jumlah_macam_diet = true;
                    akses.jumlah_porsi_diet = true;
                    akses.status_gizi = true;
                    akses.gizi_buruk = true;
                    akses.master_faskes = true;
                    akses.setstatusralan = true;
                    akses.telusurpasien = true;
                    akses.sisrute_rujukan_keluar = true;
                    akses.sisrute_rujukan_masuk = true;
                    akses.sisrute_referensi_diagnosa = true;
                    akses.sisrute_referensi_alasanrujuk = true;
                    akses.sisrute_referensi_faskes = true;
                    akses.barang_cssd = true;
                    akses.status_pulang_inap = true;
                    akses.data_persalinan = true;
                    akses.data_ponek = true;
                    akses.reg_boking_kasir = true;
                    akses.bahasa_pasien = true;
                    akses.suku_bangsa = true;
                    akses.harian_hais_inap = true;
                    akses.harian_hais_jalan = true;
                    akses.bulanan_hais_inap = true;
                    akses.bulanan_hais_jalan = true;
                    akses.ringkasan_pulang_ranap = true;
                    akses.laporan_farmasi = true;
                    akses.master_masalah_keperawatan = true;
                    akses.penilaian_awal_keperawatan_ralan = true;
                    akses.master_triase_skala1 = true;
                    akses.master_triase_skala2 = true;
                    akses.master_triase_skala3 = true;
                    akses.master_triase_skala4 = true;
                    akses.master_triase_skala5 = true;
                    akses.data_triase_igd = true;
                    akses.master_triase_pemeriksaan = true;
                    akses.master_triase_macamkasus = true;
                    akses.master_cara_bayar = true;
                    akses.status_kerja_dokter = true;
                    akses.pasien_corona = true;
                    akses.diagnosa_pasien_corona = true;
                    akses.perawatan_pasien_corona = true;
                    akses.inacbg_klaim_baru_manual2 = true;
                    akses.assesmen_gizi_harian = true;
                    akses.assesmen_gizi_ulang = true;
                    akses.tombol_nota_billing = true;
                    akses.tombol_simpan_hasil_rad = true;
                    akses.monev_asuhan_gizi = true;
                    akses.inacbg_klaim_raza = true;
                    akses.pengajuan_klaim_inacbg_raza = true;
                    akses.copy_pemeriksaan_dokter_kepetugas_ralan = true;
                    akses.jkn_belum_diproses_klaim = true;
                    akses.input_kode_icd = true;
                    akses.kendali_Mutu_kendali_Biaya_INACBG = true;
                    akses.dashboard_eResep = true;
                    akses.bpjs_sep_internal = true;
                    akses.kemenkes_sitt = true;
                    akses.rencana_kontrol_jkn = true;
                    akses.spri_jkn = true;
                    akses.hapus_sep = true;
                    akses.penilaian_awal_medis_ralan_kebidanan = true;
                    akses.penilaian_awal_keperawatan_kebidanan = true;
                    akses.ikhtisar_perawatan_hiv = true;
                    akses.survey_kepuasan = true;
                    akses.kemenkes_kanker = true;
                    akses.aktivasi_bridging = true;
                    akses.operator_antrian = true;
                    akses.penilaian_awal_medis_ralan_tht = true;
                    akses.rekam_psikologis = true;
                    akses.penilaian_pasien_geriatri = true;
                    akses.penilaian_awal_medis_ralan_mata = true;
                    akses.surat_sakit = true;
                    akses.surat_keterangan_kir_mcu = true;
                    akses.asesmen_medik_dewasa_ranap = true;
                } else if (rs2.getRow() >= 1) {
                    rs2.beforeFirst();
                    rs2.next();
                    akses.kode = user;
                    akses.penyakit = rs2.getBoolean("penyakit");
                    akses.obat_penyakit = rs2.getBoolean("obat_penyakit");
                    akses.dokter = rs2.getBoolean("dokter");
                    akses.jadwal_praktek = rs2.getBoolean("jadwal_praktek");
                    akses.petugas = rs2.getBoolean("petugas");
                    akses.pasien = rs2.getBoolean("pasien");
                    akses.registrasi = rs2.getBoolean("registrasi");
                    akses.tindakan_ralan = rs2.getBoolean("tindakan_ralan");
                    akses.kamar_inap = rs2.getBoolean("kamar_inap");
                    akses.tindakan_ranap = rs2.getBoolean("tindakan_ranap");
                    akses.operasi = rs2.getBoolean("operasi");
                    akses.rujukan_keluar = rs2.getBoolean("rujukan_keluar");
                    akses.rujukan_masuk = rs2.getBoolean("rujukan_masuk");
                    akses.beri_obat = rs2.getBoolean("beri_obat");
                    akses.resep_pulang = rs2.getBoolean("resep_pulang");
                    akses.pasien_meninggal = rs2.getBoolean("pasien_meninggal");
                    akses.diet_pasien = rs2.getBoolean("diet_pasien");
                    akses.kelahiran_bayi = rs2.getBoolean("kelahiran_bayi");
                    akses.periksa_lab = rs2.getBoolean("periksa_lab");
                    akses.periksa_radiologi = rs2.getBoolean("periksa_radiologi");
                    akses.kasir_ralan = rs2.getBoolean("kasir_ralan");
                    akses.deposit_pasien = rs2.getBoolean("deposit_pasien");
                    akses.piutang_pasien = rs2.getBoolean("piutang_pasien");
                    akses.peminjaman_berkas = rs2.getBoolean("peminjaman_berkas");
                    akses.barcode = rs2.getBoolean("barcode");
                    akses.presensi_harian = rs2.getBoolean("presensi_harian");
                    akses.presensi_bulanan = rs2.getBoolean("presensi_bulanan");
                    akses.pegawai_admin = rs2.getBoolean("pegawai_admin");
                    akses.pegawai_user = rs2.getBoolean("pegawai_user");
                    akses.suplier = rs2.getBoolean("suplier");
                    akses.satuan_barang = rs2.getBoolean("satuan_barang");
                    akses.konversi_satuan = rs2.getBoolean("konversi_satuan");
                    akses.jenis_barang = rs2.getBoolean("jenis_barang");
                    akses.obat = rs2.getBoolean("obat");
                    akses.stok_opname_obat = rs2.getBoolean("stok_opname_obat");
                    akses.stok_obat_pasien = rs2.getBoolean("stok_obat_pasien");
                    akses.pengadaan_obat = rs2.getBoolean("pengadaan_obat");
                    akses.pemesanan_obat = rs2.getBoolean("pemesanan_obat");
                    akses.penjualan_obat = rs2.getBoolean("penjualan_obat");
                    akses.piutang_obat = rs2.getBoolean("piutang_obat");
                    akses.retur_ke_suplier = rs2.getBoolean("retur_ke_suplier");
                    akses.retur_dari_pembeli = rs2.getBoolean("retur_dari_pembeli");
                    akses.retur_obat_ranap = rs2.getBoolean("retur_obat_ranap");
                    akses.retur_piutang_pasien = rs2.getBoolean("retur_piutang_pasien");
                    akses.keuntungan_penjualan = rs2.getBoolean("keuntungan_penjualan");
                    akses.keuntungan_beri_obat = rs2.getBoolean("keuntungan_beri_obat");
                    akses.sirkulasi_obat = rs2.getBoolean("sirkulasi_obat");
                    akses.ipsrs_barang = rs2.getBoolean("ipsrs_barang");
                    akses.ipsrs_pengadaan_barang = rs2.getBoolean("ipsrs_pengadaan_barang");
                    akses.ipsrs_stok_keluar = rs2.getBoolean("ipsrs_stok_keluar");
                    akses.ipsrs_jenis_barang = rs2.getBoolean("ipsrs_jenis_barang");
                    akses.ipsrs_rekap_pengadaan = rs2.getBoolean("ipsrs_rekap_pengadaan");
                    akses.ipsrs_rekap_stok_keluar = rs2.getBoolean("ipsrs_rekap_stok_keluar");
                    akses.ipsrs_pengeluaran_harian = rs2.getBoolean("ipsrs_pengeluaran_harian");
                    akses.inventaris_jenis = rs2.getBoolean("inventaris_jenis");
                    akses.inventaris_kategori = rs2.getBoolean("inventaris_kategori");
                    akses.inventaris_merk = rs2.getBoolean("inventaris_merk");
                    akses.inventaris_ruang = rs2.getBoolean("inventaris_ruang");
                    akses.inventaris_produsen = rs2.getBoolean("inventaris_produsen");
                    akses.inventaris_koleksi = rs2.getBoolean("inventaris_koleksi");
                    akses.inventaris_inventaris = rs2.getBoolean("inventaris_inventaris");
                    akses.inventaris_sirkulasi = rs2.getBoolean("inventaris_sirkulasi");
                    akses.parkir_jenis = rs2.getBoolean("parkir_jenis");
                    akses.parkir_in = rs2.getBoolean("parkir_in");
                    akses.parkir_out = rs2.getBoolean("parkir_out");
                    akses.parkir_rekap_harian = rs2.getBoolean("parkir_rekap_harian");
                    akses.parkir_rekap_bulanan = rs2.getBoolean("parkir_rekap_bulanan");
                    akses.informasi_kamar = rs2.getBoolean("informasi_kamar");
                    akses.harian_tindakan_poli = rs2.getBoolean("harian_tindakan_poli");
                    akses.obat_per_poli = rs2.getBoolean("obat_per_poli");
                    akses.obat_per_kamar = rs2.getBoolean("obat_per_kamar");
                    akses.obat_per_dokter_ralan = rs2.getBoolean("obat_per_dokter_ralan");
                    akses.obat_per_dokter_ranap = rs2.getBoolean("obat_per_dokter_ranap");
                    akses.harian_dokter = rs2.getBoolean("harian_dokter");
                    akses.bulanan_dokter = rs2.getBoolean("bulanan_dokter");
                    akses.harian_paramedis = rs2.getBoolean("harian_paramedis");
                    akses.bulanan_paramedis = rs2.getBoolean("bulanan_paramedis");
                    akses.pembayaran_ralan = rs2.getBoolean("pembayaran_ralan");
                    akses.pembayaran_ranap = rs2.getBoolean("pembayaran_ranap");
                    akses.rekap_pembayaran_ralan = rs2.getBoolean("rekap_pembayaran_ralan");
                    akses.rekap_pembayaran_ranap = rs2.getBoolean("rekap_pembayaran_ranap");
                    akses.tagihan_masuk = rs2.getBoolean("tagihan_masuk");
                    akses.tambahan_biaya = rs2.getBoolean("tambahan_biaya");
                    akses.potongan_biaya = rs2.getBoolean("potongan_biaya");
                    akses.resep_obat = rs2.getBoolean("resep_obat");
                    akses.resume_pasien = rs2.getBoolean("resume_pasien");
                    akses.penyakit_ralan = rs2.getBoolean("penyakit_ralan");
                    akses.penyakit_ranap = rs2.getBoolean("penyakit_ranap");
                    akses.kamar = rs2.getBoolean("kamar");
                    akses.tarif_ralan = rs2.getBoolean("tarif_ralan");
                    akses.tarif_ranap = rs2.getBoolean("tarif_ranap");
                    akses.tarif_lab = rs2.getBoolean("tarif_lab");
                    akses.tarif_radiologi = rs2.getBoolean("tarif_radiologi");
                    akses.tarif_operasi = rs2.getBoolean("tarif_operasi");
                    akses.akun_rekening = rs2.getBoolean("akun_rekening");
                    akses.rekening_tahun = rs2.getBoolean("rekening_tahun");
                    akses.posting_jurnal = rs2.getBoolean("posting_jurnal");
                    akses.buku_besar = rs2.getBoolean("buku_besar");
                    akses.cashflow = rs2.getBoolean("cashflow");
                    akses.keuangan = rs2.getBoolean("keuangan");
                    akses.pengeluaran = rs2.getBoolean("pengeluaran");
                    akses.setup_pjlab = rs2.getBoolean("setup_pjlab");
                    akses.setup_otolokasi = rs2.getBoolean("setup_otolokasi");
                    akses.setup_jam_kamin = rs2.getBoolean("setup_jam_kamin");
                    akses.setup_embalase = rs2.getBoolean("setup_embalase");
                    akses.tracer_login = rs2.getBoolean("tracer_login");
                    akses.display = rs2.getBoolean("display");
                    akses.set_harga_obat = rs2.getBoolean("set_harga_obat");
                    akses.set_penggunaan_tarif = rs2.getBoolean("set_penggunaan_tarif");
                    akses.set_oto_ralan = rs2.getBoolean("set_oto_ralan");
                    akses.biaya_harian = rs2.getBoolean("biaya_harian");
                    akses.biaya_masuk_sekali = rs2.getBoolean("biaya_masuk_sekali");
                    akses.set_no_rm = rs2.getBoolean("set_no_rm");
                    akses.billing_ralan = rs2.getBoolean("billing_ralan");
                    akses.billing_ranap = rs2.getBoolean("billing_ranap");
                    akses.jm_ranap_dokter = rs2.getBoolean("jm_ranap_dokter");
                    akses.igd = rs2.getBoolean("igd");
                    akses.barcoderalan = rs2.getBoolean("barcoderalan");
                    akses.barcoderanap = rs2.getBoolean("barcoderanap");
                    akses.set_harga_obat_ralan = rs2.getBoolean("set_harga_obat_ralan");
                    akses.set_harga_obat_ranap = rs2.getBoolean("set_harga_obat_ranap");
                    akses.penyakit_pd3i = rs2.getBoolean("penyakit_pd3i");
                    akses.surveilans_pd3i = rs2.getBoolean("surveilans_pd3i");
                    akses.surveilans_ralan = rs2.getBoolean("surveilans_ralan");
                    akses.diagnosa_pasien = rs2.getBoolean("diagnosa_pasien");
                    akses.surveilans_ranap = rs2.getBoolean("surveilans_ranap");
                    akses.admin = false;
                    akses.user = false;
                    akses.vakum = false;
                    akses.aplikasi = false;
                    akses.pny_takmenular_ranap = rs2.getBoolean("pny_takmenular_ranap");
                    akses.pny_takmenular_ralan = rs2.getBoolean("pny_takmenular_ralan");
                    akses.kunjungan_ralan = rs2.getBoolean("kunjungan_ralan");
                    akses.rl32 = rs2.getBoolean("rl32");
                    akses.rl33 = rs2.getBoolean("rl33");
                    akses.rl37 = rs2.getBoolean("rl37");
                    akses.rl38 = rs2.getBoolean("rl38");
                    akses.harian_tindakan_dokter = rs2.getBoolean("harian_tindakan_dokter");
                    akses.sms = rs2.getBoolean("sms");
                    akses.sidikjari = rs2.getBoolean("sidikjari");
                    akses.jam_masuk = rs2.getBoolean("jam_masuk");
                    akses.jadwal_pegawai = rs2.getBoolean("jadwal_pegawai");
                    akses.parkir_barcode = rs2.getBoolean("parkir_barcode");
                    akses.set_nota = rs2.getBoolean("set_nota");
                    akses.dpjp_ranap = rs2.getBoolean("dpjp_ranap");
                    akses.mutasi_barang = rs2.getBoolean("mutasi_barang");
                    akses.rl34 = rs2.getBoolean("rl34");
                    akses.rl36 = rs2.getBoolean("rl36");
                    akses.fee_visit_dokter = rs2.getBoolean("fee_visit_dokter");
                    akses.fee_bacaan_ekg = rs2.getBoolean("fee_bacaan_ekg");
                    akses.fee_rujukan_rontgen = rs2.getBoolean("fee_rujukan_rontgen");
                    akses.fee_rujukan_ranap = rs2.getBoolean("fee_rujukan_ranap");
                    akses.fee_ralan = rs2.getBoolean("fee_ralan");
                    akses.akun_bayar = rs2.getBoolean("akun_bayar");
                    akses.bayar_pemesanan_obat = rs2.getBoolean("bayar_pemesanan_obat");
                    akses.obat_per_dokter_peresep = rs2.getBoolean("obat_per_dokter_peresep");
                    akses.pemasukan_lain = rs2.getBoolean("pemasukan_lain");
                    akses.pengaturan_rekening = rs2.getBoolean("pengaturan_rekening");
                    akses.closing_kasir = rs2.getBoolean("closing_kasir");
                    akses.keterlambatan_presensi = rs2.getBoolean("keterlambatan_presensi");
                    akses.set_harga_kamar = rs2.getBoolean("set_harga_kamar");
                    akses.rekap_per_shift = rs2.getBoolean("rekap_per_shift");
                    akses.bpjs_cek_nik = rs2.getBoolean("bpjs_cek_nik");
                    akses.bpjs_cek_kartu = rs2.getBoolean("bpjs_cek_kartu");
                    akses.obat_per_cara_bayar = rs2.getBoolean("obat_per_cara_bayar");
                    akses.kunjungan_ranap = rs2.getBoolean("kunjungan_ranap");
                    akses.bayar_piutang = rs2.getBoolean("bayar_piutang");
                    akses.payment_point = rs2.getBoolean("payment_point");
                    akses.bpjs_cek_nomor_rujukan = rs2.getBoolean("bpjs_cek_nomor_rujukan");
                    akses.icd9 = rs2.getBoolean("icd9");
                    akses.darurat_stok = rs2.getBoolean("darurat_stok");
                    akses.retensi_rm = rs2.getBoolean("retensi_rm");
                    akses.temporary_presensi = rs2.getBoolean("temporary_presensi");
                    akses.jurnal_harian = rs2.getBoolean("jurnal_harian");
                    akses.sirkulasi_obat2 = rs2.getBoolean("sirkulasi_obat2");
                    akses.edit_registrasi = rs2.getBoolean("edit_registrasi");
                    akses.bpjs_referensi_diagnosa = rs2.getBoolean("bpjs_referensi_diagnosa");
                    akses.bpjs_referensi_poli = rs2.getBoolean("bpjs_referensi_poli");
                    akses.industrifarmasi = rs2.getBoolean("industrifarmasi");
                    akses.harian_js = rs2.getBoolean("harian_js");
                    akses.bulanan_js = rs2.getBoolean("bulanan_js");
                    akses.harian_paket_bhp = rs2.getBoolean("harian_paket_bhp");
                    akses.bulanan_paket_bhp = rs2.getBoolean("bulanan_paket_bhp");
                    akses.piutang_pasien2 = rs2.getBoolean("piutang_pasien2");
                    akses.bpjs_referensi_faskes = rs2.getBoolean("bpjs_referensi_faskes");
                    akses.bpjs_sep = rs2.getBoolean("bpjs_sep");
                    akses.pengambilan_utd = rs2.getBoolean("pengambilan_utd");
                    akses.tarif_utd = rs2.getBoolean("tarif_utd");
                    akses.pengambilan_utd2 = rs2.getBoolean("pengambilan_utd2");
                    akses.utd_medis_rusak = rs2.getBoolean("utd_medis_rusak");
                    akses.pengambilan_penunjang_utd = rs2.getBoolean("pengambilan_penunjang_utd");
                    akses.pengambilan_penunjang_utd2 = rs2.getBoolean("pengambilan_penunjang_utd2");
                    akses.utd_penunjang_rusak = rs2.getBoolean("utd_penunjang_rusak");
                    akses.suplier_penunjang = rs2.getBoolean("suplier_penunjang");
                    akses.utd_donor = rs2.getBoolean("utd_donor");
                    akses.bpjs_monitoring_klaim = rs2.getBoolean("bpjs_monitoring_klaim");
                    akses.utd_cekal_darah = rs2.getBoolean("utd_cekal_darah");
                    akses.utd_komponen_darah = rs2.getBoolean("utd_komponen_darah");
                    akses.utd_stok_darah = rs2.getBoolean("utd_stok_darah");
                    akses.utd_pemisahan_darah = rs2.getBoolean("utd_pemisahan_darah");
                    akses.harian_kamar = rs2.getBoolean("harian_kamar");
                    akses.rincian_piutang_pasien = rs2.getBoolean("rincian_piutang_pasien");
                    akses.keuntungan_beri_obat_nonpiutang = rs2.getBoolean("keuntungan_beri_obat_nonpiutang");
                    akses.reklasifikasi_ralan = rs2.getBoolean("reklasifikasi_ralan");
                    akses.reklasifikasi_ranap = rs2.getBoolean("reklasifikasi_ranap");
                    akses.utd_penyerahan_darah = rs2.getBoolean("utd_penyerahan_darah");
                    akses.hutang_obat = rs2.getBoolean("hutang_obat");
                    akses.riwayat_obat_alkes_bhp = rs2.getBoolean("riwayat_obat_alkes_bhp");
                    akses.sensus_harian_poli = rs2.getBoolean("sensus_harian_poli");
                    akses.rl4a = rs2.getBoolean("rl4a");
                    akses.aplicare_referensi_kamar = rs2.getBoolean("aplicare_referensi_kamar");
                    akses.aplicare_ketersediaan_kamar = rs2.getBoolean("aplicare_ketersediaan_kamar");
                    akses.inacbg_klaim_baru_otomatis = rs2.getBoolean("inacbg_klaim_baru_otomatis");
                    akses.inacbg_klaim_baru_manual = rs2.getBoolean("inacbg_klaim_baru_manual");
                    akses.inacbg_coder_nik = rs2.getBoolean("inacbg_coder_nik");
                    akses.mutasi_berkas = rs2.getBoolean("mutasi_berkas");
                    akses.akun_piutang = rs2.getBoolean("akun_piutang");
                    akses.harian_kso = rs2.getBoolean("harian_kso");
                    akses.bulanan_kso = rs2.getBoolean("bulanan_kso");
                    akses.harian_menejemen = rs2.getBoolean("harian_menejemen");
                    akses.bulanan_menejemen = rs2.getBoolean("bulanan_menejemen");
                    akses.inhealth_cek_eligibilitas = rs2.getBoolean("inhealth_cek_eligibilitas");
                    akses.inhealth_referensi_jenpel_ruang_rawat = rs2.getBoolean("inhealth_referensi_jenpel_ruang_rawat");
                    akses.inhealth_referensi_poli = rs2.getBoolean("inhealth_referensi_poli");
                    akses.inhealth_referensi_faskes = rs2.getBoolean("inhealth_referensi_faskes");
                    akses.inhealth_sjp = rs2.getBoolean("inhealth_sjp");
                    akses.piutang_ralan = rs2.getBoolean("piutang_ralan");
                    akses.piutang_ranap = rs2.getBoolean("piutang_ranap");
                    akses.detail_piutang_penjab = rs2.getBoolean("detail_piutang_penjab");
                    akses.lama_pelayanan_ralan = rs2.getBoolean("lama_pelayanan_ralan");
                    akses.catatan_pasien = rs2.getBoolean("catatan_pasien");
                    akses.rl4b = rs2.getBoolean("rl4b");
                    akses.rl4asebab = rs2.getBoolean("rl4asebab");
                    akses.rl4bsebab = rs2.getBoolean("rl4bsebab");
                    akses.data_HAIs = rs2.getBoolean("data_HAIs");
                    akses.harian_HAIs = rs2.getBoolean("harian_HAIs");
                    akses.bulanan_HAIs = rs2.getBoolean("bulanan_HAIs");
                    akses.hitung_bor = rs2.getBoolean("hitung_bor");
                    akses.perusahaan_pasien = rs2.getBoolean("perusahaan_pasien");
                    akses.namauser = rs2.getString("nama");
                    akses.resep_dokter = rs2.getBoolean("resep_dokter");
                    akses.lama_pelayanan_apotek = rs2.getBoolean("lama_pelayanan_apotek");
                    akses.hitung_alos = rs2.getBoolean("hitung_alos");
                    akses.detail_tindakan = rs2.getBoolean("detail_tindakan");
                    akses.rujukan_poli_internal = rs2.getBoolean("rujukan_poli_internal");
                    akses.rekap_poli_anak = rs2.getBoolean("rekap_poli_anak");
                    akses.grafik_kunjungan_poli = rs2.getBoolean("grafik_kunjungan_poli");
                    akses.grafik_kunjungan_perdokter = rs2.getBoolean("grafik_kunjungan_perdokter");
                    akses.grafik_kunjungan_perpekerjaan = rs2.getBoolean("grafik_kunjungan_perpekerjaan");
                    akses.grafik_kunjungan_perpendidikan = rs2.getBoolean("grafik_kunjungan_perpendidikan");
                    akses.grafik_kunjungan_pertahun = rs2.getBoolean("grafik_kunjungan_pertahun");
                    akses.berkas_digital_perawatan = rs2.getBoolean("berkas_digital_perawatan");
                    akses.penyakit_menular_ranap = rs2.getBoolean("penyakit_menular_ranap");
                    akses.penyakit_menular_ralan = rs2.getBoolean("penyakit_menular_ralan");
                    akses.grafik_kunjungan_perbulan = rs2.getBoolean("grafik_kunjungan_perbulan");
                    akses.grafik_kunjungan_pertanggal = rs2.getBoolean("grafik_kunjungan_pertanggal");
                    akses.grafik_kunjungan_demografi = rs2.getBoolean("grafik_kunjungan_demografi");
                    akses.grafik_kunjungan_statusdaftartahun = rs2.getBoolean("grafik_kunjungan_statusdaftartahun");
                    akses.grafik_kunjungan_statusdaftartahun2 = rs2.getBoolean("grafik_kunjungan_statusdaftartahun2");
                    akses.grafik_kunjungan_statusdaftarbulan = rs2.getBoolean("grafik_kunjungan_statusdaftarbulan");
                    akses.grafik_kunjungan_statusdaftarbulan2 = rs2.getBoolean("grafik_kunjungan_statusdaftarbulan2");
                    akses.grafik_kunjungan_statusdaftartanggal = rs2.getBoolean("grafik_kunjungan_statusdaftartanggal");
                    akses.grafik_kunjungan_statusdaftartanggal2 = rs2.getBoolean("grafik_kunjungan_statusdaftartanggal2");
                    akses.grafik_kunjungan_statusbataltahun = rs2.getBoolean("grafik_kunjungan_statusbataltahun");
                    akses.grafik_kunjungan_statusbatalbulan = rs2.getBoolean("grafik_kunjungan_statusbatalbulan");
                    akses.pcare_cek_penyakit = rs2.getBoolean("pcare_cek_penyakit");
                    akses.grafik_kunjungan_statusbataltanggal = rs2.getBoolean("grafik_kunjungan_statusbataltanggal");
                    akses.kategori_barang = rs2.getBoolean("kategori_barang");
                    akses.golongan_barang = rs2.getBoolean("golongan_barang");
                    akses.pemberian_obat_pertanggal = rs2.getBoolean("pemberian_obat_pertanggal");
                    akses.penjualan_obat_pertanggal = rs2.getBoolean("penjualan_obat_pertanggal");
                    akses.bpjs_rujukan_vclaim = rs2.getBoolean("rujukan_keluar_vclaim_bpjs");
                    akses.skdp_bpjs = rs2.getBoolean("skdp_bpjs");
                    akses.booking_registrasi = rs2.getBoolean("booking_registrasi");
                    akses.bpjs_cek_riwayat_rujukan_pcare = rs2.getBoolean("bpjs_cek_riwayat_rujukan_pcare");
                    akses.bpjs_cek_riwayat_rujukan_rs = rs2.getBoolean("bpjs_cek_riwayat_rujukan_rs");
                    akses.bpjs_cek_rujukan_kartu_rs = rs2.getBoolean("bpjs_cek_rujukan_kartu_rs");
                    akses.bpjs_cek_tgl_rujukan = rs2.getBoolean("bpjs_cek_tanggal_rujukan");
                    akses.bpjs_cek_no_rujukan_rs = rs2.getBoolean("bpjs_cek_no_rujukan_rs");
                    akses.bpjs_cek_rujukan_kartu_pcare = rs2.getBoolean("bpjs_cek_rujukan_kartu_pcare");
                    akses.bpjs_cek_referensi_kelas_rawat = rs2.getBoolean("bpjs_cek_referensi_kelas_rawat");
                    akses.bpjs_cek_referensi_prosedur = rs2.getBoolean("bpjs_cek_referensi_prosedur");
                    akses.bpjs_cek_referensi_dpjp = rs2.getBoolean("bpjs_cek_referensi_dpjp");
                    akses.bpjs_cek_referensi_dokter = rs2.getBoolean("bpjs_cek_referensi_dokter");
                    akses.bpjs_cek_referensi_spesialistik = rs2.getBoolean("bpjs_cek_referensi_spesialistik");
                    akses.bpjs_cek_referensi_ruang_rawat = rs2.getBoolean("bpjs_cek_referensi_ruang_rawat");
                    akses.bpjs_cek_referensi_cara_keluar = rs2.getBoolean("bpjs_cek_referensi_cara_keluar");
                    akses.bpjs_cek_referensi_pasca_pulang = rs2.getBoolean("bpjs_cek_referensi_pasca_pulang");
                    akses.bpjs_cek_referensi_propinsi = rs2.getBoolean("bpjs_cek_referensi_propinsi");
                    akses.bpjs_cek_referensi_kabupaten = rs2.getBoolean("bpjs_cek_referensi_kabupaten");
                    akses.bpjs_cek_referensi_kecamatan = rs2.getBoolean("bpjs_cek_referensi_kecamatan");
                    akses.permintaan_lab = rs2.getBoolean("permintaan_lab");
                    akses.permintaan_radiologi = rs2.getBoolean("permintaan_radiologi");
                    akses.selisih_tarif_bpjs = rs2.getBoolean("selisih_tarif_bpjs");
                    akses.edit_data_kematian = rs2.getBoolean("edit_data_kematian");
                    akses.bridging_jamkesda = rs2.getBoolean("bridging_jamkesda");
                    akses.masuk_pindah_pulang_inap = rs2.getBoolean("masuk_pindah_pulang_inap");
                    akses.masuk_pindah_inap = rs2.getBoolean("masuk_pindah_inap");
                    akses.jumlah_macam_diet = rs2.getBoolean("jumlah_macam_diet");
                    akses.jumlah_porsi_diet = rs2.getBoolean("jumlah_porsi_diet");
                    akses.status_gizi = rs2.getBoolean("status_gizi");
                    akses.gizi_buruk = rs2.getBoolean("gizi_buruk");
                    akses.master_faskes = rs2.getBoolean("master_faskes");
                    akses.setstatusralan = rs2.getBoolean("set_status_registrasi_ralan");
                    akses.telusurpasien = rs2.getBoolean("telusur_kunjungan_pasien");
                    akses.sisrute_rujukan_keluar = rs2.getBoolean("sisrute_rujukan_keluar");
                    akses.sisrute_rujukan_masuk = rs2.getBoolean("sisrute_rujukan_masuk");
                    akses.sisrute_referensi_diagnosa = rs2.getBoolean("sisrute_referensi_diagnosa");
                    akses.sisrute_referensi_alasanrujuk = rs2.getBoolean("sisrute_referensi_alasanrujuk");
                    akses.sisrute_referensi_faskes = rs2.getBoolean("sisrute_referensi_faskes");
                    akses.barang_cssd = rs2.getBoolean("barang_cssd");
                    akses.status_pulang_inap = rs2.getBoolean("status_pulang_inap");                    
                    akses.data_persalinan = rs2.getBoolean("data_persalinan");
                    akses.data_ponek = rs2.getBoolean("data_ponek");
                    akses.reg_boking_kasir = rs2.getBoolean("registrasi_booking_dikasir");
                    akses.bahasa_pasien = rs2.getBoolean("bahasa_pasien");
                    akses.suku_bangsa = rs2.getBoolean("suku_pasien");
                    akses.harian_hais_inap = rs2.getBoolean("harian_hais_ranap");
                    akses.harian_hais_jalan = rs2.getBoolean("harian_hais_ralan");
                    akses.bulanan_hais_inap = rs2.getBoolean("bulanan_hais_ranap");
                    akses.bulanan_hais_jalan = rs2.getBoolean("bulanan_hais_ralan");
                    akses.ringkasan_pulang_ranap = rs2.getBoolean("ringkasan_pulang_ranap");
                    akses.laporan_farmasi = rs2.getBoolean("laporan_farmasi");
                    akses.master_masalah_keperawatan = rs2.getBoolean("master_masalah_keperawatan");
                    akses.penilaian_awal_keperawatan_ralan = rs2.getBoolean("penilaian_awal_keperawatan_ralan");
                    akses.master_triase_skala1 = rs2.getBoolean("master_triase_skala1"); 
                    akses.master_triase_skala2 = rs2.getBoolean("master_triase_skala2"); 
                    akses.master_triase_skala3 = rs2.getBoolean("master_triase_skala3");                    
                    akses.master_triase_skala4 = rs2.getBoolean("master_triase_skala4"); 
                    akses.master_triase_skala5 = rs2.getBoolean("master_triase_skala5");                    
                    akses.data_triase_igd = rs2.getBoolean("data_triase_igd");  
                    akses.master_triase_pemeriksaan = rs2.getBoolean("master_triase_pemeriksaan"); 
                    akses.master_triase_macamkasus = rs2.getBoolean("master_triase_macamkasus"); 
                    akses.master_cara_bayar = rs2.getBoolean("master_cara_bayar");
                    akses.status_kerja_dokter = rs2.getBoolean("status_kerja_dokter");
                    akses.pasien_corona = rs2.getBoolean("pasien_corona");
                    akses.diagnosa_pasien_corona = rs2.getBoolean("diagnosa_pasien_corona");
                    akses.perawatan_pasien_corona = rs2.getBoolean("perawatan_pasien_corona");
                    akses.inacbg_klaim_baru_manual2 = rs2.getBoolean("inacbg_klaim_baru_manual2");
                    akses.assesmen_gizi_harian = rs2.getBoolean("assesmen_gizi_harian");
                    akses.assesmen_gizi_ulang = rs2.getBoolean("assesmen_gizi_ulang");
                    akses.tombol_nota_billing = rs2.getBoolean("tombol_nota_billing");
                    akses.tombol_simpan_hasil_rad = rs2.getBoolean("tombol_simpan_hasil_radiologi");
                    akses.monev_asuhan_gizi = rs2.getBoolean("monev_asuhan_gizi");
                    akses.inacbg_klaim_raza = rs2.getBoolean("inacbg_klaim_raza");
                    akses.pengajuan_klaim_inacbg_raza = rs2.getBoolean("pengajuan_klaim_inacbg_raza");
                    akses.copy_pemeriksaan_dokter_kepetugas_ralan = rs2.getBoolean("copy_pemeriksaan_dokter_kepetugas_ralan");
                    akses.jkn_belum_diproses_klaim = rs2.getBoolean("jkn_belum_diproses_klaim");
                    akses.input_kode_icd = rs2.getBoolean("input_kode_icd");
                    akses.kendali_Mutu_kendali_Biaya_INACBG = rs2.getBoolean("kendali_Mutu_kendali_Biaya_INACBG");
                    akses.dashboard_eResep = rs2.getBoolean("dashboard_eResep");
                    akses.bpjs_sep_internal = rs2.getBoolean("bpjs_sep_internal");
                    akses.kemenkes_sitt = rs2.getBoolean("kemenkes_sitt");
                    akses.rencana_kontrol_jkn = rs2.getBoolean("rencana_kontrol_jkn");
                    akses.spri_jkn = rs2.getBoolean("spri_jkn");
                    akses.hapus_sep = rs2.getBoolean("hapus_sep");
                    akses.penilaian_awal_medis_ralan_kebidanan = rs2.getBoolean("penilaian_awal_medis_ralan_kebidanan");
                    akses.penilaian_awal_keperawatan_kebidanan = rs2.getBoolean("penilaian_awal_keperawatan_kebidanan");
                    akses.ikhtisar_perawatan_hiv = rs2.getBoolean("ikhtisar_perawatan_hiv");
                    akses.survey_kepuasan = rs2.getBoolean("survey_kepuasan");
                    akses.kemenkes_kanker = rs2.getBoolean("kemenkes_kanker");
                    akses.aktivasi_bridging = rs2.getBoolean("aktivasi_bridging");
                    akses.operator_antrian = rs2.getBoolean("operator_antrian");
                    akses.penilaian_awal_medis_ralan_tht = rs2.getBoolean("penilaian_awal_medis_ralan_tht");
                    akses.rekam_psikologis = rs2.getBoolean("rekam_psikologis");
                    akses.penilaian_pasien_geriatri = rs2.getBoolean("penilaian_pasien_geriatri");
                    akses.penilaian_awal_medis_ralan_mata = rs2.getBoolean("penilaian_awal_medis_ralan_mata");
                    akses.surat_sakit = rs2.getBoolean("surat_sakit");
                    akses.surat_keterangan_kir_mcu = rs2.getBoolean("surat_keterangan_kir_mcu");
                    akses.asesmen_medik_dewasa_ranap = rs2.getBoolean("asesmen_medik_dewasa_ranap");
                } else if ((rs.getRow() == 0) && (rs2.getRow() == 0)) {
                    akses.kode = "";
                    akses.penyakit = false;
                    akses.obat_penyakit = false;
                    akses.dokter = false;
                    akses.jadwal_praktek = false;
                    akses.petugas = false;
                    akses.pasien = false;
                    akses.registrasi = false;
                    akses.tindakan_ralan = false;
                    akses.kamar_inap = false;
                    akses.tindakan_ranap = false;
                    akses.operasi = false;
                    akses.rujukan_keluar = false;
                    akses.rujukan_masuk = false;
                    akses.beri_obat = false;
                    akses.resep_pulang = false;
                    akses.pasien_meninggal = false;
                    akses.diet_pasien = false;
                    akses.kelahiran_bayi = false;
                    akses.periksa_lab = false;
                    akses.periksa_radiologi = false;
                    akses.kasir_ralan = false;
                    akses.deposit_pasien = false;
                    akses.piutang_pasien = false;
                    akses.peminjaman_berkas = false;
                    akses.barcode = false;
                    akses.presensi_harian = false;
                    akses.presensi_bulanan = false;
                    akses.pegawai_admin = false;
                    akses.pegawai_user = false;
                    akses.suplier = false;
                    akses.satuan_barang = false;
                    akses.konversi_satuan = false;
                    akses.jenis_barang = false;
                    akses.obat = false;
                    akses.stok_opname_obat = false;
                    akses.stok_obat_pasien = false;
                    akses.pengadaan_obat = false;
                    akses.pemesanan_obat = false;
                    akses.penjualan_obat = false;
                    akses.piutang_obat = false;
                    akses.retur_ke_suplier = false;
                    akses.retur_dari_pembeli = false;
                    akses.retur_obat_ranap = false;
                    akses.retur_piutang_pasien = false;
                    akses.keuntungan_penjualan = false;
                    akses.keuntungan_beri_obat = false;
                    akses.sirkulasi_obat = false;
                    akses.ipsrs_barang = false;
                    akses.ipsrs_pengadaan_barang = false;
                    akses.ipsrs_stok_keluar = false;
                    akses.ipsrs_rekap_pengadaan = false;
                    akses.ipsrs_rekap_stok_keluar = false;
                    akses.ipsrs_pengeluaran_harian = false;
                    akses.ipsrs_jenis_barang = false;
                    akses.inventaris_jenis = false;
                    akses.inventaris_kategori = false;
                    akses.inventaris_merk = false;
                    akses.inventaris_ruang = false;
                    akses.inventaris_produsen = false;
                    akses.inventaris_koleksi = false;
                    akses.inventaris_inventaris = false;
                    akses.inventaris_sirkulasi = false;
                    akses.parkir_jenis = false;
                    akses.parkir_in = false;
                    akses.parkir_out = false;
                    akses.parkir_rekap_harian = false;
                    akses.parkir_rekap_bulanan = false;
                    akses.informasi_kamar = false;
                    akses.harian_tindakan_poli = false;
                    akses.obat_per_poli = false;
                    akses.obat_per_kamar = false;
                    akses.obat_per_dokter_ralan = false;
                    akses.obat_per_dokter_ranap = false;
                    akses.harian_dokter = false;
                    akses.bulanan_dokter = false;
                    akses.harian_paramedis = false;
                    akses.bulanan_paramedis = false;
                    akses.pembayaran_ralan = false;
                    akses.pembayaran_ranap = false;
                    akses.rekap_pembayaran_ralan = false;
                    akses.rekap_pembayaran_ranap = false;
                    akses.tagihan_masuk = false;
                    akses.tambahan_biaya = false;
                    akses.potongan_biaya = false;
                    akses.resep_obat = false;
                    akses.resume_pasien = false;
                    akses.penyakit_ralan = false;
                    akses.penyakit_ranap = false;
                    akses.kamar = false;
                    akses.tarif_ralan = false;
                    akses.tarif_ranap = false;
                    akses.tarif_lab = false;
                    akses.tarif_radiologi = false;
                    akses.tarif_operasi = false;
                    akses.akun_rekening = false;
                    akses.rekening_tahun = false;
                    akses.posting_jurnal = false;
                    akses.buku_besar = false;
                    akses.cashflow = false;
                    akses.keuangan = false;
                    akses.pengeluaran = false;
                    akses.setup_pjlab = false;
                    akses.setup_otolokasi = false;
                    akses.setup_jam_kamin = false;
                    akses.setup_embalase = false;
                    akses.tracer_login = false;
                    akses.display = false;
                    akses.set_harga_obat = false;
                    akses.set_penggunaan_tarif = false;
                    akses.set_oto_ralan = false;
                    akses.biaya_harian = false;
                    akses.biaya_masuk_sekali = false;
                    akses.set_no_rm = false;
                    akses.billing_ralan = false;
                    akses.billing_ranap = false;
                    akses.jm_ranap_dokter = false;
                    akses.igd = false;
                    akses.barcoderalan = false;
                    akses.barcoderanap = false;
                    akses.set_harga_obat_ralan = false;
                    akses.set_harga_obat_ranap = false;
                    akses.admin = false;
                    akses.user = false;
                    akses.vakum = false;
                    akses.aplikasi = false;
                    akses.penyakit_pd3i = false;
                    akses.surveilans_pd3i = false;
                    akses.surveilans_ralan = false;
                    akses.diagnosa_pasien = false;
                    akses.surveilans_ranap = false;
                    akses.pny_takmenular_ranap = false;
                    akses.pny_takmenular_ralan = false;
                    akses.kunjungan_ralan = false;
                    akses.rl32 = false;
                    akses.rl33 = false;
                    akses.rl37 = false;
                    akses.rl38 = false;
                    akses.harian_tindakan_dokter = false;
                    akses.sms = false;
                    akses.sidikjari = false;
                    akses.jam_masuk = false;
                    akses.jadwal_pegawai = false;
                    akses.parkir_barcode = false;
                    akses.set_nota = false;
                    akses.dpjp_ranap = false;
                    akses.mutasi_barang = false;
                    akses.rl34 = false;
                    akses.rl36 = false;
                    akses.fee_visit_dokter = false;
                    akses.fee_bacaan_ekg = false;
                    akses.fee_rujukan_rontgen = false;
                    akses.fee_rujukan_ranap = false;
                    akses.fee_ralan = false;
                    akses.akun_bayar = false;
                    akses.bayar_pemesanan_obat = false;
                    akses.obat_per_dokter_peresep = false;
                    akses.pemasukan_lain = false;
                    akses.pengaturan_rekening = false;
                    akses.closing_kasir = false;
                    akses.keterlambatan_presensi = false;
                    akses.set_harga_kamar = false;
                    akses.rekap_per_shift = false;
                    akses.bpjs_cek_nik = false;
                    akses.bpjs_cek_kartu = false;
                    akses.obat_per_cara_bayar = false;
                    akses.kunjungan_ranap = false;
                    akses.bayar_piutang = false;
                    akses.payment_point = false;
                    akses.bpjs_cek_nomor_rujukan = false;
                    akses.icd9 = false;
                    akses.darurat_stok = false;
                    akses.retensi_rm = false;
                    akses.temporary_presensi = false;
                    akses.jurnal_harian = false;
                    akses.sirkulasi_obat2 = false;
                    akses.edit_registrasi = false;
                    akses.bpjs_referensi_diagnosa = false;
                    akses.bpjs_referensi_poli = false;
                    akses.industrifarmasi = false;
                    akses.harian_js = false;
                    akses.bulanan_js = false;
                    akses.harian_paket_bhp = false;
                    akses.bulanan_paket_bhp = false;
                    akses.piutang_pasien2 = false;
                    akses.bpjs_referensi_faskes = false;
                    akses.bpjs_sep = false;
                    akses.pengambilan_utd = false;
                    akses.tarif_utd = false;
                    akses.pengambilan_utd2 = false;
                    akses.utd_medis_rusak = false;
                    akses.pengambilan_penunjang_utd = false;
                    akses.pengambilan_penunjang_utd2 = false;
                    akses.utd_penunjang_rusak = false;
                    akses.suplier_penunjang = false;
                    akses.utd_donor = false;
                    akses.bpjs_monitoring_klaim = false;
                    akses.utd_cekal_darah = false;
                    akses.utd_komponen_darah = false;
                    akses.utd_stok_darah = false;
                    akses.utd_pemisahan_darah = false;
                    akses.harian_kamar = false;
                    akses.rincian_piutang_pasien = false;
                    akses.keuntungan_beri_obat_nonpiutang = false;
                    akses.reklasifikasi_ralan = false;
                    akses.reklasifikasi_ranap = false;
                    akses.utd_penyerahan_darah = false;
                    akses.hutang_obat = false;
                    akses.riwayat_obat_alkes_bhp = false;
                    akses.sensus_harian_poli = false;
                    akses.rl4a = false;
                    akses.aplicare_referensi_kamar = false;
                    akses.aplicare_ketersediaan_kamar = false;
                    akses.inacbg_klaim_baru_otomatis = false;
                    akses.inacbg_klaim_baru_manual = false;
                    akses.inacbg_coder_nik = false;
                    akses.mutasi_berkas = false;
                    akses.akun_piutang = false;
                    akses.harian_kso = false;
                    akses.bulanan_kso = false;
                    akses.harian_menejemen = false;
                    akses.bulanan_menejemen = false;
                    akses.inhealth_cek_eligibilitas = false;
                    akses.inhealth_referensi_jenpel_ruang_rawat = false;
                    akses.inhealth_referensi_poli = false;
                    akses.inhealth_referensi_faskes = false;
                    akses.inhealth_sjp = false;
                    akses.piutang_ralan = false;
                    akses.piutang_ranap = false;
                    akses.detail_piutang_penjab = false;
                    akses.lama_pelayanan_ralan = false;
                    akses.catatan_pasien = false;
                    akses.rl4b = false;
                    akses.rl4asebab = false;
                    akses.rl4bsebab = false;
                    akses.data_HAIs = false;
                    akses.harian_HAIs = false;
                    akses.bulanan_HAIs = false;
                    akses.hitung_bor = false;
                    akses.namauser = rs2.getString("nama");
                    akses.perusahaan_pasien = false;
                    akses.resep_dokter = false;
                    akses.lama_pelayanan_apotek = false;
                    akses.hitung_alos = false;
                    akses.detail_tindakan = false;
                    akses.rujukan_poli_internal = false;
                    akses.rekap_poli_anak = false;
                    akses.grafik_kunjungan_poli = false;
                    akses.grafik_kunjungan_perdokter = false;
                    akses.grafik_kunjungan_perpekerjaan = false;
                    akses.grafik_kunjungan_perpendidikan = false;
                    akses.grafik_kunjungan_pertahun = false;
                    akses.berkas_digital_perawatan = false;
                    akses.penyakit_menular_ranap = false;
                    akses.penyakit_menular_ralan = false;
                    akses.grafik_kunjungan_perbulan = false;
                    akses.grafik_kunjungan_pertanggal = false;
                    akses.grafik_kunjungan_demografi = false;
                    akses.grafik_kunjungan_statusdaftartahun = false;
                    akses.grafik_kunjungan_statusdaftartahun2 = false;
                    akses.grafik_kunjungan_statusdaftarbulan = false;
                    akses.grafik_kunjungan_statusdaftarbulan2 = false;
                    akses.grafik_kunjungan_statusdaftartanggal = false;
                    akses.grafik_kunjungan_statusdaftartanggal2 = false;
                    akses.grafik_kunjungan_statusbataltahun = false;
                    akses.grafik_kunjungan_statusbatalbulan = false;
                    akses.pcare_cek_penyakit = false;
                    akses.grafik_kunjungan_statusbataltanggal = false;
                    akses.kategori_barang = false;
                    akses.golongan_barang = false;
                    akses.pemberian_obat_pertanggal = false;
                    akses.penjualan_obat_pertanggal = false;
                    akses.bpjs_rujukan_vclaim = false;
                    akses.skdp_bpjs = false;
                    akses.booking_registrasi = false;
                    akses.bpjs_cek_riwayat_rujukan_pcare = false;
                    akses.bpjs_cek_riwayat_rujukan_rs = false;
                    akses.bpjs_cek_rujukan_kartu_rs = false;
                    akses.bpjs_cek_tgl_rujukan = false;
                    akses.bpjs_cek_no_rujukan_rs = false;
                    akses.bpjs_cek_rujukan_kartu_pcare = false;
                    akses.bpjs_cek_referensi_kelas_rawat = false;
                    akses.bpjs_cek_referensi_prosedur = false;
                    akses.bpjs_cek_referensi_dpjp = false;
                    akses.bpjs_cek_referensi_dokter = false;
                    akses.bpjs_cek_referensi_spesialistik = false;
                    akses.bpjs_cek_referensi_ruang_rawat = false;
                    akses.bpjs_cek_referensi_cara_keluar = false;
                    akses.bpjs_cek_referensi_pasca_pulang = false;
                    akses.bpjs_cek_referensi_propinsi = false;
                    akses.bpjs_cek_referensi_kabupaten = false;
                    akses.bpjs_cek_referensi_kecamatan = false;
                    akses.permintaan_lab = false;
                    akses.permintaan_radiologi = false;
                    akses.selisih_tarif_bpjs = false;
                    akses.edit_data_kematian = false;
                    akses.bridging_jamkesda = false;
                    akses.masuk_pindah_pulang_inap = false;
                    akses.masuk_pindah_inap = false;
                    akses.jumlah_macam_diet = false;
                    akses.jumlah_porsi_diet = false;
                    akses.status_gizi = false;
                    akses.gizi_buruk = false;
                    akses.master_faskes = false;
                    akses.setstatusralan = false;
                    akses.telusurpasien = false;
                    akses.sisrute_rujukan_keluar = false;
                    akses.sisrute_rujukan_masuk = false;
                    akses.sisrute_referensi_diagnosa = false;
                    akses.sisrute_referensi_alasanrujuk = false;
                    akses.sisrute_referensi_faskes = false;
                    akses.barang_cssd = false;
                    akses.status_pulang_inap = false;
                    akses.data_persalinan = false;
                    akses.data_ponek = false;
                    akses.reg_boking_kasir = false;
                    akses.bahasa_pasien = false;
                    akses.suku_bangsa = false;
                    akses.harian_hais_inap = false;
                    akses.harian_hais_jalan = false;
                    akses.bulanan_hais_inap = false;
                    akses.bulanan_hais_jalan = false;
                    akses.ringkasan_pulang_ranap = false;
                    akses.laporan_farmasi = false;
                    akses.master_masalah_keperawatan = false;
                    akses.penilaian_awal_keperawatan_ralan = false;
                    akses.master_triase_skala1 = false;
                    akses.master_triase_skala2 = false;
                    akses.master_triase_skala3 = false;
                    akses.master_triase_skala4 = false;
                    akses.master_triase_skala5 = false;
                    akses.data_triase_igd = false;
                    akses.master_triase_pemeriksaan = false;
                    akses.master_triase_macamkasus = false;
                    akses.master_cara_bayar = false;
                    akses.status_kerja_dokter = false;
                    akses.pasien_corona = false;
                    akses.diagnosa_pasien_corona = false;
                    akses.perawatan_pasien_corona = false;
                    akses.inacbg_klaim_baru_manual2 = false;
                    akses.assesmen_gizi_harian = false;
                    akses.assesmen_gizi_ulang = false;
                    akses.tombol_nota_billing = false;
                    akses.tombol_simpan_hasil_rad = false;
                    akses.monev_asuhan_gizi = false;
                    akses.inacbg_klaim_raza = false;
                    akses.pengajuan_klaim_inacbg_raza = false;
                    akses.copy_pemeriksaan_dokter_kepetugas_ralan = false;
                    akses.jkn_belum_diproses_klaim = false;
                    akses.input_kode_icd = false;
                    akses.kendali_Mutu_kendali_Biaya_INACBG = false;
                    akses.dashboard_eResep = false;
                    akses.bpjs_sep_internal = false;
                    akses.kemenkes_sitt = false;
                    akses.rencana_kontrol_jkn = false;
                    akses.spri_jkn = false;
                    akses.hapus_sep = false;
                    akses.penilaian_awal_medis_ralan_kebidanan = false;
                    akses.penilaian_awal_keperawatan_kebidanan = false;
                    akses.ikhtisar_perawatan_hiv = false;
                    akses.survey_kepuasan = false;
                    akses.kemenkes_kanker = false;
                    akses.aktivasi_bridging = false;
                    akses.operator_antrian = false;
                    akses.penilaian_awal_medis_ralan_tht = false;
                    akses.rekam_psikologis = false;
                    akses.penilaian_pasien_geriatri = false;
                    akses.penilaian_awal_medis_ralan_mata = false;
                    akses.surat_sakit = false;
                    akses.surat_keterangan_kir_mcu = false;
                    akses.asesmen_medik_dewasa_ranap = false;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public static void setNMLoket(String nmrawat) {
        if (nmrawat.equals("ralan")) {
            akses.jenisLoket = "Rawat Jalan";
        } else if (nmrawat.equals("ranap")) {
            akses.jenisLoket = "Rawat Inap";
        } else {
            akses.jenisLoket = "";
        }
    }

    public static void setNomorLoket(String noloket) {
        if (noloket.equals("1")) {
            akses.nomorLoket = "1";
        } else if (noloket.equals("2")) {
            akses.nomorLoket = "2";
        } else if (noloket.equals("3")) {
            akses.nomorLoket = "3";
        } else if (noloket.equals("4")) {
            akses.nomorLoket = "4";
        } else if (noloket.equals("5")) {
            akses.nomorLoket = "5";
        } else if (noloket.equals("6")) {
            akses.nomorLoket = "6";
        } else if (noloket.equals("")) {
            akses.nomorLoket = "";
        }
    }

    public static int getjml1() {
        return akses.jml1;
    }

    public static int getjml2() {
        return akses.jml2;
    }

    public static boolean getadmin() {
        return akses.admin;
    }    
    
    public static boolean getuser() {
        return akses.user;
    }

    public static boolean getvakum() {
        return akses.vakum;
    }

    public static boolean getaplikasi() {
        return akses.aplikasi;
    }

    public static boolean getpenyakit() {
        return akses.penyakit;
    }

    public static boolean getobat_penyakit() {
        return akses.obat_penyakit;
    }

    public static boolean getdokter() {
        return akses.dokter;
    }

    public static boolean getjadwal_praktek() {
        return akses.jadwal_praktek;
    }

    public static boolean getpetugas() {
        return akses.petugas;
    }

    public static boolean getpasien() {
        return akses.pasien;
    }

    public static boolean getregistrasi() {
        return akses.registrasi;
    }

    public static boolean gettindakan_ralan() {
        return akses.tindakan_ralan;
    }

    public static boolean getkamar_inap() {
        return akses.kamar_inap;
    }

    public static boolean gettindakan_ranap() {
        return akses.tindakan_ranap;
    }

    public static boolean getoperasi() {
        return akses.operasi;
    }

    public static boolean getrujukan_keluar() {
        return akses.rujukan_keluar;
    }

    public static boolean getrujukan_masuk() {
        return akses.rujukan_masuk;
    }

    public static boolean getberi_obat() {
        return akses.beri_obat;
    }

    public static boolean getresep_pulang() {
        return akses.resep_pulang;
    }

    public static boolean getpasien_meninggal() {
        return akses.pasien_meninggal;
    }

    public static boolean getdiet_pasien() {
        return akses.diet_pasien;
    }

    public static boolean getkelahiran_bayi() {
        return akses.kelahiran_bayi;
    }

    public static boolean getperiksa_lab() {
        return akses.periksa_lab;
    }

    public static boolean getperiksa_radiologi() {
        return akses.periksa_radiologi;
    }

    public static boolean getkasir_ralan() {
        return akses.kasir_ralan;
    }

    public static boolean getdeposit_pasien() {
        return akses.deposit_pasien;
    }

    public static boolean getpiutang_pasien() {
        return akses.piutang_pasien;
    }

    public static boolean getpeminjaman_berkas() {
        return akses.peminjaman_berkas;
    }

    public static boolean getbarcode() {
        return akses.barcode;
    }

    public static boolean getpresensi_harian() {
        return akses.presensi_harian;
    }

    public static boolean getpresensi_bulanan() {
        return akses.presensi_bulanan;
    }

    public static boolean getpegawai_admin() {
        return akses.pegawai_admin;
    }

    public static boolean getpegawai_user() {
        return akses.pegawai_user;
    }

    public static boolean getsuplier() {
        return akses.suplier;
    }

    public static boolean getsatuan_barang() {
        return akses.satuan_barang;
    }

    public static boolean getkonversi_satuan() {
        return akses.konversi_satuan;
    }

    public static boolean getjenis_barang() {
        return akses.jenis_barang;
    }

    public static boolean getobat() {
        return akses.obat;
    }

    public static boolean getstok_opname_obat() {
        return akses.stok_opname_obat;
    }

    public static boolean getstok_obat_pasien() {
        return akses.stok_obat_pasien;
    }

    public static boolean getpengadaan_obat() {
        return akses.pengadaan_obat;
    }

    public static boolean getpemesanan_obat() {
        return akses.pemesanan_obat;
    }

    public static boolean getpenjualan_obat() {
        return akses.penjualan_obat;
    }

    public static void setpenjualan_obatfalse() {
        akses.penjualan_obat = false;
    }

    public static boolean getpiutang_obat() {
        return akses.piutang_obat;
    }

    public static boolean getretur_ke_suplier() {
        return akses.retur_ke_suplier;
    }

    public static boolean getretur_dari_pembeli() {
        return akses.retur_dari_pembeli;
    }

    public static boolean getretur_obat_ranap() {
        return akses.retur_obat_ranap;
    }

    public static boolean getretur_piutang_pasien() {
        return akses.retur_piutang_pasien;
    }

    public static boolean getkeuntungan_penjualan() {
        return akses.keuntungan_penjualan;
    }

    public static boolean getkeuntungan_beri_obat() {
        return akses.keuntungan_beri_obat;
    }

    public static boolean getsirkulasi_obat() {
        return akses.sirkulasi_obat;
    }

    public static boolean getipsrs_barang() {
        return akses.ipsrs_barang;
    }

    public static boolean getipsrs_pengadaan_barang() {
        return akses.ipsrs_pengadaan_barang;
    }

    public static boolean getipsrs_stok_keluar() {
        return akses.ipsrs_stok_keluar;
    }

    public static boolean getipsrs_rekap_pengadaan() {
        return akses.ipsrs_rekap_pengadaan;
    }

    public static boolean getipsrs_rekap_stok_keluar() {
        return akses.ipsrs_rekap_stok_keluar;
    }

    public static boolean getipsrs_pengeluaran_harian() {
        return akses.ipsrs_pengeluaran_harian;
    }

    public static boolean getipsrs_jenis_barang() {
        return akses.ipsrs_jenis_barang;
    }

    public static boolean getinventaris_jenis() {
        return akses.inventaris_jenis;
    }

    public static boolean getinventaris_kategori() {
        return akses.inventaris_kategori;
    }

    public static boolean getinventaris_merk() {
        return akses.inventaris_merk;
    }

    public static boolean getinventaris_ruang() {
        return akses.inventaris_ruang;
    }

    public static boolean getinventaris_produsen() {
        return akses.inventaris_produsen;
    }

    public static boolean getinventaris_koleksi() {
        return akses.inventaris_koleksi;
    }

    public static boolean getinventaris_inventaris() {
        return akses.inventaris_inventaris;
    }

    public static boolean getinventaris_sirkulasi() {
        return akses.inventaris_sirkulasi;
    }

    public static boolean getparkir_jenis() {
        return akses.parkir_jenis;
    }

    public static boolean getparkir_in() {
        return akses.parkir_in;
    }

    public static boolean getparkir_out() {
        return akses.parkir_out;
    }

    public static boolean getparkir_rekap_harian() {
        return akses.parkir_rekap_harian;
    }

    public static boolean getparkir_rekap_bulanan() {
        return akses.parkir_rekap_bulanan;
    }

    public static boolean getinformasi_kamar() {
        return akses.informasi_kamar;
    }

    public static boolean getharian_tindakan_poli() {
        return akses.harian_tindakan_poli;
    }

    public static boolean getobat_per_poli() {
        return akses.obat_per_poli;
    }

    public static boolean getobat_per_kamar() {
        return akses.obat_per_kamar;
    }

    public static boolean getobat_per_dokter_ralan() {
        return akses.obat_per_dokter_ralan;
    }

    public static boolean getobat_per_dokter_ranap() {
        return akses.obat_per_dokter_ranap;
    }

    public static boolean getharian_dokter() {
        return akses.harian_dokter;
    }

    public static boolean getbulanan_dokter() {
        return akses.bulanan_dokter;
    }

    public static boolean getharian_paramedis() {
        return akses.harian_paramedis;
    }

    public static boolean getbulanan_paramedis() {
        return akses.bulanan_paramedis;
    }

    public static boolean getpembayaran_ralan() {
        return akses.pembayaran_ralan;
    }

    public static boolean getpembayaran_ranap() {
        return akses.pembayaran_ranap;
    }

    public static boolean getrekap_pembayaran_ralan() {
        return akses.rekap_pembayaran_ralan;
    }

    public static boolean getrekap_pembayaran_ranap() {
        return akses.rekap_pembayaran_ranap;
    }

    public static boolean gettagihan_masuk() {
        return akses.tagihan_masuk;
    }

    public static boolean gettambahan_biaya() {
        return akses.tambahan_biaya;
    }

    public static boolean getpotongan_biaya() {
        return akses.potongan_biaya;
    }

    public static boolean getresep_obat() {
        return akses.resep_obat;
    }

    public static boolean getresume_pasien() {
        return akses.resume_pasien;
    }

    public static boolean getpenyakit_ralan() {
        return akses.penyakit_ralan;
    }

    public static boolean getpenyakit_ranap() {
        return akses.penyakit_ranap;
    }

    public static boolean getkamar() {
        return akses.kamar;
    }

    public static boolean gettarif_ralan() {
        return akses.tarif_ralan;
    }

    public static boolean gettarif_ranap() {
        return akses.tarif_ranap;
    }

    public static boolean gettarif_lab() {
        return akses.tarif_lab;
    }

    public static boolean gettarif_radiologi() {
        return akses.tarif_radiologi;
    }

    public static boolean gettarif_operasi() {
        return akses.tarif_operasi;
    }

    public static boolean getakun_rekening() {
        return akses.akun_rekening;
    }

    public static boolean getrekening_tahun() {
        return akses.rekening_tahun;
    }

    public static boolean getposting_jurnal() {
        return akses.posting_jurnal;
    }

    public static boolean getbuku_besar() {
        return akses.buku_besar;
    }

    public static boolean getcashflow() {
        return akses.cashflow;
    }

    public static boolean getkeuangan() {
        return akses.keuangan;
    }

    public static boolean getpengeluaran() {
        return akses.pengeluaran;
    }

    public static boolean getsetup_pjlab() {
        return akses.setup_pjlab;
    }

    public static boolean getsetup_otolokasi() {
        return akses.setup_otolokasi;
    }

    public static boolean getsetup_jam_kamin() {
        return akses.setup_jam_kamin;
    }

    public static boolean getsetup_embalase() {
        return akses.setup_embalase;
    }

    public static boolean gettracer_login() {
        return akses.tracer_login;
    }

    public static boolean getdisplay() {
        return akses.display;
    }

    public static boolean getset_harga_obat() {
        return akses.set_harga_obat;
    }

    public static boolean getset_penggunaan_tarif() {
        return akses.set_penggunaan_tarif;
    }

    public static boolean getset_oto_ralan() {
        return akses.set_oto_ralan;
    }

    public static boolean getbiaya_harian() {
        return akses.biaya_harian;
    }

    public static boolean getbiaya_masuk_sekali() {
        return akses.biaya_masuk_sekali;
    }

    public static boolean getset_no_rm() {
        return akses.set_no_rm;
    }

    public static boolean getbilling_ralan() {
        return akses.billing_ralan;
    }

    public static boolean getbilling_ranap() {
        return akses.billing_ranap;
    }

    public static String getkode() {
        return akses.kode;
    }
    
    public static String getJenisLoket() {
        return akses.jenisLoket;
    }
    
    public static String getNomorLoket() {
        return akses.nomorLoket;
    }

    public static void setkdbangsal(String kdbangsal) {
        akses.kdbangsal = kdbangsal;
    }

    public static String getkdbangsal() {
        return akses.kdbangsal;
    }

    public static void setform(String form) {
        akses.form = form;
    }

    public static String getform() {
        return akses.form;
    }

    public static void setnamauser(String namauser) {
        akses.namauser = namauser;
    }

    public static String getnamauser() {
        return akses.namauser;
    }

    public static void setstatus(boolean status) {
        akses.status = status;
    }

    public static boolean getstatus() {
        return akses.status;
    }

    public static boolean getjm_ranap_dokter() {
        return akses.jm_ranap_dokter;
    }

    public static boolean getigd() {
        return akses.igd;
    }

    public static boolean getbarcoderalan() {
        return akses.barcoderalan;
    }

    public static boolean getbarcoderanap() {
        return akses.barcoderanap;
    }

    public static boolean getset_harga_obat_ralan() {
        return akses.set_harga_obat_ralan;
    }

    public static boolean getset_harga_obat_ranap() {
        return akses.set_harga_obat_ranap;
    }

    public static boolean getpenyakit_pd3i() {
        return akses.penyakit_pd3i;
    }

    public static boolean getsurveilans_pd3i() {
        return akses.surveilans_pd3i;
    }

    public static boolean getsurveilans_ralan() {
        return akses.surveilans_ralan;
    }

    public static boolean getdiagnosa_pasien() {
        return akses.diagnosa_pasien;
    }

    public static boolean getsurveilans_ranap() {
        return akses.surveilans_ranap;
    }

    public static boolean getpny_takmenular_ranap() {
        return akses.pny_takmenular_ranap;
    }

    public static boolean getpny_takmenular_ralan() {
        return akses.pny_takmenular_ralan;
    }

    public static void setnamars(String namars) {
        akses.namars = namars;
    }

    public static void setalamatrs(String alamatrs) {
        akses.alamatrs = alamatrs;
    }

    public static void setkabupatenrs(String kabupatenrs) {
        akses.kabupatenrs = kabupatenrs;
    }

    public static void setpropinsirs(String propinsirs) {
        akses.propinsirs = propinsirs;
    }

    public static void setkontakrs(String kontakrs) {
        akses.kontakrs = kontakrs;
    }

    public static void setemailrs(String emailrs) {
        akses.emailrs = emailrs;
    }

    public static String getnamars() {
        return akses.namars;
    }

    public static String getalamatrs() {
        return akses.alamatrs;
    }

    public static String getkabupatenrs() {
        return akses.kabupatenrs;
    }

    public static String getpropinsirs() {
        return akses.propinsirs;
    }

    public static String getkontakrs() {
        return akses.kontakrs;
    }

    public static String getemailrs() {
        return akses.emailrs;
    }

    public static boolean getkunjungan_ralan() {
        return akses.kunjungan_ralan;
    }

    public static boolean getrl32() {
        return akses.rl32;
    }

    public static boolean getrl33() {
        return akses.rl33;
    }

    public static boolean getrl37() {
        return akses.rl37;
    }

    public static boolean getrl38() {
        return akses.rl38;
    }

    public static boolean getharian_tindakan_dokter() {
        return akses.harian_tindakan_dokter;
    }

    public static boolean getsms() {
        return akses.sms;
    }

    public static boolean getsidikjari() {
        return akses.sidikjari;
    }

    public static boolean getjam_masuk() {
        return akses.jam_masuk;
    }

    public static boolean getjadwal_pegawai() {
        return akses.jadwal_pegawai;
    }

    public static boolean getparkir_barcode() {
        return akses.parkir_barcode;
    }

    public static boolean getset_nota() {
        return akses.set_nota;
    }

    public static boolean getdpjp_ranap() {
        return akses.dpjp_ranap;
    }

    public static boolean getmutasi_barang() {
        return akses.mutasi_barang;
    }

    public static boolean getrl34() {
        return akses.rl34;
    }

    public static boolean getrl36() {
        return akses.rl36;
    }

    public static boolean getfee_visit_dokter() {
        return akses.fee_visit_dokter;
    }

    public static boolean getfee_bacaan_ekg() {
        return akses.fee_bacaan_ekg;
    }

    public static boolean getfee_rujukan_rontgen() {
        return akses.fee_rujukan_rontgen;
    }

    public static boolean getfee_rujukan_ranap() {
        return akses.fee_rujukan_ranap;
    }

    public static boolean getfee_ralan() {
        return akses.fee_ralan;
    }

    public static boolean getakun_bayar() {
        return akses.akun_bayar;
    }

    public static boolean getbayar_pemesanan_obat() {
        return akses.bayar_pemesanan_obat;
    }

    public static boolean getobat_per_dokter_peresep() {
        return akses.obat_per_dokter_peresep;
    }

    public static boolean getpemasukan_lain() {
        return akses.pemasukan_lain;
    }

    public static boolean getpengaturan_rekening() {
        return akses.pengaturan_rekening;
    }

    public static boolean getclosing_kasir() {
        return akses.closing_kasir;
    }

    public static boolean getketerlambatan_presensi() {
        return akses.keterlambatan_presensi;
    }

    public static boolean getset_harga_kamar() {
        return akses.set_harga_kamar;
    }

    public static boolean getrekap_per_shift() {
        return akses.rekap_per_shift;
    }

    public static boolean getbpjs_cek_nik() {
        return akses.bpjs_cek_nik;
    }

    public static boolean getbpjs_cek_kartu() {
        return akses.bpjs_cek_kartu;
    }

    public static boolean getobat_per_cara_bayar() {
        return akses.obat_per_cara_bayar;
    }

    public static boolean getkunjungan_ranap() {
        return akses.kunjungan_ranap;
    }

    public static boolean getbayar_piutang() {
        return akses.bayar_piutang;
    }

    public static boolean getpayment_point() {
        return akses.payment_point;
    }

    public static boolean getbpjs_cek_nomor_rujukan() {
        return akses.bpjs_cek_nomor_rujukan;
    }

    public static boolean geticd9() {
        return akses.icd9;
    }

    public static boolean getdarurat_stok() {
        return akses.darurat_stok;
    }

    public static boolean getretensi_rm() {
        return akses.retensi_rm;
    }

    public static boolean gettemporary_presensi() {
        return akses.temporary_presensi;
    }

    public static boolean getjurnal_harian() {
        return akses.jurnal_harian;
    }

    public static boolean getsirkulasi_obat2() {
        return akses.sirkulasi_obat2;
    }

    public static boolean getedit_registrasi() {
        return akses.edit_registrasi;
    }

    public static boolean getbpjs_referensi_diagnosa() {
        return akses.bpjs_referensi_diagnosa;
    }

    public static boolean getbpjs_referensi_poli() {
        return akses.bpjs_referensi_poli;
    }

    public static boolean getindustrifarmasi() {
        return akses.industrifarmasi;
    }

    public static boolean getharian_js() {
        return akses.harian_js;
    }

    public static boolean getbulanan_js() {
        return akses.bulanan_js;
    }

    public static boolean getharian_paket_bhp() {
        return akses.harian_paket_bhp;
    }

    public static boolean getbulanan_paket_bhp() {
        return akses.bulanan_paket_bhp;
    }

    public static boolean getpiutang_pasien2() {
        return akses.piutang_pasien2;
    }

    public static boolean getbpjs_referensi_faskes() {
        return akses.bpjs_referensi_faskes;
    }

    public static boolean getbpjs_sep() {
        return akses.bpjs_sep;
    }

    public static boolean getpengambilan_utd() {
        return akses.pengambilan_utd;
    }

    public static boolean gettarif_utd() {
        return akses.tarif_utd;
    }

    public static boolean getpengambilan_utd2() {
        return akses.pengambilan_utd2;
    }

    public static boolean getutd_medis_rusak() {
        return akses.utd_medis_rusak;
    }

    public static boolean getpengambilan_penunjang_utd() {
        return akses.pengambilan_penunjang_utd;
    }

    public static boolean getpengambilan_penunjang_utd2() {
        return akses.pengambilan_penunjang_utd2;
    }

    public static boolean getutd_penunjang_rusak() {
        return akses.utd_penunjang_rusak;
    }

    public static boolean getsuplier_penunjang() {
        return akses.suplier_penunjang;
    }

    public static boolean getutd_donor() {
        return akses.utd_donor;
    }

    public static boolean getbpjs_monitoring_klaim() {
        return akses.bpjs_monitoring_klaim;
    }

    public static boolean getutd_cekal_darah() {
        return akses.utd_cekal_darah;
    }

    public static boolean getutd_komponen_darah() {
        return akses.utd_komponen_darah;
    }

    public static boolean getutd_stok_darah() {
        return akses.utd_stok_darah;
    }

    public static boolean getutd_pemisahan_darah() {
        return akses.utd_pemisahan_darah;
    }

    public static boolean getharian_kamar() {
        return akses.harian_kamar;
    }

    public static boolean getrincian_piutang_pasien() {
        return akses.rincian_piutang_pasien;
    }

    public static boolean getkeuntungan_beri_obat_nonpiutang() {
        return akses.keuntungan_beri_obat_nonpiutang;
    }

    public static boolean getreklasifikasi_ralan() {
        return akses.reklasifikasi_ralan;
    }

    public static boolean getreklasifikasi_ranap() {
        return akses.reklasifikasi_ranap;
    }

    public static boolean getutd_penyerahan_darah() {
        return akses.utd_penyerahan_darah;
    }

    public static void setutd_penyerahan_darahfalse() {
        akses.utd_penyerahan_darah = false;
    }

    public static boolean gethutang_obat() {
        return akses.hutang_obat;
    }

    public static boolean getriwayat_obat_alkes_bhp() {
        return akses.riwayat_obat_alkes_bhp;
    }

    public static boolean getsensus_harian_poli() {
        return akses.sensus_harian_poli;
    }

    public static boolean getrl4a() {
        return akses.rl4a;
    }

    public static boolean getaplicare_referensi_kamar() {
        return akses.aplicare_referensi_kamar;
    }

    public static boolean getaplicare_ketersediaan_kamar() {
        return akses.aplicare_ketersediaan_kamar;
    }

    public static boolean getinacbg_klaim_baru_otomatis() {
        return akses.inacbg_klaim_baru_otomatis;
    }

    public static boolean getinacbg_klaim_baru_manual() {
        return akses.inacbg_klaim_baru_manual;
    }
    
    public static boolean getinacbg_klaim_baru_manual2() {
        return akses.inacbg_klaim_baru_manual2;
    }

    public static boolean getinacbg_coder_nik() {
        return akses.inacbg_coder_nik;
    }

    public static boolean getmutasi_berkas() {
        return akses.mutasi_berkas;
    }

    public static boolean getakun_piutang() {
        return akses.akun_piutang;
    }

    public static boolean getharian_kso() {
        return akses.harian_kso;
    }

    public static boolean getbulanan_kso() {
        return akses.bulanan_kso;
    }

    public static boolean getharian_menejemen() {
        return akses.harian_menejemen;
    }

    public static boolean getbulanan_menejemen() {
        return akses.bulanan_menejemen;
    }

    public static boolean getinhealth_cek_eligibilitas() {
        return akses.inhealth_cek_eligibilitas;
    }

    public static boolean getinhealth_referensi_jenpel_ruang_rawat() {
        return akses.inhealth_referensi_jenpel_ruang_rawat;
    }

    public static boolean getinhealth_referensi_poli() {
        return akses.inhealth_referensi_poli;
    }

    public static boolean getinhealth_referensi_faskes() {
        return akses.inhealth_referensi_faskes;
    }

    public static boolean getinhealth_sjp() {
        return akses.inhealth_sjp;
    }

    public static boolean getpiutang_ralan() {
        return akses.piutang_ralan;
    }

    public static boolean getpiutang_ranap() {
        return akses.piutang_ranap;
    }

    public static boolean getdetail_piutang_penjab() {
        return akses.detail_piutang_penjab;
    }

    public static boolean getlama_pelayanan_ralan() {
        return akses.lama_pelayanan_ralan;
    }

    public static boolean getcatatan_pasien() {
        return akses.catatan_pasien;
    }

    public static boolean getrl4b() {
        return akses.rl4b;
    }

    public static boolean getrl4asebab() {
        return akses.rl4asebab;
    }

    public static boolean getrl4bsebab() {
        return akses.rl4bsebab;
    }

    public static boolean getdata_HAIs() {
        return akses.data_HAIs;
    }

    public static boolean getharian_HAIs() {
        return akses.harian_HAIs;
    }

    public static boolean getbulanan_HAIs() {
        return akses.bulanan_HAIs;
    }

    public static boolean gethitung_bor() {
        return akses.hitung_bor;
    }

    public static boolean getperusahaan_pasien() {
        return akses.perusahaan_pasien;
    }

    public static boolean getresep_dokter() {
        return akses.resep_dokter;
    }

    public static void setresep_dokterfalse() {
        akses.resep_dokter = false;
    }

    public static boolean getlama_pelayanan_apotek() {
        return akses.lama_pelayanan_apotek;
    }

    public static boolean gethitung_alos() {
        return akses.hitung_alos;
    }

    public static boolean getdetail_tindakan() {
        return akses.detail_tindakan;
    }

    public static boolean getrujukan_poli_internal() {
        return akses.rujukan_poli_internal;
    }

    public static boolean getrekap_poli_anak() {
        return akses.rekap_poli_anak;
    }

    public static boolean getgrafik_kunjungan_poli() {
        return akses.grafik_kunjungan_poli;
    }

    public static boolean getgrafik_kunjungan_perdokter() {
        return akses.grafik_kunjungan_perdokter;
    }

    public static boolean getgrafik_kunjungan_perpekerjaan() {
        return akses.grafik_kunjungan_perpekerjaan;
    }

    public static boolean getgrafik_kunjungan_perpendidikan() {
        return akses.grafik_kunjungan_perpendidikan;
    }

    public static boolean getgrafik_kunjungan_pertahun() {
        return akses.grafik_kunjungan_pertahun;
    }

    public static boolean getberkas_digital_perawatan() {
        return akses.berkas_digital_perawatan;
    }

    public static boolean getpenyakit_menular_ranap() {
        return akses.penyakit_menular_ranap;
    }

    public static boolean getpenyakit_menular_ralan() {
        return akses.penyakit_menular_ralan;
    }

    public static boolean getgrafik_kunjungan_perbulan() {
        return akses.grafik_kunjungan_perbulan;
    }

    public static boolean getgrafik_kunjungan_pertanggal() {
        return akses.grafik_kunjungan_pertanggal;
    }

    public static boolean getgrafik_kunjungan_demografi() {
        return akses.grafik_kunjungan_demografi;
    }

    public static boolean getgrafik_kunjungan_statusdaftartahun() {
        return akses.grafik_kunjungan_statusdaftartahun;
    }

    public static boolean getgrafik_kunjungan_statusdaftartahun2() {
        return akses.grafik_kunjungan_statusdaftartahun2;
    }

    public static boolean getgrafik_kunjungan_statusdaftarbulan() {
        return akses.grafik_kunjungan_statusdaftarbulan;
    }

    public static boolean getgrafik_kunjungan_statusdaftarbulan2() {
        return akses.grafik_kunjungan_statusdaftarbulan2;
    }

    public static boolean getgrafik_kunjungan_statusdaftartanggal() {
        return akses.grafik_kunjungan_statusdaftartanggal;
    }

    public static boolean getgrafik_kunjungan_statusdaftartanggal2() {
        return akses.grafik_kunjungan_statusdaftartanggal2;
    }

    public static boolean getgrafik_kunjungan_statusbataltahun() {
        return akses.grafik_kunjungan_statusbataltahun;
    }

    public static boolean getgrafik_kunjungan_statusbatalbulan() {
        return akses.grafik_kunjungan_statusbatalbulan;
    }

    public static boolean getpcare_cek_penyakit() {
        return akses.pcare_cek_penyakit;
    }

    public static boolean getgrafik_kunjungan_statusbataltanggal() {
        return akses.grafik_kunjungan_statusbataltanggal;
    }

    public static boolean getkategori_barang() {
        return akses.kategori_barang;
    }

    public static boolean getgolongan_barang() {
        return akses.golongan_barang;
    }
    
    public static boolean getpemberian_obat_pertanggal() {
        return akses.pemberian_obat_pertanggal;
    }

    public static boolean getpenjualan_obat_pertanggal() {
        return akses.penjualan_obat_pertanggal;
    }
	public static boolean getbpjs_rujukan_keluar(){return akses.bpjs_rujukan_vclaim;}
        public static boolean getskdp_bpjs(){return akses.skdp_bpjs;}
        public static boolean getbooking_registrasi(){return akses.booking_registrasi;}
        public static boolean getbpjs_cek_riwayat_rujukan_pcare(){return akses.bpjs_cek_riwayat_rujukan_pcare;}
        public static boolean getbpjs_cek_riwayat_rujukan_rs(){return akses.bpjs_cek_riwayat_rujukan_rs;}
        public static boolean getbpjs_cek_rujukan_kartu_rs(){return akses.bpjs_cek_rujukan_kartu_rs;}
        public static boolean getbpjs_cek_tgl_rujukan(){return akses.bpjs_cek_tgl_rujukan;}
        public static boolean getbpjs_cek_no_rujukan_rs(){return akses.bpjs_cek_no_rujukan_rs;}
        public static boolean getbpjs_cek_rujukan_kartu_pcare(){return akses.bpjs_cek_rujukan_kartu_pcare;}
        public static boolean getbpjs_cek_referensi_kelas_rawat(){return akses.bpjs_cek_referensi_kelas_rawat;}
        public static boolean getbpjs_cek_referensi_prosedur(){return akses.bpjs_cek_referensi_prosedur;}
        public static boolean getbpjs_cek_referensi_dpjp(){return akses.bpjs_cek_referensi_dpjp;}
        public static boolean getbpjs_cek_referensi_dokter(){return akses.bpjs_cek_referensi_dokter;}
        public static boolean getbpjs_cek_referensi_spesialistik(){return akses.bpjs_cek_referensi_spesialistik;}
        public static boolean getbpjs_cek_referensi_ruang_rawat(){return akses.bpjs_cek_referensi_ruang_rawat;}
        public static boolean getbpjs_cek_referensi_cara_keluar(){return akses.bpjs_cek_referensi_cara_keluar;}
        public static boolean getbpjs_cek_referensi_pasca_pulang(){return akses.bpjs_cek_referensi_pasca_pulang;}
        public static boolean getbpjs_cek_referensi_propinsi(){return akses.bpjs_cek_referensi_propinsi;}
        public static boolean getbpjs_cek_referensi_kabupaten(){return akses.bpjs_cek_referensi_kabupaten;}
        public static boolean getbpjs_cek_referensi_kecamatan(){return akses.bpjs_cek_referensi_kecamatan;}
        public static boolean getpermintaan_lab(){return akses.permintaan_lab;}
        public static boolean getpermintaan_radiologi(){return akses.permintaan_radiologi;}
        public static boolean getselisih_tarif_bpjs(){return akses.selisih_tarif_bpjs;}
        public static boolean getedit_data_kematian(){return akses.edit_data_kematian;}
        public static boolean getbridging_jamkesda(){return akses.bridging_jamkesda;}
        public static boolean getmasuk_pindah_pulang_inap(){return akses.masuk_pindah_pulang_inap;}
        public static boolean getmasuk_pindah_inap(){return akses.masuk_pindah_inap;}
        public static boolean getjumlah_macam_diet(){return akses.jumlah_macam_diet;}
        public static boolean getjumlah_porsi_diet(){return akses.jumlah_porsi_diet;}
        public static boolean getstatusgizi(){return akses.status_gizi;}
        public static boolean getgizi_buruk(){return akses.gizi_buruk;}
        public static boolean getmaster_faskes(){return akses.master_faskes;}
        public static boolean getsetstatusralan(){return akses.setstatusralan;}
        public static boolean gettelusurpasien(){return akses.telusurpasien;}
        public static boolean getsisrute_rujukan_keluar(){return akses.sisrute_rujukan_keluar;}
        public static void setAktif(boolean status){akses.aktif=status;}
        public static boolean getAktif(){return akses.aktif;}
        public static boolean getsisrute_rujukan_masuk(){return akses.sisrute_rujukan_masuk;}
        public static boolean getsisrute_referensi_diagnosa(){return akses.sisrute_referensi_diagnosa;}
        public static boolean getsisrute_referensi_alasanrujuk(){return akses.sisrute_referensi_alasanrujuk;}
        public static boolean getsisrute_referensi_faskes(){return akses.sisrute_referensi_faskes;}
        public static boolean getbarang_cssd(){return akses.barang_cssd;}
        public static boolean getstatus_pulang_inap(){return akses.status_pulang_inap;}
        public static boolean getdata_persalinan(){return akses.data_persalinan;}
        public static boolean getdata_ponek(){return akses.data_ponek;}
        public static boolean getreg_boking_kasir(){return akses.reg_boking_kasir;}
        public static boolean getbahasa_pasien(){return akses.bahasa_pasien;}
        public static boolean getsuku_bangsa(){return akses.suku_bangsa;}        
        public static boolean getharianhaisinap(){return akses.harian_hais_inap;}
        public static boolean getharianhaisjalan(){return akses.harian_hais_jalan;}
        public static boolean getbulananhaisinap(){return akses.bulanan_hais_inap;}
        public static boolean getbulananhaisjalan(){return akses.bulanan_hais_jalan;}
        public static boolean getringkasanpulangranap(){return akses.ringkasan_pulang_ranap;}
        public static boolean getlaporanfarmasi(){return akses.laporan_farmasi;}
        public static boolean getmaster_masalah_keperawatan(){return akses.master_masalah_keperawatan;}
        public static boolean getpenilaian_awal_keperawatan_ralan(){return akses.penilaian_awal_keperawatan_ralan;}
        public static boolean getmaster_triase_skala1(){return akses.master_triase_skala1;}
        public static boolean getmaster_triase_skala2(){return akses.master_triase_skala2;}        
        public static boolean getmaster_triase_skala3(){return akses.master_triase_skala3;}
        public static boolean getmaster_triase_skala4(){return akses.master_triase_skala4;}
        public static boolean getmaster_triase_skala5(){return akses.master_triase_skala5;}
        public static boolean getdata_triase_igd(){return akses.data_triase_igd;}
        public static boolean getmaster_triase_pemeriksaan(){return akses.master_triase_pemeriksaan;}
        public static boolean getmaster_triase_macamkasus(){return akses.master_triase_macamkasus;}
        public static boolean getmaster_cara_bayar(){return akses.master_cara_bayar;}
        public static boolean getstatus_kerja_dokter(){return akses.status_kerja_dokter;}
        public static boolean getpasien_corona(){return akses.pasien_corona;}
        public static boolean getdiagnosa_pasien_corona(){return akses.diagnosa_pasien_corona;}
        public static boolean getperawatan_pasien_corona(){return akses.perawatan_pasien_corona;}
        public static boolean getassesmen_gizi_harian(){return akses.assesmen_gizi_harian;}
        public static boolean getassesmen_gizi_ulang(){return akses.assesmen_gizi_ulang;}
        public static boolean gettombolnota_billing(){return akses.tombol_nota_billing;}
        public static boolean gettombolsimpan_hasil_rad(){return akses.tombol_simpan_hasil_rad;}
        public static boolean getmonev_asuhan_gizi(){return akses.monev_asuhan_gizi;}
        public static boolean getinacbg_klaim_raza(){return akses.inacbg_klaim_raza;}
        public static boolean getpengajuan_klaim_raza(){return akses.pengajuan_klaim_inacbg_raza;}
        public static boolean getcopy_pemeriksaan_dr_kepetugas(){return akses.copy_pemeriksaan_dokter_kepetugas_ralan;}
        public static boolean getjkn_belum_diproses_klaim(){return akses.jkn_belum_diproses_klaim;}
        public static boolean getinput_kode_icd(){return akses.input_kode_icd;}
        public static boolean getkendali_mutu_kendali_biaya_inacbg(){return akses.kendali_Mutu_kendali_Biaya_INACBG;}
        public static boolean getdashboard_eResep(){return akses.dashboard_eResep;}
        public static boolean getbpjsSEPinternal(){return akses.bpjs_sep_internal;}
        public static boolean getkemenkes_sitt(){return akses.kemenkes_sitt;}
        public static boolean getRencanaKontrolJKN(){return akses.rencana_kontrol_jkn;}
        public static boolean getSPRIJKN(){return akses.spri_jkn;}
        public static boolean getHapusSEP(){return akses.hapus_sep;}
        public static boolean getpenilaian_awal_medis_ralan_kebidanan(){return akses.penilaian_awal_medis_ralan_kebidanan;}
        public static boolean getpenilaian_awal_keperawatan_kebidanan(){return akses.penilaian_awal_keperawatan_kebidanan;}
        public static boolean getikhtisar_perawatan_hiv(){return akses.ikhtisar_perawatan_hiv;}
        public static boolean getsurvey_kepuasan(){return akses.survey_kepuasan;}
        public static boolean getkemenkes_kanker(){return akses.kemenkes_kanker;}
        public static boolean getset_bridging(){return akses.aktivasi_bridging;}
        public static boolean getOperator_antrian(){return akses.operator_antrian;}
        public static boolean getpenilaian_awal_medis_ralan_tht(){return akses.penilaian_awal_medis_ralan_tht;}
        public static boolean getrekam_psikologis(){return akses.rekam_psikologis;}
        public static boolean getpenilaian_pasien_geriatri(){return akses.penilaian_pasien_geriatri;}
        public static boolean getpenilaian_awal_medis_ralan_mata(){return akses.penilaian_awal_medis_ralan_mata;}
        public static boolean getsurat_sakit(){return akses.surat_sakit;}
        public static boolean getsurat_keterangan_kir_mcu(){return akses.surat_keterangan_kir_mcu;}
        public static boolean getasesmen_medik_dewasa_ranap(){return akses.asesmen_medik_dewasa_ranap;}
}
