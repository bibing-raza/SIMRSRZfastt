package simrskhanza;

import rekammedis.*;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class DlgPerubahanDpjp extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0, pilihan = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String dialog_simpan = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgPerubahanDpjp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. MRS", "Rg. Rawat Terakhir", "NIP DPJP", "Nama DPJP Sebelumnya", "NIP DPJP",
            "Nama DPJP Pengganti", "Tgl. Pergantian", "Keterangan / Alasan", "tgl_pergantian", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbDpjp.setModel(tabMode);
        tbDpjp.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDpjp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 13; i++) {
            TableColumn column = tbDpjp.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(130);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(140);
            } else if (i == 10) {
                column.setPreferredWidth(300);
            } else if (i == 11) {                
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 12) {                
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDpjp.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbDpjp.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        Tketerangan.setDocument(new batasInput((int) 200).getKata(Tketerangan));
        
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPerubahanDpjp")) {
                    if (pilihan == 1) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            TnipSebelum.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            TnmDpjpSebelum.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            btnDPJPSebelum.requestFocus();
                        }
                    } else if (pilihan == 2) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            TnipPengganti.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            TnmDpjpPengganti.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            btnDPJPPengganti.requestFocus();
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
        
        ChkInput.setSelected(false);
        isForm();
    }
 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnGanti = new javax.swing.JMenuItem();
        MnHapus = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnAll = new widget.Button();
        BtnExcel = new widget.Button();
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
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        jLabel9 = new widget.Label();
        TnmDpjpSebelum = new widget.TextBox();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        TnipSebelum = new widget.TextBox();
        jLabel10 = new widget.Label();
        TnipPengganti = new widget.TextBox();
        TnmDpjpPengganti = new widget.TextBox();
        jLabel11 = new widget.Label();
        TtglPergantian = new widget.Tanggal();
        jLabel13 = new widget.Label();
        Tketerangan = new widget.TextBox();
        btnDPJPSebelum = new widget.Button();
        btnDPJPPengganti = new widget.Button();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbDpjp = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnGanti.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        MnGanti.setText("Ganti Data");
        MnGanti.setName("MnGanti"); // NOI18N
        MnGanti.setPreferredSize(new java.awt.Dimension(110, 25));
        MnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnGanti);

        MnHapus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapus.setText("Hapus Data");
        MnHapus.setName("MnHapus"); // NOI18N
        MnHapus.setPreferredSize(new java.awt.Dimension(110, 25));
        MnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapus);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Perubahan DPJP Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        BtnExcel.setForeground(new java.awt.Color(0, 0, 0));
        BtnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        BtnExcel.setMnemonic('M');
        BtnExcel.setText("Export Data Ke Ms. Excel");
        BtnExcel.setToolTipText("Alt+M");
        BtnExcel.setName("BtnExcel"); // NOI18N
        BtnExcel.setPreferredSize(new java.awt.Dimension(180, 30));
        BtnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcelActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnExcel);

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
        jLabel19.setText("Tgl. Simpan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-07-2025" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-07-2025" }));
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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 180));
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

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Pasien : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 140, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("DPJP Sebelumnya : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 40, 140, 23);

        TnmDpjpSebelum.setEditable(false);
        TnmDpjpSebelum.setForeground(new java.awt.Color(0, 0, 0));
        TnmDpjpSebelum.setName("TnmDpjpSebelum"); // NOI18N
        FormInput.add(TnmDpjpSebelum);
        TnmDpjpSebelum.setBounds(302, 40, 459, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(143, 12, 175, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(322, 12, 75, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        TPasien.setPreferredSize(new java.awt.Dimension(25, 28));
        FormInput.add(TPasien);
        TPasien.setBounds(401, 12, 360, 23);

        TnipSebelum.setEditable(false);
        TnipSebelum.setForeground(new java.awt.Color(0, 0, 0));
        TnipSebelum.setName("TnipSebelum"); // NOI18N
        FormInput.add(TnipSebelum);
        TnipSebelum.setBounds(143, 40, 155, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Diganti Oleh DPJP : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 68, 140, 23);

        TnipPengganti.setEditable(false);
        TnipPengganti.setForeground(new java.awt.Color(0, 0, 0));
        TnipPengganti.setName("TnipPengganti"); // NOI18N
        FormInput.add(TnipPengganti);
        TnipPengganti.setBounds(143, 68, 155, 23);

        TnmDpjpPengganti.setEditable(false);
        TnmDpjpPengganti.setForeground(new java.awt.Color(0, 0, 0));
        TnmDpjpPengganti.setName("TnmDpjpPengganti"); // NOI18N
        FormInput.add(TnmDpjpPengganti);
        TnmDpjpPengganti.setBounds(302, 68, 459, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tgl. Pergantian : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 96, 140, 23);

        TtglPergantian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-07-2025" }));
        TtglPergantian.setDisplayFormat("dd-MM-yyyy");
        TtglPergantian.setName("TtglPergantian"); // NOI18N
        TtglPergantian.setOpaque(false);
        TtglPergantian.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglPergantian);
        TtglPergantian.setBounds(143, 96, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Keterangan / Alasan : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 124, 140, 23);

        Tketerangan.setForeground(new java.awt.Color(0, 0, 0));
        Tketerangan.setName("Tketerangan"); // NOI18N
        FormInput.add(Tketerangan);
        Tketerangan.setBounds(143, 124, 617, 23);

        btnDPJPSebelum.setForeground(new java.awt.Color(0, 0, 0));
        btnDPJPSebelum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDPJPSebelum.setName("btnDPJPSebelum"); // NOI18N
        btnDPJPSebelum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDPJPSebelumActionPerformed(evt);
            }
        });
        FormInput.add(btnDPJPSebelum);
        btnDPJPSebelum.setBounds(762, 40, 28, 23);

        btnDPJPPengganti.setForeground(new java.awt.Color(0, 0, 0));
        btnDPJPPengganti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDPJPPengganti.setName("btnDPJPPengganti"); // NOI18N
        btnDPJPPengganti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDPJPPenggantiActionPerformed(evt);
            }
        });
        FormInput.add(btnDPJPPengganti);
        btnDPJPPengganti.setBounds(762, 68, 28, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbDpjp.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbDpjp.setComponentPopupMenu(jPopupMenu1);
        tbDpjp.setName("tbDpjp"); // NOI18N
        tbDpjp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDpjpMouseClicked(evt);
            }
        });
        tbDpjp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDpjpKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbDpjp);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (TnipSebelum.getText().equals("") || TnipSebelum.getText().equals("-") || TnipSebelum.getText().equals("--")) {
            JOptionPane.showMessageDialog(null, "Pilihlah DPJP sebelumnyanya dengan benar..!!");
            btnDPJPSebelum.requestFocus();
        } else if (TnipPengganti.getText().equals("") || TnipPengganti.getText().equals("-") || TnipPengganti.getText().equals("--")) {
            JOptionPane.showMessageDialog(null, "Pilihlah DPJP penggantinya dengan benar..!!");
            btnDPJPPengganti.requestFocus();
        } else if (Tketerangan.getText().trim().equals("")) {
            Valid.textKosong(Tketerangan, "Keterangan / Alasan");
            Tketerangan.requestFocus();
        } else {
            if (Sequel.menyimpantf("perubahan_dpjp_ranap", "?,?,?,?,?,?", "No.Rawat", 6, new String[]{
                TNoRw.getText(), TnipSebelum.getText(), TnipPengganti.getText(), Valid.SetTgl(TtglPergantian.getSelectedItem() + ""),
                Tketerangan.getText(), Sequel.cariIsi("select now()")
            }) == true) {
                Sequel.queryu("delete from dpjp_ranap where no_rawat='" + TNoRw.getText() + "'");
                Sequel.menyimpan("dpjp_ranap", "'" + TNoRw.getText() + "','" + TnipPengganti.getText() + "'", "DPJP");
                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Perubahan DPJP", "Simpan");
                
                akses.setCopyData("");
                akses.setCopyData("perubahan dpjp");
                TCari.setText(TNoRw.getText());
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
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
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

    private void tbDpjpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDpjpMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbDpjpMouseClicked

    private void tbDpjpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDpjpKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbDpjpKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void btnDPJPSebelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDPJPSebelumActionPerformed
        pilihan = 0;
        pilihan = 1;
        akses.setform("DlgPerubahanDpjp");
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDPJPSebelumActionPerformed

    private void btnDPJPPenggantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDPJPPenggantiActionPerformed
        pilihan = 0;
        pilihan = 2;
        akses.setform("DlgPerubahanDpjp");
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDPJPPenggantiActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        if (tbDpjp.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from perubahan_dpjp_ranap where waktu_simpan=?", 1, new String[]{
                    tbDpjp.getValueAt(tbDpjp.getSelectedRow(), 14).toString()
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
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tampil();
        }
    }//GEN-LAST:event_MnHapusActionPerformed

    private void MnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiActionPerformed
        if (tbDpjp.getSelectedRow() > -1) {
            if (TnipSebelum.getText().equals("") || TnipSebelum.getText().equals("-") || TnipSebelum.getText().equals("--")) {
                JOptionPane.showMessageDialog(null, "Pilihlah DPJP sebelumnyanya dengan benar..!!");
                btnDPJPSebelum.requestFocus();
            } else if (TnipPengganti.getText().equals("") || TnipPengganti.getText().equals("-") || TnipPengganti.getText().equals("--")) {
                JOptionPane.showMessageDialog(null, "Pilihlah DPJP penggantinya dengan benar..!!");
                btnDPJPPengganti.requestFocus();
            } else if (Tketerangan.getText().trim().equals("")) {
                Valid.textKosong(Tketerangan, "Keterangan / Alasan");
                Tketerangan.requestFocus();
            } else {
                if (Sequel.mengedittf("perubahan_dpjp_ranap", "waktu_simpan=?", "kd_dokter_sebelum=?, kd_dokter_pengganti=?, "
                        + "tgl_pergantian=?, keterangan=?", 5, new String[]{
                            TnipSebelum.getText(), TnipPengganti.getText(), Valid.SetTgl(TtglPergantian.getSelectedItem() + ""), Tketerangan.getText(),
                            tbDpjp.getValueAt(tbDpjp.getSelectedRow(), 14).toString()
                        }) == true) {
                    
                    Sequel.queryu("delete from dpjp_ranap where no_rawat='" + TNoRw.getText() + "'");
                    Sequel.menyimpan("dpjp_ranap", "'" + TNoRw.getText() + "','" + TnipPengganti.getText() + "'", "DPJP");
                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Perubahan DPJP", "Ganti");
                    
                    akses.setCopyData("");
                    akses.setCopyData("perubahan dpjp");
                    TCari.setText(TNoRw.getText());
                    tampil();
                    emptTeks();
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tampil();
        }
    }//GEN-LAST:event_MnGantiActionPerformed

    private void BtnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcelActionPerformed
        if (tbDpjp.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Maaf, data pada tabel masih kosong...!!!");
            tampil();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary");
            int row = tabMode.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary", "'0','"
                        + tabMode.getValueAt(r, 0).toString() + "','"
                        + tabMode.getValueAt(r, 1).toString() + "','"
                        + tabMode.getValueAt(r, 2).toString() + "','"
                        + tabMode.getValueAt(r, 3).toString() + "','"
                        + tabMode.getValueAt(r, 4).toString() + "','"
                        + tabMode.getValueAt(r, 5).toString() + "','"
                        + tabMode.getValueAt(r, 6).toString() + "','"
                        + tabMode.getValueAt(r, 7).toString() + "','"
                        + tabMode.getValueAt(r, 8).toString() + "','"
                        + tabMode.getValueAt(r, 9).toString() + "','"
                        + tabMode.getValueAt(r, 10).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Perubahan DPJP");
            }
            Sequel.AutoComitTrue();
            
            dialog_simpan = Valid.openDialog();
            Valid.MyReportToExcel("SELECT temp1 'No. Rawat', temp2 'No. RM', temp3 'Nama Pasien', temp4 'Tgl. MRS', temp5 'Rg. Rawat Terakhir', "
                    + "temp6 'NIP DPJP', temp7 'Nama DPJP Sebelumnya', temp8 'NIP DPJP', temp9 'Nama DPJP Pengganti', temp10 'Tgl. Pergantian', "
                    + "temp11 'Keterangan / Alasan' FROM temporary", dialog_simpan);
            
            JOptionPane.showMessageDialog(null, "Data perubahan DPJP berhasil diexport menjadi file excel,..!!!");
            tampil();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnExcelActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPerubahanDpjp dialog = new DlgPerubahanDpjp(new javax.swing.JFrame(), true);
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
    private widget.Button BtnExcel;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnGanti;
    private javax.swing.JMenuItem MnHapus;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tketerangan;
    private widget.TextBox TnipPengganti;
    private widget.TextBox TnipSebelum;
    private widget.TextBox TnmDpjpPengganti;
    private widget.TextBox TnmDpjpSebelum;
    private widget.Tanggal TtglPergantian;
    private widget.Button btnDPJPPengganti;
    private widget.Button btnDPJPSebelum;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel13;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbDpjp;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT pdr.*, p.no_rkm_medis, p.nm_pasien, date_format(rp.tgl_registrasi,'%d-%m-%Y') tglMrs, pg1.nama dpjpSebelum, "
                    + "pg2.nama dpjpPengganti, concat(date_format(pdr.tgl_pergantian,'%d-%m-%Y'),', ',time_format(pdr.waktu_simpan,'%H:%i Wita')) tglGanti, "
                    + "(select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal where "
                    + "ki.no_rawat=pdr.no_rawat order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1) rgRawat FROM perubahan_dpjp_ranap pdr "
                    + "inner join reg_periksa rp on rp.no_rawat =pdr.no_rawat inner join pasien p on p.no_rkm_medis =rp.no_rkm_medis "
                    + "inner join pegawai pg1 on pg1.nik =pdr.kd_dokter_sebelum inner join pegawai pg2 on pg2.nik =pdr.kd_dokter_pengganti WHERE "
                    + "pdr.tgl_pergantian between ? and ? and pdr.no_rawat LIKE ? or "
                    + "pdr.tgl_pergantian between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "pdr.tgl_pergantian between ? and ? and p.nm_pasien LIKE ? or "
                    + "pdr.tgl_pergantian between ? and ? and pg1.nama LIKE ? or "
                    + "pdr.tgl_pergantian between ? and ? and pg2.nama LIKE ? or "
                    + "pdr.tgl_pergantian between ? and ? and (select b.nm_bangsal from kamar_inap ki "
                    + "inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal where "
                    + "ki.no_rawat=pdr.no_rawat order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1) LIKE ? or "
                    + "pdr.tgl_pergantian between ? and ? and pdr.keterangan LIKE ? ORDER BY pdr.waktu_simpan");

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
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();           
                while (rs.next()) {
                    tabMode.addRow(new String[]{                        
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglMrs"),
                        rs.getString("rgRawat"),
                        rs.getString("kd_dokter_sebelum"),
                        rs.getString("dpjpSebelum"),
                        rs.getString("kd_dokter_pengganti"),
                        rs.getString("dpjpPengganti"),
                        rs.getString("tglGanti"),
                        rs.getString("keterangan"),
                        rs.getString("tgl_pergantian"),
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
    
    public void emptTeks() {  
        TNoRw.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        TnipSebelum.setText("");
        TnmDpjpSebelum.setText("");
        TnipPengganti.setText("");
        TnmDpjpPengganti.setText("");
        TtglPergantian.setDate(new Date());
        Tketerangan.setText("");
        TtglPergantian.requestFocus();
    }

    private void getData() {
        if (tbDpjp.getSelectedRow() != -1) {
            TNoRw.setText(tbDpjp.getValueAt(tbDpjp.getSelectedRow(), 0).toString());
            TNoRM.setText(tbDpjp.getValueAt(tbDpjp.getSelectedRow(), 1).toString());
            TPasien.setText(tbDpjp.getValueAt(tbDpjp.getSelectedRow(), 2).toString());
            TnipSebelum.setText(tbDpjp.getValueAt(tbDpjp.getSelectedRow(), 5).toString());
            TnmDpjpSebelum.setText(tbDpjp.getValueAt(tbDpjp.getSelectedRow(), 6).toString());
            TnipPengganti.setText(tbDpjp.getValueAt(tbDpjp.getSelectedRow(), 7).toString());
            TnmDpjpPengganti.setText(tbDpjp.getValueAt(tbDpjp.getSelectedRow(), 8).toString());
            Valid.SetTgl(TtglPergantian, tbDpjp.getValueAt(tbDpjp.getSelectedRow(), 11).toString());
            Tketerangan.setText(tbDpjp.getValueAt(tbDpjp.getSelectedRow(), 10).toString());
        }
    }
    
    public void isForm(){
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 180));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getdpjp_ranap());
        BtnExcel.setEnabled(akses.getdpjp_ranap());
        MnGanti.setEnabled(akses.getadmin());
        MnHapus.setEnabled(akses.getadmin());
        btnDPJPSebelum.setEnabled(akses.getadmin());
    }
    
    public void setData(String norwt, String norm, String nmPasien, String nipSebelumnya) {
        TNoRw.setText(norwt);
        TNoRM.setText(norm);
        TPasien.setText(nmPasien);
        TnipSebelum.setText(nipSebelumnya);
        TnmDpjpSebelum.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipSebelum.getText() + "'"));
        ChkInput.setSelected(true);
        isForm();
    }
}
