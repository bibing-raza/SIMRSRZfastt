/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgSpesialis.java
 *
 * Created on May 23, 2010, 1:25:13 AM
 */
package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgNotepad;

/**
 *
 * @author dosen
 */
public class RMSkriningUlangGizi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, pscppt;
    private ResultSet rs, rs1, rs2, rs3, rscppt;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String nip = "", jnsSkrining = "", dataKonfirmasi = "", gz_anak1 = "", gz_anak2 = "", gz_anak3 = "",
            gz_anak4 = "", totSkor = "", kesimpulan = "", angkaBulanDewasa = "", angkaBulanAnak = "";
    private int x = 0, i = 0;
    private double jlhpxSkriningDewasa = 0, tdkberesikoDewasa = 0, PersenBeresikoDewasa = 0, PersenTdkBeresikoDewasa = 0,
            jlhpxSkriningAnak = 0, tdkberesikoAnak = 0, PersenBeresikoSedang = 0, PersenBeresikoBerat = 0, PersenTdkBeresiko = 0;

    /**
     * Creates new form DlgSpesialis
     *
     * @param parent
     * @param modal
     */
    public RMSkriningUlangGizi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Ruang Perawatan", "Tgl. Skrining", "Jam", "Jns. Skrining", "Nama Petugas",
            "tgl_skrining", "jam_skrining", "jenis_skrining", "gizi_dewasa1", "gizi_dewasa1ya", "gizi_dewasa2", "gizi_anak1",
            "gizi_anak2", "gizi_anak3", "gizi_anak_penyakit", "gizi_anak_penyakit_lain", "nip_petugas", "Total Skor", "Kesimpulan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbSkrining.setModel(tabMode);
        tbSkrining.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbSkrining.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 22; i++) {
            TableColumn column = tbSkrining.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(250);
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
                column.setPreferredWidth(60);
            } else if (i == 21) {
                column.setPreferredWidth(480);
            }
        }

        tbSkrining.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeCppt = new DefaultTableModel(null, new Object[]{
            "Tgl. CPPT", "Jam CPPT", "Jenis Bagian", "DPJP Konsulen", "Jenis PPA",
            "Nama PPA", "Shift", "hasil", "instruksi", "no_rawat", "tgl_cppt", "jam_cppt"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbCPPT.setModel(tabModeCppt);
        tbCPPT.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbCPPT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbCPPT.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(40);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            }
        }
        tbCPPT.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode1 = new DefaultTableModel(null, new String[]{
            "No.", "Ruang Rawat/Gedung", "Jlh. Pasien Dirawat", "Persentase Terskrining",
            "Persentase Beresiko", "Persentase Tidak Beresiko"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbPersenDewasa.setModel(tabMode1);
        tbPersenDewasa.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPersenDewasa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 6; i++) {
            TableColumn column = tbPersenDewasa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            } else if (i == 2) {
                column.setPreferredWidth(130);
            } else if (i == 3) {
                column.setPreferredWidth(140);
            } else if (i == 4) {
                column.setPreferredWidth(120);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } 
        }
        tbPersenDewasa.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode2 = new DefaultTableModel(null, new String[]{
            "No.", "Ruang Rawat/Gedung", "Jlh. Pasien Dirawat", "Persentase Terskrining",
            "Persentase Beresiko Sedang", "Persentase Beresiko Berat", "Persentase Tidak Beresiko"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbPersenAnak.setModel(tabMode2);
        tbPersenAnak.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPersenAnak.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbPersenAnak.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            } else if (i == 2) {
                column.setPreferredWidth(130);
            } else if (i == 3) {
                column.setPreferredWidth(140);
            } else if (i == 4) {
                column.setPreferredWidth(160);
            } else if (i == 5) {
                column.setPreferredWidth(160);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            }
        }
        tbPersenAnak.setDefaultRenderer(Object.class, new WarnaTable());

        TtahunDewasa.setDocument(new batasInput((byte) 4).getOnlyAngka(TtahunDewasa));
        TtahunAnak.setDocument(new batasInput((byte) 4).getOnlyAngka(TtahunAnak));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));

        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }
            });
        }

        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    nip = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                    TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    chkSaya.setSelected(false);
                    BtnPetugas.requestFocus();
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

        ChkAccor.setSelected(false);
        isMenu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        panelGlass10 = new widget.panelisi();
        panelGlass11 = new widget.panelisi();
        jLabel3 = new widget.Label();
        TNoRW = new widget.TextBox();
        TNmPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TtglSkrining = new widget.Tanggal();
        jLabel8 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        chkSaya = new widget.CekBox();
        jLabel14 = new widget.Label();
        jLabel16 = new widget.Label();
        jLabel4 = new widget.Label();
        TrgRawat = new widget.TextBox();
        TabSkrining = new javax.swing.JTabbedPane();
        FormInputDewasa = new widget.PanelBiasa();
        jLabel61 = new widget.Label();
        jLabel75 = new widget.Label();
        cmbDewasaGZ1 = new widget.ComboBox();
        skorGZ1 = new widget.TextBox();
        cmbDewasaYaGZ1 = new widget.ComboBox();
        skorYaGZ1 = new widget.TextBox();
        jLabel69 = new widget.Label();
        cmbDewasaGZ2 = new widget.ComboBox();
        skorGZ2 = new widget.TextBox();
        jLabel74 = new widget.Label();
        TotSkorGZD = new widget.TextBox();
        kesimpulanGZDewasa = new widget.TextArea();
        FormInputAnak = new widget.PanelBiasa();
        ChkGZanak1 = new widget.CekBox();
        skorGZ3 = new widget.TextBox();
        ChkGZanak2 = new widget.CekBox();
        skorGZ4 = new widget.TextBox();
        jLabel135 = new widget.Label();
        ChkGZanak3 = new widget.CekBox();
        jLabel136 = new widget.Label();
        skorGZ5 = new widget.TextBox();
        ChkGZanak4 = new widget.CekBox();
        scrollPane7 = new widget.ScrollPane();
        malnutrisi = new widget.TextArea();
        skorGZ6 = new widget.TextBox();
        jLabel138 = new widget.Label();
        TotSkorGZA = new widget.TextBox();
        kesimpulanGZanak = new widget.TextArea();
        jLabel83 = new widget.Label();
        lainGZanak = new widget.TextBox();
        TabData = new javax.swing.JTabbedPane();
        FormData = new widget.PanelBiasa();
        Scroll = new widget.ScrollPane();
        tbSkrining = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnNotepad = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        FormPersenDewasa = new widget.PanelBiasa();
        Scroll1 = new widget.ScrollPane();
        tbPersenDewasa = new widget.Table();
        panelGlass12 = new widget.panelisi();
        jLabel29 = new widget.Label();
        cmbBulanDewasa = new widget.ComboBox();
        jLabel35 = new widget.Label();
        TtahunDewasa = new widget.TextBox();
        BtnCari4 = new widget.Button();
        BtnCetakPersenDewasa = new widget.Button();
        BtnKeluar4 = new widget.Button();
        FormPersenAnak = new widget.PanelBiasa();
        Scroll2 = new widget.ScrollPane();
        tbPersenAnak = new widget.Table();
        panelGlass13 = new widget.panelisi();
        jLabel30 = new widget.Label();
        cmbBulanAnak = new widget.ComboBox();
        jLabel36 = new widget.Label();
        TtahunAnak = new widget.TextBox();
        BtnCari5 = new widget.Button();
        BtnCetakPersenAnak = new widget.Button();
        BtnKeluar5 = new widget.Button();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        Scroll3 = new widget.ScrollPane();
        tbCPPT = new widget.Table();
        panelGlass14 = new widget.panelisi();
        scrollPane5 = new widget.ScrollPane();
        Thasil = new widget.TextArea();
        scrollPane4 = new widget.ScrollPane();
        Tinstruksi = new widget.TextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Skrining Ulang Gizi Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 408));
        panelGlass10.setLayout(new java.awt.BorderLayout());

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 130));
        panelGlass11.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass11.add(jLabel3);
        jLabel3.setBounds(2, 10, 100, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        panelGlass11.add(TNoRW);
        TNoRW.setBounds(107, 10, 122, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setName("TNmPasien"); // NOI18N
        panelGlass11.add(TNmPasien);
        TNmPasien.setBounds(308, 10, 360, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        panelGlass11.add(TNoRM);
        TNoRM.setBounds(233, 10, 70, 23);

        TtglSkrining.setEditable(false);
        TtglSkrining.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-06-2024" }));
        TtglSkrining.setDisplayFormat("dd-MM-yyyy");
        TtglSkrining.setName("TtglSkrining"); // NOI18N
        TtglSkrining.setOpaque(false);
        TtglSkrining.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass11.add(TtglSkrining);
        TtglSkrining.setBounds(107, 66, 90, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Jam Skrining :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass11.add(jLabel8);
        jLabel8.setBounds(200, 66, 80, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        panelGlass11.add(cmbJam);
        cmbJam.setBounds(286, 66, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        panelGlass11.add(cmbMnt);
        cmbMnt.setBounds(337, 66, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        panelGlass11.add(cmbDtk);
        cmbDtk.setBounds(389, 66, 45, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        panelGlass11.add(TnmPetugas);
        TnmPetugas.setBounds(107, 94, 405, 23);

        BtnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('1');
        BtnPetugas.setToolTipText("Alt+1");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnPetugas);
        BtnPetugas.setBounds(515, 94, 28, 23);

        chkSaya.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya.setText("Saya Sendiri");
        chkSaya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya.setName("chkSaya"); // NOI18N
        chkSaya.setOpaque(false);
        chkSaya.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSayaActionPerformed(evt);
            }
        });
        panelGlass11.add(chkSaya);
        chkSaya.setBounds(550, 94, 90, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tgl. Skrining :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass11.add(jLabel14);
        jLabel14.setBounds(2, 66, 100, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Nama Petugas :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass11.add(jLabel16);
        jLabel16.setBounds(2, 94, 100, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Ruang Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass11.add(jLabel4);
        jLabel4.setBounds(2, 38, 100, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        panelGlass11.add(TrgRawat);
        TrgRawat.setBounds(107, 38, 560, 23);

        panelGlass10.add(panelGlass11, java.awt.BorderLayout.PAGE_START);

        TabSkrining.setBackground(new java.awt.Color(255, 255, 254));
        TabSkrining.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabSkrining.setName("TabSkrining"); // NOI18N
        TabSkrining.setPreferredSize(new java.awt.Dimension(270, 106));
        TabSkrining.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabSkriningMouseClicked(evt);
            }
        });

        FormInputDewasa.setBorder(null);
        FormInputDewasa.setToolTipText("");
        FormInputDewasa.setName("FormInputDewasa"); // NOI18N
        FormInputDewasa.setPreferredSize(new java.awt.Dimension(870, 2421));
        FormInputDewasa.setLayout(null);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("1. Apakah pasien mengalami penurunan BB yang tidak direncanakan/tidak diinginkan dalam 6 bulan terakhir ?");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInputDewasa.add(jLabel61);
        jLabel61.setBounds(0, 10, 550, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Skor :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInputDewasa.add(jLabel75);
        jLabel75.setBounds(560, 10, 40, 23);

        cmbDewasaGZ1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDewasaGZ1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Tidak Tahu/tidak yakin (ada tanda : baju menjadi longgar)", "Ya, ada penurunan BB sebanyak" }));
        cmbDewasaGZ1.setName("cmbDewasaGZ1"); // NOI18N
        cmbDewasaGZ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDewasaGZ1ActionPerformed(evt);
            }
        });
        FormInputDewasa.add(cmbDewasaGZ1);
        cmbDewasaGZ1.setBounds(240, 38, 310, 23);

        skorGZ1.setEditable(false);
        skorGZ1.setForeground(new java.awt.Color(0, 0, 0));
        skorGZ1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGZ1.setText("0");
        skorGZ1.setFocusTraversalPolicyProvider(true);
        skorGZ1.setName("skorGZ1"); // NOI18N
        FormInputDewasa.add(skorGZ1);
        skorGZ1.setBounds(570, 38, 30, 23);

        cmbDewasaYaGZ1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDewasaYaGZ1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1 - 5 Kg", "6 - 10 Kg", "11 - 15 Kg", "15 Kg", "Tidak tahu berapa Kg penurunanya" }));
        cmbDewasaYaGZ1.setName("cmbDewasaYaGZ1"); // NOI18N
        cmbDewasaYaGZ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDewasaYaGZ1ActionPerformed(evt);
            }
        });
        FormInputDewasa.add(cmbDewasaYaGZ1);
        cmbDewasaYaGZ1.setBounds(240, 66, 310, 23);

        skorYaGZ1.setEditable(false);
        skorYaGZ1.setForeground(new java.awt.Color(0, 0, 0));
        skorYaGZ1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorYaGZ1.setText("0");
        skorYaGZ1.setFocusTraversalPolicyProvider(true);
        skorYaGZ1.setName("skorYaGZ1"); // NOI18N
        FormInputDewasa.add(skorYaGZ1);
        skorYaGZ1.setBounds(570, 66, 30, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("2. Apakah asupan makan pasien berkurang karena penurunan nafsu makan / kesulitan menerima makanan ?");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInputDewasa.add(jLabel69);
        jLabel69.setBounds(0, 94, 550, 23);

        cmbDewasaGZ2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDewasaGZ2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbDewasaGZ2.setName("cmbDewasaGZ2"); // NOI18N
        cmbDewasaGZ2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDewasaGZ2ActionPerformed(evt);
            }
        });
        FormInputDewasa.add(cmbDewasaGZ2);
        cmbDewasaGZ2.setBounds(485, 122, 65, 23);

        skorGZ2.setEditable(false);
        skorGZ2.setForeground(new java.awt.Color(0, 0, 0));
        skorGZ2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGZ2.setText("0");
        skorGZ2.setFocusTraversalPolicyProvider(true);
        skorGZ2.setName("skorGZ2"); // NOI18N
        FormInputDewasa.add(skorGZ2);
        skorGZ2.setBounds(570, 122, 30, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Total Skor :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInputDewasa.add(jLabel74);
        jLabel74.setBounds(0, 150, 550, 23);

        TotSkorGZD.setEditable(false);
        TotSkorGZD.setForeground(new java.awt.Color(0, 0, 0));
        TotSkorGZD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotSkorGZD.setText("0");
        TotSkorGZD.setFocusTraversalPolicyProvider(true);
        TotSkorGZD.setName("TotSkorGZD"); // NOI18N
        FormInputDewasa.add(TotSkorGZD);
        TotSkorGZD.setBounds(570, 150, 30, 23);

        kesimpulanGZDewasa.setEditable(false);
        kesimpulanGZDewasa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Kesimpulan Skrining Gizi Dewasa (Total Skor) : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        kesimpulanGZDewasa.setColumns(20);
        kesimpulanGZDewasa.setRows(5);
        kesimpulanGZDewasa.setName("kesimpulanGZDewasa"); // NOI18N
        FormInputDewasa.add(kesimpulanGZDewasa);
        kesimpulanGZDewasa.setBounds(40, 120, 350, 50);

        TabSkrining.addTab("Skrining Gizi Dewasa", FormInputDewasa);

        FormInputAnak.setBorder(null);
        FormInputAnak.setToolTipText("");
        FormInputAnak.setName("FormInputAnak"); // NOI18N
        FormInputAnak.setPreferredSize(new java.awt.Dimension(870, 2421));
        FormInputAnak.setLayout(null);

        ChkGZanak1.setBackground(new java.awt.Color(255, 255, 250));
        ChkGZanak1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGZanak1.setForeground(new java.awt.Color(0, 0, 0));
        ChkGZanak1.setText("1. Terdapat penurunan BB atau BB menetap (pada bayi < 1 tahun) selama >= 2 bulan");
        ChkGZanak1.setBorderPainted(true);
        ChkGZanak1.setBorderPaintedFlat(true);
        ChkGZanak1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGZanak1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGZanak1.setName("ChkGZanak1"); // NOI18N
        ChkGZanak1.setOpaque(false);
        ChkGZanak1.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkGZanak1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkGZanak1ActionPerformed(evt);
            }
        });
        FormInputAnak.add(ChkGZanak1);
        ChkGZanak1.setBounds(30, 10, 470, 23);

        skorGZ3.setEditable(false);
        skorGZ3.setForeground(new java.awt.Color(0, 0, 0));
        skorGZ3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGZ3.setText("0");
        skorGZ3.setFocusTraversalPolicyProvider(true);
        skorGZ3.setName("skorGZ3"); // NOI18N
        FormInputAnak.add(skorGZ3);
        skorGZ3.setBounds(900, 10, 30, 23);

        ChkGZanak2.setBackground(new java.awt.Color(255, 255, 250));
        ChkGZanak2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGZanak2.setForeground(new java.awt.Color(0, 0, 0));
        ChkGZanak2.setText("2. Terdapat tanda-tanda klinis gangguan gizi (tampak kurus, gemuk, pendek, edema, moon face, tampak tua, iga gambang, baggy pant, anoreksia) selama 1 bulan terakhir");
        ChkGZanak2.setBorderPainted(true);
        ChkGZanak2.setBorderPaintedFlat(true);
        ChkGZanak2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGZanak2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGZanak2.setName("ChkGZanak2"); // NOI18N
        ChkGZanak2.setOpaque(false);
        ChkGZanak2.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkGZanak2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkGZanak2ActionPerformed(evt);
            }
        });
        FormInputAnak.add(ChkGZanak2);
        ChkGZanak2.setBounds(30, 38, 860, 23);

        skorGZ4.setEditable(false);
        skorGZ4.setForeground(new java.awt.Color(0, 0, 0));
        skorGZ4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGZ4.setText("0");
        skorGZ4.setFocusTraversalPolicyProvider(true);
        skorGZ4.setName("skorGZ4"); // NOI18N
        FormInputAnak.add(skorGZ4);
        skorGZ4.setBounds(900, 38, 30, 23);

        jLabel135.setForeground(new java.awt.Color(0, 0, 0));
        jLabel135.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel135.setText("* Diare berat (> 5x/hari) dan atau muntah (> 3x/hari)");
        jLabel135.setName("jLabel135"); // NOI18N
        jLabel135.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel135MouseClicked(evt);
            }
        });
        FormInputAnak.add(jLabel135);
        jLabel135.setBounds(63, 81, 270, 23);

        ChkGZanak3.setBackground(new java.awt.Color(255, 255, 250));
        ChkGZanak3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGZanak3.setForeground(new java.awt.Color(0, 0, 0));
        ChkGZanak3.setText("3. Terdapat salah satu penyakit/kondisi yg. beresiko mengakibatkan malnutrisi berikut :");
        ChkGZanak3.setBorderPainted(true);
        ChkGZanak3.setBorderPaintedFlat(true);
        ChkGZanak3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGZanak3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGZanak3.setName("ChkGZanak3"); // NOI18N
        ChkGZanak3.setOpaque(false);
        ChkGZanak3.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkGZanak3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkGZanak3ActionPerformed(evt);
            }
        });
        FormInputAnak.add(ChkGZanak3);
        ChkGZanak3.setBounds(30, 66, 480, 23);

        jLabel136.setForeground(new java.awt.Color(0, 0, 0));
        jLabel136.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel136.setText("* Penurunan asupan makanan selama lebih dari 7 hari");
        jLabel136.setName("jLabel136"); // NOI18N
        jLabel136.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel136MouseClicked(evt);
            }
        });
        FormInputAnak.add(jLabel136);
        jLabel136.setBounds(63, 96, 270, 23);

        skorGZ5.setEditable(false);
        skorGZ5.setForeground(new java.awt.Color(0, 0, 0));
        skorGZ5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGZ5.setText("0");
        skorGZ5.setFocusTraversalPolicyProvider(true);
        skorGZ5.setName("skorGZ5"); // NOI18N
        FormInputAnak.add(skorGZ5);
        skorGZ5.setBounds(900, 66, 30, 23);

        ChkGZanak4.setBackground(new java.awt.Color(255, 255, 250));
        ChkGZanak4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGZanak4.setForeground(new java.awt.Color(0, 0, 0));
        ChkGZanak4.setText("Terdapat penyakit-penyakit / keadaan yg. meningkatkan resiko malnutrisi antara lain :");
        ChkGZanak4.setBorderPainted(true);
        ChkGZanak4.setBorderPaintedFlat(true);
        ChkGZanak4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGZanak4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGZanak4.setName("ChkGZanak4"); // NOI18N
        ChkGZanak4.setOpaque(false);
        ChkGZanak4.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkGZanak4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkGZanak4ActionPerformed(evt);
            }
        });
        FormInputAnak.add(ChkGZanak4);
        ChkGZanak4.setBounds(30, 124, 480, 23);

        scrollPane7.setName("scrollPane7"); // NOI18N

        malnutrisi.setEditable(false);
        malnutrisi.setColumns(20);
        malnutrisi.setRows(5);
        malnutrisi.setText("* Diare kronik > 2 minggu\n* Penyakit jantung bawaan (tersangka)\n* Infeksi HIV (tersangka)\n* Kelainan anatomi bawaan\n* Kelainan metabolisme bawaan\n* Retardasi mental\n* Keterlambatan perkembangan\n* Kanker (tersangka)\n* Penyakit hati/ginjal kronik\n* TB Paru\n* Renca/paska operasimayor\n* Luka bakar luas\n* Terpasang stoma\n* Trauma\n* Lain-lain (jika ada, isi pada kolom lain-lain)");
        malnutrisi.setName("malnutrisi"); // NOI18N
        malnutrisi.setPreferredSize(new java.awt.Dimension(170, 230));
        malnutrisi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                malnutrisiMouseClicked(evt);
            }
        });
        scrollPane7.setViewportView(malnutrisi);

        FormInputAnak.add(scrollPane7);
        scrollPane7.setBounds(50, 150, 370, 82);

        skorGZ6.setEditable(false);
        skorGZ6.setForeground(new java.awt.Color(0, 0, 0));
        skorGZ6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGZ6.setText("0");
        skorGZ6.setFocusTraversalPolicyProvider(true);
        skorGZ6.setName("skorGZ6"); // NOI18N
        FormInputAnak.add(skorGZ6);
        skorGZ6.setBounds(900, 124, 30, 23);

        jLabel138.setForeground(new java.awt.Color(0, 0, 0));
        jLabel138.setText("Total Skor :");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInputAnak.add(jLabel138);
        jLabel138.setBounds(835, 152, 60, 23);

        TotSkorGZA.setEditable(false);
        TotSkorGZA.setForeground(new java.awt.Color(0, 0, 0));
        TotSkorGZA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotSkorGZA.setText("0");
        TotSkorGZA.setFocusTraversalPolicyProvider(true);
        TotSkorGZA.setName("TotSkorGZA"); // NOI18N
        FormInputAnak.add(TotSkorGZA);
        TotSkorGZA.setBounds(900, 152, 30, 23);

        kesimpulanGZanak.setEditable(false);
        kesimpulanGZanak.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Kesimpulan Skrining Gizi Anak (Total Skor) : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        kesimpulanGZanak.setColumns(20);
        kesimpulanGZanak.setRows(5);
        kesimpulanGZanak.setName("kesimpulanGZanak"); // NOI18N
        FormInputAnak.add(kesimpulanGZanak);
        kesimpulanGZanak.setBounds(490, 178, 350, 55);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Lain-lain : ");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInputAnak.add(jLabel83);
        jLabel83.setBounds(430, 150, 60, 23);

        lainGZanak.setForeground(new java.awt.Color(0, 0, 0));
        lainGZanak.setName("lainGZanak"); // NOI18N
        FormInputAnak.add(lainGZanak);
        lainGZanak.setBounds(490, 150, 330, 23);

        TabSkrining.addTab("Skrining Gizi Anak", FormInputAnak);

        panelGlass10.add(TabSkrining, java.awt.BorderLayout.CENTER);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        TabData.setBackground(new java.awt.Color(255, 255, 254));
        TabData.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabData.setName("TabData"); // NOI18N
        TabData.setPreferredSize(new java.awt.Dimension(270, 106));
        TabData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabDataMouseClicked(evt);
            }
        });

        FormData.setBorder(null);
        FormData.setToolTipText("");
        FormData.setName("FormData"); // NOI18N
        FormData.setPreferredSize(new java.awt.Dimension(870, 2421));
        FormData.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbSkrining.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbSkrining.setName("tbSkrining"); // NOI18N
        tbSkrining.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSkriningMouseClicked(evt);
            }
        });
        tbSkrining.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSkriningKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbSkriningKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbSkrining);

        FormData.add(Scroll, java.awt.BorderLayout.CENTER);

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

        BtnNotepad.setForeground(new java.awt.Color(0, 0, 0));
        BtnNotepad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnNotepad.setMnemonic('N');
        BtnNotepad.setText("Notepad");
        BtnNotepad.setToolTipText("Alt+N");
        BtnNotepad.setName("BtnNotepad"); // NOI18N
        BtnNotepad.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNotepad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNotepadActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnNotepad);

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
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Skrining :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-06-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-06-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+1");
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
        BtnAll.setMnemonic('2');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+2");
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
        panelGlass9.add(BtnAll);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        FormData.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        TabData.addTab("Data Skrining Ulang Gizi", FormData);

        FormPersenDewasa.setBorder(null);
        FormPersenDewasa.setToolTipText("");
        FormPersenDewasa.setName("FormPersenDewasa"); // NOI18N
        FormPersenDewasa.setPreferredSize(new java.awt.Dimension(870, 2421));
        FormPersenDewasa.setLayout(new java.awt.BorderLayout());

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbPersenDewasa.setToolTipText("");
        tbPersenDewasa.setName("tbPersenDewasa"); // NOI18N
        Scroll1.setViewportView(tbPersenDewasa);

        FormPersenDewasa.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Bulan :");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass12.add(jLabel29);

        cmbBulanDewasa.setForeground(new java.awt.Color(0, 0, 0));
        cmbBulanDewasa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "Nopember", "Desember" }));
        cmbBulanDewasa.setName("cmbBulanDewasa"); // NOI18N
        cmbBulanDewasa.setPreferredSize(new java.awt.Dimension(85, 23));
        cmbBulanDewasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBulanDewasaActionPerformed(evt);
            }
        });
        panelGlass12.add(cmbBulanDewasa);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Tahun :");
        jLabel35.setName("jLabel35"); // NOI18N
        jLabel35.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass12.add(jLabel35);

        TtahunDewasa.setForeground(new java.awt.Color(0, 0, 0));
        TtahunDewasa.setName("TtahunDewasa"); // NOI18N
        TtahunDewasa.setPreferredSize(new java.awt.Dimension(60, 23));
        TtahunDewasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtahunDewasaKeyPressed(evt);
            }
        });
        panelGlass12.add(TtahunDewasa);

        BtnCari4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari4.setMnemonic('T');
        BtnCari4.setText("Tampilkan Data");
        BtnCari4.setToolTipText("Alt+T");
        BtnCari4.setName("BtnCari4"); // NOI18N
        BtnCari4.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari4ActionPerformed(evt);
            }
        });
        BtnCari4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari4KeyPressed(evt);
            }
        });
        panelGlass12.add(BtnCari4);

        BtnCetakPersenDewasa.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetakPersenDewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetakPersenDewasa.setMnemonic('K');
        BtnCetakPersenDewasa.setText("Cetak");
        BtnCetakPersenDewasa.setToolTipText("Alt+K");
        BtnCetakPersenDewasa.setName("BtnCetakPersenDewasa"); // NOI18N
        BtnCetakPersenDewasa.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnCetakPersenDewasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakPersenDewasaActionPerformed(evt);
            }
        });
        panelGlass12.add(BtnCetakPersenDewasa);

        BtnKeluar4.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar4.setMnemonic('K');
        BtnKeluar4.setText("Keluar");
        BtnKeluar4.setToolTipText("Alt+K");
        BtnKeluar4.setName("BtnKeluar4"); // NOI18N
        BtnKeluar4.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnKeluar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar4ActionPerformed(evt);
            }
        });
        panelGlass12.add(BtnKeluar4);

        FormPersenDewasa.add(panelGlass12, java.awt.BorderLayout.PAGE_END);

        TabData.addTab("Persentase Skrining Ulang Gizi (DEWASA)", FormPersenDewasa);

        FormPersenAnak.setBorder(null);
        FormPersenAnak.setToolTipText("");
        FormPersenAnak.setName("FormPersenAnak"); // NOI18N
        FormPersenAnak.setPreferredSize(new java.awt.Dimension(870, 2421));
        FormPersenAnak.setLayout(new java.awt.BorderLayout());

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbPersenAnak.setToolTipText("");
        tbPersenAnak.setName("tbPersenAnak"); // NOI18N
        Scroll2.setViewportView(tbPersenAnak);

        FormPersenAnak.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Bulan :");
        jLabel30.setName("jLabel30"); // NOI18N
        jLabel30.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass13.add(jLabel30);

        cmbBulanAnak.setForeground(new java.awt.Color(0, 0, 0));
        cmbBulanAnak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "Nopember", "Desember" }));
        cmbBulanAnak.setName("cmbBulanAnak"); // NOI18N
        cmbBulanAnak.setPreferredSize(new java.awt.Dimension(85, 23));
        cmbBulanAnak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBulanAnakActionPerformed(evt);
            }
        });
        panelGlass13.add(cmbBulanAnak);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Tahun :");
        jLabel36.setName("jLabel36"); // NOI18N
        jLabel36.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass13.add(jLabel36);

        TtahunAnak.setForeground(new java.awt.Color(0, 0, 0));
        TtahunAnak.setName("TtahunAnak"); // NOI18N
        TtahunAnak.setPreferredSize(new java.awt.Dimension(60, 23));
        TtahunAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtahunAnakKeyPressed(evt);
            }
        });
        panelGlass13.add(TtahunAnak);

        BtnCari5.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari5.setMnemonic('T');
        BtnCari5.setText("Tampilkan Data");
        BtnCari5.setToolTipText("Alt+T");
        BtnCari5.setName("BtnCari5"); // NOI18N
        BtnCari5.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari5ActionPerformed(evt);
            }
        });
        BtnCari5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari5KeyPressed(evt);
            }
        });
        panelGlass13.add(BtnCari5);

        BtnCetakPersenAnak.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetakPersenAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetakPersenAnak.setMnemonic('K');
        BtnCetakPersenAnak.setText("Cetak");
        BtnCetakPersenAnak.setToolTipText("Alt+K");
        BtnCetakPersenAnak.setName("BtnCetakPersenAnak"); // NOI18N
        BtnCetakPersenAnak.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnCetakPersenAnak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakPersenAnakActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnCetakPersenAnak);

        BtnKeluar5.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar5.setMnemonic('K');
        BtnKeluar5.setText("Keluar");
        BtnKeluar5.setToolTipText("Alt+K");
        BtnKeluar5.setName("BtnKeluar5"); // NOI18N
        BtnKeluar5.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnKeluar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar5ActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnKeluar5);

        FormPersenAnak.add(panelGlass13, java.awt.BorderLayout.PAGE_END);

        TabData.addTab("Persentase Skrining Ulang Gizi (ANAK)", FormPersenAnak);

        internalFrame1.add(TabData, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(900, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout());

        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setToolTipText("Silahkan Klik Untuk Membaca CPPT");
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(22, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormMenu.setBackground(new java.awt.Color(250, 250, 245));
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(150, 483));
        FormMenu.setLayout(new java.awt.GridLayout(1, 2));

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " CPPT ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbCPPT.setToolTipText("Silahkan klik untuk memilih data yang dibaca cpptnya");
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
        Scroll3.setViewportView(tbCPPT);

        FormMenu.add(Scroll3);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass14.setLayout(new java.awt.BorderLayout());

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Hasil Pemeriksaan, Analisa, Rencana, Penatalaksanaan Pasien ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N
        scrollPane5.setPreferredSize(new java.awt.Dimension(212, 400));

        Thasil.setEditable(false);
        Thasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Thasil.setColumns(20);
        Thasil.setRows(5);
        Thasil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Thasil.setName("Thasil"); // NOI18N
        Thasil.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane5.setViewportView(Thasil);

        panelGlass14.add(scrollPane5, java.awt.BorderLayout.PAGE_START);

        scrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Instruksi Tenaga Kesehatan Termasuk Pasca Bedah/Prosedur ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane4.setName("scrollPane4"); // NOI18N
        scrollPane4.setPreferredSize(new java.awt.Dimension(212, 150));

        Tinstruksi.setEditable(false);
        Tinstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tinstruksi.setColumns(20);
        Tinstruksi.setRows(5);
        Tinstruksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane4.setViewportView(Tinstruksi);

        panelGlass14.add(scrollPane4, java.awt.BorderLayout.CENTER);

        FormMenu.add(panelGlass14);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.CENTER);

        getContentPane().add(PanelAccor, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
//            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, TNmPasien, BtnBatal);
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
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbSkrining.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                hapus();
            } else {
                if (nip.equals(tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 19).toString())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh petugas yang melakukan skrining gizi..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            if (tbSkrining.getSelectedRow() > -1) {
                if (akses.getadmin() == true) {
                    ganti();
                } else {
                    if (nip.equals(tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 19).toString())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas yang melakukan skrining gizi..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbSkrining.requestFocus();
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
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, TNoRW);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbSkriningMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSkriningMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbSkriningMouseClicked

    private void tbSkriningKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSkriningKeyPressed
        if (tabMode.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.setText("");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbSkriningKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void tbSkriningKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSkriningKeyReleased
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbSkriningKeyReleased

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        ChkAccor.setSelected(false);
        isMenu();
        akses.setform("RMSkriningUlangGizi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        isMenu();
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void tbCPPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCPPTMouseClicked
        if (tabModeCppt.getRowCount() != 0) {
            try {
                getDataCppt();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbCPPTMouseClicked

    private void tbCPPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCPPTKeyPressed
        if (tabModeCppt.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataCppt();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbCPPTKeyPressed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (akses.getadmin() == true) {
                nip = "-";
                TnmPetugas.setText("-");
            } else {
                nip = akses.getkode();
                TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip + "'"));
            }
        } else {
            nip = "-";
            TnmPetugas.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        ChkAccor.setSelected(false);
        isMenu();

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMSkriningUlangGizi");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            cekData();
            if (Sequel.menyimpantf("skrining_gizi_ulang", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 16, new String[]{
                TNoRW.getText(), TrgRawat.getText(), Valid.SetTgl(TtglSkrining.getSelectedItem() + ""),
                cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), jnsSkrining, cmbDewasaGZ1.getSelectedItem().toString(),
                cmbDewasaYaGZ1.getSelectedItem().toString(), cmbDewasaGZ2.getSelectedItem().toString(), gz_anak1, gz_anak2, gz_anak3, gz_anak4,
                lainGZanak.getText(), nip, totSkor, kesimpulan
            }) == true) {

                TCari.setText(TNoRW.getText());
                Valid.SetTgl(DTPCari1, Valid.SetTgl(TtglSkrining.getSelectedItem() + ""));
                emptTeks();
                tampil();
            }
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void TabSkriningMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabSkriningMouseClicked
        if (TabSkrining.getSelectedIndex() == 0) {
            kesimpulanGZDewasa.setText("");
            cmbDewasaGZ1.setSelectedIndex(0);
            cmbDewasaYaGZ1.setSelectedIndex(0);
            cmbDewasaYaGZ1.setEnabled(false);
            skorGZ1.setText("0");
            skorYaGZ1.setText("0");
            cmbDewasaGZ2.setSelectedIndex(0);
            skorGZ2.setText("0");
            TotSkorGZD.setText("0");
        } else {
            kesimpulanGZanak.setText("");
            ChkGZanak1.setSelected(false);
            ChkGZanak2.setSelected(false);
            ChkGZanak3.setSelected(false);
            ChkGZanak4.setSelected(false);
            skorGZ3.setText("0");
            skorGZ4.setText("0");
            skorGZ5.setText("0");
            skorGZ6.setText("0");
            lainGZanak.setText("");
            TotSkorGZA.setText("0");
        }
        hitungSkorGZ();
    }//GEN-LAST:event_TabSkriningMouseClicked

    private void cmbDewasaGZ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDewasaGZ1ActionPerformed
        cmbDewasaYaGZ1.setSelectedIndex(0);
        skorYaGZ1.setText("0");

        if (cmbDewasaGZ1.getSelectedIndex() == 0) {
            skorGZ1.setText("0");
            cmbDewasaYaGZ1.setEnabled(false);
        } else if (cmbDewasaGZ1.getSelectedIndex() == 1) {
            skorGZ1.setText("1");
            cmbDewasaYaGZ1.setEnabled(false);
        } else if (cmbDewasaGZ1.getSelectedIndex() == 2) {
            skorGZ1.setText("0");
            cmbDewasaYaGZ1.setEnabled(true);
        }
        hitungSkorGZ();
    }//GEN-LAST:event_cmbDewasaGZ1ActionPerformed

    private void cmbDewasaYaGZ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDewasaYaGZ1ActionPerformed
        if (cmbDewasaYaGZ1.getSelectedIndex() == 0) {
            skorYaGZ1.setText("0");
        } else if (cmbDewasaYaGZ1.getSelectedIndex() == 1) {
            skorYaGZ1.setText("1");
        } else if (cmbDewasaYaGZ1.getSelectedIndex() == 2) {
            skorYaGZ1.setText("2");
        } else if (cmbDewasaYaGZ1.getSelectedIndex() == 3) {
            skorYaGZ1.setText("3");
        } else if (cmbDewasaYaGZ1.getSelectedIndex() == 4) {
            skorYaGZ1.setText("4");
        } else if (cmbDewasaYaGZ1.getSelectedIndex() == 5) {
            skorYaGZ1.setText("2");
        }
        hitungSkorGZ();
    }//GEN-LAST:event_cmbDewasaYaGZ1ActionPerformed

    private void cmbDewasaGZ2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDewasaGZ2ActionPerformed
        if (cmbDewasaGZ2.getSelectedIndex() == 0) {
            skorGZ2.setText("0");
        } else if (cmbDewasaGZ2.getSelectedIndex() == 1) {
            skorGZ2.setText("1");
        }
        hitungSkorGZ();
    }//GEN-LAST:event_cmbDewasaGZ2ActionPerformed

    private void ChkGZanak1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkGZanak1ActionPerformed
        if (TabSkrining.getSelectedIndex() == 1) {
            if (ChkGZanak1.isSelected() == true) {
                skorGZ3.setText("1");
            } else {
                skorGZ3.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak1.setSelected(false);
            skorGZ3.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_ChkGZanak1ActionPerformed

    private void ChkGZanak2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkGZanak2ActionPerformed
        if (TabSkrining.getSelectedIndex() == 1) {
            if (ChkGZanak2.isSelected() == true) {
                skorGZ4.setText("1");
            } else {
                skorGZ4.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak2.setSelected(false);
            skorGZ4.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_ChkGZanak2ActionPerformed

    private void ChkGZanak3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkGZanak3ActionPerformed
        if (TabSkrining.getSelectedIndex() == 1) {
            if (ChkGZanak3.isSelected() == true) {
                skorGZ5.setText("1");
            } else {
                skorGZ5.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak3.setSelected(false);
            skorGZ5.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_ChkGZanak3ActionPerformed

    private void jLabel135MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel135MouseClicked
        if (TabSkrining.getSelectedIndex() == 1) {
            ChkGZanak3.setSelected(true);
            if (ChkGZanak3.isSelected() == true) {
                skorGZ5.setText("1");
            } else {
                skorGZ5.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak3.setSelected(false);
            skorGZ5.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_jLabel135MouseClicked

    private void jLabel136MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel136MouseClicked
        if (TabSkrining.getSelectedIndex() == 1) {
            ChkGZanak3.setSelected(true);
            if (ChkGZanak3.isSelected() == true) {
                skorGZ5.setText("1");
            } else {
                skorGZ5.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak3.setSelected(false);
            skorGZ5.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_jLabel136MouseClicked

    private void ChkGZanak4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkGZanak4ActionPerformed
        if (TabSkrining.getSelectedIndex() == 1) {
            if (ChkGZanak4.isSelected() == true) {
                skorGZ6.setText("2");
            } else {
                skorGZ6.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak4.setSelected(false);
            skorGZ6.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_ChkGZanak4ActionPerformed

    private void malnutrisiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_malnutrisiMouseClicked
        if (TabSkrining.getSelectedIndex() == 1) {
            ChkGZanak4.setSelected(true);
            if (ChkGZanak4.isSelected() == true) {
                skorGZ6.setText("2");
            } else {
                skorGZ6.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak4.setSelected(false);
            skorGZ6.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_malnutrisiMouseClicked

    private void TabDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabDataMouseClicked
        ChkAccor.setSelected(false);
        isMenu();

        if (TabData.getSelectedIndex() == 0) {
            tampil();
        } else if (TabData.getSelectedIndex() == 1) {
            cmbBulanDewasa.setSelectedItem(Sequel.bulanINDONESIA("select month(now())"));
            angkaBulanDewasa = Sequel.cariIsi("select month(now())");
            TtahunDewasa.setText(Sequel.cariIsi("select year(now())"));
            tampilPersenDewasa();
        } else if (TabData.getSelectedIndex() == 2) {
            cmbBulanAnak.setSelectedItem(Sequel.bulanINDONESIA("select month(now())"));
            angkaBulanAnak = Sequel.cariIsi("select month(now())");
            TtahunAnak.setText(Sequel.cariIsi("select year(now())"));
            tampilPersenAnak();
        }
    }//GEN-LAST:event_TabDataMouseClicked

    private void cmbBulanDewasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBulanDewasaActionPerformed
        angkaBulanDewasa = "";
        if (cmbBulanDewasa.getSelectedIndex() == 0) {
            angkaBulanDewasa = "1";
        } else if (cmbBulanDewasa.getSelectedIndex() == 1) {
            angkaBulanDewasa = "2";
        } else if (cmbBulanDewasa.getSelectedIndex() == 2) {
            angkaBulanDewasa = "3";
        } else if (cmbBulanDewasa.getSelectedIndex() == 3) {
            angkaBulanDewasa = "4";
        } else if (cmbBulanDewasa.getSelectedIndex() == 4) {
            angkaBulanDewasa = "5";
        } else if (cmbBulanDewasa.getSelectedIndex() == 5) {
            angkaBulanDewasa = "6";
        } else if (cmbBulanDewasa.getSelectedIndex() == 6) {
            angkaBulanDewasa = "7";
        } else if (cmbBulanDewasa.getSelectedIndex() == 7) {
            angkaBulanDewasa = "8";
        } else if (cmbBulanDewasa.getSelectedIndex() == 8) {
            angkaBulanDewasa = "9";
        } else if (cmbBulanDewasa.getSelectedIndex() == 9) {
            angkaBulanDewasa = "10";
        } else if (cmbBulanDewasa.getSelectedIndex() == 10) {
            angkaBulanDewasa = "11";
        } else if (cmbBulanDewasa.getSelectedIndex() == 11) {
            angkaBulanDewasa = "12";
        }
        BtnCari4ActionPerformed(null);
    }//GEN-LAST:event_cmbBulanDewasaActionPerformed

    private void TtahunDewasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtahunDewasaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari4ActionPerformed(null);
        }
    }//GEN-LAST:event_TtahunDewasaKeyPressed

    private void BtnCari4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari4ActionPerformed
        tampilPersenDewasa();
    }//GEN-LAST:event_BtnCari4ActionPerformed

    private void BtnCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari4ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari4KeyPressed

    private void BtnCetakPersenDewasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakPersenDewasaActionPerformed
        if (tbPersenDewasa.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan tampilkan datanya terlebih dulu..!!!");
        } else {
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE BULAN " + cmbBulanDewasa.getSelectedItem().toString().toUpperCase() + " TAHUN " + TtahunDewasa.getText());
            
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary");
            int row = tabMode1.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary", "'0','"
                        + tabMode1.getValueAt(r, 0).toString() + "','"
                        + tabMode1.getValueAt(r, 1).toString() + "','"
                        + tabMode1.getValueAt(r, 2).toString() + "','"
                        + tabMode1.getValueAt(r, 3).toString() + "','"
                        + tabMode1.getValueAt(r, 4).toString() + "','"
                        + tabMode1.getValueAt(r, 5).toString() + "',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Presentase Skrining Dewasa");
            }
            Sequel.AutoComitTrue();            
            Valid.MyReport("rptPersentaseSkriningDewasa.jasper", "report", "::[ Persentase Skrining Ulang Gizi (DEWASA) ]::",
                "select * from temporary", param);
            this.setCursor(Cursor.getDefaultCursor());

            tampilPersenDewasa();
            emptTeks();
            BtnKeluar4.requestFocus();
        }
    }//GEN-LAST:event_BtnCetakPersenDewasaActionPerformed

    private void BtnKeluar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar4ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar4ActionPerformed

    private void cmbBulanAnakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBulanAnakActionPerformed
        angkaBulanAnak = "";
        if (cmbBulanAnak.getSelectedIndex() == 0) {
            angkaBulanAnak = "1";
        } else if (cmbBulanAnak.getSelectedIndex() == 1) {
            angkaBulanAnak = "2";
        } else if (cmbBulanAnak.getSelectedIndex() == 2) {
            angkaBulanAnak = "3";
        } else if (cmbBulanAnak.getSelectedIndex() == 3) {
            angkaBulanAnak = "4";
        } else if (cmbBulanAnak.getSelectedIndex() == 4) {
            angkaBulanAnak = "5";
        } else if (cmbBulanAnak.getSelectedIndex() == 5) {
            angkaBulanAnak = "6";
        } else if (cmbBulanAnak.getSelectedIndex() == 6) {
            angkaBulanAnak = "7";
        } else if (cmbBulanAnak.getSelectedIndex() == 7) {
            angkaBulanAnak = "8";
        } else if (cmbBulanAnak.getSelectedIndex() == 8) {
            angkaBulanAnak = "9";
        } else if (cmbBulanAnak.getSelectedIndex() == 9) {
            angkaBulanAnak = "10";
        } else if (cmbBulanAnak.getSelectedIndex() == 10) {
            angkaBulanAnak = "11";
        } else if (cmbBulanAnak.getSelectedIndex() == 11) {
            angkaBulanAnak = "12";
        }
        BtnCari5ActionPerformed(null);
    }//GEN-LAST:event_cmbBulanAnakActionPerformed

    private void TtahunAnakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtahunAnakKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari5ActionPerformed(null);
        }
    }//GEN-LAST:event_TtahunAnakKeyPressed

    private void BtnCari5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari5ActionPerformed
        tampilPersenAnak();
    }//GEN-LAST:event_BtnCari5ActionPerformed

    private void BtnCari5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari5ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari5KeyPressed

    private void BtnCetakPersenAnakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakPersenAnakActionPerformed
        if (tbPersenAnak.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan tampilkan datanya terlebih dulu..!!!");
        } else {
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE BULAN " + cmbBulanAnak.getSelectedItem().toString().toUpperCase() + " TAHUN " + TtahunAnak.getText());
            
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary");
            int row = tabMode2.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary", "'0','"
                        + tabMode2.getValueAt(r, 0).toString() + "','"
                        + tabMode2.getValueAt(r, 1).toString() + "','"
                        + tabMode2.getValueAt(r, 2).toString() + "','"
                        + tabMode2.getValueAt(r, 3).toString() + "','"
                        + tabMode2.getValueAt(r, 4).toString() + "','"
                        + tabMode2.getValueAt(r, 5).toString() + "','"
                        + tabMode2.getValueAt(r, 6).toString() + "',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Presentase Skrining Anak");
            }
            Sequel.AutoComitTrue();            
            Valid.MyReport("rptPersentaseSkriningAnak.jasper", "report", "::[ Persentase Skrining Ulang Gizi (ANAK) ]::",
                "select * from temporary", param);
            this.setCursor(Cursor.getDefaultCursor());

            tampilPersenAnak();
            emptTeks();
            BtnKeluar5.requestFocus();
        }
    }//GEN-LAST:event_BtnCetakPersenAnakActionPerformed

    private void BtnKeluar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar5ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar5ActionPerformed

    /**
     * @param args the command liTabSkriningents
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMSkriningUlangGizi dialog = new RMSkriningUlangGizi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari4;
    private widget.Button BtnCari5;
    private widget.Button BtnCetakPersenAnak;
    private widget.Button BtnCetakPersenDewasa;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar4;
    private widget.Button BtnKeluar5;
    private widget.Button BtnNotepad;
    private widget.Button BtnPetugas;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAccor;
    public widget.CekBox ChkGZanak1;
    public widget.CekBox ChkGZanak2;
    public widget.CekBox ChkGZanak3;
    public widget.CekBox ChkGZanak4;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormData;
    private widget.PanelBiasa FormInputAnak;
    private widget.PanelBiasa FormInputDewasa;
    private widget.PanelBiasa FormMenu;
    private widget.PanelBiasa FormPersenAnak;
    private widget.PanelBiasa FormPersenDewasa;
    private widget.Label LCount;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.TextBox TCari;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private javax.swing.JTabbedPane TabData;
    private javax.swing.JTabbedPane TabSkrining;
    private widget.TextArea Thasil;
    private widget.TextArea Tinstruksi;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TotSkorGZA;
    private widget.TextBox TotSkorGZD;
    private widget.TextBox TrgRawat;
    private widget.TextBox TtahunAnak;
    private widget.TextBox TtahunDewasa;
    private widget.Tanggal TtglSkrining;
    private widget.CekBox chkSaya;
    private widget.ComboBox cmbBulanAnak;
    private widget.ComboBox cmbBulanDewasa;
    private widget.ComboBox cmbDewasaGZ1;
    private widget.ComboBox cmbDewasaGZ2;
    private widget.ComboBox cmbDewasaYaGZ1;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel135;
    private widget.Label jLabel136;
    private widget.Label jLabel138;
    private widget.Label jLabel14;
    private widget.Label jLabel16;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel61;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel8;
    private widget.Label jLabel83;
    private javax.swing.JPanel jPanel3;
    private widget.TextArea kesimpulanGZDewasa;
    private widget.TextArea kesimpulanGZanak;
    private widget.TextBox lainGZanak;
    private widget.TextArea malnutrisi;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane7;
    private widget.TextBox skorGZ1;
    private widget.TextBox skorGZ2;
    private widget.TextBox skorGZ3;
    private widget.TextBox skorGZ4;
    private widget.TextBox skorGZ5;
    private widget.TextBox skorGZ6;
    private widget.TextBox skorYaGZ1;
    private widget.Table tbCPPT;
    private widget.Table tbPersenAnak;
    private widget.Table tbPersenDewasa;
    private widget.Table tbSkrining;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select sg.*, p.no_rkm_medis, p.nm_pasien, date_format(sg.tgl_skrining,'%d-%m-%Y') tanggal, "
                    + "time_format(sg.jam_skrining,'%H:%i') jam, pg.nama petugas from skrining_gizi_ulang sg "
                    + "inner join reg_periksa rp on rp.no_rawat=sg.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "left join pegawai pg on pg.nik=sg.nip_petugas where "
                    + "sg.tgl_skrining between ? and ? and sg.no_rawat like ? or "
                    + "sg.tgl_skrining between ? and ? and p.no_rkm_medis like ? or "
                    + "sg.tgl_skrining between ? and ? and p.nm_pasien like ? or "
                    + "sg.tgl_skrining between ? and ? and pg.nama like ? order by sg.tgl_skrining, sg.jam_skrining");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tanggal"),
                        rs.getString("jam"),
                        rs.getString("jenis_skrining").toUpperCase(),
                        rs.getString("petugas"),
                        rs.getString("tgl_skrining"),
                        rs.getString("jam_skrining"),
                        rs.getString("jenis_skrining"),
                        rs.getString("gizi_dewasa1"),
                        rs.getString("gizi_dewasa1ya"),
                        rs.getString("gizi_dewasa2"),
                        rs.getString("gizi_anak1"),
                        rs.getString("gizi_anak2"),
                        rs.getString("gizi_anak3"),
                        rs.getString("gizi_anak_penyakit"),
                        rs.getString("gizi_anak_penyakit_lain"),
                        rs.getString("nip_petugas"),
                        rs.getString("total_skor"),
                        rs.getString("kesimpulan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        TtglSkrining.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        nip = "-";
        TnmPetugas.setText("-");
        chkSaya.setSelected(false);

        TabSkrining.setSelectedIndex(0);
        kesimpulanGZDewasa.setText("");
        cmbDewasaGZ1.setSelectedIndex(0);
        cmbDewasaYaGZ1.setSelectedIndex(0);
        cmbDewasaYaGZ1.setEnabled(false);
        skorGZ1.setText("0");
        skorYaGZ1.setText("0");
        cmbDewasaGZ2.setSelectedIndex(0);
        skorGZ2.setText("0");
        TotSkorGZD.setText("0");

        kesimpulanGZanak.setText("");
        ChkGZanak1.setSelected(false);
        ChkGZanak2.setSelected(false);
        ChkGZanak3.setSelected(false);
        ChkGZanak4.setSelected(false);
        skorGZ3.setText("0");
        skorGZ4.setText("0");
        skorGZ5.setText("0");
        skorGZ6.setText("0");
        lainGZanak.setText("");
        TotSkorGZA.setText("0");
        Valid.SetTgl(DTPCari2, Sequel.cariIsi("select DATE_ADD(now(),interval 30 day)"));
    }

    private void getData() {
        nip = "";
        gz_anak1 = "";
        gz_anak2 = "";
        gz_anak3 = "";
        gz_anak4 = "";
        jnsSkrining = "";

        if (tbSkrining.getSelectedRow() != -1) {
            TNoRW.setText(tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 0).toString());
            TNoRM.setText(tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 1).toString());
            TNmPasien.setText(tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 3).toString());
            Valid.SetTgl(TtglSkrining, tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 8).toString());
            cmbJam.setSelectedItem(tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 9).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 9).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 9).toString().substring(6, 8));
            nip = tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 19).toString();
            TnmPetugas.setText(tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 7).toString());
            jnsSkrining = tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 10).toString();
            cmbDewasaGZ1.setSelectedItem(tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 11).toString());
            cmbDewasaYaGZ1.setSelectedItem(tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 12).toString());
            cmbDewasaGZ2.setSelectedItem(tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 13).toString());
            gz_anak1 = tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 14).toString();
            gz_anak2 = tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 15).toString();
            gz_anak3 = tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 16).toString();
            gz_anak4 = tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 17).toString();
            lainGZanak.setText(tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 18).toString());
            dataCek();
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getassesmen_gizi_harian());
        BtnHapus.setEnabled(akses.getassesmen_gizi_harian());
        BtnEdit.setEnabled(akses.getassesmen_gizi_harian());
    }

    public void setData(String norw, String norm, String nmPasien, String nmunit) {
        TNoRW.setText(norw);
        TNoRM.setText(norm);
        TNmPasien.setText(nmPasien);
        Valid.SetTgl(DTPCari2, Sequel.cariIsi("select DATE_ADD(now(),interval 30 day)"));
        TrgRawat.setText(nmunit);
        TCari.setText(norw);
        Valid.SetTgl(TtglSkrining, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRW.getText() + "'"));
        cmbJam.setSelectedItem(Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat='" + TNoRW.getText() + "'").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat='" + TNoRW.getText() + "'").substring(3, 5));

        if (Sequel.cariInteger("select count(-1) from skrining_gizi_ulang where no_rawat='" + norw + "'") > 0) {
            Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_skrining from skrining_gizi_ulang where "
                    + "no_rawat='" + norw + "' order by tgl_skrining limit 1"));
        } else {
            DTPCari1.setDate(new Date());
        }

        if (akses.getadmin() == true) {
            nip = "-";
            TnmPetugas.setText("-");
        } else {
            nip = akses.getkode();
            TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip + "'"));
        }
    }

    public void isMenu() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(900, HEIGHT));
            FormMenu.setVisible(true);
            ChkAccor.setVisible(true);
            Thasil.setText("");
            Tinstruksi.setText("");
            tampilCppt();
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(22, HEIGHT));
            FormMenu.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }

    private void tampilCppt() {
        Valid.tabelKosong(tabModeCppt);
        try {
            pscppt = koneksi.prepareStatement("SELECT c.verifikasi, DATE_FORMAT(c.tgl_cppt,'%d-%m-%Y') tgl, if(c.cek_jam='ya',TIME_FORMAT(c.jam_cppt,'%H:%i'),'-') jam, "
                    + "c.jenis_bagian, pg1.nama nmdpjp, c.jenis_ppa, pg2.nama nmppa, c.cppt_shift, c.hasil_pemeriksaan, "
                    + "c.instruksi_nakes, c.waktu_simpan, c.no_rawat, c.tgl_cppt, c.jam_cppt from cppt c "
                    + "inner join pegawai pg1 on pg1.nik=c.nip_konsulen "
                    + "inner join pegawai pg2 on pg2.nik=c.nip_ppa where "
                    + "c.flag_hapus='tidak' and c.status='ranap' and c.no_rawat='" + TNoRW.getText() + "' order by c.tgl_cppt, c.jam_cppt");
            try {
                rscppt = pscppt.executeQuery();
                while (rscppt.next()) {
                    tabModeCppt.addRow(new String[]{
                        rscppt.getString("tgl"),
                        rscppt.getString("jam"),
                        rscppt.getString("jenis_bagian"),
                        rscppt.getString("nmdpjp"),
                        rscppt.getString("jenis_ppa"),
                        rscppt.getString("nmppa"),
                        rscppt.getString("cppt_shift"),
                        rscppt.getString("hasil_pemeriksaan"),
                        rscppt.getString("instruksi_nakes"),
                        rscppt.getString("no_rawat"),
                        rscppt.getString("tgl_cppt"),
                        rscppt.getString("jam_cppt")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscppt != null) {
                    rscppt.close();
                }
                if (pscppt != null) {
                    pscppt.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void getDataCppt() {
        dataKonfirmasi = "";

        if (tbCPPT.getSelectedRow() != -1) {
            Thasil.setText("Tgl. CPPT : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 0).toString() + ", Jam : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 1).toString() + " WITA\n\n"
                    + "" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 7).toString());

            //konfirmasi terapi
            if (Sequel.cariInteger("select count(-1) from cppt_konfirmasi_terapi where "
                    + "no_rawat='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString() + "' "
                    + "and tgl_cppt='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString() + "' "
                    + "and cppt_shift='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString() + "' "
                    + "and jam_cppt='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString() + "'") > 0) {

                tampilKonfirmasi(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString());

                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n" + dataKonfirmasi);
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n" + dataKonfirmasi);
                }
            } else {
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString());
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")");
                }
            }
        }
    }

    private void tampilKonfirmasi(String norwt, String tglcppt, String sift, String jamcppt) {
        try {
            ps1 = koneksi.prepareStatement("select pg1.nama ptgs, date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i') jamlapor, "
                    + "pg2.nama dpjp, date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i') jamverif from cppt_konfirmasi_terapi ck "
                    + "inner join pegawai pg1 on pg1.nik=ck.nip_petugas_konfir inner join pegawai pg2 on pg2.nik=ck.nip_dpjp_konfir where "
                    + "ck.no_rawat = '" + norwt + "' and ck.tgl_cppt='" + tglcppt + "' and ck.cppt_shift='" + sift + "' "
                    + "and ck.jam_cppt='" + jamcppt + "' order by ck.waktu_simpan");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    if (dataKonfirmasi.equals("")) {
                        dataKonfirmasi = "Tgl. Lapor : " + rs1.getString("tgllapor") + ", Jam : " + rs1.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs1.getString("tglverif") + ", Jam : " + rs1.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs1.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs1.getString("dpjp");
                    } else {
                        dataKonfirmasi = dataKonfirmasi + "\n\nTgl. Lapor : " + rs1.getString("tgllapor") + ", Jam : " + rs1.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs1.getString("tglverif") + ", Jam : " + rs1.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs1.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs1.getString("dpjp");
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

    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from skrining_gizi_ulang where ruang_rawat='" + tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 3).toString() + "' and no_rawat=?", 1, new String[]{
                tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 0).toString()
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
    }

    private void ganti() {
        cekData();
        if (Sequel.mengedittf("skrining_gizi_ulang", "no_rawat=? and ruang_rawat=?", "ruang_rawat=?, "
                + "tgl_skrining=?, jam_skrining=?, jenis_skrining=?, gizi_dewasa1=?, gizi_dewasa1ya=?, gizi_dewasa2=?, "
                + "gizi_anak1=?, gizi_anak2=?, gizi_anak3=?, gizi_anak_penyakit=?, gizi_anak_penyakit_lain=?, nip_petugas=?, total_skor=?, kesimpulan=?", 17, new String[]{
                    TrgRawat.getText(), Valid.SetTgl(TtglSkrining.getSelectedItem() + ""),
                    cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), jnsSkrining, cmbDewasaGZ1.getSelectedItem().toString(),
                    cmbDewasaYaGZ1.getSelectedItem().toString(), cmbDewasaGZ2.getSelectedItem().toString(), gz_anak1, gz_anak2, gz_anak3, gz_anak4,
                    lainGZanak.getText(), nip, totSkor, kesimpulan, tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 0).toString(),
                    tbSkrining.getValueAt(tbSkrining.getSelectedRow(), 3).toString()
                }) == true) {

            TCari.setText(TNoRW.getText());
            Valid.SetTgl(DTPCari1, Valid.SetTgl(TtglSkrining.getSelectedItem() + ""));
            tampil();
            emptTeks();
        }
    }

    private void hitungSkorGZ() {
        int A1, B1, C1, TotD, A2, B2, C2, D2, TotA;
        A1 = Integer.parseInt(skorGZ1.getText());
        B1 = Integer.parseInt(skorYaGZ1.getText());
        C1 = Integer.parseInt(skorGZ2.getText());

        A2 = Integer.parseInt(skorGZ3.getText());
        B2 = Integer.parseInt(skorGZ4.getText());
        C2 = Integer.parseInt(skorGZ5.getText());
        D2 = Integer.parseInt(skorGZ6.getText());

        TotD = 0;
        TotA = 0;

        TotD = A1 + B1 + C1;
        TotA = A2 + B2 + C2 + D2;
        TotSkorGZD.setText(Valid.SetAngka2(TotD));
        TotSkorGZA.setText(Valid.SetAngka2(TotA));

        //skrining dewasa
        if (TabSkrining.getSelectedIndex() == 0) {
            kesimpulanGZanak.setText("");
            if (TotD == 0 || TotD == 1) {
                kesimpulanGZDewasa.setText("0 - 1 : tidak beresiko malnutrisi");
            } else if (TotD >= 2) {
                kesimpulanGZDewasa.setText(">= 2 : beresiko malnutrisi, perlu pemantauan lanjutan oleh Tim Gizi/Dietisien");
            }
        }

        //skrining anak
        if (TabSkrining.getSelectedIndex() == 1) {
            kesimpulanGZDewasa.setText("");
            if (TotA == 0) {
                kesimpulanGZanak.setText("0 : tidak beresiko malnutrisi");
            } else if (TotA >= 1 && TotA <= 3) {
                kesimpulanGZanak.setText("1 - 3 : resiko malnutrisi sedang, perlu pemantauan");
            } else if (TotA >= 4) {
                kesimpulanGZanak.setText(">= 4 : resiko malnutrisi berat, perlu pemantauan lanjutan "
                        + "oleh Tim Gizi/Dietisien");
            }
        }
    }

    private void cekData() {
        if (TabSkrining.getSelectedIndex() == 0) {
            jnsSkrining = "dewasa";
            totSkor = TotSkorGZD.getText();
            kesimpulan = kesimpulanGZDewasa.getText();
        } else {
            jnsSkrining = "anak";
            totSkor = TotSkorGZA.getText();
            kesimpulan = kesimpulanGZanak.getText();
        }

        if (ChkGZanak1.isSelected() == true) {
            gz_anak1 = "1";
        } else {
            gz_anak1 = "0";
        }

        if (ChkGZanak2.isSelected() == true) {
            gz_anak2 = "1";
        } else {
            gz_anak2 = "0";
        }

        if (ChkGZanak3.isSelected() == true) {
            gz_anak3 = "1";
        } else {
            gz_anak3 = "0";
        }

        if (ChkGZanak4.isSelected() == true) {
            gz_anak4 = "2";
        } else {
            gz_anak4 = "0";
        }
    }

    private void dataCek() {
        if (jnsSkrining.equals("dewasa")) {
            TabSkrining.setSelectedIndex(0);
        } else if (jnsSkrining.equals("anak")) {
            TabSkrining.setSelectedIndex(1);
        }

        if (gz_anak1.equals("1")) {
            ChkGZanak1.setSelected(true);
            skorGZ3.setText("1");
        } else {
            ChkGZanak1.setSelected(false);
            skorGZ3.setText("0");
        }

        if (gz_anak2.equals("1")) {
            ChkGZanak2.setSelected(true);
            skorGZ4.setText("1");
        } else {
            ChkGZanak2.setSelected(false);
            skorGZ4.setText("0");
        }

        if (gz_anak3.equals("1")) {
            ChkGZanak3.setSelected(true);
            skorGZ5.setText("1");
        } else {
            ChkGZanak3.setSelected(false);
            skorGZ5.setText("0");
        }

        if (gz_anak4.equals("2")) {
            ChkGZanak4.setSelected(true);
            skorGZ6.setText("2");
        } else {
            ChkGZanak4.setSelected(false);
            skorGZ6.setText("0");
        }
        hitungSkorGZ();
    }

    private void tampilPersenDewasa() {
        if (TtahunDewasa.getText().equals("")) {
            TtahunDewasa.setText(Sequel.cariIsi("select year(now())"));
        } else {
            TtahunDewasa.setText(TtahunDewasa.getText());
        }

        jlhpxSkriningDewasa = 0;
        tdkberesikoDewasa = 0;
        PersenBeresikoDewasa = 0;
        PersenTdkBeresikoDewasa = 0;
        Valid.tabelKosong(tabMode1);
        try {
            ps2 = koneksi.prepareStatement("select * from (select a.nm_gedung, a.jlh_px_ranap, "
                    + "concat(format(((ifnull(b.jlh_px_skrining,0)/a.jlh_px_ranap)*100),0),' %') persen_tersekrining, ifnull(b.jlh_px_skrining,0) jlh_px_skrining from ( "
                    + "(SELECT b.nm_gedung, count(ki.no_rawat) jlh_px_ranap FROM kamar_inap ki "
                    + "inner join reg_periksa rp on rp.no_rawat=ki.no_rawat "
                    + "inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE MONTH(ki.tgl_masuk)=" + angkaBulanDewasa + " and "
                    + "YEAR(ki.tgl_masuk)=" + TtahunDewasa.getText().trim() + " GROUP BY MONTH(ki.tgl_masuk), b.nm_gedung) as a "
                    + "inner join "
                    + "(SELECT b.nm_gedung, count(s.no_rawat) jlh_px_skrining from skrining_gizi_ulang s "
                    + "inner join kamar_inap ki on ki.no_rawat=s.no_rawat "
                    + "inner join bangsal b on b.nm_bangsal=s.ruang_rawat "
                    + "WHERE MONTH(s.tgl_skrining)=" + angkaBulanDewasa + " and YEAR(s.tgl_skrining)=" + TtahunDewasa.getText().trim() + " and s.jenis_skrining='dewasa' "
                    + "GROUP BY month(s.tgl_skrining), b.nm_gedung) as b on a.nm_gedung = b.nm_gedung)) as z order by z.nm_gedung");
            try {
                rs2 = ps2.executeQuery();
                x = 1;
                while (rs2.next()) {
                    double A, B, C;
                    A = Double.parseDouble(rs2.getString("jlh_px_skrining"));
                    B = Double.parseDouble(Sequel.cariIsi("select ifnull(b.beresiko,0) beresiko from ( "
                            + "(SELECT b.nm_gedung FROM kamar_inap ki "
                            + "inner join reg_periksa rp on rp.no_rawat=ki.no_rawat "
                            + "inner join kamar k on k.kd_kamar=ki.kd_kamar "
                            + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                            + "WHERE MONTH(ki.tgl_masuk)=" + angkaBulanDewasa + " and "
                            + "YEAR(ki.tgl_masuk)=" + TtahunDewasa.getText().trim() + " GROUP BY MONTH(ki.tgl_masuk), b.nm_gedung) as a "
                            + "left join "
                            + "(SELECT b.nm_gedung, count(s.no_rawat) beresiko from skrining_gizi_ulang s "
                            + "inner join kamar_inap ki on ki.no_rawat=s.no_rawat "
                            + "inner join bangsal b on b.nm_bangsal=s.ruang_rawat "
                            + "WHERE MONTH(s.tgl_skrining)=" + angkaBulanDewasa + " and "
                            + "YEAR(s.tgl_skrining)=" + TtahunDewasa.getText().trim() + " and s.jenis_skrining='dewasa' and "
                            + "CONVERT(s.total_skor,int)>=2 GROUP BY month(s.tgl_skrining), b.nm_gedung) as b on a.nm_gedung = b.nm_gedung) "
                            + "where a.nm_gedung='" + rs2.getString(1) + "'"));
                    C = Double.parseDouble(Sequel.cariIsi("select ifnull(b.tdk_beresiko,0) tdk_beresiko from ( "
                            + "(SELECT b.nm_gedung FROM kamar_inap ki "
                            + "inner join reg_periksa rp on rp.no_rawat=ki.no_rawat "
                            + "inner join kamar k on k.kd_kamar=ki.kd_kamar "
                            + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                            + "WHERE MONTH(ki.tgl_masuk)=" + angkaBulanDewasa + " and "
                            + "YEAR(ki.tgl_masuk)=" + TtahunDewasa.getText().trim() + " GROUP BY MONTH(ki.tgl_masuk), b.nm_gedung) as a "
                            + "left join "
                            + "(SELECT b.nm_gedung, count(s.no_rawat) tdk_beresiko from skrining_gizi_ulang s "
                            + "inner join kamar_inap ki on ki.no_rawat=s.no_rawat "
                            + "inner join bangsal b on b.nm_bangsal=s.ruang_rawat "
                            + "WHERE MONTH(s.tgl_skrining)=" + angkaBulanDewasa + " and YEAR(s.tgl_skrining)=" + TtahunDewasa.getText().trim() + " and s.jenis_skrining='dewasa' "
                            + "and CONVERT(s.total_skor,int)<2 GROUP BY month(s.tgl_skrining), b.nm_gedung) as b on a.nm_gedung = b.nm_gedung) "
                            + "where a.nm_gedung='" + rs2.getString(1) + "'"));

                    PersenBeresikoDewasa = (B / A) * 100;
                    tdkberesikoDewasa = A - B;
                    PersenTdkBeresikoDewasa = (tdkberesikoDewasa / A) * 100;

                    tabMode1.addRow(new String[]{
                        x + ".",
                        rs2.getString(1),
                        rs2.getString(2),
                        rs2.getString(3),
                        Valid.SetAngka2(PersenBeresikoDewasa) + " %",
                        Valid.SetAngka2(PersenTdkBeresikoDewasa) + " %"
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("tampilPersenDewasa : " + e);
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
    
    private void tampilPersenAnak() {
        if (TtahunAnak.getText().equals("")) {
            TtahunAnak.setText(Sequel.cariIsi("select year(now())"));
        } else {
            TtahunAnak.setText(TtahunAnak.getText());
        }

        jlhpxSkriningAnak = 0;
        tdkberesikoAnak = 0;
        PersenBeresikoSedang = 0;
        PersenBeresikoBerat = 0;
        PersenTdkBeresiko = 0;
        Valid.tabelKosong(tabMode2);
        try {
            ps3 = koneksi.prepareStatement("select * from (select a.nm_gedung, a.jlh_px_ranap, "
                    + "concat(format(((ifnull(b.jlh_px_skrining,0)/a.jlh_px_ranap)*100),0),' %') persen_tersekrining, ifnull(b.jlh_px_skrining,0) jlh_px_skrining from ( "
                    + "(SELECT b.nm_gedung, count(ki.no_rawat) jlh_px_ranap FROM kamar_inap ki "
                    + "inner join reg_periksa rp on rp.no_rawat=ki.no_rawat "
                    + "inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE MONTH(ki.tgl_masuk)=" + angkaBulanAnak + " and "
                    + "YEAR(ki.tgl_masuk)=" + TtahunAnak.getText().trim() + " GROUP BY MONTH(ki.tgl_masuk), b.nm_gedung) as a "
                    + "inner join "
                    + "(SELECT b.nm_gedung, count(s.no_rawat) jlh_px_skrining from skrining_gizi_ulang s "
                    + "inner join kamar_inap ki on ki.no_rawat=s.no_rawat "
                    + "inner join bangsal b on b.nm_bangsal=s.ruang_rawat "
                    + "WHERE MONTH(s.tgl_skrining)=" + angkaBulanAnak + " and "
                    + "YEAR(s.tgl_skrining)=" + TtahunAnak.getText().trim() + " and s.jenis_skrining='anak' "
                    + "GROUP BY month(s.tgl_skrining), b.nm_gedung) as b on a.nm_gedung = b.nm_gedung)) as z order by z.nm_gedung");
            try {
                rs3 = ps3.executeQuery();
                x = 1;
                while (rs3.next()) {
                    double A, B, C, D;
                    A = Double.parseDouble(rs3.getString("jlh_px_skrining"));
                    B = Double.parseDouble(Sequel.cariIsi("select ifnull(b.tdk_beresiko,0) tdk_beresiko from ( "
                            + "(SELECT b.nm_gedung FROM kamar_inap ki "
                            + "inner join reg_periksa rp on rp.no_rawat=ki.no_rawat "
                            + "inner join kamar k on k.kd_kamar=ki.kd_kamar "
                            + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                            + "WHERE MONTH(ki.tgl_masuk)=" + angkaBulanAnak + " and "
                            + "YEAR(ki.tgl_masuk)=" + TtahunAnak.getText().trim() + " GROUP BY MONTH(ki.tgl_masuk), b.nm_gedung) as a "
                            + "left join "
                            + "(SELECT b.nm_gedung, count(s.no_rawat) tdk_beresiko from skrining_gizi_ulang s "
                            + "inner join kamar_inap ki on ki.no_rawat=s.no_rawat "
                            + "inner join bangsal b on b.nm_bangsal=s.ruang_rawat "
                            + "WHERE MONTH(s.tgl_skrining)=" + angkaBulanAnak + " and "
                            + "YEAR(s.tgl_skrining)=" + TtahunAnak.getText().trim() + " and s.jenis_skrining='anak' and "
                            + "CONVERT(s.total_skor,int)=0 GROUP BY month(s.tgl_skrining), b.nm_gedung) as b on a.nm_gedung = b.nm_gedung) "
                            + "where a.nm_gedung='" + rs3.getString(1) + "'"));
                    C = Double.parseDouble(Sequel.cariIsi("select ifnull(b.resiko_sedang,0) resiko_sedang from ( "
                            + "(SELECT b.nm_gedung FROM kamar_inap ki "
                            + "inner join reg_periksa rp on rp.no_rawat=ki.no_rawat "
                            + "inner join kamar k on k.kd_kamar=ki.kd_kamar "
                            + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                            + "WHERE MONTH(ki.tgl_masuk)=" + angkaBulanAnak + " and "
                            + "YEAR(ki.tgl_masuk)=" + TtahunAnak.getText().trim() + " GROUP BY MONTH(ki.tgl_masuk), b.nm_gedung) as a "
                            + "left join "
                            + "(SELECT b.nm_gedung, count(s.no_rawat) resiko_sedang from skrining_gizi_ulang s "
                            + "inner join kamar_inap ki on ki.no_rawat=s.no_rawat "
                            + "inner join bangsal b on b.nm_bangsal=s.ruang_rawat "
                            + "WHERE MONTH(s.tgl_skrining)=" + angkaBulanAnak + " and "
                            + "YEAR(s.tgl_skrining)=" + TtahunAnak.getText().trim() + " and s.jenis_skrining='anak' "
                            + "and CONVERT(s.total_skor,int)>=1 and CONVERT(s.total_skor,int)<=3 "
                            + "GROUP BY month(s.tgl_skrining), b.nm_gedung) as b on a.nm_gedung = b.nm_gedung) "
                            + "where a.nm_gedung='" + rs3.getString(1) + "'"));
                    D = Double.parseDouble(Sequel.cariIsi("select ifnull(b.resiko_berat,0) resiko_berat from ( "
                            + "(SELECT b.nm_gedung FROM kamar_inap ki "
                            + "inner join reg_periksa rp on rp.no_rawat=ki.no_rawat "
                            + "inner join kamar k on k.kd_kamar=ki.kd_kamar "
                            + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                            + "WHERE MONTH(ki.tgl_masuk)=" + angkaBulanAnak + " and "
                            + "YEAR(ki.tgl_masuk)=" + TtahunAnak.getText().trim() + " GROUP BY MONTH(ki.tgl_masuk), b.nm_gedung) as a "
                            + "left join "
                            + "(SELECT b.nm_gedung, count(s.no_rawat) resiko_berat from skrining_gizi_ulang s "
                            + "inner join kamar_inap ki on ki.no_rawat=s.no_rawat "
                            + "inner join bangsal b on b.nm_bangsal=s.ruang_rawat "
                            + "WHERE MONTH(s.tgl_skrining)=" + angkaBulanAnak + " and "
                            + "YEAR(s.tgl_skrining)=" + TtahunAnak.getText().trim() + " and s.jenis_skrining='anak' and "
                            + "CONVERT(s.total_skor,int)>=4 GROUP BY month(s.tgl_skrining), b.nm_gedung) as b on a.nm_gedung = b.nm_gedung) "
                            + "where a.nm_gedung='" + rs3.getString(1) + "'"));

                    PersenBeresikoSedang = (C / A) * 100;
                    PersenBeresikoBerat = (D / A) * 100;
                    tdkberesikoAnak = A - (C + D);
                    PersenTdkBeresiko = (tdkberesikoAnak / A) * 100;

                    tabMode2.addRow(new String[]{
                        x + ".",
                        rs3.getString(1),
                        rs3.getString(2),
                        rs3.getString(3),
                        Valid.SetAngka2(PersenBeresikoSedang) + " %",
                        Valid.SetAngka2(PersenBeresikoBerat) + " %",
                        Valid.SetAngka2(PersenTdkBeresiko) + " %"
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("tampilPersenAnak : " + e);
            } finally {
                if (rs3 != null) {
                    rs3.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
