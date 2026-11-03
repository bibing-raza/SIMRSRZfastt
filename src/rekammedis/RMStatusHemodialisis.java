package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
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
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public class RMStatusHemodialisis extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private int i = 0, x = 0;
    private String cekEmer = "", cekTeren = "", cekTglOps = "", cekTglDibuat = "", cekTglInser = "", cekTglOffShort = "", cekTglOffLong = "", cekTglMulaiFemo = "",
            tglOps = "", tglDibuat = "", tglInsersi = "", tglOffShort = "", tglOffLong = "", tglMulaiFemo = "", tglReg = "", tglSekarang = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMStatusHemodialisis(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Alamat", "Tgl. Mulai HD", "Tgl. Kunjungan", "No. Status HD",
            "diagnosa_etiologi", "tgl_mulai_hd", "emergensi", "terencana", "cimino_lokasi1", "cimino_lokasi2", "cimino_lokasi3", "cimino_lokasi4",
            "cek_tgl_operasi", "tgl_operasi", "cek_tgl_av_graft", "tgl_av_graft", "cek_tgl_insersi_catheter", "tgl_insersi_catheter", "short_lokasi", "short_tgl_insersi",
            "cek_short_tgl_off", "short_tgl_off", "long_lokasi", "long_tgl_berhenti", "cek_long_tgl_off", "long_tgl_off", "cek_tgl_mulai_femoral", "tgl_femoral",
            "diagnosa1", "diagnosa2", "diagnosa3", "diagnosa4", "diagnosa5", "tgl_diag1", "tgl_diag2", "tgl_diag3", "tgl_diag4", "tgl_diag5", "komplikasi1", "komplikasi2",
            "komplikasi3", "komplikasi4", "komplikasi5", "tgl_komplikasi1", "tgl_komplikasi2", "tgl_komplikasi3", "tgl_komplikasi4", "tgl_komplikasi5", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbStatus.setModel(tabMode);
        tbStatus.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbStatus.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 53; i++) {
            TableColumn column = tbStatus.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(75);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            } else if (i == 19) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 20) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 21) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 22) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 23) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 24) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 25) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 26) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 27) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            } else if (i == 48) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 49) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 50) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 51) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 52) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbStatus.setDefaultRenderer(Object.class, new WarnaTable());

        TnoStatus.setDocument(new batasInput((int) 10).getKata(TnoStatus));
        Tnotelp.setDocument(new batasInput((byte) 16).getOnlyAngka(Tnotelp));
        TdiagEtiologi.setDocument(new batasInput((int) 200).getKata(TdiagEtiologi));
        Tlokasi1.setDocument(new batasInput((int) 200).getKata(Tlokasi1));
        Tlokasi2.setDocument(new batasInput((int) 200).getKata(Tlokasi2));
        Tlokasi3.setDocument(new batasInput((int) 200).getKata(Tlokasi3));
        Tlokasi4.setDocument(new batasInput((int) 200).getKata(Tlokasi4));
        TlokasiShort.setDocument(new batasInput((int) 200).getKata(TlokasiShort));
        TlokasiLong.setDocument(new batasInput((int) 200).getKata(TlokasiLong));
        Tdiag1.setDocument(new batasInput((int) 200).getKata(Tdiag1));
        Tdiag2.setDocument(new batasInput((int) 200).getKata(Tdiag2));
        Tdiag3.setDocument(new batasInput((int) 200).getKata(Tdiag3));
        Tdiag4.setDocument(new batasInput((int) 200).getKata(Tdiag4));
        Tdiag5.setDocument(new batasInput((int) 200).getKata(Tdiag5));
        Tkomplikasi1.setDocument(new batasInput((int) 200).getKata(Tkomplikasi1));
        Tkomplikasi2.setDocument(new batasInput((int) 200).getKata(Tkomplikasi2));
        Tkomplikasi3.setDocument(new batasInput((int) 200).getKata(Tkomplikasi3));
        Tkomplikasi4.setDocument(new batasInput((int) 200).getKata(Tkomplikasi4));
        Tkomplikasi5.setDocument(new batasInput((int) 200).getKata(Tkomplikasi5));        
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
        panelGlass11 = new widget.panelisi();
        Scroll1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel5 = new widget.Label();
        Talamat = new widget.TextBox();
        jLabel8 = new widget.Label();
        Tnotelp = new widget.TextBox();
        jLabel9 = new widget.Label();
        Tumur = new widget.TextBox();
        jLabel10 = new widget.Label();
        TtglLahir = new widget.TextBox();
        jLabel11 = new widget.Label();
        TdiagEtiologi = new widget.TextBox();
        jLabel12 = new widget.Label();
        TtglMulaiHD = new widget.Tanggal();
        chkEmergensi = new widget.CekBox();
        chkTerencana = new widget.CekBox();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        jLabel15 = new widget.Label();
        Tlokasi1 = new widget.TextBox();
        jLabel16 = new widget.Label();
        Tlokasi2 = new widget.TextBox();
        jLabel17 = new widget.Label();
        Tlokasi3 = new widget.TextBox();
        jLabel18 = new widget.Label();
        Tlokasi4 = new widget.TextBox();
        chkTglOps = new widget.CekBox();
        TtglOps = new widget.Tanggal();
        jLabel19 = new widget.Label();
        chkTglDibuat = new widget.CekBox();
        TtglDibuat = new widget.Tanggal();
        jLabel20 = new widget.Label();
        chkTglInsersi = new widget.CekBox();
        TtglInsersi = new widget.Tanggal();
        jLabel21 = new widget.Label();
        TlokasiShort = new widget.TextBox();
        jLabel22 = new widget.Label();
        TtglInsersiShort = new widget.Tanggal();
        chkTglOffShort = new widget.CekBox();
        TtglOffShort = new widget.Tanggal();
        jLabel23 = new widget.Label();
        TlokasiLong = new widget.TextBox();
        jLabel24 = new widget.Label();
        TtglBerhenti = new widget.Tanggal();
        chkTglOffLong = new widget.CekBox();
        TtglOffLong = new widget.Tanggal();
        chkTglMulaiFemo = new widget.CekBox();
        TtglMulaiFemo = new widget.Tanggal();
        jLabel26 = new widget.Label();
        jLabel25 = new widget.Label();
        jLabel27 = new widget.Label();
        Tdiag1 = new widget.TextBox();
        jLabel28 = new widget.Label();
        TtglDiag1 = new widget.Tanggal();
        jLabel29 = new widget.Label();
        Tdiag2 = new widget.TextBox();
        jLabel30 = new widget.Label();
        TtglDiag2 = new widget.Tanggal();
        jLabel31 = new widget.Label();
        Tdiag3 = new widget.TextBox();
        jLabel32 = new widget.Label();
        TtglDiag3 = new widget.Tanggal();
        jLabel33 = new widget.Label();
        Tdiag4 = new widget.TextBox();
        jLabel34 = new widget.Label();
        TtglDiag4 = new widget.Tanggal();
        jLabel35 = new widget.Label();
        Tdiag5 = new widget.TextBox();
        jLabel36 = new widget.Label();
        TtglDiag5 = new widget.Tanggal();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        Tkomplikasi1 = new widget.TextBox();
        jLabel39 = new widget.Label();
        TtglKom1 = new widget.Tanggal();
        jLabel40 = new widget.Label();
        Tkomplikasi2 = new widget.TextBox();
        jLabel41 = new widget.Label();
        TtglKom2 = new widget.Tanggal();
        jLabel42 = new widget.Label();
        Tkomplikasi3 = new widget.TextBox();
        jLabel43 = new widget.Label();
        TtglKom3 = new widget.Tanggal();
        jLabel44 = new widget.Label();
        Tkomplikasi4 = new widget.TextBox();
        jLabel45 = new widget.Label();
        TtglKom4 = new widget.Tanggal();
        jLabel46 = new widget.Label();
        Tkomplikasi5 = new widget.TextBox();
        jLabel47 = new widget.Label();
        TtglKom5 = new widget.Tanggal();
        jLabel48 = new widget.Label();
        TnoStatus = new widget.TextBox();
        panelGlass12 = new widget.panelisi();
        Scroll = new widget.ScrollPane();
        tbStatus = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel49 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel50 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Status Hemodialisis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass11.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(192, 407));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 870));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 122, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(240, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(315, 10, 407, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Alamat : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 38, 110, 23);

        Talamat.setEditable(false);
        Talamat.setBackground(new java.awt.Color(245, 250, 240));
        Talamat.setForeground(new java.awt.Color(0, 0, 0));
        Talamat.setName("Talamat"); // NOI18N
        FormInput.add(Talamat);
        Talamat.setBounds(114, 38, 610, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("No. Status HD : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 66, 110, 23);

        Tnotelp.setBackground(new java.awt.Color(245, 250, 240));
        Tnotelp.setForeground(new java.awt.Color(0, 0, 0));
        Tnotelp.setName("Tnotelp"); // NOI18N
        Tnotelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnotelpKeyPressed(evt);
            }
        });
        FormInput.add(Tnotelp);
        Tnotelp.setBounds(305, 66, 122, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Umur :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(425, 66, 50, 23);

        Tumur.setEditable(false);
        Tumur.setBackground(new java.awt.Color(245, 250, 240));
        Tumur.setForeground(new java.awt.Color(0, 0, 0));
        Tumur.setName("Tumur"); // NOI18N
        FormInput.add(Tumur);
        Tumur.setBounds(480, 66, 70, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Tgl. Lahir :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(553, 66, 70, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setBackground(new java.awt.Color(245, 250, 240));
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        FormInput.add(TtglLahir);
        TtglLahir.setBounds(628, 66, 95, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("<html><div style=\"text-align: right;\">Diagnosa Etiologi :&nbsp;<br>PGK&nbsp;&nbsp;&nbsp;</div></html>");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 94, 110, 30);

        TdiagEtiologi.setBackground(new java.awt.Color(245, 250, 240));
        TdiagEtiologi.setForeground(new java.awt.Color(0, 0, 0));
        TdiagEtiologi.setName("TdiagEtiologi"); // NOI18N
        TdiagEtiologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagEtiologiKeyPressed(evt);
            }
        });
        FormInput.add(TdiagEtiologi);
        TdiagEtiologi.setBounds(114, 94, 610, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Mulai HD Tgl. : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 122, 110, 23);

        TtglMulaiHD.setEditable(false);
        TtglMulaiHD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglMulaiHD.setDisplayFormat("dd-MM-yyyy");
        TtglMulaiHD.setName("TtglMulaiHD"); // NOI18N
        TtglMulaiHD.setOpaque(false);
        TtglMulaiHD.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglMulaiHD);
        TtglMulaiHD.setBounds(114, 122, 90, 23);

        chkEmergensi.setBackground(new java.awt.Color(255, 255, 250));
        chkEmergensi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEmergensi.setForeground(new java.awt.Color(0, 0, 0));
        chkEmergensi.setText("Emergensi");
        chkEmergensi.setBorderPainted(true);
        chkEmergensi.setBorderPaintedFlat(true);
        chkEmergensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEmergensi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEmergensi.setName("chkEmergensi"); // NOI18N
        chkEmergensi.setOpaque(false);
        chkEmergensi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkEmergensi);
        chkEmergensi.setBounds(210, 122, 80, 23);

        chkTerencana.setBackground(new java.awt.Color(255, 255, 250));
        chkTerencana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTerencana.setForeground(new java.awt.Color(0, 0, 0));
        chkTerencana.setText("Terencana");
        chkTerencana.setBorderPainted(true);
        chkTerencana.setBorderPaintedFlat(true);
        chkTerencana.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTerencana.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTerencana.setName("chkTerencana"); // NOI18N
        chkTerencana.setOpaque(false);
        chkTerencana.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTerencana);
        chkTerencana.setBounds(300, 122, 80, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("AKSES VASKULER");
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 150, 110, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("<html><div style=\"text-align: right;\">1. Cimino / AV :&nbsp;<br>Shunt&nbsp;&nbsp;&nbsp;</div></html>");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 178, 110, 30);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("1. Lokasi :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(114, 206, 70, 23);

        Tlokasi1.setBackground(new java.awt.Color(245, 250, 240));
        Tlokasi1.setForeground(new java.awt.Color(0, 0, 0));
        Tlokasi1.setName("Tlokasi1"); // NOI18N
        Tlokasi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tlokasi1KeyPressed(evt);
            }
        });
        FormInput.add(Tlokasi1);
        Tlokasi1.setBounds(189, 206, 535, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("2. Lokasi :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(114, 234, 70, 23);

        Tlokasi2.setBackground(new java.awt.Color(245, 250, 240));
        Tlokasi2.setForeground(new java.awt.Color(0, 0, 0));
        Tlokasi2.setName("Tlokasi2"); // NOI18N
        Tlokasi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tlokasi2KeyPressed(evt);
            }
        });
        FormInput.add(Tlokasi2);
        Tlokasi2.setBounds(189, 234, 535, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("3. Lokasi :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(114, 262, 70, 23);

        Tlokasi3.setBackground(new java.awt.Color(245, 250, 240));
        Tlokasi3.setForeground(new java.awt.Color(0, 0, 0));
        Tlokasi3.setName("Tlokasi3"); // NOI18N
        Tlokasi3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tlokasi3KeyPressed(evt);
            }
        });
        FormInput.add(Tlokasi3);
        Tlokasi3.setBounds(189, 262, 535, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("4. Lokasi :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(114, 290, 70, 23);

        Tlokasi4.setBackground(new java.awt.Color(245, 250, 240));
        Tlokasi4.setForeground(new java.awt.Color(0, 0, 0));
        Tlokasi4.setName("Tlokasi4"); // NOI18N
        Tlokasi4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tlokasi4KeyPressed(evt);
            }
        });
        FormInput.add(Tlokasi4);
        Tlokasi4.setBounds(189, 290, 535, 23);

        chkTglOps.setBackground(new java.awt.Color(255, 255, 250));
        chkTglOps.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglOps.setForeground(new java.awt.Color(0, 0, 0));
        chkTglOps.setText("Tgl. Operasi :");
        chkTglOps.setBorderPainted(true);
        chkTglOps.setBorderPaintedFlat(true);
        chkTglOps.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglOps.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglOps.setName("chkTglOps"); // NOI18N
        chkTglOps.setOpaque(false);
        chkTglOps.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglOps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglOpsActionPerformed(evt);
            }
        });
        FormInput.add(chkTglOps);
        chkTglOps.setBounds(114, 178, 100, 23);

        TtglOps.setEditable(false);
        TtglOps.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglOps.setDisplayFormat("dd-MM-yyyy");
        TtglOps.setName("TtglOps"); // NOI18N
        TtglOps.setOpaque(false);
        TtglOps.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglOps);
        TtglOps.setBounds(220, 178, 90, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("2. AV Graft : ");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(0, 318, 110, 23);

        chkTglDibuat.setBackground(new java.awt.Color(255, 255, 250));
        chkTglDibuat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglDibuat.setForeground(new java.awt.Color(0, 0, 0));
        chkTglDibuat.setText("Tgl. Dibuat :");
        chkTglDibuat.setBorderPainted(true);
        chkTglDibuat.setBorderPaintedFlat(true);
        chkTglDibuat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglDibuat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglDibuat.setName("chkTglDibuat"); // NOI18N
        chkTglDibuat.setOpaque(false);
        chkTglDibuat.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglDibuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglDibuatActionPerformed(evt);
            }
        });
        FormInput.add(chkTglDibuat);
        chkTglDibuat.setBounds(114, 318, 90, 23);

        TtglDibuat.setEditable(false);
        TtglDibuat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglDibuat.setDisplayFormat("dd-MM-yyyy");
        TtglDibuat.setName("TtglDibuat"); // NOI18N
        TtglDibuat.setOpaque(false);
        TtglDibuat.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglDibuat);
        TtglDibuat.setBounds(210, 318, 90, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("<html><div style=\"text-align: right;\">3. Double Lumen :&nbsp;<br>Catheter&nbsp;&nbsp;&nbsp;</div></html>");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(0, 346, 110, 30);

        chkTglInsersi.setBackground(new java.awt.Color(255, 255, 250));
        chkTglInsersi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglInsersi.setForeground(new java.awt.Color(0, 0, 0));
        chkTglInsersi.setText("Tgl. Insersi :");
        chkTglInsersi.setBorderPainted(true);
        chkTglInsersi.setBorderPaintedFlat(true);
        chkTglInsersi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglInsersi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglInsersi.setName("chkTglInsersi"); // NOI18N
        chkTglInsersi.setOpaque(false);
        chkTglInsersi.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglInsersi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglInsersiActionPerformed(evt);
            }
        });
        FormInput.add(chkTglInsersi);
        chkTglInsersi.setBounds(114, 346, 90, 23);

        TtglInsersi.setEditable(false);
        TtglInsersi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglInsersi.setDisplayFormat("dd-MM-yyyy");
        TtglInsersi.setName("TtglInsersi"); // NOI18N
        TtglInsersi.setOpaque(false);
        TtglInsersi.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglInsersi);
        TtglInsersi.setBounds(210, 346, 90, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("1. Short term. Lokasi :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(54, 374, 130, 23);

        TlokasiShort.setBackground(new java.awt.Color(245, 250, 240));
        TlokasiShort.setForeground(new java.awt.Color(0, 0, 0));
        TlokasiShort.setName("TlokasiShort"); // NOI18N
        TlokasiShort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlokasiShortKeyPressed(evt);
            }
        });
        FormInput.add(TlokasiShort);
        TlokasiShort.setBounds(189, 374, 535, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Tgl. Insersi :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(54, 402, 130, 23);

        TtglInsersiShort.setEditable(false);
        TtglInsersiShort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglInsersiShort.setDisplayFormat("dd-MM-yyyy");
        TtglInsersiShort.setName("TtglInsersiShort"); // NOI18N
        TtglInsersiShort.setOpaque(false);
        TtglInsersiShort.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglInsersiShort);
        TtglInsersiShort.setBounds(189, 402, 90, 23);

        chkTglOffShort.setBackground(new java.awt.Color(255, 255, 250));
        chkTglOffShort.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglOffShort.setForeground(new java.awt.Color(0, 0, 0));
        chkTglOffShort.setText("Tgl. Off :");
        chkTglOffShort.setBorderPainted(true);
        chkTglOffShort.setBorderPaintedFlat(true);
        chkTglOffShort.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglOffShort.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglOffShort.setName("chkTglOffShort"); // NOI18N
        chkTglOffShort.setOpaque(false);
        chkTglOffShort.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglOffShort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglOffShortActionPerformed(evt);
            }
        });
        FormInput.add(chkTglOffShort);
        chkTglOffShort.setBounds(280, 402, 80, 23);

        TtglOffShort.setEditable(false);
        TtglOffShort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglOffShort.setDisplayFormat("dd-MM-yyyy");
        TtglOffShort.setName("TtglOffShort"); // NOI18N
        TtglOffShort.setOpaque(false);
        TtglOffShort.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglOffShort);
        TtglOffShort.setBounds(365, 402, 90, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("2. Long term. Lokasi :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(54, 430, 130, 23);

        TlokasiLong.setBackground(new java.awt.Color(245, 250, 240));
        TlokasiLong.setForeground(new java.awt.Color(0, 0, 0));
        TlokasiLong.setName("TlokasiLong"); // NOI18N
        TlokasiLong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlokasiLongKeyPressed(evt);
            }
        });
        FormInput.add(TlokasiLong);
        TlokasiLong.setBounds(189, 430, 535, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Tgl. Berhenti :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(54, 458, 130, 23);

        TtglBerhenti.setEditable(false);
        TtglBerhenti.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglBerhenti.setDisplayFormat("dd-MM-yyyy");
        TtglBerhenti.setName("TtglBerhenti"); // NOI18N
        TtglBerhenti.setOpaque(false);
        TtglBerhenti.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglBerhenti);
        TtglBerhenti.setBounds(189, 458, 90, 23);

        chkTglOffLong.setBackground(new java.awt.Color(255, 255, 250));
        chkTglOffLong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglOffLong.setForeground(new java.awt.Color(0, 0, 0));
        chkTglOffLong.setText("Tgl. Off :");
        chkTglOffLong.setBorderPainted(true);
        chkTglOffLong.setBorderPaintedFlat(true);
        chkTglOffLong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglOffLong.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglOffLong.setName("chkTglOffLong"); // NOI18N
        chkTglOffLong.setOpaque(false);
        chkTglOffLong.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglOffLong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglOffLongActionPerformed(evt);
            }
        });
        FormInput.add(chkTglOffLong);
        chkTglOffLong.setBounds(280, 458, 80, 23);

        TtglOffLong.setEditable(false);
        TtglOffLong.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglOffLong.setDisplayFormat("dd-MM-yyyy");
        TtglOffLong.setName("TtglOffLong"); // NOI18N
        TtglOffLong.setOpaque(false);
        TtglOffLong.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglOffLong);
        TtglOffLong.setBounds(365, 458, 90, 23);

        chkTglMulaiFemo.setBackground(new java.awt.Color(255, 255, 250));
        chkTglMulaiFemo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglMulaiFemo.setForeground(new java.awt.Color(0, 0, 0));
        chkTglMulaiFemo.setText("Tgl. Mulai :");
        chkTglMulaiFemo.setBorderPainted(true);
        chkTglMulaiFemo.setBorderPaintedFlat(true);
        chkTglMulaiFemo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglMulaiFemo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglMulaiFemo.setName("chkTglMulaiFemo"); // NOI18N
        chkTglMulaiFemo.setOpaque(false);
        chkTglMulaiFemo.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglMulaiFemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglMulaiFemoActionPerformed(evt);
            }
        });
        FormInput.add(chkTglMulaiFemo);
        chkTglMulaiFemo.setBounds(114, 486, 90, 23);

        TtglMulaiFemo.setEditable(false);
        TtglMulaiFemo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglMulaiFemo.setDisplayFormat("dd-MM-yyyy");
        TtglMulaiFemo.setName("TtglMulaiFemo"); // NOI18N
        TtglMulaiFemo.setOpaque(false);
        TtglMulaiFemo.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglMulaiFemo);
        TtglMulaiFemo.setBounds(210, 486, 90, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("4. Femoral : ");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(0, 486, 110, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("DIAGNOSE PENYAKIT LAIN (Comorbiditas) :");
        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 514, 270, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("1. ");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(0, 542, 110, 23);

        Tdiag1.setBackground(new java.awt.Color(245, 250, 240));
        Tdiag1.setForeground(new java.awt.Color(0, 0, 0));
        Tdiag1.setName("Tdiag1"); // NOI18N
        Tdiag1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdiag1KeyPressed(evt);
            }
        });
        FormInput.add(Tdiag1);
        Tdiag1.setBounds(114, 542, 500, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Tgl. :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(615, 542, 35, 23);

        TtglDiag1.setEditable(false);
        TtglDiag1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglDiag1.setDisplayFormat("dd-MM-yyyy");
        TtglDiag1.setName("TtglDiag1"); // NOI18N
        TtglDiag1.setOpaque(false);
        TtglDiag1.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglDiag1);
        TtglDiag1.setBounds(655, 542, 90, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("2. ");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(0, 570, 110, 23);

        Tdiag2.setBackground(new java.awt.Color(245, 250, 240));
        Tdiag2.setForeground(new java.awt.Color(0, 0, 0));
        Tdiag2.setName("Tdiag2"); // NOI18N
        Tdiag2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdiag2KeyPressed(evt);
            }
        });
        FormInput.add(Tdiag2);
        Tdiag2.setBounds(114, 570, 500, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tgl. :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(615, 570, 35, 23);

        TtglDiag2.setEditable(false);
        TtglDiag2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglDiag2.setDisplayFormat("dd-MM-yyyy");
        TtglDiag2.setName("TtglDiag2"); // NOI18N
        TtglDiag2.setOpaque(false);
        TtglDiag2.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglDiag2);
        TtglDiag2.setBounds(655, 570, 90, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("3. ");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(0, 598, 110, 23);

        Tdiag3.setBackground(new java.awt.Color(245, 250, 240));
        Tdiag3.setForeground(new java.awt.Color(0, 0, 0));
        Tdiag3.setName("Tdiag3"); // NOI18N
        Tdiag3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdiag3KeyPressed(evt);
            }
        });
        FormInput.add(Tdiag3);
        Tdiag3.setBounds(114, 598, 500, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Tgl. :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(615, 598, 35, 23);

        TtglDiag3.setEditable(false);
        TtglDiag3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglDiag3.setDisplayFormat("dd-MM-yyyy");
        TtglDiag3.setName("TtglDiag3"); // NOI18N
        TtglDiag3.setOpaque(false);
        TtglDiag3.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglDiag3);
        TtglDiag3.setBounds(655, 598, 90, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("4. ");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(0, 626, 110, 23);

        Tdiag4.setBackground(new java.awt.Color(245, 250, 240));
        Tdiag4.setForeground(new java.awt.Color(0, 0, 0));
        Tdiag4.setName("Tdiag4"); // NOI18N
        Tdiag4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdiag4KeyPressed(evt);
            }
        });
        FormInput.add(Tdiag4);
        Tdiag4.setBounds(114, 626, 500, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Tgl. :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(615, 626, 35, 23);

        TtglDiag4.setEditable(false);
        TtglDiag4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglDiag4.setDisplayFormat("dd-MM-yyyy");
        TtglDiag4.setName("TtglDiag4"); // NOI18N
        TtglDiag4.setOpaque(false);
        TtglDiag4.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglDiag4);
        TtglDiag4.setBounds(655, 626, 90, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("5. ");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(0, 654, 110, 23);

        Tdiag5.setBackground(new java.awt.Color(245, 250, 240));
        Tdiag5.setForeground(new java.awt.Color(0, 0, 0));
        Tdiag5.setName("Tdiag5"); // NOI18N
        Tdiag5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdiag5KeyPressed(evt);
            }
        });
        FormInput.add(Tdiag5);
        Tdiag5.setBounds(114, 654, 500, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Tgl. :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(615, 654, 35, 23);

        TtglDiag5.setEditable(false);
        TtglDiag5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglDiag5.setDisplayFormat("dd-MM-yyyy");
        TtglDiag5.setName("TtglDiag5"); // NOI18N
        TtglDiag5.setOpaque(false);
        TtglDiag5.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglDiag5);
        TtglDiag5.setBounds(655, 654, 90, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("KOMPLIKASI KRONIK :");
        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(0, 682, 160, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("1. ");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(0, 710, 110, 23);

        Tkomplikasi1.setBackground(new java.awt.Color(245, 250, 240));
        Tkomplikasi1.setForeground(new java.awt.Color(0, 0, 0));
        Tkomplikasi1.setName("Tkomplikasi1"); // NOI18N
        Tkomplikasi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkomplikasi1KeyPressed(evt);
            }
        });
        FormInput.add(Tkomplikasi1);
        Tkomplikasi1.setBounds(114, 710, 500, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Tgl. :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(615, 710, 35, 23);

        TtglKom1.setEditable(false);
        TtglKom1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglKom1.setDisplayFormat("dd-MM-yyyy");
        TtglKom1.setName("TtglKom1"); // NOI18N
        TtglKom1.setOpaque(false);
        TtglKom1.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglKom1);
        TtglKom1.setBounds(655, 710, 90, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("2. ");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 738, 110, 23);

        Tkomplikasi2.setBackground(new java.awt.Color(245, 250, 240));
        Tkomplikasi2.setForeground(new java.awt.Color(0, 0, 0));
        Tkomplikasi2.setName("Tkomplikasi2"); // NOI18N
        Tkomplikasi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkomplikasi2KeyPressed(evt);
            }
        });
        FormInput.add(Tkomplikasi2);
        Tkomplikasi2.setBounds(114, 738, 500, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Tgl. :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(615, 738, 35, 23);

        TtglKom2.setEditable(false);
        TtglKom2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglKom2.setDisplayFormat("dd-MM-yyyy");
        TtglKom2.setName("TtglKom2"); // NOI18N
        TtglKom2.setOpaque(false);
        TtglKom2.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglKom2);
        TtglKom2.setBounds(655, 738, 90, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("3. ");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(0, 766, 110, 23);

        Tkomplikasi3.setBackground(new java.awt.Color(245, 250, 240));
        Tkomplikasi3.setForeground(new java.awt.Color(0, 0, 0));
        Tkomplikasi3.setName("Tkomplikasi3"); // NOI18N
        Tkomplikasi3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkomplikasi3KeyPressed(evt);
            }
        });
        FormInput.add(Tkomplikasi3);
        Tkomplikasi3.setBounds(114, 766, 500, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Tgl. :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(615, 766, 35, 23);

        TtglKom3.setEditable(false);
        TtglKom3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglKom3.setDisplayFormat("dd-MM-yyyy");
        TtglKom3.setName("TtglKom3"); // NOI18N
        TtglKom3.setOpaque(false);
        TtglKom3.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglKom3);
        TtglKom3.setBounds(655, 766, 90, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("4. ");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(0, 794, 110, 23);

        Tkomplikasi4.setBackground(new java.awt.Color(245, 250, 240));
        Tkomplikasi4.setForeground(new java.awt.Color(0, 0, 0));
        Tkomplikasi4.setName("Tkomplikasi4"); // NOI18N
        Tkomplikasi4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkomplikasi4KeyPressed(evt);
            }
        });
        FormInput.add(Tkomplikasi4);
        Tkomplikasi4.setBounds(114, 794, 500, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Tgl. :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(615, 794, 35, 23);

        TtglKom4.setEditable(false);
        TtglKom4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglKom4.setDisplayFormat("dd-MM-yyyy");
        TtglKom4.setName("TtglKom4"); // NOI18N
        TtglKom4.setOpaque(false);
        TtglKom4.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglKom4);
        TtglKom4.setBounds(655, 794, 90, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("5. ");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(0, 822, 110, 23);

        Tkomplikasi5.setBackground(new java.awt.Color(245, 250, 240));
        Tkomplikasi5.setForeground(new java.awt.Color(0, 0, 0));
        Tkomplikasi5.setName("Tkomplikasi5"); // NOI18N
        Tkomplikasi5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tkomplikasi5KeyPressed(evt);
            }
        });
        FormInput.add(Tkomplikasi5);
        Tkomplikasi5.setBounds(114, 822, 500, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Tgl. :");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(615, 822, 35, 23);

        TtglKom5.setEditable(false);
        TtglKom5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        TtglKom5.setDisplayFormat("dd-MM-yyyy");
        TtglKom5.setName("TtglKom5"); // NOI18N
        TtglKom5.setOpaque(false);
        TtglKom5.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglKom5);
        TtglKom5.setBounds(655, 822, 90, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("No. Hp/Telpn. : ");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(210, 66, 90, 23);

        TnoStatus.setBackground(new java.awt.Color(245, 250, 240));
        TnoStatus.setForeground(new java.awt.Color(0, 0, 0));
        TnoStatus.setName("TnoStatus"); // NOI18N
        TnoStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoStatusKeyPressed(evt);
            }
        });
        FormInput.add(TnoStatus);
        TnoStatus.setBounds(114, 66, 90, 23);

        Scroll1.setViewportView(FormInput);

        panelGlass11.add(Scroll1);

        panelGlass12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Status Hemodialisis ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbStatus.setToolTipText("Silahkan klik untuk memilih data yang mau diperbaiki/dihapus");
        tbStatus.setName("tbStatus"); // NOI18N
        tbStatus.getTableHeader().setReorderingAllowed(false);
        tbStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbStatusMouseClicked(evt);
            }
        });
        tbStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbStatusKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbStatus);

        panelGlass12.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Tanggal :");
        jLabel49.setName("jLabel49"); // NOI18N
        jLabel49.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass10.add(jLabel49);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("s.d.");
        jLabel50.setName("jLabel50"); // NOI18N
        jLabel50.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel50);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-11-2026" }));
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
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
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

        panelGlass11.add(panelGlass12);

        internalFrame1.add(panelGlass11, java.awt.BorderLayout.CENTER);

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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            cekData();
            if (Sequel.menyimpantf("status_hemodialisis", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 47, new String[]{
                TNoRw.getText(), TnoStatus.getText(), TdiagEtiologi.getText(), Valid.SetTgl(TtglMulaiHD.getSelectedItem() + ""), cekEmer, cekTeren, Tlokasi1.getText(),
                Tlokasi2.getText(), Tlokasi3.getText(), Tlokasi4.getText(), cekTglOps, tglOps, cekTglDibuat, tglDibuat, cekTglInser, tglInsersi, TlokasiShort.getText(),
                Valid.SetTgl(TtglInsersiShort.getSelectedItem() + ""), cekTglOffShort, tglOffShort, TlokasiLong.getText(), Valid.SetTgl(TtglBerhenti.getSelectedItem() + ""),
                cekTglOffLong, tglOffLong, cekTglMulaiFemo, tglMulaiFemo, Tdiag1.getText(), Tdiag2.getText(), Tdiag3.getText(), Tdiag4.getText(), Tdiag5.getText(),
                Valid.SetTgl(TtglDiag1.getSelectedItem() + ""), Valid.SetTgl(TtglDiag2.getSelectedItem() + ""), Valid.SetTgl(TtglDiag3.getSelectedItem() + ""),
                Valid.SetTgl(TtglDiag4.getSelectedItem() + ""), Valid.SetTgl(TtglDiag5.getSelectedItem() + ""), Tkomplikasi1.getText(), Tkomplikasi2.getText(),
                Tkomplikasi3.getText(), Tkomplikasi4.getText(), Tkomplikasi5.getText(), Valid.SetTgl(TtglKom1.getSelectedItem() + ""), Valid.SetTgl(TtglKom2.getSelectedItem() + ""),
                Valid.SetTgl(TtglKom3.getSelectedItem() + ""), Valid.SetTgl(TtglKom4.getSelectedItem() + ""), Valid.SetTgl(TtglKom5.getSelectedItem() + ""),
                Sequel.cariIsi("select now()")
            }) == true) {

                Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "no_tlp='" + Tnotelp.getText() + "'");
                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Status Hemodialisis", "Simpan");
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
        if (tbStatus.getSelectedRow() > -1) {
            cekData();
            if (Sequel.mengedittf("status_hemodialisis", "no_rawat=?", "no_status=?, diagnosa_etiologi=?, tgl_mulai_hd=?, emergensi=?, terencana=?, cimino_lokasi1=?, "
                    + "cimino_lokasi2=?, cimino_lokasi3=?, cimino_lokasi4=?, cek_tgl_operasi=?, tgl_operasi=?, cek_tgl_av_graft=?, tgl_av_graft=?, cek_tgl_insersi_catheter=?, "
                    + "tgl_insersi_catheter=?, short_lokasi=?, short_tgl_insersi=?, cek_short_tgl_off=?, short_tgl_off=?, long_lokasi=?, long_tgl_berhenti=?, cek_long_tgl_off=?, "
                    + "long_tgl_off=?, cek_tgl_mulai_femoral=?, tgl_femoral=?, diagnosa1=?, diagnosa2=?, diagnosa3=?, diagnosa4=?, diagnosa5=?, tgl_diag1=?, tgl_diag2=?, "
                    + "tgl_diag3=?, tgl_diag4=?, tgl_diag5=?, komplikasi1=?, komplikasi2=?, komplikasi3=?, komplikasi4=?, komplikasi5=?, tgl_komplikasi1=?, tgl_komplikasi2=?, "
                    + "tgl_komplikasi3=?, tgl_komplikasi4=?, tgl_komplikasi5=?", 46, new String[]{
                        TnoStatus.getText(), TdiagEtiologi.getText(), Valid.SetTgl(TtglMulaiHD.getSelectedItem() + ""), cekEmer, cekTeren, Tlokasi1.getText(),
                        Tlokasi2.getText(), Tlokasi3.getText(), Tlokasi4.getText(), cekTglOps, tglOps, cekTglDibuat, tglDibuat, cekTglInser, tglInsersi, TlokasiShort.getText(),
                        Valid.SetTgl(TtglInsersiShort.getSelectedItem() + ""), cekTglOffShort, tglOffShort, TlokasiLong.getText(), Valid.SetTgl(TtglBerhenti.getSelectedItem() + ""),
                        cekTglOffLong, tglOffLong, cekTglMulaiFemo, tglMulaiFemo, Tdiag1.getText(), Tdiag2.getText(), Tdiag3.getText(), Tdiag4.getText(), Tdiag5.getText(),
                        Valid.SetTgl(TtglDiag1.getSelectedItem() + ""), Valid.SetTgl(TtglDiag2.getSelectedItem() + ""), Valid.SetTgl(TtglDiag3.getSelectedItem() + ""),
                        Valid.SetTgl(TtglDiag4.getSelectedItem() + ""), Valid.SetTgl(TtglDiag5.getSelectedItem() + ""), Tkomplikasi1.getText(), Tkomplikasi2.getText(),
                        Tkomplikasi3.getText(), Tkomplikasi4.getText(), Tkomplikasi5.getText(), Valid.SetTgl(TtglKom1.getSelectedItem() + ""), Valid.SetTgl(TtglKom2.getSelectedItem() + ""),
                        Valid.SetTgl(TtglKom3.getSelectedItem() + ""), Valid.SetTgl(TtglKom4.getSelectedItem() + ""), Valid.SetTgl(TtglKom5.getSelectedItem() + ""),
                        tbStatus.getValueAt(tbStatus.getSelectedRow(), 0).toString()
                    }) == true) {

                Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "no_tlp='" + Tnotelp.getText() + "'");
                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Status Hemodialisis", "Ganti");
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel...");
            tampil();
            tbStatus.requestFocus();
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
        frmUtama.getInstance().tutupDialogDiPanelUtama(this);
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            frmUtama.getInstance().tutupDialogDiPanelUtama(this);
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

    private void tbStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbStatusMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbStatusMouseClicked

    private void tbStatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbStatusKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbStatusKeyPressed

    private void TnotelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnotelpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdiagEtiologi.requestFocus();
        }
    }//GEN-LAST:event_TnotelpKeyPressed

    private void TdiagEtiologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagEtiologiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglMulaiHD.requestFocus();
        }
    }//GEN-LAST:event_TdiagEtiologiKeyPressed

    private void Tlokasi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tlokasi1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlokasi2.requestFocus();
        }
    }//GEN-LAST:event_Tlokasi1KeyPressed

    private void Tlokasi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tlokasi2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlokasi3.requestFocus();
        }
    }//GEN-LAST:event_Tlokasi2KeyPressed

    private void Tlokasi3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tlokasi3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlokasi4.requestFocus();
        }
    }//GEN-LAST:event_Tlokasi3KeyPressed

    private void Tlokasi4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tlokasi4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTglDibuat.requestFocus();
        }
    }//GEN-LAST:event_Tlokasi4KeyPressed

    private void TlokasiShortKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlokasiShortKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglInsersiShort.requestFocus();
        }
    }//GEN-LAST:event_TlokasiShortKeyPressed

    private void TlokasiLongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlokasiLongKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglBerhenti.requestFocus();
        }
    }//GEN-LAST:event_TlokasiLongKeyPressed

    private void Tdiag1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdiag1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglDiag1.requestFocus();
        }
    }//GEN-LAST:event_Tdiag1KeyPressed

    private void Tdiag2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdiag2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglDiag2.requestFocus();
        }
    }//GEN-LAST:event_Tdiag2KeyPressed

    private void Tdiag3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdiag3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglDiag3.requestFocus();
        }
    }//GEN-LAST:event_Tdiag3KeyPressed

    private void Tdiag4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdiag4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglDiag4.requestFocus();
        }
    }//GEN-LAST:event_Tdiag4KeyPressed

    private void Tdiag5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdiag5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglDiag5.requestFocus();
        }
    }//GEN-LAST:event_Tdiag5KeyPressed

    private void Tkomplikasi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkomplikasi1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglKom1.requestFocus();
        }
    }//GEN-LAST:event_Tkomplikasi1KeyPressed

    private void Tkomplikasi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkomplikasi2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglKom2.requestFocus();
        }
    }//GEN-LAST:event_Tkomplikasi2KeyPressed

    private void Tkomplikasi3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkomplikasi3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglKom3.requestFocus();
        }
    }//GEN-LAST:event_Tkomplikasi3KeyPressed

    private void Tkomplikasi4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkomplikasi4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglKom4.requestFocus();
        }
    }//GEN-LAST:event_Tkomplikasi4KeyPressed

    private void Tkomplikasi5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tkomplikasi5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglKom5.requestFocus();
        }
    }//GEN-LAST:event_Tkomplikasi5KeyPressed

    private void chkTglOpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglOpsActionPerformed
        TtglOps.setDate(new Date());
        Tlokasi1.setText("");
        Tlokasi2.setText("");
        Tlokasi3.setText("");
        Tlokasi4.setText("");
        
        if (chkTglOps.isSelected() == true) {
            TtglOps.setEnabled(true);
            TtglOps.requestFocus();
            Tlokasi1.setEnabled(true);
            Tlokasi2.setEnabled(true);
            Tlokasi3.setEnabled(true);
            Tlokasi4.setEnabled(true);
        } else {
            TtglOps.setEnabled(false);
            Tlokasi1.setEnabled(false);
            Tlokasi2.setEnabled(false);
            Tlokasi3.setEnabled(false);
            Tlokasi4.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglOpsActionPerformed

    private void chkTglDibuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglDibuatActionPerformed
        if (chkTglDibuat.isSelected() == true) {
            TtglDibuat.setEnabled(true);
            TtglDibuat.requestFocus();
        } else {
            TtglDibuat.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglDibuatActionPerformed

    private void chkTglInsersiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglInsersiActionPerformed
        if (chkTglInsersi.isSelected() == true) {
            TtglInsersi.setEnabled(true);
            TtglInsersi.requestFocus();
        } else {
            TtglInsersi.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglInsersiActionPerformed

    private void chkTglOffShortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglOffShortActionPerformed
        if (chkTglOffShort.isSelected() == true) {
            TtglOffShort.setEnabled(true);
            TtglOffShort.requestFocus();
        } else {
            TtglOffShort.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglOffShortActionPerformed

    private void chkTglOffLongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglOffLongActionPerformed
        if (chkTglOffLong.isSelected() == true) {
            TtglOffLong.setEnabled(true);
            TtglOffLong.requestFocus();
        } else {
            TtglOffLong.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglOffLongActionPerformed

    private void chkTglMulaiFemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglMulaiFemoActionPerformed
        if (chkTglMulaiFemo.isSelected() == true) {
            TtglMulaiFemo.setEnabled(true);
            TtglMulaiFemo.requestFocus();
        } else {
            TtglMulaiFemo.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglMulaiFemoActionPerformed

    private void TnoStatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoStatusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tnotelp.requestFocus();
        }
    }//GEN-LAST:event_TnoStatusKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbStatus.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from status_hemodialisis where no_rawat=?", 1, new String[]{
                    tbStatus.getValueAt(tbStatus.getSelectedRow(), 0).toString()
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
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel....");
            tampil();
            tbStatus.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnCari);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbStatus.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("noStatus", TnoStatus.getText());
            param.put("alamat", Talamat.getText());
            param.put("notelp", Tnotelp.getText());
            param.put("umurTtl", Tumur.getText() + " / " + TtglLahir.getText());
            param.put("diagEtiologi", TdiagEtiologi.getText());
            param.put("tglMulaiHd", TtglMulaiHD.getSelectedItem().toString());
            
            if (chkEmergensi.isSelected() == true) {
                param.put("emergensi", "V");
            } else {
                param.put("emergensi", "");
            }
            
            if (chkTerencana.isSelected() == true) {
                param.put("terencana", "V");
            } else {
                param.put("terencana", "");
            }

            if (chkTglOps.isSelected() == true) {
                param.put("tglOperasi", TtglOps.getSelectedItem().toString());
            } else {
                param.put("tglOperasi", "-");
            }
            
            param.put("lokasi1", Tlokasi1.getText());
            param.put("lokasi2", Tlokasi2.getText());
            param.put("lokasi3", Tlokasi3.getText());
            param.put("lokasi4", Tlokasi4.getText());
            
            if (chkTglDibuat.isSelected() == true) {
                param.put("tglDibuat", TtglDibuat.getSelectedItem().toString());
            } else {
                param.put("tglDibuat", "-");
            }
            
            if (chkTglInsersi.isSelected() == true) {
                param.put("tglInsersi", TtglInsersi.getSelectedItem().toString());
            } else {
                param.put("tglInsersi", "-");
            }
            
            if (chkTglOffShort.isSelected() == true) {
                param.put("shortLokasi", TlokasiShort.getText() + ", Tgl. Insersi : " + TtglInsersi.getSelectedItem().toString() + ", Tgl. Off : " + TtglOffShort.getSelectedItem().toString());
            } else {
                param.put("shortLokasi", TlokasiShort.getText() + ", Tgl. Insersi : " + TtglInsersi.getSelectedItem().toString() + ", Tgl. Off : -");
            }
            
            if (chkTglOffLong.isSelected() == true) {
                param.put("longLokasi", TlokasiLong.getText() + ", Tgl. Berhenti : " + TtglBerhenti.getSelectedItem().toString() + ", Tgl. Off : " + TtglOffLong.getSelectedItem().toString());
            } else {
                param.put("longLokasi", TlokasiLong.getText() + ", Tgl. Berhenti : " + TtglBerhenti.getSelectedItem().toString() + ", Tgl. Off : -");
            }
            
            if (chkTglMulaiFemo.isSelected() == true) {
                param.put("tglFemo", TtglMulaiFemo.getSelectedItem().toString());
            } else {
                param.put("tglFemo", "-");
            }
            
            if (Tdiag1.getText().equals("")) {
                param.put("diagnosa1", "-");
            } else {
                param.put("diagnosa1", Tdiag1.getText() + ", Tgl. " + TtglDiag1.getSelectedItem().toString());
            }
            
            if (Tdiag2.getText().equals("")) {
                param.put("diagnosa2", "-");
            } else {
                param.put("diagnosa2", Tdiag2.getText() + ", Tgl. " + TtglDiag2.getSelectedItem().toString());
            }
            
            if (Tdiag3.getText().equals("")) {
                param.put("diagnosa3", "-");
            } else {
                param.put("diagnosa3", Tdiag3.getText() + ", Tgl. " + TtglDiag3.getSelectedItem().toString());
            }
            
            if (Tdiag4.getText().equals("")) {
                param.put("diagnosa4", "-");
            } else {
                param.put("diagnosa4", Tdiag4.getText() + ", Tgl. " + TtglDiag4.getSelectedItem().toString());
            }
            
            if (Tdiag5.getText().equals("")) {
                param.put("diagnosa5", "-");
            } else {
                param.put("diagnosa5", Tdiag5.getText() + ", Tgl. " + TtglDiag5.getSelectedItem().toString());
            }
            
            if (Tkomplikasi1.getText().equals("")) {
                param.put("komplikasi1", "-");
            } else {
                param.put("komplikasi1", Tkomplikasi1.getText() + ", Tgl. " + TtglKom1.getSelectedItem().toString());
            }
            
            if (Tkomplikasi2.getText().equals("")) {
                param.put("komplikasi2", "-");
            } else {
                param.put("komplikasi2", Tkomplikasi2.getText() + ", Tgl. " + TtglKom2.getSelectedItem().toString());
            }
            
            if (Tkomplikasi3.getText().equals("")) {
                param.put("komplikasi3", "-");
            } else {
                param.put("komplikasi3", Tkomplikasi3.getText() + ", Tgl. " + TtglKom3.getSelectedItem().toString());
            }
            
            if (Tkomplikasi4.getText().equals("")) {
                param.put("komplikasi4", "-");
            } else {
                param.put("komplikasi4", Tkomplikasi4.getText() + ", Tgl. " + TtglKom4.getSelectedItem().toString());
            }
            
            if (Tkomplikasi5.getText().equals("")) {
                param.put("komplikasi5", "-");
            } else {
                param.put("komplikasi5", Tkomplikasi5.getText() + ", Tgl. " + TtglKom5.getSelectedItem().toString());
            }
            
//            Valid.MyReport("rptStatusHemodialisis2.jasper", "report", "::[ Status Hemodialisis (Data Status) ]::",
//                    "SELECT date(now()) tgl", param);
//            
//            Valid.MyReport("rptStatusHemodialisis1.jasper", "report", "::[ Status Hemodialisis (Sampul/Cover) ]::",
//                    "SELECT date(now()) tgl", param);
            
            emptTeks();
            tampil();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel....");
            tampil();
            tbStatus.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMStatusHemodialisis dialog = new RMStatusHemodialisis(new javax.swing.JFrame(), true);
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
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Talamat;
    private widget.TextBox Tdiag1;
    private widget.TextBox Tdiag2;
    private widget.TextBox Tdiag3;
    private widget.TextBox Tdiag4;
    private widget.TextBox Tdiag5;
    private widget.TextBox TdiagEtiologi;
    private widget.TextBox Tkomplikasi1;
    private widget.TextBox Tkomplikasi2;
    private widget.TextBox Tkomplikasi3;
    private widget.TextBox Tkomplikasi4;
    private widget.TextBox Tkomplikasi5;
    private widget.TextBox Tlokasi1;
    private widget.TextBox Tlokasi2;
    private widget.TextBox Tlokasi3;
    private widget.TextBox Tlokasi4;
    private widget.TextBox TlokasiLong;
    private widget.TextBox TlokasiShort;
    private widget.TextBox TnoStatus;
    private widget.TextBox Tnotelp;
    private widget.Tanggal TtglBerhenti;
    private widget.Tanggal TtglDiag1;
    private widget.Tanggal TtglDiag2;
    private widget.Tanggal TtglDiag3;
    private widget.Tanggal TtglDiag4;
    private widget.Tanggal TtglDiag5;
    private widget.Tanggal TtglDibuat;
    private widget.Tanggal TtglInsersi;
    private widget.Tanggal TtglInsersiShort;
    private widget.Tanggal TtglKom1;
    private widget.Tanggal TtglKom2;
    private widget.Tanggal TtglKom3;
    private widget.Tanggal TtglKom4;
    private widget.Tanggal TtglKom5;
    private widget.TextBox TtglLahir;
    private widget.Tanggal TtglMulaiFemo;
    private widget.Tanggal TtglMulaiHD;
    private widget.Tanggal TtglOffLong;
    private widget.Tanggal TtglOffShort;
    private widget.Tanggal TtglOps;
    private widget.TextBox Tumur;
    public widget.CekBox chkEmergensi;
    public widget.CekBox chkTerencana;
    public widget.CekBox chkTglDibuat;
    public widget.CekBox chkTglInsersi;
    public widget.CekBox chkTglMulaiFemo;
    public widget.CekBox chkTglOffLong;
    public widget.CekBox chkTglOffShort;
    public widget.CekBox chkTglOps;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
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
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.Table tbStatus;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select sh.*, p.no_rkm_medis, p.nm_pasien, p.no_tlp, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                    + "concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', ',kb.nm_kab) almat, date_format(sh.tgl_mulai_hd,'%d-%m-%Y') tglMulaiHD, "
                    + "date_format(rp.tgl_registrasi,'%d-%m-%Y') tglReg, date(now()) tglSekarang from status_hemodialisis sh "
                    + "inner join reg_periksa rp on rp.no_rawat=sh.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join kelurahan kl on kl.kd_kel=p.kd_kel inner join kecamatan kc on kc.kd_kec=p.kd_kec inner join kabupaten kb on kb.kd_kab=p.kd_kab where "
                    + "sh.tgl_mulai_hd between ? and ? and sh.no_rawat like ? or "
                    + "sh.tgl_mulai_hd between ? and ? and p.no_rkm_medis like ? or "
                    + "sh.tgl_mulai_hd between ? and ? and p.nm_pasien like ? or "
                    + "sh.tgl_mulai_hd between ? and ? and p.no_tlp like ? or "
                    + "sh.tgl_mulai_hd between ? and ? and concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', ',kb.nm_kab) like ? or "
                    + "sh.tgl_mulai_hd between ? and ? and sh.no_status like ? order by sh.waktu_simpan desc");

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
                        rs.getString("nm_pasien"),
                        rs.getString("tgllahir"),
                        rs.getString("almat"),
                        rs.getString("tglMulaiHD"),
                        rs.getString("tglReg"),
                        rs.getString("no_status"),
                        rs.getString("diagnosa_etiologi"),
                        rs.getString("tgl_mulai_hd"),
                        rs.getString("emergensi"),
                        rs.getString("terencana"),
                        rs.getString("cimino_lokasi1"),
                        rs.getString("cimino_lokasi2"),
                        rs.getString("cimino_lokasi3"),
                        rs.getString("cimino_lokasi4"),
                        rs.getString("cek_tgl_operasi"),
                        tanggalAtauHariIni(rs.getString("tgl_operasi"), rs.getString("tglSekarang")),
                        rs.getString("cek_tgl_av_graft"),
                        tanggalAtauHariIni(rs.getString("tgl_av_graft"), rs.getString("tglSekarang")),
                        rs.getString("cek_tgl_insersi_catheter"),
                        tanggalAtauHariIni(rs.getString("tgl_insersi_catheter"), rs.getString("tglSekarang")),
                        rs.getString("short_lokasi"),
                        rs.getString("short_tgl_insersi"),
                        rs.getString("cek_short_tgl_off"),
                        tanggalAtauHariIni(rs.getString("short_tgl_off"), rs.getString("tglSekarang")),
                        rs.getString("long_lokasi"),
                        rs.getString("long_tgl_berhenti"),
                        rs.getString("cek_long_tgl_off"),
                        tanggalAtauHariIni(rs.getString("long_tgl_off"), rs.getString("tglSekarang")),
                        rs.getString("cek_tgl_mulai_femoral"),
                        tanggalAtauHariIni(rs.getString("tgl_femoral"), rs.getString("tglSekarang")),
                        rs.getString("diagnosa1"),
                        rs.getString("diagnosa2"),
                        rs.getString("diagnosa3"),
                        rs.getString("diagnosa4"),
                        rs.getString("diagnosa5"),
                        rs.getString("tgl_diag1"),
                        rs.getString("tgl_diag2"),
                        rs.getString("tgl_diag3"),
                        rs.getString("tgl_diag4"),
                        rs.getString("tgl_diag5"),
                        rs.getString("komplikasi1"),
                        rs.getString("komplikasi2"),
                        rs.getString("komplikasi3"),
                        rs.getString("komplikasi4"),
                        rs.getString("komplikasi5"),
                        rs.getString("tgl_komplikasi1"),
                        rs.getString("tgl_komplikasi2"),
                        rs.getString("tgl_komplikasi3"),
                        rs.getString("tgl_komplikasi4"),
                        rs.getString("tgl_komplikasi5"),
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
        Talamat.setText("");
        TnoStatus.setText("");
        Tnotelp.setText("");
        Tumur.setText("");
        TtglLahir.setText("");
        TdiagEtiologi.setText("");
        TtglMulaiHD.setDate(new Date());
        chkEmergensi.setSelected(false);
        chkTerencana.setSelected(false);        
        chkTglOps.setSelected(false);
        TtglOps.setEnabled(false);
        TtglOps.setDate(new Date());
        Tlokasi1.setText("");
        Tlokasi2.setText("");
        Tlokasi3.setText("");
        Tlokasi4.setText("");
        Tlokasi1.setEnabled(false);
        Tlokasi2.setEnabled(false);
        Tlokasi3.setEnabled(false);
        Tlokasi4.setEnabled(false);        
        chkTglDibuat.setSelected(false);
        TtglDibuat.setEnabled(false);
        TtglDibuat.setDate(new Date());
        chkTglInsersi.setSelected(false);
        TtglInsersi.setEnabled(false);
        TtglInsersi.setDate(new Date());
        TlokasiShort.setText("");
        TtglInsersiShort.setDate(new Date());
        chkTglOffShort.setSelected(false);
        TtglOffShort.setEnabled(false);
        TtglOffShort.setDate(new Date());
        TlokasiLong.setText("");
        TtglBerhenti.setDate(new Date());
        chkTglOffLong.setSelected(false);
        TtglOffLong.setEnabled(false);
        TtglOffLong.setDate(new Date());
        chkTglMulaiFemo.setSelected(false);
        TtglMulaiFemo.setEnabled(false);
        TtglMulaiFemo.setDate(new Date());
        Tdiag1.setText("");
        Tdiag2.setText("");
        Tdiag3.setText("");
        Tdiag4.setText("");
        Tdiag5.setText("");
        TtglDiag1.setDate(new Date());
        TtglDiag2.setDate(new Date());
        TtglDiag3.setDate(new Date());
        TtglDiag4.setDate(new Date());
        TtglDiag5.setDate(new Date());        
        Tkomplikasi1.setText("");
        Tkomplikasi2.setText("");
        Tkomplikasi3.setText("");
        Tkomplikasi4.setText("");
        Tkomplikasi5.setText("");
        TtglKom1.setDate(new Date());
        TtglKom2.setDate(new Date());
        TtglKom3.setDate(new Date());
        TtglKom4.setDate(new Date());
        TtglKom5.setDate(new Date());
    }

    private void getData() {
        cekEmer = "";
        cekTeren = "";
        cekTglOps = "";
        cekTglDibuat = "";
        cekTglInser = "";
        cekTglOffShort = "";
        cekTglOffLong = "";
        cekTglMulaiFemo = "";
        tglOps = "";
        tglDibuat = "";
        tglInsersi = "";
        tglOffShort = "";
        tglOffLong = "";
        tglMulaiFemo = "";
        tglReg = "";        
        
        if (tbStatus.getSelectedRow() != -1) {
            TNoRw.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 0).toString());
            isPasien();
            TnoStatus.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 7).toString());
            TdiagEtiologi.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 8).toString());
            Valid.SetTgl(TtglMulaiHD, tbStatus.getValueAt(tbStatus.getSelectedRow(), 9).toString());
            cekEmer = tbStatus.getValueAt(tbStatus.getSelectedRow(), 10).toString();
            cekTeren = tbStatus.getValueAt(tbStatus.getSelectedRow(), 11).toString();
            cekTglOps = tbStatus.getValueAt(tbStatus.getSelectedRow(), 16).toString();
            Valid.SetTgl(TtglOps, tbStatus.getValueAt(tbStatus.getSelectedRow(), 17).toString());
            Tlokasi1.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 12).toString());
            Tlokasi2.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 13).toString());
            Tlokasi3.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 14).toString());
            Tlokasi4.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 15).toString());
            cekTglDibuat = tbStatus.getValueAt(tbStatus.getSelectedRow(), 18).toString();
            Valid.SetTgl(TtglDibuat, tbStatus.getValueAt(tbStatus.getSelectedRow(), 19).toString());
            cekTglInser = tbStatus.getValueAt(tbStatus.getSelectedRow(), 20).toString();
            Valid.SetTgl(TtglInsersi, tbStatus.getValueAt(tbStatus.getSelectedRow(), 21).toString());
            TlokasiShort.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 22).toString());
            Valid.SetTgl(TtglInsersiShort, tbStatus.getValueAt(tbStatus.getSelectedRow(), 23).toString());
            cekTglOffShort = tbStatus.getValueAt(tbStatus.getSelectedRow(), 24).toString();
            Valid.SetTgl(TtglOffShort, tbStatus.getValueAt(tbStatus.getSelectedRow(), 25).toString());
            TlokasiLong.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 26).toString());
            Valid.SetTgl(TtglBerhenti, tbStatus.getValueAt(tbStatus.getSelectedRow(), 27).toString());
            cekTglOffLong = tbStatus.getValueAt(tbStatus.getSelectedRow(), 28).toString();
            Valid.SetTgl(TtglOffLong, tbStatus.getValueAt(tbStatus.getSelectedRow(), 29).toString());
            cekTglMulaiFemo = tbStatus.getValueAt(tbStatus.getSelectedRow(), 30).toString();
            Valid.SetTgl(TtglMulaiFemo, tbStatus.getValueAt(tbStatus.getSelectedRow(), 31).toString());
            Tdiag1.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 32).toString());
            Tdiag2.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 33).toString());
            Tdiag3.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 34).toString());
            Tdiag4.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 35).toString());
            Tdiag5.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 36).toString());
            Valid.SetTgl(TtglDiag1, tbStatus.getValueAt(tbStatus.getSelectedRow(), 37).toString());
            Valid.SetTgl(TtglDiag2, tbStatus.getValueAt(tbStatus.getSelectedRow(), 38).toString());
            Valid.SetTgl(TtglDiag3, tbStatus.getValueAt(tbStatus.getSelectedRow(), 39).toString());
            Valid.SetTgl(TtglDiag4, tbStatus.getValueAt(tbStatus.getSelectedRow(), 40).toString());
            Valid.SetTgl(TtglDiag5, tbStatus.getValueAt(tbStatus.getSelectedRow(), 41).toString());
            Tkomplikasi1.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 42).toString());
            Tkomplikasi2.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 43).toString());
            Tkomplikasi3.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 44).toString());
            Tkomplikasi4.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 45).toString());
            Tkomplikasi5.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 46).toString());
            Valid.SetTgl(TtglKom1, tbStatus.getValueAt(tbStatus.getSelectedRow(), 47).toString());
            Valid.SetTgl(TtglKom2, tbStatus.getValueAt(tbStatus.getSelectedRow(), 48).toString());
            Valid.SetTgl(TtglKom3, tbStatus.getValueAt(tbStatus.getSelectedRow(), 49).toString());
            Valid.SetTgl(TtglKom4, tbStatus.getValueAt(tbStatus.getSelectedRow(), 50).toString());
            Valid.SetTgl(TtglKom5, tbStatus.getValueAt(tbStatus.getSelectedRow(), 51).toString());
            dataCek();
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.gethemodialisa());
        BtnGanti.setEnabled(akses.gethemodialisa());
        BtnHapus.setEnabled(akses.gethemodialisa());
    }
    
    private void cekData() {
        if (chkEmergensi.isSelected() == true) {
            cekEmer = "ya";
        } else {
            cekEmer = "tidak";
        }
        
        if (chkTerencana.isSelected() == true) {
            cekTeren = "ya";
        } else {
            cekTeren = "tidak";
        }
        
        if (chkTglOps.isSelected() == true) {
            cekTglOps = "ya";
            tglOps = Valid.SetTgl(TtglOps.getSelectedItem() + "");
        } else {
            cekTglOps = "tidak";
            tglOps = "0000-00-00";
        }
        
        if (chkTglDibuat.isSelected() == true) {
            cekTglDibuat = "ya";
            tglDibuat = Valid.SetTgl(TtglDibuat.getSelectedItem() + "");
        } else {
            cekTglDibuat = "tidak";
            tglDibuat = "0000-00-00";
        }
        
        if (chkTglInsersi.isSelected() == true) {
            cekTglInser = "ya";
            tglInsersi = Valid.SetTgl(TtglInsersi.getSelectedItem() + "");
        } else {
            cekTglInser = "tidak";
            tglInsersi = "0000-00-00";
        }
        
        if (chkTglOffShort.isSelected() == true) {
            cekTglOffShort = "ya";
            tglOffShort = Valid.SetTgl(TtglOffShort.getSelectedItem() + "");
        } else {
            cekTglOffShort = "tidak";
            tglOffShort = "0000-00-00";
        }
        
        if (chkTglOffLong.isSelected() == true) {
            cekTglOffLong = "ya";
            tglOffLong = Valid.SetTgl(TtglOffLong.getSelectedItem() + "");
        } else {
            cekTglOffLong = "tidak";
            tglOffLong = "0000-00-00";
        }
        
        if (chkTglMulaiFemo.isSelected() == true) {
            cekTglMulaiFemo = "ya";
            tglMulaiFemo = Valid.SetTgl(TtglMulaiFemo.getSelectedItem() + "");
        } else {
            cekTglMulaiFemo = "tidak";
            tglMulaiFemo = "0000-00-00";
        }
    }
    
    public void setData(String norw) {
        TNoRw.setText(norw);
        isPasien();
        Valid.SetTgl(DTPCari1, tglReg);
        Valid.SetTgl(TtglMulaiHD, tglReg);
        TCari.setText(norw);
    }
    
    private void isPasien() {
        try {
            ps1 = koneksi.prepareStatement("select p.*, concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', ',kb.nm_kab) almat, "
                    + "concat(rp.umurdaftar,' ',rp.sttsumur,'.') usia, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, rp.tgl_registrasi from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join kelurahan kl on kl.kd_kel=p.kd_kel "
                    + "inner join kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "inner join kabupaten kb on kb.kd_kab=p.kd_kab where rp.no_rawat='" + TNoRw.getText() + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    TNoRM.setText(rs1.getString("no_rkm_medis"));
                    TPasien.setText(rs1.getString("nm_pasien"));
                    Talamat.setText(rs1.getString("almat"));
                    Tnotelp.setText(rs1.getString("no_tlp"));
                    Tumur.setText(rs1.getString("usia"));
                    TtglLahir.setText(rs1.getString("tgllahir"));
                    tglReg = rs1.getString("tgl_registrasi");
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
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
    
    private void dataCek() {
        if (cekEmer.equals("ya")) {
            chkEmergensi.setSelected(true);
        } else {
            chkEmergensi.setSelected(false);
        }
        
        if (cekTeren.equals("ya")) {
            chkTerencana.setSelected(true);
        } else {
            chkTerencana.setSelected(false);
        }
        
        if (cekTglOps.equals("ya")) {
            chkTglOps.setSelected(true);
            TtglOps.setEnabled(true);
            Tlokasi1.setEnabled(true);
            Tlokasi2.setEnabled(true);
            Tlokasi3.setEnabled(true);
            Tlokasi4.setEnabled(true);
        } else {
            chkTglOps.setSelected(false);
            TtglOps.setEnabled(false);
            Tlokasi1.setEnabled(false);
            Tlokasi2.setEnabled(false);
            Tlokasi3.setEnabled(false);
            Tlokasi4.setEnabled(false);
        }
        
        if (cekTglDibuat.equals("ya")) {
            chkTglDibuat.setSelected(true);
            TtglDibuat.setEnabled(true);
        } else {
            chkTglDibuat.setSelected(false);
            TtglDibuat.setEnabled(false);
        }
        
        if (cekTglInser.equals("ya")) {
            chkTglInsersi.setSelected(true);
            TtglInsersi.setEnabled(true);
        } else {
            chkTglInsersi.setSelected(false);
            TtglInsersi.setEnabled(false);
        }
        
        if (cekTglOffShort.equals("ya")) {
            chkTglOffShort.setSelected(true);
            TtglOffShort.setEnabled(true);
        } else {
            chkTglOffShort.setSelected(false);
            TtglOffShort.setEnabled(false);
        }
        
        if (cekTglOffLong.equals("ya")) {
            chkTglOffLong.setSelected(true);
            TtglOffLong.setEnabled(true);
        } else {
            chkTglOffLong.setSelected(false);
            TtglOffLong.setEnabled(false);
        }
        
        if (cekTglMulaiFemo.equals("ya")) {
            chkTglMulaiFemo.setSelected(true);
            TtglMulaiFemo.setEnabled(true);
        } else {
            chkTglMulaiFemo.setSelected(false);
            TtglMulaiFemo.setEnabled(false);
        }
        
        if (Tdiag1.getText().equals("")) {
            TtglDiag1.setDate(new Date());
        } else if (Tdiag2.getText().equals("")) {
            TtglDiag2.setDate(new Date());
        } else if (Tdiag3.getText().equals("")) {
            TtglDiag3.setDate(new Date());
        } else if (Tdiag4.getText().equals("")) {
            TtglDiag4.setDate(new Date());
        } else if (Tdiag5.getText().equals("")) {
            TtglDiag5.setDate(new Date());
        }
        
        if (Tkomplikasi1.getText().equals("")) {
            TtglKom1.setDate(new Date());
        } else if (Tkomplikasi2.getText().equals("")) {
            TtglKom2.setDate(new Date());
        } else if (Tkomplikasi3.getText().equals("")) {
            TtglKom3.setDate(new Date());
        } else if (Tkomplikasi4.getText().equals("")) {
            TtglKom4.setDate(new Date());
        } else if (Tkomplikasi5.getText().equals("")) {
            TtglKom5.setDate(new Date());
        }
    }

    private String tanggalAtauHariIni(String tanggal, String hariIni) {
        if (tanggal == null || tanggal.trim().isEmpty() || tanggal.equals("0000-00-00")) {
            return hariIni;
        }
        return tanggal;
    }
    
    public void awalData() {
        tampil();
    }
}
