/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */

package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import kepegawaian.DlgCariPetugas;
import laporan.DlgPenyakit;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariPeriksaRadiologi;
import simrskhanza.DlgKabupaten;
import simrskhanza.DlgKecamatan;
import simrskhanza.DlgKelurahan;
import simrskhanza.DlgNotepad;

/**
 *
 * @author perpustakaan
 */
public final class RMGeneralConsent extends javax.swing.JDialog {
    private DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    public DlgKabupaten kab = new DlgKabupaten(null, false);
    public DlgKecamatan kec = new DlgKecamatan(null, false);
    public DlgKelurahan kel = new DlgKelurahan(null, false);
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private int i = 0, x = 0;
    private String kdkel = "", kdkec = "", kdkab = "", nipPtgs = "", pngJwbPasien = "", saksi1 = "", saksi2 = "",
            ruangrwt = "", idFileNmBerttd = "", idFileSaksi1 = "", idFileSaksi2 = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMGeneralConsent(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        this.setLocation(8,1);
        
        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Ruang Perawatan", "Tgl. General C.", "Nama Petugas",
            "nm_berttd", "alamat", "kd_kel", "kd_kec", "kd_kab", "selaku", "informasi_anggota_klg1", "informasi_anggota_klg2", "informasi_anggota_klg3",
            "izin_privasi", "orang_penengok1", "orang_penengok2", "orang_penengok3", "tanggal", "nip_petugas", "saksi1", "saksi2", "waktu_simpan",
            "no_tlp", "id_file_nm_berttd", "id_file_saksi1", "id_file_saksi2"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbGC.setModel(tabMode);
        tbGC.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbGC.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 30; i++) {
            TableColumn column = tbGC.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(90);
            } else if (i == 7) {
                column.setPreferredWidth(220);
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
            }
        }
        tbGC.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbGC.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbGC.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbGC.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbGC.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        
        TnmBerttd.setDocument(new batasInput((int) 200).getKata(TnmBerttd));
        Talamat.setDocument(new batasInput((int) 200).getKata(Talamat));
        TnmKlgA.setDocument(new batasInput((int) 200).getKata(TnmKlgA));
        TnmKlgB.setDocument(new batasInput((int) 200).getKata(TnmKlgB));
        TnmKlgC.setDocument(new batasInput((int) 200).getKata(TnmKlgC));
        TnmTengokA.setDocument(new batasInput((int) 200).getKata(TnmTengokA));
        TnmTengokB.setDocument(new batasInput((int) 200).getKata(TnmTengokB));
        TnmTengokC.setDocument(new batasInput((int) 200).getKata(TnmTengokC));
        TnmSaksi1.setDocument(new batasInput((int) 200).getKata(TnmSaksi1));
        TnmSaksi2.setDocument(new batasInput((int) 200).getKata(TnmSaksi2));
        TnoTelp.setDocument(new batasInput((byte) 13).getOnlyAngka(TnoTelp));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
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
                        nipPtgs = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPtgs.requestFocus();
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
        
        kel.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMGeneralConsent")) {
                    if (kel.getTable().getSelectedRow() != -1) {
                        TnmKel.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 0).toString());
                        kdkel = kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 1).toString();
                        BtnKelurahan.requestFocus();
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
        
        kec.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMGeneralConsent")) {
                    if (kec.getTable().getSelectedRow() != -1) {
                        TnmKec.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 0).toString());
                        kdkec = kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 1).toString();
                        BtnKecamatan.requestFocus();                        
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
        
        kab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMGeneralConsent")) {
                    if (kab.getTable().getSelectedRow() != -1) {
                        TnmKab.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 0).toString());
                        kdkab = kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 1).toString();
                        BtnKabupaten.requestFocus();             
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
        FormAsesmen = new widget.InternalFrame();
        ScrollTriase1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel5 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel146 = new widget.Label();
        BtnPtgs = new widget.Button();
        TtglGC = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TnmBerttd = new widget.TextBox();
        jLabel7 = new widget.Label();
        Talamat = new widget.TextBox();
        jLabel10 = new widget.Label();
        TnmKel = new widget.TextBox();
        jLabel11 = new widget.Label();
        TnmKec = new widget.TextBox();
        jLabel12 = new widget.Label();
        TnmKab = new widget.TextBox();
        jLabel13 = new widget.Label();
        cmbSelaku = new widget.ComboBox();
        jLabel14 = new widget.Label();
        jLabel15 = new widget.Label();
        TnmKlgA = new widget.TextBox();
        jLabel16 = new widget.Label();
        TnmKlgB = new widget.TextBox();
        jLabel17 = new widget.Label();
        TnmKlgC = new widget.TextBox();
        jLabel18 = new widget.Label();
        jLabel19 = new widget.Label();
        cmbIzin = new widget.ComboBox();
        jLabel21 = new widget.Label();
        jLabel23 = new widget.Label();
        TnmTengokA = new widget.TextBox();
        jLabel24 = new widget.Label();
        TnmTengokB = new widget.TextBox();
        jLabel25 = new widget.Label();
        TnmTengokC = new widget.TextBox();
        jLabel26 = new widget.Label();
        jLabel27 = new widget.Label();
        TnmPetugas = new widget.TextBox();
        jLabel28 = new widget.Label();
        TnmSaksi1 = new widget.TextBox();
        jLabel29 = new widget.Label();
        TnmSaksi2 = new widget.TextBox();
        BtnKelurahan = new widget.Button();
        BtnKecamatan = new widget.Button();
        BtnKabupaten = new widget.Button();
        chkSamaPasien = new widget.CekBox();
        TnoTelp = new widget.TextBox();
        jLabel30 = new widget.Label();
        chkSamaPoin = new widget.CekBox();
        FormInput2 = new widget.PanelBiasa();
        Scroll1 = new widget.ScrollPane();
        tbGC = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel20 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel8 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel9 = new widget.Label();
        LCount = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        jLabel63 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ General Consent ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        FormAsesmen.setBorder(null);
        FormAsesmen.setName("FormAsesmen"); // NOI18N
        FormAsesmen.setLayout(new java.awt.GridLayout(1, 2));

        ScrollTriase1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        ScrollTriase1.setName("ScrollTriase1"); // NOI18N
        ScrollTriase1.setOpaque(true);
        ScrollTriase1.setPreferredSize(new java.awt.Dimension(102, 760));

        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 621));
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

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(315, 10, 340, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(240, 10, 70, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Ruang Rawat : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 38, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setBackground(new java.awt.Color(245, 250, 240));
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(114, 38, 540, 23);

        jLabel146.setForeground(new java.awt.Color(0, 0, 0));
        jLabel146.setText("Yang bertanda tangan di bawah ini :");
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(0, 66, 210, 23);

        BtnPtgs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPtgs.setMnemonic('2');
        BtnPtgs.setToolTipText("Alt+2");
        BtnPtgs.setName("BtnPtgs"); // NOI18N
        BtnPtgs.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPtgs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPtgsActionPerformed(evt);
            }
        });
        FormInput.add(BtnPtgs);
        BtnPtgs.setBounds(657, 514, 28, 23);

        TtglGC.setEditable(false);
        TtglGC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-02-2026" }));
        TtglGC.setDisplayFormat("dd-MM-yyyy");
        TtglGC.setName("TtglGC"); // NOI18N
        TtglGC.setOpaque(false);
        TtglGC.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglGC);
        TtglGC.setBounds(114, 514, 90, 23);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Nama : ");
        jLabel6.setName("jLabel6"); // NOI18N
        FormInput.add(jLabel6);
        jLabel6.setBounds(0, 94, 110, 23);

        TnmBerttd.setBackground(new java.awt.Color(245, 250, 240));
        TnmBerttd.setForeground(new java.awt.Color(0, 0, 0));
        TnmBerttd.setName("TnmBerttd"); // NOI18N
        TnmBerttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmBerttdKeyPressed(evt);
            }
        });
        FormInput.add(TnmBerttd);
        TnmBerttd.setBounds(114, 94, 540, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Alamat : ");
        jLabel7.setName("jLabel7"); // NOI18N
        FormInput.add(jLabel7);
        jLabel7.setBounds(0, 122, 110, 23);

        Talamat.setBackground(new java.awt.Color(245, 250, 240));
        Talamat.setForeground(new java.awt.Color(0, 0, 0));
        Talamat.setName("Talamat"); // NOI18N
        Talamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalamatKeyPressed(evt);
            }
        });
        FormInput.add(Talamat);
        Talamat.setBounds(114, 122, 540, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Kelurahan : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 150, 110, 23);

        TnmKel.setEditable(false);
        TnmKel.setBackground(new java.awt.Color(245, 250, 240));
        TnmKel.setForeground(new java.awt.Color(0, 0, 0));
        TnmKel.setName("TnmKel"); // NOI18N
        FormInput.add(TnmKel);
        TnmKel.setBounds(114, 150, 300, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Kecamatan : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 178, 110, 23);

        TnmKec.setEditable(false);
        TnmKec.setBackground(new java.awt.Color(245, 250, 240));
        TnmKec.setForeground(new java.awt.Color(0, 0, 0));
        TnmKec.setName("TnmKec"); // NOI18N
        FormInput.add(TnmKec);
        TnmKec.setBounds(114, 178, 300, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Kabupaten : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 206, 110, 23);

        TnmKab.setEditable(false);
        TnmKab.setBackground(new java.awt.Color(245, 250, 240));
        TnmKab.setForeground(new java.awt.Color(0, 0, 0));
        TnmKab.setName("TnmKab"); // NOI18N
        FormInput.add(TnmKab);
        TnmKab.setBounds(114, 206, 400, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("No. Telp. : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 234, 110, 23);

        cmbSelaku.setForeground(new java.awt.Color(0, 0, 0));
        cmbSelaku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Pasien", "Orang Tua", "Suami", "Istri", "Anak", "Wali Hukum" }));
        cmbSelaku.setName("cmbSelaku"); // NOI18N
        cmbSelaku.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbSelaku);
        cmbSelaku.setBounds(314, 234, 90, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("III. INFORMASI KESEHATAN PASIEN (Nama anggota keluarga diberi wewenang) :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 262, 420, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("a. : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 290, 110, 23);

        TnmKlgA.setBackground(new java.awt.Color(245, 250, 240));
        TnmKlgA.setForeground(new java.awt.Color(0, 0, 0));
        TnmKlgA.setName("TnmKlgA"); // NOI18N
        TnmKlgA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmKlgAKeyPressed(evt);
            }
        });
        FormInput.add(TnmKlgA);
        TnmKlgA.setBounds(114, 290, 540, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("b. : ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 318, 110, 23);

        TnmKlgB.setBackground(new java.awt.Color(245, 250, 240));
        TnmKlgB.setForeground(new java.awt.Color(0, 0, 0));
        TnmKlgB.setName("TnmKlgB"); // NOI18N
        TnmKlgB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmKlgBKeyPressed(evt);
            }
        });
        FormInput.add(TnmKlgB);
        TnmKlgB.setBounds(114, 318, 540, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("c. : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 346, 110, 23);

        TnmKlgC.setBackground(new java.awt.Color(245, 250, 240));
        TnmKlgC.setForeground(new java.awt.Color(0, 0, 0));
        TnmKlgC.setName("TnmKlgC"); // NOI18N
        TnmKlgC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmKlgCKeyPressed(evt);
            }
        });
        FormInput.add(TnmKlgC);
        TnmKlgC.setBounds(114, 346, 540, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("V. HARAPAN DAN KEBUTUHAN PRIVASI");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 374, 220, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Jenis Izin : ");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(0, 402, 110, 23);

        cmbIzin.setForeground(new java.awt.Color(0, 0, 0));
        cmbIzin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "mengijinkan", "tidak mengijinkan" }));
        cmbIzin.setName("cmbIzin"); // NOI18N
        cmbIzin.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbIzin);
        cmbIzin.setBounds(114, 402, 115, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Yang Diberi/Tidak Diberi Izin Menengok Pasien :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(230, 402, 240, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("a. : ");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 430, 110, 23);

        TnmTengokA.setBackground(new java.awt.Color(245, 250, 240));
        TnmTengokA.setForeground(new java.awt.Color(0, 0, 0));
        TnmTengokA.setName("TnmTengokA"); // NOI18N
        TnmTengokA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmTengokAKeyPressed(evt);
            }
        });
        FormInput.add(TnmTengokA);
        TnmTengokA.setBounds(114, 430, 540, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("b. : ");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 458, 110, 23);

        TnmTengokB.setBackground(new java.awt.Color(245, 250, 240));
        TnmTengokB.setForeground(new java.awt.Color(0, 0, 0));
        TnmTengokB.setName("TnmTengokB"); // NOI18N
        TnmTengokB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmTengokBKeyPressed(evt);
            }
        });
        FormInput.add(TnmTengokB);
        TnmTengokB.setBounds(114, 458, 540, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("c. : ");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 486, 110, 23);

        TnmTengokC.setBackground(new java.awt.Color(245, 250, 240));
        TnmTengokC.setForeground(new java.awt.Color(0, 0, 0));
        TnmTengokC.setName("TnmTengokC"); // NOI18N
        TnmTengokC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmTengokCKeyPressed(evt);
            }
        });
        FormInput.add(TnmTengokC);
        TnmTengokC.setBounds(114, 486, 540, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Tanggal :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(0, 514, 110, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Nama Petugas :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(210, 514, 90, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setBackground(new java.awt.Color(245, 250, 240));
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(304, 514, 350, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Saksi 1 : ");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(0, 542, 110, 23);

        TnmSaksi1.setBackground(new java.awt.Color(245, 250, 240));
        TnmSaksi1.setForeground(new java.awt.Color(0, 0, 0));
        TnmSaksi1.setName("TnmSaksi1"); // NOI18N
        TnmSaksi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmSaksi1KeyPressed(evt);
            }
        });
        FormInput.add(TnmSaksi1);
        TnmSaksi1.setBounds(114, 542, 540, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Saksi 2 : ");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(0, 570, 110, 23);

        TnmSaksi2.setBackground(new java.awt.Color(245, 250, 240));
        TnmSaksi2.setForeground(new java.awt.Color(0, 0, 0));
        TnmSaksi2.setName("TnmSaksi2"); // NOI18N
        FormInput.add(TnmSaksi2);
        TnmSaksi2.setBounds(114, 570, 540, 23);

        BtnKelurahan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKelurahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKelurahan.setMnemonic('2');
        BtnKelurahan.setToolTipText("ALt+2");
        BtnKelurahan.setName("BtnKelurahan"); // NOI18N
        BtnKelurahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKelurahanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKelurahan);
        BtnKelurahan.setBounds(420, 150, 28, 23);

        BtnKecamatan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKecamatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKecamatan.setMnemonic('3');
        BtnKecamatan.setToolTipText("ALt+3");
        BtnKecamatan.setName("BtnKecamatan"); // NOI18N
        BtnKecamatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKecamatanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKecamatan);
        BtnKecamatan.setBounds(420, 178, 28, 23);

        BtnKabupaten.setForeground(new java.awt.Color(0, 0, 0));
        BtnKabupaten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKabupaten.setMnemonic('4');
        BtnKabupaten.setToolTipText("ALt+4");
        BtnKabupaten.setName("BtnKabupaten"); // NOI18N
        BtnKabupaten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKabupatenActionPerformed(evt);
            }
        });
        FormInput.add(BtnKabupaten);
        BtnKabupaten.setBounds(517, 206, 28, 23);

        chkSamaPasien.setBorder(null);
        chkSamaPasien.setForeground(new java.awt.Color(0, 0, 0));
        chkSamaPasien.setText("Alamat Sama Dengan Data Persetujuan R. Inap");
        chkSamaPasien.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkSamaPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSamaPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSamaPasien.setName("chkSamaPasien"); // NOI18N
        chkSamaPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSamaPasienActionPerformed(evt);
            }
        });
        FormInput.add(chkSamaPasien);
        chkSamaPasien.setBounds(455, 150, 300, 23);

        TnoTelp.setBackground(new java.awt.Color(245, 250, 240));
        TnoTelp.setForeground(new java.awt.Color(0, 0, 0));
        TnoTelp.setName("TnoTelp"); // NOI18N
        TnoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoTelpKeyPressed(evt);
            }
        });
        FormInput.add(TnoTelp);
        TnoTelp.setBounds(114, 234, 130, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Selaku : ");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(250, 234, 60, 23);

        chkSamaPoin.setBorder(null);
        chkSamaPoin.setForeground(new java.awt.Color(0, 0, 0));
        chkSamaPoin.setText("Sama dengan poin III. diatas");
        chkSamaPoin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkSamaPoin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSamaPoin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSamaPoin.setName("chkSamaPoin"); // NOI18N
        chkSamaPoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSamaPoinActionPerformed(evt);
            }
        });
        FormInput.add(chkSamaPoin);
        chkSamaPoin.setBounds(477, 402, 200, 23);

        ScrollTriase1.setViewportView(FormInput);

        FormAsesmen.add(ScrollTriase1);

        FormInput2.setBorder(null);
        FormInput2.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis..!!");
        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(870, 718));
        FormInput2.setLayout(new java.awt.BorderLayout());

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Data General Consent Pasien  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbGC.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbGC.setName("tbGC"); // NOI18N
        tbGC.getTableHeader().setReorderingAllowed(false);
        tbGC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGCMouseClicked(evt);
            }
        });
        tbGC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbGCKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbGC);

        FormInput2.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Tgl. General Consent :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass10.add(jLabel20);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-02-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d.");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel22);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-02-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari2);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Key Word :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel8);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+3");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnCari);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Record :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel9);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        FormInput2.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        FormAsesmen.add(FormInput2);

        internalFrame1.add(FormAsesmen, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
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

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Cetak Dalam Bentuk :");
        jLabel63.setName("jLabel63"); // NOI18N
        jLabel63.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel63);

        cmbPilihCetak.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetak.setName("cmbPilihCetak"); // NOI18N
        cmbPilihCetak.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass8.add(cmbPilihCetak);

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
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (TnmBerttd.getText().equals("")) {
                pngJwbPasien = "";
            } else {
                pngJwbPasien = TnmBerttd.getText() + " (Penanggung Jawab)";
            }
            
            if (TnmSaksi1.getText().equals("")) {
                saksi1 = "";
            } else {
                saksi1 = TnmSaksi1.getText() + " (Saksi 1)";
            }
            
            if (TnmSaksi2.getText().equals("")) {
                saksi2 = "";
            } else {
                saksi2 = TnmSaksi2.getText() + " (Saksi 2)";
            }
            
            if (Sequel.menyimpantf("general_consent", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 23, new String[]{
                TNoRw.getText(), pngJwbPasien, Talamat.getText(), kdkel, kdkec, kdkab, TnoTelp.getText(), cmbSelaku.getSelectedItem().toString(),
                TnmKlgA.getText(), TnmKlgB.getText(), TnmKlgC.getText(), cmbIzin.getSelectedItem().toString(), TnmTengokA.getText(),
                TnmTengokB.getText(), TnmTengokC.getText(), Valid.SetTgl(TtglGC.getSelectedItem() + ""), nipPtgs, saksi1, saksi2,
                Sequel.cariIsi("select now()"), "", "", ""
            }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "General Consent", "Simpan");
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        tampil();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
            tampil();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbGC.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from general_consent where no_rawat=?", 1, new String[]{
                    tbGC.getValueAt(tbGC.getSelectedRow(), 0).toString()
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
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (tbGC.getSelectedRow() > -1) {
            if (TnmBerttd.getText().equals("")) {
                pngJwbPasien = "";
            } else {
                pngJwbPasien = TnmBerttd.getText() + " (Penanggung Jawab)";
            }

            if (TnmSaksi1.getText().equals("")) {
                saksi1 = "";
            } else {
                saksi1 = TnmSaksi1.getText() + " (Saksi 1)";
            }

            if (TnmSaksi2.getText().equals("")) {
                saksi2 = "";
            } else {
                saksi2 = TnmSaksi2.getText() + " (Saksi 2)";
            }
            
            if (Sequel.mengedittf("general_consent", "no_rawat=?", "nm_berttd=?, alamat=?, kd_kel=?, kd_kec=?, kd_kab=?, selaku=?, informasi_anggota_klg1=?, "
                    + "informasi_anggota_klg2=?, informasi_anggota_klg3=?, izin_privasi=?, orang_penengok1=?, orang_penengok2=?, orang_penengok3=?, tanggal=?, "
                    + "nip_petugas=?, saksi1=?, saksi2=?, no_tlp=?", 19, new String[]{
                        pngJwbPasien, Talamat.getText(), kdkel, kdkec, kdkab, cmbSelaku.getSelectedItem().toString(), TnmKlgA.getText(), TnmKlgB.getText(),
                        TnmKlgC.getText(), cmbIzin.getSelectedItem().toString(), TnmTengokA.getText(), TnmTengokB.getText(), TnmTengokC.getText(),
                        Valid.SetTgl(TtglGC.getSelectedItem() + ""), nipPtgs, saksi1, saksi2, TnoTelp.getText(),
                        tbGC.getValueAt(tbGC.getSelectedRow(), 0).toString()
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "General Consent", "Ganti");
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbGC.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            
            param.put("nmBerttd", TnmBerttd.getText());
            param.put("alamat", Talamat.getText() + ", Kel. " + TnmKel.getText() + ", Kec. " + TnmKec.getText() + ", Kab. " + TnmKab.getText());            
            param.put("selaku", cmbSelaku.getSelectedItem().toString());
            
            if (TnoTelp.getText().equals("")) {
                param.put("notelp", "-");
            } else {
                param.put("notelp", TnoTelp.getText());
            }
            
            if (TnmKlgA.getText().equals("")) {
                param.put("poin3A", "..........................");
            } else {
                param.put("poin3A", TnmKlgA.getText());
            }
            
            if (TnmKlgB.getText().equals("")) {
                param.put("poin3B", "..........................");
            } else {
                param.put("poin3B", TnmKlgB.getText());
            }
            
            if (TnmKlgC.getText().equals("")) {
                param.put("poin3C", "..........................");
            } else {
                param.put("poin3C", TnmKlgC.getText());
            }
            
            param.put("izin", cmbIzin.getSelectedItem().toString());
            
            if (TnmTengokA.getText().equals("")) {
                param.put("tengokA", "..........................");
            } else {
                param.put("tengokA", TnmTengokA.getText());
            }
            
            if (TnmTengokB.getText().equals("")) {
                param.put("tengokB", "..........................");
            } else {
                param.put("tengokB", TnmTengokB.getText());
            }
            
            if (TnmTengokC.getText().equals("")) {
                param.put("tengokC", "..........................");
            } else {
                param.put("tengokC", TnmTengokC.getText());
            }
            
            param.put("tgl", "Martapura, " + Valid.SetTglINDONESIA(Valid.SetTgl(TtglGC.getSelectedItem() + "")));
            param.put("pemberiInformasi", TnmPetugas.getText());
            
            if (TnmSaksi1.getText().equals("")) {
                param.put("saksi1", "..........................");
            } else {
                param.put("saksi1", TnmSaksi1.getText());
            }
            
            if (TnmSaksi2.getText().equals("")) {
                param.put("saksi2", "..........................");
            } else {
                param.put("saksi2", TnmSaksi2.getText());
            }

            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isi = "";
                if (nipPtgs.equals("") || nipPtgs.equals("-") || nipPtgs.equals("--")) {
                    JOptionPane.showMessageDialog(rootPane, "Nama petugas TPPRI harus diisi dulu,..");
                } else {
                    isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "General Consent", TnmPetugas.getText(),
                                    Sequel.cariIsi("select date_format('" + tbGC.getValueAt(tbGC.getSelectedRow(), 25).toString() + "','%d/%m/%Y')"),
                                    Sequel.cariIsi("select time('" + tbGC.getValueAt(tbGC.getSelectedRow(), 25).toString() + "')")) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                    Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                    Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE General Consent", Sequel.cariFolderPrintTte());
                    param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                    param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));
                    
                    Valid.MyReport("rptGeneralConsent2Qr.jasper", "report", "::[ General Consent hal. 1 ]::",
                            "SELECT date(now()) tgl", param);
                    Valid.MyReport("rptGeneralConsent1Qr.jasper", "report", "::[ General Consent hal. 2 ]::",
                            "SELECT date(now()) tgl", param);
                    Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                }
            } else {
                Valid.MyReport("rptGeneralConsent2.jasper", "report", "::[ General Consent hal. 1 ]::",
                        "SELECT date(now()) tgl", param);
                Valid.MyReport("rptGeneralConsent1.jasper", "report", "::[ General Consent hal. 2 ]::",
                        "SELECT date(now()) tgl", param);
            }
            
            BtnBatalActionPerformed(null);
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih/klik dulu salah satu datanya pada tabel..!!!");
            tbGC.requestFocus();
        }        
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            tampil();
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnPtgsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPtgsActionPerformed
        akses.setform("RMGeneralConsent");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPtgsActionPerformed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void tbGCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbGCKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbGCKeyPressed

    private void tbGCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGCMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbGCMouseClicked

    private void TnmBerttdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmBerttdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Talamat.requestFocus();
        }
    }//GEN-LAST:event_TnmBerttdKeyPressed

    private void TnmKlgAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmKlgAKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmKlgB.requestFocus();
        }
    }//GEN-LAST:event_TnmKlgAKeyPressed

    private void TnmKlgBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmKlgBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmKlgC.requestFocus();
        }
    }//GEN-LAST:event_TnmKlgBKeyPressed

    private void TnmKlgCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmKlgCKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbIzin.requestFocus();
        }
    }//GEN-LAST:event_TnmKlgCKeyPressed

    private void TnmTengokAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmTengokAKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmTengokB.requestFocus();
        }
    }//GEN-LAST:event_TnmTengokAKeyPressed

    private void TnmTengokBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmTengokBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmTengokC.requestFocus();
        }
    }//GEN-LAST:event_TnmTengokBKeyPressed

    private void TnmTengokCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmTengokCKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglGC.requestFocus();
        }
    }//GEN-LAST:event_TnmTengokCKeyPressed

    private void TnmSaksi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmSaksi1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmSaksi2.requestFocus();
        }
    }//GEN-LAST:event_TnmSaksi1KeyPressed

    private void BtnKelurahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKelurahanActionPerformed
        akses.setform("RMGeneralConsent");
        kel.setSize(703, 384);
        kel.setLocationRelativeTo(internalFrame1);
        kel.setVisible(true);
        kel.TCari.requestFocus();
        kel.isCek();
    }//GEN-LAST:event_BtnKelurahanActionPerformed

    private void BtnKecamatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKecamatanActionPerformed
        akses.setform("RMGeneralConsent");
        kec.setSize(703, 384);
        kec.setLocationRelativeTo(internalFrame1);
        kec.setVisible(true);
        kec.TCari.requestFocus();
        kec.isCek();
    }//GEN-LAST:event_BtnKecamatanActionPerformed

    private void BtnKabupatenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKabupatenActionPerformed
        akses.setform("RMGeneralConsent");
        kab.setSize(703, 384);
        kab.setLocationRelativeTo(internalFrame1);
        kab.setVisible(true);
        kab.TCari.requestFocus();
        kab.isCek();
    }//GEN-LAST:event_BtnKabupatenActionPerformed

    private void chkSamaPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSamaPasienActionPerformed
        if (chkSamaPasien.isSelected() == true) {
            try {
                ps1 = koneksi.prepareStatement("select p.*, kl.nm_kel, kc.nm_kec, kb.nm_kab from persetujuan_ranap p "
                    + "inner join kelurahan kl on kl.kd_kel=p.kd_kel "
                    + "inner join kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "inner join kabupaten kb on kb.kd_kab=p.kd_kab where p.no_rawat='" + TNoRw.getText() + "'");
                try {
                    rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        TnmBerttd.setText(rs1.getString("nm_berttd"));
                        Talamat.setText(rs1.getString("alamat"));
                        kdkel = rs1.getString("kd_kel");
                        kdkec = rs1.getString("kd_kec");
                        kdkab = rs1.getString("kd_kab");
                        TnmKel.setText(rs1.getString("nm_kel"));
                        TnmKec.setText(rs1.getString("nm_kec"));
                        TnmKab.setText(rs1.getString("nm_kab"));
                        TnoTelp.setText(rs1.getString("no_tlp"));
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
        } else {
            TnmBerttd.setText("");
            Talamat.setText("");
            kdkel = "0";
            kdkec = "0";
            kdkab = "0";
            TnmKel.setText("-");
            TnmKec.setText("-");
            TnmKab.setText("-");
            TnoTelp.setText("");
        }
    }//GEN-LAST:event_chkSamaPasienActionPerformed

    private void TalamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalamatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnKelurahan.requestFocus();
        }
    }//GEN-LAST:event_TalamatKeyPressed

    private void TnoTelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoTelpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbSelaku.requestFocus();
        }
    }//GEN-LAST:event_TnoTelpKeyPressed

    private void chkSamaPoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSamaPoinActionPerformed
        if (chkSamaPoin.isSelected() == true) {
            TnmTengokA.setText(TnmKlgA.getText());
            TnmTengokB.setText(TnmKlgB.getText());
            TnmTengokC.setText(TnmKlgC.getText());
        } else {
            TnmTengokA.setText("");
            TnmTengokB.setText("");
            TnmTengokC.setText("");
        }
    }//GEN-LAST:event_chkSamaPoinActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMGeneralConsent dialog = new RMGeneralConsent(new javax.swing.JFrame(), true);
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
    private widget.Button BtnHapus;
    private widget.Button BtnKabupaten;
    private widget.Button BtnKecamatan;
    private widget.Button BtnKeluar;
    private widget.Button BtnKelurahan;
    private widget.Button BtnPrint;
    private widget.Button BtnPtgs;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.InternalFrame FormAsesmen;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput2;
    private widget.Label LCount;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane ScrollTriase1;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Talamat;
    private widget.TextBox TnmBerttd;
    private widget.TextBox TnmKab;
    private widget.TextBox TnmKec;
    private widget.TextBox TnmKel;
    private widget.TextBox TnmKlgA;
    private widget.TextBox TnmKlgB;
    private widget.TextBox TnmKlgC;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TnmSaksi1;
    private widget.TextBox TnmSaksi2;
    private widget.TextBox TnmTengokA;
    private widget.TextBox TnmTengokB;
    private widget.TextBox TnmTengokC;
    private widget.TextBox TnoTelp;
    private widget.TextBox TrgRawat;
    private widget.Tanggal TtglGC;
    private widget.CekBox chkSamaPasien;
    private widget.CekBox chkSamaPoin;
    private widget.ComboBox cmbIzin;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbSelaku;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel146;
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
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbGC;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select gc.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                    + "date_format(gc.tanggal,'%d-%m-%Y') tglGC, pg.nama nmPtgs, replace(nm_berttd,' (Penanggung Jawab)','') nmttd, "
                    + "replace(saksi1,' (Saksi 1)','') sak1, replace(saksi2,' (Saksi 2)','') sak2 from general_consent gc "
                    + "inner join reg_periksa rp on rp.no_rawat=gc.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=gc.nip_petugas where "
                    + "gc.tanggal between ? and ? and gc.no_rawat like ? or "
                    + "gc.tanggal between ? and ? and p.no_rkm_medis like ? or "
                    + "gc.tanggal between ? and ? and p.nm_pasien like ? or "
                    + "gc.tanggal between ? and ? and pg.nama like ? order by gc.waktu_simpan desc");
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
                    ruangrwt = Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                            + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + rs.getString("no_rawat") + "' "
                            + "order by ki.tgl_masuk, ki.jam_masuk limit 1");
                    
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jenkel"),
                        rs.getString("tglLahir"),
                        ruangrwt,
                        rs.getString("tglGC"),
                        rs.getString("nmPtgs"),
                        rs.getString("nmttd"),
                        rs.getString("alamat"),
                        rs.getString("kd_kel"),
                        rs.getString("kd_kec"),
                        rs.getString("kd_kab"),
                        rs.getString("selaku"),
                        rs.getString("informasi_anggota_klg1"),
                        rs.getString("informasi_anggota_klg2"),
                        rs.getString("informasi_anggota_klg3"),
                        rs.getString("izin_privasi"),
                        rs.getString("orang_penengok1"),
                        rs.getString("orang_penengok2"),
                        rs.getString("orang_penengok3"),
                        rs.getString("tanggal"),
                        rs.getString("nip_petugas"),
                        rs.getString("sak1"),
                        rs.getString("sak2"),
                        rs.getString("waktu_simpan"),
                        rs.getString("no_tlp"),                        
                        rs.getString("id_file_nm_berttd"),
                        rs.getString("id_file_saksi1"),
                        rs.getString("id_file_saksi2")
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
    
    public void emptTeks(){
        TnmBerttd.setText("");
        Talamat.setText("");
        kdkel = "0";
        kdkec = "0";
        kdkab = "0";
        TnmKel.setText("-");
        TnmKec.setText("-");
        TnmKab.setText("-");
        chkSamaPasien.setSelected(false);
        TnoTelp.setText("");
        cmbSelaku.setSelectedIndex(0);
        TnmKlgA.setText("");
        TnmKlgB.setText("");
        TnmKlgC.setText("");
        cmbIzin.setSelectedIndex(0);
        chkSamaPoin.setSelected(false);
        TnmTengokA.setText("");
        TnmTengokB.setText("");
        TnmTengokC.setText("");
        TtglGC.setDate(new Date());

        if (akses.getadmin() == true) {
            nipPtgs = "-";
        } else {
            nipPtgs = akses.getkode();
        }

        TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPtgs + "'"));
        TnmSaksi1.setText("");
        TnmSaksi2.setText("");
    }

    public void setData(String norwt) {
        TNoRw.setText(norwt);
        TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norwt + "'"));
        TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        TrgRawat.setText(Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + norwt + "' "
                + "order by ki.tgl_masuk, ki.jam_masuk limit 1"));
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(new Date());
        TCari.setText(norwt);
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getbpjs_sep());
        BtnHapus.setEnabled(akses.getbpjs_sep());
        BtnEdit.setEnabled(akses.getbpjs_sep());
        
        if (akses.getjml2() >= 1) {
            nipPtgs = akses.getkode();
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPetugas, nipPtgs);
            if (TnmPetugas.getText().equals("")) {
                TnmPetugas.setText("-");
            }
        }
    }

    private void getData() {
        nipPtgs = "";
        kdkel = "";
        kdkec = "";
        kdkab = "";
        idFileNmBerttd = "";
        idFileSaksi1 = "";
        idFileSaksi2 = "";
        
        if (tbGC.getSelectedRow() != -1) {
            TNoRw.setText(tbGC.getValueAt(tbGC.getSelectedRow(), 0).toString());
            TNoRM.setText(tbGC.getValueAt(tbGC.getSelectedRow(), 1).toString());
            TPasien.setText(tbGC.getValueAt(tbGC.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbGC.getValueAt(tbGC.getSelectedRow(), 5).toString());
            TnmBerttd.setText(tbGC.getValueAt(tbGC.getSelectedRow(), 8).toString());
            Talamat.setText(tbGC.getValueAt(tbGC.getSelectedRow(), 9).toString());
            kdkel = tbGC.getValueAt(tbGC.getSelectedRow(), 10).toString();
            kdkec = tbGC.getValueAt(tbGC.getSelectedRow(), 11).toString();
            kdkab = tbGC.getValueAt(tbGC.getSelectedRow(), 12).toString();
            TnmKel.setText(Sequel.cariIsi("select nm_kel from kelurahan where kd_kel='" + kdkel + "'"));
            TnmKec.setText(Sequel.cariIsi("select nm_kec from kecamatan where kd_kec='" + kdkec + "'"));
            TnmKab.setText(Sequel.cariIsi("select nm_kab from kabupaten where kd_kab='" + kdkab + "'"));
            cmbSelaku.setSelectedItem(tbGC.getValueAt(tbGC.getSelectedRow(), 13).toString());
            TnmKlgA.setText(tbGC.getValueAt(tbGC.getSelectedRow(), 14).toString());
            TnmKlgB.setText(tbGC.getValueAt(tbGC.getSelectedRow(), 15).toString());
            TnmKlgC.setText(tbGC.getValueAt(tbGC.getSelectedRow(), 16).toString());
            cmbIzin.setSelectedItem(tbGC.getValueAt(tbGC.getSelectedRow(), 17).toString());
            TnmTengokA.setText(tbGC.getValueAt(tbGC.getSelectedRow(), 18).toString());
            TnmTengokB.setText(tbGC.getValueAt(tbGC.getSelectedRow(), 19).toString());
            TnmTengokC.setText(tbGC.getValueAt(tbGC.getSelectedRow(), 20).toString());
            Valid.SetTgl(TtglGC, tbGC.getValueAt(tbGC.getSelectedRow(), 21).toString());
            nipPtgs = tbGC.getValueAt(tbGC.getSelectedRow(), 22).toString();
            TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPtgs + "'"));
            TnmSaksi1.setText(tbGC.getValueAt(tbGC.getSelectedRow(), 23).toString());
            TnmSaksi2.setText(tbGC.getValueAt(tbGC.getSelectedRow(), 24).toString());
            TnoTelp.setText(tbGC.getValueAt(tbGC.getSelectedRow(), 26).toString());            
            idFileNmBerttd = tbGC.getValueAt(tbGC.getSelectedRow(), 27).toString();
            idFileSaksi1 = tbGC.getValueAt(tbGC.getSelectedRow(), 28).toString();
            idFileSaksi2 = tbGC.getValueAt(tbGC.getSelectedRow(), 29).toString();
        }
    }
}
