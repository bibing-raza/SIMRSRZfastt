package simrskhanza;

import bridging.ApiLIS;
import keuangan.Jurnal;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;

public class DlgCariPeriksaLab extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Jurnal jur = new Jurnal();
    private Connection koneksi = koneksiDB.condb();
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private DlgPasien member = new DlgPasien(null, false);
    public DlgPasien pasien = new DlgPasien(null, false);
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private DlgMasterFaskes faskes = new DlgMasterFaskes(null, false);
    private ApiLIS mas_lis = new ApiLIS();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private int i, diagnosa_cek1 = 0, diagnosa_cek2 = 0, cekKeLIS = 0, cekdataLIS = 0, x, y;
    private PreparedStatement ps, ps2, ps3, ps4, psrekening, psLIS1, psLIS2, psLIS3;
    private ResultSet rs, rs2, rs3, rsrekening, rsLIS1, rsLIS2, rsLIS3;
    private String kamar, namakamar;
    private double ttl = 0, item = 0, cekLab;
    private double ttljmdokter = 0, ttljmpetugas = 0, ttlkso = 0, ttlpendapatan = 0, ttlbhp = 0;
    private String Suspen_Piutang_Laborat_Ranap = "", Laborat_Ranap = "", Beban_Jasa_Medik_Dokter_Laborat_Ranap = "",
            Utang_Jasa_Medik_Dokter_Laborat_Ranap = "", Beban_Jasa_Medik_Petugas_Laborat_Ranap = "", nmgedung = "",
            Utang_Jasa_Medik_Petugas_Laborat_Ranap = "", Beban_Kso_Laborat_Ranap = "", Utang_Kso_Laborat_Ranap = "",
            HPP_Persediaan_Laborat_Rawat_inap = "", Persediaan_BHP_Laborat_Rawat_Inap = "", status = "", cekDataLab = "",
            nolab = "", tglPeriksa = "", jamPeriksa = "", diagnosa_ok = "", tnorwt = "", kdunit = "", kdpenjab = "",
            status_rawat = "", cekbayar = "", drLab = "", noLIS = "", nm_unit = "", kddokter = "", notelpFaskes = "";

    /**
     * Creates new form DlgProgramStudi
     *
     * @param parent
     * @param modal
     */
    public DlgCariPeriksaLab(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No. Rawat", "No. LIS", "Pasien", "Petugas", "Tgl. Periksa", "Jam Periksa", 
            "Dokter Perujuk", "Penanggung Jawab", "Hasil"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbLab.setModel(tabMode);
        tbLab.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbLab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbLab.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else if (i == 2) {
                column.setPreferredWidth(600);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            }
        }
        tbLab.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode1 = new DefaultTableModel(null, new Object[]{"Jenis Pemeriksaan/Item", "Nilai Hasil", "Satuan", "Flag Kode",
            "Nilai Rujukan", "Waktu Selesai", "Metode Pemeriksaan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbDataLIS.setModel(tabMode1);
        tbDataLIS.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDataLIS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbDataLIS.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(230);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(120);
            }
        }
        tbDataLIS.setDefaultRenderer(Object.class, new WarnaTable());

        NoRawat.setDocument(new batasInput((byte) 17).getKata(NoRawat));
        kdmem.setDocument(new batasInput((byte) 8).getKata(kdmem));
        kdptg.setDocument(new batasInput((byte) 25).getKata(kdptg));
        TnoTelpPx.setDocument(new batasInput((byte) 15).getOnlyAngka(TnoTelpPx));
        TnoTelpDokter.setDocument(new batasInput((byte) 13).getOnlyAngka(TnoTelpDokter));
        TnoTlpnFaskes.setDocument(new batasInput((byte) 15).getOnlyAngka(TnoTlpnFaskes));    
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        
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
        
        faskes.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgCariPeriksaLab")){
                    if(faskes.getTable().getSelectedRow()!= -1){                   
                        TkdFaskes.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),1).toString());
                        TnmFaskes.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),3).toString());
                        TalamatPerujuk.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),6).toString());
                        TnoTlpnFaskes.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),7).toString());
                        btnFaskes.requestFocus();
                    }                    
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });

        member.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgCariPeriksaLab")) {
                    if (member.getTable().getSelectedRow() != -1) {
                        kdmem.setText(member.getTable().getValueAt(member.getTable().getSelectedRow(), 1).toString());
                        nmmem.setText(member.getTable().getValueAt(member.getTable().getSelectedRow(), 2).toString());
                    }
                    kdmem.requestFocus();
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

        member.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgCariPeriksaLab")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        member.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgCariPeriksaLab")) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        kdptg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        nmptg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    }
                    kdptg.requestFocus();
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

        pasien.penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgCariPeriksaLab")) {
                    if (pasien.penjab.getTable().getSelectedRow() != -1) {
                        kdpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 1).toString());
                        nmpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 2).toString());
                    }
                    kdpnj.requestFocus();
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

        pasien.penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgCariPeriksaLab")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgCariPeriksaLab")) {
                    if (poli.getTable().getSelectedRow() != -1) {
//                        if (pilihan == 1) {
                        kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                        TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
//                            switch (TStatus.getText()) {
//                                case "Baru":
//                                    TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 2).toString());
//                                    break;
//                                case "Lama":
//                                    TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 3).toString());
//                                    break;
//                                default:
//                                    TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 2).toString());
//                                    break;
//                            }
//                            isNumber();
                        kdpoli.requestFocus();
//                        } else if (pilihan == 2) {
//                            CrPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
//                            CrPoli.requestFocus();
//                            tampil();
                    }
                }
//                }
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
            ps = koneksi.prepareStatement("SELECT pl.no_rawat, IFNULL(lr.no_lab,'-') no_lab, rp.no_rkm_medis, p.nm_pasien, CONCAT(rp.umurdaftar,' ',rp.sttsumur,'.') AS umur_thn, "
                    + "pt.nama, pl.tgl_periksa, pl.jam, pl.dokter_perujuk, pl.kd_dokter, d.nm_dokter,IF(ifnull(h.no_lab,'-')='-','Belum','Sudah') hasil FROM periksa_lab pl "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=pl.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN petugas pt on pt.nip=pl.nip INNER JOIN dokter d on d.kd_dokter=pl.kd_dokter "
                    + "LEFT JOIN lis_reg lr on lr.no_rawat=pl.no_rawat and lr.tgl_periksa=pl.tgl_periksa AND lr.jam_periksa=pl.jam "
                    + "left join lis_hasil_periksa_lab h on h.no_lab = lr.no_lab WHERE "
                    + "pl.tgl_periksa BETWEEN ? AND ? AND pl.no_rawat LIKE ? AND rp.no_rkm_medis LIKE ? AND pt.nip LIKE ? AND p.nm_pasien LIKE ? OR "
                    + "pl.tgl_periksa BETWEEN ? AND ? AND pl.no_rawat LIKE ? AND rp.no_rkm_medis LIKE ? AND pt.nip LIKE ? AND pt.nama LIKE ? OR "
                    + "pl.tgl_periksa BETWEEN ? AND ? AND pl.no_rawat LIKE ? AND rp.no_rkm_medis LIKE ? AND pt.nip LIKE ? AND rp.no_rkm_medis LIKE ? OR "
                    + "pl.tgl_periksa BETWEEN ? AND ? AND pl.no_rawat LIKE ? AND rp.no_rkm_medis LIKE ? AND pt.nip LIKE ? AND pl.no_rawat LIKE ? OR "
                    + "pl.tgl_periksa BETWEEN ? AND ? AND pl.no_rawat LIKE ? AND rp.no_rkm_medis LIKE ? AND pt.nip LIKE ? AND lr.no_lab LIKE ? OR "
                    + "pl.tgl_periksa BETWEEN ? AND ? AND pl.no_rawat LIKE ? AND rp.no_rkm_medis LIKE ? AND pt.nip LIKE ? AND IF(ifnull(h.no_lab,'-')='-','Belum','Sudah') LIKE ? "
                    + "GROUP BY concat(pl.no_rawat, pl.tgl_periksa, pl.jam) order by pl.tgl_periksa desc, pl.jam desc");
            ps2 = koneksi.prepareStatement("SELECT jpl.kd_jenis_prw, jpl.nm_perawatan, pl.biaya FROM periksa_lab pl "
                    + "INNER JOIN jns_perawatan_lab jpl ON jpl.kd_jenis_prw=pl.kd_jenis_prw WHERE pl.no_rawat =? AND pl.tgl_periksa =? AND pl.jam =?");
            ps3 = koneksi.prepareStatement("SELECT tl.Pemeriksaan, dpl.nilai, tl.satuan, dpl.nilai_rujukan, dpl.biaya_item, dpl.keterangan, "
                    + "dpl.kd_jenis_prw FROM detail_periksa_lab dpl INNER JOIN template_laboratorium tl ON tl.id_template=dpl.id_template "
                    + "WHERE dpl.no_rawat =? AND dpl.kd_jenis_prw =? AND dpl.tgl_periksa =? AND dpl.jam =?");
            ps4 = koneksi.prepareStatement("SELECT pl.no_rawat, IFNULL(lr.no_lab,'-') no_lab, rp.no_rkm_medis, p.nm_pasien, p.jk, p.umur, pt.nama, "
                    + "DATE_FORMAT(pl.tgl_periksa,'%d-%m-%Y') AS tgl_periksa, pl.jam, pl.dokter_perujuk, pl.kd_dokter, p.alamat, d.nm_dokter, "
                    + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') AS lahir FROM periksa_lab pl INNER JOIN reg_periksa rp on rp.no_rawat=pl.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN petugas pt ON pt.nip=pl.nip INNER JOIN dokter d ON d.kd_dokter=pl.kd_dokter "
                    + "LEFT JOIN lis_reg lr on lr.no_rawat=pl.no_rawat and lr.tgl_periksa=pl.tgl_periksa AND lr.jam_periksa=pl.jam  "
                    + "WHERE pl.tgl_periksa =? AND pl.jam =? AND pl.no_rawat =? GROUP BY concat(pl.no_rawat, pl.tgl_periksa, pl.jam)");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            psrekening = koneksi.prepareStatement("select * from set_akun_ranap");
            try {
                rsrekening = psrekening.executeQuery();
                while (rsrekening.next()) {
                    Suspen_Piutang_Laborat_Ranap = rsrekening.getString("Suspen_Piutang_Laborat_Ranap");
                    Laborat_Ranap = rsrekening.getString("Laborat_Ranap");
                    Beban_Jasa_Medik_Dokter_Laborat_Ranap = rsrekening.getString("Beban_Jasa_Medik_Dokter_Laborat_Ranap");
                    Utang_Jasa_Medik_Dokter_Laborat_Ranap = rsrekening.getString("Utang_Jasa_Medik_Dokter_Laborat_Ranap");
                    Beban_Jasa_Medik_Petugas_Laborat_Ranap = rsrekening.getString("Beban_Jasa_Medik_Petugas_Laborat_Ranap");
                    Utang_Jasa_Medik_Petugas_Laborat_Ranap = rsrekening.getString("Utang_Jasa_Medik_Petugas_Laborat_Ranap");
                    Beban_Kso_Laborat_Ranap = rsrekening.getString("Beban_Kso_Laborat_Ranap");
                    Utang_Kso_Laborat_Ranap = rsrekening.getString("Utang_Kso_Laborat_Ranap");
                    HPP_Persediaan_Laborat_Rawat_inap = rsrekening.getString("HPP_Persediaan_Laborat_Rawat_inap");
                    Persediaan_BHP_Laborat_Rawat_Inap = rsrekening.getString("Persediaan_BHP_Laborat_Rawat_Inap");
                }
            } catch (Exception e) {
                System.out.println("Notif Rekening : " + e);
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

        try {
            psLIS1 = koneksi.prepareStatement("SELECT lhp.kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "GROUP BY lhp.kategori_pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut");

            psLIS2 = koneksi.prepareStatement("SELECT lhp.sub_kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "and lhp.kategori_pemeriksaan_nama=? GROUP BY lhp.sub_kategori_pemeriksaan_nama "
                    + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.sub_kategori_pemeriksaan_nama desc,lhp.pemeriksaan_no_urut");

            psLIS3 = koneksi.prepareStatement("SELECT lhp.pemeriksaan_nama, lhp.nilai_hasil, lhp.satuan, lhp.flag_kode, "
                    + "lhp.nilai_rujukan, DATE_FORMAT(lhdp.waktu_insert,'%d/%m/%Y - %h:%i') wkt_selesai, lhp.metode "
                    + "FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp ON lhp.no_lab = lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp ON lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "and lhp.sub_kategori_pemeriksaan_nama=? and lhp.kategori_pemeriksaan_nama=? GROUP BY lhp.pemeriksaan_nama "
                    + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut");
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
        MnLapRekapPerPasien = new javax.swing.JMenuItem();
        MnCetakNota = new javax.swing.JMenuItem();
        MnUbah = new javax.swing.JMenuItem();
        MnLihatHasilLIS = new javax.swing.JMenuItem();
        MnKirimLIS = new javax.swing.JMenuItem();
        jMnCetakHasilLab = new javax.swing.JMenu();
        MnCetakHasilLab = new javax.swing.JMenuItem();
        MnCetakHasilLab1 = new javax.swing.JMenuItem();
        MnCetakHasilLab2 = new javax.swing.JMenuItem();
        MnCetakHasilLab3 = new javax.swing.JMenuItem();
        MnCetakHasilLab4 = new javax.swing.JMenuItem();
        MnCetakHasilLab5 = new javax.swing.JMenuItem();
        MnCetakHasilLab6 = new javax.swing.JMenuItem();
        MnCetakHasilLab7 = new javax.swing.JMenuItem();
        MnCetakHasilLab8 = new javax.swing.JMenuItem();
        MnCetakHasilLab9 = new javax.swing.JMenuItem();
        MnCetakHasilLab10 = new javax.swing.JMenuItem();
        jMnLapInap = new javax.swing.JMenu();
        MnInapSemuaCaraBayar = new javax.swing.JMenuItem();
        MnInapDetailSemuaCaraBayar = new javax.swing.JMenuItem();
        MnInapPerCaraBayar = new javax.swing.JMenuItem();
        MnInapDetailPerCaraBayar = new javax.swing.JMenuItem();
        jMnLapJalan = new javax.swing.JMenu();
        MnJalanSemuaCaraBayar = new javax.swing.JMenuItem();
        MnJalanDetailSemuaCaraBayar = new javax.swing.JMenuItem();
        MnJalanPerCaraBayar = new javax.swing.JMenuItem();
        MnJalanDetailPerCaraBayar = new javax.swing.JMenuItem();
        MnJalanPerPoli = new javax.swing.JMenuItem();
        MnJalanDetailPerPoli = new javax.swing.JMenuItem();
        jMnLapSemuaRawat = new javax.swing.JMenu();
        MnSRSemuaCaraBayar = new javax.swing.JMenuItem();
        MnSRDetailSemuaCaraBayar = new javax.swing.JMenuItem();
        MnSRPerCaraBayar = new javax.swing.JMenuItem();
        MnSRDetailPerCaraBayar = new javax.swing.JMenuItem();
        jMnLapPemeriksaan = new javax.swing.JMenu();
        MnPemeriksaanInap = new javax.swing.JMenuItem();
        MnPemeriksaanJalan = new javax.swing.JMenuItem();
        MnPemeriksaanSemua = new javax.swing.JMenuItem();
        jMnLapPendapatan = new javax.swing.JMenu();
        MnPendapatanRalan = new javax.swing.JMenuItem();
        MnPendapatanRanap = new javax.swing.JMenuItem();
        MnPendapatanSemuaRawat = new javax.swing.JMenuItem();
        WindowDataLIS = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        internalFrame6 = new widget.InternalFrame();
        jLabel23 = new widget.Label();
        dokter_pengirim = new widget.TextBox();
        jLabel22 = new widget.Label();
        kklinis = new widget.TextBox();
        dAwal = new widget.TextBox();
        jLabel21 = new widget.Label();
        jLabel20 = new widget.Label();
        dataPasien = new widget.TextBox();
        jLabel18 = new widget.Label();
        nRawat = new widget.TextBox();
        jLabel19 = new widget.Label();
        nLab = new widget.TextBox();
        jLabel24 = new widget.Label();
        caraByr = new widget.TextBox();
        Scroll1 = new widget.ScrollPane();
        tbDataLIS = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnPrint = new widget.Button();
        BtnCloseIn4 = new widget.Button();
        WindowKirimLIS = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        jLabel13 = new widget.Label();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel14 = new widget.Label();
        TtglPeriksa = new widget.TextBox();
        jLabel15 = new widget.Label();
        TjamPeriksa = new widget.TextBox();
        jLabel16 = new widget.Label();
        Tdiagnosa = new widget.TextBox();
        jLabel17 = new widget.Label();
        TketKlinis = new widget.TextBox();
        jLabel26 = new widget.Label();
        TkdDokter = new widget.TextBox();
        TnmDokter = new widget.TextBox();
        jLabel27 = new widget.Label();
        TkdUnit = new widget.TextBox();
        TnmUnit = new widget.TextBox();
        jLabel28 = new widget.Label();
        TkdPenjab = new widget.TextBox();
        TnmPenjab = new widget.TextBox();
        jLabel29 = new widget.Label();
        TnoTelpDokter = new widget.TextBox();
        jLabel30 = new widget.Label();
        TkdFaskes = new widget.TextBox();
        TnmFaskes = new widget.TextBox();
        btnFaskes = new widget.Button();
        jLabel31 = new widget.Label();
        TnoTlpnFaskes = new widget.TextBox();
        jLabel32 = new widget.Label();
        TalamatPerujuk = new widget.TextBox();
        Kd2 = new widget.TextBox();
        jLabel33 = new widget.Label();
        TnoTelpPx = new widget.TextBox();
        panelisi5 = new widget.panelisi();
        BtnKirim = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbLab = new widget.Table();
        panelisi3 = new widget.panelisi();
        label15 = new widget.Label();
        NoRawat = new widget.TextBox();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label16 = new widget.Label();
        label13 = new widget.Label();
        kdmem = new widget.TextBox();
        kdptg = new widget.TextBox();
        nmmem = new widget.TextBox();
        nmptg = new widget.TextBox();
        btnPasien = new widget.Button();
        btnPetugas = new widget.Button();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        kdpnj = new widget.TextBox();
        nmpnj = new widget.TextBox();
        btnPenjab = new widget.Button();
        label17 = new widget.Label();
        label14 = new widget.Label();
        kdpoli = new widget.TextBox();
        TPoli = new widget.TextBox();
        BtnUnit = new widget.Button();
        BtnBersih = new widget.Button();
        label12 = new widget.Label();
        ketklinis = new widget.TextBox();
        jLabel3 = new widget.Label();
        ketAktif = new widget.Label();
        panelisi1 = new widget.panelisi();
        label10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        label9 = new widget.Label();
        BtnHapus = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel25 = new widget.Label();
        tglNota = new widget.Tanggal();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnLapRekapPerPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLapRekapPerPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLapRekapPerPasien.setText("Rekap Pemeriksaan PerPasien");
        MnLapRekapPerPasien.setName("MnLapRekapPerPasien"); // NOI18N
        MnLapRekapPerPasien.setPreferredSize(new java.awt.Dimension(250, 28));
        MnLapRekapPerPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLapRekapPerPasienActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnLapRekapPerPasien);

        MnCetakNota.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakNota.setText("Cetak Nota Lab");
        MnCetakNota.setName("MnCetakNota"); // NOI18N
        MnCetakNota.setPreferredSize(new java.awt.Dimension(250, 28));
        MnCetakNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakNotaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakNota);

        MnUbah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUbah.setText("Ubah Periksa Lab");
        MnUbah.setEnabled(false);
        MnUbah.setName("MnUbah"); // NOI18N
        MnUbah.setPreferredSize(new java.awt.Dimension(250, 28));
        MnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUbahActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnUbah);

        MnLihatHasilLIS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLihatHasilLIS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLihatHasilLIS.setText("Lihat Hasil Pemeriksaan (LIS)");
        MnLihatHasilLIS.setName("MnLihatHasilLIS"); // NOI18N
        MnLihatHasilLIS.setPreferredSize(new java.awt.Dimension(250, 28));
        MnLihatHasilLIS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLihatHasilLISActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnLihatHasilLIS);

        MnKirimLIS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKirimLIS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKirimLIS.setText("Kirim Ke Alat Pemeriksaan (LIS)");
        MnKirimLIS.setName("MnKirimLIS"); // NOI18N
        MnKirimLIS.setPreferredSize(new java.awt.Dimension(250, 28));
        MnKirimLIS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKirimLISActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKirimLIS);

        jMnCetakHasilLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMnCetakHasilLab.setText("Cetak Hasil Laboratorium");
        jMnCetakHasilLab.setEnabled(false);
        jMnCetakHasilLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnCetakHasilLab.setName("jMnCetakHasilLab"); // NOI18N
        jMnCetakHasilLab.setPreferredSize(new java.awt.Dimension(250, 28));

        MnCetakHasilLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakHasilLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakHasilLab.setText("Cetak Hasil Lab Model 1");
        MnCetakHasilLab.setName("MnCetakHasilLab"); // NOI18N
        MnCetakHasilLab.setPreferredSize(new java.awt.Dimension(200, 28));
        MnCetakHasilLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakHasilLabActionPerformed(evt);
            }
        });
        jMnCetakHasilLab.add(MnCetakHasilLab);

        MnCetakHasilLab1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakHasilLab1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakHasilLab1.setText("Cetak Hasil Lab Model 2");
        MnCetakHasilLab1.setName("MnCetakHasilLab1"); // NOI18N
        MnCetakHasilLab1.setPreferredSize(new java.awt.Dimension(200, 28));
        MnCetakHasilLab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakHasilLab1ActionPerformed(evt);
            }
        });
        jMnCetakHasilLab.add(MnCetakHasilLab1);

        MnCetakHasilLab2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakHasilLab2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakHasilLab2.setText("Cetak Hasil Lab Model 3");
        MnCetakHasilLab2.setName("MnCetakHasilLab2"); // NOI18N
        MnCetakHasilLab2.setPreferredSize(new java.awt.Dimension(200, 28));
        MnCetakHasilLab2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakHasilLab2ActionPerformed(evt);
            }
        });
        jMnCetakHasilLab.add(MnCetakHasilLab2);

        MnCetakHasilLab3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakHasilLab3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakHasilLab3.setText("Cetak Hasil Lab Model 4");
        MnCetakHasilLab3.setName("MnCetakHasilLab3"); // NOI18N
        MnCetakHasilLab3.setPreferredSize(new java.awt.Dimension(200, 28));
        MnCetakHasilLab3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakHasilLab3ActionPerformed(evt);
            }
        });
        jMnCetakHasilLab.add(MnCetakHasilLab3);

        MnCetakHasilLab4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakHasilLab4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakHasilLab4.setText("Cetak Hasil Lab Model 5");
        MnCetakHasilLab4.setName("MnCetakHasilLab4"); // NOI18N
        MnCetakHasilLab4.setPreferredSize(new java.awt.Dimension(200, 28));
        MnCetakHasilLab4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakHasilLab4ActionPerformed(evt);
            }
        });
        jMnCetakHasilLab.add(MnCetakHasilLab4);

        MnCetakHasilLab5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakHasilLab5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakHasilLab5.setText("Cetak Hasil Lab Model 6");
        MnCetakHasilLab5.setName("MnCetakHasilLab5"); // NOI18N
        MnCetakHasilLab5.setPreferredSize(new java.awt.Dimension(200, 28));
        MnCetakHasilLab5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakHasilLab5ActionPerformed(evt);
            }
        });
        jMnCetakHasilLab.add(MnCetakHasilLab5);

        MnCetakHasilLab6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakHasilLab6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakHasilLab6.setText("Cetak Hasil Lab Model 7");
        MnCetakHasilLab6.setName("MnCetakHasilLab6"); // NOI18N
        MnCetakHasilLab6.setPreferredSize(new java.awt.Dimension(200, 28));
        MnCetakHasilLab6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakHasilLab6ActionPerformed(evt);
            }
        });
        jMnCetakHasilLab.add(MnCetakHasilLab6);

        MnCetakHasilLab7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakHasilLab7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakHasilLab7.setText("Cetak Hasil Lab Model 8");
        MnCetakHasilLab7.setName("MnCetakHasilLab7"); // NOI18N
        MnCetakHasilLab7.setPreferredSize(new java.awt.Dimension(200, 28));
        MnCetakHasilLab7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakHasilLab7ActionPerformed(evt);
            }
        });
        jMnCetakHasilLab.add(MnCetakHasilLab7);

        MnCetakHasilLab8.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakHasilLab8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakHasilLab8.setText("Cetak Hasil Lab Model 9");
        MnCetakHasilLab8.setName("MnCetakHasilLab8"); // NOI18N
        MnCetakHasilLab8.setPreferredSize(new java.awt.Dimension(200, 28));
        MnCetakHasilLab8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakHasilLab8ActionPerformed(evt);
            }
        });
        jMnCetakHasilLab.add(MnCetakHasilLab8);

        MnCetakHasilLab9.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakHasilLab9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakHasilLab9.setText("Cetak Hasil Lab Model 10");
        MnCetakHasilLab9.setName("MnCetakHasilLab9"); // NOI18N
        MnCetakHasilLab9.setPreferredSize(new java.awt.Dimension(200, 28));
        MnCetakHasilLab9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakHasilLab9ActionPerformed(evt);
            }
        });
        jMnCetakHasilLab.add(MnCetakHasilLab9);

        MnCetakHasilLab10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakHasilLab10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakHasilLab10.setText("Cetak Hasil Lab Model 11");
        MnCetakHasilLab10.setName("MnCetakHasilLab10"); // NOI18N
        MnCetakHasilLab10.setPreferredSize(new java.awt.Dimension(200, 28));
        MnCetakHasilLab10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakHasilLab10ActionPerformed(evt);
            }
        });
        jMnCetakHasilLab.add(MnCetakHasilLab10);

        jPopupMenu1.add(jMnCetakHasilLab);

        jMnLapInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMnLapInap.setText("Lap. Transaksi Lab. Rawat Inap");
        jMnLapInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnLapInap.setName("jMnLapInap"); // NOI18N
        jMnLapInap.setPreferredSize(new java.awt.Dimension(250, 28));

        MnInapSemuaCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInapSemuaCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnInapSemuaCaraBayar.setText("Rekap Total Pemrk. Semua Cara Bayar");
        MnInapSemuaCaraBayar.setName("MnInapSemuaCaraBayar"); // NOI18N
        MnInapSemuaCaraBayar.setPreferredSize(new java.awt.Dimension(231, 28));
        MnInapSemuaCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInapSemuaCaraBayarActionPerformed(evt);
            }
        });
        jMnLapInap.add(MnInapSemuaCaraBayar);

        MnInapDetailSemuaCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInapDetailSemuaCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnInapDetailSemuaCaraBayar.setText("Rekap Detail Pemrk. Semua Cara Bayar");
        MnInapDetailSemuaCaraBayar.setName("MnInapDetailSemuaCaraBayar"); // NOI18N
        MnInapDetailSemuaCaraBayar.setPreferredSize(new java.awt.Dimension(231, 28));
        MnInapDetailSemuaCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInapDetailSemuaCaraBayarActionPerformed(evt);
            }
        });
        jMnLapInap.add(MnInapDetailSemuaCaraBayar);

        MnInapPerCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInapPerCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnInapPerCaraBayar.setText("Rekap Total Pemrk. Per Cara Bayar");
        MnInapPerCaraBayar.setName("MnInapPerCaraBayar"); // NOI18N
        MnInapPerCaraBayar.setPreferredSize(new java.awt.Dimension(231, 28));
        MnInapPerCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInapPerCaraBayarActionPerformed(evt);
            }
        });
        jMnLapInap.add(MnInapPerCaraBayar);

        MnInapDetailPerCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInapDetailPerCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnInapDetailPerCaraBayar.setText("Rekap Detail Pemrk. Per Cara Bayar");
        MnInapDetailPerCaraBayar.setName("MnInapDetailPerCaraBayar"); // NOI18N
        MnInapDetailPerCaraBayar.setPreferredSize(new java.awt.Dimension(231, 28));
        MnInapDetailPerCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInapDetailPerCaraBayarActionPerformed(evt);
            }
        });
        jMnLapInap.add(MnInapDetailPerCaraBayar);

        jPopupMenu1.add(jMnLapInap);

        jMnLapJalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMnLapJalan.setText("Lap. Transaksi Lab. Rawat Jalan");
        jMnLapJalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnLapJalan.setName("jMnLapJalan"); // NOI18N
        jMnLapJalan.setPreferredSize(new java.awt.Dimension(250, 28));

        MnJalanSemuaCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJalanSemuaCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnJalanSemuaCaraBayar.setText("Rekap Total Pemrk. Semua Cara Bayar");
        MnJalanSemuaCaraBayar.setName("MnJalanSemuaCaraBayar"); // NOI18N
        MnJalanSemuaCaraBayar.setPreferredSize(new java.awt.Dimension(229, 28));
        MnJalanSemuaCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJalanSemuaCaraBayarActionPerformed(evt);
            }
        });
        jMnLapJalan.add(MnJalanSemuaCaraBayar);

        MnJalanDetailSemuaCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJalanDetailSemuaCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnJalanDetailSemuaCaraBayar.setText("Rekap Detail Pemrk. Semua Cara Bayar");
        MnJalanDetailSemuaCaraBayar.setName("MnJalanDetailSemuaCaraBayar"); // NOI18N
        MnJalanDetailSemuaCaraBayar.setPreferredSize(new java.awt.Dimension(229, 28));
        MnJalanDetailSemuaCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJalanDetailSemuaCaraBayarActionPerformed(evt);
            }
        });
        jMnLapJalan.add(MnJalanDetailSemuaCaraBayar);

        MnJalanPerCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJalanPerCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnJalanPerCaraBayar.setText("Rekap Total Pemrk. Per Cara Bayar");
        MnJalanPerCaraBayar.setName("MnJalanPerCaraBayar"); // NOI18N
        MnJalanPerCaraBayar.setPreferredSize(new java.awt.Dimension(229, 28));
        MnJalanPerCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJalanPerCaraBayarActionPerformed(evt);
            }
        });
        jMnLapJalan.add(MnJalanPerCaraBayar);

        MnJalanDetailPerCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJalanDetailPerCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnJalanDetailPerCaraBayar.setText("Rekap Detail Pemrk. Per Cara Bayar");
        MnJalanDetailPerCaraBayar.setName("MnJalanDetailPerCaraBayar"); // NOI18N
        MnJalanDetailPerCaraBayar.setPreferredSize(new java.awt.Dimension(229, 28));
        MnJalanDetailPerCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJalanDetailPerCaraBayarActionPerformed(evt);
            }
        });
        jMnLapJalan.add(MnJalanDetailPerCaraBayar);

        MnJalanPerPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJalanPerPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnJalanPerPoli.setText("Rekap Total Pemrk. Per Unit/Poli");
        MnJalanPerPoli.setName("MnJalanPerPoli"); // NOI18N
        MnJalanPerPoli.setPreferredSize(new java.awt.Dimension(229, 28));
        MnJalanPerPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJalanPerPoliActionPerformed(evt);
            }
        });
        jMnLapJalan.add(MnJalanPerPoli);

        MnJalanDetailPerPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJalanDetailPerPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnJalanDetailPerPoli.setText("Rekap Detail Pemrk. Per Unit/Poli");
        MnJalanDetailPerPoli.setName("MnJalanDetailPerPoli"); // NOI18N
        MnJalanDetailPerPoli.setPreferredSize(new java.awt.Dimension(229, 28));
        MnJalanDetailPerPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJalanDetailPerPoliActionPerformed(evt);
            }
        });
        jMnLapJalan.add(MnJalanDetailPerPoli);

        jPopupMenu1.add(jMnLapJalan);

        jMnLapSemuaRawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMnLapSemuaRawat.setText("Lap. Transaksi Lab. Semua Rawat");
        jMnLapSemuaRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnLapSemuaRawat.setName("jMnLapSemuaRawat"); // NOI18N
        jMnLapSemuaRawat.setPreferredSize(new java.awt.Dimension(250, 28));

        MnSRSemuaCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSRSemuaCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnSRSemuaCaraBayar.setText("Rekap Total Pemrk. Semua Cara Bayar");
        MnSRSemuaCaraBayar.setName("MnSRSemuaCaraBayar"); // NOI18N
        MnSRSemuaCaraBayar.setPreferredSize(new java.awt.Dimension(229, 28));
        MnSRSemuaCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSRSemuaCaraBayarActionPerformed(evt);
            }
        });
        jMnLapSemuaRawat.add(MnSRSemuaCaraBayar);

        MnSRDetailSemuaCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSRDetailSemuaCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnSRDetailSemuaCaraBayar.setText("Rekap Detail Pemrk. Semua Cara Bayar");
        MnSRDetailSemuaCaraBayar.setName("MnSRDetailSemuaCaraBayar"); // NOI18N
        MnSRDetailSemuaCaraBayar.setPreferredSize(new java.awt.Dimension(229, 28));
        MnSRDetailSemuaCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSRDetailSemuaCaraBayarActionPerformed(evt);
            }
        });
        jMnLapSemuaRawat.add(MnSRDetailSemuaCaraBayar);

        MnSRPerCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSRPerCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnSRPerCaraBayar.setText("Rekap Total Pemrk. Per Cara Bayar");
        MnSRPerCaraBayar.setName("MnSRPerCaraBayar"); // NOI18N
        MnSRPerCaraBayar.setPreferredSize(new java.awt.Dimension(229, 28));
        MnSRPerCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSRPerCaraBayarActionPerformed(evt);
            }
        });
        jMnLapSemuaRawat.add(MnSRPerCaraBayar);

        MnSRDetailPerCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSRDetailPerCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnSRDetailPerCaraBayar.setText("Rekap Detail Pemrk. Per Cara Bayar");
        MnSRDetailPerCaraBayar.setName("MnSRDetailPerCaraBayar"); // NOI18N
        MnSRDetailPerCaraBayar.setPreferredSize(new java.awt.Dimension(229, 28));
        MnSRDetailPerCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSRDetailPerCaraBayarActionPerformed(evt);
            }
        });
        jMnLapSemuaRawat.add(MnSRDetailPerCaraBayar);

        jPopupMenu1.add(jMnLapSemuaRawat);

        jMnLapPemeriksaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMnLapPemeriksaan.setText("Lap. Jns. Pmriksn. Lab. PerCara Bayar");
        jMnLapPemeriksaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnLapPemeriksaan.setName("jMnLapPemeriksaan"); // NOI18N
        jMnLapPemeriksaan.setPreferredSize(new java.awt.Dimension(250, 28));

        MnPemeriksaanInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPemeriksaanInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPemeriksaanInap.setText("Pemeriksaan Rawat Inap");
        MnPemeriksaanInap.setName("MnPemeriksaanInap"); // NOI18N
        MnPemeriksaanInap.setPreferredSize(new java.awt.Dimension(180, 28));
        MnPemeriksaanInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemeriksaanInapActionPerformed(evt);
            }
        });
        jMnLapPemeriksaan.add(MnPemeriksaanInap);

        MnPemeriksaanJalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPemeriksaanJalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPemeriksaanJalan.setText("Pemeriksaan Rawat Jalan");
        MnPemeriksaanJalan.setName("MnPemeriksaanJalan"); // NOI18N
        MnPemeriksaanJalan.setPreferredSize(new java.awt.Dimension(180, 28));
        MnPemeriksaanJalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemeriksaanJalanActionPerformed(evt);
            }
        });
        jMnLapPemeriksaan.add(MnPemeriksaanJalan);

        MnPemeriksaanSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPemeriksaanSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPemeriksaanSemua.setText("Pemeriksaan Semua Rawat");
        MnPemeriksaanSemua.setName("MnPemeriksaanSemua"); // NOI18N
        MnPemeriksaanSemua.setPreferredSize(new java.awt.Dimension(180, 28));
        MnPemeriksaanSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemeriksaanSemuaActionPerformed(evt);
            }
        });
        jMnLapPemeriksaan.add(MnPemeriksaanSemua);

        jPopupMenu1.add(jMnLapPemeriksaan);

        jMnLapPendapatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMnLapPendapatan.setText("Lap. Rekap Pendapatan Lab.");
        jMnLapPendapatan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnLapPendapatan.setName("jMnLapPendapatan"); // NOI18N
        jMnLapPendapatan.setPreferredSize(new java.awt.Dimension(250, 28));

        MnPendapatanRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPendapatanRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPendapatanRalan.setText("Rekap Pendapatan Rawat Jalan");
        MnPendapatanRalan.setName("MnPendapatanRalan"); // NOI18N
        MnPendapatanRalan.setPreferredSize(new java.awt.Dimension(223, 28));
        MnPendapatanRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPendapatanRalanActionPerformed(evt);
            }
        });
        jMnLapPendapatan.add(MnPendapatanRalan);

        MnPendapatanRanap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPendapatanRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPendapatanRanap.setText("Rekap Pendapatan Rawat Inap");
        MnPendapatanRanap.setName("MnPendapatanRanap"); // NOI18N
        MnPendapatanRanap.setPreferredSize(new java.awt.Dimension(223, 28));
        MnPendapatanRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPendapatanRanapActionPerformed(evt);
            }
        });
        jMnLapPendapatan.add(MnPendapatanRanap);

        MnPendapatanSemuaRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPendapatanSemuaRawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPendapatanSemuaRawat.setText("Rekap Pendapatan Semua Rawat");
        MnPendapatanSemuaRawat.setName("MnPendapatanSemuaRawat"); // NOI18N
        MnPendapatanSemuaRawat.setPreferredSize(new java.awt.Dimension(223, 28));
        MnPendapatanSemuaRawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPendapatanSemuaRawatActionPerformed(evt);
            }
        });
        jMnLapPendapatan.add(MnPendapatanSemuaRawat);

        jPopupMenu1.add(jMnLapPendapatan);

        WindowDataLIS.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDataLIS.setName("WindowDataLIS"); // NOI18N
        WindowDataLIS.setUndecorated(true);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Hasil Pemeriksaan LIS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setPreferredSize(new java.awt.Dimension(0, 500));
        internalFrame7.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame7.setLayout(new java.awt.BorderLayout());

        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setPreferredSize(new java.awt.Dimension(0, 160));
        internalFrame6.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame6.setLayout(null);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Dokter Pengirim :");
        jLabel23.setName("jLabel23"); // NOI18N
        internalFrame6.add(jLabel23);
        jLabel23.setBounds(0, 122, 100, 23);

        dokter_pengirim.setEditable(false);
        dokter_pengirim.setForeground(new java.awt.Color(0, 0, 0));
        dokter_pengirim.setName("dokter_pengirim"); // NOI18N
        internalFrame6.add(dokter_pengirim);
        dokter_pengirim.setBounds(105, 122, 660, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Ket. Klinis :");
        jLabel22.setName("jLabel22"); // NOI18N
        internalFrame6.add(jLabel22);
        jLabel22.setBounds(0, 94, 100, 23);

        kklinis.setEditable(false);
        kklinis.setForeground(new java.awt.Color(0, 0, 0));
        kklinis.setName("kklinis"); // NOI18N
        internalFrame6.add(kklinis);
        kklinis.setBounds(105, 94, 660, 23);

        dAwal.setEditable(false);
        dAwal.setForeground(new java.awt.Color(0, 0, 0));
        dAwal.setName("dAwal"); // NOI18N
        internalFrame6.add(dAwal);
        dAwal.setBounds(105, 66, 660, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Diagnosa Awal :");
        jLabel21.setName("jLabel21"); // NOI18N
        internalFrame6.add(jLabel21);
        jLabel21.setBounds(0, 66, 100, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Pasien :");
        jLabel20.setName("jLabel20"); // NOI18N
        internalFrame6.add(jLabel20);
        jLabel20.setBounds(0, 38, 100, 23);

        dataPasien.setEditable(false);
        dataPasien.setForeground(new java.awt.Color(0, 0, 0));
        dataPasien.setName("dataPasien"); // NOI18N
        internalFrame6.add(dataPasien);
        dataPasien.setBounds(105, 38, 660, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("No. Rawat :");
        jLabel18.setName("jLabel18"); // NOI18N
        internalFrame6.add(jLabel18);
        jLabel18.setBounds(0, 10, 100, 23);

        nRawat.setEditable(false);
        nRawat.setForeground(new java.awt.Color(0, 0, 0));
        nRawat.setHighlighter(null);
        nRawat.setName("nRawat"); // NOI18N
        nRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nRawatKeyPressed(evt);
            }
        });
        internalFrame6.add(nRawat);
        nRawat.setBounds(105, 10, 120, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("No. Lab. :");
        jLabel19.setName("jLabel19"); // NOI18N
        internalFrame6.add(jLabel19);
        jLabel19.setBounds(230, 10, 50, 23);

        nLab.setEditable(false);
        nLab.setForeground(new java.awt.Color(0, 0, 0));
        nLab.setHighlighter(null);
        nLab.setName("nLab"); // NOI18N
        nLab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nLabKeyPressed(evt);
            }
        });
        internalFrame6.add(nLab);
        nLab.setBounds(285, 10, 100, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Cara Bayar :");
        jLabel24.setName("jLabel24"); // NOI18N
        internalFrame6.add(jLabel24);
        jLabel24.setBounds(390, 10, 70, 23);

        caraByr.setEditable(false);
        caraByr.setForeground(new java.awt.Color(0, 0, 0));
        caraByr.setName("caraByr"); // NOI18N
        internalFrame6.add(caraByr);
        caraByr.setBounds(465, 10, 300, 23);

        internalFrame7.add(internalFrame6, java.awt.BorderLayout.PAGE_START);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 502));

        tbDataLIS.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDataLIS.setName("tbDataLIS"); // NOI18N
        Scroll1.setViewportView(tbDataLIS);

        internalFrame7.add(Scroll1, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 45));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak Hasil Periksa Lab.");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(190, 26));
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

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.setPreferredSize(new java.awt.Dimension(90, 26));
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnCloseIn4);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        internalFrame7.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        WindowDataLIS.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        WindowKirimLIS.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowKirimLIS.setName("WindowKirimLIS"); // NOI18N
        WindowKirimLIS.setUndecorated(true);
        WindowKirimLIS.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Kirim Ke Alat Pemeriksaan (LIS) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 270));
        panelisi4.setLayout(null);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("No. Rawat : ");
        jLabel13.setName("jLabel13"); // NOI18N
        panelisi4.add(jLabel13);
        jLabel13.setBounds(0, 10, 115, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        panelisi4.add(TNoRM);
        TNoRM.setBounds(240, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        panelisi4.add(TPasien);
        TPasien.setBounds(313, 10, 250, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tgl. Periksa : ");
        jLabel14.setName("jLabel14"); // NOI18N
        panelisi4.add(jLabel14);
        jLabel14.setBounds(0, 38, 115, 23);

        TtglPeriksa.setEditable(false);
        TtglPeriksa.setForeground(new java.awt.Color(0, 0, 0));
        TtglPeriksa.setName("TtglPeriksa"); // NOI18N
        panelisi4.add(TtglPeriksa);
        TtglPeriksa.setBounds(115, 38, 80, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Jam Periksa : ");
        jLabel15.setName("jLabel15"); // NOI18N
        panelisi4.add(jLabel15);
        jLabel15.setBounds(197, 38, 80, 23);

        TjamPeriksa.setEditable(false);
        TjamPeriksa.setForeground(new java.awt.Color(0, 0, 0));
        TjamPeriksa.setName("TjamPeriksa"); // NOI18N
        panelisi4.add(TjamPeriksa);
        TjamPeriksa.setBounds(279, 38, 70, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Diagnosa : ");
        jLabel16.setName("jLabel16"); // NOI18N
        panelisi4.add(jLabel16);
        jLabel16.setBounds(0, 66, 115, 23);

        Tdiagnosa.setEditable(false);
        Tdiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        Tdiagnosa.setName("Tdiagnosa"); // NOI18N
        panelisi4.add(Tdiagnosa);
        Tdiagnosa.setBounds(115, 66, 448, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Ket. Klinis : ");
        jLabel17.setName("jLabel17"); // NOI18N
        panelisi4.add(jLabel17);
        jLabel17.setBounds(0, 94, 115, 23);

        TketKlinis.setEditable(false);
        TketKlinis.setForeground(new java.awt.Color(0, 0, 0));
        TketKlinis.setName("TketKlinis"); // NOI18N
        panelisi4.add(TketKlinis);
        TketKlinis.setBounds(115, 94, 448, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Dokter Perujuk : ");
        jLabel26.setName("jLabel26"); // NOI18N
        panelisi4.add(jLabel26);
        jLabel26.setBounds(0, 122, 115, 23);

        TkdDokter.setEditable(false);
        TkdDokter.setForeground(new java.awt.Color(0, 0, 0));
        TkdDokter.setName("TkdDokter"); // NOI18N
        panelisi4.add(TkdDokter);
        TkdDokter.setBounds(115, 122, 81, 23);

        TnmDokter.setEditable(false);
        TnmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter.setName("TnmDokter"); // NOI18N
        panelisi4.add(TnmDokter);
        TnmDokter.setBounds(199, 122, 364, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Poli/Rg. Rawat : ");
        jLabel27.setName("jLabel27"); // NOI18N
        panelisi4.add(jLabel27);
        jLabel27.setBounds(0, 178, 115, 23);

        TkdUnit.setEditable(false);
        TkdUnit.setForeground(new java.awt.Color(0, 0, 0));
        TkdUnit.setName("TkdUnit"); // NOI18N
        panelisi4.add(TkdUnit);
        TkdUnit.setBounds(115, 178, 81, 23);

        TnmUnit.setEditable(false);
        TnmUnit.setForeground(new java.awt.Color(0, 0, 0));
        TnmUnit.setName("TnmUnit"); // NOI18N
        panelisi4.add(TnmUnit);
        TnmUnit.setBounds(199, 178, 364, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Cara Bayar : ");
        jLabel28.setName("jLabel28"); // NOI18N
        panelisi4.add(jLabel28);
        jLabel28.setBounds(237, 150, 70, 23);

        TkdPenjab.setEditable(false);
        TkdPenjab.setForeground(new java.awt.Color(0, 0, 0));
        TkdPenjab.setName("TkdPenjab"); // NOI18N
        panelisi4.add(TkdPenjab);
        TkdPenjab.setBounds(310, 150, 50, 23);

        TnmPenjab.setEditable(false);
        TnmPenjab.setForeground(new java.awt.Color(0, 0, 0));
        TnmPenjab.setName("TnmPenjab"); // NOI18N
        panelisi4.add(TnmPenjab);
        TnmPenjab.setBounds(363, 150, 200, 23);

        jLabel29.setForeground(new java.awt.Color(255, 0, 51));
        jLabel29.setText("No. Telp. Dokter : ");
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N
        panelisi4.add(jLabel29);
        jLabel29.setBounds(0, 150, 115, 23);

        TnoTelpDokter.setForeground(new java.awt.Color(255, 0, 51));
        TnoTelpDokter.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TnoTelpDokter.setName("TnoTelpDokter"); // NOI18N
        panelisi4.add(TnoTelpDokter);
        TnoTelpDokter.setBounds(115, 150, 122, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Faskes Perujuk : ");
        jLabel30.setName("jLabel30"); // NOI18N
        panelisi4.add(jLabel30);
        jLabel30.setBounds(0, 206, 115, 23);

        TkdFaskes.setEditable(false);
        TkdFaskes.setForeground(new java.awt.Color(0, 0, 0));
        TkdFaskes.setName("TkdFaskes"); // NOI18N
        panelisi4.add(TkdFaskes);
        TkdFaskes.setBounds(115, 206, 100, 23);

        TnmFaskes.setEditable(false);
        TnmFaskes.setForeground(new java.awt.Color(0, 0, 0));
        TnmFaskes.setName("TnmFaskes"); // NOI18N
        panelisi4.add(TnmFaskes);
        TnmFaskes.setBounds(218, 206, 310, 23);

        btnFaskes.setForeground(new java.awt.Color(0, 0, 0));
        btnFaskes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnFaskes.setMnemonic('4');
        btnFaskes.setToolTipText("ALt+4");
        btnFaskes.setName("btnFaskes"); // NOI18N
        btnFaskes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFaskesActionPerformed(evt);
            }
        });
        panelisi4.add(btnFaskes);
        btnFaskes.setBounds(530, 206, 28, 23);

        jLabel31.setForeground(new java.awt.Color(153, 0, 153));
        jLabel31.setText("No. Tlpn. Perujuk : ");
        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setName("jLabel31"); // NOI18N
        panelisi4.add(jLabel31);
        jLabel31.setBounds(0, 234, 115, 23);

        TnoTlpnFaskes.setForeground(new java.awt.Color(153, 0, 153));
        TnoTlpnFaskes.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TnoTlpnFaskes.setName("TnoTlpnFaskes"); // NOI18N
        panelisi4.add(TnoTlpnFaskes);
        TnoTlpnFaskes.setBounds(115, 234, 120, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Alamat Perujuk : ");
        jLabel32.setName("jLabel32"); // NOI18N
        panelisi4.add(jLabel32);
        jLabel32.setBounds(236, 234, 95, 23);

        TalamatPerujuk.setForeground(new java.awt.Color(0, 0, 0));
        TalamatPerujuk.setName("TalamatPerujuk"); // NOI18N
        panelisi4.add(TalamatPerujuk);
        TalamatPerujuk.setBounds(333, 234, 230, 23);

        Kd2.setEditable(false);
        Kd2.setForeground(new java.awt.Color(0, 0, 0));
        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi4.add(Kd2);
        Kd2.setBounds(115, 10, 122, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 204));
        jLabel33.setText("No. Telp. Pasien : ");
        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setName("jLabel33"); // NOI18N
        panelisi4.add(jLabel33);
        jLabel33.setBounds(352, 38, 100, 23);

        TnoTelpPx.setForeground(new java.awt.Color(0, 0, 204));
        TnoTelpPx.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TnoTelpPx.setName("TnoTelpPx"); // NOI18N
        panelisi4.add(TnoTelpPx);
        TnoTelpPx.setBounds(454, 38, 109, 23);

        internalFrame3.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        panelisi5.setBackground(new java.awt.Color(255, 150, 255));
        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 4, 9));

        BtnKirim.setForeground(new java.awt.Color(0, 0, 0));
        BtnKirim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnKirim.setMnemonic('S');
        BtnKirim.setText("Kirim Data Ke LIS");
        BtnKirim.setToolTipText("Alt+S");
        BtnKirim.setName("BtnKirim"); // NOI18N
        BtnKirim.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnKirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKirimActionPerformed(evt);
            }
        });
        panelisi5.add(BtnKirim);

        BtnCloseIn1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn1.setMnemonic('U');
        BtnCloseIn1.setText("Tutup");
        BtnCloseIn1.setToolTipText("Alt+U");
        BtnCloseIn1.setName("BtnCloseIn1"); // NOI18N
        BtnCloseIn1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn1ActionPerformed(evt);
            }
        });
        panelisi5.add(BtnCloseIn1);

        internalFrame3.add(panelisi5, java.awt.BorderLayout.CENTER);

        WindowKirimLIS.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Pemeriksaan Laboratorium ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbLab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbLab.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbLab.setComponentPopupMenu(jPopupMenu1);
        tbLab.setName("tbLab"); // NOI18N
        tbLab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLabMouseClicked(evt);
            }
        });
        tbLab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbLabKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbLab);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 110));
        panelisi3.setLayout(null);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("No.Rawat :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label15);
        label15.setBounds(0, 10, 75, 23);

        NoRawat.setEditable(false);
        NoRawat.setForeground(new java.awt.Color(0, 0, 0));
        NoRawat.setName("NoRawat"); // NOI18N
        NoRawat.setPreferredSize(new java.awt.Dimension(207, 23));
        NoRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRawatKeyPressed(evt);
            }
        });
        panelisi3.add(NoRawat);
        NoRawat.setBounds(79, 10, 226, 23);

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label11);
        label11.setBounds(0, 40, 75, 23);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl1KeyPressed(evt);
            }
        });
        panelisi3.add(Tgl1);
        Tgl1.setBounds(79, 40, 100, 23);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("Pasien :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label16);
        label16.setBounds(330, 10, 60, 23);

        label13.setForeground(new java.awt.Color(0, 0, 0));
        label13.setText("Petugas :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label13);
        label13.setBounds(330, 40, 60, 23);

        kdmem.setEditable(false);
        kdmem.setForeground(new java.awt.Color(0, 0, 0));
        kdmem.setName("kdmem"); // NOI18N
        kdmem.setPreferredSize(new java.awt.Dimension(80, 23));
        kdmem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdmemKeyPressed(evt);
            }
        });
        panelisi3.add(kdmem);
        kdmem.setBounds(395, 10, 80, 23);

        kdptg.setEditable(false);
        kdptg.setForeground(new java.awt.Color(0, 0, 0));
        kdptg.setName("kdptg"); // NOI18N
        kdptg.setPreferredSize(new java.awt.Dimension(80, 23));
        kdptg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdptgKeyPressed(evt);
            }
        });
        panelisi3.add(kdptg);
        kdptg.setBounds(395, 40, 80, 23);

        nmmem.setEditable(false);
        nmmem.setForeground(new java.awt.Color(0, 0, 0));
        nmmem.setName("nmmem"); // NOI18N
        nmmem.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi3.add(nmmem);
        nmmem.setBounds(480, 10, 240, 23);

        nmptg.setEditable(false);
        nmptg.setForeground(new java.awt.Color(0, 0, 0));
        nmptg.setName("nmptg"); // NOI18N
        nmptg.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi3.add(nmptg);
        nmptg.setBounds(480, 40, 240, 23);

        btnPasien.setForeground(new java.awt.Color(0, 0, 0));
        btnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPasien.setMnemonic('1');
        btnPasien.setToolTipText("Alt+1");
        btnPasien.setName("btnPasien"); // NOI18N
        btnPasien.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienActionPerformed(evt);
            }
        });
        panelisi3.add(btnPasien);
        btnPasien.setBounds(722, 10, 28, 23);

        btnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('2');
        btnPetugas.setToolTipText("Alt+2");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        panelisi3.add(btnPetugas);
        btnPetugas.setBounds(722, 40, 28, 23);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label18);
        label18.setBounds(178, 40, 30, 23);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl2KeyPressed(evt);
            }
        });
        panelisi3.add(Tgl2);
        Tgl2.setBounds(205, 40, 100, 23);

        kdpnj.setEditable(false);
        kdpnj.setForeground(new java.awt.Color(0, 0, 0));
        kdpnj.setHighlighter(null);
        kdpnj.setName("kdpnj"); // NOI18N
        kdpnj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpnjKeyPressed(evt);
            }
        });
        panelisi3.add(kdpnj);
        kdpnj.setBounds(835, 10, 60, 23);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setName("nmpnj"); // NOI18N
        panelisi3.add(nmpnj);
        nmpnj.setBounds(898, 10, 230, 23);

        btnPenjab.setForeground(new java.awt.Color(0, 0, 0));
        btnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenjab.setMnemonic('2');
        btnPenjab.setToolTipText("ALt+2");
        btnPenjab.setName("btnPenjab"); // NOI18N
        btnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjabActionPerformed(evt);
            }
        });
        panelisi3.add(btnPenjab);
        btnPenjab.setBounds(1130, 10, 28, 23);

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Cara Bayar :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label17);
        label17.setBounds(760, 10, 70, 23);

        label14.setForeground(new java.awt.Color(0, 0, 0));
        label14.setText("Unit/Poli :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label14);
        label14.setBounds(760, 40, 70, 23);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });
        panelisi3.add(kdpoli);
        kdpoli.setBounds(835, 40, 60, 23);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setName("TPoli"); // NOI18N
        panelisi3.add(TPoli);
        TPoli.setBounds(898, 40, 230, 23);

        BtnUnit.setForeground(new java.awt.Color(0, 0, 0));
        BtnUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnUnit.setMnemonic('4');
        BtnUnit.setToolTipText("ALt+4");
        BtnUnit.setName("BtnUnit"); // NOI18N
        BtnUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnitActionPerformed(evt);
            }
        });
        panelisi3.add(BtnUnit);
        BtnUnit.setBounds(1130, 40, 28, 23);

        BtnBersih.setForeground(new java.awt.Color(0, 0, 0));
        BtnBersih.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnBersih.setMnemonic('R');
        BtnBersih.setText("Refresh");
        BtnBersih.setToolTipText("Alt+R");
        BtnBersih.setName("BtnBersih"); // NOI18N
        BtnBersih.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBersih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBersihActionPerformed(evt);
            }
        });
        BtnBersih.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBersihKeyPressed(evt);
            }
        });
        panelisi3.add(BtnBersih);
        BtnBersih.setBounds(1170, 10, 90, 30);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("Ket. Klinis :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label12);
        label12.setBounds(0, 70, 75, 23);

        ketklinis.setForeground(new java.awt.Color(0, 0, 0));
        ketklinis.setName("ketklinis"); // NOI18N
        ketklinis.setPreferredSize(new java.awt.Dimension(207, 23));
        ketklinis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ketklinisKeyPressed(evt);
            }
        });
        panelisi3.add(ketklinis);
        ketklinis.setBounds(79, 70, 397, 23);

        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setText("Bridging LIS :");
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        panelisi3.add(jLabel3);
        jLabel3.setBounds(480, 70, 88, 23);

        ketAktif.setForeground(new java.awt.Color(0, 51, 255));
        ketAktif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ketAktif.setText("-");
        ketAktif.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ketAktif.setName("ketAktif"); // NOI18N
        panelisi3.add(ketAktif);
        ketAktif.setBounds(575, 70, 400, 23);

        internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Key Word :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi1.add(label10);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(170, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi1.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('5');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+5");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(150, 23));
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
        panelisi1.add(BtnCari);

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(100, 30));
        panelisi1.add(label9);

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
        panelisi1.add(BtnHapus);

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
        panelisi1.add(BtnAll);

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
        panelisi1.add(BtnKeluar);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Tgl. Nota/Kwitansi : ");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(110, 23));
        panelisi1.add(jLabel25);

        tglNota.setEditable(false);
        tglNota.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-11-2023" }));
        tglNota.setDisplayFormat("dd-MM-yyyy");
        tglNota.setName("tglNota"); // NOI18N
        tglNota.setOpaque(false);
        panelisi1.add(tglNota);

        internalFrame1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void btnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienActionPerformed
        akses.setform("DlgCariPeriksaLab");
        member.emptTeks();
        member.isCek();
        member.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        member.setLocationRelativeTo(internalFrame1);
        member.setAlwaysOnTop(false);
        member.setVisible(true);
    }//GEN-LAST:event_btnPasienActionPerformed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        akses.setform("DlgCariPeriksaLab");
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void Tgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl1KeyPressed
        Valid.pindah(evt, kdmem, Tgl2);
    }//GEN-LAST:event_Tgl1KeyPressed

    private void kdmemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdmemKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=?", nmmem, kdmem.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=?", nmmem, kdmem.getText());
            NoRawat.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnPasienActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=?", nmmem, kdmem.getText());
            Tgl1.requestFocus();
        }
    }//GEN-LAST:event_kdmemKeyPressed

    private void NoRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRawatKeyPressed
        Valid.pindah(evt, BtnKeluar, kdptg);
    }//GEN-LAST:event_NoRawatKeyPressed

    private void kdptgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdptgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nama from petugas where nip=?", nmptg, kdptg.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sequel.cariIsi("select nama from petugas where nip=?", nmptg, kdptg.getText());
            Tgl2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnPetugasActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select nama from petugas where nip=?", nmptg, kdptg.getText());
            NoRawat.requestFocus();
        }
    }//GEN-LAST:event_kdptgKeyPressed

    private void Tgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl2KeyPressed
        Valid.pindah(evt, Tgl1, kdptg);
    }//GEN-LAST:event_Tgl2KeyPressed

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
        NoRawat.setText("");
        kdmem.setText("");
        nmmem.setText("");
        kdptg.setText("");
        nmptg.setText("");
        tampil();
        ketAktif.setText(Sequel.cariIsi("select if(aktivasi_LIS='1','TELAH DIAKTIFKAN','NON AKTIF') cek from set_pjlab limit 1"));


    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        NoRawat.setText("");
        kdmem.setText("");
        nmmem.setText("");
        kdptg.setText("");
        nmptg.setText("");
        tampil();
        ketAktif.setText(Sequel.cariIsi("select if(aktivasi_LIS='1','TELAH DIAKTIFKAN','NON AKTIF') cek from set_pjlab limit 1"));
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        ketAktif.setText(Sequel.cariIsi("select if(aktivasi_LIS='1','TELAH DIAKTIFKAN','NON AKTIF') cek from set_pjlab limit 1"));
        WindowKirimLIS.dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnAll, NoRawat);
        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        TCari.requestFocus();
    } else if (Kd2.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik No. Rawat pada tabel untuk memilih...!!!!");
    } else if (!(Kd2.getText().trim().equals(""))) {
        y = JOptionPane.showConfirmDialog(null, "Apakah Data Akan dihapus???", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (y == JOptionPane.YES_OPTION) {
            if (akses.getkode().equals("Admin Utama")) {
                try {
                    Sequel.AutoComitFalse();
                    status = "";
                    ttljmdokter = 0;
                    ttljmpetugas = 0;
                    ttlkso = 0;
                    ttlpendapatan = 0;
                    ttlbhp = 0;
                    ttljmdokter = Sequel.cariIsiAngka("select sum(tarif_perujuk)+sum(tarif_tindakan_dokter) from periksa_lab "
                            + "where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                    ttljmpetugas = Sequel.cariIsiAngka("select sum(tarif_tindakan_petugas) from periksa_lab "
                            + "where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                    ttlkso = Sequel.cariIsiAngka("select sum(kso) from periksa_lab where no_rawat='" + Kd2.getText() + "' and "
                            + "tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                    ttlbhp = Sequel.cariIsiAngka("select sum(bhp) from periksa_lab where no_rawat='" + Kd2.getText() + "' and "
                            + "tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                    ttlpendapatan = Sequel.cariIsiAngka("select sum(biaya) from periksa_lab where no_rawat='" + Kd2.getText() + "' and "
                            + "tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                    ttljmdokter = ttljmdokter + Sequel.cariIsiAngka("select sum(bagian_perujuk)+sum(bagian_dokter) from detail_periksa_lab "
                            + "where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                    ttljmpetugas = ttljmpetugas + Sequel.cariIsiAngka("select sum(bagian_laborat) from detail_periksa_lab "
                            + "where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                    ttlkso = ttlkso + Sequel.cariIsiAngka("select sum(kso) from detail_periksa_lab where no_rawat='" + Kd2.getText() + "' "
                            + "and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                    ttlbhp = ttlbhp + Sequel.cariIsiAngka("select sum(bhp) from detail_periksa_lab where no_rawat='" + Kd2.getText() + "' "
                            + "and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                    ttlpendapatan = ttlpendapatan + Sequel.cariIsiAngka("select sum(biaya_item) from detail_periksa_lab "
                            + "where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                    status = Sequel.cariIsi("select status from periksa_lab where no_rawat='" + Kd2.getText() + "' and "
                            + "tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");

                    if (Sequel.queryutf("delete from periksa_lab where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'") == true) {
                        if (Sequel.queryutf("delete from detail_periksa_lab where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'") == true) {
                            if (status.equals("Ranap")) {
                                Sequel.queryu("delete from tampjurnal");
                                if (ttlpendapatan > 0) {
                                    Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Laborat_Ranap + "','Suspen Piutang Laborat Ranap','0','" + ttlpendapatan + "'", "Rekening");
                                    Sequel.menyimpan("tampjurnal", "'" + Laborat_Ranap + "','Pendapatan Laborat Rawat Inap','" + ttlpendapatan + "','0'", "Rekening");
                                }
                                if (ttljmdokter > 0) {
                                    Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Laborat_Ranap + "','Beban Jasa Medik Dokter Laborat Ranap','0','" + ttljmdokter + "'", "Rekening");
                                    Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Laborat_Ranap + "','Utang Jasa Medik Dokter Laborat Ranap','" + ttljmdokter + "','0'", "Rekening");
                                }
                                if (ttljmpetugas > 0) {
                                    Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Petugas_Laborat_Ranap + "','Beban Jasa Medik Petugas Laborat Ranap','0','" + ttljmpetugas + "'", "Rekening");
                                    Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Petugas_Laborat_Ranap + "','Utang Jasa Medik Petugas Laborat Ranap','" + ttljmpetugas + "','0'", "Rekening");
                                }
                                if (ttlbhp > 0) {
                                    Sequel.menyimpan("tampjurnal", "'" + HPP_Persediaan_Laborat_Rawat_inap + "','HPP Persediaan Laborat Rawat Inap','0','" + ttlbhp + "'", "Rekening");
                                    Sequel.menyimpan("tampjurnal", "'" + Persediaan_BHP_Laborat_Rawat_Inap + "','Persediaan BHP Laborat Rawat Inap','" + ttlbhp + "','0'", "Rekening");
                                }
                                if (ttlkso > 0) {
                                    Sequel.menyimpan("tampjurnal", "'" + Beban_Kso_Laborat_Ranap + "','Beban KSO Laborat Ranap','0','" + ttlkso + "'", "Rekening");
                                    Sequel.menyimpan("tampjurnal", "'" + Utang_Kso_Laborat_Ranap + "','Utang KSO Laborat Ranap','" + ttlkso + "','0'", "Rekening");
                                }
                                jur.simpanJurnal(Kd2.getText(), Sequel.cariIsi("select current_date()"), "U", "PEMBATALAN PEMERIKSAAN LABORAT RAWAT INAP PASIEN OLEH " + akses.getkode());
                            }
                            tampil();
                        }
                    }
                    Sequel.AutoComitTrue();

//                    x = JOptionPane.showConfirmDialog(null, "Apakah Data LIS akan dihapus juga ???", "Konfirmasi", JOptionPane.YES_NO_OPTION);
//
//                    if (x == JOptionPane.YES_OPTION) {
//                        if (nolab.equals("-")) {
//                            JOptionPane.showMessageDialog(null, "Data pemeriksaan LIS tdk. ditemukan & terhapus, kemungkinan belum bridging LIS...!!!!");
//                        } else {
////                            Sequel.queryu("DELETE FROM lis_reg WHERE no_lab='" + nolab + "' ");
//                        }
//                    }
                    if (nolab.equals("-")) {
                        JOptionPane.showMessageDialog(null, "Data pemeriksaan LIS tdk. ditemukan & terhapus, kemungkinan belum bridging LIS...!!!!");
                    } else {
                        Sequel.queryu("Update lis_reg set stts_kirim = '0' WHERE no_lab='" + nolab + "' ");
                    }

                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                    JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih terlebih dulu data yang mau anda hapus...  Klik data pada tabel untuk memilih data...!!!!");
                }
            } else {
                cekLab = 0;
                cekDataLab = "select count(1) from periksa_lab where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and stts_bayar ='Belum' and jam='" + jamPeriksa + "'";

                if (Sequel.cariIsiAngka(cekDataLab) == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
                    TCari.requestFocus();
                } else {
                    try {
                        Sequel.AutoComitFalse();
                        status = "";
                        ttljmdokter = 0;
                        ttljmpetugas = 0;
                        ttlkso = 0;
                        ttlpendapatan = 0;
                        ttlbhp = 0;
                        ttljmdokter = Sequel.cariIsiAngka("select sum(tarif_perujuk)+sum(tarif_tindakan_dokter) from periksa_lab "
                                + "where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                        ttljmpetugas = Sequel.cariIsiAngka("select sum(tarif_tindakan_petugas) from periksa_lab "
                                + "where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                        ttlkso = Sequel.cariIsiAngka("select sum(kso) from periksa_lab where no_rawat='" + Kd2.getText() + "' and "
                                + "tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                        ttlbhp = Sequel.cariIsiAngka("select sum(bhp) from periksa_lab where no_rawat='" + Kd2.getText() + "' and "
                                + "tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                        ttlpendapatan = Sequel.cariIsiAngka("select sum(biaya) from periksa_lab where no_rawat='" + Kd2.getText() + "' and "
                                + "tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                        ttljmdokter = ttljmdokter + Sequel.cariIsiAngka("select sum(bagian_perujuk)+sum(bagian_dokter) from detail_periksa_lab "
                                + "where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                        ttljmpetugas = ttljmpetugas + Sequel.cariIsiAngka("select sum(bagian_laborat) from detail_periksa_lab "
                                + "where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                        ttlkso = ttlkso + Sequel.cariIsiAngka("select sum(kso) from detail_periksa_lab where no_rawat='" + Kd2.getText() + "' "
                                + "and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                        ttlbhp = ttlbhp + Sequel.cariIsiAngka("select sum(bhp) from detail_periksa_lab where no_rawat='" + Kd2.getText() + "' "
                                + "and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                        ttlpendapatan = ttlpendapatan + Sequel.cariIsiAngka("select sum(biaya_item) from detail_periksa_lab "
                                + "where no_rawat='" + Kd2.getText()
                                + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                        status = Sequel.cariIsi("select status from periksa_lab where no_rawat='" + Kd2.getText() + "' and "
                                + "tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");

                        if (Sequel.queryutf("delete from periksa_lab where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'") == true) {
                            if (Sequel.queryutf("delete from detail_periksa_lab where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'") == true) {
                                if (status.equals("Ranap")) {
                                    Sequel.queryu("delete from tampjurnal");
                                    if (ttlpendapatan > 0) {
                                        Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Laborat_Ranap + "','Suspen Piutang Laborat Ranap','0','" + ttlpendapatan + "'", "Rekening");
                                        Sequel.menyimpan("tampjurnal", "'" + Laborat_Ranap + "','Pendapatan Laborat Rawat Inap','" + ttlpendapatan + "','0'", "Rekening");
                                    }
                                    if (ttljmdokter > 0) {
                                        Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Laborat_Ranap + "','Beban Jasa Medik Dokter Laborat Ranap','0','" + ttljmdokter + "'", "Rekening");
                                        Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Laborat_Ranap + "','Utang Jasa Medik Dokter Laborat Ranap','" + ttljmdokter + "','0'", "Rekening");
                                    }
                                    if (ttljmpetugas > 0) {
                                        Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Petugas_Laborat_Ranap + "','Beban Jasa Medik Petugas Laborat Ranap','0','" + ttljmpetugas + "'", "Rekening");
                                        Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Petugas_Laborat_Ranap + "','Utang Jasa Medik Petugas Laborat Ranap','" + ttljmpetugas + "','0'", "Rekening");
                                    }
                                    if (ttlbhp > 0) {
                                        Sequel.menyimpan("tampjurnal", "'" + HPP_Persediaan_Laborat_Rawat_inap + "','HPP Persediaan Laborat Rawat Inap','0','" + ttlbhp + "'", "Rekening");
                                        Sequel.menyimpan("tampjurnal", "'" + Persediaan_BHP_Laborat_Rawat_Inap + "','Persediaan BHP Laborat Rawat Inap','" + ttlbhp + "','0'", "Rekening");
                                    }
                                    if (ttlkso > 0) {
                                        Sequel.menyimpan("tampjurnal", "'" + Beban_Kso_Laborat_Ranap + "','Beban KSO Laborat Ranap','0','" + ttlkso + "'", "Rekening");
                                        Sequel.menyimpan("tampjurnal", "'" + Utang_Kso_Laborat_Ranap + "','Utang KSO Laborat Ranap','" + ttlkso + "','0'", "Rekening");
                                    }
                                    jur.simpanJurnal(Kd2.getText(), Sequel.cariIsi("select current_date()"), "U", "PEMBATALAN PEMERIKSAAN LABORAT RAWAT INAP PASIEN OLEH " + akses.getkode());
                                }
                                tampil();
                            }
                        }
                        Sequel.AutoComitTrue();

//                        x = JOptionPane.showConfirmDialog(null, "Apakah Data LIS akan dihapus juga ???", "Konfirmasi", JOptionPane.YES_NO_OPTION);
//
//                        if (x == JOptionPane.YES_OPTION) {
//                            if (nolab.equals("-")) {
//                                JOptionPane.showMessageDialog(null, "Data pemeriksaan LIS tdk. ditemukan & terhapus, kemungkinan belum bridging LIS...!!!!");
//                            } else {
////                                Sequel.queryu("DELETE FROM lis_reg WHERE no_lab='" + nolab + "' ");
//                            }
//                        }
                        if (nolab.equals("-")) {
                            JOptionPane.showMessageDialog(null, "Data pemeriksaan LIS tdk. ditemukan & terhapus, kemungkinan belum bridging LIS...!!!!");
                        } else {
                            Sequel.queryu("Update lis_reg set stts_kirim = '0' WHERE no_lab='" + nolab + "' ");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih terlebih dulu data yang mau anda hapus...  Klik data pada tabel untuk memilih data...!!!!");
                    }
                }
            }
        }

    }
}//GEN-LAST:event_BtnHapusActionPerformed

private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnHapusActionPerformed(null);
    } else {
        Valid.pindah(evt, TCari, BtnAll);
    }
}//GEN-LAST:event_BtnHapusKeyPressed

private void tbLabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLabMouseClicked
    if (tabMode.getRowCount() != 0) {
        try {
            getData();
        } catch (java.lang.NullPointerException e) {
        }
    }
}//GEN-LAST:event_tbLabMouseClicked

private void tbLabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbLabKeyPressed
    if (tabMode.getRowCount() != 0) {
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }
}//GEN-LAST:event_tbLabKeyPressed

    private void MnCetakHasilLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakHasilLabActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mencetak. Pilih dulu data yang mau dicetak. Klik No. Rawat pada tabel untuk memilih...!!!!");
        } else if (!(Kd2.getText().trim().equals(""))) {
            try {
                ps4.setString(1, tglPeriksa);
                ps4.setString(2, jamPeriksa);
                ps4.setString(3, Kd2.getText());
                rs = ps4.executeQuery();
                while (rs.next()) {
                    Sequel.AutoComitFalse();
                    kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + rs.getString("no_rawat") + "' order by tgl_masuk desc limit 1");
                    if (!kamar.equals("")) {
                        namakamar = kamar + ", " + Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal "
                                + " where kamar.kd_kamar='" + kamar + "' ");
                        kamar = "Kamar";
                    } else if (kamar.equals("")) {
                        kamar = "Poli";
                        namakamar = Sequel.cariIsi("select nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "
                                + "where reg_periksa.no_rawat='" + rs.getString("no_rawat") + "'");
                    }
                    Map<String, Object> param = new HashMap<>();
                    param.put("noperiksa", rs.getString("no_rawat"));
                    param.put("norm", rs.getString("no_rkm_medis"));
                    param.put("namapasien", rs.getString("nm_pasien"));
                    param.put("jkel", rs.getString("jk"));
                    param.put("umur", rs.getString("umur"));
                    param.put("pengirim", Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs.getString("dokter_perujuk")));
                    param.put("tanggal", rs.getString("tgl_periksa"));
                    param.put("penjab", rs.getString("nm_dokter"));
                    param.put("petugas", rs.getString("nama"));
                    param.put("jam", rs.getString("jam"));
                    param.put("alamat", rs.getString("Alamat"));
                    param.put("kamar", kamar);
                    param.put("namakamar", namakamar);

                    Sequel.queryu("delete from temporary");

                    ps2.setString(1, rs.getString("no_rawat"));
                    ps2.setString(2, Valid.SetTgl(rs.getString("tgl_periksa")));
                    ps2.setString(3, rs.getString("jam"));
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        Sequel.menyimpan("temporary", "'0','" + rs2.getString("nm_perawatan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        ps3.setString(1, rs.getString("no_rawat"));
                        ps3.setString(2, rs2.getString("kd_jenis_prw"));
                        ps3.setString(3, Valid.SetTgl(rs.getString("tgl_periksa")));
                        ps3.setString(4, rs.getString("jam"));
                        rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            Sequel.menyimpan("temporary", "'0','  " + rs3.getString("Pemeriksaan") + "','" + rs3.getString("nilai") + "','" + rs3.getString("satuan")
                                    + "','" + rs3.getString("nilai_rujukan") + "','" + rs3.getString("keterangan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        }
                    }
                    Sequel.AutoComitTrue();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    Valid.MyReport("rptPeriksaLab.jasper", "report", "::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc", param);

                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCetakHasilLabActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        ketklinis.setText("-");
    }//GEN-LAST:event_formWindowOpened

    private void MnCetakNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakNotaActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mencetak. Pilih dulu data yang mau dicetak. Klik No. Rawat pada tabel untuk memilih...!!!!");
        } else if (!(Kd2.getText().trim().equals(""))) {
            try {
                Sequel.AutoComitFalse();
                ps4.setString(1, tglPeriksa);
                ps4.setString(2, jamPeriksa);
                ps4.setString(3, Kd2.getText());
                rs = ps4.executeQuery();
                if (rs.next()) {
                    Sequel.queryu("delete from temporary");
                    ps2.setString(1, rs.getString("no_rawat"));
                    ps2.setString(2, Valid.SetTgl(rs.getString("tgl_periksa")));
                    ps2.setString(3, rs.getString("jam"));
                    rs2 = ps2.executeQuery();
                    ttl = 0;
                    while (rs2.next()) {
                        item = rs2.getDouble("biaya");//Sequel.cariIsiAngka("select sum(biaya_item) from template_laboratorium where kd_jenis_prw=?",rs2.getString("kd_jenis_prw"));
                        ttl = ttl + item;
                        Sequel.menyimpan("temporary", "'0','" + rs2.getString("kd_jenis_prw") + "','" + rs2.getString("nm_perawatan") + "','','Pemeriksaan','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Biaya Lab");
                        ps3.setString(1, rs.getString("no_rawat"));
                        ps3.setString(2, rs2.getString("kd_jenis_prw"));
                        ps3.setString(3, Valid.SetTgl(rs.getString("tgl_periksa")));
                        ps3.setString(4, rs.getString("jam"));
                        rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            item = rs3.getDouble("biaya_item");
                            ttl = ttl + item;
                            Sequel.menyimpan("temporary", "'0','" + rs3.getString("kd_jenis_prw") + "','   " + rs3.getString("Pemeriksaan") + "','" + item + "','Detail Pemeriksaan','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Biaya Lab");
                        }
                    }
                    Sequel.menyimpan("temporary", "'0','','Total Biaya Pemeriksaan Lab','" + ttl + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Biaya Lab");
                    cetakNota();

//                    Valid.panggilUrl("billing/LaporanBiayaLab.php?norm=" + rs.getString("no_rkm_medis") + "&pasien=" + rs.getString("nm_pasien").replaceAll(" ", "_")
//                            + "&tanggal=" + rs.getString("tgl_periksa") + "&jam=" + rs.getString("jam") + "&pjlab=" + rs.getString("nm_dokter").replaceAll(" ", "_")
//                            + "&petugas=" + rs.getString("nama").replaceAll(" ", "_") + "&kasir=" + Sequel.cariIsi("select nama from pegawai where nik=?", var.getkode()));
                }
                Sequel.AutoComitTrue();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCetakNotaActionPerformed

    private void MnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUbahActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mengubah. Pilih dulu data yang mau dicetak. Klik No. Rawat pada tabel untuk memilih...!!!!");
        } else if (!(Kd2.getText().trim().equals(""))) {
            DlgUbahPeriksaLab ubah = new DlgUbahPeriksaLab(null, false);
            ubah.setSize(this.getWidth() - 40, this.getHeight() - 40);
            ubah.setNoRm(Kd2.getText(), tglPeriksa, jamPeriksa);
            ubah.setLocationRelativeTo(this);
            ubah.setVisible(true);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnUbahActionPerformed

    private void MnCetakHasilLab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakHasilLab1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mencteak. Pilih dulu data yang mau dicetak. Klik No. Rawat pada tabel untuk memilih...!!!!");
        } else if (!(Kd2.getText().trim().equals(""))) {
            try {
                ps4.setString(1, tglPeriksa);
                ps4.setString(2, jamPeriksa);
                ps4.setString(3, Kd2.getText());
                rs = ps4.executeQuery();
                while (rs.next()) {
                    Sequel.AutoComitFalse();
                    kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + rs.getString("no_rawat") + "' order by tgl_masuk desc limit 1");
                    if (!kamar.equals("")) {
                        namakamar = kamar + ", " + Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal "
                                + " where kamar.kd_kamar='" + kamar + "' ");
                        kamar = "Kamar";
                    } else if (kamar.equals("")) {
                        kamar = "Poli";
                        namakamar = Sequel.cariIsi("select nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "
                                + "where reg_periksa.no_rawat='" + rs.getString("no_rawat") + "'");
                    }
                    Map<String, Object> param = new HashMap<>();
                    param.put("noperiksa", rs.getString("no_rawat"));
                    param.put("norm", rs.getString("no_rkm_medis"));
                    param.put("namapasien", rs.getString("nm_pasien"));
                    param.put("jkel", rs.getString("jk"));
                    param.put("umur", rs.getString("umur"));
                    param.put("pengirim", Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs.getString("dokter_perujuk")));
                    param.put("tanggal", rs.getString("tgl_periksa"));
                    param.put("penjab", rs.getString("nm_dokter"));
                    param.put("petugas", rs.getString("nama"));
                    param.put("jam", rs.getString("jam"));
                    param.put("alamat", rs.getString("Alamat"));
                    param.put("kamar", kamar);
                    param.put("namakamar", namakamar);

                    Sequel.queryu("delete from temporary");

                    ps2.setString(1, rs.getString("no_rawat"));
                    ps2.setString(2, Valid.SetTgl(rs.getString("tgl_periksa")));
                    ps2.setString(3, rs.getString("jam"));
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        Sequel.menyimpan("temporary", "'0','" + rs2.getString("nm_perawatan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        ps3.setString(1, rs.getString("no_rawat"));
                        ps3.setString(2, rs2.getString("kd_jenis_prw"));
                        ps3.setString(3, Valid.SetTgl(rs.getString("tgl_periksa")));
                        ps3.setString(4, rs.getString("jam"));
                        rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            Sequel.menyimpan("temporary", "'0','  " + rs3.getString("Pemeriksaan") + "','" + rs3.getString("nilai") + "','" + rs3.getString("satuan")
                                    + "','" + rs3.getString("nilai_rujukan") + "','" + rs3.getString("keterangan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        }
                    }
                    Sequel.AutoComitTrue();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    Valid.MyReport("rptPeriksaLab2.jasper", "report", "::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc", param);

                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCetakHasilLab1ActionPerformed

    private void MnCetakHasilLab2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakHasilLab2ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mencteak. Pilih dulu data yang mau dicetak. Klik No. Rawat pada tabel untuk memilih...!!!!");
        } else if (!(Kd2.getText().trim().equals(""))) {
            try {
                ps4.setString(1, tglPeriksa);
                ps4.setString(2, jamPeriksa);
                ps4.setString(3, Kd2.getText());
                rs = ps4.executeQuery();
                while (rs.next()) {
                    Sequel.AutoComitFalse();
                    kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + rs.getString("no_rawat") + "' order by tgl_masuk desc limit 1");
                    if (!kamar.equals("")) {
                        namakamar = kamar + ", " + Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal "
                                + " where kamar.kd_kamar='" + kamar + "' ");
                        kamar = "Kamar";
                    } else if (kamar.equals("")) {
                        kamar = "Poli";
                        namakamar = Sequel.cariIsi("select nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "
                                + "where reg_periksa.no_rawat='" + rs.getString("no_rawat") + "'");
                    }
                    Map<String, Object> param = new HashMap<>();
                    param.put("noperiksa", rs.getString("no_rawat"));
                    param.put("norm", rs.getString("no_rkm_medis"));
                    param.put("namapasien", rs.getString("nm_pasien"));
                    param.put("jkel", rs.getString("jk"));
                    param.put("umur", rs.getString("umur"));
                    param.put("pengirim", Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs.getString("dokter_perujuk")));
                    param.put("tanggal", rs.getString("tgl_periksa"));
                    param.put("penjab", rs.getString("nm_dokter"));
                    param.put("petugas", rs.getString("nama"));
                    param.put("jam", rs.getString("jam"));
                    param.put("alamat", rs.getString("Alamat"));
                    param.put("kamar", kamar);
                    param.put("namakamar", namakamar);

                    Sequel.queryu("delete from temporary");

                    ps2.setString(1, rs.getString("no_rawat"));
                    ps2.setString(2, Valid.SetTgl(rs.getString("tgl_periksa")));
                    ps2.setString(3, rs.getString("jam"));
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        Sequel.menyimpan("temporary", "'0','" + rs2.getString("nm_perawatan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        ps3.setString(1, rs.getString("no_rawat"));
                        ps3.setString(2, rs2.getString("kd_jenis_prw"));
                        ps3.setString(3, Valid.SetTgl(rs.getString("tgl_periksa")));
                        ps3.setString(4, rs.getString("jam"));
                        rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            Sequel.menyimpan("temporary", "'0','  " + rs3.getString("Pemeriksaan") + "','" + rs3.getString("nilai") + "','" + rs3.getString("satuan")
                                    + "','" + rs3.getString("nilai_rujukan") + "','" + rs3.getString("keterangan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        }
                    }
                    Sequel.AutoComitTrue();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    Valid.MyReport("rptPeriksaLab3.jasper", "report", "::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc", param);

                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCetakHasilLab2ActionPerformed

    private void MnCetakHasilLab3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakHasilLab3ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mencteak. Pilih dulu data yang mau dicetak. Klik No. Rawat pada tabel untuk memilih...!!!!");
        } else if (!(Kd2.getText().trim().equals(""))) {
            try {
                ps4.setString(1, tglPeriksa);
                ps4.setString(2, jamPeriksa);
                ps4.setString(3, Kd2.getText());
                rs = ps4.executeQuery();
                while (rs.next()) {
                    Sequel.AutoComitFalse();
                    kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + rs.getString("no_rawat") + "' order by tgl_masuk desc limit 1");
                    if (!kamar.equals("")) {
                        namakamar = kamar + ", " + Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal "
                                + " where kamar.kd_kamar='" + kamar + "' ");
                        kamar = "Kamar";
                    } else if (kamar.equals("")) {
                        kamar = "Poli";
                        namakamar = Sequel.cariIsi("select nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "
                                + "where reg_periksa.no_rawat='" + rs.getString("no_rawat") + "'");
                    }
                    Map<String, Object> param = new HashMap<>();
                    param.put("noperiksa", rs.getString("no_rawat"));
                    param.put("norm", rs.getString("no_rkm_medis"));
                    param.put("namapasien", rs.getString("nm_pasien"));
                    param.put("jkel", rs.getString("jk"));
                    param.put("umur", rs.getString("umur"));
                    param.put("lahir", rs.getString("lahir"));
                    param.put("pengirim", Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs.getString("dokter_perujuk")));
                    param.put("tanggal", rs.getString("tgl_periksa"));
                    param.put("penjab", rs.getString("nm_dokter"));
                    param.put("petugas", rs.getString("nama"));
                    param.put("jam", rs.getString("jam"));
                    param.put("alamat", rs.getString("Alamat"));
                    param.put("kamar", kamar);
                    param.put("namakamar", namakamar);

                    Sequel.queryu("delete from temporary");

                    ps2.setString(1, rs.getString("no_rawat"));
                    ps2.setString(2, Valid.SetTgl(rs.getString("tgl_periksa")));
                    ps2.setString(3, rs.getString("jam"));
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        Sequel.menyimpan("temporary", "'0','" + rs2.getString("nm_perawatan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        ps3.setString(1, rs.getString("no_rawat"));
                        ps3.setString(2, rs2.getString("kd_jenis_prw"));
                        ps3.setString(3, Valid.SetTgl(rs.getString("tgl_periksa")));
                        ps3.setString(4, rs.getString("jam"));
                        rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            Sequel.menyimpan("temporary", "'0','  " + rs3.getString("Pemeriksaan") + "','" + rs3.getString("nilai") + "','" + rs3.getString("satuan")
                                    + "','" + rs3.getString("nilai_rujukan") + "','" + rs3.getString("keterangan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        }
                    }
                    Sequel.AutoComitTrue();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    Valid.MyReport("rptPeriksaLab4.jasper", "report", "::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc", param);

                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCetakHasilLab3ActionPerformed

    private void MnCetakHasilLab4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakHasilLab4ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mencteak. Pilih dulu data yang mau dicetak. Klik No. Rawat pada tabel untuk memilih...!!!!");
        } else if (!(Kd2.getText().trim().equals(""))) {
            try {
                ps4.setString(1, tglPeriksa);
                ps4.setString(2, jamPeriksa);
                ps4.setString(3, Kd2.getText());
                rs = ps4.executeQuery();
                while (rs.next()) {
                    Sequel.AutoComitFalse();
                    kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + rs.getString("no_rawat") + "' order by tgl_masuk desc limit 1");
                    if (!kamar.equals("")) {
                        namakamar = kamar + ", " + Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal "
                                + " where kamar.kd_kamar='" + kamar + "' ");
                        kamar = "Kamar";
                    } else if (kamar.equals("")) {
                        kamar = "Poli";
                        namakamar = Sequel.cariIsi("select nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "
                                + "where reg_periksa.no_rawat='" + rs.getString("no_rawat") + "'");
                    }
                    Map<String, Object> param = new HashMap<>();
                    param.put("noperiksa", rs.getString("no_rawat"));
                    param.put("norm", rs.getString("no_rkm_medis"));
                    param.put("namapasien", rs.getString("nm_pasien"));
                    param.put("jkel", rs.getString("jk"));
                    param.put("umur", rs.getString("umur"));
                    param.put("lahir", rs.getString("lahir"));
                    param.put("pengirim", Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs.getString("dokter_perujuk")));
                    param.put("tanggal", rs.getString("tgl_periksa"));
                    param.put("penjab", rs.getString("nm_dokter"));
                    param.put("petugas", rs.getString("nama"));
                    param.put("jam", rs.getString("jam"));
                    param.put("alamat", rs.getString("Alamat"));
                    param.put("kamar", kamar);
                    param.put("namakamar", namakamar);

                    Sequel.queryu("delete from temporary");

                    ps2.setString(1, rs.getString("no_rawat"));
                    ps2.setString(2, Valid.SetTgl(rs.getString("tgl_periksa")));
                    ps2.setString(3, rs.getString("jam"));
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        Sequel.menyimpan("temporary", "'0','" + rs2.getString("nm_perawatan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        ps3.setString(1, rs.getString("no_rawat"));
                        ps3.setString(2, rs2.getString("kd_jenis_prw"));
                        ps3.setString(3, Valid.SetTgl(rs.getString("tgl_periksa")));
                        ps3.setString(4, rs.getString("jam"));
                        rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            Sequel.menyimpan("temporary", "'0','  " + rs3.getString("Pemeriksaan") + "','" + rs3.getString("nilai") + "','" + rs3.getString("satuan")
                                    + "','" + rs3.getString("nilai_rujukan") + "','" + rs3.getString("keterangan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        }
                    }
                    Sequel.AutoComitTrue();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    Valid.MyReport("rptPeriksaLab5.jasper", "report", "::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc", param);

                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCetakHasilLab4ActionPerformed

    private void MnCetakHasilLab5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakHasilLab5ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mencteak. Pilih dulu data yang mau dicetak. Klik No. Rawat pada tabel untuk memilih...!!!!");
        } else if (!(Kd2.getText().trim().equals(""))) {
            try {
                ps4.setString(1, tglPeriksa);
                ps4.setString(2, jamPeriksa);
                ps4.setString(3, Kd2.getText());
                rs = ps4.executeQuery();
                while (rs.next()) {
                    Sequel.AutoComitFalse();
                    kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + rs.getString("no_rawat") + "' order by tgl_masuk desc limit 1");
                    if (!kamar.equals("")) {
                        namakamar = kamar + ", " + Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal "
                                + " where kamar.kd_kamar='" + kamar + "' ");
                        kamar = "Kamar";
                    } else if (kamar.equals("")) {
                        kamar = "Poli";
                        namakamar = Sequel.cariIsi("select nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "
                                + "where reg_periksa.no_rawat='" + rs.getString("no_rawat") + "'");
                    }
                    Map<String, Object> param = new HashMap<>();
                    param.put("noperiksa", rs.getString("no_rawat"));
                    param.put("norm", rs.getString("no_rkm_medis"));
                    param.put("namapasien", rs.getString("nm_pasien"));
                    param.put("jkel", rs.getString("jk"));
                    param.put("umur", rs.getString("umur"));
                    param.put("lahir", rs.getString("lahir"));
                    param.put("pengirim", Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs.getString("dokter_perujuk")));
                    param.put("tanggal", rs.getString("tgl_periksa"));
                    param.put("penjab", rs.getString("nm_dokter"));
                    param.put("petugas", rs.getString("nama"));
                    param.put("jam", rs.getString("jam"));
                    param.put("alamat", rs.getString("Alamat"));
                    param.put("kamar", kamar);
                    param.put("namakamar", namakamar);

                    Sequel.queryu("delete from temporary");

                    ps2.setString(1, rs.getString("no_rawat"));
                    ps2.setString(2, Valid.SetTgl(rs.getString("tgl_periksa")));
                    ps2.setString(3, rs.getString("jam"));
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        Sequel.menyimpan("temporary", "'0','" + rs2.getString("nm_perawatan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        ps3.setString(1, rs.getString("no_rawat"));
                        ps3.setString(2, rs2.getString("kd_jenis_prw"));
                        ps3.setString(3, Valid.SetTgl(rs.getString("tgl_periksa")));
                        ps3.setString(4, rs.getString("jam"));
                        rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            Sequel.menyimpan("temporary", "'0','  " + rs3.getString("Pemeriksaan") + "','" + rs3.getString("nilai") + "','" + rs3.getString("satuan")
                                    + "','" + rs3.getString("nilai_rujukan") + "','" + rs3.getString("keterangan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        }
                    }
                    Sequel.AutoComitTrue();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    Valid.MyReport("rptPeriksaLab6.jasper", "report", "::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc", param);

                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCetakHasilLab5ActionPerformed

    private void MnCetakHasilLab6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakHasilLab6ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mencteak. Pilih dulu data yang mau dicetak. Klik No. Rawat pada tabel untuk memilih...!!!!");
        } else if (!(Kd2.getText().trim().equals(""))) {
            try {
                ps4.setString(1, tglPeriksa);
                ps4.setString(2, jamPeriksa);
                ps4.setString(3, Kd2.getText());
                rs = ps4.executeQuery();
                while (rs.next()) {
                    Sequel.AutoComitFalse();
                    kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + rs.getString("no_rawat") + "' order by tgl_masuk desc limit 1");
                    if (!kamar.equals("")) {
                        namakamar = kamar + ", " + Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal "
                                + " where kamar.kd_kamar='" + kamar + "' ");
                        kamar = "Kamar";
                    } else if (kamar.equals("")) {
                        kamar = "Poli";
                        namakamar = Sequel.cariIsi("select nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "
                                + "where reg_periksa.no_rawat='" + rs.getString("no_rawat") + "'");
                    }
                    Map<String, Object> param = new HashMap<>();
                    param.put("noperiksa", rs.getString("no_rawat"));
                    param.put("norm", rs.getString("no_rkm_medis"));
                    param.put("namapasien", rs.getString("nm_pasien"));
                    param.put("jkel", rs.getString("jk"));
                    param.put("diagnosa", Sequel.cariIsi("select diagnosa_awal from kamar_inap where no_rawat=?", rs.getString("no_rawat")));
                    param.put("umur", rs.getString("umur"));
                    param.put("lahir", rs.getString("lahir"));
                    param.put("pengirim", Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs.getString("dokter_perujuk")));
                    param.put("tanggal", rs.getString("tgl_periksa"));
                    param.put("penjab", rs.getString("nm_dokter"));
                    param.put("petugas", rs.getString("nama"));
                    param.put("jam", rs.getString("jam"));
                    param.put("alamat", rs.getString("Alamat"));
                    param.put("kamar", kamar);
                    param.put("namakamar", namakamar);

                    Sequel.queryu("delete from temporary");

                    ps2.setString(1, rs.getString("no_rawat"));
                    ps2.setString(2, Valid.SetTgl(rs.getString("tgl_periksa")));
                    ps2.setString(3, rs.getString("jam"));
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        Sequel.menyimpan("temporary", "'0','" + rs2.getString("nm_perawatan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        ps3.setString(1, rs.getString("no_rawat"));
                        ps3.setString(2, rs2.getString("kd_jenis_prw"));
                        ps3.setString(3, Valid.SetTgl(rs.getString("tgl_periksa")));
                        ps3.setString(4, rs.getString("jam"));
                        rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            Sequel.menyimpan("temporary", "'0','  " + rs3.getString("Pemeriksaan") + "','" + rs3.getString("nilai") + "','" + rs3.getString("satuan")
                                    + "','" + rs3.getString("nilai_rujukan") + "','" + rs3.getString("keterangan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        }
                    }
                    Sequel.AutoComitTrue();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    Valid.MyReport("rptPeriksaLab7.jasper", "report", "::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc", param);

                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCetakHasilLab6ActionPerformed

    private void MnCetakHasilLab7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakHasilLab7ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mencteak. Pilih dulu data yang mau dicetak. Klik No. Rawat pada tabel untuk memilih...!!!!");
        } else if (!(Kd2.getText().trim().equals(""))) {
            try {
                ps4.setString(1, tglPeriksa);
                ps4.setString(2, jamPeriksa);
                ps4.setString(3, Kd2.getText());
                rs = ps4.executeQuery();
                while (rs.next()) {
                    Sequel.AutoComitFalse();
                    kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + rs.getString("no_rawat") + "' order by tgl_masuk desc limit 1");
                    if (!kamar.equals("")) {
                        namakamar = kamar + ", " + Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal "
                                + " where kamar.kd_kamar='" + kamar + "' ");
                        kamar = "Kamar";
                    } else if (kamar.equals("")) {
                        kamar = "Poli";
                        namakamar = Sequel.cariIsi("select nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "
                                + "where reg_periksa.no_rawat='" + rs.getString("no_rawat") + "'");
                    }
                    Map<String, Object> param = new HashMap<>();
                    param.put("noperiksa", rs.getString("no_rawat"));
                    param.put("norm", rs.getString("no_rkm_medis"));
                    param.put("namapasien", rs.getString("nm_pasien"));
                    param.put("jkel", rs.getString("jk"));
                    param.put("umur", rs.getString("umur"));
                    param.put("pekerjaan", Sequel.cariIsi("select pekerjaan from pasien where no_rkm_medis=?", rs.getString("no_rkm_medis")));
                    param.put("noktp", Sequel.cariIsi("select no_ktp from pasien where no_rkm_medis=?", rs.getString("no_rkm_medis")));
                    param.put("lahir", rs.getString("lahir"));
                    param.put("pengirim", Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs.getString("dokter_perujuk")));
                    param.put("tanggal", rs.getString("tgl_periksa"));
                    param.put("penjab", rs.getString("nm_dokter"));
                    param.put("petugas", rs.getString("nama"));
                    param.put("jam", rs.getString("jam"));
                    param.put("alamat", rs.getString("Alamat"));
                    param.put("kamar", kamar);
                    param.put("namakamar", namakamar);

                    Sequel.queryu("delete from temporary");

                    ps2.setString(1, rs.getString("no_rawat"));
                    ps2.setString(2, Valid.SetTgl(rs.getString("tgl_periksa")));
                    ps2.setString(3, rs.getString("jam"));
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        Sequel.menyimpan("temporary", "'0','" + rs2.getString("nm_perawatan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        ps3.setString(1, rs.getString("no_rawat"));
                        ps3.setString(2, rs2.getString("kd_jenis_prw"));
                        ps3.setString(3, Valid.SetTgl(rs.getString("tgl_periksa")));
                        ps3.setString(4, rs.getString("jam"));
                        rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            Sequel.menyimpan("temporary", "'0','  " + rs3.getString("Pemeriksaan") + "','" + rs3.getString("nilai") + "','" + rs3.getString("satuan")
                                    + "','" + rs3.getString("nilai_rujukan") + "','" + rs3.getString("keterangan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        }
                    }
                    Sequel.AutoComitTrue();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    Valid.MyReport("rptPeriksaLab8.jasper", "report", "::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc", param);

                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCetakHasilLab7ActionPerformed

    private void MnCetakHasilLab8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakHasilLab8ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mencetak. Pilih dulu data yang mau dicetak. Klik No. Rawat pada tabel untuk memilih...!!!!");
        } else if (!(Kd2.getText().trim().equals(""))) {
            try {
                ps4.setString(1, tglPeriksa);
                ps4.setString(2, jamPeriksa);
                ps4.setString(3, Kd2.getText());
                rs = ps4.executeQuery();
                while (rs.next()) {
                    Sequel.AutoComitFalse();
                    kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + rs.getString("no_rawat") + "' order by tgl_masuk desc limit 1");
                    if (!kamar.equals("")) {
                        namakamar = kamar + ", " + Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal "
                                + " where kamar.kd_kamar='" + kamar + "' ");
                        kamar = "Kamar";
                    } else if (kamar.equals("")) {
                        kamar = "Poli";
                        namakamar = Sequel.cariIsi("select nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "
                                + "where reg_periksa.no_rawat='" + rs.getString("no_rawat") + "'");
                    }
                    Map<String, Object> param = new HashMap<>();
                    param.put("noperiksa", rs.getString("no_rawat"));
                    param.put("norm", rs.getString("no_rkm_medis"));
                    param.put("namapasien", rs.getString("nm_pasien"));
                    param.put("jkel", rs.getString("jk"));
                    param.put("umur", rs.getString("umur"));
                    param.put("pengirim", Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs.getString("dokter_perujuk")));
                    param.put("tanggal", rs.getString("tgl_periksa"));
                    param.put("penjab", rs.getString("nm_dokter"));
                    param.put("petugas", rs.getString("nama"));
                    param.put("jam", rs.getString("jam"));
                    param.put("alamat", rs.getString("Alamat"));
                    param.put("kamar", kamar);
                    param.put("namakamar", namakamar);

                    Sequel.queryu("delete from temporary");

                    ps2.setString(1, rs.getString("no_rawat"));
                    ps2.setString(2, Valid.SetTgl(rs.getString("tgl_periksa")));
                    ps2.setString(3, rs.getString("jam"));
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        Sequel.menyimpan("temporary", "'0','" + rs2.getString("nm_perawatan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        ps3.setString(1, rs.getString("no_rawat"));
                        ps3.setString(2, rs2.getString("kd_jenis_prw"));
                        ps3.setString(3, Valid.SetTgl(rs.getString("tgl_periksa")));
                        ps3.setString(4, rs.getString("jam"));
                        rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            Sequel.menyimpan("temporary", "'0','  " + rs3.getString("Pemeriksaan") + "','" + rs3.getString("nilai") + "','" + rs3.getString("satuan")
                                    + "','" + rs3.getString("nilai_rujukan") + "','" + rs3.getString("keterangan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        }
                    }
                    Sequel.AutoComitTrue();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    Valid.MyReport("rptPeriksaLab9.jasper", "report", "::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc", param);

                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCetakHasilLab8ActionPerformed

    private void MnCetakHasilLab9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakHasilLab9ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mencteak. Pilih dulu data yang mau dicetak. Klik No. Rawat pada tabel untuk memilih...!!!!");
        } else if (!(Kd2.getText().trim().equals(""))) {
            try {
                ps4.setString(1, tglPeriksa);
                ps4.setString(2, jamPeriksa);
                ps4.setString(3, Kd2.getText());
                rs = ps4.executeQuery();
                while (rs.next()) {
                    Sequel.AutoComitFalse();
                    kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + rs.getString("no_rawat") + "' order by tgl_masuk desc limit 1");
                    if (!kamar.equals("")) {
                        namakamar = kamar + ", " + Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal "
                                + " where kamar.kd_kamar='" + kamar + "' ");
                        kamar = "Kamar";
                    } else if (kamar.equals("")) {
                        kamar = "Poli";
                        namakamar = Sequel.cariIsi("select nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "
                                + "where reg_periksa.no_rawat='" + rs.getString("no_rawat") + "'");
                    }
                    Map<String, Object> param = new HashMap<>();
                    param.put("noperiksa", rs.getString("no_rawat"));
                    param.put("norm", rs.getString("no_rkm_medis"));
                    param.put("namapasien", rs.getString("nm_pasien"));
                    param.put("jkel", rs.getString("jk"));
                    param.put("umur", rs.getString("umur"));
                    param.put("pengirim", Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs.getString("dokter_perujuk")));
                    param.put("tanggal", rs.getString("tgl_periksa"));
                    param.put("penjab", rs.getString("nm_dokter"));
                    param.put("petugas", rs.getString("nama"));
                    param.put("jam", rs.getString("jam"));
                    param.put("alamat", rs.getString("Alamat"));
                    param.put("kamar", kamar);
                    param.put("namakamar", namakamar);

                    Sequel.queryu("delete from temporary");

                    ps2.setString(1, rs.getString("no_rawat"));
                    ps2.setString(2, Valid.SetTgl(rs.getString("tgl_periksa")));
                    ps2.setString(3, rs.getString("jam"));
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        Sequel.menyimpan("temporary", "'0','" + rs2.getString("nm_perawatan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        ps3.setString(1, rs.getString("no_rawat"));
                        ps3.setString(2, rs2.getString("kd_jenis_prw"));
                        ps3.setString(3, Valid.SetTgl(rs.getString("tgl_periksa")));
                        ps3.setString(4, rs.getString("jam"));
                        rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            Sequel.menyimpan("temporary", "'0','  " + rs3.getString("Pemeriksaan") + "','" + rs3.getString("nilai") + "','" + rs3.getString("satuan")
                                    + "','" + rs3.getString("nilai_rujukan") + "','" + rs3.getString("keterangan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        }
                    }
                    Sequel.AutoComitTrue();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    Valid.MyReport("rptPeriksaLab10.jasper", "report", "::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc", param);

                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCetakHasilLab9ActionPerformed

    private void MnCetakHasilLab10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakHasilLab10ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mencteak. Pilih dulu data yang mau dicetak. Klik No. Rawat pada tabel untuk memilih...!!!!");
        } else if (!(Kd2.getText().trim().equals(""))) {
            try {
                ps4.setString(1, tglPeriksa);
                ps4.setString(2, jamPeriksa);
                ps4.setString(3, Kd2.getText());
                rs = ps4.executeQuery();
                while (rs.next()) {
                    Sequel.AutoComitFalse();
                    kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + rs.getString("no_rawat") + "' order by tgl_masuk desc limit 1");
                    if (!kamar.equals("")) {
                        namakamar = kamar + ", " + Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal "
                                + " where kamar.kd_kamar='" + kamar + "' ");
                        kamar = "Kamar";
                    } else if (kamar.equals("")) {
                        kamar = "Poli";
                        namakamar = Sequel.cariIsi("select nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "
                                + "where reg_periksa.no_rawat='" + rs.getString("no_rawat") + "'");
                    }
                    Map<String, Object> param = new HashMap<>();
                    param.put("noperiksa", rs.getString("no_rawat"));
                    param.put("norm", rs.getString("no_rkm_medis"));
                    param.put("namapasien", rs.getString("nm_pasien"));
                    param.put("jkel", rs.getString("jk"));
                    param.put("umur", rs.getString("umur"));
                    param.put("pengirim", Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs.getString("dokter_perujuk")));
                    param.put("tanggal", rs.getString("tgl_periksa"));
                    param.put("penjab", rs.getString("nm_dokter"));
                    param.put("petugas", rs.getString("nama"));
                    param.put("jam", rs.getString("jam"));
                    param.put("alamat", rs.getString("Alamat"));
                    param.put("kamar", kamar);
                    param.put("namakamar", namakamar);

                    Sequel.queryu("delete from temporary");

                    ps2.setString(1, rs.getString("no_rawat"));
                    ps2.setString(2, Valid.SetTgl(rs.getString("tgl_periksa")));
                    ps2.setString(3, rs.getString("jam"));
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        Sequel.menyimpan("temporary", "'0','" + rs2.getString("nm_perawatan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        ps3.setString(1, rs.getString("no_rawat"));
                        ps3.setString(2, rs2.getString("kd_jenis_prw"));
                        ps3.setString(3, Valid.SetTgl(rs.getString("tgl_periksa")));
                        ps3.setString(4, rs.getString("jam"));
                        rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            Sequel.menyimpan("temporary", "'0','  " + rs3.getString("Pemeriksaan") + "','" + rs3.getString("nilai") + "','" + rs3.getString("satuan")
                                    + "','" + rs3.getString("nilai_rujukan") + "','" + rs3.getString("keterangan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data User");
                        }
                    }
                    Sequel.AutoComitTrue();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    Valid.MyReport("rptPeriksaLab11.jasper", "report", "::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc", param);

                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCetakHasilLab10ActionPerformed

    private void kdpnjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpnjKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpnj, kdpnj.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            btnPenjab.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnPenjab.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnPenjabActionPerformed(null);
        }
    }//GEN-LAST:event_kdpnjKeyPressed

    private void btnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjabActionPerformed
        akses.setform("DlgCariPeriksaLab");
        pasien.penjab.onCari();
        pasien.penjab.isCek();
        pasien.penjab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.penjab.setLocationRelativeTo(internalFrame1);
        pasien.penjab.setVisible(true);
    }//GEN-LAST:event_btnPenjabActionPerformed

    private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            // Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?",TPoli,kdpoli.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            // BtnUnitActionPerformed(null);
        } else {
            // Valid.pindah(evt,kddokter,TNoRM);
        }
    }//GEN-LAST:event_kdpoliKeyPressed

    private void BtnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnitActionPerformed
        TPoli.setText("");
        kdpoli.setText("");

        akses.setform("DlgCariPeriksaLab");

        poli.isCek();
        poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnUnitActionPerformed

    private void BtnBersihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBersihActionPerformed
        kdpnj.setText("");
        nmpnj.setText("");
        kdpoli.setText("");
        TPoli.setText("");
        ketAktif.setText(Sequel.cariIsi("select if(aktivasi_LIS='1','TELAH DIAKTIFKAN','NON AKTIF') cek from set_pjlab limit 1"));
    }//GEN-LAST:event_BtnBersihActionPerformed

    private void BtnBersihKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBersihKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBersihKeyPressed

    private void MnInapSemuaCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInapSemuaCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptLabRTISemuaCaraBayar.jasper", "report", "::[ Laporan Rekap Total Pemeriksaan Laboratorium Rawat Inap Semua Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " a.no_rawat, a.no_rkm_medis, a.nm_pasien, a.umur, a.status_lanjut, a.unit, a.png_jawab, a.tgl_periksa, sum(c.bhp) as bakhp, sum(c.bagian_laborat) as jp, "
                + " sum(c.bagian_rs) as jasa_rs, sum(c.bagian_dokter) as jasa_dokter, sum(c.biaya_item) as total "
                + " FROM ((SELECT periksa_lab.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, "
                + " petugas.nama, reg_periksa.status_lanjut, IF (reg_periksa.status_lanjut = 'RANAP',b.nm_bangsal,p.nm_poli) unit, penjab.png_jawab, periksa_lab.tgl_periksa, "
                + " periksa_lab.jam, periksa_lab.dokter_perujuk, periksa_lab.kd_dokter, dokter.nm_dokter FROM periksa_lab "
                + " INNER JOIN reg_periksa ON periksa_lab.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN petugas ON periksa_lab.nip = petugas.nip "
                + " INNER JOIN dokter ON periksa_lab.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki ON ki.no_rawat = reg_periksa.no_rawat AND ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p ON p.kd_poli = reg_periksa.kd_poli "
                + " WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and reg_periksa.status_lanjut='Ranap' "
                + " GROUP BY concat(periksa_lab.no_rawat, periksa_lab.tgl_periksa, periksa_lab.jam)) AS a "
                + " LEFT JOIN (SELECT periksa_lab.no_rawat, periksa_lab.tgl_periksa, jns_perawatan_lab.kd_jenis_prw, periksa_lab.jam, jns_perawatan_lab.nm_perawatan, periksa_lab.biaya "
                + " FROM periksa_lab INNER JOIN jns_perawatan_lab ON periksa_lab.kd_jenis_prw = jns_perawatan_lab.kd_jenis_prw) AS b "
                + " ON a.no_rawat = b.no_rawat AND a.tgl_periksa = b.tgl_periksa AND a.jam = b.jam LEFT JOIN (SELECT template_laboratorium.Pemeriksaan, detail_periksa_lab.nilai, "
                + " template_laboratorium.satuan, detail_periksa_lab.nilai_rujukan, detail_periksa_lab.bagian_rs, detail_periksa_lab.bhp, detail_periksa_lab.bagian_dokter, detail_periksa_lab.bagian_laborat, "
                + " detail_periksa_lab.biaya_item, detail_periksa_lab.keterangan, detail_periksa_lab.kd_jenis_prw, detail_periksa_lab.no_rawat, detail_periksa_lab.tgl_periksa, detail_periksa_lab.jam "
                + " FROM detail_periksa_lab INNER JOIN template_laboratorium ON detail_periksa_lab.id_template = template_laboratorium.id_template) as c  "
                + " on c.no_rawat = a.no_rawat and c.kd_jenis_prw = b.kd_jenis_prw and c.tgl_periksa = b.tgl_periksa and c.jam = b.jam) "
                + " where IFNULL(c.Pemeriksaan,'-') <> '-' GROUP BY a.no_rawat ORDER BY a.png_jawab, a.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInapSemuaCaraBayarActionPerformed

    private void MnInapDetailSemuaCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInapDetailSemuaCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptLabRDTISemuaCaraBayar.jasper", "report", "::[ Laporan Detail Rekap Total Pemeriksaan Laboratorium Rawat Inap Semua Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " a.no_rawat, a.no_rkm_medis, a.nm_pasien, a.umur, a.status_lanjut, a.unit, a.png_jawab, a.tgl_periksa, a.nm_dokter, c.Pemeriksaan as nm_pemeriksaan, "
                + " c.bhp as bakhp, c.bagian_laborat as jp, c.bagian_rs as jasa_rs, c.bagian_dokter as jasa_dokter, c.biaya_item as total "
                + " FROM ((SELECT periksa_lab.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, "
                + " petugas.nama, reg_periksa.status_lanjut, IF (reg_periksa.status_lanjut = 'RANAP',b.nm_bangsal,p.nm_poli) unit, penjab.png_jawab, periksa_lab.tgl_periksa, "
                + " periksa_lab.jam, periksa_lab.dokter_perujuk, periksa_lab.kd_dokter, dokter.nm_dokter FROM periksa_lab "
                + " INNER JOIN reg_periksa ON periksa_lab.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN petugas ON periksa_lab.nip = petugas.nip "
                + " INNER JOIN dokter ON periksa_lab.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki ON ki.no_rawat = reg_periksa.no_rawat AND ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p ON p.kd_poli = reg_periksa.kd_poli "
                + " WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and reg_periksa.status_lanjut='Ranap' "
                + " GROUP BY concat(periksa_lab.no_rawat, periksa_lab.tgl_periksa, periksa_lab.jam)) AS a "
                + " LEFT JOIN (SELECT periksa_lab.no_rawat, periksa_lab.tgl_periksa, jns_perawatan_lab.kd_jenis_prw, periksa_lab.jam, jns_perawatan_lab.nm_perawatan, periksa_lab.biaya "
                + " FROM periksa_lab INNER JOIN jns_perawatan_lab ON periksa_lab.kd_jenis_prw = jns_perawatan_lab.kd_jenis_prw) AS b "
                + " ON a.no_rawat = b.no_rawat AND a.tgl_periksa = b.tgl_periksa AND a.jam = b.jam LEFT JOIN (SELECT template_laboratorium.Pemeriksaan, detail_periksa_lab.nilai, "
                + " template_laboratorium.satuan, detail_periksa_lab.nilai_rujukan, detail_periksa_lab.bagian_rs, detail_periksa_lab.bhp, detail_periksa_lab.bagian_dokter, detail_periksa_lab.bagian_laborat, "
                + " detail_periksa_lab.biaya_item, detail_periksa_lab.keterangan, detail_periksa_lab.kd_jenis_prw, detail_periksa_lab.no_rawat, detail_periksa_lab.tgl_periksa, detail_periksa_lab.jam "
                + " FROM detail_periksa_lab INNER JOIN template_laboratorium ON detail_periksa_lab.id_template = template_laboratorium.id_template) as c "
                + " on c.no_rawat = a.no_rawat and c.kd_jenis_prw = b.kd_jenis_prw and c.tgl_periksa = b.tgl_periksa and c.jam = b.jam) where IFNULL(c.Pemeriksaan,'-') <> '-' ORDER BY a.png_jawab, a.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInapDetailSemuaCaraBayarActionPerformed

    private void MnInapPerCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInapPerCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptLabRTIPerCaraBayar.jasper", "report", "::[ Laporan Rekap Total Pemeriksaan Laboratorium Rawat Inap Per Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " a.no_rawat, a.no_rkm_medis, a.nm_pasien, a.umur, a.status_lanjut, a.unit, a.png_jawab, a.tgl_periksa, sum(c.bhp) as bakhp, sum(c.bagian_laborat) as jp, "
                + " sum(c.bagian_rs) as jasa_rs, sum(c.bagian_dokter) as jasa_dokter, sum(c.biaya_item) as total "
                + " FROM ((SELECT periksa_lab.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, "
                + " petugas.nama, reg_periksa.status_lanjut, IF (reg_periksa.status_lanjut = 'RANAP',b.nm_bangsal,p.nm_poli) unit, penjab.png_jawab, periksa_lab.tgl_periksa, "
                + " periksa_lab.jam, periksa_lab.dokter_perujuk, periksa_lab.kd_dokter, dokter.nm_dokter FROM periksa_lab "
                + " INNER JOIN reg_periksa ON periksa_lab.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN petugas ON periksa_lab.nip = petugas.nip "
                + " INNER JOIN dokter ON periksa_lab.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki ON ki.no_rawat = reg_periksa.no_rawat AND ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p ON p.kd_poli = reg_periksa.kd_poli "
                + " WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and reg_periksa.status_lanjut='Ranap' and reg_periksa.kd_pj='" + kdpnj.getText() + "' "
                + " GROUP BY concat(periksa_lab.no_rawat, periksa_lab.tgl_periksa, periksa_lab.jam)) AS a "
                + " LEFT JOIN (SELECT periksa_lab.no_rawat, periksa_lab.tgl_periksa, jns_perawatan_lab.kd_jenis_prw, periksa_lab.jam, jns_perawatan_lab.nm_perawatan, periksa_lab.biaya "
                + " FROM periksa_lab INNER JOIN jns_perawatan_lab ON periksa_lab.kd_jenis_prw = jns_perawatan_lab.kd_jenis_prw) AS b "
                + " ON a.no_rawat = b.no_rawat AND a.tgl_periksa = b.tgl_periksa AND a.jam = b.jam LEFT JOIN (SELECT template_laboratorium.Pemeriksaan, detail_periksa_lab.nilai, "
                + " template_laboratorium.satuan, detail_periksa_lab.nilai_rujukan, detail_periksa_lab.bagian_rs, detail_periksa_lab.bhp, detail_periksa_lab.bagian_dokter, detail_periksa_lab.bagian_laborat, "
                + " detail_periksa_lab.biaya_item, detail_periksa_lab.keterangan, detail_periksa_lab.kd_jenis_prw, detail_periksa_lab.no_rawat, detail_periksa_lab.tgl_periksa, detail_periksa_lab.jam "
                + " FROM detail_periksa_lab INNER JOIN template_laboratorium ON detail_periksa_lab.id_template = template_laboratorium.id_template) as c  "
                + " on c.no_rawat = a.no_rawat and c.kd_jenis_prw = b.kd_jenis_prw and c.tgl_periksa = b.tgl_periksa and c.jam = b.jam) "
                + " where IFNULL(c.Pemeriksaan,'-') <> '-' GROUP BY a.no_rawat ORDER BY a.png_jawab, a.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInapPerCaraBayarActionPerformed

    private void MnInapDetailPerCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInapDetailPerCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptLabRDTIPerCaraBayar.jasper", "report", "::[ Laporan Detail Rekap Total Pemeriksaan Laboratorium Rawat Inap Per Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " a.no_rawat, a.no_rkm_medis, a.nm_pasien, a.umur, a.status_lanjut, a.unit, a.png_jawab, a.tgl_periksa, a.nm_dokter, c.Pemeriksaan as nm_pemeriksaan, "
                + " c.bhp as bakhp, c.bagian_laborat as jp, c.bagian_rs as jasa_rs, c.bagian_dokter as jasa_dokter, c.biaya_item as total "
                + " FROM ((SELECT periksa_lab.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, "
                + " petugas.nama, reg_periksa.status_lanjut, IF (reg_periksa.status_lanjut = 'RANAP',b.nm_bangsal,p.nm_poli) unit, penjab.png_jawab, periksa_lab.tgl_periksa, "
                + " periksa_lab.jam, periksa_lab.dokter_perujuk, periksa_lab.kd_dokter, dokter.nm_dokter FROM periksa_lab "
                + " INNER JOIN reg_periksa ON periksa_lab.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN petugas ON periksa_lab.nip = petugas.nip "
                + " INNER JOIN dokter ON periksa_lab.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki ON ki.no_rawat = reg_periksa.no_rawat AND ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p ON p.kd_poli = reg_periksa.kd_poli "
                + " WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and reg_periksa.status_lanjut='Ranap' and reg_periksa.kd_pj='" + kdpnj.getText() + "' "
                + " GROUP BY concat(periksa_lab.no_rawat, periksa_lab.tgl_periksa, periksa_lab.jam)) AS a "
                + " LEFT JOIN (SELECT periksa_lab.no_rawat, periksa_lab.tgl_periksa, jns_perawatan_lab.kd_jenis_prw, periksa_lab.jam, jns_perawatan_lab.nm_perawatan, periksa_lab.biaya "
                + " FROM periksa_lab INNER JOIN jns_perawatan_lab ON periksa_lab.kd_jenis_prw = jns_perawatan_lab.kd_jenis_prw) AS b "
                + " ON a.no_rawat = b.no_rawat AND a.tgl_periksa = b.tgl_periksa AND a.jam = b.jam LEFT JOIN (SELECT template_laboratorium.Pemeriksaan, detail_periksa_lab.nilai, "
                + " template_laboratorium.satuan, detail_periksa_lab.nilai_rujukan, detail_periksa_lab.bagian_rs, detail_periksa_lab.bhp, detail_periksa_lab.bagian_dokter, detail_periksa_lab.bagian_laborat, "
                + " detail_periksa_lab.biaya_item, detail_periksa_lab.keterangan, detail_periksa_lab.kd_jenis_prw, detail_periksa_lab.no_rawat, detail_periksa_lab.tgl_periksa, detail_periksa_lab.jam "
                + " FROM detail_periksa_lab INNER JOIN template_laboratorium ON detail_periksa_lab.id_template = template_laboratorium.id_template) as c "
                + " on c.no_rawat = a.no_rawat and c.kd_jenis_prw = b.kd_jenis_prw and c.tgl_periksa = b.tgl_periksa and c.jam = b.jam) where IFNULL(c.Pemeriksaan,'-') <> '-' ORDER BY a.png_jawab, a.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInapDetailPerCaraBayarActionPerformed

    private void MnJalanSemuaCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJalanSemuaCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptLabRTJSemuaCaraBayar.jasper", "report", "::[ Laporan Rekap Total Pemeriksaan Laboratorium Rawat Jalan Semua Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " a.no_rawat, a.no_rkm_medis, a.nm_pasien, a.umur, a.status_lanjut, a.unit, a.png_jawab, a.tgl_periksa, sum(c.bhp) as bakhp, sum(c.bagian_laborat) as jp, "
                + " sum(c.bagian_rs) as jasa_rs, sum(c.bagian_dokter) as jasa_dokter, sum(c.biaya_item) as total "
                + " FROM ((SELECT periksa_lab.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, "
                + " petugas.nama, reg_periksa.status_lanjut, IF (reg_periksa.status_lanjut = 'RANAP',b.nm_bangsal,p.nm_poli) unit, penjab.png_jawab, periksa_lab.tgl_periksa, "
                + " periksa_lab.jam, periksa_lab.dokter_perujuk, periksa_lab.kd_dokter, dokter.nm_dokter FROM periksa_lab "
                + " INNER JOIN reg_periksa ON periksa_lab.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN petugas ON periksa_lab.nip = petugas.nip "
                + " INNER JOIN dokter ON periksa_lab.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki ON ki.no_rawat = reg_periksa.no_rawat AND ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p ON p.kd_poli = reg_periksa.kd_poli "
                + " WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and reg_periksa.status_lanjut='Ralan' "
                + " GROUP BY concat(periksa_lab.no_rawat, periksa_lab.tgl_periksa, periksa_lab.jam)) AS a "
                + " LEFT JOIN (SELECT periksa_lab.no_rawat, periksa_lab.tgl_periksa, jns_perawatan_lab.kd_jenis_prw, periksa_lab.jam, jns_perawatan_lab.nm_perawatan, periksa_lab.biaya "
                + " FROM periksa_lab INNER JOIN jns_perawatan_lab ON periksa_lab.kd_jenis_prw = jns_perawatan_lab.kd_jenis_prw) AS b "
                + " ON a.no_rawat = b.no_rawat AND a.tgl_periksa = b.tgl_periksa AND a.jam = b.jam LEFT JOIN (SELECT template_laboratorium.Pemeriksaan, detail_periksa_lab.nilai, "
                + " template_laboratorium.satuan, detail_periksa_lab.nilai_rujukan, detail_periksa_lab.bagian_rs, detail_periksa_lab.bhp, detail_periksa_lab.bagian_dokter, detail_periksa_lab.bagian_laborat, "
                + " detail_periksa_lab.biaya_item, detail_periksa_lab.keterangan, detail_periksa_lab.kd_jenis_prw, detail_periksa_lab.no_rawat, detail_periksa_lab.tgl_periksa, detail_periksa_lab.jam "
                + " FROM detail_periksa_lab INNER JOIN template_laboratorium ON detail_periksa_lab.id_template = template_laboratorium.id_template) as c  "
                + " on c.no_rawat = a.no_rawat and c.kd_jenis_prw = b.kd_jenis_prw and c.tgl_periksa = b.tgl_periksa and c.jam = b.jam) "
                + " where IFNULL(c.Pemeriksaan,'-') <> '-' GROUP BY a.no_rawat ORDER BY a.png_jawab, a.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJalanSemuaCaraBayarActionPerformed

    private void MnJalanDetailSemuaCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJalanDetailSemuaCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptLabRDTJSemuaCaraBayar.jasper", "report", "::[ Laporan Detail Rekap Total Pemeriksaan Laboratorium Rawat Jalan Semua Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " a.no_rawat, a.no_rkm_medis, a.nm_pasien, a.umur, a.status_lanjut, a.unit, a.png_jawab, a.tgl_periksa, a.nm_dokter, c.Pemeriksaan as nm_pemeriksaan, "
                + " c.bhp as bakhp, c.bagian_laborat as jp, c.bagian_rs as jasa_rs, c.bagian_dokter as jasa_dokter, c.biaya_item as total "
                + " FROM ((SELECT periksa_lab.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, "
                + " petugas.nama, reg_periksa.status_lanjut, IF (reg_periksa.status_lanjut = 'RANAP',b.nm_bangsal,p.nm_poli) unit, penjab.png_jawab, periksa_lab.tgl_periksa, "
                + " periksa_lab.jam, periksa_lab.dokter_perujuk, periksa_lab.kd_dokter, dokter.nm_dokter FROM periksa_lab "
                + " INNER JOIN reg_periksa ON periksa_lab.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN petugas ON periksa_lab.nip = petugas.nip "
                + " INNER JOIN dokter ON periksa_lab.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki ON ki.no_rawat = reg_periksa.no_rawat AND ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p ON p.kd_poli = reg_periksa.kd_poli "
                + " WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and reg_periksa.status_lanjut='Ralan' "
                + " GROUP BY concat(periksa_lab.no_rawat, periksa_lab.tgl_periksa, periksa_lab.jam)) AS a "
                + " LEFT JOIN (SELECT periksa_lab.no_rawat, periksa_lab.tgl_periksa, jns_perawatan_lab.kd_jenis_prw, periksa_lab.jam, jns_perawatan_lab.nm_perawatan, periksa_lab.biaya "
                + " FROM periksa_lab INNER JOIN jns_perawatan_lab ON periksa_lab.kd_jenis_prw = jns_perawatan_lab.kd_jenis_prw) AS b "
                + " ON a.no_rawat = b.no_rawat AND a.tgl_periksa = b.tgl_periksa AND a.jam = b.jam LEFT JOIN (SELECT template_laboratorium.Pemeriksaan, detail_periksa_lab.nilai, "
                + " template_laboratorium.satuan, detail_periksa_lab.nilai_rujukan, detail_periksa_lab.bagian_rs, detail_periksa_lab.bhp, detail_periksa_lab.bagian_dokter, detail_periksa_lab.bagian_laborat, "
                + " detail_periksa_lab.biaya_item, detail_periksa_lab.keterangan, detail_periksa_lab.kd_jenis_prw, detail_periksa_lab.no_rawat, detail_periksa_lab.tgl_periksa, detail_periksa_lab.jam "
                + " FROM detail_periksa_lab INNER JOIN template_laboratorium ON detail_periksa_lab.id_template = template_laboratorium.id_template) as c "
                + " on c.no_rawat = a.no_rawat and c.kd_jenis_prw = b.kd_jenis_prw and c.tgl_periksa = b.tgl_periksa and c.jam = b.jam) where IFNULL(c.Pemeriksaan,'-') <> '-' ORDER BY a.png_jawab, a.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJalanDetailSemuaCaraBayarActionPerformed

    private void MnJalanPerCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJalanPerCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptLabRTJPerCaraBayar.jasper", "report", "::[ Laporan Rekap Total Pemeriksaan Laboratorium Rawat Jalan Per Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " a.no_rawat, a.no_rkm_medis, a.nm_pasien, a.umur, a.status_lanjut, a.unit, a.png_jawab, a.tgl_periksa, sum(c.bhp) as bakhp, sum(c.bagian_laborat) as jp, "
                + " sum(c.bagian_rs) as jasa_rs, sum(c.bagian_dokter) as jasa_dokter, sum(c.biaya_item) as total "
                + " FROM ((SELECT periksa_lab.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, "
                + " petugas.nama, reg_periksa.status_lanjut, IF (reg_periksa.status_lanjut = 'RANAP',b.nm_bangsal,p.nm_poli) unit, penjab.png_jawab, periksa_lab.tgl_periksa, "
                + " periksa_lab.jam, periksa_lab.dokter_perujuk, periksa_lab.kd_dokter, dokter.nm_dokter FROM periksa_lab "
                + " INNER JOIN reg_periksa ON periksa_lab.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN petugas ON periksa_lab.nip = petugas.nip "
                + " INNER JOIN dokter ON periksa_lab.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki ON ki.no_rawat = reg_periksa.no_rawat AND ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p ON p.kd_poli = reg_periksa.kd_poli "
                + " WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and reg_periksa.status_lanjut='Ralan' and reg_periksa.kd_pj='" + kdpnj.getText() + "' "
                + " GROUP BY concat(periksa_lab.no_rawat, periksa_lab.tgl_periksa, periksa_lab.jam)) AS a "
                + " LEFT JOIN (SELECT periksa_lab.no_rawat, periksa_lab.tgl_periksa, jns_perawatan_lab.kd_jenis_prw, periksa_lab.jam, jns_perawatan_lab.nm_perawatan, periksa_lab.biaya "
                + " FROM periksa_lab INNER JOIN jns_perawatan_lab ON periksa_lab.kd_jenis_prw = jns_perawatan_lab.kd_jenis_prw) AS b "
                + " ON a.no_rawat = b.no_rawat AND a.tgl_periksa = b.tgl_periksa AND a.jam = b.jam LEFT JOIN (SELECT template_laboratorium.Pemeriksaan, detail_periksa_lab.nilai, "
                + " template_laboratorium.satuan, detail_periksa_lab.nilai_rujukan, detail_periksa_lab.bagian_rs, detail_periksa_lab.bhp, detail_periksa_lab.bagian_dokter, detail_periksa_lab.bagian_laborat, "
                + " detail_periksa_lab.biaya_item, detail_periksa_lab.keterangan, detail_periksa_lab.kd_jenis_prw, detail_periksa_lab.no_rawat, detail_periksa_lab.tgl_periksa, detail_periksa_lab.jam "
                + " FROM detail_periksa_lab INNER JOIN template_laboratorium ON detail_periksa_lab.id_template = template_laboratorium.id_template) as c  "
                + " on c.no_rawat = a.no_rawat and c.kd_jenis_prw = b.kd_jenis_prw and c.tgl_periksa = b.tgl_periksa and c.jam = b.jam) "
                + " where IFNULL(c.Pemeriksaan,'-') <> '-' GROUP BY a.no_rawat ORDER BY a.png_jawab, a.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJalanPerCaraBayarActionPerformed

    private void MnJalanDetailPerCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJalanDetailPerCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptLabRDTJPerCaraBayar.jasper", "report", "::[ Laporan Detail Rekap Total Pemeriksaan Laboratorium Rawat Jalan Per Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " a.no_rawat, a.no_rkm_medis, a.nm_pasien, a.umur, a.status_lanjut, a.unit, a.png_jawab, a.tgl_periksa, a.nm_dokter, c.Pemeriksaan as nm_pemeriksaan, "
                + " c.bhp as bakhp, c.bagian_laborat as jp, c.bagian_rs as jasa_rs, c.bagian_dokter as jasa_dokter, c.biaya_item as total "
                + " FROM ((SELECT periksa_lab.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, "
                + " petugas.nama, reg_periksa.status_lanjut, IF (reg_periksa.status_lanjut = 'RANAP',b.nm_bangsal,p.nm_poli) unit, penjab.png_jawab, periksa_lab.tgl_periksa, "
                + " periksa_lab.jam, periksa_lab.dokter_perujuk, periksa_lab.kd_dokter, dokter.nm_dokter FROM periksa_lab "
                + " INNER JOIN reg_periksa ON periksa_lab.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN petugas ON periksa_lab.nip = petugas.nip "
                + " INNER JOIN dokter ON periksa_lab.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki ON ki.no_rawat = reg_periksa.no_rawat AND ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p ON p.kd_poli = reg_periksa.kd_poli "
                + " WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and reg_periksa.status_lanjut='Ralan' and reg_periksa.kd_pj='" + kdpnj.getText() + "' "
                + " GROUP BY concat(periksa_lab.no_rawat, periksa_lab.tgl_periksa, periksa_lab.jam)) AS a "
                + " LEFT JOIN (SELECT periksa_lab.no_rawat, periksa_lab.tgl_periksa, jns_perawatan_lab.kd_jenis_prw, periksa_lab.jam, jns_perawatan_lab.nm_perawatan, periksa_lab.biaya "
                + " FROM periksa_lab INNER JOIN jns_perawatan_lab ON periksa_lab.kd_jenis_prw = jns_perawatan_lab.kd_jenis_prw) AS b "
                + " ON a.no_rawat = b.no_rawat AND a.tgl_periksa = b.tgl_periksa AND a.jam = b.jam LEFT JOIN (SELECT template_laboratorium.Pemeriksaan, detail_periksa_lab.nilai, "
                + " template_laboratorium.satuan, detail_periksa_lab.nilai_rujukan, detail_periksa_lab.bagian_rs, detail_periksa_lab.bhp, detail_periksa_lab.bagian_dokter, detail_periksa_lab.bagian_laborat, "
                + " detail_periksa_lab.biaya_item, detail_periksa_lab.keterangan, detail_periksa_lab.kd_jenis_prw, detail_periksa_lab.no_rawat, detail_periksa_lab.tgl_periksa, detail_periksa_lab.jam "
                + " FROM detail_periksa_lab INNER JOIN template_laboratorium ON detail_periksa_lab.id_template = template_laboratorium.id_template) as c "
                + " on c.no_rawat = a.no_rawat and c.kd_jenis_prw = b.kd_jenis_prw and c.tgl_periksa = b.tgl_periksa and c.jam = b.jam) where IFNULL(c.Pemeriksaan,'-') <> '-' ORDER BY a.png_jawab, a.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJalanDetailPerCaraBayarActionPerformed

    private void MnJalanPerPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJalanPerPoliActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptLabRTJPerPoli.jasper", "report", "::[ Laporan Rekap Total Pemeriksaan Laboratorium Rawat Jalan Per Unit/Poliklinik ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " a.no_rawat, a.no_rkm_medis, a.nm_pasien, a.umur, a.status_lanjut, a.unit, a.png_jawab, a.tgl_periksa, sum(c.bhp) as bakhp, sum(c.bagian_laborat) as jp, "
                + " sum(c.bagian_rs) as jasa_rs, sum(c.bagian_dokter) as jasa_dokter, sum(c.biaya_item) as total "
                + " FROM ((SELECT periksa_lab.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, "
                + " petugas.nama, reg_periksa.status_lanjut, IF (reg_periksa.status_lanjut = 'RANAP',b.nm_bangsal,p.nm_poli) unit, penjab.png_jawab, periksa_lab.tgl_periksa, "
                + " periksa_lab.jam, periksa_lab.dokter_perujuk, periksa_lab.kd_dokter, dokter.nm_dokter FROM periksa_lab "
                + " INNER JOIN reg_periksa ON periksa_lab.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN petugas ON periksa_lab.nip = petugas.nip "
                + " INNER JOIN dokter ON periksa_lab.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki ON ki.no_rawat = reg_periksa.no_rawat AND ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p ON p.kd_poli = reg_periksa.kd_poli "
                + " WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and reg_periksa.status_lanjut='Ralan' and reg_periksa.kd_poli='" + kdpoli.getText() + "' "
                + " GROUP BY concat(periksa_lab.no_rawat, periksa_lab.tgl_periksa, periksa_lab.jam)) AS a "
                + " LEFT JOIN (SELECT periksa_lab.no_rawat, periksa_lab.tgl_periksa, jns_perawatan_lab.kd_jenis_prw, periksa_lab.jam, jns_perawatan_lab.nm_perawatan, periksa_lab.biaya "
                + " FROM periksa_lab INNER JOIN jns_perawatan_lab ON periksa_lab.kd_jenis_prw = jns_perawatan_lab.kd_jenis_prw) AS b "
                + " ON a.no_rawat = b.no_rawat AND a.tgl_periksa = b.tgl_periksa AND a.jam = b.jam LEFT JOIN (SELECT template_laboratorium.Pemeriksaan, detail_periksa_lab.nilai, "
                + " template_laboratorium.satuan, detail_periksa_lab.nilai_rujukan, detail_periksa_lab.bagian_rs, detail_periksa_lab.bhp, detail_periksa_lab.bagian_dokter, detail_periksa_lab.bagian_laborat, "
                + " detail_periksa_lab.biaya_item, detail_periksa_lab.keterangan, detail_periksa_lab.kd_jenis_prw, detail_periksa_lab.no_rawat, detail_periksa_lab.tgl_periksa, detail_periksa_lab.jam "
                + " FROM detail_periksa_lab INNER JOIN template_laboratorium ON detail_periksa_lab.id_template = template_laboratorium.id_template) as c  "
                + " on c.no_rawat = a.no_rawat and c.kd_jenis_prw = b.kd_jenis_prw and c.tgl_periksa = b.tgl_periksa and c.jam = b.jam) "
                + " where IFNULL(c.Pemeriksaan,'-') <> '-' GROUP BY a.no_rawat ORDER BY a.png_jawab, a.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJalanPerPoliActionPerformed

    private void MnJalanDetailPerPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJalanDetailPerPoliActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptLabRDTJPerPoli.jasper", "report", "::[ Laporan Detail Rekap Total Pemeriksaan Laboratorium Rawat Jalan Per Unit/Poliklinik ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " a.no_rawat, a.no_rkm_medis, a.nm_pasien, a.umur, a.status_lanjut, a.unit, a.png_jawab, a.tgl_periksa, a.nm_dokter, c.Pemeriksaan as nm_pemeriksaan, "
                + " c.bhp as bakhp, c.bagian_laborat as jp, c.bagian_rs as jasa_rs, c.bagian_dokter as jasa_dokter, c.biaya_item as total "
                + " FROM ((SELECT periksa_lab.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, "
                + " petugas.nama, reg_periksa.status_lanjut, IF (reg_periksa.status_lanjut = 'RANAP',b.nm_bangsal,p.nm_poli) unit, penjab.png_jawab, periksa_lab.tgl_periksa, "
                + " periksa_lab.jam, periksa_lab.dokter_perujuk, periksa_lab.kd_dokter, dokter.nm_dokter FROM periksa_lab "
                + " INNER JOIN reg_periksa ON periksa_lab.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN petugas ON periksa_lab.nip = petugas.nip "
                + " INNER JOIN dokter ON periksa_lab.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki ON ki.no_rawat = reg_periksa.no_rawat AND ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p ON p.kd_poli = reg_periksa.kd_poli "
                + " WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and reg_periksa.status_lanjut='Ralan' and reg_periksa.kd_poli='" + kdpoli.getText() + "' "
                + " GROUP BY concat(periksa_lab.no_rawat, periksa_lab.tgl_periksa, periksa_lab.jam)) AS a "
                + " LEFT JOIN (SELECT periksa_lab.no_rawat, periksa_lab.tgl_periksa, jns_perawatan_lab.kd_jenis_prw, periksa_lab.jam, jns_perawatan_lab.nm_perawatan, periksa_lab.biaya "
                + " FROM periksa_lab INNER JOIN jns_perawatan_lab ON periksa_lab.kd_jenis_prw = jns_perawatan_lab.kd_jenis_prw) AS b "
                + " ON a.no_rawat = b.no_rawat AND a.tgl_periksa = b.tgl_periksa AND a.jam = b.jam LEFT JOIN (SELECT template_laboratorium.Pemeriksaan, detail_periksa_lab.nilai, "
                + " template_laboratorium.satuan, detail_periksa_lab.nilai_rujukan, detail_periksa_lab.bagian_rs, detail_periksa_lab.bhp, detail_periksa_lab.bagian_dokter, detail_periksa_lab.bagian_laborat, "
                + " detail_periksa_lab.biaya_item, detail_periksa_lab.keterangan, detail_periksa_lab.kd_jenis_prw, detail_periksa_lab.no_rawat, detail_periksa_lab.tgl_periksa, detail_periksa_lab.jam "
                + " FROM detail_periksa_lab INNER JOIN template_laboratorium ON detail_periksa_lab.id_template = template_laboratorium.id_template) as c "
                + " on c.no_rawat = a.no_rawat and c.kd_jenis_prw = b.kd_jenis_prw and c.tgl_periksa = b.tgl_periksa and c.jam = b.jam) where IFNULL(c.Pemeriksaan,'-') <> '-' ORDER BY a.png_jawab, a.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJalanDetailPerPoliActionPerformed

    private void MnSRSemuaCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSRSemuaCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptLabRTSRSemuaCaraBayar.jasper", "report", "::[ Laporan Rekap Total Pemeriksaan Laboratorium Semua Jenis Rawat Semua Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " a.no_rawat, a.no_rkm_medis, a.nm_pasien, a.umur, a.status_lanjut, a.unit, a.png_jawab, a.tgl_periksa, sum(c.bhp) as bakhp, sum(c.bagian_laborat) as jp, "
                + " sum(c.bagian_rs) as jasa_rs, sum(c.bagian_dokter) as jasa_dokter, sum(c.biaya_item) as total "
                + " FROM ((SELECT periksa_lab.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, "
                + " petugas.nama, reg_periksa.status_lanjut, IF (reg_periksa.status_lanjut = 'RANAP',b.nm_bangsal,p.nm_poli) unit, penjab.png_jawab, periksa_lab.tgl_periksa, "
                + " periksa_lab.jam, periksa_lab.dokter_perujuk, periksa_lab.kd_dokter, dokter.nm_dokter FROM periksa_lab "
                + " INNER JOIN reg_periksa ON periksa_lab.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN petugas ON periksa_lab.nip = petugas.nip "
                + " INNER JOIN dokter ON periksa_lab.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki ON ki.no_rawat = reg_periksa.no_rawat AND ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p ON p.kd_poli = reg_periksa.kd_poli "
                + " WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " GROUP BY concat(periksa_lab.no_rawat, periksa_lab.tgl_periksa, periksa_lab.jam)) AS a "
                + " LEFT JOIN (SELECT periksa_lab.no_rawat, periksa_lab.tgl_periksa, jns_perawatan_lab.kd_jenis_prw, periksa_lab.jam, jns_perawatan_lab.nm_perawatan, periksa_lab.biaya "
                + " FROM periksa_lab INNER JOIN jns_perawatan_lab ON periksa_lab.kd_jenis_prw = jns_perawatan_lab.kd_jenis_prw) AS b "
                + " ON a.no_rawat = b.no_rawat AND a.tgl_periksa = b.tgl_periksa AND a.jam = b.jam LEFT JOIN (SELECT template_laboratorium.Pemeriksaan, detail_periksa_lab.nilai, "
                + " template_laboratorium.satuan, detail_periksa_lab.nilai_rujukan, detail_periksa_lab.bagian_rs, detail_periksa_lab.bhp, detail_periksa_lab.bagian_dokter, detail_periksa_lab.bagian_laborat, "
                + " detail_periksa_lab.biaya_item, detail_periksa_lab.keterangan, detail_periksa_lab.kd_jenis_prw, detail_periksa_lab.no_rawat, detail_periksa_lab.tgl_periksa, detail_periksa_lab.jam "
                + " FROM detail_periksa_lab INNER JOIN template_laboratorium ON detail_periksa_lab.id_template = template_laboratorium.id_template) as c  "
                + " on c.no_rawat = a.no_rawat and c.kd_jenis_prw = b.kd_jenis_prw and c.tgl_periksa = b.tgl_periksa and c.jam = b.jam) "
                + " where IFNULL(c.Pemeriksaan,'-') <> '-' GROUP BY a.no_rawat ORDER BY a.png_jawab, a.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSRSemuaCaraBayarActionPerformed

    private void MnSRDetailSemuaCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSRDetailSemuaCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptLabRDTSRSemuaCaraBayar.jasper", "report", "::[ Laporan Detail Rekap Total Pemeriksaan Laboratorium Semua Jenis Rawat Semua Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " a.no_rawat, a.no_rkm_medis, a.nm_pasien, a.umur, a.status_lanjut, a.unit, a.png_jawab, a.tgl_periksa, a.nm_dokter, c.Pemeriksaan as nm_pemeriksaan, "
                + " c.bhp as bakhp, c.bagian_laborat as jp, c.bagian_rs as jasa_rs, c.bagian_dokter as jasa_dokter, c.biaya_item as total "
                + " FROM ((SELECT periksa_lab.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, "
                + " petugas.nama, reg_periksa.status_lanjut, IF (reg_periksa.status_lanjut = 'RANAP',b.nm_bangsal,p.nm_poli) unit, penjab.png_jawab, periksa_lab.tgl_periksa, "
                + " periksa_lab.jam, periksa_lab.dokter_perujuk, periksa_lab.kd_dokter, dokter.nm_dokter FROM periksa_lab "
                + " INNER JOIN reg_periksa ON periksa_lab.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN petugas ON periksa_lab.nip = petugas.nip "
                + " INNER JOIN dokter ON periksa_lab.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki ON ki.no_rawat = reg_periksa.no_rawat AND ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p ON p.kd_poli = reg_periksa.kd_poli "
                + " WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " GROUP BY concat(periksa_lab.no_rawat, periksa_lab.tgl_periksa, periksa_lab.jam)) AS a "
                + " LEFT JOIN (SELECT periksa_lab.no_rawat, periksa_lab.tgl_periksa, jns_perawatan_lab.kd_jenis_prw, periksa_lab.jam, jns_perawatan_lab.nm_perawatan, periksa_lab.biaya "
                + " FROM periksa_lab INNER JOIN jns_perawatan_lab ON periksa_lab.kd_jenis_prw = jns_perawatan_lab.kd_jenis_prw) AS b "
                + " ON a.no_rawat = b.no_rawat AND a.tgl_periksa = b.tgl_periksa AND a.jam = b.jam LEFT JOIN (SELECT template_laboratorium.Pemeriksaan, detail_periksa_lab.nilai, "
                + " template_laboratorium.satuan, detail_periksa_lab.nilai_rujukan, detail_periksa_lab.bagian_rs, detail_periksa_lab.bhp, detail_periksa_lab.bagian_dokter, detail_periksa_lab.bagian_laborat, "
                + " detail_periksa_lab.biaya_item, detail_periksa_lab.keterangan, detail_periksa_lab.kd_jenis_prw, detail_periksa_lab.no_rawat, detail_periksa_lab.tgl_periksa, detail_periksa_lab.jam "
                + " FROM detail_periksa_lab INNER JOIN template_laboratorium ON detail_periksa_lab.id_template = template_laboratorium.id_template) as c "
                + " on c.no_rawat = a.no_rawat and c.kd_jenis_prw = b.kd_jenis_prw and c.tgl_periksa = b.tgl_periksa and c.jam = b.jam) where IFNULL(c.Pemeriksaan,'-') <> '-' ORDER BY a.png_jawab, a.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSRDetailSemuaCaraBayarActionPerformed

    private void MnSRPerCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSRPerCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptLabRTSRPerCaraBayar.jasper", "report", "::[ Laporan Rekap Total Pemeriksaan Laboratorium Semua Jenis Rawat Per Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " a.no_rawat, a.no_rkm_medis, a.nm_pasien, a.umur, a.status_lanjut, a.unit, a.png_jawab, a.tgl_periksa, sum(c.bhp) as bakhp, sum(c.bagian_laborat) as jp, "
                + " sum(c.bagian_rs) as jasa_rs, sum(c.bagian_dokter) as jasa_dokter, sum(c.biaya_item) as total "
                + " FROM ((SELECT periksa_lab.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, "
                + " petugas.nama, reg_periksa.status_lanjut, IF (reg_periksa.status_lanjut = 'RANAP',b.nm_bangsal,p.nm_poli) unit, penjab.png_jawab, periksa_lab.tgl_periksa, "
                + " periksa_lab.jam, periksa_lab.dokter_perujuk, periksa_lab.kd_dokter, dokter.nm_dokter FROM periksa_lab "
                + " INNER JOIN reg_periksa ON periksa_lab.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN petugas ON periksa_lab.nip = petugas.nip "
                + " INNER JOIN dokter ON periksa_lab.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki ON ki.no_rawat = reg_periksa.no_rawat AND ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p ON p.kd_poli = reg_periksa.kd_poli "
                + " WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and reg_periksa.kd_pj='" + kdpnj.getText() + "' "
                + " GROUP BY concat(periksa_lab.no_rawat, periksa_lab.tgl_periksa, periksa_lab.jam)) AS a "
                + " LEFT JOIN (SELECT periksa_lab.no_rawat, periksa_lab.tgl_periksa, jns_perawatan_lab.kd_jenis_prw, periksa_lab.jam, jns_perawatan_lab.nm_perawatan, periksa_lab.biaya "
                + " FROM periksa_lab INNER JOIN jns_perawatan_lab ON periksa_lab.kd_jenis_prw = jns_perawatan_lab.kd_jenis_prw) AS b "
                + " ON a.no_rawat = b.no_rawat AND a.tgl_periksa = b.tgl_periksa AND a.jam = b.jam LEFT JOIN (SELECT template_laboratorium.Pemeriksaan, detail_periksa_lab.nilai, "
                + " template_laboratorium.satuan, detail_periksa_lab.nilai_rujukan, detail_periksa_lab.bagian_rs, detail_periksa_lab.bhp, detail_periksa_lab.bagian_dokter, detail_periksa_lab.bagian_laborat, "
                + " detail_periksa_lab.biaya_item, detail_periksa_lab.keterangan, detail_periksa_lab.kd_jenis_prw, detail_periksa_lab.no_rawat, detail_periksa_lab.tgl_periksa, detail_periksa_lab.jam "
                + " FROM detail_periksa_lab INNER JOIN template_laboratorium ON detail_periksa_lab.id_template = template_laboratorium.id_template) as c  "
                + " on c.no_rawat = a.no_rawat and c.kd_jenis_prw = b.kd_jenis_prw and c.tgl_periksa = b.tgl_periksa and c.jam = b.jam) "
                + " where IFNULL(c.Pemeriksaan,'-') <> '-' GROUP BY a.no_rawat ORDER BY a.png_jawab, a.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSRPerCaraBayarActionPerformed

    private void MnSRDetailPerCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSRDetailPerCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptLabRDTSRPerCaraBayar.jasper", "report", "::[ Laporan Detail Rekap Total Pemeriksaan Laboratorium Semua Jenis Rawat Per Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_lab WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " a.no_rawat, a.no_rkm_medis, a.nm_pasien, a.umur, a.status_lanjut, a.unit, a.png_jawab, a.tgl_periksa, a.nm_dokter, c.Pemeriksaan as nm_pemeriksaan, "
                + " c.bhp as bakhp, c.bagian_laborat as jp, c.bagian_rs as jasa_rs, c.bagian_dokter as jasa_dokter, c.biaya_item as total "
                + " FROM ((SELECT periksa_lab.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) AS umur, "
                + " petugas.nama, reg_periksa.status_lanjut, IF (reg_periksa.status_lanjut = 'RANAP',b.nm_bangsal,p.nm_poli) unit, penjab.png_jawab, periksa_lab.tgl_periksa, "
                + " periksa_lab.jam, periksa_lab.dokter_perujuk, periksa_lab.kd_dokter, dokter.nm_dokter FROM periksa_lab "
                + " INNER JOIN reg_periksa ON periksa_lab.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN petugas ON periksa_lab.nip = petugas.nip "
                + " INNER JOIN dokter ON periksa_lab.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki ON ki.no_rawat = reg_periksa.no_rawat AND ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p ON p.kd_poli = reg_periksa.kd_poli "
                + " WHERE periksa_lab.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND reg_periksa.kd_pj='" + kdpnj.getText() + "' "
                + " GROUP BY concat(periksa_lab.no_rawat, periksa_lab.tgl_periksa, periksa_lab.jam)) AS a "
                + " LEFT JOIN (SELECT periksa_lab.no_rawat, periksa_lab.tgl_periksa, jns_perawatan_lab.kd_jenis_prw, periksa_lab.jam, jns_perawatan_lab.nm_perawatan, periksa_lab.biaya "
                + " FROM periksa_lab INNER JOIN jns_perawatan_lab ON periksa_lab.kd_jenis_prw = jns_perawatan_lab.kd_jenis_prw) AS b "
                + " ON a.no_rawat = b.no_rawat AND a.tgl_periksa = b.tgl_periksa AND a.jam = b.jam LEFT JOIN (SELECT template_laboratorium.Pemeriksaan, detail_periksa_lab.nilai, "
                + " template_laboratorium.satuan, detail_periksa_lab.nilai_rujukan, detail_periksa_lab.bagian_rs, detail_periksa_lab.bhp, detail_periksa_lab.bagian_dokter, detail_periksa_lab.bagian_laborat, "
                + " detail_periksa_lab.biaya_item, detail_periksa_lab.keterangan, detail_periksa_lab.kd_jenis_prw, detail_periksa_lab.no_rawat, detail_periksa_lab.tgl_periksa, detail_periksa_lab.jam "
                + " FROM detail_periksa_lab INNER JOIN template_laboratorium ON detail_periksa_lab.id_template = template_laboratorium.id_template) as c "
                + " on c.no_rawat = a.no_rawat and c.kd_jenis_prw = b.kd_jenis_prw and c.tgl_periksa = b.tgl_periksa and c.jam = b.jam) where IFNULL(c.Pemeriksaan,'-') <> '-' ORDER BY a.png_jawab, a.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSRDetailPerCaraBayarActionPerformed

    private void MnPemeriksaanInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemeriksaanInapActionPerformed
        if (kdpnj.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan pilih dulu salah satu cara bayar pasiennya..!!");
            btnPenjab.requestFocus();
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
            param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem() + " Untuk Jenis Bayar " + nmpnj.getText());
            Valid.MyReport("rptRekapJenisPeriksaLabInap.jasper", "report", "::[ Laporan Rekap Total Jenis Pemeriksaan Laboratorium Rawat Inap Sesuai Cara Bayarnya ]::",
                    " SELECT tl.Pemeriksaan, Count(*) tot_pemeriksaan, tl.biaya_item trf_dsr, (count(*)*tl.biaya_item) tot_biaya "
                    + "FROM detail_periksa_lab d INNER JOIN template_laboratorium tl ON d.id_template = tl.id_template "
                    + "INNER JOIN reg_periksa r ON d.no_rawat = r.no_rawat INNER JOIN penjab p ON r.kd_pj = p.kd_pj "
                    + "WHERE d.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND r.status_lanjut='Ranap' "
                    + "and p.png_jawab like '%" + nmpnj.getText() + "%' GROUP BY tl.Pemeriksaan ORDER BY tl.Pemeriksaan", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPemeriksaanInapActionPerformed

    private void MnPemeriksaanJalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemeriksaanJalanActionPerformed
        if (kdpnj.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan pilih dulu salah satu cara bayar pasiennya..!!");
            btnPenjab.requestFocus();
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
            param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem() + " Untuk Jenis Bayar " + nmpnj.getText());
            Valid.MyReport("rptRekapJenisPeriksaLabJalan.jasper", "report", "::[ Laporan Rekap Total Jenis Pemeriksaan Laboratorium Rawat Jalan Sesuai Cara Bayarnya ]::",
                    " SELECT tl.Pemeriksaan, Count(*) tot_pemeriksaan, tl.biaya_item trf_dsr, (count(*)*tl.biaya_item) tot_biaya "
                    + "FROM detail_periksa_lab d INNER JOIN template_laboratorium tl ON d.id_template = tl.id_template "
                    + "INNER JOIN reg_periksa r ON d.no_rawat = r.no_rawat INNER JOIN penjab p ON r.kd_pj = p.kd_pj "
                    + "WHERE d.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND r.status_lanjut='Ralan' "
                    + "and p.png_jawab like '%" + nmpnj.getText() + "%' GROUP BY tl.Pemeriksaan ORDER BY tl.Pemeriksaan", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPemeriksaanJalanActionPerformed

    private void MnPemeriksaanSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemeriksaanSemuaActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem() + " Untuk Semua Jenis Cara Bayar");
        Valid.MyReport("rptRekapJenisPeriksaLabSemua.jasper", "report", "::[ Laporan Rekap Total Jenis Pemeriksaan Laboratorium Semua Rawat Sesuai Cara Bayarnya ]::",
                " SELECT tl.Pemeriksaan, Count(*) tot_pemeriksaan, tl.biaya_item trf_dsr, (count(*)*tl.biaya_item) tot_biaya "
                + "FROM detail_periksa_lab d INNER JOIN template_laboratorium tl ON d.id_template = tl.id_template "
                + "INNER JOIN reg_periksa r ON d.no_rawat = r.no_rawat INNER JOIN penjab p ON r.kd_pj = p.kd_pj "
                + "WHERE d.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "GROUP BY tl.Pemeriksaan ORDER BY tl.Pemeriksaan", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPemeriksaanSemuaActionPerformed

    private void MnPendapatanRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPendapatanRalanActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        param.put("judul", "Laporan Rekap Pendapatan Laboratorium Rawat Jalan");
        Valid.MyReport("rptRekapPendapatanLaboratorium.jasper", "report", "::[ Laporan Rekap Pendapatan Laboratorium Rawat Jalan ]::",
                "select p.png_jawab,sum(tl.biaya_item) biaya from detail_periksa_lab pl "
                + "INNER JOIN template_laboratorium tl on tl.id_template=pl.id_template "
                + "INNER JOIN reg_periksa r on r.no_rawat=pl.no_rawat INNER JOIN penjab p on r.kd_pj=p.kd_pj "
                + "WHERE pl.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and r.status_lanjut='ralan' "
                + "GROUP BY p.png_jawab ORDER BY sum(tl.biaya_item) DESC, p.png_jawab ", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPendapatanRalanActionPerformed

    private void MnPendapatanRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPendapatanRanapActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        param.put("judul", "Laporan Rekap Pendapatan Laboratorium Rawat Inap");
        Valid.MyReport("rptRekapPendapatanLaboratorium.jasper", "report", "::[ Laporan Rekap Pendapatan Laboratorium Rawat Inap ]::",
                "select p.png_jawab,sum(tl.biaya_item) biaya from detail_periksa_lab pl "
                + "INNER JOIN template_laboratorium tl on tl.id_template=pl.id_template "
                + "INNER JOIN reg_periksa r on r.no_rawat=pl.no_rawat INNER JOIN penjab p on r.kd_pj=p.kd_pj "
                + "WHERE pl.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and r.status_lanjut='ranap' "
                + "GROUP BY p.png_jawab ORDER BY sum(tl.biaya_item) DESC, p.png_jawab ", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPendapatanRanapActionPerformed

    private void MnPendapatanSemuaRawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPendapatanSemuaRawatActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        param.put("judul", "Laporan Rekap Pendapatan Laboratorium Semua Rawat");
        Valid.MyReport("rptRekapPendapatanLaboratorium.jasper", "report", "::[ Laporan Rekap Pendapatan Laboratorium Semua Rawat ]::",
                "select p.png_jawab,sum(tl.biaya_item) biaya from detail_periksa_lab pl "
                + "INNER JOIN template_laboratorium tl on tl.id_template=pl.id_template "
                + "INNER JOIN reg_periksa r on r.no_rawat=pl.no_rawat INNER JOIN penjab p on r.kd_pj=p.kd_pj "
                + "WHERE pl.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "GROUP BY p.png_jawab ORDER BY sum(tl.biaya_item) DESC, p.png_jawab ", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPendapatanSemuaRawatActionPerformed

    private void MnLihatHasilLISActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLihatHasilLISActionPerformed
        cekKeLIS = 0;
        cekdataLIS = 0;
        cekKeLIS = Sequel.cariInteger("select count(1) cek from lis_reg where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam_periksa='" + jamPeriksa + "'");
        cekdataLIS = Sequel.cariInteger("select count(1) cek from lis_hasil_periksa_lab where no_lab='" + nolab + "'");

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu data pasiennya pada tabel dengan cara klik pada no.rawatnya ...!!!!");
            tbLab.requestFocus();
        } else if (Sequel.cariIsi("select aktivasi_LIS from set_pjlab").equals("0")) {
            JOptionPane.showMessageDialog(null, "Bridging dengan alat laboratorium (LIS) belum diaktifkan...!!!");
        } else if (cekKeLIS == 0) {
            JOptionPane.showMessageDialog(null, "Data hasil pemeriksaan laboratorium (LIS) tidak ditemukan ...!!!!");
            tbLab.requestFocus();
        } else if (cekdataLIS == 0) {
            JOptionPane.showMessageDialog(null, "Data hasil pemeriksaan laboratorium tidak tersedia/belum diproses...!!!!");
            tbLab.requestFocus();
        } else {
            WindowDataLIS.setSize(820, internalFrame1.getHeight() - 40);
            WindowDataLIS.setLocationRelativeTo(internalFrame1);
            WindowDataLIS.setVisible(true);
            tampilLIS();
        }
    }//GEN-LAST:event_MnLihatHasilLISActionPerformed

    private void MnKirimLISActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKirimLISActionPerformed
        cekbayar = "";
        cekKeLIS = 0;
        cekbayar = Sequel.cariIsi("select stts_bayar from periksa_lab where no_rawat='" + Kd2.getText() + "'");
        cekKeLIS = Sequel.cariInteger("select count(1) cek from lis_reg where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam_periksa='" + jamPeriksa + "'");

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu data pasiennya pada tabel dengan cara klik pada no.rawatnya ...!!!!");
            tbLab.requestFocus();
        } else if (cekbayar.equals("BAYAR")) {
            JOptionPane.showMessageDialog(null, "Pasien ini telah menyelesaikan transaksi pembayaran...!!!!");
        } else if (ketklinis.getText().trim().equals("") || ketklinis.getText().trim().equals(" ")) {
            JOptionPane.showMessageDialog(null, "Keterangan klinis harus diisi, jika memang tdk. ada isi dengan tanda (-)");
            ketklinis.requestFocus();
        } else if (cekKeLIS >= 1) {
            JOptionPane.showMessageDialog(null, "Data pemeriksaan Lab. dg. no. rawat " + Kd2.getText() + ", No. Lab. " + nolab + ",      \n"
                    + "tgl. periksa Lab. " + tglPeriksa + ", jam " + jamPeriksa + " sudah dikirim ke LIS...!!");
        } else {
            if (Sequel.cariIsi("select aktivasi_LIS from set_pjlab").equals("1")) {
                TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + Kd2.getText() + "'"));
                TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
                TnoTelpPx.setText(Sequel.cariIsi("select no_tlp from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
                TtglPeriksa.setText(tglPeriksa);
                TjamPeriksa.setText(jamPeriksa);
                Tdiagnosa.setText(diagnosa_ok);
                TketKlinis.setText(ketklinis.getText());
                TkdDokter.setText(kddokter);
                TnmDokter.setText(dokter_pengirim.getText());
                TkdUnit.setText(kdunit);
                TnmUnit.setText(nm_unit);
                TkdPenjab.setText(kdpenjab);
                TnmPenjab.setText(caraByr.getText());
                TnoTelpDokter.setText(Sequel.cariIsi("select no_telp from petugas where nip='" + kddokter + "'"));
                TkdFaskes.setText(kdunit);
                TnmFaskes.setText(nm_unit);
                TalamatPerujuk.setText("-");
                TnoTlpnFaskes.setText(notelpFaskes);
                
                WindowKirimLIS.setSize(605, 346);
                WindowKirimLIS.setLocationRelativeTo(internalFrame1);
                WindowKirimLIS.setVisible(true);
                BtnKirim.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Bridging dengan alat laboratorium (LIS) belum diaktifkan...!!!");
                tampil();
            }
        }
    }//GEN-LAST:event_MnKirimLISActionPerformed

    private void ketklinisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ketklinisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ketklinisKeyPressed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowDataLIS.dispose();
        emptDataLIS();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void nRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nRawatKeyPressed

    }//GEN-LAST:event_nRawatKeyPressed

    private void nLabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nLabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nLabKeyPressed

    private void MnLapRekapPerPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLapRekapPerPasienActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (NoRawat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik salah satu nama pasiennya terlebih dulu pada tabel sesuai dg. tgl. pemeriksaannya ...!!!!");
            tbLab.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//            int row = tabMode.getRowCount();
//            for (i = 0; i < row; i++) {
//                Sequel.menyimpan("temporary", "'0','"
//                        + tabMode.getValueAt(i, 0).toString() + "','"
//                        + tabMode.getValueAt(i, 1).toString() + "','"
//                        + tabMode.getValueAt(i, 2).toString() + "','"
//                        + tabMode.getValueAt(i, 3).toString() + "','"
//                        + tabMode.getValueAt(i, 4).toString() + "','"
//                        + tabMode.getValueAt(i, 5).toString() + "','"
//                        + tabMode.getValueAt(i, 6).toString() + "','"
//                        + tabMode.getValueAt(i, 7).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Periksa Lab");
//            }
            ProsesSimpanRekapData();

            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDataLab.jasper", "report", "::[ Rekap Data Pemeriksaan Laboratorium Per Pasien]::",
                    "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, "
                    + "temp13, temp14, temp14, temp15, temp16 from temporary order by no asc", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLapRekapPerPasienActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnCloseIn4.requestFocus();
        } else if (tabMode1.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));

            param.put("nmPasien", Sequel.cariIsi("select p.nm_pasien from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where rp.no_rawat='" + NoRawat.getText() + "'"));
            param.put("noLab", nolab);
            param.put("norawat", NoRawat.getText());
            param.put("norm", Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + NoRawat.getText() + "'"));
            param.put("jkCaraByr", Sequel.cariIsi("SELECT concat(IF (p.jk = 'L','Laki-laki','Perempuan'),' / ',pj.png_jawab) FROM reg_periksa rp "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN penjab pj ON pj.kd_pj=rp.kd_pj WHERE rp.no_rawat = '" + NoRawat.getText() + "'"));
            param.put("tglLhr_umur", Sequel.cariIsi("SELECT concat(DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' ',rp.sttsumur,'.') "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis WHERE rp.no_rawat = '" + NoRawat.getText() + "'"));
            param.put("tglPeriksa", Valid.SetTglINDONESIA(tglPeriksa) + " - " + jamPeriksa);
            param.put("drPengirim", tbLab.getValueAt(tbLab.getSelectedRow(), 6).toString());
            param.put("drLAB", tbLab.getValueAt(tbLab.getSelectedRow(), 7).toString());
            param.put("tglSurat", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("SELECT DATE(waktu_insert) FROM lis_hasil_data_pasien WHERE no_lab='" + nolab + "'")));

            kamar = "";
            kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + NoRawat.getText() + "' order by tgl_masuk desc limit 1");
            if (!kamar.equals("")) {
                param.put("labelUnit", "Rg. Perawatan");
                param.put("nmUnit", Sequel.cariIsi("select b.nm_bangsal from bangsal b inner join kamar k on b.kd_bangsal=k.kd_bangsal where k.kd_kamar='" + kamar + "' "));
            } else if (kamar.equals("")) {
                param.put("labelUnit", "Poliklinik/Inst.");
                param.put("nmUnit", Sequel.cariIsi("select pl.nm_poli from poliklinik pl inner join reg_periksa rp on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + NoRawat.getText() + "'"));
            }

            Valid.MyReport("rptHasilLIS.jasper", "report", "::[ Lembar Hasil Pemeriksaan Laboratorium (LIS) ]::", "SELECT * FROM temporary_lis", param);
            BtnCloseIn4ActionPerformed(evt);
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

    private void BtnKirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKirimActionPerformed
        mas_lis.kirim(Kd2.getText(),TtglPeriksa.getText(), TjamPeriksa.getText(),
                Tdiagnosa.getText(), TketKlinis.getText(), TkdDokter.getText(), TkdUnit.getText(), 
                TkdPenjab.getText(),TnmDokter.getText(), TnmUnit.getText(), TnmPenjab.getText(), 
                TnoTelpDokter.getText(), TkdFaskes.getText(), TnmFaskes.getText(), 
                TalamatPerujuk.getText(), TnoTlpnFaskes.getText()
        );

        Sequel.mengedit("petugas", "nip='" + TkdDokter.getText() + "'", "no_telp='" + TnoTelpDokter.getText() + "'");
        Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "no_tlp='" + TnoTelpPx.getText() + "'");
        
        if (status_rawat.equals("Ranap")) {
            Sequel.mengedit("bangsal", "nm_gedung='" + nmgedung + "'", "no_tlp='" + TnoTlpnFaskes.getText() + "'");
        }

        if (!TkdUnit.getText().equals(TkdFaskes.getText())) {
            Sequel.mengedit("master_nama_rujukan", "kd_rujukan='" + TkdFaskes.getText() + "'", "no_tlp='" + TnoTlpnFaskes.getText() + "'");
        }

        tampil();
        System.out.println("Data telah terkirim ke alat pemeriksaan LIS...!!!!");
        WindowKirimLIS.dispose();
        emptTeksLIS();
    }//GEN-LAST:event_BtnKirimActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowKirimLIS.dispose();
        emptTeksLIS();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void btnFaskesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFaskesActionPerformed
        akses.setform("DlgCariPeriksaLab");
        faskes.emptTeks();
        faskes.isCek();
        faskes.ChkInput.setSelected(false);
        faskes.tampil();
        faskes.setSize(833, internalFrame1.getHeight() - 40);
        faskes.setLocationRelativeTo(internalFrame1);
        faskes.setVisible(true);
    }//GEN-LAST:event_btnFaskesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCariPeriksaLab dialog = new DlgCariPeriksaLab(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBersih;
    private widget.Button BtnCari;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKirim;
    private widget.Button BtnPrint;
    private widget.Button BtnUnit;
    private widget.TextBox Kd2;
    private javax.swing.JMenuItem MnCetakHasilLab;
    private javax.swing.JMenuItem MnCetakHasilLab1;
    private javax.swing.JMenuItem MnCetakHasilLab10;
    private javax.swing.JMenuItem MnCetakHasilLab2;
    private javax.swing.JMenuItem MnCetakHasilLab3;
    private javax.swing.JMenuItem MnCetakHasilLab4;
    private javax.swing.JMenuItem MnCetakHasilLab5;
    private javax.swing.JMenuItem MnCetakHasilLab6;
    private javax.swing.JMenuItem MnCetakHasilLab7;
    private javax.swing.JMenuItem MnCetakHasilLab8;
    private javax.swing.JMenuItem MnCetakHasilLab9;
    private javax.swing.JMenuItem MnCetakNota;
    private javax.swing.JMenuItem MnInapDetailPerCaraBayar;
    private javax.swing.JMenuItem MnInapDetailSemuaCaraBayar;
    private javax.swing.JMenuItem MnInapPerCaraBayar;
    private javax.swing.JMenuItem MnInapSemuaCaraBayar;
    private javax.swing.JMenuItem MnJalanDetailPerCaraBayar;
    private javax.swing.JMenuItem MnJalanDetailPerPoli;
    private javax.swing.JMenuItem MnJalanDetailSemuaCaraBayar;
    private javax.swing.JMenuItem MnJalanPerCaraBayar;
    private javax.swing.JMenuItem MnJalanPerPoli;
    private javax.swing.JMenuItem MnJalanSemuaCaraBayar;
    private javax.swing.JMenuItem MnKirimLIS;
    private javax.swing.JMenuItem MnLapRekapPerPasien;
    private javax.swing.JMenuItem MnLihatHasilLIS;
    private javax.swing.JMenuItem MnPemeriksaanInap;
    private javax.swing.JMenuItem MnPemeriksaanJalan;
    private javax.swing.JMenuItem MnPemeriksaanSemua;
    private javax.swing.JMenuItem MnPendapatanRalan;
    private javax.swing.JMenuItem MnPendapatanRanap;
    private javax.swing.JMenuItem MnPendapatanSemuaRawat;
    private javax.swing.JMenuItem MnSRDetailPerCaraBayar;
    private javax.swing.JMenuItem MnSRDetailSemuaCaraBayar;
    private javax.swing.JMenuItem MnSRPerCaraBayar;
    private javax.swing.JMenuItem MnSRSemuaCaraBayar;
    private javax.swing.JMenuItem MnUbah;
    private widget.TextBox NoRawat;
    private widget.ScrollPane Scroll1;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TPasien;
    private widget.TextBox TPoli;
    private widget.TextBox TalamatPerujuk;
    private widget.TextBox Tdiagnosa;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.TextBox TjamPeriksa;
    private widget.TextBox TkdDokter;
    private widget.TextBox TkdFaskes;
    private widget.TextBox TkdPenjab;
    private widget.TextBox TkdUnit;
    private widget.TextBox TketKlinis;
    private widget.TextBox TnmDokter;
    private widget.TextBox TnmFaskes;
    private widget.TextBox TnmPenjab;
    private widget.TextBox TnmUnit;
    private widget.TextBox TnoTelpDokter;
    private widget.TextBox TnoTelpPx;
    private widget.TextBox TnoTlpnFaskes;
    private widget.TextBox TtglPeriksa;
    private javax.swing.JDialog WindowDataLIS;
    private javax.swing.JDialog WindowKirimLIS;
    private widget.Button btnFaskes;
    private widget.Button btnPasien;
    private widget.Button btnPenjab;
    private widget.Button btnPetugas;
    private widget.TextBox caraByr;
    private widget.TextBox dAwal;
    private widget.TextBox dataPasien;
    private widget.TextBox dokter_pengirim;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
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
    private javax.swing.JMenu jMnCetakHasilLab;
    private javax.swing.JMenu jMnLapInap;
    private javax.swing.JMenu jMnLapJalan;
    private javax.swing.JMenu jMnLapPemeriksaan;
    private javax.swing.JMenu jMnLapPendapatan;
    private javax.swing.JMenu jMnLapSemuaRawat;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kdmem;
    private widget.TextBox kdpnj;
    private widget.TextBox kdpoli;
    private widget.TextBox kdptg;
    public widget.Label ketAktif;
    private widget.TextBox ketklinis;
    private widget.TextBox kklinis;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label9;
    private widget.TextBox nLab;
    private widget.TextBox nRawat;
    private widget.TextBox nmmem;
    private widget.TextBox nmpnj;
    private widget.TextBox nmptg;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.panelisi panelisi5;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbDataLIS;
    private widget.Table tbLab;
    private widget.Tanggal tglNota;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        try {
            Valid.tabelKosong(tabMode);
            ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
            ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            ps.setString(3, "%" + NoRawat.getText() + "%");
            ps.setString(4, "%" + kdmem.getText() + "%");
            ps.setString(5, "%" + kdptg.getText() + "%");
            ps.setString(6, "%" + TCari.getText().trim() + "%");
            ps.setString(7, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
            ps.setString(8, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            ps.setString(9, "%" + NoRawat.getText() + "%");
            ps.setString(10, "%" + kdmem.getText() + "%");
            ps.setString(11, "%" + kdptg.getText() + "%");
            ps.setString(12, "%" + TCari.getText().trim() + "%");
            ps.setString(13, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
            ps.setString(14, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            ps.setString(15, "%" + NoRawat.getText() + "%");
            ps.setString(16, "%" + kdmem.getText() + "%");
            ps.setString(17, "%" + kdptg.getText() + "%");
            ps.setString(18, "%" + TCari.getText().trim() + "%");
            ps.setString(19, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
            ps.setString(20, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            ps.setString(21, "%" + NoRawat.getText() + "%");
            ps.setString(22, "%" + kdmem.getText() + "%");
            ps.setString(23, "%" + kdptg.getText() + "%");
            ps.setString(24, "%" + TCari.getText().trim() + "%");
            ps.setString(25, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
            ps.setString(26, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            ps.setString(27, "%" + NoRawat.getText() + "%");
            ps.setString(28, "%" + kdmem.getText() + "%");
            ps.setString(29, "%" + kdptg.getText() + "%");
            ps.setString(30, "%" + TCari.getText().trim() + "%");
            ps.setString(31, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
            ps.setString(32, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            ps.setString(33, "%" + NoRawat.getText() + "%");
            ps.setString(34, "%" + kdmem.getText() + "%");
            ps.setString(35, "%" + kdptg.getText() + "%");
            ps.setString(36, "%" + TCari.getText().trim() + "%");
            rs = ps.executeQuery();
            ttl = 0;
            while (rs.next()) {
                kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + rs.getString("no_rawat") + "' order by tgl_masuk desc limit 1");
                if (!kamar.equals("")) {
                    namakamar = kamar + ", " + Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal "
                            + " where kamar.kd_kamar='" + kamar + "' ");
                    kamar = "Kamar";
                } else if (kamar.equals("")) {
                    kamar = "Poliklinik";
                    namakamar = Sequel.cariIsi("select nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "
                            + "where reg_periksa.no_rawat='" + rs.getString("no_rawat") + "'");
                }
                tabMode.addRow(new Object[]{
                    rs.getString("no_rawat"),
                    rs.getString("no_lab"),
                    "No. RM : " + rs.getString("no_rkm_medis") + ", Nama : " + rs.getString("nm_pasien") + ", Umur : " + rs.getString("umur_thn") + " (" + kamar + " : " + namakamar + ")",
                    rs.getString("nama"),
                    rs.getString("tgl_periksa"),
                    rs.getString("jam"),
                    Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs.getString("dokter_perujuk")),
                    rs.getString("nm_dokter"), rs.getString("hasil")
                }
                );

//                tabMode.addRow(new Object[]{"", "", "Pemeriksaan", "Hasil", "Satuan", "Nilai Rujukan", "Keterangan"});
                tabMode.addRow(new Object[]{"", "", "Item Pemeriksaan :", "", "", "", "", ""});
                ps2.setString(1, rs.getString("no_rawat"));
                ps2.setString(2, rs.getString("tgl_periksa"));
                ps2.setString(3, rs.getString("jam"));
                rs2 = ps2.executeQuery();
                item = 0;
                while (rs2.next()) {
                    item = item + rs2.getDouble("biaya");
                    ttl = ttl + rs2.getDouble("biaya");
                    tabMode.addRow(new Object[]{
                        "",
                        "",
                        rs2.getString("nm_perawatan") + " " + Valid.SetAngka(rs2.getDouble("biaya")).replace("0", ""),
                        "",
                        "",
                        "",
                        "",
                        ""
                    });

                    ps3.setString(1, rs.getString("no_rawat"));
                    ps3.setString(2, rs2.getString("kd_jenis_prw"));
                    ps3.setString(3, rs.getString("tgl_periksa"));
                    ps3.setString(4, rs.getString("jam"));
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        item = item + rs3.getDouble("biaya_item");
                        ttl = ttl + rs3.getDouble("biaya_item");
                        tabMode.addRow(new Object[]{
                            "",
                            "",
                            "     " + rs3.getString("Pemeriksaan") + " " + Valid.SetAngka(rs3.getDouble("biaya_item")),
                            rs3.getString("nilai"),
                            rs3.getString("satuan"),
                            rs3.getString("nilai_rujukan"),
                            rs3.getString("keterangan")
                        });
                    }
                }
                if (item > 0) {
                    tabMode.addRow(new Object[]{"", "", "Total Biaya Pemeriksaan : Rp. " + Valid.SetAngka(item), "", "", "", "", ""});
                }
            }
            if (ttl > 0) {
                tabMode.addRow(new Object[]{">>", ">>>", "GRAND TOTAL : Rp. " + Valid.SetAngka(ttl), "", "", "", "", ""});
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void SetNoRw(String norw) {
        NoRawat.setText(norw);
        tampil();
        Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'", Tgl1);
    }

    private void getData() {
        Kd2.setText("");
        drLab = "";
        status_rawat = "";
        nolab = "";
        kddokter = "";
        tglPeriksa = "";
        jamPeriksa = "";
        kdunit = "";
        nm_unit = "";
        kdpenjab = "";
        notelpFaskes = "";
        nmgedung = "";
        diagnosa_ok = "";
        diagnosa_cek1 = 0;
        diagnosa_cek2 = 0;

        if (tbLab.getSelectedRow() != -1) {
            Kd2.setText(tbLab.getValueAt(tbLab.getSelectedRow(), 0).toString());
            nRawat.setText(tbLab.getValueAt(tbLab.getSelectedRow(), 0).toString());
            NoRawat.setText(nRawat.getText());
            nolab = tbLab.getValueAt(tbLab.getSelectedRow(), 1).toString();
            nLab.setText(tbLab.getValueAt(tbLab.getSelectedRow(), 1).toString());
            dataPasien.setText(tbLab.getValueAt(tbLab.getSelectedRow(), 2).toString());
            kklinis.setText(Sequel.cariIsi("select ket_klinis from lis_reg where no_lab='" + nolab + "'"));
            tglPeriksa = tbLab.getValueAt(tbLab.getSelectedRow(), 4).toString();
            jamPeriksa = tbLab.getValueAt(tbLab.getSelectedRow(), 5).toString();
            drLab = tbLab.getValueAt(tbLab.getSelectedRow(), 7).toString();
            kdpenjab = Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + Kd2.getText() + "'");
            caraByr.setText(Sequel.cariIsi("select png_jawab from penjab where kd_pj='" + kdpenjab + "'"));
            status_rawat = Sequel.cariIsi("select status_lanjut from reg_periksa where no_rawat='" + Kd2.getText() + "'");

            if (status_rawat.equals("Ralan")) {
                kddokter = Sequel.cariIsi("select dokter_perujuk from periksa_lab where no_rawat='" + Kd2.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'");
                dokter_pengirim.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddokter + "'"));
                kdunit = Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + Kd2.getText() + "'");
                nm_unit = Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdunit + "'");
                notelpFaskes = "0";
                diagnosa_cek1 = Sequel.cariInteger("select count(1) cek from pemeriksaan_ralan where no_rawat='" + Kd2.getText() + "'");
                
                if (diagnosa_cek1 == 0) {
                    diagnosa_ok = "-";
                    dAwal.setText(diagnosa_ok);
                } else {
                    diagnosa_ok = Sequel.cariIsi("select diagnosa from pemeriksaan_ralan where no_rawat='" + Kd2.getText() + "'");
                    dAwal.setText(diagnosa_ok);
                    
                }
            } else {
                kddokter = Sequel.cariIsi("select dokter_perujuk from periksa_lab where no_rawat='" + Kd2.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'");
                dokter_pengirim.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddokter + "'"));
                kdunit = Sequel.cariIsi("select b.kd_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                        + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + Kd2.getText() + "' and ki.stts_pulang<>'Pindah Kamar'");
                nm_unit = Sequel.cariIsi("select nm_bangsal from bangsal where kd_bangsal='" + kdunit + "'");
                notelpFaskes = Sequel.cariIsi("select no_tlp from bangsal where nm_bangsal='" + nm_unit + "' limit 1");
                nmgedung = Sequel.cariIsi("select nm_gedung from bangsal where kd_bangsal='" + kdunit + "' limit 1");
                diagnosa_cek2 = Sequel.cariInteger("select count(1) from kamar_inap where no_rawat='" + Kd2.getText() + "'");
                
                if (diagnosa_cek2 == 0) {
                    diagnosa_ok = "-";
                    dAwal.setText(diagnosa_ok);
                } else {
                    diagnosa_ok = Sequel.cariIsi("select diagnosa_awal from kamar_inap where no_rawat='" + Kd2.getText() + "'");
                    dAwal.setText(diagnosa_ok);
                }
            }
        }
    }

    public void isCek() {
        MnCetakHasilLab.setEnabled(akses.getperiksa_lab());
        MnCetakNota.setEnabled(akses.getperiksa_lab());
        tglNota.setDate(new Date());
//        MnUbah.setEnabled(var.getperiksa_lab());
        MnLapRekapPerPasien.setEnabled(akses.getperiksa_lab());
        BtnHapus.setEnabled(akses.getperiksa_lab());
    }

    public void setPasien(String norwt) {
        NoRawat.setText(norwt);
    }

    private void emptDataLIS() {
        nRawat.setText("");
        nLab.setText("");
        dataPasien.setText("");
        dAwal.setText("");
        kklinis.setText("");
        dokter_pengirim.setText("");
        caraByr.setText("");
        ketAktif.setText(Sequel.cariIsi("select if(aktivasi_LIS='1','TELAH DIAKTIFKAN','NON AKTIF') cek from set_pjlab limit 1"));
    }

    private void tampilLIS() {
        noLIS = "";
        noLIS = Sequel.cariIsi("select no_lab from lis_reg where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam_periksa='" + jamPeriksa + "'");

        try {
            Sequel.queryu("delete from temporary_lis");
            Valid.tabelKosong(tabMode1);
            psLIS1.setString(1, noLIS);
            rsLIS1 = psLIS1.executeQuery();
            while (rsLIS1.next()) {
                Sequel.menyimpan("temporary_lis", "'" + rsLIS1.getString("kategori_pemeriksaan_nama") + "','','','',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Kategori Pemeriksaan");
                tabMode1.addRow(new Object[]{rsLIS1.getString("kategori_pemeriksaan_nama")});

                psLIS2.setString(1, noLIS);
                psLIS2.setString(2, rsLIS1.getString("kategori_pemeriksaan_nama"));
                rsLIS2 = psLIS2.executeQuery();
                while (rsLIS2.next()) {
                    Sequel.menyimpan("temporary_lis", "'   " + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "','','','',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Sub Kategori Pemeriksaan");
                    tabMode1.addRow(new Object[]{"   " + rsLIS2.getString("sub_kategori_pemeriksaan_nama")});

                    psLIS3.setString(1, noLIS);
                    psLIS3.setString(2, rsLIS2.getString("sub_kategori_pemeriksaan_nama"));
                    psLIS3.setString(3, rsLIS1.getString("kategori_pemeriksaan_nama"));

                    rsLIS3 = psLIS3.executeQuery();
                    while (rsLIS3.next()) {
                        Sequel.menyimpan("temporary_lis", "'     " + Valid.mysql_real_escape_string(rsLIS3.getString("pemeriksaan_nama")) + "',"
                                + "'" + Valid.mysql_real_escape_string(rsLIS3.getString("nilai_hasil")) + "',"
                                + "'" + Valid.mysql_real_escape_string(rsLIS3.getString("satuan")) + "',"
                                + "'" + rsLIS3.getString("flag_kode") + "',"
                                + "'" + Valid.mysql_real_escape_string(rsLIS3.getString("nilai_rujukan")) + "',"
                                + "'" + rsLIS3.getString("wkt_selesai") + "',"
                                + "'" + Valid.mysql_real_escape_string(rsLIS3.getString("metode")) + "',"
                                + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Hasil Pemeriksaan");
                        tabMode1.addRow(new Object[]{
                            "     " + rsLIS3.getString("pemeriksaan_nama"),
                            rsLIS3.getString("nilai_hasil"),
                            rsLIS3.getString("satuan"),
                            rsLIS3.getString("flag_kode"),
                            rsLIS3.getString("nilai_rujukan"),
                            rsLIS3.getString("wkt_selesai"),
                            rsLIS3.getString("metode")
                        });
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void ProsesSimpanRekapData() {
        Sequel.AutoComitFalse();
        Sequel.queryu("delete from temporary");
        try {
            ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
            ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            ps.setString(3, "%" + NoRawat.getText() + "%");
            ps.setString(4, "%" + kdmem.getText() + "%");
            ps.setString(5, "%" + kdptg.getText() + "%");
            ps.setString(6, "%" + TCari.getText().trim() + "%");
            ps.setString(7, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
            ps.setString(8, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            ps.setString(9, "%" + NoRawat.getText() + "%");
            ps.setString(10, "%" + kdmem.getText() + "%");
            ps.setString(11, "%" + kdptg.getText() + "%");
            ps.setString(12, "%" + TCari.getText().trim() + "%");
            ps.setString(13, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
            ps.setString(14, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            ps.setString(15, "%" + NoRawat.getText() + "%");
            ps.setString(16, "%" + kdmem.getText() + "%");
            ps.setString(17, "%" + kdptg.getText() + "%");
            ps.setString(18, "%" + TCari.getText().trim() + "%");
            rs = ps.executeQuery();
            ttl = 0;
            while (rs.next()) {
                kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + rs.getString("no_rawat") + "' order by tgl_masuk desc limit 1");
                if (!kamar.equals("")) {
                    namakamar = kamar + ", " + Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal "
                            + " where kamar.kd_kamar='" + kamar + "' ");
                    kamar = "Kamar";
                } else if (kamar.equals("")) {
                    kamar = "Poliklinik";
                    namakamar = Sequel.cariIsi("select nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "
                            + "where reg_periksa.no_rawat='" + rs.getString("no_rawat") + "'");
                }
                Sequel.menyimpan("temporary", "'0','"
                        + rs.getString("no_rawat") + "','"
                        + rs.getString("no_lab") + "','No. RM : "
                        + rs.getString("no_rkm_medis") + ", Nama : " + rs.getString("nm_pasien") + ", Umur : " + rs.getString("umur_thn") + " (" + kamar + " : " + namakamar + "','"
                        + rs.getString("nama") + "','"
                        + rs.getString("tgl_periksa") + "','"
                        + rs.getString("jam") + "','"
                        + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs.getString("dokter_perujuk")) + "','"
                        + rs.getString("nm_dokter") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Rekap data periksa Lab.");

                Sequel.menyimpan("temporary", "'0','','','Item Pemeriksaan :','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Rekap data periksa Lab.");

                ps2.setString(1, rs.getString("no_rawat"));
                ps2.setString(2, rs.getString("tgl_periksa"));
                ps2.setString(3, rs.getString("jam"));
                rs2 = ps2.executeQuery();
                item = 0;
                while (rs2.next()) {
                    item = item + rs2.getDouble("biaya");
                    ttl = ttl + rs2.getDouble("biaya");
                    Sequel.menyimpan("temporary", "'0','','','"
                            + rs2.getString("nm_perawatan") + " " + Valid.SetAngka(rs2.getDouble("biaya")).replace("0", "") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Rekap data periksa Lab.");

                    ps3.setString(1, rs.getString("no_rawat"));
                    ps3.setString(2, rs2.getString("kd_jenis_prw"));
                    ps3.setString(3, rs.getString("tgl_periksa"));
                    ps3.setString(4, rs.getString("jam"));
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        item = item + rs3.getDouble("biaya_item");
                        ttl = ttl + rs3.getDouble("biaya_item");
                        Sequel.menyimpan("temporary", "'0','','','     "
                                + rs3.getString("Pemeriksaan") + " " + Valid.SetAngka(rs3.getDouble("biaya_item")) + "','"
                                + rs3.getString("nilai") + "','"
                                + rs3.getString("satuan") + "','"
                                + rs3.getString("nilai_rujukan") + "','"
                                + rs3.getString("keterangan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Rekap data periksa Lab.");
                    }
                }
                if (item > 0) {
                    Sequel.menyimpan("temporary", "'0','','','Total Biaya Pemeriksaan : Rp. "
                            + Valid.SetAngka(item) + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Rekap data periksa Lab.");
                }
            }
            if (ttl > 0) {
                Sequel.menyimpan("temporary", "'0','>>','>>>','GRAND TOTAL : Rp. "
                        + Valid.SetAngka(ttl) + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Rekap data periksa Lab.");
            }
        } catch (Exception e) {
            System.out.println("Notifikasi Rekap Data Pemeriksaan Lab. : " + e);
        }
        Sequel.AutoComitTrue();
    }

    private void cetakNota() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("norm", Sequel.cariIsi("select p.no_rkm_medis from reg_periksa r inner join pasien p on p.no_rkm_medis=r.no_rkm_medis where r.no_rawat='" + Kd2.getText() + "'"));
        param.put("nmpasien", Sequel.cariIsi("select p.nm_pasien from reg_periksa r inner join pasien p on p.no_rkm_medis=r.no_rkm_medis where r.no_rawat='" + Kd2.getText() + "'"));
        param.put("tglPeriksa", tglPeriksa + ", Pukul : " + jamPeriksa);
        param.put("drLab", drLab);
        param.put("cara_byr", Sequel.cariIsi("select p.png_jawab from reg_periksa r inner join penjab p on p.kd_pj=r.kd_pj where r.no_rawat='" + Kd2.getText() + "'"));
        param.put("tglNota", "Martapura, " + tglNota.getSelectedItem().toString());

        if (akses.getkode().equals("Admin Utama")) {
            param.put("petugas_ksr", "( ................... )");
        } else {
            param.put("petugas_ksr", "( " + Sequel.cariIsi("select nama from petugas where nip='" + akses.getkode() + "'") + " )");
        }
        Valid.MyReport("rptNotaLaboratorium.jasper", "report", "::[ Nota Transaksi Laboratorium ]::",
                " SELECT temp2, IF(FORMAT(temp3,0)='0','',FORMAT(temp3,0)) biaya, (select FORMAT(temp3,0) from temporary where "
                + "temp2='Total Biaya Pemeriksaan Lab') tot_byr FROM temporary WHERE temp2 not LIKE '%biaya%'; ", param);
    }
    
    private void emptTeksLIS() {
        TNoRM.setText("");
        TPasien.setText("");
        TnoTelpPx.setText("");
        TtglPeriksa.setText("");
        TjamPeriksa.setText("");
        Tdiagnosa.setText("");
        TketKlinis.setText("");
        TkdDokter.setText("");
        TnmDokter.setText("");
        TkdUnit.setText("");
        TnmUnit.setText("");
        TkdPenjab.setText("");
        TnmPenjab.setText("");
        TnoTelpDokter.setText("");
        TkdFaskes.setText("");
        TnmFaskes.setText("");
        TalamatPerujuk.setText("-");
        TnoTlpnFaskes.setText("");
    }
}
