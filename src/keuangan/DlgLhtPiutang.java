

package keuangan;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgPenanggungJawab;

/**
 *
 * @author perpustakaan
 */
public final class DlgLhtPiutang extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Jurnal jur = new Jurnal();
    private double total = 0, piutang = 0, sisapiutang1 = 0, Tsisapiutang = 0, sisapiutang = 0, cicilan = 0;
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private String koderekening = "", wktsimpan = "";
    private DlgPenanggungJawab penjab = new DlgPenanggungJawab(null, false);
    private int x = 0, i = 0;

    /** Creates new form DlgLhtBiaya
     * @param parent
     * @param modal */
    public DlgLhtPiutang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(885,674);

        Object[] rowRwJlDr={
            "No.Rawat/No.tagihan", "Tgl.Piutang", "Pasien", "Status", "Total Piutang",
            "Uang Muka", "Cicilan", "Sisa Piutang", "Jatuh Tempo", "Cara Bayar", "tglpiutang", "tgltempo"
        };
        
        tabMode=new DefaultTableModel(null,rowRwJlDr){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbPiutang.setModel(tabMode);
        tbPiutang.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPiutang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbPiutang.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(90);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(80);
            } else if (i == 9) {
                column.setPreferredWidth(130);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPiutang.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Tgl. Bayar", "No. RM", "Pasien", "Cicilan (Rp)", "Keterangan", "No. Tagihan",
            "tglbayar", "waktu_simpan", "Sisa Piutang (Rp)"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbBayar.setModel(tabMode1);
        tbBayar.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBayar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 9; i++) {
            TableColumn column = tbBayar.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(55);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(290);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            }
        }
        tbBayar.setDefaultRenderer(Object.class, new WarnaTable());

        TKd.setDocument(new batasInput((byte)20).getKata(TKd));
        Cicilan.setDocument(new batasInput((byte) 15).getOnlyAngka(Cicilan));
        Keterangan.setDocument(new batasInput((byte) 100).getKata(Keterangan));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampil();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampil();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampil();}
            });
            
            Cicilan.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    Sisa.setText(Valid.SetAngka(sisapiutang1));
                    if (!Cicilan.getText().equals("")) {
                        Sisa.setText(Valid.SetAngka(sisapiutang1 - Double.parseDouble(Cicilan.getText())));
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    Sisa.setText(Valid.SetAngka(sisapiutang1));
                    if (!Cicilan.getText().equals("")) {
                        Sisa.setText(Valid.SetAngka(sisapiutang1 - Double.parseDouble(Cicilan.getText())));
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    Sisa.setText(Valid.SetAngka(sisapiutang1));
                    if (!Cicilan.getText().equals("")) {
                        Sisa.setText(Valid.SetAngka(sisapiutang1 - Double.parseDouble(Cicilan.getText())));
                    }
                }
            });
        }  
        
        Valid.loadCombo(nama_bayar,"nama_bayar","akun_bayar");
        
        penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(penjab.getTable().getSelectedRow()!= -1){
                    kdpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(),1).toString());
                    nmpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(),2).toString());
                    tampil();
                }      
                kdpenjab.requestFocus();
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {penjab.emptTeks();}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });   
        
        penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    penjab.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        try {
            ps1 = koneksi.prepareStatement("select bp.tgl_bayar, bp.no_rkm_medis, p.nm_pasien, bp.besar_cicilan,"
                    + "bp.catatan, bp.no_rawat, date_format(bp.tgl_bayar,'%d/%m/%Y') tglbayar, bp.waktu_simpan, bp.sisa_piutang from bayar_piutang bp "
                    + "inner join pasien p on bp.no_rkm_medis=p.no_rkm_medis where "
                    + "bp.no_rawat like ? or "
                    + "bp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or "
                    + "bp.tgl_bayar like ? order by bp.tgl_bayar desc, bp.no_rkm_medis");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TKd = new widget.TextBox();
        Popup = new javax.swing.JPopupMenu();
        ppNotaPiutang = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        label19 = new widget.Label();
        kdpenjab = new widget.TextBox();
        nmpenjab = new widget.TextBox();
        BtnSeek2 = new widget.Button();
        BtnDetailPiutang = new widget.Button();
        TabPiutang = new javax.swing.JTabbedPane();
        internalFrame20 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPiutang = new widget.Table();
        panelGlass5 = new widget.panelisi();
        label17 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new javax.swing.JLabel();
        LCount = new javax.swing.JLabel();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        internalFrame21 = new widget.InternalFrame();
        panelisi5 = new widget.panelisi();
        label34 = new widget.Label();
        label32 = new widget.Label();
        NoRawat = new widget.TextBox();
        label36 = new widget.Label();
        Keterangan = new widget.TextBox();
        label35 = new widget.Label();
        Cicilan = new widget.TextBox();
        label16 = new widget.Label();
        TNoRM = new widget.TextBox();
        TNmPasien = new widget.TextBox();
        Tanggal = new widget.Tanggal();
        label38 = new widget.Label();
        Sisa = new widget.TextBox();
        jLabel11 = new widget.Label();
        nama_bayar = new widget.ComboBox();
        label20 = new widget.Label();
        TotalPiutang = new widget.Label();
        ChkPelunasan = new widget.CekBox();
        Scroll1 = new widget.ScrollPane();
        tbBayar = new widget.Table();
        jPanel1 = new javax.swing.JPanel();
        panelisi3 = new widget.panelisi();
        label9 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnAll1 = new widget.Button();
        label10 = new widget.Label();
        LCountBayar = new widget.Label();
        panelisi1 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint1 = new widget.Button();
        label12 = new widget.Label();
        LTotalBayar = new widget.Label();
        BtnKeluar1 = new widget.Button();
        jLabel23 = new widget.Label();
        tglNota = new widget.Tanggal();

        TKd.setForeground(new java.awt.Color(255, 255, 255));
        TKd.setName("TKd"); // NOI18N

        Popup.setName("Popup"); // NOI18N

        ppNotaPiutang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppNotaPiutang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        ppNotaPiutang.setText("Nota Bayar Piutang");
        ppNotaPiutang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppNotaPiutang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppNotaPiutang.setIconTextGap(8);
        ppNotaPiutang.setName("ppNotaPiutang"); // NOI18N
        ppNotaPiutang.setPreferredSize(new java.awt.Dimension(160, 25));
        ppNotaPiutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppNotaPiutangBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppNotaPiutang);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Tagihan Piutang Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tgl. Tagihan :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi4.add(label11);

        Tgl1.setEditable(false);
        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(100, 23));
        panelisi4.add(Tgl1);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(30, 23));
        panelisi4.add(label18);

        Tgl2.setEditable(false);
        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(100, 23));
        panelisi4.add(Tgl2);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("Cara Bayar :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(75, 23));
        panelisi4.add(label19);

        kdpenjab.setEditable(false);
        kdpenjab.setForeground(new java.awt.Color(0, 0, 0));
        kdpenjab.setName("kdpenjab"); // NOI18N
        kdpenjab.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi4.add(kdpenjab);

        nmpenjab.setEditable(false);
        nmpenjab.setForeground(new java.awt.Color(0, 0, 0));
        nmpenjab.setName("nmpenjab"); // NOI18N
        nmpenjab.setPreferredSize(new java.awt.Dimension(295, 23));
        panelisi4.add(nmpenjab);

        BtnSeek2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek2.setMnemonic('3');
        BtnSeek2.setToolTipText("Alt+3");
        BtnSeek2.setName("BtnSeek2"); // NOI18N
        BtnSeek2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek2ActionPerformed(evt);
            }
        });
        BtnSeek2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek2KeyPressed(evt);
            }
        });
        panelisi4.add(BtnSeek2);

        BtnDetailPiutang.setForeground(new java.awt.Color(0, 0, 0));
        BtnDetailPiutang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnDetailPiutang.setMnemonic('D');
        BtnDetailPiutang.setText("Detail Piutang");
        BtnDetailPiutang.setToolTipText("Alt+D");
        BtnDetailPiutang.setName("BtnDetailPiutang"); // NOI18N
        BtnDetailPiutang.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnDetailPiutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDetailPiutangActionPerformed(evt);
            }
        });
        BtnDetailPiutang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDetailPiutangKeyPressed(evt);
            }
        });
        panelisi4.add(BtnDetailPiutang);

        internalFrame1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        TabPiutang.setBackground(new java.awt.Color(254, 255, 254));
        TabPiutang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPiutang.setName("TabPiutang"); // NOI18N
        TabPiutang.setPreferredSize(new java.awt.Dimension(0, 2000));
        TabPiutang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPiutangMouseClicked(evt);
            }
        });

        internalFrame20.setBorder(null);
        internalFrame20.setToolTipText("Klik Kanan Pada Area Ini Untuk Melihat Hasil Pemeriksaan Penunjang Medis");
        internalFrame20.setName("internalFrame20"); // NOI18N
        internalFrame20.setPreferredSize(new java.awt.Dimension(900, 900));
        internalFrame20.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPiutang.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPiutang.setName("tbPiutang"); // NOI18N
        tbPiutang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPiutangMouseClicked(evt);
            }
        });
        tbPiutang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPiutangKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPiutang);

        internalFrame20.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Key Word :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass5.add(label17);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass5.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+2");
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
        panelGlass5.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
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
        panelGlass5.add(BtnAll);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 0, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Belum Dibayar :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass5.add(jLabel10);

        LCount.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        LCount.setForeground(new java.awt.Color(153, 0, 51));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(180, 23));
        panelGlass5.add(LCount);

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
        panelGlass5.add(BtnPrint);

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
        panelGlass5.add(BtnKeluar);

        internalFrame20.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        TabPiutang.addTab("Data Tagihan", internalFrame20);

        internalFrame21.setBorder(null);
        internalFrame21.setName("internalFrame21"); // NOI18N
        internalFrame21.setLayout(new java.awt.BorderLayout());

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 104));
        panelisi5.setLayout(null);

        label34.setForeground(new java.awt.Color(0, 0, 0));
        label34.setText("No. Rawat :");
        label34.setName("label34"); // NOI18N
        label34.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi5.add(label34);
        label34.setBounds(0, 10, 77, 23);

        label32.setForeground(new java.awt.Color(0, 0, 0));
        label32.setText("Tanggal :");
        label32.setName("label32"); // NOI18N
        label32.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi5.add(label32);
        label32.setBounds(0, 40, 77, 23);

        NoRawat.setEditable(false);
        NoRawat.setForeground(new java.awt.Color(0, 0, 0));
        NoRawat.setName("NoRawat"); // NOI18N
        NoRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRawatKeyPressed(evt);
            }
        });
        panelisi5.add(NoRawat);
        NoRawat.setBounds(80, 10, 170, 23);

        label36.setForeground(new java.awt.Color(0, 0, 0));
        label36.setText("Keterangan :");
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi5.add(label36);
        label36.setBounds(301, 40, 80, 23);

        Keterangan.setForeground(new java.awt.Color(0, 0, 0));
        Keterangan.setName("Keterangan"); // NOI18N
        Keterangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKeyPressed(evt);
            }
        });
        panelisi5.add(Keterangan);
        Keterangan.setBounds(384, 40, 326, 23);

        label35.setForeground(new java.awt.Color(0, 0, 0));
        label35.setText("Cicilan :");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi5.add(label35);
        label35.setBounds(301, 70, 80, 23);

        Cicilan.setForeground(new java.awt.Color(0, 0, 0));
        Cicilan.setName("Cicilan"); // NOI18N
        Cicilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CicilanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CicilanKeyReleased(evt);
            }
        });
        panelisi5.add(Cicilan);
        Cicilan.setBounds(384, 70, 120, 23);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("Pasien :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi5.add(label16);
        label16.setBounds(301, 10, 80, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi5.add(TNoRM);
        TNoRM.setBounds(384, 10, 90, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setName("TNmPasien"); // NOI18N
        TNmPasien.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi5.add(TNmPasien);
        TNmPasien.setBounds(476, 10, 233, 23);

        Tanggal.setEditable(false);
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        panelisi5.add(Tanggal);
        Tanggal.setBounds(80, 40, 110, 23);

        label38.setForeground(new java.awt.Color(0, 0, 0));
        label38.setText("Sisa Piutang :");
        label38.setName("label38"); // NOI18N
        label38.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi5.add(label38);
        label38.setBounds(510, 70, 77, 23);

        Sisa.setEditable(false);
        Sisa.setForeground(new java.awt.Color(0, 0, 0));
        Sisa.setName("Sisa"); // NOI18N
        panelisi5.add(Sisa);
        Sisa.setBounds(590, 70, 120, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Akun Bayar :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelisi5.add(jLabel11);
        jLabel11.setBounds(0, 70, 77, 23);

        nama_bayar.setForeground(new java.awt.Color(0, 0, 0));
        nama_bayar.setName("nama_bayar"); // NOI18N
        nama_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nama_bayarKeyPressed(evt);
            }
        });
        panelisi5.add(nama_bayar);
        nama_bayar.setBounds(80, 70, 170, 23);

        label20.setForeground(new java.awt.Color(0, 0, 0));
        label20.setText("Total Piutang Keseluruhan : Rp.");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi5.add(label20);
        label20.setBounds(720, 10, 160, 23);

        TotalPiutang.setForeground(new java.awt.Color(0, 0, 0));
        TotalPiutang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TotalPiutang.setText("t_piutang");
        TotalPiutang.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TotalPiutang.setName("TotalPiutang"); // NOI18N
        TotalPiutang.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi5.add(TotalPiutang);
        TotalPiutang.setBounds(882, 10, 180, 23);

        ChkPelunasan.setBorder(null);
        ChkPelunasan.setForeground(new java.awt.Color(0, 0, 0));
        ChkPelunasan.setText("Pelunasan Piutang");
        ChkPelunasan.setBorderPainted(true);
        ChkPelunasan.setBorderPaintedFlat(true);
        ChkPelunasan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPelunasan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPelunasan.setName("ChkPelunasan"); // NOI18N
        ChkPelunasan.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkPelunasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPelunasanActionPerformed(evt);
            }
        });
        panelisi5.add(ChkPelunasan);
        ChkPelunasan.setBounds(720, 70, 150, 23);

        internalFrame21.add(panelisi5, java.awt.BorderLayout.PAGE_START);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbBayar.setAutoCreateRowSorter(true);
        tbBayar.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbBayar.setComponentPopupMenu(Popup);
        tbBayar.setName("tbBayar"); // NOI18N
        tbBayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBayarMouseClicked(evt);
            }
        });
        tbBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBayarKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbBayar);

        internalFrame21.add(Scroll1, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 100));
        jPanel1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label9);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(350, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi3.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('2');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+2");
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
        panelisi3.add(BtnCari1);

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('1');
        BtnAll1.setText("Semua Data");
        BtnAll1.setToolTipText("Alt+1");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll1ActionPerformed(evt);
            }
        });
        BtnAll1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll1KeyPressed(evt);
            }
        });
        panelisi3.add(BtnAll1);

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label10);

        LCountBayar.setForeground(new java.awt.Color(0, 0, 0));
        LCountBayar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountBayar.setText("0");
        LCountBayar.setName("LCountBayar"); // NOI18N
        LCountBayar.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(LCountBayar);

        jPanel1.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
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
        panelisi1.add(BtnSimpan);

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

        BtnPrint1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setText("Cetak");
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        BtnPrint1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint1KeyPressed(evt);
            }
        });
        panelisi1.add(BtnPrint1);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("Total :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(105, 30));
        panelisi1.add(label12);

        LTotalBayar.setForeground(new java.awt.Color(0, 0, 0));
        LTotalBayar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LTotalBayar.setText("0");
        LTotalBayar.setName("LTotalBayar"); // NOI18N
        LTotalBayar.setPreferredSize(new java.awt.Dimension(195, 30));
        panelisi1.add(LTotalBayar);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        BtnKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar1KeyPressed(evt);
            }
        });
        panelisi1.add(BtnKeluar1);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tgl. Nota/Kwitansi : ");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(110, 23));
        panelisi1.add(jLabel23);

        tglNota.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-03-2024" }));
        tglNota.setDisplayFormat("dd-MM-yyyy");
        tglNota.setName("tglNota"); // NOI18N
        tglNota.setOpaque(false);
        panelisi1.add(tglNota);

        jPanel1.add(panelisi1, java.awt.BorderLayout.CENTER);

        internalFrame21.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        TabPiutang.addTab("Pembayaran Piutang", internalFrame21);

        internalFrame1.add(TabPiutang, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary");
            int row = tabMode.getRowCount();
            for (int i = 0; i < row; i++) {
                Sequel.menyimpan("temporary", "'0','"
                        + tabMode.getValueAt(i, 0).toString() + "','"
                        + tabMode.getValueAt(i, 1).toString() + "','"
                        + tabMode.getValueAt(i, 2).toString() + "','"
                        + tabMode.getValueAt(i, 3).toString() + "','"
                        + Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i, 4).toString())) + "','"
                        + Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i, 5).toString())) + "','"
                        + Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i, 6).toString())) + "','"
                        + Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i, 7).toString())) + "','"
                        + tabMode.getValueAt(i, 8).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Piutang Pasien");
            }
            Sequel.menyimpan("temporary", "'0','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
            Sequel.menyimpan("temporary", "'0','TOTAL PIUTANG','',':','','','','','" + LCount.getText() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Rekap Harian Tindakan Dokter");
            Sequel.AutoComitTrue();

            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRPiutangMasuk.jasper", "report", "::[ Rekap Piutang Masuk ]::",
                    "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14 from temporary order by no asc", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnAll, TKd);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        kdpenjab.setText("");
        nmpenjab.setText("");
        tampil();

}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbPiutangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPiutangMouseClicked
        if (tabMode.getRowCount() != 0) {
            setDataBayar();
        }
}//GEN-LAST:event_tbPiutangMouseClicked

    private void tbPiutangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPiutangKeyPressed
        if (tabMode.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                setDataBayar();
            }
        }
}//GEN-LAST:event_tbPiutangKeyPressed

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
        tampil();
    } else {
        Valid.pindah(evt, TKd, BtnAll);
    }
}//GEN-LAST:event_BtnCariKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        TabPiutang.setSelectedIndex(0);
        TabPiutang.setEnabledAt(1, akses.getbayar_piutang());
        tampil();
        emptTeks();
    }//GEN-LAST:event_formWindowOpened

    private void BtnSeek2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek2ActionPerformed
        penjab.isCek();
        penjab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penjab.setLocationRelativeTo(internalFrame1);
        penjab.setAlwaysOnTop(false);
        penjab.setVisible(true);
    }//GEN-LAST:event_BtnSeek2ActionPerformed

    private void BtnSeek2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek2KeyPressed
        //Valid.pindah(evt,DTPCari2,TCari);
    }//GEN-LAST:event_BtnSeek2KeyPressed

    private void TabPiutangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPiutangMouseClicked
        if (TabPiutang.getSelectedIndex() == 0) {
            tampil();
            emptTeks();
            TCari1.setText("");
        } else if (TabPiutang.getSelectedIndex() == 1) {
            tampilBayar();
        }
    }//GEN-LAST:event_TabPiutangMouseClicked

    private void NoRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRawatKeyPressed
        Valid.pindah(evt, TCari1, NoRawat);
    }//GEN-LAST:event_NoRawatKeyPressed

    private void KeteranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKeyPressed
        Valid.pindah(evt, Keterangan, Cicilan);
    }//GEN-LAST:event_KeteranganKeyPressed

    private void CicilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CicilanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sisa.setText(Valid.SetAngka(sisapiutang1));
            //            TotalPiutang.setText(Valid.SetAngka(Tsisapiutang));
            if (!Cicilan.getText().equals("")) {
                Sisa.setText(Valid.SetAngka2(sisapiutang1 - Double.parseDouble(Cicilan.getText())));
                //                TotalPiutang.setText(Valid.SetAngka(Tsisapiutang - Double.parseDouble(Cicilan.getText())));
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sisa.setText(Valid.SetAngka(sisapiutang1));
            //            TotalPiutang.setText(Valid.SetAngka(Tsisapiutang));
            if (!Cicilan.getText().equals("")) {
                Sisa.setText(Valid.SetAngka2(sisapiutang1 - Double.parseDouble(Cicilan.getText())));
                //                TotalPiutang.setText(Valid.SetAngka(Tsisapiutang - Double.parseDouble(Cicilan.getText())));
            }
            Keterangan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sisa.setText(Valid.SetAngka(sisapiutang1));
            //            TotalPiutang.setText(Valid.SetAngka(Tsisapiutang));
            if (!Cicilan.getText().equals("")) {
                Sisa.setText(Valid.SetAngka2(sisapiutang1 - Double.parseDouble(Cicilan.getText())));
                //                TotalPiutang.setText(Valid.SetAngka(Tsisapiutang - Double.parseDouble(Cicilan.getText())));
            }
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_CicilanKeyPressed

    private void CicilanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CicilanKeyReleased
        if (Cicilan.getText().equals("")) {
            Cicilan.setText("0");
        }

        Sisa.setText(Valid.SetAngka(sisapiutang1));
        //        TotalPiutang.setText(Valid.SetAngka(Tsisapiutang));
        if (!Cicilan.getText().equals("")) {
            Sisa.setText(Valid.SetAngka2(sisapiutang1 - Double.parseDouble(Cicilan.getText())));
            //            TotalPiutang.setText(Valid.SetAngka(Tsisapiutang - Double.parseDouble(Cicilan.getText())));
        }
    }//GEN-LAST:event_CicilanKeyReleased

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt,NoRawat,TNoRM);
    }//GEN-LAST:event_TanggalKeyPressed

    private void nama_bayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nama_bayarKeyPressed
        Valid.pindah(evt,Tanggal,NoRawat);
    }//GEN-LAST:event_nama_bayarKeyPressed

    private void ChkPelunasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPelunasanActionPerformed
        if (ChkPelunasan.isSelected() == true) {
            Keterangan.setText("");
            Cicilan.setText("0");
            cekSisaPiutang();
            
            Keterangan.setText("PELUNASAN PIUTANG SISA TAGIHAN");
            Cicilan.setText(Sisa.getText());
            Sisa.setText("0");
        } else {
            Keterangan.setText("");
            Cicilan.setText("0");
            cekSisaPiutang();
        }
    }//GEN-LAST:event_ChkPelunasanActionPerformed

    private void tbBayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBayarMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbBayarMouseClicked

    private void tbBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBayarKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbBayarKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar1.requestFocus();
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilBayar();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari1, BtnAll1);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari1.setText("");
        tampilBayar();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAll1ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari1, TCari1);
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (NoRawat.getText().trim().equals("")) {
            Valid.textKosong(NoRawat, "No.Tagihan/No.Rawat");
        } else if (Cicilan.getText().trim().equals("") || Cicilan.getText().trim().equals("0")) {
            Valid.textKosong(Cicilan, "Besar Cicilan");
        } else if (TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else {
            if (Sequel.cariIsi("SELECT status FROM piutang_pasien where no_rawat='" + NoRawat.getText() + "'").equals("Lunas")) {
                JOptionPane.showMessageDialog(null, "Tagihan piutang pasien sudah dilunasi...!");
            } else {
                try {
                    koderekening = Sequel.cariIsi("select kd_rek from akun_bayar where nama_bayar=?", nama_bayar.getSelectedItem().toString());
                    Sequel.menyimpan("bayar_piutang", "?,?,?,?,?,?,?,?", 8, new String[]{
                        Valid.SetTgl(Tanggal.getSelectedItem() + ""), TNoRM.getText(), Cicilan.getText(),
                        Keterangan.getText(), NoRawat.getText(), koderekening, Sequel.cariIsi("select now()"), Sisa.getText()
                    });

                    if (Double.parseDouble(Sisa.getText()) <= 1) {
                        Sequel.mengedit("piutang_pasien", "no_rawat='" + NoRawat.getText() + "'", "status='Lunas'");
                    }
                    Sequel.queryu("delete from tampjurnal");
                    Sequel.menyimpan("tampjurnal", "'" + Sequel.cariIsi("select Bayar_Piutang_Pasien from set_akun") + "','BAYAR PIUTANG','0','" + Cicilan.getText() + "'", "Rekening");
                    Sequel.menyimpan("tampjurnal", "'" + koderekening + "','" + nama_bayar.getSelectedItem() + "','" + Cicilan.getText() + "','0'", "Rekening");
                    jur.simpanJurnal(NoRawat.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), "U", "BAYAR PIUTANG");

                } catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada No.Bayar yang sama dimasukkan sebelumnya...!");
                }

                BtnCari1ActionPerformed(evt);
                emptTeks();
            }
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Sisa, BtnKeluar1);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (NoRawat.getText().trim().equals("")) {
            Valid.textKosong(NoRawat, "No.Tagihan/No.Rawat");
        } else if (Cicilan.getText().trim().equals("") || Cicilan.getText().trim().equals("0")) {
            Valid.textKosong(Cicilan, "Besar Cicilan");
        } else if (TNmPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data ini mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                try {
                    koderekening = Sequel.cariIsi("select kd_rek from bayar_piutang where "
                        + "tgl_bayar='" + Valid.SetTgl(Tanggal.getSelectedItem() + "") + "' and no_rkm_medis='" + TNoRM.getText() + "' and waktu_simpan='" + wktsimpan + "'");

                    Sequel.queryu("delete from bayar_piutang where tgl_bayar='" + Valid.SetTgl(Tanggal.getSelectedItem() + "") + "' "
                        + "and no_rkm_medis='" + TNoRM.getText() + "' "
                        + "and no_rawat='" + NoRawat.getText() + "' "
                        + "and waktu_simpan='" + wktsimpan + "'");
                    Sequel.mengedit("piutang_pasien", "no_rawat='" + NoRawat.getText() + "'", "status='Belum Lunas'");

                    Sequel.queryu("delete from tampjurnal");
                    Sequel.menyimpan("tampjurnal", "'" + Sequel.cariIsi("select Bayar_Piutang_Pasien from set_akun") + "','BAYAR PIUTANG','" + Cicilan.getText() + "','0'", "Rekening");
                    Sequel.menyimpan("tampjurnal", "'" + koderekening + "','" + nama_bayar.getSelectedItem() + "','0','" + Cicilan.getText() + "'", "Rekening");
                    jur.simpanJurnal(NoRawat.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), "U", "PEMBATALAN BAYAR PIUTANG");

                    BtnCari1ActionPerformed(evt);
                    emptTeks();
                } catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada No.Bayar yang sama dimasukkan sebelumnya...!");
                }
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnKeluar1, BtnAll1);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BtnCari1ActionPerformed(evt);
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnKeluar1.requestFocus();
        } else if (tabMode1.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBayar.jasper", "report", "::[ Bayar Piutang ]::",
                "select bayar_piutang.tgl_bayar, bayar_piutang.no_rkm_medis,pasien.nm_pasien, bayar_piutang.besar_cicilan,"
                + "bayar_piutang.catatan, bayar_piutang.no_rawat from bayar_piutang inner join pasien on bayar_piutang.no_rkm_medis=pasien.no_rkm_medis where "
                + "bayar_piutang.no_rawat like '%" + TCari1.getText() + "%' or "
                + "bayar_piutang.no_rkm_medis like '%" + TCari1.getText() + "%' or "
                + "pasien.nm_pasien like '%" + TCari1.getText() + "%' or "
                + "bayar_piutang.tgl_bayar like '%" + TCari1.getText() + "%' order by bayar_piutang.tgl_bayar,bayar_piutang.no_rkm_medis,bayar_piutang.no_rawat ", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void BtnPrint1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar1);
        }
    }//GEN-LAST:event_BtnPrint1KeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnAll1, TCari1);
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void ppNotaPiutangBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppNotaPiutangBtnPrintActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnKeluar1.requestFocus();
        } else {
            if (tbBayar.getSelectedRow() > -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("cara_byr", Sequel.cariIsi("select p.png_jawab from reg_periksa r inner join penjab p on p.kd_pj=r.kd_pj where r.no_rawat='" + NoRawat.getText() + "'"));
                param.put("tot_bayar", "Rp. " + tabMode1.getValueAt(tbBayar.getSelectedRow(), 3).toString());
                param.put("tglNota", "Martapura, " + tglNota.getSelectedItem().toString());

                param.put("norawat", tabMode1.getValueAt(tbBayar.getSelectedRow(), 5).toString());
                param.put("tglbayar", tabMode1.getValueAt(tbBayar.getSelectedRow(), 6).toString());
                param.put("norm", tabMode1.getValueAt(tbBayar.getSelectedRow(), 1).toString());
                param.put("nmpasien", tabMode1.getValueAt(tbBayar.getSelectedRow(), 2).toString());
                param.put("keterangan", tabMode1.getValueAt(tbBayar.getSelectedRow(), 4).toString());
                param.put("nilaicicilan", tabMode1.getValueAt(tbBayar.getSelectedRow(), 3).toString());
                param.put("sisapiutang", tabMode1.getValueAt(tbBayar.getSelectedRow(), 8).toString());

                if (akses.getadmin() == true) {
                    param.put("petugas_ksr", "( ................... )");
                } else {
                    param.put("petugas_ksr", "( " + Sequel.cariIsi("select nama from petugas where nip='" + akses.getkode() + "'") + " )");
                }

                Valid.MyReport("rptNotaBayarPiutang.jasper", "report", "::[ Nota Pembayaran Piutang Pasien ]::",
                    "SELECT now() tgl", param);

                tampilBayar();
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, pilih dulu salah satu datanya yang mau dicetak notanya...!!!!");
                tbBayar.requestFocus();
            }
        }
    }//GEN-LAST:event_ppNotaPiutangBtnPrintActionPerformed

    private void BtnDetailPiutangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDetailPiutangActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data tagihan piutang masih kosong...!!!!");
        } else {
            if (tbPiutang.getSelectedRow() > -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgBilingPiutang rincianpiutang = new DlgBilingPiutang(null, false);
                rincianpiutang.isRawat(
                        tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 0).toString(),
                        Double.parseDouble(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 5).toString()),
                        Double.parseDouble(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 7).toString()),
                        Double.parseDouble(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 4).toString()),
                        Double.parseDouble(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 6).toString())
                );
                rincianpiutang.setSize(this.getWidth() - 40, this.getHeight() - 40);
                rincianpiutang.setLocationRelativeTo(this);
                rincianpiutang.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih & klik datanya terlebih dulu..!!");
                tbPiutang.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnDetailPiutangActionPerformed

    private void BtnDetailPiutangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDetailPiutangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDetailPiutangKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgLhtPiutang dialog = new DlgLhtPiutang(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll1;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnDetailPiutang;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnSeek2;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkPelunasan;
    private widget.TextBox Cicilan;
    private widget.TextBox Keterangan;
    private javax.swing.JLabel LCount;
    private widget.Label LCountBayar;
    private widget.Label LTotalBayar;
    private widget.TextBox NoRawat;
    private javax.swing.JPopupMenu Popup;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.TextBox Sisa;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TKd;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private javax.swing.JTabbedPane TabPiutang;
    private widget.Tanggal Tanggal;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.Label TotalPiutang;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame20;
    private widget.InternalFrame internalFrame21;
    private javax.swing.JLabel jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel23;
    private javax.swing.JPanel jPanel1;
    private widget.TextBox kdpenjab;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label32;
    private widget.Label label34;
    private widget.Label label35;
    private widget.Label label36;
    private widget.Label label38;
    private widget.Label label9;
    private widget.ComboBox nama_bayar;
    private widget.TextBox nmpenjab;
    private widget.panelisi panelGlass5;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.panelisi panelisi5;
    private javax.swing.JMenuItem ppNotaPiutang;
    private widget.Table tbBayar;
    private widget.Table tbPiutang;
    private widget.Tanggal tglNota;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            sisapiutang = 0;
            ps = koneksi.prepareStatement("select pp.no_rawat, date_format(pp.tgl_piutang,'%d/%m/%Y') tglpiutang, concat(pp.no_rkm_medis,' ',p.nm_pasien), "
                    + "pp.status, pp.totalpiutang, pp.uangmuka, pp.sisapiutang, date_format(pp.tgltempo,'%d/%m/%Y') tgljatuhtempo, pj.png_jawab, "
                    + "pp.tgl_piutang, pp.tgltempo FROM piutang_pasien pp INNER JOIN pasien p on pp.no_rkm_medis = p.no_rkm_medis "
                    + "INNER JOIN reg_periksa rp on pp.no_rawat = rp.no_rawat INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj where "
                    + "pj.png_jawab like ? and pp.no_rawat like ? and pp.tgl_piutang between ? and ? or "
                    + "pj.png_jawab like ? and pp.no_rkm_medis like ? and pp.tgl_piutang between ? and ? or "
                    + "pj.png_jawab like ? and p.nm_pasien like ? and pp.tgl_piutang between ? and ? or "
                    + "pj.png_jawab like ? and pp.status like ? and pp.tgl_piutang between ? and ? order by pp.tgl_piutang");
            try {
                ps.setString(1, "%" + nmpenjab.getText() + "%");
                ps.setString(2, "%" + TCari.getText() + "%");
                ps.setString(3, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(4, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(5, "%" + nmpenjab.getText() + "%");
                ps.setString(6, "%" + TCari.getText() + "%");
                ps.setString(7, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(9, "%" + nmpenjab.getText() + "%");
                ps.setString(10, "%" + TCari.getText() + "%");
                ps.setString(11, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(12, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(13, "%" + nmpenjab.getText() + "%");
                ps.setString(14, "%" + TCari.getText() + "%");
                ps.setString(15, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(16, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                rs = ps.executeQuery();
                while (rs.next()) {
                    cicilan = Sequel.cariIsiAngka("SELECT ifnull(SUM(bayar_piutang.besar_cicilan),0) FROM bayar_piutang where bayar_piutang.no_rawat=?", rs.getString(1));
                    tabMode.addRow(new Object[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        cicilan,
                        (rs.getDouble(7) - cicilan),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11)
                    });
                    sisapiutang = sisapiutang + rs.getDouble(7) - cicilan;
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            
            LCount.setText(Valid.SetAngka(sisapiutang));
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void setNoRm(String norm, Date tgl){
        TCari.setText(norm);
        Tgl1.setDate(tgl);
        kdpenjab.setText("U01");
        nmpenjab.setText(Sequel.cariIsi("select png_jawab from penjab where kd_pj='" + kdpenjab.getText() + "'"));
        TabPiutang.setEnabledAt(1, akses.getbayar_piutang());
    }
    
    private void cekSisaPiutang() {
        sisapiutang1 = 0;
        sisapiutang1 = Sequel.cariIsiAngka("SELECT ifnull(SUM(pp.sisapiutang),0) FROM piutang_pasien pp where pp.no_rawat='" + NoRawat.getText() + "' and pp.no_rkm_medis=?", TNoRM.getText())
                + Sequel.cariIsiAngka("SELECT ifnull(SUM(p.sisapiutang),0) FROM piutang p where p.no_rkm_medis=?", TNoRM.getText())
                - Sequel.cariIsiAngka("SELECT ifnull(SUM(bp.besar_cicilan),0) FROM bayar_piutang bp where bp.no_rawat='" + NoRawat.getText() + "' and bp.no_rkm_medis=?", TNoRM.getText());
        Sisa.setText(Valid.SetAngka(Valid.roundUp(sisapiutang1, 100)));
        Sisa.setText(Valid.SetAngka(Valid.roundUp(sisapiutang1 - Double.parseDouble(Cicilan.getText()), 100)));
    }
    
    private void getData() {
        wktsimpan = "";
        int row = tbBayar.getSelectedRow();
        if (row != -1) {
            TNoRM.setText(tbBayar.getValueAt(row, 1).toString());
            TNmPasien.setText(tbBayar.getValueAt(row, 2).toString());
            Cicilan.setText(tbBayar.getValueAt(row, 3).toString());
            Keterangan.setText(tbBayar.getValueAt(row, 4).toString());
            NoRawat.setText(tbBayar.getValueAt(row, 5).toString());
            Valid.SetTgl(Tanggal, tbBayar.getValueAt(row, 0).toString());
            wktsimpan = tbBayar.getValueAt(row, 7).toString();
            Sisa.setText(tbBayar.getValueAt(row, 8).toString());
            ChkPelunasan.setSelected(false);
        }
    }
    
    private void emptTeks() {
        Cicilan.setText("0");
        NoRawat.setText("");
        TNoRM.setText("");
        TNmPasien.setText("");
        Sisa.setText("0");
        TotalPiutang.setText("0");
        Keterangan.setText("");
        ChkPelunasan.setSelected(false);
        Tanggal.setDate(new Date());
        tglNota.setDate(new Date());
        Keterangan.requestFocus();
    }
    
    private void tampilBayar() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1.setString(1, "%" + TCari1.getText() + "%");
            ps1.setString(2, "%" + TCari1.getText() + "%");
            ps1.setString(3, "%" + TCari1.getText() + "%");
            ps1.setString(4, "%" + TCari1.getText() + "%");
            rs1 = ps1.executeQuery();
            total = 0;
            while (rs1.next()) {
                total = total + rs1.getDouble(4);
                tabMode1.addRow(new Object[]{
                    rs1.getString(1),
                    rs1.getString(2),
                    rs1.getString(3),
                    Valid.SetAngka(rs1.getDouble(4)),
                    rs1.getString(5),
                    rs1.getString(6),
                    rs1.getString("tglbayar"),
                    rs1.getString("waktu_simpan"),
                    Valid.SetAngka(rs1.getDouble("sisa_piutang"))
                });
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCountBayar.setText("" + tabMode1.getRowCount());
        LTotalBayar.setText(Valid.SetAngka(total));
    }
    
    private void setDataBayar() {
        NoRawat.setText(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 0).toString());
        TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from piutang_pasien where no_rawat='" + NoRawat.getText() + "'"));
        TNmPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        TCari1.setText(NoRawat.getText());
        cekSisaPiutang();

//        Tsisapiutang = Sequel.cariIsiAngka("SELECT ifnull(SUM(pp.sisapiutang),0) FROM piutang_pasien pp where pp.no_rawat=?", NoRawat.getText())
////                + Sequel.cariIsiAngka("SELECT ifnull(SUM(p.sisapiutang),0) FROM piutang p where p.no_rkm_medis=?", TNoRM.getText())
//                - Sequel.cariIsiAngka("SELECT ifnull(SUM(bp.besar_cicilan),0) FROM bayar_piutang bp where bp.no_rawat=?", NoRawat.getText());
        TotalPiutang.setText(Valid.SetAngka(Valid.roundUp(Double.parseDouble(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 4).toString()), 100)));
        if (!Cicilan.getText().equals("")) {
            Sisa.setText(Valid.SetAngka(Valid.roundUp(sisapiutang1 - Double.parseDouble(Cicilan.getText()), 100)));
            TotalPiutang.setText(Valid.SetAngka(Valid.roundUp(Double.parseDouble(tbPiutang.getValueAt(tbPiutang.getSelectedRow(), 4).toString()) - Double.parseDouble(Cicilan.getText()), 100)));
        }
    }
}
