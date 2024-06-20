/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPenyakit.java
 *
 * Created on May 23, 2010, 12:57:16 AM
 */

package keuangan;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
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
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public final class DlgBayarPiutang extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Jurnal jur = new Jurnal();
    private Connection koneksi = koneksiDB.condb();
    private double total = 0, piutang = 0, sisapiutang = 0, Tsisapiutang = 0;
    private PreparedStatement ps;
    private ResultSet rs;
    private String koderekening = "", wktsimpan = "";
    private int x = 0;
    
    /** Creates new form DlgPenyakit
     * @param parent
     * @param modal */
    public DlgBayarPiutang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,2);
        setSize(628,674);

        Object[] row={"Tgl. Bayar",
                      "No. RM",
                      "Pasien",
                      "Cicilan (Rp)",
                      "Keterangan",
                      "No.Tagihan",
                      "tglbayar",
                      "waktu_simpan",
                      "Sisa Piutang (Rp)",
                      "Penjamin Piutang",
                      "Keterangan Penjamin"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbPiutang.setModel(tabMode);
        tbPiutang.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPiutang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 11; i++) {
            TableColumn column = tbPiutang.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(120);
            } else if (i == 4) {
                column.setPreferredWidth(250);
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
            } else if (i == 9) {
                column.setPreferredWidth(170);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            }
        }
        tbPiutang.setDefaultRenderer(Object.class, new WarnaTable());
        
        NoRawat.setDocument(new batasInput((byte) 17).getKata(NoRawat));
        Cicilan.setDocument(new batasInput((byte) 15).getOnlyAngka(Cicilan));
        Keterangan.setDocument(new batasInput((byte) 100).getKata(Keterangan));
        TNoRM.setDocument(new batasInput((byte) 6).getOnlyAngka(TNoRM));
        
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
            
            Cicilan.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    Sisa.setText(Valid.SetAngka(sisapiutang));
                    if(!Cicilan.getText().equals("")){                           
                         Sisa.setText(Valid.SetAngka(sisapiutang-Double.parseDouble(Cicilan.getText())));                           
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    Sisa.setText(Valid.SetAngka(sisapiutang));
                    if(!Cicilan.getText().equals("")){                           
                         Sisa.setText(Valid.SetAngka(sisapiutang-Double.parseDouble(Cicilan.getText())));                           
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    Sisa.setText(Valid.SetAngka(sisapiutang));
                    if(!Cicilan.getText().equals("")){                           
                         Sisa.setText(Valid.SetAngka(sisapiutang-Double.parseDouble(Cicilan.getText())));                           
                    }
                }
            });
        }  
        
        Valid.loadCombo(nama_bayar,"nama_bayar","akun_bayar");
        
        try {
            ps = koneksi.prepareStatement("select bp.tgl_bayar, bp.no_rkm_medis, p.nm_pasien, bp.besar_cicilan,"
                    + "bp.catatan, bp.no_rawat, date_format(bp.tgl_bayar,'%d/%m/%Y') tglbayar, bp.waktu_simpan, bp.sisa_piutang, "
                    + "pp.penjamin, pp.ket_penjamin from bayar_piutang bp inner join pasien p on bp.no_rkm_medis=p.no_rkm_medis "
                    + "inner join piutang_pasien pp on pp.no_rawat=bp.no_rawat where "
                    + "bp.no_rawat like ? or "
                    + "bp.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or "
                    + "bp.tgl_bayar like ? order by bp.tgl_bayar,bp.no_rkm_medis");
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

        Popup = new javax.swing.JPopupMenu();
        ppNotaPiutang = new javax.swing.JMenuItem();
        Kd2 = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPiutang = new widget.Table();
        panelisi4 = new widget.panelisi();
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
        jLabel10 = new widget.Label();
        nama_bayar = new widget.ComboBox();
        label17 = new widget.Label();
        TotalPiutang = new widget.Label();
        ChkPelunasan = new widget.CekBox();
        jPanel1 = new javax.swing.JPanel();
        panelisi3 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        panelisi1 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        label12 = new widget.Label();
        LTotal = new widget.Label();
        BtnKeluar = new widget.Button();
        jLabel23 = new widget.Label();
        tglNota = new widget.Tanggal();

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

        Kd2.setHighlighter(null);
        Kd2.setName("Kd2"); // NOI18N
        Kd2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kd2KeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Bayar Piutang ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(Popup);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPiutang.setAutoCreateRowSorter(true);
        tbPiutang.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPiutang.setComponentPopupMenu(Popup);
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

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 104));
        panelisi4.setLayout(null);

        label34.setForeground(new java.awt.Color(0, 0, 0));
        label34.setText("No. Rawat :");
        label34.setName("label34"); // NOI18N
        label34.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi4.add(label34);
        label34.setBounds(0, 10, 77, 23);

        label32.setForeground(new java.awt.Color(0, 0, 0));
        label32.setText("Tanggal :");
        label32.setName("label32"); // NOI18N
        label32.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi4.add(label32);
        label32.setBounds(0, 40, 77, 23);

        NoRawat.setEditable(false);
        NoRawat.setForeground(new java.awt.Color(0, 0, 0));
        NoRawat.setName("NoRawat"); // NOI18N
        NoRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRawatKeyPressed(evt);
            }
        });
        panelisi4.add(NoRawat);
        NoRawat.setBounds(80, 10, 170, 23);

        label36.setForeground(new java.awt.Color(0, 0, 0));
        label36.setText("Keterangan :");
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi4.add(label36);
        label36.setBounds(301, 40, 80, 23);

        Keterangan.setForeground(new java.awt.Color(0, 0, 0));
        Keterangan.setName("Keterangan"); // NOI18N
        Keterangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKeyPressed(evt);
            }
        });
        panelisi4.add(Keterangan);
        Keterangan.setBounds(384, 40, 326, 23);

        label35.setForeground(new java.awt.Color(0, 0, 0));
        label35.setText("Cicilan :");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi4.add(label35);
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
        panelisi4.add(Cicilan);
        Cicilan.setBounds(384, 70, 120, 23);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("Pasien :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi4.add(label16);
        label16.setBounds(301, 10, 80, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi4.add(TNoRM);
        TNoRM.setBounds(384, 10, 90, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setName("TNmPasien"); // NOI18N
        TNmPasien.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi4.add(TNmPasien);
        TNmPasien.setBounds(476, 10, 233, 23);

        Tanggal.setEditable(false);
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        panelisi4.add(Tanggal);
        Tanggal.setBounds(80, 40, 110, 23);

        label38.setForeground(new java.awt.Color(0, 0, 0));
        label38.setText("Sisa Piutang :");
        label38.setName("label38"); // NOI18N
        label38.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi4.add(label38);
        label38.setBounds(510, 70, 77, 23);

        Sisa.setEditable(false);
        Sisa.setForeground(new java.awt.Color(0, 0, 0));
        Sisa.setName("Sisa"); // NOI18N
        panelisi4.add(Sisa);
        Sisa.setBounds(590, 70, 120, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Akun Bayar :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelisi4.add(jLabel10);
        jLabel10.setBounds(0, 70, 77, 23);

        nama_bayar.setForeground(new java.awt.Color(0, 0, 0));
        nama_bayar.setName("nama_bayar"); // NOI18N
        nama_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nama_bayarKeyPressed(evt);
            }
        });
        panelisi4.add(nama_bayar);
        nama_bayar.setBounds(80, 70, 170, 23);

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Total Piutang Keseluruhan : Rp.");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi4.add(label17);
        label17.setBounds(720, 10, 160, 23);

        TotalPiutang.setForeground(new java.awt.Color(0, 0, 0));
        TotalPiutang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TotalPiutang.setText("t_piutang");
        TotalPiutang.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TotalPiutang.setName("TotalPiutang"); // NOI18N
        TotalPiutang.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi4.add(TotalPiutang);
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
        panelisi4.add(ChkPelunasan);
        ChkPelunasan.setBounds(720, 70, 150, 23);

        internalFrame1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

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

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(350, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi3.add(TCari);

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
        panelisi3.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('1');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+1");
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
        panelisi3.add(BtnAll);

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(LCount);

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
        panelisi1.add(BtnPrint);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("Total :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(105, 30));
        panelisi1.add(label12);

        LTotal.setForeground(new java.awt.Color(0, 0, 0));
        LTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LTotal.setText("0");
        LTotal.setName("LTotal"); // NOI18N
        LTotal.setPreferredSize(new java.awt.Dimension(195, 30));
        panelisi1.add(LTotal);

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

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tgl. Nota/Kwitansi : ");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(110, 23));
        panelisi1.add(jLabel23);

        tglNota.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-03-2024" }));
        tglNota.setDisplayFormat("dd-MM-yyyy");
        tglNota.setName("tglNota"); // NOI18N
        tglNota.setOpaque(false);
        panelisi1.add(tglNota);

        jPanel1.add(panelisi1, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NoRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRawatKeyPressed
        Valid.pindah(evt,TCari,NoRawat);
}//GEN-LAST:event_NoRawatKeyPressed

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

                BtnCariActionPerformed(evt);
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Sisa, BtnKeluar);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (NoRawat.getText().trim().equals("")) {
            Valid.textKosong(NoRawat, "No.Tagihan/No.Rawat");
        } else if (Cicilan.getText().trim().equals("") || Cicilan.getText().trim().equals("0")) {
            Valid.textKosong(Cicilan, "Besar Cicilan");
        } else if (TNmPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Member");
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

                    BtnCariActionPerformed(evt);
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
            Valid.pindah(evt, BtnKeluar, BtnAll);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnAll, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BtnCariActionPerformed(evt);
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnKeluar.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
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
                    + "bayar_piutang.no_rawat like '%" + TCari.getText() + "%' or "
                    + "bayar_piutang.no_rkm_medis like '%" + TCari.getText() + "%' or "
                    + "pasien.nm_pasien like '%" + TCari.getText() + "%' or "
                    + "bayar_piutang.tgl_bayar like '%" + TCari.getText() + "%' order by bayar_piutang.tgl_bayar,bayar_piutang.no_rkm_medis,bayar_piutang.no_rawat ", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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

    private void tbPiutangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPiutangMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPiutangMouseClicked

    private void tbPiutangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPiutangKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPiutangKeyPressed

private void KeteranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKeyPressed
    Valid.pindah(evt, Keterangan, Cicilan);
}//GEN-LAST:event_KeteranganKeyPressed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt,NoRawat,TNoRM);
    }//GEN-LAST:event_TanggalKeyPressed

    private void CicilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CicilanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sisa.setText(Valid.SetAngka(sisapiutang));
//            TotalPiutang.setText(Valid.SetAngka(Tsisapiutang));
            if (!Cicilan.getText().equals("")) {
                Sisa.setText(Valid.SetAngka2(sisapiutang - Double.parseDouble(Cicilan.getText())));
//                TotalPiutang.setText(Valid.SetAngka(Tsisapiutang - Double.parseDouble(Cicilan.getText())));
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sisa.setText(Valid.SetAngka(sisapiutang));
//            TotalPiutang.setText(Valid.SetAngka(Tsisapiutang));
            if (!Cicilan.getText().equals("")) {
                Sisa.setText(Valid.SetAngka2(sisapiutang - Double.parseDouble(Cicilan.getText())));
//                TotalPiutang.setText(Valid.SetAngka(Tsisapiutang - Double.parseDouble(Cicilan.getText())));
            }
            Keterangan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sisa.setText(Valid.SetAngka(sisapiutang));
//            TotalPiutang.setText(Valid.SetAngka(Tsisapiutang));
            if (!Cicilan.getText().equals("")) {
                Sisa.setText(Valid.SetAngka2(sisapiutang - Double.parseDouble(Cicilan.getText())));
//                TotalPiutang.setText(Valid.SetAngka(Tsisapiutang - Double.parseDouble(Cicilan.getText())));
            }
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_CicilanKeyPressed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, TCari);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

private void ppNotaPiutangBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppNotaPiutangBtnPrintActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
        BtnKeluar.requestFocus();
    } else {
        if (tbPiutang.getSelectedRow() > -1) {
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
            param.put("tot_bayar", "Rp. " + tabMode.getValueAt(tbPiutang.getSelectedRow(), 3).toString());
            param.put("tglNota", "Martapura, " + tglNota.getSelectedItem().toString());

            param.put("norawat", tabMode.getValueAt(tbPiutang.getSelectedRow(), 5).toString());
            param.put("tglbayar", tabMode.getValueAt(tbPiutang.getSelectedRow(), 6).toString());
            param.put("norm", tabMode.getValueAt(tbPiutang.getSelectedRow(), 1).toString());
            param.put("nmpasien", tabMode.getValueAt(tbPiutang.getSelectedRow(), 2).toString());
            param.put("keterangan", tabMode.getValueAt(tbPiutang.getSelectedRow(), 4).toString());
            param.put("nilaicicilan", tabMode.getValueAt(tbPiutang.getSelectedRow(), 3).toString());
            param.put("sisapiutang", tabMode.getValueAt(tbPiutang.getSelectedRow(), 8).toString());

            if (akses.getadmin() == true) {
                param.put("petugas_ksr", "( ................... )");
            } else {
                param.put("petugas_ksr", "( " + Sequel.cariIsi("select nama from petugas where nip='" + akses.getkode() + "'") + " )");
            }

            Valid.MyReport("rptNotaBayarPiutang.jasper", "report", "::[ Nota Pembayaran Piutang Pasien ]::",
                    "SELECT now() tgl", param);
            
            tampil();
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, pilih dulu salah satu datanya yang mau dicetak notanya...!!!!");
            tbPiutang.requestFocus();
        }
    }
}//GEN-LAST:event_ppNotaPiutangBtnPrintActionPerformed

    private void nama_bayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nama_bayarKeyPressed
        Valid.pindah(evt,Tanggal,NoRawat);
    }//GEN-LAST:event_nama_bayarKeyPressed

    private void CicilanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CicilanKeyReleased
        if (Cicilan.getText().equals("")) {
            Cicilan.setText("0");
        }

        Sisa.setText(Valid.SetAngka(sisapiutang));
//        TotalPiutang.setText(Valid.SetAngka(Tsisapiutang));
        if (!Cicilan.getText().equals("")) {
            Sisa.setText(Valid.SetAngka2(sisapiutang - Double.parseDouble(Cicilan.getText())));
//            TotalPiutang.setText(Valid.SetAngka(Tsisapiutang - Double.parseDouble(Cicilan.getText())));
        }
    }//GEN-LAST:event_CicilanKeyReleased

    private void ChkPelunasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPelunasanActionPerformed
        if (ChkPelunasan.isSelected() == true) {
            Keterangan.setText("PELUNASAN PIUTANG SISA TAGIHAN");
            Cicilan.setText(Sisa.getText());
            Sisa.setText("0");
        } else {
            Keterangan.setText("");
            Cicilan.setText("0");
            cekSisaPiutang();
        }
    }//GEN-LAST:event_ChkPelunasanActionPerformed

    private void Kd2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kd2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kd2KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgBayarPiutang dialog = new DlgBayarPiutang(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkPelunasan;
    private widget.TextBox Cicilan;
    private widget.TextBox Kd2;
    private widget.TextBox Keterangan;
    private widget.Label LCount;
    private widget.Label LTotal;
    private widget.TextBox NoRawat;
    private javax.swing.JPopupMenu Popup;
    private widget.ScrollPane Scroll;
    private widget.TextBox Sisa;
    private widget.TextBox TCari;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private widget.Tanggal Tanggal;
    private widget.Label TotalPiutang;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel23;
    private javax.swing.JPanel jPanel1;
    private widget.Label label10;
    private widget.Label label12;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label32;
    private widget.Label label34;
    private widget.Label label35;
    private widget.Label label36;
    private widget.Label label38;
    private widget.Label label9;
    private widget.ComboBox nama_bayar;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private javax.swing.JMenuItem ppNotaPiutang;
    private widget.Table tbPiutang;
    private widget.Tanggal tglNota;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps.setString(1, "%" + TCari.getText() + "%");
            ps.setString(2, "%" + TCari.getText() + "%");
            ps.setString(3, "%" + TCari.getText() + "%");
            ps.setString(4, "%" + TCari.getText() + "%");
            rs = ps.executeQuery();
            total = 0;
            while (rs.next()) {
                total = total + rs.getDouble(4);
                tabMode.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    Valid.SetAngka(rs.getDouble(4)),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString("tglbayar"),
                    rs.getString("waktu_simpan"),
                    Valid.SetAngka(rs.getDouble("sisa_piutang")),
                    rs.getString("penjamin"),
                    rs.getString("ket_penjamin")
                });
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
        LTotal.setText(Valid.SetAngka(total));
    }

    public void emptTeks() {
        Kd2.setText("");
        Cicilan.setText("0");
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
    
    public void setData(String norawat, String norm, String nama, double totpiutang) {
        NoRawat.setText(norawat);
        TNoRM.setText(norm);
        TNmPasien.setText(nama);
        TCari.setText(norawat);
        cekSisaPiutang();

//        Tsisapiutang = Sequel.cariIsiAngka("SELECT ifnull(SUM(pp.sisapiutang),0) FROM piutang_pasien pp where pp.no_rawat=?", NoRawat.getText())
////                + Sequel.cariIsiAngka("SELECT ifnull(SUM(p.sisapiutang),0) FROM piutang p where p.no_rkm_medis=?", TNoRM.getText())
//                - Sequel.cariIsiAngka("SELECT ifnull(SUM(bp.besar_cicilan),0) FROM bayar_piutang bp where bp.no_rawat=?", NoRawat.getText());

        TotalPiutang.setText(Valid.SetAngka(Valid.roundUp(totpiutang, 100)));
        if (!Cicilan.getText().equals("")) {
            Sisa.setText(Valid.SetAngka(Valid.roundUp(sisapiutang - Double.parseDouble(Cicilan.getText()), 100)));
            TotalPiutang.setText(Valid.SetAngka(Valid.roundUp(totpiutang - Double.parseDouble(Cicilan.getText()), 100)));
        }
    }

    private void getData() {
        wktsimpan = "";
        int row=tbPiutang.getSelectedRow();
        if(row!= -1){
            TNoRM.setText(tbPiutang.getValueAt(row,1).toString());
            TNmPasien.setText(tbPiutang.getValueAt(row,2).toString());
            Cicilan.setText(tbPiutang.getValueAt(row,3).toString());
            Keterangan.setText(tbPiutang.getValueAt(row,4).toString());
            NoRawat.setText(tbPiutang.getValueAt(row,5).toString());
            Valid.SetTgl(Tanggal,tbPiutang.getValueAt(row,0).toString());
            wktsimpan = tbPiutang.getValueAt(row,7).toString();
            Sisa.setText(tbPiutang.getValueAt(row,8).toString());
        }
    }

    public JTextField getTextField(){
        return NoRawat;
    }

    public JButton getButton(){
        return BtnKeluar;
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getbayar_piutang());
        BtnHapus.setEnabled(akses.getbayar_piutang());
        BtnPrint.setEnabled(akses.getbayar_piutang());
    }
    
    private void cekSisaPiutang() {
        sisapiutang = 0;
        sisapiutang = Sequel.cariIsiAngka("SELECT ifnull(SUM(pp.sisapiutang),0) FROM piutang_pasien pp where pp.no_rawat='" + NoRawat.getText() + "' and pp.no_rkm_medis=?", TNoRM.getText())
                + Sequel.cariIsiAngka("SELECT ifnull(SUM(p.sisapiutang),0) FROM piutang p where p.no_rkm_medis=?", TNoRM.getText())
                - Sequel.cariIsiAngka("SELECT ifnull(SUM(bp.besar_cicilan),0) FROM bayar_piutang bp where bp.no_rawat='" + NoRawat.getText() + "' and bp.no_rkm_medis=?", TNoRM.getText());
        Sisa.setText(Valid.SetAngka(Valid.roundUp(sisapiutang, 100)));
        Sisa.setText(Valid.SetAngka(Valid.roundUp(sisapiutang - Double.parseDouble(Cicilan.getText()), 100)));
    }
}
