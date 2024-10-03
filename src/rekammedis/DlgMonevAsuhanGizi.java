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
import kepegawaian.DlgCariPetugas;

public class DlgMonevAsuhanGizi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private int i = 0, x = 0;
    private String nip = "", angkaBulan = "";;

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgMonevAsuhanGizi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Monev", "Ruang Perawatan", "Perkembangan Fisik/Klinis", 
            "Perkembangan Diet", "Evaluasi Tindak Lanjut", "Ahli Gizi", 
            "tgl_monev", "nip_petugas", "waktu_simpan"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbMonev.setModel(tabMode);
        tbMonev.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMonev.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbMonev.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(250);
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
            }
        }
        tbMonev.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "No.", "Ruang Rawat/Gedung", "Jlh. Pasien Dirawat", "Jlh. Termonev", "Jlh. Belum Termonev", 
            "Persentase Termonev", "Persentase Belum Termonev"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbPersen.setModel(tabMode1);
        tbPersen.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPersen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbPersen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            } else if (i == 2) {
                column.setPreferredWidth(130);
            } else if (i == 3) {
                column.setPreferredWidth(130);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } 
        }
        tbPersen.setDefaultRenderer(Object.class, new WarnaTable());
        
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
                    chkSaya.setSelected(false);
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
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel17 = new widget.Label();
        jLabel25 = new widget.Label();
        TNoRW = new widget.TextBox();
        TNoRM = new widget.TextBox();
        Tpasien = new widget.TextBox();
        jLabel26 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel27 = new widget.Label();
        tglMonev = new widget.Tanggal();
        Scroll10 = new widget.ScrollPane();
        Tfisik = new widget.TextArea();
        jLabel18 = new widget.Label();
        Scroll11 = new widget.ScrollPane();
        Tdiet = new widget.TextArea();
        jLabel19 = new widget.Label();
        Scroll12 = new widget.ScrollPane();
        Tevaluasi = new widget.TextArea();
        jLabel20 = new widget.Label();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        chkSaya = new widget.CekBox();
        jLabel30 = new widget.Label();
        TtglAsuhan = new widget.TextBox();
        TabData = new javax.swing.JTabbedPane();
        FormData = new widget.PanelBiasa();
        Scroll = new widget.ScrollPane();
        tbMonev = new widget.Table();
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
        jLabel28 = new widget.Label();
        tgl1 = new widget.Tanggal();
        jLabel29 = new widget.Label();
        tgl2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        FormPersen = new widget.PanelBiasa();
        Scroll2 = new widget.ScrollPane();
        tbPersen = new widget.Table();
        panelGlass12 = new widget.panelisi();
        jLabel31 = new widget.Label();
        cmbBulan = new widget.ComboBox();
        jLabel35 = new widget.Label();
        Ttahun = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCetakPersen = new widget.Button();
        BtnKeluar1 = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Monitoring dan Evaluasi Asuhan Gizi Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(380, 338));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Perkembangan Fisik/Klinis : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 94, 175, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Pasien : ");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 10, 120, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        FormInput.add(TNoRW);
        TNoRW.setBounds(122, 10, 120, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(245, 10, 70, 23);

        Tpasien.setEditable(false);
        Tpasien.setForeground(new java.awt.Color(0, 0, 0));
        Tpasien.setName("Tpasien"); // NOI18N
        FormInput.add(Tpasien);
        Tpasien.setBounds(318, 10, 310, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Ruang Rawat : ");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(0, 38, 120, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(122, 38, 505, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Tgl. Monev : ");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(453, 66, 79, 23);

        tglMonev.setEditable(false);
        tglMonev.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-08-2024" }));
        tglMonev.setDisplayFormat("dd-MM-yyyy");
        tglMonev.setName("tglMonev"); // NOI18N
        tglMonev.setOpaque(false);
        tglMonev.setPreferredSize(new java.awt.Dimension(95, 23));
        FormInput.add(tglMonev);
        tglMonev.setBounds(533, 66, 95, 23);

        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        Tfisik.setColumns(20);
        Tfisik.setRows(5);
        Tfisik.setName("Tfisik"); // NOI18N
        Tfisik.setPreferredSize(new java.awt.Dimension(190, 1000));
        Tfisik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TfisikKeyPressed(evt);
            }
        });
        Scroll10.setViewportView(Tfisik);

        FormInput.add(Scroll10);
        Scroll10.setBounds(177, 94, 450, 55);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Perkembangan Diet : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 154, 175, 23);

        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);

        Tdiet.setColumns(20);
        Tdiet.setRows(5);
        Tdiet.setName("Tdiet"); // NOI18N
        Tdiet.setPreferredSize(new java.awt.Dimension(190, 1000));
        Tdiet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdietKeyPressed(evt);
            }
        });
        Scroll11.setViewportView(Tdiet);

        FormInput.add(Scroll11);
        Scroll11.setBounds(177, 154, 450, 55);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Evaluasi dan Tindak Lanjut : ");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(0, 214, 175, 23);

        Scroll12.setName("Scroll12"); // NOI18N
        Scroll12.setOpaque(true);

        Tevaluasi.setColumns(20);
        Tevaluasi.setRows(5);
        Tevaluasi.setName("Tevaluasi"); // NOI18N
        Tevaluasi.setPreferredSize(new java.awt.Dimension(190, 1000));
        Tevaluasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TevaluasiKeyPressed(evt);
            }
        });
        Scroll12.setViewportView(Tevaluasi);

        FormInput.add(Scroll12);
        Scroll12.setBounds(177, 214, 450, 82);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Ahli Gizi : ");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(0, 302, 120, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(122, 302, 390, 23);

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
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(515, 302, 28, 23);

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
        FormInput.add(chkSaya);
        chkSaya.setBounds(550, 302, 90, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tgl. Asuhan Gizi : ");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(0, 66, 120, 23);

        TtglAsuhan.setEditable(false);
        TtglAsuhan.setForeground(new java.awt.Color(0, 0, 0));
        TtglAsuhan.setName("TtglAsuhan"); // NOI18N
        FormInput.add(TtglAsuhan);
        TtglAsuhan.setBounds(122, 66, 170, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        TabData.setBackground(new java.awt.Color(255, 255, 254));
        TabData.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabData.setName("TabData"); // NOI18N
        TabData.setPreferredSize(new java.awt.Dimension(270, 106));
        TabData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabDataMouseClicked(evt);
            }
        });

        FormData.setBorder(null);
        FormData.setToolTipText("");
        FormData.setName("FormData"); // NOI18N
        FormData.setPreferredSize(new java.awt.Dimension(870, 2421));
        FormData.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbMonev.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbMonev.setToolTipText("Silahkan klik untuk memilih data yang akan diedit atau dihapus");
        tbMonev.setName("tbMonev"); // NOI18N
        tbMonev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMonevMouseClicked(evt);
            }
        });
        tbMonev.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMonevKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbMonev);

        FormData.add(Scroll, java.awt.BorderLayout.CENTER);

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
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
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

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Tgl. Monev : ");
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(jLabel28);

        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-08-2024" }));
        tgl1.setDisplayFormat("dd-MM-yyyy");
        tgl1.setName("tgl1"); // NOI18N
        tgl1.setOpaque(false);
        tgl1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(tgl1);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("s.d.");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass10.add(jLabel29);

        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-08-2024" }));
        tgl2.setDisplayFormat("dd-MM-yyyy");
        tgl2.setName("tgl2"); // NOI18N
        tgl2.setOpaque(false);
        tgl2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(tgl2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(300, 23));
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

        FormData.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        TabData.addTab("Data Monitoring & Evaluasi", FormData);

        FormPersen.setBorder(null);
        FormPersen.setToolTipText("");
        FormPersen.setName("FormPersen"); // NOI18N
        FormPersen.setPreferredSize(new java.awt.Dimension(870, 2421));
        FormPersen.setLayout(new java.awt.BorderLayout());

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbPersen.setToolTipText("");
        tbPersen.setName("tbPersen"); // NOI18N
        Scroll2.setViewportView(tbPersen);

        FormPersen.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Bulan :");
        jLabel31.setName("jLabel31"); // NOI18N
        jLabel31.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass12.add(jLabel31);

        cmbBulan.setForeground(new java.awt.Color(0, 0, 0));
        cmbBulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "Nopember", "Desember" }));
        cmbBulan.setName("cmbBulan"); // NOI18N
        cmbBulan.setPreferredSize(new java.awt.Dimension(85, 23));
        cmbBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBulanActionPerformed(evt);
            }
        });
        panelGlass12.add(cmbBulan);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Tahun :");
        jLabel35.setName("jLabel35"); // NOI18N
        jLabel35.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass12.add(jLabel35);

        Ttahun.setForeground(new java.awt.Color(0, 0, 0));
        Ttahun.setName("Ttahun"); // NOI18N
        Ttahun.setPreferredSize(new java.awt.Dimension(60, 23));
        Ttahun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtahunKeyPressed(evt);
            }
        });
        panelGlass12.add(Ttahun);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('T');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+T");
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
        panelGlass12.add(BtnCari1);

        BtnCetakPersen.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetakPersen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetakPersen.setMnemonic('K');
        BtnCetakPersen.setText("Cetak");
        BtnCetakPersen.setToolTipText("Alt+K");
        BtnCetakPersen.setName("BtnCetakPersen"); // NOI18N
        BtnCetakPersen.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnCetakPersen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakPersenActionPerformed(evt);
            }
        });
        panelGlass12.add(BtnCetakPersen);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        panelGlass12.add(BtnKeluar1);

        FormPersen.add(panelGlass12, java.awt.BorderLayout.PAGE_END);

        TabData.addTab("Persentase Monev Asuhan Gizi", FormPersen);

        internalFrame1.add(TabData, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Data Pasien");
        } else {
            if (Sequel.menyimpantf("monev_asuhan_gizi", "?,?,?,?,?,?,?,?", "No. Rawat & Tgl. Monev", 8, new String[]{
                TNoRW.getText(), Valid.SetTgl(tglMonev.getSelectedItem() + ""), TrgRawat.getText(), Tfisik.getText(),
                Tdiet.getText(), Tevaluasi.getText(), nip, Sequel.cariIsi("select now()")
            }) == true) {
                Valid.SetTgl(tgl1, Valid.SetTgl(tglMonev.getSelectedItem() + ""));
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
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Data Pasien");
        } else {
            if (tbMonev.getSelectedRow() > -1) {
                if (akses.getadmin() == true) {
                    ganti();
                } else {
                    if (nip.equals(tbMonev.getValueAt(tbMonev.getSelectedRow(), 10).toString())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh ahli gizi yang melakukan monev asuhan gizi..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            }
        }
}//GEN-LAST:event_BtnGantiActionPerformed

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
        tampil();
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbMonevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMonevMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbMonevMouseClicked

    private void tbMonevKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMonevKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbMonevKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbMonev.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                hapus();
            } else {
                if (nip.equals(tbMonev.getValueAt(tbMonev.getSelectedRow(), 10).toString())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh ahli gizi yang melakukan monev asuhan gizi..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnHapusActionPerformed(null);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        akses.setform("DlgMonevAsuhanGizi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

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

    private void TfisikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfisikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tdiet.requestFocus();
        }
    }//GEN-LAST:event_TfisikKeyPressed

    private void TdietKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdietKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tevaluasi.requestFocus();
        }
    }//GEN-LAST:event_TdietKeyPressed

    private void TevaluasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TevaluasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnPetugas.requestFocus();
        }
    }//GEN-LAST:event_TevaluasiKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbMonev.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", Tpasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            param.put("tglasuhan", Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_asuhan from asuhan_gizi_ranap where no_rawat='" + TNoRW.getText() + "'")));
            
            Valid.MyReport("rptMonevAsuhanGizi.jasper", "report", "::[ Laporan Monitoring Dan Evaluasi Asuhan Gizi Pasien ]::",
                    "SELECT * from monev_asuhan_gizi where no_rawat='" + TNoRW.getText() + "' order by tgl_monev", param);

            emptTeks();
            tampil();
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
            tbMonev.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void cmbBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBulanActionPerformed
        angkaBulan = "";
        if (cmbBulan.getSelectedIndex() == 0) {
            angkaBulan = "1";
        } else if (cmbBulan.getSelectedIndex() == 1) {
            angkaBulan = "2";
        } else if (cmbBulan.getSelectedIndex() == 2) {
            angkaBulan = "3";
        } else if (cmbBulan.getSelectedIndex() == 3) {
            angkaBulan = "4";
        } else if (cmbBulan.getSelectedIndex() == 4) {
            angkaBulan = "5";
        } else if (cmbBulan.getSelectedIndex() == 5) {
            angkaBulan = "6";
        } else if (cmbBulan.getSelectedIndex() == 6) {
            angkaBulan = "7";
        } else if (cmbBulan.getSelectedIndex() == 7) {
            angkaBulan = "8";
        } else if (cmbBulan.getSelectedIndex() == 8) {
            angkaBulan = "9";
        } else if (cmbBulan.getSelectedIndex() == 9) {
            angkaBulan = "10";
        } else if (cmbBulan.getSelectedIndex() == 10) {
            angkaBulan = "11";
        } else if (cmbBulan.getSelectedIndex() == 11) {
            angkaBulan = "12";
        }
        BtnCari1ActionPerformed(null);
    }//GEN-LAST:event_cmbBulanActionPerformed

    private void TtahunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtahunKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TtahunKeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilPersentase();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnCetakPersenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakPersenActionPerformed
        if (tbPersen.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan tampilkan datanya terlebih dulu..!!!");
        } else {
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE BULAN " + cmbBulan.getSelectedItem().toString().toUpperCase() + " TAHUN " + Ttahun.getText());

            Valid.MyReport("rptPersentaseMonevAsuhan.jasper", "report", "::[ Persentase Monev Asuhan Gizi Pasien ]::",
                    "select * from (select a.nm_gedung, a.jlh_px_ranap, ifnull(b.jlh_px_asuhan,0) jlh_px_monev, "
                    + "(a.jlh_px_ranap-ifnull(b.jlh_px_asuhan,0)) px_belum_monev, "
                    + "concat(format(((ifnull(b.jlh_px_asuhan,0)/a.jlh_px_ranap)*100),0),' %') persen_termonev, "
                    + "concat(format((100-(ifnull(b.jlh_px_asuhan,0)/a.jlh_px_ranap)*100),0),' %') persen_blm_termonev from "
                    + "( "
                    + "(SELECT b.nm_gedung, count(ki.no_rawat) jlh_px_ranap FROM kamar_inap ki "
                    + "inner join reg_periksa rp on rp.no_rawat=ki.no_rawat "
                    + "inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE MONTH(ki.tgl_masuk)=" + angkaBulan + " and YEAR(ki.tgl_masuk)=" + Ttahun.getText().trim() + " GROUP BY MONTH(ki.tgl_masuk), b.nm_gedung) as a "
                    + "inner join "
                    + "(SELECT b.nm_gedung, count(m.no_rawat) jlh_px_asuhan from monev_asuhan_gizi m "
                    + "inner join kamar_inap ki on ki.no_rawat=m.no_rawat "
                    + "inner join bangsal b on b.nm_bangsal=m.ruang_rawat "
                    + "WHERE MONTH(m.tgl_monev)=" + angkaBulan + " and YEAR(m.tgl_monev)=" + Ttahun.getText().trim() + " GROUP BY month(m.tgl_monev), b.nm_gedung) "
                    + "as b on a.nm_gedung = b.nm_gedung)) as z order by z.nm_gedung", param);
            this.setCursor(Cursor.getDefaultCursor());

            tampilPersentase();
            emptTeks();
            BtnKeluar1.requestFocus();
        }
    }//GEN-LAST:event_BtnCetakPersenActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void TabDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabDataMouseClicked
        if (TabData.getSelectedIndex() == 0) {
            tampil();
        } else if (TabData.getSelectedIndex() == 1) {
            cmbBulan.setSelectedItem(Sequel.bulanINDONESIA("select month(now())"));
            angkaBulan = Sequel.cariIsi("select month(now())");
            Ttahun.setText(Sequel.cariIsi("select year(now())"));
            tampilPersentase();
        } 
    }//GEN-LAST:event_TabDataMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgMonevAsuhanGizi dialog = new DlgMonevAsuhanGizi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCetakPersen;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.PanelBiasa FormData;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormPersen;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll2;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private javax.swing.JTabbedPane TabData;
    private widget.TextArea Tdiet;
    private widget.TextArea Tevaluasi;
    private widget.TextArea Tfisik;
    private widget.TextBox TnmPetugas;
    private widget.TextBox Tpasien;
    private widget.TextBox TrgRawat;
    private widget.TextBox Ttahun;
    private widget.TextBox TtglAsuhan;
    private widget.CekBox chkSaya;
    private widget.ComboBox cmbBulan;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel35;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.Table tbMonev;
    private widget.Table tbPersen;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    private widget.Tanggal tglMonev;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT m.*, date_format(m.tgl_monev,'%d-%m-%Y') tgl, p.no_rkm_medis, p.nm_pasien, pg.nama petugas "
                    + "from monev_asuhan_gizi m inner join reg_periksa rp on rp.no_rawat=m.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=m.nip_petugas WHERE "
                    + "m.tgl_monev BETWEEN ? and ? and m.no_rawat like ? or "
                    + "m.tgl_monev BETWEEN ? and ? and p.no_rkm_medis like ? or "
                    + "m.tgl_monev BETWEEN ? and ? and p.nm_pasien like ? or "
                    + "m.tgl_monev BETWEEN ? and ? and m.perkembangan_fisik_klinis like ? or "
                    + "m.tgl_monev BETWEEN ? and ? and m.perkembangan_diet like ? or "
                    + "m.tgl_monev BETWEEN ? and ? and m.evaluasi_tindak_lanjut like ? or "
                    + "m.tgl_monev BETWEEN ? and ? and pg.nama like ? order by m.tgl_monev desc");
            try {
                ps.setString(1, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl"),
                        rs.getString("ruang_rawat"),
                        rs.getString("perkembangan_fisik_klinis"),                        
                        rs.getString("perkembangan_diet"),
                        rs.getString("evaluasi_tindak_lanjut"),
                        rs.getString("petugas"),
                        rs.getString("tgl_monev"),
                        rs.getString("nip_petugas"),
                        rs.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("rekammedis.DlgMonevAsuhanGizi.tampil() : " + e);
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
        tglMonev.setDate(new Date());   
        Tfisik.setText("");
        Tdiet.setText("");
        Tevaluasi.setText("");
        nip = "-";
        TnmPetugas.setText("-");
        chkSaya.setSelected(false);
    }

    private void getData() {
        nip = "";
        if (tbMonev.getSelectedRow() != -1) {
            TNoRW.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 0).toString());
            TNoRM.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 1).toString());
            Tpasien.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 4).toString());
            Valid.SetTgl(tglMonev, tbMonev.getValueAt(tbMonev.getSelectedRow(), 9).toString());
            Tfisik.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 5).toString());
            Tdiet.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 6).toString());
            Tevaluasi.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 7).toString());
            nip = tbMonev.getValueAt(tbMonev.getSelectedRow(), 10).toString();
            TnmPetugas.setText(tbMonev.getValueAt(tbMonev.getSelectedRow(), 8).toString());
            TtglAsuhan.setText(Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_asuhan from asuhan_gizi_ranap where no_rawat='" + TNoRW.getText() + "'")));
        }
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangInap) {
        TNoRW.setText(norw);
        TNoRM.setText(norm);
        Tpasien.setText(nmpasien);
        TrgRawat.setText(ruangInap);
        Valid.SetTgl(tgl1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'"));
        TtglAsuhan.setText(Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_asuhan from asuhan_gizi_ranap where no_rawat='" + norw + "'")));
        tgl2.setDate(new Date());
        TCari.setText(norw);
        
        if (akses.getadmin() == true) {
            nip = "-";
            TnmPetugas.setText("-");            
        } else {
            nip = akses.getkode();
            TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip + "'"));
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getmonev_asuhan_gizi());
        BtnGanti.setEnabled(akses.getmonev_asuhan_gizi());
        BtnHapus.setEnabled(akses.getmonev_asuhan_gizi());
    }
    
    private void ganti() {
        if (Sequel.mengedittf("monev_asuhan_gizi", "waktu_simpan=?", "tgl_monev=?, ruang_rawat=?,"
                + "perkembangan_fisik_klinis=?, perkembangan_diet=?, evaluasi_tindak_lanjut=?, nip_petugas=?", 7, new String[]{
                    Valid.SetTgl(tglMonev.getSelectedItem() + ""), TrgRawat.getText(), Tfisik.getText(),
                    Tdiet.getText(), Tevaluasi.getText(), nip, tbMonev.getValueAt(tbMonev.getSelectedRow(), 11).toString()
                }) == true) {

            Valid.SetTgl(tgl1, Valid.SetTgl(tglMonev.getSelectedItem() + ""));
            tampil();
            emptTeks();
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from monev_asuhan_gizi where waktu_simpan=?", 1, new String[]{
                tbMonev.getValueAt(tbMonev.getSelectedRow(), 11).toString()
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
    
    private void tampilPersentase() {
        if (Ttahun.getText().equals("")) {
            Ttahun.setText(Sequel.cariIsi("select year(now())"));
        } else {
            Ttahun.setText(Ttahun.getText());
        }

        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("select * from (select a.nm_gedung, a.jlh_px_ranap, ifnull(b.jlh_px_asuhan,0) jlh_px_monev, "
                    + "(a.jlh_px_ranap-ifnull(b.jlh_px_asuhan,0)) px_belum_monev, "
                    + "concat(format(((ifnull(b.jlh_px_asuhan,0)/a.jlh_px_ranap)*100),0),' %') persen_termonev, "
                    + "concat(format((100-(ifnull(b.jlh_px_asuhan,0)/a.jlh_px_ranap)*100),0),' %') persen_blm_termonev from "
                    + "( "
                    + "(SELECT b.nm_gedung, count(ki.no_rawat) jlh_px_ranap FROM kamar_inap ki "
                    + "inner join reg_periksa rp on rp.no_rawat=ki.no_rawat "
                    + "inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE MONTH(ki.tgl_masuk)=" + angkaBulan + " and YEAR(ki.tgl_masuk)=" + Ttahun.getText().trim() + " GROUP BY MONTH(ki.tgl_masuk), b.nm_gedung) as a "
                    + "inner join "
                    + "(SELECT b.nm_gedung, count(m.no_rawat) jlh_px_asuhan from monev_asuhan_gizi m "
                    + "inner join kamar_inap ki on ki.no_rawat=m.no_rawat "
                    + "inner join bangsal b on b.nm_bangsal=m.ruang_rawat "
                    + "WHERE MONTH(m.tgl_monev)=" + angkaBulan + " and YEAR(m.tgl_monev)=" + Ttahun.getText().trim() + " GROUP BY month(m.tgl_monev), b.nm_gedung) "
                    + "as b on a.nm_gedung = b.nm_gedung)) as z order by z.nm_gedung");
            try {
                rs1 = ps1.executeQuery();
                x = 1;
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        x + ".",
                        rs1.getString(1),
                        rs1.getString(2),
                        rs1.getString(3),
                        rs1.getString(4),
                        rs1.getString(5),
                        rs1.getString(6)
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("tampilPersentase : " + e);
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
}
