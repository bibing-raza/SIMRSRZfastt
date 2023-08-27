package laporan;

import simrskhanza.*;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariPoli;
import simrskhanza.DlgPasien;

/**
 *
 * @author dosen
 */
public class DlgLaporanFarmasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, g = 0, cekData = 0;
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private DlgPasien pasien = new DlgPasien(null, false);
    private DlgCariBangsal bangsal = new DlgCariBangsal(null, false);
    private String tgl1 = "", tgl2 = "", sql = "", depo1 = "", depo2 = "", norm = "";

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgLaporanFarmasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
            "Tgl. Beri","Jam Beri","No. Rawat","No. RM","Nama Pasien","Cara Bayar","Nama Obat/Alkes","Embalase",
            "Tuslah","Jmlh.","Biaya Obat","Total","Depo/Gudang","kd_penjab"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbObat.setModel(tabMode);

        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 14; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(130);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(80);
            } else if (i == 9) {
                column.setPreferredWidth(40);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(150);
            } else if (i == 13) {
//                column.setPreferredWidth(150);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());


        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
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
        
        ChkInput.setSelected(false);
        isForm();
        
        bangsal.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgLaporanFarmasi")) {
                    if (bangsal.getTable().getSelectedRow() != -1) {
                        KdGudang.setText(bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(), 0).toString());
                        NmGudang.setText(bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(), 1).toString());
                    }
                    KdGudang.requestFocus();
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
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgLaporanFarmasi")) {
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
        
        pasien.penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgLaporanFarmasi")) {
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
                if (akses.getform().equals("DlgLaporanFarmasi")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
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

        Popup1 = new javax.swing.JPopupMenu();
        jMenuLapJalanAllDepo = new javax.swing.JMenu();
        jMnRekapTotalPerPasienJalan = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienJalan = new javax.swing.JMenuItem();
        jMnRekapTotalPerPasienJalanCara = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienJalanCara = new javax.swing.JMenuItem();
        jMnRekapTotalResepPasienPerCB = new javax.swing.JMenuItem();
        jMnRekapDetailResepPasienPerCB = new javax.swing.JMenuItem();
        jMnRekapTotalResepPasienSemuaCB = new javax.swing.JMenuItem();
        jMnRekapDetailResepPasienSemuaCB = new javax.swing.JMenuItem();
        jMenuLapInapAllDepo = new javax.swing.JMenu();
        jMnRekapTotalPerPasienInap = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienInap = new javax.swing.JMenuItem();
        jMnRekapTotalPerPasienInapCara = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienInapCara = new javax.swing.JMenuItem();
        jMenuLapJalanPerDepo = new javax.swing.JMenu();
        jMnRekapTotalPerPasienJalan1 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienJalan1 = new javax.swing.JMenuItem();
        jMnRekapTotalPerPasienJalanCara1 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienJalanCara1 = new javax.swing.JMenuItem();
        jMnRekapTotalResepPasienPerCB1 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalResepPasienPerCB1 = new javax.swing.JMenuItem();
        jMnRekapTotalResepPasienSemuaCB1 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalResepPasienSemuaCB1 = new javax.swing.JMenuItem();
        jMenuLapInapPerDepo = new javax.swing.JMenu();
        jMnRekapTotalPerPasienInap1 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienInap1 = new javax.swing.JMenuItem();
        jMnRekapTotalPerPasienInapCara1 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienInapCara1 = new javax.swing.JMenuItem();
        jMenuLapAllRawatPerDepo = new javax.swing.JMenu();
        jMnRekapTotalPerPasienJalan2 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienJalan2 = new javax.swing.JMenuItem();
        jMnRekapTotalPerPasienJalanCara2 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienJalanCara2 = new javax.swing.JMenuItem();
        jMenuLapSemuaAllDepo = new javax.swing.JMenu();
        jMnRekapTotalPerPasienInap2 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienInap2 = new javax.swing.JMenuItem();
        jMnRekapTotalPerPasienInapCara2 = new javax.swing.JMenuItem();
        jMnRekapDetailTotalPerPasienInapCara2 = new javax.swing.JMenuItem();
        jMnRekapPemakaianObatKhusus1Px = new javax.swing.JMenuItem();
        jMenuLapPemakaian = new javax.swing.JMenu();
        jMenuPerDokter = new javax.swing.JMenuItem();
        jMenuObatKhususPerTgl = new javax.swing.JMenuItem();
        jMenuObatKhusus = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnBatal = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        jLabel8 = new widget.Label();
        limitData = new widget.ComboBox();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel23 = new widget.Label();
        kdpnj = new widget.TextBox();
        nmpnj = new widget.TextBox();
        btnPenjab = new widget.Button();
        jLabel18 = new widget.Label();
        KdGudang = new widget.TextBox();
        NmGudang = new widget.TextBox();
        btnBarang1 = new widget.Button();
        label19 = new widget.Label();
        kdpoli = new widget.TextBox();
        TPoli = new widget.TextBox();
        BtnUnit = new widget.Button();
        jLabel14 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel19 = new widget.Label();
        DTPCari2 = new widget.Tanggal();

        Popup1.setName("Popup1"); // NOI18N

        jMenuLapJalanAllDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapJalanAllDepo.setText("1) Lap. Rawat Jalan Semua DEPO");
        jMenuLapJalanAllDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapJalanAllDepo.setName("jMenuLapJalanAllDepo"); // NOI18N
        jMenuLapJalanAllDepo.setPreferredSize(new java.awt.Dimension(220, 25));

        jMnRekapTotalPerPasienJalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienJalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienJalan.setText("1) Rekap Resep Per Pasien");
        jMnRekapTotalPerPasienJalan.setName("jMnRekapTotalPerPasienJalan"); // NOI18N
        jMnRekapTotalPerPasienJalan.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapTotalPerPasienJalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienJalanActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapTotalPerPasienJalan);

        jMnRekapDetailTotalPerPasienJalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienJalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienJalan.setText("2) Rekap Detail Resep Per Pasien");
        jMnRekapDetailTotalPerPasienJalan.setName("jMnRekapDetailTotalPerPasienJalan"); // NOI18N
        jMnRekapDetailTotalPerPasienJalan.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailTotalPerPasienJalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienJalanActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapDetailTotalPerPasienJalan);

        jMnRekapTotalPerPasienJalanCara.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienJalanCara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienJalanCara.setText("3) Rekap Resep Per Pasien Per Cara Bayar");
        jMnRekapTotalPerPasienJalanCara.setName("jMnRekapTotalPerPasienJalanCara"); // NOI18N
        jMnRekapTotalPerPasienJalanCara.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapTotalPerPasienJalanCara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienJalanCaraActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapTotalPerPasienJalanCara);

        jMnRekapDetailTotalPerPasienJalanCara.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara.setText("4) Rekap Detail Resep Per Pasien Per Cara Bayar");
        jMnRekapDetailTotalPerPasienJalanCara.setName("jMnRekapDetailTotalPerPasienJalanCara"); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailTotalPerPasienJalanCara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienJalanCaraActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapDetailTotalPerPasienJalanCara);

        jMnRekapTotalResepPasienPerCB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalResepPasienPerCB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalResepPasienPerCB.setText("5) Rekap Resep Pasien Per Poli Per Cara Bayar");
        jMnRekapTotalResepPasienPerCB.setToolTipText("");
        jMnRekapTotalResepPasienPerCB.setName("jMnRekapTotalResepPasienPerCB"); // NOI18N
        jMnRekapTotalResepPasienPerCB.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapTotalResepPasienPerCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalResepPasienPerCBActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapTotalResepPasienPerCB);

        jMnRekapDetailResepPasienPerCB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailResepPasienPerCB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailResepPasienPerCB.setText("6) Rekap Detail Resep Pasien Per Poli Per Cara Bayar");
        jMnRekapDetailResepPasienPerCB.setName("jMnRekapDetailResepPasienPerCB"); // NOI18N
        jMnRekapDetailResepPasienPerCB.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailResepPasienPerCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailResepPasienPerCBActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapDetailResepPasienPerCB);

        jMnRekapTotalResepPasienSemuaCB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalResepPasienSemuaCB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalResepPasienSemuaCB.setText("7) Rekap Resep Pasien Per Poli Semua Cara Bayar");
        jMnRekapTotalResepPasienSemuaCB.setToolTipText("");
        jMnRekapTotalResepPasienSemuaCB.setName("jMnRekapTotalResepPasienSemuaCB"); // NOI18N
        jMnRekapTotalResepPasienSemuaCB.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapTotalResepPasienSemuaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalResepPasienSemuaCBActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapTotalResepPasienSemuaCB);

        jMnRekapDetailResepPasienSemuaCB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailResepPasienSemuaCB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailResepPasienSemuaCB.setText("8) Rekap Detail Resep Pasien Per Poli Semua Cara Bayar");
        jMnRekapDetailResepPasienSemuaCB.setName("jMnRekapDetailResepPasienSemuaCB"); // NOI18N
        jMnRekapDetailResepPasienSemuaCB.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailResepPasienSemuaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailResepPasienSemuaCBActionPerformed(evt);
            }
        });
        jMenuLapJalanAllDepo.add(jMnRekapDetailResepPasienSemuaCB);

        Popup1.add(jMenuLapJalanAllDepo);

        jMenuLapInapAllDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapInapAllDepo.setText("2) Lap. Rawat Inap Semua DEPO");
        jMenuLapInapAllDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapInapAllDepo.setName("jMenuLapInapAllDepo"); // NOI18N
        jMenuLapInapAllDepo.setPreferredSize(new java.awt.Dimension(220, 25));

        jMnRekapTotalPerPasienInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienInap.setText("1) Rekap Resep Per Pasien");
        jMnRekapTotalPerPasienInap.setName("jMnRekapTotalPerPasienInap"); // NOI18N
        jMnRekapTotalPerPasienInap.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapTotalPerPasienInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienInapActionPerformed(evt);
            }
        });
        jMenuLapInapAllDepo.add(jMnRekapTotalPerPasienInap);

        jMnRekapDetailTotalPerPasienInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienInap.setText("2) Rekap Detail Resep Per Pasien");
        jMnRekapDetailTotalPerPasienInap.setName("jMnRekapDetailTotalPerPasienInap"); // NOI18N
        jMnRekapDetailTotalPerPasienInap.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienInapActionPerformed(evt);
            }
        });
        jMenuLapInapAllDepo.add(jMnRekapDetailTotalPerPasienInap);

        jMnRekapTotalPerPasienInapCara.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienInapCara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienInapCara.setText("3) Rekap Resep Per Pasien Per Cara Bayar");
        jMnRekapTotalPerPasienInapCara.setName("jMnRekapTotalPerPasienInapCara"); // NOI18N
        jMnRekapTotalPerPasienInapCara.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapTotalPerPasienInapCara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienInapCaraActionPerformed(evt);
            }
        });
        jMenuLapInapAllDepo.add(jMnRekapTotalPerPasienInapCara);

        jMnRekapDetailTotalPerPasienInapCara.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara.setText("4) Rekap Detail Resep Per Pasien Per Cara Bayar");
        jMnRekapDetailTotalPerPasienInapCara.setName("jMnRekapDetailTotalPerPasienInapCara"); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienInapCara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienInapCaraActionPerformed(evt);
            }
        });
        jMenuLapInapAllDepo.add(jMnRekapDetailTotalPerPasienInapCara);

        Popup1.add(jMenuLapInapAllDepo);

        jMenuLapJalanPerDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapJalanPerDepo.setText("3) Lap. Rawat Jalan Per DEPO");
        jMenuLapJalanPerDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapJalanPerDepo.setName("jMenuLapJalanPerDepo"); // NOI18N
        jMenuLapJalanPerDepo.setPreferredSize(new java.awt.Dimension(220, 25));

        jMnRekapTotalPerPasienJalan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienJalan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienJalan1.setText("1) Rekap Resep Per Pasien");
        jMnRekapTotalPerPasienJalan1.setName("jMnRekapTotalPerPasienJalan1"); // NOI18N
        jMnRekapTotalPerPasienJalan1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapTotalPerPasienJalan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienJalan1ActionPerformed(evt);
            }
        });
        jMenuLapJalanPerDepo.add(jMnRekapTotalPerPasienJalan1);

        jMnRekapDetailTotalPerPasienJalan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienJalan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienJalan1.setText("2) Rekap Detail Resep Per Pasien");
        jMnRekapDetailTotalPerPasienJalan1.setName("jMnRekapDetailTotalPerPasienJalan1"); // NOI18N
        jMnRekapDetailTotalPerPasienJalan1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailTotalPerPasienJalan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienJalan1ActionPerformed(evt);
            }
        });
        jMenuLapJalanPerDepo.add(jMnRekapDetailTotalPerPasienJalan1);

        jMnRekapTotalPerPasienJalanCara1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienJalanCara1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienJalanCara1.setText("3) Rekap Resep Per Pasien Per Cara Bayar");
        jMnRekapTotalPerPasienJalanCara1.setName("jMnRekapTotalPerPasienJalanCara1"); // NOI18N
        jMnRekapTotalPerPasienJalanCara1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapTotalPerPasienJalanCara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienJalanCara1ActionPerformed(evt);
            }
        });
        jMenuLapJalanPerDepo.add(jMnRekapTotalPerPasienJalanCara1);

        jMnRekapDetailTotalPerPasienJalanCara1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara1.setText("4) Rekap Detail Resep Per Pasien Per Cara Bayar");
        jMnRekapDetailTotalPerPasienJalanCara1.setName("jMnRekapDetailTotalPerPasienJalanCara1"); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailTotalPerPasienJalanCara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienJalanCara1ActionPerformed(evt);
            }
        });
        jMenuLapJalanPerDepo.add(jMnRekapDetailTotalPerPasienJalanCara1);

        jMnRekapTotalResepPasienPerCB1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalResepPasienPerCB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalResepPasienPerCB1.setText("5) Rekap Resep Pasien Per Poli Per Cara Bayar");
        jMnRekapTotalResepPasienPerCB1.setName("jMnRekapTotalResepPasienPerCB1"); // NOI18N
        jMnRekapTotalResepPasienPerCB1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapTotalResepPasienPerCB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalResepPasienPerCB1ActionPerformed(evt);
            }
        });
        jMenuLapJalanPerDepo.add(jMnRekapTotalResepPasienPerCB1);

        jMnRekapDetailTotalResepPasienPerCB1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalResepPasienPerCB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalResepPasienPerCB1.setText("6) Rekap Detail Resep Pasien Per Poli Per Cara Bayar");
        jMnRekapDetailTotalResepPasienPerCB1.setName("jMnRekapDetailTotalResepPasienPerCB1"); // NOI18N
        jMnRekapDetailTotalResepPasienPerCB1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailTotalResepPasienPerCB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalResepPasienPerCB1ActionPerformed(evt);
            }
        });
        jMenuLapJalanPerDepo.add(jMnRekapDetailTotalResepPasienPerCB1);

        jMnRekapTotalResepPasienSemuaCB1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalResepPasienSemuaCB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalResepPasienSemuaCB1.setText("7) Rekap Resep Pasien Per Poli Semua Cara Bayar");
        jMnRekapTotalResepPasienSemuaCB1.setName("jMnRekapTotalResepPasienSemuaCB1"); // NOI18N
        jMnRekapTotalResepPasienSemuaCB1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapTotalResepPasienSemuaCB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalResepPasienSemuaCB1ActionPerformed(evt);
            }
        });
        jMenuLapJalanPerDepo.add(jMnRekapTotalResepPasienSemuaCB1);

        jMnRekapDetailTotalResepPasienSemuaCB1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalResepPasienSemuaCB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalResepPasienSemuaCB1.setText("8) Rekap Detail Resep Pasien Per Poli Semua Cara Bayar");
        jMnRekapDetailTotalResepPasienSemuaCB1.setName("jMnRekapDetailTotalResepPasienSemuaCB1"); // NOI18N
        jMnRekapDetailTotalResepPasienSemuaCB1.setPreferredSize(new java.awt.Dimension(320, 25));
        jMnRekapDetailTotalResepPasienSemuaCB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalResepPasienSemuaCB1ActionPerformed(evt);
            }
        });
        jMenuLapJalanPerDepo.add(jMnRekapDetailTotalResepPasienSemuaCB1);

        Popup1.add(jMenuLapJalanPerDepo);

        jMenuLapInapPerDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapInapPerDepo.setText("4) Lap. Rawat Inap Per DEPO");
        jMenuLapInapPerDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapInapPerDepo.setName("jMenuLapInapPerDepo"); // NOI18N
        jMenuLapInapPerDepo.setPreferredSize(new java.awt.Dimension(220, 25));

        jMnRekapTotalPerPasienInap1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienInap1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienInap1.setText("1) Rekap Resep Per Pasien");
        jMnRekapTotalPerPasienInap1.setName("jMnRekapTotalPerPasienInap1"); // NOI18N
        jMnRekapTotalPerPasienInap1.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapTotalPerPasienInap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienInap1ActionPerformed(evt);
            }
        });
        jMenuLapInapPerDepo.add(jMnRekapTotalPerPasienInap1);

        jMnRekapDetailTotalPerPasienInap1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienInap1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienInap1.setText("2) Rekap Detail Resep Per Pasien");
        jMnRekapDetailTotalPerPasienInap1.setName("jMnRekapDetailTotalPerPasienInap1"); // NOI18N
        jMnRekapDetailTotalPerPasienInap1.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienInap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienInap1ActionPerformed(evt);
            }
        });
        jMenuLapInapPerDepo.add(jMnRekapDetailTotalPerPasienInap1);

        jMnRekapTotalPerPasienInapCara1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienInapCara1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienInapCara1.setText("3) Rekap Resep Per Pasien Per Cara Bayar");
        jMnRekapTotalPerPasienInapCara1.setName("jMnRekapTotalPerPasienInapCara1"); // NOI18N
        jMnRekapTotalPerPasienInapCara1.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapTotalPerPasienInapCara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienInapCara1ActionPerformed(evt);
            }
        });
        jMenuLapInapPerDepo.add(jMnRekapTotalPerPasienInapCara1);

        jMnRekapDetailTotalPerPasienInapCara1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara1.setText("4) Rekap Detail Resep Per Pasien Per Cara Bayar");
        jMnRekapDetailTotalPerPasienInapCara1.setName("jMnRekapDetailTotalPerPasienInapCara1"); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara1.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienInapCara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienInapCara1ActionPerformed(evt);
            }
        });
        jMenuLapInapPerDepo.add(jMnRekapDetailTotalPerPasienInapCara1);

        Popup1.add(jMenuLapInapPerDepo);

        jMenuLapAllRawatPerDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapAllRawatPerDepo.setText("5) Lap. Semua Rawat Per DEPO");
        jMenuLapAllRawatPerDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapAllRawatPerDepo.setName("jMenuLapAllRawatPerDepo"); // NOI18N
        jMenuLapAllRawatPerDepo.setPreferredSize(new java.awt.Dimension(220, 25));

        jMnRekapTotalPerPasienJalan2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienJalan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienJalan2.setText("1) Rekap Resep Per Pasien");
        jMnRekapTotalPerPasienJalan2.setName("jMnRekapTotalPerPasienJalan2"); // NOI18N
        jMnRekapTotalPerPasienJalan2.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapTotalPerPasienJalan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienJalan2ActionPerformed(evt);
            }
        });
        jMenuLapAllRawatPerDepo.add(jMnRekapTotalPerPasienJalan2);

        jMnRekapDetailTotalPerPasienJalan2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienJalan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienJalan2.setText("2) Rekap Detail Resep Per Pasien");
        jMnRekapDetailTotalPerPasienJalan2.setName("jMnRekapDetailTotalPerPasienJalan2"); // NOI18N
        jMnRekapDetailTotalPerPasienJalan2.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienJalan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienJalan2ActionPerformed(evt);
            }
        });
        jMenuLapAllRawatPerDepo.add(jMnRekapDetailTotalPerPasienJalan2);

        jMnRekapTotalPerPasienJalanCara2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienJalanCara2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienJalanCara2.setText("3) Rekap Resep Per Pasien Per Cara Bayar");
        jMnRekapTotalPerPasienJalanCara2.setName("jMnRekapTotalPerPasienJalanCara2"); // NOI18N
        jMnRekapTotalPerPasienJalanCara2.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapTotalPerPasienJalanCara2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienJalanCara2ActionPerformed(evt);
            }
        });
        jMenuLapAllRawatPerDepo.add(jMnRekapTotalPerPasienJalanCara2);

        jMnRekapDetailTotalPerPasienJalanCara2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara2.setText("4) Rekap Detail Resep Per Pasien Per Cara Bayar");
        jMnRekapDetailTotalPerPasienJalanCara2.setName("jMnRekapDetailTotalPerPasienJalanCara2"); // NOI18N
        jMnRekapDetailTotalPerPasienJalanCara2.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienJalanCara2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienJalanCara2ActionPerformed(evt);
            }
        });
        jMenuLapAllRawatPerDepo.add(jMnRekapDetailTotalPerPasienJalanCara2);

        Popup1.add(jMenuLapAllRawatPerDepo);

        jMenuLapSemuaAllDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapSemuaAllDepo.setText("6) Lap. Semua Rawat Semua DEPO");
        jMenuLapSemuaAllDepo.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapSemuaAllDepo.setName("jMenuLapSemuaAllDepo"); // NOI18N
        jMenuLapSemuaAllDepo.setPreferredSize(new java.awt.Dimension(220, 25));

        jMnRekapTotalPerPasienInap2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienInap2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienInap2.setText("1) Rekap Resep Per Pasien");
        jMnRekapTotalPerPasienInap2.setName("jMnRekapTotalPerPasienInap2"); // NOI18N
        jMnRekapTotalPerPasienInap2.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapTotalPerPasienInap2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienInap2ActionPerformed(evt);
            }
        });
        jMenuLapSemuaAllDepo.add(jMnRekapTotalPerPasienInap2);

        jMnRekapDetailTotalPerPasienInap2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienInap2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienInap2.setText("2) Rekap Detail Resep Per Pasien");
        jMnRekapDetailTotalPerPasienInap2.setName("jMnRekapDetailTotalPerPasienInap2"); // NOI18N
        jMnRekapDetailTotalPerPasienInap2.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienInap2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienInap2ActionPerformed(evt);
            }
        });
        jMenuLapSemuaAllDepo.add(jMnRekapDetailTotalPerPasienInap2);

        jMnRekapTotalPerPasienInapCara2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapTotalPerPasienInapCara2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapTotalPerPasienInapCara2.setText("3) Rekap Resep Per Pasien Per Cara Bayar");
        jMnRekapTotalPerPasienInapCara2.setName("jMnRekapTotalPerPasienInapCara2"); // NOI18N
        jMnRekapTotalPerPasienInapCara2.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapTotalPerPasienInapCara2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapTotalPerPasienInapCara2ActionPerformed(evt);
            }
        });
        jMenuLapSemuaAllDepo.add(jMnRekapTotalPerPasienInapCara2);

        jMnRekapDetailTotalPerPasienInapCara2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara2.setText("4) Rekap Detail Resep Per Pasien Per Cara Bayar");
        jMnRekapDetailTotalPerPasienInapCara2.setToolTipText("");
        jMnRekapDetailTotalPerPasienInapCara2.setName("jMnRekapDetailTotalPerPasienInapCara2"); // NOI18N
        jMnRekapDetailTotalPerPasienInapCara2.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapDetailTotalPerPasienInapCara2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapDetailTotalPerPasienInapCara2ActionPerformed(evt);
            }
        });
        jMenuLapSemuaAllDepo.add(jMnRekapDetailTotalPerPasienInapCara2);

        jMnRekapPemakaianObatKhusus1Px.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnRekapPemakaianObatKhusus1Px.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMnRekapPemakaianObatKhusus1Px.setText("5) Rekap Pmkaian. Obat Utk. 1 Pasien Per Cara Bayar");
        jMnRekapPemakaianObatKhusus1Px.setToolTipText("");
        jMnRekapPemakaianObatKhusus1Px.setName("jMnRekapPemakaianObatKhusus1Px"); // NOI18N
        jMnRekapPemakaianObatKhusus1Px.setPreferredSize(new java.awt.Dimension(290, 25));
        jMnRekapPemakaianObatKhusus1Px.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnRekapPemakaianObatKhusus1PxActionPerformed(evt);
            }
        });
        jMenuLapSemuaAllDepo.add(jMnRekapPemakaianObatKhusus1Px);

        Popup1.add(jMenuLapSemuaAllDepo);

        jMenuLapPemakaian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMenuLapPemakaian.setText("7) Lap. Pemakaian Obat");
        jMenuLapPemakaian.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuLapPemakaian.setName("jMenuLapPemakaian"); // NOI18N
        jMenuLapPemakaian.setPreferredSize(new java.awt.Dimension(220, 25));

        jMenuPerDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuPerDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMenuPerDokter.setText("1) Obat Per Dokter");
        jMenuPerDokter.setName("jMenuPerDokter"); // NOI18N
        jMenuPerDokter.setPreferredSize(new java.awt.Dimension(190, 25));
        jMenuPerDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPerDokterActionPerformed(evt);
            }
        });
        jMenuLapPemakaian.add(jMenuPerDokter);

        jMenuObatKhususPerTgl.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuObatKhususPerTgl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMenuObatKhususPerTgl.setText("2) Obat Khusus PerTanggal");
        jMenuObatKhususPerTgl.setName("jMenuObatKhususPerTgl"); // NOI18N
        jMenuObatKhususPerTgl.setPreferredSize(new java.awt.Dimension(190, 25));
        jMenuObatKhususPerTgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuObatKhususPerTglActionPerformed(evt);
            }
        });
        jMenuLapPemakaian.add(jMenuObatKhususPerTgl);

        jMenuObatKhusus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenuObatKhusus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        jMenuObatKhusus.setText("3) Obat Khusus");
        jMenuObatKhusus.setName("jMenuObatKhusus"); // NOI18N
        jMenuObatKhusus.setPreferredSize(new java.awt.Dimension(190, 25));
        jMenuObatKhusus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuObatKhususActionPerformed(evt);
            }
        });
        jMenuLapPemakaian.add(jMenuObatKhusus);

        Popup1.add(jMenuLapPemakaian);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Laporan Farmasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(Popup1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang ataupun dihapus");
        tbObat.setComponentPopupMenu(Popup1);
        tbObat.setName("tbObat"); // NOI18N
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
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(450, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
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
        panelGlass10.add(BtnCari);

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

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Limit Data :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel8);

        limitData.setForeground(new java.awt.Color(0, 0, 0));
        limitData.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20", "50", "100", "200", "500", "1000", "Semua" }));
        limitData.setName("limitData"); // NOI18N
        limitData.setPreferredSize(new java.awt.Dimension(80, 20));
        limitData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                limitDataMouseClicked(evt);
            }
        });
        limitData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limitDataActionPerformed(evt);
            }
        });
        limitData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                limitDataKeyPressed(evt);
            }
        });
        panelGlass10.add(limitData);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 160));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Input Data");
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
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Pilihan Cara Bayar :");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(94, 12));
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 10, 120, 20);

        kdpnj.setEditable(false);
        kdpnj.setForeground(new java.awt.Color(0, 0, 0));
        kdpnj.setCaretColor(new java.awt.Color(0, 0, 0));
        kdpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpnj.setEnabled(false);
        kdpnj.setHighlighter(null);
        kdpnj.setName("kdpnj"); // NOI18N
        kdpnj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpnjKeyPressed(evt);
            }
        });
        FormInput.add(kdpnj);
        kdpnj.setBounds(127, 10, 70, 23);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setCaretColor(new java.awt.Color(0, 0, 0));
        nmpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nmpnj.setEnabled(false);
        nmpnj.setName("nmpnj"); // NOI18N
        FormInput.add(nmpnj);
        nmpnj.setBounds(200, 10, 240, 23);

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
        FormInput.add(btnPenjab);
        btnPenjab.setBounds(444, 10, 28, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Pilihan Unit Farmasi :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 40, 120, 20);

        KdGudang.setEditable(false);
        KdGudang.setForeground(new java.awt.Color(0, 0, 0));
        KdGudang.setCaretColor(new java.awt.Color(0, 0, 0));
        KdGudang.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        KdGudang.setEnabled(false);
        KdGudang.setName("KdGudang"); // NOI18N
        KdGudang.setPreferredSize(new java.awt.Dimension(80, 23));
        KdGudang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdGudangKeyPressed(evt);
            }
        });
        FormInput.add(KdGudang);
        KdGudang.setBounds(127, 40, 70, 23);

        NmGudang.setEditable(false);
        NmGudang.setForeground(new java.awt.Color(0, 0, 0));
        NmGudang.setCaretColor(new java.awt.Color(0, 0, 0));
        NmGudang.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        NmGudang.setEnabled(false);
        NmGudang.setName("NmGudang"); // NOI18N
        NmGudang.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmGudang);
        NmGudang.setBounds(200, 40, 240, 23);

        btnBarang1.setForeground(new java.awt.Color(0, 0, 0));
        btnBarang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnBarang1.setMnemonic('1');
        btnBarang1.setToolTipText("Alt+1");
        btnBarang1.setName("btnBarang1"); // NOI18N
        btnBarang1.setPreferredSize(new java.awt.Dimension(28, 23));
        btnBarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarang1ActionPerformed(evt);
            }
        });
        FormInput.add(btnBarang1);
        btnBarang1.setBounds(444, 40, 28, 23);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("Nama Unit/Poliklinik :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(110, 23));
        FormInput.add(label19);
        label19.setBounds(0, 70, 120, 20);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setCaretColor(new java.awt.Color(0, 0, 0));
        kdpoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpoli.setEnabled(false);
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.setPreferredSize(new java.awt.Dimension(55, 23));
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });
        FormInput.add(kdpoli);
        kdpoli.setBounds(127, 70, 70, 23);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setCaretColor(new java.awt.Color(0, 0, 0));
        TPoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TPoli.setEnabled(false);
        TPoli.setName("TPoli"); // NOI18N
        TPoli.setPreferredSize(new java.awt.Dimension(203, 23));
        FormInput.add(TPoli);
        TPoli.setBounds(200, 70, 240, 23);

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
        FormInput.add(BtnUnit);
        BtnUnit.setBounds(444, 70, 30, 26);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tanggal Laporan :");
        jLabel14.setToolTipText("");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(58, 23));
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 100, 120, 23);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-03-2020" }));
        DTPCari1.setToolTipText("");
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(DTPCari1);
        DTPCari1.setBounds(127, 100, 100, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("s.d");
        jLabel19.setToolTipText("");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(18, 23));
        FormInput.add(jLabel19);
        jLabel19.setBounds(229, 100, 25, 23);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-03-2020" }));
        DTPCari2.setToolTipText("");
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(DTPCari2);
        DTPCari2.setBounds(257, 100, 100, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
//            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnBatal,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
//            Valid.pindah(evt, BtnCari, kdfaskes);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

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
        akses.setform("DlgLaporanFarmasi");
        pasien.penjab.onCari();
        pasien.penjab.isCek();
        pasien.penjab.setSize(882, 593);
        pasien.penjab.setLocationRelativeTo(internalFrame1);
        pasien.penjab.setVisible(true);
        pasien.penjab.onCari();
    }//GEN-LAST:event_btnPenjabActionPerformed

    private void KdGudangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdGudangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdGudangKeyPressed

    private void btnBarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarang1ActionPerformed
        akses.setform("DlgLaporanFarmasi");
        bangsal.emptTeks();
        bangsal.isCek();
        bangsal.setSize(1046, 400);
        bangsal.setLocationRelativeTo(internalFrame1);
        bangsal.setAlwaysOnTop(false);
        bangsal.setVisible(true);
        bangsal.emptTeks();
    }//GEN-LAST:event_btnBarang1ActionPerformed

    private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            //            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", TPoli, kdpoli.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            //            BtnUnitActionPerformed(null);
        } else {
            //            Valid.pindah(evt, kddokter, TNoRM);
        }
    }//GEN-LAST:event_kdpoliKeyPressed

    private void BtnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnitActionPerformed
        TPoli.setText("");
        kdpoli.setText("");

        akses.setform("DlgLaporanFarmasi");
        poli.isCek();
        poli.setSize(1055, 400);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
        poli.emptTeks();
    }//GEN-LAST:event_BtnUnitActionPerformed

    private void jMnRekapTotalPerPasienJalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienJalanActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT COUNT(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                + " AND bangsal.kd_bangsal in ('APT01','APT02')) AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ralan' "
                + " AND a.kd_bangsal in ('APT01','APT02')");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRJRTPerPasienSemuaDepo.jasper", "report", "::[ Laporan Rekap Total Resep Rawat Jalan Per Pasien Semua Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                    + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02')) AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ralan' "
                    + " AND a.kd_bangsal in ('APT01','APT02') group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapTotalPerPasienJalanActionPerformed

    private void jMnRekapDetailTotalPerPasienJalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienJalanActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                + " AND bangsal.kd_bangsal in ('APT01','APT02')) and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.status_lanjut = 'Ralan' AND a.kd_bangsal in ('APT01','APT02')");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDRTPerPasienSemuaDepo1.jasper", "report", "::[ Laporan Detail Rekap Total Resep Rawat Jalan Per Pasien Semua Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02')) and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.status_lanjut = 'Ralan' AND a.kd_bangsal in ('APT01','APT02') ORDER BY a.png_jawab, a.no_resep", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienJalanActionPerformed

    private void jMnRekapTotalPerPasienJalanCaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienJalanCaraActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02') AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ralan' "
                    + " AND a.kd_bangsal in ('APT01','APT02') AND a.kd_pj='" + kdpnj.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRJRTPerPasienSemuaDepoPerCB.jasper", "report", "::[ Laporan Rekap Total Resep Rawat Jalan Per Pasien Per Cara Bayar Semua Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                    + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02') AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ralan' "
                    + " AND a.kd_bangsal in ('APT01','APT02') AND a.kd_pj='" + kdpnj.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapTotalPerPasienJalanCaraActionPerformed

    private void jMnRekapDetailTotalPerPasienJalanCaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienJalanCaraActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02') AND penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.status_lanjut = 'Ralan' AND a.kd_bangsal in ('APT01','APT02') AND a.kd_pj='" + kdpnj.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDRTPerPasienSemuaDepoPerCB1.jasper", "report", "::[ Laporan Detail Rekap Total Resep Rawat Jalan Per Pasien Per Cara Bayar Semua Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02') AND penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.status_lanjut = 'Ralan' AND a.kd_bangsal in ('APT01','APT02') AND a.kd_pj='" + kdpnj.getText() + "' ORDER BY a.png_jawab, a.no_resep ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienJalanCaraActionPerformed

    private void jMnRekapTotalResepPasienPerCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalResepPasienPerCBActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
                    + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
                    + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
                    + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "' ");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("nm_poli", TPoli.getText());
            Valid.MyReport("rptRekapTotalResepPerPoliPerCB.jasper", "report", "::[ Laporan Rekap Resep Pasien Rawat Jalan Semua Depo Per Poli Per Cara Bayar ]::",
                    " SELECT (SELECT MIN(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
                    + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, "
                    + " sum(detail_pemberian_obat.biaya_obat) as biaya_obat, sum(detail_pemberian_obat.embalase) as embalase, sum(detail_pemberian_obat.tuslah) as tuslah, "
                    + " sum((detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah))) AS total "
                    + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
                    + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
                    + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
                    + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "' "
                    + " GROUP BY resep_obat.no_resep ORDER BY penjab.png_jawab,resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapTotalResepPasienPerCBActionPerformed

    private void jMnRekapDetailResepPasienPerCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailResepPasienPerCBActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
                    + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
                    + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
                    + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "' ");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("nm_poli", TPoli.getText());
            Valid.MyReport("rptRekapDetailResepPerPoliPerCB.jasper", "report", "::[ Laporan Rekap Detail Resep Pasien Rawat Jalan Semua Depo Per Poli Per Cara Bayar ]::",
                    " SELECT (SELECT MIN(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
                    + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, databarang.kode_sat, databarang.nama_brng, detail_pemberian_obat.jml, "
                    + " detail_pemberian_obat.biaya_obat, detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, (detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah)) AS total "
                    + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
                    + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
                    + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
                    + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "' "
                    + " ORDER BY penjab.png_jawab, resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapDetailResepPasienPerCBActionPerformed

    private void jMnRekapTotalResepPasienSemuaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalResepPasienSemuaCBActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
                    + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
                    + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
                    + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' ");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("nm_poli", TPoli.getText());
            Valid.MyReport("rptRekapTotalResepPerPoliSemuaCB.jasper", "report", "::[ Laporan Rekap Resep Pasien Rawat Jalan Semua Depo Per Poli Semua Cara Bayar ]::",
                    " SELECT (SELECT MIN(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
                    + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, "
                    + " sum(detail_pemberian_obat.biaya_obat) as biaya_obat, sum(detail_pemberian_obat.embalase) as embalase, sum(detail_pemberian_obat.tuslah) as tuslah, "
                    + " sum((detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah))) AS total "
                    + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
                    + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
                    + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
                    + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' "
                    + " GROUP BY resep_obat.no_resep ORDER BY penjab.png_jawab,resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapTotalResepPasienSemuaCBActionPerformed

    private void jMnRekapDetailResepPasienSemuaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailResepPasienSemuaCBActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
                    + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
                    + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
                    + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' ");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("nm_poli", TPoli.getText());
            Valid.MyReport("rptRekapDetailResepPerPoliSemuaCB.jasper", "report", "::[ Laporan Rekap Detail Resep Pasien Rawat Jalan Semua Depo Per Poli Semua Cara Bayar ]::",
                    " SELECT (SELECT MIN(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
                    + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, databarang.kode_sat, databarang.nama_brng, detail_pemberian_obat.jml, "
                    + " detail_pemberian_obat.biaya_obat, detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, (detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah)) AS total "
                    + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
                    + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
                    + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
                    + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' "
                    + " ORDER BY penjab.png_jawab, resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapDetailResepPasienSemuaCBActionPerformed

    private void jMnRekapTotalPerPasienInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienInapActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02')) AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ranap' "
                    + " AND a.kd_bangsal in ('APT01','APT02') ");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRIRTPerPasienSemuaDepo.jasper", "report", "::[ Laporan Rekap Total Resep Rawat Inap Per Pasien Semua Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab,	a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                    + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02')) AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ranap' "
                    + " AND a.kd_bangsal in ('APT01','APT02') group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapTotalPerPasienInapActionPerformed

    private void jMnRekapDetailTotalPerPasienInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienInapActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02')) and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.status_lanjut = 'Ranap' AND a.kd_bangsal in ('APT01','APT02') ");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDRTPerPasienSemuaDepo2.jasper", "report", "::[ Laporan Detail Rekap Total Resep Rawat Inap Per Pasien Semua Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02')) and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.status_lanjut = 'Ranap' AND a.kd_bangsal in ('APT01','APT02') ORDER BY a.png_jawab, a.no_resep", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienInapActionPerformed

    private void jMnRekapTotalPerPasienInapCaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienInapCaraActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02') AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ranap' "
                    + " AND a.kd_bangsal in ('APT01','APT02') AND a.kd_pj='" + kdpnj.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRIRTPerPasienSemuaDepoPerCB.jasper", "report", "::[ Laporan Rekap Total Resep Rawat Inap Per Pasien Per Cara Bayar Semua Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                    + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02') AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ranap' "
                    + " AND a.kd_bangsal in ('APT01','APT02') AND a.kd_pj='" + kdpnj.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapTotalPerPasienInapCaraActionPerformed

    private void jMnRekapDetailTotalPerPasienInapCaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienInapCaraActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02') and penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.status_lanjut = 'Ranap' AND a.kd_bangsal in ('APT01','APT02') and a.kd_pj='" + kdpnj.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDRTPerPasienSemuaDepoPerCB2.jasper", "report", "::[ Laporan Detail Rekap Total Resep Rawat Inap Per Pasien Per Cara Bayar Semua Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02') and penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.status_lanjut = 'Ranap' AND a.kd_bangsal in ('APT01','APT02') and a.kd_pj='" + kdpnj.getText() + "' ORDER BY a.png_jawab, a.no_resep ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienInapCaraActionPerformed

    private void jMnRekapTotalPerPasienJalan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienJalan1ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                    + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ralan' "
                    + " AND a.kd_bangsal='" + KdGudang.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRJRTPerPasienPerDepo.jasper", "report", "::[ Laporan Rekap Total Resep Rawat Jalan Per Pasien Per Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab,	a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                    + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                    + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ralan' "
                    + " AND a.kd_bangsal='" + KdGudang.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapTotalPerPasienJalan1ActionPerformed

    private void jMnRekapDetailTotalPerPasienJalan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienJalan1ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                    + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.status_lanjut = 'Ralan' AND a.kd_bangsal='" + KdGudang.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDRTPerPasienPerDepo1.jasper", "report", "::[ Laporan Detail Rekap Total Resep Rawat Jalan Per Pasien Per Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                    + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.status_lanjut = 'Ralan' AND a.kd_bangsal='" + KdGudang.getText() + "' ORDER BY a.png_jawab, a.no_resep", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienJalan1ActionPerformed

    private void jMnRekapTotalPerPasienJalanCara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienJalanCara1ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                    + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ralan' "
                    + " AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRJRTPerPasienPerDepoPerCB.jasper", "report", "::[ Laporan Rekap Total Resep Rawat Jalan Per Pasien Per Cara Bayar Per Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                    + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                    + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ralan' "
                    + " AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapTotalPerPasienJalanCara1ActionPerformed

    private void jMnRekapDetailTotalPerPasienJalanCara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienJalanCara1ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                    + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.status_lanjut = 'Ralan' AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDRTPerPasienPerDepoPerCB1.jasper", "report", "::[ Laporan Detail Rekap Total Resep Rawat Jalan Per Pasien Per Cara Bayar Per Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ralan' "
                    + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.status_lanjut = 'Ralan' AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "' ORDER BY a.png_jawab, a.no_resep ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienJalanCara1ActionPerformed

    private void jMnRekapTotalResepPasienPerCB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalResepPasienPerCB1ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
                    + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
                    + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
                    + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND detail_pemberian_obat.kd_bangsal='" + KdGudang.getText() + "' AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("nm_unit", NmGudang.getText());
            param.put("nm_poli", TPoli.getText());
            Valid.MyReport("rptRekapTotalResepPerPoliPerCBPerDepo.jasper", "report", "::[ Laporan Rekap Resep Pasien Rawat Jalan Per Depo Per Poli Per Cara Bayar ]::",
                    " SELECT (SELECT MIN(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
                    + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, "
                    + " sum(detail_pemberian_obat.biaya_obat) as biaya_obat, sum(detail_pemberian_obat.embalase) as embalase, sum(detail_pemberian_obat.tuslah) as tuslah, "
                    + " sum((detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah))) AS total "
                    + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
                    + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
                    + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
                    + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND detail_pemberian_obat.kd_bangsal='" + KdGudang.getText() + "' AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "' "
                    + " GROUP BY resep_obat.no_resep ORDER BY penjab.png_jawab,resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapTotalResepPasienPerCB1ActionPerformed

    private void jMnRekapDetailTotalResepPasienPerCB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalResepPasienPerCB1ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
                + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
                + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
                + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND detail_pemberian_obat.kd_bangsal='" + KdGudang.getText() + "' AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("nm_unit", NmGudang.getText());
            param.put("nm_poli", TPoli.getText());
            Valid.MyReport("rptRekapDetailResepPerPoliPerCBPerDepo.jasper", "report", "::[ Laporan Rekap Detail Resep Pasien Rawat Jalan Per Depo Per Poli Per Cara Bayar ]::",
                    " SELECT (SELECT MIN(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
                    + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, databarang.kode_sat, databarang.nama_brng, detail_pemberian_obat.jml, "
                    + " detail_pemberian_obat.biaya_obat, detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, (detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah)) AS total "
                    + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
                    + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
                    + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
                    + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND detail_pemberian_obat.kd_bangsal='" + KdGudang.getText() + "' AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "' "
                    + " ORDER BY penjab.png_jawab, resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapDetailTotalResepPasienPerCB1ActionPerformed

    private void jMnRekapTotalResepPasienSemuaCB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalResepPasienSemuaCB1ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
                + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
                + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
                + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND detail_pemberian_obat.kd_bangsal='" + KdGudang.getText() + "' AND reg_periksa.kd_poli = '" + kdpoli.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("nm_poli", TPoli.getText());
            param.put("nm_unit", NmGudang.getText());
            Valid.MyReport("rptRekapTotalResepPerPoliSemuaCBPerDepo.jasper", "report", "::[ Laporan Rekap Resep Pasien Rawat Jalan Per Depo Per Poli Semua Cara Bayar ]::",
                    " SELECT (SELECT MIN(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
                    + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, "
                    + " sum(detail_pemberian_obat.biaya_obat) as biaya_obat, sum(detail_pemberian_obat.embalase) as embalase, sum(detail_pemberian_obat.tuslah) as tuslah, "
                    + " sum((detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah))) AS total "
                    + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
                    + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
                    + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
                    + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND detail_pemberian_obat.kd_bangsal='" + KdGudang.getText() + "' AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' "
                    + " GROUP BY resep_obat.no_resep ORDER BY penjab.png_jawab,resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapTotalResepPasienSemuaCB1ActionPerformed

    private void jMnRekapDetailTotalResepPasienSemuaCB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalResepPasienSemuaCB1ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
                + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
                + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
                + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND detail_pemberian_obat.kd_bangsal='" + KdGudang.getText() + "' AND reg_periksa.kd_poli = '" + kdpoli.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("nm_poli", TPoli.getText());
            param.put("nm_unit", NmGudang.getText());
            Valid.MyReport("rptRekapDetailResepPerPoliSemuaCBPerDepo.jasper", "report", "::[ Laporan Rekap Detail Resep Pasien Rawat Jalan Per Depo Per Poli Semua Cara Bayar ]::",
                    " SELECT (SELECT MIN(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " IFNULL(bridging_sep.no_kartu,'-') No_kartu, IFNULL(bridging_sep.no_sep,'-') No_SEP, resep_obat.no_resep, resep_obat.tgl_perawatan, "
                    + " resep_obat.no_rawat, penjab.png_jawab, reg_periksa.no_rkm_medis, pasien.nm_pasien, dokter.nm_dokter, databarang.kode_sat, databarang.nama_brng, detail_pemberian_obat.jml, "
                    + " detail_pemberian_obat.biaya_obat, detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, (detail_pemberian_obat.total-(detail_pemberian_obat.embalase + detail_pemberian_obat.tuslah)) AS total "
                    + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter inner join penjab "
                    + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat AND resep_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter and penjab.kd_pj = reg_periksa.kd_pj "
                    + " left join bridging_sep on bridging_sep.no_rawat = resep_obat.no_rawat "
                    + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND detail_pemberian_obat.kd_bangsal='" + KdGudang.getText() + "' AND reg_periksa.kd_poli = '" + kdpoli.getText() + "' "
                    + " ORDER BY penjab.png_jawab, resep_obat.tgl_perawatan, reg_periksa.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapDetailTotalResepPasienSemuaCB1ActionPerformed

    private void jMnRekapTotalPerPasienInap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienInap1ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ranap' "
                + " AND a.kd_bangsal='" + KdGudang.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRIRTPerPasienPerDepo.jasper", "report", "::[ Laporan Rekap Total Resep Rawat Inap Per Pasien Per Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab,	a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                    + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                    + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ranap' "
                    + " AND a.kd_bangsal='" + KdGudang.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapTotalPerPasienInap1ActionPerformed

    private void jMnRekapDetailTotalPerPasienInap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienInap1ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.status_lanjut = 'Ranap' AND a.kd_bangsal='" + KdGudang.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDRTPerPasienPerDepo2.jasper", "report", "::[ Laporan Detail Rekap Total Resep Rawat Inap Per Pasien Per Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                    + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.status_lanjut = 'Ranap' AND a.kd_bangsal='" + KdGudang.getText() + "' ORDER BY a.png_jawab, a.no_resep", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienInap1ActionPerformed

    private void jMnRekapTotalPerPasienInapCara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienInapCara1ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ranap' "
                + " AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRIRTPerPasienPerDepoPerCB.jasper", "report", "::[ Laporan Rekap Total Resep Rawat Inap Per Pasien Per Cara Bayar Per Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                    + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                    + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND a.status_lanjut = 'Ranap' "
                    + " AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapTotalPerPasienInapCara1ActionPerformed

    private void jMnRekapDetailTotalPerPasienInapCara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienInapCara1ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.status_lanjut = 'Ranap' AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDRTPerPasienPerDepoPerCB2.jasper", "report", "::[ Laporan Detail Rekap Total Resep Rawat Inap Per Pasien Per Cara Bayar Per Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " IF (a.status_lanjut = 'RANAP', b.nm_bangsal, a.nm_poli) unit, a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND reg_periksa.status_lanjut = 'Ranap' "
                    + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.status_lanjut = 'Ranap' AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "' ORDER BY a.png_jawab, a.no_resep ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienInapCara1ActionPerformed

    private void jMnRekapTotalPerPasienJalan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienJalan2ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.kd_bangsal='" + KdGudang.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSJRRTPerPasienPerDepo.jasper", "report", "::[ Laporan Rekap Total Resep Semua Jenis Rawat Per Pasien Per Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                    + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.kd_bangsal='" + KdGudang.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapTotalPerPasienJalan2ActionPerformed

    private void jMnRekapDetailTotalPerPasienJalan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienJalan2ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.kd_bangsal='" + KdGudang.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDRTPerPasienPerDepoSR.jasper", "report", "::[ Laporan Detail Rekap Total Resep Semua Jenis Rawat Per Pasien Per Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.kd_bangsal='" + KdGudang.getText() + "' ORDER BY a.png_jawab, a.no_resep", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienJalan2ActionPerformed

    private void jMnRekapTotalPerPasienJalanCara2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienJalanCara2ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSJRRTPerPasienPerDepoPerCB.jasper", "report", "::[ Laporan Rekap Total Resep Semua Jenis Rawat Per Pasien Per Cara Bayar Per Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                    + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapTotalPerPasienJalanCara2ActionPerformed

    private void jMnRekapDetailTotalPerPasienJalanCara2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienJalanCara2ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDRTPerPasienPerDepoPerCBSR.jasper", "report", "::[ Laporan Detail Rekap Total Resep Semua Jenis Rawat Per Pasien Per Cara Bayar Per Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND bangsal.kd_bangsal='" + KdGudang.getText() + "' AND penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.kd_bangsal='" + KdGudang.getText() + "' AND a.kd_pj='" + kdpnj.getText() + "' ORDER BY a.png_jawab, a.no_resep ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienJalanCara2ActionPerformed

    private void jMnRekapTotalPerPasienInap2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienInap2ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND bangsal.kd_bangsal in ('APT01','APT02')) AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.kd_bangsal in ('APT01','APT02')");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSJRRTPerPasienSemuaDepo.jasper", "report", "::[ Laporan Rekap Total Resep Semua Jenis Rawat Per Pasien Semua Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                    + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02')) AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.kd_bangsal in ('APT01','APT02') group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapTotalPerPasienInap2ActionPerformed

    private void jMnRekapDetailTotalPerPasienInap2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienInap2ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek "
                + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND bangsal.kd_bangsal in ('APT01','APT02')) and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.kd_bangsal in ('APT01','APT02')");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDRTPerPasienSemuaDepoSR.jasper", "report", "::[ Laporan Detail Rekap Total Resep Semua Jenis Rawat Per Pasien Semua Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, bangsal.nm_bangsal "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02')) and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.kd_bangsal in ('APT01','APT02') ORDER BY a.png_jawab, a.no_resep", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienInap2ActionPerformed

    private void jMnRekapTotalPerPasienInapCara2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapTotalPerPasienInapCara2ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal FROM "
                + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND bangsal.kd_bangsal in ('APT01','APT02') AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.kd_bangsal in ('APT01','APT02') AND a.kd_pj='" + kdpnj.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSJRRTPerPasienSemuaDepoPerCB.jasper", "report", "::[ Laporan Rekap Total Resep Semua Jenis Rawat Per Pasien Per Cara Bayar Semua Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " sum(a.sub_total) T_sub_total, sum(a.embalase) T_embalase, sum(a.tuslah) T_tuslah, "
                    + " (sum(a.sub_total)+sum(a.embalase)+sum(a.tuslah)) T_total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, "
                    + " databarang.nama_brng, databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal FROM "
                    + " detail_pemberian_obat INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk	FROM kamar_inap p "
                    + " INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal "
                    + " WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02') AND penjab.kd_pj='" + kdpnj.getText() + "') AND p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) "
                    + " WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.kd_bangsal in ('APT01','APT02') AND a.kd_pj='" + kdpnj.getText() + "' group by a.no_resep ORDER BY a.png_jawab, a.tgl_perawatan, a.no_rkm_medis", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapTotalPerPasienInapCara2ActionPerformed

    private void jMnRekapDetailTotalPerPasienInapCara2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapDetailTotalPerPasienInapCara2ActionPerformed
        cekData = 0;
        cekData = Sequel.cariInteger("SELECT count(1) cek FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                + " FROM detail_pemberian_obat "
                + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                + " AND detail_pemberian_obat.jam = resep_obat.jam "
                + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                + " AND riwayat_obat_pasien.`status` = 'simpan' "
                + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND bangsal.kd_bangsal in ('APT01','APT02') AND penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " AND a.kd_bangsal in ('APT01','APT02') AND a.kd_pj='" + kdpnj.getText() + "'");

        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, cek lagi tanggal laporannya...!!!");
        } else if (cekData > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDRTPerPasienSemuaDepoPerCBSR.jasper", "report", "::[ Laporan Detail Rekap Total Resep Semua Jenis Rawat Per Pasien Per Cara Bayar Semua Depo ]::",
                    " SELECT a.tgl_awal, a.tgl_akhir, a.tgl_perawatan, a.no_rawat, a.no_resep, a.nm_bangsal as apotek, a.nm_dokter, a.no_rkm_medis, a.nm_pasien, a.png_jawab, a.status_lanjut, "
                    + " a.apotek, a.nama_brng, a.kode_sat, a.hrg_jual, a.jml, a.sub_total, a.embalase, a.tuslah, a.total "
                    + " FROM ((SELECT (SELECT MIN(tgl_perawatan) FROM detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                    + " (SELECT MAX(tgl_perawatan) FROM  detail_pemberian_obat WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                    + " detail_pemberian_obat.tgl_perawatan, detail_pemberian_obat.no_rawat, IFNULL(resep_obat.no_resep, '-') no_resep, IFNULL(dokter.nm_dokter, '-') nm_dokter, "
                    + " reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, reg_periksa.status_lanjut, poliklinik.nm_poli, bangsal.nm_bangsal AS apotek, databarang.nama_brng, "
                    + " databarang.kode_sat, detail_pemberian_obat.biaya_obat AS hrg_jual, detail_pemberian_obat.jml, (detail_pemberian_obat.biaya_obat * detail_pemberian_obat.jml) AS sub_total, "
                    + " detail_pemberian_obat.embalase, detail_pemberian_obat.tuslah, detail_pemberian_obat.total, bangsal.kd_bangsal, penjab.kd_pj, bangsal.nm_bangsal "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " ORDER BY penjab.png_jawab, resep_obat.no_resep, detail_pemberian_obat.no_rawat) AS a "
                    + " LEFT JOIN (SELECT p.no_rawat, b2.nm_bangsal, p.tgl_masuk, p.jam_masuk FROM kamar_inap p INNER JOIN kamar k ON k.kd_kamar = p.kd_kamar "
                    + " INNER JOIN bangsal b2 ON b2.kd_bangsal = k.kd_bangsal WHERE p.no_rawat IN (SELECT DISTINCT detail_pemberian_obat.no_rawat "
                    + " FROM detail_pemberian_obat "
                    + " INNER JOIN reg_periksa ON reg_periksa.no_rawat = detail_pemberian_obat.no_rawat "
                    + " INNER JOIN poliklinik ON poliklinik.kd_poli = reg_periksa.kd_poli "
                    + " INNER JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " INNER JOIN databarang ON detail_pemberian_obat.no_rawat = reg_periksa.no_rawat "
                    + " AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND detail_pemberian_obat.kode_brng = databarang.kode_brng "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " LEFT JOIN resep_obat ON resep_obat.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan "
                    + " AND detail_pemberian_obat.jam = resep_obat.jam "
                    + " LEFT JOIN dokter ON resep_obat.kd_dokter = dokter.kd_dokter "
                    + " LEFT JOIN riwayat_obat_pasien ON riwayat_obat_pasien.kode_brng = detail_pemberian_obat.kode_brng "
                    + " AND riwayat_obat_pasien.tanggal = detail_pemberian_obat.tgl_perawatan "
                    + " AND riwayat_obat_pasien.no_rawat = detail_pemberian_obat.no_rawat "
                    + " AND riwayat_obat_pasien.jam = detail_pemberian_obat.jam "
                    + " AND riwayat_obat_pasien.`status` = 'simpan' "
                    + " LEFT JOIN bangsal ON bangsal.kd_bangsal = riwayat_obat_pasien.kd_bangsal "
                    + " WHERE detail_pemberian_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND bangsal.kd_bangsal in ('APT01','APT02') AND penjab.kd_pj='" + kdpnj.getText() + "') and p.stts_pulang <> 'Pindah Kamar' "
                    + " ORDER BY p.tgl_masuk DESC, p.jam_masuk DESC) AS b ON a.no_rawat = b.no_rawat) WHERE a.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " AND a.kd_bangsal in ('APT01','APT02') AND a.kd_pj='" + kdpnj.getText() + "' ORDER BY a.png_jawab, a.no_resep ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapDetailTotalPerPasienInapCara2ActionPerformed

    private void jMenuPerDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPerDokterActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptLapRekapObatSemua.jasper", "report", "::[ Laporan Rekap Total Pemakaian Obat Per Dokter ]::",
                " select * from(SELECT (SELECT MIN(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_perawatan) FROM resep_obat WHERE resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " databarang.nama_brng, databarang.kode_sat, sum(detail_pemberian_obat.jml) Jumlah, dokter.nm_dokter "
                + " FROM resep_obat INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN detail_pemberian_obat INNER JOIN dokter INNER JOIN penjab "
                + " INNER JOIN databarang ON detail_pemberian_obat.kode_brng = databarang.kode_brng AND detail_pemberian_obat.no_rawat = resep_obat.no_rawat "
                + " AND resep_obat.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND dokter.kd_dokter = resep_obat.kd_dokter "
                + " AND penjab.kd_pj = reg_periksa.kd_pj LEFT JOIN bridging_sep ON bridging_sep.no_rawat = resep_obat.no_rawat "
                + " WHERE detail_pemberian_obat.tgl_perawatan = resep_obat.tgl_perawatan AND detail_pemberian_obat.jam = resep_obat.jam "
                + " AND resep_obat.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' group BY databarang.kode_brng, dokter.kd_dokter "
                + " union ALL "
                + " select (SELECT MIN(tgl_jual) FROM penjualan WHERE penjualan.tgl_jual BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_jual) FROM penjualan WHERE penjualan.tgl_jual BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " databarang.nama_brng, databarang.kode_sat, sum(detailjual.jumlah) jumlah, ifnull(dokter.nm_dokter,'-') dokter from penjualan "
                + " inner join detailjual on penjualan.nota_jual = detailjual.nota_jual inner join databarang  on databarang.kode_brng = detailjual.kode_brng "
                + " left join dokter  on dokter.kd_dokter = penjualan.kd_dokter where penjualan.tgl_jual BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + " group by databarang.kode_brng, dokter.kd_dokter) as a order by a.nama_brng, a.Jumlah", param);
        this.setCursor(Cursor.getDefaultCursor());        
    }//GEN-LAST:event_jMenuPerDokterActionPerformed

    private void jMenuObatKhususPerTglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuObatKhususPerTglActionPerformed
        if (tbObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TCari.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama obat yang akan dicari belum diketik pada key word...!!!!");
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
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReport("rptLapPemakaianObatKhususPerTgl.jasper", "report", "::[ Laporan Rekap Pemakaian Obat Khusus PerTanggal ]::",
                    " select rp.no_rkm_medis,p.nm_pasien,CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                    + "db.nama_brng, sum(dpo.jml) jumlh, bs.nm_bangsal depo_farmasi, ro.tgl_perawatan, IFNULL(d.nm_dokter,'-') dokter_peresep from detail_pemberian_obat dpo  "
                    + "inner join reg_periksa rp on rp.no_rawat=dpo.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis  "
                    + "inner join databarang db on db.kode_brng=dpo.kode_brng INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab INNER JOIN bangsal bs on bs.kd_bangsal=dpo.kd_bangsal "
                    + "LEFT JOIN resep_obat ro ON ro.no_rawat=dpo.no_rawat and ro.tgl_perawatan=dpo.tgl_perawatan and ro.jam=dpo.jam "
                    + "LEFT JOIN dokter d on d.kd_dokter=ro.kd_dokter "
                    + "where dpo.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "and db.nama_brng like '%" + TCari.getText() + "%' and bs.kd_bangsal like '%" + KdGudang.getText() + "%' GROUP BY dpo.kode_brng, rp.no_rkm_medis, ro.tgl_perawatan ,bs.kd_bangsal "
                    + "union all "
                    + "select p.no_rkm_medis,p.nm_pasien,ifnull(CONCAT( ps.alamat, ', ', kl.nm_kel, ', ', kc.nm_kec, ', ', kb.nm_kab ),'-') alamat, "
                    + "d.nama_brng,sum(j.jumlah) jumlh, b.nm_bangsal depo_farmasi, p.tgl_jual tgl_perawatan, ifnull(dk.nm_dokter,p.keterangan) dokter_peresep "
                    + "from penjualan p "
                    + "inner join detailjual j on j.nota_jual = p.nota_jual "
                    + "inner join databarang d on d.kode_brng = j.kode_brng "
                    + "inner join bangsal b on b.kd_bangsal = p.kd_bangsal "
                    + "left join pasien ps on ps.no_rkm_medis = p.no_rkm_medis "
                    + "left JOIN kelurahan kl ON kl.kd_kel = ps.kd_kel "
                    + "left JOIN kecamatan kc ON kc.kd_kec = ps.kd_kec "
                    + "left JOIN kabupaten kb ON kb.kd_kab = ps.kd_kab "
                    + "left join dokter dk on dk.kd_dokter = p.kd_dokter "
                    + "where p.tgl_jual BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "and d.nama_brng like '%" + TCari.getText() + "%' and b.kd_bangsal like '%" + KdGudang.getText() + "%' "
                    + "group by j.kode_brng,p.no_rkm_medis,p.tgl_jual,b.kd_bangsal order by tgl_perawatan,nm_pasien DESC", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMenuObatKhususPerTglActionPerformed

    private void jMenuObatKhususActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuObatKhususActionPerformed
        if (tbObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TCari.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama obat yang akan dicari belum diketik pada key word...!!!!");
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
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReport("rptLapPemakaianObatKhusus.jasper", "report", "::[ Laporan Rekap Pemakaian Obat Khusus PerTanggal ]::",
                    " select rp.no_rkm_medis,p.nm_pasien,CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                    + "db.nama_brng, sum(dpo.jml) jumlh, ro.tgl_perawatan, IFNULL(d.nm_dokter,'-') dokter_peresep from detail_pemberian_obat dpo  "
                    + "inner join reg_periksa rp on rp.no_rawat=dpo.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis  "
                    + "inner join databarang db on db.kode_brng=dpo.kode_brng INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab LEFT JOIN resep_obat ro ON ro.no_rawat=dpo.no_rawat and ro.tgl_perawatan=dpo.tgl_perawatan and ro.jam=dpo.jam "
                    + "LEFT JOIN dokter d on d.kd_dokter=ro.kd_dokter "
                    + "where dpo.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "and db.nama_brng like '%" + TCari.getText() + "%' GROUP BY dpo.kode_brng, rp.no_rkm_medis "
                    + "union all "
                    + "select p.no_rkm_medis,p.nm_pasien,ifnull(CONCAT( ps.alamat, ', ', kl.nm_kel, ', ', kc.nm_kec, ', ', kb.nm_kab ),'-') alamat, "
                    + "d.nama_brng,sum(j.jumlah) jumlh,p.tgl_jual tgl_perawatan, ifnull(dk.nm_dokter,p.keterangan) dokter_peresep "
                    + "from penjualan p "
                    + "inner join detailjual j on j.nota_jual = p.nota_jual "
                    + "inner join databarang d on d.kode_brng = j.kode_brng "
                    + "inner join bangsal b on b.kd_bangsal = p.kd_bangsal "
                    + "left join pasien ps on ps.no_rkm_medis = p.no_rkm_medis "
                    + "left JOIN kelurahan kl ON kl.kd_kel = ps.kd_kel "
                    + "left JOIN kecamatan kc ON kc.kd_kec = ps.kd_kec "
                    + "left JOIN kabupaten kb ON kb.kd_kab = ps.kd_kab "
                    + "left join dokter dk on dk.kd_dokter = p.kd_dokter "
                    + "where p.tgl_jual BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and d.nama_brng like '%" + TCari.getText() + "%' "
                    + "group by j.kode_brng,p.no_rkm_medis order by jumlh DESC", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMenuObatKhususActionPerformed

    private void limitDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_limitDataMouseClicked
        limitData.setEditable(false);
    }//GEN-LAST:event_limitDataMouseClicked

    private void limitDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limitDataActionPerformed

    }//GEN-LAST:event_limitDataActionPerformed

    private void limitDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_limitDataKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_limitDataKeyPressed

    private void jMnRekapPemakaianObatKhusus1PxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnRekapPemakaianObatKhusus1PxActionPerformed
        if (norm.equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu nama pasiennya pada tabel dengan cara diklik...!!!");
            tbObat.requestFocus();
        } else if (norm.equals("-")) {
            JOptionPane.showMessageDialog(null, "Obat dari transaksi penjualan bebas tdk bisa. menggunakan laporan ini, gunakan menu penjualan bebas...!!!");
        } else if (kdpnj.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu cara bayarnya...!!!");
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
            param.put("periode", "Periode Tgl. Pemberian Obat/Resep " + DTPCari1.getSelectedItem() + " S.D. " + DTPCari2.getSelectedItem());
            Valid.MyReport("rptRekapPemakaianObat1Px.jasper", "report", "::[ Laporan Rekap Pemakaian Obat Untuk 1 Pasien Per Cara Bayar Semua Depo ]::",
                    " select dpo.tgl_perawatan,dpo.jam, dpo.no_rawat,rp.no_rkm_medis,p.nm_pasien, db.nama_brng, db.kode_sat, dpo.embalase,dpo.tuslah, "
                    + "dpo.jml,dpo.biaya_obat,dpo.total,b.nm_bangsal, pb.png_jawab from detail_pemberian_obat dpo "
                    + "inner join reg_periksa rp on rp.no_rawat=dpo.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join databarang db on db.kode_brng=dpo.kode_brng inner join penjab pb on pb.kd_pj=rp.kd_pj "
                    + "left JOIN riwayat_obat_pasien rop ON rop.kode_brng = dpo.kode_brng AND rop.tanggal = dpo.tgl_perawatan and rop.no_rawat = dpo.no_rawat "
                    + "AND Mid(rop.jam,1,5) = Mid(dpo.jam,1,5) and rop.status = 'simpan' left join bangsal b on b.kd_bangsal = rop.kd_bangsal "
                    + "where dpo.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "and b.kd_bangsal like '%" + KdGudang.getText() + "%' and rp.no_rkm_medis like '%" + norm + "%' and pb.kd_pj like '%" + kdpnj.getText() + "%' "
                    + "order by dpo.tgl_perawatan, dpo.jam", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jMnRekapPemakaianObatKhusus1PxActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgLaporanFarmasi dialog = new DlgLaporanFarmasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar;
    private widget.Button BtnUnit;
    public widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.TextBox KdGudang;
    private widget.Label LCount;
    private widget.TextBox NmGudang;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPopupMenu Popup1;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.TextBox TPoli;
    private widget.Button btnBarang1;
    private widget.Button btnPenjab;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel14;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel23;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JMenu jMenuLapAllRawatPerDepo;
    private javax.swing.JMenu jMenuLapInapAllDepo;
    private javax.swing.JMenu jMenuLapInapPerDepo;
    private javax.swing.JMenu jMenuLapJalanAllDepo;
    private javax.swing.JMenu jMenuLapJalanPerDepo;
    private javax.swing.JMenu jMenuLapPemakaian;
    private javax.swing.JMenu jMenuLapSemuaAllDepo;
    private javax.swing.JMenuItem jMenuObatKhusus;
    private javax.swing.JMenuItem jMenuObatKhususPerTgl;
    private javax.swing.JMenuItem jMenuPerDokter;
    private javax.swing.JMenuItem jMnRekapDetailResepPasienPerCB;
    private javax.swing.JMenuItem jMnRekapDetailResepPasienSemuaCB;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienInap;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienInap1;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienInap2;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienInapCara;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienInapCara1;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienInapCara2;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienJalan;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienJalan1;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienJalan2;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienJalanCara;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienJalanCara1;
    private javax.swing.JMenuItem jMnRekapDetailTotalPerPasienJalanCara2;
    private javax.swing.JMenuItem jMnRekapDetailTotalResepPasienPerCB1;
    private javax.swing.JMenuItem jMnRekapDetailTotalResepPasienSemuaCB1;
    private javax.swing.JMenuItem jMnRekapPemakaianObatKhusus1Px;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienInap;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienInap1;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienInap2;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienInapCara;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienInapCara1;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienInapCara2;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienJalan;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienJalan1;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienJalan2;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienJalanCara;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienJalanCara1;
    private javax.swing.JMenuItem jMnRekapTotalPerPasienJalanCara2;
    private javax.swing.JMenuItem jMnRekapTotalResepPasienPerCB;
    private javax.swing.JMenuItem jMnRekapTotalResepPasienPerCB1;
    private javax.swing.JMenuItem jMnRekapTotalResepPasienSemuaCB;
    private javax.swing.JMenuItem jMnRekapTotalResepPasienSemuaCB1;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox kdpnj;
    private widget.TextBox kdpoli;
    private widget.Label label19;
    private widget.ComboBox limitData;
    private widget.TextBox nmpnj;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {  
        depo1 = "";
        depo2 = "";
        tgl1 = " dpo.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
        tgl2 = "'" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'";
        depo1 = "b.kd_bangsal like '%" + KdGudang.getText() + "%'";
        depo2 = "b1.kd_bangsal like '%" + KdGudang.getText() + "%'";

        if (limitData.getSelectedItem().toString().equals("Semua")) {
            sql = "select dpo.tgl_perawatan,dpo.jam, dpo.no_rawat,rp.no_rkm_medis,p.nm_pasien, db.nama_brng,dpo.embalase,dpo.tuslah, "
                    + "dpo.jml,dpo.biaya_obat,dpo.total,b.nm_bangsal, pb.png_jawab, pb.kd_pj from detail_pemberian_obat dpo "
                    + "inner join reg_periksa rp on rp.no_rawat=dpo.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join databarang db on db.kode_brng=dpo.kode_brng inner join penjab pb on pb.kd_pj=rp.kd_pj "
                    + "left JOIN riwayat_obat_pasien rop ON rop.kode_brng = dpo.kode_brng AND rop.tanggal = dpo.tgl_perawatan and rop.no_rawat = dpo.no_rawat "
                    + "AND Mid(rop.jam,1,5) = Mid(dpo.jam,1,5) and rop.status = 'simpan' left join bangsal b on b.kd_bangsal = rop.kd_bangsal "
                    + "where  " + tgl1 + " and " + depo1 + " and tgl_perawatan like ? or "
                    + "" + tgl1 + " and " + depo1 + " and dpo.no_rawat like ? or "
                    + "" + tgl1 + " and " + depo1 + " and rp.no_rkm_medis like ? or "
                    + "" + tgl1 + " and " + depo1 + " and p.nm_pasien like ? or "
                    + "" + tgl1 + " and " + depo1 + " and pb.png_jawab like ? or "
                    + "" + tgl1 + " and " + depo1 + " and db.nama_brng like ? "
                    + "union all "
                    + "select pj.tgl_jual tgl_perawatan,'00:00:00' jam,'Penjualan Langsung' no_rawat,pj.no_rkm_medis, pj.nm_pasien, db1.nama_brng, "
                    + "0 embalase, dj.tambahan tuslah,dj.jumlah jml,dj.h_jual biaya_obat, dj.total, b1.nm_bangsal, '-' png_jawab, '-' kd_pj "
                    + "from penjualan pj inner join detailjual dj on dj.nota_jual = pj.nota_jual inner join databarang db1 on db1.kode_brng = dj.kode_brng "
                    + "inner join bangsal b1 on b1.kd_bangsal = pj.kd_bangsal "
                    + "where pj.tgl_jual BETWEEN " + tgl2 + " and " + depo2 + " and db1.nama_brng like ? or "
                    + "pj.tgl_jual BETWEEN " + tgl2 + " and " + depo2 + " and pj.no_rkm_medis like ? or "
                    + "pj.tgl_jual BETWEEN " + tgl2 + " and " + depo2 + " and pj.nm_pasien like ? or "
                    + "pj.tgl_jual BETWEEN " + tgl2 + " and " + depo2 + " and db1.nama_brng like ? "
                    + "order by tgl_perawatan,jam ";
            
        } else {
            sql = "select dpo.tgl_perawatan,dpo.jam, dpo.no_rawat,rp.no_rkm_medis,p.nm_pasien, db.nama_brng,dpo.embalase,dpo.tuslah, "
                    + "dpo.jml,dpo.biaya_obat,dpo.total,b.nm_bangsal, pb.png_jawab, pb.kd_pj from detail_pemberian_obat dpo "
                    + "inner join reg_periksa rp on rp.no_rawat=dpo.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join databarang db on db.kode_brng=dpo.kode_brng inner join penjab pb on pb.kd_pj=rp.kd_pj "
                    + "left JOIN riwayat_obat_pasien rop ON rop.kode_brng = dpo.kode_brng AND rop.tanggal = dpo.tgl_perawatan and rop.no_rawat = dpo.no_rawat "
                    + "AND Mid(rop.jam,1,5) = Mid(dpo.jam,1,5) and rop.status = 'simpan' left join bangsal b on b.kd_bangsal = rop.kd_bangsal "
                    + "where " + tgl1 + " and " + depo1 + " and tgl_perawatan like ? or "
                    + "" + tgl1 + " and " + depo1 + " and dpo.no_rawat like ? or "
                    + "" + tgl1 + " and " + depo1 + " and rp.no_rkm_medis like ? or "
                    + "" + tgl1 + " and " + depo1 + " and p.nm_pasien like ? or "
                    + "" + tgl1 + " and " + depo1 + " and pb.png_jawab like ? or "
                    + "" + tgl1 + " and " + depo1 + " and db.nama_brng like ? "
                    + "union all "
                    + "select pj.tgl_jual tgl_perawatan,'00:00:00' jam,'Penjualan Langsung' no_rawat,pj.no_rkm_medis, pj.nm_pasien, db1.nama_brng, "
                    + "0 embalase, dj.tambahan tuslah,dj.jumlah jml,dj.h_jual biaya_obat, dj.total, b1.nm_bangsal, '-' png_jawab, '-' kd_pj "
                    + "from penjualan pj inner join detailjual dj on dj.nota_jual = pj.nota_jual inner join databarang db1 on db1.kode_brng = dj.kode_brng "
                    + "inner join bangsal b1 on b1.kd_bangsal = pj.kd_bangsal "
                    + "where pj.tgl_jual BETWEEN " + tgl2 + " and " + depo2 + " and db1.nama_brng like ? or "
                    + "pj.tgl_jual BETWEEN " + tgl2 + " and " + depo2 + " and pj.no_rkm_medis like ? or "
                    + "pj.tgl_jual BETWEEN " + tgl2 + " and " + depo2 + " and pj.nm_pasien like ? or "
                    + "pj.tgl_jual BETWEEN " + tgl2 + " and " + depo2 + " and db1.nama_brng like ? "
                    + "order by tgl_perawatan,jam limit " + limitData.getSelectedItem().toString() + "";
        }
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(sql);
            ps.setString(1, "%" + TCari.getText().trim() + "%");
            ps.setString(2, "%" + TCari.getText().trim() + "%");
            ps.setString(3, "%" + TCari.getText().trim() + "%");
            ps.setString(4, "%" + TCari.getText().trim() + "%");
            ps.setString(5, "%" + TCari.getText().trim() + "%");
            ps.setString(6, "%" + TCari.getText().trim() + "%");
            ps.setString(7, "%" + TCari.getText().trim() + "%");
            ps.setString(8, "%" + TCari.getText().trim() + "%");
            ps.setString(9, "%" + TCari.getText().trim() + "%"); 
            ps.setString(10, "%" + TCari.getText().trim() + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Object[] data = {
                    rs.getString("tgl_perawatan"),
                    rs.getString("jam"),
                    rs.getString("no_rawat"),
                    rs.getString("no_rkm_medis"),
                    rs.getString("nm_pasien"),
                    rs.getString("png_jawab"),
                    rs.getString("nama_brng"),
                    rs.getDouble("embalase"),
                    rs.getDouble("tuslah"),
                    rs.getDouble("jml"),
                    rs.getDouble("biaya_obat"),
                    rs.getDouble("total"),
                    rs.getString("nm_bangsal"),
                    rs.getString("kd_pj")
                };
                tabMode.addRow(data);
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {        
        kdpnj.setText("");
        nmpnj.setText("");
        KdGudang.setText("");
        NmGudang.setText("");
        kdpoli.setText("");
        TPoli.setText("");
        limitData.setSelectedIndex(0);
        DTPCari1.setDate(new Date());
        DTPCari2.setDate(new Date());
        btnPenjab.requestFocus();
    }
    
    public void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,160));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    private void getData() {
        norm = "";
        if (tbObat.getSelectedRow() != -1) {
            norm = tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString();
            kdpnj.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString());
            nmpnj.setText(Sequel.cariIsi("select png_jawab from penjab where kd_pj='" + kdpnj.getText() + "'"));
        }
    }

}
