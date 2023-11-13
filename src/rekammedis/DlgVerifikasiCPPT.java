package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgCatatanResep;
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

/**
 *
 * @author dosen
 */
public class DlgVerifikasiCPPT extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private int i = 0, x = 0;
    private String status = "", kodekamar = "";
    private Boolean conteng;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgVerifikasiCPPT(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null, new Object[]{
            "Cek","Verifikasi", "Tgl. CPPT", "Jam CPPT", "Jenis Bagian", "DPJP Konsulen", "Jenis PPA",
            "Nama PPA", "Shift", "hasil", "instruksi", "wkt_simpan", "konfirmasi_terapi", "tgl_lapor", 
            "jam_lapor", "tgl_verifikasi", "jam_verifikasi", "nmpetugasKonfir", "nmdpjpKonfir"
        }) {
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbCPPT.setModel(tabMode);
        tbCPPT.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCPPT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbCPPT.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(32);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(40);
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
        tbCPPT.setDefaultRenderer(Object.class, new WarnaTable());
        
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
        MnCopyHasil = new javax.swing.JMenuItem();
        MnCopyInstruksi = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        PanelInput = new javax.swing.JPanel();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        jLabel9 = new widget.Label();
        TnmUnit = new widget.TextBox();
        TNoRm = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel10 = new widget.Label();
        TtglMrs = new widget.TextBox();
        jLabel11 = new widget.Label();
        TnmDPJP = new widget.TextBox();
        internalFrame2 = new widget.InternalFrame();
        scrollPane4 = new widget.ScrollPane();
        Tinstruksi = new widget.TextArea();
        scrollPane5 = new widget.ScrollPane();
        Thasil = new widget.TextArea();
        Scroll = new widget.ScrollPane();
        tbCPPT = new widget.Table();
        panelGlass8 = new widget.panelisi();
        jLabel8 = new widget.Label();
        tglCppt1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        tglCppt2 = new widget.Tanggal();
        BtnCari = new widget.Button();
        BtnResep = new widget.Button();
        BtnConteng = new widget.Button();
        BtnHapus = new widget.Button();
        BtnBatal = new widget.Button();
        BtnSimpan = new widget.Button();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCopyHasil.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopyHasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopyHasil.setText("Copy Hasil");
        MnCopyHasil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCopyHasil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCopyHasil.setIconTextGap(5);
        MnCopyHasil.setName("MnCopyHasil"); // NOI18N
        MnCopyHasil.setPreferredSize(new java.awt.Dimension(130, 26));
        MnCopyHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopyHasilActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCopyHasil);

        MnCopyInstruksi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopyInstruksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopyInstruksi.setText("Copy Instruksi");
        MnCopyInstruksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCopyInstruksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCopyInstruksi.setIconTextGap(5);
        MnCopyInstruksi.setName("MnCopyInstruksi"); // NOI18N
        MnCopyInstruksi.setPreferredSize(new java.awt.Dimension(130, 26));
        MnCopyInstruksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopyInstruksiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCopyInstruksi);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Verifikasi CPPT ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 110));
        PanelInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Pasien : ");
        jLabel4.setName("jLabel4"); // NOI18N
        PanelInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 100, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        PanelInput.add(TNoRw);
        TNoRw.setBounds(103, 10, 122, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Rg. Rawat/Unit : ");
        jLabel9.setName("jLabel9"); // NOI18N
        PanelInput.add(jLabel9);
        jLabel9.setBounds(0, 40, 100, 23);

        TnmUnit.setEditable(false);
        TnmUnit.setForeground(new java.awt.Color(0, 0, 0));
        TnmUnit.setName("TnmUnit"); // NOI18N
        PanelInput.add(TnmUnit);
        TnmUnit.setBounds(103, 40, 390, 23);

        TNoRm.setEditable(false);
        TNoRm.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm.setName("TNoRm"); // NOI18N
        PanelInput.add(TNoRm);
        TNoRm.setBounds(229, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        PanelInput.add(TPasien);
        TPasien.setBounds(303, 10, 360, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Tgl. MRS :");
        jLabel10.setName("jLabel10"); // NOI18N
        PanelInput.add(jLabel10);
        jLabel10.setBounds(495, 40, 60, 23);

        TtglMrs.setEditable(false);
        TtglMrs.setForeground(new java.awt.Color(0, 0, 0));
        TtglMrs.setName("TtglMrs"); // NOI18N
        PanelInput.add(TtglMrs);
        TtglMrs.setBounds(560, 40, 103, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Nama DPJP : ");
        jLabel11.setName("jLabel11"); // NOI18N
        PanelInput.add(jLabel11);
        jLabel11.setBounds(0, 70, 100, 23);

        TnmDPJP.setEditable(false);
        TnmDPJP.setForeground(new java.awt.Color(0, 0, 0));
        TnmDPJP.setName("TnmDPJP"); // NOI18N
        PanelInput.add(TnmDPJP);
        TnmDPJP.setBounds(103, 70, 560, 23);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.GridLayout(1, 3));

        scrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Instruksi Tenaga Kesehatan Termasuk Pasca Bedah/Prosedur ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        scrollPane4.setName("scrollPane4"); // NOI18N

        Tinstruksi.setEditable(false);
        Tinstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tinstruksi.setColumns(20);
        Tinstruksi.setRows(5);
        Tinstruksi.setComponentPopupMenu(jPopupMenu1);
        Tinstruksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        scrollPane4.setViewportView(Tinstruksi);

        internalFrame2.add(scrollPane4);

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Hasil Pemeriksaan, Analisa, Rencana, Penatalaksanaan Pasien ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N

        Thasil.setEditable(false);
        Thasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Thasil.setColumns(20);
        Thasil.setRows(5);
        Thasil.setComponentPopupMenu(jPopupMenu1);
        Thasil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Thasil.setName("Thasil"); // NOI18N
        scrollPane5.setViewportView(Thasil);

        internalFrame2.add(scrollPane5);

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbCPPT.setToolTipText("Silahkan klik untuk membaca CPPT");
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
        Scroll.setViewportView(tbCPPT);

        internalFrame2.add(Scroll);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. CPPT :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass8.add(jLabel8);

        tglCppt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-11-2023" }));
        tglCppt1.setDisplayFormat("dd-MM-yyyy");
        tglCppt1.setName("tglCppt1"); // NOI18N
        tglCppt1.setOpaque(false);
        tglCppt1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass8.add(tglCppt1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass8.add(jLabel21);

        tglCppt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-11-2023" }));
        tglCppt2.setDisplayFormat("dd-MM-yyyy");
        tglCppt2.setName("tglCppt2"); // NOI18N
        tglCppt2.setOpaque(false);
        tglCppt2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass8.add(tglCppt2);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+1");
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
        panelGlass8.add(BtnCari);

        BtnResep.setForeground(new java.awt.Color(0, 0, 0));
        BtnResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Vial-Pills.png"))); // NOI18N
        BtnResep.setMnemonic('R');
        BtnResep.setText("Resep Obat");
        BtnResep.setToolTipText("Alt+R");
        BtnResep.setName("BtnResep"); // NOI18N
        BtnResep.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResepActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnResep);

        BtnConteng.setForeground(new java.awt.Color(0, 0, 0));
        BtnConteng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnConteng.setMnemonic('G');
        BtnConteng.setText("Conteng Semua");
        BtnConteng.setToolTipText("Alt+G");
        BtnConteng.setName("BtnConteng"); // NOI18N
        BtnConteng.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnConteng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnContengActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnConteng);

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnHapus.setMnemonic('M');
        BtnHapus.setText("Hapus Conteng");
        BtnHapus.setToolTipText("Alt+M");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(140, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnBatal.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Batal Verifikasi");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Setuju Verifikasi");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(150, 30));
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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
            TNoRw.requestFocus();
        } else {
            x = 0;
            for (i = 0; i < tbCPPT.getRowCount(); i++) {
                if (tbCPPT.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }
            
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu CPPTnya untuk setuju diverifikasi..!!!!");
                tbCPPT.requestFocus();
            } else {
                try {
                    for (i = 0; i < tbCPPT.getRowCount(); i++) {
                        if (tbCPPT.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.queryu("update cppt set verifikasi = 'Sudah' where waktu_simpan='" + tbCPPT.getValueAt(i, 11).toString() + "'");
                        }
                    }
                    BtnHapusActionPerformed(null);
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,TNoRw,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
            TNoRw.requestFocus();
        } else {
            x = 0;
            for (i = 0; i < tbCPPT.getRowCount(); i++) {
                if (tbCPPT.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu CPPTnya untuk pembatalan verifikasi..!!!!");
                tbCPPT.requestFocus();
            } else {
                try {
                    for (i = 0; i < tbCPPT.getRowCount(); i++) {
                        if (tbCPPT.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.queryu("update cppt set verifikasi = 'Belum' where waktu_simpan='" + tbCPPT.getValueAt(i, 11).toString() + "'");
                        }
                    }
                    BtnHapusActionPerformed(null);
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            }
        }        
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnContengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnContengActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, CPPT belum ada...!!!!");
            tbCPPT.requestFocus();
        } else {
            Thasil.setText("");
            Tinstruksi.setText("");
            tampil();
            for (i = 0; i < tbCPPT.getRowCount(); i++) {
                tbCPPT.setValueAt(Boolean.TRUE, i, 0);
            }
        }
}//GEN-LAST:event_BtnContengActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } 
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, CPPT belum ada...!!!!");
            tbCPPT.requestFocus();
        } else {
            Thasil.setText("");
            Tinstruksi.setText("");
            tampil();
            for (i = 0; i < tbCPPT.getRowCount(); i++) {
                tbCPPT.setValueAt(Boolean.FALSE, i, 0);
            }
        }           
}//GEN-LAST:event_BtnHapusActionPerformed

    private void tbCPPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCPPTMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbCPPTMouseClicked

    private void tbCPPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCPPTKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbCPPTKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("DlgVerifikasiCPPT");
        DlgCatatanResep form = new DlgCatatanResep(null, false);
        form.isCek();
        
        if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
            form.setData(TNoRw.getText(), "IGDK", status);
        } else if (status.equals("ranap")) {
            kodekamar = "";
            kodekamar = Sequel.cariIsi("select ki.kd_kamar from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' "
                    + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");
            form.setData(TNoRw.getText(), Sequel.cariIsi("SELECT b.nm_gedung FROM kamar k "
                    + "INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar='" + kodekamar + "'"), status);
        }        
        
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnResepActionPerformed

    private void MnCopyHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopyHasilActionPerformed
        if (Thasil.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data hasil pemeriksaan masih kosong/belum terisi...!!!");
        } else {
            akses.setCopyData(Thasil.getText());
            BtnKeluarActionPerformed(null);
        }
    }//GEN-LAST:event_MnCopyHasilActionPerformed

    private void MnCopyInstruksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopyInstruksiActionPerformed
        if (Tinstruksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data instruksi tenaga kesehatan masih kosong/belum terisi...!!!");
        } else {
            akses.setCopyData(Tinstruksi.getText());
            BtnKeluarActionPerformed(null);
        }
    }//GEN-LAST:event_MnCopyInstruksiActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgVerifikasiCPPT dialog = new DlgVerifikasiCPPT(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnConteng;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnResep;
    private widget.Button BtnSimpan;
    private javax.swing.JMenuItem MnCopyHasil;
    private javax.swing.JMenuItem MnCopyInstruksi;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea Thasil;
    private widget.TextArea Tinstruksi;
    private widget.TextBox TnmDPJP;
    private widget.TextBox TnmUnit;
    private widget.TextBox TtglMrs;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel21;
    private widget.Label jLabel4;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbCPPT;
    private widget.Tanggal tglCppt1;
    private widget.Tanggal tglCppt2;
    // End of variables declaration//GEN-END:variables

    private void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
                ps = koneksi.prepareStatement("SELECT c.verifikasi, DATE_FORMAT(c.tgl_cppt,'%d-%m-%Y') tgl, if(c.cek_jam='ya',TIME_FORMAT(c.jam_cppt,'%H:%i'),'-') jam, "
                        + "c.jenis_bagian, pg1.nama nmdpjp, c.jenis_ppa, pg2.nama nmppa, c.cppt_shift, c.hasil_pemeriksaan, "
                        + "c.instruksi_nakes, c.waktu_simpan, c.konfirmasi_terapi, DATE_FORMAT(c.tgl_lapor,'%d-%m-%Y') tgl_lapor, "
                        + "time_format(c.jam_lapor,'%H:%i') jam_lapor, DATE_FORMAT(c.tgl_verifikasi,'%d-%m-%Y') tgl_verifikasi, "
                        + "time_format(c.jam_verifikasi,'%H:%i') jam_verif, pg3.nama petugasKonfir, pg4.nama dpjpKonfir from cppt c "
                        + "inner join pegawai pg1 on pg1.nik=c.nip_konsulen "
                        + "inner join pegawai pg2 on pg2.nik=c.nip_ppa "
                        + "INNER JOIN pegawai pg3 ON pg3.nik = c.nip_petugas_konfir "
                        + "INNER JOIN pegawai pg4 ON pg4.nik = c.nip_dpjp_konfir where "
                        + "c.flag_hapus='tidak' and c.status='ralan' and c.tgl_cppt between ? and ? and c.no_rawat='" + TNoRw.getText() + "' order by c.tgl_cppt, c.jam_cppt");
            } else if (status.equals("ranap")) {
                ps = koneksi.prepareStatement("SELECT c.verifikasi, DATE_FORMAT(c.tgl_cppt,'%d-%m-%Y') tgl, if(c.cek_jam='ya',TIME_FORMAT(c.jam_cppt,'%H:%i'),'-') jam, "
                        + "c.jenis_bagian, pg1.nama nmdpjp, c.jenis_ppa, pg2.nama nmppa, c.cppt_shift, c.hasil_pemeriksaan, "
                        + "c.instruksi_nakes, c.waktu_simpan, c.konfirmasi_terapi, DATE_FORMAT(c.tgl_lapor,'%d-%m-%Y') tgl_lapor, "
                        + "time_format(c.jam_lapor,'%H:%i') jam_lapor, DATE_FORMAT(c.tgl_verifikasi,'%d-%m-%Y') tgl_verifikasi, "
                        + "time_format(c.jam_verifikasi,'%H:%i') jam_verif, pg3.nama petugasKonfir, pg4.nama dpjpKonfir from cppt c "
                        + "inner join pegawai pg1 on pg1.nik=c.nip_konsulen "
                        + "inner join pegawai pg2 on pg2.nik=c.nip_ppa "
                        + "INNER JOIN pegawai pg3 ON pg3.nik = c.nip_petugas_konfir "
                        + "INNER JOIN pegawai pg4 ON pg4.nik = c.nip_dpjp_konfir where "
                        + "c.flag_hapus='tidak' and c.status='ranap' and c.tgl_cppt between ? and ? and c.no_rawat='" + TNoRw.getText() + "' order by c.tgl_cppt, c.jam_cppt");
            }

            try {
                ps.setString(1, Valid.SetTgl(tglCppt1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(tglCppt2.getSelectedItem() + ""));
                rs = ps.executeQuery();                
                while (rs.next()) {
                    if (rs.getString("verifikasi").equals("Sudah")) {
                        conteng = true;
                    } else {
                        conteng = false;
                    }
                    
                    tabMode.addRow(new Object[]{
                        conteng,
                        rs.getString("verifikasi"),
                        rs.getString("tgl"),
                        rs.getString("jam"),
                        rs.getString("jenis_bagian"),
                        rs.getString("nmdpjp"),
                        rs.getString("jenis_ppa"),
                        rs.getString("nmppa"),
                        rs.getString("cppt_shift"),
                        rs.getString("hasil_pemeriksaan"),
                        rs.getString("instruksi_nakes"),
                        rs.getString("waktu_simpan"),
                        rs.getString("konfirmasi_terapi"),
                        rs.getString("tgl_lapor"),
                        rs.getString("jam_lapor"),
                        rs.getString("tgl_verifikasi"),
                        rs.getString("jam_verif"),
                        rs.getString("petugasKonfir"),
                        rs.getString("dpjpKonfir")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
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
    }
    
    private void getData() {
        if (tbCPPT.getSelectedRow() != -1) {
            Thasil.setText("Tgl. CPPT : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 2).toString() + ", Jam : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 3).toString() + " WITA\n\n"
                    + "" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString());
            
            //konfirmasi terapi
            if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 12).toString().equals("ya")) {
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 7).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString() + "\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n"
                            + "Tgl. Lapor : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 13).toString() + ", Jam : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 14).toString() + " WITA\n"
                            + "Tgl. Verifikasi : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 15).toString() + ", Jam : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 16).toString() + " WITA\n"
                            + "Nama " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString() + " : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 17).toString() + "\n"
                            + "DPJP : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 18).toString());
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 7).toString() + ")\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n"
                            + "Tgl. Lapor : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 13).toString() + ", Jam : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 14).toString() + " WITA\n"
                            + "Tgl. Verifikasi : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 15).toString() + ", Jam : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 16).toString() + " WITA\n"
                            + "Nama " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString() + " : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 17).toString() + "\n"
                            + "DPJP : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 18).toString());
                }
            } else {
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 7).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString());
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 7).toString() + ")");
                }
            }
        }
    }
    
    public void setData(String norw, String stts_rwt) {
        TNoRw.setText(norw);
        status = stts_rwt;
        isPasien();
    }
    
    private void isPasien() {
        try {
            ps1 = koneksi.prepareStatement("SELECT rp.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(rp.tgl_registrasi,'%d-%m-%Y') tglmrs, "
                    + "rp.kd_poli, rp.kd_dokter, rp.tgl_registrasi from reg_periksa rp inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                    + "rp.no_rawat='" + TNoRw.getText() + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    TNoRm.setText(rs1.getString("no_rkm_medis"));
                    TPasien.setText(rs1.getString("nm_pasien"));
                    TtglMrs.setText(rs1.getString("tglmrs"));
                    tglCppt1.setDate(rs1.getDate("tgl_registrasi"));
                    tglCppt2.setDate(new Date());
                    
                    if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
                        TnmUnit.setText(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + rs1.getString("kd_poli") + "'"));
                        TnmDPJP.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + rs1.getString("kd_dokter") + "'"));
                    } else if (status.equals("ranap")) {
                        TnmUnit.setText(Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                                + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + rs1.getString("no_rawat") + "' "
                                + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));
                        TnmDPJP.setText(Sequel.cariIsi("select d.nm_dokter from dpjp_ranap dr inner join dokter d on d.kd_dokter=dr.kd_dokter "
                                + "where dr.no_rawat='" + rs1.getString("no_rawat") + "'"));
                    }
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
    
}
