package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import kepegawaian.DlgCariPetugas;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author dosen
 */
public class RMPartografPersalinan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4, tabMode5, tabMode6, tabMode7, tabMode8, tabMode9, tabMode10;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9, ps10, ps11;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9, rs10, rs11;
    private int i = 0, x = 0;
    private String nipBidan = "", pembukaan = "", trun_kpl = "", urutData = "", dataKala3A = "", dataKala3B = "", dataKala3C = "", urutanKe = "",
            bidan8 = "", teman8 = "", klg8 = "", suami8 = "", dukun8 = "", tdkAda8 = "", gawat9 = "", perdarahan9 = "", hdk9 = "", infeksi9 = "", peb9 = "",
            bidan9 = "", lainya9 = "", suami15 = "", teman15 = "", tdkAda15 = "", klg15 = "", dukun15 = "", sebutkan38a = "", sebutkan38b = "", jamKetuban = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMPartografPersalinan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Ruang Rawat", "Gravida", "Paritas", "Abortus", "Tgl. Masuk", "Jam Masuk", 
            "Ketuban Pecah", "NIP Bidan", "Nama Bidan", "tgl_masuk", "jam_masuk", "jam_ketuban", "masalah", "penatalaksanaan", "hasilnya", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPartograf.setModel(tabMode);
        tbPartograf.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPartograf.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbPartograf.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(75);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(105);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            }
        }
        tbPartograf.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new String[]{"no_rawat", "DJJ", "Jeda (/menit)", "urutan", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbDjj.setModel(tabMode1);
        tbDjj.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDjj.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbDjj.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDjj.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbDjj.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbDjj.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode2 = new DefaultTableModel(null, new String[]{"no_rawat", "urutan", "Air Ketuban", "Mulase", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbAirKetuban.setModel(tabMode2);
        tbAirKetuban.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAirKetuban.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbAirKetuban.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(320);
            } else if (i == 3) {
                column.setPreferredWidth(410);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbAirKetuban.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new String[]{"no_rawat", "Waktu Ke", "Jam", "Cm.", "Pembukaan",
            "Turun Kepala", "Keterangan", "urutan", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbServik.setModel(tabMode3);
        tbServik.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbServik.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbServik.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbServik.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbServik.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbServik.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbServik.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbServik.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbServik.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        
        tabMode4 = new DefaultTableModel(null, new String[]{"no_rawat", "Lajur", "Detik", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbKontraksi.setModel(tabMode4);
        tbKontraksi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKontraksi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbKontraksi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbKontraksi.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbKontraksi.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        tabMode5 = new DefaultTableModel(null, new String[]{"no_rawat", "Konsentrasi U/L", "Kecepatan (tetes/mnt)", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbOksitosin.setModel(tabMode5);
        tbOksitosin.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbOksitosin.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbOksitosin.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbOksitosin.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode6 = new DefaultTableModel(null, new String[]{"no_rawat", "Obat / Cairan", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbObat.setModel(tabMode6);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(310);
            } else if (i == 2) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode7 = new DefaultTableModel(null, new String[]{"no_rawat", "urutan", "Nadi", "TD (Sistole)", "TD (Diastole)", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbNadi.setModel(tabMode7);
        tbNadi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbNadi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbNadi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbNadi.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbNadi.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbNadi.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbNadi.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tabMode8 = new DefaultTableModel(null, new String[]{"no_rawat", "Jam", "Suhu", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbSuhu.setModel(tabMode8);
        tbSuhu.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSuhu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbSuhu.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbSuhu.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbSuhu.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbSuhu.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode9 = new DefaultTableModel(null, new String[]{"no_rawat", "Jam", "Protein", "Aseton", "Volume", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbUrin.setModel(tabMode9);
        tbUrin.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbUrin.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbUrin.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbUrin.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbUrin.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        tabMode10 = new DefaultTableModel(null, new String[]{"no_rawat", "urutan", "Jam Ke", "Waktu", "Tekanan Drh.", "Nadi",
            "Suhu", "Tinggi Fundus U.", "Kontraksi Uterus", "Urin Output", "Darah Yg. Keluar", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPemantauan.setModel(tabMode10);
        tbPemantauan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPemantauan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbPemantauan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(55);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(60);
            } else if (i == 6) {
                column.setPreferredWidth(60);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(150);
            } else if (i == 9) {
                column.setPreferredWidth(150);
            } else if (i == 10) {
                column.setPreferredWidth(150);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPemantauan.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPemantauan.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbPemantauan.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbPemantauan.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbPemantauan.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbPemantauan.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        Tgravida.setDocument(new batasInput((int) 10).getKata(Tgravida));
        Tparitas.setDocument(new batasInput((int) 10).getKata(Tparitas));
        Tabortus.setDocument(new batasInput((int) 10).getKata(Tabortus));
        Tdjj.setDocument(new batasInput((int) 7).getKata(Tdjj));
        Tjeda.setDocument(new batasInput((int) 3).getKata(Tjeda));        
        Toksitosin.setDocument(new batasInput((int) 30).getKata(Toksitosin));
        Ttetes.setDocument(new batasInput((int) 30).getKata(Ttetes));
        TobatCairan.setDocument(new batasInput((int) 200).getKata(TobatCairan));
        Tnadi.setDocument(new batasInput((int) 7).getKata(Tnadi));
        Tsistol.setDocument(new batasInput((int) 7).getKata(Tsistol));
        Tdistol.setDocument(new batasInput((int) 7).getKata(Tdistol));
        Tsuhu.setDocument(new batasInput((int) 7).getKata(Tsuhu));
        Tvolume.setDocument(new batasInput((int) 7).getKata(Tvolume));        
        TtmptPersalinanLain.setDocument(new batasInput((int) 150).getKata(TtmptPersalinanLain));
        TalasanMerujuk.setDocument(new batasInput((int) 200).getKata(TalasanMerujuk));
        TtmptRujukan.setDocument(new batasInput((int) 200).getKata(TtmptRujukan));
        TmasalahLain.setDocument(new batasInput((int) 200).getKata(TmasalahLain));
        Tkala2YaIndikasi.setDocument(new batasInput((int) 200).getKata(Tkala2YaIndikasi));
        Tkala2YaTindakanGawat.setDocument(new batasInput((int) 200).getKata(Tkala2YaTindakanGawat));
        Tkala2Pemantauan.setDocument(new batasInput((int) 200).getKata(Tkala2Pemantauan));
        Tkala2YaTindakanDisto.setDocument(new batasInput((int) 200).getKata(Tkala2YaTindakanDisto));
        Tkala3Tidak.setDocument(new batasInput((int) 200).getKata(Tkala3Tidak));
        Tkala3Lama.setDocument(new batasInput((int) 7).getKata(Tkala3Lama));        
        Tkala3PemberianUlang.setDocument(new batasInput((int) 200).getKata(Tkala3PemberianUlang));
        Tkala3Penegangan.setDocument(new batasInput((int) 200).getKata(Tkala3Penegangan));
        Tkala3Masase.setDocument(new batasInput((int) 200).getKata(Tkala3Masase));
        Tkala3Plasenta25A.setDocument(new batasInput((int) 200).getKata(Tkala3Plasenta25A));
        Tkala3Plasenta25B.setDocument(new batasInput((int) 200).getKata(Tkala3Plasenta25B));
        Tkala3Plasenta26.setDocument(new batasInput((int) 200).getKata(Tkala3Plasenta26));
        Tkala3Laserasi.setDocument(new batasInput((int) 200).getKata(Tkala3Laserasi));
        Tkala3Alasan.setDocument(new batasInput((int) 200).getKata(Tkala3Alasan));
        Tkala3Atonia.setDocument(new batasInput((int) 200).getKata(Tkala3Atonia));
        Tkala3Jumlah.setDocument(new batasInput((int) 7).getKata(Tkala3Jumlah));
        TbayiBB.setDocument(new batasInput((int) 7).getKata(TbayiBB));
        TbayiPB.setDocument(new batasInput((int) 7).getKata(TbayiPB));
        TbayiSebutkan.setDocument(new batasInput((int) 200).getKata(TbayiSebutkan));
        TbayiTindakanA.setDocument(new batasInput((int) 200).getKata(TbayiTindakanA));
        TbayiTindakanB.setDocument(new batasInput((int) 200).getKata(TbayiTindakanB));
        TbayiTindakanC.setDocument(new batasInput((int) 200).getKata(TbayiTindakanC));
        TbayiYaPemberian.setDocument(new batasInput((int) 7).getKata(TbayiYaPemberian));
        TbayiTidakAlasan.setDocument(new batasInput((int) 200).getKata(TbayiTidakAlasan));
        TbayiMasalah.setDocument(new batasInput((int) 200).getKata(TbayiMasalah));
        TjamKe.setDocument(new batasInput((int) 7).getKata(TjamKe));
        TtdKala4.setDocument(new batasInput((int) 7).getKata(TtdKala4));
        TnadiKala4.setDocument(new batasInput((int) 7).getKata(TnadiKala4));
        TsuhuKala4.setDocument(new batasInput((int) 7).getKata(TsuhuKala4));
        TTinggiFundus.setDocument(new batasInput((int) 150).getKata(TTinggiFundus));
        Tkontraksi.setDocument(new batasInput((int) 100).getKata(Tkontraksi));
        Turin.setDocument(new batasInput((int) 100).getKata(Turin));
        TdarahYang.setDocument(new batasInput((int) 100).getKata(TdarahYang));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampil();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampil();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampil();}
            });
        }
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    nipBidan = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                    TnmBidan.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    BtnBidan.requestFocus();
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
    }
 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        TabPartograf = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        Scroll3 = new widget.ScrollPane();
        internalFrame20 = new widget.InternalFrame();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel5 = new widget.Label();
        Tgravida = new widget.TextBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel8 = new widget.Label();
        Tparitas = new widget.TextBox();
        jLabel9 = new widget.Label();
        Tabortus = new widget.TextBox();
        jLabel10 = new widget.Label();
        TtglMasuk = new widget.Tanggal();
        jLabel11 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel12 = new widget.Label();
        cmbKetuban = new widget.ComboBox();
        jLabel13 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel15 = new widget.Label();
        jLabel16 = new widget.Label();
        Tdjj = new widget.TextBox();
        jLabel17 = new widget.Label();
        Scroll1 = new widget.ScrollPane();
        tbDjj = new widget.Table();
        BtnBaruDjj = new widget.Button();
        BtnTambahDjj = new widget.Button();
        BtnHapusDjj = new widget.Button();
        BtnGantiDjj = new widget.Button();
        jLabel18 = new widget.Label();
        cmbAirKetuban = new widget.ComboBox();
        jLabel19 = new widget.Label();
        cmbMulase = new widget.ComboBox();
        Scroll2 = new widget.ScrollPane();
        tbAirKetuban = new widget.Table();
        BtnBaruAKM = new widget.Button();
        BtnTambahAKM = new widget.Button();
        BtnHapusAKM = new widget.Button();
        BtnGantiAKM = new widget.Button();
        jLabel21 = new widget.Label();
        jLabel22 = new widget.Label();
        cmbWaktuKe = new widget.ComboBox();
        jLabel23 = new widget.Label();
        cmbJam4 = new widget.ComboBox();
        cmbMnt4 = new widget.ComboBox();
        cmbDtk4 = new widget.ComboBox();
        jLabel24 = new widget.Label();
        cmbCm = new widget.ComboBox();
        jLabel25 = new widget.Label();
        chkPembukaan = new widget.CekBox();
        jLabel26 = new widget.Label();
        chkTurunya = new widget.CekBox();
        jLabel27 = new widget.Label();
        TketServik = new widget.TextBox();
        Scroll4 = new widget.ScrollPane();
        tbServik = new widget.Table();
        BtnBaruServik = new widget.Button();
        BtnHapusServik = new widget.Button();
        BtnTambahServik = new widget.Button();
        BtnGantiServik = new widget.Button();
        jLabel28 = new widget.Label();
        jLabel29 = new widget.Label();
        cmbLajur = new widget.ComboBox();
        jLabel30 = new widget.Label();
        cmbDetik = new widget.ComboBox();
        Scroll5 = new widget.ScrollPane();
        tbKontraksi = new widget.Table();
        BtnBaruKontraksi = new widget.Button();
        BtnHapusKontraksi = new widget.Button();
        BtnTambahKontraksi = new widget.Button();
        BtnGantiKontraksi = new widget.Button();
        jLabel34 = new widget.Label();
        Ttetes = new widget.TextBox();
        jLabel35 = new widget.Label();
        Scroll6 = new widget.ScrollPane();
        tbOksitosin = new widget.Table();
        BtnBaruOksi = new widget.Button();
        BtnHapusOksi = new widget.Button();
        BtnTambahOksi = new widget.Button();
        BtnGantiOksi = new widget.Button();
        jLabel37 = new widget.Label();
        TobatCairan = new widget.TextBox();
        Scroll7 = new widget.ScrollPane();
        tbObat = new widget.Table();
        BtnBaruObat = new widget.Button();
        BtnHapusObat = new widget.Button();
        BtnTambahObat = new widget.Button();
        BtnGantiObat = new widget.Button();
        jLabel38 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel40 = new widget.Label();
        Tsistol = new widget.TextBox();
        jLabel41 = new widget.Label();
        Tdistol = new widget.TextBox();
        jLabel42 = new widget.Label();
        Scroll8 = new widget.ScrollPane();
        tbNadi = new widget.Table();
        BtnBaruNadi = new widget.Button();
        BtnHapusNadi = new widget.Button();
        BtnTambahNadi = new widget.Button();
        BtnGantiNadi = new widget.Button();
        jLabel44 = new widget.Label();
        Tsuhu = new widget.TextBox();
        jLabel45 = new widget.Label();
        jLabel46 = new widget.Label();
        cmbJam9 = new widget.ComboBox();
        cmbMnt9 = new widget.ComboBox();
        cmbDtk9 = new widget.ComboBox();
        Scroll9 = new widget.ScrollPane();
        tbSuhu = new widget.Table();
        BtnBaruSuhu = new widget.Button();
        BtnHapusSuhu = new widget.Button();
        BtnTambahSuhu = new widget.Button();
        BtnGantiSuhu = new widget.Button();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        Tvolume = new widget.TextBox();
        jLabel51 = new widget.Label();
        cmbJam10 = new widget.ComboBox();
        cmbMnt10 = new widget.ComboBox();
        cmbDtk10 = new widget.ComboBox();
        Scroll10 = new widget.ScrollPane();
        tbUrin = new widget.Table();
        BtnBaruUrin = new widget.Button();
        BtnHapusUrin = new widget.Button();
        BtnTambahUrin = new widget.Button();
        BtnGantiUrin = new widget.Button();
        cmbProtein = new widget.ComboBox();
        cmbAseton = new widget.ComboBox();
        Tjeda = new widget.TextBox();
        jLabel131 = new widget.Label();
        jLabel32 = new widget.Label();
        jLabel132 = new widget.Label();
        Toksitosin = new widget.TextBox();
        jLabel36 = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnGanti = new widget.Button();
        BtnHapus = new widget.Button();
        BtnCetak = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        Scroll11 = new widget.ScrollPane();
        internalFrame21 = new widget.InternalFrame();
        jLabel52 = new widget.Label();
        jLabel53 = new widget.Label();
        TtglCatatan = new widget.Tanggal();
        jLabel64 = new widget.Label();
        TnmBidan = new widget.TextBox();
        jLabel54 = new widget.Label();
        cmbTmptPersalinan = new widget.ComboBox();
        TtmptPersalinanLain = new widget.TextBox();
        jLabel65 = new widget.Label();
        TalmtTmpPersalinan = new widget.TextBox();
        jLabel55 = new widget.Label();
        cmbCttnRujuk = new widget.ComboBox();
        jLabel66 = new widget.Label();
        TalasanMerujuk = new widget.TextBox();
        jLabel67 = new widget.Label();
        TtmptRujukan = new widget.TextBox();
        jLabel68 = new widget.Label();
        chkBidan8 = new widget.CekBox();
        chkTeman8 = new widget.CekBox();
        chkKlg8 = new widget.CekBox();
        chkSuami8 = new widget.CekBox();
        chkDukun8 = new widget.CekBox();
        chkTidakAda8 = new widget.CekBox();
        jLabel69 = new widget.Label();
        chkGawat9 = new widget.CekBox();
        chkPerdarahan9 = new widget.CekBox();
        chkHdk9 = new widget.CekBox();
        chkInfeksi9 = new widget.CekBox();
        chkPeb9 = new widget.CekBox();
        chkBidan9 = new widget.CekBox();
        chkLainya9 = new widget.CekBox();
        TmasalahLain = new widget.TextBox();
        jLabel56 = new widget.Label();
        jLabel70 = new widget.Label();
        cmbPartogram = new widget.ComboBox();
        jLabel71 = new widget.Label();
        Tkala1MasalahLain = new widget.TextBox();
        jLabel72 = new widget.Label();
        Tkala1Penata = new widget.TextBox();
        jLabel73 = new widget.Label();
        Tkala1Hasilnya = new widget.TextBox();
        jLabel57 = new widget.Label();
        jLabel74 = new widget.Label();
        cmbEpisiotomi = new widget.ComboBox();
        Tkala2YaIndikasi = new widget.TextBox();
        jLabel75 = new widget.Label();
        chkKala2Suami = new widget.CekBox();
        chkKala2Teman = new widget.CekBox();
        chkKala2TidakAda = new widget.CekBox();
        chkKala2Klg = new widget.CekBox();
        chkKala2Dukun = new widget.CekBox();
        jLabel76 = new widget.Label();
        cmbGawatJanin = new widget.ComboBox();
        Tkala2YaTindakanGawat = new widget.TextBox();
        jLabel77 = new widget.Label();
        Tkala2Pemantauan = new widget.TextBox();
        jLabel78 = new widget.Label();
        cmbDistosia = new widget.ComboBox();
        Tkala2YaTindakanDisto = new widget.TextBox();
        jLabel79 = new widget.Label();
        Tkala2MasalahLain = new widget.TextBox();
        jLabel58 = new widget.Label();
        jLabel80 = new widget.Label();
        cmbInisiasi = new widget.ComboBox();
        Tkala3Tidak = new widget.TextBox();
        jLabel81 = new widget.Label();
        Tkala3Lama = new widget.TextBox();
        jLabel82 = new widget.Label();
        jLabel83 = new widget.Label();
        cmbPemberianOksi = new widget.ComboBox();
        Tkala3PemberianOksi = new widget.TextBox();
        labelKetPemberian = new widget.Label();
        jLabel84 = new widget.Label();
        cmbPemberianUlang = new widget.ComboBox();
        Tkala3PemberianUlang = new widget.TextBox();
        jLabel85 = new widget.Label();
        cmbPenegangan = new widget.ComboBox();
        Tkala3Penegangan = new widget.TextBox();
        jLabel86 = new widget.Label();
        cmbMasase = new widget.ComboBox();
        Tkala3Masase = new widget.TextBox();
        jLabel87 = new widget.Label();
        cmbPlasenta25 = new widget.ComboBox();
        jLabel88 = new widget.Label();
        jLabel89 = new widget.Label();
        Tkala3Plasenta25A = new widget.TextBox();
        jLabel90 = new widget.Label();
        Tkala3Plasenta25B = new widget.TextBox();
        jLabel91 = new widget.Label();
        cmbPlasenta26 = new widget.ComboBox();
        Tkala3Plasenta26 = new widget.TextBox();
        jLabel92 = new widget.Label();
        cmbLaserasi = new widget.ComboBox();
        Tkala3Laserasi = new widget.TextBox();
        jLabel93 = new widget.Label();
        cmbJika = new widget.ComboBox();
        jLabel94 = new widget.Label();
        cmbTindakan = new widget.ComboBox();
        jLabel95 = new widget.Label();
        Tkala3Alasan = new widget.TextBox();
        jLabel96 = new widget.Label();
        cmbAtonia = new widget.ComboBox();
        Tkala3Atonia = new widget.TextBox();
        jLabel97 = new widget.Label();
        Tkala3Jumlah = new widget.TextBox();
        jLabel98 = new widget.Label();
        jLabel99 = new widget.Label();
        Tkala3Masalah = new widget.TextBox();
        jLabel100 = new widget.Label();
        Tkala3Penata = new widget.TextBox();
        jLabel101 = new widget.Label();
        Tkala3Hasilnya = new widget.TextBox();
        jLabel59 = new widget.Label();
        jLabel102 = new widget.Label();
        TbayiBB = new widget.TextBox();
        jLabel103 = new widget.Label();
        jLabel104 = new widget.Label();
        TbayiPB = new widget.TextBox();
        jLabel105 = new widget.Label();
        jLabel106 = new widget.Label();
        cmbJenkel = new widget.ComboBox();
        jLabel107 = new widget.Label();
        cmbPenilaian = new widget.ComboBox();
        jLabel108 = new widget.Label();
        cmbBayiLahir = new widget.ComboBox();
        cmbBayiNormal = new widget.ComboBox();
        cmbBayiAsfeksia = new widget.ComboBox();
        jLabel109 = new widget.Label();
        TbayiSebutkan = new widget.TextBox();
        jLabel110 = new widget.Label();
        TbayiTindakanA = new widget.TextBox();
        jLabel111 = new widget.Label();
        TbayiTindakanB = new widget.TextBox();
        jLabel112 = new widget.Label();
        TbayiTindakanC = new widget.TextBox();
        jLabel113 = new widget.Label();
        cmbPemberianAsi = new widget.ComboBox();
        TbayiYaPemberian = new widget.TextBox();
        jLabel114 = new widget.Label();
        jLabel115 = new widget.Label();
        TbayiTidakAlasan = new widget.TextBox();
        jLabel116 = new widget.Label();
        TbayiMasalah = new widget.TextBox();
        jLabel117 = new widget.Label();
        TbayiHasilnya = new widget.TextBox();
        jLabel60 = new widget.Label();
        jLabel119 = new widget.Label();
        TjamKe = new widget.TextBox();
        jLabel120 = new widget.Label();
        cmbJam11 = new widget.ComboBox();
        cmbMnt11 = new widget.ComboBox();
        cmbDtk11 = new widget.ComboBox();
        jLabel121 = new widget.Label();
        TtdKala4 = new widget.TextBox();
        jLabel61 = new widget.Label();
        TnadiKala4 = new widget.TextBox();
        jLabel62 = new widget.Label();
        TsuhuKala4 = new widget.TextBox();
        jLabel122 = new widget.Label();
        jLabel123 = new widget.Label();
        TTinggiFundus = new widget.TextBox();
        jLabel124 = new widget.Label();
        Tkontraksi = new widget.TextBox();
        jLabel125 = new widget.Label();
        Turin = new widget.TextBox();
        jLabel126 = new widget.Label();
        TdarahYang = new widget.TextBox();
        Scroll12 = new widget.ScrollPane();
        tbPemantauan = new widget.Table();
        BtnBaruKala4 = new widget.Button();
        BtnTambahKala4 = new widget.Button();
        BtnHapusKala4 = new widget.Button();
        BtnGantiKala4 = new widget.Button();
        jLabel127 = new widget.Label();
        TmasalahKala4 = new widget.TextBox();
        jLabel128 = new widget.Label();
        TpenataKala4 = new widget.TextBox();
        jLabel129 = new widget.Label();
        ThasilKala4 = new widget.TextBox();
        BtnBidan = new widget.Button();
        panelGlass11 = new widget.panelisi();
        BtnSimpan1 = new widget.Button();
        BtnBatal1 = new widget.Button();
        BtnGanti1 = new widget.Button();
        BtnHapus1 = new widget.Button();
        BtnCetak1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPartograf = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel118 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel130 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        BtnCetak2 = new widget.Button();
        BtnKeluar2 = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Partograf Persalinan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabPartograf.setBackground(new java.awt.Color(254, 255, 254));
        TabPartograf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPartograf.setName("TabPartograf"); // NOI18N
        TabPartograf.setPreferredSize(new java.awt.Dimension(0, 2000));
        TabPartograf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPartografMouseClicked(evt);
            }
        });

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);
        Scroll3.setPreferredSize(new java.awt.Dimension(102, 557));

        internalFrame20.setBorder(null);
        internalFrame20.setName("internalFrame20"); // NOI18N
        internalFrame20.setPreferredSize(new java.awt.Dimension(900, 1434));
        internalFrame20.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat : ");
        jLabel4.setName("jLabel4"); // NOI18N
        internalFrame20.add(jLabel4);
        jLabel4.setBounds(0, 10, 135, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        internalFrame20.add(TNoRw);
        TNoRw.setBounds(136, 10, 122, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        internalFrame20.add(TNoRM);
        TNoRM.setBounds(260, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        internalFrame20.add(TPasien);
        TPasien.setBounds(332, 10, 390, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Gravida : ");
        jLabel5.setName("jLabel5"); // NOI18N
        internalFrame20.add(jLabel5);
        jLabel5.setBounds(0, 66, 135, 23);

        Tgravida.setBackground(new java.awt.Color(245, 250, 240));
        Tgravida.setForeground(new java.awt.Color(0, 0, 0));
        Tgravida.setName("Tgravida"); // NOI18N
        Tgravida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgravidaKeyPressed(evt);
            }
        });
        internalFrame20.add(Tgravida);
        Tgravida.setBounds(136, 66, 90, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat : ");
        jLabel63.setName("jLabel63"); // NOI18N
        internalFrame20.add(jLabel63);
        jLabel63.setBounds(0, 38, 135, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        internalFrame20.add(TrgRawat);
        TrgRawat.setBounds(136, 38, 585, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Paritas : ");
        jLabel8.setName("jLabel8"); // NOI18N
        internalFrame20.add(jLabel8);
        jLabel8.setBounds(230, 66, 60, 23);

        Tparitas.setBackground(new java.awt.Color(245, 250, 240));
        Tparitas.setForeground(new java.awt.Color(0, 0, 0));
        Tparitas.setName("Tparitas"); // NOI18N
        Tparitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TparitasKeyPressed(evt);
            }
        });
        internalFrame20.add(Tparitas);
        Tparitas.setBounds(294, 66, 90, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Abortus : ");
        jLabel9.setName("jLabel9"); // NOI18N
        internalFrame20.add(jLabel9);
        jLabel9.setBounds(385, 66, 60, 23);

        Tabortus.setBackground(new java.awt.Color(245, 250, 240));
        Tabortus.setForeground(new java.awt.Color(0, 0, 0));
        Tabortus.setName("Tabortus"); // NOI18N
        Tabortus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TabortusKeyPressed(evt);
            }
        });
        internalFrame20.add(Tabortus);
        Tabortus.setBounds(447, 66, 90, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Tgl. Masuk : ");
        jLabel10.setName("jLabel10"); // NOI18N
        internalFrame20.add(jLabel10);
        jLabel10.setBounds(0, 94, 135, 23);

        TtglMasuk.setEditable(false);
        TtglMasuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-05-2026" }));
        TtglMasuk.setDisplayFormat("dd-MM-yyyy");
        TtglMasuk.setName("TtglMasuk"); // NOI18N
        TtglMasuk.setOpaque(false);
        TtglMasuk.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame20.add(TtglMasuk);
        TtglMasuk.setBounds(136, 94, 90, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Jam : ");
        jLabel11.setName("jLabel11"); // NOI18N
        internalFrame20.add(jLabel11);
        jLabel11.setBounds(230, 94, 60, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        internalFrame20.add(cmbJam);
        cmbJam.setBounds(294, 94, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        internalFrame20.add(cmbMnt);
        cmbMnt.setBounds(345, 94, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        internalFrame20.add(cmbDtk);
        cmbDtk.setBounds(397, 94, 45, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Ketuban Pecah : ");
        jLabel12.setName("jLabel12"); // NOI18N
        internalFrame20.add(jLabel12);
        jLabel12.setBounds(0, 122, 135, 23);

        cmbKetuban.setForeground(new java.awt.Color(0, 0, 0));
        cmbKetuban.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKetuban.setName("cmbKetuban"); // NOI18N
        cmbKetuban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKetubanActionPerformed(evt);
            }
        });
        internalFrame20.add(cmbKetuban);
        cmbKetuban.setBounds(136, 122, 60, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Sejak Jam : ");
        jLabel13.setName("jLabel13"); // NOI18N
        internalFrame20.add(jLabel13);
        jLabel13.setBounds(200, 122, 90, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        internalFrame20.add(cmbJam1);
        cmbJam1.setBounds(294, 122, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        internalFrame20.add(cmbMnt1);
        cmbMnt1.setBounds(345, 122, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        internalFrame20.add(cmbDtk1);
        cmbDtk1.setBounds(397, 122, 45, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Denyut Jantung Janin : ");
        jLabel15.setName("jLabel15"); // NOI18N
        internalFrame20.add(jLabel15);
        jLabel15.setBounds(0, 150, 135, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("/menit   DJJ : ");
        jLabel16.setName("jLabel16"); // NOI18N
        internalFrame20.add(jLabel16);
        jLabel16.setBounds(240, 150, 70, 23);

        Tdjj.setBackground(new java.awt.Color(245, 250, 240));
        Tdjj.setForeground(new java.awt.Color(0, 0, 0));
        Tdjj.setName("Tdjj"); // NOI18N
        Tdjj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdjjKeyPressed(evt);
            }
        });
        internalFrame20.add(Tdjj);
        Tdjj.setBounds(313, 150, 60, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("x/menit");
        jLabel17.setName("jLabel17"); // NOI18N
        internalFrame20.add(jLabel17);
        jLabel17.setBounds(380, 150, 50, 23);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbDjj.setName("tbDjj"); // NOI18N
        tbDjj.getTableHeader().setReorderingAllowed(false);
        tbDjj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDjjMouseClicked(evt);
            }
        });
        tbDjj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDjjKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbDjj);

        internalFrame20.add(Scroll1);
        Scroll1.setBounds(136, 178, 220, 100);

        BtnBaruDjj.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaruDjj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaruDjj.setText("Baru");
        BtnBaruDjj.setToolTipText("Data DJJ Baru");
        BtnBaruDjj.setName("BtnBaruDjj"); // NOI18N
        BtnBaruDjj.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnBaruDjj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruDjjActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnBaruDjj);
        BtnBaruDjj.setBounds(365, 178, 90, 30);

        BtnTambahDjj.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahDjj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahDjj.setText("Tambah");
        BtnTambahDjj.setToolTipText("Tambah Data DJJ");
        BtnTambahDjj.setName("BtnTambahDjj"); // NOI18N
        BtnTambahDjj.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahDjj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahDjjActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnTambahDjj);
        BtnTambahDjj.setBounds(365, 217, 90, 30);

        BtnHapusDjj.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusDjj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusDjj.setText("Hapus");
        BtnHapusDjj.setToolTipText("Hapus DJJ");
        BtnHapusDjj.setName("BtnHapusDjj"); // NOI18N
        BtnHapusDjj.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusDjj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusDjjActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnHapusDjj);
        BtnHapusDjj.setBounds(470, 178, 90, 30);

        BtnGantiDjj.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiDjj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiDjj.setText("Ganti");
        BtnGantiDjj.setToolTipText("Ganti DJJ");
        BtnGantiDjj.setName("BtnGantiDjj"); // NOI18N
        BtnGantiDjj.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiDjj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiDjjActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnGantiDjj);
        BtnGantiDjj.setBounds(470, 217, 90, 30);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Air Ketuban : ");
        jLabel18.setName("jLabel18"); // NOI18N
        internalFrame20.add(jLabel18);
        jLabel18.setBounds(0, 284, 135, 23);

        cmbAirKetuban.setForeground(new java.awt.Color(0, 0, 0));
        cmbAirKetuban.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "U : Ketuban utuh (belum pecah)", "J : Ketuban sudah pecah dan air ketuban jernih", "M : Ketuban sudah pecah dan air ketuban bercampur mekonium", "D : Ketuban sudah pecah dan air ketuban bercampur darah", "K : Ketuban sudah pecah dan tidak air ketuban (kering)" }));
        cmbAirKetuban.setName("cmbAirKetuban"); // NOI18N
        internalFrame20.add(cmbAirKetuban);
        cmbAirKetuban.setBounds(136, 284, 340, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Mulase : ");
        jLabel19.setName("jLabel19"); // NOI18N
        internalFrame20.add(jLabel19);
        jLabel19.setBounds(480, 284, 65, 23);

        cmbMulase.setForeground(new java.awt.Color(0, 0, 0));
        cmbMulase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0 : Tulang-tulang kepala janin terpisah, sutura dengan mudah dapat dipalpasi", "1 : Tulang-tulang kepala janin hanya saling bersentuhan", "2 : Tulang-tulang kepala janin saling tumpang tindih, tapi masih dapat dipisahkan", "3 : Tulang-tulang kepala janin tumpang tindih dan tidak dapat dipisahkan" }));
        cmbMulase.setName("cmbMulase"); // NOI18N
        internalFrame20.add(cmbMulase);
        cmbMulase.setBounds(548, 284, 420, 23);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbAirKetuban.setName("tbAirKetuban"); // NOI18N
        tbAirKetuban.getTableHeader().setReorderingAllowed(false);
        tbAirKetuban.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAirKetubanMouseClicked(evt);
            }
        });
        tbAirKetuban.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAirKetubanKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbAirKetuban);

        internalFrame20.add(Scroll2);
        Scroll2.setBounds(136, 312, 800, 135);

        BtnBaruAKM.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaruAKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaruAKM.setText("Baru");
        BtnBaruAKM.setToolTipText("Data Air Ketuban/Mulase Baru");
        BtnBaruAKM.setName("BtnBaruAKM"); // NOI18N
        BtnBaruAKM.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnBaruAKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruAKMActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnBaruAKM);
        BtnBaruAKM.setBounds(945, 312, 90, 30);

        BtnTambahAKM.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahAKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahAKM.setText("Tambah");
        BtnTambahAKM.setToolTipText("Tambah Data Air Ketuban/Mulase");
        BtnTambahAKM.setName("BtnTambahAKM"); // NOI18N
        BtnTambahAKM.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahAKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahAKMActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnTambahAKM);
        BtnTambahAKM.setBounds(945, 351, 90, 30);

        BtnHapusAKM.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusAKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusAKM.setText("Hapus");
        BtnHapusAKM.setToolTipText("Hapus Air Ketuban/Mulase");
        BtnHapusAKM.setName("BtnHapusAKM"); // NOI18N
        BtnHapusAKM.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusAKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusAKMActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnHapusAKM);
        BtnHapusAKM.setBounds(1050, 312, 90, 30);

        BtnGantiAKM.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiAKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiAKM.setText("Ganti");
        BtnGantiAKM.setToolTipText("Ganti Air Ketuban/Mulase");
        BtnGantiAKM.setName("BtnGantiAKM"); // NOI18N
        BtnGantiAKM.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiAKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiAKMActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnGantiAKM);
        BtnGantiAKM.setBounds(1050, 351, 90, 30);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Pembukaan Serviks : ");
        jLabel21.setName("jLabel21"); // NOI18N
        internalFrame20.add(jLabel21);
        jLabel21.setBounds(0, 455, 135, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Waktu Ke : ");
        jLabel22.setName("jLabel22"); // NOI18N
        internalFrame20.add(jLabel22);
        jLabel22.setBounds(136, 455, 64, 23);

        cmbWaktuKe.setForeground(new java.awt.Color(0, 0, 0));
        cmbWaktuKe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" }));
        cmbWaktuKe.setName("cmbWaktuKe"); // NOI18N
        internalFrame20.add(cmbWaktuKe);
        cmbWaktuKe.setBounds(203, 455, 45, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Jam : ");
        jLabel23.setName("jLabel23"); // NOI18N
        internalFrame20.add(jLabel23);
        jLabel23.setBounds(250, 455, 40, 23);

        cmbJam4.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam4.setName("cmbJam4"); // NOI18N
        cmbJam4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam4MouseReleased(evt);
            }
        });
        internalFrame20.add(cmbJam4);
        cmbJam4.setBounds(294, 455, 45, 23);

        cmbMnt4.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt4.setName("cmbMnt4"); // NOI18N
        cmbMnt4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt4MouseReleased(evt);
            }
        });
        internalFrame20.add(cmbMnt4);
        cmbMnt4.setBounds(345, 455, 45, 23);

        cmbDtk4.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk4.setName("cmbDtk4"); // NOI18N
        cmbDtk4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk4MouseReleased(evt);
            }
        });
        internalFrame20.add(cmbDtk4);
        cmbDtk4.setBounds(397, 455, 45, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Centimeter (cm) :");
        jLabel24.setName("jLabel24"); // NOI18N
        internalFrame20.add(jLabel24);
        jLabel24.setBounds(445, 455, 100, 23);

        cmbCm.setForeground(new java.awt.Color(0, 0, 0));
        cmbCm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cmbCm.setName("cmbCm"); // NOI18N
        internalFrame20.add(cmbCm);
        cmbCm.setBounds(550, 455, 45, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Pembukaan Serviks :");
        jLabel25.setName("jLabel25"); // NOI18N
        internalFrame20.add(jLabel25);
        jLabel25.setBounds(136, 483, 115, 23);

        chkPembukaan.setBackground(new java.awt.Color(255, 255, 250));
        chkPembukaan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPembukaan.setForeground(new java.awt.Color(0, 0, 0));
        chkPembukaan.setText("X");
        chkPembukaan.setBorderPainted(true);
        chkPembukaan.setBorderPaintedFlat(true);
        chkPembukaan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkPembukaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPembukaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPembukaan.setName("chkPembukaan"); // NOI18N
        chkPembukaan.setOpaque(false);
        chkPembukaan.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame20.add(chkPembukaan);
        chkPembukaan.setBounds(257, 483, 40, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Turunnya Kepala :");
        jLabel26.setName("jLabel26"); // NOI18N
        internalFrame20.add(jLabel26);
        jLabel26.setBounds(306, 483, 110, 23);

        chkTurunya.setBackground(new java.awt.Color(255, 255, 250));
        chkTurunya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTurunya.setForeground(new java.awt.Color(0, 0, 0));
        chkTurunya.setText("O");
        chkTurunya.setBorderPainted(true);
        chkTurunya.setBorderPaintedFlat(true);
        chkTurunya.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkTurunya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTurunya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTurunya.setName("chkTurunya"); // NOI18N
        chkTurunya.setOpaque(false);
        chkTurunya.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame20.add(chkTurunya);
        chkTurunya.setBounds(422, 483, 40, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Keterangan :");
        jLabel27.setName("jLabel27"); // NOI18N
        internalFrame20.add(jLabel27);
        jLabel27.setBounds(470, 483, 80, 23);

        TketServik.setForeground(new java.awt.Color(0, 0, 0));
        TketServik.setName("TketServik"); // NOI18N
        TketServik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketServikKeyPressed(evt);
            }
        });
        internalFrame20.add(TketServik);
        TketServik.setBounds(555, 483, 410, 23);

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbServik.setName("tbServik"); // NOI18N
        tbServik.getTableHeader().setReorderingAllowed(false);
        tbServik.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbServikMouseClicked(evt);
            }
        });
        tbServik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbServikKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbServik);

        internalFrame20.add(Scroll4);
        Scroll4.setBounds(136, 511, 585, 135);

        BtnBaruServik.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaruServik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaruServik.setText("Baru");
        BtnBaruServik.setToolTipText("Data Pembukaan Serviks Baru");
        BtnBaruServik.setName("BtnBaruServik"); // NOI18N
        BtnBaruServik.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnBaruServik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruServikActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnBaruServik);
        BtnBaruServik.setBounds(730, 511, 90, 30);

        BtnHapusServik.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusServik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusServik.setText("Hapus");
        BtnHapusServik.setToolTipText("Hapus Data Serviks");
        BtnHapusServik.setName("BtnHapusServik"); // NOI18N
        BtnHapusServik.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusServik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusServikActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnHapusServik);
        BtnHapusServik.setBounds(835, 511, 90, 30);

        BtnTambahServik.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahServik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahServik.setText("Tambah");
        BtnTambahServik.setToolTipText("Tambah Data Pembukaan Serviks");
        BtnTambahServik.setName("BtnTambahServik"); // NOI18N
        BtnTambahServik.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahServik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahServikActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnTambahServik);
        BtnTambahServik.setBounds(730, 550, 90, 30);

        BtnGantiServik.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiServik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiServik.setText("Ganti");
        BtnGantiServik.setToolTipText("Ganti Data Serviks");
        BtnGantiServik.setName("BtnGantiServik"); // NOI18N
        BtnGantiServik.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiServik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiServikActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnGantiServik);
        BtnGantiServik.setBounds(835, 550, 90, 30);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Kontraksi : ");
        jLabel28.setName("jLabel28"); // NOI18N
        internalFrame20.add(jLabel28);
        jLabel28.setBounds(0, 655, 135, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Lajur Kontraksi : ");
        jLabel29.setName("jLabel29"); // NOI18N
        internalFrame20.add(jLabel29);
        jLabel29.setBounds(136, 655, 120, 23);

        cmbLajur.setForeground(new java.awt.Color(0, 0, 0));
        cmbLajur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        cmbLajur.setName("cmbLajur"); // NOI18N
        internalFrame20.add(cmbLajur);
        cmbLajur.setBounds(259, 655, 45, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Detik Kontraksi : ");
        jLabel30.setName("jLabel30"); // NOI18N
        internalFrame20.add(jLabel30);
        jLabel30.setBounds(306, 655, 104, 23);

        cmbDetik.setForeground(new java.awt.Color(0, 0, 0));
        cmbDetik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "< 20", "30 - 40", "> 40" }));
        cmbDetik.setName("cmbDetik"); // NOI18N
        internalFrame20.add(cmbDetik);
        cmbDetik.setBounds(414, 655, 70, 23);

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbKontraksi.setName("tbKontraksi"); // NOI18N
        tbKontraksi.getTableHeader().setReorderingAllowed(false);
        tbKontraksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKontraksiMouseClicked(evt);
            }
        });
        tbKontraksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKontraksiKeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbKontraksi);

        internalFrame20.add(Scroll5);
        Scroll5.setBounds(136, 683, 190, 138);

        BtnBaruKontraksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaruKontraksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaruKontraksi.setText("Baru");
        BtnBaruKontraksi.setToolTipText("Data Kontraksi Baru");
        BtnBaruKontraksi.setName("BtnBaruKontraksi"); // NOI18N
        BtnBaruKontraksi.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnBaruKontraksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruKontraksiActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnBaruKontraksi);
        BtnBaruKontraksi.setBounds(340, 683, 90, 30);

        BtnHapusKontraksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusKontraksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusKontraksi.setText("Hapus");
        BtnHapusKontraksi.setToolTipText("Hapus Data Kontraksi");
        BtnHapusKontraksi.setName("BtnHapusKontraksi"); // NOI18N
        BtnHapusKontraksi.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusKontraksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusKontraksiActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnHapusKontraksi);
        BtnHapusKontraksi.setBounds(445, 683, 90, 30);

        BtnTambahKontraksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahKontraksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahKontraksi.setText("Tambah");
        BtnTambahKontraksi.setToolTipText("Tambah Data Kontraksi");
        BtnTambahKontraksi.setName("BtnTambahKontraksi"); // NOI18N
        BtnTambahKontraksi.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahKontraksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahKontraksiActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnTambahKontraksi);
        BtnTambahKontraksi.setBounds(340, 722, 90, 30);

        BtnGantiKontraksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiKontraksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiKontraksi.setText("Ganti");
        BtnGantiKontraksi.setToolTipText("Ganti Data Kontraksi");
        BtnGantiKontraksi.setName("BtnGantiKontraksi"); // NOI18N
        BtnGantiKontraksi.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiKontraksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiKontraksiActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnGantiKontraksi);
        BtnGantiKontraksi.setBounds(445, 722, 90, 30);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Oksitosin : ");
        jLabel34.setName("jLabel34"); // NOI18N
        internalFrame20.add(jLabel34);
        jLabel34.setBounds(0, 827, 135, 23);

        Ttetes.setBackground(new java.awt.Color(245, 250, 240));
        Ttetes.setForeground(new java.awt.Color(0, 0, 0));
        Ttetes.setName("Ttetes"); // NOI18N
        internalFrame20.add(Ttetes);
        Ttetes.setBounds(466, 827, 150, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("tetes/menit");
        jLabel35.setName("jLabel35"); // NOI18N
        internalFrame20.add(jLabel35);
        jLabel35.setBounds(624, 827, 70, 23);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbOksitosin.setName("tbOksitosin"); // NOI18N
        tbOksitosin.getTableHeader().setReorderingAllowed(false);
        tbOksitosin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbOksitosinMouseClicked(evt);
            }
        });
        tbOksitosin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbOksitosinKeyPressed(evt);
            }
        });
        Scroll6.setViewportView(tbOksitosin);

        internalFrame20.add(Scroll6);
        Scroll6.setBounds(136, 855, 280, 100);

        BtnBaruOksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaruOksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaruOksi.setText("Baru");
        BtnBaruOksi.setToolTipText("Data Oksitosin Baru");
        BtnBaruOksi.setName("BtnBaruOksi"); // NOI18N
        BtnBaruOksi.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnBaruOksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruOksiActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnBaruOksi);
        BtnBaruOksi.setBounds(425, 855, 90, 30);

        BtnHapusOksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusOksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusOksi.setText("Hapus");
        BtnHapusOksi.setToolTipText("Hapus Oksitosin");
        BtnHapusOksi.setName("BtnHapusOksi"); // NOI18N
        BtnHapusOksi.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusOksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusOksiActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnHapusOksi);
        BtnHapusOksi.setBounds(530, 855, 90, 30);

        BtnTambahOksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahOksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahOksi.setText("Tambah");
        BtnTambahOksi.setToolTipText("Tambah Data Oksitosin");
        BtnTambahOksi.setName("BtnTambahOksi"); // NOI18N
        BtnTambahOksi.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahOksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahOksiActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnTambahOksi);
        BtnTambahOksi.setBounds(425, 894, 90, 30);

        BtnGantiOksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiOksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiOksi.setText("Ganti");
        BtnGantiOksi.setToolTipText("Ganti Oksitosin");
        BtnGantiOksi.setName("BtnGantiOksi"); // NOI18N
        BtnGantiOksi.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiOksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiOksiActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnGantiOksi);
        BtnGantiOksi.setBounds(530, 894, 90, 30);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Obat & Cairan IV : ");
        jLabel37.setName("jLabel37"); // NOI18N
        internalFrame20.add(jLabel37);
        jLabel37.setBounds(0, 961, 135, 23);

        TobatCairan.setBackground(new java.awt.Color(245, 250, 240));
        TobatCairan.setForeground(new java.awt.Color(0, 0, 0));
        TobatCairan.setName("TobatCairan"); // NOI18N
        internalFrame20.add(TobatCairan);
        TobatCairan.setBounds(136, 961, 340, 23);

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbObat.setName("tbObat"); // NOI18N
        tbObat.getTableHeader().setReorderingAllowed(false);
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll7.setViewportView(tbObat);

        internalFrame20.add(Scroll7);
        Scroll7.setBounds(136, 989, 340, 100);

        BtnBaruObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaruObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaruObat.setText("Baru");
        BtnBaruObat.setToolTipText("Data Obat & Cairan Baru");
        BtnBaruObat.setName("BtnBaruObat"); // NOI18N
        BtnBaruObat.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnBaruObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruObatActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnBaruObat);
        BtnBaruObat.setBounds(490, 989, 90, 30);

        BtnHapusObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusObat.setText("Hapus");
        BtnHapusObat.setToolTipText("Hapus Obat & Cairan");
        BtnHapusObat.setName("BtnHapusObat"); // NOI18N
        BtnHapusObat.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusObatActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnHapusObat);
        BtnHapusObat.setBounds(600, 989, 90, 30);

        BtnTambahObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahObat.setText("Tambah");
        BtnTambahObat.setToolTipText("Tambah Data Obat & Cairan");
        BtnTambahObat.setName("BtnTambahObat"); // NOI18N
        BtnTambahObat.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahObatActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnTambahObat);
        BtnTambahObat.setBounds(490, 1028, 90, 30);

        BtnGantiObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiObat.setText("Ganti");
        BtnGantiObat.setToolTipText("Ganti Obat & Cairan");
        BtnGantiObat.setName("BtnGantiObat"); // NOI18N
        BtnGantiObat.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiObatActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnGantiObat);
        BtnGantiObat.setBounds(600, 1028, 90, 30);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Nadi : ");
        jLabel38.setName("jLabel38"); // NOI18N
        internalFrame20.add(jLabel38);
        jLabel38.setBounds(0, 1095, 135, 23);

        Tnadi.setBackground(new java.awt.Color(245, 250, 240));
        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        internalFrame20.add(Tnadi);
        Tnadi.setBounds(136, 1095, 50, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("x/menit      Tekanan Darah : Sistole : ");
        jLabel40.setName("jLabel40"); // NOI18N
        internalFrame20.add(jLabel40);
        jLabel40.setBounds(192, 1095, 180, 23);

        Tsistol.setBackground(new java.awt.Color(245, 250, 240));
        Tsistol.setForeground(new java.awt.Color(0, 0, 0));
        Tsistol.setName("Tsistol"); // NOI18N
        Tsistol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsistolKeyPressed(evt);
            }
        });
        internalFrame20.add(Tsistol);
        Tsistol.setBounds(373, 1095, 50, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Diastole : ");
        jLabel41.setName("jLabel41"); // NOI18N
        internalFrame20.add(jLabel41);
        jLabel41.setBounds(430, 1095, 60, 23);

        Tdistol.setBackground(new java.awt.Color(245, 250, 240));
        Tdistol.setForeground(new java.awt.Color(0, 0, 0));
        Tdistol.setName("Tdistol"); // NOI18N
        Tdistol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdistolKeyPressed(evt);
            }
        });
        internalFrame20.add(Tdistol);
        Tdistol.setBounds(494, 1095, 50, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("mmHg");
        jLabel42.setName("jLabel42"); // NOI18N
        internalFrame20.add(jLabel42);
        jLabel42.setBounds(550, 1095, 40, 23);

        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbNadi.setName("tbNadi"); // NOI18N
        tbNadi.getTableHeader().setReorderingAllowed(false);
        tbNadi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNadiMouseClicked(evt);
            }
        });
        tbNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbNadiKeyPressed(evt);
            }
        });
        Scroll8.setViewportView(tbNadi);

        internalFrame20.add(Scroll8);
        Scroll8.setBounds(136, 1123, 270, 110);

        BtnBaruNadi.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaruNadi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaruNadi.setText("Baru");
        BtnBaruNadi.setToolTipText("Data Nadi Tensi Baru");
        BtnBaruNadi.setName("BtnBaruNadi"); // NOI18N
        BtnBaruNadi.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnBaruNadi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruNadiActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnBaruNadi);
        BtnBaruNadi.setBounds(420, 1123, 90, 30);

        BtnHapusNadi.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusNadi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusNadi.setText("Hapus");
        BtnHapusNadi.setToolTipText("Hapus Data Nadi Tensi");
        BtnHapusNadi.setName("BtnHapusNadi"); // NOI18N
        BtnHapusNadi.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusNadi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusNadiActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnHapusNadi);
        BtnHapusNadi.setBounds(525, 1123, 90, 30);

        BtnTambahNadi.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahNadi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahNadi.setText("Tambah");
        BtnTambahNadi.setToolTipText("Tambah Data Nadi Tensi");
        BtnTambahNadi.setName("BtnTambahNadi"); // NOI18N
        BtnTambahNadi.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahNadi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahNadiActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnTambahNadi);
        BtnTambahNadi.setBounds(420, 1162, 90, 30);

        BtnGantiNadi.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiNadi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiNadi.setText("Ganti");
        BtnGantiNadi.setToolTipText("Ganti Data Nadi Tensi");
        BtnGantiNadi.setName("BtnGantiNadi"); // NOI18N
        BtnGantiNadi.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiNadi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiNadiActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnGantiNadi);
        BtnGantiNadi.setBounds(525, 1162, 90, 30);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Suhu : ");
        jLabel44.setName("jLabel44"); // NOI18N
        internalFrame20.add(jLabel44);
        jLabel44.setBounds(0, 1238, 135, 23);

        Tsuhu.setBackground(new java.awt.Color(245, 250, 240));
        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        internalFrame20.add(Tsuhu);
        Tsuhu.setBounds(136, 1238, 50, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("°C");
        jLabel45.setName("jLabel45"); // NOI18N
        internalFrame20.add(jLabel45);
        jLabel45.setBounds(192, 1238, 30, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Jam : ");
        jLabel46.setName("jLabel46"); // NOI18N
        internalFrame20.add(jLabel46);
        jLabel46.setBounds(250, 1238, 40, 23);

        cmbJam9.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam9.setName("cmbJam9"); // NOI18N
        cmbJam9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam9MouseReleased(evt);
            }
        });
        internalFrame20.add(cmbJam9);
        cmbJam9.setBounds(295, 1238, 45, 23);

        cmbMnt9.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt9.setName("cmbMnt9"); // NOI18N
        cmbMnt9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt9MouseReleased(evt);
            }
        });
        internalFrame20.add(cmbMnt9);
        cmbMnt9.setBounds(347, 1238, 45, 23);

        cmbDtk9.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk9.setName("cmbDtk9"); // NOI18N
        cmbDtk9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk9MouseReleased(evt);
            }
        });
        internalFrame20.add(cmbDtk9);
        cmbDtk9.setBounds(400, 1238, 45, 23);

        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        tbSuhu.setName("tbSuhu"); // NOI18N
        tbSuhu.getTableHeader().setReorderingAllowed(false);
        tbSuhu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSuhuMouseClicked(evt);
            }
        });
        tbSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSuhuKeyPressed(evt);
            }
        });
        Scroll9.setViewportView(tbSuhu);

        internalFrame20.add(Scroll9);
        Scroll9.setBounds(136, 1266, 170, 140);

        BtnBaruSuhu.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaruSuhu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaruSuhu.setText("Baru");
        BtnBaruSuhu.setToolTipText("Data Suhu Baru");
        BtnBaruSuhu.setName("BtnBaruSuhu"); // NOI18N
        BtnBaruSuhu.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnBaruSuhu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruSuhuActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnBaruSuhu);
        BtnBaruSuhu.setBounds(315, 1266, 90, 30);

        BtnHapusSuhu.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusSuhu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusSuhu.setText("Hapus");
        BtnHapusSuhu.setToolTipText("Hapus Data Suhu");
        BtnHapusSuhu.setName("BtnHapusSuhu"); // NOI18N
        BtnHapusSuhu.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusSuhu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusSuhuActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnHapusSuhu);
        BtnHapusSuhu.setBounds(420, 1266, 90, 30);

        BtnTambahSuhu.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahSuhu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahSuhu.setText("Tambah");
        BtnTambahSuhu.setToolTipText("Tambah Data Suhu");
        BtnTambahSuhu.setName("BtnTambahSuhu"); // NOI18N
        BtnTambahSuhu.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahSuhu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahSuhuActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnTambahSuhu);
        BtnTambahSuhu.setBounds(315, 1305, 90, 30);

        BtnGantiSuhu.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiSuhu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiSuhu.setText("Ganti");
        BtnGantiSuhu.setToolTipText("Ganti Data Suhu");
        BtnGantiSuhu.setName("BtnGantiSuhu"); // NOI18N
        BtnGantiSuhu.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiSuhu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiSuhuActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnGantiSuhu);
        BtnGantiSuhu.setBounds(420, 1305, 90, 30);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Urin : ");
        jLabel47.setName("jLabel47"); // NOI18N
        internalFrame20.add(jLabel47);
        jLabel47.setBounds(520, 1238, 100, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Protein : ");
        jLabel48.setName("jLabel48"); // NOI18N
        internalFrame20.add(jLabel48);
        jLabel48.setBounds(620, 1238, 60, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Aseton : ");
        jLabel49.setName("jLabel49"); // NOI18N
        internalFrame20.add(jLabel49);
        jLabel49.setBounds(795, 1238, 60, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Volume : ");
        jLabel50.setName("jLabel50"); // NOI18N
        internalFrame20.add(jLabel50);
        jLabel50.setBounds(620, 1266, 60, 23);

        Tvolume.setBackground(new java.awt.Color(245, 250, 240));
        Tvolume.setForeground(new java.awt.Color(0, 0, 0));
        Tvolume.setName("Tvolume"); // NOI18N
        Tvolume.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TvolumeKeyPressed(evt);
            }
        });
        internalFrame20.add(Tvolume);
        Tvolume.setBounds(685, 1266, 110, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("cc.    Jam : ");
        jLabel51.setName("jLabel51"); // NOI18N
        internalFrame20.add(jLabel51);
        jLabel51.setBounds(800, 1266, 57, 23);

        cmbJam10.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam10.setName("cmbJam10"); // NOI18N
        cmbJam10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam10MouseReleased(evt);
            }
        });
        internalFrame20.add(cmbJam10);
        cmbJam10.setBounds(862, 1266, 45, 23);

        cmbMnt10.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt10.setName("cmbMnt10"); // NOI18N
        cmbMnt10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt10MouseReleased(evt);
            }
        });
        internalFrame20.add(cmbMnt10);
        cmbMnt10.setBounds(914, 1266, 45, 23);

        cmbDtk10.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk10.setName("cmbDtk10"); // NOI18N
        cmbDtk10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk10MouseReleased(evt);
            }
        });
        internalFrame20.add(cmbDtk10);
        cmbDtk10.setBounds(965, 1266, 45, 23);

        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        tbUrin.setName("tbUrin"); // NOI18N
        tbUrin.getTableHeader().setReorderingAllowed(false);
        tbUrin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUrinMouseClicked(evt);
            }
        });
        tbUrin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbUrinKeyPressed(evt);
            }
        });
        Scroll10.setViewportView(tbUrin);

        internalFrame20.add(Scroll10);
        Scroll10.setBounds(635, 1300, 320, 110);

        BtnBaruUrin.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaruUrin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaruUrin.setText("Baru");
        BtnBaruUrin.setToolTipText("Data Urin Baru");
        BtnBaruUrin.setName("BtnBaruUrin"); // NOI18N
        BtnBaruUrin.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnBaruUrin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruUrinActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnBaruUrin);
        BtnBaruUrin.setBounds(965, 1300, 90, 30);

        BtnHapusUrin.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusUrin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusUrin.setText("Hapus");
        BtnHapusUrin.setToolTipText("Hapus Data Urin");
        BtnHapusUrin.setName("BtnHapusUrin"); // NOI18N
        BtnHapusUrin.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusUrin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusUrinActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnHapusUrin);
        BtnHapusUrin.setBounds(1065, 1300, 90, 30);

        BtnTambahUrin.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahUrin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahUrin.setText("Tambah");
        BtnTambahUrin.setToolTipText("Tambah Data Urin");
        BtnTambahUrin.setName("BtnTambahUrin"); // NOI18N
        BtnTambahUrin.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahUrin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahUrinActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnTambahUrin);
        BtnTambahUrin.setBounds(965, 1340, 90, 30);

        BtnGantiUrin.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiUrin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiUrin.setText("Ganti");
        BtnGantiUrin.setToolTipText("Ganti Data Urin");
        BtnGantiUrin.setName("BtnGantiUrin"); // NOI18N
        BtnGantiUrin.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiUrin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiUrinActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnGantiUrin);
        BtnGantiUrin.setBounds(1065, 1340, 90, 30);

        cmbProtein.setForeground(new java.awt.Color(0, 0, 0));
        cmbProtein.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "-", "+", "++", "+++" }));
        cmbProtein.setName("cmbProtein"); // NOI18N
        internalFrame20.add(cmbProtein);
        cmbProtein.setBounds(685, 1238, 60, 23);

        cmbAseton.setForeground(new java.awt.Color(0, 0, 0));
        cmbAseton.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "-", "+" }));
        cmbAseton.setName("cmbAseton"); // NOI18N
        internalFrame20.add(cmbAseton);
        cmbAseton.setBounds(862, 1238, 40, 23);

        Tjeda.setBackground(new java.awt.Color(245, 250, 240));
        Tjeda.setForeground(new java.awt.Color(0, 0, 0));
        Tjeda.setName("Tjeda"); // NOI18N
        Tjeda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjedaKeyPressed(evt);
            }
        });
        internalFrame20.add(Tjeda);
        Tjeda.setBounds(185, 150, 50, 23);

        jLabel131.setForeground(new java.awt.Color(0, 0, 0));
        jLabel131.setText("Jeda : ");
        jLabel131.setName("jLabel131"); // NOI18N
        internalFrame20.add(jLabel131);
        jLabel131.setBounds(136, 150, 45, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Tiap 10 Menit   ");
        jLabel32.setName("jLabel32"); // NOI18N
        internalFrame20.add(jLabel32);
        jLabel32.setBounds(0, 670, 135, 23);

        jLabel132.setForeground(new java.awt.Color(0, 0, 0));
        jLabel132.setText("Konsentrasi :");
        jLabel132.setName("jLabel132"); // NOI18N
        internalFrame20.add(jLabel132);
        jLabel132.setBounds(136, 827, 80, 23);

        Toksitosin.setBackground(new java.awt.Color(245, 250, 240));
        Toksitosin.setForeground(new java.awt.Color(0, 0, 0));
        Toksitosin.setName("Toksitosin"); // NOI18N
        Toksitosin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ToksitosinKeyPressed(evt);
            }
        });
        internalFrame20.add(Toksitosin);
        Toksitosin.setBounds(220, 827, 150, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("U/L    Kecepatan :");
        jLabel36.setName("jLabel36"); // NOI18N
        internalFrame20.add(jLabel36);
        jLabel36.setBounds(375, 827, 90, 23);

        Scroll3.setViewportView(internalFrame20);

        jPanel3.add(Scroll3, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
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

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setText("Ganti");
        BtnGanti.setToolTipText("Alt+G");
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        BtnGanti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantiKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnGanti);

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Hapus Air Ketuban/Mulase");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnCetak.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetak.setText("Cetak");
        BtnCetak.setToolTipText("Hapus Air Ketuban/Mulase");
        BtnCetak.setName("BtnCetak"); // NOI18N
        BtnCetak.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnCetak);

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
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        TabPartograf.addTab("Input Partograf", jPanel3);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass9.setLayout(new java.awt.BorderLayout());

        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);
        Scroll11.setPreferredSize(new java.awt.Dimension(102, 557));

        internalFrame21.setBorder(null);
        internalFrame21.setName("internalFrame21"); // NOI18N
        internalFrame21.setPreferredSize(new java.awt.Dimension(900, 1433));
        internalFrame21.setLayout(null);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("CATATAN PERSALINAN :");
        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel52.setName("jLabel52"); // NOI18N
        internalFrame21.add(jLabel52);
        jLabel52.setBounds(0, 10, 160, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("1. Tanggal :");
        jLabel53.setName("jLabel53"); // NOI18N
        internalFrame21.add(jLabel53);
        jLabel53.setBounds(0, 38, 175, 23);

        TtglCatatan.setEditable(false);
        TtglCatatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-05-2026" }));
        TtglCatatan.setDisplayFormat("dd-MM-yyyy");
        TtglCatatan.setName("TtglCatatan"); // NOI18N
        TtglCatatan.setOpaque(false);
        TtglCatatan.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame21.add(TtglCatatan);
        TtglCatatan.setBounds(180, 38, 90, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("2. Nama Bidan :");
        jLabel64.setName("jLabel64"); // NOI18N
        internalFrame21.add(jLabel64);
        jLabel64.setBounds(0, 66, 175, 23);

        TnmBidan.setEditable(false);
        TnmBidan.setForeground(new java.awt.Color(0, 0, 0));
        TnmBidan.setName("TnmBidan"); // NOI18N
        internalFrame21.add(TnmBidan);
        TnmBidan.setBounds(180, 66, 420, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("3. Tempat Persalinan :");
        jLabel54.setName("jLabel54"); // NOI18N
        internalFrame21.add(jLabel54);
        jLabel54.setBounds(0, 94, 175, 23);

        cmbTmptPersalinan.setForeground(new java.awt.Color(0, 0, 0));
        cmbTmptPersalinan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Rumah Ibu", "Polindes", "Klinik Swasta", "Puskesmas", "Rumah Sakit", "Lainnya" }));
        cmbTmptPersalinan.setName("cmbTmptPersalinan"); // NOI18N
        cmbTmptPersalinan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTmptPersalinanActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbTmptPersalinan);
        cmbTmptPersalinan.setBounds(180, 94, 95, 23);

        TtmptPersalinanLain.setForeground(new java.awt.Color(0, 0, 0));
        TtmptPersalinanLain.setName("TtmptPersalinanLain"); // NOI18N
        TtmptPersalinanLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtmptPersalinanLainKeyPressed(evt);
            }
        });
        internalFrame21.add(TtmptPersalinanLain);
        TtmptPersalinanLain.setBounds(280, 94, 320, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("4. Alamat Tempat Persalinan :");
        jLabel65.setName("jLabel65"); // NOI18N
        internalFrame21.add(jLabel65);
        jLabel65.setBounds(0, 122, 175, 23);

        TalmtTmpPersalinan.setForeground(new java.awt.Color(0, 0, 0));
        TalmtTmpPersalinan.setName("TalmtTmpPersalinan"); // NOI18N
        TalmtTmpPersalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalmtTmpPersalinanKeyPressed(evt);
            }
        });
        internalFrame21.add(TalmtTmpPersalinan);
        TalmtTmpPersalinan.setBounds(180, 122, 420, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("5. Catatan (Rujuk Kala) :");
        jLabel55.setName("jLabel55"); // NOI18N
        internalFrame21.add(jLabel55);
        jLabel55.setBounds(0, 150, 175, 23);

        cmbCttnRujuk.setForeground(new java.awt.Color(0, 0, 0));
        cmbCttnRujuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kala I", "Kala II", "Kala III", "Kala IV" }));
        cmbCttnRujuk.setName("cmbCttnRujuk"); // NOI18N
        internalFrame21.add(cmbCttnRujuk);
        cmbCttnRujuk.setBounds(180, 150, 65, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("6. Alasan Merujuk :");
        jLabel66.setName("jLabel66"); // NOI18N
        internalFrame21.add(jLabel66);
        jLabel66.setBounds(0, 178, 175, 23);

        TalasanMerujuk.setForeground(new java.awt.Color(0, 0, 0));
        TalasanMerujuk.setName("TalasanMerujuk"); // NOI18N
        TalasanMerujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalasanMerujukKeyPressed(evt);
            }
        });
        internalFrame21.add(TalasanMerujuk);
        TalasanMerujuk.setBounds(180, 178, 420, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("7. Tempat Rujukan :");
        jLabel67.setName("jLabel67"); // NOI18N
        internalFrame21.add(jLabel67);
        jLabel67.setBounds(0, 206, 175, 23);

        TtmptRujukan.setForeground(new java.awt.Color(0, 0, 0));
        TtmptRujukan.setName("TtmptRujukan"); // NOI18N
        TtmptRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtmptRujukanKeyPressed(evt);
            }
        });
        internalFrame21.add(TtmptRujukan);
        TtmptRujukan.setBounds(180, 206, 420, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("8. Pendamping Pada Saat Merujuk :");
        jLabel68.setName("jLabel68"); // NOI18N
        internalFrame21.add(jLabel68);
        jLabel68.setBounds(0, 234, 200, 23);

        chkBidan8.setBackground(new java.awt.Color(255, 255, 250));
        chkBidan8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBidan8.setForeground(new java.awt.Color(0, 0, 0));
        chkBidan8.setText("Bidan");
        chkBidan8.setBorderPainted(true);
        chkBidan8.setBorderPaintedFlat(true);
        chkBidan8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBidan8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBidan8.setName("chkBidan8"); // NOI18N
        chkBidan8.setOpaque(false);
        chkBidan8.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkBidan8);
        chkBidan8.setBounds(210, 234, 60, 23);

        chkTeman8.setBackground(new java.awt.Color(255, 255, 250));
        chkTeman8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTeman8.setForeground(new java.awt.Color(0, 0, 0));
        chkTeman8.setText("Teman");
        chkTeman8.setBorderPainted(true);
        chkTeman8.setBorderPaintedFlat(true);
        chkTeman8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTeman8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTeman8.setName("chkTeman8"); // NOI18N
        chkTeman8.setOpaque(false);
        chkTeman8.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkTeman8);
        chkTeman8.setBounds(280, 234, 70, 23);

        chkKlg8.setBackground(new java.awt.Color(255, 255, 250));
        chkKlg8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKlg8.setForeground(new java.awt.Color(0, 0, 0));
        chkKlg8.setText("Keluarga");
        chkKlg8.setBorderPainted(true);
        chkKlg8.setBorderPaintedFlat(true);
        chkKlg8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKlg8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKlg8.setName("chkKlg8"); // NOI18N
        chkKlg8.setOpaque(false);
        chkKlg8.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkKlg8);
        chkKlg8.setBounds(360, 234, 80, 23);

        chkSuami8.setBackground(new java.awt.Color(255, 255, 250));
        chkSuami8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSuami8.setForeground(new java.awt.Color(0, 0, 0));
        chkSuami8.setText("Suami");
        chkSuami8.setBorderPainted(true);
        chkSuami8.setBorderPaintedFlat(true);
        chkSuami8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSuami8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSuami8.setName("chkSuami8"); // NOI18N
        chkSuami8.setOpaque(false);
        chkSuami8.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkSuami8);
        chkSuami8.setBounds(210, 262, 60, 23);

        chkDukun8.setBackground(new java.awt.Color(255, 255, 250));
        chkDukun8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDukun8.setForeground(new java.awt.Color(0, 0, 0));
        chkDukun8.setText("Dukun");
        chkDukun8.setBorderPainted(true);
        chkDukun8.setBorderPaintedFlat(true);
        chkDukun8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDukun8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDukun8.setName("chkDukun8"); // NOI18N
        chkDukun8.setOpaque(false);
        chkDukun8.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkDukun8);
        chkDukun8.setBounds(280, 262, 70, 23);

        chkTidakAda8.setBackground(new java.awt.Color(255, 255, 250));
        chkTidakAda8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTidakAda8.setForeground(new java.awt.Color(0, 0, 0));
        chkTidakAda8.setText("Tidak Ada");
        chkTidakAda8.setBorderPainted(true);
        chkTidakAda8.setBorderPaintedFlat(true);
        chkTidakAda8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTidakAda8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTidakAda8.setName("chkTidakAda8"); // NOI18N
        chkTidakAda8.setOpaque(false);
        chkTidakAda8.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkTidakAda8);
        chkTidakAda8.setBounds(360, 262, 80, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("9. Masalah Dalam Kehamilan / Persalinan Ini :");
        jLabel69.setName("jLabel69"); // NOI18N
        internalFrame21.add(jLabel69);
        jLabel69.setBounds(0, 290, 240, 23);

        chkGawat9.setBackground(new java.awt.Color(255, 255, 250));
        chkGawat9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGawat9.setForeground(new java.awt.Color(0, 0, 0));
        chkGawat9.setText("Gawat Darurat");
        chkGawat9.setBorderPainted(true);
        chkGawat9.setBorderPaintedFlat(true);
        chkGawat9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGawat9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGawat9.setName("chkGawat9"); // NOI18N
        chkGawat9.setOpaque(false);
        chkGawat9.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkGawat9);
        chkGawat9.setBounds(250, 290, 100, 23);

        chkPerdarahan9.setBackground(new java.awt.Color(255, 255, 250));
        chkPerdarahan9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerdarahan9.setForeground(new java.awt.Color(0, 0, 0));
        chkPerdarahan9.setText("Perdarahan");
        chkPerdarahan9.setBorderPainted(true);
        chkPerdarahan9.setBorderPaintedFlat(true);
        chkPerdarahan9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPerdarahan9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPerdarahan9.setName("chkPerdarahan9"); // NOI18N
        chkPerdarahan9.setOpaque(false);
        chkPerdarahan9.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkPerdarahan9);
        chkPerdarahan9.setBounds(360, 290, 90, 23);

        chkHdk9.setBackground(new java.awt.Color(255, 255, 250));
        chkHdk9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHdk9.setForeground(new java.awt.Color(0, 0, 0));
        chkHdk9.setText("HDK");
        chkHdk9.setBorderPainted(true);
        chkHdk9.setBorderPaintedFlat(true);
        chkHdk9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHdk9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHdk9.setName("chkHdk9"); // NOI18N
        chkHdk9.setOpaque(false);
        chkHdk9.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkHdk9);
        chkHdk9.setBounds(460, 290, 60, 23);

        chkInfeksi9.setBackground(new java.awt.Color(255, 255, 250));
        chkInfeksi9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkInfeksi9.setForeground(new java.awt.Color(0, 0, 0));
        chkInfeksi9.setText("Infeksi");
        chkInfeksi9.setBorderPainted(true);
        chkInfeksi9.setBorderPaintedFlat(true);
        chkInfeksi9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInfeksi9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInfeksi9.setName("chkInfeksi9"); // NOI18N
        chkInfeksi9.setOpaque(false);
        chkInfeksi9.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkInfeksi9);
        chkInfeksi9.setBounds(250, 318, 80, 23);

        chkPeb9.setBackground(new java.awt.Color(255, 255, 250));
        chkPeb9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPeb9.setForeground(new java.awt.Color(0, 0, 0));
        chkPeb9.setText("PEB");
        chkPeb9.setBorderPainted(true);
        chkPeb9.setBorderPaintedFlat(true);
        chkPeb9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPeb9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPeb9.setName("chkPeb9"); // NOI18N
        chkPeb9.setOpaque(false);
        chkPeb9.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkPeb9);
        chkPeb9.setBounds(360, 318, 50, 23);

        chkBidan9.setBackground(new java.awt.Color(255, 255, 250));
        chkBidan9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBidan9.setForeground(new java.awt.Color(0, 0, 0));
        chkBidan9.setText("Bidan PMTCT");
        chkBidan9.setBorderPainted(true);
        chkBidan9.setBorderPaintedFlat(true);
        chkBidan9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBidan9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBidan9.setName("chkBidan9"); // NOI18N
        chkBidan9.setOpaque(false);
        chkBidan9.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkBidan9);
        chkBidan9.setBounds(460, 318, 100, 23);

        chkLainya9.setBackground(new java.awt.Color(255, 255, 250));
        chkLainya9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainya9.setForeground(new java.awt.Color(0, 0, 0));
        chkLainya9.setText("Lainnya :");
        chkLainya9.setBorderPainted(true);
        chkLainya9.setBorderPaintedFlat(true);
        chkLainya9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainya9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainya9.setName("chkLainya9"); // NOI18N
        chkLainya9.setOpaque(false);
        chkLainya9.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainya9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainya9ActionPerformed(evt);
            }
        });
        internalFrame21.add(chkLainya9);
        chkLainya9.setBounds(250, 346, 70, 23);

        TmasalahLain.setForeground(new java.awt.Color(0, 0, 0));
        TmasalahLain.setName("TmasalahLain"); // NOI18N
        TmasalahLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmasalahLainKeyPressed(evt);
            }
        });
        internalFrame21.add(TmasalahLain);
        TmasalahLain.setBounds(320, 346, 280, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("KALA I :");
        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel56.setName("jLabel56"); // NOI18N
        internalFrame21.add(jLabel56);
        jLabel56.setBounds(0, 374, 90, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("10. Partogram Melewati Garis Waspada :");
        jLabel70.setName("jLabel70"); // NOI18N
        internalFrame21.add(jLabel70);
        jLabel70.setBounds(0, 402, 240, 23);

        cmbPartogram.setForeground(new java.awt.Color(0, 0, 0));
        cmbPartogram.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPartogram.setName("cmbPartogram"); // NOI18N
        internalFrame21.add(cmbPartogram);
        cmbPartogram.setBounds(245, 402, 65, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("11. Masalah Lain, Sebutkan :");
        jLabel71.setName("jLabel71"); // NOI18N
        internalFrame21.add(jLabel71);
        jLabel71.setBounds(0, 430, 175, 23);

        Tkala1MasalahLain.setForeground(new java.awt.Color(0, 0, 0));
        Tkala1MasalahLain.setName("Tkala1MasalahLain"); // NOI18N
        Tkala1MasalahLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala1MasalahLainKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala1MasalahLain);
        Tkala1MasalahLain.setBounds(180, 430, 420, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("12. Penatalaksanaan Masalah Tersebut :");
        jLabel72.setName("jLabel72"); // NOI18N
        internalFrame21.add(jLabel72);
        jLabel72.setBounds(0, 458, 240, 23);

        Tkala1Penata.setForeground(new java.awt.Color(0, 0, 0));
        Tkala1Penata.setName("Tkala1Penata"); // NOI18N
        Tkala1Penata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala1PenataKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala1Penata);
        Tkala1Penata.setBounds(245, 458, 355, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("13. Hasilnya :");
        jLabel73.setName("jLabel73"); // NOI18N
        internalFrame21.add(jLabel73);
        jLabel73.setBounds(0, 486, 175, 23);

        Tkala1Hasilnya.setForeground(new java.awt.Color(0, 0, 0));
        Tkala1Hasilnya.setName("Tkala1Hasilnya"); // NOI18N
        Tkala1Hasilnya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala1HasilnyaKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala1Hasilnya);
        Tkala1Hasilnya.setBounds(180, 486, 420, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("KALA II :");
        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel57.setName("jLabel57"); // NOI18N
        internalFrame21.add(jLabel57);
        jLabel57.setBounds(0, 514, 90, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("14. Episiotomi :");
        jLabel74.setName("jLabel74"); // NOI18N
        internalFrame21.add(jLabel74);
        jLabel74.setBounds(0, 542, 175, 23);

        cmbEpisiotomi.setForeground(new java.awt.Color(0, 0, 0));
        cmbEpisiotomi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya, indikasi" }));
        cmbEpisiotomi.setName("cmbEpisiotomi"); // NOI18N
        cmbEpisiotomi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEpisiotomiActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbEpisiotomi);
        cmbEpisiotomi.setBounds(180, 542, 85, 23);

        Tkala2YaIndikasi.setForeground(new java.awt.Color(0, 0, 0));
        Tkala2YaIndikasi.setName("Tkala2YaIndikasi"); // NOI18N
        Tkala2YaIndikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala2YaIndikasiKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala2YaIndikasi);
        Tkala2YaIndikasi.setBounds(270, 542, 330, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("15. Pendamping Pada Saat-saat Persalinan :");
        jLabel75.setName("jLabel75"); // NOI18N
        internalFrame21.add(jLabel75);
        jLabel75.setBounds(0, 570, 240, 23);

        chkKala2Suami.setBackground(new java.awt.Color(255, 255, 250));
        chkKala2Suami.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKala2Suami.setForeground(new java.awt.Color(0, 0, 0));
        chkKala2Suami.setText("Suami");
        chkKala2Suami.setBorderPainted(true);
        chkKala2Suami.setBorderPaintedFlat(true);
        chkKala2Suami.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKala2Suami.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKala2Suami.setName("chkKala2Suami"); // NOI18N
        chkKala2Suami.setOpaque(false);
        chkKala2Suami.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkKala2Suami);
        chkKala2Suami.setBounds(250, 570, 60, 23);

        chkKala2Teman.setBackground(new java.awt.Color(255, 255, 250));
        chkKala2Teman.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKala2Teman.setForeground(new java.awt.Color(0, 0, 0));
        chkKala2Teman.setText("Teman");
        chkKala2Teman.setBorderPainted(true);
        chkKala2Teman.setBorderPaintedFlat(true);
        chkKala2Teman.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKala2Teman.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKala2Teman.setName("chkKala2Teman"); // NOI18N
        chkKala2Teman.setOpaque(false);
        chkKala2Teman.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkKala2Teman);
        chkKala2Teman.setBounds(320, 570, 60, 23);

        chkKala2TidakAda.setBackground(new java.awt.Color(255, 255, 250));
        chkKala2TidakAda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKala2TidakAda.setForeground(new java.awt.Color(0, 0, 0));
        chkKala2TidakAda.setText("Tidak Ada");
        chkKala2TidakAda.setBorderPainted(true);
        chkKala2TidakAda.setBorderPaintedFlat(true);
        chkKala2TidakAda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKala2TidakAda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKala2TidakAda.setName("chkKala2TidakAda"); // NOI18N
        chkKala2TidakAda.setOpaque(false);
        chkKala2TidakAda.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkKala2TidakAda);
        chkKala2TidakAda.setBounds(390, 570, 80, 23);

        chkKala2Klg.setBackground(new java.awt.Color(255, 255, 250));
        chkKala2Klg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKala2Klg.setForeground(new java.awt.Color(0, 0, 0));
        chkKala2Klg.setText("Keluarga");
        chkKala2Klg.setBorderPainted(true);
        chkKala2Klg.setBorderPaintedFlat(true);
        chkKala2Klg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKala2Klg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKala2Klg.setName("chkKala2Klg"); // NOI18N
        chkKala2Klg.setOpaque(false);
        chkKala2Klg.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkKala2Klg);
        chkKala2Klg.setBounds(250, 598, 70, 23);

        chkKala2Dukun.setBackground(new java.awt.Color(255, 255, 250));
        chkKala2Dukun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKala2Dukun.setForeground(new java.awt.Color(0, 0, 0));
        chkKala2Dukun.setText("Dukun");
        chkKala2Dukun.setBorderPainted(true);
        chkKala2Dukun.setBorderPaintedFlat(true);
        chkKala2Dukun.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKala2Dukun.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKala2Dukun.setName("chkKala2Dukun"); // NOI18N
        chkKala2Dukun.setOpaque(false);
        chkKala2Dukun.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame21.add(chkKala2Dukun);
        chkKala2Dukun.setBounds(330, 598, 60, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("16. Gawat Janin :");
        jLabel76.setName("jLabel76"); // NOI18N
        internalFrame21.add(jLabel76);
        jLabel76.setBounds(0, 626, 175, 23);

        cmbGawatJanin.setForeground(new java.awt.Color(0, 0, 0));
        cmbGawatJanin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya, tindakan yang dilakukan" }));
        cmbGawatJanin.setName("cmbGawatJanin"); // NOI18N
        cmbGawatJanin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGawatJaninActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbGawatJanin);
        cmbGawatJanin.setBounds(180, 626, 170, 23);

        Tkala2YaTindakanGawat.setForeground(new java.awt.Color(0, 0, 0));
        Tkala2YaTindakanGawat.setName("Tkala2YaTindakanGawat"); // NOI18N
        Tkala2YaTindakanGawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala2YaTindakanGawatKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala2YaTindakanGawat);
        Tkala2YaTindakanGawat.setBounds(355, 626, 245, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Pemantauan DJJ Setiap 5-10 Menit Selama Kala II. Hasil :");
        jLabel77.setName("jLabel77"); // NOI18N
        internalFrame21.add(jLabel77);
        jLabel77.setBounds(0, 654, 300, 23);

        Tkala2Pemantauan.setForeground(new java.awt.Color(0, 0, 0));
        Tkala2Pemantauan.setName("Tkala2Pemantauan"); // NOI18N
        Tkala2Pemantauan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala2PemantauanKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala2Pemantauan);
        Tkala2Pemantauan.setBounds(305, 654, 295, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("17. Distosia Bahu :");
        jLabel78.setName("jLabel78"); // NOI18N
        internalFrame21.add(jLabel78);
        jLabel78.setBounds(0, 682, 175, 23);

        cmbDistosia.setForeground(new java.awt.Color(0, 0, 0));
        cmbDistosia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya, tindakan yang dilakukan" }));
        cmbDistosia.setName("cmbDistosia"); // NOI18N
        cmbDistosia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDistosiaActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbDistosia);
        cmbDistosia.setBounds(180, 682, 170, 23);

        Tkala2YaTindakanDisto.setForeground(new java.awt.Color(0, 0, 0));
        Tkala2YaTindakanDisto.setName("Tkala2YaTindakanDisto"); // NOI18N
        Tkala2YaTindakanDisto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala2YaTindakanDistoKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala2YaTindakanDisto);
        Tkala2YaTindakanDisto.setBounds(355, 682, 245, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("18. Masalah Lain, Penatalaksanaan Masalah Tersebut dan Hasilnya :");
        jLabel79.setName("jLabel79"); // NOI18N
        internalFrame21.add(jLabel79);
        jLabel79.setBounds(0, 710, 360, 23);

        Tkala2MasalahLain.setForeground(new java.awt.Color(0, 0, 0));
        Tkala2MasalahLain.setName("Tkala2MasalahLain"); // NOI18N
        Tkala2MasalahLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala2MasalahLainKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala2MasalahLain);
        Tkala2MasalahLain.setBounds(50, 738, 550, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("KALA III :");
        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel58.setName("jLabel58"); // NOI18N
        internalFrame21.add(jLabel58);
        jLabel58.setBounds(0, 766, 90, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("19. Inisiasi Menyusui Dini :");
        jLabel80.setName("jLabel80"); // NOI18N
        internalFrame21.add(jLabel80);
        jLabel80.setBounds(0, 794, 175, 23);

        cmbInisiasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbInisiasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak, alasan" }));
        cmbInisiasi.setName("cmbInisiasi"); // NOI18N
        cmbInisiasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbInisiasiActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbInisiasi);
        cmbInisiasi.setBounds(180, 794, 95, 23);

        Tkala3Tidak.setForeground(new java.awt.Color(0, 0, 0));
        Tkala3Tidak.setName("Tkala3Tidak"); // NOI18N
        Tkala3Tidak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala3TidakKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala3Tidak);
        Tkala3Tidak.setBounds(280, 794, 320, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("20. Lama Kala III :");
        jLabel81.setName("jLabel81"); // NOI18N
        internalFrame21.add(jLabel81);
        jLabel81.setBounds(0, 822, 175, 23);

        Tkala3Lama.setForeground(new java.awt.Color(0, 0, 0));
        Tkala3Lama.setName("Tkala3Lama"); // NOI18N
        Tkala3Lama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala3LamaKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala3Lama);
        Tkala3Lama.setBounds(180, 822, 60, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("Menit");
        jLabel82.setName("jLabel82"); // NOI18N
        internalFrame21.add(jLabel82);
        jLabel82.setBounds(245, 822, 40, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("21. Pemberian Oksitosin 10 U im ?");
        jLabel83.setName("jLabel83"); // NOI18N
        internalFrame21.add(jLabel83);
        jLabel83.setBounds(0, 850, 240, 23);

        cmbPemberianOksi.setForeground(new java.awt.Color(0, 0, 0));
        cmbPemberianOksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya, waktu", "Tidak, alasan", "Penjepitan tali pusat" }));
        cmbPemberianOksi.setName("cmbPemberianOksi"); // NOI18N
        cmbPemberianOksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPemberianOksiActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbPemberianOksi);
        cmbPemberianOksi.setBounds(245, 850, 130, 23);

        Tkala3PemberianOksi.setForeground(new java.awt.Color(0, 0, 0));
        Tkala3PemberianOksi.setName("Tkala3PemberianOksi"); // NOI18N
        Tkala3PemberianOksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala3PemberianOksiKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala3PemberianOksi);
        Tkala3PemberianOksi.setBounds(50, 878, 325, 23);

        labelKetPemberian.setForeground(new java.awt.Color(0, 0, 0));
        labelKetPemberian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKetPemberian.setName("labelKetPemberian"); // NOI18N
        internalFrame21.add(labelKetPemberian);
        labelKetPemberian.setBounds(380, 878, 220, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("22. Pemberian Ulang Oksitosin (2X) ?");
        jLabel84.setName("jLabel84"); // NOI18N
        internalFrame21.add(jLabel84);
        jLabel84.setBounds(0, 906, 240, 23);

        cmbPemberianUlang.setForeground(new java.awt.Color(0, 0, 0));
        cmbPemberianUlang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya, alasan", "Tidak" }));
        cmbPemberianUlang.setName("cmbPemberianUlang"); // NOI18N
        cmbPemberianUlang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPemberianUlangActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbPemberianUlang);
        cmbPemberianUlang.setBounds(245, 906, 85, 23);

        Tkala3PemberianUlang.setForeground(new java.awt.Color(0, 0, 0));
        Tkala3PemberianUlang.setName("Tkala3PemberianUlang"); // NOI18N
        Tkala3PemberianUlang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala3PemberianUlangKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala3PemberianUlang);
        Tkala3PemberianUlang.setBounds(335, 906, 265, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("23. Penegangan Tali Pusat terkendali :");
        jLabel85.setName("jLabel85"); // NOI18N
        internalFrame21.add(jLabel85);
        jLabel85.setBounds(600, 38, 240, 23);

        cmbPenegangan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenegangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak, alasan" }));
        cmbPenegangan.setName("cmbPenegangan"); // NOI18N
        cmbPenegangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPeneganganActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbPenegangan);
        cmbPenegangan.setBounds(845, 38, 95, 23);

        Tkala3Penegangan.setForeground(new java.awt.Color(0, 0, 0));
        Tkala3Penegangan.setName("Tkala3Penegangan"); // NOI18N
        Tkala3Penegangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala3PeneganganKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala3Penegangan);
        Tkala3Penegangan.setBounds(945, 38, 265, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("24. Masase Fundus Uteri :");
        jLabel86.setName("jLabel86"); // NOI18N
        internalFrame21.add(jLabel86);
        jLabel86.setBounds(635, 66, 140, 23);

        cmbMasase.setForeground(new java.awt.Color(0, 0, 0));
        cmbMasase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak, alasan" }));
        cmbMasase.setName("cmbMasase"); // NOI18N
        cmbMasase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMasaseActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbMasase);
        cmbMasase.setBounds(782, 66, 95, 23);

        Tkala3Masase.setForeground(new java.awt.Color(0, 0, 0));
        Tkala3Masase.setName("Tkala3Masase"); // NOI18N
        Tkala3Masase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala3MasaseKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala3Masase);
        Tkala3Masase.setBounds(885, 66, 325, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("25. Plasenta Lahir Lengkap (intact) :");
        jLabel87.setName("jLabel87"); // NOI18N
        internalFrame21.add(jLabel87);
        jLabel87.setBounds(600, 94, 240, 23);

        cmbPlasenta25.setForeground(new java.awt.Color(0, 0, 0));
        cmbPlasenta25.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPlasenta25.setName("cmbPlasenta25"); // NOI18N
        cmbPlasenta25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPlasenta25ActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbPlasenta25);
        cmbPlasenta25.setBounds(845, 94, 60, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel88.setText("Jika tidak lengkap, tindakan yang dilakukan :");
        jLabel88.setName("jLabel88"); // NOI18N
        internalFrame21.add(jLabel88);
        jLabel88.setBounds(910, 94, 240, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("a. ");
        jLabel89.setName("jLabel89"); // NOI18N
        internalFrame21.add(jLabel89);
        jLabel89.setBounds(600, 122, 175, 23);

        Tkala3Plasenta25A.setForeground(new java.awt.Color(0, 0, 0));
        Tkala3Plasenta25A.setName("Tkala3Plasenta25A"); // NOI18N
        Tkala3Plasenta25A.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala3Plasenta25AKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala3Plasenta25A);
        Tkala3Plasenta25A.setBounds(780, 122, 430, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("b. ");
        jLabel90.setName("jLabel90"); // NOI18N
        internalFrame21.add(jLabel90);
        jLabel90.setBounds(600, 150, 175, 23);

        Tkala3Plasenta25B.setForeground(new java.awt.Color(0, 0, 0));
        Tkala3Plasenta25B.setName("Tkala3Plasenta25B"); // NOI18N
        Tkala3Plasenta25B.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala3Plasenta25BKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala3Plasenta25B);
        Tkala3Plasenta25B.setBounds(780, 150, 430, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("26. Plasenta Tidak Lahir > 30 Menit :");
        jLabel91.setName("jLabel91"); // NOI18N
        internalFrame21.add(jLabel91);
        jLabel91.setBounds(600, 178, 240, 23);

        cmbPlasenta26.setForeground(new java.awt.Color(0, 0, 0));
        cmbPlasenta26.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya, tindakan" }));
        cmbPlasenta26.setName("cmbPlasenta26"); // NOI18N
        cmbPlasenta26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPlasenta26ActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbPlasenta26);
        cmbPlasenta26.setBounds(845, 178, 95, 23);

        Tkala3Plasenta26.setForeground(new java.awt.Color(0, 0, 0));
        Tkala3Plasenta26.setName("Tkala3Plasenta26"); // NOI18N
        Tkala3Plasenta26.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala3Plasenta26KeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala3Plasenta26);
        Tkala3Plasenta26.setBounds(945, 178, 265, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("27. Laserasi :");
        jLabel92.setName("jLabel92"); // NOI18N
        internalFrame21.add(jLabel92);
        jLabel92.setBounds(600, 206, 175, 23);

        cmbLaserasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbLaserasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya, dimana", "Tidak" }));
        cmbLaserasi.setName("cmbLaserasi"); // NOI18N
        cmbLaserasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLaserasiActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbLaserasi);
        cmbLaserasi.setBounds(782, 206, 85, 23);

        Tkala3Laserasi.setForeground(new java.awt.Color(0, 0, 0));
        Tkala3Laserasi.setName("Tkala3Laserasi"); // NOI18N
        Tkala3Laserasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala3LaserasiKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala3Laserasi);
        Tkala3Laserasi.setBounds(872, 206, 338, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("28. Jika Laserasi Perineum, Derajat :");
        jLabel93.setName("jLabel93"); // NOI18N
        internalFrame21.add(jLabel93);
        jLabel93.setBounds(600, 234, 240, 23);

        cmbJika.setForeground(new java.awt.Color(0, 0, 0));
        cmbJika.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "4" }));
        cmbJika.setName("cmbJika"); // NOI18N
        internalFrame21.add(cmbJika);
        cmbJika.setBounds(845, 234, 40, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Tindakan Penjahitan :");
        jLabel94.setName("jLabel94"); // NOI18N
        internalFrame21.add(jLabel94);
        jLabel94.setBounds(885, 234, 130, 23);

        cmbTindakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbTindakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Dengan anestesi", "Tanpa anestesi", "Tidak dijahit, alasan" }));
        cmbTindakan.setName("cmbTindakan"); // NOI18N
        cmbTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTindakanActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbTindakan);
        cmbTindakan.setBounds(1024, 234, 130, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Alasan :");
        jLabel95.setName("jLabel95"); // NOI18N
        internalFrame21.add(jLabel95);
        jLabel95.setBounds(600, 262, 175, 23);

        Tkala3Alasan.setForeground(new java.awt.Color(0, 0, 0));
        Tkala3Alasan.setName("Tkala3Alasan"); // NOI18N
        Tkala3Alasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala3AlasanKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala3Alasan);
        Tkala3Alasan.setBounds(780, 262, 430, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("29. Atonia Uteri :");
        jLabel96.setName("jLabel96"); // NOI18N
        internalFrame21.add(jLabel96);
        jLabel96.setBounds(600, 290, 175, 23);

        cmbAtonia.setForeground(new java.awt.Color(0, 0, 0));
        cmbAtonia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya, tindakan" }));
        cmbAtonia.setName("cmbAtonia"); // NOI18N
        cmbAtonia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAtoniaActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbAtonia);
        cmbAtonia.setBounds(782, 290, 95, 23);

        Tkala3Atonia.setForeground(new java.awt.Color(0, 0, 0));
        Tkala3Atonia.setName("Tkala3Atonia"); // NOI18N
        Tkala3Atonia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala3AtoniaKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala3Atonia);
        Tkala3Atonia.setBounds(882, 290, 328, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("30. Jumlah Darah Yang Keluar/Perdarahan :");
        jLabel97.setName("jLabel97"); // NOI18N
        internalFrame21.add(jLabel97);
        jLabel97.setBounds(600, 318, 240, 23);

        Tkala3Jumlah.setForeground(new java.awt.Color(0, 0, 0));
        Tkala3Jumlah.setName("Tkala3Jumlah"); // NOI18N
        Tkala3Jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala3JumlahKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala3Jumlah);
        Tkala3Jumlah.setBounds(845, 318, 60, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel98.setText("ml.");
        jLabel98.setName("jLabel98"); // NOI18N
        internalFrame21.add(jLabel98);
        jLabel98.setBounds(910, 318, 40, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("31. Masalah :");
        jLabel99.setName("jLabel99"); // NOI18N
        internalFrame21.add(jLabel99);
        jLabel99.setBounds(600, 346, 175, 23);

        Tkala3Masalah.setForeground(new java.awt.Color(0, 0, 0));
        Tkala3Masalah.setName("Tkala3Masalah"); // NOI18N
        Tkala3Masalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala3MasalahKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala3Masalah);
        Tkala3Masalah.setBounds(780, 346, 430, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("32. Penatalaksanaan Masalah Tersebut :");
        jLabel100.setName("jLabel100"); // NOI18N
        internalFrame21.add(jLabel100);
        jLabel100.setBounds(600, 374, 240, 23);

        Tkala3Penata.setForeground(new java.awt.Color(0, 0, 0));
        Tkala3Penata.setName("Tkala3Penata"); // NOI18N
        Tkala3Penata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala3PenataKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala3Penata);
        Tkala3Penata.setBounds(845, 374, 365, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("33. Hasilnya :");
        jLabel101.setName("jLabel101"); // NOI18N
        internalFrame21.add(jLabel101);
        jLabel101.setBounds(600, 402, 175, 23);

        Tkala3Hasilnya.setForeground(new java.awt.Color(0, 0, 0));
        Tkala3Hasilnya.setName("Tkala3Hasilnya"); // NOI18N
        Tkala3Hasilnya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkala3HasilnyaKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkala3Hasilnya);
        Tkala3Hasilnya.setBounds(780, 402, 430, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("BAYI BARU LAHIR :");
        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel59.setName("jLabel59"); // NOI18N
        internalFrame21.add(jLabel59);
        jLabel59.setBounds(600, 430, 150, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("34. Berat Badan :");
        jLabel102.setName("jLabel102"); // NOI18N
        internalFrame21.add(jLabel102);
        jLabel102.setBounds(600, 458, 175, 23);

        TbayiBB.setForeground(new java.awt.Color(0, 0, 0));
        TbayiBB.setName("TbayiBB"); // NOI18N
        TbayiBB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbayiBBKeyPressed(evt);
            }
        });
        internalFrame21.add(TbayiBB);
        TbayiBB.setBounds(780, 458, 70, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel103.setText("gram");
        jLabel103.setName("jLabel103"); // NOI18N
        internalFrame21.add(jLabel103);
        jLabel103.setBounds(857, 458, 40, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("35. Panjang Badan :");
        jLabel104.setName("jLabel104"); // NOI18N
        internalFrame21.add(jLabel104);
        jLabel104.setBounds(600, 486, 175, 23);

        TbayiPB.setForeground(new java.awt.Color(0, 0, 0));
        TbayiPB.setName("TbayiPB"); // NOI18N
        TbayiPB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbayiPBKeyPressed(evt);
            }
        });
        internalFrame21.add(TbayiPB);
        TbayiPB.setBounds(780, 486, 70, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("cm");
        jLabel105.setName("jLabel105"); // NOI18N
        internalFrame21.add(jLabel105);
        jLabel105.setBounds(857, 486, 40, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("36. Jenis Kelamin :");
        jLabel106.setName("jLabel106"); // NOI18N
        internalFrame21.add(jLabel106);
        jLabel106.setBounds(600, 514, 175, 23);

        cmbJenkel.setForeground(new java.awt.Color(0, 0, 0));
        cmbJenkel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Laki-laki", "Perempuan" }));
        cmbJenkel.setName("cmbJenkel"); // NOI18N
        internalFrame21.add(cmbJenkel);
        cmbJenkel.setBounds(782, 514, 90, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("37. Penilaian Bayi Baru Lahir :");
        jLabel107.setName("jLabel107"); // NOI18N
        internalFrame21.add(jLabel107);
        jLabel107.setBounds(600, 542, 175, 23);

        cmbPenilaian.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenilaian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Baik", "Ada penyulit" }));
        cmbPenilaian.setName("cmbPenilaian"); // NOI18N
        internalFrame21.add(cmbPenilaian);
        cmbPenilaian.setBounds(782, 542, 95, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("38. Bayi Lahir :");
        jLabel108.setName("jLabel108"); // NOI18N
        internalFrame21.add(jLabel108);
        jLabel108.setBounds(600, 570, 175, 23);

        cmbBayiLahir.setForeground(new java.awt.Color(0, 0, 0));
        cmbBayiLahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal, tindakan", "Asfiksia ringan", "Asfiksia pucat", "Asfiksia biru", "Asfiksia lemas", "Cacat bawaan, sebutkan", "Hipotermi, tindakan" }));
        cmbBayiLahir.setName("cmbBayiLahir"); // NOI18N
        cmbBayiLahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBayiLahirActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbBayiLahir);
        cmbBayiLahir.setBounds(782, 570, 155, 23);

        cmbBayiNormal.setForeground(new java.awt.Color(0, 0, 0));
        cmbBayiNormal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mengeringkan", "Menghangatkan", "Rangsang taktil", "Pakaian/selimut bayi dan tempatkan disisi ibu" }));
        cmbBayiNormal.setName("cmbBayiNormal"); // NOI18N
        internalFrame21.add(cmbBayiNormal);
        cmbBayiNormal.setBounds(945, 570, 250, 23);

        cmbBayiAsfeksia.setForeground(new java.awt.Color(0, 0, 0));
        cmbBayiAsfeksia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mengeringkan", "Rangsang taktil", "Pakaian/selimut bayi dan tempatkan disisi ibu", "Bebaskan jalan nafas", "Menghangatkan", "Lain-lain, sebutkan" }));
        cmbBayiAsfeksia.setName("cmbBayiAsfeksia"); // NOI18N
        cmbBayiAsfeksia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBayiAsfeksiaActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbBayiAsfeksia);
        cmbBayiAsfeksia.setBounds(945, 598, 250, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("Sebutkan :");
        jLabel109.setName("jLabel109"); // NOI18N
        internalFrame21.add(jLabel109);
        jLabel109.setBounds(600, 626, 175, 23);

        TbayiSebutkan.setForeground(new java.awt.Color(0, 0, 0));
        TbayiSebutkan.setName("TbayiSebutkan"); // NOI18N
        internalFrame21.add(TbayiSebutkan);
        TbayiSebutkan.setBounds(780, 626, 430, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("Hipotermia, Tindakan A. :");
        jLabel110.setName("jLabel110"); // NOI18N
        internalFrame21.add(jLabel110);
        jLabel110.setBounds(600, 654, 175, 23);

        TbayiTindakanA.setForeground(new java.awt.Color(0, 0, 0));
        TbayiTindakanA.setName("TbayiTindakanA"); // NOI18N
        TbayiTindakanA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbayiTindakanAKeyPressed(evt);
            }
        });
        internalFrame21.add(TbayiTindakanA);
        TbayiTindakanA.setBounds(780, 654, 430, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Tindakan B. :");
        jLabel111.setName("jLabel111"); // NOI18N
        internalFrame21.add(jLabel111);
        jLabel111.setBounds(600, 682, 175, 23);

        TbayiTindakanB.setForeground(new java.awt.Color(0, 0, 0));
        TbayiTindakanB.setName("TbayiTindakanB"); // NOI18N
        TbayiTindakanB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbayiTindakanBKeyPressed(evt);
            }
        });
        internalFrame21.add(TbayiTindakanB);
        TbayiTindakanB.setBounds(780, 682, 430, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("Tindakan C. :");
        jLabel112.setName("jLabel112"); // NOI18N
        internalFrame21.add(jLabel112);
        jLabel112.setBounds(600, 710, 175, 23);

        TbayiTindakanC.setForeground(new java.awt.Color(0, 0, 0));
        TbayiTindakanC.setName("TbayiTindakanC"); // NOI18N
        TbayiTindakanC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbayiTindakanCKeyPressed(evt);
            }
        });
        internalFrame21.add(TbayiTindakanC);
        TbayiTindakanC.setBounds(780, 710, 430, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("39. Pemberian ASI :");
        jLabel113.setName("jLabel113"); // NOI18N
        internalFrame21.add(jLabel113);
        jLabel113.setBounds(600, 738, 175, 23);

        cmbPemberianAsi.setForeground(new java.awt.Color(0, 0, 0));
        cmbPemberianAsi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya, waktu", "Tidak, alasan" }));
        cmbPemberianAsi.setName("cmbPemberianAsi"); // NOI18N
        cmbPemberianAsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPemberianAsiActionPerformed(evt);
            }
        });
        internalFrame21.add(cmbPemberianAsi);
        cmbPemberianAsi.setBounds(782, 738, 95, 23);

        TbayiYaPemberian.setForeground(new java.awt.Color(0, 0, 0));
        TbayiYaPemberian.setName("TbayiYaPemberian"); // NOI18N
        TbayiYaPemberian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbayiYaPemberianKeyPressed(evt);
            }
        });
        internalFrame21.add(TbayiYaPemberian);
        TbayiYaPemberian.setBounds(882, 738, 80, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel114.setText("Jam Setelah Bayi Lahir");
        jLabel114.setName("jLabel114"); // NOI18N
        internalFrame21.add(jLabel114);
        jLabel114.setBounds(968, 738, 140, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("Alasan :");
        jLabel115.setName("jLabel115"); // NOI18N
        internalFrame21.add(jLabel115);
        jLabel115.setBounds(600, 766, 175, 23);

        TbayiTidakAlasan.setForeground(new java.awt.Color(0, 0, 0));
        TbayiTidakAlasan.setName("TbayiTidakAlasan"); // NOI18N
        TbayiTidakAlasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbayiTidakAlasanKeyPressed(evt);
            }
        });
        internalFrame21.add(TbayiTidakAlasan);
        TbayiTidakAlasan.setBounds(780, 766, 430, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("40. Masalah Lain, Sebutkan :");
        jLabel116.setName("jLabel116"); // NOI18N
        internalFrame21.add(jLabel116);
        jLabel116.setBounds(600, 794, 175, 23);

        TbayiMasalah.setForeground(new java.awt.Color(0, 0, 0));
        TbayiMasalah.setName("TbayiMasalah"); // NOI18N
        TbayiMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbayiMasalahKeyPressed(evt);
            }
        });
        internalFrame21.add(TbayiMasalah);
        TbayiMasalah.setBounds(780, 794, 430, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("Hasilnya :");
        jLabel117.setName("jLabel117"); // NOI18N
        internalFrame21.add(jLabel117);
        jLabel117.setBounds(600, 822, 175, 23);

        TbayiHasilnya.setForeground(new java.awt.Color(0, 0, 0));
        TbayiHasilnya.setName("TbayiHasilnya"); // NOI18N
        TbayiHasilnya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbayiHasilnyaKeyPressed(evt);
            }
        });
        internalFrame21.add(TbayiHasilnya);
        TbayiHasilnya.setBounds(780, 822, 430, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("PEMANTAUAN PERSALINAN KALA IV :");
        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel60.setName("jLabel60"); // NOI18N
        internalFrame21.add(jLabel60);
        jLabel60.setBounds(0, 934, 240, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("Jam Ke :");
        jLabel119.setName("jLabel119"); // NOI18N
        internalFrame21.add(jLabel119);
        jLabel119.setBounds(0, 962, 175, 23);

        TjamKe.setForeground(new java.awt.Color(0, 0, 0));
        TjamKe.setName("TjamKe"); // NOI18N
        TjamKe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjamKeKeyPressed(evt);
            }
        });
        internalFrame21.add(TjamKe);
        TjamKe.setBounds(180, 962, 70, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("Waktu :");
        jLabel120.setName("jLabel120"); // NOI18N
        internalFrame21.add(jLabel120);
        jLabel120.setBounds(250, 962, 60, 23);

        cmbJam11.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam11.setName("cmbJam11"); // NOI18N
        cmbJam11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam11MouseReleased(evt);
            }
        });
        internalFrame21.add(cmbJam11);
        cmbJam11.setBounds(315, 962, 45, 23);

        cmbMnt11.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt11.setName("cmbMnt11"); // NOI18N
        cmbMnt11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt11MouseReleased(evt);
            }
        });
        internalFrame21.add(cmbMnt11);
        cmbMnt11.setBounds(367, 962, 45, 23);

        cmbDtk11.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk11.setName("cmbDtk11"); // NOI18N
        cmbDtk11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk11MouseReleased(evt);
            }
        });
        internalFrame21.add(cmbDtk11);
        cmbDtk11.setBounds(418, 962, 45, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("Tekanan Darah :");
        jLabel121.setName("jLabel121"); // NOI18N
        internalFrame21.add(jLabel121);
        jLabel121.setBounds(465, 962, 100, 23);

        TtdKala4.setForeground(new java.awt.Color(0, 0, 0));
        TtdKala4.setName("TtdKala4"); // NOI18N
        TtdKala4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdKala4KeyPressed(evt);
            }
        });
        internalFrame21.add(TtdKala4);
        TtdKala4.setBounds(570, 962, 70, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("mmHg       Nadi :");
        jLabel61.setName("jLabel61"); // NOI18N
        internalFrame21.add(jLabel61);
        jLabel61.setBounds(645, 962, 80, 23);

        TnadiKala4.setForeground(new java.awt.Color(0, 0, 0));
        TnadiKala4.setName("TnadiKala4"); // NOI18N
        TnadiKala4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKala4KeyPressed(evt);
            }
        });
        internalFrame21.add(TnadiKala4);
        TnadiKala4.setBounds(728, 962, 70, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("x/mnt      Suhu : ");
        jLabel62.setName("jLabel62"); // NOI18N
        internalFrame21.add(jLabel62);
        jLabel62.setBounds(805, 962, 80, 23);

        TsuhuKala4.setForeground(new java.awt.Color(0, 0, 0));
        TsuhuKala4.setName("TsuhuKala4"); // NOI18N
        TsuhuKala4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKala4KeyPressed(evt);
            }
        });
        internalFrame21.add(TsuhuKala4);
        TsuhuKala4.setBounds(890, 962, 70, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel122.setText("°C");
        jLabel122.setName("jLabel122"); // NOI18N
        internalFrame21.add(jLabel122);
        jLabel122.setBounds(965, 962, 30, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setText("Tinggi Fundus Uteri :");
        jLabel123.setName("jLabel123"); // NOI18N
        internalFrame21.add(jLabel123);
        jLabel123.setBounds(0, 990, 175, 23);

        TTinggiFundus.setForeground(new java.awt.Color(0, 0, 0));
        TTinggiFundus.setName("TTinggiFundus"); // NOI18N
        TTinggiFundus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTinggiFundusKeyPressed(evt);
            }
        });
        internalFrame21.add(TTinggiFundus);
        TTinggiFundus.setBounds(180, 990, 175, 23);

        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setText("Kontraksi Uterus :");
        jLabel124.setName("jLabel124"); // NOI18N
        internalFrame21.add(jLabel124);
        jLabel124.setBounds(360, 990, 100, 23);

        Tkontraksi.setForeground(new java.awt.Color(0, 0, 0));
        Tkontraksi.setName("Tkontraksi"); // NOI18N
        Tkontraksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkontraksiKeyPressed(evt);
            }
        });
        internalFrame21.add(Tkontraksi);
        Tkontraksi.setBounds(465, 990, 170, 23);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setText("Urin Output :");
        jLabel125.setName("jLabel125"); // NOI18N
        internalFrame21.add(jLabel125);
        jLabel125.setBounds(640, 990, 80, 23);

        Turin.setForeground(new java.awt.Color(0, 0, 0));
        Turin.setName("Turin"); // NOI18N
        Turin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TurinKeyPressed(evt);
            }
        });
        internalFrame21.add(Turin);
        Turin.setBounds(725, 990, 170, 23);

        jLabel126.setForeground(new java.awt.Color(0, 0, 0));
        jLabel126.setText("Darah Yang Keluar :");
        jLabel126.setName("jLabel126"); // NOI18N
        internalFrame21.add(jLabel126);
        jLabel126.setBounds(900, 990, 110, 23);

        TdarahYang.setForeground(new java.awt.Color(0, 0, 0));
        TdarahYang.setName("TdarahYang"); // NOI18N
        TdarahYang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdarahYangKeyPressed(evt);
            }
        });
        internalFrame21.add(TdarahYang);
        TdarahYang.setBounds(1015, 990, 170, 23);

        Scroll12.setName("Scroll12"); // NOI18N
        Scroll12.setOpaque(true);

        tbPemantauan.setAutoCreateRowSorter(true);
        tbPemantauan.setName("tbPemantauan"); // NOI18N
        tbPemantauan.getTableHeader().setReorderingAllowed(false);
        tbPemantauan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemantauanMouseClicked(evt);
            }
        });
        tbPemantauan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemantauanKeyPressed(evt);
            }
        });
        Scroll12.setViewportView(tbPemantauan);

        internalFrame21.add(Scroll12);
        Scroll12.setBounds(71, 1025, 970, 160);

        BtnBaruKala4.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaruKala4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaruKala4.setText("Baru");
        BtnBaruKala4.setToolTipText("Data Pemantauan Persalinan Kala 4 Baru");
        BtnBaruKala4.setName("BtnBaruKala4"); // NOI18N
        BtnBaruKala4.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnBaruKala4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruKala4ActionPerformed(evt);
            }
        });
        internalFrame21.add(BtnBaruKala4);
        BtnBaruKala4.setBounds(1050, 1025, 90, 30);

        BtnTambahKala4.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahKala4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahKala4.setText("Tambah");
        BtnTambahKala4.setToolTipText("Tambah Data Pemantauan Persalinan Kala 4");
        BtnTambahKala4.setName("BtnTambahKala4"); // NOI18N
        BtnTambahKala4.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahKala4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahKala4ActionPerformed(evt);
            }
        });
        internalFrame21.add(BtnTambahKala4);
        BtnTambahKala4.setBounds(1050, 1064, 90, 30);

        BtnHapusKala4.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusKala4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusKala4.setText("Hapus");
        BtnHapusKala4.setToolTipText("Hapus Pemantauan Persalinan Kala 4");
        BtnHapusKala4.setName("BtnHapusKala4"); // NOI18N
        BtnHapusKala4.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusKala4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusKala4ActionPerformed(evt);
            }
        });
        internalFrame21.add(BtnHapusKala4);
        BtnHapusKala4.setBounds(1050, 1103, 90, 30);

        BtnGantiKala4.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiKala4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiKala4.setText("Ganti");
        BtnGantiKala4.setToolTipText("Ganti Pemantauan Persalinan Kala 4");
        BtnGantiKala4.setName("BtnGantiKala4"); // NOI18N
        BtnGantiKala4.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiKala4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiKala4ActionPerformed(evt);
            }
        });
        internalFrame21.add(BtnGantiKala4);
        BtnGantiKala4.setBounds(1050, 1142, 90, 30);

        jLabel127.setForeground(new java.awt.Color(0, 0, 0));
        jLabel127.setText("Masalah Kala 4 :");
        jLabel127.setName("jLabel127"); // NOI18N
        internalFrame21.add(jLabel127);
        jLabel127.setBounds(0, 1195, 175, 23);

        TmasalahKala4.setForeground(new java.awt.Color(0, 0, 0));
        TmasalahKala4.setName("TmasalahKala4"); // NOI18N
        TmasalahKala4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmasalahKala4KeyPressed(evt);
            }
        });
        internalFrame21.add(TmasalahKala4);
        TmasalahKala4.setBounds(180, 1195, 860, 23);

        jLabel128.setForeground(new java.awt.Color(0, 0, 0));
        jLabel128.setText("Penatalaksanaan Masalah Tersebut :");
        jLabel128.setName("jLabel128"); // NOI18N
        internalFrame21.add(jLabel128);
        jLabel128.setBounds(0, 1223, 220, 23);

        TpenataKala4.setForeground(new java.awt.Color(0, 0, 0));
        TpenataKala4.setName("TpenataKala4"); // NOI18N
        TpenataKala4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpenataKala4KeyPressed(evt);
            }
        });
        internalFrame21.add(TpenataKala4);
        TpenataKala4.setBounds(225, 1223, 815, 23);

        jLabel129.setForeground(new java.awt.Color(0, 0, 0));
        jLabel129.setText("Hasilnya :");
        jLabel129.setName("jLabel129"); // NOI18N
        internalFrame21.add(jLabel129);
        jLabel129.setBounds(0, 1251, 175, 23);

        ThasilKala4.setForeground(new java.awt.Color(0, 0, 0));
        ThasilKala4.setName("ThasilKala4"); // NOI18N
        internalFrame21.add(ThasilKala4);
        ThasilKala4.setBounds(180, 1251, 860, 23);

        BtnBidan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnBidan.setToolTipText("Alt+2");
        BtnBidan.setName("BtnBidan"); // NOI18N
        BtnBidan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnBidan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBidanActionPerformed(evt);
            }
        });
        internalFrame21.add(BtnBidan);
        BtnBidan.setBounds(600, 66, 28, 23);

        Scroll11.setViewportView(internalFrame21);

        panelGlass9.add(Scroll11, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        BtnSimpan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan1KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnSimpan1);

        BtnBatal1.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal1.setText("Baru");
        BtnBatal1.setToolTipText("Alt+B");
        BtnBatal1.setName("BtnBatal1"); // NOI18N
        BtnBatal1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatal1ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnBatal1);

        BtnGanti1.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti1.setText("Ganti");
        BtnGanti1.setToolTipText("Alt+G");
        BtnGanti1.setName("BtnGanti1"); // NOI18N
        BtnGanti1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGanti1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGanti1ActionPerformed(evt);
            }
        });
        BtnGanti1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGanti1KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnGanti1);

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapus1.setText("Hapus");
        BtnHapus1.setToolTipText("Hapus Air Ketuban/Mulase");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnHapus1);

        BtnCetak1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetak1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetak1.setText("Cetak");
        BtnCetak1.setToolTipText("Hapus Air Ketuban/Mulase");
        BtnCetak1.setName("BtnCetak1"); // NOI18N
        BtnCetak1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCetak1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetak1ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnCetak1);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnKeluar1);

        panelGlass9.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        TabPartograf.addTab("Catatan Persalinan", panelGlass9);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPartograf.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbPartograf.setName("tbPartograf"); // NOI18N
        tbPartograf.getTableHeader().setReorderingAllowed(false);
        tbPartograf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPartografMouseClicked(evt);
            }
        });
        tbPartograf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPartografKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPartograf);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 55));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("Tgl. Partograf :");
        jLabel118.setName("jLabel118"); // NOI18N
        jLabel118.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass10.add(jLabel118);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-05-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel130.setForeground(new java.awt.Color(0, 0, 0));
        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel130.setText("s.d.");
        jLabel130.setName("jLabel130"); // NOI18N
        jLabel130.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel130);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-05-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setText("Tampilkan Data");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 30));
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
        panelGlass10.add(BtnCari);

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
        panelGlass10.add(BtnAll);

        BtnCetak2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetak2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetak2.setText("Cetak");
        BtnCetak2.setToolTipText("Hapus Air Ketuban/Mulase");
        BtnCetak2.setName("BtnCetak2"); // NOI18N
        BtnCetak2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCetak2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetak2ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnCetak2);

        BtnKeluar2.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar2.setText("Keluar");
        BtnKeluar2.setToolTipText("Alt+K");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar2ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnKeluar2);

        internalFrame2.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        TabPartograf.addTab("Data Partograf", internalFrame2);

        internalFrame1.add(TabPartograf, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Ppasien");
        } else if (nipBidan.equals("")) {
            Valid.textKosong(TnmBidan, "nama bidan");
            BtnBidan.requestFocus();
        } else {
            if (cmbKetuban.getSelectedIndex() == 1) {
                jamKetuban = cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem();
            } else {
                jamKetuban = "00:00:00";
            }

            cekData();
            if (Sequel.menyimpantf("partograf_persalinan", "?,?,?,?,?,?,?,?,?,?", "partograf persalinan", 10, new String[]{
                TNoRw.getText(), TrgRawat.getText(), Tgravida.getText(), Tparitas.getText(), Tabortus.getText(), Valid.SetTgl(TtglMasuk.getSelectedItem() + ""),
                cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), 
                cmbKetuban.getSelectedItem().toString(), jamKetuban, Sequel.cariIsi("select now()")
            }) == true) {
                if (tbDjj.getRowCount() != 0) {
                    for (i = 0; i < tbDjj.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_djj",
                                "'" + tbDjj.getValueAt(i, 0).toString() + "','"
                                + tbDjj.getValueAt(i, 1).toString() + "','"
                                + tbDjj.getValueAt(i, 2).toString() + "','"
                                + tbDjj.getValueAt(i, 3).toString() + "','"
                                + tbDjj.getValueAt(i, 4).toString() + "'", "Data DJJ");
                    }
                }

                if (tbAirKetuban.getRowCount() != 0) {
                    for (i = 0; i < tbAirKetuban.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_air_ketuban",
                                "'" + tbAirKetuban.getValueAt(i, 0).toString() + "','"
                                + tbAirKetuban.getValueAt(i, 1).toString() + "','"
                                + tbAirKetuban.getValueAt(i, 2).toString() + "','"
                                + tbAirKetuban.getValueAt(i, 3).toString() + "','"
                                + tbAirKetuban.getValueAt(i, 4).toString() + "'", "Data Air Ketuban");
                    }
                }

                if (tbServik.getRowCount() != 0) {
                    for (i = 0; i < tbServik.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_pembukaan_serviks",
                                "'" + tbServik.getValueAt(i, 0).toString() + "','"
                                + tbServik.getValueAt(i, 1).toString() + "','"
                                + tbServik.getValueAt(i, 2).toString() + "','"
                                + tbServik.getValueAt(i, 3).toString() + "','"
                                + tbServik.getValueAt(i, 4).toString() + "','"
                                + tbServik.getValueAt(i, 5).toString() + "','"
                                + tbServik.getValueAt(i, 6).toString() + "','"
                                + tbServik.getValueAt(i, 7).toString() + "','"
                                + tbServik.getValueAt(i, 8).toString() + "'", "Data Pembukaan Serviks");
                    }
                }

                if (tbKontraksi.getRowCount() != 0) {
                    for (i = 0; i < tbKontraksi.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_kontraksi",
                                "'" + tbKontraksi.getValueAt(i, 0).toString() + "','"
                                + tbKontraksi.getValueAt(i, 1).toString() + "','"
                                + tbKontraksi.getValueAt(i, 2).toString() + "','"
                                + tbKontraksi.getValueAt(i, 3).toString() + "'", "Data Kontraksi");
                    }
                }
                
                if (tbOksitosin.getRowCount() != 0) {
                    for (i = 0; i < tbOksitosin.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_oksitosin",
                                "'" + tbOksitosin.getValueAt(i, 0).toString() + "','"
                                + tbOksitosin.getValueAt(i, 1).toString() + "','"
                                + tbOksitosin.getValueAt(i, 2).toString() + "','"
                                + tbOksitosin.getValueAt(i, 3).toString() + "'", "Data Oksitosin");
                    }
                }

                if (tbObat.getRowCount() != 0) {
                    for (i = 0; i < tbObat.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_obat_cairan",
                                "'" + tbObat.getValueAt(i, 0).toString() + "','"
                                + tbObat.getValueAt(i, 1).toString() + "','"
                                + tbObat.getValueAt(i, 2).toString() + "'", "Data Obat & Cairan");
                    }                
                }

                if (tbNadi.getRowCount() != 0) {
                    for (i = 0; i < tbNadi.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_nadi_tensi",
                                "'" + tbNadi.getValueAt(i, 0).toString() + "','"
                                + tbNadi.getValueAt(i, 1).toString() + "','"
                                + tbNadi.getValueAt(i, 2).toString() + "','"
                                + tbNadi.getValueAt(i, 3).toString() + "','"
                                + tbNadi.getValueAt(i, 4).toString() + "','"
                                + tbNadi.getValueAt(i, 5).toString() + "'", "Data Nadi");
                    }
                }

                if (tbSuhu.getRowCount() != 0) {
                    for (i = 0; i < tbSuhu.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_suhu",
                                "'" + tbSuhu.getValueAt(i, 0).toString() + "','"
                                + tbSuhu.getValueAt(i, 1).toString() + "','"
                                + tbSuhu.getValueAt(i, 2).toString() + "','"
                                + tbSuhu.getValueAt(i, 3).toString() + "'", "Data Suhu");
                    }
                }
                
                if (tbUrin.getRowCount() != 0) {
                    for (i = 0; i < tbUrin.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_urin",
                                "'" + tbUrin.getValueAt(i, 0).toString() + "','"
                                + tbUrin.getValueAt(i, 1).toString() + "','"
                                + tbUrin.getValueAt(i, 2).toString() + "','"
                                + tbUrin.getValueAt(i, 3).toString() + "','"
                                + tbUrin.getValueAt(i, 4).toString() + "','"
                                + tbUrin.getValueAt(i, 5).toString() + "'", "Data Urin");
                    }
                }

                cekData();
                if (cmbPemberianOksi.getSelectedIndex() == 1) {
                    dataKala3A = Tkala3PemberianOksi.getText();
                } else if (cmbPemberianOksi.getSelectedIndex() == 2 || cmbPemberianOksi.getSelectedIndex() == 0) {
                    dataKala3B = Tkala3PemberianOksi.getText();
                } else if (cmbPemberianOksi.getSelectedIndex() == 3) {
                    dataKala3C = Tkala3PemberianOksi.getText();
                }

                if (cmbBayiLahir.getSelectedIndex() == 6) {
                    sebutkan38a = TbayiSebutkan.getText();
                } else if (cmbBayiAsfeksia.getSelectedIndex() == 6) {
                    sebutkan38b = TbayiSebutkan.getText();
                }

                if (Sequel.menyimpantf("partograf_catatan_persalinan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Catatan Persalinan", 87, new String[]{
                            TNoRw.getText(), Valid.SetTgl(TtglCatatan.getSelectedItem() + ""), nipBidan, cmbTmptPersalinan.getSelectedItem().toString(), TtmptPersalinanLain.getText(),
                            TalmtTmpPersalinan.getText(), cmbCttnRujuk.getSelectedItem().toString(), TalasanMerujuk.getText(), TtmptRujukan.getText(), bidan8, teman8,
                            klg8, suami8, dukun8, tdkAda8, gawat9, perdarahan9, infeksi9, peb9, hdk9, bidan9, lainya9, TmasalahLain.getText(), cmbPartogram.getSelectedItem().toString(),
                            Tkala1MasalahLain.getText(), Tkala1Penata.getText(), Tkala1Hasilnya.getText(), cmbEpisiotomi.getSelectedItem().toString(), Tkala2YaIndikasi.getText(),
                            suami15, klg15, teman15, dukun15, tdkAda15, cmbGawatJanin.getSelectedItem().toString(), Tkala2YaTindakanGawat.getText(), Tkala2Pemantauan.getText(),
                            cmbDistosia.getSelectedItem().toString(), Tkala2YaTindakanDisto.getText(), Tkala2MasalahLain.getText(), cmbInisiasi.getSelectedItem().toString(),
                            Tkala3Tidak.getText(), Tkala3Lama.getText(), cmbPemberianOksi.getSelectedItem().toString(), dataKala3A, dataKala3B, dataKala3C,
                            cmbPemberianUlang.getSelectedItem().toString(), Tkala3PemberianUlang.getText(), cmbPenegangan.getSelectedItem().toString(), Tkala3Penegangan.getText(),
                            cmbMasase.getSelectedItem().toString(), Tkala3Masase.getText(), cmbPlasenta25.getSelectedItem().toString(), Tkala3Plasenta25A.getText(), 
                            Tkala3Plasenta25B.getText(), cmbPlasenta26.getSelectedItem().toString(), Tkala3Plasenta26.getText(), cmbLaserasi.getSelectedItem().toString(),
                            Tkala3Laserasi.getText(), cmbJika.getSelectedItem().toString(), cmbTindakan.getSelectedItem().toString(), Tkala3Alasan.getText(),
                            cmbAtonia.getSelectedItem().toString(), Tkala3Atonia.getText(), Tkala3Jumlah.getText(), Tkala3Masalah.getText(), Tkala3Penata.getText(),
                            Tkala3Hasilnya.getText(), TbayiBB.getText(), TbayiPB.getText(), cmbJenkel.getSelectedItem().toString(), cmbPenilaian.getSelectedItem().toString(),
                            cmbBayiLahir.getSelectedItem().toString(), cmbBayiNormal.getSelectedItem().toString(), cmbBayiAsfeksia.getSelectedItem().toString(),
                            sebutkan38b, sebutkan38a, TbayiTindakanA.getText(), TbayiTindakanB.getText(), TbayiTindakanC.getText(), cmbPemberianAsi.getSelectedItem().toString(),
                            TbayiYaPemberian.getText(), TbayiTidakAlasan.getText(), TbayiMasalah.getText(), TbayiHasilnya.getText(), Sequel.cariIsi("select now()")
                        }) == true) {
                    System.out.println("Proses simpan data partograf catatan persalinan berhasil disimpan,...!!");
                }

                if (tbPemantauan.getRowCount() != 0) {
                    for (i = 0; i < tbPemantauan.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_kala_4",
                                "'" + tbPemantauan.getValueAt(i, 0).toString() + "','"
                                + tbPemantauan.getValueAt(i, 1).toString() + "','"
                                + tbPemantauan.getValueAt(i, 2).toString() + "','"
                                + tbPemantauan.getValueAt(i, 3).toString() + "','"
                                + tbPemantauan.getValueAt(i, 4).toString() + "','"
                                + tbPemantauan.getValueAt(i, 5).toString() + "','"
                                + tbPemantauan.getValueAt(i, 6).toString() + "','"
                                + tbPemantauan.getValueAt(i, 7).toString() + "','"
                                + tbPemantauan.getValueAt(i, 8).toString() + "','"
                                + tbPemantauan.getValueAt(i, 9).toString() + "','"
                                + tbPemantauan.getValueAt(i, 10).toString() + "','"
                                + tbPemantauan.getValueAt(i, 11).toString() + "'", "Data Pemantauan Persalinan Kala 4");
                    }
                }
                
                if (Sequel.menyimpantf("partograf_pemantauan_kala4", "?,?,?,?,?", "Pemantauan Kala 4", 5, new String[]{
                            TNoRw.getText(), TmasalahKala4.getText(), TpenataKala4.getText(), ThasilKala4.getText(), Sequel.cariIsi("select now()")
                        }) == true) {
                    System.out.println("Proses simpan data partograf pemantauan kala 4 berhasil disimpan,...!!");
                }

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Partograf Persalinan", "Simpan");
                TCari.setText(TNoRw.getText());
                TabPartograf.setSelectedIndex(2);
                emptTeks();
                tampil();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        TabPartograf.setSelectedIndex(0);
        tampil();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnBatal, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

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
        tampil();
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
        BtnCariActionPerformed(null);
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
            TCari.setText("");
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbPartografMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPartografMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPartografMouseClicked

    private void tbPartografKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPartografKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPartografKeyPressed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void tbDjjMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDjjMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataDjj();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDjjMouseClicked

    private void tbDjjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDjjKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getDataDjj();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDjjKeyPressed

    private void BtnBaruDjjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruDjjActionPerformed
        emptTeksDJJ();
        urutkanDataDjj();
    }//GEN-LAST:event_BtnBaruDjjActionPerformed

    private void BtnTambahDjjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahDjjActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (Tjeda.getText().equals("")) {
            Valid.textKosong(Tjeda, "jeda/menit");
            Tjeda.requestFocus();
        } else if (Tdjj.getText().equals("")) {
            Valid.textKosong(Tdjj, "Nadi");
            Tdjj.requestFocus();
        } else {
            urutData = "1";
            if (tabMode1.getRowCount() > 0) {
                int max = 0;
                for (int i = 0; i < tabMode1.getRowCount(); i++) {
                    int nilai = Integer.parseInt(tabMode1.getValueAt(i, 3).toString());
                    if (nilai > max) {
                        max = nilai;
                    }
                }
                urutData = String.valueOf(max + 1);
            }
            
            tabMode1.addRow(new String[]{TNoRw.getText(), Tdjj.getText(), Tjeda.getText(), urutData, Sequel.cariIsi("select now()")});
            BtnBaruDjjActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTambahDjjActionPerformed

    private void BtnHapusDjjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusDjjActionPerformed
        if (tbDjj.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data denyut jantung janin yang bisa dihapus..!!");
        } else {
            if (tbDjj.getSelectedRow() > -1) {
                int row = tbDjj.convertRowIndexToModel(tbDjj.getSelectedRow());
                tabMode1.removeRow(row);
                BtnBaruDjjActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel denyut jantung janin..!!");
                tbDjj.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusDjjActionPerformed

    private void BtnGantiDjjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiDjjActionPerformed
        if (tbDjj.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data denyut jantung janin yang bisa diganti..!!");
        } else {
            if (tbDjj.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (Tjeda.getText().equals("")) {
                    Valid.textKosong(Tjeda, "jeda/menit");
                    Tjeda.requestFocus();
                } else if (Tdjj.getText().equals("")) {
                    Valid.textKosong(Tdjj, "Nadi");
                    Tdjj.requestFocus();
                } else {
                    int row = tbDjj.convertRowIndexToModel(tbDjj.getSelectedRow());
                    tabMode1.setValueAt(TNoRw.getText(), row, 0);
                    tabMode1.setValueAt(Tdjj.getText(), row, 1);
                    tabMode1.setValueAt(Tjeda.getText(), row, 2);
                    tabMode1.setValueAt(urutanKe, row, 3);
                    tabMode1.setValueAt(Sequel.cariIsi("select now()"), row, 4);
                    
                    BtnBaruDjjActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel denyut jantung janin..!!");
                tbDjj.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiDjjActionPerformed

    private void TgravidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgravidaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tparitas.requestFocus();
        }
    }//GEN-LAST:event_TgravidaKeyPressed

    private void TparitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TparitasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tabortus.requestFocus();
        }
    }//GEN-LAST:event_TparitasKeyPressed

    private void TabortusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabortusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglMasuk.requestFocus();
        }
    }//GEN-LAST:event_TabortusKeyPressed

    private void TdjjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdjjKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnTambahDjj.requestFocus();
        }
    }//GEN-LAST:event_TdjjKeyPressed

    private void tbAirKetubanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAirKetubanMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataAirKetuban();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbAirKetubanMouseClicked

    private void tbAirKetubanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAirKetubanKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getDataAirKetuban();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbAirKetubanKeyPressed

    private void BtnBaruAKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruAKMActionPerformed
        emptTeksAirKetuban();
        urutkanDataAirKetuban();
    }//GEN-LAST:event_BtnBaruAKMActionPerformed

    private void BtnTambahAKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahAKMActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            urutData = "1";
            if (tabMode2.getRowCount() > 0) {
                int max = 0;
                for (int i = 0; i < tabMode2.getRowCount(); i++) {
                    int nilai = Integer.parseInt(tabMode2.getValueAt(i, 1).toString());
                    if (nilai > max) {
                        max = nilai;
                    }
                }
                urutData = String.valueOf(max + 1);
            }
            
            tabMode2.addRow(new String[]{TNoRw.getText(), urutData, cmbAirKetuban.getSelectedItem().toString(),
                cmbMulase.getSelectedItem().toString(), Sequel.cariIsi("select now()")});
            BtnBaruAKMActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTambahAKMActionPerformed

    private void BtnHapusAKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusAKMActionPerformed
        if (tbAirKetuban.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data air ketuban (mulase) yang bisa dihapus..!!");
        } else {
            if (tbAirKetuban.getSelectedRow() > -1) {
                int row = tbAirKetuban.convertRowIndexToModel(tbAirKetuban.getSelectedRow());
                tabMode2.removeRow(row);
                BtnBaruAKMActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel air ketuban (mulase)..!!");
                tbAirKetuban.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusAKMActionPerformed

    private void BtnGantiAKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiAKMActionPerformed
        if (tbAirKetuban.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data air ketuban (mulase) yang bisa diganti..!!");
        } else {
            if (tbAirKetuban.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else {
                    int row = tbAirKetuban.convertRowIndexToModel(tbAirKetuban.getSelectedRow());
                    tabMode2.setValueAt(TNoRw.getText(), row, 0);
                    tabMode2.setValueAt(urutanKe, row, 1);
                    tabMode2.setValueAt(cmbAirKetuban.getSelectedItem().toString(), row, 2);
                    tabMode2.setValueAt(cmbMulase.getSelectedItem().toString(), row, 3);
                    tabMode2.setValueAt(Sequel.cariIsi("select now()"), row, 4);
                    
                    BtnBaruAKMActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel air ketuban (mulase)..!!");
                tbAirKetuban.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiAKMActionPerformed

    private void cmbJam4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam4MouseReleased
        AutoCompleteDecorator.decorate(cmbJam4);
    }//GEN-LAST:event_cmbJam4MouseReleased

    private void cmbMnt4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt4MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt4);
    }//GEN-LAST:event_cmbMnt4MouseReleased

    private void cmbDtk4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk4MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk4);
    }//GEN-LAST:event_cmbDtk4MouseReleased

    private void tbServikMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbServikMouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getDataServiks();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbServikMouseClicked

    private void tbServikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbServikKeyPressed
        if (tabMode3.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getDataServiks();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbServikKeyPressed

    private void BtnBaruServikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruServikActionPerformed
        emptTeksServik();
        urutkanDataServiks();
    }//GEN-LAST:event_BtnBaruServikActionPerformed

    private void BtnHapusServikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusServikActionPerformed
        if (tbServik.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data pembukaan serviks yang bisa dihapus..!!");
        } else {
            if (tbServik.getSelectedRow() > -1) {                
                int row = tbServik.convertRowIndexToModel(tbServik.getSelectedRow());
                tabMode3.removeRow(row);
                BtnBaruServikActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel pembukaan serviks..!!");
                tbServik.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusServikActionPerformed

    private void BtnTambahServikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahServikActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            urutData = "1";
            if (tabMode3.getRowCount() > 0) {
                int max = 0;
                for (int i = 0; i < tabMode3.getRowCount(); i++) {
                    int nilai = Integer.parseInt(tabMode3.getValueAt(i, 7).toString());
                    if (nilai > max) {
                        max = nilai;
                    }
                }
                urutData = String.valueOf(max + 1);
            }
            
            if (chkPembukaan.isSelected() == true) {
                pembukaan = "X";
            } else {
                pembukaan = "";
            }
            
            if (chkTurunya.isSelected() == true) {
                trun_kpl = "O";
            } else {
                trun_kpl = "";
            }
            
            tabMode3.addRow(new String[]{TNoRw.getText(), cmbWaktuKe.getSelectedItem().toString(),
                cmbJam4.getSelectedItem() + ":" + cmbMnt4.getSelectedItem() + ":" + cmbDtk4.getSelectedItem(),
                cmbCm.getSelectedItem().toString(), pembukaan, trun_kpl, TketServik.getText(), urutData, Sequel.cariIsi("select now()")
            });
            BtnBaruServikActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTambahServikActionPerformed

    private void BtnGantiServikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiServikActionPerformed
        if (tbServik.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data pembukaan serviks yang bisa diganti..!!");
        } else {
            if (tbServik.getSelectedRow() > -1) {                
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else {
                    if (chkPembukaan.isSelected() == true) {
                        pembukaan = "X";
                    } else {
                        pembukaan = "";
                    }

                    if (chkTurunya.isSelected() == true) {
                        trun_kpl = "O";
                    } else {
                        trun_kpl = "";
                    }
                    
                    int row = tbServik.convertRowIndexToModel(tbServik.getSelectedRow());
                    tabMode3.setValueAt(TNoRw.getText(), row, 0);
                    tabMode3.setValueAt(cmbWaktuKe.getSelectedItem().toString(), row, 1);
                    tabMode3.setValueAt(cmbJam4.getSelectedItem() + ":" + cmbMnt4.getSelectedItem() + ":" + cmbDtk4.getSelectedItem(), row, 2);
                    tabMode3.setValueAt(cmbCm.getSelectedItem().toString(), row, 3);
                    tabMode3.setValueAt(pembukaan, row, 4);
                    tabMode3.setValueAt(trun_kpl, row, 5);
                    tabMode3.setValueAt(TketServik.getText(), row, 6);
                    tabMode3.setValueAt(urutanKe, row, 7);
                    tabMode3.setValueAt(Sequel.cariIsi("select now()"), row, 8);
                    
                    BtnBaruServikActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel pembukaan serviks..!!");
                tbServik.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiServikActionPerformed

    private void tbKontraksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKontraksiMouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {
                getDataKontraksi();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbKontraksiMouseClicked

    private void tbKontraksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKontraksiKeyPressed
        if (tabMode4.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getDataKontraksi();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbKontraksiKeyPressed

    private void BtnBaruKontraksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruKontraksiActionPerformed
        emptTeksKontaksi();
        urutkanDataKontraksi();
    }//GEN-LAST:event_BtnBaruKontraksiActionPerformed

    private void BtnHapusKontraksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusKontraksiActionPerformed
        if (tbKontraksi.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data kontraksi tiap 10 menit yang bisa dihapus..!!");
        } else {
            if (tbKontraksi.getSelectedRow() > -1) {
                int row = tbKontraksi.convertRowIndexToModel(tbKontraksi.getSelectedRow());
                tabMode4.removeRow(row);
                BtnBaruKontraksiActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel kontraksi tiap 10 menit..!!");
                tbKontraksi.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusKontraksiActionPerformed

    private void BtnTambahKontraksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahKontraksiActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (cmbDetik.getSelectedIndex() == 0) {
            Valid.textKosong(cmbDetik, "Detik Kontraksi");
            cmbDetik.requestFocus();
        } else {
            tabMode4.addRow(new String[]{TNoRw.getText(), cmbLajur.getSelectedItem().toString(), cmbDetik.getSelectedItem().toString(),
                Sequel.cariIsi("select now()")});
            
            BtnBaruKontraksiActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTambahKontraksiActionPerformed

    private void BtnGantiKontraksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiKontraksiActionPerformed
        if (tbKontraksi.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data kontraksi tiap 10 menit yang bisa diganti..!!");
        } else {
            if (tbKontraksi.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (cmbDetik.getSelectedIndex() == 0) {
                    Valid.textKosong(cmbDetik, "Detik Kontraksi");
                    cmbDetik.requestFocus();
                } else {
                    int row = tbKontraksi.convertRowIndexToModel(tbKontraksi.getSelectedRow());
                    tabMode4.setValueAt(TNoRw.getText(), row, 0);
                    tabMode4.setValueAt(cmbLajur.getSelectedItem().toString(), row, 1);
                    tabMode4.setValueAt(cmbDetik.getSelectedItem().toString(), row, 2);
                    tabMode4.setValueAt(Sequel.cariIsi("select now()"), row, 3);
                    
                    BtnBaruKontraksiActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel kontraksi tiap 10 menit..!!");
                tbKontraksi.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiKontraksiActionPerformed

    private void tbOksitosinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbOksitosinMouseClicked
        if (tabMode5.getRowCount() != 0) {
            try {
                getDataOksitosin();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbOksitosinMouseClicked

    private void tbOksitosinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbOksitosinKeyPressed
        if (tabMode5.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getDataOksitosin();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbOksitosinKeyPressed

    private void BtnBaruOksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruOksiActionPerformed
        emptTeksOksitosin();
        urutkanDataOksitosi();
    }//GEN-LAST:event_BtnBaruOksiActionPerformed

    private void BtnHapusOksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusOksiActionPerformed
        if (tbOksitosin.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data oksitosin yang bisa dihapus..!!");
        } else {
            if (tbOksitosin.getSelectedRow() > -1) {
                int row = tbOksitosin.convertRowIndexToModel(tbOksitosin.getSelectedRow());
                tabMode5.removeRow(row);
                BtnBaruOksiActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel oksitosin..!!");
                tbOksitosin.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusOksiActionPerformed

    private void BtnTambahOksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahOksiActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (Toksitosin.getText().equals("")) {
            Valid.textKosong(Toksitosin, "konsentrasi U/L");
            Toksitosin.requestFocus();
        } else if (Ttetes.getText().equals("")) {
            Valid.textKosong(Ttetes, "kecepatan (tetes/mnt)");
            Ttetes.requestFocus();
        } else {
            tabMode5.addRow(new String[]{TNoRw.getText(), Toksitosin.getText(), Ttetes.getText(), Sequel.cariIsi("select now()")});
            BtnBaruOksiActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTambahOksiActionPerformed

    private void BtnGantiOksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiOksiActionPerformed
        if (tbOksitosin.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data oksitosin yang bisa diganti..!!");
        } else {
            if (tbOksitosin.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (Ttetes.getText().equals("")) {
                    Valid.textKosong(Ttetes, "Oksitosin");
                    Ttetes.requestFocus();
                } else {
                    int row = tbOksitosin.convertRowIndexToModel(tbOksitosin.getSelectedRow());
                    tabMode5.setValueAt(TNoRw.getText(), row, 0);
                    tabMode5.setValueAt(Toksitosin.getText(), row, 1);
                    tabMode5.setValueAt(Ttetes.getText(), row, 2);
                    
                    BtnBaruOksiActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel oksitosin..!!");
                tbOksitosin.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiOksiActionPerformed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabMode6.getRowCount() != 0) {
            try {
                getDataObatCairan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tabMode6.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getDataObatCairan();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObatKeyPressed

    private void BtnBaruObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruObatActionPerformed
        emptTeksObat();
        urutkanDataObat();
    }//GEN-LAST:event_BtnBaruObatActionPerformed

    private void BtnHapusObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusObatActionPerformed
        if (tbObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data obat & cairan IV yang bisa dihapus..!!");
        } else {
            if (tbObat.getSelectedRow() > -1) {
                int row = tbObat.convertRowIndexToModel(tbObat.getSelectedRow());
                tabMode6.removeRow(row);
                BtnBaruObatActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel obat & cairan IV..!!");
                tbObat.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusObatActionPerformed

    private void BtnTambahObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahObatActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (TobatCairan.getText().equals("")) {
            Valid.textKosong(TobatCairan, "Obat & Cairan IV");
            TobatCairan.requestFocus();
        } else {
            tabMode6.addRow(new String[]{TNoRw.getText(), TobatCairan.getText(), Sequel.cariIsi("select now()")});
            BtnBaruObatActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTambahObatActionPerformed

    private void BtnGantiObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiObatActionPerformed
        if (tbObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data obat & cairan IV yang bisa diganti..!!");
        } else {
            if (tbObat.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (TobatCairan.getText().equals("")) {
                    Valid.textKosong(TobatCairan, "Obat & Cairan IV");
                    TobatCairan.requestFocus();
                } else {
                    int row = tbObat.convertRowIndexToModel(tbObat.getSelectedRow());
                    tabMode6.setValueAt(TNoRw.getText(), row, 0);
                    tabMode6.setValueAt(TobatCairan.getText().toString(), row, 1);
                    
                    BtnBaruObatActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel obat & cairan IV..!!");
                tbObat.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiObatActionPerformed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsistol.requestFocus();
        }
    }//GEN-LAST:event_TnadiKeyPressed

    private void TsistolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsistolKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdistol.requestFocus();
        }
    }//GEN-LAST:event_TsistolKeyPressed

    private void TdistolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdistolKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnTambahNadi.requestFocus();
        }
    }//GEN-LAST:event_TdistolKeyPressed

    private void tbNadiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNadiMouseClicked
        if (tabMode7.getRowCount() != 0) {
            try {
                getDataNadiTD();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbNadiMouseClicked

    private void tbNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNadiKeyPressed
        if (tabMode7.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getDataNadiTD();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbNadiKeyPressed

    private void BtnBaruNadiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruNadiActionPerformed
        emptTeksNadiTensi();
        urutkanDataNadi();
    }//GEN-LAST:event_BtnBaruNadiActionPerformed

    private void BtnHapusNadiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusNadiActionPerformed
        if (tbNadi.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data nadi & tekanan darah yang bisa dihapus..!!");
        } else {
            if (tbNadi.getSelectedRow() > -1) {
                int row = tbNadi.convertRowIndexToModel(tbNadi.getSelectedRow());
                tabMode7.removeRow(row);
                BtnBaruNadiActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel nadi & tekanan darah..!!");
                tbNadi.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusNadiActionPerformed

    private void BtnTambahNadiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahNadiActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            urutData = "1";
            if (tabMode7.getRowCount() > 0) {
                int max = 0;
                for (int i = 0; i < tabMode7.getRowCount(); i++) {
                    int nilai = Integer.parseInt(tabMode7.getValueAt(i, 1).toString());
                    if (nilai > max) {
                        max = nilai;
                    }
                }
                urutData = String.valueOf(max + 1);
            }
            
            tabMode7.addRow(new String[]{TNoRw.getText(), urutData, Tnadi.getText(), Tsistol.getText(), Tdistol.getText(), Sequel.cariIsi("select now()")});
            BtnBaruNadiActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTambahNadiActionPerformed

    private void BtnGantiNadiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiNadiActionPerformed
        if (tbNadi.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data nadi & tekanan darah yang bisa diganti..!!");
        } else {
            if (tbNadi.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else {
                    int row = tbNadi.convertRowIndexToModel(tbNadi.getSelectedRow());
                    tabMode7.setValueAt(TNoRw.getText(), row, 0);
                    tabMode7.setValueAt(urutanKe, row, 1);
                    tabMode7.setValueAt(Tnadi.getText(), row, 2);
                    tabMode7.setValueAt(Tsistol.getText(), row, 3);
                    tabMode7.setValueAt(Tdistol.getText(), row, 4);
                    
                    BtnBaruNadiActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel nadi & tekanan darah..!!");
                tbNadi.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiNadiActionPerformed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJam9.requestFocus();
        }
    }//GEN-LAST:event_TsuhuKeyPressed

    private void cmbJam9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam9MouseReleased
        AutoCompleteDecorator.decorate(cmbJam9);
    }//GEN-LAST:event_cmbJam9MouseReleased

    private void cmbMnt9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt9MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt9);
    }//GEN-LAST:event_cmbMnt9MouseReleased

    private void cmbDtk9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk9MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk9);
    }//GEN-LAST:event_cmbDtk9MouseReleased

    private void tbSuhuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSuhuMouseClicked
        if (tabMode8.getRowCount() != 0) {
            try {
                getDataSuhu();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbSuhuMouseClicked

    private void tbSuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSuhuKeyPressed
        if (tabMode8.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getDataSuhu();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbSuhuKeyPressed

    private void BtnBaruSuhuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruSuhuActionPerformed
        emptTeksSuhu();
        urutkanDataSuhu();
    }//GEN-LAST:event_BtnBaruSuhuActionPerformed

    private void BtnHapusSuhuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusSuhuActionPerformed
        if (tbSuhu.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data suhu yang bisa dihapus..!!");
        } else {
            if (tbSuhu.getSelectedRow() > -1) {
                int row = tbSuhu.convertRowIndexToModel(tbSuhu.getSelectedRow());
                tabMode8.removeRow(row);
                BtnBaruSuhuActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel suhu..!!");
                tbSuhu.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusSuhuActionPerformed

    private void BtnTambahSuhuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahSuhuActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (Tsuhu.getText().equals("")) {
            Valid.textKosong(Tsuhu, "Suhu");
            Tsuhu.requestFocus();
        } else {
            tabMode8.addRow(new String[]{TNoRw.getText(), cmbJam9.getSelectedItem() + ":" + cmbMnt9.getSelectedItem() + ":" + cmbDtk9.getSelectedItem(),
                Tsuhu.getText(), Sequel.cariIsi("select now()")
            });
            BtnBaruSuhuActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTambahSuhuActionPerformed

    private void BtnGantiSuhuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiSuhuActionPerformed
        if (tbSuhu.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data suhu yang bisa diganti..!!");
        } else {
            if (tbSuhu.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else if (Tsuhu.getText().equals("")) {
                    Valid.textKosong(Tsuhu, "Suhu");
                    Tsuhu.requestFocus();
                } else {
                    int row = tbSuhu.convertRowIndexToModel(tbSuhu.getSelectedRow());
                    tabMode8.setValueAt(TNoRw.getText(), row, 0);
                    tabMode8.setValueAt(cmbJam9.getSelectedItem() + ":" + cmbMnt9.getSelectedItem() + ":" + cmbDtk9.getSelectedItem(), row, 1);
                    tabMode8.setValueAt(Tsuhu.getText(), row, 2);
                    tabMode8.setValueAt(Sequel.cariIsi("select now()"), row, 3);
                    
                    BtnBaruSuhuActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel suhu..!!");
                tbSuhu.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiSuhuActionPerformed

    private void TvolumeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TvolumeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJam10.requestFocus();
        }
    }//GEN-LAST:event_TvolumeKeyPressed

    private void cmbJam10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam10MouseReleased
        AutoCompleteDecorator.decorate(cmbJam10);
    }//GEN-LAST:event_cmbJam10MouseReleased

    private void cmbMnt10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt10MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt10);
    }//GEN-LAST:event_cmbMnt10MouseReleased

    private void cmbDtk10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk10MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk10);
    }//GEN-LAST:event_cmbDtk10MouseReleased

    private void tbUrinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUrinMouseClicked
        if (tabMode9.getRowCount() != 0) {
            try {
                getDataUrin();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbUrinMouseClicked

    private void tbUrinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbUrinKeyPressed
        if (tabMode9.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getDataUrin();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbUrinKeyPressed

    private void BtnBaruUrinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruUrinActionPerformed
        emptTeksUrin();
        urutkanDataUrin();
    }//GEN-LAST:event_BtnBaruUrinActionPerformed

    private void BtnHapusUrinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusUrinActionPerformed
        if (tbUrin.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data urin yang bisa dihapus..!!");
        } else {
            if (tbUrin.getSelectedRow() > -1) {
                int row = tbUrin.convertRowIndexToModel(tbUrin.getSelectedRow());
                tabMode9.removeRow(row);
                BtnBaruUrinActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel urin..!!");
                tbUrin.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusUrinActionPerformed

    private void BtnTambahUrinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahUrinActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            tabMode9.addRow(new String[]{TNoRw.getText(), cmbJam10.getSelectedItem() + ":" + cmbMnt10.getSelectedItem() + ":" + cmbDtk10.getSelectedItem(),
                cmbProtein.getSelectedItem().toString(), cmbAseton.getSelectedItem().toString(), Tvolume.getText(), Sequel.cariIsi("select now()")
            });
            BtnBaruUrinActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTambahUrinActionPerformed

    private void BtnGantiUrinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiUrinActionPerformed
        if (tbUrin.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data urin yang bisa diganti..!!");
        } else {
            if (tbUrin.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else {
                    int row = tbUrin.convertRowIndexToModel(tbUrin.getSelectedRow());
                    tabMode9.setValueAt(TNoRw.getText(), row, 0);
                    tabMode9.setValueAt(cmbJam10.getSelectedItem() + ":" + cmbMnt10.getSelectedItem() + ":" + cmbDtk10.getSelectedItem(), row, 1);
                    tabMode9.setValueAt(cmbProtein.getSelectedItem().toString(), row, 2);
                    tabMode9.setValueAt(cmbAseton.getSelectedItem().toString(), row, 3);
                    tabMode9.setValueAt(Tvolume.getText(), row, 4);
                    tabMode9.setValueAt(Sequel.cariIsi("select now()"), row, 5);
                    
                    BtnBaruUrinActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel urin..!!");
                tbUrin.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiUrinActionPerformed

    private void cmbTmptPersalinanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTmptPersalinanActionPerformed
        TtmptPersalinanLain.setText("");
        if (cmbTmptPersalinan.getSelectedIndex() == 6) {
            TtmptPersalinanLain.setEnabled(true);
            TtmptPersalinanLain.requestFocus();
        } else {
            TtmptPersalinanLain.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTmptPersalinanActionPerformed

    private void chkLainya9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainya9ActionPerformed
        TmasalahLain.setText("");
        if (chkLainya9.isSelected() == true) {
            TmasalahLain.setEnabled(true);
            TmasalahLain.requestFocus();
        } else {
            TmasalahLain.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainya9ActionPerformed

    private void cmbEpisiotomiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEpisiotomiActionPerformed
        Tkala2YaIndikasi.setText("");
        if (cmbEpisiotomi.getSelectedIndex() == 1) {
            Tkala2YaIndikasi.setEnabled(true);
            Tkala2YaIndikasi.requestFocus();
        } else {
            Tkala2YaIndikasi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbEpisiotomiActionPerformed

    private void cmbGawatJaninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGawatJaninActionPerformed
        Tkala2YaTindakanGawat.setText("");
        if (cmbGawatJanin.getSelectedIndex() == 2) {
            Tkala2YaTindakanGawat.setEnabled(true);
            Tkala2YaTindakanGawat.requestFocus();
        } else {
            Tkala2YaTindakanGawat.setEnabled(false);
        }
    }//GEN-LAST:event_cmbGawatJaninActionPerformed

    private void cmbDistosiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDistosiaActionPerformed
        Tkala2YaTindakanDisto.setText("");
        if (cmbDistosia.getSelectedIndex() == 2) {
            Tkala2YaTindakanDisto.setEnabled(true);
            Tkala2YaTindakanDisto.requestFocus();
        } else {
            Tkala2YaTindakanDisto.setEnabled(false);
        }
    }//GEN-LAST:event_cmbDistosiaActionPerformed

    private void cmbInisiasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbInisiasiActionPerformed
        Tkala3Tidak.setText("");
        if (cmbInisiasi.getSelectedIndex() == 2) {
            Tkala3Tidak.setEnabled(true);
            Tkala3Tidak.requestFocus();
        } else {
            Tkala3Tidak.setEnabled(false);
        }
    }//GEN-LAST:event_cmbInisiasiActionPerformed

    private void cmbPemberianOksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPemberianOksiActionPerformed
        Tkala3PemberianOksi.setText("");
        labelKetPemberian.setText("");
        Tkala3PemberianOksi.requestFocus();

        if (cmbPemberianOksi.getSelectedIndex() == 1) {
            labelKetPemberian.setText("Menit sesudah persalinan");
            Tkala3PemberianOksi.setEnabled(true);
            Tkala3PemberianOksi.setDocument(new batasInput((int) 7).getKata(Tkala3PemberianOksi));
        } else if (cmbPemberianOksi.getSelectedIndex() == 2 || cmbPemberianOksi.getSelectedIndex() == 0) {
            labelKetPemberian.setText("");
            Tkala3PemberianOksi.setEnabled(true);
            Tkala3PemberianOksi.setDocument(new batasInput((int) 200).getKata(Tkala3PemberianOksi));
        } else if (cmbPemberianOksi.getSelectedIndex() == 3) {
            labelKetPemberian.setText("Menit setelah bayi lahir");
            Tkala3PemberianOksi.setEnabled(true);
            Tkala3PemberianOksi.setDocument(new batasInput((int) 7).getKata(Tkala3PemberianOksi));
        } else {
            Tkala3PemberianOksi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPemberianOksiActionPerformed

    private void cmbPemberianUlangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPemberianUlangActionPerformed
        Tkala3PemberianUlang.setText("");
        if (cmbPemberianUlang.getSelectedIndex() == 1) {
            Tkala3PemberianUlang.setEnabled(true);
            Tkala3PemberianUlang.requestFocus();
        } else {
            Tkala3PemberianUlang.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPemberianUlangActionPerformed

    private void cmbPeneganganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPeneganganActionPerformed
        Tkala3Penegangan.setText("");
        if (cmbPenegangan.getSelectedIndex() == 2) {
            Tkala3Penegangan.setEnabled(true);
            Tkala3Penegangan.requestFocus();
        } else {
            Tkala3Penegangan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPeneganganActionPerformed

    private void cmbMasaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMasaseActionPerformed
        Tkala3Masase.setText("");
        if (cmbMasase.getSelectedIndex() == 2) {
            Tkala3Masase.setEnabled(true);
            Tkala3Masase.requestFocus();
        } else {
            Tkala3Masase.setEnabled(false);
        }
    }//GEN-LAST:event_cmbMasaseActionPerformed

    private void cmbPlasenta25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPlasenta25ActionPerformed
        Tkala3Plasenta25A.setText("");
        Tkala3Plasenta25B.setText("");
        if (cmbPlasenta25.getSelectedIndex() == 2) {
            Tkala3Plasenta25A.setEnabled(true);
            Tkala3Plasenta25B.setEnabled(true);
            Tkala3Plasenta25A.requestFocus();
        } else {
            Tkala3Plasenta25A.setEnabled(false);
            Tkala3Plasenta25B.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPlasenta25ActionPerformed

    private void cmbPlasenta26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPlasenta26ActionPerformed
        Tkala3Plasenta26.setText("");
        if (cmbPlasenta26.getSelectedIndex() == 2) {
            Tkala3Plasenta26.setEnabled(true);
            Tkala3Plasenta26.requestFocus();
        } else {
            Tkala3Plasenta26.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPlasenta26ActionPerformed

    private void cmbLaserasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLaserasiActionPerformed
        Tkala3Laserasi.setText("");
        if (cmbLaserasi.getSelectedIndex() == 1) {
            Tkala3Laserasi.setEnabled(true);
            Tkala3Laserasi.requestFocus();
        } else {
            Tkala3Laserasi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbLaserasiActionPerformed

    private void cmbTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTindakanActionPerformed
        Tkala3Alasan.setText("");
        if (cmbTindakan.getSelectedIndex() == 3) {
            Tkala3Alasan.setEnabled(true);
            Tkala3Alasan.requestFocus();
        } else {
            Tkala3Alasan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTindakanActionPerformed

    private void cmbAtoniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAtoniaActionPerformed
        Tkala3Atonia.setText("");
        if (cmbAtonia.getSelectedIndex() == 2) {
            Tkala3Atonia.setEnabled(true);
            Tkala3Atonia.requestFocus();
        } else {
            Tkala3Atonia.setEnabled(false);
        }
    }//GEN-LAST:event_cmbAtoniaActionPerformed

    private void TtmptPersalinanLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtmptPersalinanLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TalmtTmpPersalinan.requestFocus();
        }
    }//GEN-LAST:event_TtmptPersalinanLainKeyPressed

    private void TalmtTmpPersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalmtTmpPersalinanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbCttnRujuk.requestFocus();
        }
    }//GEN-LAST:event_TalmtTmpPersalinanKeyPressed

    private void TalasanMerujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalasanMerujukKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtmptRujukan.requestFocus();
        }
    }//GEN-LAST:event_TalasanMerujukKeyPressed

    private void TtmptRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtmptRujukanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkBidan8.requestFocus();
        }
    }//GEN-LAST:event_TtmptRujukanKeyPressed

    private void TmasalahLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmasalahLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPartogram.requestFocus();
        }
    }//GEN-LAST:event_TmasalahLainKeyPressed

    private void Tkala1MasalahLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala1MasalahLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkala1Penata.requestFocus();
        }
    }//GEN-LAST:event_Tkala1MasalahLainKeyPressed

    private void Tkala1PenataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala1PenataKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkala1Hasilnya.requestFocus();
        }
    }//GEN-LAST:event_Tkala1PenataKeyPressed

    private void Tkala1HasilnyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala1HasilnyaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbEpisiotomi.requestFocus();
        }
    }//GEN-LAST:event_Tkala1HasilnyaKeyPressed

    private void Tkala2YaIndikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala2YaIndikasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkKala2Suami.requestFocus();
        }
    }//GEN-LAST:event_Tkala2YaIndikasiKeyPressed

    private void Tkala2YaTindakanGawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala2YaTindakanGawatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkala2Pemantauan.requestFocus();
        }
    }//GEN-LAST:event_Tkala2YaTindakanGawatKeyPressed

    private void Tkala2PemantauanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala2PemantauanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbDistosia.requestFocus();
        }
    }//GEN-LAST:event_Tkala2PemantauanKeyPressed

    private void Tkala2YaTindakanDistoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala2YaTindakanDistoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkala2MasalahLain.requestFocus();
        }
    }//GEN-LAST:event_Tkala2YaTindakanDistoKeyPressed

    private void Tkala2MasalahLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala2MasalahLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbInisiasi.requestFocus();
        }
    }//GEN-LAST:event_Tkala2MasalahLainKeyPressed

    private void Tkala3TidakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala3TidakKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkala3Lama.requestFocus();
        }
    }//GEN-LAST:event_Tkala3TidakKeyPressed

    private void Tkala3LamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala3LamaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPemberianOksi.requestFocus();
        }
    }//GEN-LAST:event_Tkala3LamaKeyPressed

    private void Tkala3PemberianOksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala3PemberianOksiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPemberianUlang.requestFocus();
        }
    }//GEN-LAST:event_Tkala3PemberianOksiKeyPressed

    private void Tkala3PemberianUlangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala3PemberianUlangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPenegangan.requestFocus();
        }
    }//GEN-LAST:event_Tkala3PemberianUlangKeyPressed

    private void Tkala3PeneganganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala3PeneganganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbMasase.requestFocus();
        }
    }//GEN-LAST:event_Tkala3PeneganganKeyPressed

    private void Tkala3MasaseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala3MasaseKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPlasenta25.requestFocus();
        }
    }//GEN-LAST:event_Tkala3MasaseKeyPressed

    private void Tkala3Plasenta25AKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala3Plasenta25AKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkala3Plasenta25B.requestFocus();
        }
    }//GEN-LAST:event_Tkala3Plasenta25AKeyPressed

    private void Tkala3Plasenta25BKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala3Plasenta25BKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPlasenta26.requestFocus();
        }
    }//GEN-LAST:event_Tkala3Plasenta25BKeyPressed

    private void Tkala3Plasenta26KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala3Plasenta26KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbLaserasi.requestFocus();
        }
    }//GEN-LAST:event_Tkala3Plasenta26KeyPressed

    private void Tkala3LaserasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala3LaserasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJika.requestFocus();
        }
    }//GEN-LAST:event_Tkala3LaserasiKeyPressed

    private void Tkala3AlasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala3AlasanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbAtonia.requestFocus();
        }
    }//GEN-LAST:event_Tkala3AlasanKeyPressed

    private void Tkala3AtoniaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala3AtoniaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkala3Jumlah.requestFocus();
        }
    }//GEN-LAST:event_Tkala3AtoniaKeyPressed

    private void Tkala3JumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala3JumlahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkala3Masalah.requestFocus();
        }
    }//GEN-LAST:event_Tkala3JumlahKeyPressed

    private void Tkala3MasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala3MasalahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkala3Penata.requestFocus();
        }
    }//GEN-LAST:event_Tkala3MasalahKeyPressed

    private void Tkala3PenataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala3PenataKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkala3Hasilnya.requestFocus();
        }
    }//GEN-LAST:event_Tkala3PenataKeyPressed

    private void TbayiBBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbayiBBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TbayiPB.requestFocus();
        }
    }//GEN-LAST:event_TbayiBBKeyPressed

    private void Tkala3HasilnyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkala3HasilnyaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TbayiBB.requestFocus();
        }
    }//GEN-LAST:event_Tkala3HasilnyaKeyPressed

    private void TbayiPBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbayiPBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJenkel.requestFocus();
        }
    }//GEN-LAST:event_TbayiPBKeyPressed

    private void TbayiTindakanAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbayiTindakanAKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TbayiTindakanB.requestFocus();
        }
    }//GEN-LAST:event_TbayiTindakanAKeyPressed

    private void TbayiTindakanBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbayiTindakanBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TbayiTindakanC.requestFocus();
        }
    }//GEN-LAST:event_TbayiTindakanBKeyPressed

    private void TbayiTindakanCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbayiTindakanCKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPemberianAsi.requestFocus();
        }
    }//GEN-LAST:event_TbayiTindakanCKeyPressed

    private void cmbBayiLahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBayiLahirActionPerformed
        cmbBayiNormal.setSelectedIndex(0);
        cmbBayiAsfeksia.setSelectedIndex(0);
        TbayiSebutkan.setText("");
        TbayiTindakanA.setText("");
        TbayiTindakanB.setText("");
        TbayiTindakanC.setText("");

        if (cmbBayiLahir.getSelectedIndex() == 1) {
            cmbBayiNormal.setEnabled(true);
            cmbBayiNormal.requestFocus();
            cmbBayiAsfeksia.setEnabled(false);
            TbayiSebutkan.setEnabled(false);
            TbayiTindakanA.setEnabled(false);
            TbayiTindakanB.setEnabled(false);
            TbayiTindakanC.setEnabled(false);
        } else if (cmbBayiLahir.getSelectedIndex() == 2 || cmbBayiLahir.getSelectedIndex() == 3
                || cmbBayiLahir.getSelectedIndex() == 4 || cmbBayiLahir.getSelectedIndex() == 5) {
            cmbBayiNormal.setEnabled(false);            
            cmbBayiAsfeksia.setEnabled(true);
            cmbBayiAsfeksia.requestFocus();
            TbayiSebutkan.setEnabled(false);
            TbayiTindakanA.setEnabled(false);
            TbayiTindakanB.setEnabled(false);
            TbayiTindakanC.setEnabled(false);
        } else if (cmbBayiLahir.getSelectedIndex() == 6) {
            cmbBayiNormal.setEnabled(false);
            cmbBayiAsfeksia.setEnabled(false);            
            TbayiSebutkan.setEnabled(true);
            TbayiSebutkan.requestFocus();
            TbayiTindakanA.setEnabled(false);
            TbayiTindakanB.setEnabled(false);
            TbayiTindakanC.setEnabled(false);
        } else if (cmbBayiLahir.getSelectedIndex() == 7) {
            cmbBayiNormal.setEnabled(false);
            cmbBayiAsfeksia.setEnabled(false);
            TbayiSebutkan.setEnabled(false);            
            TbayiTindakanA.setEnabled(true);
            TbayiTindakanA.requestFocus();
            TbayiTindakanB.setEnabled(true);
            TbayiTindakanC.setEnabled(true);
        } else {
            cmbBayiNormal.setEnabled(false);
            cmbBayiAsfeksia.setEnabled(false);
            TbayiSebutkan.setEnabled(false);
            TbayiTindakanA.setEnabled(false);
            TbayiTindakanB.setEnabled(false);
            TbayiTindakanC.setEnabled(false);
        }
    }//GEN-LAST:event_cmbBayiLahirActionPerformed

    private void cmbBayiAsfeksiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBayiAsfeksiaActionPerformed
        TbayiSebutkan.setText("");
        if (cmbBayiAsfeksia.getSelectedIndex() == 6) {
            TbayiSebutkan.setEnabled(true);
            TbayiSebutkan.requestFocus();
        } else {
            TbayiSebutkan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbBayiAsfeksiaActionPerformed

    private void cmbPemberianAsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPemberianAsiActionPerformed
        TbayiYaPemberian.setText("");
        TbayiTidakAlasan.setText("");
        
        if (cmbPemberianAsi.getSelectedIndex() == 1) {
            TbayiYaPemberian.setEnabled(true);
            TbayiYaPemberian.requestFocus();
            TbayiTidakAlasan.setEnabled(false);
        } else if (cmbPemberianAsi.getSelectedIndex() == 2) {
            TbayiYaPemberian.setEnabled(false);            
            TbayiTidakAlasan.setEnabled(true);
            TbayiTidakAlasan.requestFocus();
        } else {
            TbayiYaPemberian.setEnabled(false);
            TbayiTidakAlasan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPemberianAsiActionPerformed

    private void TbayiYaPemberianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbayiYaPemberianKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TbayiMasalah.requestFocus();
        }
    }//GEN-LAST:event_TbayiYaPemberianKeyPressed

    private void TbayiTidakAlasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbayiTidakAlasanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TbayiMasalah.requestFocus();
        }
    }//GEN-LAST:event_TbayiTidakAlasanKeyPressed

    private void TbayiMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbayiMasalahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TbayiHasilnya.requestFocus();
        }
    }//GEN-LAST:event_TbayiMasalahKeyPressed

    private void TbayiHasilnyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbayiHasilnyaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjamKe.requestFocus();
        }
    }//GEN-LAST:event_TbayiHasilnyaKeyPressed

    private void TjamKeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjamKeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJam11.requestFocus();
        }
    }//GEN-LAST:event_TjamKeKeyPressed

    private void cmbJam11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam11MouseReleased
        AutoCompleteDecorator.decorate(cmbJam11);
    }//GEN-LAST:event_cmbJam11MouseReleased

    private void cmbMnt11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt11MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt11);
    }//GEN-LAST:event_cmbMnt11MouseReleased

    private void cmbDtk11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk11MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk11);
    }//GEN-LAST:event_cmbDtk11MouseReleased

    private void TtdKala4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdKala4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnadiKala4.requestFocus();
        }
    }//GEN-LAST:event_TtdKala4KeyPressed

    private void TnadiKala4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKala4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TsuhuKala4.requestFocus();
        }
    }//GEN-LAST:event_TnadiKala4KeyPressed

    private void TsuhuKala4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKala4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TTinggiFundus.requestFocus();
        }
    }//GEN-LAST:event_TsuhuKala4KeyPressed

    private void TkontraksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkontraksiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Turin.requestFocus();
        }
    }//GEN-LAST:event_TkontraksiKeyPressed

    private void TTinggiFundusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTinggiFundusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkontraksi.requestFocus();
        }
    }//GEN-LAST:event_TTinggiFundusKeyPressed

    private void TurinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TurinKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdarahYang.requestFocus();
        }
    }//GEN-LAST:event_TurinKeyPressed

    private void TdarahYangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdarahYangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnTambahKala4.requestFocus();
        }
    }//GEN-LAST:event_TdarahYangKeyPressed

    private void tbPemantauanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemantauanMouseClicked
        if (tabMode10.getRowCount() != 0) {
            try {
                getDataPemantauan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPemantauanMouseClicked

    private void tbPemantauanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemantauanKeyPressed
        if (tabMode10.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getDataPemantauan();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPemantauanKeyPressed

    private void BtnBaruKala4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruKala4ActionPerformed
        emptTeksPemantauanKala4();
        urutkanDataPemantauanKala4();
    }//GEN-LAST:event_BtnBaruKala4ActionPerformed

    private void BtnTambahKala4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahKala4ActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            urutData = "1";
            if (tabMode10.getRowCount() > 0) {
                int max = 0;
                for (int i = 0; i < tabMode10.getRowCount(); i++) {
                    int nilai = Integer.parseInt(tabMode10.getValueAt(i, 1).toString());
                    if (nilai > max) {
                        max = nilai;
                    }
                }
                urutData = String.valueOf(max + 1);
            }
            
            tabMode10.addRow(new String[]{TNoRw.getText(), urutData, TjamKe.getText(),
                cmbJam11.getSelectedItem() + ":" + cmbMnt11.getSelectedItem() + ":" + cmbDtk11.getSelectedItem(), TtdKala4.getText(), 
                TnadiKala4.getText(), TsuhuKala4.getText(), TTinggiFundus.getText(), Tkontraksi.getText(), Turin.getText(), TdarahYang.getText(), 
                Sequel.cariIsi("select now()")
            });
            
            BtnBaruKala4ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTambahKala4ActionPerformed

    private void BtnHapusKala4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusKala4ActionPerformed
        if (tbPemantauan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data pemantauan persalinan kala 4 yang bisa dihapus..!!");
        } else {
            if (tbPemantauan.getSelectedRow() > -1) {
                int row = tbPemantauan.convertRowIndexToModel(tbPemantauan.getSelectedRow());
                tabMode10.removeRow(row);
                BtnBaruKala4ActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel pemantauan persalinan kala 4..!!");
                tbPemantauan.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusKala4ActionPerformed

    private void BtnGantiKala4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiKala4ActionPerformed
        if (tbPemantauan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data pemantauan persalinan kala 4 yang bisa diganti..!!");
        } else {
            if (tbPemantauan.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else {
                    int row = tbPemantauan.convertRowIndexToModel(tbPemantauan.getSelectedRow());
                    tabMode10.setValueAt(TNoRw.getText(), row, 0);
                    tabMode10.setValueAt(urutanKe, row, 1);
                    tabMode10.setValueAt(TjamKe.getText(), row, 2);
                    tabMode10.setValueAt(cmbJam11.getSelectedItem() + ":" + cmbMnt11.getSelectedItem() + ":" + cmbDtk11.getSelectedItem(), row, 3);
                    tabMode10.setValueAt(TtdKala4.getText(), row, 4);
                    tabMode10.setValueAt(TnadiKala4.getText(), row, 5);
                    tabMode10.setValueAt(TsuhuKala4.getText(), row, 6);
                    tabMode10.setValueAt(TTinggiFundus.getText(), row, 7);
                    tabMode10.setValueAt(Tkontraksi.getText(), row, 8);
                    tabMode10.setValueAt(Turin.getText(), row, 9);
                    tabMode10.setValueAt(TdarahYang.getText(), row, 10);
                    tabMode10.setValueAt(Sequel.cariIsi("select now()"), row, 11);
                    
                    BtnBaruKala4ActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel pemantauan persalinan kala 4..!!");
                tbPemantauan.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiKala4ActionPerformed

    private void TmasalahKala4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmasalahKala4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TpenataKala4.requestFocus();
        }
    }//GEN-LAST:event_TmasalahKala4KeyPressed

    private void TpenataKala4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpenataKala4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ThasilKala4.requestFocus();
        }
    }//GEN-LAST:event_TpenataKala4KeyPressed

    private void BtnBidanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBidanActionPerformed
        akses.setform("RMPartografPersalinan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnBidanActionPerformed

    private void TketServikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketServikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnTambahServik.requestFocus();
        }
    }//GEN-LAST:event_TketServikKeyPressed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        BtnSimpanActionPerformed(null);
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
        BtnSimpanKeyPressed(null);
    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void BtnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal1ActionPerformed
        BtnBatalActionPerformed(null);
    }//GEN-LAST:event_BtnBatal1ActionPerformed

    private void BtnGanti1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGanti1ActionPerformed
        BtnGantiActionPerformed(null);
    }//GEN-LAST:event_BtnGanti1ActionPerformed

    private void BtnGanti1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGanti1KeyPressed
        BtnGantiKeyPressed(null);
    }//GEN-LAST:event_BtnGanti1KeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar2ActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbPartograf.getSelectedRow() > -1) {
            if (akses.getkode().equals(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 10).toString()) || akses.getadmin() == true) {
                hapus();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Maaf, data partograf persalinan pasien ini hanya bisa dihapus oleh "
                        + tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 11).toString() + " ...!!");
                TabPartograf.setSelectedIndex(2);
                tbPartograf.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            TabPartograf.setSelectedIndex(2);
            tbPartograf.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        BtnHapusActionPerformed(null);
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (Sequel.cariInteger("select count(-1) from partograf_persalinan where no_rawat='" + TNoRw.getText() + "'") > 0
                || TNoRw.getText().trim().equals("")) {
            TabPartograf.setSelectedIndex(2);
        } else {
            TabPartograf.setSelectedIndex(0);
        }
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void TabPartografMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPartografMouseClicked
        if (TabPartograf.getSelectedIndex() == 2) {
            tampil();
        }
    }//GEN-LAST:event_TabPartografMouseClicked

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGantiActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
    }//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Ppasien");
        } else if (nipBidan.equals("")) {
            Valid.textKosong(TnmBidan, "nama bidan");
            BtnBidan.requestFocus();
        } else {
            if (cmbKetuban.getSelectedIndex() == 1) {
                jamKetuban = cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem();
            } else {
                jamKetuban = "00:00:00";
            }

            if (Sequel.mengedittf("partograf_persalinan", "no_rawat=?", "ruang_rawat=?, gravida=?, paritas=?, abortus=?, tgl_masuk=?, jam_masuk=?, ketuban_pecah=?, jam_ketuban=?", 9, new String[]{
                TrgRawat.getText(), Tgravida.getText(), Tparitas.getText(), Tabortus.getText(), Valid.SetTgl(TtglMasuk.getSelectedItem() + ""),
                cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                cmbKetuban.getSelectedItem().toString(), jamKetuban,
                tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString()
            }) == true) {
                if (tbDjj.getRowCount() != 0) {
                    Sequel.queryu("delete from partograf_djj where no_rawat='" + TNoRw.getText() + "'");
                    for (i = 0; i < tbDjj.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_djj",
                                "'" + tbDjj.getValueAt(i, 0).toString() + "','"
                                + tbDjj.getValueAt(i, 1).toString() + "','"
                                + tbDjj.getValueAt(i, 2).toString() + "','"
                                + tbDjj.getValueAt(i, 3).toString() + "','"
                                + tbDjj.getValueAt(i, 4).toString() + "'", "Data DJJ");
                    }
                }

                if (tbAirKetuban.getRowCount() != 0) {
                    Sequel.queryu("delete from partograf_air_ketuban where no_rawat='" + TNoRw.getText() + "'");
                    for (i = 0; i < tbAirKetuban.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_air_ketuban",
                                "'" + tbAirKetuban.getValueAt(i, 0).toString() + "','"
                                + tbAirKetuban.getValueAt(i, 1).toString() + "','"
                                + tbAirKetuban.getValueAt(i, 2).toString() + "','"
                                + tbAirKetuban.getValueAt(i, 3).toString() + "','"
                                + tbAirKetuban.getValueAt(i, 4).toString() + "'", "Data Air Ketuban");
                    }
                }

                if (tbServik.getRowCount() != 0) {
                    Sequel.queryu("delete from partograf_pembukaan_serviks where no_rawat='" + TNoRw.getText() + "'");
                    for (i = 0; i < tbServik.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_pembukaan_serviks",
                                "'" + tbServik.getValueAt(i, 0).toString() + "','"
                                + tbServik.getValueAt(i, 1).toString() + "','"
                                + tbServik.getValueAt(i, 2).toString() + "','"
                                + tbServik.getValueAt(i, 3).toString() + "','"
                                + tbServik.getValueAt(i, 4).toString() + "','"
                                + tbServik.getValueAt(i, 5).toString() + "','"
                                + tbServik.getValueAt(i, 6).toString() + "','"
                                + tbServik.getValueAt(i, 7).toString() + "','"
                                + tbServik.getValueAt(i, 8).toString() + "'", "Data Pembukaan Serviks");
                    }
                }

                if (tbKontraksi.getRowCount() != 0) {
                    Sequel.queryu("delete from partograf_kontraksi where no_rawat='" + TNoRw.getText() + "'");
                    for (i = 0; i < tbKontraksi.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_kontraksi",
                                "'" + tbKontraksi.getValueAt(i, 0).toString() + "','"
                                + tbKontraksi.getValueAt(i, 1).toString() + "','"
                                + tbKontraksi.getValueAt(i, 2).toString() + "','"
                                + tbKontraksi.getValueAt(i, 3).toString() + "'", "Data Kontraksi");
                    }
                }

                if (tbOksitosin.getRowCount() != 0) {
                    Sequel.queryu("delete from partograf_oksitosin where no_rawat='" + TNoRw.getText() + "'");
                    for (i = 0; i < tbOksitosin.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_oksitosin",
                                "'" + tbOksitosin.getValueAt(i, 0).toString() + "','"
                                + tbOksitosin.getValueAt(i, 1).toString() + "','"
                                + tbOksitosin.getValueAt(i, 2).toString() + "','"
                                + tbOksitosin.getValueAt(i, 3).toString() + "'", "Data Oksitosin");
                    }
                }

                if (tbObat.getRowCount() != 0) {
                    Sequel.queryu("delete from partograf_obat_cairan where no_rawat='" + TNoRw.getText() + "'");
                    for (i = 0; i < tbObat.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_obat_cairan",
                                "'" + tbObat.getValueAt(i, 0).toString() + "','"
                                + tbObat.getValueAt(i, 1).toString() + "','"
                                + tbObat.getValueAt(i, 2).toString() + "'", "Data Obat & Cairan");
                    }
                }

                if (tbNadi.getRowCount() != 0) {
                    Sequel.queryu("delete from partograf_nadi_tensi where no_rawat='" + TNoRw.getText() + "'");
                    for (i = 0; i < tbNadi.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_nadi_tensi",
                                "'" + tbNadi.getValueAt(i, 0).toString() + "','"
                                + tbNadi.getValueAt(i, 1).toString() + "','"
                                + tbNadi.getValueAt(i, 2).toString() + "','"
                                + tbNadi.getValueAt(i, 3).toString() + "','"
                                + tbNadi.getValueAt(i, 4).toString() + "','"
                                + tbNadi.getValueAt(i, 5).toString() + "'", "Data Nadi");
                    }
                }

                if (tbSuhu.getRowCount() != 0) {
                    Sequel.queryu("delete from partograf_suhu where no_rawat='" + TNoRw.getText() + "'");
                    for (i = 0; i < tbSuhu.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_suhu",
                                "'" + tbSuhu.getValueAt(i, 0).toString() + "','"
                                + tbSuhu.getValueAt(i, 1).toString() + "','"
                                + tbSuhu.getValueAt(i, 2).toString() + "','"
                                + tbSuhu.getValueAt(i, 3).toString() + "'", "Data Suhu");
                    }
                }

                if (tbUrin.getRowCount() != 0) {
                    Sequel.queryu("delete from partograf_urin where no_rawat='" + TNoRw.getText() + "'");
                    for (i = 0; i < tbUrin.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_urin",
                                "'" + tbUrin.getValueAt(i, 0).toString() + "','"
                                + tbUrin.getValueAt(i, 1).toString() + "','"
                                + tbUrin.getValueAt(i, 2).toString() + "','"
                                + tbUrin.getValueAt(i, 3).toString() + "','"
                                + tbUrin.getValueAt(i, 4).toString() + "','"
                                + tbUrin.getValueAt(i, 5).toString() + "'", "Data Urin");
                    }
                }

                cekData();
                if (cmbPemberianOksi.getSelectedIndex() == 1) {
                    dataKala3A = Tkala3PemberianOksi.getText();
                } else if (cmbPemberianOksi.getSelectedIndex() == 2 || cmbPemberianOksi.getSelectedIndex() == 0) {
                    dataKala3B = Tkala3PemberianOksi.getText();
                } else if (cmbPemberianOksi.getSelectedIndex() == 3) {
                    dataKala3C = Tkala3PemberianOksi.getText();
                }

                if (cmbBayiLahir.getSelectedIndex() == 6) {
                    sebutkan38a = TbayiSebutkan.getText();
                } else if (cmbBayiAsfeksia.getSelectedIndex() == 6) {
                    sebutkan38b = TbayiSebutkan.getText();
                }

                if (Sequel.mengedittf("partograf_catatan_persalinan", "no_rawat=?", "cttn_tanggal=?, cttn_nip_bidan=?, cttn_tempat_persalinan=?, cttn_ket_lainya_tmpt=?, "
                        + "cttn_alamat_tempat=?, cttn_rujukan=?, cttn_alasan_merujuk=?, cttn_tempat_rujukan=?, cttn_pendamping_bidan=?, cttn_pendamping_teman=?, cttn_pendamping_klg=?, "
                        + "cttn_pendamping_suami=?, cttn_pendamping_dukun=?, cttn_pendamping_tidak_ada=?, cttn_masalah_gawat=?, cttn_masalah_perdarahan=?, cttn_masalah_infeksi=?, "
                        + "cttn_masalah_peb=?, cttn_masalah_hdk=?, cttn_masalah_bidan_pmtct=?, cttn_masalah_lainya=?, cttn_masalah_ket_lainya=?, kala1_partogram=?, kala1_masalah_lain=?, "
                        + "kala1_penatalaksanaan=?, kala1_hasilnya=?, kala2_episiotomi=?, kala2_episiotomi_indikasi=?, kala2_pendamping_suami=?, kala2_pendamping_klg=?, "
                        + "kala2_pendamping_teman=?, kala2_pendamping_dukun=?, kala2_pendamping_tidak_ada=?, kala2_gawat_janin=?, kala2_ket_ya_gawat_janin=?, kala2_ket_hasil_pemantauan=?, "
                        + "kala2_distosia_bahu=?, kala2_ket_ya_distosia_bahu=?, kala2_masalah_lain_hasilnya=?, kala3_inisiasi=?, kala3_ket_tidak_alasan=?, kala3_lama=?, "
                        + "kala3_pemberian_oksitosin=?, kala3_pemberian_oksitosin_ya=?, kala3_pemberian_oksitosin_tidak=?, kala3_pemberian_oksitosin_penjepitan=?, kala3_pemberian_ulang=?, "
                        + "kala3_pemberian_ulang_ya=?, kala3_penegangan_tali=?, kala3_penegangan_tali_tidak=?, kala3_masase_fundus=?, kala3_masase_fundus_tidak=?, kala3_plasenta_lahir=?, "
                        + "kala3_plasenta_lahir_tidak_a=?, kala3_plasenta_lahir_tidak_b=?, kala3_plasenta_tidak_lahir=?, kala3_plasenta_tidak_lahir_ya=?, kala3_laserasi=?, "
                        + "kala3_laserasi_ya=?, kala3_laserasi_perineum=?, kala3_laserasi_perineum_penjahitan=?, kala3_laserasi_perineum_penjahitan_tidak=?, kala3_atonia_uteri=?, "
                        + "kala3_atonia_uteri_ya=?, kala3_jumlah_darah=?, kala3_masalah=?, kala3_penatalaksanaan_masalah=?, kala3_hasilnya=?, bayi_bb=?, bayi_pb=?, bayi_jenkel=?, "
                        + "bayi_penilaian=?, bayi_bayi_lahir=?, bayi_bayi_lahir_normal=?, bayi_bayi_lahir_asfiksia_tindakan=?, bayi_bayi_lahir_asfiksia_tindakan_sebutkan=?, "
                        + "bayi_bayi_lahir_cacat_bawaan_sebutkan=?, bayi_bayi_lahir_hipotermia_a=?, bayi_bayi_lahir_hipotermia_b=?, bayi_bayi_lahir_hipotermia_c=?, bayi_pemberian_asi=?, "
                        + "bayi_pemberian_asi_ya=?, bayi_pemberian_asi_tidak_alasan=?, bayi_masalah_lain=?, bayi_hasilnya=?", 86, new String[]{
                            Valid.SetTgl(TtglCatatan.getSelectedItem() + ""), nipBidan, cmbTmptPersalinan.getSelectedItem().toString(), TtmptPersalinanLain.getText(),
                            TalmtTmpPersalinan.getText(), cmbCttnRujuk.getSelectedItem().toString(), TalasanMerujuk.getText(), TtmptRujukan.getText(), bidan8, teman8,
                            klg8, suami8, dukun8, tdkAda8, gawat9, perdarahan9, infeksi9, peb9, hdk9, bidan9, lainya9, TmasalahLain.getText(), cmbPartogram.getSelectedItem().toString(),
                            Tkala1MasalahLain.getText(), Tkala1Penata.getText(), Tkala1Hasilnya.getText(), cmbEpisiotomi.getSelectedItem().toString(), Tkala2YaIndikasi.getText(),
                            suami15, klg15, teman15, dukun15, tdkAda15, cmbGawatJanin.getSelectedItem().toString(), Tkala2YaTindakanGawat.getText(), Tkala2Pemantauan.getText(),
                            cmbDistosia.getSelectedItem().toString(), Tkala2YaTindakanDisto.getText(), Tkala2MasalahLain.getText(), cmbInisiasi.getSelectedItem().toString(),
                            Tkala3Tidak.getText(), Tkala3Lama.getText(), cmbPemberianOksi.getSelectedItem().toString(), dataKala3A, dataKala3B, dataKala3C,
                            cmbPemberianUlang.getSelectedItem().toString(), Tkala3PemberianUlang.getText(), cmbPenegangan.getSelectedItem().toString(), Tkala3Penegangan.getText(),
                            cmbMasase.getSelectedItem().toString(), Tkala3Masase.getText(), cmbPlasenta25.getSelectedItem().toString(), Tkala3Plasenta25A.getText(),
                            Tkala3Plasenta25B.getText(), cmbPlasenta26.getSelectedItem().toString(), Tkala3Plasenta26.getText(), cmbLaserasi.getSelectedItem().toString(),
                            Tkala3Laserasi.getText(), cmbJika.getSelectedItem().toString(), cmbTindakan.getSelectedItem().toString(), Tkala3Alasan.getText(),
                            cmbAtonia.getSelectedItem().toString(), Tkala3Atonia.getText(), Tkala3Jumlah.getText(), Tkala3Masalah.getText(), Tkala3Penata.getText(),
                            Tkala3Hasilnya.getText(), TbayiBB.getText(), TbayiPB.getText(), cmbJenkel.getSelectedItem().toString(), cmbPenilaian.getSelectedItem().toString(),
                            cmbBayiLahir.getSelectedItem().toString(), cmbBayiNormal.getSelectedItem().toString(), cmbBayiAsfeksia.getSelectedItem().toString(),
                            sebutkan38b, sebutkan38a, TbayiTindakanA.getText(), TbayiTindakanB.getText(), TbayiTindakanC.getText(), cmbPemberianAsi.getSelectedItem().toString(),
                            TbayiYaPemberian.getText(), TbayiTidakAlasan.getText(), TbayiMasalah.getText(), TbayiHasilnya.getText(),
                            tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString()
                        }) == true) {
                    System.out.println("Proses ganti data partograf catatan persalinan berhasil disimpan,...!!");
                }

                if (tbPemantauan.getRowCount() != 0) {
                    Sequel.queryu("delete from partograf_kala_4 where no_rawat='" + TNoRw.getText() + "'");
                    for (i = 0; i < tbPemantauan.getRowCount(); i++) {
                        Sequel.menyimpanIgnore("partograf_kala_4",
                                "'" + tbPemantauan.getValueAt(i, 0).toString() + "','"
                                + tbPemantauan.getValueAt(i, 1).toString() + "','"
                                + tbPemantauan.getValueAt(i, 2).toString() + "','"
                                + tbPemantauan.getValueAt(i, 3).toString() + "','"
                                + tbPemantauan.getValueAt(i, 4).toString() + "','"
                                + tbPemantauan.getValueAt(i, 5).toString() + "','"
                                + tbPemantauan.getValueAt(i, 6).toString() + "','"
                                + tbPemantauan.getValueAt(i, 7).toString() + "','"
                                + tbPemantauan.getValueAt(i, 8).toString() + "','"
                                + tbPemantauan.getValueAt(i, 9).toString() + "','"
                                + tbPemantauan.getValueAt(i, 10).toString() + "','"
                                + tbPemantauan.getValueAt(i, 11).toString() + "'", "Data Pemantauan Persalinan Kala 4");
                    }
                }

                if (Sequel.mengedittf("partograf_pemantauan_kala4", "no_rawat=?", "masalah=?, penatalaksanaan=?, hasilnya=?", 4, new String[]{
                    TmasalahKala4.getText(), TpenataKala4.getText(), ThasilKala4.getText(),
                    tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString()
                }) == true) {
                    System.out.println("Proses ganti data partograf pemantauan kala 4 berhasil disimpan,...!!");
                }

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Partograf Persalinan", "Ganti");
                TCari.setText(TNoRw.getText());
                TabPartograf.setSelectedIndex(2);
                tampil();
                emptTeks();
            }
        }
    }//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakActionPerformed
        if (tbPartograf.getSelectedRow() > -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {
                Valid.panggilUrlRME("/partograf_persalinan/partograf.php?no_rawat=" + TNoRw.getText());
                TabPartograf.setSelectedIndex(2);
                tampil();
                emptTeks();
            } catch (Exception ex) {
                System.out.println("Notifikasi : " + ex);
            }
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            TabPartograf.setSelectedIndex(2);
            tbPartograf.requestFocus();
        }
    }//GEN-LAST:event_BtnCetakActionPerformed

    private void BtnCetak1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetak1ActionPerformed
        BtnCetakActionPerformed(null);
    }//GEN-LAST:event_BtnCetak1ActionPerformed

    private void BtnCetak2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetak2ActionPerformed
        BtnCetakActionPerformed(null);
    }//GEN-LAST:event_BtnCetak2ActionPerformed

    private void TjedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjedaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdjj.requestFocus();
        }
    }//GEN-LAST:event_TjedaKeyPressed

    private void ToksitosinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ToksitosinKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttetes.requestFocus();
        }
    }//GEN-LAST:event_ToksitosinKeyPressed

    private void cmbKetubanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKetubanActionPerformed
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);

        if (cmbKetuban.getSelectedIndex() == 1) {
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
            cmbJam1.requestFocus();
        } else {
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
        }
    }//GEN-LAST:event_cmbKetubanActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPartografPersalinan dialog = new RMPartografPersalinan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBaruAKM;
    private widget.Button BtnBaruDjj;
    private widget.Button BtnBaruKala4;
    private widget.Button BtnBaruKontraksi;
    private widget.Button BtnBaruNadi;
    private widget.Button BtnBaruObat;
    private widget.Button BtnBaruOksi;
    private widget.Button BtnBaruServik;
    private widget.Button BtnBaruSuhu;
    private widget.Button BtnBaruUrin;
    private widget.Button BtnBatal;
    private widget.Button BtnBatal1;
    private widget.Button BtnBidan;
    private widget.Button BtnCari;
    private widget.Button BtnCetak;
    private widget.Button BtnCetak1;
    private widget.Button BtnCetak2;
    private widget.Button BtnGanti;
    private widget.Button BtnGanti1;
    private widget.Button BtnGantiAKM;
    private widget.Button BtnGantiDjj;
    private widget.Button BtnGantiKala4;
    private widget.Button BtnGantiKontraksi;
    private widget.Button BtnGantiNadi;
    private widget.Button BtnGantiObat;
    private widget.Button BtnGantiOksi;
    private widget.Button BtnGantiServik;
    private widget.Button BtnGantiSuhu;
    private widget.Button BtnGantiUrin;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnHapusAKM;
    private widget.Button BtnHapusDjj;
    private widget.Button BtnHapusKala4;
    private widget.Button BtnHapusKontraksi;
    private widget.Button BtnHapusNadi;
    private widget.Button BtnHapusObat;
    private widget.Button BtnHapusOksi;
    private widget.Button BtnHapusServik;
    private widget.Button BtnHapusSuhu;
    private widget.Button BtnHapusUrin;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluar2;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnTambahAKM;
    private widget.Button BtnTambahDjj;
    private widget.Button BtnTambahKala4;
    private widget.Button BtnTambahKontraksi;
    private widget.Button BtnTambahNadi;
    private widget.Button BtnTambahObat;
    private widget.Button BtnTambahOksi;
    private widget.Button BtnTambahServik;
    private widget.Button BtnTambahSuhu;
    private widget.Button BtnTambahUrin;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TTinggiFundus;
    private javax.swing.JTabbedPane TabPartograf;
    private widget.TextBox Tabortus;
    private widget.TextBox TalasanMerujuk;
    private widget.TextBox TalmtTmpPersalinan;
    private widget.TextBox TbayiBB;
    private widget.TextBox TbayiHasilnya;
    private widget.TextBox TbayiMasalah;
    private widget.TextBox TbayiPB;
    private widget.TextBox TbayiSebutkan;
    private widget.TextBox TbayiTidakAlasan;
    private widget.TextBox TbayiTindakanA;
    private widget.TextBox TbayiTindakanB;
    private widget.TextBox TbayiTindakanC;
    private widget.TextBox TbayiYaPemberian;
    private widget.TextBox TdarahYang;
    private widget.TextBox Tdistol;
    private widget.TextBox Tdjj;
    private widget.TextBox Tgravida;
    private widget.TextBox ThasilKala4;
    private widget.TextBox TjamKe;
    private widget.TextBox Tjeda;
    private widget.TextBox Tkala1Hasilnya;
    private widget.TextBox Tkala1MasalahLain;
    private widget.TextBox Tkala1Penata;
    private widget.TextBox Tkala2MasalahLain;
    private widget.TextBox Tkala2Pemantauan;
    private widget.TextBox Tkala2YaIndikasi;
    private widget.TextBox Tkala2YaTindakanDisto;
    private widget.TextBox Tkala2YaTindakanGawat;
    private widget.TextBox Tkala3Alasan;
    private widget.TextBox Tkala3Atonia;
    private widget.TextBox Tkala3Hasilnya;
    private widget.TextBox Tkala3Jumlah;
    private widget.TextBox Tkala3Lama;
    private widget.TextBox Tkala3Laserasi;
    private widget.TextBox Tkala3Masalah;
    private widget.TextBox Tkala3Masase;
    private widget.TextBox Tkala3PemberianOksi;
    private widget.TextBox Tkala3PemberianUlang;
    private widget.TextBox Tkala3Penata;
    private widget.TextBox Tkala3Penegangan;
    private widget.TextBox Tkala3Plasenta25A;
    private widget.TextBox Tkala3Plasenta25B;
    private widget.TextBox Tkala3Plasenta26;
    private widget.TextBox Tkala3Tidak;
    private widget.TextBox TketServik;
    private widget.TextBox Tkontraksi;
    private widget.TextBox TmasalahKala4;
    private widget.TextBox TmasalahLain;
    private widget.TextBox Tnadi;
    private widget.TextBox TnadiKala4;
    private widget.TextBox TnmBidan;
    private widget.TextBox TobatCairan;
    private widget.TextBox Toksitosin;
    private widget.TextBox Tparitas;
    private widget.TextBox TpenataKala4;
    private widget.TextBox TrgRawat;
    private widget.TextBox Tsistol;
    private widget.TextBox Tsuhu;
    private widget.TextBox TsuhuKala4;
    private widget.TextBox TtdKala4;
    private widget.TextBox Ttetes;
    private widget.Tanggal TtglCatatan;
    private widget.Tanggal TtglMasuk;
    private widget.TextBox TtmptPersalinanLain;
    private widget.TextBox TtmptRujukan;
    private widget.TextBox Turin;
    private widget.TextBox Tvolume;
    public widget.CekBox chkBidan8;
    public widget.CekBox chkBidan9;
    public widget.CekBox chkDukun8;
    public widget.CekBox chkGawat9;
    public widget.CekBox chkHdk9;
    public widget.CekBox chkInfeksi9;
    public widget.CekBox chkKala2Dukun;
    public widget.CekBox chkKala2Klg;
    public widget.CekBox chkKala2Suami;
    public widget.CekBox chkKala2Teman;
    public widget.CekBox chkKala2TidakAda;
    public widget.CekBox chkKlg8;
    public widget.CekBox chkLainya9;
    public widget.CekBox chkPeb9;
    public widget.CekBox chkPembukaan;
    public widget.CekBox chkPerdarahan9;
    public widget.CekBox chkSuami8;
    public widget.CekBox chkTeman8;
    public widget.CekBox chkTidakAda8;
    public widget.CekBox chkTurunya;
    private widget.ComboBox cmbAirKetuban;
    private widget.ComboBox cmbAseton;
    private widget.ComboBox cmbAtonia;
    private widget.ComboBox cmbBayiAsfeksia;
    private widget.ComboBox cmbBayiLahir;
    private widget.ComboBox cmbBayiNormal;
    private widget.ComboBox cmbCm;
    private widget.ComboBox cmbCttnRujuk;
    private widget.ComboBox cmbDetik;
    private widget.ComboBox cmbDistosia;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk10;
    private widget.ComboBox cmbDtk11;
    private widget.ComboBox cmbDtk4;
    private widget.ComboBox cmbDtk9;
    private widget.ComboBox cmbEpisiotomi;
    private widget.ComboBox cmbGawatJanin;
    private widget.ComboBox cmbInisiasi;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam10;
    private widget.ComboBox cmbJam11;
    private widget.ComboBox cmbJam4;
    private widget.ComboBox cmbJam9;
    private widget.ComboBox cmbJenkel;
    private widget.ComboBox cmbJika;
    private widget.ComboBox cmbKetuban;
    private widget.ComboBox cmbLajur;
    private widget.ComboBox cmbLaserasi;
    private widget.ComboBox cmbMasase;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt10;
    private widget.ComboBox cmbMnt11;
    private widget.ComboBox cmbMnt4;
    private widget.ComboBox cmbMnt9;
    private widget.ComboBox cmbMulase;
    private widget.ComboBox cmbPartogram;
    private widget.ComboBox cmbPemberianAsi;
    private widget.ComboBox cmbPemberianOksi;
    private widget.ComboBox cmbPemberianUlang;
    private widget.ComboBox cmbPenegangan;
    private widget.ComboBox cmbPenilaian;
    private widget.ComboBox cmbPlasenta25;
    private widget.ComboBox cmbPlasenta26;
    private widget.ComboBox cmbProtein;
    private widget.ComboBox cmbTindakan;
    private widget.ComboBox cmbTmptPersalinan;
    private widget.ComboBox cmbWaktuKe;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame20;
    private widget.InternalFrame internalFrame21;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel12;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel13;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel32;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel5;
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
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel9;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPanel jPanel3;
    private widget.Label labelKetPemberian;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbAirKetuban;
    private widget.Table tbDjj;
    private widget.Table tbKontraksi;
    private widget.Table tbNadi;
    private widget.Table tbObat;
    private widget.Table tbOksitosin;
    private widget.Table tbPartograf;
    private widget.Table tbPemantauan;
    private widget.Table tbServik;
    private widget.Table tbSuhu;
    private widget.Table tbUrin;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT pp.*, p.no_rkm_medis, p.nm_pasien, if(pcp.no_rawat is null,'-',pcp.cttn_nip_bidan) nipBidan, "
                    + "if(pcp.no_rawat is null,'-',pg.nama) nmBidan, date_format(pp.tgl_masuk,'%d-%m-%Y') tglmasuk, date_format(pp.jam_masuk,'%H:%i') jammasuk, "
                    + "if(ppk.no_rawat is null,'',ppk.masalah) mslah, if(ppk.no_rawat is null,'',ppk.penatalaksanaan) pnata, if(ppk.no_rawat is null,'',ppk.hasilnya) hasil "
                    + "from partograf_persalinan pp inner join reg_periksa rp on rp.no_rawat=pp.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis =rp.no_rkm_medis "
                    + "left join partograf_catatan_persalinan pcp on pcp.no_rawat=pp.no_rawat "
                    + "left join partograf_pemantauan_kala4 ppk on ppk.no_rawat=pp.no_rawat "
                    + "left join pegawai pg on pg.nik=pcp.cttn_nip_bidan where "
                    + "date(pp.waktu_simpan) between ? and ? and pp.no_rawat like ? or "
                    + "date(pp.waktu_simpan) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(pp.waktu_simpan) between ? and ? and p.nm_pasien like ? or "
                    + "date(pp.waktu_simpan) between ? and ? and if(pcp.no_rawat is null,'-',pcp.cttn_nip_bidan) like ? or "
                    + "date(pp.waktu_simpan) between ? and ? and if(pcp.no_rawat is null,'-',pg.nama) like ? order by pp.waktu_simpan desc");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText() + "%");
                ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText() + "%");
                ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText() + "%");
                ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText() + "%");
                ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText() + "%");
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{                        
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("ruang_rawat"),
                        rs.getString("gravida"),
                        rs.getString("paritas"),
                        rs.getString("abortus"),
                        rs.getString("tglmasuk"),
                        rs.getString("jammasuk") + " Wita",
                        rs.getString("ketuban_pecah"),
                        rs.getString("nipBidan"),
                        rs.getString("nmBidan"),
                        rs.getString("tgl_masuk"),
                        rs.getString("jam_masuk"),
                        rs.getString("jam_ketuban"),
                        rs.getString("mslah"),
                        rs.getString("pnata"),
                        rs.getString("hasil"),
                        rs.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampil() : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }
    
    private void emptTeksDJJ() {
        Tdjj.setText("");
        tbDjj.clearSelection();

        if (tabMode1.getRowCount() != 0) {
            int viewRow = tbDjj.getRowCount() - 1;
            int modelRow = tbDjj.convertRowIndexToModel(viewRow);
            Tjeda.setText(tabMode1.getValueAt(modelRow, 2).toString());
        } else {
            Tjeda.setText("");
        }
    }
    
    private void emptTeksAirKetuban() {
        cmbAirKetuban.setSelectedIndex(0);
        cmbMulase.setSelectedIndex(0);
        tbAirKetuban.clearSelection();
    }
    
    private void emptTeksServik() {
        cmbWaktuKe.setSelectedIndex(0);
        cmbJam4.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt4.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk4.setSelectedIndex(0);
        cmbCm.setSelectedIndex(0);
        chkPembukaan.setSelected(false);
        chkTurunya.setSelected(false);
        TketServik.setText("");
        tbServik.clearSelection();
    }
    
    private void emptTeksKontaksi() {
        cmbLajur.setSelectedIndex(0);
        cmbDetik.setSelectedIndex(0);
        tbKontraksi.clearSelection();
    }
    
    private void emptTeksOksitosin() {
        Toksitosin.setText("");
        Ttetes.setText("");
        tbOksitosin.clearSelection();
    }
    
    private void emptTeksObat() {
        TobatCairan.setText("");
        tbObat.clearSelection();
    }
    
    private void emptTeksNadiTensi() {
        Tnadi.setText("");
        Tsistol.setText("");
        Tdistol.setText("");
        tbNadi.clearSelection();
    }
    
    private void emptTeksSuhu() {
        Tsuhu.setText("");
        cmbJam9.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt9.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk9.setSelectedIndex(0);
        tbSuhu.clearSelection();
    }
    
    private void emptTeksUrin() {
        cmbProtein.setSelectedIndex(0);
        cmbAseton.setSelectedIndex(0);
        Tvolume.setText("");
        cmbJam10.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt10.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk10.setSelectedIndex(0);
        tbUrin.clearSelection();
    }
    
    private void emptTeksPemantauanKala4() {
        TjamKe.setText("");
        cmbJam11.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt11.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk11.setSelectedIndex(0);
        TtdKala4.setText("");
        TnadiKala4.setText("");
        TsuhuKala4.setText("");
        TTinggiFundus.setText("");
        Tkontraksi.setText("");
        Turin.setText("");
        TdarahYang.setText("");
        tbPemantauan.clearSelection();
    }

    public void emptTeks() {
        Valid.tabelKosong(tabMode1);
        Valid.tabelKosong(tabMode2);
        Valid.tabelKosong(tabMode3);
        Valid.tabelKosong(tabMode4);
        Valid.tabelKosong(tabMode5);
        Valid.tabelKosong(tabMode6);
        Valid.tabelKosong(tabMode7);
        Valid.tabelKosong(tabMode8);
        Valid.tabelKosong(tabMode9);
        Valid.tabelKosong(tabMode10);
        
        Tgravida.setText("");
        Tparitas.setText("");
        Tabortus.setText("");
        TtglMasuk.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        cmbKetuban.setSelectedIndex(0);
        cmbJam1.setEnabled(false);
        cmbMnt1.setEnabled(false);
        cmbDtk1.setEnabled(false);
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        emptTeksDJJ();
        emptTeksAirKetuban();
        emptTeksServik();
        emptTeksKontaksi();
        emptTeksOksitosin();
        emptTeksObat();
        emptTeksNadiTensi();        
        emptTeksSuhu();
        emptTeksUrin();        
        TtglCatatan.setDate(new Date());
        cmbTmptPersalinan.setSelectedIndex(0);
        TtmptPersalinanLain.setText("");
        TtmptPersalinanLain.setEnabled(false);
        TalmtTmpPersalinan.setText("");
        cmbCttnRujuk.setSelectedIndex(0);
        TalasanMerujuk.setText("");
        TtmptRujukan.setText("");
        chkBidan8.setSelected(false);
        chkTeman8.setSelected(false);
        chkKlg8.setSelected(false);
        chkSuami8.setSelected(false);
        chkDukun8.setSelected(false);
        chkTidakAda8.setSelected(false);
        chkGawat9.setSelected(false);
        chkPerdarahan9.setSelected(false);
        chkHdk9.setSelected(false);
        chkInfeksi9.setSelected(false);
        chkPeb9.setSelected(false);
        chkBidan9.setSelected(false);
        chkLainya9.setSelected(false);
        TmasalahLain.setText("");
        TmasalahLain.setEnabled(false);
        cmbPartogram.setSelectedIndex(0);
        Tkala1MasalahLain.setText("");
        Tkala1Penata.setText("");
        Tkala1Hasilnya.setText("");
        cmbEpisiotomi.setSelectedIndex(0);
        Tkala2YaIndikasi.setText("");
        Tkala2YaIndikasi.setEnabled(false);
        chkKala2Suami.setSelected(false);
        chkKala2Teman.setSelected(false);
        chkKala2TidakAda.setSelected(false);
        chkKala2Klg.setSelected(false);
        chkKala2Dukun.setSelected(false);
        cmbGawatJanin.setSelectedIndex(0);
        Tkala2YaTindakanGawat.setText("");
        Tkala2YaTindakanGawat.setEnabled(false);
        Tkala2Pemantauan.setText("");
        cmbDistosia.setSelectedIndex(0);
        Tkala2YaTindakanDisto.setText("");
        Tkala2YaTindakanDisto.setEnabled(false);
        Tkala2MasalahLain.setText("");
        cmbInisiasi.setSelectedIndex(0);
        Tkala3Tidak.setText("");
        Tkala3Tidak.setEnabled(false);
        Tkala3Lama.setText("");
        cmbPemberianOksi.setSelectedIndex(0);
        Tkala3PemberianOksi.setText("");
        labelKetPemberian.setText("");
        Tkala3PemberianOksi.setEnabled(false);
        cmbPemberianUlang.setSelectedIndex(0);
        Tkala3PemberianUlang.setText("");
        Tkala3PemberianUlang.setEnabled(false);
        cmbPenegangan.setSelectedIndex(0);
        Tkala3Penegangan.setText("");
        Tkala3Penegangan.setEnabled(false);
        cmbMasase.setSelectedIndex(0);
        Tkala3Masase.setText("");
        Tkala3Masase.setEnabled(false);
        cmbPlasenta25.setSelectedIndex(0);
        Tkala3Plasenta25A.setText("");
        Tkala3Plasenta25B.setText("");
        Tkala3Plasenta25A.setEnabled(false);
        Tkala3Plasenta25B.setEnabled(false);
        cmbPlasenta26.setSelectedIndex(0);
        Tkala3Plasenta26.setText("");
        Tkala3Plasenta26.setEnabled(false);
        cmbLaserasi.setSelectedIndex(0);
        Tkala3Laserasi.setText("");
        Tkala3Laserasi.setEnabled(false);
        cmbJika.setSelectedIndex(0);
        cmbTindakan.setSelectedIndex(0);
        Tkala3Alasan.setText("");
        Tkala3Alasan.setEnabled(false);
        cmbAtonia.setSelectedIndex(0);
        Tkala3Atonia.setText("");
        Tkala3Atonia.setEnabled(false);
        Tkala3Jumlah.setText("");
        Tkala3Masalah.setText("");
        Tkala3Penata.setText("");
        Tkala3Hasilnya.setText("");
        TbayiBB.setText("");
        TbayiPB.setText("");
        cmbJenkel.setSelectedIndex(0);
        cmbPenilaian.setSelectedIndex(0);        
        cmbBayiLahir.setSelectedIndex(0);
        cmbBayiNormal.setSelectedIndex(0);
        cmbBayiAsfeksia.setSelectedIndex(0);
        TbayiSebutkan.setText("");
        TbayiTindakanA.setText("");
        TbayiTindakanB.setText("");
        TbayiTindakanC.setText("");
        cmbBayiNormal.setEnabled(false);
        cmbBayiAsfeksia.setEnabled(false);
        TbayiSebutkan.setEnabled(false);
        TbayiTindakanA.setEnabled(false);
        TbayiTindakanB.setEnabled(false);
        TbayiTindakanC.setEnabled(false);
        cmbPemberianAsi.setSelectedIndex(0);
        TbayiYaPemberian.setText("");
        TbayiTidakAlasan.setText("");
        TbayiYaPemberian.setEnabled(false);
        TbayiTidakAlasan.setEnabled(false);
        TbayiMasalah.setText("");
        TbayiHasilnya.setText("");
        emptTeksPemantauanKala4();
        TmasalahKala4.setText("");
        TpenataKala4.setText("");
        ThasilKala4.setText("");
    }

    private void getData() {
        nipBidan = "";
        if (tbPartograf.getSelectedRow() != -1) {
            TNoRw.setText(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString());
            TNoRM.setText(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 1).toString());
            TPasien.setText(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 3).toString());
            Tgravida.setText(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 4).toString());
            Tparitas.setText(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 5).toString());
            Tabortus.setText(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 6).toString());            
            Valid.SetTgl(TtglMasuk, tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 12).toString());
            cmbJam.setSelectedItem(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 13).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 13).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 13).toString().substring(6, 8));
            cmbKetuban.setSelectedItem(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 9).toString());
            cmbJam1.setSelectedItem(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 14).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 14).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 14).toString().substring(6, 8));
            nipBidan = tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 10).toString();
            TnmBidan.setText(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 11).toString());
            emptTeksDJJ();
            tampilDjj(TNoRw.getText());
            emptTeksAirKetuban();
            tampilAirKetuban(TNoRw.getText());
            emptTeksServik();
            tampilServiks(TNoRw.getText());
            emptTeksKontaksi();
            tampilKontraksi(TNoRw.getText());
            emptTeksOksitosin();
            tampilOksitosin(TNoRw.getText());
            emptTeksObat();
            tampilObatCairan(TNoRw.getText());
            emptTeksNadiTensi();
            tampilNadiTD(TNoRw.getText());
            emptTeksSuhu();
            tampilSuhu(TNoRw.getText());
            emptTeksUrin();
            tampilUrin(TNoRw.getText());
            tampilCatatan(TNoRw.getText());            

            emptTeksPemantauanKala4();
            tampilPemantauanKala4(TNoRw.getText());
            TmasalahKala4.setText(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 15).toString());
            TpenataKala4.setText(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 16).toString());
            ThasilKala4.setText(tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 17).toString());
            dataCek();
        }
    }
    
    private void getDataDjj() {
        urutanKe = "";
        if (tbDjj.getSelectedRow() != -1) {            
            Tdjj.setText(tbDjj.getValueAt(tbDjj.getSelectedRow(), 1).toString());
            Tjeda.setText(tbDjj.getValueAt(tbDjj.getSelectedRow(), 2).toString());
            urutanKe = tbDjj.getValueAt(tbDjj.getSelectedRow(), 3).toString();
        }
    }
    
    private void getDataAirKetuban() {
        urutanKe = "";
        if (tbAirKetuban.getSelectedRow() != -1) {
            urutanKe = tbAirKetuban.getValueAt(tbAirKetuban.getSelectedRow(), 1).toString();
            cmbAirKetuban.setSelectedItem(tbAirKetuban.getValueAt(tbAirKetuban.getSelectedRow(), 2).toString());
            cmbMulase.setSelectedItem(tbAirKetuban.getValueAt(tbAirKetuban.getSelectedRow(), 3).toString());            
        }
    }
    
    private void getDataServiks() {
        pembukaan = "";
        trun_kpl = "";
        urutanKe = "";
        if (tbServik.getSelectedRow() != -1) {
            cmbWaktuKe.setSelectedItem(tbServik.getValueAt(tbServik.getSelectedRow(), 1).toString());
            cmbJam4.setSelectedItem(tbServik.getValueAt(tbServik.getSelectedRow(), 2).toString().substring(0, 2));
            cmbMnt4.setSelectedItem(tbServik.getValueAt(tbServik.getSelectedRow(), 2).toString().substring(3, 5));
            cmbDtk4.setSelectedItem(tbServik.getValueAt(tbServik.getSelectedRow(), 2).toString().substring(6, 8));            
            cmbCm.setSelectedItem(tbServik.getValueAt(tbServik.getSelectedRow(), 3).toString());
            pembukaan = tbServik.getValueAt(tbServik.getSelectedRow(), 4).toString();
            trun_kpl = tbServik.getValueAt(tbServik.getSelectedRow(), 5).toString();
            TketServik.setText(tbServik.getValueAt(tbServik.getSelectedRow(), 6).toString());
            urutanKe = tbServik.getValueAt(tbServik.getSelectedRow(), 7).toString();
            
            if (pembukaan.equals("X")) {
                chkPembukaan.setSelected(true);
            } else {
                chkPembukaan.setSelected(false);
            }
            
            if (trun_kpl.equals("O")) {
                chkTurunya.setSelected(true);
            } else {
                chkTurunya.setSelected(false);
            }
        }
    }
    
    private void getDataKontraksi() {
        if (tbKontraksi.getSelectedRow() != -1) {            
            cmbLajur.setSelectedItem(tbKontraksi.getValueAt(tbKontraksi.getSelectedRow(), 1).toString());
            cmbDetik.setSelectedItem(tbKontraksi.getValueAt(tbKontraksi.getSelectedRow(), 2).toString());            
        }
    }

    private void getDataOksitosin() {
        if (tbOksitosin.getSelectedRow() != -1) {
            Toksitosin.setText(tbOksitosin.getValueAt(tbOksitosin.getSelectedRow(), 1).toString());
            Ttetes.setText(tbOksitosin.getValueAt(tbOksitosin.getSelectedRow(), 2).toString());
        }
    }
    
    private void getDataObatCairan() {
        if (tbObat.getSelectedRow() != -1) {            
            TobatCairan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
        }
    }
    
    private void getDataNadiTD() {
        urutanKe = "";
        if (tbNadi.getSelectedRow() != -1) {
            urutanKe = tbNadi.getValueAt(tbNadi.getSelectedRow(), 1).toString();
            Tnadi.setText(tbNadi.getValueAt(tbNadi.getSelectedRow(), 2).toString());
            Tsistol.setText(tbNadi.getValueAt(tbNadi.getSelectedRow(), 3).toString());
            Tdistol.setText(tbNadi.getValueAt(tbNadi.getSelectedRow(), 4).toString());
        }
    }
    
    private void getDataSuhu() {
        if (tbSuhu.getSelectedRow() != -1) {            
            cmbJam9.setSelectedItem(tbSuhu.getValueAt(tbSuhu.getSelectedRow(), 1).toString().substring(0, 2));
            cmbMnt9.setSelectedItem(tbSuhu.getValueAt(tbSuhu.getSelectedRow(), 1).toString().substring(3, 5));
            cmbDtk9.setSelectedItem(tbSuhu.getValueAt(tbSuhu.getSelectedRow(), 1).toString().substring(6, 8));            
            Tsuhu.setText(tbSuhu.getValueAt(tbSuhu.getSelectedRow(), 2).toString());
        }
    }
    
    private void getDataUrin() {
        if (tbUrin.getSelectedRow() != -1) {            
            cmbJam10.setSelectedItem(tbUrin.getValueAt(tbUrin.getSelectedRow(), 1).toString().substring(0, 2));
            cmbMnt10.setSelectedItem(tbUrin.getValueAt(tbUrin.getSelectedRow(), 1).toString().substring(3, 5));
            cmbDtk10.setSelectedItem(tbUrin.getValueAt(tbUrin.getSelectedRow(), 1).toString().substring(6, 8));  
            cmbProtein.setSelectedItem(tbUrin.getValueAt(tbUrin.getSelectedRow(), 2).toString());
            cmbAseton.setSelectedItem(tbUrin.getValueAt(tbUrin.getSelectedRow(), 3).toString());
            Tvolume.setText(tbUrin.getValueAt(tbUrin.getSelectedRow(), 2).toString());
            Tsistol.setText(tbUrin.getValueAt(tbUrin.getSelectedRow(), 4).toString());
        }
    }
    
    private void getDataPemantauan() {
        urutanKe = "";
        if (tbPemantauan.getSelectedRow() != -1) {
            urutanKe = tbPemantauan.getValueAt(tbPemantauan.getSelectedRow(), 1).toString();
            TjamKe.setText(tbPemantauan.getValueAt(tbPemantauan.getSelectedRow(), 2).toString());
            cmbJam11.setSelectedItem(tbPemantauan.getValueAt(tbPemantauan.getSelectedRow(), 3).toString().substring(0, 2));
            cmbMnt11.setSelectedItem(tbPemantauan.getValueAt(tbPemantauan.getSelectedRow(), 3).toString().substring(3, 5));
            cmbDtk11.setSelectedItem(tbPemantauan.getValueAt(tbPemantauan.getSelectedRow(), 3).toString().substring(6, 8));            
            TtdKala4.setText(tbPemantauan.getValueAt(tbPemantauan.getSelectedRow(), 4).toString());
            TnadiKala4.setText(tbPemantauan.getValueAt(tbPemantauan.getSelectedRow(), 5).toString());
            TsuhuKala4.setText(tbPemantauan.getValueAt(tbPemantauan.getSelectedRow(), 6).toString());
            TTinggiFundus.setText(tbPemantauan.getValueAt(tbPemantauan.getSelectedRow(), 7).toString());
            Tkontraksi.setText(tbPemantauan.getValueAt(tbPemantauan.getSelectedRow(), 8).toString());
            Turin.setText(tbPemantauan.getValueAt(tbPemantauan.getSelectedRow(), 9).toString());
            TdarahYang.setText(tbPemantauan.getValueAt(tbPemantauan.getSelectedRow(), 10).toString());            
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnGanti.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        
        BtnSimpan1.setEnabled(akses.getcppt());
        BtnGanti1.setEnabled(akses.getcppt());
        BtnHapus1.setEnabled(akses.getcppt());

        if (akses.getjml2() >= 1) {
//            BtnBidan.setEnabled(false);
            nipBidan = akses.getkode();
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmBidan, nipBidan);
            if (TnmBidan.getText().equals("")) {
                nipBidan = "-";
            }
        }
    }
    
    public void setData(String norwt, String norm, String nmpasien, String rgrawat) {
        TNoRw.setText(norwt);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TrgRawat.setText(rgrawat);
        TCari.setText(norwt);
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(new Date());
    }

    private void urutkanDataPemantauanKala4() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabMode10);
        tbPemantauan.setRowSorter(sorter);

        /*Penjelasan : sorter.setComparator(1 
        new RowSorter.SortKey(1
        angka 1 adalah kolom ke nya
         */
        
        sorter.setComparator(1, (o1, o2) -> {
            Integer n1 = Integer.parseInt(o1.toString());
            Integer n2 = Integer.parseInt(o2.toString());
            return n1.compareTo(n2);
        });

        sorter.setSortKeys(Arrays.asList(
                new RowSorter.SortKey(1, SortOrder.ASCENDING)
        ));

        sorter.sort();
    }
    
    private void urutkanDataDjj() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabMode1);
        tbDjj.setRowSorter(sorter);

        /*Penjelasan : sorter.setComparator(3 
        new RowSorter.SortKey(3
        angka 3 adalah kolom ke nya
         */
        
        sorter.setComparator(3, (o1, o2) -> {
            Integer n1 = Integer.parseInt(o1.toString());
            Integer n2 = Integer.parseInt(o2.toString());
            return n1.compareTo(n2);
        });

        sorter.setSortKeys(Arrays.asList(
                new RowSorter.SortKey(3, SortOrder.ASCENDING)
        ));

        sorter.sort();
    }
    
    private void urutkanDataAirKetuban() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabMode2);
        tbAirKetuban.setRowSorter(sorter);

        /*Penjelasan : sorter.setComparator(1 
        new RowSorter.SortKey(1
        angka 1 adalah kolom ke nya
         */
        
        sorter.setComparator(1, (o1, o2) -> {
            Integer n1 = Integer.parseInt(o1.toString());
            Integer n2 = Integer.parseInt(o2.toString());
            return n1.compareTo(n2);
        });

        sorter.setSortKeys(Arrays.asList(
                new RowSorter.SortKey(1, SortOrder.ASCENDING)
        ));

        sorter.sort();
    }
    
    private void urutkanDataServiks() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabMode3);
        tbServik.setRowSorter(sorter);

        /*Penjelasan : sorter.setComparator(7 
        new RowSorter.SortKey(7
        angka 7 adalah kolom ke nya
         */
        
        sorter.setComparator(7, (o1, o2) -> {
            Integer n1 = Integer.parseInt(o1.toString());
            Integer n2 = Integer.parseInt(o2.toString());
            return n1.compareTo(n2);
        });

        sorter.setSortKeys(Arrays.asList(
                new RowSorter.SortKey(7, SortOrder.ASCENDING)
        ));

        sorter.sort();
    }
    
    private void urutkanDataKontraksi() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabMode4);
        tbKontraksi.setRowSorter(sorter);

        /*Penjelasan : sorter.setComparator(1 
        new RowSorter.SortKey(1
        angka 1 adalah kolom ke nya
         */
        
        sorter.setComparator(1, (o1, o2) -> {
            Integer n1 = Integer.parseInt(o1.toString());
            Integer n2 = Integer.parseInt(o2.toString());
            return n1.compareTo(n2);
        });

        sorter.setSortKeys(Arrays.asList(
                new RowSorter.SortKey(1, SortOrder.ASCENDING)
        ));

        sorter.sort();
    }
    
    private void urutkanDataOksitosi() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.S]");
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabMode5);
        tbOksitosin.setRowSorter(sorter);

        /*Penjelasan : sorter.setComparator(3 
        new RowSorter.SortKey(3
        angka 3 adalah kolom ke nya
         */
        
        sorter.setComparator(3, (o1, o2) -> {
            LocalDateTime t1 = LocalDateTime.parse(o1.toString(), format);
            LocalDateTime t2 = LocalDateTime.parse(o2.toString(), format);
            return t1.compareTo(t2);
        });

        sorter.setSortKeys(Arrays.asList(
                new RowSorter.SortKey(3, SortOrder.ASCENDING)
        ));

        sorter.sort();
    }
    
    private void urutkanDataObat() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.S]");
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabMode6);
        tbObat.setRowSorter(sorter);

        /*Penjelasan : sorter.setComparator(2
        new RowSorter.SortKey(2
        angka 2 adalah kolom ke nya
         */
        
        sorter.setComparator(2, (o1, o2) -> {
            LocalTime t1 = LocalTime.parse(o1.toString(), format);
            LocalTime t2 = LocalTime.parse(o2.toString(), format);
            return t1.compareTo(t2);
        });

        sorter.setSortKeys(Arrays.asList(
                new RowSorter.SortKey(2, SortOrder.ASCENDING)
        ));

        sorter.sort();
    }
    
    private void urutkanDataNadi() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabMode7);
        tbNadi.setRowSorter(sorter);

        /*Penjelasan : sorter.setComparator(1 
        new RowSorter.SortKey(1
        angka 1 adalah kolom ke nya
         */
        
        sorter.setComparator(1, (o1, o2) -> {
            Integer n1 = Integer.parseInt(o1.toString());
            Integer n2 = Integer.parseInt(o2.toString());
            return n1.compareTo(n2);
        });

        sorter.setSortKeys(Arrays.asList(
                new RowSorter.SortKey(1, SortOrder.ASCENDING)
        ));

        sorter.sort();
    }
    
    private void urutkanDataSuhu() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabMode8);
        tbSuhu.setRowSorter(sorter);

        /*Penjelasan : sorter.setComparator(1 
        new RowSorter.SortKey(1
        angka 1 adalah kolom ke nya
         */
        
        sorter.setComparator(1, (o1, o2) -> {
            LocalTime t1 = LocalTime.parse(o1.toString(), format);
            LocalTime t2 = LocalTime.parse(o2.toString(), format);
            return t1.compareTo(t2);
        });

        sorter.setSortKeys(Arrays.asList(
                new RowSorter.SortKey(1, SortOrder.ASCENDING)
        ));

        sorter.sort();
    }
    
    private void urutkanDataUrin() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabMode9);
        tbUrin.setRowSorter(sorter);

        /*Penjelasan : sorter.setComparator(1 
        new RowSorter.SortKey(1
        angka 1 adalah kolom ke nya
         */
        
        sorter.setComparator(1, (o1, o2) -> {
            LocalTime t1 = LocalTime.parse(o1.toString(), format);
            LocalTime t2 = LocalTime.parse(o2.toString(), format);
            return t1.compareTo(t2);
        });

        sorter.setSortKeys(Arrays.asList(
                new RowSorter.SortKey(1, SortOrder.ASCENDING)
        ));

        sorter.sort();
    }
    
    private void tampilDjj(String norw) {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("select * from partograf_djj where no_rawat ='" + norw + "' order by urutan");
            try {                
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        rs1.getString("no_rawat"),
                        rs1.getString("nilai_djj"),
                        rs1.getString("jeda_menit"),
                        rs1.getString("urutan"),
                        rs1.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilDjj() : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilAirKetuban(String norw) {
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("select * from partograf_air_ketuban where no_rawat ='" + norw + "' order by urutan");
            try {                
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new String[]{
                        rs2.getString("no_rawat"),
                        rs2.getString("urutan"),
                        rs2.getString("air_ketuban"),
                        rs2.getString("mulase"),
                        rs2.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilAirKetuban() : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilServiks(String norw) {
        Valid.tabelKosong(tabMode3);
        try {
            ps3 = koneksi.prepareStatement("select * from partograf_pembukaan_serviks where no_rawat ='" + norw + "' order by urutan");
            try {                
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    tabMode3.addRow(new String[]{
                        rs3.getString("no_rawat"),
                        rs3.getString("waktu_ke"),
                        rs3.getString("jam"),
                        rs3.getString("centimeter_servik"),
                        rs3.getString("pembukaan"),
                        rs3.getString("turun_kepala"),
                        rs3.getString("keterangan"),
                        rs3.getString("urutan"),
                        rs3.getString("waktu_simpan")                        
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilServiks() : " + e);
            } finally {
                if (rs3 != null) {
                    rs3.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilKontraksi(String norw) {
        Valid.tabelKosong(tabMode4);
        try {
            ps4 = koneksi.prepareStatement("select * from partograf_kontraksi where no_rawat ='" + norw + "' order by waktu_simpan");
            try {                
                rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    tabMode4.addRow(new String[]{
                        rs4.getString("no_rawat"),
                        rs4.getString("lajur_kontraksi"),
                        rs4.getString("detik_kontraksi"),
                        rs4.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilKontraksi() : " + e);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilOksitosin(String norw) {
        Valid.tabelKosong(tabMode5);
        try {
            ps5 = koneksi.prepareStatement("select * from partograf_oksitosin where no_rawat ='" + norw + "' order by waktu_simpan");
            try {                
                rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    tabMode5.addRow(new String[]{
                        rs5.getString("no_rawat"),
                        rs5.getString("oksitosin"),
                        rs5.getString("tetes_menit"),
                        rs5.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilOksitosin() : " + e);
            } finally {
                if (rs5 != null) {
                    rs5.close();
                }
                if (ps5 != null) {
                    ps5.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilObatCairan(String norw) {
        Valid.tabelKosong(tabMode6);
        try {
            ps6 = koneksi.prepareStatement("select * from partograf_obat_cairan where no_rawat ='" + norw + "' order by waktu_simpan");
            try {                
                rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    tabMode6.addRow(new String[]{
                        rs6.getString("no_rawat"),
                        rs6.getString("obat_cairan"),
                        rs6.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilObatCairan() : " + e);
            } finally {
                if (rs6 != null) {
                    rs6.close();
                }
                if (ps6 != null) {
                    ps6.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilNadiTD(String norw) {
        Valid.tabelKosong(tabMode7);
        try {
            ps7 = koneksi.prepareStatement("select * from partograf_nadi_tensi where no_rawat ='" + norw + "' order by urutan");
            try {                
                rs7 = ps7.executeQuery();
                while (rs7.next()) {
                    tabMode7.addRow(new String[]{
                        rs7.getString("no_rawat"),
                        rs7.getString("urutan"),
                        rs7.getString("nadi"),
                        rs7.getString("sistole"),
                        rs7.getString("distole"),
                        rs7.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilNadiTD() : " + e);
            } finally {
                if (rs7 != null) {
                    rs7.close();
                }
                if (ps7 != null) {
                    ps7.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilSuhu(String norw) {
        Valid.tabelKosong(tabMode8);
        try {
            ps8 = koneksi.prepareStatement("select * from partograf_suhu where no_rawat ='" + norw + "' order by waktu_simpan");
            try {                
                rs8 = ps8.executeQuery();
                while (rs8.next()) {
                    tabMode8.addRow(new String[]{
                        rs8.getString("no_rawat"),
                        rs8.getString("pukul"),
                        rs8.getString("suhu"),
                        rs8.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilSuhu() : " + e);
            } finally {
                if (rs8 != null) {
                    rs8.close();
                }
                if (ps8 != null) {
                    ps8.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilUrin(String norw) {
        Valid.tabelKosong(tabMode9);
        try {
            ps9 = koneksi.prepareStatement("select * from partograf_urin where no_rawat ='" + norw + "' order by waktu_simpan");
            try {                
                rs9 = ps9.executeQuery();
                while (rs9.next()) {
                    tabMode9.addRow(new String[]{
                        rs9.getString("no_rawat"),
                        rs9.getString("pukul"),
                        rs9.getString("protein"),
                        rs9.getString("aseton"),
                        rs9.getString("volume"),
                        rs9.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilUrin() : " + e);
            } finally {
                if (rs9 != null) {
                    rs9.close();
                }
                if (ps9 != null) {
                    ps9.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilPemantauanKala4(String norw) {
        Valid.tabelKosong(tabMode10);
        try {
            ps10 = koneksi.prepareStatement("select * from partograf_kala_4 where no_rawat ='" + norw + "' order by urutan");
            try {                
                rs10 = ps10.executeQuery();
                while (rs10.next()) {
                    tabMode10.addRow(new String[]{
                        rs10.getString("no_rawat"),
                        rs10.getString("urutan"),
                        rs10.getString("jam_ke"),
                        rs10.getString("waktu"),
                        rs10.getString("tekanan_darah"),
                        rs10.getString("nadi"),
                        rs10.getString("suhu"),
                        rs10.getString("tinggi_fundus_uteri"),
                        rs10.getString("kontraksi_uterus"),
                        rs10.getString("urin_output"),
                        rs10.getString("darah_yang_keluar"),
                        rs10.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilPemantauanKala4() : " + e);
            } finally {
                if (rs10 != null) {
                    rs10.close();
                }
                if (ps10 != null) {
                    ps10.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilCatatan(String norw) {
        try {
            ps11 = koneksi.prepareStatement("select * from partograf_catatan_persalinan where no_rawat ='" + norw + "'");
            try {                
                rs11 = ps11.executeQuery();
                while (rs11.next()) {
                    Valid.SetTgl(TtglCatatan, rs11.getString("cttn_tanggal"));
                    cmbTmptPersalinan.setSelectedItem(rs11.getString("cttn_tempat_persalinan"));
                    TtmptPersalinanLain.setText(rs11.getString("cttn_ket_lainya_tmpt"));
                    TalmtTmpPersalinan.setText(rs11.getString("cttn_alamat_tempat"));
                    cmbCttnRujuk.setSelectedItem(rs11.getString("cttn_rujukan"));
                    TalasanMerujuk.setText(rs11.getString("cttn_alasan_merujuk"));
                    TtmptRujukan.setText(rs11.getString("cttn_tempat_rujukan"));
                    bidan8 = rs11.getString("cttn_pendamping_bidan");
                    teman8 = rs11.getString("cttn_pendamping_teman");
                    klg8 = rs11.getString("cttn_pendamping_klg");
                    suami8 = rs11.getString("cttn_pendamping_suami");
                    dukun8 = rs11.getString("cttn_pendamping_dukun");
                    tdkAda8 = rs11.getString("cttn_pendamping_tidak_ada");
                    gawat9 = rs11.getString("cttn_masalah_gawat");
                    perdarahan9 = rs11.getString("cttn_masalah_perdarahan");
                    hdk9 = rs11.getString("cttn_masalah_hdk");
                    infeksi9 = rs11.getString("cttn_masalah_infeksi");
                    peb9 = rs11.getString("cttn_masalah_peb");
                    bidan9 = rs11.getString("cttn_masalah_bidan_pmtct");
                    lainya9 = rs11.getString("cttn_masalah_lainya");
                    TmasalahLain.setText(rs11.getString("cttn_masalah_ket_lainya"));
                    cmbPartogram.setSelectedItem(rs11.getString("kala1_partogram"));
                    Tkala1MasalahLain.setText(rs11.getString("kala1_masalah_lain"));
                    Tkala1Penata.setText(rs11.getString("kala1_penatalaksanaan"));
                    Tkala1Hasilnya.setText(rs11.getString("kala1_hasilnya"));
                    cmbEpisiotomi.setSelectedItem(rs11.getString("kala2_episiotomi"));
                    Tkala2YaIndikasi.setText(rs11.getString("kala2_episiotomi_indikasi"));
                    suami15 = rs11.getString("kala2_pendamping_suami");
                    teman15 = rs11.getString("kala2_pendamping_teman");
                    tdkAda15 = rs11.getString("kala2_pendamping_tidak_ada");
                    klg15 = rs11.getString("kala2_pendamping_klg");
                    dukun15 = rs11.getString("kala2_pendamping_dukun");
                    cmbGawatJanin.setSelectedItem(rs11.getString("kala2_gawat_janin"));
                    Tkala2YaTindakanGawat.setText(rs11.getString("kala2_ket_ya_gawat_janin"));
                    Tkala2Pemantauan.setText(rs11.getString("kala2_ket_hasil_pemantauan"));
                    cmbDistosia.setSelectedItem(rs11.getString("kala2_distosia_bahu"));
                    Tkala2YaTindakanDisto.setText(rs11.getString("kala2_ket_ya_distosia_bahu"));
                    Tkala2MasalahLain.setText(rs11.getString("kala2_masalah_lain_hasilnya"));
                    cmbInisiasi.setSelectedItem(rs11.getString("kala3_inisiasi"));
                    Tkala3Tidak.setText(rs11.getString("kala3_ket_tidak_alasan"));
                    Tkala3Lama.setText(rs11.getString("kala3_lama"));
                    cmbPemberianOksi.setSelectedItem(rs11.getString("kala3_pemberian_oksitosin"));

                    if (cmbPemberianOksi.getSelectedIndex() == 1) {
                        Tkala3PemberianOksi.setText(rs11.getString("kala3_pemberian_oksitosin_ya"));
                    } else if (cmbPemberianOksi.getSelectedIndex() == 2) {
                        Tkala3PemberianOksi.setText(rs11.getString("kala3_pemberian_oksitosin_tidak"));
                    } else if (cmbPemberianOksi.getSelectedIndex() == 3) {
                        Tkala3PemberianOksi.setText(rs11.getString("kala3_pemberian_oksitosin_penjepitan"));
                    } else if (cmbPemberianOksi.getSelectedIndex() == 0) {
                        Tkala3PemberianOksi.setText("");
                    }
                    
                    cmbPemberianUlang.setSelectedItem(rs11.getString("kala3_pemberian_ulang"));
                    Tkala3PemberianUlang.setText(rs11.getString("kala3_pemberian_ulang_ya"));
                    cmbPenegangan.setSelectedItem(rs11.getString("kala3_penegangan_tali"));
                    Tkala3Penegangan.setText(rs11.getString("kala3_penegangan_tali_tidak"));
                    cmbMasase.setSelectedItem(rs11.getString("kala3_masase_fundus"));
                    Tkala3Masase.setText(rs11.getString("kala3_masase_fundus_tidak"));
                    cmbPlasenta25.setSelectedItem(rs11.getString("kala3_plasenta_lahir"));
                    Tkala3Plasenta25A.setText(rs11.getString("kala3_plasenta_lahir_tidak_a"));
                    Tkala3Plasenta25B.setText(rs11.getString("kala3_plasenta_lahir_tidak_b"));
                    cmbPlasenta26.setSelectedItem(rs11.getString("kala3_plasenta_tidak_lahir"));
                    Tkala3Plasenta26.setText(rs11.getString("kala3_plasenta_tidak_lahir_ya"));
                    cmbLaserasi.setSelectedItem(rs11.getString("kala3_laserasi"));
                    Tkala3Laserasi.setText(rs11.getString("kala3_laserasi_ya"));
                    cmbJika.setSelectedItem(rs11.getString("kala3_laserasi_perineum"));
                    cmbTindakan.setSelectedItem(rs11.getString("kala3_laserasi_perineum_penjahitan"));
                    Tkala3Alasan.setText(rs11.getString("kala3_laserasi_perineum_penjahitan_tidak"));
                    cmbAtonia.setSelectedItem(rs11.getString("kala3_atonia_uteri"));
                    Tkala3Atonia.setText(rs11.getString("kala3_atonia_uteri_ya"));
                    Tkala3Jumlah.setText(rs11.getString("kala3_jumlah_darah"));
                    Tkala3Masalah.setText(rs11.getString("kala3_masalah"));
                    Tkala3Penata.setText(rs11.getString("kala3_penatalaksanaan_masalah"));
                    Tkala3Hasilnya.setText(rs11.getString("kala3_hasilnya"));
                    TbayiBB.setText(rs11.getString("bayi_bb"));
                    TbayiPB.setText(rs11.getString("bayi_pb"));
                    cmbJenkel.setSelectedItem(rs11.getString("bayi_jenkel"));
                    cmbPenilaian.setSelectedItem(rs11.getString("bayi_penilaian"));
                    cmbBayiLahir.setSelectedItem(rs11.getString("bayi_bayi_lahir"));
                    cmbBayiNormal.setSelectedItem(rs11.getString("bayi_bayi_lahir_normal"));
                    cmbBayiAsfeksia.setSelectedItem(rs11.getString("bayi_bayi_lahir_asfiksia_tindakan"));

                    if (cmbBayiLahir.getSelectedIndex() == 6) {
                        TbayiSebutkan.setText(rs11.getString("bayi_bayi_lahir_cacat_bawaan_sebutkan"));
                    } else if (cmbBayiAsfeksia.getSelectedIndex() == 6) {
                        TbayiSebutkan.setText(rs11.getString("bayi_bayi_lahir_asfiksia_tindakan_sebutkan"));
                    }
                    
                    if (cmbBayiLahir.getSelectedIndex() == 7) {
                        TbayiTindakanA.setText(rs11.getString("bayi_bayi_lahir_hipotermia_a"));
                        TbayiTindakanB.setText(rs11.getString("bayi_bayi_lahir_hipotermia_b"));
                        TbayiTindakanC.setText(rs11.getString("bayi_bayi_lahir_hipotermia_c"));
                    }
                    
                    cmbPemberianAsi.setSelectedItem(rs11.getString("bayi_pemberian_asi"));
                    TbayiYaPemberian.setText(rs11.getString("bayi_pemberian_asi_ya"));
                    TbayiTidakAlasan.setText(rs11.getString("bayi_pemberian_asi_tidak_alasan"));
                    TbayiMasalah.setText(rs11.getString("bayi_masalah_lain"));
                    TbayiHasilnya.setText(rs11.getString("bayi_hasilnya"));
                }
            } catch (Exception e) {
                System.out.println("tampilCatatan() : " + e);
            } finally {
                if (rs11 != null) {
                    rs11.close();
                }
                if (ps11 != null) {
                    ps11.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void dataCek() {
        if (cmbKetuban.getSelectedIndex() == 1) {
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
        } else {
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
        }

        if (tbDjj.getRowCount() != 0) {
            int viewRow = tbDjj.getRowCount() - 1;
            int modelRow = tbDjj.convertRowIndexToModel(viewRow);
            Tjeda.setText(tabMode1.getValueAt(modelRow, 2).toString());
        } else {
            Tjeda.setText("");
        }
        
        //-----------------------------------------------------------------------        
        if (cmbTmptPersalinan.getSelectedIndex() == 6) {
            TtmptPersalinanLain.setEnabled(true);
        } else {
            TtmptPersalinanLain.setEnabled(false);
        }
        
        if (bidan8.equals("ya")) {
            chkBidan8.setSelected(true);
        } else {
            chkBidan8.setSelected(false);
        }
        
        if (teman8.equals("ya")) {
            chkTeman8.setSelected(true);
        } else {
            chkTeman8.setSelected(false);
        }
        
        if (klg8.equals("ya")) {
            chkKlg8.setSelected(true);
        } else {
            chkKlg8.setSelected(false);
        }
        
        if (suami8.equals("ya")) {
            chkSuami8.setSelected(true);
        } else {
            chkSuami8.setSelected(false);
        }
        
        if (dukun8.equals("ya")) {
            chkDukun8.setSelected(true);
        } else {
            chkDukun8.setSelected(false);
        }
        
        if (tdkAda8.equals("ya")) {
            chkTidakAda8.setSelected(true);
        } else {
            chkTidakAda8.setSelected(false);
        }
        
        if (gawat9.equals("ya")) {
            chkGawat9.setSelected(true);
        } else {
            chkGawat9.setSelected(false);
        }
        
        if (perdarahan9.equals("ya")) {
            chkPerdarahan9.setSelected(true);
        } else {
            chkPerdarahan9.setSelected(false);
        }
        
        if (hdk9.equals("ya")) {
            chkHdk9.setSelected(true);
        } else {
            chkHdk9.setSelected(false);
        }
        
        if (infeksi9.equals("ya")) {
            chkInfeksi9.setSelected(true);
        } else {
            chkInfeksi9.setSelected(false);
        }
        
        if (peb9.equals("ya")) {
            chkPeb9.setSelected(true);
        } else {
            chkPeb9.setSelected(false);
        }
        
        if (bidan9.equals("ya")) {
            chkBidan9.setSelected(true);
        } else {
            chkBidan9.setSelected(false);
        }
        
        if (lainya9.equals("ya")) {
            chkLainya9.setSelected(true);
            TmasalahLain.setEnabled(true);
        } else {
            chkLainya9.setSelected(false);
            TmasalahLain.setEnabled(false);
        }
        
        if (cmbEpisiotomi.getSelectedIndex() == 1) {
            Tkala2YaIndikasi.setEnabled(true);
        } else {
            Tkala2YaIndikasi.setEnabled(false);
        }
        
        if (suami15.equals("ya")) {
            chkKala2Suami.setSelected(true);
        } else {
            chkKala2Suami.setSelected(false);
        }
        
        if (teman15.equals("ya")) {
            chkKala2Teman.setSelected(true);
        } else {
            chkKala2Teman.setSelected(false);
        }
        
        if (tdkAda15.equals("ya")) {
            chkKala2TidakAda.setSelected(true);
        } else {
            chkKala2TidakAda.setSelected(false);
        }
        
        if (klg15.equals("ya")) {
            chkKala2Klg.setSelected(true);
        } else {
            chkKala2Klg.setSelected(false);
        }
        
        if (dukun15.equals("ya")) {
            chkKala2Dukun.setSelected(true);
        } else {
            chkKala2Dukun.setSelected(false);
        }
        
        if (cmbGawatJanin.getSelectedIndex() == 2) {
            Tkala2YaTindakanGawat.setEnabled(true);
        } else {
            Tkala2YaTindakanGawat.setEnabled(false);
        }
        
        if (cmbDistosia.getSelectedIndex() == 2) {
            Tkala2YaTindakanDisto.setEnabled(true);
        } else {
            Tkala2YaTindakanDisto.setEnabled(false);
        }
        
        if (cmbInisiasi.getSelectedIndex() == 2) {
            Tkala3Tidak.setEnabled(true);
        } else {
            Tkala3Tidak.setEnabled(false);
        }
        
        if (cmbPemberianOksi.getSelectedIndex() == 1) {
            labelKetPemberian.setText("Menit sesudah persalinan");
            Tkala3PemberianOksi.setEnabled(true);
        } else if (cmbPemberianOksi.getSelectedIndex() == 2 || cmbPemberianOksi.getSelectedIndex() == 0) {
            labelKetPemberian.setText("");
            Tkala3PemberianOksi.setEnabled(true);
        } else if (cmbPemberianOksi.getSelectedIndex() == 3) {
            labelKetPemberian.setText("Menit setelah bayi lahir");
            Tkala3PemberianOksi.setEnabled(true);
        } else {
            Tkala3PemberianOksi.setEnabled(false);
        }
        
        if (cmbPemberianUlang.getSelectedIndex() == 1) {
            Tkala3PemberianUlang.setEnabled(true);
        } else {
            Tkala3PemberianUlang.setEnabled(false);
        }
        
        if (cmbPenegangan.getSelectedIndex() == 2) {
            Tkala3Penegangan.setEnabled(true);
        } else {
            Tkala3Penegangan.setEnabled(false);
        }
        
        if (cmbMasase.getSelectedIndex() == 2) {
            Tkala3Masase.setEnabled(true);
        } else {
            Tkala3Masase.setEnabled(false);
        }
        
        if (cmbPlasenta25.getSelectedIndex() == 2) {
            Tkala3Plasenta25A.setEnabled(true);
            Tkala3Plasenta25B.setEnabled(true);
        } else {
            Tkala3Plasenta25A.setEnabled(false);
            Tkala3Plasenta25B.setEnabled(false);
        }
        
        if (cmbPlasenta26.getSelectedIndex() == 2) {
            Tkala3Plasenta26.setEnabled(true);
        } else {
            Tkala3Plasenta26.setEnabled(false);
        }
        
        if (cmbLaserasi.getSelectedIndex() == 1) {
            Tkala3Laserasi.setEnabled(true);
        } else {
            Tkala3Laserasi.setEnabled(false);
        }
        
        if (cmbTindakan.getSelectedIndex() == 3) {
            Tkala3Alasan.setEnabled(true);
        } else {
            Tkala3Alasan.setEnabled(false);
        }
        
        if (cmbAtonia.getSelectedIndex() == 2) {
            Tkala3Atonia.setEnabled(true);
        } else {
            Tkala3Atonia.setEnabled(false);
        }
        
        if (cmbBayiLahir.getSelectedIndex() == 1) {
            cmbBayiNormal.setEnabled(true);
            cmbBayiAsfeksia.setEnabled(false);
            TbayiSebutkan.setEnabled(false);
            TbayiTindakanA.setEnabled(false);
            TbayiTindakanB.setEnabled(false);
            TbayiTindakanC.setEnabled(false);
        } else if (cmbBayiLahir.getSelectedIndex() == 2 || cmbBayiLahir.getSelectedIndex() == 3
                || cmbBayiLahir.getSelectedIndex() == 4 || cmbBayiLahir.getSelectedIndex() == 5) {
            cmbBayiNormal.setEnabled(false);            
            cmbBayiAsfeksia.setEnabled(true);
            TbayiSebutkan.setEnabled(false);
            TbayiTindakanA.setEnabled(false);
            TbayiTindakanB.setEnabled(false);
            TbayiTindakanC.setEnabled(false);
        } else if (cmbBayiLahir.getSelectedIndex() == 6) {
            cmbBayiNormal.setEnabled(false);
            cmbBayiAsfeksia.setEnabled(false);            
            TbayiSebutkan.setEnabled(true);
            TbayiTindakanA.setEnabled(false);
            TbayiTindakanB.setEnabled(false);
            TbayiTindakanC.setEnabled(false);
        } else if (cmbBayiLahir.getSelectedIndex() == 7) {
            cmbBayiNormal.setEnabled(false);
            cmbBayiAsfeksia.setEnabled(false);
            TbayiSebutkan.setEnabled(false);            
            TbayiTindakanA.setEnabled(true);
            TbayiTindakanB.setEnabled(true);
            TbayiTindakanC.setEnabled(true);
        } else {
            cmbBayiNormal.setEnabled(false);
            cmbBayiAsfeksia.setEnabled(false);
            TbayiSebutkan.setEnabled(false);
            TbayiTindakanA.setEnabled(false);
            TbayiTindakanB.setEnabled(false);
            TbayiTindakanC.setEnabled(false);
        }
        
        if (cmbBayiAsfeksia.getSelectedIndex() == 6) {
            TbayiSebutkan.setEnabled(true);
        }
        
        if (cmbPemberianAsi.getSelectedIndex() == 1) {
            TbayiYaPemberian.setEnabled(true);
            TbayiTidakAlasan.setEnabled(false);
        } else if (cmbPemberianAsi.getSelectedIndex() == 2) {
            TbayiYaPemberian.setEnabled(false);            
            TbayiTidakAlasan.setEnabled(true);
        } else {
            TbayiYaPemberian.setEnabled(false);
            TbayiTidakAlasan.setEnabled(false);
        }
    }
    
    private void cekData() {
        if (chkBidan8.isSelected() == true) {
            bidan8 = "ya";
        } else {
            bidan8 = "tidak";
        }
        
        if (chkTeman8.isSelected() == true) {
            teman8 = "ya";
        } else {
            teman8 = "tidak";
        }
        
        if (chkKlg8.isSelected() == true) {
            klg8 = "ya";
        } else {
            klg8 = "tidak";
        }
        
        if (chkSuami8.isSelected() == true) {
            suami8 = "ya";
        } else {
            suami8 = "tidak";
        }
        
        if (chkDukun8.isSelected() == true) {
            dukun8 = "ya";
        } else {
            dukun8 = "tidak";
        }
        
        if (chkTidakAda8.isSelected() == true) {
            tdkAda8 = "ya";
        } else {
            tdkAda8 = "tidak";
        }
        
        if (chkGawat9.isSelected() == true) {
            gawat9 = "ya";
        } else {
            gawat9 = "tidak";
        }
        
        if (chkPerdarahan9.isSelected() == true) {
            perdarahan9 = "ya";
        } else {
            perdarahan9 = "tidak";
        }
        
        if (chkHdk9.isSelected() == true) {
            hdk9 = "ya";
        } else {
            hdk9 = "tidak";
        }
        
        if (chkInfeksi9.isSelected() == true) {
            infeksi9 = "ya";
        } else {
            infeksi9 = "tidak";
        }
        
        if (chkPeb9.isSelected() == true) {
            peb9 = "ya";
        } else {
            peb9 = "tidak";
        }
        
        if (chkBidan9.isSelected() == true) {
            bidan9 = "ya";
        } else {
            bidan9 = "tidak";
        }
        
        if (chkLainya9.isSelected() == true) {
            lainya9 = "ya";
        } else {
            lainya9 = "tidak";
        }
        
        if (chkKala2Suami.isSelected() == true) {
            suami15 = "ya";
        } else {
            suami15 = "tidak";
        }
        
        if (chkKala2Teman.isSelected() == true) {
            teman15 = "ya";
        } else {
            teman15 = "tidak";
        }
        
        if (chkKala2TidakAda.isSelected() == true) {
            tdkAda15 = "ya";
        } else {
            tdkAda15 = "tidak";
        }
        
        if (chkKala2Klg.isSelected() == true) {
            klg15 = "ya";
        } else {
            klg15 = "tidak";
        }
        
        if (chkKala2Dukun.isSelected() == true) {
            dukun15 = "ya";
        } else {
            dukun15 = "tidak";
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from partograf_persalinan where no_rawat=?", 1, new String[]{
                tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString()
            }) == true) {
                Sequel.queryu("delete from partograf_djj where no_rawat='" + tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString() + "'");
                Sequel.queryu("delete from partograf_air_ketuban where no_rawat='" + tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString() + "'");
                Sequel.queryu("delete from partograf_pembukaan_serviks where no_rawat='" + tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString() + "'");
                Sequel.queryu("delete from partograf_kontraksi where no_rawat='" + tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString() + "'");
                Sequel.queryu("delete from partograf_oksitosin where no_rawat='" + tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString() + "'");
                Sequel.queryu("delete from partograf_obat_cairan where no_rawat='" + tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString() + "'");
                Sequel.queryu("delete from partograf_nadi_tensi where no_rawat='" + tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString() + "'");
                Sequel.queryu("delete from partograf_suhu where no_rawat='" + tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString() + "'");
                Sequel.queryu("delete from partograf_urin where no_rawat='" + tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString() + "'");
                Sequel.queryu("delete from partograf_catatan_persalinan where no_rawat='" + tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString() + "'");
                Sequel.queryu("delete from partograf_kala_4 where no_rawat='" + tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString() + "'");
                Sequel.queryu("delete from partograf_pemantauan_kala4 where no_rawat='" + tbPartograf.getValueAt(tbPartograf.getSelectedRow(), 0).toString() + "'");
                
                TabPartograf.setSelectedIndex(2);
                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        } else {
            tampil();
            emptTeks();
        }
    }
}