package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import kepegawaian.DlgCariPetugas;
import simrskhanza.DlgCariDokter;
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public class RMPersetujuanTindakanHemodialisis extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private int i = 0, x = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String sttsrwt = "", nipSaksi = "", nipDokter = "", idFileSaksiKlg = "", idFileBerttd = "", tglReg = "", alamatPasien = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMPersetujuanTindakanHemodialisis(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Alamat", "Ruang Rawat", "Tgl. Persetujuan", "Nama Dokter", "Nama Saksi Dari RS", "Pembuat Pernyataan",
            "ruang_rawat", "nm_betttd", "umur", "jenkel", "alamat", "no_bukti_diri", "diagnosis", "tujuan_tindakan", "alternatif_tindakan", "resiko", "prognosis",
            "perkiraan_biaya", "ket_hemodialisis_indonesia", "ket_hemodialisis_english", "terhadap_ind", "terhadap_eng", "ket_lainya", "tgl_persetujuan", "nip_dokter",
            "nip_saksi_rs", "nm_saksi_klg", "id_file_nm_berttd", "id_file_nm_saksi_klg", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPersetujuan.setModel(tabMode);
        tbPersetujuan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPersetujuan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 34; i++) {
            TableColumn column = tbPersetujuan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(220);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setPreferredWidth(220);
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
            }
        }
        tbPersetujuan.setDefaultRenderer(Object.class, new WarnaTable());

        TnmBerttd.setDocument(new batasInput((int) 150).getKata(TnmBerttd));
        Tumur.setDocument(new batasInput((byte) 3).getOnlyAngka(Tumur));
        Talamat.setDocument(new batasInput((int) 255).getKata(Talamat));
        TnoBukti.setDocument(new batasInput((int) 50).getKata(TnoBukti));
        Tdiagnosis.setDocument(new batasInput((int) 255).getKata(Tdiagnosis));
        Ttujuan.setDocument(new batasInput((int) 255).getKata(Ttujuan));
        Talternatif.setDocument(new batasInput((int) 255).getKata(Talternatif));
        Tresiko.setDocument(new batasInput((int) 255).getKata(Tresiko));
        Tprognosis.setDocument(new batasInput((int) 255).getKata(Tprognosis));
        Tperkiraan.setDocument(new batasInput((int) 20).getKata(Tperkiraan));
        Tlainya.setDocument(new batasInput((int) 100).getKata(Tlainya));
        TnmSaksiKlg.setDocument(new batasInput((int) 150).getKata(TnmSaksiKlg));        
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    nipSaksi = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();                    
                    TnmSaksiRS.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    BtnPerawat.requestFocus();
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMPersetujuanTindakanHemodialisis")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        nipDokter = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                        TnmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        BtnDokter.requestFocus();
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
        panelGlass11 = new widget.panelisi();
        Scroll1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel5 = new widget.Label();
        TnmBerttd = new widget.TextBox();
        jLabel8 = new widget.Label();
        Tumur = new widget.TextBox();
        jLabel9 = new widget.Label();
        cmbJenkel = new widget.ComboBox();
        jLabel10 = new widget.Label();
        Talamat = new widget.TextBox();
        jLabel11 = new widget.Label();
        TnoBukti = new widget.TextBox();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        Tdiagnosis = new widget.TextBox();
        jLabel14 = new widget.Label();
        Ttujuan = new widget.TextBox();
        jLabel15 = new widget.Label();
        Talternatif = new widget.TextBox();
        Tresiko = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel17 = new widget.Label();
        Tprognosis = new widget.TextBox();
        jLabel18 = new widget.Label();
        Tperkiraan = new widget.TextBox();
        jLabel19 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        TketIndonesia = new widget.TextArea();
        scrollPane4 = new widget.ScrollPane();
        TketEnglish = new widget.TextArea();
        jLabel20 = new widget.Label();
        jLabel21 = new widget.Label();
        cmbTerhadapInd = new widget.ComboBox();
        Tlainya = new widget.TextBox();
        jLabel22 = new widget.Label();
        cmbTerhadapEng = new widget.ComboBox();
        jLabel23 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel24 = new widget.Label();
        TnmDokter = new widget.TextBox();
        jLabel25 = new widget.Label();
        TnmSaksiRS = new widget.TextBox();
        jLabel26 = new widget.Label();
        TnmSaksiKlg = new widget.TextBox();
        jLabel27 = new widget.Label();
        TtglPersetujuan = new widget.Tanggal();
        BtnPerawat = new widget.Button();
        BtnDokter = new widget.Button();
        chkSama = new widget.CekBox();
        panelGlass12 = new widget.panelisi();
        Scroll = new widget.ScrollPane();
        tbPersetujuan = new widget.Table();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Persetujuan Tindakan Kedokteran (Hemodialisis) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass11.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(192, 407));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 760));
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
        jLabel5.setText("Nama Pembuat Pernyataan/Bertanda Tangan : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 38, 260, 23);

        TnmBerttd.setBackground(new java.awt.Color(245, 250, 240));
        TnmBerttd.setForeground(new java.awt.Color(0, 0, 0));
        TnmBerttd.setName("TnmBerttd"); // NOI18N
        TnmBerttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmBerttdKeyPressed(evt);
            }
        });
        FormInput.add(TnmBerttd);
        TnmBerttd.setBounds(265, 38, 457, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Umur : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 66, 110, 23);

        Tumur.setBackground(new java.awt.Color(245, 250, 240));
        Tumur.setForeground(new java.awt.Color(0, 0, 0));
        Tumur.setName("Tumur"); // NOI18N
        Tumur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurKeyPressed(evt);
            }
        });
        FormInput.add(Tumur);
        Tumur.setBounds(114, 66, 50, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("tahun.    Jenis Kelamin : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(170, 66, 120, 23);

        cmbJenkel.setForeground(new java.awt.Color(0, 0, 0));
        cmbJenkel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Laki-laki", "Perempuan" }));
        cmbJenkel.setName("cmbJenkel"); // NOI18N
        cmbJenkel.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbJenkel);
        cmbJenkel.setBounds(292, 66, 90, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Alamat : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 94, 110, 23);

        Talamat.setBackground(new java.awt.Color(245, 250, 240));
        Talamat.setForeground(new java.awt.Color(0, 0, 0));
        Talamat.setName("Talamat"); // NOI18N
        Talamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalamatKeyPressed(evt);
            }
        });
        FormInput.add(Talamat);
        Talamat.setBounds(114, 94, 607, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Nomor Bukti Diri (KTP/SIM/Passport) : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 122, 260, 23);

        TnoBukti.setBackground(new java.awt.Color(245, 250, 240));
        TnoBukti.setForeground(new java.awt.Color(0, 0, 0));
        TnoBukti.setName("TnoBukti"); // NOI18N
        TnoBukti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoBuktiKeyPressed(evt);
            }
        });
        FormInput.add(TnoBukti);
        TnoBukti.setBounds(265, 122, 457, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Telah Mendapatkan Penjelasan Dokter Tentang :");
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 150, 310, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Diagnosis dan Tata Cara Tindakan Kedokteran : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 178, 260, 23);

        Tdiagnosis.setBackground(new java.awt.Color(245, 250, 240));
        Tdiagnosis.setForeground(new java.awt.Color(0, 0, 0));
        Tdiagnosis.setName("Tdiagnosis"); // NOI18N
        Tdiagnosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosisKeyPressed(evt);
            }
        });
        FormInput.add(Tdiagnosis);
        Tdiagnosis.setBounds(265, 178, 457, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tujuan Tindakan Kedokteran Yang Dilakukan : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 206, 260, 23);

        Ttujuan.setBackground(new java.awt.Color(245, 250, 240));
        Ttujuan.setForeground(new java.awt.Color(0, 0, 0));
        Ttujuan.setName("Ttujuan"); // NOI18N
        Ttujuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtujuanKeyPressed(evt);
            }
        });
        FormInput.add(Ttujuan);
        Ttujuan.setBounds(265, 206, 457, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Alternatif Tindakan Lain dan Resikonya : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 234, 260, 23);

        Talternatif.setBackground(new java.awt.Color(245, 250, 240));
        Talternatif.setForeground(new java.awt.Color(0, 0, 0));
        Talternatif.setName("Talternatif"); // NOI18N
        Talternatif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalternatifKeyPressed(evt);
            }
        });
        FormInput.add(Talternatif);
        Talternatif.setBounds(265, 234, 457, 23);

        Tresiko.setBackground(new java.awt.Color(245, 250, 240));
        Tresiko.setForeground(new java.awt.Color(0, 0, 0));
        Tresiko.setName("Tresiko"); // NOI18N
        Tresiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TresikoKeyPressed(evt);
            }
        });
        FormInput.add(Tresiko);
        Tresiko.setBounds(265, 262, 457, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Resiko dan Komplikasi Yang Mungkin Terjadi : ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 262, 260, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("<html><div style=\"text-align: right;\">Prognosis Terhadap Tindakan Yang :&nbsp;<br>Mungkin Dilakukan&nbsp;&nbsp;&nbsp;</div></html>");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 290, 260, 30);

        Tprognosis.setBackground(new java.awt.Color(245, 250, 240));
        Tprognosis.setForeground(new java.awt.Color(0, 0, 0));
        Tprognosis.setName("Tprognosis"); // NOI18N
        Tprognosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprognosisKeyPressed(evt);
            }
        });
        FormInput.add(Tprognosis);
        Tprognosis.setBounds(265, 290, 457, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Perkiraan Biaya : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 318, 260, 23);

        Tperkiraan.setBackground(new java.awt.Color(245, 250, 240));
        Tperkiraan.setForeground(new java.awt.Color(0, 0, 0));
        Tperkiraan.setName("Tperkiraan"); // NOI18N
        Tperkiraan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TperkiraanKeyPressed(evt);
            }
        });
        FormInput.add(Tperkiraan);
        Tperkiraan.setBounds(265, 318, 140, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("<html><div style=\"text-align: right;\">Dengan ini saya menyatakan dengan :&nbsp;<br>\nsesungguhnya bahwa saya <b>SETUJU</b> untuk&nbsp;&nbsp;&nbsp;<br>\ndilakukan operasi atau tindakan medis yang&nbsp;&nbsp;&nbsp;<br>\nsudah dijelaskan diatas berupa&nbsp;&nbsp;&nbsp;</div></html>");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(0, 346, 260, 60);

        scrollPane3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane3.setName("scrollPane3"); // NOI18N

        TketIndonesia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TketIndonesia.setColumns(20);
        TketIndonesia.setRows(5);
        TketIndonesia.setName("TketIndonesia"); // NOI18N
        TketIndonesia.setPreferredSize(new java.awt.Dimension(162, 700));
        TketIndonesia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketIndonesiaKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(TketIndonesia);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(265, 346, 457, 110);

        scrollPane4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane4.setName("scrollPane4"); // NOI18N

        TketEnglish.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TketEnglish.setColumns(20);
        TketEnglish.setRows(5);
        TketEnglish.setName("TketEnglish"); // NOI18N
        TketEnglish.setPreferredSize(new java.awt.Dimension(162, 700));
        TketEnglish.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketEnglishKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(TketEnglish);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(265, 462, 457, 110);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("<html><div style=\"text-align: right;\"><i>I hereby declare to the fact that i :&nbsp;<br>\n<b>AGREE</b> to do surgery or medical procedures&nbsp;&nbsp;&nbsp;<br>\nthat have been described above in the form*</i>&nbsp;&nbsp;&nbsp;</div></html>");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(0, 462, 260, 50);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Terhadap Diri : ");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(0, 577, 260, 23);

        cmbTerhadapInd.setForeground(new java.awt.Color(0, 0, 0));
        cmbTerhadapInd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Saya*", "Anak*", "Istri*", "Suami*", "Ayah*", "Ibu*", "Lainnya" }));
        cmbTerhadapInd.setName("cmbTerhadapInd"); // NOI18N
        cmbTerhadapInd.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbTerhadapInd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTerhadapIndActionPerformed(evt);
            }
        });
        FormInput.add(cmbTerhadapInd);
        cmbTerhadapInd.setBounds(265, 577, 70, 23);

        Tlainya.setBackground(new java.awt.Color(245, 250, 240));
        Tlainya.setForeground(new java.awt.Color(0, 0, 0));
        Tlainya.setName("Tlainya"); // NOI18N
        Tlainya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainyaKeyPressed(evt);
            }
        });
        FormInput.add(Tlainya);
        Tlainya.setBounds(342, 577, 380, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("<html><i>Of Myselft * : </i></html>");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 605, 260, 23);

        cmbTerhadapEng.setForeground(new java.awt.Color(0, 0, 0));
        cmbTerhadapEng.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "I*", "Child*", "Wife*", "Husband*", "Father*", "Mother*", "Other" }));
        cmbTerhadapEng.setName("cmbTerhadapEng"); // NOI18N
        cmbTerhadapEng.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbTerhadapEng);
        cmbTerhadapEng.setBounds(265, 605, 80, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Ruang Rawat : ");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 633, 260, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setBackground(new java.awt.Color(245, 250, 240));
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(265, 633, 457, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Nama Dokter : ");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 661, 260, 23);

        TnmDokter.setEditable(false);
        TnmDokter.setBackground(new java.awt.Color(245, 250, 240));
        TnmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter.setName("TnmDokter"); // NOI18N
        FormInput.add(TnmDokter);
        TnmDokter.setBounds(265, 661, 457, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Nama Saksi Dari Rumah Sakit : ");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 689, 260, 23);

        TnmSaksiRS.setEditable(false);
        TnmSaksiRS.setBackground(new java.awt.Color(245, 250, 240));
        TnmSaksiRS.setForeground(new java.awt.Color(0, 0, 0));
        TnmSaksiRS.setName("TnmSaksiRS"); // NOI18N
        FormInput.add(TnmSaksiRS);
        TnmSaksiRS.setBounds(265, 689, 457, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Nama Saksi Dari Keluarga : ");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(0, 717, 260, 23);

        TnmSaksiKlg.setBackground(new java.awt.Color(245, 250, 240));
        TnmSaksiKlg.setForeground(new java.awt.Color(0, 0, 0));
        TnmSaksiKlg.setName("TnmSaksiKlg"); // NOI18N
        FormInput.add(TnmSaksiKlg);
        TnmSaksiKlg.setBounds(265, 717, 457, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Tgl. Persetujuan : ");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(345, 605, 110, 23);

        TtglPersetujuan.setEditable(false);
        TtglPersetujuan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-11-2026" }));
        TtglPersetujuan.setDisplayFormat("dd-MM-yyyy");
        TtglPersetujuan.setName("TtglPersetujuan"); // NOI18N
        TtglPersetujuan.setOpaque(false);
        TtglPersetujuan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglPersetujuan);
        TtglPersetujuan.setBounds(460, 605, 90, 23);

        BtnPerawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPerawat.setMnemonic('2');
        BtnPerawat.setToolTipText("Alt+2");
        BtnPerawat.setName("BtnPerawat"); // NOI18N
        BtnPerawat.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerawatActionPerformed(evt);
            }
        });
        FormInput.add(BtnPerawat);
        BtnPerawat.setBounds(725, 689, 28, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(725, 661, 28, 23);

        chkSama.setBackground(new java.awt.Color(255, 255, 250));
        chkSama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSama.setForeground(new java.awt.Color(0, 0, 0));
        chkSama.setText("Sama Dengan Pasien");
        chkSama.setBorderPainted(true);
        chkSama.setBorderPaintedFlat(true);
        chkSama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSama.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSama.setName("chkSama"); // NOI18N
        chkSama.setOpaque(false);
        chkSama.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSamaActionPerformed(evt);
            }
        });
        FormInput.add(chkSama);
        chkSama.setBounds(727, 94, 140, 23);

        Scroll1.setViewportView(FormInput);

        panelGlass11.add(Scroll1);

        panelGlass12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Persetujuan Tindakan ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPersetujuan.setToolTipText("Silahkan klik untuk memilih data yang mau diperbaiki/dihapus");
        tbPersetujuan.setName("tbPersetujuan"); // NOI18N
        tbPersetujuan.getTableHeader().setReorderingAllowed(false);
        tbPersetujuan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPersetujuanMouseClicked(evt);
            }
        });
        tbPersetujuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPersetujuanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPersetujuan);

        panelGlass12.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Tanggal :");
        jLabel49.setName("jLabel49"); // NOI18N
        jLabel49.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass10.add(jLabel49);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-11-2026" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-11-2026" }));
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
            if (Sequel.menyimpantf("persetujuan_tindakan_hemodialisis", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 26, new String[]{
                TNoRw.getText(), sttsrwt, TrgRawat.getText(), TnmBerttd.getText(), Tumur.getText(), cmbJenkel.getSelectedItem().toString(),
                Talamat.getText(), TnoBukti.getText(), Tdiagnosis.getText(), Ttujuan.getText(), Talternatif.getText(), Tresiko.getText(), Tprognosis.getText(),
                Tperkiraan.getText(), TketIndonesia.getText(), TketEnglish.getText(), cmbTerhadapInd.getSelectedItem().toString(), cmbTerhadapEng.getSelectedItem().toString(),
                Tlainya.getText(), Valid.SetTgl(TtglPersetujuan.getSelectedItem() + ""), nipDokter, nipSaksi, TnmSaksiKlg.getText(), "", "",
                Sequel.cariIsi("select now()")
            }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Persetujuan Tindakan Kedokteran (Hemodialisis)", "Simpan");
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
        if (tbPersetujuan.getSelectedRow() > -1) {
            if (Sequel.mengedittf("persetujuan_tindakan_hemodialisis", "waktu_simpan=?", "nm_betttd=?, umur=?, jenkel=?, alamat=?, no_bukti_diri=?, "
                    + "diagnosis=?, tujuan_tindakan=?, alternatif_tindakan=?, resiko=?, prognosis=?, perkiraan_biaya=?, ket_hemodialisis_indonesia=?, ket_hemodialisis_english=?, "
                    + "terhadap_ind=?, terhadap_eng=?, ket_lainya=?, tgl_persetujuan=?, nip_dokter=?, nip_saksi_rs=?, nm_saksi_klg=?", 21, new String[]{
                        TnmBerttd.getText(), Tumur.getText(), cmbJenkel.getSelectedItem().toString(),
                        Talamat.getText(), TnoBukti.getText(), Tdiagnosis.getText(), Ttujuan.getText(), Talternatif.getText(), Tresiko.getText(), Tprognosis.getText(),
                        Tperkiraan.getText(), TketIndonesia.getText(), TketEnglish.getText(), cmbTerhadapInd.getSelectedItem().toString(), cmbTerhadapEng.getSelectedItem().toString(),
                        Tlainya.getText(), Valid.SetTgl(TtglPersetujuan.getSelectedItem() + ""), nipDokter, nipSaksi, TnmSaksiKlg.getText(),
                        tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 33).toString()
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Persetujuan Tindakan Kedokteran (Hemodialisis)", "Ganti");
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel...");
            tampil();
            tbPersetujuan.requestFocus();
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

    private void tbPersetujuanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPersetujuanMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPersetujuanMouseClicked

    private void tbPersetujuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPersetujuanKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPersetujuanKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbPersetujuan.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from persetujuan_tindakan_hemodialisis where waktu_simpan=?", 1, new String[]{
                    tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 33).toString()
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
            tbPersetujuan.requestFocus();
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
        if (tbPersetujuan.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("alamat", Talamat.getText());
            
            Valid.MyReport("rptStatusHemodialisis2.jasper", "report", "::[ Status Hemodialisis (Data Status) ]::",
                    "SELECT date(now()) tgl", param);
            
            Valid.MyReport("rptStatusHemodialisis1.jasper", "report", "::[ Status Hemodialisis (Sampul/Cover) ]::",
                    "SELECT date(now()) tgl", param);
            
            emptTeks();
            tampil();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel....");
            tampil();
            tbPersetujuan.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void TketIndonesiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketIndonesiaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TketEnglish.requestFocus();
        }
    }//GEN-LAST:event_TketIndonesiaKeyPressed

    private void TketEnglishKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketEnglishKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbTerhadapInd.requestFocus();
        }
    }//GEN-LAST:event_TketEnglishKeyPressed

    private void TnmBerttdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmBerttdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tumur.requestFocus();
        }
    }//GEN-LAST:event_TnmBerttdKeyPressed

    private void TumurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJenkel.requestFocus();
        }
    }//GEN-LAST:event_TumurKeyPressed

    private void TalamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalamatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnoBukti.requestFocus();
        }
    }//GEN-LAST:event_TalamatKeyPressed

    private void TnoBuktiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoBuktiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdiagnosis.requestFocus();
        }
    }//GEN-LAST:event_TnoBuktiKeyPressed

    private void TdiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttujuan.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosisKeyPressed

    private void TtujuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtujuanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Talternatif.requestFocus();
        }
    }//GEN-LAST:event_TtujuanKeyPressed

    private void TalternatifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalternatifKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tresiko.requestFocus();
        }
    }//GEN-LAST:event_TalternatifKeyPressed

    private void TresikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TresikoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tprognosis.requestFocus();
        }
    }//GEN-LAST:event_TresikoKeyPressed

    private void TprognosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprognosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tperkiraan.requestFocus();
        }
    }//GEN-LAST:event_TprognosisKeyPressed

    private void TperkiraanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TperkiraanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TketIndonesia.requestFocus();
        }
    }//GEN-LAST:event_TperkiraanKeyPressed

    private void TlainyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainyaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglPersetujuan.requestFocus();
        }
    }//GEN-LAST:event_TlainyaKeyPressed

    private void cmbTerhadapIndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTerhadapIndActionPerformed
        Tlainya.setText("");
        if (cmbTerhadapInd.getSelectedIndex() == 0) {
            cmbTerhadapEng.setSelectedIndex(0);
            Tlainya.setEnabled(false);
        } else if (cmbTerhadapInd.getSelectedIndex() == 1) {
            cmbTerhadapEng.setSelectedIndex(1);
            Tlainya.setEnabled(false);
        } else if (cmbTerhadapInd.getSelectedIndex() == 2) {
            cmbTerhadapEng.setSelectedIndex(2);
            Tlainya.setEnabled(false);
        } else if (cmbTerhadapInd.getSelectedIndex() == 3) {
            cmbTerhadapEng.setSelectedIndex(3);
            Tlainya.setEnabled(false);
        } else if (cmbTerhadapInd.getSelectedIndex() == 4) {
            cmbTerhadapEng.setSelectedIndex(4);
            Tlainya.setEnabled(false);
        } else if (cmbTerhadapInd.getSelectedIndex() == 5) {
            cmbTerhadapEng.setSelectedIndex(5);
            Tlainya.setEnabled(false);
        } else if (cmbTerhadapInd.getSelectedIndex() == 6) {
            cmbTerhadapEng.setSelectedIndex(6);
            Tlainya.setEnabled(false);
        } else if (cmbTerhadapInd.getSelectedIndex() == 7) {
            cmbTerhadapEng.setSelectedIndex(7);
            Tlainya.setEnabled(true);
            Tlainya.requestFocus();
        }
    }//GEN-LAST:event_cmbTerhadapIndActionPerformed

    private void BtnPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatActionPerformed
        akses.setform("RMPersetujuanTindakanHemodialisis");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatActionPerformed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        akses.setform("RMPersetujuanTindakanHemodialisis");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void chkSamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSamaActionPerformed
        if (chkSama.isSelected() == true) {
            Talamat.setText(alamatPasien);
        } else {
            Talamat.setText("");
        }
    }//GEN-LAST:event_chkSamaActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPersetujuanTindakanHemodialisis dialog = new RMPersetujuanTindakanHemodialisis(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDokter;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPerawat;
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
    private widget.TextBox Talternatif;
    private widget.TextBox Tdiagnosis;
    private widget.TextArea TketEnglish;
    private widget.TextArea TketIndonesia;
    private widget.TextBox Tlainya;
    private widget.TextBox TnmBerttd;
    private widget.TextBox TnmDokter;
    private widget.TextBox TnmSaksiKlg;
    private widget.TextBox TnmSaksiRS;
    private widget.TextBox TnoBukti;
    private widget.TextBox Tperkiraan;
    private widget.TextBox Tprognosis;
    private widget.TextBox Tresiko;
    private widget.TextBox TrgRawat;
    private widget.Tanggal TtglPersetujuan;
    private widget.TextBox Ttujuan;
    private widget.TextBox Tumur;
    public widget.CekBox chkSama;
    private widget.ComboBox cmbJenkel;
    private widget.ComboBox cmbTerhadapEng;
    private widget.ComboBox cmbTerhadapInd;
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
    private widget.Label jLabel4;
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
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.Table tbPersetujuan;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select pt.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                    + "concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', ',kb.nm_kab) almat, date_format(pt.tgl_persetujuan,'%d-%m-%Y') tglSetuju, "
                    + "pg1.nama nmDokter, pg2.nama nmSaksiRS from persetujuan_tindakan_hemodialisis pt inner join reg_periksa rp on rp.no_rawat=pt.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join kelurahan kl on kl.kd_kel=p.kd_kel "
                    + "inner join kecamatan kc on kc.kd_kec=p.kd_kec inner join kabupaten kb on kb.kd_kab=p.kd_kab "
                    + "inner join pegawai pg1 on pg1.nik=pt.nip_dokter inner join pegawai pg2 on pg2.nik=pt.nip_saksi_rs where "
                    + "pt.tgl_persetujuan between ? and ? and pt.no_rawat like ? or "
                    + "pt.tgl_persetujuan between ? and ? and p.no_rkm_medis like ? or "
                    + "pt.tgl_persetujuan between ? and ? and p.nm_pasien like ? or "
                    + "pt.tgl_persetujuan between ? and ? and pt.nm_betttd like ? or "
                    + "pt.tgl_persetujuan between ? and ? and concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', ',kb.nm_kab) like ? order by pt.tgl_persetujuan desc");
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
                rs = ps.executeQuery();                
                while (rs.next()) {                    
                    tabMode.addRow(new String[]{                        
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglLahir"),
                        rs.getString("almat"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglSetuju"),
                        rs.getString("nmDokter"),
                        rs.getString("nmSaksiRS"),
                        rs.getString("nm_betttd"),
                        rs.getString("ruang_rawat"),
                        rs.getString("nm_betttd"),
                        rs.getString("umur"),
                        rs.getString("jenkel"),
                        rs.getString("alamat"),
                        rs.getString("no_bukti_diri"),
                        rs.getString("diagnosis"),
                        rs.getString("tujuan_tindakan"),
                        rs.getString("alternatif_tindakan"),
                        rs.getString("resiko"),
                        rs.getString("prognosis"),
                        rs.getString("perkiraan_biaya"),
                        rs.getString("ket_hemodialisis_indonesia"),
                        rs.getString("ket_hemodialisis_english"),
                        rs.getString("terhadap_ind"),
                        rs.getString("terhadap_eng"),
                        rs.getString("ket_lainya"),
                        rs.getString("tgl_persetujuan"),
                        rs.getString("nip_dokter"),
                        rs.getString("nip_saksi_rs"),
                        rs.getString("nm_saksi_klg"),
                        rs.getString("id_file_nm_berttd"),
                        rs.getString("id_file_nm_saksi_klg"),
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
        TnmBerttd.setText("");
        Tumur.setText("");
        cmbJenkel.setSelectedIndex(0);
        Talamat.setText("");
        TnoBukti.setText("");        
        Tdiagnosis.setText("");
        Ttujuan.setText("");
        Talternatif.setText("");
        Tresiko.setText("");
        Tprognosis.setText("");
        Tperkiraan.setText("");
        TketIndonesia.setText("");
        TketEnglish.setText("");
        cmbTerhadapInd.setSelectedIndex(0);
        Tlainya.setText("");
        cmbTerhadapEng.setSelectedIndex(0);        
        TtglPersetujuan.setDate(new Date());
        TrgRawat.setText("");
        nipDokter = "-";
        TnmDokter.setText("");
        nipSaksi = "-";
        TnmSaksiRS.setText("");
        TnmSaksiKlg.setText("");
        chkSama.setSelected(false);
        TnmBerttd.requestFocus();
    }

    private void getData() {
        sttsrwt = "";
        nipSaksi = "";
        nipDokter = "";
        idFileSaksiKlg = "";
        idFileBerttd = "";
        tglReg = "";
        alamatPasien = ""; 
        
        if (tbPersetujuan.getSelectedRow() != -1) {
            TNoRw.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 0).toString());
            isPasien();
            TnmBerttd.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 11).toString());
            Tumur.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 12).toString());
            cmbJenkel.setSelectedItem(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 13).toString());
            Talamat.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 14).toString());
            TnoBukti.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 15).toString());
            Tdiagnosis.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 16).toString());
            Ttujuan.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 17).toString());
            Talternatif.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 18).toString());
            Tresiko.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 19).toString());
            Tprognosis.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 20).toString());
            Tperkiraan.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 21).toString());
            TketIndonesia.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 22).toString());
            TketEnglish.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 23).toString());
            cmbTerhadapInd.setSelectedItem(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 24).toString());
            cmbTerhadapEng.setSelectedItem(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 25).toString());
            Tlainya.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 26).toString());
            Valid.SetTgl(TtglPersetujuan, tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 27).toString());
            TrgRawat.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 10).toString());
            nipDokter = tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 28).toString();
            TnmDokter.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 7).toString()); 
            nipSaksi = tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 29).toString();
            TnmSaksiRS.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 8).toString());
            TnmSaksiKlg.setText(tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 30).toString());
            idFileBerttd = tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 31).toString();
            idFileSaksiKlg = tbPersetujuan.getValueAt(tbPersetujuan.getSelectedRow(), 32).toString();

            if (cmbTerhadapInd.getSelectedIndex() == 7) {
                Tlainya.setEnabled(true);
            } else {
                Tlainya.setEnabled(false);
            }
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.gethemodialisa());
        BtnGanti.setEnabled(akses.gethemodialisa());
        BtnHapus.setEnabled(akses.gethemodialisa());
    }
    
    public void setData(String norw, String sttsRawat, String unit) {
        TNoRw.setText(norw);
        sttsrwt = sttsRawat;
        TrgRawat.setText(unit);
        isPasien();
        Valid.SetTgl(DTPCari1, tglReg);
        TCari.setText(norw);
    }
    
    private void isPasien() {
        try {
            ps1 = koneksi.prepareStatement("select p.*, concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', ',kb.nm_kab) almat, "
                    + "date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, rp.tgl_registrasi from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join kelurahan kl on kl.kd_kel=p.kd_kel "
                    + "inner join kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "inner join kabupaten kb on kb.kd_kab=p.kd_kab where rp.no_rawat='" + TNoRw.getText() + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    TNoRM.setText(rs1.getString("no_rkm_medis"));
                    TPasien.setText(rs1.getString("nm_pasien"));
                    TnmBerttd.setText(rs1.getString("namakeluarga"));
                    alamatPasien = rs1.getString("almat");
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
    
    public void awalData() {
        tampil();
    }
}
