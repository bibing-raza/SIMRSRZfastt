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
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import laporan.DlgKirimWhatsapp;
import rekammedis.RMDokumenPenunjangMedis;

public class DlgCariPeriksaLab extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Jurnal jur = new Jurnal();
    private Connection koneksi = koneksiDB.condb();
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private DlgPasien member = new DlgPasien(null, false);
    public DlgPasien pasien = new DlgPasien(null, false);
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private DlgMasterFaskes faskes = new DlgMasterFaskes(null, false);
    public DlgCariDokter dokter = new DlgCariDokter(null, false);
    private ApiLIS mas_lis = new ApiLIS();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private int i, diagnosa_cek1 = 0, diagnosa_cek2 = 0, cekKeLIS = 0, cekdataLIS = 0, x, y, pilihan = 0;
    private PreparedStatement ps, ps2, ps3, ps4, ps5, ps6, psrekening, psLIS1, psLIS2, psLIS3;
    private ResultSet rs, rs2, rs3, rs5, rs6, rsrekening, rsLIS1, rsLIS2, rsLIS3;
    private String kamar, namakamar;
    private double ttl = 0, item = 0, cekLab;
    private double ttljmdokter = 0, ttljmpetugas = 0, ttlkso = 0, ttlpendapatan = 0, ttlbhp = 0;
    private String Suspen_Piutang_Laborat_Ranap = "", Laborat_Ranap = "", Beban_Jasa_Medik_Dokter_Laborat_Ranap = "",
            Utang_Jasa_Medik_Dokter_Laborat_Ranap = "", Beban_Jasa_Medik_Petugas_Laborat_Ranap = "", nmgedung = "",
            Utang_Jasa_Medik_Petugas_Laborat_Ranap = "", Beban_Kso_Laborat_Ranap = "", Utang_Kso_Laborat_Ranap = "",
            HPP_Persediaan_Laborat_Rawat_inap = "", Persediaan_BHP_Laborat_Rawat_Inap = "", status = "", cekDataLab = "",
            nolab = "", tglPeriksa = "", jamPeriksa = "", diagnosa_ok = "", tnorwt = "", kdunit = "", kdpenjab = "",
            status_rawat = "", cekbayar = "", drLab = "", noLIS = "", nm_unit = "", Tnip = "", notelpFaskes = "", tte = "",
            dokterBaca = "", user = "", tglperiksaHsl = "", nipperujukHsl = "", kddokter = "", form = "", nipPerujuk = "",
            cariData = "", cariBayar = "", dialog_simpan = "";
    private frmUtama formUtama;

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
        
        tabMode2 = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. PA", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Dokter Pengirim", "Rg. Rawat/Poli/Inst.",
            "Tgl. Periksa", "Tgl. Hasil", "Lokasi/Organ", "Makroskopik", "Mikroskopik", "Kesimpulan", "Anjuran",
            "kd_gambar", "nip_perujuk", "tgl_periksa", "tgl_lahir", "tgl_hasil", "waktu_simpan",
            "italic_makroskopik", "italic_mikroskopik", "italic_kesimpulan", "italic_anjuran", "nmDokterPA", "Terkirim Ke WA", 
            "Waktu Terkirim", "No. WA Penerima", "nm_unit", "rumah_sakit"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbHasil.setModel(tabMode2);
        tbHasil.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbHasil.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 31; i++) {
            TableColumn column = tbHasil.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(115);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(75);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(250);
            } else if (i == 12) {
                column.setPreferredWidth(250);
            } else if (i == 13) {
                column.setPreferredWidth(250);
            } else if (i == 14) {
                column.setPreferredWidth(250);
            } else if (i == 15) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 16) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 17) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 18) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 19) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 20) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 21) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 22) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 23) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 24) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 25) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 26) {
                column.setPreferredWidth(90);
            } else if (i == 27) {
                column.setPreferredWidth(130);
            } else if (i == 28) {
                column.setPreferredWidth(100);
            } else if (i == 29) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 30) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbHasil.setDefaultRenderer(Object.class, new WarnaTable());

        NoRawat.setDocument(new batasInput((byte) 17).getKata(NoRawat));
        kdmem.setDocument(new batasInput((byte) 8).getKata(kdmem));
        kdptg.setDocument(new batasInput((byte) 25).getKata(kdptg));
        TnoPa.setDocument(new batasInput((byte) 50).getKata(TnoPa));
        TnoTelpPx.setDocument(new batasInput((byte) 15).getOnlyAngka(TnoTelpPx));
        TnoTelpDokter.setDocument(new batasInput((byte) 13).getOnlyAngka(TnoTelpDokter));
        TnoTlpnFaskes.setDocument(new batasInput((byte) 15).getOnlyAngka(TnoTlpnFaskes));    
        Tunit.setDocument(new batasInput((int) 200).getKata(Tunit));
        Trs.setDocument(new batasInput((int) 200).getKata(Trs));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        
//        if (koneksiDB.cariCepat().equals("aktif")) {
//            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
//                @Override
//                public void insertUpdate(DocumentEvent e) {
//                    tampil();
//                }
//
//                @Override
//                public void removeUpdate(DocumentEvent e) {
//                    tampil();
//                }
//
//                @Override
//                public void changedUpdate(DocumentEvent e) {
//                    tampil();
//                }
//            });
//        }
        
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgCariPeriksaLab")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        TNip.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        TnmPerujuk.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        btnPerujuk.requestFocus();
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
                    if (pilihan == 1) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            kdptg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                            nmptg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        }
                    } else if (pilihan == 2) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            TnipPemeriksa.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                            TnmPemeriksa.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        }
                        
                        Sequel.queryu("update periksa_lab set nip='" + TnipPemeriksa.getText() + "' where "
                                + "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
                        
                        if (Sequel.cariInteger("select count(-1) from periksa_lab where no_rawat='" + Kd2.getText() + "' and "
                                + "tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "' and nip='" + TnipPemeriksa.getText() + "'") > 0) {
                            JOptionPane.showMessageDialog(null, "Nama petugas pemriksa lab. berhasil diganti...!");
                        }
                    } else if (pilihan == 3) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            TnipPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                            TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
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
                        kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                        TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                        kdpoli.requestFocus();
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
            StringBuilder sb8 = new StringBuilder();
            sb8.append("select * from set_akun_ranap");
            psrekening = koneksi.prepareStatement(sb8.toString());
            
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
            StringBuilder sb5 = new StringBuilder();
            sb5.append("SELECT lhp.kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab ");
            sb5.append("LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? ");
            sb5.append("GROUP BY lhp.kategori_pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut");
            psLIS1 = koneksi.prepareStatement(sb5.toString());

            StringBuilder sb6 = new StringBuilder();
            sb6.append("SELECT lhp.sub_kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab ");
            sb6.append("LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? ");
            sb6.append("and lhp.kategori_pemeriksaan_nama=? GROUP BY lhp.sub_kategori_pemeriksaan_nama ");
            sb6.append("ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.sub_kategori_pemeriksaan_nama desc,lhp.pemeriksaan_no_urut");
            psLIS2 = koneksi.prepareStatement(sb6.toString());

            StringBuilder sb7 = new StringBuilder();
            sb7.append("SELECT lhp.pemeriksaan_nama, lhp.nilai_hasil, lhp.satuan, lhp.flag_kode, ");
            sb7.append("lhp.nilai_rujukan, DATE_FORMAT(lhdp.waktu_insert,'%d/%m/%Y - %H:%i:%s') wkt_selesai, lhp.metode ");
            sb7.append("FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp ON lhp.no_lab = lr.no_lab ");
            sb7.append("LEFT JOIN lis_hasil_data_pasien lhdp ON lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? ");
            sb7.append("and lhp.sub_kategori_pemeriksaan_nama=? and lhp.kategori_pemeriksaan_nama=? GROUP BY lhp.pemeriksaan_nama ");
            sb7.append("ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut");
            psLIS3 = koneksi.prepareStatement(sb7.toString());
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(".isi td{border-right: 1px solid #edf2e8;font: 10px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}");
        Document doc = kit.createDefaultDocument();
        
        LoadHTML1.setEditable(true);
        LoadHTML1.setEditorKit(kit);
        LoadHTML1.setDocument(doc);
        LoadHTML1.setEditable(false);
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
        MnDownloadDataSampling = new javax.swing.JMenuItem();
        MnCetakNota = new javax.swing.JMenu();
        MnTTDnota = new javax.swing.JMenuItem();
        MnTTEnota = new javax.swing.JMenuItem();
        MnKirimKeWhatsappNota = new javax.swing.JMenuItem();
        MnHasilPatologiAnatomi = new javax.swing.JMenuItem();
        MnDokumenJangMed = new javax.swing.JMenuItem();
        MnUbah = new javax.swing.JMenuItem();
        MnLihatHasilLIS = new javax.swing.JMenuItem();
        MnKirimLIS = new javax.swing.JMenuItem();
        MnGantiPerujuk = new javax.swing.JMenuItem();
        MnGantiPemeriksaLab = new javax.swing.JMenuItem();
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
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnHapusGambar = new javax.swing.JMenuItem();
        MnGantiPerujukPA = new javax.swing.JMenuItem();
        MnKirimKeWhatsapp = new javax.swing.JMenuItem();
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
        jLabel56 = new widget.Label();
        TnipPemeriksa = new widget.TextBox();
        TnmPemeriksa = new widget.TextBox();
        btnPemeriksa = new widget.Button();
        Scroll1 = new widget.ScrollPane();
        tbDataLIS = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        jLabel95 = new widget.Label();
        cmbPilihCetak1 = new widget.ComboBox();
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
        WindowHasilPA = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        internalFrame9 = new widget.InternalFrame();
        jLabel34 = new widget.Label();
        Ttglperiksa = new widget.TextBox();
        jLabel35 = new widget.Label();
        Tunit = new widget.TextBox();
        TdokterPengirim = new widget.TextBox();
        jLabel36 = new widget.Label();
        jLabel37 = new widget.Label();
        Tpasien = new widget.TextBox();
        jLabel38 = new widget.Label();
        TnoRw = new widget.TextBox();
        jLabel40 = new widget.Label();
        TnoPa = new widget.TextBox();
        TnoRm = new widget.TextBox();
        TtglLahir = new widget.TextBox();
        jLabel41 = new widget.Label();
        Tjenkel = new widget.TextBox();
        jLabel42 = new widget.Label();
        TtglHasil = new widget.Tanggal();
        jLabel43 = new widget.Label();
        Tlokasi = new widget.TextBox();
        jLabel44 = new widget.Label();
        scrollPane11 = new widget.ScrollPane();
        Tmakros = new widget.TextArea();
        scrollPane12 = new widget.ScrollPane();
        Tmikros = new widget.TextArea();
        jLabel45 = new widget.Label();
        scrollPane13 = new widget.ScrollPane();
        Tkesimpulan = new widget.TextArea();
        jLabel46 = new widget.Label();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        TkodeFile = new widget.TextBox();
        TnmPemeriksaan = new widget.TextBox();
        TtglUpload = new widget.TextBox();
        Scroll5 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        scrollPane14 = new widget.ScrollPane();
        Tanjuran = new widget.TextArea();
        jLabel51 = new widget.Label();
        TitalicMakros = new widget.TextBox();
        jLabel52 = new widget.Label();
        TitalicMikros = new widget.TextBox();
        jLabel53 = new widget.Label();
        TitalicKesimpulan = new widget.TextBox();
        jLabel54 = new widget.Label();
        TitalicAnjuran = new widget.TextBox();
        jLabel58 = new widget.Label();
        Trs = new widget.TextBox();
        Scroll2 = new widget.ScrollPane();
        tbHasil = new widget.Table();
        jPanel4 = new javax.swing.JPanel();
        panelGlass9 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus1 = new widget.Button();
        BtnEdit = new widget.Button();
        jLabel11 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint1 = new widget.Button();
        BtnDokumen = new widget.Button();
        BtnCloseIn5 = new widget.Button();
        jLabel55 = new widget.Label();
        cmbLimit = new widget.ComboBox();
        BtnCari1 = new widget.Button();
        WindowGantiPerujuk = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        panelisi6 = new widget.panelisi();
        jLabel39 = new widget.Label();
        TnmPerujuk = new widget.TextBox();
        btnPerujuk = new widget.Button();
        TNip = new widget.TextBox();
        chkPerujuk = new widget.CekBox();
        jLabel59 = new widget.Label();
        TnmPengirim = new widget.TextBox();
        panelisi7 = new widget.panelisi();
        BtnSimpan1 = new widget.Button();
        BtnCloseIn2 = new widget.Button();
        WindowGantiPemeriksa = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        panelisi8 = new widget.panelisi();
        jLabel57 = new widget.Label();
        TnmPetugas = new widget.TextBox();
        btnPetugas1 = new widget.Button();
        TnipPetugas = new widget.TextBox();
        panelisi9 = new widget.panelisi();
        BtnSimpan2 = new widget.Button();
        BtnCloseIn3 = new widget.Button();
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
        label19 = new widget.Label();
        cmbSttsTran = new widget.ComboBox();
        panelisi1 = new widget.panelisi();
        label10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
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

        MnDownloadDataSampling.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadDataSampling.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadDataSampling.setText("Download Data Pmrk. Sampling Reguler");
        MnDownloadDataSampling.setName("MnDownloadDataSampling"); // NOI18N
        MnDownloadDataSampling.setPreferredSize(new java.awt.Dimension(250, 28));
        MnDownloadDataSampling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadDataSamplingActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDownloadDataSampling);

        MnCetakNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakNota.setText("Cetak Nota Lab");
        MnCetakNota.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakNota.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakNota.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakNota.setIconTextGap(5);
        MnCetakNota.setName("MnCetakNota"); // NOI18N
        MnCetakNota.setOpaque(true);
        MnCetakNota.setPreferredSize(new java.awt.Dimension(250, 28));

        MnTTDnota.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTTDnota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnTTDnota.setText("TTD Basah");
        MnTTDnota.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTTDnota.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTTDnota.setIconTextGap(5);
        MnTTDnota.setName("MnTTDnota"); // NOI18N
        MnTTDnota.setPreferredSize(new java.awt.Dimension(120, 28));
        MnTTDnota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTTDnotaActionPerformed(evt);
            }
        });
        MnCetakNota.add(MnTTDnota);

        MnTTEnota.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTTEnota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnTTEnota.setText("TTE (QRCode)");
        MnTTEnota.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTTEnota.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTTEnota.setIconTextGap(5);
        MnTTEnota.setName("MnTTEnota"); // NOI18N
        MnTTEnota.setPreferredSize(new java.awt.Dimension(120, 28));
        MnTTEnota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTTEnotaActionPerformed(evt);
            }
        });
        MnCetakNota.add(MnTTEnota);

        jPopupMenu1.add(MnCetakNota);

        MnKirimKeWhatsappNota.setBackground(new java.awt.Color(242, 242, 242));
        MnKirimKeWhatsappNota.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKirimKeWhatsappNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/whatsapp.png"))); // NOI18N
        MnKirimKeWhatsappNota.setText("Kirim Nota Ke WhatsApp");
        MnKirimKeWhatsappNota.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKirimKeWhatsappNota.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKirimKeWhatsappNota.setIconTextGap(5);
        MnKirimKeWhatsappNota.setName("MnKirimKeWhatsappNota"); // NOI18N
        MnKirimKeWhatsappNota.setPreferredSize(new java.awt.Dimension(250, 28));
        MnKirimKeWhatsappNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKirimKeWhatsappNotaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKirimKeWhatsappNota);

        MnHasilPatologiAnatomi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPatologiAnatomi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPatologiAnatomi.setText("Hasil Pemeriksaan Patologi Anatomi");
        MnHasilPatologiAnatomi.setName("MnHasilPatologiAnatomi"); // NOI18N
        MnHasilPatologiAnatomi.setPreferredSize(new java.awt.Dimension(250, 28));
        MnHasilPatologiAnatomi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPatologiAnatomiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHasilPatologiAnatomi);

        MnDokumenJangMed.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokumenJangMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokumenJangMed.setText("Dokumen Penunjang Medis");
        MnDokumenJangMed.setName("MnDokumenJangMed"); // NOI18N
        MnDokumenJangMed.setPreferredSize(new java.awt.Dimension(250, 28));
        MnDokumenJangMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokumenJangMedActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDokumenJangMed);

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

        MnGantiPerujuk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiPerujuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiPerujuk.setText("Ganti Nama Perujuk/Pengirim");
        MnGantiPerujuk.setName("MnGantiPerujuk"); // NOI18N
        MnGantiPerujuk.setPreferredSize(new java.awt.Dimension(250, 28));
        MnGantiPerujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiPerujukActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnGantiPerujuk);

        MnGantiPemeriksaLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiPemeriksaLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiPemeriksaLab.setText("Ganti Nama Pemeriksa Lab.");
        MnGantiPemeriksaLab.setName("MnGantiPemeriksaLab"); // NOI18N
        MnGantiPemeriksaLab.setPreferredSize(new java.awt.Dimension(250, 28));
        MnGantiPemeriksaLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiPemeriksaLabActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnGantiPemeriksaLab);

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

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnHapusGambar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusGambar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusGambar.setText("Hapus Gambar");
        MnHapusGambar.setName("MnHapusGambar"); // NOI18N
        MnHapusGambar.setPreferredSize(new java.awt.Dimension(200, 28));
        MnHapusGambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusGambarActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHapusGambar);

        MnGantiPerujukPA.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiPerujukPA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiPerujukPA.setText("Ganti Nama Perujuk/Pengirim");
        MnGantiPerujukPA.setName("MnGantiPerujukPA"); // NOI18N
        MnGantiPerujukPA.setPreferredSize(new java.awt.Dimension(200, 28));
        MnGantiPerujukPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiPerujukPAActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnGantiPerujukPA);

        MnKirimKeWhatsapp.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKirimKeWhatsapp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/whatsapp.png"))); // NOI18N
        MnKirimKeWhatsapp.setText("Kirim Hasil Ke WhatsApp");
        MnKirimKeWhatsapp.setName("MnKirimKeWhatsapp"); // NOI18N
        MnKirimKeWhatsapp.setPreferredSize(new java.awt.Dimension(200, 28));
        MnKirimKeWhatsapp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKirimKeWhatsappActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnKirimKeWhatsapp);

        WindowDataLIS.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDataLIS.setName("WindowDataLIS"); // NOI18N
        WindowDataLIS.setUndecorated(true);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Hasil Pemeriksaan LIS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setPreferredSize(new java.awt.Dimension(0, 500));
        internalFrame7.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame7.setLayout(new java.awt.BorderLayout());

        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setPreferredSize(new java.awt.Dimension(0, 188));
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

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Pemeriksa Lab. :");
        jLabel56.setName("jLabel56"); // NOI18N
        internalFrame6.add(jLabel56);
        jLabel56.setBounds(0, 150, 100, 23);

        TnipPemeriksa.setEditable(false);
        TnipPemeriksa.setForeground(new java.awt.Color(0, 0, 0));
        TnipPemeriksa.setName("TnipPemeriksa"); // NOI18N
        internalFrame6.add(TnipPemeriksa);
        TnipPemeriksa.setBounds(105, 150, 160, 23);

        TnmPemeriksa.setEditable(false);
        TnmPemeriksa.setForeground(new java.awt.Color(0, 0, 0));
        TnmPemeriksa.setName("TnmPemeriksa"); // NOI18N
        internalFrame6.add(TnmPemeriksa);
        TnmPemeriksa.setBounds(270, 150, 467, 23);

        btnPemeriksa.setForeground(new java.awt.Color(0, 0, 0));
        btnPemeriksa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPemeriksa.setToolTipText("Alt+2");
        btnPemeriksa.setName("btnPemeriksa"); // NOI18N
        btnPemeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPemeriksaActionPerformed(evt);
            }
        });
        internalFrame6.add(btnPemeriksa);
        btnPemeriksa.setBounds(738, 150, 28, 23);

        internalFrame7.add(internalFrame6, java.awt.BorderLayout.PAGE_START);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 502));

        tbDataLIS.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDataLIS.setName("tbDataLIS"); // NOI18N
        tbDataLIS.getTableHeader().setReorderingAllowed(false);
        Scroll1.setViewportView(tbDataLIS);

        internalFrame7.add(Scroll1, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 45));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Cetak Dalam Bentuk :");
        jLabel95.setName("jLabel95"); // NOI18N
        jLabel95.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel95);

        cmbPilihCetak1.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihCetak1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetak1.setName("cmbPilihCetak1"); // NOI18N
        cmbPilihCetak1.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass8.add(cmbPilihCetak1);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
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

        internalFrame3.add(panelisi4, java.awt.BorderLayout.CENTER);

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 48));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 4, 9));

        BtnKirim.setForeground(new java.awt.Color(0, 0, 0));
        BtnKirim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
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

        internalFrame3.add(panelisi5, java.awt.BorderLayout.PAGE_END);

        WindowKirimLIS.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        WindowHasilPA.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowHasilPA.setName("WindowHasilPA"); // NOI18N
        WindowHasilPA.setUndecorated(true);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Hasil Pemeriksaan Patologi Anatomi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setPreferredSize(new java.awt.Dimension(0, 500));
        internalFrame8.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame8.setLayout(new java.awt.BorderLayout());

        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setPreferredSize(new java.awt.Dimension(0, 594));
        internalFrame9.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame9.setLayout(null);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Rumah Sakit :");
        jLabel34.setName("jLabel34"); // NOI18N
        internalFrame9.add(jLabel34);
        jLabel34.setBounds(0, 122, 130, 23);

        Ttglperiksa.setEditable(false);
        Ttglperiksa.setForeground(new java.awt.Color(0, 0, 0));
        Ttglperiksa.setName("Ttglperiksa"); // NOI18N
        internalFrame9.add(Ttglperiksa);
        Ttglperiksa.setBounds(490, 122, 110, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Rg. Rawat/Poli/Inst. :");
        jLabel35.setName("jLabel35"); // NOI18N
        internalFrame9.add(jLabel35);
        jLabel35.setBounds(0, 94, 130, 23);

        Tunit.setForeground(new java.awt.Color(0, 0, 0));
        Tunit.setName("Tunit"); // NOI18N
        Tunit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TunitKeyPressed(evt);
            }
        });
        internalFrame9.add(Tunit);
        Tunit.setBounds(135, 94, 630, 23);

        TdokterPengirim.setEditable(false);
        TdokterPengirim.setForeground(new java.awt.Color(0, 0, 0));
        TdokterPengirim.setName("TdokterPengirim"); // NOI18N
        internalFrame9.add(TdokterPengirim);
        TdokterPengirim.setBounds(135, 66, 400, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Dokter Pengirim :");
        jLabel36.setName("jLabel36"); // NOI18N
        internalFrame9.add(jLabel36);
        jLabel36.setBounds(0, 66, 130, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Tgl. Lahir :");
        jLabel37.setName("jLabel37"); // NOI18N
        internalFrame9.add(jLabel37);
        jLabel37.setBounds(0, 38, 130, 23);

        Tpasien.setEditable(false);
        Tpasien.setForeground(new java.awt.Color(0, 0, 0));
        Tpasien.setName("Tpasien"); // NOI18N
        internalFrame9.add(Tpasien);
        Tpasien.setBounds(345, 10, 420, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Pasien :");
        jLabel38.setName("jLabel38"); // NOI18N
        internalFrame9.add(jLabel38);
        jLabel38.setBounds(0, 10, 130, 23);

        TnoRw.setEditable(false);
        TnoRw.setForeground(new java.awt.Color(0, 0, 0));
        TnoRw.setName("TnoRw"); // NOI18N
        internalFrame9.add(TnoRw);
        TnoRw.setBounds(135, 10, 120, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("No. PA :");
        jLabel40.setName("jLabel40"); // NOI18N
        internalFrame9.add(jLabel40);
        jLabel40.setBounds(511, 38, 70, 23);

        TnoPa.setForeground(new java.awt.Color(0, 0, 0));
        TnoPa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnoPa.setName("TnoPa"); // NOI18N
        internalFrame9.add(TnoPa);
        TnoPa.setBounds(585, 38, 180, 23);

        TnoRm.setEditable(false);
        TnoRm.setForeground(new java.awt.Color(0, 0, 0));
        TnoRm.setName("TnoRm"); // NOI18N
        internalFrame9.add(TnoRm);
        TnoRm.setBounds(260, 10, 80, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        internalFrame9.add(TtglLahir);
        TtglLahir.setBounds(135, 38, 130, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Jns. Kelamin :");
        jLabel41.setName("jLabel41"); // NOI18N
        internalFrame9.add(jLabel41);
        jLabel41.setBounds(265, 38, 80, 23);

        Tjenkel.setEditable(false);
        Tjenkel.setForeground(new java.awt.Color(0, 0, 0));
        Tjenkel.setName("Tjenkel"); // NOI18N
        internalFrame9.add(Tjenkel);
        Tjenkel.setBounds(350, 38, 100, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Tgl. Hasil : ");
        jLabel42.setName("jLabel42"); // NOI18N
        internalFrame9.add(jLabel42);
        jLabel42.setBounds(605, 122, 70, 23);

        TtglHasil.setEditable(false);
        TtglHasil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-08-2026" }));
        TtglHasil.setDisplayFormat("dd-MM-yyyy");
        TtglHasil.setName("TtglHasil"); // NOI18N
        TtglHasil.setOpaque(false);
        TtglHasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtglHasilKeyPressed(evt);
            }
        });
        internalFrame9.add(TtglHasil);
        TtglHasil.setBounds(675, 122, 90, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Lokasi / Organ :");
        jLabel43.setName("jLabel43"); // NOI18N
        internalFrame9.add(jLabel43);
        jLabel43.setBounds(0, 150, 130, 23);

        Tlokasi.setForeground(new java.awt.Color(0, 0, 0));
        Tlokasi.setName("Tlokasi"); // NOI18N
        Tlokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlokasiKeyPressed(evt);
            }
        });
        internalFrame9.add(Tlokasi);
        Tlokasi.setBounds(135, 150, 630, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Makroskopik :");
        jLabel44.setName("jLabel44"); // NOI18N
        internalFrame9.add(jLabel44);
        jLabel44.setBounds(0, 178, 130, 23);

        scrollPane11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane11.setName("scrollPane11"); // NOI18N

        Tmakros.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tmakros.setColumns(20);
        Tmakros.setRows(5);
        Tmakros.setName("Tmakros"); // NOI18N
        Tmakros.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tmakros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmakrosKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(Tmakros);

        internalFrame9.add(scrollPane11);
        scrollPane11.setBounds(135, 178, 630, 75);

        scrollPane12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane12.setName("scrollPane12"); // NOI18N

        Tmikros.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tmikros.setColumns(20);
        Tmikros.setRows(5);
        Tmikros.setName("Tmikros"); // NOI18N
        Tmikros.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tmikros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmikrosKeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(Tmikros);

        internalFrame9.add(scrollPane12);
        scrollPane12.setBounds(135, 286, 630, 75);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Mikroskopik :");
        jLabel45.setName("jLabel45"); // NOI18N
        internalFrame9.add(jLabel45);
        jLabel45.setBounds(0, 286, 130, 23);

        scrollPane13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane13.setName("scrollPane13"); // NOI18N

        Tkesimpulan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkesimpulan.setColumns(20);
        Tkesimpulan.setRows(5);
        Tkesimpulan.setName("Tkesimpulan"); // NOI18N
        Tkesimpulan.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tkesimpulan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesimpulanKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Tkesimpulan);

        internalFrame9.add(scrollPane13);
        scrollPane13.setBounds(135, 394, 630, 75);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Kesimpulan :");
        jLabel46.setName("jLabel46"); // NOI18N
        internalFrame9.add(jLabel46);
        jLabel46.setBounds(0, 394, 130, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Anjuran :");
        jLabel47.setName("jLabel47"); // NOI18N
        internalFrame9.add(jLabel47);
        jLabel47.setBounds(0, 502, 130, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Kode File :");
        jLabel48.setName("jLabel48"); // NOI18N
        internalFrame9.add(jLabel48);
        jLabel48.setBounds(790, 10, 120, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Nama Pemeriksaan :");
        jLabel49.setName("jLabel49"); // NOI18N
        internalFrame9.add(jLabel49);
        jLabel49.setBounds(790, 38, 120, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Tgl. Upload :");
        jLabel50.setName("jLabel50"); // NOI18N
        internalFrame9.add(jLabel50);
        jLabel50.setBounds(790, 66, 120, 23);

        TkodeFile.setEditable(false);
        TkodeFile.setForeground(new java.awt.Color(0, 0, 0));
        TkodeFile.setName("TkodeFile"); // NOI18N
        internalFrame9.add(TkodeFile);
        TkodeFile.setBounds(914, 10, 180, 23);

        TnmPemeriksaan.setEditable(false);
        TnmPemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        TnmPemeriksaan.setName("TnmPemeriksaan"); // NOI18N
        internalFrame9.add(TnmPemeriksaan);
        TnmPemeriksaan.setBounds(914, 38, 330, 23);

        TtglUpload.setEditable(false);
        TtglUpload.setForeground(new java.awt.Color(0, 0, 0));
        TtglUpload.setName("TtglUpload"); // NOI18N
        internalFrame9.add(TtglUpload);
        TtglUpload.setBounds(914, 66, 250, 23);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Gambar Hasil Pemeriksaan Patologi Anatomi :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll5.setViewportView(LoadHTML1);

        internalFrame9.add(Scroll5);
        Scroll5.setBounds(790, 94, 670, 348);

        scrollPane14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane14.setName("scrollPane14"); // NOI18N

        Tanjuran.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tanjuran.setColumns(20);
        Tanjuran.setRows(5);
        Tanjuran.setName("Tanjuran"); // NOI18N
        Tanjuran.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tanjuran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanjuranKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Tanjuran);

        internalFrame9.add(scrollPane14);
        scrollPane14.setBounds(135, 502, 630, 75);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Text Italic Makroskopik :");
        jLabel51.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel51.setName("jLabel51"); // NOI18N
        internalFrame9.add(jLabel51);
        jLabel51.setBounds(0, 258, 130, 23);

        TitalicMakros.setForeground(new java.awt.Color(0, 0, 0));
        TitalicMakros.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        TitalicMakros.setName("TitalicMakros"); // NOI18N
        TitalicMakros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TitalicMakrosKeyPressed(evt);
            }
        });
        internalFrame9.add(TitalicMakros);
        TitalicMakros.setBounds(135, 258, 630, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Text Italic Mikroskopik :");
        jLabel52.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel52.setName("jLabel52"); // NOI18N
        internalFrame9.add(jLabel52);
        jLabel52.setBounds(0, 366, 130, 23);

        TitalicMikros.setForeground(new java.awt.Color(0, 0, 0));
        TitalicMikros.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        TitalicMikros.setName("TitalicMikros"); // NOI18N
        TitalicMikros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TitalicMikrosKeyPressed(evt);
            }
        });
        internalFrame9.add(TitalicMikros);
        TitalicMikros.setBounds(135, 366, 630, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Text Italic Kesimpulan :");
        jLabel53.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel53.setName("jLabel53"); // NOI18N
        internalFrame9.add(jLabel53);
        jLabel53.setBounds(0, 474, 130, 23);

        TitalicKesimpulan.setForeground(new java.awt.Color(0, 0, 0));
        TitalicKesimpulan.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        TitalicKesimpulan.setName("TitalicKesimpulan"); // NOI18N
        TitalicKesimpulan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TitalicKesimpulanKeyPressed(evt);
            }
        });
        internalFrame9.add(TitalicKesimpulan);
        TitalicKesimpulan.setBounds(135, 474, 630, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Text Italic Anjuran :");
        jLabel54.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel54.setName("jLabel54"); // NOI18N
        internalFrame9.add(jLabel54);
        jLabel54.setBounds(770, 502, 110, 23);

        TitalicAnjuran.setForeground(new java.awt.Color(0, 0, 0));
        TitalicAnjuran.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        TitalicAnjuran.setName("TitalicAnjuran"); // NOI18N
        internalFrame9.add(TitalicAnjuran);
        TitalicAnjuran.setBounds(887, 502, 570, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Tgl. Periksa : ");
        jLabel58.setName("jLabel58"); // NOI18N
        internalFrame9.add(jLabel58);
        jLabel58.setBounds(410, 122, 80, 23);

        Trs.setForeground(new java.awt.Color(0, 0, 0));
        Trs.setName("Trs"); // NOI18N
        Trs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrsKeyPressed(evt);
            }
        });
        internalFrame9.add(Trs);
        Trs.setBounds(135, 122, 270, 23);

        internalFrame8.add(internalFrame9, java.awt.BorderLayout.PAGE_START);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 502));

        tbHasil.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbHasil.setComponentPopupMenu(jPopupMenu2);
        tbHasil.setName("tbHasil"); // NOI18N
        tbHasil.getTableHeader().setReorderingAllowed(false);
        tbHasil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHasilMouseClicked(evt);
            }
        });
        tbHasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbHasilKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbHasil);

        internalFrame8.add(Scroll2, java.awt.BorderLayout.CENTER);

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(44, 45));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 26));
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
        panelGlass9.add(BtnSimpan);

        BtnBatal.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 26));
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
        panelGlass9.add(BtnBatal);

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus1.setText("Hapus");
        BtnHapus1.setToolTipText("Alt+H");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(100, 26));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        BtnHapus1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapus1KeyPressed(evt);
            }
        });
        panelGlass9.add(BtnHapus1);

        BtnEdit.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 26));
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
        panelGlass9.add(BtnEdit);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Cetak Dalam Bentuk :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass9.add(jLabel11);

        cmbPilihCetak.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetak.setName("cmbPilihCetak"); // NOI18N
        cmbPilihCetak.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass9.add(cmbPilihCetak);

        BtnPrint1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint1.setText("Cetak");
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(80, 26));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnPrint1);

        BtnDokumen.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/addressbook-search24.png"))); // NOI18N
        BtnDokumen.setText("Dokumen Penunjang Medis");
        BtnDokumen.setToolTipText("Alt+T");
        BtnDokumen.setName("BtnDokumen"); // NOI18N
        BtnDokumen.setPreferredSize(new java.awt.Dimension(210, 26));
        BtnDokumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokumenActionPerformed(evt);
            }
        });
        BtnDokumen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokumenKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnDokumen);

        BtnCloseIn5.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn5.setText("Tutup");
        BtnCloseIn5.setToolTipText("Alt+U");
        BtnCloseIn5.setName("BtnCloseIn5"); // NOI18N
        BtnCloseIn5.setPreferredSize(new java.awt.Dimension(90, 26));
        BtnCloseIn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn5ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnCloseIn5);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Limit Data :");
        jLabel55.setName("jLabel55"); // NOI18N
        jLabel55.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel55);

        cmbLimit.setForeground(new java.awt.Color(0, 0, 0));
        cmbLimit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100" }));
        cmbLimit.setName("cmbLimit"); // NOI18N
        cmbLimit.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(cmbLimit);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+1");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        panelGlass9.add(BtnCari1);

        jPanel4.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        internalFrame8.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        WindowHasilPA.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        WindowGantiPerujuk.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGantiPerujuk.setName("WindowGantiPerujuk"); // NOI18N
        WindowGantiPerujuk.setUndecorated(true);
        WindowGantiPerujuk.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Perujuk/Pengirim Pemeriksaan Lab. ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame4.setLayout(new java.awt.BorderLayout());

        panelisi6.setName("panelisi6"); // NOI18N
        panelisi6.setPreferredSize(new java.awt.Dimension(100, 50));
        panelisi6.setLayout(null);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Nama Pengirim : ");
        jLabel39.setName("jLabel39"); // NOI18N
        panelisi6.add(jLabel39);
        jLabel39.setBounds(0, 10, 100, 23);

        TnmPerujuk.setEditable(false);
        TnmPerujuk.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerujuk.setName("TnmPerujuk"); // NOI18N
        panelisi6.add(TnmPerujuk);
        TnmPerujuk.setBounds(230, 10, 330, 23);

        btnPerujuk.setForeground(new java.awt.Color(0, 0, 0));
        btnPerujuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnPerujuk.setToolTipText("ALt+7");
        btnPerujuk.setName("btnPerujuk"); // NOI18N
        btnPerujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerujukActionPerformed(evt);
            }
        });
        panelisi6.add(btnPerujuk);
        btnPerujuk.setBounds(566, 10, 28, 23);

        TNip.setEditable(false);
        TNip.setForeground(new java.awt.Color(0, 0, 0));
        TNip.setName("TNip"); // NOI18N
        panelisi6.add(TNip);
        TNip.setBounds(100, 10, 125, 23);

        chkPerujuk.setBackground(new java.awt.Color(255, 255, 250));
        chkPerujuk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerujuk.setForeground(new java.awt.Color(0, 0, 0));
        chkPerujuk.setText("Selain Yang Ada Pada Pilihan Yang Tersedia");
        chkPerujuk.setBorderPainted(true);
        chkPerujuk.setBorderPaintedFlat(true);
        chkPerujuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPerujuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPerujuk.setName("chkPerujuk"); // NOI18N
        chkPerujuk.setOpaque(false);
        chkPerujuk.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPerujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPerujukActionPerformed(evt);
            }
        });
        panelisi6.add(chkPerujuk);
        chkPerujuk.setBounds(100, 38, 250, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Nama Pengirim : ");
        jLabel59.setName("jLabel59"); // NOI18N
        panelisi6.add(jLabel59);
        jLabel59.setBounds(0, 66, 100, 23);

        TnmPengirim.setForeground(new java.awt.Color(0, 0, 0));
        TnmPengirim.setName("TnmPengirim"); // NOI18N
        panelisi6.add(TnmPengirim);
        TnmPengirim.setBounds(100, 66, 460, 23);

        internalFrame4.add(panelisi6, java.awt.BorderLayout.CENTER);

        panelisi7.setName("panelisi7"); // NOI18N
        panelisi7.setPreferredSize(new java.awt.Dimension(100, 48));
        panelisi7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 4, 9));

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        panelisi7.add(BtnSimpan1);

        BtnCloseIn2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn2.setText("Tutup");
        BtnCloseIn2.setToolTipText("Alt+U");
        BtnCloseIn2.setName("BtnCloseIn2"); // NOI18N
        BtnCloseIn2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn2ActionPerformed(evt);
            }
        });
        panelisi7.add(BtnCloseIn2);

        internalFrame4.add(panelisi7, java.awt.BorderLayout.PAGE_END);

        WindowGantiPerujuk.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        WindowGantiPemeriksa.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGantiPemeriksa.setName("WindowGantiPemeriksa"); // NOI18N
        WindowGantiPemeriksa.setUndecorated(true);
        WindowGantiPemeriksa.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Nama Petugas Pemeriksaan Lab. ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        panelisi8.setName("panelisi8"); // NOI18N
        panelisi8.setPreferredSize(new java.awt.Dimension(100, 50));
        panelisi8.setLayout(null);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Nama Petugas : ");
        jLabel57.setName("jLabel57"); // NOI18N
        panelisi8.add(jLabel57);
        jLabel57.setBounds(0, 10, 100, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        panelisi8.add(TnmPetugas);
        TnmPetugas.setBounds(230, 10, 330, 23);

        btnPetugas1.setForeground(new java.awt.Color(0, 0, 0));
        btnPetugas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnPetugas1.setToolTipText("ALt+7");
        btnPetugas1.setName("btnPetugas1"); // NOI18N
        btnPetugas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugas1ActionPerformed(evt);
            }
        });
        panelisi8.add(btnPetugas1);
        btnPetugas1.setBounds(566, 10, 28, 23);

        TnipPetugas.setEditable(false);
        TnipPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnipPetugas.setName("TnipPetugas"); // NOI18N
        panelisi8.add(TnipPetugas);
        TnipPetugas.setBounds(100, 10, 125, 23);

        internalFrame5.add(panelisi8, java.awt.BorderLayout.CENTER);

        panelisi9.setName("panelisi9"); // NOI18N
        panelisi9.setPreferredSize(new java.awt.Dimension(100, 48));
        panelisi9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 4, 9));

        BtnSimpan2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan2.setText("Simpan");
        BtnSimpan2.setToolTipText("Alt+S");
        BtnSimpan2.setName("BtnSimpan2"); // NOI18N
        BtnSimpan2.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan2ActionPerformed(evt);
            }
        });
        panelisi9.add(BtnSimpan2);

        BtnCloseIn3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn3.setText("Tutup");
        BtnCloseIn3.setToolTipText("Alt+U");
        BtnCloseIn3.setName("BtnCloseIn3"); // NOI18N
        BtnCloseIn3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn3ActionPerformed(evt);
            }
        });
        panelisi9.add(BtnCloseIn3);

        internalFrame5.add(panelisi9, java.awt.BorderLayout.PAGE_END);

        WindowGantiPemeriksa.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

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
        tbLab.getTableHeader().setReorderingAllowed(false);
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
        ketklinis.setBounds(79, 70, 310, 23);

        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setText("Bridging LIS :");
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        panelisi3.add(jLabel3);
        jLabel3.setBounds(645, 70, 88, 23);

        ketAktif.setForeground(new java.awt.Color(0, 51, 255));
        ketAktif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ketAktif.setText("-");
        ketAktif.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ketAktif.setName("ketAktif"); // NOI18N
        panelisi3.add(ketAktif);
        ketAktif.setBounds(740, 70, 400, 23);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("Status Transaksi :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label19);
        label19.setBounds(390, 70, 100, 23);

        cmbSttsTran.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsTran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Sudah Lunas", "Belum Bayar", "Piutang", "Transaksi Belum Selesai" }));
        cmbSttsTran.setName("cmbSttsTran"); // NOI18N
        panelisi3.add(cmbSttsTran);
        cmbSttsTran.setBounds(495, 70, 145, 23);

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

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi1.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi1.add(LCount);

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
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
        tglNota.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-08-2026" }));
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
        pilihan = 0;
        pilihan = 1;
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
        frmUtama.getInstance().tutupDialogDiPanelUtama(this);
        ketAktif.setText(Sequel.cariIsi("select if(aktivasi_LIS='1','TELAH DIAKTIFKAN','NON AKTIF') cek from set_pjlab limit 1"));
        WindowKirimLIS.dispose();
        WindowHasilPA.dispose();
        WindowGantiPerujuk.dispose();
        WindowGantiPemeriksa.dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            frmUtama.getInstance().tutupDialogDiPanelUtama(this);
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

    private void MnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUbahActionPerformed
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
            
            TnipPemeriksa.setText(Sequel.cariIsi("select nip from periksa_lab where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'"));
            TnmPemeriksa.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipPemeriksa.getText() + "'"));
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
            
            if (Sequel.cariInteger("select count(-1) from pembaca_hasil_lab where no_rawat='" + NoRawat.getText() + "' and "
                    + "no_lab='" + nolab + "' and tgl_periksa='" + tglPeriksa + "'") == 0) {
                param.put("judulDokterBaca", "");
                param.put("dokterBaca", "");
            } else {
                tampilPembaca(NoRawat.getText(), nolab, tglPeriksa);
                param.put("judulDokterBaca", "Hasil pemeriksaan Lab. telah dibaca oleh dokter :");
                param.put("dokterBaca", dokterBaca);
            }

            kamar = "";
            kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + NoRawat.getText() + "' order by tgl_masuk desc limit 1");
            if (!kamar.equals("")) {
                param.put("labelUnit", "Rg. Perawatan");
                param.put("nmUnit", Sequel.cariIsi("select b.nm_bangsal from bangsal b inner join kamar k on b.kd_bangsal=k.kd_bangsal where k.kd_kamar='" + kamar + "' "));
            } else if (kamar.equals("")) {
                param.put("labelUnit", "Poliklinik/Inst.");
                param.put("nmUnit", Sequel.cariIsi("select pl.nm_poli from poliklinik pl inner join reg_periksa rp on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + NoRawat.getText() + "'"));
            }

            if (cmbPilihCetak1.getSelectedIndex() == 0) {
                String isi = "", nipPemeriksa = "", nmPemeriksa = "";
                nipPemeriksa = Sequel.cariIsi("select nip from periksa_lab where no_rawat='" + NoRawat.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");                
                
                if (nipPemeriksa.equals("") || nipPemeriksa.equals("-") || nipPemeriksa.equals("--") || nipPemeriksa.equals("PP24")) {
                    JOptionPane.showMessageDialog(rootPane, "Maaf, nama petugas Lab./pemeriksa harus diisi sesuai dg. nama petugasnya dulu,...      ");
                } else {
                    nmPemeriksa = Sequel.cariIsi("select nama from pegawai where nik='" + nipPemeriksa + "'");
                    isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='006'"),
                                    "Hasil Pemeriksaan LIS", nmPemeriksa, Sequel.cariIsi("select date_format(now(),'%d/%m/%Y')"),
                                    Sequel.cariIsi("select time(now())")) + "') from kalimat_tte where kode='006'");

                    Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                    Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                    Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Assesmen Pre Induksi", Sequel.cariFolderPrintTte());
                    param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                    param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='006'"));
                    param.put("nmPemeriksa", nmPemeriksa);
                    
                    Valid.MyReport("rptHasilLISQr.jasper", "report", "::[ Lembar Hasil Pemeriksaan Laboratorium (LIS) ]::", "SELECT * FROM temporary_lis", param);
                    Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                    BtnCloseIn4ActionPerformed(evt);
                }
            } else {
                Valid.MyReport("rptHasilLIS.jasper", "report", "::[ Lembar Hasil Pemeriksaan Laboratorium (LIS) ]::", "SELECT * FROM temporary_lis", param);
                BtnCloseIn4ActionPerformed(evt);
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

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        if (tbHasil.getSelectedRow() > -1) {
            String nipdokterpa = Sequel.cariIsi("select nip_dokter_pa from hasil_patologi_anatomi where waktu_simpan='" + tbHasil.getValueAt(tbHasil.getSelectedRow(), 20).toString() + "'");
            
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            
            param.put("noPA", TnoPa.getText());
            param.put("norm", TnoRm.getText());
            param.put("nmpasien", Tpasien.getText());
            param.put("tgllahir", TtglLahir.getText());
            param.put("jenkel", Tjenkel.getText());
            param.put("drPengirim", TdokterPengirim.getText());
            param.put("unit", Tunit.getText());
            param.put("rs", Trs.getText());
            param.put("tglperiksa", Ttglperiksa.getText());
            param.put("tglhasil", Valid.SetTglINDONESIA(Valid.SetTgl(TtglHasil.getSelectedItem() + "")));
            param.put("lokasi", Tlokasi.getText() + "\n");
            param.put("makros", Tmakros.getText().replace("\r\n", "<br/>").replace("\n", "<br/>").replace("\r", "<br/>"));
            param.put("italicmakros", TitalicMakros.getText());
            param.put("mikros", Tmikros.getText().replace("\r\n", "<br/>").replace("\n", "<br/>").replace("\r", "<br/>"));
            param.put("italicmikros", TitalicMikros.getText());
            param.put("kesimpulan", Tkesimpulan.getText().replace("\r\n", "<br/>").replace("\n", "<br/>").replace("\r", "<br/>"));
            param.put("italickesimpulan", TitalicKesimpulan.getText());
            param.put("anjuran", Tanjuran.getText().replace("\r\n", "<br/>").replace("\n", "<br/>").replace("\r", "<br/>"));
            param.put("italicanjuran", TitalicAnjuran.getText());
            param.put("sip", Sequel.cariIsi("select no_ijn_praktek from dokter where kd_dokter='" + nipdokterpa + "'"));
            param.put("nmDokterpa", Sequel.cariIsi("select nama from pegawai where nik='" + nipdokterpa + "'"));

            try {
                String gambarnya = "", ipGambarnya = "";
                try {
                    //cek atau ping ip addres
                    ipGambarnya = "192.168.0.230";
                    InetAddress inet = InetAddress.getByName(ipGambarnya);

                    //ping sukses timeout 100 ms (0.1 detik)
                    if (inet.isReachable(100)) {
                        if (TkodeFile.getText().equals("")) {
                            gambarnya = "http://192.168.0.230:7183/img-rme/gambar_tidak_ditemukan.jpg";
                        } else {
                            gambarnya = "http://192.168.0.230:7183/rme/download.php?id=" + TkodeFile.getText();
                        }
                        //ping gagal
                    } else {
                        gambarnya = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/gambar_tidak_ditemukan.jpg";
                    }
                } catch (Exception e) {
                    System.out.println("Notif : " + e);
                    gambarnya = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/gambar_tidak_ditemukan.jpg";
                }
                
                param.put("gambarPA", gambarnya);
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }

            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isi = "";
                isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                        + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='006'"),
                                "Hasil Pemeriksaan Lab. (Patologi Anatomi)", tbHasil.getValueAt(tbHasil.getSelectedRow(), 25).toString(),
                                Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from hasil_patologi_anatomi where "
                                        + "waktu_simpan='" + tbHasil.getValueAt(tbHasil.getSelectedRow(), 20).toString() + "'"),
                                Sequel.cariIsi("select time(waktu_simpan) from hasil_patologi_anatomi where "
                                        + "waktu_simpan='" + tbHasil.getValueAt(tbHasil.getSelectedRow(), 20).toString() + "'")) + "') from kalimat_tte where kode='006'");

                Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Lab. Patologi Anatomi", Sequel.cariFolderPrintTte());
                param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='006'"));
                Valid.MyReport("rptPeriksaPatologiAnatomiQr.jasper", "report", "::[ Lembar Hasil Pemeriksaan Patologi Anatomi ]::", "SELECT now() tgl", param);
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            } else {
                Valid.MyReport("rptPeriksaPatologiAnatomi.jasper", "report", "::[ Lembar Hasil Pemeriksaan Patologi Anatomi ]::", "SELECT now() tgl", param);
            }
            emptTeksPatologi();
            tampilHasil();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tampilHasil();
            tbHasil.requestFocus();
        }
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void BtnCloseIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn5ActionPerformed
        WindowHasilPA.dispose();
        WindowGantiPerujuk.dispose();
        tampil();
    }//GEN-LAST:event_BtnCloseIn5ActionPerformed

    private void TtglHasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtglHasilKeyPressed
        Valid.pindah(evt, TtglHasil, Tlokasi);
    }//GEN-LAST:event_TtglHasilKeyPressed

    private void TmakrosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmakrosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TitalicMakros.requestFocus();
        }
    }//GEN-LAST:event_TmakrosKeyPressed

    private void TmikrosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmikrosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TitalicMikros.requestFocus();
        }
    }//GEN-LAST:event_TmikrosKeyPressed

    private void TkesimpulanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesimpulanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TitalicKesimpulan.requestFocus();
        }
    }//GEN-LAST:event_TkesimpulanKeyPressed

    private void TlokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlokasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tmakros.requestFocus();
        }
    }//GEN-LAST:event_TlokasiKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TnoRw.getText().trim().equals("")) {
            Valid.textKosong(TnoRw, "Pasien");
        } else if (TnoPa.getText().trim().equals("")) {
            Valid.textKosong(TnoPa, "No. PA");
        } else {
            if (akses.getadmin() == true) {
                user = "-";
            } else {
                user = akses.getkode();
            }
            
            if (Sequel.menyimpantf("hasil_patologi_anatomi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Hasil pemeriksaan patologi anatomi", 22, new String[]{
                TnoRw.getText(), TnoPa.getText(), Valid.SetTgl(TtglHasil.getSelectedItem() + ""), Tlokasi.getText(), Tmakros.getText(), Tmikros.getText(),
                Tkesimpulan.getText(), Tanjuran.getText(), user, TkodeFile.getText(), nipperujukHsl, Tunit.getText(), tglperiksaHsl, Sequel.cariIsi("select now()"),
                TitalicMakros.getText(), TitalicMikros.getText(), TitalicKesimpulan.getText(), TitalicAnjuran.getText(), "BELUM", "0000-00-00 00:00:00", "", Trs.getText()
            }) == true) {
                emptTeksPatologi();
                tampilHasil();
            }
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeksPatologi();
        tampilHasil();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeksPatologi();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus1);
        }
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        if (tbHasil.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from hasil_patologi_anatomi where waktu_simpan=?", 1, new String[]{
                    tbHasil.getValueAt(tbHasil.getSelectedRow(), 20).toString()
                }) == true) {
                    Sequel.mengedit("rme_file_upload", "id_file=?", "stts_data=?", 2, new String[]{
                        "0", tbHasil.getValueAt(tbHasil.getSelectedRow(), 15).toString()
                    });
                    emptTeksPatologi();
                    tampilHasil();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                emptTeksPatologi();
                tampilHasil();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel..!!");
            emptTeksPatologi();
            tampilHasil();
        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnHapus1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapus1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapus1ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
    }//GEN-LAST:event_BtnHapus1KeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TnoRw.getText().trim().equals("")) {
            Valid.textKosong(TnoRw, "Pasien");
        } else if (TnoPa.getText().trim().equals("")) {
            Valid.textKosong(TnoPa, "No. PA");
        } else {
            if (tbHasil.getSelectedRow() > -1) {
                if (akses.getadmin() == true) {
                    user = "-";
                } else {
                    user = akses.getkode();
                }

                if (Sequel.mengedittf("hasil_patologi_anatomi", "waktu_simpan=?", "no_pa=?, tgl_hasil=?, lokasi_organ=?, makroskopik=?, mikroskopik=?, "
                        + "kesimpulan=?, anjuran=?, nip_dokter_pa=?, kd_gambar=?, nip_perujuk=?, nm_unit=?, tgl_periksa=?, italic_makroskopik=?, "
                        + "italic_mikroskopik=?, italic_kesimpulan=?, italic_anjuran=?, rumah_sakit=?", 18, new String[]{
                            TnoPa.getText(), Valid.SetTgl(TtglHasil.getSelectedItem() + ""), Tlokasi.getText(), Tmakros.getText(), Tmikros.getText(),
                            Tkesimpulan.getText(), Tanjuran.getText(), user, TkodeFile.getText(), nipperujukHsl, Tunit.getText(), tglperiksaHsl,
                            TitalicMakros.getText(), TitalicMikros.getText(), TitalicKesimpulan.getText(), TitalicAnjuran.getText(), Trs.getText(),
                            tbHasil.getValueAt(tbHasil.getSelectedRow(), 20).toString()
                        }) == true) {
                    emptTeksPatologi();
                    tampilHasil();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel..!!");
                emptTeksPatologi();
                tampilHasil();
            }
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus1, BtnPrint1);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void MnHasilPatologiAnatomiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPatologiAnatomiActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else {
            if (tbLab.getSelectedRow() > -1) {
                if (NoRawat.getText().equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel tepat di nama pasien/no. RM nya..!");
                    tampil();
                } else {
                    WindowHasilPA.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    WindowHasilPA.setLocationRelativeTo(internalFrame1);
                    WindowHasilPA.setVisible(true);
                    WindowHasilPA.toFront();
                    WindowHasilPA.requestFocus();

                    emptTeksPatologi();
                    TnoRw.setText(nRawat.getText());
                    TnoRm.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + TnoRw.getText() + "'"));
                    Tpasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TnoRm.getText() + "'"));
                    TtglLahir.setText(Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + TnoRm.getText() + "'")));
                    Tjenkel.setText(Sequel.cariIsi("select if(jk='L','Laki-laki','Perempuan') from pasien where no_rkm_medis='" + TnoRm.getText() + "'"));
                    nipperujukHsl = kddokter;
                    tglperiksaHsl = tglPeriksa;
                    
                    if (Sequel.cariInteger("select count(-1) from perujuk_lab where no_rawat='" + TnoRw.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'") > 0) {
                        TdokterPengirim.setText(Sequel.cariIsi("select nm_perujuk_pengirim from perujuk_lab where no_rawat='" + TnoRw.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'"));
                    } else {
                        TdokterPengirim.setText(dokter_pengirim.getText());
                    }
                            
                    Tunit.setText(nm_unit);
                    Trs.setText(akses.getnamars());
                    Ttglperiksa.setText(Valid.SetTglINDONESIA(tglPeriksa));
                    cmbLimit.setSelectedIndex(0);                    
                    tampilHasil();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel tepat di nama pasien/no. RM nya..!");
                tampil();
            }
        }
    }//GEN-LAST:event_MnHasilPatologiAnatomiActionPerformed

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (NoRawat.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgCariPeriksaLab");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(NoRawat.getText(), Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + NoRawat.getText() + "'"), 
                    Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + NoRawat.getText() + "'") + "'"));
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void BtnDokumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokumenActionPerformed
        if (TnoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgCariPeriksaLab");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TnoRw.getText(), TnoRm.getText(), Tpasien.getText());
            form.setSize(internalFrame8.getWidth() - 40, internalFrame8.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame8);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnDokumenActionPerformed

    private void BtnDokumenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokumenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokumenKeyPressed

    private void tbHasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbHasilKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataHasil();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    getDataHasil();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbHasilKeyPressed

    private void tbHasilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHasilMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataHasil();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbHasilMouseClicked

    private void MnHapusGambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusGambarActionPerformed
        if (tbHasil.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin gambar hasil pemeriksaan patologi anatomi mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                Sequel.mengedit("hasil_patologi_anatomi", "waktu_simpan=?", "kd_gambar=?", 2, new String[]{
                    "", tbHasil.getValueAt(tbHasil.getSelectedRow(), 20).toString()});
                
                Sequel.mengedit("rme_file_upload", "id_file=?", "stts_data=?", 2, new String[]{
                    "0", tbHasil.getValueAt(tbHasil.getSelectedRow(), 15).toString()});
                emptTeksPatologi();
                tampilHasil();
            } else {
                emptTeksPatologi();
                tampilHasil();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel..!!");
            emptTeksPatologi();
            tampilHasil();
        }
    }//GEN-LAST:event_MnHapusGambarActionPerformed

    private void TitalicMakrosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TitalicMakrosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tmikros.requestFocus();
        }
    }//GEN-LAST:event_TitalicMakrosKeyPressed

    private void TitalicMikrosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TitalicMikrosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkesimpulan.requestFocus();
        }
    }//GEN-LAST:event_TitalicMikrosKeyPressed

    private void TitalicKesimpulanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TitalicKesimpulanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tanjuran.requestFocus();
        }
    }//GEN-LAST:event_TitalicKesimpulanKeyPressed

    private void TanjuranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanjuranKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TitalicAnjuran.requestFocus();
        }
    }//GEN-LAST:event_TanjuranKeyPressed

    private void btnPerujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerujukActionPerformed
        akses.setform("DlgCariPeriksaLab");
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnPerujukActionPerformed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (form.equals("1")) {
            if (Kd2.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik salah satu datanya pada tabel..!");
            } else {
                if (chkPerujuk.isSelected() == true) {
                    if (TnmPengirim.getText().equals("")) {
                        Valid.textKosong(TnmPengirim, "nama perujuk");
                        TnmPengirim.requestFocus();
                    } else {
                        Sequel.mengedit("periksa_lab", "no_rawat='" + Kd2.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'", "dokter_perujuk='" + TNip.getText() + "'");
                        Sequel.mengedit("hasil_patologi_anatomi", "no_rawat='" + Kd2.getText() + "' and tgl_periksa = '" + tglPeriksa + "'", "nip_perujuk='" + TNip.getText() + "'");
                        
                        if (Sequel.cariInteger("select count(-1) from perujuk_lab where no_rawat='" + Kd2.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'") > 0) {
                            Sequel.mengedit("perujuk_lab", "no_rawat='" + Kd2.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'",
                                    "nm_perujuk_pengirim='" + TnmPengirim.getText() + "'");
                        } else {
                            Sequel.menyimpan("perujuk_lab", "'" + Kd2.getText() + "','" + tglPeriksa + "','" + jamPeriksa + "','" + TnmPengirim.getText() + "'", "Nama Perujuk/Pengirim");
                        }
                        BtnCariActionPerformed(null);
                    }
                } else {
                    if (TNip.getText().trim().equals("") || TnmPerujuk.getText().trim().equals("")) {
                        Valid.textKosong(TNip, "nama perujuk");
                    } else {
                        Sequel.mengedit("periksa_lab", "no_rawat='" + Kd2.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'", "dokter_perujuk='" + TNip.getText() + "'");
                        Sequel.mengedit("hasil_patologi_anatomi", "no_rawat='" + Kd2.getText() + "' and tgl_periksa = '" + tglPeriksa + "'", "nip_perujuk='" + TNip.getText() + "'");
                        Sequel.queryu("delete from perujuk_lab where no_rawat='" + Kd2.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'");
                        BtnCariActionPerformed(null);
                    }
                }
            }
        } else if (form.equals("2")) {
            if (TnoRw.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik salah satu datanya pada tabel..!");
            } else {
                if (chkPerujuk.isSelected() == true) {
                    if (TnmPengirim.getText().equals("")) {
                        Valid.textKosong(TnmPengirim, "nama perujuk");
                        TnmPengirim.requestFocus();
                    } else {
                        Sequel.mengedit("periksa_lab", "no_rawat='" + TnoRw.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'", "dokter_perujuk='" + TNip.getText() + "'");
                        Sequel.mengedit("hasil_patologi_anatomi", "no_rawat='" + TnoRw.getText() + "' and tgl_periksa = '" + tglPeriksa + "'", "nip_perujuk='" + TNip.getText() + "'");

                        if (Sequel.cariInteger("select count(-1) from perujuk_lab where no_rawat='" + TnoRw.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'") > 0) {
                            Sequel.mengedit("perujuk_lab", "no_rawat='" + TnoRw.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'",
                                    "nm_perujuk_pengirim='" + TnmPengirim.getText() + "'");
                        } else {
                            Sequel.menyimpan("perujuk_lab", "'" + TnoRw.getText() + "','" + tglPeriksa + "','" + jamPeriksa + "','" + TnmPengirim.getText() + "'", "Nama Perujuk/Pengirim");
                        }
                        TdokterPengirim.setText(TnmPengirim.getText());
                        tampilHasil();
                        emptTeksPatologi();
                    }
                } else {
                    if (TNip.getText().trim().equals("") || TnmPerujuk.getText().trim().equals("")) {
                        Valid.textKosong(TNip, "nama perujuk");
                    } else {
                        Sequel.mengedit("periksa_lab", "no_rawat='" + TnoRw.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'", "dokter_perujuk='" + TNip.getText() + "'");
                        Sequel.mengedit("hasil_patologi_anatomi", "no_rawat='" + TnoRw.getText() + "' and tgl_periksa = '" + tglPeriksa + "'", "nip_perujuk='" + TNip.getText() + "'");
                        Sequel.queryu("delete from perujuk_lab where no_rawat='" + TnoRw.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'");
                        TdokterPengirim.setText(TnmPerujuk.getText());
                        tampilHasil();
                        emptTeksPatologi();
                    }
                }
            }
        }
        WindowGantiPerujuk.dispose();
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnCloseIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn2ActionPerformed
        WindowGantiPerujuk.dispose();
    }//GEN-LAST:event_BtnCloseIn2ActionPerformed

    private void MnGantiPerujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiPerujukActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else {
            if (tbLab.getSelectedRow() > -1) {
                form = "";
                if (Kd2.getText().equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel tepat di nama pasien/no. RM nya..!");
                    tampil();
                } else {
                    WindowGantiPerujuk.setSize(659, 186);
                    WindowGantiPerujuk.setLocationRelativeTo(internalFrame1);
                    WindowGantiPerujuk.setVisible(true);
                    WindowGantiPerujuk.toFront();
                    
                    form = "1";
                    TNip.setText(kddokter);
                    TnmPerujuk.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddokter + "'"));
                    
                    if (kddokter.equals("-")) {
                        chkPerujuk.setSelected(true);
                        TnmPengirim.setEnabled(true);
                        TnmPengirim.setText(Sequel.cariIsi("select if(nm_perujuk_pengirim is null,'',nm_perujuk_pengirim) from perujuk_lab "
                                + "where no_rawat='" + Kd2.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'"));
                        btnPerujuk.setEnabled(false);
                    } else {
                        chkPerujuk.setSelected(false);
                        TnmPengirim.setEnabled(false);
                        TnmPengirim.setText("");
                        btnPerujuk.setEnabled(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel tepat di nama pasien/no. RM nya..!");
                tampil();
            }    
        }
    }//GEN-LAST:event_MnGantiPerujukActionPerformed

    private void MnGantiPerujukPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiPerujukPAActionPerformed
        if (TnoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Maaf, data pasien yang melakukan pemeriksaan patologi anatomi belum dipilih..!");
            tampilHasil();
            emptTeksPatologi();
        } else {
            form = "";
            WindowGantiPerujuk.setSize(659, 186);
            WindowGantiPerujuk.setLocationRelativeTo(internalFrame8);
            WindowGantiPerujuk.setVisible(true);
            WindowGantiPerujuk.toFront();

            form = "2";
            TNip.setText(kddokter);
            TnmPerujuk.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddokter + "'"));
            
            if (kddokter.equals("-")) {
                chkPerujuk.setSelected(true);
                TnmPengirim.setEnabled(true);
                TnmPengirim.setText(Sequel.cariIsi("select if(nm_perujuk_pengirim is null,'',nm_perujuk_pengirim) from perujuk_lab "
                        + "where no_rawat='" + TnoRw.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'"));
                btnPerujuk.setEnabled(false);
            } else {
                chkPerujuk.setSelected(false);
                TnmPengirim.setEnabled(false);
                TnmPengirim.setText("");
                btnPerujuk.setEnabled(true);
            }
        }
    }//GEN-LAST:event_MnGantiPerujukPAActionPerformed

    private void MnKirimKeWhatsappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKirimKeWhatsappActionPerformed
        if (TnoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Maaf, data pasien yang melakukan pemeriksaan patologi anatomi belum dipilih..!");
            tampilHasil();
            emptTeksPatologi();
        } else {
            if (tbHasil.getSelectedRow() > -1) {
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgCariPeriksaLab");
                DlgKirimWhatsapp form = new DlgKirimWhatsapp(null, false);
                form.setSize(557, 148);
                form.setLocationRelativeTo(internalFrame8);
                form.emptTeks();
                form.setData("hasil patologi anatomi", tbHasil.getValueAt(tbHasil.getSelectedRow(), 0).toString(), 
                        tbHasil.getValueAt(tbHasil.getSelectedRow(), 20).toString(), Tpasien.getText());
                form.dataKirim();
                form.setVisible(true);
                form.toFront();
                form.requestFocus();
                emptTeksPatologi();
                tampilHasil();
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                tampilHasil();
                tbHasil.requestFocus();
            }
        }
    }//GEN-LAST:event_MnKirimKeWhatsappActionPerformed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilHasil();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void MnTTDnotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTTDnotaActionPerformed
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
                    tte = "";
                    tte = "tidak";
                    cetakNota();
                }
                Sequel.AutoComitTrue();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTTDnotaActionPerformed

    private void MnTTEnotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTTEnotaActionPerformed
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
                    tte = "";
                    tte = "ya";
                    cetakNota();
                }
                Sequel.AutoComitTrue();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTTEnotaActionPerformed

    private void btnPemeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPemeriksaActionPerformed
        akses.setform("DlgCariPeriksaLab");
        pilihan = 0;
        pilihan = 2;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPemeriksaActionPerformed

    private void btnPetugas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugas1ActionPerformed
        akses.setform("DlgCariPeriksaLab");
        pilihan = 0;
        pilihan = 3;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugas1ActionPerformed

    private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
        if (TnipPetugas.getText().equals("") || TnipPetugas.getText().equals("-") || TnipPetugas.getText().equals("--") || TnipPetugas.getText().equals("PP24")) {
            JOptionPane.showMessageDialog(rootPane, "Maaf, nama petugas Lab./pemeriksa harus diisi dengan benar,...      ");
        } else {
            Sequel.queryu("update periksa_lab set nip='" + TnipPetugas.getText() + "' where "
                    + "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'");
            
            BtnCloseIn3ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpan2ActionPerformed

    private void BtnCloseIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn3ActionPerformed
        WindowGantiPemeriksa.dispose();
        tampil();
    }//GEN-LAST:event_BtnCloseIn3ActionPerformed

    private void MnGantiPemeriksaLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiPemeriksaLabActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else {
            if (tbLab.getSelectedRow() > -1) {
                form = "";
                if (Kd2.getText().equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel tepat di nama pasien/no. RM nya..!");
                    tampil();
                } else {
                    WindowGantiPemeriksa.setSize(653, 121);
                    WindowGantiPemeriksa.setLocationRelativeTo(internalFrame1);
                    WindowGantiPemeriksa.setVisible(true);
                    WindowGantiPemeriksa.toFront();

                    TnipPetugas.setText(Sequel.cariIsi("select nip from periksa_lab where no_rawat='" + Kd2.getText() + "' and "
                            + "tgl_periksa='" + tglPeriksa + "' and jam='" + jamPeriksa + "'"));
                    TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipPetugas.getText() + "'"));
                    btnPetugas1.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik dulu datanya pada tabel tepat di nama pasien/no. RM nya..!");
                tampil();
            }    
        }
    }//GEN-LAST:event_MnGantiPemeriksaLabActionPerformed

    private void MnKirimKeWhatsappNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKirimKeWhatsappNotaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mencetak. Pilih dulu data yang mau dicetak. Klik No. Rawat pada tabel untuk memilih...!!!!");
        } else if (!(Kd2.getText().trim().equals(""))) {
            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            akses.setform("DlgCariPeriksaLab");
            DlgKirimWhatsapp form = new DlgKirimWhatsapp(null, false);
            form.setSize(557, 148);
            form.setLocationRelativeTo(internalFrame1);
            form.emptTeks();
            form.setData("nota lab", Kd2.getText(), tglNota.getSelectedItem().toString(), 
                    Sequel.cariIsi("select p.nm_pasien from reg_periksa rp inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where rp.no_rawat='" + Kd2.getText() + "'"));
            form.jaminanTrans(tglPeriksa, jamPeriksa, drLab, Tgl1.getSelectedItem() + "", Tgl2.getSelectedItem() + "", cariData, cariBayar, "", "", "", "");
            form.dataKirim();
            form.setVisible(true);
            form.toFront();
            form.requestFocus();
        }
    }//GEN-LAST:event_MnKirimKeWhatsappNotaActionPerformed

    private void TunitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TunitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trs.requestFocus();
        }
    }//GEN-LAST:event_TunitKeyPressed

    private void TrsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlokasi.requestFocus();
        }
    }//GEN-LAST:event_TrsKeyPressed

    private void chkPerujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPerujukActionPerformed
        if (chkPerujuk.isSelected() == true) {
            TNip.setText("-");
            TnmPerujuk.setText("-");
            btnPerujuk.setEnabled(false);
            TnmPengirim.setEnabled(true);
            TnmPengirim.requestFocus();
        } else {
            btnPerujuk.setEnabled(true);
            TnmPengirim.setText("");
            TnmPengirim.setEnabled(false);
            btnPerujuk.requestFocus();
        }
    }//GEN-LAST:event_chkPerujukActionPerformed

    private void MnDownloadDataSamplingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadDataSamplingActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            int jumlahData = 0;
            String tglAwal = Valid.SetTgl(Tgl1.getSelectedItem() + "");
            String tglAkhir = Valid.SetTgl(Tgl2.getSelectedItem() + "");
            
            jumlahData = Sequel.cariInteger("SELECT EXISTS (SELECT 1 FROM periksa_lab pl "
                    + "INNER JOIN detail_periksa_lab dpl ON dpl.no_rawat = pl.no_rawat AND dpl.tgl_periksa = pl.tgl_periksa AND dpl.jam = pl.jam "
                    + "WHERE pl.tgl_periksa between '" + tglAwal + "' AND '" + tglAkhir + "' AND dpl.kd_jenis_prw = 'LABPKK')");
            
            if (jumlahData == 0) {
                JOptionPane.showMessageDialog(null, "Data sampling reguler pada periode tgl. " + Tgl1.getSelectedItem().toString().replaceAll("-", "/")
                        + " s.d. " + Tgl2.getSelectedItem().toString().replaceAll("-", "/") + " tidak ditemukan.\n"
                        + "Proses download data dibatalkan.", "Data Tidak Ditemukan", JOptionPane.WARNING_MESSAGE);
                tampil();
                return;
            }
            
            dialog_simpan = Valid.openDialog();
            if (dialog_simpan == null) {
                return;
            }
            
            Valid.MyReportToExcel("SELECT DISTINCT date_format(pl.tgl_periksa,'%d/%m/%Y') 'Tgl. Periksa', "
                    + "p.no_rkm_medis 'No. RM', p.nm_pasien 'Nama Pasien', "
                    + "CASE WHEN rp.status_lanjut = 'Ralan' THEN plk.nm_poli ELSE b.nm_bangsal END 'Poliklinik/Inst./Rg. Rawat', "
                    + "CASE WHEN rp.status_lanjut = 'Ralan' THEN 'R. Jalan' ELSE 'R. Inap' END 'Jenis Rawat' FROM periksa_lab pl "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = pl.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN poliklinik plk ON plk.kd_poli = rp.kd_poli "
                    + "LEFT JOIN kamar_inap ki ON ki.no_rawat = pl.no_rawat "
                    + "LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                    + "LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                    + "WHERE pl.tgl_periksa between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND "
                    + "EXISTS (SELECT 1 FROM detail_periksa_lab dpl WHERE dpl.no_rawat = pl.no_rawat AND dpl.tgl_periksa = pl.tgl_periksa AND dpl.jam = pl.jam AND dpl.kd_jenis_prw = 'LABPKK') "
                    + "ORDER BY pl.tgl_periksa", dialog_simpan);
            JOptionPane.showMessageDialog(null, "Data pemeriksaan sampling reguler berhasil didownload menjadi file Excel.");
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Proses download data pemeriksaan sampling reguler gagal:\n" + e.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            this.setCursor(Cursor.getDefaultCursor());
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDownloadDataSamplingActionPerformed

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
    private widget.Button BtnBatal;
    private widget.Button BtnBersih;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn2;
    private widget.Button BtnCloseIn3;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCloseIn5;
    private widget.Button BtnDokumen;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnKeluar;
    private widget.Button BtnKirim;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan2;
    private widget.Button BtnUnit;
    private widget.TextBox Kd2;
    private widget.Label LCount;
    private widget.editorpane LoadHTML1;
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
    private javax.swing.JMenu MnCetakNota;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnDownloadDataSampling;
    private javax.swing.JMenuItem MnGantiPemeriksaLab;
    private javax.swing.JMenuItem MnGantiPerujuk;
    private javax.swing.JMenuItem MnGantiPerujukPA;
    private javax.swing.JMenuItem MnHapusGambar;
    private javax.swing.JMenuItem MnHasilPatologiAnatomi;
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
    private javax.swing.JMenuItem MnKirimKeWhatsapp;
    private javax.swing.JMenuItem MnKirimKeWhatsappNota;
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
    private javax.swing.JMenuItem MnTTDnota;
    private javax.swing.JMenuItem MnTTEnota;
    private javax.swing.JMenuItem MnUbah;
    private widget.TextBox NoRawat;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll5;
    private widget.TextBox TCari;
    private widget.TextBox TNip;
    private widget.TextBox TNoRM;
    private widget.TextBox TPasien;
    private widget.TextBox TPoli;
    private widget.TextBox TalamatPerujuk;
    private widget.TextArea Tanjuran;
    private widget.TextBox Tdiagnosa;
    private widget.TextBox TdokterPengirim;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.TextBox TitalicAnjuran;
    private widget.TextBox TitalicKesimpulan;
    private widget.TextBox TitalicMakros;
    private widget.TextBox TitalicMikros;
    private widget.TextBox TjamPeriksa;
    private widget.TextBox Tjenkel;
    private widget.TextBox TkdDokter;
    private widget.TextBox TkdFaskes;
    private widget.TextBox TkdPenjab;
    private widget.TextBox TkdUnit;
    private widget.TextArea Tkesimpulan;
    private widget.TextBox TketKlinis;
    private widget.TextBox TkodeFile;
    private widget.TextBox Tlokasi;
    private widget.TextArea Tmakros;
    private widget.TextArea Tmikros;
    private widget.TextBox TnipPemeriksa;
    private widget.TextBox TnipPetugas;
    private widget.TextBox TnmDokter;
    private widget.TextBox TnmFaskes;
    private widget.TextBox TnmPemeriksa;
    private widget.TextBox TnmPemeriksaan;
    private widget.TextBox TnmPengirim;
    private widget.TextBox TnmPenjab;
    private widget.TextBox TnmPerujuk;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TnmUnit;
    private widget.TextBox TnoPa;
    private widget.TextBox TnoRm;
    private widget.TextBox TnoRw;
    private widget.TextBox TnoTelpDokter;
    private widget.TextBox TnoTelpPx;
    private widget.TextBox TnoTlpnFaskes;
    private widget.TextBox Tpasien;
    private widget.TextBox Trs;
    private widget.Tanggal TtglHasil;
    private widget.TextBox TtglLahir;
    private widget.TextBox TtglPeriksa;
    private widget.TextBox TtglUpload;
    private widget.TextBox Ttglperiksa;
    private widget.TextBox Tunit;
    private javax.swing.JDialog WindowDataLIS;
    private javax.swing.JDialog WindowGantiPemeriksa;
    private javax.swing.JDialog WindowGantiPerujuk;
    private javax.swing.JDialog WindowHasilPA;
    private javax.swing.JDialog WindowKirimLIS;
    private widget.Button btnFaskes;
    private widget.Button btnPasien;
    private widget.Button btnPemeriksa;
    private widget.Button btnPenjab;
    private widget.Button btnPerujuk;
    private widget.Button btnPetugas;
    private widget.Button btnPetugas1;
    private widget.TextBox caraByr;
    public widget.CekBox chkPerujuk;
    private widget.ComboBox cmbLimit;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbPilihCetak1;
    private widget.ComboBox cmbSttsTran;
    private widget.TextBox dAwal;
    private widget.TextBox dataPasien;
    private widget.TextBox dokter_pengirim;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel11;
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
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel7;
    private widget.Label jLabel95;
    private javax.swing.JMenu jMnCetakHasilLab;
    private javax.swing.JMenu jMnLapInap;
    private javax.swing.JMenu jMnLapJalan;
    private javax.swing.JMenu jMnLapPemeriksaan;
    private javax.swing.JMenu jMnLapPendapatan;
    private javax.swing.JMenu jMnLapSemuaRawat;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
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
    private widget.Label label19;
    private widget.TextBox nLab;
    private widget.TextBox nRawat;
    private widget.TextBox nmmem;
    private widget.TextBox nmpnj;
    private widget.TextBox nmptg;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.panelisi panelisi5;
    private widget.panelisi panelisi6;
    private widget.panelisi panelisi7;
    private widget.panelisi panelisi8;
    private widget.panelisi panelisi9;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.Table tbDataLIS;
    private widget.Table tbHasil;
    private widget.Table tbLab;
    private widget.Tanggal tglNota;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {            
            queryTampil();
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
                    tabMode.addRow(new Object[]{"", "", "Total Biaya Pemeriksaan : Rp. " + Valid.SetAngka(item)
                        + ", Cara Bayar : " + rs.getString("png_jawab") + " (" + rs.getString("cekBayar") + ")",
                        "", "", "", "", ""});
                }
            }
            if (ttl > 0) {
                tabMode.addRow(new Object[]{">>", ">>>", "GRAND TOTAL : Rp. " + Valid.SetAngka(ttl), "", "", "", "", ""});
            }
            jumlahNoRawat();
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
            kddokter = Sequel.cariIsi("select dokter_perujuk from periksa_lab where no_rawat='" + Kd2.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'");
            
            if (Sequel.cariInteger("select count(-1) from perujuk_lab where no_rawat='" + Kd2.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'") > 0) {
                dokter_pengirim.setText(Sequel.cariIsi("select nm_perujuk_pengirim from perujuk_lab where no_rawat='" + Kd2.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'"));
            } else {
                dokter_pengirim.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddokter + "'"));
            }

            if (status_rawat.equals("Ralan")) {                
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
        MnCetakNota.setEnabled(akses.getbilling_ralan());
        MnKirimKeWhatsappNota.setEnabled(akses.getbilling_ralan());
        MnGantiPemeriksaLab.setEnabled(akses.getperiksa_lab());
        tglNota.setDate(new Date());
//        MnUbah.setEnabled(var.getperiksa_lab());
        MnLapRekapPerPasien.setEnabled(akses.getperiksa_lab());
        BtnHapus.setEnabled(akses.getperiksa_lab());
        MnGantiPerujuk.setEnabled(akses.getperiksa_lab());
        MnGantiPerujukPA.setEnabled(akses.getperiksa_lab());
        
        if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "' and status='1' and nm_dokter like '%dr.%'") > 0 || akses.getadmin() == true) {
            MnHasilPatologiAnatomi.setEnabled(true);
        } else {
            MnHasilPatologiAnatomi.setEnabled(false);
        }
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
        String crbyr = "", nmpetgs = "";
        crbyr = Sequel.cariIsi("select p.png_jawab from reg_periksa r inner join penjab p on p.kd_pj=r.kd_pj where r.no_rawat='" + Kd2.getText() + "'");

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
        param.put("cara_byr", crbyr);
        param.put("tglNota", "Martapura, " + tglNota.getSelectedItem().toString());

        if (akses.getadmin() == true) {
            nmpetgs = "...................";            
        } else {
            nmpetgs = Sequel.cariIsi("select nama from petugas where nip='" + akses.getkode() + "'");
        }

        param.put("petugas_ksr", "( " + nmpetgs + " )");

        if (tte.equals("tidak")) {
            Valid.MyReport("rptNotaLaboratorium.jasper", "report", "::[ Nota Transaksi Laboratorium ]::",
                    " SELECT temp2, IF(FORMAT(temp3,0)='0','',FORMAT(temp3,0)) biaya, (select FORMAT(temp3,0) from temporary where "
                    + "temp2='Total Biaya Pemeriksaan Lab') tot_byr FROM temporary WHERE temp2 not LIKE '%biaya%'; ", param);
        } else if (tte.equals("ya")) {
            if (akses.getadmin() == true) {
                Valid.MyReport("rptNotaLaboratorium.jasper", "report", "::[ Nota Transaksi Laboratorium ]::",
                        " SELECT temp2, IF(FORMAT(temp3,0)='0','',FORMAT(temp3,0)) biaya, (select FORMAT(temp3,0) from temporary where "
                        + "temp2='Total Biaya Pemeriksaan Lab') tot_byr FROM temporary WHERE temp2 not LIKE '%biaya%'; ", param);
            } else {
                String isi = "";
                isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                        + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='011'"),
                                "Nota Transaksi Laboratorium (" + crbyr + ")", nmpetgs,
                                Sequel.cariIsi("select date_format('" + Valid.SetTgl(tglNota.getSelectedItem() + "") + "','%d/%m/%Y')"),
                                Sequel.cariIsi("select time(now())")) + "') from kalimat_tte where kode='011'");

                Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Nota Transaksi Lab.", Sequel.cariFolderPrintTte());
                param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='011'"));

                Valid.MyReport("rptNotaLaboratoriumQr.jasper", "report", "::[ Nota Transaksi Laboratorium ]::",
                        " SELECT temp2, IF(FORMAT(temp3,0)='0','',FORMAT(temp3,0)) biaya, (select FORMAT(temp3,0) from temporary where "
                        + "temp2='Total Biaya Pemeriksaan Lab') tot_byr FROM temporary WHERE temp2 not LIKE '%biaya%'; ", param);
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            }
        }
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
    
    private void jumlahNoRawat() {
        int jmlnorwt = 0;
        for (i = 0; i < tabMode.getRowCount(); i++) {
            if (!tabMode.getValueAt(i, 0).equals("") && !tabMode.getValueAt(i, 0).equals(">>")) {
                jmlnorwt++;
            }
        }        
        LCount.setText(Valid.SetAngka(jmlnorwt));
    }
    
    private void queryTampil() {
        cariData = "";
        cariBayar = "";
        
        cariData = "pl.no_rawat LIKE '%" + TCari.getText() + "%' OR "
                + "rp.no_rkm_medis LIKE '%" + TCari.getText() + "%' OR "
                + "pt.nip LIKE '%" + TCari.getText() + "%' OR "
                + "p.nm_pasien LIKE '%" + TCari.getText() + "%' OR "
                + "pt.nama LIKE '%" + TCari.getText() + "%' OR "
                + "lr.no_lab LIKE '%" + TCari.getText() + "%' OR "
                + "pj.png_jawab LIKE '%" + TCari.getText() + "%' OR "
                + "IF(IFNULL(h.no_lab,'-')='-','Belum','Sudah') LIKE '%" + TCari.getText() + "%'";
        
        if (cmbSttsTran.getSelectedIndex() == 0) {
            cariBayar = "";
        } else {
            cariBayar = "HAVING cekBayar = '" + cmbSttsTran.getSelectedItem().toString() + "'";
        }
        
        try {
            StringBuilder sb1 = new StringBuilder();
            sb1.append("SELECT pl.no_rawat, IFNULL(lr.no_lab,'-') no_lab, rp.no_rkm_medis, p.nm_pasien, CONCAT(rp.umurdaftar,' ',rp.sttsumur,'.') AS umur_thn, ");
            sb1.append("pt.nama, pl.tgl_periksa, pl.jam, pl.dokter_perujuk, pl.kd_dokter, d.nm_dokter,IF(ifnull(h.no_lab,'-')='-','Belum','Sudah') hasil, rp.kd_pj, pj.png_jawab, ");
            sb1.append("CASE WHEN rp.kd_pj='U01' THEN IF(COUNT(bl.no_rawat) > 0, 'Sudah Lunas', 'Belum Bayar') ");
            sb1.append("ELSE IF(COUNT(pp.no_rawat) > 0, 'Piutang', 'Transaksi Belum Selesai') END cekBayar ");
            sb1.append("FROM periksa_lab pl INNER JOIN reg_periksa rp on rp.no_rawat=pl.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis ");
            sb1.append("INNER JOIN petugas pt on pt.nip=pl.nip INNER JOIN dokter d on d.kd_dokter=pl.kd_dokter INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj ");
            sb1.append("LEFT JOIN lis_reg lr on lr.no_rawat=pl.no_rawat and lr.tgl_periksa=pl.tgl_periksa AND lr.jam_periksa=pl.jam ");
            sb1.append("left join lis_hasil_periksa_lab h on h.no_lab = lr.no_lab LEFT JOIN billing bl ON bl.no_rawat=pl.no_rawat LEFT JOIN piutang_pasien pp ON pp.no_rawat=pl.no_rawat WHERE ");
            sb1.append("pl.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND (" + cariData + ") ");
            sb1.append("GROUP BY concat(pl.no_rawat, pl.tgl_periksa, pl.jam) " + cariBayar + " order by pl.tgl_periksa desc, pl.jam desc");
            ps = koneksi.prepareStatement(sb1.toString());

            StringBuilder sb2 = new StringBuilder();
            sb2.append("SELECT jpl.kd_jenis_prw, jpl.nm_perawatan, pl.biaya FROM periksa_lab pl ");
            sb2.append("INNER JOIN jns_perawatan_lab jpl ON jpl.kd_jenis_prw=pl.kd_jenis_prw WHERE pl.no_rawat =? AND pl.tgl_periksa =? AND pl.jam =?");
            ps2 = koneksi.prepareStatement(sb2.toString());

            StringBuilder sb3 = new StringBuilder();
            sb3.append("SELECT tl.Pemeriksaan, dpl.nilai, tl.satuan, dpl.nilai_rujukan, dpl.biaya_item, dpl.keterangan, ");
            sb3.append("dpl.kd_jenis_prw FROM detail_periksa_lab dpl INNER JOIN template_laboratorium tl ON tl.id_template=dpl.id_template ");
            sb3.append("WHERE dpl.no_rawat =? AND dpl.kd_jenis_prw =? AND dpl.tgl_periksa =? AND dpl.jam =?");
            ps3 = koneksi.prepareStatement(sb3.toString());

            StringBuilder sb4 = new StringBuilder();
            sb4.append("SELECT pl.no_rawat, IFNULL(lr.no_lab,'-') no_lab, rp.no_rkm_medis, p.nm_pasien, p.jk, p.umur, pt.nama, ");
            sb4.append("DATE_FORMAT(pl.tgl_periksa,'%d-%m-%Y') AS tgl_periksa, pl.jam, pl.dokter_perujuk, pl.kd_dokter, p.alamat, d.nm_dokter, ");
            sb4.append("DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') AS lahir FROM periksa_lab pl INNER JOIN reg_periksa rp on rp.no_rawat=pl.no_rawat ");
            sb4.append("INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN petugas pt ON pt.nip=pl.nip INNER JOIN dokter d ON d.kd_dokter=pl.kd_dokter ");
            sb4.append("LEFT JOIN lis_reg lr on lr.no_rawat=pl.no_rawat and lr.tgl_periksa=pl.tgl_periksa AND lr.jam_periksa=pl.jam ");
            sb4.append("WHERE pl.tgl_periksa =? AND pl.jam =? AND pl.no_rawat =? GROUP BY concat(pl.no_rawat, pl.tgl_periksa, pl.jam)");
            ps4 = koneksi.prepareStatement(sb4.toString());

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void tampilPembaca(String norw, String nolabb, String tgl) {
        dokterBaca = "";
        try {
            ps5 = koneksi.prepareStatement("SELECT ph.*, p.nama, date_format(ph.waktu_simpan,'%d-%m-%Y') tglBaca, time_format(ph.waktu_simpan,'%H:%i:%s') jamBaca "
                    + "FROM pembaca_hasil_lab ph inner join pegawai p on p.nik=ph.kd_dokter where "
                    + "ph.no_rawat='" + norw + "' and ph.no_lab='" + nolabb + "' and ph.tgl_periksa='" + tgl + "' order by ph.waktu_simpan");
            try {
                rs5 = ps5.executeQuery();
                x = 0;
                while (rs5.next()) {
                    x++;
                    if (dokterBaca.equals("")) {
                        dokterBaca = x + ". " + rs5.getString("nama") + " (Tgl. " + rs5.getString("tglBaca") + ", Jam " + rs5.getString("jamBaca").substring(0, 5) + " Wita)";
                    } else {
                        dokterBaca = dokterBaca + "\n" + x + ". " + rs5.getString("nama") + " (Tgl. " + rs5.getString("tglBaca") + ", Jam " + rs5.getString("jamBaca").substring(0, 5) + " Wita)";
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs5 != null) {
                    rs5.close();
                }
                if (ps5 != null) {
                    ps5.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void emptTeksPatologi() {
        TtglHasil.setDate(new Date());
        Tunit.setText("");
        Trs.setText("");
        Tlokasi.setText("");
        Tlokasi.requestFocus();
        Tmakros.setText("");
        Tmikros.setText("");
        Tkesimpulan.setText("");
        Tanjuran.setText("");
        TkodeFile.setText("");
        TnmPemeriksaan.setText("");
        TtglUpload.setText("");
        TnoPa.setText("");
        LoadHTML1.setText("");
        
        TitalicMakros.setText("");
        TitalicMikros.setText("");
        TitalicKesimpulan.setText("");
        TitalicAnjuran.setText("");
    }

    private void tampilHasil() {
        Valid.tabelKosong(tabMode2);
        try {
            if (cmbLimit.getSelectedIndex() == 0) {
                ps6 = koneksi.prepareStatement("SELECT hpa.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                        + "p2.nama drPengirim, date_format(hpa.tgl_periksa,'%d-%m-%Y') tglPeriksa, date_format(hpa.tgl_hasil,'%d-%m-%Y') tglHasil, p.tgl_lahir, "
                        + "p3.nama nmDokterPA, if(hpa.terkirim_ke_whatsapp='BELUM','-',date_format(hpa.waktu_terkirim,'%d-%m-%Y %H:%i Wita')) wktTerkirim "
                        + "FROM hasil_patologi_anatomi hpa inner join reg_periksa rp on rp.no_rawat=hpa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai p2 on p2.nik=hpa.nip_perujuk inner join pegawai p3 on p3.nik=hpa.nip_dokter_pa where "
                        + "hpa.no_rawat='" + nRawat.getText() + "' order by hpa.waktu_simpan desc");
            } else {
                ps6 = koneksi.prepareStatement("SELECT hpa.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                        + "p2.nama drPengirim, date_format(hpa.tgl_periksa,'%d-%m-%Y') tglPeriksa, date_format(hpa.tgl_hasil,'%d-%m-%Y') tglHasil, p.tgl_lahir, "
                        + "p3.nama nmDokterPA, if(hpa.terkirim_ke_whatsapp='BELUM','-',date_format(hpa.waktu_terkirim,'%d-%m-%Y %H:%i Wita')) wktTerkirim "
                        + "FROM hasil_patologi_anatomi hpa inner join reg_periksa rp on rp.no_rawat=hpa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai p2 on p2.nik=hpa.nip_perujuk inner join pegawai p3 on p3.nik=hpa.nip_dokter_pa where "
                        + "rp.no_rkm_medis='" + TnoRm.getText() + "' order by hpa.waktu_simpan desc limit " + cmbLimit.getSelectedItem().toString() + "");
            }            
            try {
                rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    tabMode2.addRow(new String[]{
                        rs6.getString("no_rawat"),
                        rs6.getString("no_pa"),
                        rs6.getString("no_rkm_medis"),
                        rs6.getString("nm_pasien"),
                        rs6.getString("jenkel"),
                        rs6.getString("tglLahir"),
                        rs6.getString("drPengirim"),
                        rs6.getString("nm_unit") + " (" + rs6.getString("rumah_sakit") + ")",
                        rs6.getString("tglPeriksa"),
                        rs6.getString("tglHasil"),
                        rs6.getString("lokasi_organ"),
                        rs6.getString("makroskopik"),
                        rs6.getString("mikroskopik"),
                        rs6.getString("kesimpulan"),
                        rs6.getString("anjuran"),
                        rs6.getString("kd_gambar"),
                        rs6.getString("nip_perujuk"),
                        rs6.getString("tgl_periksa"),
                        rs6.getString("tgl_lahir"),
                        rs6.getString("tgl_hasil"),
                        rs6.getString("waktu_simpan"),                        
                        rs6.getString("italic_makroskopik"),
                        rs6.getString("italic_mikroskopik"),
                        rs6.getString("italic_kesimpulan"),
                        rs6.getString("italic_anjuran"),
                        rs6.getString("nmDokterPA"),
                        rs6.getString("terkirim_ke_whatsapp"),
                        rs6.getString("wktTerkirim"),
                        rs6.getString("no_whatsapp_penerima"),
                        rs6.getString("nm_unit"),
                        rs6.getString("rumah_sakit")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs6 != null) {
                    rs6.close();
                }
                if (ps6 != null) {
                    ps6.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataHasil() {
        nipperujukHsl = "";
        tglperiksaHsl = "";
        if (tbHasil.getSelectedRow() != -1) {
            TnoRw.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 0).toString());
            TnoRm.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 2).toString());
            Tpasien.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 3).toString());
            TtglLahir.setText(Valid.SetTglINDONESIA(tbHasil.getValueAt(tbHasil.getSelectedRow(), 18).toString()));
            Tjenkel.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 4).toString());
            TnoPa.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 1).toString());
//            nipperujukHsl = tbHasil.getValueAt(tbHasil.getSelectedRow(), 16).toString();
            nipperujukHsl = Sequel.cariIsi("select dokter_perujuk from periksa_lab where no_rawat='" + TnoRw.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'");
            Ttglperiksa.setText(Valid.SetTglINDONESIA(tbHasil.getValueAt(tbHasil.getSelectedRow(), 17).toString()));
            Valid.SetTgl(TtglHasil, tbHasil.getValueAt(tbHasil.getSelectedRow(), 19).toString());
            Tlokasi.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 10).toString());
            Tmakros.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 11).toString());
            Tmikros.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 12).toString());
            Tkesimpulan.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 13).toString());
            Tanjuran.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 14).toString());
            TkodeFile.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 15).toString());
            TnmPemeriksaan.setText(Sequel.cariIsi("select rj.nama_pemeriksaan from rme_file_upload rf "
                    + "inner join rme_jenis_pemeriksaan rj on rj.kode_jenis_pemeriksaan=rf.jenis_pemeriksaan where "
                    + "rf.id_file='" + TkodeFile.getText() + "'"));
            TtglUpload.setText(Sequel.cariIsi("select date_format(rf.tgl_upload,'%d-%m-%Y, Pukul : %H:%i Wita') from rme_file_upload rf "
                    + "inner join rme_jenis_pemeriksaan rj on rj.kode_jenis_pemeriksaan=rf.jenis_pemeriksaan where "
                    + "rf.id_file='" + TkodeFile.getText() + "'"));
            tglperiksaHsl = tbHasil.getValueAt(tbHasil.getSelectedRow(), 17).toString();            
            TitalicMakros.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 21).toString());
            TitalicMikros.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 22).toString());
            TitalicKesimpulan.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 23).toString());
            TitalicAnjuran.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 24).toString());
            Tunit.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 29).toString());
            Trs.setText(tbHasil.getValueAt(tbHasil.getSelectedRow(), 30).toString());
            
            if (Sequel.cariInteger("select count(-1) from perujuk_lab where no_rawat='" + TnoRw.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'") > 0) {
                TdokterPengirim.setText(Sequel.cariIsi("select nm_perujuk_pengirim from perujuk_lab where no_rawat='" + TnoRw.getText() + "' and tgl_periksa = '" + tglPeriksa + "' and jam = '" + jamPeriksa + "'"));
            } else {
                TdokterPengirim.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipperujukHsl + "'"));
            }
            tampilGambar();
        }
    }

    private void tampilGambar() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            String gambar = "", ipGambar = "";
            try {
                //cek atau ping ip addres
                ipGambar = "192.168.0.230";
                InetAddress inet = InetAddress.getByName(ipGambar);

                //ping sukses timeout 100 ms (0.1 detik)
                if (inet.isReachable(100)) {
                    if (TkodeFile.getText().equals("")) {
                        gambar = "http://192.168.0.230:7183/img-rme/gambar_tidak_ditemukan.jpg";
                    } else {
                        gambar = "http://192.168.0.230:7183/rme/download.php?id=" + TkodeFile.getText();
                    }
                    //ping gagal
                } else {
                    gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/gambar_tidak_ditemukan.jpg";
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
                gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/gambar_tidak_ditemukan.jpg";
            }

            htmlContent.append(
                    "<tr class='isi'>"
                    + "<td valign='middle' colspan='5' rowspan='5' align='center'><br><img src='" + gambar + "' width='500' alt='Patologi Anatomi'></td>"                    
                    + "</tr>");
            
            htmlContent.append(
                    "</tbody>"
                    + "</table>");

            LoadHTML1.setText(
                    "<html>"
                    + "<table width='100%' border='0' align='center' colspan='8' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString()
                    + "</table>"
                    + "</html>");
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private byte[] getBytesFromStream(ByteArrayInputStream inputStream) {
        if (inputStream == null) {
            return null;
        }

        try {
            return inputStream.readAllBytes();
        } catch (Exception e) {
            return null;
        }
    }

}
