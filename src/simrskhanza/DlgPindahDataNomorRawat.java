package simrskhanza;

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
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author dosen
 */
public class DlgPindahDataNomorRawat extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps1, ps2, psNom;
    private ResultSet rs1, rs2, rsNom;
    private int i = 0, x = 0, pilihan = 0;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgPindahDataNomorRawat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
            "No. Rawat", "Tgl. Tran./Data", "Jam Trans./Data", "Uraian Transaksi/Data"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbDipilih.setModel(tabMode);
        tbDipilih.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDipilih.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbDipilih.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(90);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(450);
            }
        }
        tbDipilih.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1=new DefaultTableModel(null,new Object[]{
            "No. Rawat", "Tgl. Tran./Data", "Jam Trans./Data", "Uraian Transaksi/Data"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbTujuan.setModel(tabMode1);
        tbTujuan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTujuan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbTujuan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(90);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(450);
            }
        }
        tbTujuan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2=new DefaultTableModel(null,new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Masuk", "Jam Masuk", "Tgl. Pulang", "Jam Pulang", "Ruang Rawat", "Status Pulang"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbNomor.setModel(tabMode2);
        tbNomor.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbNomor.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbNomor.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(220);
            } else if (i == 8) {
                column.setPreferredWidth(180);
            } 
        }
        tbNomor.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRwTujuan.setDocument(new batasInput((byte) 17).getKata(TNoRwTujuan));
        tglDari.setDocument(new batasInput((byte) 10).getKata(tglDari));
        pukulDari.setDocument(new batasInput((byte) 8).getKata(pukulDari));
        
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

        jPopupMenu = new javax.swing.JPopupMenu();
        MnBatalPindah = new javax.swing.JMenuItem();
        WindowNomorRawat = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbNomor = new widget.Table();
        panelisi4 = new widget.panelisi();
        jLabel21 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        BtnCari = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel63 = new widget.Label();
        TNoRwTerpilih = new widget.TextBox();
        pasienTerpilih = new widget.TextBox();
        jLabel64 = new widget.Label();
        TRgRawatTerpilih = new widget.TextBox();
        jLabel65 = new widget.Label();
        TNoRwTujuan = new widget.TextBox();
        jLabel66 = new widget.Label();
        TRgRawatTujuan = new widget.TextBox();
        jLabel67 = new widget.Label();
        ChkRMranap = new widget.CekBox();
        ChkRMigd = new widget.CekBox();
        jLabel108 = new widget.Label();
        cmbJnsTran = new widget.ComboBox();
        jLabel107 = new widget.Label();
        tglDari = new widget.TextBox();
        jLabel110 = new widget.Label();
        pukulDari = new widget.TextBox();
        ChkTglTran = new widget.CekBox();
        TNoRmTerpilih = new widget.TextBox();
        TNoRmTujuan = new widget.TextBox();
        btnNomor = new widget.Button();
        btnUlangi = new widget.Button();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbDipilih = new widget.Table();
        Scroll1 = new widget.ScrollPane();
        tbTujuan = new widget.Table();
        panelGlass8 = new widget.panelisi();
        BtnCekData = new widget.Button();
        BtnProsesData = new widget.Button();
        BtnKeluar = new widget.Button();

        jPopupMenu.setName("jPopupMenu"); // NOI18N

        MnBatalPindah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBatalPindah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBatalPindah.setText("Batal Pindah Data");
        MnBatalPindah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBatalPindah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBatalPindah.setIconTextGap(5);
        MnBatalPindah.setName("MnBatalPindah"); // NOI18N
        MnBatalPindah.setPreferredSize(new java.awt.Dimension(145, 26));
        MnBatalPindah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBatalPindahActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnBatalPindah);

        WindowNomorRawat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowNomorRawat.setName("WindowNomorRawat"); // NOI18N
        WindowNomorRawat.setUndecorated(true);
        WindowNomorRawat.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pilih Nomor Rawat Tujuan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame3.setLayout(new java.awt.BorderLayout());

        Scroll2.setBorder(null);
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbNomor.setToolTipText("");
        tbNomor.setName("tbNomor"); // NOI18N
        tbNomor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNomorMouseClicked(evt);
            }
        });
        tbNomor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbNomorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbNomorKeyReleased(evt);
            }
        });
        Scroll2.setViewportView(tbNomor);

        internalFrame3.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelisi4.setBackground(new java.awt.Color(255, 150, 255));
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 9));

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Tgl. Masuk :");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi4.add(jLabel21);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-06-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi4.add(DTPCari1);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(25, 23));
        panelisi4.add(jLabel22);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-06-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi4.add(DTPCari2);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('4');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+4");
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
        panelisi4.add(BtnCari);

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
        panelisi4.add(BtnCloseIn1);

        internalFrame3.add(panelisi4, java.awt.BorderLayout.PAGE_END);

        WindowNomorRawat.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pindah Data Ke Nomor Rawat Tujuan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 235));
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

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("No. Rawat Terpilih : ");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 10, 150, 23);

        TNoRwTerpilih.setEditable(false);
        TNoRwTerpilih.setForeground(new java.awt.Color(0, 0, 0));
        TNoRwTerpilih.setName("TNoRwTerpilih"); // NOI18N
        TNoRwTerpilih.setPreferredSize(new java.awt.Dimension(135, 23));
        FormInput.add(TNoRwTerpilih);
        TNoRwTerpilih.setBounds(152, 10, 125, 23);

        pasienTerpilih.setEditable(false);
        pasienTerpilih.setForeground(new java.awt.Color(0, 0, 0));
        pasienTerpilih.setHighlighter(null);
        pasienTerpilih.setName("pasienTerpilih"); // NOI18N
        pasienTerpilih.setPreferredSize(new java.awt.Dimension(135, 23));
        FormInput.add(pasienTerpilih);
        pasienTerpilih.setBounds(365, 10, 300, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Rg. Rawat Terpilih : ");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 38, 150, 23);

        TRgRawatTerpilih.setEditable(false);
        TRgRawatTerpilih.setForeground(new java.awt.Color(0, 0, 0));
        TRgRawatTerpilih.setName("TRgRawatTerpilih"); // NOI18N
        TRgRawatTerpilih.setPreferredSize(new java.awt.Dimension(135, 23));
        FormInput.add(TRgRawatTerpilih);
        TRgRawatTerpilih.setBounds(152, 38, 513, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("No. Rawat Tujuan : ");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 66, 150, 23);

        TNoRwTujuan.setEditable(false);
        TNoRwTujuan.setForeground(new java.awt.Color(0, 0, 0));
        TNoRwTujuan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TNoRwTujuan.setName("TNoRwTujuan"); // NOI18N
        TNoRwTujuan.setPreferredSize(new java.awt.Dimension(135, 23));
        FormInput.add(TNoRwTujuan);
        TNoRwTujuan.setBounds(152, 66, 180, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Rg. Rawat Tujuan : ");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 94, 150, 23);

        TRgRawatTujuan.setEditable(false);
        TRgRawatTujuan.setForeground(new java.awt.Color(0, 0, 0));
        TRgRawatTujuan.setName("TRgRawatTujuan"); // NOI18N
        TRgRawatTujuan.setPreferredSize(new java.awt.Dimension(135, 23));
        FormInput.add(TRgRawatTujuan);
        TRgRawatTujuan.setBounds(152, 94, 513, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("RME Yang Dipindah : ");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 122, 150, 23);

        ChkRMranap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkRMranap.setForeground(new java.awt.Color(0, 0, 0));
        ChkRMranap.setText("Semua Rekam Medis RAWAT INAP");
        ChkRMranap.setBorderPainted(true);
        ChkRMranap.setBorderPaintedFlat(true);
        ChkRMranap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkRMranap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkRMranap.setName("ChkRMranap"); // NOI18N
        ChkRMranap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkRMranapActionPerformed(evt);
            }
        });
        FormInput.add(ChkRMranap);
        ChkRMranap.setBounds(152, 122, 210, 23);

        ChkRMigd.setBorder(null);
        ChkRMigd.setForeground(new java.awt.Color(0, 0, 0));
        ChkRMigd.setText("Semua Rekam Medis IGD");
        ChkRMigd.setBorderPainted(true);
        ChkRMigd.setBorderPaintedFlat(true);
        ChkRMigd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkRMigd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkRMigd.setName("ChkRMigd"); // NOI18N
        ChkRMigd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkRMigdActionPerformed(evt);
            }
        });
        FormInput.add(ChkRMigd);
        ChkRMigd.setBounds(380, 122, 160, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("RME/Jenis Tran. Dipilih : ");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(0, 150, 150, 23);

        cmbJnsTran.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsTran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "FARMASI", "RADIOLOGI", "LABORATORIUM", "PENANGANAN DOKTER", "PENANGANAN PETUGAS", "PENANGANAN DOKTER & PETUGAS", "DIET HARIAN", "ASESMEN MEDIK DEWASA RAWAT INAP", "ASESMEN KEPERAWATAN DEWASA RAWAT INAP", "CPPT RAWAT INAP", "CPPT IGD", "CATATAN RESEP RAWAT INAP", "CATATAN RESEP IGD", "RINGKASAN PULANG RAWAT INAP", "TRANSFER SERAH TERIMA RAWAT INAP", "TRANSFER SERAH TERIMA IGD", "CATATAN TINDAKAN KEPERAWATAN RAWAT INAP", "JADWAL PEMBERIAN OBAT RAWAT INAP", "JADWAL PEMBERIAN OBAT IGD", "KONSUL ANTAR UNIT RAWAT INAP", "DATA PERSALINAN", "SPIROMETRI", "SURAT ISTIRAHAT SAKIT", "DATA PERSALINAN DINKES KABUPATEN", "SURAT KETERANGAN SAKIT", "DPJP RAWAT INAP", "PERMINTAAN PERIKSA LAB.", "PERMINTAAN PERIKSA RADIOLOGI", "PEMANTAUAN HARIAN PASIEN RAWAT INAP", "TRIASE IGD", "ASESMEN MEDIK IGD", "ASESMEN KEPERAWATAN IGD", "PROTOKOL KEMOTERAPI", "ASESMEN ULANG RESIKO JATUH DEWASA", "PENGELOLAAN TRANSFUSI DARAH", "MONITORING EWS DEWASA", "ASESMEN KEPERAWATAN ANAK RAWAT INAP", "ASESMEN MEDIK ANAK RAWAT INAP", "ASESMEN ULANG RESIKO JATUH ANAK", "MONITORING PEDIATRIC EWS", "ASESMEN RESTRAIN RAWAT INAP", "OBSERVASI RESTRAIN RAWAT INAP" }));
        cmbJnsTran.setName("cmbJnsTran"); // NOI18N
        cmbJnsTran.setPreferredSize(new java.awt.Dimension(115, 23));
        cmbJnsTran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJnsTranMouseReleased(evt);
            }
        });
        FormInput.add(cmbJnsTran);
        cmbJnsTran.setBounds(152, 150, 430, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("Tgl. Data Dipilih Dari : ");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(0, 178, 150, 23);

        tglDari.setEditable(false);
        tglDari.setForeground(new java.awt.Color(0, 0, 0));
        tglDari.setName("tglDari"); // NOI18N
        tglDari.setPreferredSize(new java.awt.Dimension(135, 23));
        FormInput.add(tglDari);
        tglDari.setBounds(152, 178, 80, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("Pukul : ");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(234, 178, 40, 23);

        pukulDari.setEditable(false);
        pukulDari.setForeground(new java.awt.Color(0, 0, 0));
        pukulDari.setName("pukulDari"); // NOI18N
        pukulDari.setPreferredSize(new java.awt.Dimension(135, 23));
        FormInput.add(pukulDari);
        pukulDari.setBounds(274, 178, 80, 23);

        ChkTglTran.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTglTran.setForeground(new java.awt.Color(0, 0, 0));
        ChkTglTran.setText("Dipilih");
        ChkTglTran.setBorderPainted(true);
        ChkTglTran.setBorderPaintedFlat(true);
        ChkTglTran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTglTran.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTglTran.setName("ChkTglTran"); // NOI18N
        ChkTglTran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkTglTranActionPerformed(evt);
            }
        });
        FormInput.add(ChkTglTran);
        ChkTglTran.setBounds(359, 178, 200, 23);

        TNoRmTerpilih.setEditable(false);
        TNoRmTerpilih.setForeground(new java.awt.Color(0, 0, 0));
        TNoRmTerpilih.setName("TNoRmTerpilih"); // NOI18N
        TNoRmTerpilih.setPreferredSize(new java.awt.Dimension(135, 23));
        FormInput.add(TNoRmTerpilih);
        TNoRmTerpilih.setBounds(280, 10, 83, 23);

        TNoRmTujuan.setEditable(false);
        TNoRmTujuan.setForeground(new java.awt.Color(0, 0, 0));
        TNoRmTujuan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TNoRmTujuan.setName("TNoRmTujuan"); // NOI18N
        TNoRmTujuan.setPreferredSize(new java.awt.Dimension(135, 23));
        FormInput.add(TNoRmTujuan);
        TNoRmTujuan.setBounds(335, 66, 90, 23);

        btnNomor.setForeground(new java.awt.Color(0, 0, 0));
        btnNomor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnNomor.setMnemonic('3');
        btnNomor.setToolTipText("Alt+3");
        btnNomor.setName("btnNomor"); // NOI18N
        btnNomor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNomorActionPerformed(evt);
            }
        });
        FormInput.add(btnNomor);
        btnNomor.setBounds(463, 66, 28, 23);

        btnUlangi.setForeground(new java.awt.Color(0, 0, 0));
        btnUlangi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        btnUlangi.setMnemonic('3');
        btnUlangi.setToolTipText("Alt+3");
        btnUlangi.setName("btnUlangi"); // NOI18N
        btnUlangi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUlangiActionPerformed(evt);
            }
        });
        FormInput.add(btnUlangi);
        btnUlangi.setBounds(426, 66, 28, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.GridLayout(1, 2));

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " [ Data Sesuai No. Rawat Dipilih ] ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbDipilih.setToolTipText("");
        tbDipilih.setName("tbDipilih"); // NOI18N
        tbDipilih.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDipilihMouseClicked(evt);
            }
        });
        tbDipilih.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDipilihKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbDipilih);

        internalFrame2.add(Scroll);

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " [ Data Ke No. Rawat Tujuan ] ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbTujuan.setToolTipText("");
        tbTujuan.setComponentPopupMenu(jPopupMenu);
        tbTujuan.setName("tbTujuan"); // NOI18N
        tbTujuan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTujuanMouseClicked(evt);
            }
        });
        tbTujuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTujuanKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbTujuan);

        internalFrame2.add(Scroll1);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 5));

        BtnCekData.setForeground(new java.awt.Color(0, 0, 0));
        BtnCekData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search.png"))); // NOI18N
        BtnCekData.setMnemonic('C');
        BtnCekData.setText("Cek Data");
        BtnCekData.setToolTipText("Alt+C");
        BtnCekData.setName("BtnCekData"); // NOI18N
        BtnCekData.setPreferredSize(new java.awt.Dimension(120, 30));
        BtnCekData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCekDataActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnCekData);

        BtnProsesData.setForeground(new java.awt.Color(0, 0, 0));
        BtnProsesData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnProsesData.setMnemonic('P');
        BtnProsesData.setText("Proses Pindah");
        BtnProsesData.setToolTipText("Alt+P");
        BtnProsesData.setName("BtnProsesData"); // NOI18N
        BtnProsesData.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnProsesData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProsesDataActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnProsesData);

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

    private void BtnProsesDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProsesDataActionPerformed
        if (TNoRwTujuan.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Nomor rawat tujuan harus terisi dengan benar...!!");
            TNoRwTujuan.requestFocus();
        } else if (TNoRwTujuan.getText().equals(TNoRwTerpilih.getText())) {
            JOptionPane.showMessageDialog(null, "Nomor rawat tujuan harus berbeda dengan nomor rawat terpilih...!!");
        } else {
            if (cmbJnsTran.isEnabled() == false) {
                pilihan = 1;
                if (ChkRMranap.isSelected() == true) {
                    x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin semua rekam medis rawat inap akan diproses..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        RMranapGantiNoRawat();
                        JOptionPane.showMessageDialog(null, "Proses pemindahan semua data rekam medis Rawat Inap BERHASIL dilakukan ke no. rawat " + TNoRwTujuan.getText() + "...!!");
                        ChkTglTran.setSelected(false);
                        ChkTglTran.setText("Semua Tgl. Transaksi");
                        tglDari.setText("");
                        pukulDari.setText("");
                        ChkRMranap.setSelected(false);
                        ChkRMigd.setSelected(false);
                        pilihan = 0;
                    }
                }
                
                if (ChkRMigd.isSelected() == true) {
                    x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin semua rekam medis IGD akan diproses..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        RMigdGantiNoRawat();
                        JOptionPane.showMessageDialog(null, "Proses pemindahan semua data rekam medis IGD BERHASIL dilakukan ke no. rawat " + TNoRwTujuan.getText() + "...!!");
                        ChkTglTran.setSelected(false);
                        ChkTglTran.setText("Semua Tgl. Transaksi");
                        tglDari.setText("");
                        pukulDari.setText("");
                        ChkRMranap.setSelected(false);
                        ChkRMigd.setSelected(false);
                        pilihan = 0;
                    }
                }

            } else {
                i = 0;
                i = Sequel.cariInteger("select count(billing.no_rawat) cek from billing where billing.no_rawat='" + TNoRwTujuan.getText() + "'");
                if (cmbJnsTran.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Pilih dulu salah satu jenis transaksi yang akan diproses...!!");
                    cmbJnsTran.requestFocus();
                } else if (i > 0) {
                    JOptionPane.showMessageDialog(null, "Untuk No. Rawat tujuan notanya sudah tersimpan, hapus dulu notanya dibilling pembayaran..!!!");
                } else if (tglDari.getText().equals("salah satu")) {
                    JOptionPane.showMessageDialog(null, "Tgl. Transaksi dan Pukul salah...!!");
                    cmbJnsTran.requestFocus();
                } else {
                    pilihan = 1;
                    pindahData();
                }
            }
        }
}//GEN-LAST:event_BtnProsesDataActionPerformed

    private void BtnCekDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCekDataActionPerformed
        if (TNoRwTerpilih.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Nomor rawat dipilih harus sudah terisi dengan benar...!!");
            BtnKeluar.requestFocus();
        } else if (TNoRwTujuan.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Nomor rawat tujuan harus terisi dengan benar...!!");
            TNoRwTujuan.requestFocus();
        } else {
            if (cmbJnsTran.getSelectedIndex() == 0) {
                ChkTglTran.setSelected(false);
                ChkTglTran.setText("Semua Tgl. Transaksi");
                tglDari.setText("");
                pukulDari.setText("");
                Valid.tabelKosong(tabMode);
                Valid.tabelKosong(tabMode1);
            } else {
                ChkTglTran.setSelected(false);
                ChkTglTran.setText("Semua Tgl. Transaksi");
                tglDari.setText("");
                pukulDari.setText("");
                tampil();
                tampilTujuan();
            }
        }
}//GEN-LAST:event_BtnCekDataActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowNomorRawat.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void ChkRMranapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkRMranapActionPerformed
        if (ChkRMranap.isSelected() == true || ChkRMigd.isSelected() == true
            || (ChkRMranap.isSelected() == true && ChkRMigd.isSelected() == true)) {
            cmbJnsTran.setEnabled(false);
            ChkTglTran.setEnabled(false);
            BtnCekData.setEnabled(false);
            cmbJnsTran.setSelectedIndex(0);
            tglDari.setText("");
            pukulDari.setText("");
            ChkTglTran.setSelected(false);
        } else {
            cmbJnsTran.setEnabled(true);
            ChkTglTran.setEnabled(true);
            BtnCekData.setEnabled(true);
        }
    }//GEN-LAST:event_ChkRMranapActionPerformed

    private void ChkRMigdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkRMigdActionPerformed
        if (ChkRMranap.isSelected() == true || ChkRMigd.isSelected() == true
            || (ChkRMranap.isSelected() == true && ChkRMigd.isSelected() == true)) {
            cmbJnsTran.setEnabled(false);
            ChkTglTran.setEnabled(false);
            BtnCekData.setEnabled(false);
            cmbJnsTran.setSelectedIndex(0);
            tglDari.setText("");
            pukulDari.setText("");
            ChkTglTran.setSelected(false);
        } else {
            cmbJnsTran.setEnabled(true);
            ChkTglTran.setEnabled(true);
            BtnCekData.setEnabled(true);
        }
    }//GEN-LAST:event_ChkRMigdActionPerformed

    private void ChkTglTranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTglTranActionPerformed
        if (ChkTglTran.isSelected() == true) {
            ChkTglTran.setText("Dipilih");
        } else if (ChkTglTran.isSelected() == false) {
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
        }
    }//GEN-LAST:event_ChkTglTranActionPerformed

    private void cmbJnsTranMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJnsTranMouseReleased
        AutoCompleteDecorator.decorate(cmbJnsTran);
    }//GEN-LAST:event_cmbJnsTranMouseReleased

    private void tbDipilihMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDipilihMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getdataPilih();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDipilihMouseClicked

    private void tbDipilihKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDipilihKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getdataPilih();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDipilihKeyPressed

    private void btnNomorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNomorActionPerformed
        if (TNoRwTerpilih.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu data pasien terlebih dahulu..!!");
        } else {
            WindowNomorRawat.setSize(963, 370);
            WindowNomorRawat.setLocationRelativeTo(internalFrame1);
            WindowNomorRawat.setVisible(true);
            
            Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRwTerpilih.getText() + "'"));
            DTPCari2.setDate(new Date());
            tampilNomor();
            DTPCari1.requestFocus();
        }
    }//GEN-LAST:event_btnNomorActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowNomorRawat.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampilNomor();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void tbNomorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNomorKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                TNoRwTujuan.setText(tbNomor.getValueAt(tbNomor.getSelectedRow(), 0).toString());
                TNoRmTujuan.setText(tbNomor.getValueAt(tbNomor.getSelectedRow(), 1).toString());
                TRgRawatTujuan.setText(tbNomor.getValueAt(tbNomor.getSelectedRow(), 7).toString());
                WindowNomorRawat.dispose();
            }
        }
    }//GEN-LAST:event_tbNomorKeyPressed

    private void tbNomorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNomorKeyReleased
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    TNoRwTujuan.setText(tbNomor.getValueAt(tbNomor.getSelectedRow(), 0).toString());
                    TNoRmTujuan.setText(tbNomor.getValueAt(tbNomor.getSelectedRow(), 1).toString());
                    TRgRawatTujuan.setText(tbNomor.getValueAt(tbNomor.getSelectedRow(), 7).toString());
                    WindowNomorRawat.dispose();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbNomorKeyReleased

    private void tbNomorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNomorMouseClicked
        if (tabMode2.getRowCount() != 0) {
            if (evt.getClickCount() == 2) {
                try {
                    TNoRwTujuan.setText(tbNomor.getValueAt(tbNomor.getSelectedRow(), 0).toString());
                    TNoRmTujuan.setText(tbNomor.getValueAt(tbNomor.getSelectedRow(), 1).toString());
                    TRgRawatTujuan.setText(tbNomor.getValueAt(tbNomor.getSelectedRow(), 7).toString());
                    WindowNomorRawat.dispose();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbNomorMouseClicked

    private void btnUlangiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUlangiActionPerformed
        TNoRwTujuan.setText("");
        TNoRmTujuan.setText("");
        TRgRawatTujuan.setText("");
    }//GEN-LAST:event_btnUlangiActionPerformed

    private void MnBatalPindahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBatalPindahActionPerformed
        if (TNoRwTerpilih.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data pasiennya dulu..!!!!");
        } else if (TNoRwTujuan.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih nomor rawat tujuannya dulu..!!!!");
        } else if (TNoRwTujuan.getText().equals(TNoRwTerpilih.getText())) {
            JOptionPane.showMessageDialog(null, "Nomor rawat tujuan harus berbeda dengan nomor rawat terpilih...!!");
        } else if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Belum ada data yang dipindahkan ke nomor rawat tujuan..!!!!");
        } else {
            if (cmbJnsTran.isEnabled() == false) {
                pilihan = 2;
                if (ChkRMranap.isSelected() == true) {
                    x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin semua rekam medis rawat inap akan diproses..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        RMranapGantiNoRawat();
                        JOptionPane.showMessageDialog(null, "Proses pemindahan semua data rekam medis Rawat Inap BERHASIL dilakukan ke no. rawat " + TNoRwTujuan.getText() + "...!!");
                        ChkTglTran.setSelected(false);
                        ChkTglTran.setText("Semua Tgl. Transaksi");
                        tglDari.setText("");
                        pukulDari.setText("");
                        ChkRMranap.setSelected(false);
                        ChkRMigd.setSelected(false);
                        pilihan = 0;
                    }
                }

                if (ChkRMigd.isSelected() == true) {
                    x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin semua rekam medis IGD akan diproses..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        RMigdGantiNoRawat();
                        JOptionPane.showMessageDialog(null, "Proses pemindahan semua data rekam medis IGD BERHASIL dilakukan ke no. rawat " + TNoRwTujuan.getText() + "...!!");
                        ChkTglTran.setSelected(false);
                        ChkTglTran.setText("Semua Tgl. Transaksi");
                        tglDari.setText("");
                        pukulDari.setText("");
                        ChkRMranap.setSelected(false);
                        ChkRMigd.setSelected(false);
                        pilihan = 0;
                    }
                }

            } else {
                i = 0;
                i = Sequel.cariInteger("select count(billing.no_rawat) cek from billing where billing.no_rawat='" + TNoRwTujuan.getText() + "'");
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "Untuk No. Rawat tujuan notanya sudah tersimpan, hapus dulu notanya dibilling pembayaran..!!!");
                } else {
                    pilihan = 2;
                    pindahData();
                }
            }
        }
    }//GEN-LAST:event_MnBatalPindahActionPerformed

    private void tbTujuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTujuanKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getdataTujuan();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTujuanKeyPressed

    private void tbTujuanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTujuanMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getdataTujuan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTujuanMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPindahDataNomorRawat dialog = new DlgPindahDataNomorRawat(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnCekData;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnKeluar;
    private widget.Button BtnProsesData;
    public widget.CekBox ChkInput;
    private widget.CekBox ChkRMigd;
    private widget.CekBox ChkRMranap;
    private widget.CekBox ChkTglTran;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private javax.swing.JMenuItem MnBatalPindah;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TNoRmTerpilih;
    private widget.TextBox TNoRmTujuan;
    private widget.TextBox TNoRwTerpilih;
    private widget.TextBox TNoRwTujuan;
    private widget.TextBox TRgRawatTerpilih;
    private widget.TextBox TRgRawatTujuan;
    private javax.swing.JDialog WindowNomorRawat;
    private widget.Button btnNomor;
    private widget.Button btnUlangi;
    private widget.ComboBox cmbJnsTran;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel110;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private javax.swing.JPopupMenu jPopupMenu;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi4;
    private widget.TextBox pasienTerpilih;
    private widget.TextBox pukulDari;
    private widget.Table tbDipilih;
    private widget.Table tbNomor;
    private widget.Table tbTujuan;
    private widget.TextBox tglDari;
    // End of variables declaration//GEN-END:variables

    public void isForm(){
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 235));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }
    
    private void RMigdGantiNoRawat() {
        if (pilihan == 1) {
            Sequel.mengedit("triase_igd", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("penilaian_awal_medis_igd", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_igdrz", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_igd_resiko", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("transfer_serah_terima_pasien_igd", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("pemberian_obat", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("pelaksana_pemberian_obat", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("penilaian_awal_medis_obstetri_ralan", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("cppt", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("cppt_history", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("cppt_konfirmasi_terapi", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("surat_tindakan_kedokteran", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("permintaan_lab_raza", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("permintaan_radiologi", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("surat_istirahat_sakit", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("surat_keterangan_sakit", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("catatan_resep", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("triase_igd", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("penilaian_awal_medis_igd", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_igdrz", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_igd_resiko", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("transfer_serah_terima_pasien_igd", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("pemberian_obat", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("pelaksana_pemberian_obat", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("penilaian_awal_medis_obstetri_ralan", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("cppt", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("cppt_history", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("cppt_konfirmasi_terapi", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("surat_tindakan_kedokteran", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("permintaan_lab_raza", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("permintaan_radiologi", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("surat_istirahat_sakit", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("surat_keterangan_sakit", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("catatan_resep", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }
    }
    
    private void RMranapGantiNoRawat() {
        if (pilihan == 1) {
            Sequel.mengedit("catatan_tindakan_keperawatan", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("evaluasi_catatan_tindakan_keperawatan", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("manajemen_catatan_tindakan_keperawatan", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("surat_konsul_unit_ranap", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("asesmen_medik_dewasa_ranap", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("asesmen_medik_dewasa_ranap_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("ringkasan_pulang_ranap", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("cppt", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("cppt_history", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("cppt_konfirmasi_terapi", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("transfer_serah_terima_pasien_igd", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("pemberian_obat", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("pelaksana_pemberian_obat", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("surat_tindakan_kedokteran", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("spirometri", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("data_persalinan", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("permintaan_lab_raza", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("permintaan_radiologi", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("surat_istirahat_sakit", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("surat_keterangan_sakit", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("catatan_resep_ranap", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_dewasa_ranap", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_dewasa_ranap_resiko", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_dewasa_ranap_decubitus", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("pemantauan_harian_parental", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("pemantauan_harian_24jam", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("protokol_kemoterapi", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("protokol_kemoterapi_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("asesmen_ulang_resiko_jatuh", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("asesmen_ulang_resiko_jatuh_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("detail_asesmen_ulang_resiko_jatuh", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("detail_asesmen_ulang_resiko_jatuh_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("pengelolaan_transfusi_darah", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("pengelolaan_transfusi_darah_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");            
            Sequel.mengedit("monitoring_ews_dewasa", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_anak_ranap", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_anak_ranap_resiko", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");            
            Sequel.mengedit("asesmen_medik_anak_ranap", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("asesmen_medik_anak_ranap_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");            
            Sequel.mengedit("asesmen_ulang_resiko_jatuh_anak", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("asesmen_ulang_resiko_jatuh_anak_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("detail_asesmen_ulang_resiko_jatuh_anak", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("detail_asesmen_ulang_resiko_jatuh_anak_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("monitoring_pews_anak", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("asesmen_restrain", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("asesmen_restrain_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("observasi_restrain", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");            
        } else if (pilihan == 2) {
            Sequel.mengedit("catatan_tindakan_keperawatan", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("evaluasi_catatan_tindakan_keperawatan", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("manajemen_catatan_tindakan_keperawatan", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("surat_konsul_unit_ranap", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("asesmen_medik_dewasa_ranap", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("asesmen_medik_dewasa_ranap_histori", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");            
            Sequel.mengedit("ringkasan_pulang_ranap", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("cppt", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("cppt_history", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("cppt_konfirmasi_terapi", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("transfer_serah_terima_pasien_igd", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("pemberian_obat", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("pelaksana_pemberian_obat", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("surat_tindakan_kedokteran", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("spirometri", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("data_persalinan", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("permintaan_lab_raza", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("permintaan_radiologi", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("surat_istirahat_sakit", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("surat_keterangan_sakit", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("catatan_resep_ranap", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_dewasa_ranap", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_dewasa_ranap_resiko", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_dewasa_ranap_decubitus", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("pemantauan_harian_parental", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("pemantauan_harian_24jam", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("protokol_kemoterapi", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("protokol_kemoterapi_histori", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("asesmen_ulang_resiko_jatuh", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("asesmen_ulang_resiko_jatuh_histori", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("detail_asesmen_ulang_resiko_jatuh", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("detail_asesmen_ulang_resiko_jatuh_histori", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("pengelolaan_transfusi_darah", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("pengelolaan_transfusi_darah_histori", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("monitoring_ews_dewasa", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_anak_ranap", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_anak_ranap_resiko", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");            
            Sequel.mengedit("asesmen_medik_anak_ranap", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("asesmen_medik_anak_ranap_histori", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");            
            Sequel.mengedit("asesmen_ulang_resiko_jatuh_anak", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("asesmen_ulang_resiko_jatuh_anak_histori", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("detail_asesmen_ulang_resiko_jatuh_anak", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("detail_asesmen_ulang_resiko_jatuh_anak_histori", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("monitoring_pews_anak", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("asesmen_restrain", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("asesmen_restrain_histori", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("observasi_restrain", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }
    }
    
    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
                //farmasi
            if (cmbJnsTran.getSelectedIndex() == 1) {
                ps1 = koneksi.prepareStatement("SELECT d.no_rawat, d.tgl_perawatan tgl, d.jam, db.nama_brng data FROM detail_pemberian_obat d "
                        + "INNER JOIN databarang db on db.kode_brng=d.kode_brng "
                        + "WHERE d.no_rawat='" + TNoRwTerpilih.getText() + "' ORDER BY d.tgl_perawatan, d.jam");
                //radiologi
            } else if (cmbJnsTran.getSelectedIndex() == 2) {
                ps1 = koneksi.prepareStatement("SELECT pr.no_rawat, pr.tgl_periksa tgl, pr.jam, j.nm_perawatan data FROM periksa_radiologi pr "
                        + "INNER JOIN jns_perawatan_radiologi j on j.kd_jenis_prw=pr.kd_jenis_prw "
                        + "WHERE pr.no_rawat='" + TNoRwTerpilih.getText() + "' ORDER BY pr.tgl_periksa, pr.jam");                
                //laboratorium
            } else if (cmbJnsTran.getSelectedIndex() == 3) {
                ps1 = koneksi.prepareStatement("SELECT pl.no_rawat, pl.tgl_periksa tgl, pl.jam, j.nm_perawatan data FROM periksa_lab pl "
                        + "INNER JOIN jns_perawatan_lab j on j.kd_jenis_prw=pl.kd_jenis_prw "
                        + "WHERE pl.no_rawat='" + TNoRwTerpilih.getText() + "' ORDER BY pl.tgl_periksa, pl.jam");
                //dokter
            } else if (cmbJnsTran.getSelectedIndex() == 4) {
                ps1 = koneksi.prepareStatement("SELECT ri.no_rawat, ri.tgl_perawatan tgl, ri.jam_rawat jam, ji.nm_perawatan data FROM rawat_inap_dr ri "
                        + "INNER JOIN jns_perawatan_inap ji ON ji.kd_jenis_prw=ri.kd_jenis_prw "
                        + "WHERE ri.no_rawat='" + TNoRwTerpilih.getText() + "' ORDER BY ri.tgl_perawatan, ri.jam_rawat");                
                //petugas
            } else if (cmbJnsTran.getSelectedIndex() == 5) {
                ps1 = koneksi.prepareStatement("SELECT ri.no_rawat, ri.tgl_perawatan tgl, ri.jam_rawat jam, ji.nm_perawatan data FROM rawat_inap_pr ri "
                        + "INNER JOIN jns_perawatan_inap ji ON ji.kd_jenis_prw=ri.kd_jenis_prw "
                        + "WHERE ri.no_rawat='" + TNoRwTerpilih.getText() + "' ORDER BY ri.tgl_perawatan, ri.jam_rawat");                
                //dokter & petugas
            } else if (cmbJnsTran.getSelectedIndex() == 6) {
                ps1 = koneksi.prepareStatement("SELECT ri.no_rawat, ri.tgl_perawatan tgl, ri.jam_rawat jam, ji.nm_perawatan data FROM rawat_inap_drpr ri "
                        + "INNER JOIN jns_perawatan_inap ji ON ji.kd_jenis_prw=ri.kd_jenis_prw "
                        + "WHERE ri.no_rawat='" + TNoRwTerpilih.getText() + "' ORDER BY ri.tgl_perawatan, ri.jam_rawat");                
                //diet harian
            } else if (cmbJnsTran.getSelectedIndex() == 7) {
                ps1 = koneksi.prepareStatement("select dd.no_rawat, dd.tanggal tgl, TIME(dd.waktu_simpan) jam, concat(d.nama_diet,', Diet : ',dd.waktu) data "
                        + "from detail_beri_diet dd inner join diet d on d.kd_diet=dd.kd_diet "
                        + "where dd.no_rawat ='" + TNoRwTerpilih.getText() + "' order by dd.tanggal, d.nama_diet");                
                //asesmen medik dewasa ranap
            } else if (cmbJnsTran.getSelectedIndex() == 8) {
                ps1 = koneksi.prepareStatement("select no_rawat, date(tgl_asesmen) tgl, time(tgl_asesmen) jam, keluhan_utama data "
                        + "from asesmen_medik_dewasa_ranap where no_rawat ='" + TNoRwTerpilih.getText() + "'");                                
                //asesmen keperawatan dewasa ranap
            } else if (cmbJnsTran.getSelectedIndex() == 9) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_asesmen tgl, jam_asesmen jam, keluhan_utama data "
                        + "from penilaian_awal_keperawatan_dewasa_ranap where no_rawat ='" + TNoRwTerpilih.getText() + "'");                
                //cppt ranap
            } else if (cmbJnsTran.getSelectedIndex() == 10) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_cppt tgl, jam_cppt jam, "
                        + "concat('Terhapus : ',UPPER(flag_hapus),' - ',hasil_pemeriksaan) data "
                        + "from cppt where no_rawat ='" + TNoRwTerpilih.getText() + "' and status='Ranap' order by tgl_cppt, jam_cppt");
                //cppt igd
            } else if (cmbJnsTran.getSelectedIndex() == 11) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_cppt tgl, jam_cppt jam, "
                        + "concat('Terhapus : ',UPPER(flag_hapus),' - ',hasil_pemeriksaan) data "
                        + "from cppt where no_rawat ='" + TNoRwTerpilih.getText() + "' and status='Ralan' order by tgl_cppt, jam_cppt");
                //catatan resep ranap
            } else if (cmbJnsTran.getSelectedIndex() == 12) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_perawatan tgl, jam_perawatan jam, nama_obat data "
                        + "from catatan_resep_ranap where no_rawat ='" + TNoRwTerpilih.getText() + "' order by noId");
                //catatan resep igd
            } else if (cmbJnsTran.getSelectedIndex() == 13) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_perawatan tgl, jam_perawatan jam, nama_obat data "
                        + "from catatan_resep where no_rawat ='" + TNoRwTerpilih.getText() + "' order by noId");
                //ringkasan pulang ranap
            } else if (cmbJnsTran.getSelectedIndex() == 14) {
                ps1 = koneksi.prepareStatement("select no_rawat, date(now()) tgl, time(now()) jam, ringkasan_riwayat_penyakit data "
                        + "from ringkasan_pulang_ranap where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //transfer serah terima ranap
            } else if (cmbJnsTran.getSelectedIndex() == 15) {
                ps1 = koneksi.prepareStatement("select no_rawat, date(waktu_simpan) tgl, time(waktu_simpan) jam, diagnosis data "
                        + "from transfer_serah_terima_pasien_igd where no_rawat ='" + TNoRwTerpilih.getText() + "' and status='Ranap'");
                //transfer serah terima igd
            } else if (cmbJnsTran.getSelectedIndex() == 16) {
                ps1 = koneksi.prepareStatement("select no_rawat, date(waktu_simpan) tgl, time(waktu_simpan) jam, diagnosis data "
                        + "from transfer_serah_terima_pasien_igd where no_rawat ='" + TNoRwTerpilih.getText() + "' and status='Ralan'");
                //catatan tindakan keperawatan ranap
            } else if (cmbJnsTran.getSelectedIndex() == 17) {
                ps1 = koneksi.prepareStatement("select no_rawat, tanggal tgl, jam_tindakan jam, nm_tindakan data "
                        + "from catatan_tindakan_keperawatan where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //jadwal pemberian obat ranap
            } else if (cmbJnsTran.getSelectedIndex() == 18) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_pemberian tgl, jadwal_pemberian jam, nama_obat data "
                        + "from pemberian_obat where no_rawat ='" + TNoRwTerpilih.getText() + "' and status='Ranap'");
                //jadwal pemberian obat igd
            } else if (cmbJnsTran.getSelectedIndex() == 19) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_pemberian tgl, jadwal_pemberian jam, nama_obat data "
                        + "from pemberian_obat where no_rawat ='" + TNoRwTerpilih.getText() + "' and status='Ralan'");
                //konsul antar unit
            } else if (cmbJnsTran.getSelectedIndex() == 20) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_minta tgl, jam_minta jam, permintaan_konsul data "
                        + "from surat_konsul_unit_ranap where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //data persalinan
            } else if (cmbJnsTran.getSelectedIndex() == 21) {
                ps1 = koneksi.prepareStatement("select no_rawat, date(now()) tgl, time(now()) jam, concat(jns_persalinan,', Rujukan : ',rujukan) data "
                        + "from data_persalinan where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //spirometri
            } else if (cmbJnsTran.getSelectedIndex() == 22) {
                ps1 = koneksi.prepareStatement("select no_rawat, date(now()) tgl, time(now()) jam, keluhan data "
                        + "from spirometri where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //surat istirahat sakit
            } else if (cmbJnsTran.getSelectedIndex() == 23) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_mulai tgl, time(now()) jam, no_surat data "
                        + "from surat_istirahat_sakit where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //data persalinan dinkes kabupaten
            } else if (cmbJnsTran.getSelectedIndex() == 24) {
                ps1 = koneksi.prepareStatement("select no_rawat, date(now()) tgl, time(now()) jam, kasus_persalinan data "
                        + "from persalinan_dinkes where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //surat keterangan sakit
            } else if (cmbJnsTran.getSelectedIndex() == 25) {
                ps1 = koneksi.prepareStatement("select no_rawat, sejak_tgl tgl, time(now()) jam, no_surat data "
                        + "from surat_keterangan_sakit where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //dpjp
            } else if (cmbJnsTran.getSelectedIndex() == 26) {
                ps1 = koneksi.prepareStatement("select dr.no_rawat, date(now()) tgl, time(now()) jam, d.nm_dokter data "
                        + "from dpjp_ranap dr inner join dokter d on d.kd_dokter=dr.kd_dokter where dr.no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //permintaan periksa lab
            } else if (cmbJnsTran.getSelectedIndex() == 27) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_permintaan tgl, jam_permintaan jam, nm_pemeriksaan data "
                        + "from permintaan_lab_raza where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //permintaan periksa radiologi
            } else if (cmbJnsTran.getSelectedIndex() == 28) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_permintaan tgl, jam_permintaan jam, nm_pemeriksaan data "
                        + "from permintaan_radiologi where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //pemantauan harian pasien ranap
            } else if (cmbJnsTran.getSelectedIndex() == 29) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_pantau tgl, time(now()) jam, kd_pantau data "
                        + "from pemantauan_harian_24jam where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //triase igd
            } else if (cmbJnsTran.getSelectedIndex() == 30) {
                ps1 = koneksi.prepareStatement("select no_rawat, date(tanggal) tgl, time(tanggal) jam, keluhan_utama data "
                        + "from triase_igd where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //asesmen medik igd
            } else if (cmbJnsTran.getSelectedIndex() == 31) {
                ps1 = koneksi.prepareStatement("select no_rawat, date(tanggal) tgl, time(tanggal) jam, diag_medis_sementara data "
                        + "from penilaian_awal_medis_igd where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //asesmen keperawatan igd
            } else if (cmbJnsTran.getSelectedIndex() == 32) {
                ps1 = koneksi.prepareStatement("select no_rawat, date(tanggal) tgl, time(tanggal) jam, keluhan_utama data "
                        + "from penilaian_awal_keperawatan_igdrz where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //protokol kemoterapi
            } else if (cmbJnsTran.getSelectedIndex() == 33) {
                ps1 = koneksi.prepareStatement("select no_rawat, date(tgl_siklus) tgl, time(waktu_simpan) jam, program data "
                        + "from protokol_kemoterapi where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //asesmen ulang resiko jatuh dewasa
            } else if (cmbJnsTran.getSelectedIndex() == 34) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_asesmen tgl, jam_asesmen jam, kode_ulang_resiko data "
                        + "from asesmen_ulang_resiko_jatuh where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //pengelolaan transfusi darah
            } else if (cmbJnsTran.getSelectedIndex() == 35) {
                ps1 = koneksi.prepareStatement("select no_rawat, tanggal tgl, jam, jenis_darah_transfusi data "
                        + "from pengelolaan_transfusi_darah where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //monitoring ews dewasa
            } else if (cmbJnsTran.getSelectedIndex() == 36) {
                ps1 = koneksi.prepareStatement("select no_rawat, tanggal tgl, jam, nilai_skor data "
                        + "from monitoring_ews_dewasa where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //asesmen keperawatan anak ranap
            } else if (cmbJnsTran.getSelectedIndex() == 37) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_asesmen tgl, jam_asesmen jam, keluhan_utama data "
                        + "from penilaian_awal_keperawatan_anak_ranap where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //asesmen medik anak rawat inap
            } else if (cmbJnsTran.getSelectedIndex() == 38) {
                ps1 = koneksi.prepareStatement("select no_rawat, date(tgl_asesmen) tgl, time(tgl_asesmen) jam, keluhan_utama data "
                        + "from asesmen_medik_anak_ranap where no_rawat ='" + TNoRwTerpilih.getText() + "'");                
                 //asesmen ulang resiko jatuh anak
            } else if (cmbJnsTran.getSelectedIndex() == 39) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_asesmen tgl, jam_asesmen jam, kode_ulang_resiko data "
                        + "from asesmen_ulang_resiko_jatuh_anak where no_rawat ='" + TNoRwTerpilih.getText() + "'");
                //monitoring pediatric ews
            } else if (cmbJnsTran.getSelectedIndex() == 40) {
                ps1 = koneksi.prepareStatement("select no_rawat, tanggal tgl, jam, nilai_skor data "
                        + "from monitoring_pews_anak where no_rawat ='" + TNoRwTerpilih.getText() + "'");                
                //asesmen restrain
            } else if (cmbJnsTran.getSelectedIndex() == 41) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_asesmen tgl, jam_asesmen jam, kd_restrain data "
                        + "from asesmen_restrain where no_rawat ='" + TNoRwTerpilih.getText() + "'");                
                //observasi restrain
            } else if (cmbJnsTran.getSelectedIndex() == 42) {
                ps1 = koneksi.prepareStatement("select no_rawat, tgl_lokasi tgl, jam_lokasi jam, kd_restrain data "
                        + "from observasi_restrain where no_rawat ='" + TNoRwTerpilih.getText() + "'");                
            }

            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode.addRow(new Object[]{
                        rs1.getString("no_rawat"),
                        rs1.getString("tgl"),
                        rs1.getString("jam"),
                        rs1.getString("data")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println(e);
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
    
    private void tampilTujuan() {
        Valid.tabelKosong(tabMode1);
        try {
                //farmasi
            if (cmbJnsTran.getSelectedIndex() == 1) {
                ps2 = koneksi.prepareStatement("SELECT d.no_rawat, d.tgl_perawatan tgl, d.jam, db.nama_brng data FROM detail_pemberian_obat d "
                        + "INNER JOIN databarang db on db.kode_brng=d.kode_brng "
                        + "WHERE d.no_rawat='" + TNoRwTujuan.getText() + "' ORDER BY d.tgl_perawatan, d.jam");
                //radiologi
            } else if (cmbJnsTran.getSelectedIndex() == 2) {
                ps2 = koneksi.prepareStatement("SELECT pr.no_rawat, pr.tgl_periksa tgl, pr.jam, j.nm_perawatan data FROM periksa_radiologi pr "
                        + "INNER JOIN jns_perawatan_radiologi j on j.kd_jenis_prw=pr.kd_jenis_prw "
                        + "WHERE pr.no_rawat='" + TNoRwTujuan.getText() + "' ORDER BY pr.tgl_periksa, pr.jam");
                //laboratorium
            } else if (cmbJnsTran.getSelectedIndex() == 3) {
                ps2 = koneksi.prepareStatement("SELECT pl.no_rawat, pl.tgl_periksa tgl, pl.jam, j.nm_perawatan data FROM periksa_lab pl "
                        + "INNER JOIN jns_perawatan_lab j on j.kd_jenis_prw=pl.kd_jenis_prw "
                        + "WHERE pl.no_rawat='" + TNoRwTujuan.getText() + "' ORDER BY pl.tgl_periksa, pl.jam");
                //dokter
            } else if (cmbJnsTran.getSelectedIndex() == 4) {
                ps2 = koneksi.prepareStatement("SELECT ri.no_rawat, ri.tgl_perawatan tgl, ri.jam_rawat jam, ji.nm_perawatan data FROM rawat_inap_dr ri "
                        + "INNER JOIN jns_perawatan_inap ji ON ji.kd_jenis_prw=ri.kd_jenis_prw "
                        + "WHERE ri.no_rawat='" + TNoRwTujuan.getText() + "' ORDER BY ri.tgl_perawatan, ri.jam_rawat");
                //petugas
            } else if (cmbJnsTran.getSelectedIndex() == 5) {
                ps2 = koneksi.prepareStatement("SELECT ri.no_rawat, ri.tgl_perawatan tgl, ri.jam_rawat jam, ji.nm_perawatan data FROM rawat_inap_pr ri "
                        + "INNER JOIN jns_perawatan_inap ji ON ji.kd_jenis_prw=ri.kd_jenis_prw "
                        + "WHERE ri.no_rawat='" + TNoRwTujuan.getText() + "' ORDER BY ri.tgl_perawatan, ri.jam_rawat");
                //dokter & petugas
            } else if (cmbJnsTran.getSelectedIndex() == 6) {
                ps2 = koneksi.prepareStatement("SELECT ri.no_rawat, ri.tgl_perawatan tgl, ri.jam_rawat jam, ji.nm_perawatan data FROM rawat_inap_drpr ri "
                        + "INNER JOIN jns_perawatan_inap ji ON ji.kd_jenis_prw=ri.kd_jenis_prw "
                        + "WHERE ri.no_rawat='" + TNoRwTujuan.getText() + "' ORDER BY ri.tgl_perawatan, ri.jam_rawat");
                //diet harian
            } else if (cmbJnsTran.getSelectedIndex() == 7) {
                ps2 = koneksi.prepareStatement("select dd.no_rawat, dd.tanggal tgl, TIME(dd.waktu_simpan) jam, concat(d.nama_diet,', Diet : ',dd.waktu) data "
                        + "from detail_beri_diet dd inner join diet d on d.kd_diet=dd.kd_diet "
                        + "where dd.no_rawat ='" + TNoRwTujuan.getText() + "' order by dd.tanggal, d.nama_diet");
                //asesmen medik dewasa ranap
            } else if (cmbJnsTran.getSelectedIndex() == 8) {
                ps2 = koneksi.prepareStatement("select no_rawat, date(tgl_asesmen) tgl, time(tgl_asesmen) jam, keluhan_utama data "
                        + "from asesmen_medik_dewasa_ranap where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //asesmen keperawatan dewasa ranap
            } else if (cmbJnsTran.getSelectedIndex() == 9) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_asesmen tgl, jam_asesmen jam, keluhan_utama data "
                        + "from penilaian_awal_keperawatan_dewasa_ranap where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //cppt ranap
            } else if (cmbJnsTran.getSelectedIndex() == 10) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_cppt tgl, jam_cppt jam, "
                        + "concat('Terhapus : ',UPPER(flag_hapus),' - ',hasil_pemeriksaan) data "
                        + "from cppt where no_rawat ='" + TNoRwTujuan.getText() + "' and status='Ranap' order by tgl_cppt, jam_cppt");
                //cppt igd
            } else if (cmbJnsTran.getSelectedIndex() == 11) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_cppt tgl, jam_cppt jam, "
                        + "concat('Terhapus : ',UPPER(flag_hapus),' - ',hasil_pemeriksaan) data "
                        + "from cppt where no_rawat ='" + TNoRwTujuan.getText() + "' and status='Ralan' order by tgl_cppt, jam_cppt");
                //catatan resep ranap
            } else if (cmbJnsTran.getSelectedIndex() == 12) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_perawatan tgl, jam_perawatan jam, nama_obat data "
                        + "from catatan_resep_ranap where no_rawat ='" + TNoRwTujuan.getText() + "' order by noId");
                //catatan resep igd
            } else if (cmbJnsTran.getSelectedIndex() == 13) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_perawatan tgl, jam_perawatan jam, nama_obat data "
                        + "from catatan_resep where no_rawat ='" + TNoRwTujuan.getText() + "' order by noId");
                //ringkasan pulang ranap
            } else if (cmbJnsTran.getSelectedIndex() == 14) {
                ps2 = koneksi.prepareStatement("select no_rawat, date(now()) tgl, time(now()) jam, ringkasan_riwayat_penyakit data "
                        + "from ringkasan_pulang_ranap where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //transfer serah terima ranap
            } else if (cmbJnsTran.getSelectedIndex() == 15) {
                ps2 = koneksi.prepareStatement("select no_rawat, date(waktu_simpan) tgl, time(waktu_simpan) jam, diagnosis data "
                        + "from transfer_serah_terima_pasien_igd where no_rawat ='" + TNoRwTujuan.getText() + "' and status='Ranap'");
                //transfer serah terima igd
            } else if (cmbJnsTran.getSelectedIndex() == 16) {
                ps2 = koneksi.prepareStatement("select no_rawat, date(waktu_simpan) tgl, time(waktu_simpan) jam, diagnosis data "
                        + "from transfer_serah_terima_pasien_igd where no_rawat ='" + TNoRwTujuan.getText() + "' and status='Ralan'");
                //catatan tindakan keperawatan ranap
            } else if (cmbJnsTran.getSelectedIndex() == 17) {
                ps2 = koneksi.prepareStatement("select no_rawat, tanggal tgl, jam_tindakan jam, nm_tindakan data "
                        + "from catatan_tindakan_keperawatan where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //jadwal pemberian obat ranap
            } else if (cmbJnsTran.getSelectedIndex() == 18) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_pemberian tgl, jadwal_pemberian jam, nama_obat data "
                        + "from pemberian_obat where no_rawat ='" + TNoRwTujuan.getText() + "' and status='Ranap'");
                //jadwal pemberian obat igd
            } else if (cmbJnsTran.getSelectedIndex() == 19) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_pemberian tgl, jadwal_pemberian jam, nama_obat data "
                        + "from pemberian_obat where no_rawat ='" + TNoRwTujuan.getText() + "' and status='Ralan'");
                //konsul antar unit
            } else if (cmbJnsTran.getSelectedIndex() == 20) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_minta tgl, jam_minta jam, permintaan_konsul data "
                        + "from surat_konsul_unit_ranap where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //data persalinan
            } else if (cmbJnsTran.getSelectedIndex() == 21) {
                ps2 = koneksi.prepareStatement("select no_rawat, date(now()) tgl, time(now()) jam, concat(jns_persalinan,', Rujukan : ',rujukan) data "
                        + "from data_persalinan where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //spirometri
            } else if (cmbJnsTran.getSelectedIndex() == 22) {
                ps2 = koneksi.prepareStatement("select no_rawat, date(now()) tgl, time(now()) jam, keluhan data "
                        + "from spirometri where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //surat istirahat sakit
            } else if (cmbJnsTran.getSelectedIndex() == 23) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_mulai tgl, time(now()) jam, no_surat data "
                        + "from surat_istirahat_sakit where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //data persalinan dinkes kabupaten
            } else if (cmbJnsTran.getSelectedIndex() == 24) {
                ps2 = koneksi.prepareStatement("select no_rawat, date(now()) tgl, time(now()) jam, kasus_persalinan data "
                        + "from persalinan_dinkes where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //surat keterangan sakit
            } else if (cmbJnsTran.getSelectedIndex() == 25) {
                ps2 = koneksi.prepareStatement("select no_rawat, sejak_tgl tgl, time(now()) jam, no_surat data "
                        + "from surat_keterangan_sakit where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //dpjp
            } else if (cmbJnsTran.getSelectedIndex() == 26) {
                ps2 = koneksi.prepareStatement("select dr.no_rawat, date(now()) tgl, time(now()) jam, d.nm_dokter data "
                        + "from dpjp_ranap dr inner join dokter d on d.kd_dokter=dr.kd_dokter where dr.no_rawat ='" + TNoRwTujuan.getText() + "'");
                //permintaan periksa lab
            } else if (cmbJnsTran.getSelectedIndex() == 27) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_permintaan tgl, jam_permintaan jam, nm_pemeriksaan data "
                        + "from permintaan_lab_raza where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //permintaan periksa radiologi
            } else if (cmbJnsTran.getSelectedIndex() == 28) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_permintaan tgl, jam_permintaan jam, nm_pemeriksaan data "
                        + "from permintaan_radiologi where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //pemantauan harian pasien ranap
            } else if (cmbJnsTran.getSelectedIndex() == 29) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_pantau tgl, time(now()) jam, kd_pantau data "
                        + "from pemantauan_harian_24jam where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //triase igd
            } else if (cmbJnsTran.getSelectedIndex() == 30) {
                ps2 = koneksi.prepareStatement("select no_rawat, date(tanggal) tgl, time(tanggal) jam, keluhan_utama data "
                        + "from triase_igd where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //asesmen medik igd
            } else if (cmbJnsTran.getSelectedIndex() == 31) {
                ps2 = koneksi.prepareStatement("select no_rawat, date(tanggal) tgl, time(tanggal) jam, diag_medis_sementara data "
                        + "from penilaian_awal_medis_igd where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //asesmen keperawatan igd
            } else if (cmbJnsTran.getSelectedIndex() == 32) {
                ps2 = koneksi.prepareStatement("select no_rawat, date(tanggal) tgl, time(tanggal) jam, keluhan_utama data "
                        + "from penilaian_awal_keperawatan_igdrz where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //protokol kemoterapi
            } else if (cmbJnsTran.getSelectedIndex() == 33) {
                ps2 = koneksi.prepareStatement("select no_rawat, date(tgl_siklus) tgl, time(waktu_simpan) jam, program data "
                        + "from protokol_kemoterapi where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //asesmen ulang resiko jatuh dewasa
            } else if (cmbJnsTran.getSelectedIndex() == 34) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_asesmen tgl, jam_asesmen jam, kode_ulang_resiko data "
                        + "from asesmen_ulang_resiko_jatuh where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //pengelolaan transfusi darah
            } else if (cmbJnsTran.getSelectedIndex() == 35) {
                ps2 = koneksi.prepareStatement("select no_rawat, tanggal tgl, jam jam, jenis_darah_transfusi data "
                        + "from pengelolaan_transfusi_darah where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //monitoring ews dewasa
            } else if (cmbJnsTran.getSelectedIndex() == 36) {
                ps2 = koneksi.prepareStatement("select no_rawat, tanggal tgl, jam, nilai_skor data "
                        + "from monitoring_ews_dewasa where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //asesmen keperawatan anak ranap
            } else if (cmbJnsTran.getSelectedIndex() == 37) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_asesmen tgl, jam_asesmen jam, keluhan_utama data "
                        + "from penilaian_awal_keperawatan_anak_ranap where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //asesmen medik anak ranap
            } else if (cmbJnsTran.getSelectedIndex() == 38) {
                ps2 = koneksi.prepareStatement("select no_rawat, date(tgl_asesmen) tgl, time(tgl_asesmen) jam, keluhan_utama data "
                        + "from asesmen_medik_anak_ranap where no_rawat ='" + TNoRwTujuan.getText() + "'");                
                //asesmen ulang resiko jatuh anak
            } else if (cmbJnsTran.getSelectedIndex() == 39) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_asesmen tgl, jam_asesmen jam, kode_ulang_resiko data "
                        + "from asesmen_ulang_resiko_jatuh_anak where no_rawat ='" + TNoRwTujuan.getText() + "'");
                //monitoring pediatric ews
            } else if (cmbJnsTran.getSelectedIndex() == 40) {
                ps2 = koneksi.prepareStatement("select no_rawat, tanggal tgl, jam, nilai_skor data "
                        + "from monitoring_pews_anak where no_rawat ='" + TNoRwTujuan.getText() + "'");                
                //asesmen restrain
            } else if (cmbJnsTran.getSelectedIndex() == 41) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_asesmen tgl, jam_asesmen jam, kd_restrain data "
                        + "from asesmen_restrain where no_rawat ='" + TNoRwTujuan.getText() + "'");                
                //observasi restrain
            } else if (cmbJnsTran.getSelectedIndex() == 42) {
                ps2 = koneksi.prepareStatement("select no_rawat, tgl_lokasi tgl, jam_lokasi jam, kd_restrain data "
                        + "from observasi_restrain where no_rawat ='" + TNoRwTujuan.getText() + "'");                
            }

            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode1.addRow(new Object[]{
                        rs2.getString("no_rawat"),
                        rs2.getString("tgl"),
                        rs2.getString("jam"),
                        rs2.getString("data")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void emptTeks() {
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");        
        cmbJnsTran.setSelectedIndex(0);
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
        Valid.tabelKosong(tabMode);
        Valid.tabelKosong(tabMode1);
    }
    
    private void tranFarmasi() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("detail_pemberian_obat", "tgl_perawatan = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("detail_pemberian_obat", "tgl_perawatan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

                Sequel.mengedit("resep_obat", "tgl_perawatan = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("resep_obat", "tgl_perawatan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

                Sequel.mengedit("riwayat_obat_pasien", "tanggal = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("riwayat_obat_pasien", "tanggal > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("detail_pemberian_obat", "tgl_perawatan = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("detail_pemberian_obat", "tgl_perawatan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");

                Sequel.mengedit("resep_obat", "tgl_perawatan = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("resep_obat", "tgl_perawatan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");

                Sequel.mengedit("riwayat_obat_pasien", "tanggal = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("riwayat_obat_pasien", "tanggal > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);
        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("detail_pemberian_obat", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("resep_obat", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("riwayat_obat_pasien", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("detail_pemberian_obat", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("resep_obat", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("riwayat_obat_pasien", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }
            
            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);
        } else {
            JOptionPane.showMessageDialog(null, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranRadiologi() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("periksa_radiologi", "tgl_periksa = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("periksa_radiologi", "tgl_periksa > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

                Sequel.mengedit("beri_bhp_radiologi", "tgl_periksa = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("beri_bhp_radiologi", "tgl_periksa > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

                Sequel.mengedit("hasil_radiologi", "tgl_periksa = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("hasil_radiologi", "tgl_periksa > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("periksa_radiologi", "tgl_periksa = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("periksa_radiologi", "tgl_periksa > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");

                Sequel.mengedit("beri_bhp_radiologi", "tgl_periksa = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("beri_bhp_radiologi", "tgl_periksa > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");

                Sequel.mengedit("hasil_radiologi", "tgl_periksa = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("hasil_radiologi", "tgl_periksa > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("periksa_radiologi", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("beri_bhp_radiologi", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("hasil_radiologi", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("periksa_radiologi", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("beri_bhp_radiologi", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("hasil_radiologi", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }
            
            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);
        } else {
            JOptionPane.showMessageDialog(null, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranLaboratorium() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("periksa_lab", "tgl_periksa = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("periksa_lab", "tgl_periksa > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

                Sequel.mengedit("detail_periksa_lab", "tgl_periksa = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("detail_periksa_lab", "tgl_periksa > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

                Sequel.mengedit("lis_reg", "tgl_periksa = '" + tglDari.getText() + "' and jam_periksa >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("lis_reg", "tgl_periksa > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("periksa_lab", "tgl_periksa = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("periksa_lab", "tgl_periksa > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");

                Sequel.mengedit("detail_periksa_lab", "tgl_periksa = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("detail_periksa_lab", "tgl_periksa > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");

                Sequel.mengedit("lis_reg", "tgl_periksa = '" + tglDari.getText() + "' and jam_periksa >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("lis_reg", "tgl_periksa > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);
        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("periksa_lab", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("detail_periksa_lab", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("lis_reg", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("periksa_lab", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("detail_periksa_lab", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("lis_reg", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }
            
            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);
        } else {
            JOptionPane.showMessageDialog(null, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranDokter() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("rawat_inap_dr", "tgl_perawatan = '" + tglDari.getText() + "' and jam_rawat >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("rawat_inap_dr", "tgl_perawatan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("rawat_inap_dr", "tgl_perawatan = '" + tglDari.getText() + "' and jam_rawat >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("rawat_inap_dr", "tgl_perawatan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);
            
        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("rawat_inap_dr", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("rawat_inap_dr", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }
            
            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);
            
        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranPetugas() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("rawat_inap_pr", "tgl_perawatan = '" + tglDari.getText() + "' and jam_rawat >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("rawat_inap_pr", "tgl_perawatan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("rawat_inap_pr", "tgl_perawatan = '" + tglDari.getText() + "' and jam_rawat >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("rawat_inap_pr", "tgl_perawatan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("rawat_inap_pr", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("rawat_inap_pr", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranDokterPetugas() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("rawat_inap_drpr", "tgl_perawatan = '" + tglDari.getText() + "' and jam_rawat >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("rawat_inap_drpr", "tgl_perawatan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("rawat_inap_drpr", "tgl_perawatan = '" + tglDari.getText() + "' and jam_rawat >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("rawat_inap_drpr", "tgl_perawatan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("rawat_inap_drpr", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("rawat_inap_drpr", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranDietGizi() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("detail_beri_diet", "tanggal = '" + tglDari.getText() + "' and TIME(waktu_simpan) >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("detail_beri_diet", "tanggal > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

                Sequel.mengedit("diet_ranap_daftar_rincian", "tanggal = '" + tglDari.getText() + "' and TIME(waktu_simpan) >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("diet_ranap_daftar_rincian", "tanggal > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("detail_beri_diet", "tanggal = '" + tglDari.getText() + "' and TIME(waktu_simpan) >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("detail_beri_diet", "tanggal > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");

                Sequel.mengedit("diet_ranap_daftar_rincian", "tanggal = '" + tglDari.getText() + "' and TIME(waktu_simpan) >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("diet_ranap_daftar_rincian", "tanggal > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("detail_beri_diet", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("diet_ranap_daftar_rincian", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("detail_beri_diet", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("diet_ranap_daftar_rincian", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    public void setData(String norwPilih, String rgrawat) {
        TNoRwTerpilih.setText(norwPilih);
        TNoRmTerpilih.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norwPilih + "'"));
        pasienTerpilih.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRmTerpilih.getText() + "'"));
        TRgRawatTerpilih.setText(rgrawat);
    }
    
    private void getdataPilih() {
        if (tbDipilih.getSelectedRow() != -1) {
            tglDari.setText(tbDipilih.getValueAt(tbDipilih.getSelectedRow(), 1).toString());
            pukulDari.setText(tbDipilih.getValueAt(tbDipilih.getSelectedRow(), 2).toString());
            ChkTglTran.setSelected(true);
            ChkTglTran.setText("Dipilih");
        }
    }
    
    private void getdataTujuan() {
        if (tbTujuan.getSelectedRow() != -1) {
            tglDari.setText(tbTujuan.getValueAt(tbTujuan.getSelectedRow(), 1).toString());
            pukulDari.setText(tbTujuan.getValueAt(tbTujuan.getSelectedRow(), 2).toString());
            ChkTglTran.setSelected(true);
            ChkTglTran.setText("Dipilih");
        }
    }
    
    private void tampilNomor() {
        Valid.tabelKosong(tabMode2);
        try {
            psNom = koneksi.prepareStatement("SELECT ki.no_rawat, r.no_rkm_medis, p.nm_pasien, ki.tgl_masuk, ki.jam_masuk, "
                    + "IF(ki.tgl_keluar='0000-00-00','',ki.tgl_keluar) tgl_keluar, "
                    + "IF(ki.jam_keluar='00:00:00','',ki.jam_keluar) jam_keluar, b.nm_bangsal, ki.stts_pulang "
                    + "FROM kamar_inap ki INNER JOIN reg_periksa r ON ki.no_rawat = r.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = r.no_rkm_medis INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                    + "INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal where r.no_rkm_medis='" + TNoRmTerpilih.getText() + "' and "
                    + "ki.tgl_masuk between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "order by b.nm_bangsal, ki.tgl_masuk, ki.jam_masuk");
            try {
                rsNom = psNom.executeQuery();
                while (rsNom.next()) {
                    tabMode2.addRow(new Object[]{
                        rsNom.getString("no_rawat"),
                        rsNom.getString("no_rkm_medis"),
                        rsNom.getString("nm_pasien"),
                        rsNom.getString("tgl_masuk"),
                        rsNom.getString("jam_masuk"),
                        rsNom.getString("tgl_keluar"),
                        rsNom.getString("jam_keluar"),
                        rsNom.getString("nm_bangsal"),
                        rsNom.getString("stts_pulang")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (rsNom != null) {
                    rsNom.close();
                }
                if (psNom != null) {
                    psNom.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tranAsesMedikDewasaRanap() {
        if (pilihan == 1) {
            Sequel.mengedit("asesmen_medik_dewasa_ranap", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("asesmen_medik_dewasa_ranap_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("asesmen_medik_dewasa_ranap", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("asesmen_medik_dewasa_ranap_histori", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void tranAsesMedikAnakRanap() {
        if (pilihan == 1) {
            Sequel.mengedit("asesmen_medik_anak_ranap", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("asesmen_medik_anak_ranap_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("asesmen_medik_anak_ranap", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("asesmen_medik_anak_ranap_histori", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void tranAsesRestrain() {
        if (pilihan == 1) {
            Sequel.mengedit("asesmen_restrain", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("asesmen_restrain_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("asesmen_restrain", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("asesmen_restrain_histori", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void tranObservasiRestrain() {
        if (pilihan == 1) {
            Sequel.mengedit("observasi_restrain", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("observasi_restrain", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void tranAsesKepDewasaRanap() {
        if (pilihan == 1) {
            Sequel.mengedit("penilaian_awal_keperawatan_dewasa_ranap", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_dewasa_ranap_resiko", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_dewasa_ranap_decubitus", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("penilaian_awal_keperawatan_dewasa_ranap", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_dewasa_ranap_resiko", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_dewasa_ranap_decubitus", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void tranCPPTranap() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("cppt", "tgl_cppt = '" + tglDari.getText() + "' and jam_cppt >='" + pukulDari.getText() + "' and status='Ranap' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("cppt", "tgl_cppt > '" + tglDari.getText() + "' and status='Ranap' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                
                Sequel.mengedit("cppt_history", "tgl_cppt = '" + tglDari.getText() + "' and jam_cppt >='" + pukulDari.getText() + "' and status='Ranap' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("cppt_history", "tgl_cppt > '" + tglDari.getText() + "' and status='Ranap' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                
                Sequel.mengedit("cppt_konfirmasi_terapi", "tgl_cppt = '" + tglDari.getText() + "' and jam_cppt >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("cppt_konfirmasi_terapi", "tgl_cppt > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("cppt", "tgl_cppt = '" + tglDari.getText() + "' and jam_cppt >='" + pukulDari.getText() + "' and status='Ranap' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("cppt", "tgl_cppt > '" + tglDari.getText() + "' and status='Ranap' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                
                Sequel.mengedit("cppt_history", "tgl_cppt = '" + tglDari.getText() + "' and jam_cppt >='" + pukulDari.getText() + "' and status='Ranap' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("cppt_history", "tgl_cppt > '" + tglDari.getText() + "' and status='Ranap' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                
                Sequel.mengedit("cppt_konfirmasi_terapi", "tgl_cppt = '" + tglDari.getText() + "' and jam_cppt >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("cppt_konfirmasi_terapi", "tgl_cppt > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("cppt", "no_rawat='" + TNoRwTerpilih.getText() + "' and status='Ranap'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("cppt_history", "no_rawat='" + TNoRwTerpilih.getText() + "' and status='Ranap'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("cppt_konfirmasi_terapi", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("cppt", "no_rawat='" + TNoRwTujuan.getText() + "' and status='Ranap'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("cppt_history", "no_rawat='" + TNoRwTujuan.getText() + "' and status='Ranap'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("cppt_konfirmasi_terapi", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranCPPTigd() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("cppt", "tgl_cppt = '" + tglDari.getText() + "' and jam_cppt >='" + pukulDari.getText() + "' and status='Ralan' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("cppt", "tgl_cppt > '" + tglDari.getText() + "' and status='Ralan' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                
                Sequel.mengedit("cppt_history", "tgl_cppt = '" + tglDari.getText() + "' and jam_cppt >='" + pukulDari.getText() + "' and status='Ralan' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("cppt_history", "tgl_cppt > '" + tglDari.getText() + "' and status='Ralan' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                
                Sequel.mengedit("cppt_konfirmasi_terapi", "tgl_cppt = '" + tglDari.getText() + "' and jam_cppt >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("cppt_konfirmasi_terapi", "tgl_cppt > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("cppt", "tgl_cppt = '" + tglDari.getText() + "' and jam_cppt >='" + pukulDari.getText() + "' and status='Ralan' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("cppt", "tgl_cppt > '" + tglDari.getText() + "' and status='Ralan' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                
                Sequel.mengedit("cppt_history", "tgl_cppt = '" + tglDari.getText() + "' and jam_cppt >='" + pukulDari.getText() + "' and status='Ralan' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("cppt_history", "tgl_cppt > '" + tglDari.getText() + "' and status='Ralan' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                
                Sequel.mengedit("cppt_konfirmasi_terapi", "tgl_cppt = '" + tglDari.getText() + "' and jam_cppt >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("cppt_konfirmasi_terapi", "tgl_cppt > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("cppt", "no_rawat='" + TNoRwTerpilih.getText() + "' and status='Ralan'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("cppt_history", "no_rawat='" + TNoRwTerpilih.getText() + "' and status='Ralan'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("cppt_konfirmasi_terapi", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("cppt", "no_rawat='" + TNoRwTujuan.getText() + "' and status='Ralan'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("cppt_history", "no_rawat='" + TNoRwTujuan.getText() + "' and status='Ralan'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("cppt_konfirmasi_terapi", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranCatatanResepRanap() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("catatan_resep_ranap", "tgl_perawatan = '" + tglDari.getText() + "' and jam_perawatan >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("catatan_resep_ranap", "tgl_perawatan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("catatan_resep_ranap", "tgl_perawatan = '" + tglDari.getText() + "' and jam_perawatan >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("catatan_resep_ranap", "tgl_perawatan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("catatan_resep_ranap", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("catatan_resep_ranap", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranCatatanResepIGD() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("catatan_resep", "tgl_perawatan = '" + tglDari.getText() + "' and jam_perawatan >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("catatan_resep", "tgl_perawatan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("catatan_resep", "tgl_perawatan = '" + tglDari.getText() + "' and jam_perawatan >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("catatan_resep", "tgl_perawatan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("catatan_resep", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("catatan_resep", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranRingkasanPulang() {
        if (pilihan == 1) {
            Sequel.mengedit("ringkasan_pulang_ranap", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("ringkasan_pulang_ranap", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void tranTransferRanap() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("transfer_serah_terima_pasien_igd", "date(waktu_simpan) = '" + tglDari.getText() + "' and time(waktu_simpan) >='" + pukulDari.getText() + "' and status='Ranap' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("transfer_serah_terima_pasien_igd", "date(waktu_simpan) > '" + tglDari.getText() + "' and status='Ranap' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("transfer_serah_terima_pasien_igd", "date(waktu_simpan) = '" + tglDari.getText() + "' and time(waktu_simpan) >='" + pukulDari.getText() + "' and status='Ranap' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("transfer_serah_terima_pasien_igd", "date(waktu_simpan) > '" + tglDari.getText() + "' and status='Ranap' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("transfer_serah_terima_pasien_igd", "no_rawat='" + TNoRwTerpilih.getText() + "' and status='Ranap'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("transfer_serah_terima_pasien_igd", "no_rawat='" + TNoRwTujuan.getText() + "' and status='Ranap'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranTransferIGD() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("transfer_serah_terima_pasien_igd", "date(waktu_simpan) = '" + tglDari.getText() + "' and time(waktu_simpan) >='" + pukulDari.getText() + "' and status='Ralan' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("transfer_serah_terima_pasien_igd", "date(waktu_simpan) > '" + tglDari.getText() + "' and status='Ralan' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("transfer_serah_terima_pasien_igd", "date(waktu_simpan) = '" + tglDari.getText() + "' and time(waktu_simpan) >='" + pukulDari.getText() + "' and status='Ralan' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("transfer_serah_terima_pasien_igd", "date(waktu_simpan) > '" + tglDari.getText() + "' and status='Ralan' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("transfer_serah_terima_pasien_igd", "no_rawat='" + TNoRwTerpilih.getText() + "' and status='Ralan'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("transfer_serah_terima_pasien_igd", "no_rawat='" + TNoRwTujuan.getText() + "' and status='Ralan'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranCTKranap() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("catatan_tindakan_keperawatan", "tanggal = '" + tglDari.getText() + "' and jam_tindakan >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("catatan_tindakan_keperawatan", "tanggal > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                
                Sequel.mengedit("evaluasi_catatan_tindakan_keperawatan", "tanggal = '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("evaluasi_catatan_tindakan_keperawatan", "tanggal > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                
                Sequel.mengedit("manajemen_catatan_tindakan_keperawatan", "tanggal = '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("manajemen_catatan_tindakan_keperawatan", "tanggal > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("catatan_tindakan_keperawatan", "tanggal = '" + tglDari.getText() + "' and jam_tindakan >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("catatan_tindakan_keperawatan", "tanggal > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                
                Sequel.mengedit("evaluasi_catatan_tindakan_keperawatan", "tanggal = '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("evaluasi_catatan_tindakan_keperawatan", "tanggal > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                
                Sequel.mengedit("manajemen_catatan_tindakan_keperawatan", "tanggal = '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("manajemen_catatan_tindakan_keperawatan", "tanggal > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("catatan_tindakan_keperawatan", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("evaluasi_catatan_tindakan_keperawatan", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("manajemen_catatan_tindakan_keperawatan", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("catatan_tindakan_keperawatan", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("evaluasi_catatan_tindakan_keperawatan", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("manajemen_catatan_tindakan_keperawatan", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranPemberianObatranap() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("pemberian_obat", "tgl_pemberian = '" + tglDari.getText() + "' and status='Ranap' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("pemberian_obat", "tgl_pemberian > '" + tglDari.getText() + "' and status='Ranap' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                
                Sequel.mengedit("pelaksana_pemberian_obat", "tgl_pemberian = '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("pelaksana_pemberian_obat", "tgl_pemberian > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("pemberian_obat", "tgl_pemberian = '" + tglDari.getText() + "' and status='Ranap' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("pemberian_obat", "tgl_pemberian > '" + tglDari.getText() + "' and status='Ranap' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                
                Sequel.mengedit("pelaksana_pemberian_obat", "tgl_pemberian = '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("pelaksana_pemberian_obat", "tgl_pemberian > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("pemberian_obat", "no_rawat='" + TNoRwTerpilih.getText() + "' and status='Ranap'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("pelaksana_pemberian_obat", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("pemberian_obat", "no_rawat='" + TNoRwTujuan.getText() + "' and status='Ranap'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("pelaksana_pemberian_obat", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranPemberianObatIGD() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("pemberian_obat", "tgl_pemberian = '" + tglDari.getText() + "' and status='Ralan' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("pemberian_obat", "tgl_pemberian > '" + tglDari.getText() + "' and status='Ralan' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                
                Sequel.mengedit("pelaksana_pemberian_obat", "tgl_pemberian = '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("pelaksana_pemberian_obat", "tgl_pemberian > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("pemberian_obat", "tgl_pemberian = '" + tglDari.getText() + "' and status='Ralan' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("pemberian_obat", "tgl_pemberian > '" + tglDari.getText() + "' and status='Ralan' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                
                Sequel.mengedit("pelaksana_pemberian_obat", "tgl_pemberian = '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("pelaksana_pemberian_obat", "tgl_pemberian > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("pemberian_obat", "no_rawat='" + TNoRwTerpilih.getText() + "' and status='Ralan'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("pelaksana_pemberian_obat", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("pemberian_obat", "no_rawat='" + TNoRwTujuan.getText() + "' and status='Ralan'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("pelaksana_pemberian_obat", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranKonsulAntarUnit() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("surat_konsul_unit_ranap", "tgl_minta = '" + tglDari.getText() + "' and jam_minta >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("surat_konsul_unit_ranap", "tgl_minta > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("surat_konsul_unit_ranap", "tgl_minta = '" + tglDari.getText() + "' and jam_minta >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("surat_konsul_unit_ranap", "tgl_minta > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("surat_konsul_unit_ranap", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("surat_konsul_unit_ranap", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranDataPersalinan() {
        if (pilihan == 1) {
            Sequel.mengedit("data_persalinan", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("data_persalinan", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void tranSpirometri() {
        if (pilihan == 1) {
            Sequel.mengedit("spirometri", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("spirometri", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void tranSuratIstirahatSakit() {
        if (pilihan == 1) {
            Sequel.mengedit("surat_istirahat_sakit", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("surat_istirahat_sakit", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void tranDataPersalinanDinkes() {
        if (pilihan == 1) {
            Sequel.mengedit("persalinan_dinkes", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("detail_persalinan_dinkes", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("persalinan_dinkes", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("detail_persalinan_dinkes", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void tranSuratKeteranganSakit() {
        if (pilihan == 1) {
            Sequel.mengedit("surat_keterangan_sakit", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("surat_keterangan_sakit", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void tranDPJP() {
        if (pilihan == 1) {
            Sequel.mengedit("dpjp_ranap", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("dpjp_ranap", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void tranPermintaanPeriksaLab() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("permintaan_lab_raza", "tgl_permintaan = '" + tglDari.getText() + "' and jam_permintaan >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("permintaan_lab_raza", "tgl_permintaan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("permintaan_lab_raza", "tgl_permintaan = '" + tglDari.getText() + "' and jam_permintaan >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("permintaan_lab_raza", "tgl_permintaan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("permintaan_lab_raza", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("permintaan_lab_raza", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranPermintaanPeriksaRad() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("permintaan_radiologi", "tgl_permintaan = '" + tglDari.getText() + "' and jam_permintaan >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("permintaan_radiologi", "tgl_permintaan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("permintaan_radiologi", "tgl_permintaan = '" + tglDari.getText() + "' and jam_permintaan >='" + pukulDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("permintaan_radiologi", "tgl_permintaan > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("permintaan_radiologi", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("permintaan_radiologi", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranPemantauanHarianRanap() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            if (pilihan == 1) {
                Sequel.mengedit("pemantauan_harian_24jam", "tgl_pantau = '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("pemantauan_harian_24jam", "tgl_pantau > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                
                Sequel.mengedit("pemantauan_harian_parental", "tgl_pantau = '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("pemantauan_harian_parental", "tgl_pantau > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("pemantauan_harian_24jam", "tgl_pantau = '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("pemantauan_harian_24jam", "tgl_pantau > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                
                Sequel.mengedit("pemantauan_harian_parental", "tgl_pantau = '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("pemantauan_harian_parental", "tgl_pantau > '" + tglDari.getText() + "' "
                        + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            if (pilihan == 1) {                
                Sequel.mengedit("pemantauan_harian_parental", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
                Sequel.mengedit("pemantauan_harian_24jam", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            } else if (pilihan == 2) {
                Sequel.mengedit("pemantauan_harian_parental", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
                Sequel.mengedit("pemantauan_harian_24jam", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            }

            tampil();
            tampilTujuan();
            pilihan = 0;
            ChkTglTran.setSelected(false);
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
            ChkRMranap.setSelected(false);
            ChkRMigd.setSelected(false);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranTriaseIGD() {
        if (pilihan == 1) {
            Sequel.mengedit("triase_igd", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("triase_igd", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void tranAsesmenMedikIGD() {
        if (pilihan == 1) {
            Sequel.mengedit("penilaian_awal_medis_igd", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("penilaian_awal_medis_igd", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void tranAsesmenKeperawatanIGD() {
        if (pilihan == 1) {
            Sequel.mengedit("penilaian_awal_keperawatan_igdrz", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("penilaian_awal_keperawatan_igdrz", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void protokolKemoterapi() {
        if (pilihan == 1) {
            Sequel.mengedit("protokol_kemoterapi", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("protokol_kemoterapi_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("protokol_kemoterapi", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("protokol_kemoterapi_histori", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void asesmenUlangResikoJatuhDewasa() {
        if (pilihan == 1) {
            Sequel.mengedit("asesmen_ulang_resiko_jatuh", "tgl_asesmen = '" + tglDari + "' and jam_asesmen >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("asesmen_ulang_resiko_jatuh", "tgl_asesmen > '" + tglDari + "' and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

            Sequel.mengedit("asesmen_ulang_resiko_jatuh_histori", "tgl_asesmen = '" + tglDari + "' and jam_asesmen >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("asesmen_ulang_resiko_jatuh_histori", "tgl_asesmen > '" + tglDari + "' and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

            Sequel.mengedit("detail_asesmen_ulang_resiko_jatuh", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("detail_asesmen_ulang_resiko_jatuh_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("asesmen_ulang_resiko_jatuh", "tgl_asesmen = '" + tglDari + "' and jam_asesmen >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("asesmen_ulang_resiko_jatuh", "tgl_asesmen > '" + tglDari + "' and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");

            Sequel.mengedit("asesmen_ulang_resiko_jatuh_histori", "tgl_asesmen = '" + tglDari + "' and jam_asesmen >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("asesmen_ulang_resiko_jatuh_histori", "tgl_asesmen > '" + tglDari + "' and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");

            Sequel.mengedit("detail_asesmen_ulang_resiko_jatuh", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("detail_asesmen_ulang_resiko_jatuh_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void asesmenUlangResikoJatuhAnak() {
        if (pilihan == 1) {
            Sequel.mengedit("asesmen_ulang_resiko_jatuh_anak", "tgl_asesmen = '" + tglDari + "' and jam_asesmen >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("asesmen_ulang_resiko_jatuh_anak", "tgl_asesmen > '" + tglDari + "' and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

            Sequel.mengedit("asesmen_ulang_resiko_jatuh_anak_histori", "tgl_asesmen = '" + tglDari + "' and jam_asesmen >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("asesmen_ulang_resiko_jatuh_anak_histori", "tgl_asesmen > '" + tglDari + "' and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

            Sequel.mengedit("detail_asesmen_ulang_resiko_jatuh_anak", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("detail_asesmen_ulang_resiko_jatuh_anak_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("asesmen_ulang_resiko_jatuh_anak", "tgl_asesmen = '" + tglDari + "' and jam_asesmen >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("asesmen_ulang_resiko_jatuh_anak", "tgl_asesmen > '" + tglDari + "' and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");

            Sequel.mengedit("asesmen_ulang_resiko_jatuh_anak_histori", "tgl_asesmen = '" + tglDari + "' and jam_asesmen >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("asesmen_ulang_resiko_jatuh_anak_histori", "tgl_asesmen > '" + tglDari + "' and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");

            Sequel.mengedit("detail_asesmen_ulang_resiko_jatuh_anak", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("detail_asesmen_ulang_resiko_jatuh_anak_histori", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void pengelolaanTransfusiDarah() {
        if (pilihan == 1) {
            Sequel.mengedit("pengelolaan_transfusi_darah", "tanggal = '" + tglDari + "' and jam >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("pengelolaan_transfusi_darah", "tanggal > '" + tglDari + "' and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            
            Sequel.mengedit("pengelolaan_transfusi_darah_histori", "tanggal = '" + tglDari + "' and jam >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("pengelolaan_transfusi_darah_histori", "tanggal > '" + tglDari + "' and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            
        } else if (pilihan == 2) {
            Sequel.mengedit("pengelolaan_transfusi_darah", "tanggal = '" + tglDari + "' and jam >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("pengelolaan_transfusi_darah", "tanggal > '" + tglDari + "' and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            
            Sequel.mengedit("pengelolaan_transfusi_darah_histori", "tanggal = '" + tglDari + "' and jam >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("pengelolaan_transfusi_darah_histori", "tanggal > '" + tglDari + "' and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void monitoringEWSdewasa() {
        if (pilihan == 1) {
            Sequel.mengedit("monitoring_ews_dewasa", "tanggal = '" + tglDari + "' and jam >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("monitoring_ews_dewasa", "tanggal > '" + tglDari + "' and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            
        } else if (pilihan == 2) {
            Sequel.mengedit("monitoring_ews_dewasa", "tanggal = '" + tglDari + "' and jam >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("monitoring_ews_dewasa", "tanggal > '" + tglDari + "' and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void monitoringPediatricEWS() {
        if (pilihan == 1) {
            Sequel.mengedit("monitoring_pews_anak", "tanggal = '" + tglDari + "' and jam >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("monitoring_pews_anak", "tanggal > '" + tglDari + "' and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            
        } else if (pilihan == 2) {
            Sequel.mengedit("monitoring_pews_anak", "tanggal = '" + tglDari + "' and jam >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("monitoring_pews_anak", "tanggal > '" + tglDari + "' and no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }
    
    private void tranAsesKepAnakRanap() {
        if (pilihan == 1) {
            Sequel.mengedit("penilaian_awal_keperawatan_anak_ranap", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_anak_ranap_resiko", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
        } else if (pilihan == 2) {
            Sequel.mengedit("penilaian_awal_keperawatan_anak_ranap", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
            Sequel.mengedit("penilaian_awal_keperawatan_anak_ranap_resiko", "no_rawat='" + TNoRwTujuan.getText() + "'", "no_rawat='" + TNoRwTerpilih.getText() + "'");
        }

        tampil();
        tampilTujuan();
        pilihan = 0;
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        tglDari.setText("");
        pukulDari.setText("");
        ChkRMranap.setSelected(false);
        ChkRMigd.setSelected(false);
    }

    private void pindahData() {
        if (cmbJnsTran.getSelectedIndex() == 1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranFarmasi();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 2) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranRadiologi();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 3) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranLaboratorium();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 4) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranDokter();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 5) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranPetugas();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 6) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranDokterPetugas();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 7) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranDietGizi();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 8) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranAsesMedikDewasaRanap();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 9) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranAsesKepDewasaRanap();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 10) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranCPPTranap();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 11) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranCPPTigd();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 12) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranCatatanResepRanap();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 13) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranCatatanResepIGD();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 14) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranRingkasanPulang();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 15) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranTransferRanap();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 16) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranTransferIGD();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 17) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranCTKranap();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 18) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranPemberianObatranap();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 19) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranPemberianObatIGD();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 20) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranKonsulAntarUnit();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 21) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranDataPersalinan();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 22) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranSpirometri();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 23) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranSuratIstirahatSakit();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 24) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranDataPersalinanDinkes();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 25) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranSuratKeteranganSakit();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 26) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranDPJP();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 27) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranPermintaanPeriksaLab();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 28) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranPermintaanPeriksaRad();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 29) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranPemantauanHarianRanap();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 30) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranTriaseIGD();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 31) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranAsesmenMedikIGD();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 32) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranAsesmenKeperawatanIGD();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 33) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                protokolKemoterapi();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 34) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                asesmenUlangResikoJatuhDewasa();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 35) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                pengelolaanTransfusiDarah();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 36) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                monitoringEWSdewasa();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 37) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranAsesKepAnakRanap();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 38) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranAsesMedikAnakRanap();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 39) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                asesmenUlangResikoJatuhAnak();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 40) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                monitoringPediatricEWS();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 41) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranAsesRestrain();
            }
        } else if (cmbJnsTran.getSelectedIndex() == 42) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data " + cmbJnsTran.getSelectedItem().toString() + " yang dipilih akan dipindah nomor rawatnya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tranObservasiRestrain();
            }
        }
    }
}
