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
package simrskhanza;

import bridging.BPJSCekNIK;
import bridging.BPJSCekNoKartu;
import bridging.BPJSNik;
import bridging.BPJSPeserta;
import bridging.CoronaPasien;
import fungsi.WarnaTable;
import fungsi.batasInput;
import grafikanalisa.grafikjkel;
import grafikanalisa.grafikpasienperagama;
import grafikanalisa.grafikpasienperpekerjaaan;
import grafikanalisa.grafiksql;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import org.bouncycastle.util.Strings;
import simrskhanza.DlgCatatan;
import simrskhanza.DlgIKBBayi;
import simrskhanza.DlgKabupaten;
import simrskhanza.DlgKecamatan;
import simrskhanza.DlgKelurahan;
import simrskhanza.DlgPenanggungJawab;
import simrskhanza.DlgResumePerawatan;

/**
 *
 * @author igos
 */
public class DlgPasien extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    public DlgPenanggungJawab penjab = new DlgPenanggungJawab(null, false);
    public DlgKabupaten kab = new DlgKabupaten(null, false);
    public DlgKecamatan kec = new DlgKecamatan(null, false);
    public DlgKelurahan kel = new DlgKelurahan(null, false);
    public DlgBahasa bahasa = new DlgBahasa(null, false);
    public DlgSuku suku = new DlgSuku(null, false);
    private int pilih = 0, z = 0, j = 0, p_no_ktp = 0, p_tmp_lahir = 0, p_nm_ibu = 0, p_alamat = 0,
            p_pekerjaan = 0, p_no_tlp = 0, p_umur = 0, p_namakeluarga = 0, p_no_peserta = 0,
            p_kelurahan = 0, p_kecamatan = 0, p_kabupaten = 0, p_pekerjaanpj = 0,
            p_alamatpj = 0, p_kelurahanpj = 0, p_kecamatanpj = 0, p_kabupatenpj = 0, cekPeserta = 0, cekKTP = 0, umurBy = 0,
            p_kelurahanDom = 0, p_kecamatanDom = 0, p_kabupatenDom = 0, p_alamatDom = 0;
    private double jumlah = 0, x = 0, i = 0;
    private String say = "", pengurutan = "", asalform = "", bulan = "", tahun = "", awalantahun = "", awalanbulan = "", posisitahun = "",
            no_ktp = "", tmp_lahir = "", nm_ibu = "", alamat = "", pekerjaan = "", no_tlp = "", umur = "", namakeluarga = "", no_peserta = "", 
            kelurahan = "", kecamatan = "", kabupaten = "", pekerjaanpj = "", alamatpj = "", kelurahanpj = "", kecamatanpj = "",
            kabupatenpj = "", keterangan = "", kelurahanDom = "", kecamatanDom = "", kabupatenDom = "", alamatDom = "";
    private PreparedStatement ps, ps2, pscariwilayah, pssetalamat, pskelengkapan;
    private ResultSet rs;
    private BPJSCekNIK cekViaBPJS = new BPJSCekNIK();
    private BPJSCekNoKartu cekViaBPJSKartu = new BPJSCekNoKartu();
    private Date lahir;
    private LocalDate today = LocalDate.now();
    private LocalDate birthday;
    private Period p;
    private long p2;

    /**
     * Creates new form DlgPas
     *
     * @param parent
     * @param modal
     */
    public DlgPasien(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"#", "No. RM", "Nama Pasien", "No. SIM/KTP", "J.K.", "Tmp. Lahir", "Tgl. Lahir", "Nama Ibu", "Alamat KTP/KK", "Alamat Domisili",
            "G.D.", "Pekerjaan", "Stts. Nikah", "Agama", "Tgl. Daftar", "No. Telp/HP", "Umur", "Pendidikan", "Png. Jawab", "Nama Png. Jawab", "Cara Bayar",
            "No. Peserta", "Daftar", "Pekerjaan P.J.", "Alamat P.J.", "Suku Pasien", "Bahasa Pasien", "kd_suku", "kd_bahasa", "umurPJ", "notlpPJ"
        };
        tabMode = new DefaultTableModel(null, row) {
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbPasien.setModel(tabMode);
        tbPasien.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (z = 0; z < 31; z++) {
            TableColumn column = tbPasien.getColumnModel().getColumn(z);
            if (z == 0) {
                column.setPreferredWidth(25);
            } else if (z == 1) {
                column.setPreferredWidth(85);
            } else if (z == 2) {
                column.setPreferredWidth(190);
            } else if (z == 3) {
                column.setPreferredWidth(100);
            } else if (z == 4) {
                column.setPreferredWidth(35);
            } else if (z == 5) {
                column.setPreferredWidth(100);
            } else if (z == 6) {
                column.setPreferredWidth(70);
            } else if (z == 7) {
//                column.setPreferredWidth(150);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (z == 8) {
                column.setPreferredWidth(190);
            } else if (z == 9) {
                column.setPreferredWidth(190);
            } else if (z == 10) {
                column.setPreferredWidth(35);
            } else if (z == 11) {
                column.setPreferredWidth(100);
            } else if (z == 12) {
                column.setPreferredWidth(100);
            } else if (z == 13) {
                column.setPreferredWidth(75);
            } else if (z == 14) {
                column.setPreferredWidth(75);
            } else if (z == 15) {
                column.setPreferredWidth(80);
            } else if (z == 16) {
                column.setPreferredWidth(90);
            } else if (z == 17) {
                column.setPreferredWidth(80);
            } else if (z == 18) {
                column.setPreferredWidth(80);
            } else if (z == 19) {
                column.setPreferredWidth(150);
            } else if (z == 20) {
                column.setPreferredWidth(120);
            } else if (z == 21) {
                column.setPreferredWidth(100);
            } else if (z == 22) {
                column.setPreferredWidth(60);
            } else if (z == 23) {
//                column.setPreferredWidth(85);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (z == 24) {
//                column.setPreferredWidth(160);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (z == 25) {
                column.setPreferredWidth(90);
            } else if (z == 26) {
                column.setPreferredWidth(90);
            } else if (z == 27) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (z == 28) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (z == 29) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (z == 30) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPasien.setDefaultRenderer(Object.class, new WarnaTable());

        TNo.setDocument(new batasInput((byte) 15).getKata(TNo));
        TNm.setDocument(new batasInput((byte) 40).getKata(TNm));
        NmIbu.setDocument(new batasInput((byte) 40).getKata(NmIbu));
        TKtp.setDocument(new batasInput((byte) 20).getKata(TKtp));
        Kdpnj.setDocument(new batasInput((byte) 3).getKata(Kdpnj));
        TTmp.setDocument(new batasInput((byte) 15).getKata(TTmp));
        Alamat.setDocument(new batasInput((int) 200).getFilter(Alamat));
        AlamatDomisili.setDocument(new batasInput((int) 200).getFilter(AlamatDomisili));
        AlamatPj.setDocument(new batasInput((int) 100).getFilter(AlamatPj));
        Pekerjaan.setDocument(new batasInput((byte) 15).getKata(Pekerjaan));
        PekerjaanPj.setDocument(new batasInput((byte) 15).getKata(PekerjaanPj));
        TUmurTh.setDocument(new batasInput((byte) 5).getOnlyAngka(TUmurTh));
        Saudara.setDocument(new batasInput((byte) 50).getKata(Saudara));
        Kabupaten.setDocument(new batasInput((byte) 60).getFilter(Kabupaten));
        Kecamatan.setDocument(new batasInput((byte) 60).getFilter(Kecamatan));
        Kelurahan.setDocument(new batasInput((byte) 60).getFilter(Kelurahan));
        KabupatenDom.setDocument(new batasInput((byte) 60).getFilter(KabupatenDom));
        KecamatanDom.setDocument(new batasInput((byte) 60).getFilter(KecamatanDom));
        KelurahanDom.setDocument(new batasInput((byte) 60).getFilter(KelurahanDom));
        KabupatenPj.setDocument(new batasInput((byte) 60).getFilter(KabupatenPj));
        KecamatanPj.setDocument(new batasInput((byte) 60).getFilter(KecamatanPj));
        KelurahanPj.setDocument(new batasInput((byte) 60).getFilter(KelurahanPj));
        Kabupaten2.setDocument(new batasInput((byte) 60).getFilter(Kabupaten2));
        Kecamatan2.setDocument(new batasInput((byte) 60).getFilter(Kecamatan2));
        Kelurahan2.setDocument(new batasInput((byte) 60).getFilter(Kelurahan2));
        TNoPeserta.setDocument(new batasInput((byte) 25).getKata(TNoPeserta));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TTlp.setDocument(new batasInput((byte) 13).getOnlyAngka(TTlp));
        umurPjawab.setDocument(new batasInput((byte) 3).getKata(umurPjawab));
        notlpPJ.setDocument(new batasInput((byte) 13).getOnlyAngka(notlpPJ));
        
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampil();
                }
            });
        }
        ChkInput.setSelected(false);
        isForm();

        pengurutan = Sequel.cariIsi("select urutan from set_urut_no_rkm_medis");
        tahun = Sequel.cariIsi("select tahun from set_urut_no_rkm_medis");
        bulan = Sequel.cariIsi("select bulan from set_urut_no_rkm_medis");
        posisitahun = Sequel.cariIsi("select posisi_tahun_bulan from set_urut_no_rkm_medis");

        penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPasien")) {
                    if (penjab.getTable().getSelectedRow() != -1) {
                        Kdpnj.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 1).toString());
                        nmpnj.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 2).toString());
                    }
                    Kdpnj.requestFocus();
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
                if (akses.getform().equals("DlgPasien")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        suku.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPasien")) {
                    if (suku.getTable().getSelectedRow() != -1) {
                        kdsuku.setText(suku.getTable().getValueAt(suku.getTable().getSelectedRow(), 0).toString());
                        nmsukubangsa.setText(suku.getTable().getValueAt(suku.getTable().getSelectedRow(), 1).toString());
                        BtnBahasa.requestFocus();
                    }
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

        suku.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgPasien")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        suku.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        bahasa.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPasien")) {
                    if (bahasa.getTable().getSelectedRow() != -1) {
                        kdbahasa.setText(bahasa.getTable().getValueAt(bahasa.getTable().getSelectedRow(), 0).toString());
                        nmbahasa.setText(bahasa.getTable().getValueAt(bahasa.getTable().getSelectedRow(), 1).toString());
                        BtnSimpan.requestFocus();
                    }
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

        bahasa.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgPasien")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        bahasa.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        kab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPasien")) {
                    if (kab.getTable().getSelectedRow() != -1) {
                        if (pilih == 1) {
                            Kabupaten.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 0).toString());
                            Kabupaten.requestFocus();
                        } else if (pilih == 2) {
                            Kabupaten2.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 0).toString());
                            Kabupaten2.requestFocus();
                        } else if (pilih == 3) {
                            KabupatenPj.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 0).toString());
                            KabupatenPj.requestFocus();
                        } else if (pilih == 4) {
                            KabupatenDom.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 0).toString());
                            KabupatenDom.requestFocus();
                        }
                    }
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

        kec.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPasien")) {
                    if (kec.getTable().getSelectedRow() != -1) {
                        if (pilih == 1) {
                            Kecamatan.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 0).toString());
                            Kecamatan.requestFocus();
                        } else if (pilih == 2) {
                            Kecamatan2.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 0).toString());
                            Kecamatan2.requestFocus();
                        } else if (pilih == 3) {
                            KecamatanPj.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 0).toString());
                            KecamatanPj.requestFocus();
                        } else if (pilih == 4) {
                            KecamatanDom.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 0).toString());
                            KecamatanDom.requestFocus();
                        }
                    }
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

        kel.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPasien")) {
                    if (kel.getTable().getSelectedRow() != -1) {
                        if (pilih == 1) {
                            Kelurahan.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 0).toString());
                            Kelurahan.requestFocus();
                        } else if (pilih == 2) {
                            Kelurahan2.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 0).toString());
                            Kelurahan2.requestFocus();
                        } else if (pilih == 3) {
                            KelurahanPj.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 0).toString());
                            KelurahanPj.requestFocus();
                        } else if (pilih == 4) {
                            KelurahanDom.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 0).toString());
                            KelurahanDom.requestFocus();
                        }
                    }
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

        try {
            pssetalamat = koneksi.prepareStatement("select * from set_alamat_pasien");
            try {
                rs = pssetalamat.executeQuery();
                while (rs.next()) {
                    Kelurahan.setEditable(rs.getBoolean("kelurahan"));
                    KelurahanDom.setEditable(rs.getBoolean("kelurahan"));
                    KelurahanPj.setEditable(rs.getBoolean("kelurahan"));
                    
                    Kecamatan.setEditable(rs.getBoolean("kecamatan"));
                    KecamatanDom.setEditable(rs.getBoolean("kecamatan"));
                    KecamatanPj.setEditable(rs.getBoolean("kecamatan"));
                    
                    Kabupaten.setEditable(rs.getBoolean("kabupaten"));
                    KabupatenDom.setEditable(rs.getBoolean("kabupaten"));
                    KabupatenPj.setEditable(rs.getBoolean("kabupaten"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pssetalamat != null) {
                    pssetalamat.close();
                }
            }

            pskelengkapan = koneksi.prepareStatement("select * from set_kelengkapan_data_pasien");
            try {
                rs = pskelengkapan.executeQuery();
                while (rs.next()) {
                    no_ktp = rs.getString("no_ktp");
                    p_no_ktp = rs.getInt("p_no_ktp");
                    tmp_lahir = rs.getString("tmp_lahir");
                    p_tmp_lahir = rs.getInt("p_tmp_lahir");
                    nm_ibu = rs.getString("nm_ibu");
                    p_nm_ibu = rs.getInt("p_nm_ibu");
                    alamat = rs.getString("alamat");
                    p_alamat = rs.getInt("p_alamat");
                    alamatDom = rs.getString("alamat_dom");
                    p_alamatDom = rs.getInt("p_alamat_dom");
                    pekerjaan = rs.getString("pekerjaan");
                    p_pekerjaan = rs.getInt("p_pekerjaan");
                    no_tlp = rs.getString("no_tlp");
                    p_no_tlp = rs.getInt("p_no_tlp");
                    umur = rs.getString("umur");
                    p_umur = rs.getInt("p_umur");
                    namakeluarga = rs.getString("namakeluarga");
                    p_namakeluarga = rs.getInt("p_namakeluarga");
                    no_peserta = rs.getString("no_peserta");
                    p_no_peserta = rs.getInt("p_no_peserta");
                    kelurahan = rs.getString("kelurahan");
                    p_kelurahan = rs.getInt("p_kelurahan");
                    kecamatan = rs.getString("kecamatan");
                    p_kecamatan = rs.getInt("p_kecamatan");
                    kabupaten = rs.getString("kabupaten");
                    p_kabupaten = rs.getInt("p_kabupaten");
                    
                    kelurahanDom = rs.getString("kelurahan_dom");
                    p_kelurahanDom = rs.getInt("p_kelurahan_dom");
                    kecamatanDom = rs.getString("kecamatan_dom");
                    p_kecamatanDom = rs.getInt("p_kecamatan_dom");
                    kabupatenDom = rs.getString("kabupaten_dom");
                    p_kabupatenDom = rs.getInt("p_kabupaten_dom");
                    
                    pekerjaanpj = rs.getString("pekerjaanpj");
                    p_pekerjaanpj = rs.getInt("p_pekerjaanpj");
                    alamatpj = rs.getString("alamatpj");
                    p_alamatpj = rs.getInt("p_alamatpj");
                    kelurahanpj = rs.getString("kelurahanpj");
                    p_kelurahanpj = rs.getInt("p_kelurahanpj");
                    kecamatanpj = rs.getString("kecamatanpj");
                    p_kecamatanpj = rs.getInt("p_kecamatanpj");
                    kabupatenpj = rs.getString("kabupatenpj");
                    p_kabupatenpj = rs.getInt("p_kabupatenpj");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pskelengkapan != null) {
                    pskelengkapan.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
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
        MnKartu = new javax.swing.JMenu();
        MnKartuUmum = new javax.swing.JMenuItem();
        MnKartuNonUmum = new javax.swing.JMenuItem();
        MnCetakKelengkapanInap = new javax.swing.JMenu();
        MnGelangDewasaAnak = new javax.swing.JMenu();
        MnPrinterBaru = new javax.swing.JMenuItem();
        MnPrinterLama = new javax.swing.JMenuItem();
        MnGelangBayi = new javax.swing.JMenu();
        MnPrinterBaru1 = new javax.swing.JMenuItem();
        MnPrinterBaru2 = new javax.swing.JMenuItem();
        MnPrinterLama1 = new javax.swing.JMenuItem();
        MnBarcodeRM1 = new javax.swing.JMenuItem();
        MnBarcodeRM2 = new javax.swing.JMenuItem();
        MnLabelRM1 = new javax.swing.JMenuItem();
        MnLabelRM2 = new javax.swing.JMenuItem();
        MenuIdentitas = new javax.swing.JMenu();
        MnIdentitas1 = new javax.swing.JMenuItem();
        MnIdentitas2 = new javax.swing.JMenuItem();
        MnIdentitas3 = new javax.swing.JMenuItem();
        MnBarcode = new javax.swing.JMenuItem();
        MnKartuStatus = new javax.swing.JMenuItem();
        MenuBPJS = new javax.swing.JMenu();
        MnCekKepesertaan = new javax.swing.JMenuItem();
        MnCekNIK = new javax.swing.JMenuItem();
        ppKelahiranBayi = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        MnLaporanRM = new javax.swing.JMenuItem();
        MnLaporanRM1 = new javax.swing.JMenuItem();
        MnLaporanRM2 = new javax.swing.JMenuItem();
        MnFormulirPendaftaran = new javax.swing.JMenuItem();
        MnSCreening = new javax.swing.JMenuItem();
        MnCopyResep = new javax.swing.JMenuItem();
        MnLembarKeluarMasuk = new javax.swing.JMenuItem();
        MnLembarKeluarMasuk2 = new javax.swing.JMenuItem();
        MnLaporanIGD = new javax.swing.JMenuItem();
        MnLembarAnamNesa = new javax.swing.JMenuItem();
        MnLembarGrafik = new javax.swing.JMenuItem();
        MnLembarCatatanPerkembangan = new javax.swing.JMenuItem();
        MnLembarCatatanKeperawatan = new javax.swing.JMenuItem();
        MnLaporanAnestesia = new javax.swing.JMenuItem();
        MnPengantarHemodalisa = new javax.swing.JMenuItem();
        MnCover = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        ppGrafikPerAgama = new javax.swing.JMenuItem();
        ppGrafikPerPekerjaan = new javax.swing.JMenuItem();
        ppGrafikjkbayi = new javax.swing.JMenuItem();
        ppGrafikDemografi = new javax.swing.JMenuItem();
        ppRegistrasi = new javax.swing.JMenuItem();
        ppRiwayat = new javax.swing.JMenuItem();
        ppCatatanPasien = new javax.swing.JMenuItem();
        ppGabungRM = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnViaBPJSNik = new javax.swing.JMenuItem();
        MnViaBPJSNoKartu = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        DlgDemografi = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        panelBiasa2 = new widget.PanelBiasa();
        BtnPrint2 = new widget.Button();
        BtnKeluar2 = new widget.Button();
        Kelurahan2 = new widget.TextBox();
        BtnSeek8 = new widget.Button();
        Kecamatan2 = new widget.TextBox();
        BtnSeek9 = new widget.Button();
        Kabupaten2 = new widget.TextBox();
        BtnSeek10 = new widget.Button();
        jLabel33 = new widget.Label();
        jLabel34 = new widget.Label();
        jLabel35 = new widget.Label();
        BtnPrint3 = new widget.Button();
        WindowGabungRM = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        BtnCloseIn6 = new widget.Button();
        BtnSimpan6 = new widget.Button();
        label40 = new widget.Label();
        NoRmTujuan = new widget.TextBox();
        NmPasienTujuan = new widget.TextBox();
        label41 = new widget.Label();
        BtnCari1 = new widget.Button();
        Kd2 = new widget.TextBox();
        NoRm = new widget.TextBox();
        kdsuku = new widget.TextBox();
        kdbahasa = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPasien = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel11 = new widget.Label();
        Carialamat = new widget.TextBox();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel7 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel6 = new widget.Label();
        cmbHlm = new widget.ComboBox();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        jLabel4 = new widget.Label();
        TTmp = new widget.TextBox();
        CmbJk = new widget.ComboBox();
        TNm = new widget.TextBox();
        jLabel8 = new widget.Label();
        CMbGd = new widget.ComboBox();
        jLabel9 = new widget.Label();
        jLabel13 = new widget.Label();
        DTPLahir = new widget.Tanggal();
        jLabel18 = new widget.Label();
        cmbAgama = new widget.ComboBox();
        jLabel19 = new widget.Label();
        CmbStts = new widget.ComboBox();
        jLabel20 = new widget.Label();
        jLabel21 = new widget.Label();
        Pekerjaan = new widget.TextBox();
        jLabel12 = new widget.Label();
        Alamat = new widget.TextBox();
        TTlp = new widget.TextBox();
        TNo = new widget.TextBox();
        jLabel15 = new widget.Label();
        TKtp = new widget.TextBox();
        DTPDaftar = new widget.Tanggal();
        jLabel22 = new widget.Label();
        jLabel17 = new widget.Label();
        jLabel23 = new widget.Label();
        CMbPnd = new widget.ComboBox();
        Saudara = new widget.TextBox();
        jLabel24 = new widget.Label();
        Kdpnj = new widget.TextBox();
        nmpnj = new widget.TextBox();
        BtnPenjab = new widget.Button();
        Kelurahan = new widget.TextBox();
        Kecamatan = new widget.TextBox();
        Kabupaten = new widget.TextBox();
        BtnKelurahan = new widget.Button();
        BtnKecamatan = new widget.Button();
        BtnKabupaten = new widget.Button();
        ChkDaftar = new widget.CekBox();
        jLabel14 = new widget.Label();
        NmIbu = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel25 = new widget.Label();
        PekerjaanPj = new widget.TextBox();
        jLabel26 = new widget.Label();
        jLabel27 = new widget.Label();
        AlamatPj = new widget.TextBox();
        KecamatanPj = new widget.TextBox();
        BtnKecamatanPj = new widget.Button();
        KabupatenPj = new widget.TextBox();
        BtnKabupatenPj = new widget.Button();
        BtnKelurahanPj = new widget.Button();
        KelurahanPj = new widget.TextBox();
        jLabel28 = new widget.Label();
        TNoPeserta = new widget.TextBox();
        ChkRM = new widget.CekBox();
        BtnKelurahan1 = new widget.Button();
        TUmurTh = new widget.TextBox();
        jLabel31 = new widget.Label();
        TUmurBl = new widget.TextBox();
        jLabel29 = new widget.Label();
        TUmurHr = new widget.TextBox();
        jLabel30 = new widget.Label();
        jLabel32 = new widget.Label();
        nmsukubangsa = new widget.TextBox();
        BtnSuku = new widget.Button();
        nmbahasa = new widget.TextBox();
        BtnBahasa = new widget.Button();
        ChkIbuAnak = new widget.CekBox();
        jLabel37 = new widget.Label();
        cmbPngJawab = new widget.ComboBox();
        jLabel36 = new widget.Label();
        AlamatDomisili = new widget.TextBox();
        KelurahanDom = new widget.TextBox();
        BtnKelurahanDom = new widget.Button();
        KecamatanDom = new widget.TextBox();
        BtnKecamatanDom = new widget.Button();
        KabupatenDom = new widget.TextBox();
        BtnKabupatenDom = new widget.Button();
        ChkSamakan = new widget.CekBox();
        jLabel38 = new widget.Label();
        umurPjawab = new widget.TextBox();
        jLabel39 = new widget.Label();
        jLabel40 = new widget.Label();
        notlpPJ = new widget.TextBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnKartu.setBackground(new java.awt.Color(255, 255, 255));
        MnKartu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartu.setText("Kartu Berobat Pasien (KIB)");
        MnKartu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKartu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKartu.setIconTextGap(5);
        MnKartu.setName("MnKartu"); // NOI18N
        MnKartu.setOpaque(true);
        MnKartu.setPreferredSize(new java.awt.Dimension(250, 25));

        MnKartuUmum.setBackground(new java.awt.Color(255, 255, 255));
        MnKartuUmum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartuUmum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartuUmum.setText("Pasien UMUM");
        MnKartuUmum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKartuUmum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKartuUmum.setIconTextGap(5);
        MnKartuUmum.setName("MnKartuUmum"); // NOI18N
        MnKartuUmum.setPreferredSize(new java.awt.Dimension(160, 25));
        MnKartuUmum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartuUmumActionPerformed(evt);
            }
        });
        MnKartu.add(MnKartuUmum);

        MnKartuNonUmum.setBackground(new java.awt.Color(255, 255, 255));
        MnKartuNonUmum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartuNonUmum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartuNonUmum.setText("Pasien NON UMUM");
        MnKartuNonUmum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKartuNonUmum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKartuNonUmum.setIconTextGap(5);
        MnKartuNonUmum.setName("MnKartuNonUmum"); // NOI18N
        MnKartuNonUmum.setPreferredSize(new java.awt.Dimension(160, 25));
        MnKartuNonUmum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartuNonUmumActionPerformed(evt);
            }
        });
        MnKartu.add(MnKartuNonUmum);

        jPopupMenu1.add(MnKartu);

        MnCetakKelengkapanInap.setBackground(new java.awt.Color(255, 255, 255));
        MnCetakKelengkapanInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakKelengkapanInap.setText("Cetak Kelengkapan Opname");
        MnCetakKelengkapanInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakKelengkapanInap.setName("MnCetakKelengkapanInap"); // NOI18N
        MnCetakKelengkapanInap.setOpaque(true);
        MnCetakKelengkapanInap.setPreferredSize(new java.awt.Dimension(250, 25));

        MnGelangDewasaAnak.setBackground(new java.awt.Color(255, 255, 255));
        MnGelangDewasaAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelangDewasaAnak.setText("Gelang DEWASA & ANAK-ANAK");
        MnGelangDewasaAnak.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelangDewasaAnak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelangDewasaAnak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelangDewasaAnak.setIconTextGap(5);
        MnGelangDewasaAnak.setName("MnGelangDewasaAnak"); // NOI18N
        MnGelangDewasaAnak.setOpaque(true);
        MnGelangDewasaAnak.setPreferredSize(new java.awt.Dimension(210, 26));

        MnPrinterBaru.setBackground(new java.awt.Color(255, 255, 255));
        MnPrinterBaru.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPrinterBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPrinterBaru.setText("Printer BARU");
        MnPrinterBaru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPrinterBaru.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPrinterBaru.setIconTextGap(5);
        MnPrinterBaru.setName("MnPrinterBaru"); // NOI18N
        MnPrinterBaru.setPreferredSize(new java.awt.Dimension(125, 26));
        MnPrinterBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPrinterBaruActionPerformed(evt);
            }
        });
        MnGelangDewasaAnak.add(MnPrinterBaru);

        MnPrinterLama.setBackground(new java.awt.Color(255, 255, 255));
        MnPrinterLama.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPrinterLama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPrinterLama.setText("Printer LAMA");
        MnPrinterLama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPrinterLama.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPrinterLama.setIconTextGap(5);
        MnPrinterLama.setName("MnPrinterLama"); // NOI18N
        MnPrinterLama.setPreferredSize(new java.awt.Dimension(125, 26));
        MnPrinterLama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPrinterLamaActionPerformed(evt);
            }
        });
        MnGelangDewasaAnak.add(MnPrinterLama);

        MnCetakKelengkapanInap.add(MnGelangDewasaAnak);

        MnGelangBayi.setBackground(new java.awt.Color(255, 255, 255));
        MnGelangBayi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelangBayi.setText("Gelang BAYI");
        MnGelangBayi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelangBayi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelangBayi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelangBayi.setIconTextGap(5);
        MnGelangBayi.setName("MnGelangBayi"); // NOI18N
        MnGelangBayi.setOpaque(true);
        MnGelangBayi.setPreferredSize(new java.awt.Dimension(210, 26));

        MnPrinterBaru1.setBackground(new java.awt.Color(255, 255, 255));
        MnPrinterBaru1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPrinterBaru1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPrinterBaru1.setText("Printer BARU Versi 1");
        MnPrinterBaru1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPrinterBaru1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPrinterBaru1.setIconTextGap(5);
        MnPrinterBaru1.setName("MnPrinterBaru1"); // NOI18N
        MnPrinterBaru1.setPreferredSize(new java.awt.Dimension(150, 26));
        MnPrinterBaru1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPrinterBaru1ActionPerformed(evt);
            }
        });
        MnGelangBayi.add(MnPrinterBaru1);

        MnPrinterBaru2.setBackground(new java.awt.Color(255, 255, 255));
        MnPrinterBaru2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPrinterBaru2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPrinterBaru2.setText("Printer BARU Versi 2");
        MnPrinterBaru2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPrinterBaru2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPrinterBaru2.setIconTextGap(5);
        MnPrinterBaru2.setName("MnPrinterBaru2"); // NOI18N
        MnPrinterBaru2.setPreferredSize(new java.awt.Dimension(150, 26));
        MnPrinterBaru2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPrinterBaru2ActionPerformed(evt);
            }
        });
        MnGelangBayi.add(MnPrinterBaru2);

        MnPrinterLama1.setBackground(new java.awt.Color(255, 255, 255));
        MnPrinterLama1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPrinterLama1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPrinterLama1.setText("Printer LAMA");
        MnPrinterLama1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPrinterLama1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPrinterLama1.setIconTextGap(5);
        MnPrinterLama1.setName("MnPrinterLama1"); // NOI18N
        MnPrinterLama1.setPreferredSize(new java.awt.Dimension(150, 26));
        MnPrinterLama1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPrinterLama1ActionPerformed(evt);
            }
        });
        MnGelangBayi.add(MnPrinterLama1);

        MnCetakKelengkapanInap.add(MnGelangBayi);

        MnBarcodeRM1.setBackground(new java.awt.Color(255, 255, 255));
        MnBarcodeRM1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnBarcodeRM1.setText("Barcode (KERTAS BESAR)");
        MnBarcodeRM1.setName("MnBarcodeRM1"); // NOI18N
        MnBarcodeRM1.setPreferredSize(new java.awt.Dimension(220, 25));
        MnBarcodeRM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM1ActionPerformed(evt);
            }
        });
        MnCetakKelengkapanInap.add(MnBarcodeRM1);

        MnBarcodeRM2.setBackground(new java.awt.Color(255, 255, 255));
        MnBarcodeRM2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnBarcodeRM2.setText("Barcode (KERTAS KECIL)");
        MnBarcodeRM2.setName("MnBarcodeRM2"); // NOI18N
        MnBarcodeRM2.setPreferredSize(new java.awt.Dimension(220, 25));
        MnBarcodeRM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM2ActionPerformed(evt);
            }
        });
        MnCetakKelengkapanInap.add(MnBarcodeRM2);

        MnLabelRM1.setBackground(new java.awt.Color(255, 255, 255));
        MnLabelRM1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelRM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnLabelRM1.setText("Label Identitas (KERTAS BESAR)");
        MnLabelRM1.setName("MnLabelRM1"); // NOI18N
        MnLabelRM1.setPreferredSize(new java.awt.Dimension(220, 25));
        MnLabelRM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelRM1ActionPerformed(evt);
            }
        });
        MnCetakKelengkapanInap.add(MnLabelRM1);

        MnLabelRM2.setBackground(new java.awt.Color(255, 255, 255));
        MnLabelRM2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelRM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnLabelRM2.setText("Label Identitas (KERTAS KECIL)");
        MnLabelRM2.setName("MnLabelRM2"); // NOI18N
        MnLabelRM2.setPreferredSize(new java.awt.Dimension(220, 25));
        MnLabelRM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelRM2ActionPerformed(evt);
            }
        });
        MnCetakKelengkapanInap.add(MnLabelRM2);

        jPopupMenu1.add(MnCetakKelengkapanInap);

        MenuIdentitas.setBackground(new java.awt.Color(255, 255, 255));
        MenuIdentitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MenuIdentitas.setText("Identitas Pasien");
        MenuIdentitas.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MenuIdentitas.setName("MenuIdentitas"); // NOI18N
        MenuIdentitas.setOpaque(true);
        MenuIdentitas.setPreferredSize(new java.awt.Dimension(250, 25));

        MnIdentitas1.setBackground(new java.awt.Color(255, 255, 255));
        MnIdentitas1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnIdentitas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnIdentitas1.setText("Identitas Pasien 1");
        MnIdentitas1.setName("MnIdentitas1"); // NOI18N
        MnIdentitas1.setPreferredSize(new java.awt.Dimension(140, 25));
        MnIdentitas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnIdentitas1ActionPerformed(evt);
            }
        });
        MenuIdentitas.add(MnIdentitas1);

        MnIdentitas2.setBackground(new java.awt.Color(255, 255, 255));
        MnIdentitas2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnIdentitas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnIdentitas2.setText("Identitas Pasien 2");
        MnIdentitas2.setName("MnIdentitas2"); // NOI18N
        MnIdentitas2.setPreferredSize(new java.awt.Dimension(140, 25));
        MnIdentitas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnIdentitas2ActionPerformed(evt);
            }
        });
        MenuIdentitas.add(MnIdentitas2);

        MnIdentitas3.setBackground(new java.awt.Color(255, 255, 255));
        MnIdentitas3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnIdentitas3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnIdentitas3.setText("Identitas Pasien 3");
        MnIdentitas3.setName("MnIdentitas3"); // NOI18N
        MnIdentitas3.setPreferredSize(new java.awt.Dimension(140, 25));
        MnIdentitas3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnIdentitas3ActionPerformed(evt);
            }
        });
        MenuIdentitas.add(MnIdentitas3);

        jPopupMenu1.add(MenuIdentitas);

        MnBarcode.setBackground(new java.awt.Color(255, 255, 255));
        MnBarcode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcode.setText("Kartu Indeks Keseluruhan");
        MnBarcode.setName("MnBarcode"); // NOI18N
        MnBarcode.setPreferredSize(new java.awt.Dimension(250, 25));
        MnBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBarcode);

        MnKartuStatus.setBackground(new java.awt.Color(255, 255, 255));
        MnKartuStatus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartuStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartuStatus.setText("Kartu Indeks Pasien Yang Dipilih");
        MnKartuStatus.setName("MnKartuStatus"); // NOI18N
        MnKartuStatus.setPreferredSize(new java.awt.Dimension(250, 25));
        MnKartuStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartuStatusActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKartuStatus);

        MenuBPJS.setBackground(new java.awt.Color(255, 255, 255));
        MenuBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MenuBPJS.setText("BPJS");
        MenuBPJS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MenuBPJS.setName("MenuBPJS"); // NOI18N
        MenuBPJS.setOpaque(true);
        MenuBPJS.setPreferredSize(new java.awt.Dimension(250, 25));

        MnCekKepesertaan.setBackground(new java.awt.Color(255, 255, 255));
        MnCekKepesertaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCekKepesertaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCekKepesertaan.setText("Pencarian Peserta Berdasarkan Nomor Kepesertaan");
        MnCekKepesertaan.setName("MnCekKepesertaan"); // NOI18N
        MnCekKepesertaan.setPreferredSize(new java.awt.Dimension(300, 25));
        MnCekKepesertaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekKepesertaanActionPerformed(evt);
            }
        });
        MenuBPJS.add(MnCekKepesertaan);

        MnCekNIK.setBackground(new java.awt.Color(255, 255, 255));
        MnCekNIK.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCekNIK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCekNIK.setText("Pencarian Peserta Berdasarkan NIK/No.KTP");
        MnCekNIK.setName("MnCekNIK"); // NOI18N
        MnCekNIK.setPreferredSize(new java.awt.Dimension(300, 25));
        MnCekNIK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekNIKActionPerformed(evt);
            }
        });
        MenuBPJS.add(MnCekNIK);

        jPopupMenu1.add(MenuBPJS);

        ppKelahiranBayi.setBackground(new java.awt.Color(255, 255, 255));
        ppKelahiranBayi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppKelahiranBayi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppKelahiranBayi.setText("Data Kelahiran Bayi");
        ppKelahiranBayi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppKelahiranBayi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppKelahiranBayi.setName("ppKelahiranBayi"); // NOI18N
        ppKelahiranBayi.setPreferredSize(new java.awt.Dimension(250, 25));
        ppKelahiranBayi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppKelahiranBayiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppKelahiranBayi);

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu1.setText("Berkas Rekam Medis");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N
        jMenu1.setOpaque(true);
        jMenu1.setPreferredSize(new java.awt.Dimension(250, 25));

        MnLaporanRM.setBackground(new java.awt.Color(255, 255, 255));
        MnLaporanRM.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRM.setText("Lembar Rawat Jalan Model 1");
        MnLaporanRM.setName("MnLaporanRM"); // NOI18N
        MnLaporanRM.setPreferredSize(new java.awt.Dimension(300, 28));
        MnLaporanRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRMActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRM);

        MnLaporanRM1.setBackground(new java.awt.Color(255, 255, 255));
        MnLaporanRM1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRM1.setText("Lembar Rawat Jalan Model 2");
        MnLaporanRM1.setName("MnLaporanRM1"); // NOI18N
        MnLaporanRM1.setPreferredSize(new java.awt.Dimension(300, 28));
        MnLaporanRM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRM1ActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRM1);

        MnLaporanRM2.setBackground(new java.awt.Color(255, 255, 255));
        MnLaporanRM2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRM2.setText("Lembar Rawat Jalan Model 3");
        MnLaporanRM2.setName("MnLaporanRM2"); // NOI18N
        MnLaporanRM2.setPreferredSize(new java.awt.Dimension(300, 28));
        MnLaporanRM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRM2ActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRM2);

        MnFormulirPendaftaran.setBackground(new java.awt.Color(255, 255, 255));
        MnFormulirPendaftaran.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnFormulirPendaftaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnFormulirPendaftaran.setText("Formulir Pendaftaran Pasien");
        MnFormulirPendaftaran.setName("MnFormulirPendaftaran"); // NOI18N
        MnFormulirPendaftaran.setPreferredSize(new java.awt.Dimension(300, 28));
        MnFormulirPendaftaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFormulirPendaftaranActionPerformed(evt);
            }
        });
        jMenu1.add(MnFormulirPendaftaran);

        MnSCreening.setBackground(new java.awt.Color(255, 255, 255));
        MnSCreening.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSCreening.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSCreening.setText("Lembar Screening Awal Pasien Masuk Rawat Jalan");
        MnSCreening.setName("MnSCreening"); // NOI18N
        MnSCreening.setPreferredSize(new java.awt.Dimension(300, 28));
        MnSCreening.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSCreeningActionPerformed(evt);
            }
        });
        jMenu1.add(MnSCreening);

        MnCopyResep.setBackground(new java.awt.Color(255, 255, 255));
        MnCopyResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopyResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopyResep.setText("Formulir Penempelan Copy Resep");
        MnCopyResep.setName("MnCopyResep"); // NOI18N
        MnCopyResep.setPreferredSize(new java.awt.Dimension(300, 28));
        MnCopyResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopyResepActionPerformed(evt);
            }
        });
        jMenu1.add(MnCopyResep);

        MnLembarKeluarMasuk.setBackground(new java.awt.Color(255, 255, 255));
        MnLembarKeluarMasuk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarKeluarMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarKeluarMasuk.setText("Lembar Masuk Keluar Model 1");
        MnLembarKeluarMasuk.setName("MnLembarKeluarMasuk"); // NOI18N
        MnLembarKeluarMasuk.setPreferredSize(new java.awt.Dimension(300, 28));
        MnLembarKeluarMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarKeluarMasukActionPerformed(evt);
            }
        });
        jMenu1.add(MnLembarKeluarMasuk);

        MnLembarKeluarMasuk2.setBackground(new java.awt.Color(255, 255, 255));
        MnLembarKeluarMasuk2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarKeluarMasuk2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarKeluarMasuk2.setText("Lembar Masuk Keluar Model 2");
        MnLembarKeluarMasuk2.setName("MnLembarKeluarMasuk2"); // NOI18N
        MnLembarKeluarMasuk2.setPreferredSize(new java.awt.Dimension(300, 28));
        MnLembarKeluarMasuk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarKeluarMasuk2ActionPerformed(evt);
            }
        });
        jMenu1.add(MnLembarKeluarMasuk2);

        MnLaporanIGD.setBackground(new java.awt.Color(255, 255, 255));
        MnLaporanIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanIGD.setText("Laporan IGD");
        MnLaporanIGD.setName("MnLaporanIGD"); // NOI18N
        MnLaporanIGD.setPreferredSize(new java.awt.Dimension(300, 28));
        MnLaporanIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanIGDActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanIGD);

        MnLembarAnamNesa.setBackground(new java.awt.Color(255, 255, 255));
        MnLembarAnamNesa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarAnamNesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarAnamNesa.setText("Lembar Anamnesa");
        MnLembarAnamNesa.setName("MnLembarAnamNesa"); // NOI18N
        MnLembarAnamNesa.setPreferredSize(new java.awt.Dimension(300, 28));
        MnLembarAnamNesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarAnamNesaActionPerformed(evt);
            }
        });
        jMenu1.add(MnLembarAnamNesa);

        MnLembarGrafik.setBackground(new java.awt.Color(255, 255, 255));
        MnLembarGrafik.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarGrafik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarGrafik.setText("Lembar Grafik");
        MnLembarGrafik.setName("MnLembarGrafik"); // NOI18N
        MnLembarGrafik.setPreferredSize(new java.awt.Dimension(300, 28));
        MnLembarGrafik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarGrafikActionPerformed(evt);
            }
        });
        jMenu1.add(MnLembarGrafik);

        MnLembarCatatanPerkembangan.setBackground(new java.awt.Color(255, 255, 255));
        MnLembarCatatanPerkembangan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarCatatanPerkembangan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarCatatanPerkembangan.setText("Lembar Catatan Perkembangan");
        MnLembarCatatanPerkembangan.setName("MnLembarCatatanPerkembangan"); // NOI18N
        MnLembarCatatanPerkembangan.setPreferredSize(new java.awt.Dimension(300, 28));
        MnLembarCatatanPerkembangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarCatatanPerkembanganActionPerformed(evt);
            }
        });
        jMenu1.add(MnLembarCatatanPerkembangan);

        MnLembarCatatanKeperawatan.setBackground(new java.awt.Color(255, 255, 255));
        MnLembarCatatanKeperawatan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarCatatanKeperawatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarCatatanKeperawatan.setText("Lembar Catatan Keperawatan");
        MnLembarCatatanKeperawatan.setName("MnLembarCatatanKeperawatan"); // NOI18N
        MnLembarCatatanKeperawatan.setPreferredSize(new java.awt.Dimension(300, 28));
        MnLembarCatatanKeperawatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarCatatanKeperawatanActionPerformed(evt);
            }
        });
        jMenu1.add(MnLembarCatatanKeperawatan);

        MnLaporanAnestesia.setBackground(new java.awt.Color(255, 255, 255));
        MnLaporanAnestesia.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanAnestesia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanAnestesia.setText("Lembar Laporan Anastesia");
        MnLaporanAnestesia.setName("MnLaporanAnestesia"); // NOI18N
        MnLaporanAnestesia.setPreferredSize(new java.awt.Dimension(300, 28));
        MnLaporanAnestesia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanAnestesiaActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanAnestesia);

        MnPengantarHemodalisa.setBackground(new java.awt.Color(255, 255, 255));
        MnPengantarHemodalisa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPengantarHemodalisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPengantarHemodalisa.setText("Pengantar Hemodialisa");
        MnPengantarHemodalisa.setName("MnPengantarHemodalisa"); // NOI18N
        MnPengantarHemodalisa.setPreferredSize(new java.awt.Dimension(300, 28));
        MnPengantarHemodalisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPengantarHemodalisaActionPerformed(evt);
            }
        });
        jMenu1.add(MnPengantarHemodalisa);

        MnCover.setBackground(new java.awt.Color(255, 255, 255));
        MnCover.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCover.setText("Cover Rekam Medis");
        MnCover.setName("MnCover"); // NOI18N
        MnCover.setPreferredSize(new java.awt.Dimension(300, 28));
        MnCover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCoverActionPerformed(evt);
            }
        });
        jMenu1.add(MnCover);

        jPopupMenu1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(255, 255, 255));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu2.setText("Grafik Analisa");
        jMenu2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N
        jMenu2.setOpaque(true);
        jMenu2.setPreferredSize(new java.awt.Dimension(250, 25));

        ppGrafikPerAgama.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikPerAgama.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerAgama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerAgama.setText("Grafik Pasien Per Agama");
        ppGrafikPerAgama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerAgama.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerAgama.setName("ppGrafikPerAgama"); // NOI18N
        ppGrafikPerAgama.setPreferredSize(new java.awt.Dimension(200, 25));
        ppGrafikPerAgama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerAgamaActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerAgama);

        ppGrafikPerPekerjaan.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikPerPekerjaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerPekerjaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerPekerjaan.setText("Grafik Pasien Per Pekerjaan");
        ppGrafikPerPekerjaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerPekerjaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerPekerjaan.setName("ppGrafikPerPekerjaan"); // NOI18N
        ppGrafikPerPekerjaan.setPreferredSize(new java.awt.Dimension(200, 25));
        ppGrafikPerPekerjaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerPekerjaanActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerPekerjaan);

        ppGrafikjkbayi.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikjkbayi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikjkbayi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikjkbayi.setText("Grafik Jenis Kelamin Pasien");
        ppGrafikjkbayi.setActionCommand("Grafik Pasien Per Jenis Kelamin");
        ppGrafikjkbayi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikjkbayi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikjkbayi.setName("ppGrafikjkbayi"); // NOI18N
        ppGrafikjkbayi.setPreferredSize(new java.awt.Dimension(200, 25));
        ppGrafikjkbayi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikjkbayiActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikjkbayi);

        ppGrafikDemografi.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikDemografi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikDemografi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikDemografi.setText("Demografi Pasien");
        ppGrafikDemografi.setActionCommand("Grafik Demografi Pasien");
        ppGrafikDemografi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikDemografi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikDemografi.setName("ppGrafikDemografi"); // NOI18N
        ppGrafikDemografi.setPreferredSize(new java.awt.Dimension(200, 25));
        ppGrafikDemografi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikDemografiActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikDemografi);

        jPopupMenu1.add(jMenu2);

        ppRegistrasi.setBackground(new java.awt.Color(255, 255, 255));
        ppRegistrasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRegistrasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRegistrasi.setText("Tampilkan Banyak Daftar");
        ppRegistrasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRegistrasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRegistrasi.setName("ppRegistrasi"); // NOI18N
        ppRegistrasi.setPreferredSize(new java.awt.Dimension(250, 25));
        ppRegistrasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRegistrasiBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppRegistrasi);

        ppRiwayat.setBackground(new java.awt.Color(255, 255, 255));
        ppRiwayat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRiwayat.setText("Riwayat Perawatan");
        ppRiwayat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRiwayat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRiwayat.setName("ppRiwayat"); // NOI18N
        ppRiwayat.setPreferredSize(new java.awt.Dimension(250, 25));
        ppRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRiwayatBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppRiwayat);

        ppCatatanPasien.setBackground(new java.awt.Color(255, 255, 255));
        ppCatatanPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCatatanPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCatatanPasien.setText("Catatan Untuk Pasien");
        ppCatatanPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCatatanPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCatatanPasien.setName("ppCatatanPasien"); // NOI18N
        ppCatatanPasien.setPreferredSize(new java.awt.Dimension(250, 25));
        ppCatatanPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCatatanPasienBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppCatatanPasien);

        ppGabungRM.setBackground(new java.awt.Color(255, 255, 255));
        ppGabungRM.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGabungRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGabungRM.setText("Gabungkan Data RM");
        ppGabungRM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGabungRM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGabungRM.setName("ppGabungRM"); // NOI18N
        ppGabungRM.setPreferredSize(new java.awt.Dimension(250, 25));
        ppGabungRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGabungRMBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppGabungRM);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnViaBPJSNik.setBackground(new java.awt.Color(255, 255, 255));
        MnViaBPJSNik.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnViaBPJSNik.setForeground(new java.awt.Color(60, 80, 50));
        MnViaBPJSNik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnViaBPJSNik.setText("Cek Via NIK Web Servis BPJS");
        MnViaBPJSNik.setName("MnViaBPJSNik"); // NOI18N
        MnViaBPJSNik.setPreferredSize(new java.awt.Dimension(250, 25));
        MnViaBPJSNik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnViaBPJSNikActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnViaBPJSNik);

        MnViaBPJSNoKartu.setBackground(new java.awt.Color(255, 255, 255));
        MnViaBPJSNoKartu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnViaBPJSNoKartu.setForeground(new java.awt.Color(60, 80, 50));
        MnViaBPJSNoKartu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnViaBPJSNoKartu.setText("Cek Via No Kartu Web Servis BPJS");
        MnViaBPJSNoKartu.setName("MnViaBPJSNoKartu"); // NOI18N
        MnViaBPJSNoKartu.setPreferredSize(new java.awt.Dimension(250, 25));
        MnViaBPJSNoKartu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnViaBPJSNoKartuActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnViaBPJSNoKartu);

        DlgDemografi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgDemografi.setName("DlgDemografi"); // NOI18N
        DlgDemografi.setUndecorated(true);
        DlgDemografi.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Demografi Pasien ]::"));
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa2.setName("panelBiasa2"); // NOI18N
        panelBiasa2.setLayout(null);

        BtnPrint2.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint2.setMnemonic('T');
        BtnPrint2.setText("Grafik");
        BtnPrint2.setToolTipText("Alt+T");
        BtnPrint2.setName("BtnPrint2"); // NOI18N
        BtnPrint2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint2ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnPrint2);
        BtnPrint2.setBounds(110, 110, 100, 30);

        BtnKeluar2.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar2.setMnemonic('K');
        BtnKeluar2.setText("Keluar");
        BtnKeluar2.setToolTipText("Alt+K");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar2ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnKeluar2);
        BtnKeluar2.setBounds(430, 110, 100, 30);

        Kelurahan2.setForeground(new java.awt.Color(0, 0, 0));
        Kelurahan2.setHighlighter(null);
        Kelurahan2.setName("Kelurahan2"); // NOI18N
        panelBiasa2.add(Kelurahan2);
        Kelurahan2.setBounds(105, 70, 350, 23);

        BtnSeek8.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek8.setMnemonic('1');
        BtnSeek8.setToolTipText("ALt+1");
        BtnSeek8.setName("BtnSeek8"); // NOI18N
        BtnSeek8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek8ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnSeek8);
        BtnSeek8.setBounds(460, 70, 28, 23);

        Kecamatan2.setForeground(new java.awt.Color(0, 0, 0));
        Kecamatan2.setHighlighter(null);
        Kecamatan2.setName("Kecamatan2"); // NOI18N
        panelBiasa2.add(Kecamatan2);
        Kecamatan2.setBounds(105, 40, 350, 23);

        BtnSeek9.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek9.setMnemonic('1');
        BtnSeek9.setToolTipText("ALt+1");
        BtnSeek9.setName("BtnSeek9"); // NOI18N
        BtnSeek9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek9ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnSeek9);
        BtnSeek9.setBounds(460, 40, 28, 23);

        Kabupaten2.setForeground(new java.awt.Color(0, 0, 0));
        Kabupaten2.setHighlighter(null);
        Kabupaten2.setName("Kabupaten2"); // NOI18N
        panelBiasa2.add(Kabupaten2);
        Kabupaten2.setBounds(105, 10, 350, 23);

        BtnSeek10.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek10.setMnemonic('1');
        BtnSeek10.setToolTipText("ALt+1");
        BtnSeek10.setName("BtnSeek10"); // NOI18N
        BtnSeek10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek10ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnSeek10);
        BtnSeek10.setBounds(460, 10, 28, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Kelurahan :");
        jLabel33.setName("jLabel33"); // NOI18N
        panelBiasa2.add(jLabel33);
        jLabel33.setBounds(0, 70, 100, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Kabupaten :");
        jLabel34.setName("jLabel34"); // NOI18N
        panelBiasa2.add(jLabel34);
        jLabel34.setBounds(0, 10, 100, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Kecamatan :");
        jLabel35.setName("jLabel35"); // NOI18N
        panelBiasa2.add(jLabel35);
        jLabel35.setBounds(0, 40, 100, 23);

        BtnPrint3.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint3.setMnemonic('T');
        BtnPrint3.setText("Cetak");
        BtnPrint3.setToolTipText("Alt+T");
        BtnPrint3.setName("BtnPrint3"); // NOI18N
        BtnPrint3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint3ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnPrint3);
        BtnPrint3.setBounds(10, 110, 100, 30);

        internalFrame3.add(panelBiasa2, java.awt.BorderLayout.CENTER);

        DlgDemografi.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        WindowGabungRM.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGabungRM.setModal(true);
        WindowGabungRM.setName("WindowGabungRM"); // NOI18N
        WindowGabungRM.setUndecorated(true);
        WindowGabungRM.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Gabungkan Ke Nomor RM ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame8.setLayout(null);

        BtnCloseIn6.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn6.setMnemonic('P');
        BtnCloseIn6.setText("Tutup");
        BtnCloseIn6.setToolTipText("Alt+P");
        BtnCloseIn6.setName("BtnCloseIn6"); // NOI18N
        BtnCloseIn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn6ActionPerformed(evt);
            }
        });
        internalFrame8.add(BtnCloseIn6);
        BtnCloseIn6.setBounds(130, 87, 100, 30);

        BtnSimpan6.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan6.setMnemonic('S');
        BtnSimpan6.setText("Simpan");
        BtnSimpan6.setToolTipText("Alt+S");
        BtnSimpan6.setName("BtnSimpan6"); // NOI18N
        BtnSimpan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan6ActionPerformed(evt);
            }
        });
        internalFrame8.add(BtnSimpan6);
        BtnSimpan6.setBounds(20, 87, 100, 30);

        label40.setForeground(new java.awt.Color(0, 0, 0));
        label40.setText("No.Rekam Medik :");
        label40.setName("label40"); // NOI18N
        label40.setPreferredSize(new java.awt.Dimension(35, 23));
        internalFrame8.add(label40);
        label40.setBounds(0, 22, 105, 23);

        NoRmTujuan.setForeground(new java.awt.Color(0, 0, 0));
        NoRmTujuan.setHighlighter(null);
        NoRmTujuan.setMaxLenth(7);
        NoRmTujuan.setName("NoRmTujuan"); // NOI18N
        NoRmTujuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRmTujuanKeyPressed(evt);
            }
        });
        internalFrame8.add(NoRmTujuan);
        NoRmTujuan.setBounds(108, 22, 90, 23);

        NmPasienTujuan.setEditable(false);
        NmPasienTujuan.setForeground(new java.awt.Color(0, 0, 0));
        NmPasienTujuan.setHighlighter(null);
        NmPasienTujuan.setName("NmPasienTujuan"); // NOI18N
        internalFrame8.add(NmPasienTujuan);
        NmPasienTujuan.setBounds(108, 52, 300, 23);

        label41.setForeground(new java.awt.Color(0, 0, 0));
        label41.setText("Nama Pasien :");
        label41.setName("label41"); // NOI18N
        label41.setPreferredSize(new java.awt.Dimension(35, 23));
        internalFrame8.add(label41);
        label41.setBounds(0, 52, 105, 23);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('2');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+2");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(140, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        internalFrame8.add(BtnCari1);
        BtnCari1.setBounds(210, 22, 130, 23);

        WindowGabungRM.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        NoRm.setHighlighter(null);
        NoRm.setName("NoRm"); // NOI18N
        NoRm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRmKeyPressed(evt);
            }
        });

        kdsuku.setName("kdsuku"); // NOI18N
        kdsuku.setPreferredSize(new java.awt.Dimension(207, 23));

        kdbahasa.setName("kdbahasa"); // NOI18N
        kdbahasa.setPreferredSize(new java.awt.Dimension(207, 23));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ScrollMouseClicked(evt);
            }
        });

        tbPasien.setAutoCreateRowSorter(true);
        tbPasien.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPasien.setComponentPopupMenu(jPopupMenu1);
        tbPasien.setName("tbPasien"); // NOI18N
        tbPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPasienMouseClicked(evt);
            }
        });
        tbPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPasienKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPasien);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

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

        BtnEdit.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setEnabled(false);
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
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

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 30));
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
        jLabel10.setPreferredSize(new java.awt.Dimension(70, 30));
        panelGlass8.add(jLabel10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(72, 30));
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

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Alamat :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(58, 23));
        panelGlass9.add(jLabel11);

        Carialamat.setForeground(new java.awt.Color(0, 0, 0));
        Carialamat.setName("Carialamat"); // NOI18N
        Carialamat.setPreferredSize(new java.awt.Dimension(340, 23));
        Carialamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CarialamatKeyPressed(evt);
            }
        });
        panelGlass9.add(Carialamat);

        jSeparator5.setBackground(new java.awt.Color(220, 225, 215));
        jSeparator5.setForeground(new java.awt.Color(170, 190, 145));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setName("jSeparator5"); // NOI18N
        jSeparator5.setOpaque(true);
        jSeparator5.setPreferredSize(new java.awt.Dimension(1, 23));
        panelGlass9.add(jSeparator5);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Key Word :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(68, 23));
        panelGlass9.add(jLabel7);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(180, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('5');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+5");
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

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Limit Data :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        cmbHlm.setForeground(new java.awt.Color(0, 0, 0));
        cmbHlm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "50", "100", "200", "300", "400", "500", "1000", "Semua" }));
        cmbHlm.setName("cmbHlm"); // NOI18N
        cmbHlm.setOpaque(false);
        cmbHlm.setPreferredSize(new java.awt.Dimension(80, 23));
        cmbHlm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbHlmMouseClicked(evt);
            }
        });
        panelGlass9.add(cmbHlm);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(560, 300));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

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

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(560, 168));
        FormInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FormInputMouseClicked(evt);
            }
        });
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No.Rekam Medis :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(4, 12, 95, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nama Pasien :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(4, 40, 95, 23);

        TTmp.setForeground(new java.awt.Color(0, 0, 0));
        TTmp.setName("TTmp"); // NOI18N
        TTmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTmpKeyPressed(evt);
            }
        });
        FormInput.add(TTmp);
        TTmp.setBounds(102, 98, 188, 23);

        CmbJk.setForeground(new java.awt.Color(0, 0, 0));
        CmbJk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "LAKI-LAKI", "PEREMPUAN" }));
        CmbJk.setName("CmbJk"); // NOI18N
        CmbJk.setOpaque(false);
        CmbJk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJkKeyPressed(evt);
            }
        });
        FormInput.add(CmbJk);
        CmbJk.setBounds(102, 69, 110, 23);

        TNm.setForeground(new java.awt.Color(0, 0, 0));
        TNm.setHighlighter(null);
        TNm.setName("TNm"); // NOI18N
        TNm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNmActionPerformed(evt);
            }
        });
        TNm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TNmKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNmKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TNmKeyReleased(evt);
            }
        });
        FormInput.add(TNm);
        TNm.setBounds(102, 40, 290, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("J.K. :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(4, 69, 95, 23);

        CMbGd.setForeground(new java.awt.Color(0, 0, 0));
        CMbGd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "A", "B", "AB", "O" }));
        CMbGd.setName("CMbGd"); // NOI18N
        CMbGd.setOpaque(false);
        CMbGd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMbGdMouseClicked(evt);
            }
        });
        CMbGd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CMbGdKeyPressed(evt);
            }
        });
        FormInput.add(CMbGd);
        CMbGd.setBounds(322, 69, 70, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Gol. Darah :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(247, 69, 72, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tmp/Tgl. Lahir :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(4, 98, 95, 23);

        DTPLahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-11-2022" }));
        DTPLahir.setDisplayFormat("dd-MM-yyyy");
        DTPLahir.setName("DTPLahir"); // NOI18N
        DTPLahir.setOpaque(false);
        DTPLahir.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPLahirItemStateChanged(evt);
            }
        });
        DTPLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPLahirKeyPressed(evt);
            }
        });
        FormInput.add(DTPLahir);
        DTPLahir.setBounds(292, 98, 100, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Agama :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(432, 12, 60, 23);

        cmbAgama.setBackground(new java.awt.Color(245, 253, 240));
        cmbAgama.setForeground(new java.awt.Color(0, 0, 0));
        cmbAgama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ISLAM", "KRISTEN", "KATOLIK", "HINDU", "BUDHA", "KONG HU CHU", "-" }));
        cmbAgama.setLightWeightPopupEnabled(false);
        cmbAgama.setName("cmbAgama"); // NOI18N
        cmbAgama.setOpaque(false);
        cmbAgama.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbAgamaMouseClicked(evt);
            }
        });
        cmbAgama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbAgamaKeyPressed(evt);
            }
        });
        FormInput.add(cmbAgama);
        cmbAgama.setBounds(496, 12, 130, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Stts. Nikah :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(629, 12, 120, 23);

        CmbStts.setBackground(new java.awt.Color(245, 253, 240));
        CmbStts.setForeground(new java.awt.Color(0, 0, 0));
        CmbStts.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BELUM MENIKAH", "MENIKAH", "JANDA", "DUDA" }));
        CmbStts.setLightWeightPopupEnabled(false);
        CmbStts.setName("CmbStts"); // NOI18N
        CmbStts.setOpaque(false);
        CmbStts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CmbSttsMouseClicked(evt);
            }
        });
        CmbStts.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbSttsKeyPressed(evt);
            }
        });
        FormInput.add(CmbStts);
        CmbStts.setBounds(753, 12, 120, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Alamat KTP/KK :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(402, 156, 90, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("No.Telp Pasien :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(402, 98, 90, 23);

        Pekerjaan.setForeground(new java.awt.Color(0, 0, 0));
        Pekerjaan.setName("Pekerjaan"); // NOI18N
        Pekerjaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PekerjaanKeyPressed(evt);
            }
        });
        FormInput.add(Pekerjaan);
        Pekerjaan.setBounds(496, 127, 120, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Pekerjaan :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(402, 127, 90, 23);

        Alamat.setForeground(new java.awt.Color(0, 0, 0));
        Alamat.setText("ALAMAT");
        Alamat.setHighlighter(null);
        Alamat.setName("Alamat"); // NOI18N
        Alamat.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                AlamatMouseMoved(evt);
            }
        });
        Alamat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AlamatMouseExited(evt);
            }
        });
        Alamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatKeyPressed(evt);
            }
        });
        FormInput.add(Alamat);
        Alamat.setBounds(496, 156, 213, 23);

        TTlp.setForeground(new java.awt.Color(0, 0, 0));
        TTlp.setToolTipText("Harus diisi dengan angka..!!!");
        TTlp.setName("TTlp"); // NOI18N
        TTlp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTlpKeyPressed(evt);
            }
        });
        FormInput.add(TTlp);
        TTlp.setBounds(496, 98, 150, 23);

        TNo.setEditable(false);
        TNo.setBackground(new java.awt.Color(245, 250, 240));
        TNo.setForeground(new java.awt.Color(0, 0, 0));
        TNo.setName("TNo"); // NOI18N
        TNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoKeyPressed(evt);
            }
        });
        FormInput.add(TNo);
        TNo.setBounds(102, 12, 80, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("No.KTP/SIM :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(628, 127, 80, 23);

        TKtp.setForeground(new java.awt.Color(0, 0, 0));
        TKtp.setComponentPopupMenu(jPopupMenu2);
        TKtp.setName("TKtp"); // NOI18N
        TKtp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKtpKeyPressed(evt);
            }
        });
        FormInput.add(TKtp);
        TKtp.setBounds(712, 127, 130, 23);

        DTPDaftar.setEditable(false);
        DTPDaftar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-11-2022" }));
        DTPDaftar.setDisplayFormat("dd-MM-yyyy");
        DTPDaftar.setEnabled(false);
        DTPDaftar.setName("DTPDaftar"); // NOI18N
        DTPDaftar.setOpaque(false);
        DTPDaftar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPDaftarKeyPressed(evt);
            }
        });
        FormInput.add(DTPDaftar);
        DTPDaftar.setBounds(753, 98, 93, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Pertama Daftar :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(649, 98, 100, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Umur Pasien :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(4, 127, 95, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Pendidikan :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(247, 127, 72, 23);

        CMbPnd.setForeground(new java.awt.Color(0, 0, 0));
        CMbPnd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "TS", "TK", "SD", "SMP", "SMA", "D1", "D2", "D3", "D4", "S1", "S2", "S3" }));
        CMbPnd.setName("CMbPnd"); // NOI18N
        CMbPnd.setOpaque(false);
        CMbPnd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMbPndMouseClicked(evt);
            }
        });
        CMbPnd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CMbPndKeyPressed(evt);
            }
        });
        FormInput.add(CMbPnd);
        CMbPnd.setBounds(322, 127, 70, 23);

        Saudara.setForeground(new java.awt.Color(0, 0, 0));
        Saudara.setName("Saudara"); // NOI18N
        Saudara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SaudaraKeyPressed(evt);
            }
        });
        FormInput.add(Saudara);
        Saudara.setBounds(102, 214, 290, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Cara Bayar :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(402, 40, 90, 23);

        Kdpnj.setEditable(false);
        Kdpnj.setForeground(new java.awt.Color(0, 0, 0));
        Kdpnj.setText("-");
        Kdpnj.setHighlighter(null);
        Kdpnj.setName("Kdpnj"); // NOI18N
        Kdpnj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdpnjKeyPressed(evt);
            }
        });
        FormInput.add(Kdpnj);
        Kdpnj.setBounds(496, 40, 80, 23);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setText("-");
        nmpnj.setName("nmpnj"); // NOI18N
        FormInput.add(nmpnj);
        nmpnj.setBounds(578, 40, 265, 23);

        BtnPenjab.setForeground(new java.awt.Color(0, 0, 0));
        BtnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPenjab.setMnemonic('1');
        BtnPenjab.setToolTipText("ALt+1");
        BtnPenjab.setName("BtnPenjab"); // NOI18N
        BtnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPenjabActionPerformed(evt);
            }
        });
        FormInput.add(BtnPenjab);
        BtnPenjab.setBounds(845, 40, 28, 23);

        Kelurahan.setEditable(false);
        Kelurahan.setForeground(new java.awt.Color(0, 0, 0));
        Kelurahan.setText("KELURAHAN");
        Kelurahan.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Kelurahan.setEnabled(false);
        Kelurahan.setHighlighter(null);
        Kelurahan.setName("Kelurahan"); // NOI18N
        Kelurahan.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                KelurahanMouseMoved(evt);
            }
        });
        Kelurahan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KelurahanMouseExited(evt);
            }
        });
        Kelurahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelurahanKeyPressed(evt);
            }
        });
        FormInput.add(Kelurahan);
        Kelurahan.setBounds(712, 156, 130, 23);

        Kecamatan.setEditable(false);
        Kecamatan.setForeground(new java.awt.Color(0, 0, 0));
        Kecamatan.setText("KECAMATAN");
        Kecamatan.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Kecamatan.setEnabled(false);
        Kecamatan.setHighlighter(null);
        Kecamatan.setName("Kecamatan"); // NOI18N
        Kecamatan.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                KecamatanMouseMoved(evt);
            }
        });
        Kecamatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KecamatanMouseExited(evt);
            }
        });
        Kecamatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KecamatanKeyPressed(evt);
            }
        });
        FormInput.add(Kecamatan);
        Kecamatan.setBounds(496, 185, 130, 23);

        Kabupaten.setEditable(false);
        Kabupaten.setForeground(new java.awt.Color(0, 0, 0));
        Kabupaten.setText("KABUPATEN");
        Kabupaten.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Kabupaten.setEnabled(false);
        Kabupaten.setHighlighter(null);
        Kabupaten.setName("Kabupaten"); // NOI18N
        Kabupaten.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                KabupatenMouseMoved(evt);
            }
        });
        Kabupaten.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KabupatenMouseExited(evt);
            }
        });
        Kabupaten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KabupatenKeyPressed(evt);
            }
        });
        FormInput.add(Kabupaten);
        Kabupaten.setBounds(663, 185, 130, 23);

        BtnKelurahan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKelurahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKelurahan.setMnemonic('2');
        BtnKelurahan.setToolTipText("ALt+2");
        BtnKelurahan.setName("BtnKelurahan"); // NOI18N
        BtnKelurahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKelurahanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKelurahan);
        BtnKelurahan.setBounds(845, 156, 28, 23);

        BtnKecamatan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKecamatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKecamatan.setMnemonic('3');
        BtnKecamatan.setToolTipText("ALt+3");
        BtnKecamatan.setName("BtnKecamatan"); // NOI18N
        BtnKecamatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKecamatanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKecamatan);
        BtnKecamatan.setBounds(629, 185, 28, 23);

        BtnKabupaten.setForeground(new java.awt.Color(0, 0, 0));
        BtnKabupaten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKabupaten.setMnemonic('4');
        BtnKabupaten.setToolTipText("ALt+4");
        BtnKabupaten.setName("BtnKabupaten"); // NOI18N
        BtnKabupaten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKabupatenActionPerformed(evt);
            }
        });
        FormInput.add(BtnKabupaten);
        BtnKabupaten.setBounds(796, 185, 28, 23);

        ChkDaftar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(175, 180, 170)));
        ChkDaftar.setForeground(new java.awt.Color(0, 0, 0));
        ChkDaftar.setBorderPainted(true);
        ChkDaftar.setBorderPaintedFlat(true);
        ChkDaftar.setEnabled(false);
        ChkDaftar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkDaftar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkDaftar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkDaftar.setName("ChkDaftar"); // NOI18N
        ChkDaftar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkDaftarItemStateChanged(evt);
            }
        });
        ChkDaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkDaftarActionPerformed(evt);
            }
        });
        FormInput.add(ChkDaftar);
        ChkDaftar.setBounds(850, 98, 23, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Nama Ibu :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(4, 156, 95, 23);

        NmIbu.setForeground(new java.awt.Color(0, 0, 0));
        NmIbu.setName("NmIbu"); // NOI18N
        NmIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmIbuKeyPressed(evt);
            }
        });
        FormInput.add(NmIbu);
        NmIbu.setBounds(102, 156, 290, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Png. Jawab :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(4, 185, 95, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Nama Png. Jwb. :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(4, 214, 95, 23);

        PekerjaanPj.setForeground(new java.awt.Color(0, 0, 0));
        PekerjaanPj.setName("PekerjaanPj"); // NOI18N
        PekerjaanPj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PekerjaanPjKeyPressed(evt);
            }
        });
        FormInput.add(PekerjaanPj);
        PekerjaanPj.setBounds(102, 243, 290, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Pekerjaan P.J. :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(4, 243, 95, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Alamat P.J. : ");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(880, 69, 95, 23);

        AlamatPj.setForeground(new java.awt.Color(0, 0, 0));
        AlamatPj.setText("ALAMAT");
        AlamatPj.setHighlighter(null);
        AlamatPj.setName("AlamatPj"); // NOI18N
        AlamatPj.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                AlamatPjMouseMoved(evt);
            }
        });
        AlamatPj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AlamatPjMouseExited(evt);
            }
        });
        AlamatPj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatPjKeyPressed(evt);
            }
        });
        FormInput.add(AlamatPj);
        AlamatPj.setBounds(980, 69, 213, 23);

        KecamatanPj.setEditable(false);
        KecamatanPj.setForeground(new java.awt.Color(0, 0, 0));
        KecamatanPj.setText("KECAMATAN");
        KecamatanPj.setHighlighter(null);
        KecamatanPj.setName("KecamatanPj"); // NOI18N
        KecamatanPj.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                KecamatanPjMouseMoved(evt);
            }
        });
        KecamatanPj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KecamatanPjMouseExited(evt);
            }
        });
        KecamatanPj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KecamatanPjKeyPressed(evt);
            }
        });
        FormInput.add(KecamatanPj);
        KecamatanPj.setBounds(980, 127, 180, 23);

        BtnKecamatanPj.setForeground(new java.awt.Color(0, 0, 0));
        BtnKecamatanPj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKecamatanPj.setMnemonic('3');
        BtnKecamatanPj.setToolTipText("ALt+3");
        BtnKecamatanPj.setName("BtnKecamatanPj"); // NOI18N
        BtnKecamatanPj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKecamatanPjActionPerformed(evt);
            }
        });
        FormInput.add(BtnKecamatanPj);
        BtnKecamatanPj.setBounds(1162, 127, 28, 23);

        KabupatenPj.setEditable(false);
        KabupatenPj.setForeground(new java.awt.Color(0, 0, 0));
        KabupatenPj.setText("KABUPATEN");
        KabupatenPj.setHighlighter(null);
        KabupatenPj.setName("KabupatenPj"); // NOI18N
        KabupatenPj.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                KabupatenPjMouseMoved(evt);
            }
        });
        KabupatenPj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KabupatenPjMouseExited(evt);
            }
        });
        KabupatenPj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KabupatenPjKeyPressed(evt);
            }
        });
        FormInput.add(KabupatenPj);
        KabupatenPj.setBounds(980, 156, 180, 23);

        BtnKabupatenPj.setForeground(new java.awt.Color(0, 0, 0));
        BtnKabupatenPj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKabupatenPj.setMnemonic('4');
        BtnKabupatenPj.setToolTipText("ALt+4");
        BtnKabupatenPj.setName("BtnKabupatenPj"); // NOI18N
        BtnKabupatenPj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKabupatenPjActionPerformed(evt);
            }
        });
        FormInput.add(BtnKabupatenPj);
        BtnKabupatenPj.setBounds(1162, 156, 28, 23);

        BtnKelurahanPj.setForeground(new java.awt.Color(0, 0, 0));
        BtnKelurahanPj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKelurahanPj.setMnemonic('2');
        BtnKelurahanPj.setToolTipText("ALt+2");
        BtnKelurahanPj.setName("BtnKelurahanPj"); // NOI18N
        BtnKelurahanPj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKelurahanPjActionPerformed(evt);
            }
        });
        FormInput.add(BtnKelurahanPj);
        BtnKelurahanPj.setBounds(1162, 98, 28, 23);

        KelurahanPj.setEditable(false);
        KelurahanPj.setForeground(new java.awt.Color(0, 0, 0));
        KelurahanPj.setText("KELURAHAN");
        KelurahanPj.setHighlighter(null);
        KelurahanPj.setName("KelurahanPj"); // NOI18N
        KelurahanPj.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                KelurahanPjMouseMoved(evt);
            }
        });
        KelurahanPj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KelurahanPjMouseExited(evt);
            }
        });
        KelurahanPj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelurahanPjKeyPressed(evt);
            }
        });
        FormInput.add(KelurahanPj);
        KelurahanPj.setBounds(980, 98, 180, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("No. Peserta :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(392, 69, 100, 23);

        TNoPeserta.setForeground(new java.awt.Color(0, 0, 0));
        TNoPeserta.setName("TNoPeserta"); // NOI18N
        TNoPeserta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoPesertaKeyPressed(evt);
            }
        });
        FormInput.add(TNoPeserta);
        TNoPeserta.setBounds(496, 69, 270, 23);

        ChkRM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(175, 180, 170)));
        ChkRM.setForeground(new java.awt.Color(0, 0, 0));
        ChkRM.setSelected(true);
        ChkRM.setBorderPainted(true);
        ChkRM.setBorderPaintedFlat(true);
        ChkRM.setEnabled(false);
        ChkRM.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkRM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkRM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkRM.setName("ChkRM"); // NOI18N
        ChkRM.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkRMItemStateChanged(evt);
            }
        });
        FormInput.add(ChkRM);
        ChkRM.setBounds(185, 12, 23, 23);

        BtnKelurahan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKelurahan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/011.png"))); // NOI18N
        BtnKelurahan1.setMnemonic('2');
        BtnKelurahan1.setToolTipText("ALt+2");
        BtnKelurahan1.setComponentPopupMenu(jPopupMenu2);
        BtnKelurahan1.setName("BtnKelurahan1"); // NOI18N
        BtnKelurahan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKelurahan1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnKelurahan1);
        BtnKelurahan1.setBounds(845, 127, 28, 23);

        TUmurTh.setForeground(new java.awt.Color(0, 0, 0));
        TUmurTh.setName("TUmurTh"); // NOI18N
        TUmurTh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TUmurThKeyPressed(evt);
            }
        });
        FormInput.add(TUmurTh);
        TUmurTh.setBounds(102, 127, 35, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Th");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(131, 127, 20, 23);

        TUmurBl.setForeground(new java.awt.Color(0, 0, 0));
        TUmurBl.setName("TUmurBl"); // NOI18N
        TUmurBl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TUmurBlKeyPressed(evt);
            }
        });
        FormInput.add(TUmurBl);
        TUmurBl.setBounds(153, 127, 35, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Bl");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(178, 127, 20, 23);

        TUmurHr.setForeground(new java.awt.Color(0, 0, 0));
        TUmurHr.setName("TUmurHr"); // NOI18N
        TUmurHr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TUmurHrKeyPressed(evt);
            }
        });
        FormInput.add(TUmurHr);
        TUmurHr.setBounds(200, 127, 35, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Hr");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(229, 127, 20, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Suku/Bangsa :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(880, 12, 95, 23);

        nmsukubangsa.setEditable(false);
        nmsukubangsa.setForeground(new java.awt.Color(0, 0, 0));
        nmsukubangsa.setName("nmsukubangsa"); // NOI18N
        FormInput.add(nmsukubangsa);
        nmsukubangsa.setBounds(980, 12, 150, 23);

        BtnSuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSuku.setMnemonic('1');
        BtnSuku.setToolTipText("ALt+1");
        BtnSuku.setName("BtnSuku"); // NOI18N
        BtnSuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSukuActionPerformed(evt);
            }
        });
        BtnSuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSukuKeyPressed(evt);
            }
        });
        FormInput.add(BtnSuku);
        BtnSuku.setBounds(1135, 12, 28, 23);

        nmbahasa.setEditable(false);
        nmbahasa.setForeground(new java.awt.Color(0, 0, 0));
        nmbahasa.setName("nmbahasa"); // NOI18N
        FormInput.add(nmbahasa);
        nmbahasa.setBounds(980, 40, 150, 23);

        BtnBahasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnBahasa.setMnemonic('1');
        BtnBahasa.setToolTipText("ALt+1");
        BtnBahasa.setName("BtnBahasa"); // NOI18N
        BtnBahasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBahasaActionPerformed(evt);
            }
        });
        BtnBahasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBahasaKeyPressed(evt);
            }
        });
        FormInput.add(BtnBahasa);
        BtnBahasa.setBounds(1135, 40, 28, 23);

        ChkIbuAnak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(175, 180, 170)));
        ChkIbuAnak.setForeground(new java.awt.Color(0, 0, 0));
        ChkIbuAnak.setText("Duplikasikan dg. Data Pasien Lain");
        ChkIbuAnak.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkIbuAnak.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkIbuAnak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkIbuAnak.setName("ChkIbuAnak"); // NOI18N
        ChkIbuAnak.setOpaque(false);
        FormInput.add(ChkIbuAnak);
        ChkIbuAnak.setBounds(216, 12, 210, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Bahasa Dipakai :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(880, 40, 95, 23);

        cmbPngJawab.setForeground(new java.awt.Color(0, 0, 0));
        cmbPngJawab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SAUDARA", "AYAH", "IBU", "ANAK", "SUAMI", "ISTRI", "SEPUPU", "PASIEN SENDIRI", "PAMAN", "BIBI", "KAKEK", "NENEK", "TEMAN", "TETANGGA", "IPAR", "BESAN", "MENANTU", "MERTUA", "KEPONAKAN", "KAKAK", "ADIK", "CUCU" }));
        cmbPngJawab.setName("cmbPngJawab"); // NOI18N
        cmbPngJawab.setOpaque(false);
        cmbPngJawab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbPngJawabMouseClicked(evt);
            }
        });
        cmbPngJawab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbPngJawabKeyPressed(evt);
            }
        });
        FormInput.add(cmbPngJawab);
        cmbPngJawab.setBounds(102, 185, 130, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Alamat Domisili :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(402, 214, 90, 23);

        AlamatDomisili.setForeground(new java.awt.Color(0, 0, 0));
        AlamatDomisili.setText("ALAMAT");
        AlamatDomisili.setHighlighter(null);
        AlamatDomisili.setName("AlamatDomisili"); // NOI18N
        AlamatDomisili.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                AlamatDomisiliMouseMoved(evt);
            }
        });
        AlamatDomisili.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AlamatDomisiliMouseExited(evt);
            }
        });
        AlamatDomisili.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatDomisiliKeyPressed(evt);
            }
        });
        FormInput.add(AlamatDomisili);
        AlamatDomisili.setBounds(496, 214, 213, 23);

        KelurahanDom.setEditable(false);
        KelurahanDom.setForeground(new java.awt.Color(0, 0, 0));
        KelurahanDom.setText("KELURAHAN");
        KelurahanDom.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        KelurahanDom.setEnabled(false);
        KelurahanDom.setHighlighter(null);
        KelurahanDom.setName("KelurahanDom"); // NOI18N
        KelurahanDom.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                KelurahanDomMouseMoved(evt);
            }
        });
        KelurahanDom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KelurahanDomMouseExited(evt);
            }
        });
        KelurahanDom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelurahanDomKeyPressed(evt);
            }
        });
        FormInput.add(KelurahanDom);
        KelurahanDom.setBounds(712, 214, 130, 23);

        BtnKelurahanDom.setForeground(new java.awt.Color(0, 0, 0));
        BtnKelurahanDom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKelurahanDom.setMnemonic('2');
        BtnKelurahanDom.setToolTipText("ALt+2");
        BtnKelurahanDom.setName("BtnKelurahanDom"); // NOI18N
        BtnKelurahanDom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKelurahanDomActionPerformed(evt);
            }
        });
        FormInput.add(BtnKelurahanDom);
        BtnKelurahanDom.setBounds(845, 214, 28, 23);

        KecamatanDom.setEditable(false);
        KecamatanDom.setForeground(new java.awt.Color(0, 0, 0));
        KecamatanDom.setText("KECAMATAN");
        KecamatanDom.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        KecamatanDom.setEnabled(false);
        KecamatanDom.setHighlighter(null);
        KecamatanDom.setName("KecamatanDom"); // NOI18N
        KecamatanDom.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                KecamatanDomMouseMoved(evt);
            }
        });
        KecamatanDom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KecamatanDomMouseExited(evt);
            }
        });
        KecamatanDom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KecamatanDomKeyPressed(evt);
            }
        });
        FormInput.add(KecamatanDom);
        KecamatanDom.setBounds(496, 243, 130, 23);

        BtnKecamatanDom.setForeground(new java.awt.Color(0, 0, 0));
        BtnKecamatanDom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKecamatanDom.setMnemonic('3');
        BtnKecamatanDom.setToolTipText("ALt+3");
        BtnKecamatanDom.setName("BtnKecamatanDom"); // NOI18N
        BtnKecamatanDom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKecamatanDomActionPerformed(evt);
            }
        });
        FormInput.add(BtnKecamatanDom);
        BtnKecamatanDom.setBounds(629, 243, 28, 23);

        KabupatenDom.setEditable(false);
        KabupatenDom.setForeground(new java.awt.Color(0, 0, 0));
        KabupatenDom.setText("KABUPATEN");
        KabupatenDom.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        KabupatenDom.setEnabled(false);
        KabupatenDom.setHighlighter(null);
        KabupatenDom.setName("KabupatenDom"); // NOI18N
        KabupatenDom.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                KabupatenDomMouseMoved(evt);
            }
        });
        KabupatenDom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KabupatenDomMouseExited(evt);
            }
        });
        KabupatenDom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KabupatenDomKeyPressed(evt);
            }
        });
        FormInput.add(KabupatenDom);
        KabupatenDom.setBounds(663, 243, 130, 23);

        BtnKabupatenDom.setForeground(new java.awt.Color(0, 0, 0));
        BtnKabupatenDom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKabupatenDom.setMnemonic('4');
        BtnKabupatenDom.setToolTipText("ALt+4");
        BtnKabupatenDom.setName("BtnKabupatenDom"); // NOI18N
        BtnKabupatenDom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKabupatenDomActionPerformed(evt);
            }
        });
        FormInput.add(BtnKabupatenDom);
        BtnKabupatenDom.setBounds(796, 243, 28, 23);

        ChkSamakan.setBorder(null);
        ChkSamakan.setForeground(new java.awt.Color(0, 0, 0));
        ChkSamakan.setText("Domisili Disamakan dg. Alamat KTP/KK");
        ChkSamakan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkSamakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSamakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSamakan.setName("ChkSamakan"); // NOI18N
        ChkSamakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSamakanActionPerformed(evt);
            }
        });
        FormInput.add(ChkSamakan);
        ChkSamakan.setBounds(850, 243, 250, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Umur : ");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(232, 185, 50, 23);

        umurPjawab.setForeground(new java.awt.Color(0, 0, 0));
        umurPjawab.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        umurPjawab.setName("umurPjawab"); // NOI18N
        umurPjawab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                umurPjawabKeyPressed(evt);
            }
        });
        FormInput.add(umurPjawab);
        umurPjawab.setBounds(284, 185, 45, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("Tahun");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(336, 185, 50, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("No.Telp Png. Jawab :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(845, 185, 130, 23);

        notlpPJ.setForeground(new java.awt.Color(0, 0, 0));
        notlpPJ.setToolTipText("Harus diisi dengan angka..!!!");
        notlpPJ.setName("notlpPJ"); // NOI18N
        FormInput.add(notlpPJ);
        notlpPJ.setBounds(980, 185, 150, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void tbPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPasienMouseClicked
    if (tabMode.getRowCount() != 0) {
        try {
            getData();
        } catch (java.lang.NullPointerException e) {
        }
    }
}//GEN-LAST:event_tbPasienMouseClicked

private void tbPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienKeyPressed
    if (tabMode.getRowCount() != 0) {
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            akses.setform(asalform);
        } else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
            TCari.setText("");
            TCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_A) {
            for (z = 0; z < tbPasien.getRowCount(); z++) {
                tbPasien.setValueAt(true, z, 0);
            }
        }
    }
}//GEN-LAST:event_tbPasienKeyPressed

private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
    if (TNo.getText().trim().equals("")) {
        Valid.textKosong(TNo, "No.Rekam Medis");
    } else if (TNm.getText().trim().equals("")) {
        Valid.textKosong(TNm, "nama pasien");
    } else if (nmpnj.getText().trim().equals("") || Kdpnj.getText().trim().equals("")) {
        Valid.textKosong(Kdpnj, "Asuransi/Penanggung Jawab");
    } else if (CmbJk.getSelectedItem().toString().equals(" ")) {
        Valid.textKosong(CmbJk, "jenis kelamin pasien");
    } else if (kdsuku.getText().trim().equals("") || kdbahasa.getText().trim().equals("")) {
        Valid.textKosong(kdsuku, "Suku atau bahasa Pasien");
    } else if (no_ktp.equals("Yes") && (TKtp.getText().trim().length() < p_no_ktp)) {
        Valid.textKosong(TKtp, "No.KTP/SIM minimal " + p_no_ktp + " karakter dan ");
    } else if (tmp_lahir.equals("Yes") && (TTmp.getText().trim().length() < p_tmp_lahir)) {
        Valid.textKosong(TTmp, "Tempat Lahir minimal " + p_tmp_lahir + " karakter dan ");
    } else if (nm_ibu.equals("Yes") && (NmIbu.getText().trim().length() < p_nm_ibu)) {
        Valid.textKosong(NmIbu, "Nama Ibu minimal " + p_nm_ibu + " karakter dan ");
    } else if (alamat.equals("Yes") && (Alamat.getText().trim().length() < p_alamat)) {
        Valid.textKosong(Alamat, "Alamat Pasien minimal " + p_alamat + " karakter dan ");
    } else if (alamatDom.equals("Yes") && (AlamatDomisili.getText().trim().length() < p_alamatDom)) {
        Valid.textKosong(AlamatDomisili, "Alamat Domisili Pasien minimal " + p_alamatDom + " karakter dan ");
    } else if (pekerjaan.equals("Yes") && (Pekerjaan.getText().trim().length() < p_pekerjaan)) {
        Valid.textKosong(Pekerjaan, "Pekerjaan Pasien minimal " + p_pekerjaan + " karakter dan ");
    } else if (no_tlp.equals("Yes") && (TTlp.getText().trim().length() < p_no_tlp)) {
        Valid.textKosong(TTlp, "Telp Pasien minimal " + p_no_tlp + " karakter dan ");
    } else if (umur.equals("Yes") && (TUmurTh.getText().trim().length() < p_umur)) {
        Valid.textKosong(TUmurTh, "Umur Pasien minimal " + p_umur + " karakter dan ");
    } else if (namakeluarga.equals("Yes") && (Saudara.getText().trim().length() < p_namakeluarga)) {
        Valid.textKosong(Saudara, "Penanggung Jawab Pasien minimal " + p_namakeluarga + " karakter dan ");
    } else if (no_peserta.equals("Yes") && (TNoPeserta.getText().trim().length() < p_no_peserta)) {
        Valid.textKosong(TNoPeserta, "No.Peserta Pasien minimal " + p_no_peserta + " karakter dan ");
    } else if (kelurahan.equals("Yes") && (Kelurahan.getText().trim().length() < p_kelurahan)) {
        Valid.textKosong(Kelurahan, "Kelurahan minimal " + p_kelurahan + " karakter dan ");
    } else if (kecamatan.equals("Yes") && (Kecamatan.getText().trim().length() < p_kecamatan)) {
        Valid.textKosong(Kecamatan, "Kecamatan minimal " + p_kecamatan + " karakter dan ");
    } else if (kabupaten.equals("Yes") && (Kabupaten.getText().trim().length() < p_kabupaten)) {
        Valid.textKosong(Kabupaten, "Kabupaten minimal " + p_kabupaten + " karakter dan ");
    } else if (kelurahanDom.equals("Yes") && (KelurahanDom.getText().trim().length() < p_kelurahanDom)) {
        Valid.textKosong(KelurahanDom, "Kelurahan Domisili minimal " + p_kelurahanDom + " karakter dan ");
    } else if (kecamatanDom.equals("Yes") && (KecamatanDom.getText().trim().length() < p_kecamatanDom)) {
        Valid.textKosong(KecamatanDom, "Kecamatan Domisili minimal " + p_kecamatanDom + " karakter dan ");
    } else if (kabupatenDom.equals("Yes") && (KabupatenDom.getText().trim().length() < p_kabupatenDom)) {
        Valid.textKosong(KabupatenDom, "Kabupaten Domisili minimal " + p_kabupatenDom + " karakter dan ");
    } else if (pekerjaanpj.equals("Yes") && (PekerjaanPj.getText().trim().length() < p_pekerjaanpj)) {
        Valid.textKosong(PekerjaanPj, "Pekerjaan P.J. minimal " + p_pekerjaanpj + " karakter dan ");
    } else if (alamatpj.equals("Yes") && (AlamatPj.getText().trim().length() < p_alamatpj)) {
        Valid.textKosong(AlamatPj, "Alamat P.J. minimal " + p_alamatpj + " karakter dan ");
    } else if (kelurahanpj.equals("Yes") && (KelurahanPj.getText().trim().length() < p_kelurahanpj)) {
        Valid.textKosong(KelurahanPj, "Kelurahan P.J. minimal " + p_kelurahanpj + " karakter dan ");
    } else if (kecamatanpj.equals("Yes") && (KecamatanPj.getText().trim().length() < p_kecamatanpj)) {
        Valid.textKosong(KecamatanPj, "Kecamatan P.J. minimal " + p_kecamatanpj + " karakter dan ");
    } else if (kabupatenpj.equals("Yes") && (KabupatenPj.getText().trim().length() < p_kabupatenpj)) {
        Valid.textKosong(KabupatenPj, "Kabupaten P.J. minimal " + p_kabupatenpj + " karakter dan ");
    } else if (Kecamatan.getText().equals("-") || Kecamatan.getText().equals("KECAMATAN")
            || Kelurahan.getText().equals("-") || Kelurahan.getText().equals("KELURAHAN")
            || Kabupaten.getText().equals("-") || Kabupaten.getText().equals("KABUPATEN")) {
        JOptionPane.showMessageDialog(null, "Kelurahan / Kecamatan / Kecamatan tidak boleh kosong (-)");
    } else if (KecamatanDom.getText().equals("-") || KecamatanDom.getText().equals("KECAMATAN")
            || KelurahanDom.getText().equals("-") || KelurahanDom.getText().equals("KELURAHAN")
            || KabupatenDom.getText().equals("-") || KabupatenDom.getText().equals("KABUPATEN")) {
        JOptionPane.showMessageDialog(null, "Kelurahan / Kecamatan / Kecamatan domisili tidak boleh kosong (-)");
    } else if ((CMbPnd.getSelectedItem().toString().equals("-")) && (umurBy >= 6)) {
        JOptionPane.showMessageDialog(null, "Pendidikan Harus di Isi");
    } else if ((!CMbPnd.getSelectedItem().toString().equals("-")) && (umurBy < 6)) {
        JOptionPane.showMessageDialog(null, "Pendidikan Tidak Sesuai");
    } else if (cmbPngJawab.getSelectedIndex() == 4 && umurPjawab.getText().equals("")) {
        Valid.textKosong(umurPjawab, "umur suami");
        umurPjawab.requestFocus();
    } else {
        ChkSamakanActionPerformed(null);
        lahir = DTPLahir.getDate();
        birthday = lahir.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        p = Period.between(birthday, today);
        p2 = ChronoUnit.DAYS.between(birthday, today);

        if (p2 < 0) {
            JOptionPane.showMessageDialog(null, "Tanggal lahir pasien SALAH, cek tanggal lahirnya lagi...!!!");
            DTPLahir.requestFocus();
        } else {
            cekValid();
            if (((!TNoPeserta.getText().equals("0")) && (!TNoPeserta.getText().equals("-"))) || ((!TKtp.getText().equals("0")) && (!TKtp.getText().equals("-")))) {
                if (cekPeserta > 0 || cekKTP > 0) {
                    JOptionPane.showMessageDialog(null, "Pasien sudah terdaftar. Cari berdasarkan No. KTP / No. Kartu BPJS");
                } else {
                    Sequel.AutoComitFalse();

                    if (Sequel.menyimpantf2("pasien", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rekam Medis Pasien", 37, new String[]{
                        TNo.getText(), TNm.getText(), TKtp.getText(), CmbJk.getSelectedItem().toString().substring(0, 1), TTmp.getText(),
                        Valid.SetTgl(DTPLahir.getSelectedItem() + ""), NmIbu.getText(), Alamat.getText().replaceAll("ALAMAT", ""), CMbGd.getSelectedItem().toString(), 
                        Pekerjaan.getText(), CmbStts.getSelectedItem().toString(), cmbAgama.getSelectedItem().toString(), 
                        DTPDaftar.getSelectedItem().toString().substring(6, 10) + "-" + DTPDaftar.getSelectedItem().toString().substring(3, 5) + "-" + DTPDaftar.getSelectedItem().toString().substring(0, 2),
                        TTlp.getText(), TUmurTh.getText() + " Th " + TUmurBl.getText() + " Bl " + TUmurHr.getText() + " Hr", CMbPnd.getSelectedItem().toString(), cmbPngJawab.getSelectedItem().toString(), Saudara.getText(), Kdpnj.getText(), TNoPeserta.getText(),
                        Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", Kelurahan.getText().replaceAll("KELURAHAN", "-")),
                        Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", Kecamatan.getText().replaceAll("KECAMATAN", "-")),
                        Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", Kabupaten.getText().replaceAll("KABUPATEN", "-")),
                        PekerjaanPj.getText(), AlamatPj.getText(), KelurahanPj.getText(), KecamatanPj.getText(), KabupatenPj.getText(), "0", kdsuku.getText(), kdbahasa.getText(), AlamatDomisili.getText().replaceAll("ALAMAT", ""),
                        Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", KelurahanDom.getText().replaceAll("KELURAHAN", "-")),
                        Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", KecamatanDom.getText().replaceAll("KECAMATAN", "-")),
                        Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", KabupatenDom.getText().replaceAll("KABUPATEN", "-")), umurPjawab.getText(), notlpPJ.getText()
                    }) == true) {
                        if (akses.getform().equals("DlgReg")) {
                            TCari.setText(TNo.getText());
                        }
                        tampil();
                        if (ChkRM.isSelected() == true) {
                            Sequel.queryu2("delete from set_no_rkm_medis");
                            Sequel.queryu2("insert into set_no_rkm_medis values(?)", 1, new String[]{TNo.getText()});
                        }
                        emptTeks();
                    } else {
                        autoNomor();
                        if (Sequel.menyimpantf2("pasien", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rekam Medis Pasien", 37, new String[]{
                            TNo.getText(), TNm.getText(), TKtp.getText(), CmbJk.getSelectedItem().toString().substring(0, 1), TTmp.getText(),
                            Valid.SetTgl(DTPLahir.getSelectedItem() + ""), NmIbu.getText(),
                            Alamat.getText().replaceAll("ALAMAT", ""), CMbGd.getSelectedItem().toString(), Pekerjaan.getText(), CmbStts.getSelectedItem().toString(), cmbAgama.getSelectedItem().toString(),
                            DTPDaftar.getSelectedItem().toString().substring(6, 10) + "-" + DTPDaftar.getSelectedItem().toString().substring(3, 5) + "-" + DTPDaftar.getSelectedItem().toString().substring(0, 2),
                            TTlp.getText(), TUmurTh.getText() + " Th", CMbPnd.getSelectedItem().toString(), cmbPngJawab.getSelectedItem().toString(), Saudara.getText(), Kdpnj.getText(), TNoPeserta.getText(),
                            Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", Kelurahan.getText().replaceAll("KELURAHAN", "-")),
                            Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", Kecamatan.getText().replaceAll("KECAMATAN", "-")),
                            Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", Kabupaten.getText().replaceAll("KABUPATEN", "-")),
                            PekerjaanPj.getText(), AlamatPj.getText(), KelurahanPj.getText(), KecamatanPj.getText(), KabupatenPj.getText(), "0", kdsuku.getText(), kdbahasa.getText(), AlamatDomisili.getText().replaceAll("ALAMAT", ""),
                            Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", KelurahanDom.getText().replaceAll("KELURAHAN", "-")),
                            Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", KecamatanDom.getText().replaceAll("KECAMATAN", "-")),
                            Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", KabupatenDom.getText().replaceAll("KABUPATEN", "-")), umurPjawab.getText(), notlpPJ.getText()
                        }) == true) {
                            if (akses.getform().equals("DlgReg")) {
                                TCari.setText(TNo.getText());
                            }
                            tampil();
                            if (ChkRM.isSelected() == true) {
                                Sequel.queryu2("delete from set_no_rkm_medis");
                                Sequel.queryu2("insert into set_no_rkm_medis values(?)", 1, new String[]{TNo.getText()});
                            }
                            emptTeks();
                        } else {
                            autoNomor();
                            if (Sequel.menyimpantf2("pasien", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rekam Medis Pasien", 37, new String[]{
                                TNo.getText(), TNm.getText(), TKtp.getText(), CmbJk.getSelectedItem().toString().substring(0, 1), TTmp.getText(),
                                Valid.SetTgl(DTPLahir.getSelectedItem() + ""), NmIbu.getText(),
                                Alamat.getText().replaceAll("ALAMAT", ""), CMbGd.getSelectedItem().toString(), Pekerjaan.getText(), CmbStts.getSelectedItem().toString(), cmbAgama.getSelectedItem().toString(),
                                DTPDaftar.getSelectedItem().toString().substring(6, 10) + "-" + DTPDaftar.getSelectedItem().toString().substring(3, 5) + "-" + DTPDaftar.getSelectedItem().toString().substring(0, 2),
                                TTlp.getText(), TUmurTh.getText() + " Th", CMbPnd.getSelectedItem().toString(), cmbPngJawab.getSelectedItem().toString(), Saudara.getText(), Kdpnj.getText(), TNoPeserta.getText(),
                                Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", Kelurahan.getText().replaceAll("KELURAHAN", "-")),
                                Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", Kecamatan.getText().replaceAll("KECAMATAN", "-")),
                                Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", Kabupaten.getText().replaceAll("KABUPATEN", "-")),
                                PekerjaanPj.getText(), AlamatPj.getText(), KelurahanPj.getText(), KecamatanPj.getText(), KabupatenPj.getText(), "0", kdsuku.getText(), kdbahasa.getText(), AlamatDomisili.getText().replaceAll("ALAMAT", ""),
                                Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", KelurahanDom.getText().replaceAll("KELURAHAN", "-")),
                                Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", KecamatanDom.getText().replaceAll("KECAMATAN", "-")),
                                Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", KabupatenDom.getText().replaceAll("KABUPATEN", "-")), umurPjawab.getText(), notlpPJ.getText()
                            }) == true) {
                                if (akses.getform().equals("DlgReg")) {
                                    TCari.setText(TNo.getText());
                                }
                                tampil();
                                if (ChkRM.isSelected() == true) {
                                    Sequel.queryu2("delete from set_no_rkm_medis");
                                    Sequel.queryu2("insert into set_no_rkm_medis values(?)", 1, new String[]{TNo.getText()});
                                }
                                emptTeks();
                            } else {
                                autoNomor();
                                if (Sequel.menyimpantf2("pasien", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rekam Medis Pasien", 37, new String[]{
                                    TNo.getText(), TNm.getText(), TKtp.getText(), CmbJk.getSelectedItem().toString().substring(0, 1), TTmp.getText(),
                                    Valid.SetTgl(DTPLahir.getSelectedItem() + ""), NmIbu.getText(),
                                    Alamat.getText().replaceAll("ALAMAT", ""), CMbGd.getSelectedItem().toString(), Pekerjaan.getText(), CmbStts.getSelectedItem().toString(), cmbAgama.getSelectedItem().toString(),
                                    DTPDaftar.getSelectedItem().toString().substring(6, 10) + "-" + DTPDaftar.getSelectedItem().toString().substring(3, 5) + "-" + DTPDaftar.getSelectedItem().toString().substring(0, 2),
                                    TTlp.getText(), TUmurTh.getText() + " Th", CMbPnd.getSelectedItem().toString(), cmbPngJawab.getSelectedItem().toString(), Saudara.getText(), Kdpnj.getText(), TNoPeserta.getText(),
                                    Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", Kelurahan.getText().replaceAll("KELURAHAN", "-")),
                                    Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", Kecamatan.getText().replaceAll("KECAMATAN", "-")),
                                    Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", Kabupaten.getText().replaceAll("KABUPATEN", "-")),
                                    PekerjaanPj.getText(), AlamatPj.getText(), KelurahanPj.getText(), KecamatanPj.getText(), KabupatenPj.getText(), "0", kdsuku.getText(), kdbahasa.getText(), AlamatDomisili.getText().replaceAll("ALAMAT", ""),
                                    Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", KelurahanDom.getText().replaceAll("KELURAHAN", "-")),
                                    Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", KecamatanDom.getText().replaceAll("KECAMATAN", "-")),
                                    Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", KabupatenDom.getText().replaceAll("KABUPATEN", "-")), umurPjawab.getText(), notlpPJ.getText()
                                }) == true) {
                                    if (akses.getform().equals("DlgReg")) {
                                        TCari.setText(TNo.getText());
                                    }
                                    tampil();
                                    if (ChkRM.isSelected() == true) {
                                        Sequel.queryu2("delete from set_no_rkm_medis");
                                        Sequel.queryu2("insert into set_no_rkm_medis values(?)", 1, new String[]{TNo.getText()});
                                    }
                                    emptTeks();
                                } else {
                                    autoNomor();
                                    if (Sequel.menyimpantf("pasien", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rekam Medis Pasien", 37, new String[]{
                                        TNo.getText(), TNm.getText(), TKtp.getText(), CmbJk.getSelectedItem().toString().substring(0, 1), TTmp.getText(),
                                        Valid.SetTgl(DTPLahir.getSelectedItem() + ""), NmIbu.getText(),
                                        Alamat.getText().replaceAll("ALAMAT", ""), CMbGd.getSelectedItem().toString(), Pekerjaan.getText(), CmbStts.getSelectedItem().toString(), cmbAgama.getSelectedItem().toString(),
                                        DTPDaftar.getSelectedItem().toString().substring(6, 10) + "-" + DTPDaftar.getSelectedItem().toString().substring(3, 5) + "-" + DTPDaftar.getSelectedItem().toString().substring(0, 2),
                                        TTlp.getText(), TUmurTh.getText() + " Th", CMbPnd.getSelectedItem().toString(), cmbPngJawab.getSelectedItem().toString(), Saudara.getText(), Kdpnj.getText(), TNoPeserta.getText(),
                                        Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", Kelurahan.getText().replaceAll("KELURAHAN", "-")),
                                        Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", Kecamatan.getText().replaceAll("KECAMATAN", "-")),
                                        Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", Kabupaten.getText().replaceAll("KABUPATEN", "-")),
                                        PekerjaanPj.getText(), AlamatPj.getText(), KelurahanPj.getText(), KecamatanPj.getText(), KabupatenPj.getText(), "0", kdsuku.getText(), kdbahasa.getText(), AlamatDomisili.getText().replaceAll("ALAMAT", ""),
                                        Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", KelurahanDom.getText().replaceAll("KELURAHAN", "-")),
                                        Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", KecamatanDom.getText().replaceAll("KECAMATAN", "-")),
                                        Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", KabupatenDom.getText().replaceAll("KABUPATEN", "-")), umurPjawab.getText(), notlpPJ.getText()
                                    }) == true) {
                                        if (akses.getform().equals("DlgReg")) {
                                            TCari.setText(TNo.getText());
                                        }
                                        tampil();
                                        if (ChkRM.isSelected() == true) {
                                            Sequel.queryu2("delete from set_no_rkm_medis");
                                            Sequel.queryu2("insert into set_no_rkm_medis values(?)", 1, new String[]{TNo.getText()});
                                        }
                                        emptTeks();
                                    } else {
                                        TNm.requestFocus();
                                        autoNomor();
                                    }
                                }
                            }
                        }
                    }
                    Sequel.menyimpan("history_user", "Now(),'-','" + akses.getkode() + "','" + keterangan + "','Simpan'");
                    Sequel.AutoComitTrue();

                }

            } else {
                Sequel.AutoComitFalse();

                if (Sequel.menyimpantf2("pasien", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rekam Medis Pasien", 37, new String[]{
                    TNo.getText(), TNm.getText(), TKtp.getText(), CmbJk.getSelectedItem().toString().substring(0, 1), TTmp.getText(),
                    Valid.SetTgl(DTPLahir.getSelectedItem() + ""), NmIbu.getText(),
                    Alamat.getText().replaceAll("ALAMAT", ""), CMbGd.getSelectedItem().toString(), Pekerjaan.getText(), CmbStts.getSelectedItem().toString(), cmbAgama.getSelectedItem().toString(),
                    DTPDaftar.getSelectedItem().toString().substring(6, 10) + "-" + DTPDaftar.getSelectedItem().toString().substring(3, 5) + "-" + DTPDaftar.getSelectedItem().toString().substring(0, 2),
                    TTlp.getText(), TUmurTh.getText() + " Th " + TUmurBl.getText() + " Bl " + TUmurHr.getText() + " Hr", CMbPnd.getSelectedItem().toString(), cmbPngJawab.getSelectedItem().toString(), Saudara.getText(), Kdpnj.getText(), TNoPeserta.getText(),
                    Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", Kelurahan.getText().replaceAll("KELURAHAN", "-")),
                    Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", Kecamatan.getText().replaceAll("KECAMATAN", "-")),
                    Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", Kabupaten.getText().replaceAll("KABUPATEN", "-")),
                    PekerjaanPj.getText(), AlamatPj.getText(), KelurahanPj.getText(), KecamatanPj.getText(), KabupatenPj.getText(), "0", kdsuku.getText(), kdbahasa.getText(), AlamatDomisili.getText().replaceAll("ALAMAT", ""),
                    Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", KelurahanDom.getText().replaceAll("KELURAHAN", "-")),
                    Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", KecamatanDom.getText().replaceAll("KECAMATAN", "-")),
                    Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", KabupatenDom.getText().replaceAll("KABUPATEN", "-")), umurPjawab.getText(), notlpPJ.getText()
                }) == true) {
                    if (akses.getform().equals("DlgReg")) {
                        TCari.setText(TNo.getText());
                    }
                    tampil();
                    if (ChkRM.isSelected() == true) {
                        Sequel.queryu2("delete from set_no_rkm_medis");
                        Sequel.queryu2("insert into set_no_rkm_medis values(?)", 1, new String[]{TNo.getText()});
                    }
                    emptTeks();
                } else {
                    autoNomor();
                    if (Sequel.menyimpantf2("pasien", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rekam Medis Pasien", 37, new String[]{
                        TNo.getText(), TNm.getText(), TKtp.getText(), CmbJk.getSelectedItem().toString().substring(0, 1), TTmp.getText(),
                        Valid.SetTgl(DTPLahir.getSelectedItem() + ""), NmIbu.getText(),
                        Alamat.getText().replaceAll("ALAMAT", ""), CMbGd.getSelectedItem().toString(), Pekerjaan.getText(), CmbStts.getSelectedItem().toString(), cmbAgama.getSelectedItem().toString(),
                        DTPDaftar.getSelectedItem().toString().substring(6, 10) + "-" + DTPDaftar.getSelectedItem().toString().substring(3, 5) + "-" + DTPDaftar.getSelectedItem().toString().substring(0, 2),
                        TTlp.getText(), TUmurTh.getText() + " Th", CMbPnd.getSelectedItem().toString(), cmbPngJawab.getSelectedItem().toString(), Saudara.getText(), Kdpnj.getText(), TNoPeserta.getText(),
                        Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", Kelurahan.getText().replaceAll("KELURAHAN", "-")),
                        Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", Kecamatan.getText().replaceAll("KECAMATAN", "-")),
                        Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", Kabupaten.getText().replaceAll("KABUPATEN", "-")),
                        PekerjaanPj.getText(), AlamatPj.getText(), KelurahanPj.getText(), KecamatanPj.getText(), KabupatenPj.getText(), "0", kdsuku.getText(), kdbahasa.getText(), AlamatDomisili.getText().replaceAll("ALAMAT", ""),
                        Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", KelurahanDom.getText().replaceAll("KELURAHAN", "-")),
                        Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", KecamatanDom.getText().replaceAll("KECAMATAN", "-")),
                        Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", KabupatenDom.getText().replaceAll("KABUPATEN", "-")), umurPjawab.getText(), notlpPJ.getText()
                    }) == true) {
                        if (akses.getform().equals("DlgReg")) {
                            TCari.setText(TNo.getText());
                        }
                        tampil();
                        if (ChkRM.isSelected() == true) {
                            Sequel.queryu2("delete from set_no_rkm_medis");
                            Sequel.queryu2("insert into set_no_rkm_medis values(?)", 1, new String[]{TNo.getText()});
                        }
                        emptTeks();
                    } else {
                        autoNomor();
                        if (Sequel.menyimpantf2("pasien", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rekam Medis Pasien", 37, new String[]{
                            TNo.getText(), TNm.getText(), TKtp.getText(), CmbJk.getSelectedItem().toString().substring(0, 1), TTmp.getText(),
                            Valid.SetTgl(DTPLahir.getSelectedItem() + ""), NmIbu.getText(),
                            Alamat.getText().replaceAll("ALAMAT", ""), CMbGd.getSelectedItem().toString(), Pekerjaan.getText(), CmbStts.getSelectedItem().toString(), cmbAgama.getSelectedItem().toString(),
                            DTPDaftar.getSelectedItem().toString().substring(6, 10) + "-" + DTPDaftar.getSelectedItem().toString().substring(3, 5) + "-" + DTPDaftar.getSelectedItem().toString().substring(0, 2),
                            TTlp.getText(), TUmurTh.getText() + " Th", CMbPnd.getSelectedItem().toString(), cmbPngJawab.getSelectedItem().toString(), Saudara.getText(), Kdpnj.getText(), TNoPeserta.getText(),
                            Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", Kelurahan.getText().replaceAll("KELURAHAN", "-")),
                            Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", Kecamatan.getText().replaceAll("KECAMATAN", "-")),
                            Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", Kabupaten.getText().replaceAll("KABUPATEN", "-")),
                            PekerjaanPj.getText(), AlamatPj.getText(), KelurahanPj.getText(), KecamatanPj.getText(), KabupatenPj.getText(), "0", kdsuku.getText(), kdbahasa.getText(), AlamatDomisili.getText().replaceAll("ALAMAT", ""),
                            Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", KelurahanDom.getText().replaceAll("KELURAHAN", "-")),
                            Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", KecamatanDom.getText().replaceAll("KECAMATAN", "-")),
                            Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", KabupatenDom.getText().replaceAll("KABUPATEN", "-")), umurPjawab.getText(), notlpPJ.getText()
                        }) == true) {
                            if (akses.getform().equals("DlgReg")) {
                                TCari.setText(TNo.getText());
                            }
                            tampil();
                            if (ChkRM.isSelected() == true) {
                                Sequel.queryu2("delete from set_no_rkm_medis");
                                Sequel.queryu2("insert into set_no_rkm_medis values(?)", 1, new String[]{TNo.getText()});
                            }
                            emptTeks();
                        } else {
                            autoNomor();
                            if (Sequel.menyimpantf2("pasien", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rekam Medis Pasien", 37, new String[]{
                                TNo.getText(), TNm.getText(), TKtp.getText(), CmbJk.getSelectedItem().toString().substring(0, 1), TTmp.getText(),
                                Valid.SetTgl(DTPLahir.getSelectedItem() + ""), NmIbu.getText(),
                                Alamat.getText().replaceAll("ALAMAT", ""), CMbGd.getSelectedItem().toString(), Pekerjaan.getText(), CmbStts.getSelectedItem().toString(), cmbAgama.getSelectedItem().toString(),
                                DTPDaftar.getSelectedItem().toString().substring(6, 10) + "-" + DTPDaftar.getSelectedItem().toString().substring(3, 5) + "-" + DTPDaftar.getSelectedItem().toString().substring(0, 2),
                                TTlp.getText(), TUmurTh.getText() + " Th", CMbPnd.getSelectedItem().toString(), cmbPngJawab.getSelectedItem().toString(), Saudara.getText(), Kdpnj.getText(), TNoPeserta.getText(),
                                Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", Kelurahan.getText().replaceAll("KELURAHAN", "-")),
                                Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", Kecamatan.getText().replaceAll("KECAMATAN", "-")),
                                Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", Kabupaten.getText().replaceAll("KABUPATEN", "-")),
                                PekerjaanPj.getText(), AlamatPj.getText(), KelurahanPj.getText(), KecamatanPj.getText(), KabupatenPj.getText(), "0", kdsuku.getText(), kdbahasa.getText(), AlamatDomisili.getText().replaceAll("ALAMAT", ""),
                                Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", KelurahanDom.getText().replaceAll("KELURAHAN", "-")),
                                Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", KecamatanDom.getText().replaceAll("KECAMATAN", "-")),
                                Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", KabupatenDom.getText().replaceAll("KABUPATEN", "-")), umurPjawab.getText(), notlpPJ.getText()
                            }) == true) {
                                if (akses.getform().equals("DlgReg")) {
                                    TCari.setText(TNo.getText());
                                }
                                tampil();
                                if (ChkRM.isSelected() == true) {
                                    Sequel.queryu2("delete from set_no_rkm_medis");
                                    Sequel.queryu2("insert into set_no_rkm_medis values(?)", 1, new String[]{TNo.getText()});
                                }
                                emptTeks();
                            } else {
                                autoNomor();
                                if (Sequel.menyimpantf("pasien", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rekam Medis Pasien", 37, new String[]{
                                    TNo.getText(), TNm.getText(), TKtp.getText(), CmbJk.getSelectedItem().toString().substring(0, 1), TTmp.getText(),
                                    Valid.SetTgl(DTPLahir.getSelectedItem() + ""), NmIbu.getText(),
                                    Alamat.getText().replaceAll("ALAMAT", ""), CMbGd.getSelectedItem().toString(), Pekerjaan.getText(), CmbStts.getSelectedItem().toString(), cmbAgama.getSelectedItem().toString(),
                                    DTPDaftar.getSelectedItem().toString().substring(6, 10) + "-" + DTPDaftar.getSelectedItem().toString().substring(3, 5) + "-" + DTPDaftar.getSelectedItem().toString().substring(0, 2),
                                    TTlp.getText(), TUmurTh.getText() + " Th", CMbPnd.getSelectedItem().toString(), cmbPngJawab.getSelectedItem().toString(), Saudara.getText(), Kdpnj.getText(), TNoPeserta.getText(),
                                    Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", Kelurahan.getText().replaceAll("KELURAHAN", "-")),
                                    Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", Kecamatan.getText().replaceAll("KECAMATAN", "-")),
                                    Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", Kabupaten.getText().replaceAll("KABUPATEN", "-")),
                                    PekerjaanPj.getText(), AlamatPj.getText(), KelurahanPj.getText(), KecamatanPj.getText(), KabupatenPj.getText(), "0", kdsuku.getText(), kdbahasa.getText(), AlamatDomisili.getText().replaceAll("ALAMAT", ""),
                                    Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", KelurahanDom.getText().replaceAll("KELURAHAN", "-")),
                                    Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", KecamatanDom.getText().replaceAll("KECAMATAN", "-")),
                                    Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", KabupatenDom.getText().replaceAll("KABUPATEN", "-")), umurPjawab.getText(), notlpPJ.getText()
                                }) == true) {
                                    if (akses.getform().equals("DlgReg")) {
                                        TCari.setText(TNo.getText());
                                    }
                                    tampil();
                                    if (ChkRM.isSelected() == true) {
                                        Sequel.queryu2("delete from set_no_rkm_medis");
                                        Sequel.queryu2("insert into set_no_rkm_medis values(?)", 1, new String[]{TNo.getText()});
                                    }
                                    emptTeks();
                                } else {
                                    TNm.requestFocus();
                                    autoNomor();
                                }
                            }
                        }
                    }
                }
                Sequel.menyimpan("history_user", "Now(),'-','" + akses.getkode() + "','" + keterangan + "','Simpan'");
                Sequel.AutoComitTrue();

            }
        }
    }
}//GEN-LAST:event_BtnSimpanActionPerformed

private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnSimpanActionPerformed(null);
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        if (KabupatenPj.getText().equals("KABUPATEN")) {
            KabupatenPj.setText("");
        }
        KabupatenPj.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        BtnBatal.requestFocus();
    }

}//GEN-LAST:event_BtnSimpanKeyPressed

private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
    ChkInput.setSelected(true);
    isForm();
    emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        emptTeks();
    } else {
        Valid.pindah(evt, BtnSimpan, BtnHapus);
    }
}//GEN-LAST:event_BtnBatalKeyPressed

private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
    for (z = 0; z < tbPasien.getRowCount(); z++) {
        if (tbPasien.getValueAt(z, 0).toString().equals("true")) {
            Sequel.meghapus("pasien", "no_rkm_medis", tbPasien.getValueAt(z, 1).toString());
        }
    }
    Sequel.menyimpan("history_user", "Now(),'-','" + akses.getkode() + "','Input Pasien Baru','Hapus'");
    tampil();
    emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed

private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnHapusActionPerformed(null);
    } else {
        Valid.pindah(evt, BtnBatal, BtnEdit);
    }
}//GEN-LAST:event_BtnHapusKeyPressed

private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
    if (TNo.getText().trim().equals("")) {
        Valid.textKosong(TNo, "No.Rekam Medis");
    } else if (TNm.getText().trim().equals("")) {
        Valid.textKosong(TNm, "nama pasien");
    } else if (CmbJk.getSelectedItem().toString().equals(" ")) {
        Valid.textKosong(CmbJk, "jenis kelamin pasien");
    } else if (nmpnj.getText().trim().equals("") || Kdpnj.getText().trim().equals("")) {
        Valid.textKosong(Kdpnj, "Asuransi/Penanggung Jawab");
    } else if (kdsuku.getText().trim().equals("") || nmsukubangsa.getText().trim().equals("-")) {
        Valid.textKosong(kdsuku, "Suku/bangsa Pasien");
    } else if (kdbahasa.getText().trim().equals("") || nmbahasa.getText().trim().equals("-")) {
        Valid.textKosong(kdbahasa, "Bahasa Pasien");
    } else if (no_ktp.equals("Yes") && (TKtp.getText().trim().length() < p_no_ktp)) {
        Valid.textKosong(TKtp, "No.KTP/SIM minimal " + p_no_ktp + " karakter dan ");
    } else if (tmp_lahir.equals("Yes") && (TTmp.getText().trim().length() < p_tmp_lahir)) {
        Valid.textKosong(TTmp, "Tempat Lahir minimal " + p_tmp_lahir + " karakter dan ");
    } else if (nm_ibu.equals("Yes") && (NmIbu.getText().trim().length() < p_nm_ibu)) {
        Valid.textKosong(NmIbu, "Nama Ibu minimal " + p_nm_ibu + " karakter dan ");
    } else if (alamat.equals("Yes") && (Alamat.getText().trim().length() < p_alamat)) {
        Valid.textKosong(Alamat, "Alamat Pasien minimal " + p_alamat + " karakter dan ");
    } else if (alamatDom.equals("Yes") && (AlamatDomisili.getText().trim().length() < p_alamatDom)) {
        Valid.textKosong(AlamatDomisili, "Alamat Domisili Pasien minimal " + p_alamatDom + " karakter dan ");
    } else if (pekerjaan.equals("Yes") && (Pekerjaan.getText().trim().length() < p_pekerjaan)) {
        Valid.textKosong(Pekerjaan, "Pekerjaan Pasien minimal " + p_pekerjaan + " karakter dan ");
    } else if (no_tlp.equals("Yes") && (TTlp.getText().trim().length() < p_no_tlp)) {
        Valid.textKosong(TTlp, "Telp Pasien minimal " + p_no_tlp + " karakter dan ");
    } else if (umur.equals("Yes") && (TUmurTh.getText().trim().length() < p_umur)) {
        Valid.textKosong(TUmurTh, "Umur Pasien minimal " + p_umur + " karakter dan ");
    } else if (namakeluarga.equals("Yes") && (Saudara.getText().trim().length() < p_namakeluarga)) {
        Valid.textKosong(Saudara, "Penanggung Jawab Pasien minimal " + p_namakeluarga + " karakter dan ");
    } else if (no_peserta.equals("Yes") && (TNoPeserta.getText().trim().length() < p_no_peserta)) {
        Valid.textKosong(TNoPeserta, "No.Peserta Pasien minimal " + p_no_peserta + " karakter dan ");
    } else if (kelurahan.equals("Yes") && (Kelurahan.getText().trim().length() < p_kelurahan)) {
        Valid.textKosong(Kelurahan, "Kelurahan minimal " + p_kelurahan + " karakter dan ");
    } else if (kecamatan.equals("Yes") && (Kecamatan.getText().trim().length() < p_kecamatan)) {
        Valid.textKosong(Kecamatan, "Kecamatan minimal " + p_kecamatan + " karakter dan ");
    } else if (kabupaten.equals("Yes") && (Kabupaten.getText().trim().length() < p_kabupaten)) {
        Valid.textKosong(Kabupaten, "Kabupaten minimal " + p_kabupaten + " karakter dan ");
    } else if (kelurahanDom.equals("Yes") && (KelurahanDom.getText().trim().length() < p_kelurahanDom)) {
        Valid.textKosong(KelurahanDom, "Kelurahan Domisili minimal " + p_kelurahanDom + " karakter dan ");
    } else if (kecamatanDom.equals("Yes") && (KecamatanDom.getText().trim().length() < p_kecamatanDom)) {
        Valid.textKosong(KecamatanDom, "Kecamatan Domisili minimal " + p_kecamatanDom + " karakter dan ");
    } else if (kabupatenDom.equals("Yes") && (KabupatenDom.getText().trim().length() < p_kabupatenDom)) {
        Valid.textKosong(KabupatenDom, "Kabupaten Domisili minimal " + p_kabupatenDom + " karakter dan ");
    } else if (pekerjaanpj.equals("Yes") && (PekerjaanPj.getText().trim().length() < p_pekerjaanpj)) {
        Valid.textKosong(PekerjaanPj, "Pekerjaan P.J. minimal " + p_pekerjaanpj + " karakter dan ");
    } else if (alamatpj.equals("Yes") && (AlamatPj.getText().trim().length() < p_alamatpj)) {
        Valid.textKosong(AlamatPj, "Alamat P.J. minimal " + p_alamatpj + " karakter dan ");
    } else if (kelurahanpj.equals("Yes") && (KelurahanPj.getText().trim().length() < p_kelurahanpj)) {
        Valid.textKosong(KelurahanPj, "Kelurahan P.J. minimal " + p_kelurahanpj + " karakter dan ");
    } else if (kecamatanpj.equals("Yes") && (KecamatanPj.getText().trim().length() < p_kecamatanpj)) {
        Valid.textKosong(KecamatanPj, "Kecamatan P.J. minimal " + p_kecamatanpj + " karakter dan ");
    } else if (kabupatenpj.equals("Yes") && (KabupatenPj.getText().trim().length() < p_kabupatenpj)) {
        Valid.textKosong(KabupatenPj, "Kabupaten P.J. minimal " + p_kabupatenpj + " karakter dan ");
    } else if ((CMbPnd.getSelectedItem().toString().equals("-")) && (umurBy >= 6)) {
        JOptionPane.showMessageDialog(null, "Pendidikan Harus di Isi");
    } else if ((!CMbPnd.getSelectedItem().toString().equals("-")) && (umurBy < 6)) {
        JOptionPane.showMessageDialog(null, "Pendidikan Tidak Sesuai");
    } else if (cmbPngJawab.getSelectedIndex() == 4 && umurPjawab.getText().equals("")) {
        Valid.textKosong(umurPjawab, "umur suami");
        umurPjawab.requestFocus();
    } else {

        lahir = DTPLahir.getDate();
        birthday = lahir.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        p = Period.between(birthday, today);
        p2 = ChronoUnit.DAYS.between(birthday, today);

        if (p2 < 0) {
            JOptionPane.showMessageDialog(null, "Tanggal lahir pasien SALAH, cek tanggal lahirnya lagi...!!!");
            DTPLahir.requestFocus();
        } else {
            Sequel.AutoComitFalse();
            Valid.editTable(tabMode, "pasien", "no_rkm_medis", "?", "no_rkm_medis=?,nm_pasien=?,no_ktp=?,jk=?,tmp_lahir=?,"
                    + "tgl_lahir=?,alamat=?,gol_darah=?,pekerjaan=?,stts_nikah=?,agama=?,tgl_daftar=?,no_tlp=?,umur=?"
                    + ",pnd=?,keluarga=?,namakeluarga=?,kd_pj=?,no_peserta=?,kd_kel=?,kd_kec=?,kd_kab=?,nm_ibu=?,pekerjaanpj=?,"
                    + "alamatpj=?,kelurahanpj=?,kecamatanpj=?,kabupatenpj=?,tinggi_badan=?,suku_bangsa=?,bahasa_pasien=?,"
                    + "alamat_domisili_pasien=?,kd_kel_domisili_pasien=?,kd_kec_domisili_pasien=?,kd_kab_domisili_pasien=?,umur_pj=?,no_tlp_pj=?", 38,
                    new String[]{TNo.getText(), TNm.getText(), TKtp.getText(), CmbJk.getSelectedItem().toString().substring(0, 1), TTmp.getText(),
                        Valid.SetTgl(DTPLahir.getSelectedItem() + ""),
                        Alamat.getText(), CMbGd.getSelectedItem().toString(), Pekerjaan.getText(), CmbStts.getSelectedItem().toString(), cmbAgama.getSelectedItem().toString(),
                        DTPDaftar.getSelectedItem().toString().substring(6, 10) + "-" + DTPDaftar.getSelectedItem().toString().substring(3, 5) + "-" + DTPDaftar.getSelectedItem().toString().substring(0, 2),
                        TTlp.getText(), TUmurTh.getText() + " Th " + TUmurBl.getText() + " Bl " + TUmurHr.getText() + " Hr", CMbPnd.getSelectedItem().toString(), cmbPngJawab.getSelectedItem().toString(), Saudara.getText(), Kdpnj.getText(), TNoPeserta.getText(),
                        Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", Kelurahan.getText()),
                        Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", Kecamatan.getText()),
                        Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", Kabupaten.getText()),
                        NmIbu.getText(), PekerjaanPj.getText(), AlamatPj.getText(), KelurahanPj.getText(), KecamatanPj.getText(),
                        KabupatenPj.getText(), "0", kdsuku.getText(), kdbahasa.getText(), AlamatDomisili.getText(), 
                        Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", KelurahanDom.getText()),
                        Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", KecamatanDom.getText()),
                        Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", KabupatenDom.getText()), 
                        umurPjawab.getText(), notlpPJ.getText(), Kd2.getText()});
            Sequel.AutoComitTrue();
            if (tabMode.getRowCount() != 0) {
                tampil();
            }
            emptTeks();
        }
    }
}//GEN-LAST:event_BtnEditActionPerformed

private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnEditActionPerformed(null);
    } else {
        Valid.pindah(evt, BtnHapus, BtnPrint);
    }
}//GEN-LAST:event_BtnEditKeyPressed

private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
        BtnBatal.requestFocus();
    } else if (tabMode.getRowCount() != 0) {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));

        if (!cmbHlm.getSelectedItem().toString().equals("Semua")) {
            Valid.MyReport("rptPasien.jasper", "report", "::[ Data Pasien Umum ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.no_peserta from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "
                    + "where concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%' and pasien.no_rkm_medis like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.no_ktp like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.no_peserta like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.tmp_lahir like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.tgl_lahir like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.nm_ibu like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and penjab.png_jawab like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.alamat like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.gol_darah like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.pekerjaan like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.stts_nikah like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.agama like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.namakeluarga like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.tgl_daftar like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.no_tlp like '%" + TCari.getText().trim() + "%'  order by pasien.no_rkm_medis desc" + " LIMIT " + cmbHlm.getSelectedItem(), param);
        } else {
            Valid.MyReport("rptPasien.jasper", "report", "::[ Data Pasien Umum ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.no_peserta from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "
                    + "where concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%' and pasien.no_rkm_medis like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.no_ktp like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.no_peserta like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.tmp_lahir like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.tgl_lahir like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.nm_ibu like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and penjab.png_jawab like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.alamat like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.gol_darah like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.pekerjaan like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.stts_nikah like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.agama like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.namakeluarga like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.tgl_daftar like '%" + TCari.getText().trim() + "%' "
                    + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.no_tlp like '%" + TCari.getText().trim() + "%'  order by pasien.no_rkm_medis desc ", param);
        }
    }
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnPrintActionPerformed(null);
    } else {
        Valid.pindah(evt, BtnHapus, BtnAll);
    }
}//GEN-LAST:event_BtnPrintKeyPressed

private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
    TCari.setText("");
    Carialamat.setText("");
    tampil();
}//GEN-LAST:event_BtnAllActionPerformed

private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnAllActionPerformed(null);
    } else {
        Valid.pindah(evt, BtnPrint, BtnKeluar);
    }
}//GEN-LAST:event_BtnAllKeyPressed

private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
    DlgDemografi.dispose();
    dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        dispose();
    } else {
        Valid.pindah(evt, BtnPrint, TCari);
    }
}//GEN-LAST:event_BtnKeluarKeyPressed

private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        BtnCariActionPerformed(null);
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        BtnCari.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        BtnKeluar.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        tbPasien.requestFocus();
    }
}//GEN-LAST:event_TCariKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    tampil();
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnCariActionPerformed(null);
    } else {
        Valid.pindah(evt, TCari, BtnAll);
    }
}//GEN-LAST:event_BtnCariKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

private void TTmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTmpKeyPressed
    Valid.pindah(evt, CMbGd, DTPLahir);
}//GEN-LAST:event_TTmpKeyPressed

private void CmbJkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJkKeyPressed
    Valid.pindah(evt, TNm, CMbGd);
}//GEN-LAST:event_CmbJkKeyPressed

private void TNmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNmKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        TCari.setText(TNm.getText());
        tampil();
    } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (TNm.getText().toLowerCase().contains("tn.")) {
            CmbJk.setSelectedItem("LAKI-LAKI");
        } else if (TNm.getText().toLowerCase().contains("sdr.")) {
            CmbJk.setSelectedItem("LAKI-LAKI");
        } else if (TNm.getText().toLowerCase().contains("ny.")) {
            CmbJk.setSelectedItem("PEREMPUAN");
        } else if (TNm.getText().toLowerCase().contains("nn.")) {
            CmbJk.setSelectedItem("PEREMPUAN");
        }
        CmbJk.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        TNo.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
        TCari.requestFocus();
    }
}//GEN-LAST:event_TNmKeyPressed

private void CMbGdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CMbGdKeyPressed
    Valid.pindah(evt, CmbJk, TTmp);
}//GEN-LAST:event_CMbGdKeyPressed

private void cmbAgamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbAgamaKeyPressed
    Valid.pindah(evt, PekerjaanPj, CmbStts);
}//GEN-LAST:event_cmbAgamaKeyPressed

private void CmbSttsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbSttsKeyPressed
    Valid.pindah(evt, cmbAgama, Kdpnj);
}//GEN-LAST:event_CmbSttsKeyPressed

private void PekerjaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PekerjaanKeyPressed
    Valid.pindah(evt, TTlp, TKtp);
}//GEN-LAST:event_PekerjaanKeyPressed

private void AlamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (Alamat.getText().equals("")) {
            Alamat.setText("ALAMAT");
        }
        if (Kelurahan.getText().equals("KELURAHAN")) {
            Kelurahan.setText("");
        }
        Kelurahan.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        if (Alamat.getText().equals("")) {
            Alamat.setText("ALAMAT");
        }
        TKtp.requestFocus();
    }
}//GEN-LAST:event_AlamatKeyPressed

private void TTlpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTlpKeyPressed
    Valid.pindah(evt, TNoPeserta, Pekerjaan);
}//GEN-LAST:event_TTlpKeyPressed

private void TNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        TCari.setText(TNo.getText());
        tampil();
    } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        TNm.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        KabupatenPj.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
        TCari.requestFocus();
    }
}//GEN-LAST:event_TNoKeyPressed

private void TKtpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKtpKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (Alamat.getText().equals("ALAMAT")) {
            Alamat.setText("");
        }
        Alamat.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        Pekerjaan.requestFocus();
    }
}//GEN-LAST:event_TKtpKeyPressed

private void DTPDaftarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPDaftarKeyPressed
    Valid.pindah2(evt, Pekerjaan, BtnSimpan);
}//GEN-LAST:event_DTPDaftarKeyPressed

private void CMbPndKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CMbPndKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        NmIbu.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        TUmurTh.requestFocus();
    }
}//GEN-LAST:event_CMbPndKeyPressed

private void SaudaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SaudaraKeyPressed
    Valid.pindah(evt, NmIbu, PekerjaanPj);
}//GEN-LAST:event_SaudaraKeyPressed

private void MnBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    tampil();
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
        BtnBatal.requestFocus();
    } else if (tabMode.getRowCount() != 0) {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptKartuPasien.jasper", "report", "::[ Kartu Periksa Pasien(Umum) ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                + "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab  from pasien "
                + "inner join kelurahan inner join kecamatan inner join kabupaten "
                + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "
                + "where concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.no_rkm_medis like '%" + TCari.getText().trim() + "%' "
                + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' "
                + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.no_ktp like '%" + TCari.getText().trim() + "%' "
                + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.jk like '%" + TCari.getText().trim() + "%' "
                + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.tmp_lahir like '%" + TCari.getText().trim() + "%' "
                + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.tgl_lahir like '%" + TCari.getText().trim() + "%' "
                + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and penjab.png_jawab like '%" + TCari.getText().trim() + "%' "
                + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.alamat like '%" + TCari.getText().trim() + "%' "
                + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.gol_darah like '%" + TCari.getText().trim() + "%' "
                + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.pekerjaan like '%" + TCari.getText().trim() + "%' "
                + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.stts_nikah like '%" + TCari.getText().trim() + "%' "
                + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.agama like '%" + TCari.getText().trim() + "%' "
                + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.tgl_daftar like '%" + TCari.getText().trim() + "%' "
                + "or  concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) like '%" + Carialamat.getText().trim() + "%'  and pasien.no_tlp like '%" + TCari.getText().trim() + "%'  order by pasien.no_rkm_medis desc", param);
    }
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_MnBarcodeActionPerformed

private void MnKartuStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartuStatusActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
        TNo.requestFocus();
    } else if (TNm.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
        tbPasien.requestFocus();
    } else {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptKartuPasien.jasper", "report", "::[ Kartu Periksa Pasien(Umum) ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                + "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                + "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + TNo.getText() + "' ", param);
        this.setCursor(Cursor.getDefaultCursor());
    }
}//GEN-LAST:event_MnKartuStatusActionPerformed

private void DTPLahirItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPLahirItemStateChanged
    lahir = DTPLahir.getDate();
    birthday = lahir.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    p = Period.between(birthday, today);
    p2 = ChronoUnit.DAYS.between(birthday, today);
    TUmurTh.setText(String.valueOf(p.getYears()));
    TUmurBl.setText(String.valueOf(p.getMonths()));
    TUmurHr.setText(String.valueOf(p.getDays()));
    umurBy = Integer.parseInt(TUmurTh.getText());
}//GEN-LAST:event_DTPLahirItemStateChanged

private void KdpnjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdpnjKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpnj, Kdpnj.getText());
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        BtnPenjabActionPerformed(null);
    } else {
        Valid.pindah(evt, CmbStts, TNoPeserta);
    }
}//GEN-LAST:event_KdpnjKeyPressed

private void BtnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPenjabActionPerformed
    akses.setform("DlgPasien");
    penjab.isCek();
    penjab.onCari();
    penjab.setSize(868, 519);
    penjab.setLocationRelativeTo(internalFrame1);
    penjab.setVisible(true);
    penjab.TCari.requestFocus();
}//GEN-LAST:event_BtnPenjabActionPerformed

private void ppGrafikjkbayiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikjkbayiActionPerformed
    grafikjkel kas = new grafikjkel("Grafik Jenis Kelamin Pasien ", " ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikjkbayiActionPerformed

private void MnLaporanRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRMActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
        TNo.requestFocus();
    } else if (TNm.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
        tbPasien.requestFocus();
    } else {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptRM2.jasper", "report", "::[ Identitas Pasien ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                + "inner join kelurahan inner join kecamatan inner join kabupaten "
                + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNo.getText() + "' ", param);
    }
}//GEN-LAST:event_MnLaporanRMActionPerformed

private void MnLaporanIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanIGDActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
        TNo.requestFocus();
    } else if (TNm.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
        tbPasien.requestFocus();
    } else {
        Valid.MyReport("rptKartuIgd.jasper", "report", "::[ Laporan Rekam Medik ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                + "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                + "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                + "on pasien.kd_kel=kelurahan.kd_kel "
                + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + TNo.getText() + "' ");
    }
}//GEN-LAST:event_MnLaporanIGDActionPerformed

private void ppKelahiranBayiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppKelahiranBayiActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
        TNo.requestFocus();
    } else if (TNm.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        tbPasien.requestFocus();
    } else {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgIKBBayi resume = new DlgIKBBayi(null, false);
        resume.setData(TNo.getText());
        resume.tampil();
        resume.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        resume.setLocationRelativeTo(internalFrame1);
        resume.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }
}//GEN-LAST:event_ppKelahiranBayiActionPerformed

private void MnLembarKeluarMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarKeluarMasukActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
        TNo.requestFocus();
    } else if (TNm.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
        tbPasien.requestFocus();
    } else {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptLembarKeluarMasuk.jasper", "report", "::[ Ringkasan Masuk Keluar ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                + "inner join kelurahan inner join kecamatan inner join kabupaten "
                + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNo.getText() + "' ", param);
        this.setCursor(Cursor.getDefaultCursor());
    }
}//GEN-LAST:event_MnLembarKeluarMasukActionPerformed

private void MnLembarAnamNesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarAnamNesaActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
        TNo.requestFocus();
    } else if (TNm.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
        tbPasien.requestFocus();
    } else {
        Valid.MyReport("rptLembarAnamnesi.jasper", "report", "::[ Lembar Anamnesa ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                + "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                + "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                + "on pasien.kd_kel=kelurahan.kd_kel "
                + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + TNo.getText() + "' ");
    }
}//GEN-LAST:event_MnLembarAnamNesaActionPerformed

private void MnLembarGrafikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarGrafikActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
        TNo.requestFocus();
    } else if (TNm.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
        tbPasien.requestFocus();
    } else {
        Valid.MyReport("rptLembarGrafik.jasper", "report", "::[ Lembar Grafik ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                + "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                + "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                + "on pasien.kd_kel=kelurahan.kd_kel "
                + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + TNo.getText() + "' ");
    }
}//GEN-LAST:event_MnLembarGrafikActionPerformed

private void MnLembarCatatanPerkembanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarCatatanPerkembanganActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
        TNo.requestFocus();
    } else if (TNm.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
        tbPasien.requestFocus();
    } else {
        Valid.MyReport("rptLembarPerkembangan.jasper", "report", "::[ Lembar Catatan Perkembangan ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                + "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                + "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                + "on pasien.kd_kel=kelurahan.kd_kel "
                + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + TNo.getText() + "' ");
    }
}//GEN-LAST:event_MnLembarCatatanPerkembanganActionPerformed

private void MnLembarCatatanKeperawatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarCatatanKeperawatanActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
        TNo.requestFocus();
    } else if (TNm.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
        tbPasien.requestFocus();
    } else {
        Valid.MyReport("rptLembarPerkembangan.jasper", "report", "::[ Lembar Catatan Keperawatan ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                + "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                + "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                + "on pasien.kd_kel=kelurahan.kd_kel "
                + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + TNo.getText() + "' ");
    }
}//GEN-LAST:event_MnLembarCatatanKeperawatanActionPerformed

private void MnLaporanAnestesiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanAnestesiaActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
        TNo.requestFocus();
    } else if (TNm.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
        tbPasien.requestFocus();
    } else {
        Valid.MyReport("rptLaporanAnestesia.jasper", "report", "::[ Lembar Catatan Keperawatan ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                + "pasien.tmp_lahir, pasien.tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                + "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                + "on pasien.kd_kel=kelurahan.kd_kel "
                + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + TNo.getText() + "' ");
    }
}//GEN-LAST:event_MnLaporanAnestesiaActionPerformed

private void CarialamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CarialamatKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        BtnCariActionPerformed(null);
    } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        BtnCariActionPerformed(null);
        TCari.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        BtnCariActionPerformed(null);
        TNo.requestFocus();
    }
}//GEN-LAST:event_CarialamatKeyPressed

private void KelurahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelurahanKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (Kelurahan.getText().equals("")) {
            Kelurahan.setText("KELURAHAN");
        }
        if (Kecamatan.getText().equals("KECAMATAN")) {
            Kecamatan.setText("");
        }
        Kecamatan.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        if (Kelurahan.getText().equals("")) {
            Kelurahan.setText("KELURAHAN");
        }
        if (Alamat.getText().equals("ALAMAT")) {
            Alamat.setText("");
        }
        Alamat.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        BtnKelurahanActionPerformed(null);
    }
}//GEN-LAST:event_KelurahanKeyPressed

private void KecamatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KecamatanKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (Kecamatan.getText().equals("")) {
            Kecamatan.setText("KECAMATAN");
        }
        if (Kabupaten.getText().equals("KABUPATEN")) {
            Kabupaten.setText("");
        }
        Kabupaten.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        if (Kecamatan.getText().equals("")) {
            Kecamatan.setText("KECAMATAN");
        }
        if (Kelurahan.getText().equals("KELURAHAN")) {
            Kelurahan.setText("");
        }
        Kelurahan.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        BtnKecamatanActionPerformed(null);
    }
}//GEN-LAST:event_KecamatanKeyPressed

private void KabupatenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KabupatenKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (Kabupaten.getText().equals("")) {
            Kabupaten.setText("KABUPATEN");
        }
        if (AlamatDomisili.getText().equals("ALAMAT")) {
            AlamatDomisili.setText("");
        }
        AlamatDomisili.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        if (Kabupaten.getText().equals("")) {
            Kabupaten.setText("KABUPATEN");
        }
        if (Kecamatan.getText().equals("KECAMATAN")) {
            Kecamatan.setText("");
        }
        Kecamatan.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        BtnKabupatenActionPerformed(null);
    }
}//GEN-LAST:event_KabupatenKeyPressed

private void BtnKelurahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKelurahanActionPerformed
    akses.setform("DlgPasien");
    pilih = 1;
    kel.setSize(703, 384);
    kel.setLocationRelativeTo(internalFrame1);
    kel.setVisible(true);
    kel.TCari.requestFocus();
    kel.isCek();
}//GEN-LAST:event_BtnKelurahanActionPerformed

private void BtnKecamatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKecamatanActionPerformed
    akses.setform("DlgPasien");
    pilih = 1;
    kec.setSize(703, 384);
    kec.setLocationRelativeTo(internalFrame1);
    kec.setVisible(true);
    kec.TCari.requestFocus();
    kec.isCek();
}//GEN-LAST:event_BtnKecamatanActionPerformed

private void BtnKabupatenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKabupatenActionPerformed
    akses.setform("DlgPasien");
    pilih = 1;
    kab.setSize(703, 384);
    kab.setLocationRelativeTo(internalFrame1);
    kab.setVisible(true);
    kab.TCari.requestFocus();
    kab.isCek();
}//GEN-LAST:event_BtnKabupatenActionPerformed

private void ppGrafikDemografiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikDemografiActionPerformed
    DlgDemografi.setSize(550, 180);
    DlgDemografi.setLocationRelativeTo(internalFrame1);
    DlgDemografi.setVisible(true);
}//GEN-LAST:event_ppGrafikDemografiActionPerformed

private void ppGrafikPerAgamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerAgamaActionPerformed
    grafikpasienperagama kas = new grafikpasienperagama("Grafik Pasien Per Agama ", " ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerAgamaActionPerformed

private void ppGrafikPerPekerjaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerPekerjaanActionPerformed
    grafikpasienperpekerjaaan kas = new grafikpasienperpekerjaaan("Grafik Pasien Per Pekerjaan ", " ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerPekerjaanActionPerformed

private void BtnPrint2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint2ActionPerformed
    if (!Kelurahan2.getText().equals("")) {
        DlgDemografi.dispose();
        grafiksql kas = new grafiksql("::[ Data Demografi Per Area Kelurahan " + Kelurahan2.getText() + ", Kecamatan " + Kecamatan2.getText() + ", Kabupaten " + Kabupaten2.getText() + " ]::",
                " pasien inner join kabupaten inner join kecamatan inner join kelurahan on pasien.kd_kab=kabupaten.kd_kab and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel "
                + " where kabupaten.nm_kab='" + Kabupaten2.getText() + "' and kecamatan.nm_kec='" + Kecamatan2.getText() + "' and kelurahan.nm_kel='" + Kelurahan2.getText() + "'",
                "pasien.alamat", "Area");
        kas.setSize(this.getWidth(), this.getHeight());
        kas.setLocationRelativeTo(this);
        kas.setVisible(true);
    } else if (!Kecamatan2.getText().equals("")) {
        DlgDemografi.dispose();
        grafiksql kas = new grafiksql("::[ Data Demografi Per Kelurahan Kecamatan " + Kecamatan2.getText() + " Kabupaten " + Kabupaten2.getText() + " ]::",
                " pasien inner join kabupaten inner join kecamatan inner join kelurahan on pasien.kd_kab=kabupaten.kd_kab "
                + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel "
                + "where kabupaten.nm_kab='" + Kabupaten2.getText() + "' and kecamatan.nm_kec='" + Kecamatan2.getText() + "'",
                "kelurahan.nm_kel", "Kelurahan");
        kas.setSize(this.getWidth(), this.getHeight());
        kas.setLocationRelativeTo(this);
        kas.setVisible(true);
    } else if (!Kabupaten2.getText().equals("")) {
        DlgDemografi.dispose();
        grafiksql kas = new grafiksql("::[ Data Per Kecamatan Kabupaten " + Kabupaten2.getText() + " ]::",
                " pasien inner join kabupaten inner join kecamatan on pasien.kd_kab=kabupaten.kd_kab "
                + "and pasien.kd_kec=kecamatan.kd_kec where kabupaten.nm_kab='" + Kabupaten2.getText() + "'",
                "kecamatan.nm_kec", "Kecamatan");
        kas.setSize(this.getWidth(), this.getHeight());
        kas.setLocationRelativeTo(this);
        kas.setVisible(true);
    } else if (Kabupaten2.getText().equals("")) {
        DlgDemografi.dispose();
        grafiksql kas = new grafiksql("::[ Data Demografi Per Kabupaten ]::",
                " pasien inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab",
                "kabupaten.nm_kab", "Kabupaten");
        kas.setSize(this.getWidth(), this.getHeight());
        kas.setLocationRelativeTo(this);
        kas.setVisible(true);
    }
}//GEN-LAST:event_BtnPrint2ActionPerformed

private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
    DlgDemografi.dispose();
}//GEN-LAST:event_BtnKeluar2ActionPerformed

private void BtnSeek8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek8ActionPerformed
    if (Kecamatan2.getText().equals("")) {
        Valid.textKosong(Kecamatan2, "Kecamatan");
    } else {
        akses.setform("DlgPasien");
        pilih = 2;
        kel.emptTeks();
        kel.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        kel.setLocationRelativeTo(internalFrame1);
        kel.setVisible(true);
    }
}//GEN-LAST:event_BtnSeek8ActionPerformed

private void BtnSeek9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek9ActionPerformed
    if (Kabupaten2.getText().equals("")) {
        Valid.textKosong(Kabupaten2, "Kabupaten");
    } else {
        akses.setform("DlgPasien");
        pilih = 2;
        kec.emptTeks();
        kec.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        kec.setLocationRelativeTo(internalFrame1);
        kec.setVisible(true);
    }
}//GEN-LAST:event_BtnSeek9ActionPerformed

private void BtnSeek10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek10ActionPerformed
    akses.setform("DlgPasien");
    pilih = 2;
    kab.emptTeks();
    kab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    kab.setLocationRelativeTo(internalFrame1);
    kab.setVisible(true);
}//GEN-LAST:event_BtnSeek10ActionPerformed

private void BtnPrint3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint3ActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
        BtnBatal.requestFocus();
    } else if (tabMode.getRowCount() != 0) {
        if (!Kelurahan2.getText().equals("")) {
            DlgDemografi.dispose();
            Map<String, Object> data = new HashMap<>();
            data.put("judul", "Data Demografi Per Area Kelurahan " + Kelurahan2.getText() + ", Kecamatan " + Kecamatan2.getText() + ", Kabupaten " + Kabupaten2.getText());
            data.put("area", "Area");
            data.put("namars", akses.getnamars());
            data.put("alamatrs", akses.getalamatrs());
            data.put("kotars", akses.getkabupatenrs());
            data.put("propinsirs", akses.getpropinsirs());
            data.put("kontakrs", akses.getkontakrs());
            data.put("emailrs", akses.getemailrs());
            data.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDemografi.jasper", "report", "::[ Data Demografi Per Area Kelurahan " + Kelurahan2.getText() + ", Kecamatan " + Kecamatan2.getText() + ", Kabupaten " + Kabupaten2.getText() + " ]::",
                    "select  pasien.alamat as area,count(pasien.alamat) as jumlah from pasien "
                    + "inner join kabupaten inner join kecamatan inner join kelurahan on pasien.kd_kab=kabupaten.kd_kab "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel "
                    + "where kabupaten.nm_kab='" + Kabupaten2.getText() + "' and kecamatan.nm_kec='" + Kecamatan2.getText() + "' "
                    + "and kelurahan.nm_kel='" + Kelurahan2.getText() + "' group by pasien.alamat order by pasien.alamat", data);
        } else if (!Kecamatan2.getText().equals("")) {
            DlgDemografi.dispose();
            Map<String, Object> data = new HashMap<>();
            data.put("judul", "Data Demografi Per Kelurahan Kecamatan " + Kecamatan2.getText() + ", Kabupaten " + Kabupaten2.getText());
            data.put("area", "Kelurahan");
            data.put("namars", akses.getnamars());
            data.put("alamatrs", akses.getalamatrs());
            data.put("kotars", akses.getkabupatenrs());
            data.put("propinsirs", akses.getpropinsirs());
            data.put("kontakrs", akses.getkontakrs());
            data.put("emailrs", akses.getemailrs());
            data.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDemografi.jasper", "report", "::[ Data Demografi Per Kelurahan Kecamatan " + Kecamatan2.getText() + " Kabupaten " + Kabupaten2.getText() + " ]::",
                    "select kelurahan.nm_kel as area,count(kelurahan.nm_kel) as jumlah from pasien "
                    + "inner join kabupaten inner join kecamatan inner join kelurahan on pasien.kd_kab=kabupaten.kd_kab "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel "
                    + "where kabupaten.nm_kab='" + Kabupaten2.getText() + "' and kecamatan.nm_kec='" + Kecamatan2.getText() + "' group by kelurahan.nm_kel order by kelurahan.nm_kel", data);
        } else if (!Kabupaten2.getText().equals("")) {
            DlgDemografi.dispose();
            Map<String, Object> data = new HashMap<>();
            data.put("judul", "Data Demografi Per Kecamatan Kabupaten " + Kabupaten2.getText());
            data.put("area", "Kecamatan");
            data.put("namars", akses.getnamars());
            data.put("alamatrs", akses.getalamatrs());
            data.put("kotars", akses.getkabupatenrs());
            data.put("propinsirs", akses.getpropinsirs());
            data.put("kontakrs", akses.getkontakrs());
            data.put("emailrs", akses.getemailrs());
            data.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDemografi.jasper", "report", "::[ Data Per Kecamatan Kabupaten " + Kabupaten2.getText() + " ]::",
                    "select kecamatan.nm_kec as area,count(kecamatan.nm_kec) as jumlah from pasien "
                    + "inner join kabupaten inner join kecamatan on pasien.kd_kab=kabupaten.kd_kab "
                    + "and pasien.kd_kec=kecamatan.kd_kec where kabupaten.nm_kab='" + Kabupaten2.getText() + "' group by kecamatan.nm_kec order by kecamatan.nm_kec", data);
        } else if (Kabupaten2.getText().equals("")) {
            DlgDemografi.dispose();
            Map<String, Object> data = new HashMap<>();
            data.put("judul", "Data Demografi Per Kabupaten");
            data.put("area", "Kabupaten");
            data.put("namars", akses.getnamars());
            data.put("alamatrs", akses.getalamatrs());
            data.put("kotars", akses.getkabupatenrs());
            data.put("propinsirs", akses.getpropinsirs());
            data.put("kontakrs", akses.getkontakrs());
            data.put("emailrs", akses.getemailrs());
            data.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDemografi.jasper", "report", "::[ Data Demografi Per Kabupaten ]::", "select kabupaten.nm_kab as area,count(kabupaten.nm_kab) as jumlah from pasien "
                    + "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab group by kabupaten.nm_kab order by kabupaten.nm_kab", data);
        }
    }
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrint3ActionPerformed

private void ppRegistrasiBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRegistrasiBtnPrintActionPerformed
    prosesCari2();
}//GEN-LAST:event_ppRegistrasiBtnPrintActionPerformed

private void AlamatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlamatMouseExited
    if (Alamat.getText().equals("")) {
        Alamat.setText("ALAMAT");
    }
}//GEN-LAST:event_AlamatMouseExited

private void KelurahanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KelurahanMouseExited
    if (Kelurahan.getText().equals("")) {
        Kelurahan.setText("KELURAHAN");
    }
}//GEN-LAST:event_KelurahanMouseExited

private void KecamatanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KecamatanMouseExited
    if (Kecamatan.getText().equals("")) {
        Kecamatan.setText("KECAMATAN");
    }
}//GEN-LAST:event_KecamatanMouseExited

private void KabupatenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KabupatenMouseExited
    if (Kabupaten.getText().equals("")) {
        Kabupaten.setText("KABUPATEN");
    }
}//GEN-LAST:event_KabupatenMouseExited

private void AlamatMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlamatMouseMoved
    if (Alamat.getText().equals("ALAMAT")) {
        Alamat.setText("");
    }
}//GEN-LAST:event_AlamatMouseMoved

private void KelurahanMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KelurahanMouseMoved
    if (Kelurahan.getText().equals("KELURAHAN")) {
        Kelurahan.setText("");
    }
}//GEN-LAST:event_KelurahanMouseMoved

private void KecamatanMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KecamatanMouseMoved
    if (Kecamatan.getText().equals("KECAMATAN")) {
        Kecamatan.setText("");
    }
}//GEN-LAST:event_KecamatanMouseMoved

private void KabupatenMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KabupatenMouseMoved
    if (Kabupaten.getText().equals("KABUPATEN")) {
        Kabupaten.setText("");
    }
}//GEN-LAST:event_KabupatenMouseMoved

    private void ChkDaftarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkDaftarItemStateChanged
        if (ChkDaftar.isSelected() == true) {
            DTPDaftar.setEditable(true);
        } else if (ChkDaftar.isSelected() == false) {
            DTPDaftar.setEditable(false);
        }
        DTPDaftar.requestFocus();
    }//GEN-LAST:event_ChkDaftarItemStateChanged

    private void MnIdentitas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnIdentitas1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRM1.jasper", "report", "::[ Identitas Pasien (Lembar RM 1)]::",
                    "SELECT p.no_rkm_medis, p.nm_pasien, p.no_ktp, IF(p.jk='L','Laki-laki','Perempuan') jk, p.tmp_lahir, p.tgl_lahir,p.nm_ibu, "
                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, p.gol_darah, p.pekerjaan, "
                    + "p.stts_nikah,p.agama,p.tgl_daftar,ifnull(p.no_tlp_pj,p.no_tlp) no_tlp, p.umur, p.pnd, p.keluarga, p.namakeluarga,pj.png_jawab,p.pekerjaanpj, "
                    + "CONCAT(IF(p.alamatpj='alamat','-',p.alamatpj),', ',IF(p.kelurahanpj='kelurahan','-',p.kelurahanpj),', ',IF(p.kecamatanpj='kecamatan','-', "
                    + "p.kecamatanpj),', ',IF(p.kabupatenpj='kabupaten','-',p.kabupatenpj)) alamatpj FROM pasien p INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel "
                    + "INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab "
                    + "INNER JOIN penjab pj ON pj.kd_pj=p.kd_pj WHERE p.no_rkm_medis='" + TNo.getText() + "'", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnIdentitas1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void NmIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmIbuKeyPressed
        Valid.pindah(evt, CMbPnd, cmbPngJawab);
    }//GEN-LAST:event_NmIbuKeyPressed

    private void NoRmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRmKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRmKeyPressed

    private void MnPengantarHemodalisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPengantarHemodalisaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        } else {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            String nip = Sequel.cariIsi("select kd_dokterhemodialisa from set_pjlab");
            param.put("dokter", Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + nip + "'"));
            param.put("nipdokter", nip);
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRM3.jasper", "report", "::[ Identitas Pasien ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNo.getText() + "' ", param);
        }
    }//GEN-LAST:event_MnPengantarHemodalisaActionPerformed

    private void PekerjaanPjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PekerjaanPjKeyPressed
        Valid.pindah(evt, Saudara, cmbAgama);
    }//GEN-LAST:event_PekerjaanPjKeyPressed

    private void AlamatPjMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlamatPjMouseMoved
        if (AlamatPj.getText().equals("ALAMAT")) {
            AlamatPj.setText("");
        }
    }//GEN-LAST:event_AlamatPjMouseMoved

    private void AlamatPjMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlamatPjMouseExited
        if (AlamatPj.getText().equals("")) {
            AlamatPj.setText("ALAMAT");
        }
    }//GEN-LAST:event_AlamatPjMouseExited

    private void AlamatPjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatPjKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (AlamatPj.getText().equals("")) {
                AlamatPj.setText("ALAMAT");
            }
            if (KelurahanPj.getText().equals("KELURAHAN")) {
                KelurahanPj.setText("");
            }
            KelurahanPj.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (AlamatPj.getText().equals("")) {
                AlamatPj.setText("ALAMAT");
            }
            if (Kabupaten.getText().equals("KABUPATEN")) {
                Kabupaten.setText("");
            }
            Kabupaten.requestFocus();
        }
    }//GEN-LAST:event_AlamatPjKeyPressed

    private void KecamatanPjMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KecamatanPjMouseMoved
        if (KecamatanPj.getText().equals("KECAMATAN")) {
            KecamatanPj.setText("");
        }
    }//GEN-LAST:event_KecamatanPjMouseMoved

    private void KecamatanPjMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KecamatanPjMouseExited
        if (KecamatanPj.getText().equals("")) {
            KecamatanPj.setText("KECAMATAN");
        }
    }//GEN-LAST:event_KecamatanPjMouseExited

    private void KecamatanPjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KecamatanPjKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (KecamatanPj.getText().equals("")) {
                KecamatanPj.setText("KECAMATAN");
            }
            if (KabupatenPj.getText().equals("KABUPATEN")) {
                KabupatenPj.setText("");
            }
            KabupatenPj.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (KecamatanPj.getText().equals("")) {
                KecamatanPj.setText("KECAMATAN");
            }
            if (KelurahanPj.getText().equals("KELURAHAN")) {
                KelurahanPj.setText("");
            }
            KelurahanPj.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnKecamatanPjActionPerformed(null);
        }
    }//GEN-LAST:event_KecamatanPjKeyPressed

    private void BtnKecamatanPjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKecamatanPjActionPerformed
        akses.setform("DlgPasien");
        pilih = 3;
        kec.setSize(703, 384);
        kec.setLocationRelativeTo(internalFrame1);
        kec.setVisible(true);
        kec.TCari.requestFocus();
        kec.isCek();
    }//GEN-LAST:event_BtnKecamatanPjActionPerformed

    private void KabupatenPjMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KabupatenPjMouseMoved
        if (KabupatenPj.getText().equals("KABUPATEN")) {
            KabupatenPj.setText("");
        }
    }//GEN-LAST:event_KabupatenPjMouseMoved

    private void KabupatenPjMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KabupatenPjMouseExited
        if (KabupatenPj.getText().equals("")) {
            KabupatenPj.setText("KABUPATEN");
        }
    }//GEN-LAST:event_KabupatenPjMouseExited

    private void KabupatenPjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KabupatenPjKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (KabupatenPj.getText().equals("")) {
                KabupatenPj.setText("KABUPATEN");
            }
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (KabupatenPj.getText().equals("")) {
                KabupatenPj.setText("KABUPATEN");
            }
            if (KecamatanPj.getText().equals("KECAMATAN")) {
                KecamatanPj.setText("");
            }
            KecamatanPj.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnKabupatenPjActionPerformed(null);
        }
    }//GEN-LAST:event_KabupatenPjKeyPressed

    private void BtnKabupatenPjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKabupatenPjActionPerformed
        akses.setform("DlgPasien");
        pilih = 3;
        kab.setSize(703, 384);
        kab.setLocationRelativeTo(internalFrame1);
        kab.setVisible(true);
        kab.TCari.requestFocus();
        kab.isCek();
    }//GEN-LAST:event_BtnKabupatenPjActionPerformed

    private void BtnKelurahanPjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKelurahanPjActionPerformed
        akses.setform("DlgPasien");
        pilih = 3;
        kel.setSize(703, 384);
        kel.setLocationRelativeTo(internalFrame1);
        kel.setVisible(true);
        kel.TCari.requestFocus();
        kel.isCek();
    }//GEN-LAST:event_BtnKelurahanPjActionPerformed

    private void KelurahanPjMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KelurahanPjMouseMoved
        if (KelurahanPj.getText().equals("KELURAHAN")) {
            KelurahanPj.setText("");
        }
    }//GEN-LAST:event_KelurahanPjMouseMoved

    private void KelurahanPjMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KelurahanPjMouseExited
        if (KelurahanPj.getText().equals("")) {
            KelurahanPj.setText("KELURAHAN");
        }
    }//GEN-LAST:event_KelurahanPjMouseExited

    private void KelurahanPjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelurahanPjKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (KelurahanPj.getText().equals("")) {
                KelurahanPj.setText("KELURAHAN");
            }
            if (KecamatanPj.getText().equals("KECAMATAN")) {
                KecamatanPj.setText("");
            }
            KecamatanPj.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (KelurahanPj.getText().equals("")) {
                KelurahanPj.setText("KELURAHAN");
            }
            if (AlamatPj.getText().equals("ALAMAT")) {
                AlamatPj.setText("");
            }
            AlamatPj.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnKelurahanPjActionPerformed(null);
        }
    }//GEN-LAST:event_KelurahanPjKeyPressed

    private void TNoPesertaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoPesertaKeyPressed
        Valid.pindah(evt, Kdpnj, TTlp);
    }//GEN-LAST:event_TNoPesertaKeyPressed

    private void ChkRMItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkRMItemStateChanged
        if (ChkRM.isSelected() == true) {
            TNo.setEditable(false);
            TNo.setBackground(new Color(245, 250, 240));
        } else if (ChkRM.isSelected() == false) {
            TNo.setEditable(true);
            TNo.setBackground(new Color(250, 255, 245));
        }
    }//GEN-LAST:event_ChkRMItemStateChanged

    private void MnCekKepesertaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekKepesertaanActionPerformed
        if (!TNoPeserta.getText().equals("")) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            BPJSPeserta form = new BPJSPeserta(null, true);
            form.tampil(TNoPeserta.getText());
            form.setSize(640, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, Nomor kepesertaan kosong...!!!");
        }

    }//GEN-LAST:event_MnCekKepesertaanActionPerformed

    private void MnCekNIKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekNIKActionPerformed
        if (!TKtp.getText().equals("")) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            BPJSNik form = new BPJSNik(null, true);
            form.tampil(TKtp.getText());
            form.setSize(640, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, NIK KTP kosong...!!!");
        }
    }//GEN-LAST:event_MnCekNIKActionPerformed

    private void MnViaBPJSNikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnViaBPJSNikActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        cekViaBPJS.tampil(TKtp.getText());
        TNm.setText(Strings.toUpperCase(cekViaBPJS.nama));
        Valid.SetTgl(DTPLahir, cekViaBPJS.tglLahir);
        TNoPeserta.setText(cekViaBPJS.noKartu);
        TKtp.setText(cekViaBPJS.nik);
        Pekerjaan.setText(cekViaBPJS.jenisPesertaketerangan);
        Valid.SetTgl(DTPLahir, cekViaBPJS.tglLahir);
        DTPLahirItemStateChanged(null);

        if (cekViaBPJS.sex.equals("L")) {
            CmbJk.setSelectedIndex(1);
        } else {
            CmbJk.setSelectedIndex(2);
        }        
        jPopupMenu2.setVisible(false);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnViaBPJSNikActionPerformed

    private void MnViaBPJSNoKartuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnViaBPJSNoKartuActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        cekViaBPJSKartu.tampil(TNoPeserta.getText(), Sequel.cariIsi("SELECT DATE(NOW())"));
        TNm.setText(Strings.toUpperCase(cekViaBPJSKartu.nama));
        Valid.SetTgl(DTPLahir, cekViaBPJSKartu.tglLahir);
        TNoPeserta.setText(cekViaBPJSKartu.noKartu);
        TKtp.setText(cekViaBPJSKartu.nik);
        Pekerjaan.setText(cekViaBPJSKartu.jenisPesertaketerangan);
        Valid.SetTgl(DTPLahir, cekViaBPJSKartu.tglLahir);
        DTPLahirItemStateChanged(null);        
        
        if (cekViaBPJSKartu.sex.equals("L")) {
            CmbJk.setSelectedIndex(1);
        } else {
            CmbJk.setSelectedIndex(2);
        }
        jPopupMenu2.setVisible(false);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnViaBPJSNoKartuActionPerformed

    private void BtnKelurahan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKelurahan1ActionPerformed
        if (TKtp.getText().trim().equals("") && TNoPeserta.getText().trim().equals("")) {
            TKtp.requestFocus();
            JOptionPane.showMessageDialog(null, "Silahkan isi terlebih dahulu No.Peserta/NIK/No.KTP..!!");
        } else {
            jPopupMenu2.setLocation(712, 222);
            jPopupMenu2.setVisible(true);
        }
    }//GEN-LAST:event_BtnKelurahan1ActionPerformed

    private void MnLaporanRM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRM1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRM4.jasper", "report", "::[ Identitas Pasien ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNo.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLaporanRM1ActionPerformed

    private void ppRiwayatBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRiwayatBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgResumePerawatan resume = new DlgResumePerawatan(null, true);
            resume.setNoRm(TNo.getText(), TNm.getText());
            resume.tampil();
            resume.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            resume.setLocationRelativeTo(internalFrame1);
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppRiwayatBtnPrintActionPerformed

    private void MnIdentitas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnIdentitas2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRM5.jasper", "report", "::[ Identitas Pasien (Lembar RM 1) ]::",
                    "SELECT p.no_rkm_medis, p.nm_pasien, p.no_ktp, IF(p.jk='L','Laki-laki','Perempuan') jk, p.tmp_lahir, p.tgl_lahir,p.nm_ibu, "
                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, p.gol_darah, p.pekerjaan, p.no_peserta, "
                    + "p.stts_nikah,p.agama,p.tgl_daftar,ifnull(p.no_tlp_pj,p.no_tlp) no_tlp, p.umur, p.pnd, p.keluarga, p.namakeluarga,pj.png_jawab,p.pekerjaanpj, "
                    + "CONCAT(IF(p.alamatpj='alamat','-',p.alamatpj),', ',IF(p.kelurahanpj='kelurahan','-',p.kelurahanpj),', ',IF(p.kecamatanpj='kecamatan','-', "
                    + "p.kecamatanpj),', ',IF(p.kabupatenpj='kabupaten','-',p.kabupatenpj)) alamatpj FROM pasien p INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel "
                    + "INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab "
                    + "INNER JOIN penjab pj ON pj.kd_pj=p.kd_pj WHERE p.no_rkm_medis='" + TNo.getText() + "'", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnIdentitas2ActionPerformed

    private void MnLaporanRM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRM2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRM6.jasper", "report", "::[ Lembar Rawat Jalan ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNo.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLaporanRM2ActionPerformed

    private void MnFormulirPendaftaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFormulirPendaftaranActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRM7.jasper", "report", "::[ Formulir Pendaftaran ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNo.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnFormulirPendaftaranActionPerformed

    private void MnSCreeningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSCreeningActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRM8.jasper", "report", "::[ Screening Awal ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNo.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnSCreeningActionPerformed

    private void MnCopyResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopyResepActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRM9.jasper", "report", "::[ Copy Resep ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNo.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCopyResepActionPerformed

    private void MnLembarKeluarMasuk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarKeluarMasuk2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLembarKeluarMasuk2.jasper", "report", "::[ Ringkasan Masuk Keluar ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNo.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLembarKeluarMasuk2ActionPerformed

    private void MnIdentitas3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnIdentitas3ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRM10.jasper", "report", "::[ Identitas Pasien (Lembar RM 1) ]::",
                    "SELECT p.no_rkm_medis, p.nm_pasien, p.no_ktp, IF(p.jk='L','Laki-laki','Perempuan') jk, p.tmp_lahir, p.tgl_lahir,p.nm_ibu, "
                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, p.gol_darah, p.pekerjaan, p.no_peserta, "
                    + "p.stts_nikah,p.agama,p.tgl_daftar,ifnull(p.no_tlp_pj,p.no_tlp) no_tlp, p.umur, p.pnd, p.keluarga, p.namakeluarga,pj.png_jawab,p.pekerjaanpj, "
                    + "CONCAT(IF(p.alamatpj='alamat','-',p.alamatpj),', ',IF(p.kelurahanpj='kelurahan','-',p.kelurahanpj),', ',IF(p.kecamatanpj='kecamatan','-', "
                    + "p.kecamatanpj),', ',IF(p.kabupatenpj='kabupaten','-',p.kabupatenpj)) alamatpj FROM pasien p INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel "
                    + "INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab "
                    + "INNER JOIN penjab pj ON pj.kd_pj=p.kd_pj WHERE p.no_rkm_medis='" + TNo.getText() + "'", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnIdentitas3ActionPerformed

    private void TUmurThKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUmurThKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TUmurThKeyPressed

    private void TUmurBlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUmurBlKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TUmurBlKeyPressed

    private void TUmurHrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUmurHrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //       CmbUmur.requestFocus();
            try {
                Valid.SetTgl(DTPLahir, Sequel.cariIsi("select DATE_SUB('" + Valid.SetTgl(DTPLahir.getSelectedItem() + "") + "', interval " + TUmurTh.getText() + " year)"));
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            DTPLahir.requestFocus();
        }
    }//GEN-LAST:event_TUmurHrKeyPressed

    private void DTPLahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPLahirKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TUmurTh.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TTmp.requestFocus();
        }
    }//GEN-LAST:event_DTPLahirKeyPressed

    private void MnBarcodeRM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM11.jasper", "report", "::[ BARCODE (Label BESAR Rekam Medis) ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d/%m/%Y') as tgl_lahir, pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNo.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM1ActionPerformed

    private void MnLabelRM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelRM1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNo.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNo.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNo.getText() + "'"));
            Valid.MyReport("rptLabelRM.jasper", "report", "::[ LABEL BESAR IDENTITAS (Rekam Medis) ]::", 
                    "select no_rkm_medis, nm_pasien from pasien where no_rkm_medis='" + TNo.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLabelRM1ActionPerformed

	private void ppCatatanPasienBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCatatanPasienBtnPrintActionPerformed
            if (tabMode.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
                TNo.requestFocus();
            } else if (TNm.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien pada table...!!!");
                TCari.requestFocus();
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgCatatan catatan = new DlgCatatan(null, true);
                catatan.setNoRm(TNo.getText());
                catatan.setSize(720, 330);
                catatan.setLocationRelativeTo(internalFrame1);
                catatan.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
    }//GEN-LAST:event_ppCatatanPasienBtnPrintActionPerformed

	private void MnCoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCoverActionPerformed
            if (tabMode.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
                TNo.requestFocus();
            } else if (TNm.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
                tbPasien.requestFocus();
            } else {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptCoverMap.jasper", "report", "::[ Cover Rekam Medis ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                        + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                        + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,pasien.no_peserta,"
                        + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                        + "inner join kelurahan inner join kecamatan inner join kabupaten "
                        + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                        + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNo.getText() + "' ", param);
            }
    }//GEN-LAST:event_MnCoverActionPerformed

    private void ppGabungRMBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGabungRMBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNo.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien yang mau digabung data rekam medisnya...!!!");
            TCari.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien yang mau digabung data rekam medisnya...!!!");
            TCari.requestFocus();
        } else {
            NoRmTujuan.requestFocus();
            NoRmTujuan.setText("");
            NmPasienTujuan.setText("");
            WindowGabungRM.setSize(430, 130);
            WindowGabungRM.setLocationRelativeTo(internalFrame1);
            WindowGabungRM.setAlwaysOnTop(false);
            WindowGabungRM.setVisible(true);
        }
    }//GEN-LAST:event_ppGabungRMBtnPrintActionPerformed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowGabungRM.dispose();
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void BtnSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan6ActionPerformed
        if (NoRmTujuan.getText().trim().equals("")) {
            Valid.textKosong(NoRmTujuan, "No.R.M Tujuan masih kosong");
        } else if (NmPasienTujuan.getText().trim().equals("")) {
            Valid.textKosong(NmPasienTujuan, "Nama Pasien Tujuan");
        } else if (NoRmTujuan.getText().trim().equals(TNo.getText().trim())) {
            JOptionPane.showMessageDialog(rootPane, "No.R.M dan No.R.M. tujuan tidak boleh sama...!!");
        } else {
            if (Sequel.mengedittf("reg_periksa", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                NoRmTujuan.getText(), TNo.getText()
            }) == true) {
                Sequel.mengedit("bayar_piutang", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("catatan_pasien", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("pasien_bayi", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("pasien_mati", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("peminjaman_berkas", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("penjualan", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("piutang", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("piutang_pasien", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("retensi_pasien", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("returjual", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("returpiutang", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("rujukanranap_dokter_rs", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("sidikjaripasien", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("riwayat_obat_pasien", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("hiv_identitas_riwayat_pribadi", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("hiv_akhir_follow_up", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("hiv_follow_up_perawatan_dan_terapi", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("hiv_pemeriksaan_klinis_lab", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("hiv_pengobatan_tb", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("hiv_riwayat_keluarga", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("hiv_riwayat_terapi", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.mengedit("hiv_terapi_antiretroviral", "no_rkm_medis=?", "no_rkm_medis=?", 2, new String[]{
                    NoRmTujuan.getText(), TNo.getText()
                });
                Sequel.meghapus("pasien", "no_rkm_medis", TNo.getText());
                tampil();
                emptTeks();
                WindowGabungRM.dispose();
            }
        }
    }//GEN-LAST:event_BtnSimpan6ActionPerformed

    private void NoRmTujuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRmTujuanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!NoRmTujuan.getText().trim().equals("")) {
                Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=?", NmPasienTujuan, NoRmTujuan.getText());
                if (NmPasienTujuan.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Maaf, nama pasien tidak ditemukan...!!!");
                }
                NoRmTujuan.requestFocus();
            }
        }
    }//GEN-LAST:event_NoRmTujuanKeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=?", NmPasienTujuan, NoRmTujuan.getText());
        if (NmPasienTujuan.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Data pasien tidak ditemukan..!!");
        }
        NoRmTujuan.requestFocus();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void FormInputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FormInputMouseClicked
        jPopupMenu2.setVisible(false);
    }//GEN-LAST:event_FormInputMouseClicked

    private void MnBarcodeRM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM111.jasper", "report", "::[ BARCODE (Label KECIL Rekam Medis) ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d/%m/%Y') as tgl_lahir, pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + TNo.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM2ActionPerformed

    private void MnLabelRM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelRM2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNo.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNo.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNo.getText() + "'"));
            Valid.MyReport("rptLabelRM1.jasper", "report", "::[ LABEL KECIL IDENTITAS (Rekam Medis) ]::", 
                    "select no_rkm_medis, nm_pasien from pasien where pasien.no_rkm_medis='" + TNo.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLabelRM2ActionPerformed

    private void cmbAgamaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbAgamaMouseClicked
        cmbAgama.setEditable(false);
    }//GEN-LAST:event_cmbAgamaMouseClicked

    private void CmbSttsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CmbSttsMouseClicked
        CmbStts.setEditable(false);
    }//GEN-LAST:event_CmbSttsMouseClicked

    private void CMbGdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMbGdMouseClicked
        CMbGd.setEditable(false);
    }//GEN-LAST:event_CMbGdMouseClicked

    private void CMbPndMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMbPndMouseClicked
        CMbPnd.setEditable(false);
    }//GEN-LAST:event_CMbPndMouseClicked

    private void cmbHlmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbHlmMouseClicked
        cmbHlm.setEditable(false);
    }//GEN-LAST:event_cmbHlmMouseClicked

    private void BtnSukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSukuActionPerformed
        akses.setform("DlgPasien");
        suku.isCek();
        suku.setSize(1105, 512);
        suku.setLocationRelativeTo(internalFrame1);
        suku.setVisible(true);
        suku.TCari.requestFocus();
    }//GEN-LAST:event_BtnSukuActionPerformed

    private void BtnSukuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSukuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSukuActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            PekerjaanPj.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnBahasa.requestFocus();
        }
    }//GEN-LAST:event_BtnSukuKeyPressed

    private void BtnBahasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBahasaActionPerformed
        akses.setform("DlgPasien");
        bahasa.isCek();
        bahasa.setSize(1105, 512);
        bahasa.setLocationRelativeTo(internalFrame1);
        bahasa.setVisible(true);
        bahasa.TCari.requestFocus();
    }//GEN-LAST:event_BtnBahasaActionPerformed

    private void BtnBahasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBahasaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBahasaActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnSuku.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_BtnBahasaKeyPressed

    private void ScrollMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ScrollMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ScrollMouseClicked

    private void MnKartuUmumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartuUmumActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.MyReport("rptKartuBerobat.jasper", "report", "::[ Kartu Berobat Pasien (KIB) Umum ]::",
                "select * from pasien where pasien.no_rkm_medis='" + TNo.getText() + "' ");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnKartuUmumActionPerformed

    private void MnKartuNonUmumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartuNonUmumActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNo.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbPasien.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.MyReport("rptKartuBerobatAsuransi.jasper", "report", "::[ Kartu Berobat Pasien (KIB) Non Umum ]::",
                "select * from pasien where pasien.no_rkm_medis='" + TNo.getText() + "' ");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnKartuNonUmumActionPerformed

    private void ChkDaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkDaftarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkDaftarActionPerformed

    private void TNmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNmKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_TNmKeyTyped

    private void TNmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNmActionPerformed
        
    }//GEN-LAST:event_TNmActionPerformed

    private void TNmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNmKeyReleased

    }//GEN-LAST:event_TNmKeyReleased

    private void cmbPngJawabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPngJawabMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPngJawabMouseClicked

    private void cmbPngJawabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbPngJawabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPngJawabKeyPressed

    private void AlamatDomisiliMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlamatDomisiliMouseMoved
        if (AlamatDomisili.getText().equals("ALAMAT")) {
            AlamatDomisili.setText("");
        }
    }//GEN-LAST:event_AlamatDomisiliMouseMoved

    private void AlamatDomisiliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlamatDomisiliMouseExited
        if (AlamatDomisili.getText().equals("")) {
            AlamatDomisili.setText("ALAMAT");
        }
    }//GEN-LAST:event_AlamatDomisiliMouseExited

    private void AlamatDomisiliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatDomisiliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (AlamatDomisili.getText().equals("")) {
                AlamatDomisili.setText("ALAMAT");
            }
            if (KelurahanDom.getText().equals("KELURAHAN")) {
                KelurahanDom.setText("");
            }
            KelurahanDom.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (AlamatDomisili.getText().equals("")) {
                AlamatDomisili.setText("ALAMAT");
            }
        }
    }//GEN-LAST:event_AlamatDomisiliKeyPressed

    private void KelurahanDomMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KelurahanDomMouseMoved
        if (KelurahanDom.getText().equals("")) {
            KelurahanDom.setText("KELURAHAN");
        }
    }//GEN-LAST:event_KelurahanDomMouseMoved

    private void KelurahanDomMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KelurahanDomMouseExited
        if (KelurahanDom.getText().equals("")) {
            KelurahanDom.setText("KELURAHAN");
        }
    }//GEN-LAST:event_KelurahanDomMouseExited

    private void KelurahanDomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelurahanDomKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (KelurahanDom.getText().equals("")) {
                KelurahanDom.setText("KELURAHAN");
            }
            if (KecamatanDom.getText().equals("KECAMATAN")) {
                KecamatanDom.setText("");
            }
            KecamatanDom.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (KelurahanDom.getText().equals("")) {
                KelurahanDom.setText("KELURAHAN");
            }
            if (AlamatDomisili.getText().equals("ALAMAT")) {
                AlamatDomisili.setText("");
            }
            AlamatDomisili.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnKelurahanDomActionPerformed(null);
        }
    }//GEN-LAST:event_KelurahanDomKeyPressed

    private void BtnKelurahanDomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKelurahanDomActionPerformed
        akses.setform("DlgPasien");
        pilih = 4;
        kel.setSize(703, 384);
        kel.setLocationRelativeTo(internalFrame1);
        kel.setVisible(true);
        kel.TCari.requestFocus();
        kel.isCek();
    }//GEN-LAST:event_BtnKelurahanDomActionPerformed

    private void KecamatanDomMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KecamatanDomMouseMoved
        if (KecamatanDom.getText().equals("KECAMATAN")) {
            KecamatanDom.setText("");
        }
    }//GEN-LAST:event_KecamatanDomMouseMoved

    private void KecamatanDomMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KecamatanDomMouseExited
        if (KecamatanDom.getText().equals("")) {
            KecamatanDom.setText("KECAMATAN");
        }
    }//GEN-LAST:event_KecamatanDomMouseExited

    private void KecamatanDomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KecamatanDomKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (KecamatanDom.getText().equals("")) {
                KecamatanDom.setText("KECAMATAN");
            }
            if (KabupatenDom.getText().equals("KABUPATEN")) {
                KabupatenDom.setText("");
            }
            KabupatenDom.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (KecamatanDom.getText().equals("")) {
                KecamatanDom.setText("KECAMATAN");
            }
            if (KelurahanDom.getText().equals("KELURAHAN")) {
                KelurahanDom.setText("");
            }
            KelurahanDom.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnKecamatanDomActionPerformed(null);
        }
    }//GEN-LAST:event_KecamatanDomKeyPressed

    private void BtnKecamatanDomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKecamatanDomActionPerformed
        akses.setform("DlgPasien");
        pilih = 4;
        kec.setSize(703, 384);
        kec.setLocationRelativeTo(internalFrame1);
        kec.setVisible(true);
        kec.TCari.requestFocus();
        kec.isCek();
    }//GEN-LAST:event_BtnKecamatanDomActionPerformed

    private void KabupatenDomMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KabupatenDomMouseMoved
        if (KabupatenDom.getText().equals("")) {
            KabupatenDom.setText("KABUPATEN");
        }
    }//GEN-LAST:event_KabupatenDomMouseMoved

    private void KabupatenDomMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KabupatenDomMouseExited
        if (KabupatenDom.getText().equals("")) {
            KabupatenDom.setText("KABUPATEN");
        }
    }//GEN-LAST:event_KabupatenDomMouseExited

    private void KabupatenDomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KabupatenDomKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (KabupatenDom.getText().equals("")) {
                KabupatenDom.setText("KABUPATEN");
            }
            if (AlamatPj.getText().equals("ALAMAT")) {
                AlamatPj.setText("");
            }
            AlamatPj.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (KabupatenDom.getText().equals("")) {
                KabupatenDom.setText("KABUPATEN");
            }
            if (KecamatanDom.getText().equals("KECAMATAN")) {
                KecamatanDom.setText("");
            }
            KecamatanDom.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnKabupatenDomActionPerformed(null);
        }
    }//GEN-LAST:event_KabupatenDomKeyPressed

    private void BtnKabupatenDomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKabupatenDomActionPerformed
        akses.setform("DlgPasien");
        pilih = 4;
        kab.setSize(703, 384);
        kab.setLocationRelativeTo(internalFrame1);
        kab.setVisible(true);
        kab.TCari.requestFocus();
        kab.isCek();
    }//GEN-LAST:event_BtnKabupatenDomActionPerformed

    private void ChkSamakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSamakanActionPerformed
        if (ChkSamakan.isSelected() == true) {
            if (!Alamat.getText().equals("")) {
                AlamatDomisili.setText(Alamat.getText());
            } else {
                AlamatDomisili.setText("ALAMAT");
            }
            
            if (!Kelurahan.getText().equals("")) {
                KelurahanDom.setText(Kelurahan.getText());
            } else {
                KelurahanDom.setText("KELURAHAN");
            }

            if (!Kecamatan.getText().equals("")) {
                KecamatanDom.setText(Kecamatan.getText());
            } else {
                Kecamatan.setText("KECAMATAN");
            }
            
            if (!Kabupaten.getText().equals("")) {
                KabupatenDom.setText(Kabupaten.getText());
            } else {
                Kabupaten.setText("KABUPATEN");
            }
        }
    }//GEN-LAST:event_ChkSamakanActionPerformed

    private void MnPrinterBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPrinterBaruActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("kotars", akses.getkabupatenrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNo.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNo.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNo.getText() + "'"));
            Valid.MyReport("rptGelangDewasa.jasper", "report", "::[ Gelang Pasien Dewasa/Anak (Laki-laki/Perempuan) ]::",
                    "select no_rkm_medis, nm_pasien, jk from pasien where no_rkm_medis='" + TNo.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPrinterBaruActionPerformed

    private void MnPrinterLamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPrinterLamaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNo.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNo.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNo.getText() + "'"));
            Valid.MyReport("rptBarcodeRM7.jasper", "report", "::[ Gelang Pasien Dewasa/Anak (Laki-laki/Perempuan) ]::",
                    "select no_rkm_medis, nm_pasien, jk from pasien where no_rkm_medis='" + TNo.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPrinterLamaActionPerformed

    private void MnPrinterBaru1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPrinterBaru1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("kotars", akses.getkabupatenrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNo.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNo.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNo.getText() + "'"));
            Valid.MyReport("rptGelangBayi2021.jasper", "report", "::[ Gelang Pasien Bayi (Laki-laki/Perempuan) ]::",
                    "select no_rkm_medis, nm_pasien, jk from pasien where no_rkm_medis='" + TNo.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPrinterBaru1ActionPerformed

    private void MnPrinterLama1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPrinterLama1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNo.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNo.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNo.getText() + "'"));
            Valid.MyReport("rptGelangBayi.jasper", "report", "::[ Gelang Pasien Bayi (Laki-laki/Perempuan) ]::",
                    "select no_rkm_medis, nm_pasien, jk from pasien where no_rkm_medis='" + TNo.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPrinterLama1ActionPerformed

    private void MnPrinterBaru2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPrinterBaru2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TNm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada tabel...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("kotars", akses.getkabupatenrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNo.getText() + "'") + " "
                + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNo.getText() + "'") + " "
                + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNo.getText() + "'"));
            Valid.MyReport("rptGelangBayi2022.jasper", "report", "::[ Gelang Pasien Bayi (Laki-laki/Perempuan) ]::",
                "select no_rkm_medis, nm_pasien, jk from pasien where no_rkm_medis='" + TNo.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPrinterBaru2ActionPerformed

    private void umurPjawabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_umurPjawabKeyPressed
        Valid.pindah(evt, cmbPngJawab, Saudara);
    }//GEN-LAST:event_umurPjawabKeyPressed

    /**
     * @data args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DlgPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            DlgPasien dialog = new DlgPasien(new javax.swing.JFrame(), true);
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
    private widget.TextBox Alamat;
    private widget.TextBox AlamatDomisili;
    private widget.TextBox AlamatPj;
    private widget.Button BtnAll;
    private widget.Button BtnBahasa;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn6;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKabupaten;
    private widget.Button BtnKabupatenDom;
    private widget.Button BtnKabupatenPj;
    private widget.Button BtnKecamatan;
    private widget.Button BtnKecamatanDom;
    private widget.Button BtnKecamatanPj;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar2;
    private widget.Button BtnKelurahan;
    private widget.Button BtnKelurahan1;
    private widget.Button BtnKelurahanDom;
    private widget.Button BtnKelurahanPj;
    private widget.Button BtnPenjab;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint2;
    private widget.Button BtnPrint3;
    private widget.Button BtnSeek10;
    private widget.Button BtnSeek8;
    private widget.Button BtnSeek9;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan6;
    private widget.Button BtnSuku;
    private widget.ComboBox CMbGd;
    private widget.ComboBox CMbPnd;
    private widget.TextBox Carialamat;
    private widget.CekBox ChkDaftar;
    private widget.CekBox ChkIbuAnak;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkRM;
    private widget.CekBox ChkSamakan;
    private widget.ComboBox CmbJk;
    private widget.ComboBox CmbStts;
    private widget.Tanggal DTPDaftar;
    private widget.Tanggal DTPLahir;
    private javax.swing.JDialog DlgDemografi;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Kabupaten;
    private widget.TextBox Kabupaten2;
    private widget.TextBox KabupatenDom;
    private widget.TextBox KabupatenPj;
    private widget.TextBox Kd2;
    private widget.TextBox Kdpnj;
    private widget.TextBox Kecamatan;
    private widget.TextBox Kecamatan2;
    private widget.TextBox KecamatanDom;
    private widget.TextBox KecamatanPj;
    private widget.TextBox Kelurahan;
    private widget.TextBox Kelurahan2;
    private widget.TextBox KelurahanDom;
    private widget.TextBox KelurahanPj;
    private widget.Label LCount;
    private javax.swing.JMenu MenuBPJS;
    private javax.swing.JMenu MenuIdentitas;
    private javax.swing.JMenuItem MnBarcode;
    private javax.swing.JMenuItem MnBarcodeRM1;
    private javax.swing.JMenuItem MnBarcodeRM2;
    private javax.swing.JMenuItem MnCekKepesertaan;
    private javax.swing.JMenuItem MnCekNIK;
    private javax.swing.JMenu MnCetakKelengkapanInap;
    private javax.swing.JMenuItem MnCopyResep;
    private javax.swing.JMenuItem MnCover;
    private javax.swing.JMenuItem MnFormulirPendaftaran;
    private javax.swing.JMenu MnGelangBayi;
    private javax.swing.JMenu MnGelangDewasaAnak;
    private javax.swing.JMenuItem MnIdentitas1;
    private javax.swing.JMenuItem MnIdentitas2;
    private javax.swing.JMenuItem MnIdentitas3;
    private javax.swing.JMenu MnKartu;
    private javax.swing.JMenuItem MnKartuNonUmum;
    private javax.swing.JMenuItem MnKartuStatus;
    private javax.swing.JMenuItem MnKartuUmum;
    private javax.swing.JMenuItem MnLabelRM1;
    private javax.swing.JMenuItem MnLabelRM2;
    private javax.swing.JMenuItem MnLaporanAnestesia;
    private javax.swing.JMenuItem MnLaporanIGD;
    private javax.swing.JMenuItem MnLaporanRM;
    private javax.swing.JMenuItem MnLaporanRM1;
    private javax.swing.JMenuItem MnLaporanRM2;
    private javax.swing.JMenuItem MnLembarAnamNesa;
    private javax.swing.JMenuItem MnLembarCatatanKeperawatan;
    private javax.swing.JMenuItem MnLembarCatatanPerkembangan;
    private javax.swing.JMenuItem MnLembarGrafik;
    private javax.swing.JMenuItem MnLembarKeluarMasuk;
    private javax.swing.JMenuItem MnLembarKeluarMasuk2;
    private javax.swing.JMenuItem MnPengantarHemodalisa;
    private javax.swing.JMenuItem MnPrinterBaru;
    private javax.swing.JMenuItem MnPrinterBaru1;
    private javax.swing.JMenuItem MnPrinterBaru2;
    private javax.swing.JMenuItem MnPrinterLama;
    private javax.swing.JMenuItem MnPrinterLama1;
    private javax.swing.JMenuItem MnSCreening;
    private javax.swing.JMenuItem MnViaBPJSNik;
    private javax.swing.JMenuItem MnViaBPJSNoKartu;
    private widget.TextBox NmIbu;
    private widget.TextBox NmPasienTujuan;
    private widget.TextBox NoRm;
    private widget.TextBox NoRmTujuan;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox Pekerjaan;
    private widget.TextBox PekerjaanPj;
    private widget.TextBox Saudara;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.TextBox TKtp;
    private widget.TextBox TNm;
    private widget.TextBox TNo;
    private widget.TextBox TNoPeserta;
    private widget.TextBox TTlp;
    private widget.TextBox TTmp;
    private widget.TextBox TUmurBl;
    private widget.TextBox TUmurHr;
    private widget.TextBox TUmurTh;
    private javax.swing.JDialog WindowGabungRM;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.ComboBox cmbAgama;
    private widget.ComboBox cmbHlm;
    private widget.ComboBox cmbPngJawab;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame8;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
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
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JSeparator jSeparator5;
    private widget.TextBox kdbahasa;
    private widget.TextBox kdsuku;
    private widget.Label label40;
    private widget.Label label41;
    private widget.TextBox nmbahasa;
    private widget.TextBox nmpnj;
    private widget.TextBox nmsukubangsa;
    private widget.TextBox notlpPJ;
    private widget.PanelBiasa panelBiasa2;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JMenuItem ppCatatanPasien;
    private javax.swing.JMenuItem ppGabungRM;
    private javax.swing.JMenuItem ppGrafikDemografi;
    private javax.swing.JMenuItem ppGrafikPerAgama;
    private javax.swing.JMenuItem ppGrafikPerPekerjaan;
    private javax.swing.JMenuItem ppGrafikjkbayi;
    private javax.swing.JMenuItem ppKelahiranBayi;
    private javax.swing.JMenuItem ppRegistrasi;
    private javax.swing.JMenuItem ppRiwayat;
    private widget.Table tbPasien;
    private widget.TextBox umurPjawab;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, p.no_ktp, p.jk, p.tmp_lahir, p.tgl_lahir, p.nm_ibu, concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) AS alamat, "
                    + "p.gol_darah, p.pekerjaan, p.stts_nikah, p.agama, p.tgl_daftar, p.no_tlp, p.umur, p.pnd, p.keluarga, p.namakeluarga, pj.png_jawab, p.no_peserta, p.pekerjaanpj, "
                    + "concat(p.alamatpj,', ',p.kelurahanpj,', ',p.kecamatanpj,', ',p.kabupatenpj) almt_pj, sb.nama_suku_bangsa, bp.nama_bahasa, sb.id id_suku, bp.id id_bhs, "
                    + "concat(p.alamat_domisili_pasien,', ',kl2.nm_kel,', ',kc2.nm_kec,', ',kb2.nm_kab) AS alamat_dom, ifnull(p.umur_pj,'') umurpj, "
                    + "ifnull(p.no_tlp_pj,'') notlppj FROM pasien p INNER JOIN kelurahan kl1 ON kl1.kd_kel=p.kd_kel INNER JOIN kecamatan kc1 ON kc1.kd_kec=p.kd_kec INNER JOIN kabupaten kb1 ON kb1.kd_kab=p.kd_kab "
                    + "INNER JOIN penjab pj ON pj.kd_pj=p.kd_pj INNER JOIN bahasa_pasien bp ON bp.id=p.bahasa_pasien INNER JOIN suku_bangsa sb ON sb.id=p.suku_bangsa "
                    + "INNER JOIN kelurahan kl2 ON kl2.kd_kel = p.kd_kel_domisili_pasien INNER JOIN kecamatan kc2 ON kc2.kd_kec = p.kd_kec_domisili_pasien INNER JOIN kabupaten kb2 ON kb2.kd_kab = p.kd_kab_domisili_pasien WHERE "
                    + "concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) LIKE ? AND p.no_rkm_medis LIKE ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.nm_pasien like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.no_ktp like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.no_peserta like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.tmp_lahir like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.tgl_lahir like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and pj.png_jawab like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.alamat like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.gol_darah like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.pekerjaan like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.stts_nikah like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.namakeluarga like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.agama like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.nm_ibu like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.tgl_daftar like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and sb.nama_suku_bangsa like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and bp.nama_bahasa like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and concat(p.alamat_domisili_pasien,', ',kl2.nm_kel,', ',kc2.nm_kec,', ',kb2.nm_kab) like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.no_tlp like ? order by p.no_rkm_medis desc LIMIT ? ");
            
            ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, p.no_ktp, p.jk, p.tmp_lahir, p.tgl_lahir, p.nm_ibu, concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) AS alamat, "
                    + "p.gol_darah, p.pekerjaan, p.stts_nikah, p.agama, p.tgl_daftar, p.no_tlp, p.umur, p.pnd, p.keluarga, p.namakeluarga, pj.png_jawab, p.no_peserta, p.pekerjaanpj, "
                    + "concat(p.alamatpj,', ',p.kelurahanpj,', ',p.kecamatanpj,', ',p.kabupatenpj) almt_pj, sb.nama_suku_bangsa, bp.nama_bahasa, sb.id id_suku, bp.id id_bhs, "
                    + "concat(p.alamat_domisili_pasien,', ',kl2.nm_kel,', ',kc2.nm_kec,', ',kb2.nm_kab) AS alamat_dom, ifnull(p.umur_pj,'') umurpj, "
                    + "ifnull(p.no_tlp_pj,'') notlppj FROM pasien p INNER JOIN kelurahan kl1 ON kl1.kd_kel=p.kd_kel INNER JOIN kecamatan kc1 ON kc1.kd_kec=p.kd_kec INNER JOIN kabupaten kb1 ON kb1.kd_kab=p.kd_kab "
                    + "INNER JOIN penjab pj ON pj.kd_pj=p.kd_pj INNER JOIN bahasa_pasien bp ON bp.id=p.bahasa_pasien INNER JOIN suku_bangsa sb ON sb.id=p.suku_bangsa "
                    + "INNER JOIN kelurahan kl2 ON kl2.kd_kel = p.kd_kel_domisili_pasien INNER JOIN kecamatan kc2 ON kc2.kd_kec = p.kd_kec_domisili_pasien INNER JOIN kabupaten kb2 ON kb2.kd_kab = p.kd_kab_domisili_pasien WHERE "
                    + "concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) LIKE ? AND p.no_rkm_medis LIKE ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.nm_pasien like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.no_ktp like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.no_peserta like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.tmp_lahir like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.tgl_lahir like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and pj.png_jawab like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.alamat like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.gol_darah like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.pekerjaan like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.stts_nikah like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.namakeluarga like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.agama like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.nm_ibu like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.tgl_daftar like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and sb.nama_suku_bangsa like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and bp.nama_bahasa like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and concat(p.alamat_domisili_pasien,', ',kl2.nm_kel,', ',kc2.nm_kec,', ',kb2.nm_kab) like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.no_tlp like ? order by p.no_rkm_medis desc");
            try {
                if (cmbHlm.getSelectedItem().toString().equals("Semua")) {
                    ps2.setString(1, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(2, "%" + TCari.getText().trim() + "%");
                    ps2.setString(3, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(4, "%" + TCari.getText().trim() + "%");
                    ps2.setString(5, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(6, "%" + TCari.getText().trim() + "%");
                    ps2.setString(7, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(8, "%" + TCari.getText().trim() + "%");
                    ps2.setString(9, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(10, "%" + TCari.getText().trim() + "%");
                    ps2.setString(11, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(12, "%" + TCari.getText().trim() + "%");
                    ps2.setString(13, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(14, "%" + TCari.getText().trim() + "%");
                    ps2.setString(15, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(16, "%" + TCari.getText().trim() + "%");
                    ps2.setString(17, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(18, "%" + TCari.getText().trim() + "%");
                    ps2.setString(19, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(20, "%" + TCari.getText().trim() + "%");
                    ps2.setString(21, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(22, "%" + TCari.getText().trim() + "%");
                    ps2.setString(23, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(24, "%" + TCari.getText().trim() + "%");
                    ps2.setString(25, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(26, "%" + TCari.getText().trim() + "%");
                    ps2.setString(27, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(28, "%" + TCari.getText().trim() + "%");
                    ps2.setString(29, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(30, "%" + TCari.getText().trim() + "%");
                    ps2.setString(31, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(32, "%" + TCari.getText().trim() + "%");
                    ps2.setString(33, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(34, "%" + TCari.getText().trim() + "%");
                    ps2.setString(35, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(36, "%" + TCari.getText().trim() + "%");
                    ps2.setString(37, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(38, "%" + TCari.getText().trim() + "%");
                    rs = ps2.executeQuery();
                } else {
                    ps.setString(1, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(2, "%" + TCari.getText().trim() + "%");
                    ps.setString(3, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(4, "%" + TCari.getText().trim() + "%");
                    ps.setString(5, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(8, "%" + TCari.getText().trim() + "%");
                    ps.setString(9, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(10, "%" + TCari.getText().trim() + "%");
                    ps.setString(11, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(12, "%" + TCari.getText().trim() + "%");
                    ps.setString(13, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(14, "%" + TCari.getText().trim() + "%");
                    ps.setString(15, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(16, "%" + TCari.getText().trim() + "%");
                    ps.setString(17, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(18, "%" + TCari.getText().trim() + "%");
                    ps.setString(19, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(20, "%" + TCari.getText().trim() + "%");
                    ps.setString(21, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(22, "%" + TCari.getText().trim() + "%");
                    ps.setString(23, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(24, "%" + TCari.getText().trim() + "%");
                    ps.setString(25, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(26, "%" + TCari.getText().trim() + "%");
                    ps.setString(27, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(28, "%" + TCari.getText().trim() + "%");
                    ps.setString(29, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(30, "%" + TCari.getText().trim() + "%");
                    ps.setString(31, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(32, "%" + TCari.getText().trim() + "%");
                    ps.setString(33, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(34, "%" + TCari.getText().trim() + "%");
                    ps.setString(35, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(36, "%" + TCari.getText().trim() + "%");
                    ps.setString(37, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(38, "%" + TCari.getText().trim() + "%");
                    ps.setInt(39, Integer.parseInt(cmbHlm.getSelectedItem().toString()));
                    rs = ps.executeQuery();
                }
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        false, 
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("no_ktp"),
                        rs.getString("jk"),
                        rs.getString("tmp_lahir"),
                        rs.getString("tgl_lahir"),
                        rs.getString("nm_ibu"),
                        rs.getString("alamat"),
                        rs.getString("alamat_dom"),
                        rs.getString("gol_darah"),
                        rs.getString("pekerjaan"),
                        rs.getString("stts_nikah"),
                        rs.getString("agama"),
                        rs.getString("tgl_daftar"),
                        rs.getString("no_tlp"),
                        rs.getString("umur"),
                        rs.getString("pnd"),
                        rs.getString("keluarga"),
                        rs.getString("namakeluarga"),
                        rs.getString("png_jawab"), 
                        rs.getString("no_peserta"),
                        "Klik Kanan, Tampilkan Banyak Daftar",                        
                        rs.getString("pekerjaanpj"),
                        rs.getString("almt_pj"),
                        rs.getString("nama_suku_bangsa"),
                        rs.getString("nama_bahasa"),
                        rs.getString("id_suku"),
                        rs.getString("id_bhs"),
                        rs.getString("umurpj"),
                        rs.getString("notlppj")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }

                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        TNo.setText("");
        Kd2.setText("");
        TNm.setText("");
        CmbJk.setSelectedIndex(0);
        CMbGd.setSelectedIndex(0);
        TTmp.setText("");
        cmbAgama.setSelectedIndex(0);
        CmbStts.setSelectedIndex(0);
        Alamat.setText("ALAMAT");
        AlamatDomisili.setText("ALAMAT");
        AlamatPj.setText("ALAMAT");
        TKtp.setText("");
        TNoPeserta.setText("");
        Pekerjaan.setText("");
        PekerjaanPj.setText("");
        TTlp.setText("0");
        TUmurTh.setText("");
        Saudara.setText("");
        NmIbu.setText("");
        kdsuku.setText("");
        nmsukubangsa.setText("");
        kdbahasa.setText("");
        nmbahasa.setText("");
        umurPjawab.setText("");
        notlpPJ.setText("");
        Kelurahan.setText("KELURAHAN");
        Kecamatan.setText("KECAMATAN");
        Kabupaten.setText("KABUPATEN");
        KelurahanDom.setText("KELURAHAN");
        KecamatanDom.setText("KECAMATAN");
        KabupatenDom.setText("KABUPATEN");
        KelurahanPj.setText("KELURAHAN");
        KecamatanPj.setText("KECAMATAN");
        KabupatenPj.setText("KABUPATEN");
        cmbPngJawab.setSelectedIndex(0);
        DTPLahir.setDate(new Date());
        DTPDaftar.setDate(new Date());
        jPopupMenu2.setVisible(false);
        ChkIbuAnak.setSelected(false);

        autoNomor();

        TNo.requestFocus();

        if (akses.getkode().equals("Admin Utama")) {
            Kdpnj.setText("");
            nmpnj.setText("");

        } else if ((akses.getkode().equals("PP24")) || (akses.getkode().equals("PP23"))) {
            Kdpnj.setText("U01");
            nmpnj.setText("UMUM");

        } else {
            Kdpnj.setText("U01");
            nmpnj.setText("UMUM");
        }
    }

    private void getData() {
        if (tbPasien.getSelectedRow() != -1) {
            try {
                TNo.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 1).toString());
                Kd2.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 1).toString());
                TNm.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 2).toString());
                TKtp.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 3).toString());
                kdsuku.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 27).toString());
                kdbahasa.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 28).toString());
                umurPjawab.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 29).toString());
                notlpPJ.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 30).toString());
                Sequel.cariIsi("select nama_suku_bangsa from suku_bangsa where id=?", nmsukubangsa, kdsuku.getText());
                Sequel.cariIsi("select nama_bahasa from bahasa_pasien where id=?", nmbahasa, kdbahasa.getText());

                switch (tbPasien.getValueAt(tbPasien.getSelectedRow(), 4).toString()) {
                    case "L":
                        CmbJk.setSelectedItem("LAKI-LAKI");
                        break;
                    case "P":
                        CmbJk.setSelectedItem("PEREMPUAN");
                        break;
                }

                TTmp.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 5).toString());

                pscariwilayah = koneksi.prepareStatement("SELECT p.alamat, kl1.nm_kel, kc1.nm_kec, kb1.nm_kab, p.pekerjaanpj, p.alamatpj, p.kelurahanpj, "
                        + "p.kecamatanpj, p.kabupatenpj, p.alamat_domisili_pasien, kl2.nm_kel nm_kelDom, kc2.nm_kec nm_kecDom, kb2.nm_kab nm_kabDom FROM pasien p "
                        + "INNER JOIN kelurahan kl1 ON kl1.kd_kel=p.kd_kel INNER JOIN kecamatan kc1 ON kc1.kd_kec=p.kd_kec INNER JOIN kabupaten kb1 ON kb1.kd_kab=p.kd_kab "
                        + "INNER JOIN kelurahan kl2 ON kl2.kd_kel=p.kd_kel_domisili_pasien INNER JOIN kecamatan kc2 ON kc2.kd_kec=p.kd_kec_domisili_pasien "
                        + "INNER JOIN kabupaten kb2 ON kb2.kd_kab=p.kd_kab_domisili_pasien WHERE p.no_rkm_medis=?");
                try {
                    pscariwilayah.setString(1, tbPasien.getValueAt(tbPasien.getSelectedRow(), 1).toString());
                    rs = pscariwilayah.executeQuery();
                    if (rs.next()) {
                        Alamat.setText(rs.getString("alamat"));
                        Kabupaten.setText(rs.getString("nm_kab"));
                        Kecamatan.setText(rs.getString("nm_kec"));
                        Kelurahan.setText(rs.getString("nm_kel"));                        
                        AlamatDomisili.setText(rs.getString("alamat_domisili_pasien"));
                        KabupatenDom.setText(rs.getString("nm_kabDom"));
                        KecamatanDom.setText(rs.getString("nm_kecDom"));
                        KelurahanDom.setText(rs.getString("nm_kelDom"));                        
                        PekerjaanPj.setText(rs.getString("pekerjaanpj"));
                        AlamatPj.setText(rs.getString("alamatpj"));
                        KelurahanPj.setText(rs.getString("kelurahanpj"));
                        KecamatanPj.setText(rs.getString("kecamatanpj"));
                        KabupatenPj.setText(rs.getString("kabupatenpj"));
                    }
                } catch (Exception e) {
                    System.out.println("Notofikasi : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }

                    if (pscariwilayah != null) {
                        pscariwilayah.close();
                    }
                }

                CMbGd.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 10).toString());
                Pekerjaan.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 11).toString());
                CmbStts.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 12).toString());
                cmbAgama.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 13).toString());
                TTlp.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 15).toString());
                Saudara.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 19).toString());
                Sequel.cariIsi("select kd_pj from pasien where no_rkm_medis='" + TNo.getText() + "'", Kdpnj);
                nmpnj.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 20).toString());
                TNoPeserta.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 21).toString());
                NmIbu.setText(tbPasien.getValueAt(tbPasien.getSelectedRow(), 7).toString());
                CMbPnd.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 17).toString());
                cmbPngJawab.setSelectedItem(tbPasien.getValueAt(tbPasien.getSelectedRow(), 18).toString());

            } catch (Exception ex) {
            }

            Valid.SetTgl(DTPLahir, tbPasien.getValueAt(tbPasien.getSelectedRow(), 6).toString());
            Valid.SetTgl(DTPDaftar, tbPasien.getValueAt(tbPasien.getSelectedRow(), 14).toString());
        }
    }

    public JTable getTable() {
        return tbPasien;
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 300));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getpasien());
        BtnHapus.setEnabled(akses.getpasien());
        BtnEdit.setEnabled(akses.getpasien());
//        BtnPrint.setEnabled(var.getpasien());
        ppGabungRM.setEnabled(akses.getpasien());
        ppRiwayat.setEnabled(akses.getresume_pasien());
        ppCatatanPasien.setEnabled(akses.getcatatan_pasien());
        asalform = akses.getform();
    }

    private void prosesCari2() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, p.no_ktp, p.jk, p.tmp_lahir, p.tgl_lahir, p.nm_ibu, concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) AS alamat, "
                    + "p.gol_darah, p.pekerjaan, p.stts_nikah, p.agama, p.tgl_daftar, p.no_tlp, p.umur, p.pnd, p.keluarga, p.namakeluarga, pj.png_jawab, p.no_peserta, p.pekerjaanpj, "
                    + "concat(p.alamatpj,', ',p.kelurahanpj,', ',p.kecamatanpj,', ',p.kabupatenpj) almt_pj, sb.nama_suku_bangsa, bp.nama_bahasa, sb.id id_suku, bp.id id_bhs, "
                    + "concat(p.alamat_domisili_pasien,', ',kl2.nm_kel,', ',kc2.nm_kec,', ',kb2.nm_kab) AS alamat_dom FROM pasien p INNER JOIN kelurahan kl1 ON kl1.kd_kel=p.kd_kel "
                    + "INNER JOIN kecamatan kc1 ON kc1.kd_kec=p.kd_kec INNER JOIN kabupaten kb1 ON kb1.kd_kab=p.kd_kab "
                    + "INNER JOIN penjab pj ON pj.kd_pj=p.kd_pj INNER JOIN bahasa_pasien bp ON bp.id=p.bahasa_pasien INNER JOIN suku_bangsa sb ON sb.id=p.suku_bangsa "
                    + "INNER JOIN kelurahan kl2 ON kl2.kd_kel = p.kd_kel_domisili_pasien INNER JOIN kecamatan kc2 ON kc2.kd_kec = p.kd_kec_domisili_pasien INNER JOIN kabupaten kb2 ON kb2.kd_kab = p.kd_kab_domisili_pasien WHERE "
                    + "concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) LIKE ? AND p.no_rkm_medis LIKE ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.nm_pasien like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.no_ktp like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.no_peserta like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.tmp_lahir like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.tgl_lahir like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and pj.png_jawab like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.alamat like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.gol_darah like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.pekerjaan like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.stts_nikah like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.namakeluarga like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.agama like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.nm_ibu like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.tgl_daftar like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and sb.nama_suku_bangsa like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and bp.nama_bahasa like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and concat(p.alamat_domisili_pasien,', ',kl2.nm_kel,', ',kc2.nm_kec,', ',kb2.nm_kab) like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.no_tlp like ? order by p.no_rkm_medis desc LIMIT ? ");
            ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, p.no_ktp, p.jk, p.tmp_lahir, p.tgl_lahir, p.nm_ibu, concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) AS alamat, "
                    + "p.gol_darah, p.pekerjaan, p.stts_nikah, p.agama, p.tgl_daftar, p.no_tlp, p.umur, p.pnd, p.keluarga, p.namakeluarga, pj.png_jawab, p.no_peserta, p.pekerjaanpj, "
                    + "concat(p.alamatpj,', ',p.kelurahanpj,', ',p.kecamatanpj,', ',p.kabupatenpj) almt_pj, sb.nama_suku_bangsa, bp.nama_bahasa, sb.id id_suku, bp.id id_bhs, "
                    + "concat(p.alamat_domisili_pasien,', ',kl2.nm_kel,', ',kc2.nm_kec,', ',kb2.nm_kab) AS alamat_dom FROM pasien p INNER JOIN kelurahan kl1 ON kl1.kd_kel=p.kd_kel "
                    + "INNER JOIN kecamatan kc1 ON kc1.kd_kec=p.kd_kec INNER JOIN kabupaten kb1 ON kb1.kd_kab=p.kd_kab "
                    + "INNER JOIN penjab pj ON pj.kd_pj=p.kd_pj INNER JOIN bahasa_pasien bp ON bp.id=p.bahasa_pasien INNER JOIN suku_bangsa sb ON sb.id=p.suku_bangsa "
                    + "INNER JOIN kelurahan kl2 ON kl2.kd_kel = p.kd_kel_domisili_pasien INNER JOIN kecamatan kc2 ON kc2.kd_kec = p.kd_kec_domisili_pasien INNER JOIN kabupaten kb2 ON kb2.kd_kab = p.kd_kab_domisili_pasien WHERE "
                    + "concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) LIKE ? AND p.no_rkm_medis LIKE ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.nm_pasien like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.no_ktp like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.no_peserta like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.tmp_lahir like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.tgl_lahir like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and pj.png_jawab like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.alamat like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.gol_darah like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.pekerjaan like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.stts_nikah like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.namakeluarga like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.agama like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.nm_ibu like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.tgl_daftar like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and sb.nama_suku_bangsa like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and bp.nama_bahasa like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and concat(p.alamat_domisili_pasien,', ',kl2.nm_kel,', ',kc2.nm_kec,', ',kb2.nm_kab) like ? "
                    + "or concat(p.alamat,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) like ? and p.no_tlp like ? order by p.no_rkm_medis desc");
            try {
                if (cmbHlm.getSelectedItem().toString().equals("Semua")) {
                    ps2.setString(1, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(2, "%" + TCari.getText().trim() + "%");
                    ps2.setString(3, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(4, "%" + TCari.getText().trim() + "%");
                    ps2.setString(5, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(6, "%" + TCari.getText().trim() + "%");
                    ps2.setString(7, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(8, "%" + TCari.getText().trim() + "%");
                    ps2.setString(9, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(10, "%" + TCari.getText().trim() + "%");
                    ps2.setString(11, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(12, "%" + TCari.getText().trim() + "%");
                    ps2.setString(13, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(14, "%" + TCari.getText().trim() + "%");
                    ps2.setString(15, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(16, "%" + TCari.getText().trim() + "%");
                    ps2.setString(17, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(18, "%" + TCari.getText().trim() + "%");
                    ps2.setString(19, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(20, "%" + TCari.getText().trim() + "%");
                    ps2.setString(21, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(22, "%" + TCari.getText().trim() + "%");
                    ps2.setString(23, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(24, "%" + TCari.getText().trim() + "%");
                    ps2.setString(25, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(26, "%" + TCari.getText().trim() + "%");
                    ps2.setString(27, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(28, "%" + TCari.getText().trim() + "%");
                    ps2.setString(29, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(30, "%" + TCari.getText().trim() + "%");
                    ps2.setString(31, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(32, "%" + TCari.getText().trim() + "%");
                    ps2.setString(33, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(34, "%" + TCari.getText().trim() + "%");
                    ps2.setString(35, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(36, "%" + TCari.getText().trim() + "%");
                    ps2.setString(37, "%" + Carialamat.getText().trim() + "%");
                    ps2.setString(38, "%" + TCari.getText().trim() + "%");
                    rs = ps2.executeQuery();
                } else {
                    ps.setString(1, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(2, "%" + TCari.getText().trim() + "%");
                    ps.setString(3, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(4, "%" + TCari.getText().trim() + "%");
                    ps.setString(5, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(8, "%" + TCari.getText().trim() + "%");
                    ps.setString(9, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(10, "%" + TCari.getText().trim() + "%");
                    ps.setString(11, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(12, "%" + TCari.getText().trim() + "%");
                    ps.setString(13, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(14, "%" + TCari.getText().trim() + "%");
                    ps.setString(15, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(16, "%" + TCari.getText().trim() + "%");
                    ps.setString(17, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(18, "%" + TCari.getText().trim() + "%");
                    ps.setString(19, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(20, "%" + TCari.getText().trim() + "%");
                    ps.setString(21, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(22, "%" + TCari.getText().trim() + "%");
                    ps.setString(23, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(24, "%" + TCari.getText().trim() + "%");
                    ps.setString(25, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(26, "%" + TCari.getText().trim() + "%");
                    ps.setString(27, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(28, "%" + TCari.getText().trim() + "%");
                    ps.setString(29, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(30, "%" + TCari.getText().trim() + "%");
                    ps.setString(31, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(32, "%" + TCari.getText().trim() + "%");
                    ps.setString(33, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(34, "%" + TCari.getText().trim() + "%");
                    ps.setString(35, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(36, "%" + TCari.getText().trim() + "%");
                    ps.setString(37, "%" + Carialamat.getText().trim() + "%");
                    ps.setString(38, "%" + TCari.getText().trim() + "%");
                    ps.setInt(39, Integer.parseInt(cmbHlm.getSelectedItem().toString()));
                    rs = ps.executeQuery();
                }
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        false, 
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("no_ktp"),
                        rs.getString("jk"),
                        rs.getString("tmp_lahir"),
                        rs.getString("tgl_lahir"),
                        rs.getString("nm_ibu"),
                        rs.getString("alamat"),
                        rs.getString("alamat_dom"),
                        rs.getString("gol_darah"),
                        rs.getString("pekerjaan"),
                        rs.getString("stts_nikah"),
                        rs.getString("agama"),
                        rs.getString("tgl_daftar"),
                        rs.getString("no_tlp"),
                        rs.getString("umur"),
                        rs.getString("pnd"),
                        rs.getString("keluarga"),
                        rs.getString("namakeluarga"),
                        rs.getString("png_jawab"), 
                        rs.getString("no_peserta"),
                        Sequel.cariIsi("select count(reg_periksa.no_rkm_medis) from reg_periksa where reg_periksa.no_rkm_medis=?", rs.getString("no_rkm_medis")) + " X",                        
                        rs.getString("pekerjaanpj"),
                        rs.getString("almt_pj"),
                        rs.getString("nama_suku_bangsa"),
                        rs.getString("nama_bahasa"),
                        rs.getString("id_suku"),
                        rs.getString("id_bhs")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
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
        LCount.setText("" + tabMode.getRowCount());
    }

    public void TutupJendela() {
        ChkInput.setSelected(false);
        isForm();
    }

    private void autoNomor() {
        if (ChkIbuAnak.isSelected() == true){
            Kd2.setText("");
        }
        
        if (Kd2.getText().equals("")) {
            if (ChkRM.isSelected() == true) {
                if (tahun.equals("Yes")) {
                    awalantahun = DTPDaftar.getSelectedItem().toString().substring(8, 10);
                } else {
                    awalantahun = "";
                }

                if (bulan.equals("Yes")) {
                    awalanbulan = DTPDaftar.getSelectedItem().toString().substring(3, 5);
                } else {
                    awalanbulan = "";
                }

                if (posisitahun.equals("Depan")) {
                    switch (pengurutan) {
                        case "Straight":
                            Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_rkm_medis,6),signed)),0) from set_no_rkm_medis", "", 6, NoRm);
                            break;
                        case "Terminal":
                            Valid.autoNomer4("select ifnull(MAX(CONVERT(CONCAT(SUBSTRING(RIGHT(no_rkm_medis,6),5,2),SUBSTRING(RIGHT(no_rkm_medis,6),3,2),SUBSTRING(RIGHT(no_rkm_medis,6),1,2)),signed)),0) from set_no_rkm_medis", "", 6, NoRm);
                            break;
                        case "Middle":
                            Valid.autoNomer5("select ifnull(MAX(CONVERT(CONCAT(SUBSTRING(RIGHT(no_rkm_medis,6),3,2),SUBSTRING(RIGHT(no_rkm_medis,6),1,2),SUBSTRING(RIGHT(no_rkm_medis,6),5,2)),signed)),0) from set_no_rkm_medis", "", 6, NoRm);
                            break;
                    }
                } else if (posisitahun.equals("Belakang")) {
                    switch (pengurutan) {
                        case "Straight":
                            Valid.autoNomer3("select ifnull(MAX(CONVERT(LEFT(no_rkm_medis,6),signed)),0) from set_no_rkm_medis", "", 6, NoRm);
                            break;
                        case "Terminal":
                            Valid.autoNomer4("select ifnull(MAX(CONVERT(CONCAT(SUBSTRING(LEFT(no_rkm_medis,6),5,2),SUBSTRING(LEFT(no_rkm_medis,6),3,2),SUBSTRING(LEFT(no_rkm_medis,6),1,2)),signed)),0) from set_no_rkm_medis", "", 6, NoRm);
                            break;
                        case "Middle":
                            Valid.autoNomer5("select ifnull(MAX(CONVERT(CONCAT(SUBSTRING(LEFT(no_rkm_medis,6),3,2),SUBSTRING(LEFT(no_rkm_medis,6),1,2),SUBSTRING(LEFT(no_rkm_medis,6),5,2)),signed)),0) from set_no_rkm_medis", "", 6, NoRm);
                            break;
                    }
                }

                if (posisitahun.equals("Depan")) {
                    TNo.setText(awalantahun + awalanbulan + NoRm.getText());
                } else if (posisitahun.equals("Belakang")) {
                    if (!(awalanbulan + awalantahun).equals("")) {
                        TNo.setText(NoRm.getText() + "-" + awalanbulan + awalantahun);
                    } else {
                        TNo.setText(NoRm.getText());
                    }
                }
            }
        }
    }

    public void isiKeterangan(String a) {
        keterangan = a;
    }

    public void cekValid() {
        if (((!TNoPeserta.getText().equals("0")) && (!TNoPeserta.getText().equals("-")))) {
            cekPeserta = Sequel.cariInteger("select count(-1) from pasien where no_peserta ='" + TNoPeserta.getText() + "'");

        } else {
            cekPeserta = 0;
        }

        if (((!TKtp.getText().equals("0")) && (!TKtp.getText().equals("-")))) {
            cekKTP = Sequel.cariInteger("select count(-1) from pasien where no_ktp ='" + TKtp.getText() + "'");

        } else {
            cekKTP = 0;
        }
    }

    public void fokus() {
        TCari.requestFocus();
    }

    public void setPasien(String NamaPasien, String Kontak, String Alamat,
            String TempatLahir, String TglLahir, String JK, String NoKartuJKN, String NIK) {
        this.TNm.setText(NamaPasien);
        this.TTlp.setText(Kontak);
        this.Alamat.setText(Alamat);
        this.TTmp.setText(TempatLahir);
        Valid.SetTgl(this.DTPLahir, TglLahir);
        if (JK.equals("L")) {
            this.CmbJk.setSelectedItem("LAKI-LAKI");
        } else {
            this.CmbJk.setSelectedItem("PEREMPUAN");
        }
        this.TNoPeserta.setText(NoKartuJKN);
        this.TKtp.setText(NIK);
    }
}
