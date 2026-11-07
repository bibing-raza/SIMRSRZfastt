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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public class RMLainHemodialisis extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private int i = 0, x = 0;
    private String cekMening = "", cekPindahHD = "", cekPindahCAPD = "", cekTglTranA = "", cekTglTranB = "", cekTglTravel1 = "", cekTglTravel2 = "",
            cekTglTravel3 = "", tglReg = "", sttsrwt = "", rgrawat = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMLainHemodialisis(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Meninggal", "Pindah Tempat HD", "Pindah Ke CAPD", "Transplantasi", "Traveling Dyalisis",
            "cek_tgl_meninggal", "tgl_meninggal", "tempat_meninggal", "penyebab_meninggal", "cek_tgl_pindah_hd", "tgl_pindah_hd", "pindah_ke_hd",
            "alasan_pindah_hd", "cek_tgl_pindah_capd", "tgl_pindah_capd", "alasan_pindah_capd", "cek_tgl_trans_a", "tgl_transplantasi_a", "tempat_transplantasi",
            "donor", "hubungan", "cek_tgl_trans_b", "tgl_transplantasi_b", "ke", "cek_tgl_travel1", "tgl_travel1", "tempat_travel1", "alasan_travel1",
            "cek_tgl_travel2", "tgl_travel2", "tempat_travel2", "alasan_travel2", "cek_tgl_travel3", "tgl_travel3", "tempat_travel3", "alasan_travel3",
            "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbStatus.setModel(tabMode);
        tbStatus.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbStatus.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 41; i++) {
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
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(100);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(100);
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
            } 
        }
        tbStatus.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbStatus.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbStatus.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbStatus.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbStatus.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbStatus.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbStatus.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbStatus.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbStatus.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

        TtmptMeninggal.setDocument(new batasInput((int) 100).getKata(TtmptMeninggal));
        TpenyeMeninggal.setDocument(new batasInput((int) 200).getKata(TpenyeMeninggal));
        TpindahHD.setDocument(new batasInput((int) 100).getKata(TpindahHD));
        TalasanPindahHd.setDocument(new batasInput((int) 200).getKata(TalasanPindahHd));
        TalasanCapd.setDocument(new batasInput((int) 200).getKata(TalasanCapd));
        Ttempat.setDocument(new batasInput((int) 100).getKata(Ttempat));
        Tdonor.setDocument(new batasInput((int) 200).getKata(Tdonor));
        Thubungan.setDocument(new batasInput((int) 100).getKata(Thubungan));
        Tke.setDocument(new batasInput((int) 100).getKata(Tke));
        TtempatTravel1.setDocument(new batasInput((int) 100).getKata(TtempatTravel1));
        TalasanTravel1.setDocument(new batasInput((int) 200).getKata(TalasanTravel1));
        TtempatTravel2.setDocument(new batasInput((int) 100).getKata(TtempatTravel2));
        TalasanTravel2.setDocument(new batasInput((int) 200).getKata(TalasanTravel2));
        TtempatTravel3.setDocument(new batasInput((int) 100).getKata(TtempatTravel3));
        TalasanTravel3.setDocument(new batasInput((int) 200).getKata(TalasanTravel3));
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
        TtglMeninggal = new widget.Tanggal();
        jLabel8 = new widget.Label();
        TtmptMeninggal = new widget.TextBox();
        jLabel9 = new widget.Label();
        TpenyeMeninggal = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        TtglPindahHd = new widget.Tanggal();
        jLabel13 = new widget.Label();
        TpindahHD = new widget.TextBox();
        jLabel14 = new widget.Label();
        TalasanPindahHd = new widget.TextBox();
        jLabel15 = new widget.Label();
        chkTglMeninggal = new widget.CekBox();
        chkTglPindahHD = new widget.CekBox();
        chkTglPindahCAPD = new widget.CekBox();
        TtglPindahCapd = new widget.Tanggal();
        jLabel16 = new widget.Label();
        TalasanCapd = new widget.TextBox();
        jLabel17 = new widget.Label();
        chkTglTransA = new widget.CekBox();
        TtglTransA = new widget.Tanggal();
        jLabel18 = new widget.Label();
        Ttempat = new widget.TextBox();
        jLabel19 = new widget.Label();
        Tdonor = new widget.TextBox();
        jLabel20 = new widget.Label();
        Thubungan = new widget.TextBox();
        chkTglTransB = new widget.CekBox();
        TtglTransB = new widget.Tanggal();
        jLabel21 = new widget.Label();
        Tke = new widget.TextBox();
        jLabel22 = new widget.Label();
        chkTglTravel1 = new widget.CekBox();
        TtglTravel1 = new widget.Tanggal();
        jLabel23 = new widget.Label();
        TtempatTravel1 = new widget.TextBox();
        jLabel24 = new widget.Label();
        TalasanTravel1 = new widget.TextBox();
        chkTglTravel2 = new widget.CekBox();
        TtglTravel2 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        TtempatTravel2 = new widget.TextBox();
        jLabel26 = new widget.Label();
        TalasanTravel2 = new widget.TextBox();
        chkTglTravel3 = new widget.CekBox();
        TtglTravel3 = new widget.Tanggal();
        jLabel27 = new widget.Label();
        TtempatTravel3 = new widget.TextBox();
        jLabel28 = new widget.Label();
        TalasanTravel3 = new widget.TextBox();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Lain - Lain Hemodialisis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass11.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(192, 407));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 615));
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

        TtglMeninggal.setEditable(false);
        TtglMeninggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2026" }));
        TtglMeninggal.setDisplayFormat("dd-MM-yyyy");
        TtglMeninggal.setName("TtglMeninggal"); // NOI18N
        TtglMeninggal.setOpaque(false);
        TtglMeninggal.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglMeninggal);
        TtglMeninggal.setBounds(114, 66, 90, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tempat Meninggal :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(200, 66, 120, 23);

        TtmptMeninggal.setBackground(new java.awt.Color(245, 250, 240));
        TtmptMeninggal.setForeground(new java.awt.Color(0, 0, 0));
        TtmptMeninggal.setName("TtmptMeninggal"); // NOI18N
        TtmptMeninggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtmptMeninggalKeyPressed(evt);
            }
        });
        FormInput.add(TtmptMeninggal);
        TtmptMeninggal.setBounds(325, 66, 397, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Penyebab Meninggal :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(200, 94, 120, 23);

        TpenyeMeninggal.setBackground(new java.awt.Color(245, 250, 240));
        TpenyeMeninggal.setForeground(new java.awt.Color(0, 0, 0));
        TpenyeMeninggal.setName("TpenyeMeninggal"); // NOI18N
        TpenyeMeninggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpenyeMeninggalKeyPressed(evt);
            }
        });
        FormInput.add(TpenyeMeninggal);
        TpenyeMeninggal.setBounds(325, 94, 397, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("1. Meninggal");
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 38, 110, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("2. Pindah Tempat HD");
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 122, 155, 23);

        TtglPindahHd.setEditable(false);
        TtglPindahHd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2026" }));
        TtglPindahHd.setDisplayFormat("dd-MM-yyyy");
        TtglPindahHd.setName("TtglPindahHd"); // NOI18N
        TtglPindahHd.setOpaque(false);
        TtglPindahHd.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglPindahHd);
        TtglPindahHd.setBounds(114, 150, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Pindah Ke :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(200, 150, 100, 23);

        TpindahHD.setBackground(new java.awt.Color(245, 250, 240));
        TpindahHD.setForeground(new java.awt.Color(0, 0, 0));
        TpindahHD.setName("TpindahHD"); // NOI18N
        TpindahHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpindahHDKeyPressed(evt);
            }
        });
        FormInput.add(TpindahHD);
        TpindahHD.setBounds(305, 150, 417, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Alasan Pindah : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(200, 178, 100, 23);

        TalasanPindahHd.setBackground(new java.awt.Color(245, 250, 240));
        TalasanPindahHd.setForeground(new java.awt.Color(0, 0, 0));
        TalasanPindahHd.setName("TalasanPindahHd"); // NOI18N
        TalasanPindahHd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalasanPindahHdKeyPressed(evt);
            }
        });
        FormInput.add(TalasanPindahHd);
        TalasanPindahHd.setBounds(305, 178, 417, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("3. Pindah Ke CAPD");
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 206, 140, 23);

        chkTglMeninggal.setBackground(new java.awt.Color(255, 255, 250));
        chkTglMeninggal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglMeninggal.setForeground(new java.awt.Color(0, 0, 0));
        chkTglMeninggal.setText("Tanggal : ");
        chkTglMeninggal.setBorderPainted(true);
        chkTglMeninggal.setBorderPaintedFlat(true);
        chkTglMeninggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglMeninggal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglMeninggal.setName("chkTglMeninggal"); // NOI18N
        chkTglMeninggal.setOpaque(false);
        chkTglMeninggal.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglMeninggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglMeninggalActionPerformed(evt);
            }
        });
        FormInput.add(chkTglMeninggal);
        chkTglMeninggal.setBounds(0, 66, 110, 23);

        chkTglPindahHD.setBackground(new java.awt.Color(255, 255, 250));
        chkTglPindahHD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglPindahHD.setForeground(new java.awt.Color(0, 0, 0));
        chkTglPindahHD.setText("Tanggal : ");
        chkTglPindahHD.setBorderPainted(true);
        chkTglPindahHD.setBorderPaintedFlat(true);
        chkTglPindahHD.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglPindahHD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglPindahHD.setName("chkTglPindahHD"); // NOI18N
        chkTglPindahHD.setOpaque(false);
        chkTglPindahHD.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglPindahHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglPindahHDActionPerformed(evt);
            }
        });
        FormInput.add(chkTglPindahHD);
        chkTglPindahHD.setBounds(0, 150, 110, 23);

        chkTglPindahCAPD.setBackground(new java.awt.Color(255, 255, 250));
        chkTglPindahCAPD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglPindahCAPD.setForeground(new java.awt.Color(0, 0, 0));
        chkTglPindahCAPD.setText("Tanggal : ");
        chkTglPindahCAPD.setBorderPainted(true);
        chkTglPindahCAPD.setBorderPaintedFlat(true);
        chkTglPindahCAPD.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglPindahCAPD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglPindahCAPD.setName("chkTglPindahCAPD"); // NOI18N
        chkTglPindahCAPD.setOpaque(false);
        chkTglPindahCAPD.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglPindahCAPD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglPindahCAPDActionPerformed(evt);
            }
        });
        FormInput.add(chkTglPindahCAPD);
        chkTglPindahCAPD.setBounds(0, 234, 110, 23);

        TtglPindahCapd.setEditable(false);
        TtglPindahCapd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2026" }));
        TtglPindahCapd.setDisplayFormat("dd-MM-yyyy");
        TtglPindahCapd.setName("TtglPindahCapd"); // NOI18N
        TtglPindahCapd.setOpaque(false);
        TtglPindahCapd.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglPindahCapd);
        TtglPindahCapd.setBounds(114, 234, 90, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Alasan :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(200, 234, 70, 23);

        TalasanCapd.setBackground(new java.awt.Color(245, 250, 240));
        TalasanCapd.setForeground(new java.awt.Color(0, 0, 0));
        TalasanCapd.setName("TalasanCapd"); // NOI18N
        TalasanCapd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalasanCapdKeyPressed(evt);
            }
        });
        FormInput.add(TalasanCapd);
        TalasanCapd.setBounds(275, 234, 447, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("4. Transplantasi");
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 262, 130, 23);

        chkTglTransA.setBackground(new java.awt.Color(255, 255, 250));
        chkTglTransA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglTransA.setForeground(new java.awt.Color(0, 0, 0));
        chkTglTransA.setText("a. Tanggal : ");
        chkTglTransA.setBorderPainted(true);
        chkTglTransA.setBorderPaintedFlat(true);
        chkTglTransA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglTransA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglTransA.setName("chkTglTransA"); // NOI18N
        chkTglTransA.setOpaque(false);
        chkTglTransA.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglTransA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglTransAActionPerformed(evt);
            }
        });
        FormInput.add(chkTglTransA);
        chkTglTransA.setBounds(0, 290, 110, 23);

        TtglTransA.setEditable(false);
        TtglTransA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2026" }));
        TtglTransA.setDisplayFormat("dd-MM-yyyy");
        TtglTransA.setName("TtglTransA"); // NOI18N
        TtglTransA.setOpaque(false);
        TtglTransA.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglTransA);
        TtglTransA.setBounds(114, 290, 90, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tempat :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(200, 290, 70, 23);

        Ttempat.setBackground(new java.awt.Color(245, 250, 240));
        Ttempat.setForeground(new java.awt.Color(0, 0, 0));
        Ttempat.setName("Ttempat"); // NOI18N
        Ttempat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtempatKeyPressed(evt);
            }
        });
        FormInput.add(Ttempat);
        Ttempat.setBounds(275, 290, 447, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Donor :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(200, 318, 70, 23);

        Tdonor.setBackground(new java.awt.Color(245, 250, 240));
        Tdonor.setForeground(new java.awt.Color(0, 0, 0));
        Tdonor.setName("Tdonor"); // NOI18N
        Tdonor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdonorKeyPressed(evt);
            }
        });
        FormInput.add(Tdonor);
        Tdonor.setBounds(275, 318, 447, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Hubungan Antara Donor Dan Resipien :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(30, 346, 240, 23);

        Thubungan.setBackground(new java.awt.Color(245, 250, 240));
        Thubungan.setForeground(new java.awt.Color(0, 0, 0));
        Thubungan.setName("Thubungan"); // NOI18N
        Thubungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThubunganKeyPressed(evt);
            }
        });
        FormInput.add(Thubungan);
        Thubungan.setBounds(275, 346, 447, 23);

        chkTglTransB.setBackground(new java.awt.Color(255, 255, 250));
        chkTglTransB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglTransB.setForeground(new java.awt.Color(0, 0, 0));
        chkTglTransB.setText("b. Tanggal : ");
        chkTglTransB.setBorderPainted(true);
        chkTglTransB.setBorderPaintedFlat(true);
        chkTglTransB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglTransB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglTransB.setName("chkTglTransB"); // NOI18N
        chkTglTransB.setOpaque(false);
        chkTglTransB.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglTransB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglTransBActionPerformed(evt);
            }
        });
        FormInput.add(chkTglTransB);
        chkTglTransB.setBounds(0, 374, 110, 23);

        TtglTransB.setEditable(false);
        TtglTransB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2026" }));
        TtglTransB.setDisplayFormat("dd-MM-yyyy");
        TtglTransB.setName("TtglTransB"); // NOI18N
        TtglTransB.setOpaque(false);
        TtglTransB.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglTransB);
        TtglTransB.setBounds(114, 374, 90, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Ke :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(200, 374, 70, 23);

        Tke.setBackground(new java.awt.Color(245, 250, 240));
        Tke.setForeground(new java.awt.Color(0, 0, 0));
        Tke.setName("Tke"); // NOI18N
        Tke.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeKeyPressed(evt);
            }
        });
        FormInput.add(Tke);
        Tke.setBounds(275, 374, 447, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("5. Traveling Dyalisis");
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 402, 150, 23);

        chkTglTravel1.setBackground(new java.awt.Color(255, 255, 250));
        chkTglTravel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglTravel1.setForeground(new java.awt.Color(0, 0, 0));
        chkTglTravel1.setText("Tanggal : ");
        chkTglTravel1.setBorderPainted(true);
        chkTglTravel1.setBorderPaintedFlat(true);
        chkTglTravel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglTravel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglTravel1.setName("chkTglTravel1"); // NOI18N
        chkTglTravel1.setOpaque(false);
        chkTglTravel1.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglTravel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglTravel1ActionPerformed(evt);
            }
        });
        FormInput.add(chkTglTravel1);
        chkTglTravel1.setBounds(0, 430, 110, 23);

        TtglTravel1.setEditable(false);
        TtglTravel1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2026" }));
        TtglTravel1.setDisplayFormat("dd-MM-yyyy");
        TtglTravel1.setName("TtglTravel1"); // NOI18N
        TtglTravel1.setOpaque(false);
        TtglTravel1.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglTravel1);
        TtglTravel1.setBounds(114, 430, 90, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tempat :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(200, 430, 70, 23);

        TtempatTravel1.setBackground(new java.awt.Color(245, 250, 240));
        TtempatTravel1.setForeground(new java.awt.Color(0, 0, 0));
        TtempatTravel1.setName("TtempatTravel1"); // NOI18N
        TtempatTravel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtempatTravel1KeyPressed(evt);
            }
        });
        FormInput.add(TtempatTravel1);
        TtempatTravel1.setBounds(275, 430, 447, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Alasan :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(200, 458, 70, 23);

        TalasanTravel1.setBackground(new java.awt.Color(245, 250, 240));
        TalasanTravel1.setForeground(new java.awt.Color(0, 0, 0));
        TalasanTravel1.setName("TalasanTravel1"); // NOI18N
        TalasanTravel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalasanTravel1KeyPressed(evt);
            }
        });
        FormInput.add(TalasanTravel1);
        TalasanTravel1.setBounds(275, 458, 447, 23);

        chkTglTravel2.setBackground(new java.awt.Color(255, 255, 250));
        chkTglTravel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglTravel2.setForeground(new java.awt.Color(0, 0, 0));
        chkTglTravel2.setText("Tanggal : ");
        chkTglTravel2.setBorderPainted(true);
        chkTglTravel2.setBorderPaintedFlat(true);
        chkTglTravel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglTravel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglTravel2.setName("chkTglTravel2"); // NOI18N
        chkTglTravel2.setOpaque(false);
        chkTglTravel2.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglTravel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglTravel2ActionPerformed(evt);
            }
        });
        FormInput.add(chkTglTravel2);
        chkTglTravel2.setBounds(0, 486, 110, 23);

        TtglTravel2.setEditable(false);
        TtglTravel2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2026" }));
        TtglTravel2.setDisplayFormat("dd-MM-yyyy");
        TtglTravel2.setName("TtglTravel2"); // NOI18N
        TtglTravel2.setOpaque(false);
        TtglTravel2.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglTravel2);
        TtglTravel2.setBounds(114, 486, 90, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Tempat :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(200, 486, 70, 23);

        TtempatTravel2.setBackground(new java.awt.Color(245, 250, 240));
        TtempatTravel2.setForeground(new java.awt.Color(0, 0, 0));
        TtempatTravel2.setName("TtempatTravel2"); // NOI18N
        TtempatTravel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtempatTravel2KeyPressed(evt);
            }
        });
        FormInput.add(TtempatTravel2);
        TtempatTravel2.setBounds(275, 486, 447, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Alasan :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(200, 514, 70, 23);

        TalasanTravel2.setBackground(new java.awt.Color(245, 250, 240));
        TalasanTravel2.setForeground(new java.awt.Color(0, 0, 0));
        TalasanTravel2.setName("TalasanTravel2"); // NOI18N
        TalasanTravel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalasanTravel2KeyPressed(evt);
            }
        });
        FormInput.add(TalasanTravel2);
        TalasanTravel2.setBounds(275, 514, 447, 23);

        chkTglTravel3.setBackground(new java.awt.Color(255, 255, 250));
        chkTglTravel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglTravel3.setForeground(new java.awt.Color(0, 0, 0));
        chkTglTravel3.setText("Tanggal : ");
        chkTglTravel3.setBorderPainted(true);
        chkTglTravel3.setBorderPaintedFlat(true);
        chkTglTravel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglTravel3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglTravel3.setName("chkTglTravel3"); // NOI18N
        chkTglTravel3.setOpaque(false);
        chkTglTravel3.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglTravel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglTravel3ActionPerformed(evt);
            }
        });
        FormInput.add(chkTglTravel3);
        chkTglTravel3.setBounds(0, 542, 110, 23);

        TtglTravel3.setEditable(false);
        TtglTravel3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2026" }));
        TtglTravel3.setDisplayFormat("dd-MM-yyyy");
        TtglTravel3.setName("TtglTravel3"); // NOI18N
        TtglTravel3.setOpaque(false);
        TtglTravel3.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglTravel3);
        TtglTravel3.setBounds(114, 542, 90, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Tempat :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(200, 542, 70, 23);

        TtempatTravel3.setBackground(new java.awt.Color(245, 250, 240));
        TtempatTravel3.setForeground(new java.awt.Color(0, 0, 0));
        TtempatTravel3.setName("TtempatTravel3"); // NOI18N
        TtempatTravel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtempatTravel3KeyPressed(evt);
            }
        });
        FormInput.add(TtempatTravel3);
        TtempatTravel3.setBounds(275, 542, 447, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Alasan :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(200, 570, 70, 23);

        TalasanTravel3.setBackground(new java.awt.Color(245, 250, 240));
        TalasanTravel3.setForeground(new java.awt.Color(0, 0, 0));
        TalasanTravel3.setName("TalasanTravel3"); // NOI18N
        FormInput.add(TalasanTravel3);
        TalasanTravel3.setBounds(275, 570, 447, 23);

        Scroll1.setViewportView(FormInput);

        panelGlass11.add(Scroll1);

        panelGlass12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Lain - Lain Hemodialisis ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
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

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2026" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-11-2026" }));
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
        BtnPrint.setEnabled(false);
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
            if (Sequel.menyimpantf("lainlain_hemodialisis", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 35, new String[]{
                TNoRw.getText(), sttsrwt, rgrawat, cekMening, Valid.SetTgl(TtglMeninggal.getSelectedItem() + ""), TtmptMeninggal.getText(), TpenyeMeninggal.getText(),
                cekPindahHD, Valid.SetTgl(TtglPindahHd.getSelectedItem() + ""), TpindahHD.getText(), TalasanPindahHd.getText(), cekPindahCAPD,
                Valid.SetTgl(TtglPindahCapd.getSelectedItem() + ""), TalasanCapd.getText(), cekTglTranA, Valid.SetTgl(TtglTransA.getSelectedItem() + ""),
                Ttempat.getText(), Tdonor.getText(), Thubungan.getText(), cekTglTranB, Valid.SetTgl(TtglTransB.getSelectedItem() + ""), Tke.getText(),
                cekTglTravel1, Valid.SetTgl(TtglTravel1.getSelectedItem() + ""), TtempatTravel1.getText(), TalasanTravel1.getText(),
                cekTglTravel2, Valid.SetTgl(TtglTravel2.getSelectedItem() + ""), TtempatTravel2.getText(), TalasanTravel2.getText(),
                cekTglTravel3, Valid.SetTgl(TtglTravel3.getSelectedItem() + ""), TtempatTravel3.getText(), TalasanTravel3.getText(),
                Sequel.cariIsi("select now()")
            }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Lain-Lain Hemodialisis", "Simpan");
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
            if (Sequel.mengedittf("lainlain_hemodialisis", "waktu_simpan=?", "cek_tgl_meninggal=?, tgl_meninggal=?, tempat_meninggal=?, penyebab_meninggal=?, cek_tgl_pindah_hd=?, "
                    + "tgl_pindah_hd=?, pindah_ke_hd=?, alasan_pindah_hd=?, cek_tgl_pindah_capd=?, tgl_pindah_capd=?, alasan_pindah_capd=?, cek_tgl_trans_a=?, tgl_transplantasi_a=?, "
                    + "tempat_transplantasi=?, donor=?, hubungan=?, cek_tgl_trans_b=?, tgl_transplantasi_b=?, ke=?, cek_tgl_travel1=?, tgl_travel1=?, tempat_travel1=?, alasan_travel1=?, "
                    + "cek_tgl_travel2=?, tgl_travel2=?, tempat_travel2=?, alasan_travel2=?, cek_tgl_travel3=?, tgl_travel3=?, tempat_travel3=?, alasan_travel3=?", 32, new String[]{
                        cekMening, Valid.SetTgl(TtglMeninggal.getSelectedItem() + ""), TtmptMeninggal.getText(), TpenyeMeninggal.getText(),
                        cekPindahHD, Valid.SetTgl(TtglPindahHd.getSelectedItem() + ""), TpindahHD.getText(), TalasanPindahHd.getText(), cekPindahCAPD,
                        Valid.SetTgl(TtglPindahCapd.getSelectedItem() + ""), TalasanCapd.getText(), cekTglTranA, Valid.SetTgl(TtglTransA.getSelectedItem() + ""),
                        Ttempat.getText(), Tdonor.getText(), Thubungan.getText(), cekTglTranB, Valid.SetTgl(TtglTransB.getSelectedItem() + ""), Tke.getText(),
                        cekTglTravel1, Valid.SetTgl(TtglTravel1.getSelectedItem() + ""), TtempatTravel1.getText(), TalasanTravel1.getText(),
                        cekTglTravel2, Valid.SetTgl(TtglTravel2.getSelectedItem() + ""), TtempatTravel2.getText(), TalasanTravel2.getText(),
                        cekTglTravel3, Valid.SetTgl(TtglTravel3.getSelectedItem() + ""), TtempatTravel3.getText(), TalasanTravel3.getText(),
                        tbStatus.getValueAt(tbStatus.getSelectedRow(), 40).toString()
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Lain-Lain Hemodialisis", "Ganti");
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

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbStatus.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from lainlain_hemodialisis where waktu_simpan=?", 1, new String[]{
                    tbStatus.getValueAt(tbStatus.getSelectedRow(), 40).toString()
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
            
            Valid.MyReport("rptStatusHemodialisis2.jasper", "report", "::[ Status Hemodialisis (Data Status) ]::",
                    "SELECT date(now()) tgl", param);
            
            Valid.MyReport("rptStatusHemodialisis1.jasper", "report", "::[ Status Hemodialisis (Sampul/Cover) ]::",
                    "SELECT date(now()) tgl", param);
            
            emptTeks();
            tampil();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel....");
            tampil();
            tbStatus.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void chkTglMeninggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglMeninggalActionPerformed
        TtglMeninggal.setDate(new Date());
        TtmptMeninggal.setText("");
        TpenyeMeninggal.setText("");

        if (chkTglMeninggal.isSelected() == true) {
            TtglMeninggal.setEnabled(true);
            TtglMeninggal.requestFocus();
            TtmptMeninggal.setEnabled(true);
            TpenyeMeninggal.setEnabled(true);
        } else {
            TtglMeninggal.setEnabled(false);
            TtmptMeninggal.setEnabled(false);
            TpenyeMeninggal.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglMeninggalActionPerformed

    private void chkTglPindahHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglPindahHDActionPerformed
        TtglPindahHd.setDate(new Date());
        TpindahHD.setText("");
        TalasanPindahHd.setText("");

        if (chkTglPindahHD.isSelected() == true) {
            TtglPindahHd.setEnabled(true);
            TtglPindahHd.requestFocus();
            TpindahHD.setEnabled(true);
            TalasanPindahHd.setEnabled(true);
        } else {
            TtglPindahHd.setEnabled(false);
            TpindahHD.setEnabled(false);
            TalasanPindahHd.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglPindahHDActionPerformed

    private void chkTglPindahCAPDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglPindahCAPDActionPerformed
        TtglPindahCapd.setDate(new Date());
        TalasanCapd.setText("");

        if (chkTglPindahCAPD.isSelected() == true) {
            TtglPindahCapd.setEnabled(true);
            TtglPindahCapd.requestFocus();
            TalasanCapd.setEnabled(true);
        } else {
            TtglPindahCapd.setEnabled(false);
            TalasanCapd.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglPindahCAPDActionPerformed

    private void chkTglTransAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglTransAActionPerformed
        TtglTransA.setDate(new Date());
        Ttempat.setText("");
        Tdonor.setText("");
        Thubungan.setText("");

        if (chkTglTransA.isSelected() == true) {
            TtglTransA.setEnabled(true);
            TtglTransA.requestFocus();
            Ttempat.setEnabled(true);
            Tdonor.setEnabled(true);
            Thubungan.setEnabled(true);
        } else {
            TtglTransA.setEnabled(false);
            Ttempat.setEnabled(false);
            Tdonor.setEnabled(false);
            Thubungan.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglTransAActionPerformed

    private void chkTglTransBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglTransBActionPerformed
        TtglTransB.setDate(new Date());
        Tke.setText("");

        if (chkTglTransB.isSelected() == true) {
            TtglTransB.setEnabled(true);
            TtglTransB.requestFocus();
            Tke.setEnabled(true);
        } else {
            TtglTransB.setEnabled(false);
            Tke.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglTransBActionPerformed

    private void chkTglTravel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglTravel1ActionPerformed
        TtglTravel1.setDate(new Date());
        TtempatTravel1.setText("");
        TalasanTravel1.setText("");

        if (chkTglTravel1.isSelected() == true) {
            TtglTravel1.setEnabled(true);
            TtglTravel1.requestFocus();
            TtempatTravel1.setEnabled(true);
            TalasanTravel1.setEnabled(true);
        } else {
            TtglTravel1.setEnabled(false);
            TtempatTravel1.setEnabled(false);
            TalasanTravel1.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglTravel1ActionPerformed

    private void chkTglTravel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglTravel2ActionPerformed
        TtglTravel2.setDate(new Date());
        TtempatTravel2.setText("");
        TalasanTravel2.setText("");

        if (chkTglTravel2.isSelected() == true) {
            TtglTravel2.setEnabled(true);
            TtglTravel2.requestFocus();
            TtempatTravel2.setEnabled(true);
            TalasanTravel2.setEnabled(true);
        } else {
            TtglTravel2.setEnabled(false);
            TtempatTravel2.setEnabled(false);
            TalasanTravel2.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglTravel2ActionPerformed

    private void chkTglTravel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglTravel3ActionPerformed
        TtglTravel3.setDate(new Date());
        TtempatTravel3.setText("");
        TalasanTravel3.setText("");

        if (chkTglTravel3.isSelected() == true) {
            TtglTravel3.setEnabled(true);
            TtglTravel3.requestFocus();
            TtempatTravel3.setEnabled(true);
            TalasanTravel3.setEnabled(true);
        } else {
            TtglTravel1.setEnabled(false);
            TtempatTravel1.setEnabled(false);
            TalasanTravel1.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglTravel3ActionPerformed

    private void TtmptMeninggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtmptMeninggalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TpenyeMeninggal.requestFocus();
        }
    }//GEN-LAST:event_TtmptMeninggalKeyPressed

    private void TpenyeMeninggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpenyeMeninggalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTglPindahHD.requestFocus();
        }
    }//GEN-LAST:event_TpenyeMeninggalKeyPressed

    private void TpindahHDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpindahHDKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TalasanPindahHd.requestFocus();
        }
    }//GEN-LAST:event_TpindahHDKeyPressed

    private void TalasanPindahHdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalasanPindahHdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTglPindahCAPD.requestFocus();
        }
    }//GEN-LAST:event_TalasanPindahHdKeyPressed

    private void TalasanCapdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalasanCapdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTglTransA.requestFocus();
        }
    }//GEN-LAST:event_TalasanCapdKeyPressed

    private void TtempatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtempatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdonor.requestFocus();
        }
    }//GEN-LAST:event_TtempatKeyPressed

    private void TdonorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdonorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Thubungan.requestFocus();
        }
    }//GEN-LAST:event_TdonorKeyPressed

    private void ThubunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThubunganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTglTransB.requestFocus();
        }
    }//GEN-LAST:event_ThubunganKeyPressed

    private void TkeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTglTravel1.requestFocus();
        }
    }//GEN-LAST:event_TkeKeyPressed

    private void TtempatTravel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtempatTravel1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TalasanTravel1.requestFocus();
        }
    }//GEN-LAST:event_TtempatTravel1KeyPressed

    private void TalasanTravel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalasanTravel1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTglTravel2.requestFocus();
        }
    }//GEN-LAST:event_TalasanTravel1KeyPressed

    private void TtempatTravel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtempatTravel2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TalasanTravel2.requestFocus();
        }
    }//GEN-LAST:event_TtempatTravel2KeyPressed

    private void TalasanTravel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalasanTravel2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTglTravel3.requestFocus();
        }
    }//GEN-LAST:event_TalasanTravel2KeyPressed

    private void TtempatTravel3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtempatTravel3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TalasanTravel3.requestFocus();
        }
    }//GEN-LAST:event_TtempatTravel3KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMLainHemodialisis dialog = new RMLainHemodialisis(new javax.swing.JFrame(), true);
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
    private widget.TextBox TalasanCapd;
    private widget.TextBox TalasanPindahHd;
    private widget.TextBox TalasanTravel1;
    private widget.TextBox TalasanTravel2;
    private widget.TextBox TalasanTravel3;
    private widget.TextBox Tdonor;
    private widget.TextBox Thubungan;
    private widget.TextBox Tke;
    private widget.TextBox TpenyeMeninggal;
    private widget.TextBox TpindahHD;
    private widget.TextBox Ttempat;
    private widget.TextBox TtempatTravel1;
    private widget.TextBox TtempatTravel2;
    private widget.TextBox TtempatTravel3;
    private widget.Tanggal TtglMeninggal;
    private widget.Tanggal TtglPindahCapd;
    private widget.Tanggal TtglPindahHd;
    private widget.Tanggal TtglTransA;
    private widget.Tanggal TtglTransB;
    private widget.Tanggal TtglTravel1;
    private widget.Tanggal TtglTravel2;
    private widget.Tanggal TtglTravel3;
    private widget.TextBox TtmptMeninggal;
    public widget.CekBox chkTglMeninggal;
    public widget.CekBox chkTglPindahCAPD;
    public widget.CekBox chkTglPindahHD;
    public widget.CekBox chkTglTransA;
    public widget.CekBox chkTglTransB;
    public widget.CekBox chkTglTravel1;
    public widget.CekBox chkTglTravel2;
    public widget.CekBox chkTglTravel3;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
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
    private widget.Label jLabel4;
    private widget.Label jLabel49;
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
            ps = koneksi.prepareStatement("select lh.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                    + "if(lh.cek_tgl_meninggal='ya','V','') meninggal, if(lh.cek_tgl_pindah_hd='ya','V','') pindahHD, if(lh.cek_tgl_pindah_capd='ya','V','') pindahCapd, "
                    + "if(lh.cek_tgl_trans_a='ya','V','') transplan from lainlain_hemodialisis lh "
                    + "inner join reg_periksa rp on rp.no_rawat=lh.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                    + "date(lh.waktu_simpan) between ? and ? and lh.no_rawat like ? or "
                    + "date(lh.waktu_simpan) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(lh.waktu_simpan) between ? and ? and p.nm_pasien like ? order by lh.waktu_simpan desc");
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
                rs = ps.executeQuery();                
                while (rs.next()) {
                    String traveling = "", transplan = "";
                    if (rs.getString("cek_tgl_travel1").equals("ya") || rs.getString("cek_tgl_travel2").equals("ya") || rs.getString("cek_tgl_travel3").equals("ya")) {
                        traveling = "V";
                    } else {
                        traveling = "";
                    }
                    
                    if (rs.getString("cek_tgl_trans_a").equals("ya") || rs.getString("cek_tgl_trans_b").equals("ya")) {
                        transplan = "V";
                    } else {
                        transplan = "";
                    }
                    
                    tabMode.addRow(new String[]{                        
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglLahir"),
                        rs.getString("meninggal"),                        
                        rs.getString("pindahHD"),                        
                        rs.getString("pindahCapd"),                        
                        transplan,
                        traveling,
                        rs.getString("cek_tgl_meninggal"),
                        rs.getString("tgl_meninggal"),
                        rs.getString("tempat_meninggal"),
                        rs.getString("penyebab_meninggal"),
                        rs.getString("cek_tgl_pindah_hd"),
                        rs.getString("tgl_pindah_hd"),
                        rs.getString("pindah_ke_hd"),
                        rs.getString("alasan_pindah_hd"),
                        rs.getString("cek_tgl_pindah_capd"),
                        rs.getString("tgl_pindah_capd"),
                        rs.getString("alasan_pindah_capd"),
                        rs.getString("cek_tgl_trans_a"),
                        rs.getString("tgl_transplantasi_a"),
                        rs.getString("tempat_transplantasi"),
                        rs.getString("donor"),
                        rs.getString("hubungan"),
                        rs.getString("cek_tgl_trans_b"),
                        rs.getString("tgl_transplantasi_b"),
                        rs.getString("ke"),
                        rs.getString("cek_tgl_travel1"),
                        rs.getString("tgl_travel1"),
                        rs.getString("tempat_travel1"),
                        rs.getString("alasan_travel1"),
                        rs.getString("cek_tgl_travel2"),
                        rs.getString("tgl_travel2"),
                        rs.getString("tempat_travel2"),
                        rs.getString("alasan_travel2"),
                        rs.getString("cek_tgl_travel3"),
                        rs.getString("tgl_travel3"),
                        rs.getString("tempat_travel3"),
                        rs.getString("alasan_travel3"),
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
        chkTglMeninggal.setSelected(false);
        TtglMeninggal.setDate(new Date());
        TtmptMeninggal.setText("");
        TpenyeMeninggal.setText("");
        TtglMeninggal.setEnabled(false);
        TtmptMeninggal.setEnabled(false);
        TpenyeMeninggal.setEnabled(false);        
        chkTglPindahHD.setSelected(false);
        TtglPindahHd.setDate(new Date());
        TpindahHD.setText("");
        TalasanPindahHd.setText("");
        TtglPindahHd.setEnabled(false);
        TpindahHD.setEnabled(false);
        TalasanPindahHd.setEnabled(false);        
        chkTglPindahCAPD.setSelected(false);
        TtglPindahCapd.setDate(new Date());
        TalasanCapd.setText("");
        TtglPindahCapd.setEnabled(false);
        TalasanCapd.setEnabled(false);        
        chkTglTransA.setSelected(false);
        TtglTransA.setDate(new Date());
        Ttempat.setText("");
        Tdonor.setText("");
        Thubungan.setText("");        
        TtglTransA.setEnabled(false);
        Ttempat.setEnabled(false);
        Tdonor.setEnabled(false);
        Thubungan.setEnabled(false);        
        chkTglTransB.setSelected(false);
        TtglTransB.setDate(new Date());
        Tke.setText("");        
        TtglTransB.setEnabled(false);
        Tke.setEnabled(false);        
        chkTglTravel1.setSelected(false);
        TtglTravel1.setDate(new Date());
        TtempatTravel1.setText("");
        TalasanTravel1.setText("");
        TtglTravel1.setEnabled(false);
        TtempatTravel1.setEnabled(false);
        TalasanTravel1.setEnabled(false);        
        chkTglTravel2.setSelected(false);
        TtglTravel2.setDate(new Date());
        TtempatTravel2.setText("");
        TalasanTravel2.setText("");
        TtglTravel2.setEnabled(false);
        TtempatTravel2.setEnabled(false);
        TalasanTravel2.setEnabled(false);        
        chkTglTravel3.setSelected(false);
        TtglTravel3.setDate(new Date());
        TtempatTravel3.setText("");
        TalasanTravel3.setText("");
        TtglTravel3.setEnabled(false);
        TtempatTravel3.setEnabled(false);
        TalasanTravel3.setEnabled(false);
    }

    private void getData() {
        cekMening = "";
        cekPindahHD = "";
        cekPindahCAPD = "";
        cekTglTranA = "";
        cekTglTranB = "";
        cekTglTravel1 = "";
        cekTglTravel2 = "";
        cekTglTravel3 = "";
        tglReg = "";
        sttsrwt = "";
        rgrawat = "";       
        
        if (tbStatus.getSelectedRow() != -1) {
            TNoRw.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 0).toString());
            isPasien();
            cekMening = tbStatus.getValueAt(tbStatus.getSelectedRow(), 9).toString();
            Valid.SetTgl(TtglMeninggal, tbStatus.getValueAt(tbStatus.getSelectedRow(), 10).toString());
            TtmptMeninggal.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 11).toString());
            TpenyeMeninggal.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 12).toString());
            cekPindahHD = tbStatus.getValueAt(tbStatus.getSelectedRow(), 13).toString();
            Valid.SetTgl(TtglPindahHd, tbStatus.getValueAt(tbStatus.getSelectedRow(), 14).toString());
            TpindahHD.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 15).toString());
            TalasanPindahHd.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 16).toString());
            cekPindahCAPD = tbStatus.getValueAt(tbStatus.getSelectedRow(), 17).toString();
            Valid.SetTgl(TtglPindahCapd, tbStatus.getValueAt(tbStatus.getSelectedRow(), 18).toString());
            TalasanCapd.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 19).toString());
            cekTglTranA = tbStatus.getValueAt(tbStatus.getSelectedRow(), 20).toString();
            Valid.SetTgl(TtglTransA, tbStatus.getValueAt(tbStatus.getSelectedRow(), 21).toString());
            Ttempat.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 22).toString());
            Tdonor.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 23).toString());
            Thubungan.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 24).toString());
            cekTglTranB = tbStatus.getValueAt(tbStatus.getSelectedRow(), 25).toString();
            Valid.SetTgl(TtglTransB, tbStatus.getValueAt(tbStatus.getSelectedRow(), 26).toString());
            Tke.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(), 27).toString());            
            cekTglTravel1 = tbStatus.getValueAt(tbStatus.getSelectedRow(), 28).toString();
            Valid.SetTgl(TtglTravel1, tbStatus.getValueAt(tbStatus.getSelectedRow(), 29).toString());
            TtempatTravel1.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(),30).toString());
            TalasanTravel1.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(),31).toString());            
            cekTglTravel2 = tbStatus.getValueAt(tbStatus.getSelectedRow(), 32).toString();
            Valid.SetTgl(TtglTravel2, tbStatus.getValueAt(tbStatus.getSelectedRow(), 33).toString());
            TtempatTravel2.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(),34).toString());
            TalasanTravel2.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(),35).toString());            
            cekTglTravel3 = tbStatus.getValueAt(tbStatus.getSelectedRow(), 36).toString();
            Valid.SetTgl(TtglTravel3, tbStatus.getValueAt(tbStatus.getSelectedRow(), 37).toString());
            TtempatTravel3.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(),38).toString());
            TalasanTravel3.setText(tbStatus.getValueAt(tbStatus.getSelectedRow(),39).toString());            
            dataCek();
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.gethemodialisa());
        BtnGanti.setEnabled(akses.gethemodialisa());
        BtnHapus.setEnabled(akses.gethemodialisa());
    }
    
    private void cekData() {
        if (chkTglMeninggal.isSelected() == true) {
            cekMening = "ya";
        } else {
            cekMening = "tidak";
        }
        
        if (chkTglPindahHD.isSelected() == true) {
            cekPindahHD = "ya";
        } else {
            cekPindahHD = "tidak";
        }
        
        if (chkTglPindahCAPD.isSelected() == true) {
            cekPindahCAPD = "ya";
        } else {
            cekPindahCAPD = "tidak";
        }
        
        if (chkTglTransA.isSelected() == true) {
            cekTglTranA = "ya";
        } else {
            cekTglTranA = "tidak";
        }
        
        if (chkTglTransB.isSelected() == true) {
            cekTglTranB = "ya";
        } else {
            cekTglTranB = "tidak";
        }
        
        if (chkTglTravel1.isSelected() == true) {
            cekTglTravel1 = "ya";
        } else {
            cekTglTravel1 = "tidak";
        }
        
        if (chkTglTravel2.isSelected() == true) {
            cekTglTravel2 = "ya";
        } else {
            cekTglTravel2 = "tidak";
        }
        
        if (chkTglTravel3.isSelected() == true) {
            cekTglTravel3 = "ya";
        } else {
            cekTglTravel3 = "tidak";
        }
    }
    
    public void setData(String norw, String sttsRawat, String unit) {
        TNoRw.setText(norw);
        sttsrwt = sttsRawat;
        rgrawat = unit;
        isPasien();
        Valid.SetTgl(DTPCari1, tglReg);
        TCari.setText(norw);
    }
    
    private void isPasien() {
        try {
            ps1 = koneksi.prepareStatement("select p.*, rp.tgl_registrasi from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where rp.no_rawat='" + TNoRw.getText() + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    TNoRM.setText(rs1.getString("no_rkm_medis"));
                    TPasien.setText(rs1.getString("nm_pasien"));
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
        if (cekMening.equals("ya")) {
            chkTglMeninggal.setSelected(true);
            TtglMeninggal.setEnabled(true);
            TtmptMeninggal.setEnabled(true);
            TpenyeMeninggal.setEnabled(true);
        } else {
            chkTglMeninggal.setSelected(false);
            TtglMeninggal.setEnabled(false);
            TtmptMeninggal.setEnabled(false);
            TpenyeMeninggal.setEnabled(false);
        }
        
        if (cekPindahHD.equals("ya")) {
            chkTglPindahHD.setSelected(true);
            TtglPindahHd.setEnabled(true);
            TpindahHD.setEnabled(true);
            TalasanPindahHd.setEnabled(true);
        } else {
            chkTglPindahHD.setSelected(false);
            TtglPindahHd.setEnabled(false);
            TpindahHD.setEnabled(false);
            TalasanPindahHd.setEnabled(false);
        }
        
        if (cekPindahCAPD.equals("ya")) {
            chkTglPindahCAPD.setSelected(true);
            TtglPindahCapd.setEnabled(true);
            TalasanCapd.setEnabled(true);
        } else {
            chkTglPindahCAPD.setSelected(false);
            TtglPindahCapd.setEnabled(false);
            TalasanCapd.setEnabled(false);
        }
        
        if (cekTglTranA.equals("ya")) {
            chkTglTransA.setSelected(true);
            TtglTransA.setEnabled(true);
            Ttempat.setEnabled(true);
            Tdonor.setEnabled(true);
            Thubungan.setEnabled(true);
        } else {
            chkTglTransA.setSelected(false);
            TtglTransA.setEnabled(false);
            Ttempat.setEnabled(false);
            Tdonor.setEnabled(false);
            Thubungan.setEnabled(false);
        }
        
        if (cekTglTranB.equals("ya")) {
            chkTglTransB.setSelected(true);
            TtglTransB.setEnabled(true);
            Tke.setEnabled(true);
        } else {
            chkTglTransB.setSelected(false);
            TtglTransB.setEnabled(false);
            Tke.setEnabled(false);
        }
        
        if (cekTglTravel1.equals("ya")) {
            chkTglTravel1.setSelected(true);
            TtglTravel1.setEnabled(true);
            TtempatTravel1.setEnabled(true);
            TalasanTravel1.setEnabled(true);
        } else {
            chkTglTravel1.setSelected(false);
            TtglTravel1.setEnabled(false);
            TtempatTravel1.setEnabled(false);
            TalasanTravel1.setEnabled(false);
        }
        
        if (cekTglTravel2.equals("ya")) {
            chkTglTravel2.setSelected(true);
            TtglTravel2.setEnabled(true);
            TtempatTravel2.setEnabled(true);
            TalasanTravel2.setEnabled(true);
        } else {
            chkTglTravel2.setSelected(false);
            TtglTravel2.setEnabled(false);
            TtempatTravel2.setEnabled(false);
            TalasanTravel2.setEnabled(false);
        }
        
        if (cekTglTravel3.equals("ya")) {
            chkTglTravel3.setSelected(true);
            TtglTravel3.setEnabled(true);
            TtempatTravel3.setEnabled(true);
            TalasanTravel3.setEnabled(true);
        } else {
            chkTglTravel3.setSelected(false);
            TtglTravel3.setEnabled(false);
            TtempatTravel3.setEnabled(false);
            TalasanTravel3.setEnabled(false);
        }
    }

    public void awalData() {
        tampil();
    }
}
