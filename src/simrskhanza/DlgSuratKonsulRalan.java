package simrskhanza;

import rekammedis.*;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Color;
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
public class DlgSuratKonsulRalan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private String aktifjadwal = "", kasus = "", ketklinis = "", tgljawaban = "", tglkonsululang = "", dokterpenjawab = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgSuratKonsulRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new String[]{
            "waktu_simpan", "No. Rawat", "No. RM", "Nama Pasien", "Poliklinik Awal", "Poliklinik Tujuan", "Jenis Konsul", "Tujuan Konsul",
            "Ket. Lain Tujuan Konsul", "Tgl. Permintaan Konsul", "Keterangan Klinis", "Status Jawaban", "tgl_permintaan_konsul", "kd_poli",
            "kasus_ditemukan", "ket_klinis_jawaban", "tgl_menjawab", "tgl_konsul_ulang", "dokterPenjawab", "konsul_ulang", "kd_poli_pembalas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbKonsul.setModel(tabMode);
        tbKonsul.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKonsul.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 21; i++) {
            TableColumn column = tbKonsul.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(240);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(130);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(90);
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
            } else if (i == 19) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 20) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbKonsul.setDefaultRenderer(Object.class, new WarnaTable());

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
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli.getTable().getSelectedRow()!= -1){   
                    kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),0).toString());
                    TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
                    kdpoli.requestFocus();                        
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
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            aktifjadwal = prop.getProperty("JADWALDOKTERDIREGISTRASI");
        } catch (Exception ex) {
            aktifjadwal = "";
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

        internalFrame1 = new widget.InternalFrame();
        FormInput = new widget.PanelBiasa();
        jLabel9 = new widget.Label();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel11 = new widget.Label();
        cmbJenisKonsul = new widget.ComboBox();
        jLabel12 = new widget.Label();
        cmbTujuanKonsul = new widget.ComboBox();
        jLabel13 = new widget.Label();
        TketLain = new widget.TextBox();
        jLabel14 = new widget.Label();
        TtglMintaKonsul = new widget.Tanggal();
        jLabel15 = new widget.Label();
        Scroll7 = new widget.ScrollPane();
        TketKlinis = new widget.TextArea();
        jLabel16 = new widget.Label();
        kdpoli = new widget.TextBox();
        TPoli = new widget.TextBox();
        BtnPoli = new widget.Button();
        jLabel17 = new widget.Label();
        ketHari = new widget.Label();
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
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKonsul = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Surat Konsultasi Poliklinik Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 400));
        FormInput.setLayout(null);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Poliklinik Tujuan :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 38, 110, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(247, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(319, 10, 410, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Jenis Konsul :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(580, 38, 80, 23);

        cmbJenisKonsul.setForeground(new java.awt.Color(0, 0, 0));
        cmbJenisKonsul.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Biasa", "Cito" }));
        cmbJenisKonsul.setName("cmbJenisKonsul"); // NOI18N
        FormInput.add(cmbJenisKonsul);
        cmbJenisKonsul.setBounds(665, 38, 60, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tujuan Konsul :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 66, 110, 23);

        cmbTujuanKonsul.setForeground(new java.awt.Color(0, 0, 0));
        cmbTujuanKonsul.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Konsultasi/Tindakan Masalah Medis Saat Ini", "Mengambil Alih Kasus Ini Untuk Selanjutnya", "Perawatan Bersama Untuk Selanjutnya", "Lainnya" }));
        cmbTujuanKonsul.setName("cmbTujuanKonsul"); // NOI18N
        cmbTujuanKonsul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTujuanKonsulActionPerformed(evt);
            }
        });
        FormInput.add(cmbTujuanKonsul);
        cmbTujuanKonsul.setBounds(114, 66, 240, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Ket. Lainnya :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(360, 66, 80, 23);

        TketLain.setForeground(new java.awt.Color(0, 0, 0));
        TketLain.setHighlighter(null);
        TketLain.setName("TketLain"); // NOI18N
        TketLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketLainKeyPressed(evt);
            }
        });
        FormInput.add(TketLain);
        TketLain.setBounds(444, 66, 285, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tgl. Konsultasi :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 94, 110, 23);

        TtglMintaKonsul.setEditable(false);
        TtglMintaKonsul.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-01-2025" }));
        TtglMintaKonsul.setDisplayFormat("dd-MM-yyyy");
        TtglMintaKonsul.setName("TtglMintaKonsul"); // NOI18N
        TtglMintaKonsul.setOpaque(false);
        TtglMintaKonsul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TtglMintaKonsulActionPerformed(evt);
            }
        });
        FormInput.add(TtglMintaKonsul);
        TtglMintaKonsul.setBounds(114, 94, 100, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Permintaan Konsul :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 122, 110, 23);

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        TketKlinis.setColumns(20);
        TketKlinis.setRows(5);
        TketKlinis.setName("TketKlinis"); // NOI18N
        TketKlinis.setPreferredSize(new java.awt.Dimension(170, 2000));
        TketKlinis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketKlinisKeyPressed(evt);
            }
        });
        Scroll7.setViewportView(TketKlinis);

        FormInput.add(Scroll7);
        Scroll7.setBounds(114, 122, 615, 266);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("/ Keterangan Klinis  ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 137, 110, 23);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setName("kdpoli"); // NOI18N
        FormInput.add(kdpoli);
        kdpoli.setBounds(114, 38, 66, 23);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setName("TPoli"); // NOI18N
        FormInput.add(TPoli);
        TPoli.setBounds(185, 38, 365, 23);

        BtnPoli.setForeground(new java.awt.Color(0, 0, 0));
        BtnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnPoli.setMnemonic('4');
        BtnPoli.setToolTipText("ALt+4");
        BtnPoli.setName("BtnPoli"); // NOI18N
        BtnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPoliActionPerformed(evt);
            }
        });
        FormInput.add(BtnPoli);
        BtnPoli.setBounds(550, 38, 28, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Ket. : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(215, 94, 40, 23);

        ketHari.setForeground(new java.awt.Color(0, 0, 0));
        ketHari.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ketHari.setText("-");
        ketHari.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ketHari.setName("ketHari"); // NOI18N
        FormInput.add(ketHari);
        ketHari.setBounds(258, 94, 530, 23);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

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

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Konsultasi :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(88, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-01-2025" }));
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

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-01-2025" }));
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

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKonsul.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbKonsul.setName("tbKonsul"); // NOI18N
        tbKonsul.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKonsulMouseClicked(evt);
            }
        });
        tbKonsul.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKonsulKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbKonsul);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (kdpoli.getText().trim().equals("-")) {
            Valid.textKosong(kdpoli, "Poliklinik Tujuan");
            BtnPoli.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from hari_libur where tgl_libur='" + Valid.SetTgl(TtglMintaKonsul.getSelectedItem() + "") + "'") > 0) {
            JOptionPane.showMessageDialog(rootPane, "Pelayanan rawat jalan poliklinik TUTUP, karena sedang/memperingati "
                    + Sequel.cariIsi("select keterangan from hari_libur where tgl_libur='" + Valid.SetTgl(TtglMintaKonsul.getSelectedItem() + "") + "'")
                    + ", silahkan ganti hari lain utk. tgl. rencana konsulnya");
        } else {
            if (Sequel.menyimpantf("surat_konsul_unit_ralan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 19, new String[]{
                TNoRw.getText(), Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + TNoRw.getText() + "' and status_lanjut='Ralan'"), 
                cmbJenisKonsul.getSelectedItem().toString(), cmbTujuanKonsul.getSelectedItem().toString(), TketLain.getText(), 
                Valid.SetTgl(TtglMintaKonsul.getSelectedItem() + ""), Sequel.cariIsi("select time(now())"), TketKlinis.getText(),
                "Belum", "-", kdpoli.getText(), "", "", "tidak", "0000-00-00", "0000-00-00", "00:00:00", "-", Sequel.cariIsi("select now()")
            }) == true) {
                TCari.setText(TNoRw.getText());
                Valid.SetTgl(DTPCari2, Valid.SetTgl(TtglMintaKonsul.getSelectedItem() + ""));
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
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (kdpoli.getText().trim().equals("-")) {
            Valid.textKosong(kdpoli, "Poliklinik Tujuan");
            BtnPoli.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from hari_libur where tgl_libur='" + Valid.SetTgl(TtglMintaKonsul.getSelectedItem() + "") + "'") > 0) {
            JOptionPane.showMessageDialog(rootPane, "Pelayanan rawat jalan poliklinik TUTUP, karena sedang/memperingati "
                    + Sequel.cariIsi("select keterangan from hari_libur where tgl_libur='" + Valid.SetTgl(TtglMintaKonsul.getSelectedItem() + "") + "'")
                    + ", silahkan ganti hari lain utk. tgl. rencana konsulnya");
        } else {
            if (Sequel.mengedittf("surat_konsul_unit_ralan", "waktu_simpan=?", "jenis_konsul=?, tujuan=?, "
                    + "ket_tujuan_lain=?, tgl_permintaan_konsul=?, jam_permintaan_konsul=?, keterangan_klinis=?, kd_poli_pembalas=?", 8, new String[]{
                        cmbJenisKonsul.getSelectedItem().toString(), cmbTujuanKonsul.getSelectedItem().toString(),
                        TketLain.getText(), Valid.SetTgl(TtglMintaKonsul.getSelectedItem() + ""), Sequel.cariIsi("select time(now())"), 
                        TketKlinis.getText(), kdpoli.getText(),
                        tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 0).toString()
                    }) == true) {

                TCari.setText(TNoRw.getText());
                Valid.SetTgl(DTPCari2, Valid.SetTgl(TtglMintaKonsul.getSelectedItem() + ""));
                tampil();
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnGantiActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

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

    private void tbKonsulMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKonsulMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbKonsulMouseClicked

    private void tbKonsulKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKonsulKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbKonsulKeyPressed

    private void TketKlinisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketKlinisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_TketKlinisKeyPressed

    private void BtnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoliActionPerformed
        TPoli.setText("");
        kdpoli.setText("");
        akses.setform("DlgSuratKonsulRalan");
        poli.isCek();
        poli.setSize(1074, 662);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
        poli.emptTeks();
    }//GEN-LAST:event_BtnPoliActionPerformed

    private void TtglMintaKonsulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TtglMintaKonsulActionPerformed
        cekHariLibur();
    }//GEN-LAST:event_TtglMintaKonsulActionPerformed

    private void cmbTujuanKonsulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTujuanKonsulActionPerformed
        TketLain.setText("");
        if (cmbTujuanKonsul.getSelectedIndex() == 4) {            
            TketLain.setEnabled(true);
            TketLain.requestFocus();
        } else {
            TketLain.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTujuanKonsulActionPerformed

    private void TketLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TketKlinis.requestFocus();
        }
    }//GEN-LAST:event_TketLainKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbKonsul.getSelectedRow() > -1) {
            if (Sequel.cariInteger("select count(-1) from surat_konsul_unit_ralan where "
                    + "waktu_simpan='" + tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 0).toString() + "' and status_jawaban='Sudah'") == 0) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from surat_konsul_unit_ralan where waktu_simpan=?", 1, new String[]{
                        tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 0).toString()
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
                JOptionPane.showMessageDialog(rootPane, "Maaf, surat konsultasi atas nama pasien ini sudah terjawab..!!");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnGanti);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbKonsul.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            
            param.put("norawat", TNoRw.getText());
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tglLahir", Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + TNoRM.getText() + "'")));
            param.put("jenkel", Sequel.cariIsi("select if(jk='L','Laki-laki','Perempuan') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            param.put("poliAwal", Sequel.cariIsi("select pl.nm_poli from reg_periksa rp "
                    + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + TNoRw.getText() + "'"));
            param.put("dokterPengonsul", Sequel.cariIsi("select d.nm_dokter from reg_periksa rp "
                    + "inner join dokter d on d.kd_dokter=rp.kd_dokter where rp.no_rawat='" + TNoRw.getText() + "'"));
            param.put("tglKunjungan", Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'")));
            param.put("poliTujuan", TPoli.getText());
            param.put("jenisKonsul", cmbJenisKonsul.getSelectedItem().toString());
            param.put("dokterPenjawab", dokterpenjawab);
            
            if (cmbTujuanKonsul.getSelectedIndex() == 4) {
                param.put("tujuanKonsul", cmbTujuanKonsul.getSelectedItem().toString() + " (" + TketLain.getText() + ")");
            } else {
                param.put("tujuanKonsul", cmbTujuanKonsul.getSelectedItem().toString());
            }
            
            param.put("tglKonsul", Valid.SetTglINDONESIA(Valid.SetTgl(TtglMintaKonsul.getSelectedItem() + "")));
            param.put("ketKlinis", TketKlinis.getText() + "\n");
            param.put("jawabanKonsul", "Ditemukan kasus : " + kasus + "\n\n" + ketklinis + "\n");
            
            if (tgljawaban.equals("0000-00-00")) {
                param.put("tglJawaban", Valid.SetTglINDONESIA(Sequel.cariIsi("select date(now())")));
            } else {
                param.put("tglJawaban", Valid.SetTglINDONESIA(tgljawaban));
            }
            
            if (tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 19).toString().equals("tidak")) {
                param.put("tglKonsulUlang", "-");
            } else {
                param.put("tglKonsulUlang", Valid.SetTglINDONESIA(tglkonsululang));
            }
            Valid.MyReport("rptCetakSuratKonsulRalan.jasper", "report", "::[ Surat Konsultasi Internal Poliklinik ]::",
                "SELECT now() tanggal", param);

            emptTeks();
            tampil();
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
            tbKonsul.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgSuratKonsulRalan dialog = new DlgSuratKonsulRalan(new javax.swing.JFrame(), true);
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
    public widget.Button BtnPoli;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll7;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPoli;
    private widget.TextArea TketKlinis;
    private widget.TextBox TketLain;
    private widget.Tanggal TtglMintaKonsul;
    private widget.ComboBox cmbJenisKonsul;
    private widget.ComboBox cmbTujuanKonsul;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox kdpoli;
    private widget.Label ketHari;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbKonsul;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select sk.*, p.no_rkm_medis, p.nm_pasien, pl1.nm_poli poliAwal, d.nm_dokter, "
                    + "pl2.nm_poli poliTujuan, DATE_FORMAT(sk.tgl_permintaan_konsul,'%d-%m-%Y') tglKonsul from surat_konsul_unit_ralan sk "
                    + "inner join reg_periksa rp on rp.no_rawat=sk.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join poliklinik pl1 on pl1.kd_poli=sk.kd_poli inner join poliklinik pl2 on pl2.kd_poli=sk.kd_poli_pembalas "
                    + "inner join dokter d on d.kd_dokter=sk.kd_dokter_pembalas where "
                    + "sk.tgl_permintaan_konsul between ? and ? and sk.no_rawat like ? or "
                    + "sk.tgl_permintaan_konsul between ? and ? and p.no_rkm_medis like ? or "
                    + "sk.tgl_permintaan_konsul between ? and ? and p.nm_pasien like ? or "
                    + "sk.tgl_permintaan_konsul between ? and ? and pl1.nm_poli like ? or "
                    + "sk.tgl_permintaan_konsul between ? and ? and pl2.nm_poli like ? or "
                    + "sk.tgl_permintaan_konsul between ? and ? and sk.jenis_konsul like ? or "
                    + "sk.tgl_permintaan_konsul between ? and ? and sk.tujuan like ? or "
                    + "sk.tgl_permintaan_konsul between ? and ? and sk.ket_tujuan_lain like ? or "
                    + "sk.tgl_permintaan_konsul between ? and ? and sk.keterangan_klinis like ? ORDER BY sk.waktu_simpan desc");
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
                ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText() + "%");                
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText() + "%");                
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText() + "%");
                ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText() + "%");
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{                        
                        rs.getString("waktu_simpan"),
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("poliAwal"),
                        rs.getString("poliTujuan"),
                        rs.getString("jenis_konsul"),
                        rs.getString("tujuan"),
                        rs.getString("ket_tujuan_lain"),
                        rs.getString("tglKonsul"),
                        rs.getString("keterangan_klinis"),
                        rs.getString("status_jawaban"),
                        rs.getString("tgl_permintaan_konsul"),
                        rs.getString("kd_poli"),                        
                        rs.getString("kasus_ditemukan"),
                        rs.getString("ket_klinis_jawaban"),
                        rs.getString("tgl_menjawab"),
                        rs.getString("tgl_konsul_ulang"),
                        rs.getString("nm_dokter"),
                        rs.getString("konsul_ulang"),
                        rs.getString("kd_poli_pembalas")
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
        kdpoli.setText("-");
        TPoli.setText("-");
        BtnPoli.requestFocus();
        cmbJenisKonsul.setSelectedIndex(0);
        cmbTujuanKonsul.setSelectedIndex(0);
        TketLain.setText("");
        TketLain.setEnabled(false);
        TtglMintaKonsul.setDate(new Date());
        TketKlinis.setText("");
        cekHariLibur();
    }

    private void getData() {
        kasus = "";
        ketklinis = "";
        tgljawaban = "";
        tglkonsululang = "";
        dokterpenjawab = "";

        if (tbKonsul.getSelectedRow() != -1) {
            TNoRw.setText(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 1).toString());
            TNoRM.setText(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 2).toString());
            TPasien.setText(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 3).toString());
            kdpoli.setText(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 20).toString());
            TPoli.setText(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 5).toString());
            cmbJenisKonsul.setSelectedItem(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 6).toString());
            cmbTujuanKonsul.setSelectedItem(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 7).toString());
            TketLain.setText(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 8).toString());
            Valid.SetTgl(TtglMintaKonsul, tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 12).toString());
            TketKlinis.setText(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 10).toString());
            kasus = tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 14).toString();
            ketklinis = tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 15).toString();
            dokterpenjawab = tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 18).toString();
            
            if (tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 16).toString().equals("")) {
                tgljawaban = "0000-00-00";
            } else {
                tgljawaban = tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 16).toString();
            }
            
            if (tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 19).toString().equals("tidak")) {
                tglkonsululang = "0000-00-00";
            } else {
                tglkonsululang = tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 17).toString();
            }
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.gettindakan_ralan());
        BtnGanti.setEnabled(akses.gettindakan_ralan());
        BtnHapus.setEnabled(akses.gettindakan_ralan());
    }
    
    private void cekHariLibur() {
        ketHari.setText("-");
        
        if (Sequel.cariIsi("select ifnull(tgl_libur,'') from hari_libur where tgl_libur='" + Valid.SetTgl(TtglMintaKonsul.getSelectedItem() + "") + "'").equals("")) {
            ketHari.setText("Kalender/penanggalan/hari normal seperti biasa.");
            ketHari.setForeground(Color.BLACK);
        } else {
            ketHari.setText(Sequel.cariIsi("select keterangan from hari_libur where tgl_libur='" + Valid.SetTgl(TtglMintaKonsul.getSelectedItem() + "") + "'"));
            ketHari.setForeground(Color.RED);
        }
    }
    
    public void setNoRm(String norw, String norm, String namapasien) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(namapasien);
        
        if (Sequel.cariInteger("select count(-1) from surat_konsul_unit_ralan where no_rawat='" + TNoRw.getText() + "'") > 0) {
            Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
            Valid.SetTgl(DTPCari2, Sequel.cariIsi("select tgl_permintaan_konsul from surat_konsul_unit_ralan where no_rawat='" + TNoRw.getText() + "'"));
        }
    }
}
