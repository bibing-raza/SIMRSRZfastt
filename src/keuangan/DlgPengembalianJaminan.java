package keuangan;

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
import kepegawaian.DlgCariPetugas;

/**
 *
 * @author dosen
 */
public class DlgPengembalianJaminan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private int i = 0, x = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String jumlahNomKem = "", jumlahNomTer = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgPengembalianJaminan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Ruang Perawatan/Inst.", "Jenis Jaminan", "Nama Pemberi Jaminan", "No. Telp.", "Tgl. Terima", "Petugas Menerima",
            "Keterangan", "Jml. Nominal", "Tgl. Dikembalikan", "Petugas Mengembalikan", "Telah Terima Dari",
            "tgl_terima", "nip_penerima", "jumlah_nominal", "tgl_dikembalikan", "nip_mengembalikan", "jaminan_batal", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPengembalian.setModel(tabMode);
        tbPengembalian.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPengembalian.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 21; i++) {
            TableColumn column = tbPengembalian.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(230);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(110);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(110);
            } else if (i == 12) {
                column.setPreferredWidth(220);
            } else if (i == 13) {
                column.setPreferredWidth(220);
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
        tbPengembalian.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPengembalian.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPengembalian.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPengembalian.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbPengembalian.getColumnModel().getColumn(11).setCellRenderer(centerRenderer);
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Ruang Perawatan/Inst.", "Jenis Jaminan", "Nama Pemberi Jaminan", "No. Telp.", "Tgl. Terima", "Petugas Menerima",
            "Keterangan", "Jml. Nominal", 
            "tgl_terima", "nip_penerima", "jumlah_nominal", "Jaminan Batal", "tglTer"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbJaminan.setModel(tabMode1);
        tbJaminan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbJaminan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbJaminan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(230);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(110);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(80);
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
                column.setPreferredWidth(80);
            } else if (i == 15) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbJaminan.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbJaminan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbJaminan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbJaminan.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbJaminan.getColumnModel().getColumn(13).setCellRenderer(centerRenderer);

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TnmTelah.setDocument(new batasInput((int) 150).getKata(TnmTelah));
        
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampilDikembalikan();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampilDikembalikan();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampilDikembalikan();
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
                    TnipMengembalikan.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                    TnmMengembalikan.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    btnPetugas.requestFocus();
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
        jLabel11 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel65 = new widget.Label();
        jLabel66 = new widget.Label();
        TnmPemberi = new widget.TextBox();
        jLabel67 = new widget.Label();
        TnoTelp = new widget.TextBox();
        jLabel68 = new widget.Label();
        TnmMenerima = new widget.TextBox();
        TnipMenerima = new widget.TextBox();
        jLabel69 = new widget.Label();
        jLabel70 = new widget.Label();
        TjnsJaminan = new widget.TextBox();
        TtglTerima = new widget.TextBox();
        scrollPane9 = new widget.ScrollPane();
        Tketerangan = new widget.TextArea();
        jLabel71 = new widget.Label();
        labelNominal = new widget.Label();
        jLabel72 = new widget.Label();
        TtglDikembalikan = new widget.Tanggal();
        jLabel73 = new widget.Label();
        TnipMengembalikan = new widget.TextBox();
        TnmMengembalikan = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel74 = new widget.Label();
        TnmTelah = new widget.TextBox();
        internalFrame2 = new widget.InternalFrame();
        panelGlass12 = new widget.panelisi();
        Scroll = new widget.ScrollPane();
        tbPengembalian = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel20 = new widget.Label();
        DTPa = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPb = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass9 = new widget.panelisi();
        Scroll1 = new widget.ScrollPane();
        tbJaminan = new widget.Table();
        panelGlass11 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel8 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        jLabel10 = new widget.Label();
        LCount1 = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pengembalian Jaminan Transaksi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 292));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("No. Rawat :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 10, 150, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(155, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(290, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(364, 10, 410, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat/Inst. :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 38, 150, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(155, 38, 400, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Jenis Jaminan :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(560, 38, 90, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Nama Pemberi Jaminan :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 66, 150, 23);

        TnmPemberi.setEditable(false);
        TnmPemberi.setForeground(new java.awt.Color(0, 0, 0));
        TnmPemberi.setName("TnmPemberi"); // NOI18N
        FormInput.add(TnmPemberi);
        TnmPemberi.setBounds(155, 66, 370, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("No. Telp./HP :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(530, 66, 80, 23);

        TnoTelp.setEditable(false);
        TnoTelp.setForeground(new java.awt.Color(0, 0, 0));
        TnoTelp.setName("TnoTelp"); // NOI18N
        FormInput.add(TnoTelp);
        TnoTelp.setBounds(614, 66, 160, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Tgl. Terima :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 94, 150, 23);

        TnmMenerima.setEditable(false);
        TnmMenerima.setForeground(new java.awt.Color(0, 0, 0));
        TnmMenerima.setName("TnmMenerima"); // NOI18N
        FormInput.add(TnmMenerima);
        TnmMenerima.setBounds(328, 122, 445, 23);

        TnipMenerima.setEditable(false);
        TnipMenerima.setForeground(new java.awt.Color(0, 0, 0));
        TnipMenerima.setName("TnipMenerima"); // NOI18N
        FormInput.add(TnipMenerima);
        TnipMenerima.setBounds(155, 122, 170, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Petugas Menerima :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 122, 150, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Keterangan :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 150, 150, 23);

        TjnsJaminan.setEditable(false);
        TjnsJaminan.setForeground(new java.awt.Color(0, 0, 0));
        TjnsJaminan.setName("TjnsJaminan"); // NOI18N
        FormInput.add(TjnsJaminan);
        TjnsJaminan.setBounds(654, 38, 120, 23);

        TtglTerima.setEditable(false);
        TtglTerima.setForeground(new java.awt.Color(0, 0, 0));
        TtglTerima.setName("TtglTerima"); // NOI18N
        FormInput.add(TtglTerima);
        TtglTerima.setBounds(155, 94, 190, 23);

        scrollPane9.setName("scrollPane9"); // NOI18N

        Tketerangan.setEditable(false);
        Tketerangan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tketerangan.setColumns(20);
        Tketerangan.setRows(5);
        Tketerangan.setName("Tketerangan"); // NOI18N
        Tketerangan.setPreferredSize(new java.awt.Dimension(162, 2000));
        scrollPane9.setViewportView(Tketerangan);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(158, 150, 615, 70);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Jumlah Nominal :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(345, 94, 100, 23);

        labelNominal.setForeground(new java.awt.Color(0, 0, 0));
        labelNominal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNominal.setText("0");
        labelNominal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelNominal.setName("labelNominal"); // NOI18N
        FormInput.add(labelNominal);
        labelNominal.setBounds(450, 94, 320, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Tgl. Dikembalikan :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 225, 150, 23);

        TtglDikembalikan.setEditable(false);
        TtglDikembalikan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-08-2025" }));
        TtglDikembalikan.setDisplayFormat("dd-MM-yyyy");
        TtglDikembalikan.setName("TtglDikembalikan"); // NOI18N
        TtglDikembalikan.setOpaque(false);
        TtglDikembalikan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglDikembalikan);
        TtglDikembalikan.setBounds(155, 225, 90, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Petugas Mengembalikan :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 253, 150, 23);

        TnipMengembalikan.setEditable(false);
        TnipMengembalikan.setForeground(new java.awt.Color(0, 0, 0));
        TnipMengembalikan.setName("TnipMengembalikan"); // NOI18N
        FormInput.add(TnipMengembalikan);
        TnipMengembalikan.setBounds(155, 253, 170, 23);

        TnmMengembalikan.setEditable(false);
        TnmMengembalikan.setForeground(new java.awt.Color(0, 0, 0));
        TnmMengembalikan.setName("TnmMengembalikan"); // NOI18N
        FormInput.add(TnmMengembalikan);
        TnmMengembalikan.setBounds(328, 253, 445, 23);

        btnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('1');
        btnPetugas.setToolTipText("Alt+1");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        FormInput.add(btnPetugas);
        btnPetugas.setBounds(775, 253, 28, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Nama Telah Terima Dari :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(247, 225, 140, 23);

        TnmTelah.setForeground(new java.awt.Color(0, 0, 0));
        TnmTelah.setName("TnmTelah"); // NOI18N
        TnmTelah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmTelahKeyPressed(evt);
            }
        });
        FormInput.add(TnmTelah);
        TnmTelah.setBounds(393, 225, 380, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.GridLayout(1, 2));

        panelGlass12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Pengembalian Jaminan ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPengembalian.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbPengembalian.setName("tbPengembalian"); // NOI18N
        tbPengembalian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPengembalianMouseClicked(evt);
            }
        });
        tbPengembalian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPengembalianKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPengembalian);

        panelGlass12.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Tgl. Dikembalikan :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass10.add(jLabel20);

        DTPa.setEditable(false);
        DTPa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-08-2025" }));
        DTPa.setDisplayFormat("dd-MM-yyyy");
        DTPa.setName("DTPa"); // NOI18N
        DTPa.setOpaque(false);
        DTPa.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPa);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d.");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel22);

        DTPb.setEditable(false);
        DTPb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-08-2025" }));
        DTPb.setDisplayFormat("dd-MM-yyyy");
        DTPb.setName("DTPb"); // NOI18N
        DTPb.setOpaque(false);
        DTPb.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPb);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(210, 23));
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

        panelGlass12.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        internalFrame2.add(panelGlass12);

        panelGlass9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Jaminan Transaksi Tersimpan ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass9.setLayout(new java.awt.BorderLayout());

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbJaminan.setToolTipText("Silahkan klik untuk memilih data yang akan dilakukan pengembalian");
        tbJaminan.setName("tbJaminan"); // NOI18N
        tbJaminan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbJaminanMouseClicked(evt);
            }
        });
        tbJaminan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbJaminanKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbJaminan);

        panelGlass9.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Terima :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass11.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-08-2025" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass11.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass11.add(jLabel21);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-08-2025" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass11.add(DTPCari2);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Key Word :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass11.add(jLabel8);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass11.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('2');
        BtnCari1.setText("Tampilkan Data");
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
        panelGlass11.add(BtnCari1);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass11.add(jLabel10);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass11.add(LCount1);

        panelGlass9.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        internalFrame2.add(panelGlass9);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 47));
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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jaminan transaksi yang telah diterima pada tabel...!!!!");
            tbJaminan.requestFocus();
        } else if (TnmTelah.getText().equals("")) {
            Valid.textKosong(TnmTelah, "Nama Telah Terima Dari");
            TnmTelah.requestFocus();
        } else if (TnipMengembalikan.getText().equals("") || TnipMengembalikan.getText().equals("-") || TnipMengembalikan.getText().equals("--")) {
            JOptionPane.showMessageDialog(null, "Petugas yang menngembalikan jaminan transaksi harus diisi dulu..!!");
            btnPetugas.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from jaminan_transaksi where no_rawat='" + TNoRw.getText() + "' and date(tgl_dikembalikan)<>'0000-00-00'") > 0) {
            JOptionPane.showMessageDialog(null, "Pengembalian jaminan transaksi pasien ini sudah tersimpan..!!");
            BtnBatalActionPerformed(null);
        } else {
            if (Sequel.mengedittf("jaminan_transaksi", "no_rawat=?", "tgl_dikembalikan=?, nip_mengembalikan=?, telah_terima=?", 4, new String[]{
                Valid.SetTgl(TtglDikembalikan.getSelectedItem() + "") + " " + Sequel.cariIsi("select time(now())"), TnipMengembalikan.getText(), TnmTelah.getText(),
                TNoRw.getText()
            }) == true) {
                BtnBatalActionPerformed(null);
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
        tampilDikembalikan();
        tampilTerima();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (tbPengembalian.getSelectedRow() > -1) {
            if (TNoRw.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jaminan transaksi yang telah diterima pada tabel...!!!!");
                tbJaminan.requestFocus();
            } else if (TnmTelah.getText().equals("")) {
                Valid.textKosong(TnmTelah, "Nama Telah Terima Dari");
                TnmTelah.requestFocus();
            } else if (TnipMengembalikan.getText().equals("") || TnipMengembalikan.getText().equals("-") || TnipMengembalikan.getText().equals("--")) {
                JOptionPane.showMessageDialog(null, "Petugas yang menngembalikan jaminan transaksi harus diisi dulu..!!");
                btnPetugas.requestFocus();
            } else {
                if (Sequel.mengedittf("jaminan_transaksi", "no_rawat=?", "tgl_dikembalikan=?, nip_mengembalikan=?, telah_terima=?", 4, new String[]{
                    Valid.SetTgl(TtglDikembalikan.getSelectedItem() + "") + " " + Sequel.cariIsi("select time(now())"), TnipMengembalikan.getText(), TnmTelah.getText(),
                    TNoRw.getText()
                }) == true) {
                    BtnBatalActionPerformed(null);
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel pengembalian jaminan..!!");
            BtnBatalActionPerformed(null);
            tbJaminan.requestFocus();
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
        tampilDikembalikan();
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

    private void tbPengembalianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPengembalianMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getDataKembali();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPengembalianMouseClicked

    private void tbPengembalianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPengembalianKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataKembali();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPengembalianKeyPressed

    private void tbJaminanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbJaminanMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataTerima();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbJaminanMouseClicked

    private void tbJaminanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbJaminanKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataTerima();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbJaminanKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilTerima();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        akses.setform("DlgPengembalianJaminan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void TnmTelahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmTelahKeyPressed
        Valid.pindah(evt, TtglDikembalikan, btnPetugas);
    }//GEN-LAST:event_TnmTelahKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampilDikembalikan();
        tampilTerima();
    }//GEN-LAST:event_formWindowOpened

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbPengembalian.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data pengembalian jaminan transaksi pasien ini akan dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.mengedittf("jaminan_transaksi", "no_rawat=?", "tgl_dikembalikan=?, nip_mengembalikan=?, telah_terima=?", 4, new String[]{
                    "0000-00-00 00:00:00", "-", "-",
                    TNoRw.getText()
                }) == true) {
                    BtnBatalActionPerformed(null);
                }
            } else {
                BtnBatalActionPerformed(null);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel pengembalian jaminan..!!");
            BtnBatalActionPerformed(null);
            tbJaminan.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (Sequel.cariInteger("select count(-1) from jaminan_transaksi where "
                + "date(tgl_dikembalikan) BETWEEN '" + Valid.SetTgl(DTPa.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPb.getSelectedItem() + "") + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data pengembalian jaminan transaksi tidak ditemukan..!!!!");
            BtnBatalActionPerformed(null);
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("emailrs", akses.getemailrs());
            param.put("periode", "PERIODE TANGGAL " + DTPa.getSelectedItem() + " S.D " + DTPb.getSelectedItem());
            param.put("judul", "LAPORAN JAMINAN TRANSAKSI YANG DIKEMBALIKAN");
            Valid.MyReport("rptLaporanJaminanDikembalikan.jasper", "report", "::[ Laporan Pengembalian Jaminan Transaksi ]::",
                    "SELECT jt.*, p.no_rkm_medis, p.nm_pasien, date_format(jt.tgl_terima,'%d-%m-%Y\n%H:%i Wita') tglTerima, pg1.nama petugasMenerima, "
                    + "if(jt.jumlah_nominal='0','-',format(jt.jumlah_nominal,0)) jmlNominal, date_format(jt.tgl_dikembalikan,'%d-%m-%Y\n%H:%i Wita') tglDikembalikan, "
                    + "pg2.nama petugasMengembalikan FROM jaminan_transaksi jt inner join reg_periksa rp on rp.no_rawat =jt.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis =rp.no_rkm_medis inner join pegawai pg1 on pg1.nik=jt.nip_penerima inner join pegawai pg2 on pg2.nik=jt.nip_mengembalikan where "
                    + "date(jt.tgl_dikembalikan) between '" + Valid.SetTgl(DTPa.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPb.getSelectedItem() + "") + "' "
                    + "order by jt.tgl_dikembalikan", param);

            BtnBatalActionPerformed(null);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPengembalianJaminan dialog = new DlgPengembalianJaminan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPa;
    private widget.Tanggal DTPb;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    public widget.TextBox TCari;
    public widget.TextBox TCari1;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TjnsJaminan;
    private widget.TextArea Tketerangan;
    private widget.TextBox TnipMenerima;
    private widget.TextBox TnipMengembalikan;
    private widget.TextBox TnmMenerima;
    private widget.TextBox TnmMengembalikan;
    private widget.TextBox TnmPemberi;
    private widget.TextBox TnmTelah;
    private widget.TextBox TnoTelp;
    private widget.TextBox TrgRawat;
    private widget.Tanggal TtglDikembalikan;
    private widget.TextBox TtglTerima;
    private widget.Button btnPetugas;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel8;
    private widget.Label labelNominal;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbJaminan;
    private widget.Table tbPengembalian;
    // End of variables declaration//GEN-END:variables

    public void emptTeks() {
        TNoRw.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        TrgRawat.setText("");
        TjnsJaminan.setText("");
        TnmPemberi.setText("");
        TnoTelp.setText("");
        TtglTerima.setText("");
        TnipMenerima.setText("");
        TnmMenerima.setText("");
        Tketerangan.setText("");        
        labelNominal.setText("Rp. 0");
        TtglDikembalikan.setDate(new Date());
        TnmTelah.setText("");
        TnipMengembalikan.setText("-");
        TnmMengembalikan.setText("-");
        TtglDikembalikan.requestFocus();
    }

    private void getDataKembali() {
        if (tbPengembalian.getSelectedRow() != -1) {            
            TNoRw.setText(tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 0).toString());
            TNoRM.setText(tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 1).toString());
            TPasien.setText(tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 3).toString());
            TjnsJaminan.setText(tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 4).toString());
            TnmPemberi.setText(tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 5).toString());
            TnoTelp.setText(tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 6).toString());
            TtglTerima.setText(Valid.SetTglINDONESIA(tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 14).toString()));
            TnipMenerima.setText(tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 15).toString());
            TnmMenerima.setText(tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 8).toString());
            Tketerangan.setText(tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 9).toString());
            Valid.SetTgl(TtglDikembalikan, tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 17).toString());
            TnmTelah.setText(tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 13).toString());
            TnipMengembalikan.setText(tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 18).toString());
            TnmMengembalikan.setText(tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 12).toString());
            
            if (tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 16).toString().equals("0")) {
                labelNominal.setText("Rp. 0");
            } else {
                labelNominal.setText("Rp. " + Valid.SetAngka(Double.parseDouble(tbPengembalian.getValueAt(tbPengembalian.getSelectedRow(), 16).toString())));
            }
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getbilling_ranap());
        BtnGanti.setEnabled(akses.getbilling_ranap());
        BtnHapus.setEnabled(akses.getbilling_ranap());
        
        if (akses.getadmin() == true) {
            TnipMengembalikan.setText("-");
            TnmMengembalikan.setText("-");
        } else {
            TnipMengembalikan.setText(akses.getkode());
            TnmMengembalikan.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipMengembalikan.getText() + "'"));
        }
    }
    
    private void tampilTerima() {
        Valid.tabelKosong(tabMode1);
        jumlahNomTer = "";
        try {
            ps1 = koneksi.prepareStatement("SELECT jt.*, p.no_rkm_medis, p.nm_pasien, date_format(jt.tgl_terima,'%d-%m-%Y, %H:%i') tglTerima, pg.nama petugasMenerima, "
                    + "format(jt.jumlah_nominal,0) jmlNominal, date_format(jt.tgl_dikembalikan,'%d-%m-%Y, %H:%i') tglDikembalikan, date(jt.tgl_terima) tglTer "
                    + "FROM jaminan_transaksi jt inner join reg_periksa rp on rp.no_rawat =jt.no_rawat inner join pasien p on p.no_rkm_medis =rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=jt.nip_penerima where "
                    + "date(jt.tgl_terima) between ? and ? and jt.jaminan_batal='Tidak' and jt.no_rawat like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.jaminan_batal='Tidak' and p.no_rkm_medis like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.jaminan_batal='Tidak' and p.nm_pasien like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.jaminan_batal='Tidak' and jt.ruang_rawat like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.jaminan_batal='Tidak' and jt.nm_pemberi_jaminan like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.jaminan_batal='Tidak' and jt.telah_terima like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.jaminan_batal='Tidak' and jt.jenis_jaminan like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.jaminan_batal='Tidak' and jt.keterangan like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.jaminan_batal='Tidak' and pg.nama like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.jaminan_batal='Tidak' and jt.jaminan_batal like ? or "
                    + "date(jt.tgl_terima) between ? and ? and jt.jaminan_batal='Tidak' and jt.jumlah_nominal like ? order by jt.tgl_terima");
            try {
                ps1.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps1.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps1.setString(3, "%" + TCari1.getText().trim() + "%");
                ps1.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps1.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps1.setString(6, "%" + TCari1.getText().trim() + "%");
                ps1.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps1.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps1.setString(9, "%" + TCari1.getText().trim() + "%");
                ps1.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps1.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps1.setString(12, "%" + TCari1.getText().trim() + "%");
                ps1.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps1.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps1.setString(15, "%" + TCari1.getText().trim() + "%");
                ps1.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps1.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps1.setString(18, "%" + TCari1.getText().trim() + "%");
                ps1.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps1.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps1.setString(21, "%" + TCari1.getText().trim() + "%");
                ps1.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps1.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps1.setString(24, "%" + TCari1.getText().trim() + "%");
                ps1.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps1.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps1.setString(27, "%" + TCari1.getText().trim() + "%");
                ps1.setString(28, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps1.setString(29, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps1.setString(30, "%" + TCari1.getText().trim() + "%");
                ps1.setString(31, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps1.setString(32, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps1.setString(33, "%" + TCari1.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    if (rs1.getString("jmlNominal").equals("0")) {
                        jumlahNomTer = "-";
                    } else {
                        jumlahNomTer = rs1.getString("jmlNominal");
                    }

                    tabMode1.addRow(new String[]{
                        rs1.getString("no_rawat"),
                        rs1.getString("no_rkm_medis"),
                        rs1.getString("nm_pasien"),
                        rs1.getString("ruang_rawat"),
                        rs1.getString("jenis_jaminan"),
                        rs1.getString("nm_pemberi_jaminan"),
                        rs1.getString("no_telp"),
                        rs1.getString("tglTerima"),
                        rs1.getString("petugasMenerima"),
                        rs1.getString("keterangan"),
                        jumlahNomTer,
                        rs1.getString("tgl_terima"),
                        rs1.getString("nip_penerima"),
                        rs1.getString("jumlah_nominal"),
                        rs1.getString("jaminan_batal"),
                        rs1.getString("tglTer")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampil() : " + e);
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
        LCount1.setText("" + tabMode1.getRowCount());
    }
    
    private void getDataTerima() {
        TnmTelah.setText("");
        if (tbJaminan.getSelectedRow() != -1) {
            TNoRw.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 0).toString());
            TNoRM.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 1).toString());
            TPasien.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 3).toString());
            TjnsJaminan.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 4).toString());
            TnmPemberi.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 5).toString());
            TnoTelp.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 6).toString());
            TtglTerima.setText(Valid.SetTglINDONESIA(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 15).toString()));
            TnipMenerima.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 12).toString());
            TnmMenerima.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 8).toString());
            Tketerangan.setText(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 9).toString());

            if (tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 13).toString().equals("0")) {
                labelNominal.setText("Rp. 0");
            } else {
                labelNominal.setText("Rp. " + Valid.SetAngka(Double.parseDouble(tbJaminan.getValueAt(tbJaminan.getSelectedRow(), 13).toString())));
            }
        }
    }

    private void tampilDikembalikan() {
        Valid.tabelKosong(tabMode);
        jumlahNomKem = "";
        try {
            ps = koneksi.prepareStatement("SELECT jt.*, p.no_rkm_medis, p.nm_pasien, date_format(jt.tgl_terima,'%d-%m-%Y, %H:%i') tglTerima, pg1.nama petugasMenerima, "
                    + "format(jt.jumlah_nominal,0) jmlNominal, date_format(jt.tgl_dikembalikan,'%d-%m-%Y, %H:%i') tglDikembalikan, pg2.nama petugasMengembalikan, "
                    + "date_format(jt.tgl_dikembalikan,'%d-%m-%Y, %H:%i') tglKembali, date(jt.tgl_dikembalikan) tglkembalikan "
                    + "FROM jaminan_transaksi jt inner join reg_periksa rp on rp.no_rawat =jt.no_rawat inner join pasien p on p.no_rkm_medis =rp.no_rkm_medis "
                    + "inner join pegawai pg1 on pg1.nik=jt.nip_penerima inner join pegawai pg2 on pg2.nik=jt.nip_mengembalikan where "
                    + "date(jt.tgl_dikembalikan) between ? and ? and jt.jaminan_batal='Tidak' and jt.no_rawat like ? or "
                    + "date(jt.tgl_dikembalikan) between ? and ? and jt.jaminan_batal='Tidak' and p.no_rkm_medis like ? or "
                    + "date(jt.tgl_dikembalikan) between ? and ? and jt.jaminan_batal='Tidak' and p.nm_pasien like ? or "
                    + "date(jt.tgl_dikembalikan) between ? and ? and jt.jaminan_batal='Tidak' and jt.ruang_rawat like ? or "
                    + "date(jt.tgl_dikembalikan) between ? and ? and jt.jaminan_batal='Tidak' and jt.nm_pemberi_jaminan like ? or "
                    + "date(jt.tgl_dikembalikan) between ? and ? and jt.jaminan_batal='Tidak' and jt.telah_terima like ? or "
                    + "date(jt.tgl_dikembalikan) between ? and ? and jt.jaminan_batal='Tidak' and jt.jenis_jaminan like ? or "
                    + "date(jt.tgl_dikembalikan) between ? and ? and jt.jaminan_batal='Tidak' and jt.keterangan like ? or "
                    + "date(jt.tgl_dikembalikan) between ? and ? and jt.jaminan_batal='Tidak' and pg1.nama like ? or "
                    + "date(jt.tgl_dikembalikan) between ? and ? and jt.jaminan_batal='Tidak' and pg2.nama like ? or "
                    + "date(jt.tgl_dikembalikan) between ? and ? and jt.jaminan_batal='Tidak' and jt.jaminan_batal like ? or "
                    + "date(jt.tgl_dikembalikan) between ? and ? and jt.jaminan_batal='Tidak' and jt.jumlah_nominal like ? order by jt.tgl_dikembalikan");
            try {
                ps.setString(1, Valid.SetTgl(DTPa.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPb.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(DTPa.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPb.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(DTPa.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPb.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(DTPa.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPb.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(DTPa.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(DTPb.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, Valid.SetTgl(DTPa.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(DTPb.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, Valid.SetTgl(DTPa.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPb.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, Valid.SetTgl(DTPa.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPb.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                ps.setString(25, Valid.SetTgl(DTPa.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(DTPb.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText().trim() + "%");
                ps.setString(28, Valid.SetTgl(DTPa.getSelectedItem() + ""));
                ps.setString(29, Valid.SetTgl(DTPb.getSelectedItem() + ""));
                ps.setString(30, "%" + TCari.getText().trim() + "%");
                ps.setString(31, Valid.SetTgl(DTPa.getSelectedItem() + ""));
                ps.setString(32, Valid.SetTgl(DTPb.getSelectedItem() + ""));
                ps.setString(33, "%" + TCari.getText().trim() + "%");
                ps.setString(34, Valid.SetTgl(DTPa.getSelectedItem() + ""));
                ps.setString(35, Valid.SetTgl(DTPb.getSelectedItem() + ""));
                ps.setString(36, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString("jmlNominal").equals("0")) {
                        jumlahNomKem = "-";
                    } else {
                        jumlahNomKem = rs.getString("jmlNominal");
                    }
                    
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("ruang_rawat"),
                        rs.getString("jenis_jaminan"),
                        rs.getString("nm_pemberi_jaminan"),
                        rs.getString("no_telp"),
                        rs.getString("tglTerima"),
                        rs.getString("petugasMenerima"),
                        rs.getString("keterangan"),
                        jumlahNomKem,
                        rs.getString("tglKembali"),
                        rs.getString("petugasMengembalikan"),
                        rs.getString("telah_terima"),                        
                        rs.getString("tgl_terima"),
                        rs.getString("nip_penerima"),
                        rs.getString("jumlah_nominal"),
                        rs.getString("tgl_dikembalikan"),
                        rs.getString("nip_mengembalikan"),
                        rs.getString("jaminan_batal"),
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
}
