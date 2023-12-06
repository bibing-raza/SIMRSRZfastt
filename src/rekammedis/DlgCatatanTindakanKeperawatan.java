    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgSpesialis.java
 *
 * Created on May 23, 2010, 1:25:13 AM
 */

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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author dosen
 */
public class DlgCatatanTindakanKeperawatan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, psPagi, psSore, psMalam, pscppt;
    private ResultSet rs, rs1, rs2, rsPagi, rsSore, rsMalam, rscppt;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String nip = "", wktSimpan = "", kodeTindakan = "", dataKonfirmasi = "", wktSimpanPagi = "", wktSimpanSore = "", wktSimpanMalam = "";
    private int x = 0, i = 0;
    private Date date = new Date(), jamSekarang, jamSift1, jamSift2, jamSift3;

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public DlgCatatanTindakanKeperawatan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"Cek", "No. Rawat", "No. RM", "Nama Pasien", "Tanggal", "Sift", "Jam",
            "Nama Tindakan", "Petugas", "tgl", "jam", "nip", "waktu_simpan"
        };
        tabMode=new DefaultTableModel(null,row){
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbCatatan.setModel(tabMode);
        tbCatatan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCatatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 13; i++) {
            TableColumn column = tbCatatan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(60);
            } else if (i == 6) {
                column.setPreferredWidth(60);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }

        tbCatatan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Nama Tindakan", "kode"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTindakan.setModel(tabMode1);
        tbTindakan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 2; i++) {
            TableColumn column = tbTindakan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(500);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbTindakan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "Evaluasi Nyeri", "Keterangan Pagi", "Manajemen Nyeri", "tanggal", "waktu_simpan", "Sift", "Tanggal"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPagi.setModel(tabMode2);
        tbPagi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPagi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbPagi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(90);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setPreferredWidth(60);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            }
        }
        tbPagi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new Object[]{
            "Evaluasi Nyeri", "Keterangan Sore", "Manajemen Nyeri", "tanggal", "waktu_simpan", "Sift", "Tanggal"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbSore.setModel(tabMode3);
        tbSore.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbSore.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbSore.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(90);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setPreferredWidth(60);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            }
        }
        tbSore.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode4 = new DefaultTableModel(null, new Object[]{
            "Evaluasi Nyeri", "Keterangan Malam", "Manajemen Nyeri", "tanggal", "waktu_simpan", "Sift", "Tanggal"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbMalam.setModel(tabMode4);
        tbMalam.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbMalam.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbMalam.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(90);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setPreferredWidth(60);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            }
        }
        tbMalam.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeCppt=new DefaultTableModel(null, new Object[]{
            "Tgl. CPPT", "Jam CPPT", "Jenis Bagian", "DPJP Konsulen", "Jenis PPA",
            "Nama PPA", "Shift", "hasil", "instruksi", "no_rawat", "tgl_cppt", "jam_cppt"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbCPPT.setModel(tabModeCppt);
        tbCPPT.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCPPT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbCPPT.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(40);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbCPPT.setDefaultRenderer(Object.class, new WarnaTable());

        TnmTindakan.setDocument(new batasInput((int) 200).getKata(TnmTindakan));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
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
                    nip = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                    TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    BtnPetugas.requestFocus();
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
        
        ChkAccor.setSelected(false);
        isMenu();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu = new javax.swing.JPopupMenu();
        MnContengSemua = new javax.swing.JMenuItem();
        MnHapusConteng = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnHapus = new javax.swing.JMenuItem();
        jPopupMenuPagi = new javax.swing.JPopupMenu();
        MnHapusPagi = new javax.swing.JMenuItem();
        MnGantiPagi = new javax.swing.JMenuItem();
        jPopupMenuSore = new javax.swing.JPopupMenu();
        MnHapusSore = new javax.swing.JMenuItem();
        MnGantiSore = new javax.swing.JMenuItem();
        jPopupMenuMalam = new javax.swing.JPopupMenu();
        MnHapusMalam = new javax.swing.JMenuItem();
        MnGantiMalam = new javax.swing.JMenuItem();
        WindowTindakan = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbTindakan = new widget.Table();
        panelisi5 = new widget.panelisi();
        jLabel48 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        WindowEvaluasi = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        panelisi6 = new widget.panelisi();
        BtnSimpan1 = new widget.Button();
        BtnEdit1 = new widget.Button();
        BtnCloseIn2 = new widget.Button();
        panelisi7 = new widget.panelisi();
        jLabel50 = new widget.Label();
        cmbEvaluasi = new widget.ComboBox();
        jLabel51 = new widget.Label();
        Scroll7 = new widget.ScrollPane();
        Tmanajemen = new widget.TextArea();
        jLabel52 = new widget.Label();
        cmbSiftNyeri = new widget.ComboBox();
        jLabel54 = new widget.Label();
        Tpagi = new widget.TextBox();
        jLabel55 = new widget.Label();
        Tsore = new widget.TextBox();
        jLabel56 = new widget.Label();
        Tmalam = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbCatatan = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnCopy = new widget.Button();
        BtnEvaluasi = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass7 = new widget.panelisi();
        panelGlass10 = new widget.panelisi();
        jLabel3 = new widget.Label();
        TNoRW = new widget.TextBox();
        TNmPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel4 = new widget.Label();
        Ttgl = new widget.Tanggal();
        jLabel5 = new widget.Label();
        cmbSift = new widget.ComboBox();
        jLabel8 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel9 = new widget.Label();
        jLabel10 = new widget.Label();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        TnmTindakan = new widget.TextBox();
        BtnTindakan = new widget.Button();
        chkSaya = new widget.CekBox();
        panelGlass11 = new widget.panelisi();
        Scroll2 = new widget.ScrollPane();
        tbPagi = new widget.Table();
        Scroll4 = new widget.ScrollPane();
        tbSore = new widget.Table();
        Scroll5 = new widget.ScrollPane();
        tbMalam = new widget.Table();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        Scroll3 = new widget.ScrollPane();
        tbCPPT = new widget.Table();
        panelGlass14 = new widget.panelisi();
        scrollPane5 = new widget.ScrollPane();
        Thasil = new widget.TextArea();
        scrollPane4 = new widget.ScrollPane();
        Tinstruksi = new widget.TextArea();

        jPopupMenu.setName("jPopupMenu"); // NOI18N

        MnContengSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnContengSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnContengSemua.setText("Conteng Semua");
        MnContengSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnContengSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnContengSemua.setIconTextGap(5);
        MnContengSemua.setName("MnContengSemua"); // NOI18N
        MnContengSemua.setPreferredSize(new java.awt.Dimension(150, 26));
        MnContengSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnContengSemuaActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnContengSemua);

        MnHapusConteng.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusConteng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusConteng.setText("Hapus Conteng");
        MnHapusConteng.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusConteng.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusConteng.setIconTextGap(5);
        MnHapusConteng.setName("MnHapusConteng"); // NOI18N
        MnHapusConteng.setPreferredSize(new java.awt.Dimension(150, 26));
        MnHapusConteng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusContengActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnHapusConteng);

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHapus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        MnHapus.setText("Hapus");
        MnHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapus.setIconTextGap(5);
        MnHapus.setName("MnHapus"); // NOI18N
        MnHapus.setPreferredSize(new java.awt.Dimension(90, 26));
        MnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapus);

        jPopupMenuPagi.setName("jPopupMenuPagi"); // NOI18N

        MnHapusPagi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusPagi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        MnHapusPagi.setText("Hapus");
        MnHapusPagi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusPagi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusPagi.setIconTextGap(5);
        MnHapusPagi.setName("MnHapusPagi"); // NOI18N
        MnHapusPagi.setPreferredSize(new java.awt.Dimension(90, 26));
        MnHapusPagi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusPagiActionPerformed(evt);
            }
        });
        jPopupMenuPagi.add(MnHapusPagi);

        MnGantiPagi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiPagi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiPagi.setText("Ganti");
        MnGantiPagi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGantiPagi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGantiPagi.setIconTextGap(5);
        MnGantiPagi.setName("MnGantiPagi"); // NOI18N
        MnGantiPagi.setPreferredSize(new java.awt.Dimension(90, 26));
        MnGantiPagi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiPagiActionPerformed(evt);
            }
        });
        jPopupMenuPagi.add(MnGantiPagi);

        jPopupMenuSore.setName("jPopupMenuSore"); // NOI18N

        MnHapusSore.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusSore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        MnHapusSore.setText("Hapus");
        MnHapusSore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusSore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusSore.setIconTextGap(5);
        MnHapusSore.setName("MnHapusSore"); // NOI18N
        MnHapusSore.setPreferredSize(new java.awt.Dimension(90, 26));
        MnHapusSore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusSoreActionPerformed(evt);
            }
        });
        jPopupMenuSore.add(MnHapusSore);

        MnGantiSore.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiSore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiSore.setText("Ganti");
        MnGantiSore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGantiSore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGantiSore.setIconTextGap(5);
        MnGantiSore.setName("MnGantiSore"); // NOI18N
        MnGantiSore.setPreferredSize(new java.awt.Dimension(90, 26));
        MnGantiSore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiSoreActionPerformed(evt);
            }
        });
        jPopupMenuSore.add(MnGantiSore);

        jPopupMenuMalam.setName("jPopupMenuMalam"); // NOI18N

        MnHapusMalam.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusMalam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        MnHapusMalam.setText("Hapus");
        MnHapusMalam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusMalam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusMalam.setIconTextGap(5);
        MnHapusMalam.setName("MnHapusMalam"); // NOI18N
        MnHapusMalam.setPreferredSize(new java.awt.Dimension(90, 26));
        MnHapusMalam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusMalamActionPerformed(evt);
            }
        });
        jPopupMenuMalam.add(MnHapusMalam);

        MnGantiMalam.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiMalam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiMalam.setText("Ganti");
        MnGantiMalam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGantiMalam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGantiMalam.setIconTextGap(5);
        MnGantiMalam.setName("MnGantiMalam"); // NOI18N
        MnGantiMalam.setPreferredSize(new java.awt.Dimension(90, 26));
        MnGantiMalam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiMalamActionPerformed(evt);
            }
        });
        jPopupMenuMalam.add(MnGantiMalam);

        WindowTindakan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTindakan.setName("WindowTindakan"); // NOI18N
        WindowTindakan.setUndecorated(true);
        WindowTindakan.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Daftar Nama Tindakan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(new java.awt.BorderLayout());

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTindakan.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTindakan.setComponentPopupMenu(jPopupMenu1);
        tbTindakan.setName("tbTindakan"); // NOI18N
        tbTindakan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTindakanMouseClicked(evt);
            }
        });
        tbTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTindakanKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbTindakan);

        internalFrame6.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 7));

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Key Word :");
        jLabel48.setName("jLabel48"); // NOI18N
        jLabel48.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel48.setRequestFocusEnabled(false);
        panelisi5.add(jLabel48);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi5.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
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
        panelisi5.add(BtnCari1);

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

        internalFrame6.add(panelisi5, java.awt.BorderLayout.PAGE_END);

        WindowTindakan.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        WindowEvaluasi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowEvaluasi.setName("WindowEvaluasi"); // NOI18N
        WindowEvaluasi.setUndecorated(true);
        WindowEvaluasi.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Evaluasi Nyeri ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame7.setLayout(new java.awt.BorderLayout());

        panelisi6.setName("panelisi6"); // NOI18N
        panelisi6.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 7));

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
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
        panelisi6.add(BtnSimpan1);

        BtnEdit1.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit1.setMnemonic('G');
        BtnEdit1.setText("Ganti");
        BtnEdit1.setToolTipText("Alt+G");
        BtnEdit1.setName("BtnEdit1"); // NOI18N
        BtnEdit1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit1ActionPerformed(evt);
            }
        });
        BtnEdit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEdit1KeyPressed(evt);
            }
        });
        panelisi6.add(BtnEdit1);

        BtnCloseIn2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn2.setMnemonic('U');
        BtnCloseIn2.setText("Tutup");
        BtnCloseIn2.setToolTipText("Alt+U");
        BtnCloseIn2.setName("BtnCloseIn2"); // NOI18N
        BtnCloseIn2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn2ActionPerformed(evt);
            }
        });
        panelisi6.add(BtnCloseIn2);

        internalFrame7.add(panelisi6, java.awt.BorderLayout.PAGE_END);

        panelisi7.setName("panelisi7"); // NOI18N
        panelisi7.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi7.setLayout(null);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Evaluasi Nyeri : ");
        jLabel50.setName("jLabel50"); // NOI18N
        jLabel50.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel50.setRequestFocusEnabled(false);
        panelisi7.add(jLabel50);
        jLabel50.setBounds(0, 8, 110, 23);

        cmbEvaluasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbEvaluasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Skala", "2. Lokasi", "3. Sifat", "4. Tindakan" }));
        cmbEvaluasi.setName("cmbEvaluasi"); // NOI18N
        panelisi7.add(cmbEvaluasi);
        cmbEvaluasi.setBounds(112, 8, 100, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Manajemen Nyeri : ");
        jLabel51.setName("jLabel51"); // NOI18N
        jLabel51.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel51.setRequestFocusEnabled(false);
        panelisi7.add(jLabel51);
        jLabel51.setBounds(0, 120, 110, 23);

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        Tmanajemen.setColumns(20);
        Tmanajemen.setRows(5);
        Tmanajemen.setName("Tmanajemen"); // NOI18N
        Tmanajemen.setPreferredSize(new java.awt.Dimension(170, 600));
        Scroll7.setViewportView(Tmanajemen);

        panelisi7.add(Scroll7);
        Scroll7.setBounds(112, 120, 430, 110);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Sift Petugas : ");
        jLabel52.setName("jLabel52"); // NOI18N
        jLabel52.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel52.setRequestFocusEnabled(false);
        panelisi7.add(jLabel52);
        jLabel52.setBounds(212, 8, 90, 23);

        cmbSiftNyeri.setForeground(new java.awt.Color(0, 0, 0));
        cmbSiftNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pagi", "Sore", "Malam" }));
        cmbSiftNyeri.setName("cmbSiftNyeri"); // NOI18N
        cmbSiftNyeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSiftNyeriActionPerformed(evt);
            }
        });
        panelisi7.add(cmbSiftNyeri);
        cmbSiftNyeri.setBounds(304, 8, 70, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Keterangan Pagi : ");
        jLabel54.setName("jLabel54"); // NOI18N
        jLabel54.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel54.setRequestFocusEnabled(false);
        panelisi7.add(jLabel54);
        jLabel54.setBounds(0, 36, 110, 23);

        Tpagi.setForeground(new java.awt.Color(0, 0, 0));
        Tpagi.setName("Tpagi"); // NOI18N
        Tpagi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpagiKeyPressed(evt);
            }
        });
        panelisi7.add(Tpagi);
        Tpagi.setBounds(112, 36, 430, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Keterangan Sore : ");
        jLabel55.setName("jLabel55"); // NOI18N
        jLabel55.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel55.setRequestFocusEnabled(false);
        panelisi7.add(jLabel55);
        jLabel55.setBounds(0, 64, 110, 23);

        Tsore.setForeground(new java.awt.Color(0, 0, 0));
        Tsore.setName("Tsore"); // NOI18N
        Tsore.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsoreKeyPressed(evt);
            }
        });
        panelisi7.add(Tsore);
        Tsore.setBounds(112, 64, 430, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Keterangan Malam : ");
        jLabel56.setName("jLabel56"); // NOI18N
        jLabel56.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel56.setRequestFocusEnabled(false);
        panelisi7.add(jLabel56);
        jLabel56.setBounds(0, 92, 110, 23);

        Tmalam.setForeground(new java.awt.Color(0, 0, 0));
        Tmalam.setName("Tmalam"); // NOI18N
        Tmalam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmalamKeyPressed(evt);
            }
        });
        panelisi7.add(Tmalam);
        Tmalam.setBounds(112, 92, 430, 23);

        internalFrame7.add(panelisi7, java.awt.BorderLayout.CENTER);

        WindowEvaluasi.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Catatan Tindakan Keperawatan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbCatatan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbCatatan.setComponentPopupMenu(jPopupMenu);
        tbCatatan.setName("tbCatatan"); // NOI18N
        tbCatatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCatatanMouseClicked(evt);
            }
        });
        tbCatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCatatanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbCatatanKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbCatatan);

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

        BtnCopy.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnCopy.setMnemonic('P');
        BtnCopy.setText("Copy Tindakan");
        BtnCopy.setToolTipText("Alt+P");
        BtnCopy.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnCopy.setName("BtnCopy"); // NOI18N
        BtnCopy.setPreferredSize(new java.awt.Dimension(140, 30));
        BtnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopyActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnCopy);

        BtnEvaluasi.setForeground(new java.awt.Color(0, 0, 0));
        BtnEvaluasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnEvaluasi.setMnemonic('P');
        BtnEvaluasi.setText("Evaluasi Nyeri");
        BtnEvaluasi.setToolTipText("Alt+P");
        BtnEvaluasi.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnEvaluasi.setName("BtnEvaluasi"); // NOI18N
        BtnEvaluasi.setPreferredSize(new java.awt.Dimension(140, 30));
        BtnEvaluasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEvaluasiActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnEvaluasi);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
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
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-12-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-12-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+1");
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

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+2");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 23));
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
        panelGlass9.add(BtnAll);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 170));
        panelGlass7.setLayout(new java.awt.GridLayout(1, 2));

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 130));
        panelGlass10.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass10.add(jLabel3);
        jLabel3.setBounds(2, 10, 100, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        panelGlass10.add(TNoRW);
        TNoRW.setBounds(107, 10, 122, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setName("TNmPasien"); // NOI18N
        panelGlass10.add(TNmPasien);
        TNmPasien.setBounds(308, 10, 291, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        panelGlass10.add(TNoRM);
        TNoRM.setBounds(233, 10, 70, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tanggal :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass10.add(jLabel4);
        jLabel4.setBounds(2, 38, 100, 23);

        Ttgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-12-2023" }));
        Ttgl.setDisplayFormat("dd-MM-yyyy");
        Ttgl.setName("Ttgl"); // NOI18N
        Ttgl.setOpaque(false);
        Ttgl.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(Ttgl);
        Ttgl.setBounds(107, 38, 90, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Sift Petugas :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass10.add(jLabel5);
        jLabel5.setBounds(200, 38, 80, 23);

        cmbSift.setForeground(new java.awt.Color(0, 0, 0));
        cmbSift.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pagi", "Sore", "Malam" }));
        cmbSift.setName("cmbSift"); // NOI18N
        panelGlass10.add(cmbSift);
        cmbSift.setBounds(285, 38, 70, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Jam Tindakan :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass10.add(jLabel8);
        jLabel8.setBounds(356, 38, 90, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        panelGlass10.add(cmbJam);
        cmbJam.setBounds(453, 38, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        panelGlass10.add(cmbMnt);
        cmbMnt.setBounds(504, 38, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        panelGlass10.add(cmbDtk);
        cmbDtk.setBounds(555, 38, 45, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nama Tindakan :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass10.add(jLabel9);
        jLabel9.setBounds(2, 66, 100, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Nama Petugas :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass10.add(jLabel10);
        jLabel10.setBounds(2, 94, 100, 23);

        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        panelGlass10.add(TnmPetugas);
        TnmPetugas.setBounds(107, 94, 360, 23);

        BtnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('1');
        BtnPetugas.setToolTipText("Alt+1");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnPetugas);
        BtnPetugas.setBounds(470, 94, 28, 23);

        TnmTindakan.setForeground(new java.awt.Color(0, 0, 0));
        TnmTindakan.setName("TnmTindakan"); // NOI18N
        panelGlass10.add(TnmTindakan);
        TnmTindakan.setBounds(107, 66, 491, 23);

        BtnTindakan.setForeground(new java.awt.Color(0, 0, 0));
        BtnTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnTindakan.setMnemonic('1');
        BtnTindakan.setToolTipText("Alt+1");
        BtnTindakan.setName("BtnTindakan"); // NOI18N
        BtnTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTindakanActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnTindakan);
        BtnTindakan.setBounds(603, 66, 28, 23);

        chkSaya.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya.setText("Saya Sendiri");
        chkSaya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya.setName("chkSaya"); // NOI18N
        chkSaya.setOpaque(false);
        chkSaya.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSayaActionPerformed(evt);
            }
        });
        panelGlass10.add(chkSaya);
        chkSaya.setBounds(505, 94, 90, 23);

        panelGlass7.add(panelGlass10);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 130));
        panelGlass11.setLayout(new java.awt.GridLayout(1, 3));

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Evaluasi Nyeri (PAGI) ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbPagi.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPagi.setComponentPopupMenu(jPopupMenuPagi);
        tbPagi.setName("tbPagi"); // NOI18N
        tbPagi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPagiMouseClicked(evt);
            }
        });
        tbPagi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPagiKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPagiKeyReleased(evt);
            }
        });
        Scroll2.setViewportView(tbPagi);

        panelGlass11.add(Scroll2);

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Evaluasi Nyeri (SORE) ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbSore.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbSore.setComponentPopupMenu(jPopupMenuSore);
        tbSore.setName("tbSore"); // NOI18N
        tbSore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSoreMouseClicked(evt);
            }
        });
        tbSore.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSoreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbSoreKeyReleased(evt);
            }
        });
        Scroll4.setViewportView(tbSore);

        panelGlass11.add(Scroll4);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Evaluasi Nyeri (MALAM) ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbMalam.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbMalam.setComponentPopupMenu(jPopupMenuMalam);
        tbMalam.setName("tbMalam"); // NOI18N
        tbMalam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMalamMouseClicked(evt);
            }
        });
        tbMalam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMalamKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbMalamKeyReleased(evt);
            }
        });
        Scroll5.setViewportView(tbMalam);

        panelGlass11.add(Scroll5);

        panelGlass7.add(panelGlass11);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(900, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout());

        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setToolTipText("Silahkan Klik Untuk Membaca CPPT");
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(22, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormMenu.setBackground(new java.awt.Color(250, 250, 245));
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(150, 483));
        FormMenu.setLayout(new java.awt.GridLayout(1, 2));

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " CPPT ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbCPPT.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbCPPT.setName("tbCPPT"); // NOI18N
        tbCPPT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCPPTMouseClicked(evt);
            }
        });
        tbCPPT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCPPTKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbCPPT);

        FormMenu.add(Scroll3);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass14.setLayout(new java.awt.BorderLayout());

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Hasil Pemeriksaan, Analisa, Rencana, Penatalaksanaan Pasien ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N
        scrollPane5.setPreferredSize(new java.awt.Dimension(212, 350));

        Thasil.setEditable(false);
        Thasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Thasil.setColumns(20);
        Thasil.setRows(5);
        Thasil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Thasil.setName("Thasil"); // NOI18N
        Thasil.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane5.setViewportView(Thasil);

        panelGlass14.add(scrollPane5, java.awt.BorderLayout.PAGE_START);

        scrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Instruksi Tenaga Kesehatan Termasuk Pasca Bedah/Prosedur ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane4.setName("scrollPane4"); // NOI18N
        scrollPane4.setPreferredSize(new java.awt.Dimension(212, 150));

        Tinstruksi.setEditable(false);
        Tinstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tinstruksi.setColumns(20);
        Tinstruksi.setRows(5);
        Tinstruksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane4.setViewportView(Tinstruksi);

        panelGlass14.add(scrollPane4, java.awt.BorderLayout.CENTER);

        FormMenu.add(panelGlass14);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelAccor, java.awt.BorderLayout.EAST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (cmbSift.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Pilih sift nya dulu dengan benar..!!!");
            cmbSift.requestFocus();
        } else {
            Sequel.menyimpan("catatan_tindakan_keperawatan",
                    "'" + TNoRW.getText() + "','" + cmbSift.getSelectedItem().toString() + "',"
                    + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "',"
                    + "'" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                    + "'" + TnmTindakan.getText() + "','" + nip + "','" + Sequel.cariIsi("select now()") + "'", "Catatan Tindakan Keperawatan");
            
            Sequel.simpanReplaceInto("master_catatan_tindakan_keperawatan", "'0','" + TnmTindakan.getText() + "'", "Data Master Catatan");
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, TNmPasien, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
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
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                //cek conteng
                x = 0;
                for (i = 0; i < tbCatatan.getRowCount(); i++) {
                    if (tbCatatan.getValueAt(i, 0).toString().equals("true")) {
                        x++;
                    }
                }

                //jika tidak diconteng
                if (x == 0) {
                    if (Sequel.queryu2tf("delete from catatan_tindakan_keperawatan where waktu_simpan=?", 1, new String[]{
                        tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 12).toString()
                    }) == true) {
                        tampil();
                        emptTeks();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                    //jika diconteng
                } else {
                    try {
                        for (i = 0; i < tbCatatan.getRowCount(); i++) {
                            if (tbCatatan.getValueAt(i, 0).toString().equals("true")) {
                                Sequel.meghapus("catatan_tindakan_keperawatan", "waktu_simpan",
                                        tbCatatan.getValueAt(i, 12).toString());
                            }
                        }
                        tampil();
                        emptTeks();
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }
            }
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (cmbSift.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Pilih sift nya dulu dengan benar..!!!");
            cmbSift.requestFocus();
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!!");
            tbCatatan.requestFocus();
        } else {
            if (tbCatatan.getSelectedRow() > -1) {
                Sequel.mengedit("catatan_tindakan_keperawatan", "waktu_simpan=?", "sift=?, tanggal=?, jam_tindakan=?, "
                        + "nm_tindakan=?, nip_petugas=?", 6, new String[]{
                            cmbSift.getSelectedItem().toString(), Valid.SetTgl(Ttgl.getSelectedItem() + ""),
                            cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            TnmTindakan.getText(), nip,
                            wktSimpan
                });
                
                Sequel.simpanReplaceInto("master_catatan_tindakan_keperawatan", "'0','" + TnmTindakan.getText() + "'", "Data Master Catatan");
                tampil();
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowTindakan.dispose();
        WindowEvaluasi.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
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
            tbCatatan.requestFocus();
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
       tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, TNoRW);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbCatatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCatatanMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbCatatanMouseClicked

    private void tbCatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCatatanKeyPressed
        if (tabMode.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.setText("");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbCatatanKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void tbCatatanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCatatanKeyReleased
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbCatatanKeyReleased

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        akses.setform("DlgCatatanTindakanKeperawatan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTindakanActionPerformed
        WindowTindakan.setSize(603, internalFrame1.getHeight() - 40);
        WindowTindakan.setLocationRelativeTo(internalFrame1);
        WindowTindakan.setAlwaysOnTop(false);
        WindowTindakan.setVisible(true);
        tampilTindakan();
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnTindakanActionPerformed

    private void tbTindakanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTindakanMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                kodeTindakan = tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 1).toString();
            } catch (java.lang.NullPointerException e) {
            }
            
            if (evt.getClickCount() == 2) {
                if (tbTindakan.getSelectedRow() != -1) {
                    TnmTindakan.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 0).toString());
                    kodeTindakan = tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 1).toString();
                    WindowTindakan.dispose();
                }
            }
        }
    }//GEN-LAST:event_tbTindakanMouseClicked

    private void tbTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTindakanKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SPACE || evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (tbTindakan.getSelectedRow() != -1) {
                    TnmTindakan.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 0).toString());
                    kodeTindakan = tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 1).toString();
                    WindowTindakan.dispose();
                }
            }
        }
    }//GEN-LAST:event_tbTindakanKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilTindakan();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowTindakan.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
//        if (tabMode.getRowCount() == 0) {
//            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
//            BtnBatal.requestFocus();
//        } else if (TNoRW.getText().equals("")) {
//            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu datanya pada tabel utk. mencetak laporan...!!!!");
//        } else if (Sequel.cariInteger("select count(-1) from pemberian_obat where no_rawat='" + TNoRW.getText() + "'") == 0) {
//            JOptionPane.showMessageDialog(null, "Data pemberian obat dg. no. rawat pasien tersebut belum tersimpan...!!!!");
//        } else if (tabMode.getRowCount() != 0) {
//            WindowCetak.setSize(598, 105);
//            WindowCetak.setLocationRelativeTo(internalFrame1);
//            WindowCetak.setAlwaysOnTop(false);
//            WindowCetak.setVisible(true);
//            cmbJnsObat1.setSelectedItem(cmbJnsObat.getSelectedItem());
//            tgl_beriCetak.setDate(tgl_beri.getDate());
//
//            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
//                cmbJnsRawat.setSelectedIndex(0);
//            } else if (status.equals("ranap")) {
//                cmbJnsRawat.setSelectedIndex(1);
//            }
//        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void MnContengSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnContengSemuaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data catatan tindakan keperawatan belum ada...!!!!");
        } else {
            tampil();
            for (i = 0; i < tbCatatan.getRowCount(); i++) {
                tbCatatan.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }//GEN-LAST:event_MnContengSemuaActionPerformed

    private void MnHapusContengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusContengActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data catatan tindakan keperawatan belum ada...!!!!");
        } else {
            wktSimpan = "";
            tampil();
            for (i = 0; i < tbCatatan.getRowCount(); i++) {
                tbCatatan.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_MnHapusContengActionPerformed

    private void BtnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopyActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data catatan tindakan keperawatan masih kosong...!!!!");
        } else {
            //cek conteng
            x = 0;
            for (i = 0; i < tbCatatan.getRowCount(); i++) {
                if (tbCatatan.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu data catatan tindakan keperawatan yang dipilih utk. di copy..!!!!");
                tbCatatan.requestFocus();
            } else {
                try {
                    for (i = 0; i < tbCatatan.getRowCount(); i++) {
                        if (tbCatatan.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpan("catatan_tindakan_keperawatan",
                                "'" + tbCatatan.getValueAt(i, 1).toString() + "',"
                                + "'" + tbCatatan.getValueAt(i, 5).toString() + "',"
                                + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "',"
                                + "'" + tbCatatan.getValueAt(i, 10).toString() + "',"
                                + "'" + tbCatatan.getValueAt(i, 7).toString() + "',"
                                + "'" + tbCatatan.getValueAt(i, 11).toString() + "',"
                                + "'" + Sequel.cariIsi("select now()") + "'", "Catatan Tindakan Keperawatan");

                            //jeda 1 detik
                            Thread.sleep(1000);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }

                DTPCari1.setDate(Ttgl.getDate());
                JOptionPane.showMessageDialog(null, "Data catatan tindakan keperawatan berhasil di copy..!!!!");
                tampil();
                emptTeks();
                MnHapusContengActionPerformed(null);
            }
        }
    }//GEN-LAST:event_BtnCopyActionPerformed

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        if (kodeTindakan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu data nama tindakan yang akan dihapus..!!!!");
            tbTindakan.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from master_catatan_tindakan_keperawatan where kode=?", 1, new String[]{
                    kodeTindakan
                }) == true) {
                    tampilTindakan();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        }
    }//GEN-LAST:event_MnHapusActionPerformed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        isMenu();
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void tbCPPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCPPTMouseClicked
        if (tabModeCppt.getRowCount() != 0) {
            try {
                getDataCppt();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbCPPTMouseClicked

    private void tbCPPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCPPTKeyPressed
        if (tabModeCppt.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataCppt();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbCPPTKeyPressed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (akses.getadmin() == true) {
                nip = "-";
                TnmPetugas.setText("-");
            } else {
                nip = akses.getkode();
                TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip + "'"));
            }
        } else {
            nip = "-";
            TnmPetugas.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void tbPagiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPagiMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataPagi();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPagiMouseClicked

    private void tbPagiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPagiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPagiKeyPressed

    private void tbPagiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPagiKeyReleased
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPagi();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPagiKeyReleased

    private void tbSoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSoreMouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getDataSore();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbSoreMouseClicked

    private void tbSoreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSoreKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbSoreKeyPressed

    private void tbSoreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSoreKeyReleased
        if (tabMode3.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataSore();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbSoreKeyReleased

    private void tbMalamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMalamMouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {
                getDataMalam();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbMalamMouseClicked

    private void tbMalamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMalamKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMalamKeyPressed

    private void tbMalamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMalamKeyReleased
        if (tabMode4.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataMalam();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbMalamKeyReleased

    private void BtnCloseIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn2ActionPerformed
        WindowEvaluasi.dispose();
        wktSimpanPagi = "";
        wktSimpanSore = "";
        wktSimpanMalam = "";
    }//GEN-LAST:event_BtnCloseIn2ActionPerformed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (Sequel.cariInteger("select count(-1) from evaluasi_catatan_tindakan_keperawatan where no_rawat='" + TNoRW.getText() + "' "
                + "and sift='" + cmbSiftNyeri.getSelectedItem().toString() + "' and evaluasi_nyeri='" + cmbEvaluasi.getSelectedItem() + "' "
                + "and tanggal='" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "'") > 0) {
            JOptionPane.showMessageDialog(null, "Data evaluasi nyeri " + cmbEvaluasi.getSelectedItem() + " sift " + cmbSiftNyeri.getSelectedItem() + " tgl. " + Ttgl.getSelectedItem() + " sudah tersimpan,..!!");
        } else {
            Sequel.menyimpan("evaluasi_catatan_tindakan_keperawatan", "'" + TNoRW.getText() + "','" + cmbSiftNyeri.getSelectedItem().toString() + "',"
                    + "'" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "','" + cmbEvaluasi.getSelectedItem().toString() + "',"
                    + "'" + Tmanajemen.getText() + "','" + Tpagi.getText() + "','" + Tsore.getText() + "','" + Tmalam.getText() + "',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "Evaluasi Nyeri");

            tampilPagi();
            tampilSore();
            tampilMalam();
            emptTeksEvaluasi();
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } 
    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void BtnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit1ActionPerformed
        if (!wktSimpanPagi.equals("")) {
            Sequel.mengedit("evaluasi_catatan_tindakan_keperawatan", "waktu_simpan=?", "sift=?, tanggal=?, evaluasi_nyeri=?, "
                    + "manajemen_nyeri=?, ket_pagi=?, ket_sore=?, ket_malam=?", 8, new String[]{
                        cmbSiftNyeri.getSelectedItem().toString(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbEvaluasi.getSelectedItem().toString(),
                        Tmanajemen.getText(), Tpagi.getText(), Tsore.getText(), Tmalam.getText(),
                        wktSimpanPagi
                    });
            tampilPagi();
            emptTeksEvaluasi();
            wktSimpanPagi = "";
            WindowEvaluasi.dispose();
        } else if (!wktSimpanSore.equals("")) {
            Sequel.mengedit("evaluasi_catatan_tindakan_keperawatan", "waktu_simpan=?", "sift=?, tanggal=?, evaluasi_nyeri=?, "
                    + "manajemen_nyeri=?, ket_pagi=?, ket_sore=?, ket_malam=?", 8, new String[]{
                        cmbSiftNyeri.getSelectedItem().toString(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbEvaluasi.getSelectedItem().toString(),
                        Tmanajemen.getText(), Tpagi.getText(), Tsore.getText(), Tmalam.getText(),
                        wktSimpanSore
                    });
            tampilSore();
            emptTeksEvaluasi();
            wktSimpanSore = "";
            WindowEvaluasi.dispose();
        } else if (!wktSimpanMalam.equals("")) {
            Sequel.mengedit("evaluasi_catatan_tindakan_keperawatan", "waktu_simpan=?", "sift=?, tanggal=?, evaluasi_nyeri=?, "
                    + "manajemen_nyeri=?, ket_pagi=?, ket_sore=?, ket_malam=?", 8, new String[]{
                        cmbSiftNyeri.getSelectedItem().toString(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbEvaluasi.getSelectedItem().toString(),
                        Tmanajemen.getText(), Tpagi.getText(), Tsore.getText(), Tmalam.getText(),
                        wktSimpanMalam
                    });
            tampilMalam();
            emptTeksEvaluasi();
            wktSimpanMalam = "";
            WindowEvaluasi.dispose();
        }
    }//GEN-LAST:event_BtnEdit1ActionPerformed

    private void BtnEdit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEdit1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnEdit1KeyPressed

    private void TpagiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpagiKeyPressed
        Valid.pindah(evt, Tpagi, Tmanajemen);
    }//GEN-LAST:event_TpagiKeyPressed

    private void TsoreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsoreKeyPressed
        Valid.pindah(evt, Tsore, Tmanajemen);
    }//GEN-LAST:event_TsoreKeyPressed

    private void TmalamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmalamKeyPressed
        Valid.pindah(evt, Tmalam, Tmanajemen);
    }//GEN-LAST:event_TmalamKeyPressed

    private void BtnEvaluasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEvaluasiActionPerformed
        WindowEvaluasi.setSize(578, 309);
        WindowEvaluasi.setLocationRelativeTo(internalFrame1);
        WindowEvaluasi.setAlwaysOnTop(false);
        WindowEvaluasi.setVisible(true);
        emptTeksEvaluasi();
    }//GEN-LAST:event_BtnEvaluasiActionPerformed

    private void cmbSiftNyeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSiftNyeriActionPerformed
        Tpagi.setText("");
        Tsore.setText("");
        Tmalam.setText("");
        
        if (cmbSiftNyeri.getSelectedIndex() == 0) {
            Tpagi.setEnabled(true);
            Tsore.setEnabled(false);
            Tmalam.setEnabled(false);
            Tpagi.requestFocus();
        } else if (cmbSiftNyeri.getSelectedIndex() == 1) {
            Tpagi.setEnabled(false);
            Tsore.setEnabled(true);
            Tmalam.setEnabled(false);
            Tsore.requestFocus();
        } else if (cmbSiftNyeri.getSelectedIndex() == 2) {
            Tpagi.setEnabled(false);
            Tsore.setEnabled(false);
            Tmalam.setEnabled(true);
            Tmalam.requestFocus();
        }
    }//GEN-LAST:event_cmbSiftNyeriActionPerformed

    private void MnHapusPagiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusPagiActionPerformed
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (wktSimpanPagi.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel,..!!");
            tbPagi.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from evaluasi_catatan_tindakan_keperawatan where waktu_simpan=?", 1, new String[]{
                    wktSimpanPagi
                }) == true) {
                    tampilPagi();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampilPagi();
                wktSimpanPagi = "";
            }
        }
    }//GEN-LAST:event_MnHapusPagiActionPerformed

    private void MnHapusSoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusSoreActionPerformed
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (wktSimpanSore.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel,..!!");
            tbSore.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from evaluasi_catatan_tindakan_keperawatan where waktu_simpan=?", 1, new String[]{
                    wktSimpanSore
                }) == true) {
                    tampilSore();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampilSore();
                wktSimpanSore = "";
            }
        }
    }//GEN-LAST:event_MnHapusSoreActionPerformed

    private void MnHapusMalamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusMalamActionPerformed
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (wktSimpanMalam.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel,..!!");
            tbMalam.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from evaluasi_catatan_tindakan_keperawatan where waktu_simpan=?", 1, new String[]{
                    wktSimpanMalam
                }) == true) {
                    tampilMalam();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampilMalam();
                wktSimpanMalam = "";
            }
        }
    }//GEN-LAST:event_MnHapusMalamActionPerformed

    private void MnGantiPagiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiPagiActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (wktSimpanPagi.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel,..!!");
        } else {
            WindowEvaluasi.setSize(578, 309);
            WindowEvaluasi.setLocationRelativeTo(internalFrame1);
            WindowEvaluasi.setAlwaysOnTop(false);
            WindowEvaluasi.setVisible(true);
        }
    }//GEN-LAST:event_MnGantiPagiActionPerformed

    private void MnGantiMalamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiMalamActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (wktSimpanMalam.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel,..!!");
        } else {
            WindowEvaluasi.setSize(578, 309);
            WindowEvaluasi.setLocationRelativeTo(internalFrame1);
            WindowEvaluasi.setAlwaysOnTop(false);
            WindowEvaluasi.setVisible(true);
        }
    }//GEN-LAST:event_MnGantiMalamActionPerformed

    private void MnGantiSoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiSoreActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (wktSimpanSore.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel,..!!");
        } else {
            WindowEvaluasi.setSize(578, 309);
            WindowEvaluasi.setLocationRelativeTo(internalFrame1);
            WindowEvaluasi.setAlwaysOnTop(false);
            WindowEvaluasi.setVisible(true);
        }
    }//GEN-LAST:event_MnGantiSoreActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCatatanTindakanKeperawatan dialog = new DlgCatatanTindakanKeperawatan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn2;
    private widget.Button BtnCopy;
    private widget.Button BtnEdit;
    private widget.Button BtnEdit1;
    private widget.Button BtnEvaluasi;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnTindakan;
    public widget.CekBox ChkAccor;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormMenu;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnContengSemua;
    private javax.swing.JMenuItem MnGantiMalam;
    private javax.swing.JMenuItem MnGantiPagi;
    private javax.swing.JMenuItem MnGantiSore;
    private javax.swing.JMenuItem MnHapus;
    private javax.swing.JMenuItem MnHapusConteng;
    private javax.swing.JMenuItem MnHapusMalam;
    private javax.swing.JMenuItem MnHapusPagi;
    private javax.swing.JMenuItem MnHapusSore;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll7;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextArea Thasil;
    private widget.TextArea Tinstruksi;
    private widget.TextBox Tmalam;
    private widget.TextArea Tmanajemen;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TnmTindakan;
    private widget.TextBox Tpagi;
    private widget.TextBox Tsore;
    private widget.Tanggal Ttgl;
    private javax.swing.JDialog WindowEvaluasi;
    private javax.swing.JDialog WindowTindakan;
    private widget.CekBox chkSaya;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbEvaluasi;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbSift;
    private widget.ComboBox cmbSiftNyeri;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.Label jLabel10;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel48;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenuMalam;
    private javax.swing.JPopupMenu jPopupMenuPagi;
    private javax.swing.JPopupMenu jPopupMenuSore;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi5;
    private widget.panelisi panelisi6;
    private widget.panelisi panelisi7;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbCPPT;
    private widget.Table tbCatatan;
    private widget.Table tbMalam;
    private widget.Table tbPagi;
    private widget.Table tbSore;
    private widget.Table tbTindakan;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select c.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(c.tanggal,'%d-%m-%Y') tgl, "
                    + "c.sift, if(c.jam_tindakan='00:00:00','',time_format(c.jam_tindakan,'%H:%i')) jamTindkaan, "
                    + "c.nm_tindakan, pg.nama ptgs, c.tanggal, c.jam_tindakan, c.nip_petugas, c.waktu_simpan from catatan_tindakan_keperawatan c "
                    + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=c.nip_petugas where "
                    + "c.tanggal between ? and ? and c.no_rawat like ? or "
                    + "c.tanggal between ? and ? and p.no_rkm_medis like ? or "
                    + "c.tanggal between ? and ? and p.nm_pasien like ? or "
                    + "c.tanggal between ? and ? and c.sift like ? or "
                    + "c.tanggal between ? and ? and c.nm_tindakan like ? or "
                    + "c.tanggal between ? and ? and pg.nama like ? order by c.sift, c.waktu_simpan");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        false,
                        rs.getString("no_rawat"), 
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl"),
                        rs.getString("sift"),
                        rs.getString("jamTindkaan"),
                        rs.getString("nm_tindakan"),
                        rs.getString("ptgs"),
                        rs.getString("tanggal"),
                        rs.getString("jam_tindakan"),
                        rs.getString("nip_petugas"),
                        rs.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        Ttgl.setDate(new Date());
        cmbSift.setSelectedIndex(0);
        cmbJam.setSelectedIndex(0);
        cmbMnt.setSelectedIndex(0);
        cmbDtk.setSelectedIndex(0);
        TnmTindakan.setText("");
        wktSimpan = "";
        chkSaya.setSelected(false);
        Valid.SetTgl(DTPCari2, Sequel.cariIsi("select DATE_ADD(now(),interval 30 day)"));
        Ttgl.requestFocus();
    }

    private void getData() {
        nip = "";
        wktSimpan = "";

        if (tbCatatan.getSelectedRow() != -1) {
            TNoRW.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 1).toString());
            TNoRM.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 2).toString());
            TNmPasien.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 3).toString());
            Valid.SetTgl(Ttgl, tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 9).toString());
            cmbSift.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 5).toString());
            cmbJam.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 10).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 10).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 10).toString().substring(6, 8));
            TnmTindakan.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 7).toString());
            nip = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 11).toString();
            TnmPetugas.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 8).toString());
            wktSimpan = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 12).toString();
        }
    }
    
    private void getDataPagi() {
        wktSimpanPagi = "";
        if (tbPagi.getSelectedRow() != -1) {
            cmbEvaluasi.setSelectedItem(tbPagi.getValueAt(tbPagi.getSelectedRow(), 0).toString());
            cmbSiftNyeri.setSelectedItem(tbPagi.getValueAt(tbPagi.getSelectedRow(), 5).toString());
            Valid.SetTgl(Ttgl, tbPagi.getValueAt(tbPagi.getSelectedRow(), 3).toString());
            Tpagi.setText(tbPagi.getValueAt(tbPagi.getSelectedRow(), 1).toString());
            Tmanajemen.setText(tbPagi.getValueAt(tbPagi.getSelectedRow(), 2).toString());
            wktSimpanPagi = tbPagi.getValueAt(tbPagi.getSelectedRow(), 4).toString();
  
            if (cmbSiftNyeri.getSelectedIndex() == 0) {
                Tpagi.setEnabled(true);
                Tsore.setEnabled(false);
                Tmalam.setEnabled(false);
                Tpagi.requestFocus();
            } else if (cmbSiftNyeri.getSelectedIndex() == 1) {
                Tpagi.setEnabled(false);
                Tsore.setEnabled(true);
                Tmalam.setEnabled(false);
                Tsore.requestFocus();
            } else if (cmbSiftNyeri.getSelectedIndex() == 2) {
                Tpagi.setEnabled(false);
                Tsore.setEnabled(false);
                Tmalam.setEnabled(true);
                Tmalam.requestFocus();
            }
        }
    }
    
    private void getDataSore() {
        wktSimpanSore = "";
        if (tbSore.getSelectedRow() != -1) {
            cmbEvaluasi.setSelectedItem(tbSore.getValueAt(tbSore.getSelectedRow(), 0).toString());
            cmbSiftNyeri.setSelectedItem(tbSore.getValueAt(tbSore.getSelectedRow(), 5).toString());
            Valid.SetTgl(Ttgl, tbSore.getValueAt(tbSore.getSelectedRow(), 3).toString());
            Tsore.setText(tbSore.getValueAt(tbSore.getSelectedRow(), 1).toString());
            Tmanajemen.setText(tbSore.getValueAt(tbSore.getSelectedRow(), 2).toString());
            wktSimpanSore = tbSore.getValueAt(tbSore.getSelectedRow(), 4).toString();
            
            if (cmbSiftNyeri.getSelectedIndex() == 0) {
                Tpagi.setEnabled(true);
                Tsore.setEnabled(false);
                Tmalam.setEnabled(false);
                Tpagi.requestFocus();
            } else if (cmbSiftNyeri.getSelectedIndex() == 1) {
                Tpagi.setEnabled(false);
                Tsore.setEnabled(true);
                Tmalam.setEnabled(false);
                Tsore.requestFocus();
            } else if (cmbSiftNyeri.getSelectedIndex() == 2) {
                Tpagi.setEnabled(false);
                Tsore.setEnabled(false);
                Tmalam.setEnabled(true);
                Tmalam.requestFocus();
            }
        }
    }
    
    private void getDataMalam() {
        wktSimpanMalam = "";
        if (tbMalam.getSelectedRow() != -1) {
            cmbEvaluasi.setSelectedItem(tbMalam.getValueAt(tbMalam.getSelectedRow(), 0).toString());
            cmbSiftNyeri.setSelectedItem(tbMalam.getValueAt(tbMalam.getSelectedRow(), 5).toString());
            Valid.SetTgl(Ttgl, tbMalam.getValueAt(tbMalam.getSelectedRow(), 3).toString());
            Tmalam.setText(tbMalam.getValueAt(tbMalam.getSelectedRow(), 1).toString());
            Tmanajemen.setText(tbMalam.getValueAt(tbMalam.getSelectedRow(), 2).toString());
            wktSimpanMalam = tbMalam.getValueAt(tbMalam.getSelectedRow(), 4).toString();
            
            if (cmbSiftNyeri.getSelectedIndex() == 0) {
                Tpagi.setEnabled(true);
                Tsore.setEnabled(false);
                Tmalam.setEnabled(false);
                Tpagi.requestFocus();
            } else if (cmbSiftNyeri.getSelectedIndex() == 1) {
                Tpagi.setEnabled(false);
                Tsore.setEnabled(true);
                Tmalam.setEnabled(false);
                Tsore.requestFocus();
            } else if (cmbSiftNyeri.getSelectedIndex() == 2) {
                Tpagi.setEnabled(false);
                Tsore.setEnabled(false);
                Tmalam.setEnabled(true);
                Tmalam.requestFocus();
            }
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getcppt());
       BtnHapus.setEnabled(akses.getcppt());
       BtnEdit.setEnabled(akses.getcppt());
       MnHapus.setEnabled(akses.getadmin());
    }
    
    private void tampilTindakan() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT nm_tindakan, kode FROM master_catatan_tindakan_keperawatan where "
                    + "nm_tindakan like '%" + TCari1.getText() + "%' group by nm_tindakan order by nm_tindakan");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        rs1.getString("nm_tindakan"),
                        rs1.getString("kode")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void setData(String norw, String norm, String nmPasien) {
        TNoRW.setText(norw);
        TNoRM.setText(norm);
        TNmPasien.setText(nmPasien);
        tampilPagi();
        tampilSore();
        tampilMalam();
        
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        
        try {
            jamSekarang = new SimpleDateFormat("HH:mm").parse(Sequel.cariIsi("SELECT TIME_FORMAT(NOW(),'%H:%i')"));
            jamSift1 = new SimpleDateFormat("HH:mm").parse("08:00");            
            jamSift2 = new SimpleDateFormat("HH:mm").parse("14:00");
            jamSift3 = new SimpleDateFormat("HH:mm").parse("20:00");
        } catch (Exception e) {
            System.out.println("Tanggal error, cek lagi..!!");
        }
        
        if (jamSift1.before(jamSekarang)) {
            cmbSift.setSelectedIndex(0);
            cmbSiftNyeri.setSelectedIndex(0);
        } 
        
        if (jamSift2.before(jamSekarang)) {
            cmbSift.setSelectedIndex(1);
            cmbSiftNyeri.setSelectedIndex(1);
        } 
        
        if (jamSift3.before(jamSekarang)) {
            cmbSift.setSelectedIndex(2);
            cmbSiftNyeri.setSelectedIndex(2);
        }
        
        if (akses.getadmin() == true) {
            nip = "-";
            TnmPetugas.setText("-");
        } else {
            nip = akses.getkode();
            TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip + "'"));
        }
    }
    
    public void isMenu() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(900, HEIGHT));
            FormMenu.setVisible(true);
            ChkAccor.setVisible(true);
            Thasil.setText("");
            Tinstruksi.setText("");
            tampilCppt();
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(22, HEIGHT));
            FormMenu.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }
    
    private void tampilCppt() {
        Valid.tabelKosong(tabModeCppt);
        try {
            pscppt = koneksi.prepareStatement("SELECT c.verifikasi, DATE_FORMAT(c.tgl_cppt,'%d-%m-%Y') tgl, if(c.cek_jam='ya',TIME_FORMAT(c.jam_cppt,'%H:%i'),'-') jam, "
                    + "c.jenis_bagian, pg1.nama nmdpjp, c.jenis_ppa, pg2.nama nmppa, c.cppt_shift, c.hasil_pemeriksaan, "
                    + "c.instruksi_nakes, c.waktu_simpan, c.no_rawat, c.tgl_cppt, c.jam_cppt from cppt c "
                    + "inner join pegawai pg1 on pg1.nik=c.nip_konsulen "
                    + "inner join pegawai pg2 on pg2.nik=c.nip_ppa where "
                    + "c.flag_hapus='tidak' and c.status='ranap' and c.no_rawat='" + TNoRW.getText() + "' order by c.tgl_cppt, c.jam_cppt");
            try {
                rscppt = pscppt.executeQuery();                
                while (rscppt.next()) {
                    tabModeCppt.addRow(new String[]{
                        rscppt.getString("tgl"),
                        rscppt.getString("jam"),
                        rscppt.getString("jenis_bagian"),
                        rscppt.getString("nmdpjp"),
                        rscppt.getString("jenis_ppa"),
                        rscppt.getString("nmppa"),
                        rscppt.getString("cppt_shift"),
                        rscppt.getString("hasil_pemeriksaan"),
                        rscppt.getString("instruksi_nakes"),
                        rscppt.getString("no_rawat"),
                        rscppt.getString("tgl_cppt"),
                        rscppt.getString("jam_cppt")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscppt != null) {
                    rscppt.close();
                }
                if (pscppt != null) {
                    pscppt.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataCppt() {
        dataKonfirmasi = "";
        
        if (tbCPPT.getSelectedRow() != -1) {
            Thasil.setText("Tgl. CPPT : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 0).toString() + ", Jam : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 1).toString() + " WITA\n\n"
                    + "" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 7).toString());
            
            //konfirmasi terapi
            if (Sequel.cariInteger("select count(-1) from cppt_konfirmasi_terapi where "
                    + "no_rawat='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString() + "' "
                    + "and tgl_cppt='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString() + "' "
                    + "and cppt_shift='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString() + "' "
                    + "and jam_cppt='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString() + "'") > 0) {

                tampilKonfirmasi(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString());
                
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n" + dataKonfirmasi);
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n" + dataKonfirmasi);
                }
            } else {
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString());
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")");
                }
            }
        }
    }
    
    private void tampilKonfirmasi(String norwt, String tglcppt, String sift, String jamcppt) {
        try {
            ps2 = koneksi.prepareStatement("select pg1.nama ptgs, date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i') jamlapor, "
                    + "pg2.nama dpjp, date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i') jamverif from cppt_konfirmasi_terapi ck "
                    + "inner join pegawai pg1 on pg1.nik=ck.nip_petugas_konfir inner join pegawai pg2 on pg2.nik=ck.nip_dpjp_konfir where "
                    + "ck.no_rawat = '" + norwt + "' and ck.tgl_cppt='" + tglcppt + "' and ck.cppt_shift='" + sift + "' "
                    + "and ck.jam_cppt='" + jamcppt + "' order by ck.waktu_simpan");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    if (dataKonfirmasi.equals("")) {
                        dataKonfirmasi = "Tgl. Lapor : " + rs2.getString("tgllapor") + ", Jam : " + rs2.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs2.getString("tglverif") + ", Jam : " + rs2.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs2.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs2.getString("dpjp");
                    } else {
                        dataKonfirmasi = dataKonfirmasi + "\n\nTgl. Lapor : " + rs2.getString("tgllapor") + ", Jam : " + rs2.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs2.getString("tglverif") + ", Jam : " + rs2.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs2.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs2.getString("dpjp");
                    }
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
    
    private void tampilPagi() {
        Valid.tabelKosong(tabMode2);
        try {
            psPagi = koneksi.prepareStatement("SELECT *, date_format(tanggal,'%d-%m-%Y') tgl from evaluasi_catatan_tindakan_keperawatan where "
                    + "no_rawat='" + TNoRW.getText() + "' and tanggal='" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "' and sift='Pagi' order by evaluasi_nyeri");
            try {
                rsPagi = psPagi.executeQuery();                
                while (rsPagi.next()) {
                    tabMode2.addRow(new String[]{                        
                        rsPagi.getString("evaluasi_nyeri"),
                        rsPagi.getString("ket_pagi"),
                        rsPagi.getString("manajemen_nyeri"),
                        rsPagi.getString("tanggal"),
                        rsPagi.getString("waktu_simpan"),
                        rsPagi.getString("sift"),
                        rsPagi.getString("tgl")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsPagi != null) {
                    rsPagi.close();
                }
                if (psPagi != null) {
                    psPagi.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilSore() {
        Valid.tabelKosong(tabMode3);
        try {
            psSore = koneksi.prepareStatement("SELECT *, date_format(tanggal,'%d-%m-%Y') tgl from evaluasi_catatan_tindakan_keperawatan where "
                    + "no_rawat='" + TNoRW.getText() + "' and tanggal='" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "' and sift='Sore' order by evaluasi_nyeri");
            try {
                rsSore = psSore.executeQuery();                
                while (rsSore.next()) {
                    tabMode3.addRow(new String[]{
                        rsSore.getString("evaluasi_nyeri"),
                        rsSore.getString("ket_sore"),
                        rsSore.getString("manajemen_nyeri"),
                        rsSore.getString("tanggal"),
                        rsSore.getString("waktu_simpan"),
                        rsSore.getString("sift"),
                        rsSore.getString("tgl")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsSore != null) {
                    rsSore.close();
                }
                if (psSore != null) {
                    psSore.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilMalam() {
        Valid.tabelKosong(tabMode4);
        try {
            psMalam = koneksi.prepareStatement("SELECT *, date_format(tanggal,'%d-%m-%Y') tgl from evaluasi_catatan_tindakan_keperawatan where "
                    + "no_rawat='" + TNoRW.getText() + "' and tanggal='" + Valid.SetTgl(Ttgl.getSelectedItem() + "") + "' and sift='Malam' order by evaluasi_nyeri");
            try {
                rsMalam = psMalam.executeQuery();                
                while (rsMalam.next()) {
                    tabMode4.addRow(new String[]{
                        rsMalam.getString("evaluasi_nyeri"),
                        rsMalam.getString("ket_malam"),
                        rsMalam.getString("manajemen_nyeri"),
                        rsMalam.getString("tanggal"),
                        rsMalam.getString("waktu_simpan"),
                        rsMalam.getString("sift"),
                        rsMalam.getString("tgl")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsMalam != null) {
                    rsMalam.close();
                }
                if (psMalam != null) {
                    psMalam.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void emptTeksEvaluasi() {
        Tmanajemen.setText("");
        Tpagi.setText("");
        Tsore.setText("");
        Tmalam.setText("");

        if (cmbSiftNyeri.getSelectedIndex() == 0) {
            Tpagi.setEnabled(true);
            Tsore.setEnabled(false);
            Tmalam.setEnabled(false);
            Tpagi.requestFocus();
        } else if (cmbSiftNyeri.getSelectedIndex() == 1) {
            Tpagi.setEnabled(false);
            Tsore.setEnabled(true);
            Tmalam.setEnabled(false);
            Tsore.requestFocus();
        } else if (cmbSiftNyeri.getSelectedIndex() == 2) {
            Tpagi.setEnabled(false);
            Tsore.setEnabled(false);
            Tmalam.setEnabled(true);
            Tmalam.requestFocus();
        }
    }
}

