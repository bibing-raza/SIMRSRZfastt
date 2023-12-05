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
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String nip = "", wktSimpan = "", kodeTindakan = "";
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
        WindowTindakan = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbTindakan = new widget.Table();
        panelisi5 = new widget.panelisi();
        jLabel48 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCloseIn1 = new widget.Button();
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
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 130));
        panelGlass7.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass7.add(jLabel3);
        jLabel3.setBounds(2, 10, 100, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        panelGlass7.add(TNoRW);
        TNoRW.setBounds(107, 10, 122, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setName("TNmPasien"); // NOI18N
        panelGlass7.add(TNmPasien);
        TNmPasien.setBounds(308, 10, 291, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        panelGlass7.add(TNoRM);
        TNoRM.setBounds(233, 10, 70, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tanggal :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass7.add(jLabel4);
        jLabel4.setBounds(2, 38, 100, 23);

        Ttgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-12-2023" }));
        Ttgl.setDisplayFormat("dd-MM-yyyy");
        Ttgl.setName("Ttgl"); // NOI18N
        Ttgl.setOpaque(false);
        Ttgl.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(Ttgl);
        Ttgl.setBounds(107, 38, 90, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Sift Petugas :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(200, 38, 80, 23);

        cmbSift.setForeground(new java.awt.Color(0, 0, 0));
        cmbSift.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Pagi", "Sore", "Malam" }));
        cmbSift.setName("cmbSift"); // NOI18N
        panelGlass7.add(cmbSift);
        cmbSift.setBounds(285, 38, 70, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Jam Tindakan :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(356, 38, 90, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbJam);
        cmbJam.setBounds(453, 38, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbMnt);
        cmbMnt.setBounds(504, 38, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbDtk);
        cmbDtk.setBounds(555, 38, 45, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nama Tindakan :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass7.add(jLabel9);
        jLabel9.setBounds(2, 66, 100, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Nama Petugas :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass7.add(jLabel10);
        jLabel10.setBounds(2, 94, 100, 23);

        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        panelGlass7.add(TnmPetugas);
        TnmPetugas.setBounds(107, 94, 491, 23);

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
        panelGlass7.add(BtnPetugas);
        BtnPetugas.setBounds(603, 94, 28, 23);

        TnmTindakan.setForeground(new java.awt.Color(0, 0, 0));
        TnmTindakan.setName("TnmTindakan"); // NOI18N
        panelGlass7.add(TnmTindakan);
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
        panelGlass7.add(BtnTindakan);
        BtnTindakan.setBounds(603, 66, 28, 23);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

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
    private widget.Button BtnCopy;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnTindakan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnContengSemua;
    private javax.swing.JMenuItem MnHapus;
    private javax.swing.JMenuItem MnHapusConteng;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TnmTindakan;
    private widget.Tanggal Ttgl;
    private javax.swing.JDialog WindowTindakan;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbSift;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame6;
    private widget.Label jLabel10;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel48;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi5;
    private widget.Table tbCatatan;
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
        nip = "-";
        TnmPetugas.setText("-");
        wktSimpan = "";
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
            cmbSift.setSelectedIndex(1);
        } 
        
        if (jamSift2.before(jamSekarang)) {
            cmbSift.setSelectedIndex(2);
        } 
        
        if (jamSift3.before(jamSekarang)) {
            cmbSift.setSelectedIndex(3);
        }
        
        if (akses.getadmin() == true) {
            nip = "-";
            TnmPetugas.setText("-");
        } else {
            nip = akses.getkode();
            TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip + "'"));
        }
    }
}
