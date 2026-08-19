package kepegawaian;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariJabatan;
import simrskhanza.DlgJabatan;
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public final class DlgPegawai extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private String idSttsWP = "", idSttsKJ = "", idIndex = "", cekmkAwal = "", cekmkAkhir = "";
    private DlgCariDepartemen departemen;
    private DlgCariJabatan jabatan;
    private frmUtama formUtama;

    /** Creates new form DlgPetugas
     * @param parent
     * @param modal */
    public DlgPegawai(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(885,674);

        Object[] row = {"NIP", "Nama Pegawai/Karyawan", "Tmp. Lahir", "Tgl. Lahir", "J.K.", "Alamat", "Pendidikan", "Jabatan", "jnj_jabatan", "Status Aktif",
            "NIK KTP", "Departeman", "Bidang", "Status WP", "Status Kerja", "NPWP", "Gaji Pokok (Rp.)", "Mulai Kerja", "Masa Kerja", "Indexing", "Nama Bank",
            "No. Rekening", "Wajib Masuk", "Pengurang", "Indek", "Mulai Kontrak", "Cuti Diambil", "Dankes", "dep_id",
            "gol_pangkat", "tmt_pangkat", "ms_kerja_thn", "ms_kerja_bln", "nm_diklat", "thn_diklat", "jml_jam", "jurusan_pnddkn", "thn_lulus_sekolah", "batas_umur_pensiun",
            "tgl_pensiun", "eselon", "catatan_mutasi", "cek_mk_awal", "tgl_mk_awal", "cek_mk_akhir", "tgl_mk_akhir", "no_surat", "sumber_gaji"
        };

        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbPegawai.setModel(tabMode);
        tbPegawai.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbPegawai.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 48; i++) {
            TableColumn column = tbPegawai.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(270);
            } else if (i == 6) {
                column.setPreferredWidth(135);
            } else if (i == 7) {
                column.setPreferredWidth(300);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(150);
            } else if (i == 11) {
                column.setPreferredWidth(350);
            } else if (i == 12) {
                column.setPreferredWidth(140);
            } else if (i == 13) {
                column.setPreferredWidth(200);
            } else if (i == 14) {
                column.setPreferredWidth(140);
            } else if (i == 15) {
                column.setPreferredWidth(140);
            } else if (i == 16) {
                column.setPreferredWidth(100);
            } else if (i == 17) {
                column.setPreferredWidth(75);
            } else if (i == 18) {
                column.setPreferredWidth(65);
            } else if (i == 19) {
                column.setPreferredWidth(75);
            } else if (i == 20) {
                column.setPreferredWidth(140);
            } else if (i == 21) {
                column.setPreferredWidth(140);
            } else if (i == 22) {
                column.setPreferredWidth(70);
            } else if (i == 23) {
                column.setPreferredWidth(90);
            } else if (i == 24) {
                column.setPreferredWidth(50);
            } else if (i == 25) {
                column.setPreferredWidth(82);
            } else if (i == 26) {
                column.setPreferredWidth(72);
            } else if (i == 27) {
                column.setPreferredWidth(90);
            } else if (i == 28) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 29) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 30) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 31) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 32) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 33) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 34) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 35) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 36) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 37) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 38) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 39) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 40) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 41) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 42) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 43) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 44) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 45) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 46) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 47) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPegawai.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"NIP", "Nama Pegawai/Karyawan", "Tmp. Lahir", "Tgl. Lahir", "J.K.", "Alamat", "Pendidikan", "Jabatan", "jnj_jabatan", "Status Aktif",
            "NIK KTP", "Departeman", "Bidang", "Status WP", "Status Kerja", "NPWP", "Gaji Pokok (Rp.)", "Mulai Kerja", "Masa Kerja", "Indexing", "Nama Bank",
            "No. Rekening", "Wajib Masuk", "Pengurang", "Indek", "Mulai Kontrak", "Cuti Diambil", "Dankes", "dep_id",
            "gol_pangkat", "tmt_pangkat", "ms_kerja_thn", "ms_kerja_bln", "nm_diklat", "thn_diklat", "jml_jam", "jurusan_pnddkn", "thn_lulus_sekolah", "batas_umur_pensiun",
            "tgl_pensiun", "eselon", "catatan_mutasi", "cek_mk_awal", "tgl_mk_awal", "cek_mk_akhir", "tgl_mk_akhir", "no_surat", "sumber_gaji"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPegawai1.setModel(tabMode1);
        tbPegawai1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPegawai1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 48; i++) {
            TableColumn column = tbPegawai1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(270);
            } else if (i == 6) {
                column.setPreferredWidth(135);
            } else if (i == 7) {
                column.setPreferredWidth(300);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(150);
            } else if (i == 11) {
                column.setPreferredWidth(350);
            } else if (i == 12) {
                column.setPreferredWidth(140);
            } else if (i == 13) {
                column.setPreferredWidth(200);
            } else if (i == 14) {
                column.setPreferredWidth(140);
            } else if (i == 15) {
                column.setPreferredWidth(140);
            } else if (i == 16) {
                column.setPreferredWidth(100);
            } else if (i == 17) {
                column.setPreferredWidth(75);
            } else if (i == 18) {
                column.setPreferredWidth(65);
            } else if (i == 19) {
                column.setPreferredWidth(75);
            } else if (i == 20) {
                column.setPreferredWidth(140);
            } else if (i == 21) {
                column.setPreferredWidth(140);
            } else if (i == 22) {
                column.setPreferredWidth(70);
            } else if (i == 23) {
                column.setPreferredWidth(90);
            } else if (i == 24) {
                column.setPreferredWidth(50);
            } else if (i == 25) {
                column.setPreferredWidth(82);
            } else if (i == 26) {
                column.setPreferredWidth(72);
            } else if (i == 27) {
                column.setPreferredWidth(90);
            } else if (i == 28) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 29) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 30) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 31) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 32) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 33) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 34) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 35) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 36) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 37) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 38) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 39) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 40) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 41) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 42) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 43) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 44) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 45) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 46) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 47) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPegawai1.setDefaultRenderer(Object.class, new WarnaTable());

        TNip.setDocument(new batasInput((int) 20).getKata(TNip));
        TNm.setDocument(new batasInput((int) 200).getKata(TNm));                
        npwp.setDocument(new batasInput((int) 15).getKata(npwp));
        TGapok.setDocument(new batasInput((int) 10).getOnlyAngka(TGapok));
        TTmp.setDocument(new batasInput((int) 150).getKata(TTmp));
        TAlmt.setDocument(new batasInput((int) 200).getKata(TAlmt));
        TKota.setDocument(new batasInput((int) 150).getKata(TKota));        
        TRek.setDocument(new batasInput((int) 50).getKata(TRek));
        TwajibMsk.setDocument(new batasInput((int) 4).getOnlyAngka(TwajibMsk));
        Tpengurang.setDocument(new batasInput((int) 10).getOnlyAngka(Tpengurang));
        Tindek.setDocument(new batasInput((int) 4).getOnlyAngka(Tindek));
        Tcuti.setDocument(new batasInput((int) 3).getOnlyAngka(Tcuti));
        Tdankes.setDocument(new batasInput((int) 10).getOnlyAngka(Tdankes));
        TnoKTP.setDocument(new batasInput((int) 20).getKata(TnoKTP));
        Tgol.setDocument(new batasInput((int) 4).getKata(Tgol));
        TmsKerjaThn.setDocument(new batasInput((int) 2).getOnlyAngka(TmsKerjaThn));
        TmsKerjaBln.setDocument(new batasInput((int) 2).getOnlyAngka(TmsKerjaBln));
        TnmDiklat.setDocument(new batasInput((int) 200).getKata(TnmDiklat));
        TthnDiklat.setDocument(new batasInput((int) 60).getKata(TthnDiklat));
        TjmlJam.setDocument(new batasInput((int) 10).getKata(TjmlJam));
        TjurPendidikan.setDocument(new batasInput((int) 200).getKata(TjurPendidikan));
        TthnLulus.setDocument(new batasInput((int) 4).getOnlyAngka(TthnLulus));
        TbtsUmur.setDocument(new batasInput((int) 2).getOnlyAngka(TbtsUmur));
        Teselon.setDocument(new batasInput((int) 15).getKata(Teselon));
        TcttnMutasi.setDocument(new batasInput((int) 200).getKata(TcttnMutasi));
        TnoSurat.setDocument(new batasInput((int) 200).getKata(TnoSurat));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampilAktif();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampilAktif();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampilAktif();}
            });
        }
        
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

        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnEdit = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        jLabel4 = new widget.Label();
        TTmp = new widget.TextBox();
        CmbJk = new widget.ComboBox();
        TNm = new widget.TextBox();
        jLabel8 = new widget.Label();
        jLabel13 = new widget.Label();
        DTPLahir = new widget.Tanggal();
        jLabel19 = new widget.Label();
        cmbSttsAktif = new widget.ComboBox();
        jLabel20 = new widget.Label();
        jLabel21 = new widget.Label();
        jLabel12 = new widget.Label();
        TAlmt = new widget.TextBox();
        TNip = new widget.TextBox();
        TnoKTP = new widget.TextBox();
        jLabel15 = new widget.Label();
        jLabel17 = new widget.Label();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        jLabel25 = new widget.Label();
        jLabel26 = new widget.Label();
        npwp = new widget.TextBox();
        cmbBid = new widget.ComboBox();
        cmbsttsWP = new widget.ComboBox();
        cmbsttsKJ = new widget.ComboBox();
        jLabel27 = new widget.Label();
        cmbPendidikan = new widget.ComboBox();
        jLabel28 = new widget.Label();
        TGapok = new widget.TextBox();
        jLabel29 = new widget.Label();
        TKota = new widget.TextBox();
        DTPmulaiKJ = new widget.Tanggal();
        jLabel31 = new widget.Label();
        cmbMasaKJ = new widget.ComboBox();
        jLabel32 = new widget.Label();
        cmbIndex = new widget.ComboBox();
        jLabel33 = new widget.Label();
        cmbBank = new widget.ComboBox();
        jLabel34 = new widget.Label();
        TRek = new widget.TextBox();
        jLabel35 = new widget.Label();
        TwajibMsk = new widget.TextBox();
        jLabel36 = new widget.Label();
        Tpengurang = new widget.TextBox();
        jLabel37 = new widget.Label();
        Tindek = new widget.TextBox();
        jLabel38 = new widget.Label();
        DTPmulaiKontrak = new widget.Tanggal();
        jLabel39 = new widget.Label();
        Tcuti = new widget.TextBox();
        jLabel40 = new widget.Label();
        Tdankes = new widget.TextBox();
        TkdDep = new widget.TextBox();
        TnmDepartemen = new widget.TextBox();
        btnDepartemen = new widget.Button();
        jLabel41 = new widget.Label();
        Tgol = new widget.TextBox();
        jLabel42 = new widget.Label();
        TtglTmt = new widget.Tanggal();
        jLabel43 = new widget.Label();
        TmsKerjaThn = new widget.TextBox();
        jLabel44 = new widget.Label();
        TmsKerjaBln = new widget.TextBox();
        jLabel45 = new widget.Label();
        TnmDiklat = new widget.TextBox();
        jLabel46 = new widget.Label();
        TthnDiklat = new widget.TextBox();
        jLabel47 = new widget.Label();
        TjmlJam = new widget.TextBox();
        jLabel14 = new widget.Label();
        jLabel18 = new widget.Label();
        jLabel48 = new widget.Label();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        jLabel53 = new widget.Label();
        TjurPendidikan = new widget.TextBox();
        TthnLulus = new widget.TextBox();
        jLabel22 = new widget.Label();
        TbtsUmur = new widget.TextBox();
        TtglPensiun = new widget.Tanggal();
        jLabel56 = new widget.Label();
        Teselon = new widget.TextBox();
        TcttnMutasi = new widget.TextBox();
        chkTglMsAwal = new widget.CekBox();
        TtglMKawal = new widget.Tanggal();
        chkTglMsAkhir = new widget.CekBox();
        TtglMKakhir = new widget.Tanggal();
        TnoSurat = new widget.TextBox();
        cmbSumber = new widget.ComboBox();
        TkdJbtn = new widget.TextBox();
        TnmJabatan = new widget.TextBox();
        btnJabatan = new widget.Button();
        jLabel30 = new widget.Label();
        ChkInput = new widget.CekBox();
        TabPegawai = new javax.swing.JTabbedPane();
        scrollPane1 = new widget.ScrollPane();
        tbPegawai = new widget.Table();
        scrollPane2 = new widget.ScrollPane();
        tbPegawai1 = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Pegawai/Karyawan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(260, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('T');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+T");
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
        BtnAll.setMnemonic('S');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+S");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 30));
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

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(70, 30));
        panelGlass9.add(jLabel10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(72, 30));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(850, 340));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(850, 137));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("NIP / NR :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 12, 105, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nama Pegawai :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 42, 105, 23);

        TTmp.setForeground(new java.awt.Color(0, 0, 0));
        TTmp.setName("TTmp"); // NOI18N
        TTmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTmpKeyPressed(evt);
            }
        });
        FormInput.add(TTmp);
        TTmp.setBounds(109, 102, 206, 23);

        CmbJk.setForeground(new java.awt.Color(0, 0, 0));
        CmbJk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LAKI-LAKI", "PEREMPUAN" }));
        CmbJk.setName("CmbJk"); // NOI18N
        CmbJk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJkKeyPressed(evt);
            }
        });
        FormInput.add(CmbJk);
        CmbJk.setBounds(109, 72, 95, 23);

        TNm.setForeground(new java.awt.Color(0, 0, 0));
        TNm.setName("TNm"); // NOI18N
        TNm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNmKeyPressed(evt);
            }
        });
        FormInput.add(TNm);
        TNm.setBounds(109, 42, 425, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Jenis Kelamin :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 72, 105, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tmp/Tgl. Lahir :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 102, 105, 23);

        DTPLahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2026" }));
        DTPLahir.setDisplayFormat("dd-MM-yyyy");
        DTPLahir.setName("DTPLahir"); // NOI18N
        DTPLahir.setOpaque(false);
        FormInput.add(DTPLahir);
        DTPLahir.setBounds(319, 102, 90, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Status Aktif :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(355, 222, 80, 23);

        cmbSttsAktif.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsAktif.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AKTIF", "CUTI", "KELUAR", "TENAGA LUAR", "PENSIUN" }));
        cmbSttsAktif.setLightWeightPopupEnabled(false);
        cmbSttsAktif.setName("cmbSttsAktif"); // NOI18N
        cmbSttsAktif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbSttsAktifKeyPressed(evt);
            }
        });
        FormInput.add(cmbSttsAktif);
        cmbSttsAktif.setBounds(442, 222, 105, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Alamat :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(0, 252, 105, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("NIK KTP :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(211, 72, 55, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Jabatan  :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(557, 12, 100, 23);

        TAlmt.setForeground(new java.awt.Color(0, 0, 0));
        TAlmt.setName("TAlmt"); // NOI18N
        TAlmt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlmtKeyPressed(evt);
            }
        });
        FormInput.add(TAlmt);
        TAlmt.setBounds(109, 252, 425, 23);

        TNip.setForeground(new java.awt.Color(0, 0, 0));
        TNip.setName("TNip"); // NOI18N
        TNip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNipKeyPressed(evt);
            }
        });
        FormInput.add(TNip);
        TNip.setBounds(109, 12, 200, 23);

        TnoKTP.setForeground(new java.awt.Color(0, 0, 0));
        TnoKTP.setName("TnoKTP"); // NOI18N
        TnoKTP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoKTPKeyPressed(evt);
            }
        });
        FormInput.add(TnoKTP);
        TnoKTP.setBounds(270, 72, 150, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Departemen :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 132, 105, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Mulai Kerja :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(557, 42, 100, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Bidang :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 162, 105, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Status WP :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(220, 162, 80, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Status Kerja :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 192, 105, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("NPWP :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(240, 192, 60, 23);

        npwp.setForeground(new java.awt.Color(0, 0, 0));
        npwp.setName("npwp"); // NOI18N
        npwp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                npwpKeyPressed(evt);
            }
        });
        FormInput.add(npwp);
        npwp.setBounds(305, 192, 200, 23);

        cmbBid.setForeground(new java.awt.Color(0, 0, 0));
        cmbBid.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbBid.setName("cmbBid"); // NOI18N
        FormInput.add(cmbBid);
        cmbBid.setBounds(109, 162, 110, 23);

        cmbsttsWP.setForeground(new java.awt.Color(0, 0, 0));
        cmbsttsWP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbsttsWP.setName("cmbsttsWP"); // NOI18N
        FormInput.add(cmbsttsWP);
        cmbsttsWP.setBounds(305, 162, 211, 23);

        cmbsttsKJ.setForeground(new java.awt.Color(0, 0, 0));
        cmbsttsKJ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbsttsKJ.setName("cmbsttsKJ"); // NOI18N
        FormInput.add(cmbsttsKJ);
        cmbsttsKJ.setBounds(109, 192, 130, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Pendidikan :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(557, 72, 100, 23);

        cmbPendidikan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPendidikan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbPendidikan.setName("cmbPendidikan"); // NOI18N
        FormInput.add(cmbPendidikan);
        cmbPendidikan.setBounds(661, 72, 165, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Gaji Pokok :");
        jLabel28.setToolTipText("");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(557, 102, 100, 23);

        TGapok.setForeground(new java.awt.Color(0, 0, 0));
        TGapok.setName("TGapok"); // NOI18N
        TGapok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TGapokKeyPressed(evt);
            }
        });
        FormInput.add(TGapok);
        TGapok.setBounds(661, 102, 150, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Kota :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(0, 282, 105, 23);

        TKota.setForeground(new java.awt.Color(0, 0, 0));
        TKota.setName("TKota"); // NOI18N
        TKota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKotaKeyPressed(evt);
            }
        });
        FormInput.add(TKota);
        TKota.setBounds(109, 282, 330, 23);

        DTPmulaiKJ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2026" }));
        DTPmulaiKJ.setDisplayFormat("dd-MM-yyyy");
        DTPmulaiKJ.setName("DTPmulaiKJ"); // NOI18N
        DTPmulaiKJ.setOpaque(false);
        FormInput.add(DTPmulaiKJ);
        DTPmulaiKJ.setBounds(661, 42, 90, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Masa Kerja :");
        jLabel31.setToolTipText("");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(750, 42, 80, 23);

        cmbMasaKJ.setForeground(new java.awt.Color(0, 0, 0));
        cmbMasaKJ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "<1", "PT", "FT>1" }));
        cmbMasaKJ.setName("cmbMasaKJ"); // NOI18N
        FormInput.add(cmbMasaKJ);
        cmbMasaKJ.setBounds(835, 42, 60, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Indexing :");
        jLabel32.setToolTipText("");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(826, 102, 70, 23);

        cmbIndex.setForeground(new java.awt.Color(0, 0, 0));
        cmbIndex.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbIndex.setName("cmbIndex"); // NOI18N
        FormInput.add(cmbIndex);
        cmbIndex.setBounds(901, 102, 60, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Bank :");
        jLabel33.setToolTipText("");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(826, 72, 70, 23);

        cmbBank.setForeground(new java.awt.Color(0, 0, 0));
        cmbBank.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbBank.setName("cmbBank"); // NOI18N
        FormInput.add(cmbBank);
        cmbBank.setBounds(901, 72, 120, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Rekening :");
        jLabel34.setToolTipText("");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(557, 132, 100, 23);

        TRek.setForeground(new java.awt.Color(0, 0, 0));
        TRek.setName("TRek"); // NOI18N
        TRek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRekKeyPressed(evt);
            }
        });
        FormInput.add(TRek);
        TRek.setBounds(661, 132, 200, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Wajib Masuk :");
        jLabel35.setToolTipText("");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(557, 162, 100, 23);

        TwajibMsk.setForeground(new java.awt.Color(0, 0, 0));
        TwajibMsk.setName("TwajibMsk"); // NOI18N
        TwajibMsk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TwajibMskKeyPressed(evt);
            }
        });
        FormInput.add(TwajibMsk);
        TwajibMsk.setBounds(661, 162, 50, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Pengurang :");
        jLabel36.setToolTipText("");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(711, 162, 70, 23);

        Tpengurang.setForeground(new java.awt.Color(0, 0, 0));
        Tpengurang.setName("Tpengurang"); // NOI18N
        Tpengurang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpengurangKeyPressed(evt);
            }
        });
        FormInput.add(Tpengurang);
        Tpengurang.setBounds(786, 162, 125, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Indek :");
        jLabel37.setToolTipText("");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(911, 162, 45, 23);

        Tindek.setForeground(new java.awt.Color(0, 0, 0));
        Tindek.setName("Tindek"); // NOI18N
        Tindek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindekKeyPressed(evt);
            }
        });
        FormInput.add(Tindek);
        Tindek.setBounds(961, 162, 50, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Mulai Kontrak :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(0, 222, 105, 23);

        DTPmulaiKontrak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2026" }));
        DTPmulaiKontrak.setDisplayFormat("dd-MM-yyyy");
        DTPmulaiKontrak.setName("DTPmulaiKontrak"); // NOI18N
        DTPmulaiKontrak.setOpaque(false);
        FormInput.add(DTPmulaiKontrak);
        DTPmulaiKontrak.setBounds(109, 222, 90, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Cuti Diambil :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(557, 192, 100, 23);

        Tcuti.setForeground(new java.awt.Color(0, 0, 0));
        Tcuti.setName("Tcuti"); // NOI18N
        Tcuti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcutiKeyPressed(evt);
            }
        });
        FormInput.add(Tcuti);
        Tcuti.setBounds(661, 192, 50, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Dankes :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(200, 222, 60, 23);

        Tdankes.setForeground(new java.awt.Color(0, 0, 0));
        Tdankes.setName("Tdankes"); // NOI18N
        Tdankes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdankesKeyPressed(evt);
            }
        });
        FormInput.add(Tdankes);
        Tdankes.setBounds(265, 222, 90, 23);

        TkdDep.setEditable(false);
        TkdDep.setForeground(new java.awt.Color(0, 0, 0));
        TkdDep.setName("TkdDep"); // NOI18N
        FormInput.add(TkdDep);
        TkdDep.setBounds(109, 132, 70, 23);

        TnmDepartemen.setEditable(false);
        TnmDepartemen.setForeground(new java.awt.Color(0, 0, 0));
        TnmDepartemen.setName("TnmDepartemen"); // NOI18N
        FormInput.add(TnmDepartemen);
        TnmDepartemen.setBounds(183, 132, 350, 23);

        btnDepartemen.setForeground(new java.awt.Color(0, 0, 0));
        btnDepartemen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDepartemen.setMnemonic('1');
        btnDepartemen.setToolTipText("Alt+1");
        btnDepartemen.setName("btnDepartemen"); // NOI18N
        btnDepartemen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepartemenActionPerformed(evt);
            }
        });
        FormInput.add(btnDepartemen);
        btnDepartemen.setBounds(536, 132, 28, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Gol./Pangkat :");
        jLabel41.setToolTipText("");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(711, 192, 90, 23);

        Tgol.setForeground(new java.awt.Color(0, 0, 0));
        Tgol.setName("Tgol"); // NOI18N
        Tgol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgolKeyPressed(evt);
            }
        });
        FormInput.add(Tgol);
        Tgol.setBounds(806, 192, 60, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("TMT Pangkat :");
        jLabel42.setToolTipText("");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(865, 192, 90, 23);

        TtglTmt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2026" }));
        TtglTmt.setDisplayFormat("dd-MM-yyyy");
        TtglTmt.setName("TtglTmt"); // NOI18N
        TtglTmt.setOpaque(false);
        FormInput.add(TtglTmt);
        TtglTmt.setBounds(960, 192, 90, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Masa Kerja (Thn.)");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(557, 222, 100, 23);

        TmsKerjaThn.setForeground(new java.awt.Color(0, 0, 0));
        TmsKerjaThn.setName("TmsKerjaThn"); // NOI18N
        TmsKerjaThn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmsKerjaThnKeyPressed(evt);
            }
        });
        FormInput.add(TmsKerjaThn);
        TmsKerjaThn.setBounds(661, 222, 50, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Masa Kerja (Bln.) :");
        jLabel44.setToolTipText("");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(711, 222, 110, 23);

        TmsKerjaBln.setForeground(new java.awt.Color(0, 0, 0));
        TmsKerjaBln.setName("TmsKerjaBln"); // NOI18N
        TmsKerjaBln.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmsKerjaBlnKeyPressed(evt);
            }
        });
        FormInput.add(TmsKerjaBln);
        TmsKerjaBln.setBounds(826, 222, 50, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Nama Diklat :");
        jLabel45.setToolTipText("");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(557, 252, 100, 23);

        TnmDiklat.setForeground(new java.awt.Color(0, 0, 0));
        TnmDiklat.setName("TnmDiklat"); // NOI18N
        TnmDiklat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmDiklatKeyPressed(evt);
            }
        });
        FormInput.add(TnmDiklat);
        TnmDiklat.setBounds(661, 252, 390, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Tahun Diklat :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(557, 280, 100, 23);

        TthnDiklat.setForeground(new java.awt.Color(0, 0, 0));
        TthnDiklat.setName("TthnDiklat"); // NOI18N
        TthnDiklat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TthnDiklatKeyPressed(evt);
            }
        });
        FormInput.add(TthnDiklat);
        TthnDiklat.setBounds(661, 280, 110, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Jumlah Jam :");
        jLabel47.setToolTipText("");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(770, 280, 80, 23);

        TjmlJam.setForeground(new java.awt.Color(0, 0, 0));
        TjmlJam.setName("TjmlJam"); // NOI18N
        TjmlJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjmlJamKeyPressed(evt);
            }
        });
        FormInput.add(TjmlJam);
        TjmlJam.setBounds(856, 280, 70, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Jur. Pendidikan :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(1050, 12, 100, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Thn. Lulus Sekolah :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(1030, 42, 120, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Tgl. Pensiun :");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(1050, 72, 100, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Catatan Mutasi :");
        jLabel49.setToolTipText("");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(1050, 102, 100, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText(" Masa Kerja Awal :");
        jLabel50.setToolTipText("");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(1050, 132, 100, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText(" Masa Kerja Akhir :");
        jLabel51.setToolTipText("");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(1050, 162, 100, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("No. Surat (SK) :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(1050, 192, 100, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Sumber Gaji :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(1050, 222, 100, 23);

        TjurPendidikan.setForeground(new java.awt.Color(0, 0, 0));
        TjurPendidikan.setName("TjurPendidikan"); // NOI18N
        TjurPendidikan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjurPendidikanKeyPressed(evt);
            }
        });
        FormInput.add(TjurPendidikan);
        TjurPendidikan.setBounds(1155, 12, 300, 23);

        TthnLulus.setForeground(new java.awt.Color(0, 0, 0));
        TthnLulus.setName("TthnLulus"); // NOI18N
        TthnLulus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TthnLulusKeyPressed(evt);
            }
        });
        FormInput.add(TthnLulus);
        TthnLulus.setBounds(1155, 42, 60, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Batas Umur Pensiun :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(1215, 42, 120, 23);

        TbtsUmur.setForeground(new java.awt.Color(0, 0, 0));
        TbtsUmur.setName("TbtsUmur"); // NOI18N
        TbtsUmur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbtsUmurKeyPressed(evt);
            }
        });
        FormInput.add(TbtsUmur);
        TbtsUmur.setBounds(1340, 42, 60, 23);

        TtglPensiun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2026" }));
        TtglPensiun.setDisplayFormat("dd-MM-yyyy");
        TtglPensiun.setName("TtglPensiun"); // NOI18N
        TtglPensiun.setOpaque(false);
        FormInput.add(TtglPensiun);
        TtglPensiun.setBounds(1155, 72, 90, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Eselon :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(1245, 72, 60, 23);

        Teselon.setForeground(new java.awt.Color(0, 0, 0));
        Teselon.setName("Teselon"); // NOI18N
        Teselon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TeselonKeyPressed(evt);
            }
        });
        FormInput.add(Teselon);
        Teselon.setBounds(1310, 72, 145, 23);

        TcttnMutasi.setForeground(new java.awt.Color(0, 0, 0));
        TcttnMutasi.setName("TcttnMutasi"); // NOI18N
        TcttnMutasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcttnMutasiKeyPressed(evt);
            }
        });
        FormInput.add(TcttnMutasi);
        TcttnMutasi.setBounds(1155, 102, 300, 23);

        chkTglMsAwal.setBackground(new java.awt.Color(255, 255, 250));
        chkTglMsAwal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglMsAwal.setForeground(new java.awt.Color(0, 0, 0));
        chkTglMsAwal.setText("Tanggal :");
        chkTglMsAwal.setBorderPainted(true);
        chkTglMsAwal.setBorderPaintedFlat(true);
        chkTglMsAwal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTglMsAwal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglMsAwal.setName("chkTglMsAwal"); // NOI18N
        chkTglMsAwal.setOpaque(false);
        chkTglMsAwal.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglMsAwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglMsAwalActionPerformed(evt);
            }
        });
        FormInput.add(chkTglMsAwal);
        chkTglMsAwal.setBounds(1155, 132, 70, 23);

        TtglMKawal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2026" }));
        TtglMKawal.setDisplayFormat("dd-MM-yyyy");
        TtglMKawal.setName("TtglMKawal"); // NOI18N
        TtglMKawal.setOpaque(false);
        FormInput.add(TtglMKawal);
        TtglMKawal.setBounds(1230, 132, 90, 23);

        chkTglMsAkhir.setBackground(new java.awt.Color(255, 255, 250));
        chkTglMsAkhir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglMsAkhir.setForeground(new java.awt.Color(0, 0, 0));
        chkTglMsAkhir.setText("Tanggal :");
        chkTglMsAkhir.setBorderPainted(true);
        chkTglMsAkhir.setBorderPaintedFlat(true);
        chkTglMsAkhir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTglMsAkhir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglMsAkhir.setName("chkTglMsAkhir"); // NOI18N
        chkTglMsAkhir.setOpaque(false);
        chkTglMsAkhir.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglMsAkhir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglMsAkhirActionPerformed(evt);
            }
        });
        FormInput.add(chkTglMsAkhir);
        chkTglMsAkhir.setBounds(1155, 162, 70, 23);

        TtglMKakhir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2026" }));
        TtglMKakhir.setDisplayFormat("dd-MM-yyyy");
        TtglMKakhir.setName("TtglMKakhir"); // NOI18N
        TtglMKakhir.setOpaque(false);
        FormInput.add(TtglMKakhir);
        TtglMKakhir.setBounds(1230, 162, 90, 23);

        TnoSurat.setForeground(new java.awt.Color(0, 0, 0));
        TnoSurat.setName("TnoSurat"); // NOI18N
        TnoSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoSuratKeyPressed(evt);
            }
        });
        FormInput.add(TnoSurat);
        TnoSurat.setBounds(1155, 192, 300, 23);

        cmbSumber.setForeground(new java.awt.Color(0, 0, 0));
        cmbSumber.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "APBD", "BLUD" }));
        cmbSumber.setName("cmbSumber"); // NOI18N
        FormInput.add(cmbSumber);
        cmbSumber.setBounds(1155, 222, 60, 23);

        TkdJbtn.setEditable(false);
        TkdJbtn.setForeground(new java.awt.Color(0, 0, 0));
        TkdJbtn.setName("TkdJbtn"); // NOI18N
        FormInput.add(TkdJbtn);
        TkdJbtn.setBounds(661, 12, 64, 23);

        TnmJabatan.setEditable(false);
        TnmJabatan.setForeground(new java.awt.Color(0, 0, 0));
        TnmJabatan.setName("TnmJabatan"); // NOI18N
        FormInput.add(TnmJabatan);
        TnmJabatan.setBounds(730, 12, 260, 23);

        btnJabatan.setForeground(new java.awt.Color(0, 0, 0));
        btnJabatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnJabatan.setMnemonic('1');
        btnJabatan.setToolTipText("Alt+1");
        btnJabatan.setName("btnJabatan"); // NOI18N
        btnJabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJabatanActionPerformed(evt);
            }
        });
        FormInput.add(btnJabatan);
        btnJabatan.setBounds(994, 12, 28, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("tahun");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(1405, 42, 40, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
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

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        TabPegawai.setBackground(new java.awt.Color(250, 255, 245));
        TabPegawai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabPegawai.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabPegawai.setName("TabPegawai"); // NOI18N
        TabPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPegawaiMouseClicked(evt);
            }
        });

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbPegawai.setAutoCreateRowSorter(true);
        tbPegawai.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPegawai.setName("tbPegawai"); // NOI18N
        tbPegawai.getTableHeader().setReorderingAllowed(false);
        tbPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPegawaiMouseClicked(evt);
            }
        });
        tbPegawai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPegawaiKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbPegawai);

        TabPegawai.addTab("Status Aktif", scrollPane1);

        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setOpaque(true);

        tbPegawai1.setAutoCreateRowSorter(true);
        tbPegawai1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPegawai1.setName("tbPegawai1"); // NOI18N
        tbPegawai1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPegawai1MouseClicked(evt);
            }
        });
        tbPegawai1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPegawai1KeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(tbPegawai1);

        TabPegawai.addTab("Status Non Aktif", scrollPane2);

        internalFrame1.add(TabPegawai, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TTmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTmpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DTPLahir.requestFocus();
        }
}//GEN-LAST:event_TTmpKeyPressed

    private void CmbJkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJkKeyPressed
        Valid.pindah(evt, TNm, TnoKTP);
}//GEN-LAST:event_CmbJkKeyPressed

    private void TNmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNmKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            CmbJk.requestFocus();
        }
}//GEN-LAST:event_TNmKeyPressed

    private void cmbSttsAktifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSttsAktifKeyPressed
        Valid.pindah(evt, DTPmulaiKontrak, TAlmt);
}//GEN-LAST:event_cmbSttsAktifKeyPressed

    private void TAlmtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlmtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TKota.requestFocus();
        }
}//GEN-LAST:event_TAlmtKeyPressed

    private void TNipKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNipKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TNm.requestFocus();
        }
}//GEN-LAST:event_TNipKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNip.getText().trim().equals("")) {
            Valid.textKosong(TNip, "NIP/NR");
        } else if (TNm.getText().trim().equals("")) {
            Valid.textKosong(TNm, "nama pegawai");
        } else if (TTmp.getText().trim().equals("")) {
            Valid.textKosong(TTmp, "Tempat lahir");
        } else {       
            if (Sequel.cariInteger("select count(-1) from pegawai where nik='" + TNip.getText() + "'") == 0) {
                cekData();
                Sequel.menyimpan("pegawai", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", 49, new String[]{
                    "0", TNip.getText(), TNm.getText(), CmbJk.getSelectedItem().toString().replaceAll("PEREMPUAN", "Wanita").replaceAll("LAKI-LAKI", "Pria"),
                    TnmJabatan.getText(), TkdJbtn.getText(), TkdDep.getText(), cmbBid.getSelectedItem().toString(), idSttsWP, idSttsKJ, npwp.getText(),
                    cmbPendidikan.getSelectedItem().toString(), TGapok.getText(), TTmp.getText(), Valid.SetTgl(DTPLahir.getSelectedItem() + ""), TAlmt.getText(),
                    TKota.getText(), Valid.SetTgl(DTPmulaiKJ.getSelectedItem() + ""), cmbMasaKJ.getSelectedItem().toString(), idIndex, cmbBank.getSelectedItem().toString(),
                    TRek.getText(), cmbSttsAktif.getSelectedItem().toString(), TwajibMsk.getText(), Tpengurang.getText(), Tindek.getText(), Valid.SetTgl(DTPmulaiKontrak.getSelectedItem() + ""),
                    Tcuti.getText(), Tdankes.getText(), TnoKTP.getText(), Tgol.getText(), Valid.SetTgl(TtglTmt.getSelectedItem() + ""), TmsKerjaThn.getText(), TmsKerjaBln.getText(),
                    TnmDiklat.getText(), TthnDiklat.getText(), TjmlJam.getText(), TjurPendidikan.getText(), TthnLulus.getText(), TbtsUmur.getText(), 
                    Valid.SetTgl(TtglPensiun.getSelectedItem() + ""), Teselon.getText(), TcttnMutasi.getText(), cekmkAwal, Valid.SetTgl(TtglMKawal.getSelectedItem() + ""),
                    cekmkAkhir, Valid.SetTgl(TtglMKakhir.getSelectedItem() + ""), TnoSurat.getText(), cmbSumber.getSelectedItem().toString()
                });

                TabPegawaiMouseClicked(null);
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(rootPane, "NIK/NR : " + TNip.getText() + " sudah tersimpan di database, proses simpan data GAGAL...!!!");
                TNip.requestFocus();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Tcuti, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        ChkInput.setSelected(true);
        isForm(); 
        emptTeks();
        TabPegawaiMouseClicked(null);
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnEdit);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        if (departemen != null) {
            departemen.dispose();
            departemen = null;
        }

        if (jabatan != null) {
            jabatan.dispose();
            jabatan = null;
        }
        frmUtama.getInstance().tutupDialogDiPanelUtama(this);        
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        TabPegawaiMouseClicked(null);
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNip.getText().trim().equals("")) {
            Valid.textKosong(TNip, "NIP/NR");
        } else if (TNm.getText().trim().equals("")) {
            Valid.textKosong(TNm, "nama pegawai");
        } else if (TTmp.getText().trim().equals("")) {
            Valid.textKosong(TTmp, "Tempat lahir");
        } else {
            if (TabPegawai.getSelectedIndex() == 0) {
                if (tbPegawai.getSelectedRow() > -1) {
                    cekData();
                    if (Sequel.mengedittf("pegawai", "nik=?", "nik=?, nama=?, jk=?, jbtn=?, jnj_jabatan=?, departemen=?, bidang=?, stts_wp=?, stts_kerja=?, npwp=?, "
                            + "pendidikan=?, gapok=?, tmp_lahir=?, tgl_lahir=?, alamat=?, kota=?, mulai_kerja=?, ms_kerja=?, indexins=?, bpd=?, rekening=?, stts_aktif=?, "
                            + "wajibmasuk=?, pengurang=?, indek=?, mulai_kontrak=?, cuti_diambil=?, dankes=?, no_ktp=?, gol_pangkat=?, tmt_pangkat=?, ms_kerja_thn=?, "
                            + "ms_kerja_bln=?, nm_diklat=?, thn_diklat=?, jml_jam=?, jurusan_pnddkn=?, thn_lulus_sekolah=?, batas_umur_pensiun=?, tgl_pensiun=?, eselon=?, "
                            + "catatan_mutasi=?, cek_mk_awal=?, tgl_mk_awal=?, cek_mk_akhir=?, tgl_mk_akhir=?, no_surat=?, sumber_gaji=?", 49, new String[]{
                                TNip.getText(), TNm.getText(), CmbJk.getSelectedItem().toString().replaceAll("PEREMPUAN", "Wanita").replaceAll("LAKI-LAKI", "Pria"),
                                TnmJabatan.getText(), TkdJbtn.getText(), TkdDep.getText(), cmbBid.getSelectedItem().toString(), idSttsWP, idSttsKJ, npwp.getText(),
                                cmbPendidikan.getSelectedItem().toString(), TGapok.getText(), TTmp.getText(), Valid.SetTgl(DTPLahir.getSelectedItem() + ""), TAlmt.getText(),
                                TKota.getText(), Valid.SetTgl(DTPmulaiKJ.getSelectedItem() + ""), cmbMasaKJ.getSelectedItem().toString(), idIndex, cmbBank.getSelectedItem().toString(),
                                TRek.getText(), cmbSttsAktif.getSelectedItem().toString(), TwajibMsk.getText(), Tpengurang.getText(), Tindek.getText(), Valid.SetTgl(DTPmulaiKontrak.getSelectedItem() + ""),
                                Tcuti.getText(), Tdankes.getText(), TnoKTP.getText(), Tgol.getText(), Valid.SetTgl(TtglTmt.getSelectedItem() + ""), TmsKerjaThn.getText(), TmsKerjaBln.getText(),
                                TnmDiklat.getText(), TthnDiklat.getText(), TjmlJam.getText(), TjurPendidikan.getText(), TthnLulus.getText(), TbtsUmur.getText(),
                                Valid.SetTgl(TtglPensiun.getSelectedItem() + ""), Teselon.getText(), TcttnMutasi.getText(), cekmkAwal, Valid.SetTgl(TtglMKawal.getSelectedItem() + ""),
                                cekmkAkhir, Valid.SetTgl(TtglMKakhir.getSelectedItem() + ""), TnoSurat.getText(), cmbSumber.getSelectedItem().toString(),
                                tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 0).toString()
                            }) == true) {
                        
                        Sequel.mengedit("rawat_inap_dr", "kd_dokter='" + tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 0).toString() + "'", "kd_dokter='" + TNip.getText() + "'");
                        Sequel.mengedit("rawat_inap_dr", "kd_dokter_mewakili='" + tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 0).toString() + "'", "kd_dokter_mewakili='" + TNip.getText() + "'");

                        TabPegawaiMouseClicked(null);
                        emptTeks();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                    tbPegawai.requestFocus();
                }
            } else {
                if (tbPegawai1.getSelectedRow() > -1) {
                    cekData();
                    if (Sequel.mengedittf("pegawai", "nik=?", "nik=?, nama=?, jk=?, jbtn=?, jnj_jabatan=?, departemen=?, bidang=?, stts_wp=?, stts_kerja=?, npwp=?, "
                            + "pendidikan=?, gapok=?, tmp_lahir=?, tgl_lahir=?, alamat=?, kota=?, mulai_kerja=?, ms_kerja=?, indexins=?, bpd=?, rekening=?, stts_aktif=?, "
                            + "wajibmasuk=?, pengurang=?, indek=?, mulai_kontrak=?, cuti_diambil=?, dankes=?, no_ktp=?, gol_pangkat=?, tmt_pangkat=?, ms_kerja_thn=?, "
                            + "ms_kerja_bln=?, nm_diklat=?, thn_diklat=?, jml_jam=?, jurusan_pnddkn=?, thn_lulus_sekolah=?, batas_umur_pensiun=?, tgl_pensiun=?, eselon=?, "
                            + "catatan_mutasi=?, cek_mk_awal=?, tgl_mk_awal=?, cek_mk_akhir=?, tgl_mk_akhir=?, no_surat=?, sumber_gaji=?", 49, new String[]{
                                TNip.getText(), TNm.getText(), CmbJk.getSelectedItem().toString().replaceAll("PEREMPUAN", "Wanita").replaceAll("LAKI-LAKI", "Pria"),
                                TnmJabatan.getText(), TkdJbtn.getText(), TkdDep.getText(), cmbBid.getSelectedItem().toString(), idSttsWP, idSttsKJ, npwp.getText(),
                                cmbPendidikan.getSelectedItem().toString(), TGapok.getText(), TTmp.getText(), Valid.SetTgl(DTPLahir.getSelectedItem() + ""), TAlmt.getText(),
                                TKota.getText(), Valid.SetTgl(DTPmulaiKJ.getSelectedItem() + ""), cmbMasaKJ.getSelectedItem().toString(), idIndex, cmbBank.getSelectedItem().toString(),
                                TRek.getText(), cmbSttsAktif.getSelectedItem().toString(), TwajibMsk.getText(), Tpengurang.getText(), Tindek.getText(), Valid.SetTgl(DTPmulaiKontrak.getSelectedItem() + ""),
                                Tcuti.getText(), Tdankes.getText(), TnoKTP.getText(), Tgol.getText(), Valid.SetTgl(TtglTmt.getSelectedItem() + ""), TmsKerjaThn.getText(), TmsKerjaBln.getText(),
                                TnmDiklat.getText(), TthnDiklat.getText(), TjmlJam.getText(), TjurPendidikan.getText(), TthnLulus.getText(), TbtsUmur.getText(),
                                Valid.SetTgl(TtglPensiun.getSelectedItem() + ""), Teselon.getText(), TcttnMutasi.getText(), cekmkAwal, Valid.SetTgl(TtglMKawal.getSelectedItem() + ""),
                                cekmkAkhir, Valid.SetTgl(TtglMKakhir.getSelectedItem() + ""), TnoSurat.getText(), cmbSumber.getSelectedItem().toString(),
                                tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 0).toString()
                            }) == true) {
                        
                        Sequel.mengedit("rawat_inap_dr", "kd_dokter='" + tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 0).toString() + "'", "kd_dokter='" + TNip.getText() + "'");
                        Sequel.mengedit("rawat_inap_dr", "kd_dokter_mewakili='" + tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 0).toString() + "'", "kd_dokter_mewakili='" + TNip.getText() + "'");

                        TabPegawaiMouseClicked(null);
                        emptTeks();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                    tbPegawai1.requestFocus();
                }
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnKeluar, BtnBatal);
        }
}//GEN-LAST:event_BtnEditKeyPressed

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
        TabPegawaiMouseClicked(null);
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void tbPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPegawaiMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getDataAktif();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPegawaiMouseClicked

    private void tbPegawaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPegawaiKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataAktif();
                } catch (java.lang.NullPointerException e) {
                }
            }            
        }
}//GEN-LAST:event_tbPegawaiKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        TabPegawaiMouseClicked(null);
        Sequel.cariIsiComboDB("select nama from bidang where nama <>'-'", cmbBid);
        Sequel.cariIsiComboDB("select ktg from stts_wp WHERE stts <>'-'", cmbsttsWP);
        Sequel.cariIsiComboDB("select ktg from stts_kerja WHERE stts <>'-'", cmbsttsKJ);
        Sequel.cariIsiComboDB("select tingkat from pendidikan WHERE tingkat <>'-'", cmbPendidikan);
        Sequel.cariIsiComboDB("select persen from indexins WHERE dep_id <>'-'", cmbIndex);
        Sequel.cariIsiComboDB("select namabank from bank where namabank<>'-'", cmbBank);
    }//GEN-LAST:event_formWindowOpened

    private void TnoKTPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoKTPKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TTmp.requestFocus();
        }
    }//GEN-LAST:event_TnoKTPKeyPressed

    private void npwpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_npwpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DTPmulaiKontrak.requestFocus();
        }
    }//GEN-LAST:event_npwpKeyPressed

    private void TGapokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TGapokKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbIndex.requestFocus();
        }
    }//GEN-LAST:event_TGapokKeyPressed

    private void TKotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKotaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnJabatan.requestFocus();
        }
    }//GEN-LAST:event_TKotaKeyPressed

    private void TRekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRekKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TwajibMsk.requestFocus();
        }
    }//GEN-LAST:event_TRekKeyPressed

    private void TwajibMskKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TwajibMskKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tpengurang.requestFocus();
        }
    }//GEN-LAST:event_TwajibMskKeyPressed

    private void TpengurangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpengurangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tindek.requestFocus();
        }
    }//GEN-LAST:event_TpengurangKeyPressed

    private void TindekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindekKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tcuti.requestFocus();
        }
    }//GEN-LAST:event_TindekKeyPressed

    private void TcutiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcutiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tgol.requestFocus();
        }
    }//GEN-LAST:event_TcutiKeyPressed

    private void TdankesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdankesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbSttsAktif.requestFocus();
        }
    }//GEN-LAST:event_TdankesKeyPressed

    private void tbPegawai1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPegawai1MouseClicked
        if(tabMode1.getRowCount()!=0){
            try {
                getDataNonAktif();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPegawai1MouseClicked

    private void tbPegawai1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPegawai1KeyPressed
        if(tabMode1.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataNonAktif();
                } catch (java.lang.NullPointerException e) {
                }
            }            
        }
    }//GEN-LAST:event_tbPegawai1KeyPressed

    private void TabPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPegawaiMouseClicked
        if (TabPegawai.getSelectedIndex() == 0) {
            tampilAktif();
        } else if (TabPegawai.getSelectedIndex() == 1) {
            tampilNonAktif();
        }
    }//GEN-LAST:event_TabPegawaiMouseClicked

    private void btnDepartemenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepartemenActionPerformed
        initDeparteman();
        akses.setform("DlgPegawai");
        departemen.setSize(886, internalFrame1.getHeight() - 40);
        departemen.setLocationRelativeTo(internalFrame1);
        departemen.setVisible(true);
    }//GEN-LAST:event_btnDepartemenActionPerformed

    private void TgolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgolKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglTmt.requestFocus();
        }
    }//GEN-LAST:event_TgolKeyPressed

    private void TmsKerjaThnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmsKerjaThnKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TmsKerjaBln.requestFocus();
        }
    }//GEN-LAST:event_TmsKerjaThnKeyPressed

    private void TmsKerjaBlnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmsKerjaBlnKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmDiklat.requestFocus();
        }
    }//GEN-LAST:event_TmsKerjaBlnKeyPressed

    private void TnmDiklatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmDiklatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TthnDiklat.requestFocus();
        }
    }//GEN-LAST:event_TnmDiklatKeyPressed

    private void TthnDiklatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TthnDiklatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjmlJam.requestFocus();
        }
    }//GEN-LAST:event_TthnDiklatKeyPressed

    private void TjmlJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjmlJamKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjurPendidikan.requestFocus();
        }
    }//GEN-LAST:event_TjmlJamKeyPressed

    private void TjurPendidikanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjurPendidikanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TthnLulus.requestFocus();
        }
    }//GEN-LAST:event_TjurPendidikanKeyPressed

    private void TthnLulusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TthnLulusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TbtsUmur.requestFocus();
        }
    }//GEN-LAST:event_TthnLulusKeyPressed

    private void TbtsUmurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbtsUmurKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglPensiun.requestFocus();
        }
    }//GEN-LAST:event_TbtsUmurKeyPressed

    private void TeselonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TeselonKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TcttnMutasi.requestFocus();
        }
    }//GEN-LAST:event_TeselonKeyPressed

    private void TcttnMutasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcttnMutasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTglMsAwal.requestFocus();
        }
    }//GEN-LAST:event_TcttnMutasiKeyPressed

    private void TnoSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoSuratKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbSumber.requestFocus();
        }
    }//GEN-LAST:event_TnoSuratKeyPressed

    private void chkTglMsAwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglMsAwalActionPerformed
        TtglMKawal.setDate(new Date());
        if(chkTglMsAwal.isSelected() == true) {
            TtglMKawal.setEnabled(true);
            TtglMKawal.requestFocus();
        } else {
            TtglMKawal.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglMsAwalActionPerformed

    private void chkTglMsAkhirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglMsAkhirActionPerformed
        TtglMKakhir.setDate(new Date());
        if(chkTglMsAkhir.isSelected() == true) {
            TtglMKakhir.setEnabled(true);
            TtglMKakhir.requestFocus();
        } else {
            TtglMKakhir.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglMsAkhirActionPerformed

    private void btnJabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJabatanActionPerformed
        initJabatan();
        akses.setform("DlgPegawai");
        jabatan.setSize(1100, internalFrame1.getHeight() - 40);
        jabatan.setLocationRelativeTo(internalFrame1);
        jabatan.setVisible(true);        
    }//GEN-LAST:event_btnJabatanActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPegawai dialog = new DlgPegawai(new javax.swing.JFrame(), true);
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
    private widget.Button BtnEdit;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkInput;
    private widget.ComboBox CmbJk;
    private widget.Tanggal DTPLahir;
    private widget.Tanggal DTPmulaiKJ;
    private widget.Tanggal DTPmulaiKontrak;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox TAlmt;
    public widget.TextBox TCari;
    private widget.TextBox TGapok;
    private widget.TextBox TKota;
    private widget.TextBox TNip;
    private widget.TextBox TNm;
    private widget.TextBox TRek;
    private widget.TextBox TTmp;
    private javax.swing.JTabbedPane TabPegawai;
    private widget.TextBox TbtsUmur;
    private widget.TextBox TcttnMutasi;
    private widget.TextBox Tcuti;
    private widget.TextBox Tdankes;
    private widget.TextBox Teselon;
    private widget.TextBox Tgol;
    private widget.TextBox Tindek;
    private widget.TextBox TjmlJam;
    private widget.TextBox TjurPendidikan;
    private widget.TextBox TkdDep;
    private widget.TextBox TkdJbtn;
    private widget.TextBox TmsKerjaBln;
    private widget.TextBox TmsKerjaThn;
    private widget.TextBox TnmDepartemen;
    private widget.TextBox TnmDiklat;
    private widget.TextBox TnmJabatan;
    private widget.TextBox TnoKTP;
    private widget.TextBox TnoSurat;
    private widget.TextBox Tpengurang;
    private widget.Tanggal TtglMKakhir;
    private widget.Tanggal TtglMKawal;
    private widget.Tanggal TtglPensiun;
    private widget.Tanggal TtglTmt;
    private widget.TextBox TthnDiklat;
    private widget.TextBox TthnLulus;
    private widget.TextBox TwajibMsk;
    private widget.Button btnDepartemen;
    private widget.Button btnJabatan;
    public widget.CekBox chkTglMsAkhir;
    public widget.CekBox chkTglMsAwal;
    private widget.ComboBox cmbBank;
    private widget.ComboBox cmbBid;
    private widget.ComboBox cmbIndex;
    private widget.ComboBox cmbMasaKJ;
    private widget.ComboBox cmbPendidikan;
    private widget.ComboBox cmbSttsAktif;
    private widget.ComboBox cmbSumber;
    private widget.ComboBox cmbsttsKJ;
    private widget.ComboBox cmbsttsWP;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel56;
    private widget.Label jLabel6;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox npwp;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbPegawai;
    private widget.Table tbPegawai1;
    // End of variables declaration//GEN-END:variables

    private void tampilAktif() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT p.*, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, IF(p.jk='Pria','Laki-laki','Perempuan') jk, CONCAT(p.alamat,', ',p.kota) almt, "
                    + "j.nm_jbtn, d.nama nm_dep, sw.ktg stwp, sk.ktg stsk, format(p.gapok,0) gapok, date_format(p.mulai_kerja,'%d-%m-%Y') ml_krja, "
                    + "i.persen indeksing, p.bpd nm_bank, date_format(p.mulai_kontrak,'%d-%m-%Y') ml_kontrak, d.dep_id, "
                    + "IF(p.tmt_pangkat IS NULL OR p.tmt_pangkat = '0000-00-00',CURDATE(),p.tmt_pangkat) tglTmt, "
                    + "IF(p.tgl_pensiun IS NULL OR p.tgl_pensiun = '0000-00-00',CURDATE(),p.tgl_pensiun) tglPensiun, "
                    + "IF(p.tgl_mk_awal IS NULL OR p.tgl_mk_awal = '0000-00-00',CURDATE(),p.tgl_mk_awal) tglMkAwal, "
                    + "IF(p.tgl_mk_akhir IS NULL OR p.tgl_mk_akhir = '0000-00-00',CURDATE(),p.tgl_mk_akhir) tglMkAkhir FROM pegawai p "
                    + "INNER JOIN jabatan j ON j.kd_jbtn=p.jnj_jabatan INNER JOIN departemen d ON d.dep_id=p.departemen "
                    + "INNER JOIN stts_wp sw ON sw.stts=p.stts_wp INNER JOIN stts_kerja sk ON sk.stts=p.stts_kerja INNER JOIN indexins i ON i.dep_id=p.indexins where "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.nik like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.nama like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.tmp_lahir like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and CONCAT(p.alamat, ', ', p.kota) like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.pendidikan like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.gol_pangkat like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and j.nm_jbtn like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.stts_aktif like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.no_ktp like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and d.nama like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.bidang like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and sw.ktg like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and sk.ktg like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.npwp like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.gapok like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.ms_kerja like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.ms_kerja_thn like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.nm_diklat like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.jml_jam like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.jurusan_pnddkn like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.eselon like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.catatan_mutasi like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.no_surat like ? or "
                    + "p.stts_aktif in ('aktif','tenaga luar') and p.sumber_gaji like ? order by p.nik DESC");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, "%" + TCari.getText().trim() + "%");
                ps.setString(8, "%" + TCari.getText().trim() + "%");
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, "%" + TCari.getText().trim() + "%");
                ps.setString(11, "%" + TCari.getText().trim() + "%");
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, "%" + TCari.getText().trim() + "%");
                ps.setString(14, "%" + TCari.getText().trim() + "%");
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, "%" + TCari.getText().trim() + "%");
                ps.setString(17, "%" + TCari.getText().trim() + "%");
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, "%" + TCari.getText().trim() + "%");
                ps.setString(20, "%" + TCari.getText().trim() + "%");
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, "%" + TCari.getText().trim() + "%");
                ps.setString(23, "%" + TCari.getText().trim() + "%");
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("nik"),
                        rs.getString("nama"),
                        rs.getString("tmp_lahir"),
                        rs.getString("tgl_lhr"),
                        rs.getString("jk"),
                        rs.getString("almt"),
                        rs.getString("pendidikan"),
                        rs.getString("nm_jbtn"),
                        rs.getString("jnj_jabatan"),
                        rs.getString("stts_aktif"),
                        rs.getString("no_ktp"),
                        rs.getString("nm_dep"),
                        rs.getString("bidang"),
                        rs.getString("stwp"),
                        rs.getString("stsk"),
                        rs.getString("npwp"),
                        rs.getString("gapok"),
                        rs.getString("ml_krja"),
                        rs.getString("ms_kerja"),
                        rs.getString("indeksing"),
                        rs.getString("nm_bank"),
                        rs.getString("rekening"),
                        rs.getString("wajibmasuk"),
                        rs.getString("pengurang"),
                        rs.getString("indek"),
                        rs.getString("ml_kontrak"),
                        rs.getString("cuti_diambil"),
                        rs.getString("dankes"),
                        rs.getString("dep_id"),
                        rs.getString("gol_pangkat"),
                        rs.getString("tglTmt"),
                        rs.getString("ms_kerja_thn"),
                        rs.getString("ms_kerja_bln"),
                        rs.getString("nm_diklat"),
                        rs.getString("thn_diklat"),
                        rs.getString("jml_jam"),
                        rs.getString("jurusan_pnddkn"),
                        rs.getString("thn_lulus_sekolah"),
                        rs.getString("batas_umur_pensiun"),
                        rs.getString("tglPensiun"),
                        rs.getString("eselon"),
                        rs.getString("catatan_mutasi"),
                        rs.getString("cek_mk_awal"),
                        rs.getString("tglMkAwal"),
                        rs.getString("cek_mk_akhir"),
                        rs.getString("tglMkAkhir"),
                        rs.getString("no_surat"),
                        rs.getString("sumber_gaji")
                    });
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }
    
    private void tampilNonAktif() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT p.*, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, IF(p.jk='Pria','Laki-laki','Perempuan') jk, CONCAT(p.alamat,', ',p.kota) almt, "
                    + "j.nm_jbtn, d.nama nm_dep, sw.ktg stwp, sk.ktg stsk, format(p.gapok,0) gapok, date_format(p.mulai_kerja,'%d-%m-%Y') ml_krja, "
                    + "i.persen indeksing, p.bpd nm_bank, date_format(p.mulai_kontrak,'%d-%m-%Y') ml_kontrak, d.dep_id, "
                    + "IF(p.tmt_pangkat IS NULL OR p.tmt_pangkat = '0000-00-00',CURDATE(),p.tmt_pangkat) tglTmt, "
                    + "IF(p.tgl_pensiun IS NULL OR p.tgl_pensiun = '0000-00-00',CURDATE(),p.tgl_pensiun) tglPensiun, "
                    + "IF(p.tgl_mk_awal IS NULL OR p.tgl_mk_awal = '0000-00-00',CURDATE(),p.tgl_mk_awal) tglMkAwal, "
                    + "IF(p.tgl_mk_akhir IS NULL OR p.tgl_mk_akhir = '0000-00-00',CURDATE(),p.tgl_mk_akhir) tglMkAkhir FROM pegawai p "
                    + "INNER JOIN jabatan j ON j.kd_jbtn=p.jnj_jabatan INNER JOIN departemen d ON d.dep_id=p.departemen "
                    + "INNER JOIN stts_wp sw ON sw.stts=p.stts_wp INNER JOIN stts_kerja sk ON sk.stts=p.stts_kerja INNER JOIN indexins i ON i.dep_id=p.indexins where "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.nik like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.nama like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.tmp_lahir like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and CONCAT(p.alamat, ', ', p.kota) like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.pendidikan like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.gol_pangkat like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and j.nm_jbtn like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.stts_aktif like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.no_ktp like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and d.nama like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.bidang like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and sw.ktg like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and sk.ktg like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.npwp like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.gapok like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.ms_kerja like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.ms_kerja_thn like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.nm_diklat like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.jml_jam like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.jurusan_pnddkn like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.eselon like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.catatan_mutasi like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.no_surat like ? or "
                    + "p.stts_aktif not in ('aktif','tenaga luar') and p.sumber_gaji like ? order by p.nik DESC");
            try {
                ps1.setString(1, "%" + TCari.getText().trim() + "%");
                ps1.setString(2, "%" + TCari.getText().trim() + "%");
                ps1.setString(3, "%" + TCari.getText().trim() + "%");
                ps1.setString(4, "%" + TCari.getText().trim() + "%");
                ps1.setString(5, "%" + TCari.getText().trim() + "%");
                ps1.setString(6, "%" + TCari.getText().trim() + "%");
                ps1.setString(7, "%" + TCari.getText().trim() + "%");
                ps1.setString(8, "%" + TCari.getText().trim() + "%");
                ps1.setString(9, "%" + TCari.getText().trim() + "%");
                ps1.setString(10, "%" + TCari.getText().trim() + "%");
                ps1.setString(11, "%" + TCari.getText().trim() + "%");
                ps1.setString(12, "%" + TCari.getText().trim() + "%");
                ps1.setString(13, "%" + TCari.getText().trim() + "%");
                ps1.setString(14, "%" + TCari.getText().trim() + "%");
                ps1.setString(15, "%" + TCari.getText().trim() + "%");
                ps1.setString(16, "%" + TCari.getText().trim() + "%");
                ps1.setString(17, "%" + TCari.getText().trim() + "%");
                ps1.setString(18, "%" + TCari.getText().trim() + "%");
                ps1.setString(19, "%" + TCari.getText().trim() + "%");
                ps1.setString(20, "%" + TCari.getText().trim() + "%");
                ps1.setString(21, "%" + TCari.getText().trim() + "%");
                ps1.setString(22, "%" + TCari.getText().trim() + "%");
                ps1.setString(23, "%" + TCari.getText().trim() + "%");
                ps1.setString(24, "%" + TCari.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        rs1.getString("nik"),
                        rs1.getString("nama"),
                        rs1.getString("tmp_lahir"),
                        rs1.getString("tgl_lhr"),
                        rs1.getString("jk"),
                        rs1.getString("almt"),
                        rs1.getString("pendidikan"),
                        rs1.getString("nm_jbtn"),
                        rs1.getString("jnj_jabatan"),
                        rs1.getString("stts_aktif"),
                        rs1.getString("no_ktp"),
                        rs1.getString("nm_dep"),
                        rs1.getString("bidang"),
                        rs1.getString("stwp"),
                        rs1.getString("stsk"),
                        rs1.getString("npwp"),
                        rs1.getString("gapok"),
                        rs1.getString("ml_krja"),
                        rs1.getString("ms_kerja"),
                        rs1.getString("indeksing"),
                        rs1.getString("nm_bank"),
                        rs1.getString("rekening"),
                        rs1.getString("wajibmasuk"),
                        rs1.getString("pengurang"),
                        rs1.getString("indek"),
                        rs1.getString("ml_kontrak"),
                        rs1.getString("cuti_diambil"),
                        rs1.getString("dankes"),
                        rs1.getString("dep_id"),
                        rs1.getString("gol_pangkat"),
                        rs1.getString("tglTmt"),
                        rs1.getString("ms_kerja_thn"),
                        rs1.getString("ms_kerja_bln"),
                        rs1.getString("nm_diklat"),
                        rs1.getString("thn_diklat"),
                        rs1.getString("jml_jam"),
                        rs1.getString("jurusan_pnddkn"),
                        rs1.getString("thn_lulus_sekolah"),
                        rs1.getString("batas_umur_pensiun"),
                        rs1.getString("tglPensiun"),
                        rs1.getString("eselon"),
                        rs1.getString("catatan_mutasi"),
                        rs1.getString("cek_mk_awal"),
                        rs1.getString("tglMkAwal"),
                        rs1.getString("cek_mk_akhir"),
                        rs1.getString("tglMkAkhir"),
                        rs1.getString("no_surat"),
                        rs1.getString("sumber_gaji")
                    });
                }
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
        LCount.setText("" + tabMode1.getRowCount());
    }

    public void emptTeks() {
        TNip.setText("");
        TNm.setText("");
        CmbJk.setSelectedIndex(0);
        TTmp.setText("");
        DTPLahir.setDate(new Date());
        TkdDep.setText("-");
        TnmDepartemen.setText("-");
        cmbBid.setSelectedIndex(0);
        cmbsttsWP.setSelectedIndex(0);
        cmbsttsKJ.setSelectedIndex(0);
        npwp.setText("");
        DTPmulaiKontrak.setDate(new Date());
        cmbSttsAktif.setSelectedIndex(0);        
        TAlmt.setText("");        
        TnoKTP.setText("");
        TKota.setText("");
        TkdJbtn.setText("-");
        TnmJabatan.setText("-");
        cmbPendidikan.setSelectedIndex(0);
        TGapok.setText("0");
        DTPmulaiKJ.setDate(new Date());
        cmbMasaKJ.setSelectedIndex(0);
        cmbIndex.setSelectedIndex(0);
        cmbBank.setSelectedIndex(0);
        TRek.setText("");
        TwajibMsk.setText("0");
        Tpengurang.setText("0");
        Tindek.setText("0");
        Tdankes.setText("0");
        Tcuti.setText("0");        
        Tgol.setText("");
        TtglTmt.setDate(new Date());
        TmsKerjaThn.setText("");
        TmsKerjaThn.setText("");
        TnmDiklat.setText("");
        TthnDiklat.setText("");
        TjmlJam.setText("");
        TjurPendidikan.setText("");
        TthnLulus.setText("");
        TbtsUmur.setText("");
        TtglPensiun.setDate(new Date());
        Teselon.setText("");
        TcttnMutasi.setText("");
        chkTglMsAwal.setSelected(false);
        TtglMKawal.setDate(new Date());
        TtglMKawal.setEnabled(false);        
        chkTglMsAkhir.setSelected(false);
        TtglMKakhir.setDate(new Date());
        TtglMKakhir.setEnabled(false);
        TnoSurat.setText("");
        cmbSumber.setSelectedIndex(0);        
    }

    private void getDataAktif() {
        cekmkAwal = "";
        cekmkAkhir = "";
        
        if (tbPegawai.getSelectedRow() != -1) {
            TNip.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 0).toString());            
            TNm.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 1).toString());
            TnoKTP.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 10).toString());
            TTmp.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 2).toString());
            Valid.SetTgl(DTPLahir, Sequel.cariIsi("select tgl_lahir from pegawai where nik='" + TNip.getText() + "'"));
            TkdDep.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 28).toString());
            TnmDepartemen.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 11).toString());
            cmbBid.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 12).toString());
            cmbsttsWP.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 13).toString());
            cmbsttsKJ.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 14).toString());
            npwp.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 15).toString());
            Valid.SetTgl(DTPmulaiKontrak, Sequel.cariIsi("select IF(mulai_kontrak IS NULL OR mulai_kontrak = '0000-00-00',CURDATE(),mulai_kontrak) "
                    + "from pegawai where nik='" + TNip.getText() + "'"));
            cmbSttsAktif.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 9).toString());
            TAlmt.setText(Sequel.cariIsi("select alamat from pegawai where nik='" + TNip.getText() + "'"));
            TKota.setText(Sequel.cariIsi("select kota from pegawai where nik='" + TNip.getText() + "'"));
            TkdJbtn.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 8).toString());
            TnmJabatan.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 7).toString());
            cmbPendidikan.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 6).toString());
            TGapok.setText(Sequel.cariIsi("select gapok from pegawai where nik='" + TNip.getText() + "'"));
            Valid.SetTgl(DTPmulaiKJ, Sequel.cariIsi("select IF(mulai_kerja IS NULL OR mulai_kerja = '0000-00-00',CURDATE(),mulai_kerja) "
                    + "from pegawai where nik='" + TNip.getText() + "'"));
            cmbMasaKJ.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 18).toString());
            cmbIndex.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 19).toString());
            cmbBank.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 20).toString());
            TRek.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 21).toString());
            TwajibMsk.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 22).toString());
            Tpengurang.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 23).toString());
            Tindek.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 24).toString());
            Tdankes.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 27).toString());
            Tcuti.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 26).toString());
            
            Tgol.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 29).toString());
            Valid.SetTgl(TtglTmt, tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 30).toString());
            TmsKerjaThn.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 31).toString());
            TmsKerjaBln.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 32).toString());
            TnmDiklat.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 33).toString());
            TthnDiklat.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 34).toString());
            TjmlJam.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 35).toString());
            TjurPendidikan.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 36).toString());
            TthnLulus.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 37).toString());
            TbtsUmur.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 38).toString());
            Valid.SetTgl(TtglPensiun, tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 39).toString());
            Teselon.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 40).toString());
            TcttnMutasi.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 41).toString());
            cekmkAwal = tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 42).toString();
            Valid.SetTgl(TtglMKawal, tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 43).toString());
            cekmkAkhir = tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 44).toString();
            Valid.SetTgl(TtglMKakhir, tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 45).toString());
            TnoSurat.setText(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 46).toString());
            cmbSumber.setSelectedItem(tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 47).toString());
            
            switch (tbPegawai.getValueAt(tbPegawai.getSelectedRow(), 4).toString()) {
                case "Laki-laki":
                    CmbJk.setSelectedItem("LAKI-LAKI");
                    break;
                case "Perempuan":
                    CmbJk.setSelectedItem("PEREMPUAN");
                    break;
            }

            if (cekmkAwal.equals("ya")) {
                chkTglMsAwal.setSelected(true);
                TtglMKawal.setEnabled(true);
            } else {
                chkTglMsAwal.setSelected(false);
                TtglMKawal.setEnabled(false);
            }
            
            if (cekmkAkhir.equals("ya")) {
                chkTglMsAkhir.setSelected(true);
                TtglMKakhir.setEnabled(true);
            } else {
                chkTglMsAkhir.setSelected(false);
                TtglMKakhir.setEnabled(false);
            }
        }
    }
    
    private void getDataNonAktif() {
        cekmkAwal = "";
        cekmkAkhir = "";
        
        if (tbPegawai1.getSelectedRow() != -1) {
            TNip.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 0).toString());            
            TNm.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 1).toString());
            TnoKTP.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 10).toString());
            TTmp.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 2).toString());
            Valid.SetTgl(DTPLahir, Sequel.cariIsi("select tgl_lahir from pegawai where nik='" + TNip.getText() + "'"));
            TkdDep.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 28).toString());
            TnmDepartemen.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 11).toString());
            cmbBid.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 12).toString());
            cmbsttsWP.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 13).toString());
            cmbsttsKJ.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 14).toString());
            npwp.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 15).toString());
            Valid.SetTgl(DTPmulaiKontrak, Sequel.cariIsi("select IF(mulai_kontrak IS NULL OR mulai_kontrak = '0000-00-00',CURDATE(),mulai_kontrak) "
                    + "from pegawai where nik='" + TNip.getText() + "'"));
            cmbSttsAktif.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 9).toString());
            TAlmt.setText(Sequel.cariIsi("select alamat from pegawai where nik='" + TNip.getText() + "'"));
            TKota.setText(Sequel.cariIsi("select kota from pegawai where nik='" + TNip.getText() + "'"));
            TkdJbtn.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 8).toString());
            TnmJabatan.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 7).toString());
            cmbPendidikan.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 6).toString());
            TGapok.setText(Sequel.cariIsi("select gapok from pegawai where nik='" + TNip.getText() + "'"));
            Valid.SetTgl(DTPmulaiKJ, Sequel.cariIsi("select IF(mulai_kerja IS NULL OR mulai_kerja = '0000-00-00',CURDATE(),mulai_kerja) "
                    + "from pegawai where nik='" + TNip.getText() + "'"));
            cmbMasaKJ.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 18).toString());
            cmbIndex.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 19).toString());
            cmbBank.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 20).toString());
            TRek.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 21).toString());
            TwajibMsk.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 22).toString());
            Tpengurang.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 23).toString());
            Tindek.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 24).toString());
            Tdankes.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 27).toString());
            Tcuti.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 26).toString());
            
            Tgol.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 29).toString());
            Valid.SetTgl(TtglTmt, tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 30).toString());
            TmsKerjaThn.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 31).toString());
            TmsKerjaBln.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 32).toString());
            TnmDiklat.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 33).toString());
            TthnDiklat.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 34).toString());
            TjmlJam.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 35).toString());
            TjurPendidikan.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 36).toString());
            TthnLulus.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 37).toString());
            TbtsUmur.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 38).toString());
            Valid.SetTgl(TtglPensiun, tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 39).toString());
            Teselon.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 40).toString());
            TcttnMutasi.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 41).toString());
            cekmkAwal = tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 42).toString();
            Valid.SetTgl(TtglMKawal, tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 43).toString());
            cekmkAkhir = tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 44).toString();
            Valid.SetTgl(TtglMKakhir, tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 45).toString());
            TnoSurat.setText(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 46).toString());
            cmbSumber.setSelectedItem(tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 47).toString());

            switch (tbPegawai1.getValueAt(tbPegawai1.getSelectedRow(), 4).toString()) {
                case "Laki-laki":
                    CmbJk.setSelectedItem("LAKI-LAKI");
                    break;
                case "Perempuan":
                    CmbJk.setSelectedItem("PEREMPUAN");
                    break;
            }
            
            if (cekmkAkhir.equals("ya")) {
                chkTglMsAkhir.setSelected(true);
                TtglMKakhir.setEnabled(true);
            } else {
                chkTglMsAkhir.setSelected(false);
                TtglMKakhir.setEnabled(false);
            }
        }
    }

    public JTextField getTextField(){
        return TNip;
    }

    public JTable getTable(){
        return tbPegawai;
    }
    
    public void isForm(){
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 340));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpegawai_admin());
        BtnEdit.setEnabled(akses.getpegawai_admin());        
    }
    
    private void cekData() {
        idSttsWP = "";
        idSttsKJ = "";
        idIndex = "";
        idIndex = Sequel.cariIsi("select dep_id from indexins WHERE persen='" + cmbIndex.getSelectedItem().toString() + "'");
        idSttsKJ = Sequel.cariIsi("select stts from stts_kerja where ktg='" + cmbsttsKJ.getSelectedItem().toString() + "'");
        idSttsWP = Sequel.cariIsi("select stts from stts_wp where ktg='" + cmbsttsWP.getSelectedItem().toString() + "'");

        if (TGapok.getText().equals("")) {
            TGapok.setText("0");
        }
        
        if (TRek.getText().equals("")) {
            TRek.setText("-");
        }
        
        if (TwajibMsk.getText().equals("")) {
            TwajibMsk.setText("0");
        }
        
        if (Tpengurang.getText().equals("")) {
            Tpengurang.setText("0");
        }
        
        if (Tindek.getText().equals("")) {
            Tindek.setText("0");
        }
        
        if (Tdankes.getText().equals("")) {
            Tdankes.setText("0");
        }
        
        if (Tcuti.getText().equals("")) {
            Tcuti.setText("0");
        }
        
        if (npwp.getText().equals("")) {
            npwp.setText("-");
        }
        
        if (chkTglMsAwal.isSelected() == true) {
            cekmkAwal = "ya";
        } else {
            cekmkAwal = "tidak";
        }
        
        if (chkTglMsAkhir.isSelected() == true) {
            cekmkAkhir = "ya";
        } else {
            cekmkAkhir = "tidak";
        }
    }
    
    public void awalData() {
        TabPegawaiMouseClicked(null);
        Sequel.cariIsiComboDB("select nama from bidang where nama <>'-'", cmbBid);
        Sequel.cariIsiComboDB("select ktg from stts_wp WHERE stts <>'-'", cmbsttsWP);
        Sequel.cariIsiComboDB("select ktg from stts_kerja WHERE stts <>'-'", cmbsttsKJ);
        Sequel.cariIsiComboDB("select tingkat from pendidikan WHERE tingkat <>'-'", cmbPendidikan);
        Sequel.cariIsiComboDB("select persen from indexins WHERE dep_id <>'-'", cmbIndex);
        Sequel.cariIsiComboDB("select namabank from bank where namabank<>'-'", cmbBank);
    }
    
    private void initDeparteman() {
        if (departemen == null) {
            departemen = new DlgCariDepartemen(null, false);

            departemen.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    DlgCariDepartemen dlg = (DlgCariDepartemen) e.getWindow();
                    if (dlg.getTable().getSelectedRow() != -1) {
                        int row = dlg.getTable().getSelectedRow();
                        TkdDep.setText(dlg.getTable().getValueAt(row, 0).toString());
                        TnmDepartemen.setText(dlg.getTable().getValueAt(row, 1).toString());
                        btnDepartemen.requestFocus();
                    }
                    departemen = null;
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

            departemen.getTable().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (akses.getform().equals("DlgPegawai")) {
                        if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                            departemen.dispose();
                        }
                    }
                }
            });
        }
    }
    
    private void initJabatan() {
        if (jabatan == null) {
            jabatan = new DlgCariJabatan(null, false);

            jabatan.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    DlgCariJabatan dlg = (DlgCariJabatan) e.getWindow();
                    if (dlg.getTable().getSelectedRow() != -1) {
                        int row = dlg.getTable().getSelectedRow();
                        TkdJbtn.setText(dlg.getTable().getValueAt(row, 0).toString());
                        TnmJabatan.setText(dlg.getTable().getValueAt(row, 1).toString());
                        btnJabatan.requestFocus();
                    }
                    jabatan = null;
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

            jabatan.getTable().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (akses.getform().equals("DlgPegawai")) {
                        if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                            jabatan.dispose();
                        }
                    }
                }
            });
        }
    }
}
