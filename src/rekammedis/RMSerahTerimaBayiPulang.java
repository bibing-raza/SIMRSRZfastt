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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;

/**
 *
 * @author dosen
 */
public class RMSerahTerimaBayiPulang extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private int i = 0, x = 0;
    private String nip = "", norawatIbu = "", normIbu = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMSerahTerimaBayiPulang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien/Bayi", "Jns. Kelamin", "Tgl. Lahir", "Ruang Perawatan",
            "Nama Ibu Kandung", "Diagnosa Bayi", "Tgl. Pulang", "BB Lahir", "BB Pulang", "Nama Petugas",
            "Nama Orang Tua/Wali", "tgl_lahir", "tgl_pulang", "nip_petugas", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbSerah.setModel(tabMode);
        tbSerah.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSerah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 17; i++) {
            TableColumn column = tbSerah.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(170);
            } else if (i == 6) {
                column.setPreferredWidth(160);
            } else if (i == 7) {
                column.setPreferredWidth(220);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(75);
            } else if (i == 10) {
                column.setPreferredWidth(75);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setPreferredWidth(220);
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
            } 
        }
        tbSerah.setDefaultRenderer(Object.class, new WarnaTable());

        TnmIbu.setDocument(new batasInput((int) 100).getKata(TnmIbu));
        TbbLahir.setDocument(new batasInput((int) 7).getKata(TbbLahir));
        TbbPulang.setDocument(new batasInput((int) 7).getKata(TbbPulang));
        TnmOrtu.setDocument(new batasInput((int) 100).getKata(TnmOrtu));
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

        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel5 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel8 = new widget.Label();
        TnmIbu = new widget.TextBox();
        jLabel10 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        Talamat = new widget.TextArea();
        jLabel11 = new widget.Label();
        Tdiagnosa = new widget.TextBox();
        jLabel9 = new widget.Label();
        TtglLahir = new widget.TextBox();
        jLabel12 = new widget.Label();
        TtglPulang = new widget.Tanggal();
        jLabel13 = new widget.Label();
        TbbLahir = new widget.TextBox();
        jLabel25 = new widget.Label();
        TbbPulang = new widget.TextBox();
        jLabel26 = new widget.Label();
        jLabel14 = new widget.Label();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        jLabel15 = new widget.Label();
        TnmOrtu = new widget.TextBox();
        jLabel16 = new widget.Label();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbSerah = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Serah Terima Bayi Pulang ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
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
        panelGlass8.add(BtnHapus);

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setMnemonic('G');
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

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Pulang :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-03-2025" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-03-2025" }));
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

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(195, 243));
        PanelInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        PanelInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 130, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        PanelInput.add(TNoRw);
        TNoRw.setBounds(134, 10, 122, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        PanelInput.add(TNoRM);
        TNoRM.setBounds(260, 10, 70, 23);

        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        PanelInput.add(TPasien);
        TPasien.setBounds(335, 10, 395, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Ruang Rawat :");
        jLabel5.setName("jLabel5"); // NOI18N
        PanelInput.add(jLabel5);
        jLabel5.setBounds(0, 38, 130, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setBackground(new java.awt.Color(245, 250, 240));
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        PanelInput.add(TrgRawat);
        TrgRawat.setBounds(134, 38, 190, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Nama Ibu Kandung :");
        jLabel8.setName("jLabel8"); // NOI18N
        PanelInput.add(jLabel8);
        jLabel8.setBounds(325, 38, 120, 23);

        TnmIbu.setBackground(new java.awt.Color(245, 250, 240));
        TnmIbu.setForeground(new java.awt.Color(0, 0, 0));
        TnmIbu.setName("TnmIbu"); // NOI18N
        TnmIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmIbuKeyPressed(evt);
            }
        });
        PanelInput.add(TnmIbu);
        TnmIbu.setBounds(450, 38, 280, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Alamat :");
        jLabel10.setName("jLabel10"); // NOI18N
        PanelInput.add(jLabel10);
        jLabel10.setBounds(0, 66, 130, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        Talamat.setEditable(false);
        Talamat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Talamat.setColumns(20);
        Talamat.setRows(5);
        Talamat.setName("Talamat"); // NOI18N
        Talamat.setPreferredSize(new java.awt.Dimension(162, 2000));
        scrollPane14.setViewportView(Talamat);

        PanelInput.add(scrollPane14);
        scrollPane14.setBounds(134, 66, 596, 50);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Diagnosa Medik Bayi :");
        jLabel11.setName("jLabel11"); // NOI18N
        PanelInput.add(jLabel11);
        jLabel11.setBounds(0, 121, 130, 23);

        Tdiagnosa.setBackground(new java.awt.Color(245, 250, 240));
        Tdiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        Tdiagnosa.setName("Tdiagnosa"); // NOI18N
        Tdiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaKeyPressed(evt);
            }
        });
        PanelInput.add(Tdiagnosa);
        Tdiagnosa.setBounds(134, 121, 596, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Tgl. Lahir Bayi :");
        jLabel9.setName("jLabel9"); // NOI18N
        PanelInput.add(jLabel9);
        jLabel9.setBounds(0, 149, 130, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setBackground(new java.awt.Color(245, 250, 240));
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        PanelInput.add(TtglLahir);
        TtglLahir.setBounds(134, 149, 170, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Pulang Bayi :");
        jLabel12.setName("jLabel12"); // NOI18N
        PanelInput.add(jLabel12);
        jLabel12.setBounds(305, 149, 94, 23);

        TtglPulang.setEditable(false);
        TtglPulang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-03-2025" }));
        TtglPulang.setDisplayFormat("dd-MM-yyyy");
        TtglPulang.setName("TtglPulang"); // NOI18N
        TtglPulang.setOpaque(false);
        TtglPulang.setPreferredSize(new java.awt.Dimension(90, 23));
        PanelInput.add(TtglPulang);
        TtglPulang.setBounds(405, 149, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("BB Lahir :");
        jLabel13.setName("jLabel13"); // NOI18N
        PanelInput.add(jLabel13);
        jLabel13.setBounds(565, 149, 70, 23);

        TbbLahir.setBackground(new java.awt.Color(245, 250, 240));
        TbbLahir.setForeground(new java.awt.Color(0, 0, 0));
        TbbLahir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TbbLahir.setName("TbbLahir"); // NOI18N
        TbbLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbLahirKeyPressed(evt);
            }
        });
        PanelInput.add(TbbLahir);
        TbbLahir.setBounds(640, 149, 60, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("gram");
        jLabel25.setName("jLabel25"); // NOI18N
        PanelInput.add(jLabel25);
        jLabel25.setBounds(707, 149, 40, 23);

        TbbPulang.setBackground(new java.awt.Color(245, 250, 240));
        TbbPulang.setForeground(new java.awt.Color(0, 0, 0));
        TbbPulang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TbbPulang.setName("TbbPulang"); // NOI18N
        TbbPulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbPulangKeyPressed(evt);
            }
        });
        PanelInput.add(TbbPulang);
        TbbPulang.setBounds(640, 177, 60, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("gram");
        jLabel26.setName("jLabel26"); // NOI18N
        PanelInput.add(jLabel26);
        jLabel26.setBounds(707, 177, 40, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Nama Petugas :");
        jLabel14.setName("jLabel14"); // NOI18N
        PanelInput.add(jLabel14);
        jLabel14.setBounds(0, 177, 130, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setBackground(new java.awt.Color(245, 250, 240));
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        PanelInput.add(TnmPetugas);
        TnmPetugas.setBounds(134, 177, 390, 23);

        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('2');
        BtnPetugas.setToolTipText("Alt+2");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        PanelInput.add(BtnPetugas);
        BtnPetugas.setBounds(525, 177, 28, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Nama Orang Tua/Wali :");
        jLabel15.setName("jLabel15"); // NOI18N
        PanelInput.add(jLabel15);
        jLabel15.setBounds(0, 205, 130, 23);

        TnmOrtu.setBackground(new java.awt.Color(245, 250, 240));
        TnmOrtu.setForeground(new java.awt.Color(0, 0, 0));
        TnmOrtu.setName("TnmOrtu"); // NOI18N
        PanelInput.add(TnmOrtu);
        TnmOrtu.setBounds(134, 205, 390, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("BB Pulang :");
        jLabel16.setName("jLabel16"); // NOI18N
        PanelInput.add(jLabel16);
        jLabel16.setBounds(565, 177, 70, 23);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbSerah.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbSerah.setName("tbSerah"); // NOI18N
        tbSerah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSerahMouseClicked(evt);
            }
        });
        tbSerah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSerahKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbSerah);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (Sequel.menyimpantf("serah_terima_bayi_pulang_perinatologi", "?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 11, new String[]{
                TNoRw.getText(), TrgRawat.getText(), TnmIbu.getText(), Tdiagnosa.getText(), Valid.SetTgl(TtglPulang.getSelectedItem() + ""),
                TbbLahir.getText(), TbbPulang.getText(), nip, TnmOrtu.getText(), Sequel.cariIsi("select now()"), TPasien.getText()
            }) == true) {
                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Serah Terima Bayi Pulang", "Simpan");
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
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
        tampil();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (tbSerah.getSelectedRow() > -1) {
                if (akses.getadmin() == true) {
                    ganti();
                } else {
                    if (nip.equals(akses.getkode())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas yang bernama " + tbSerah.getValueAt(tbSerah.getSelectedRow(), 10).toString() + " ..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            }
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void ganti() {
        if (Sequel.mengedittf("serah_terima_bayi_pulang_perinatologi", "no_rawat=?", "nm_ibu_kandung=?, diagnosa=?, "
                + "tgl_pulang=?, bb_lahir=?, bb_pulang=?, nip_petugas=?, nm_ortu_wali=?, nm_bayi=?", 9, new String[]{
                    TnmIbu.getText(), Tdiagnosa.getText(), Valid.SetTgl(TtglPulang.getSelectedItem() + ""),
                    TbbLahir.getText(), TbbPulang.getText(), nip, TnmOrtu.getText(), TPasien.getText(),
                    tbSerah.getValueAt(tbSerah.getSelectedRow(), 0).toString()
                }) == true) {
            Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Serah Terima Bayi Pulang", "Ganti");
            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
        }
    }
    
    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGantiActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnBatal,TCari);}
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

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        akses.setform("RMSerahTerimaBayiPulang");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void tbSerahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSerahKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbSerahKeyPressed

    private void tbSerahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSerahMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbSerahMouseClicked

    private void TnmIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmIbuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdiagnosa.requestFocus();
        }
    }//GEN-LAST:event_TnmIbuKeyPressed

    private void TdiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglPulang.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaKeyPressed

    private void TbbLahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbLahirKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TbbPulang.requestFocus();
        }
    }//GEN-LAST:event_TbbLahirKeyPressed

    private void TbbPulangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbPulangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnPetugas.requestFocus();
        }
    }//GEN-LAST:event_TbbPulangKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbSerah.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                hapus();
            } else {
                if (nip.equals(akses.getkode())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh perawat yang bernama " + tbSerah.getValueAt(tbSerah.getSelectedRow(), 10).toString() + " ..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from serah_terima_bayi_pulang_perinatologi where no_rawat=?", 1, new String[]{
                tbSerah.getValueAt(tbSerah.getSelectedRow(), 0).toString()
            }) == true) {
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
    
    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbSerah.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            
            if (TnmIbu.getText().equals("")) {
                param.put("nmIbu", "-");
            } else {
                param.put("nmIbu", TnmIbu.getText());
            }
            
            if (Talamat.getText().equals("")) {
                param.put("alamat", "-");
            } else {
                param.put("alamat", Talamat.getText());
            }
            
            if (Tdiagnosa.getText().equals("")) {
                param.put("diagnosa", "-");
            } else {
                param.put("diagnosa", Tdiagnosa.getText());
            }
            
            param.put("tglLahir", TtglLahir.getText());
            param.put("tglPulang", Valid.SetTglINDONESIA(Valid.SetTgl(TtglPulang.getSelectedItem() + "")));
            
            if (TbbLahir.getText().equals("")) {
                param.put("bbLahir", "..... gram");
            } else {
                param.put("bbLahir", TbbLahir.getText() + " gram");
            }
            
            if (TbbPulang.getText().equals("")) {
                param.put("bbPulang", "..... gram");
            } else {
                param.put("bbPulang", TbbPulang.getText() + " gram");
            }
            
            param.put("nmPetugas", "( " + TnmPetugas.getText() + " )");
            param.put("nmOrtu", "( " + TnmOrtu.getText() + " )");
            
            Valid.MyReport("rptSerahTerimaBayiPulang.jasper", "report", "::[ Lembar Serah Terima Bayi Pulang ]::",
                "SELECT now() tanggal", param);

            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } 
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmIbu.requestFocus();
        }
    }//GEN-LAST:event_TPasienKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMSerahTerimaBayiPulang dialog = new RMSerahTerimaBayiPulang(new javax.swing.JFrame(), true);
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
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea Talamat;
    private widget.TextBox TbbLahir;
    private widget.TextBox TbbPulang;
    private widget.TextBox Tdiagnosa;
    private widget.TextBox TnmIbu;
    private widget.TextBox TnmOrtu;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TrgRawat;
    private widget.TextBox TtglLahir;
    private widget.Tanggal TtglPulang;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollPane14;
    private widget.Table tbSerah;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT st.*, p.no_rkm_medis, if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(p.tgl_lahir,'%d/%m/%Y') tglLahir, "
                    + "date_format(st.tgl_pulang,'%d/%m/%Y') tglPulang, pg.nama nmPetugas, p.tgl_lahir FROM serah_terima_bayi_pulang_perinatologi st "
                    + "inner join reg_periksa rp on rp.no_rawat=st.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=st.nip_petugas WHERE "
                    + "st.tgl_pulang between ? and ? and st.no_rawat like ? or "
                    + "st.tgl_pulang between ? and ? and p.no_rkm_medis like ? or "
                    + "st.tgl_pulang between ? and ? and st.nm_bayi like ? or "
                    + "st.tgl_pulang between ? and ? and if(p.jk='L','Laki-laki','Perempuan') like ? or "
                    + "st.tgl_pulang between ? and ? and st.nm_ibu_kandung like ? or "
                    + "st.tgl_pulang between ? and ? and pg.nama like ? ORDER BY st.waktu_simpan desc");
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
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_bayi"),
                        rs.getString("jenkel"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("nm_ibu_kandung"),
                        rs.getString("diagnosa"),
                        rs.getString("tglPulang"),
                        rs.getString("bb_lahir"),
                        rs.getString("bb_pulang"),
                        rs.getString("nmPetugas"),
                        rs.getString("nm_ortu_wali"),                        
                        rs.getString("tgl_lahir"),
                        rs.getString("tgl_pulang"),
                        rs.getString("nip_petugas"),
                        rs.getString("waktu_simpan")
                    });
                }                
            } catch (Exception e) {
                System.out.println("rekammedis.RMSerahTerimaBayiPulang.tampil() : " + e);
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
    
    public void emptTeks() {
        TNoRw.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        TrgRawat.setText("");
        TnmIbu.setText("");
        Talamat.setText("");
        Tdiagnosa.setText("");
        TtglLahir.setText("");
        TtglPulang.setDate(new Date());
        TbbLahir.setText("");
        TbbPulang.setText("");
        TnmOrtu.setText("");
    }

    private void getData() {
        nip = "";
        if (tbSerah.getSelectedRow() != -1) {
            TNoRw.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 0).toString());
            TNoRM.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 1).toString());
            TPasien.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 5).toString());
            TnmIbu.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 6).toString());
            Talamat.setText(Sequel.cariIsi("SELECT concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', Kab. ',kb.nm_kab) FROM pasien p "
                    + "INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab where p.no_rkm_medis='" + TNoRM.getText() + "'"));
            Tdiagnosa.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 7).toString());
            TtglLahir.setText(Valid.SetTglINDONESIA(tbSerah.getValueAt(tbSerah.getSelectedRow(), 13).toString()));
            Valid.SetTgl(TtglPulang, tbSerah.getValueAt(tbSerah.getSelectedRow(), 14).toString());
            TbbLahir.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 9).toString());
            TbbPulang.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 10).toString());
            nip = tbSerah.getValueAt(tbSerah.getSelectedRow(), 15).toString();
            TnmPetugas.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 11).toString());
            TnmOrtu.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 12).toString());
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnGanti.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        
        if (akses.getjml2() >= 1) {
            nip = akses.getkode();            
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPetugas, nip);
            if (TnmPetugas.getText().equals("")) {
                nip = "-";
                TnmPetugas.setText("-");
            }
        }
    }
    
    public void setData(String norwt, String rgrawat) {
        TNoRw.setText(norwt);
        TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norwt + "'"));
        TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        TrgRawat.setText(rgrawat);        
        Talamat.setText(Sequel.cariIsi("SELECT concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', Kab. ',kb.nm_kab) FROM pasien p "
                + "INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec "
                + "INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab where p.no_rkm_medis='" + TNoRM.getText() + "'"));
        TtglLahir.setText(Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + TNoRM.getText() + "'")));
        TbbLahir.setText(Sequel.cariIsi("select ifnull(bbl,'') from asesmen_keperawatan_perinatologi where no_rawat='" + norwt + "'"));
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(new Date());
        norawatIbu = Sequel.cariIsi("select ifnull(no_rawat_ibu,'-') from pasien_bayi where no_rkm_medis='" + TNoRM.getText() + "'");        
        
        if (norawatIbu.equals("-") || norawatIbu.equals("")) {            
            normIbu = "-";
            TnmIbu.setText(Sequel.cariIsi("select nm_ibu from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        } else {
            normIbu = Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norawatIbu + "'");
            TnmIbu.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + normIbu + "'"));
        }
        
        TCari.setText(norwt);
    }
}
