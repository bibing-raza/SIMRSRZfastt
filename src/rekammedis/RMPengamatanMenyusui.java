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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import kepegawaian.DlgCariPetugas;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgKabupaten;
import simrskhanza.DlgKecamatan;
import simrskhanza.DlgKelurahan;

/**
 *
 * @author perpustakaan
 */
public final class RMPengamatanMenyusui extends javax.swing.JDialog {
    private DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;    
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String nip = "", ibuSehat = "", ibuRileks = "", ibuTerlihat = "", ibuSakit = "", ibuTegang = "", ibuTidak = "", bayiSehat = "", bayiTenang = "",
            bayiLapar = "", bayiMengantuk = "", bayiGelisah = "", bayiMencari = "", payudaraSehat = "", payudaraNyaman = "", payudaraDitopang = "",
            payudaraPutingKeluar = "", payudaraMerah = "", payudaraNyeri = "", payudaraAerola = "", payudaraPutingDatar = "", posisiKepala = "",
            posisiDipegang = "", posisiSeluruh = "", posisiHidungBerhadapan = "", posisiLeher = "", posisiTakDipegang = "", posisiHanya = "", posisiBibirBawah = "",
            pelekatanTampak = "", pelekatanTerbuka = "", pelekatanTerputarKeluar = "", pelekatanMenempel = "", pelekatanLebih = "", pelekatanTakTerbuka = "",
            pelekatanTerputarKedalam = "", pelekatanTidakMenempel = "", mengisapLambat = "", mengisapPipiMembulat = "", mengisapMelepaskanSelesai = "",
            mengisapReflex = "", mengisapDangkal = "", mengisapPipiTertarik = "", mengisapMelepaskanPayudara = "", mengisapOksitosin = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPengamatanMenyusui(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Ruang Perawatan", "IMD", "Lama IMD", "Alasan", "ASI Eksklusif", "Lama Menyusui", "Tanggal", "Jam", "Nama Perawat",
            "ibu_sehat", "ibu_rileks", "ibu_terlihat", "ibu_sakit", "ibu_tegang", "ibu_tidak", "bayi_sehat", "bayi_tenang", "bayi_lapar", 
            "bayi_mengantuk", "bayi_gelisah", "bayi_mencari", "payudara_sehat", "payudara_nyaman", "payudara_ditopang", "payudara_puting_keluar", 
            "payudara_merah", "payudara_nyeri", "payudara_aerola", "payudara_puting_datar", "posisi_kepala", "posisi_dipegang", "posisi_seluruh", 
            "posisi_hidung_berhadapan", "posisi_leher", "posisi_tak_dipegang", "posisi_hanya", "posisi_bibir_bawah", "pelekatan_tampak", "pelekatan_terbuka", 
            "pelekatan_terputar_keluar", "pelekatan_menempel", "pelekatan_lebih", "pelekatan_tak_terbuka", "pelekatan_terputar_kedalam", "pelekatan_tidak_menempel", 
            "mengisap_lambat", "mengisap_pipi_membulat", "mengisap_melepaskan_selesai", "mengisap_reflex", "mengisap_dangkal", "mengisap_pipi_tertarik", 
            "mengisap_melepaskan_payudara", "mengisap_oksitosin", "lama_menyusui", "catatan", "tanggal", "jam", "nip_perawat", "waktu_simpan", "lama_menyusui"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbPengamatan.setModel(tabMode);
        tbPengamatan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPengamatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 65; i++) {
            TableColumn column = tbPengamatan.getColumnModel().getColumn(i);
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
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setPreferredWidth(60);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(80);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(70);
            } else if (i == 12) {
                column.setPreferredWidth(65);
            } else if (i == 13) {
                column.setPreferredWidth(250);
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
            } else if (i == 53) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 54) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 55) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 56) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 57) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 58) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 59) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 60) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 61) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 62) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 63) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 64) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPengamatan.setDefaultRenderer(Object.class, new WarnaTable());
        
        TlamaImd.setDocument(new batasInput((int) 140).getKata(TlamaImd));
        Talasan.setDocument(new batasInput((int) 255).getKata(Talasan));
        TlamaWkt.setDocument(new batasInput((int) 7).getKata(TlamaWkt));
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
                    nip = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                    TnmPerawat.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
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
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass10 = new widget.panelisi();
        FormTindakan = new widget.InternalFrame();
        ScrollTriase1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel60 = new widget.Label();
        TnmPerawat = new widget.TextBox();
        BtnPerawat = new widget.Button();
        jLabel5 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel8 = new widget.Label();
        cmbImd = new widget.ComboBox();
        jLabel9 = new widget.Label();
        TlamaImd = new widget.TextBox();
        jLabel10 = new widget.Label();
        Talasan = new widget.TextBox();
        jLabel11 = new widget.Label();
        cmbAsi = new widget.ComboBox();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        chkIbuSehat = new widget.CekBox();
        chkIbuRileks = new widget.CekBox();
        chkIbuTerlihat = new widget.CekBox();
        chkIbuSakit = new widget.CekBox();
        chkIbuTegang = new widget.CekBox();
        chkIbuTidak = new widget.CekBox();
        jLabel15 = new widget.Label();
        chkBayiSehat = new widget.CekBox();
        chkBayiTenang = new widget.CekBox();
        chkBayiLapar = new widget.CekBox();
        chkBayiMengantuk = new widget.CekBox();
        chkBayiGelisah = new widget.CekBox();
        chkBayiMencari = new widget.CekBox();
        jLabel16 = new widget.Label();
        chkPayudaraSehat = new widget.CekBox();
        chkPayudaraMerah = new widget.CekBox();
        chkPayudaraNyaman = new widget.CekBox();
        chkPayudaraDitopang = new widget.CekBox();
        chkPayudaraPutingKeluar = new widget.CekBox();
        chkPayudaraNyeri = new widget.CekBox();
        chkPayudaraAerola = new widget.CekBox();
        chkPayudaraPutingDatar = new widget.CekBox();
        jLabel17 = new widget.Label();
        chkPosisiKepala = new widget.CekBox();
        chkPosisiDipegang = new widget.CekBox();
        chkPosisiSeluruh = new widget.CekBox();
        chkPosisiHidung = new widget.CekBox();
        chkPosisiLeher = new widget.CekBox();
        chkPosisiTakDipegang = new widget.CekBox();
        chkPosisiHanya = new widget.CekBox();
        chkPosisiBibir = new widget.CekBox();
        jLabel18 = new widget.Label();
        chkPelekatanTampak = new widget.CekBox();
        chkPelekatanTerbuka = new widget.CekBox();
        chkPelekatanTerputarKeluar = new widget.CekBox();
        chkPelekatanMenempel = new widget.CekBox();
        chkPelekatanLebih = new widget.CekBox();
        chkPelekatanTakTerbuka = new widget.CekBox();
        chkPelekatanTerputarKedalam = new widget.CekBox();
        chkPelekatanTdkMenempel = new widget.CekBox();
        chkMengisapLambat = new widget.CekBox();
        jLabel20 = new widget.Label();
        chkMengisapPipiMembulat = new widget.CekBox();
        chkMengisapMelepaskanSelesai = new widget.CekBox();
        chkMengisapReflex = new widget.CekBox();
        chkMengisapDangkal = new widget.CekBox();
        chkMengisapPipiTertarik = new widget.CekBox();
        chkMengisapMelepaskanPayudara = new widget.CekBox();
        chkMengisapOksitosin = new widget.CekBox();
        jLabel22 = new widget.Label();
        TlamaWkt = new widget.TextBox();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        Tcatatan = new widget.TextArea();
        jLabel25 = new widget.Label();
        Ttgl = new widget.Tanggal();
        jLabel96 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        internalFrame4 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPengamatan = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Lembaran Bantuan Pengamatan Menyusui ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass8.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass8.add(LCount);

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass10.setLayout(new java.awt.GridLayout(1, 2));

        FormTindakan.setBorder(null);
        FormTindakan.setName("FormTindakan"); // NOI18N
        FormTindakan.setLayout(new java.awt.BorderLayout(1, 1));

        ScrollTriase1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        ScrollTriase1.setName("ScrollTriase1"); // NOI18N
        ScrollTriase1.setOpaque(true);
        ScrollTriase1.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Input Pengamatan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        FormInput.setToolTipText("");
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1020));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 20, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 20, 122, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(315, 20, 407, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(240, 20, 70, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Nama Perawat :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(0, 982, 130, 23);

        TnmPerawat.setEditable(false);
        TnmPerawat.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawat.setName("TnmPerawat"); // NOI18N
        FormInput.add(TnmPerawat);
        TnmPerawat.setBounds(134, 982, 560, 23);

        BtnPerawat.setForeground(new java.awt.Color(0, 0, 0));
        BtnPerawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPerawat.setMnemonic('4');
        BtnPerawat.setToolTipText("ALt+4");
        BtnPerawat.setName("BtnPerawat"); // NOI18N
        BtnPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerawatActionPerformed(evt);
            }
        });
        FormInput.add(BtnPerawat);
        BtnPerawat.setBounds(695, 982, 28, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Ruang Rawat :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 48, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setBackground(new java.awt.Color(245, 250, 240));
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(114, 48, 608, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("IMD :");
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 76, 110, 23);

        cmbImd.setForeground(new java.awt.Color(0, 0, 0));
        cmbImd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbImd.setName("cmbImd"); // NOI18N
        cmbImd.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbImd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbImdActionPerformed(evt);
            }
        });
        FormInput.add(cmbImd);
        cmbImd.setBounds(114, 76, 60, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Lama IMD : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(180, 76, 70, 23);

        TlamaImd.setBackground(new java.awt.Color(245, 250, 240));
        TlamaImd.setForeground(new java.awt.Color(0, 0, 0));
        TlamaImd.setName("TlamaImd"); // NOI18N
        TlamaImd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlamaImdKeyPressed(evt);
            }
        });
        FormInput.add(TlamaImd);
        TlamaImd.setBounds(252, 76, 470, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Alasan :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 104, 110, 23);

        Talasan.setBackground(new java.awt.Color(245, 250, 240));
        Talasan.setForeground(new java.awt.Color(0, 0, 0));
        Talasan.setName("Talasan"); // NOI18N
        Talasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalasanKeyPressed(evt);
            }
        });
        FormInput.add(Talasan);
        Talasan.setBounds(114, 104, 608, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("ASI Eksklusif :");
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 132, 110, 23);

        cmbAsi.setForeground(new java.awt.Color(0, 0, 0));
        cmbAsi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbAsi.setName("cmbAsi"); // NOI18N
        cmbAsi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbAsi);
        cmbAsi.setBounds(114, 132, 60, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Tanda menyusui berjalan baik :");
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(110, 160, 230, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Tanda mungkin ditemukan kesukaran :");
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(470, 160, 300, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("UMUM IBU :");
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 188, 130, 23);

        chkIbuSehat.setBackground(new java.awt.Color(255, 255, 250));
        chkIbuSehat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIbuSehat.setForeground(new java.awt.Color(0, 0, 0));
        chkIbuSehat.setText("Ibu Tampak Sehat");
        chkIbuSehat.setBorderPainted(true);
        chkIbuSehat.setBorderPaintedFlat(true);
        chkIbuSehat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIbuSehat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIbuSehat.setName("chkIbuSehat"); // NOI18N
        chkIbuSehat.setOpaque(false);
        chkIbuSehat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIbuSehat);
        chkIbuSehat.setBounds(134, 188, 210, 23);

        chkIbuRileks.setBackground(new java.awt.Color(255, 255, 250));
        chkIbuRileks.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIbuRileks.setForeground(new java.awt.Color(0, 0, 0));
        chkIbuRileks.setText("Ibu Tampak Rileks Dan Nyaman");
        chkIbuRileks.setBorderPainted(true);
        chkIbuRileks.setBorderPaintedFlat(true);
        chkIbuRileks.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIbuRileks.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIbuRileks.setName("chkIbuRileks"); // NOI18N
        chkIbuRileks.setOpaque(false);
        chkIbuRileks.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIbuRileks);
        chkIbuRileks.setBounds(134, 216, 210, 23);

        chkIbuTerlihat.setBackground(new java.awt.Color(255, 255, 250));
        chkIbuTerlihat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIbuTerlihat.setForeground(new java.awt.Color(0, 0, 0));
        chkIbuTerlihat.setText("Terlihat Tanda Bonding Ibu - Bayi");
        chkIbuTerlihat.setBorderPainted(true);
        chkIbuTerlihat.setBorderPaintedFlat(true);
        chkIbuTerlihat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIbuTerlihat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIbuTerlihat.setName("chkIbuTerlihat"); // NOI18N
        chkIbuTerlihat.setOpaque(false);
        chkIbuTerlihat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIbuTerlihat);
        chkIbuTerlihat.setBounds(134, 244, 210, 23);

        chkIbuSakit.setBackground(new java.awt.Color(255, 255, 250));
        chkIbuSakit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIbuSakit.setForeground(new java.awt.Color(0, 0, 0));
        chkIbuSakit.setText("Ibu Tampak Sakit Atau Depresi");
        chkIbuSakit.setBorderPainted(true);
        chkIbuSakit.setBorderPaintedFlat(true);
        chkIbuSakit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIbuSakit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIbuSakit.setName("chkIbuSakit"); // NOI18N
        chkIbuSakit.setOpaque(false);
        chkIbuSakit.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIbuSakit);
        chkIbuSakit.setBounds(470, 188, 220, 23);

        chkIbuTegang.setBackground(new java.awt.Color(255, 255, 250));
        chkIbuTegang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIbuTegang.setForeground(new java.awt.Color(0, 0, 0));
        chkIbuTegang.setText("Ibu Tampak Tegang Dan Tak Nyaman");
        chkIbuTegang.setBorderPainted(true);
        chkIbuTegang.setBorderPaintedFlat(true);
        chkIbuTegang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIbuTegang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIbuTegang.setName("chkIbuTegang"); // NOI18N
        chkIbuTegang.setOpaque(false);
        chkIbuTegang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIbuTegang);
        chkIbuTegang.setBounds(470, 216, 220, 23);

        chkIbuTidak.setBackground(new java.awt.Color(255, 255, 250));
        chkIbuTidak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIbuTidak.setForeground(new java.awt.Color(0, 0, 0));
        chkIbuTidak.setText("Tidak Ada Kontak Mata Ibu - Bayi");
        chkIbuTidak.setBorderPainted(true);
        chkIbuTidak.setBorderPaintedFlat(true);
        chkIbuTidak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIbuTidak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIbuTidak.setName("chkIbuTidak"); // NOI18N
        chkIbuTidak.setOpaque(false);
        chkIbuTidak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIbuTidak);
        chkIbuTidak.setBounds(470, 244, 220, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("UMUM BAYI :");
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 277, 130, 23);

        chkBayiSehat.setBackground(new java.awt.Color(255, 255, 250));
        chkBayiSehat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBayiSehat.setForeground(new java.awt.Color(0, 0, 0));
        chkBayiSehat.setText("Bayi Tampak Sehat");
        chkBayiSehat.setBorderPainted(true);
        chkBayiSehat.setBorderPaintedFlat(true);
        chkBayiSehat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBayiSehat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBayiSehat.setName("chkBayiSehat"); // NOI18N
        chkBayiSehat.setOpaque(false);
        chkBayiSehat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBayiSehat);
        chkBayiSehat.setBounds(134, 277, 210, 23);

        chkBayiTenang.setBackground(new java.awt.Color(255, 255, 250));
        chkBayiTenang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBayiTenang.setForeground(new java.awt.Color(0, 0, 0));
        chkBayiTenang.setText("Bayi Tampak Tenang Dan Rileks");
        chkBayiTenang.setBorderPainted(true);
        chkBayiTenang.setBorderPaintedFlat(true);
        chkBayiTenang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBayiTenang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBayiTenang.setName("chkBayiTenang"); // NOI18N
        chkBayiTenang.setOpaque(false);
        chkBayiTenang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBayiTenang);
        chkBayiTenang.setBounds(134, 305, 210, 23);

        chkBayiLapar.setBackground(new java.awt.Color(255, 255, 250));
        chkBayiLapar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBayiLapar.setForeground(new java.awt.Color(0, 0, 0));
        chkBayiLapar.setText("Bayi Mencari Payudara (Rooting) Bila Lapar");
        chkBayiLapar.setBorderPainted(true);
        chkBayiLapar.setBorderPaintedFlat(true);
        chkBayiLapar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBayiLapar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBayiLapar.setName("chkBayiLapar"); // NOI18N
        chkBayiLapar.setOpaque(false);
        chkBayiLapar.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBayiLapar);
        chkBayiLapar.setBounds(134, 333, 240, 23);

        chkBayiMengantuk.setBackground(new java.awt.Color(255, 255, 250));
        chkBayiMengantuk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBayiMengantuk.setForeground(new java.awt.Color(0, 0, 0));
        chkBayiMengantuk.setText("Bayi Tampak Mengantuk");
        chkBayiMengantuk.setBorderPainted(true);
        chkBayiMengantuk.setBorderPaintedFlat(true);
        chkBayiMengantuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBayiMengantuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBayiMengantuk.setName("chkBayiMengantuk"); // NOI18N
        chkBayiMengantuk.setOpaque(false);
        chkBayiMengantuk.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBayiMengantuk);
        chkBayiMengantuk.setBounds(470, 277, 210, 23);

        chkBayiGelisah.setBackground(new java.awt.Color(255, 255, 250));
        chkBayiGelisah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBayiGelisah.setForeground(new java.awt.Color(0, 0, 0));
        chkBayiGelisah.setText("Bayi Tampak Gelisah Atau Menangis");
        chkBayiGelisah.setBorderPainted(true);
        chkBayiGelisah.setBorderPaintedFlat(true);
        chkBayiGelisah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBayiGelisah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBayiGelisah.setName("chkBayiGelisah"); // NOI18N
        chkBayiGelisah.setOpaque(false);
        chkBayiGelisah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBayiGelisah);
        chkBayiGelisah.setBounds(470, 305, 210, 23);

        chkBayiMencari.setBackground(new java.awt.Color(255, 255, 250));
        chkBayiMencari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBayiMencari.setForeground(new java.awt.Color(0, 0, 0));
        chkBayiMencari.setText("Bayi Tidak Mencari Payudara (Rooting)");
        chkBayiMencari.setBorderPainted(true);
        chkBayiMencari.setBorderPaintedFlat(true);
        chkBayiMencari.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBayiMencari.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBayiMencari.setName("chkBayiMencari"); // NOI18N
        chkBayiMencari.setOpaque(false);
        chkBayiMencari.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBayiMencari);
        chkBayiMencari.setBounds(470, 333, 240, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("PAYUDARA :");
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 366, 130, 23);

        chkPayudaraSehat.setBackground(new java.awt.Color(255, 255, 250));
        chkPayudaraSehat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPayudaraSehat.setForeground(new java.awt.Color(0, 0, 0));
        chkPayudaraSehat.setText("Payudara Tampak Sehat");
        chkPayudaraSehat.setBorderPainted(true);
        chkPayudaraSehat.setBorderPaintedFlat(true);
        chkPayudaraSehat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPayudaraSehat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPayudaraSehat.setName("chkPayudaraSehat"); // NOI18N
        chkPayudaraSehat.setOpaque(false);
        chkPayudaraSehat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPayudaraSehat);
        chkPayudaraSehat.setBounds(134, 366, 210, 23);

        chkPayudaraMerah.setBackground(new java.awt.Color(255, 255, 250));
        chkPayudaraMerah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPayudaraMerah.setForeground(new java.awt.Color(0, 0, 0));
        chkPayudaraMerah.setText("Payudara Tampak Merah, Bengkak, Lecet");
        chkPayudaraMerah.setBorderPainted(true);
        chkPayudaraMerah.setBorderPaintedFlat(true);
        chkPayudaraMerah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPayudaraMerah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPayudaraMerah.setName("chkPayudaraMerah"); // NOI18N
        chkPayudaraMerah.setOpaque(false);
        chkPayudaraMerah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPayudaraMerah);
        chkPayudaraMerah.setBounds(470, 366, 320, 23);

        chkPayudaraNyaman.setBackground(new java.awt.Color(255, 255, 250));
        chkPayudaraNyaman.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPayudaraNyaman.setForeground(new java.awt.Color(0, 0, 0));
        chkPayudaraNyaman.setText("Ibu Merasa Nyaman Atau Tidak Nyeri");
        chkPayudaraNyaman.setBorderPainted(true);
        chkPayudaraNyaman.setBorderPaintedFlat(true);
        chkPayudaraNyaman.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPayudaraNyaman.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPayudaraNyaman.setName("chkPayudaraNyaman"); // NOI18N
        chkPayudaraNyaman.setOpaque(false);
        chkPayudaraNyaman.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPayudaraNyaman);
        chkPayudaraNyaman.setBounds(134, 394, 210, 23);

        chkPayudaraDitopang.setBackground(new java.awt.Color(255, 255, 250));
        chkPayudaraDitopang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPayudaraDitopang.setForeground(new java.awt.Color(0, 0, 0));
        chkPayudaraDitopang.setText("Payudara Ditopang Dg. Baik Oleh Jari-Jari Yg. Jauh Dari Puting");
        chkPayudaraDitopang.setBorderPainted(true);
        chkPayudaraDitopang.setBorderPaintedFlat(true);
        chkPayudaraDitopang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPayudaraDitopang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPayudaraDitopang.setName("chkPayudaraDitopang"); // NOI18N
        chkPayudaraDitopang.setOpaque(false);
        chkPayudaraDitopang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPayudaraDitopang);
        chkPayudaraDitopang.setBounds(134, 422, 330, 23);

        chkPayudaraPutingKeluar.setBackground(new java.awt.Color(255, 255, 250));
        chkPayudaraPutingKeluar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPayudaraPutingKeluar.setForeground(new java.awt.Color(0, 0, 0));
        chkPayudaraPutingKeluar.setText("Puting Keluar Dan Lentur");
        chkPayudaraPutingKeluar.setBorderPainted(true);
        chkPayudaraPutingKeluar.setBorderPaintedFlat(true);
        chkPayudaraPutingKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPayudaraPutingKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPayudaraPutingKeluar.setName("chkPayudaraPutingKeluar"); // NOI18N
        chkPayudaraPutingKeluar.setOpaque(false);
        chkPayudaraPutingKeluar.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPayudaraPutingKeluar);
        chkPayudaraPutingKeluar.setBounds(134, 450, 210, 23);

        chkPayudaraNyeri.setBackground(new java.awt.Color(255, 255, 250));
        chkPayudaraNyeri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPayudaraNyeri.setForeground(new java.awt.Color(0, 0, 0));
        chkPayudaraNyeri.setText("Ibu Merasa Payudara/Puting Nyeri");
        chkPayudaraNyeri.setBorderPainted(true);
        chkPayudaraNyeri.setBorderPaintedFlat(true);
        chkPayudaraNyeri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPayudaraNyeri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPayudaraNyeri.setName("chkPayudaraNyeri"); // NOI18N
        chkPayudaraNyeri.setOpaque(false);
        chkPayudaraNyeri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPayudaraNyeri);
        chkPayudaraNyeri.setBounds(470, 394, 210, 23);

        chkPayudaraAerola.setBackground(new java.awt.Color(255, 255, 250));
        chkPayudaraAerola.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPayudaraAerola.setForeground(new java.awt.Color(0, 0, 0));
        chkPayudaraAerola.setText("Payudara Ditopang Dengan Jari-Jari Di Aerola");
        chkPayudaraAerola.setBorderPainted(true);
        chkPayudaraAerola.setBorderPaintedFlat(true);
        chkPayudaraAerola.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPayudaraAerola.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPayudaraAerola.setName("chkPayudaraAerola"); // NOI18N
        chkPayudaraAerola.setOpaque(false);
        chkPayudaraAerola.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPayudaraAerola);
        chkPayudaraAerola.setBounds(470, 422, 260, 23);

        chkPayudaraPutingDatar.setBackground(new java.awt.Color(255, 255, 250));
        chkPayudaraPutingDatar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPayudaraPutingDatar.setForeground(new java.awt.Color(0, 0, 0));
        chkPayudaraPutingDatar.setText("Puting Datar/Terbenam, Besar/Panjang");
        chkPayudaraPutingDatar.setBorderPainted(true);
        chkPayudaraPutingDatar.setBorderPaintedFlat(true);
        chkPayudaraPutingDatar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPayudaraPutingDatar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPayudaraPutingDatar.setName("chkPayudaraPutingDatar"); // NOI18N
        chkPayudaraPutingDatar.setOpaque(false);
        chkPayudaraPutingDatar.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPayudaraPutingDatar);
        chkPayudaraPutingDatar.setBounds(470, 450, 240, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("POSISI BAYI :");
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 483, 130, 23);

        chkPosisiKepala.setBackground(new java.awt.Color(255, 255, 250));
        chkPosisiKepala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPosisiKepala.setForeground(new java.awt.Color(0, 0, 0));
        chkPosisiKepala.setText("Kepala Dan Badan Bayi Dalam Garis Lurus");
        chkPosisiKepala.setBorderPainted(true);
        chkPosisiKepala.setBorderPaintedFlat(true);
        chkPosisiKepala.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPosisiKepala.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPosisiKepala.setName("chkPosisiKepala"); // NOI18N
        chkPosisiKepala.setOpaque(false);
        chkPosisiKepala.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPosisiKepala);
        chkPosisiKepala.setBounds(134, 483, 250, 23);

        chkPosisiDipegang.setBackground(new java.awt.Color(255, 255, 250));
        chkPosisiDipegang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPosisiDipegang.setForeground(new java.awt.Color(0, 0, 0));
        chkPosisiDipegang.setText("Bayi Dipegang Dekat Badan Ibu");
        chkPosisiDipegang.setBorderPainted(true);
        chkPosisiDipegang.setBorderPaintedFlat(true);
        chkPosisiDipegang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPosisiDipegang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPosisiDipegang.setName("chkPosisiDipegang"); // NOI18N
        chkPosisiDipegang.setOpaque(false);
        chkPosisiDipegang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPosisiDipegang);
        chkPosisiDipegang.setBounds(134, 511, 250, 23);

        chkPosisiSeluruh.setBackground(new java.awt.Color(255, 255, 250));
        chkPosisiSeluruh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPosisiSeluruh.setForeground(new java.awt.Color(0, 0, 0));
        chkPosisiSeluruh.setText("Seluruh Badan Bayi Ditopang");
        chkPosisiSeluruh.setBorderPainted(true);
        chkPosisiSeluruh.setBorderPaintedFlat(true);
        chkPosisiSeluruh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPosisiSeluruh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPosisiSeluruh.setName("chkPosisiSeluruh"); // NOI18N
        chkPosisiSeluruh.setOpaque(false);
        chkPosisiSeluruh.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPosisiSeluruh);
        chkPosisiSeluruh.setBounds(134, 539, 250, 23);

        chkPosisiHidung.setBackground(new java.awt.Color(255, 255, 250));
        chkPosisiHidung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPosisiHidung.setForeground(new java.awt.Color(0, 0, 0));
        chkPosisiHidung.setText("Bayi Mendekat Ke Payudara, Hidung Berhadapan Dg. Puting");
        chkPosisiHidung.setBorderPainted(true);
        chkPosisiHidung.setBorderPaintedFlat(true);
        chkPosisiHidung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPosisiHidung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPosisiHidung.setName("chkPosisiHidung"); // NOI18N
        chkPosisiHidung.setOpaque(false);
        chkPosisiHidung.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPosisiHidung);
        chkPosisiHidung.setBounds(134, 567, 320, 23);

        chkPosisiLeher.setBackground(new java.awt.Color(255, 255, 250));
        chkPosisiLeher.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPosisiLeher.setForeground(new java.awt.Color(0, 0, 0));
        chkPosisiLeher.setText("Leher Dan Kepala Bayi Berputar");
        chkPosisiLeher.setBorderPainted(true);
        chkPosisiLeher.setBorderPaintedFlat(true);
        chkPosisiLeher.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPosisiLeher.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPosisiLeher.setName("chkPosisiLeher"); // NOI18N
        chkPosisiLeher.setOpaque(false);
        chkPosisiLeher.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPosisiLeher);
        chkPosisiLeher.setBounds(470, 483, 250, 23);

        chkPosisiTakDipegang.setBackground(new java.awt.Color(255, 255, 250));
        chkPosisiTakDipegang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPosisiTakDipegang.setForeground(new java.awt.Color(0, 0, 0));
        chkPosisiTakDipegang.setText("Bayi Tak Dipegang Dekat Badan Ibu");
        chkPosisiTakDipegang.setBorderPainted(true);
        chkPosisiTakDipegang.setBorderPaintedFlat(true);
        chkPosisiTakDipegang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPosisiTakDipegang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPosisiTakDipegang.setName("chkPosisiTakDipegang"); // NOI18N
        chkPosisiTakDipegang.setOpaque(false);
        chkPosisiTakDipegang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPosisiTakDipegang);
        chkPosisiTakDipegang.setBounds(470, 511, 250, 23);

        chkPosisiHanya.setBackground(new java.awt.Color(255, 255, 250));
        chkPosisiHanya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPosisiHanya.setForeground(new java.awt.Color(0, 0, 0));
        chkPosisiHanya.setText("Hanya Leher Dan Kepala Bayi Ditopang");
        chkPosisiHanya.setBorderPainted(true);
        chkPosisiHanya.setBorderPaintedFlat(true);
        chkPosisiHanya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPosisiHanya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPosisiHanya.setName("chkPosisiHanya"); // NOI18N
        chkPosisiHanya.setOpaque(false);
        chkPosisiHanya.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPosisiHanya);
        chkPosisiHanya.setBounds(470, 539, 250, 23);

        chkPosisiBibir.setBackground(new java.awt.Color(255, 255, 250));
        chkPosisiBibir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPosisiBibir.setForeground(new java.awt.Color(0, 0, 0));
        chkPosisiBibir.setText("Bayi Mendekat Payudara, Bibir Bawah Berhadapan Dg. Puting");
        chkPosisiBibir.setBorderPainted(true);
        chkPosisiBibir.setBorderPaintedFlat(true);
        chkPosisiBibir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPosisiBibir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPosisiBibir.setName("chkPosisiBibir"); // NOI18N
        chkPosisiBibir.setOpaque(false);
        chkPosisiBibir.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPosisiBibir);
        chkPosisiBibir.setBounds(470, 567, 330, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("PELEKATAN BAYI :");
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 600, 130, 23);

        chkPelekatanTampak.setBackground(new java.awt.Color(255, 255, 250));
        chkPelekatanTampak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPelekatanTampak.setForeground(new java.awt.Color(0, 0, 0));
        chkPelekatanTampak.setText("Tampak Lebih Banyak Aerola Diatas Bibir");
        chkPelekatanTampak.setBorderPainted(true);
        chkPelekatanTampak.setBorderPaintedFlat(true);
        chkPelekatanTampak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPelekatanTampak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPelekatanTampak.setName("chkPelekatanTampak"); // NOI18N
        chkPelekatanTampak.setOpaque(false);
        chkPelekatanTampak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPelekatanTampak);
        chkPelekatanTampak.setBounds(134, 600, 250, 23);

        chkPelekatanTerbuka.setBackground(new java.awt.Color(255, 255, 250));
        chkPelekatanTerbuka.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPelekatanTerbuka.setForeground(new java.awt.Color(0, 0, 0));
        chkPelekatanTerbuka.setText("Mulut Bayi Terbuka Lebar");
        chkPelekatanTerbuka.setBorderPainted(true);
        chkPelekatanTerbuka.setBorderPaintedFlat(true);
        chkPelekatanTerbuka.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPelekatanTerbuka.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPelekatanTerbuka.setName("chkPelekatanTerbuka"); // NOI18N
        chkPelekatanTerbuka.setOpaque(false);
        chkPelekatanTerbuka.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPelekatanTerbuka);
        chkPelekatanTerbuka.setBounds(134, 628, 250, 23);

        chkPelekatanTerputarKeluar.setBackground(new java.awt.Color(255, 255, 250));
        chkPelekatanTerputarKeluar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPelekatanTerputarKeluar.setForeground(new java.awt.Color(0, 0, 0));
        chkPelekatanTerputarKeluar.setText("Bibir Bawah Terputar Keluar");
        chkPelekatanTerputarKeluar.setBorderPainted(true);
        chkPelekatanTerputarKeluar.setBorderPaintedFlat(true);
        chkPelekatanTerputarKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPelekatanTerputarKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPelekatanTerputarKeluar.setName("chkPelekatanTerputarKeluar"); // NOI18N
        chkPelekatanTerputarKeluar.setOpaque(false);
        chkPelekatanTerputarKeluar.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPelekatanTerputarKeluar);
        chkPelekatanTerputarKeluar.setBounds(134, 656, 250, 23);

        chkPelekatanMenempel.setBackground(new java.awt.Color(255, 255, 250));
        chkPelekatanMenempel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPelekatanMenempel.setForeground(new java.awt.Color(0, 0, 0));
        chkPelekatanMenempel.setText("Dagu Bayi Menempel Pada Payudara");
        chkPelekatanMenempel.setBorderPainted(true);
        chkPelekatanMenempel.setBorderPaintedFlat(true);
        chkPelekatanMenempel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPelekatanMenempel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPelekatanMenempel.setName("chkPelekatanMenempel"); // NOI18N
        chkPelekatanMenempel.setOpaque(false);
        chkPelekatanMenempel.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPelekatanMenempel);
        chkPelekatanMenempel.setBounds(134, 684, 250, 23);

        chkPelekatanLebih.setBackground(new java.awt.Color(255, 255, 250));
        chkPelekatanLebih.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPelekatanLebih.setForeground(new java.awt.Color(0, 0, 0));
        chkPelekatanLebih.setText("Lebih Banyak Aerola Dibawah Bibir");
        chkPelekatanLebih.setBorderPainted(true);
        chkPelekatanLebih.setBorderPaintedFlat(true);
        chkPelekatanLebih.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPelekatanLebih.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPelekatanLebih.setName("chkPelekatanLebih"); // NOI18N
        chkPelekatanLebih.setOpaque(false);
        chkPelekatanLebih.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPelekatanLebih);
        chkPelekatanLebih.setBounds(470, 600, 250, 23);

        chkPelekatanTakTerbuka.setBackground(new java.awt.Color(255, 255, 250));
        chkPelekatanTakTerbuka.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPelekatanTakTerbuka.setForeground(new java.awt.Color(0, 0, 0));
        chkPelekatanTakTerbuka.setText("Mulut Bayi Tak Terbuka Lebar");
        chkPelekatanTakTerbuka.setBorderPainted(true);
        chkPelekatanTakTerbuka.setBorderPaintedFlat(true);
        chkPelekatanTakTerbuka.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPelekatanTakTerbuka.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPelekatanTakTerbuka.setName("chkPelekatanTakTerbuka"); // NOI18N
        chkPelekatanTakTerbuka.setOpaque(false);
        chkPelekatanTakTerbuka.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPelekatanTakTerbuka);
        chkPelekatanTakTerbuka.setBounds(470, 628, 250, 23);

        chkPelekatanTerputarKedalam.setBackground(new java.awt.Color(255, 255, 250));
        chkPelekatanTerputarKedalam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPelekatanTerputarKedalam.setForeground(new java.awt.Color(0, 0, 0));
        chkPelekatanTerputarKedalam.setText("Bibir Bawah Terputar Ke Dalam");
        chkPelekatanTerputarKedalam.setBorderPainted(true);
        chkPelekatanTerputarKedalam.setBorderPaintedFlat(true);
        chkPelekatanTerputarKedalam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPelekatanTerputarKedalam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPelekatanTerputarKedalam.setName("chkPelekatanTerputarKedalam"); // NOI18N
        chkPelekatanTerputarKedalam.setOpaque(false);
        chkPelekatanTerputarKedalam.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPelekatanTerputarKedalam);
        chkPelekatanTerputarKedalam.setBounds(470, 656, 250, 23);

        chkPelekatanTdkMenempel.setBackground(new java.awt.Color(255, 255, 250));
        chkPelekatanTdkMenempel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPelekatanTdkMenempel.setForeground(new java.awt.Color(0, 0, 0));
        chkPelekatanTdkMenempel.setText("Dagu Bayi Tidak Menempel Pada Payudara");
        chkPelekatanTdkMenempel.setBorderPainted(true);
        chkPelekatanTdkMenempel.setBorderPaintedFlat(true);
        chkPelekatanTdkMenempel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPelekatanTdkMenempel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPelekatanTdkMenempel.setName("chkPelekatanTdkMenempel"); // NOI18N
        chkPelekatanTdkMenempel.setOpaque(false);
        chkPelekatanTdkMenempel.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPelekatanTdkMenempel);
        chkPelekatanTdkMenempel.setBounds(470, 684, 250, 23);

        chkMengisapLambat.setBackground(new java.awt.Color(255, 255, 250));
        chkMengisapLambat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMengisapLambat.setForeground(new java.awt.Color(0, 0, 0));
        chkMengisapLambat.setText("Isapan Lambat, Dalam Dengan Istirahat");
        chkMengisapLambat.setBorderPainted(true);
        chkMengisapLambat.setBorderPaintedFlat(true);
        chkMengisapLambat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMengisapLambat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMengisapLambat.setName("chkMengisapLambat"); // NOI18N
        chkMengisapLambat.setOpaque(false);
        chkMengisapLambat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMengisapLambat);
        chkMengisapLambat.setBounds(134, 717, 250, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("MENGISAP :");
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(0, 717, 130, 23);

        chkMengisapPipiMembulat.setBackground(new java.awt.Color(255, 255, 250));
        chkMengisapPipiMembulat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMengisapPipiMembulat.setForeground(new java.awt.Color(0, 0, 0));
        chkMengisapPipiMembulat.setText("Pipi Membulat Waktu Mengisap");
        chkMengisapPipiMembulat.setBorderPainted(true);
        chkMengisapPipiMembulat.setBorderPaintedFlat(true);
        chkMengisapPipiMembulat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMengisapPipiMembulat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMengisapPipiMembulat.setName("chkMengisapPipiMembulat"); // NOI18N
        chkMengisapPipiMembulat.setOpaque(false);
        chkMengisapPipiMembulat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMengisapPipiMembulat);
        chkMengisapPipiMembulat.setBounds(134, 745, 250, 23);

        chkMengisapMelepaskanSelesai.setBackground(new java.awt.Color(255, 255, 250));
        chkMengisapMelepaskanSelesai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMengisapMelepaskanSelesai.setForeground(new java.awt.Color(0, 0, 0));
        chkMengisapMelepaskanSelesai.setText("Bayi Melepaskan Payudara Waktu Selesai");
        chkMengisapMelepaskanSelesai.setBorderPainted(true);
        chkMengisapMelepaskanSelesai.setBorderPaintedFlat(true);
        chkMengisapMelepaskanSelesai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMengisapMelepaskanSelesai.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMengisapMelepaskanSelesai.setName("chkMengisapMelepaskanSelesai"); // NOI18N
        chkMengisapMelepaskanSelesai.setOpaque(false);
        chkMengisapMelepaskanSelesai.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMengisapMelepaskanSelesai);
        chkMengisapMelepaskanSelesai.setBounds(134, 773, 250, 23);

        chkMengisapReflex.setBackground(new java.awt.Color(255, 255, 250));
        chkMengisapReflex.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMengisapReflex.setForeground(new java.awt.Color(0, 0, 0));
        chkMengisapReflex.setText("Ibu Merasakan Tanda-Tanda Reflex Oksitosin");
        chkMengisapReflex.setBorderPainted(true);
        chkMengisapReflex.setBorderPaintedFlat(true);
        chkMengisapReflex.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMengisapReflex.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMengisapReflex.setName("chkMengisapReflex"); // NOI18N
        chkMengisapReflex.setOpaque(false);
        chkMengisapReflex.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMengisapReflex);
        chkMengisapReflex.setBounds(134, 801, 250, 23);

        chkMengisapDangkal.setBackground(new java.awt.Color(255, 255, 250));
        chkMengisapDangkal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMengisapDangkal.setForeground(new java.awt.Color(0, 0, 0));
        chkMengisapDangkal.setText("Isapan Dangkal Dan Cepat");
        chkMengisapDangkal.setBorderPainted(true);
        chkMengisapDangkal.setBorderPaintedFlat(true);
        chkMengisapDangkal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMengisapDangkal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMengisapDangkal.setName("chkMengisapDangkal"); // NOI18N
        chkMengisapDangkal.setOpaque(false);
        chkMengisapDangkal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMengisapDangkal);
        chkMengisapDangkal.setBounds(470, 717, 250, 23);

        chkMengisapPipiTertarik.setBackground(new java.awt.Color(255, 255, 250));
        chkMengisapPipiTertarik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMengisapPipiTertarik.setForeground(new java.awt.Color(0, 0, 0));
        chkMengisapPipiTertarik.setText("Pipi Tertarik Kedalam Waktu Mengisap");
        chkMengisapPipiTertarik.setBorderPainted(true);
        chkMengisapPipiTertarik.setBorderPaintedFlat(true);
        chkMengisapPipiTertarik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMengisapPipiTertarik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMengisapPipiTertarik.setName("chkMengisapPipiTertarik"); // NOI18N
        chkMengisapPipiTertarik.setOpaque(false);
        chkMengisapPipiTertarik.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMengisapPipiTertarik);
        chkMengisapPipiTertarik.setBounds(470, 745, 250, 23);

        chkMengisapMelepaskanPayudara.setBackground(new java.awt.Color(255, 255, 250));
        chkMengisapMelepaskanPayudara.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMengisapMelepaskanPayudara.setForeground(new java.awt.Color(0, 0, 0));
        chkMengisapMelepaskanPayudara.setText("Bayi Melepaskan Bayi Dari Payudara");
        chkMengisapMelepaskanPayudara.setBorderPainted(true);
        chkMengisapMelepaskanPayudara.setBorderPaintedFlat(true);
        chkMengisapMelepaskanPayudara.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMengisapMelepaskanPayudara.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMengisapMelepaskanPayudara.setName("chkMengisapMelepaskanPayudara"); // NOI18N
        chkMengisapMelepaskanPayudara.setOpaque(false);
        chkMengisapMelepaskanPayudara.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMengisapMelepaskanPayudara);
        chkMengisapMelepaskanPayudara.setBounds(470, 773, 250, 23);

        chkMengisapOksitosin.setBackground(new java.awt.Color(255, 255, 250));
        chkMengisapOksitosin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMengisapOksitosin.setForeground(new java.awt.Color(0, 0, 0));
        chkMengisapOksitosin.setText("Tidak Tampak Tanda Oksitosin Yang Jelas");
        chkMengisapOksitosin.setBorderPainted(true);
        chkMengisapOksitosin.setBorderPaintedFlat(true);
        chkMengisapOksitosin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMengisapOksitosin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMengisapOksitosin.setName("chkMengisapOksitosin"); // NOI18N
        chkMengisapOksitosin.setOpaque(false);
        chkMengisapOksitosin.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMengisapOksitosin);
        chkMengisapOksitosin.setBounds(470, 801, 250, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Lama Waktu Menyusui :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 829, 130, 23);

        TlamaWkt.setForeground(new java.awt.Color(0, 0, 0));
        TlamaWkt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TlamaWkt.setName("TlamaWkt"); // NOI18N
        TlamaWkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlamaWktKeyPressed(evt);
            }
        });
        FormInput.add(TlamaWkt);
        TlamaWkt.setBounds(134, 829, 60, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("Menit");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(200, 829, 40, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Catatan :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 857, 130, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        Tcatatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tcatatan.setColumns(20);
        Tcatatan.setRows(5);
        Tcatatan.setName("Tcatatan"); // NOI18N
        Tcatatan.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tcatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcatatanKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Tcatatan);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(134, 857, 590, 90);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Tanggal :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 954, 130, 23);

        Ttgl.setEditable(false);
        Ttgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-02-2025" }));
        Ttgl.setDisplayFormat("dd-MM-yyyy");
        Ttgl.setName("Ttgl"); // NOI18N
        Ttgl.setOpaque(false);
        Ttgl.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(Ttgl);
        Ttgl.setBounds(134, 954, 90, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Jam : ");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(230, 954, 40, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(273, 954, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(325, 954, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(378, 954, 45, 23);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(30, 270, 700, 1);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(30, 359, 700, 1);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(30, 476, 700, 1);

        jSeparator15.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator15.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator15.setName("jSeparator15"); // NOI18N
        FormInput.add(jSeparator15);
        jSeparator15.setBounds(30, 593, 700, 1);

        jSeparator16.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator16.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator16.setName("jSeparator16"); // NOI18N
        FormInput.add(jSeparator16);
        jSeparator16.setBounds(30, 710, 700, 1);

        ScrollTriase1.setViewportView(FormInput);

        FormTindakan.add(ScrollTriase1, java.awt.BorderLayout.CENTER);

        panelGlass10.add(FormTindakan);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Data Pengamatan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPengamatan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPengamatan.setName("tbPengamatan"); // NOI18N
        tbPengamatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPengamatanMouseClicked(evt);
            }
        });
        tbPengamatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPengamatanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPengamatan);

        internalFrame4.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-02-2025" }));
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

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-02-2025" }));
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
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

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
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnCari);

        internalFrame4.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        panelGlass10.add(internalFrame4);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            cekData();
            if (Sequel.menyimpantf("pengamatan_menyusui_perinatologi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 56, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), cmbImd.getSelectedItem().toString(), TlamaImd.getText(), Talasan.getText(), cmbAsi.getSelectedItem().toString(),
                        ibuSehat, ibuRileks, ibuTerlihat, ibuSakit, ibuTegang, ibuTidak, bayiSehat, bayiTenang, bayiLapar, bayiMengantuk, bayiGelisah, bayiMencari, payudaraSehat,
                        payudaraNyaman, payudaraDitopang, payudaraPutingKeluar, payudaraMerah, payudaraNyeri, payudaraAerola, payudaraPutingDatar, posisiKepala, posisiDipegang,
                        posisiSeluruh, posisiHidungBerhadapan, posisiLeher, posisiTakDipegang, posisiHanya, posisiBibirBawah, pelekatanTampak, pelekatanTerbuka, pelekatanTerputarKeluar,
                        pelekatanMenempel, pelekatanLebih, pelekatanTakTerbuka, pelekatanTerputarKedalam, pelekatanTidakMenempel, mengisapLambat, mengisapPipiMembulat,
                        mengisapMelepaskanSelesai, mengisapReflex, mengisapDangkal, mengisapPipiTertarik, mengisapMelepaskanPayudara, mengisapOksitosin, TlamaWkt.getText(),
                        Tcatatan.getText(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        nip, Sequel.cariIsi("select now()")
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Lembaran Bantuan Pengamatan Menyusui", "Simpan");                
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
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbPengamatan.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                hapus();
            } else {
                if (nip.equals(akses.getkode())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh perawat yang bernama " + tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 13).toString() + " ..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (tbPengamatan.getSelectedRow() > -1) {
                if (akses.getadmin() == true) {
                    ganti();
                } else {
                    if (nip.equals(akses.getkode())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh perawat yang bernama " + tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 13).toString() + " ..!!");
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
        if (tbPengamatan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data pada tabel masih kosong..!!");
        }  else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            
            if (TlamaImd.getText().equals("")) {
                param.put("imd", cmbImd.getSelectedItem().toString() + ",  Lama IMD ......");
            } else {
                param.put("imd", cmbImd.getSelectedItem().toString() + ",  Lama IMD : " + TlamaImd.getText());
            }
            
            if (Talasan.getText().equals("")) {
                param.put("alasan", "-");
            } else {
                param.put("alasan", Talasan.getText());
            }

            param.put("asi", cmbAsi.getSelectedItem().toString());
            
            //umum ibu
            if (chkIbuSehat.isSelected() == true) {
                param.put("ibuSe", "V");
            } else {
                param.put("ibuSe", "");
            }
            
            if (chkIbuRileks.isSelected() == true) {
                param.put("ibuR", "V");
            } else {
                param.put("ibuR", "");
            }
            
            if (chkIbuTerlihat.isSelected() == true) {
                param.put("ibuTer", "V");
            } else {
                param.put("ibuTer", "");
            }
            
            if (chkIbuSakit.isSelected() == true) {
                param.put("ibuSa", "V");
            } else {
                param.put("ibuSa", "");
            }
            
            if (chkIbuTegang.isSelected() == true) {
                param.put("ibuTeg", "V");
            } else {
                param.put("ibuTeg", "");
            }
            
            if (chkIbuTidak.isSelected() == true) {
                param.put("ibuTi", "V");
            } else {
                param.put("ibuTi", "");
            }
            
            //umum bayi
            if (chkBayiSehat.isSelected() == true) {
                param.put("bayiSe", "V");
            } else {
                param.put("bayiSe", "");
            }
            
            if (chkBayiTenang.isSelected() == true) {
                param.put("bayiTe", "V");
            } else {
                param.put("bayiTe", "");
            }
            
            if (chkBayiLapar.isSelected() == true) {
                param.put("bayiL", "V");
            } else {
                param.put("bayiL", "");
            }
            
            if (chkBayiMengantuk.isSelected() == true) {
                param.put("bayiMeng", "V");
            } else {
                param.put("bayiMeng", "");
            }
            
            if (chkBayiGelisah.isSelected() == true) {
                param.put("bayiG", "V");
            } else {
                param.put("bayiG", "");
            }
            
            if (chkBayiMencari.isSelected() == true) {
                param.put("bayiMen", "V");
            } else {
                param.put("bayiMen", "");
            }
            
            //payudara
            if (chkPayudaraSehat.isSelected() == true) {
                param.put("payuSe", "V");
            } else {
                param.put("payuSe", "");
            }
            
            if (chkPayudaraNyaman.isSelected() == true) {
                param.put("payuNyam", "V");
            } else {
                param.put("payuNyam", "");
            }
            
            if (chkPayudaraDitopang.isSelected() == true) {
                param.put("payuDit", "V");
            } else {
                param.put("payuDit", "");
            }
            
            if (chkPayudaraPutingKeluar.isSelected() == true) {
                param.put("payuPutingKel", "V");
            } else {
                param.put("payuPutingKel", "");
            }
            
            if (chkPayudaraMerah.isSelected() == true) {
                param.put("payuMer", "V");
            } else {
                param.put("payuMer", "");
            }
            
            if (chkPayudaraNyeri.isSelected() == true) {
                param.put("payuNyer", "V");
            } else {
                param.put("payuNyer", "");
            }
            
            if (chkPayudaraAerola.isSelected() == true) {
                param.put("payuA", "V");
            } else {
                param.put("payuA", "");
            }
            
            if (chkPayudaraPutingDatar.isSelected() == true) {
                param.put("payuPutingDat", "V");
            } else {
                param.put("payuPutingDat", "");
            }
            
            //posisi bayi
            if (chkPosisiKepala.isSelected() == true) {
                param.put("posisiKep", "V");
            } else {
                param.put("posisiKep", "");
            }
            
            if (chkPosisiDipegang.isSelected() == true) {
                param.put("posisiDip", "V");
            } else {
                param.put("posisiDip", "");
            }
            
            if (chkPosisiSeluruh.isSelected() == true) {
                param.put("posisiS", "V");
            } else {
                param.put("posisiS", "");
            }
            
            if (chkPosisiHidung.isSelected() == true) {
                param.put("posisiHid", "V");
            } else {
                param.put("posisiHid", "");
            }
            
            if (chkPosisiLeher.isSelected() == true) {
                param.put("posisiLeh", "V");
            } else {
                param.put("posisiLeh", "");
            }
            
            if (chkPosisiTakDipegang.isSelected() == true) {
                param.put("posisiTak", "V");
            } else {
                param.put("posisiTak", "");
            }
            
            if (chkPosisiHanya.isSelected() == true) {
                param.put("posisiHan", "V");
            } else {
                param.put("posisiHan", "");
            }
            
            if (chkPosisiBibir.isSelected() == true) {
                param.put("posisiB", "V");
            } else {
                param.put("posisiB", "");
            }
            
            //pelekatan bayi
            if (chkPelekatanTampak.isSelected() == true) {
                param.put("pelekatanTam", "V");
            } else {
                param.put("pelekatanTam", "");
            }
            
            if (chkPelekatanTerbuka.isSelected() == true) {
                param.put("pelekatanTer", "V");
            } else {
                param.put("pelekatanTer", "");
            }
            
            if (chkPelekatanTerputarKeluar.isSelected() == true) {
                param.put("pelekatanTerKel", "V");
            } else {
                param.put("pelekatanTerKel", "");
            }
            
            if (chkPelekatanMenempel.isSelected() == true) {
                param.put("pelekatanMen", "V");
            } else {
                param.put("pelekatanMen", "");
            }
            
            if (chkPelekatanLebih.isSelected() == true) {
                param.put("pelekatanLeb", "V");
            } else {
                param.put("pelekatanLeb", "");
            }
            
            if (chkPelekatanTakTerbuka.isSelected() == true) {
                param.put("pelekatanTakTer", "V");
            } else {
                param.put("pelekatanTakTer", "");
            }
            
            if (chkPelekatanTerputarKedalam.isSelected() == true) {
                param.put("pelekatanTerKed", "V");
            } else {
                param.put("pelekatanTerKed", "");
            }
            
            if (chkPelekatanTdkMenempel.isSelected() == true) {
                param.put("pelekatanTdkMen", "V");
            } else {
                param.put("pelekatanTdkMen", "");
            }
            
            //mengisap
            if (chkMengisapLambat.isSelected() == true) {
                param.put("mengisapLam", "V");
            } else {
                param.put("mengisapLam", "");
            }
            
            if (chkMengisapPipiMembulat.isSelected() == true) {
                param.put("mengisapPipMem", "V");
            } else {
                param.put("mengisapPipMem", "");
            }
            
            if (chkMengisapMelepaskanSelesai.isSelected() == true) {
                param.put("mengisapMelSel", "V");
            } else {
                param.put("mengisapMelSel", "");
            }
            
            if (chkMengisapReflex.isSelected() == true) {
                param.put("mengisapRef", "V");
            } else {
                param.put("mengisapRef", "");
            }
            
            if (chkMengisapDangkal.isSelected() == true) {
                param.put("mengisapDang", "V");
            } else {
                param.put("mengisapDang", "");
            }
            
            if (chkMengisapPipiTertarik.isSelected() == true) {
                param.put("mengisapPipTer", "V");
            } else {
                param.put("mengisapPipTer", "");
            }
            
            if (chkMengisapMelepaskanPayudara.isSelected() == true) {
                param.put("mengisapMelPay", "V");
            } else {
                param.put("mengisapMelPay", "");
            }
            
            if (chkMengisapOksitosin.isSelected() == true) {
                param.put("mengisapOks", "V");
            } else {
                param.put("mengisapOks", "");
            }
            
            if (TlamaWkt.getText().equals("")) {
                param.put("lamaMenyusui", "-");
            } else {
                param.put("lamaMenyusui", TlamaWkt.getText());
            }
            
            if (Tcatatan.getText().equals("")) {
                param.put("catatan", "-");
            } else {
                param.put("catatan", Tcatatan.getText());
            }
            
            param.put("tanggal", Valid.SetTglINDONESIA(Valid.SetTgl(Ttgl.getSelectedItem() + "")));
            param.put("jam", cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " Wita");
            param.put("petugas", TnmPerawat.getText());
            
//            Valid.MyReport("rptBeriInfoTindakan.jasper", "report", "::[ Lembar Pemberian Informasi Tindakan ]::",
//                    "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, d.nm_dokter, pg.nama pemberiInfo, "
//                    + "s.penerima_info, s.isi_info_diagnosis_kerja, s.isi_info_dasar_diagnosis, s.isi_info_tindakan, s.isi_info_indikasi, s.isi_info_tatacara, "
//                    + "s.isi_info_tujuan, s.isi_info_resiko, s.isi_info_komplikasi, s.isi_info_prognosis, s.isi_info_alternatif, s.isi_info_lainlain, "
//                    + "time_format(s.jam_surat,'%H:%i WITA') jamBeriInfo FROM surat_tindakan_kedokteran s INNER JOIN reg_periksa rp ON rp.no_rawat = s.no_rawat "
//                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN dokter d ON d.kd_dokter = s.nip_dokter_pelaksana "
//                    + "INNER JOIN pegawai pg ON pg.nik = s.nip_pemberi_info where s.waktu_simpan='" + wktSimpan + "'", param);

            this.setCursor(Cursor.getDefaultCursor());
            TCari.setText(TNoRw.getText());
            tampil();
            BtnBatalActionPerformed(null);
        }
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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

    private void tbPengamatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPengamatanKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPengamatanKeyPressed

    private void tbPengamatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPengamatanMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPengamatanMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatActionPerformed
        akses.setform("RMTindakanKedokteran");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatActionPerformed

    private void cmbImdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbImdActionPerformed
        TlamaImd.setText("");
        if (cmbImd.getSelectedIndex() == 2) {
            TlamaImd.setEnabled(true);
            TlamaImd.requestFocus();
        } else {
            TlamaImd.setEnabled(false);
        }
    }//GEN-LAST:event_cmbImdActionPerformed

    private void TlamaImdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlamaImdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Talasan.requestFocus();
        }
    }//GEN-LAST:event_TlamaImdKeyPressed

    private void TalasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalasanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbAsi.requestFocus();
        }
    }//GEN-LAST:event_TalasanKeyPressed

    private void TlamaWktKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlamaWktKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tcatatan.requestFocus();
        }
    }//GEN-LAST:event_TlamaWktKeyPressed

    private void TcatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Ttgl.requestFocus();
        }
    }//GEN-LAST:event_TcatatanKeyPressed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPengamatanMenyusui dialog = new RMPengamatanMenyusui(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar;
    private widget.Button BtnPerawat;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.InternalFrame FormTindakan;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane ScrollTriase1;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Talasan;
    private widget.TextArea Tcatatan;
    private widget.TextBox TlamaImd;
    public widget.TextBox TlamaWkt;
    public widget.TextBox TnmPerawat;
    private widget.TextBox TrgRawat;
    private widget.Tanggal Ttgl;
    public widget.CekBox chkBayiGelisah;
    public widget.CekBox chkBayiLapar;
    public widget.CekBox chkBayiMencari;
    public widget.CekBox chkBayiMengantuk;
    public widget.CekBox chkBayiSehat;
    public widget.CekBox chkBayiTenang;
    public widget.CekBox chkIbuRileks;
    public widget.CekBox chkIbuSakit;
    public widget.CekBox chkIbuSehat;
    public widget.CekBox chkIbuTegang;
    public widget.CekBox chkIbuTerlihat;
    public widget.CekBox chkIbuTidak;
    public widget.CekBox chkMengisapDangkal;
    public widget.CekBox chkMengisapLambat;
    public widget.CekBox chkMengisapMelepaskanPayudara;
    public widget.CekBox chkMengisapMelepaskanSelesai;
    public widget.CekBox chkMengisapOksitosin;
    public widget.CekBox chkMengisapPipiMembulat;
    public widget.CekBox chkMengisapPipiTertarik;
    public widget.CekBox chkMengisapReflex;
    public widget.CekBox chkPayudaraAerola;
    public widget.CekBox chkPayudaraDitopang;
    public widget.CekBox chkPayudaraMerah;
    public widget.CekBox chkPayudaraNyaman;
    public widget.CekBox chkPayudaraNyeri;
    public widget.CekBox chkPayudaraPutingDatar;
    public widget.CekBox chkPayudaraPutingKeluar;
    public widget.CekBox chkPayudaraSehat;
    public widget.CekBox chkPelekatanLebih;
    public widget.CekBox chkPelekatanMenempel;
    public widget.CekBox chkPelekatanTakTerbuka;
    public widget.CekBox chkPelekatanTampak;
    public widget.CekBox chkPelekatanTdkMenempel;
    public widget.CekBox chkPelekatanTerbuka;
    public widget.CekBox chkPelekatanTerputarKedalam;
    public widget.CekBox chkPelekatanTerputarKeluar;
    public widget.CekBox chkPosisiBibir;
    public widget.CekBox chkPosisiDipegang;
    public widget.CekBox chkPosisiHanya;
    public widget.CekBox chkPosisiHidung;
    public widget.CekBox chkPosisiKepala;
    public widget.CekBox chkPosisiLeher;
    public widget.CekBox chkPosisiSeluruh;
    public widget.CekBox chkPosisiTakDipegang;
    private widget.ComboBox cmbAsi;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbImd;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame4;
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
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.Label jLabel96;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane14;
    private widget.Table tbPengamatan;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select pm.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                    + "date_format(pm.tanggal,'%d-%m-%Y') tggl, time_format(pm.jam,'%H:%i Wita') jamm, pg.nama nmPetugas from pengamatan_menyusui_perinatologi pm "
                    + "inner join reg_periksa rp on rp.no_rawat=pm.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=pm.nip_perawat where "
                    + "pm.tanggal between ? and ? and pm.no_rawat like ? or "
                    + "pm.tanggal between ? and ? and p.no_rkm_medis like ? or "
                    + "pm.tanggal between ? and ? and p.nm_pasien like ? or "
                    + "pm.tanggal between ? and ? and pm.lama_menyusui like ? or "
                    + "pm.tanggal between ? and ? and pm.alasan like ? or "
                    + "pm.tanggal between ? and ? and pg.nama like ? order by pm.waktu_simpan desc");
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
                        rs.getString("jenkel"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("imd"),
                        rs.getString("lama_imd"),
                        rs.getString("alasan"),
                        rs.getString("asi_eksklusif"),
                        rs.getString("lama_menyusui") + " menit",
                        rs.getString("tggl"),
                        rs.getString("jamm"),
                        rs.getString("nmPetugas"),
                        rs.getString("ibu_sehat"),
                        rs.getString("ibu_rileks"),
                        rs.getString("ibu_terlihat"),
                        rs.getString("ibu_sakit"),
                        rs.getString("ibu_tegang"),
                        rs.getString("ibu_tidak"),
                        rs.getString("bayi_sehat"),
                        rs.getString("bayi_tenang"),
                        rs.getString("bayi_lapar"),
                        rs.getString("bayi_mengantuk"),
                        rs.getString("bayi_gelisah"),
                        rs.getString("bayi_mencari"),
                        rs.getString("payudara_sehat"),
                        rs.getString("payudara_nyaman"),
                        rs.getString("payudara_ditopang"),
                        rs.getString("payudara_puting_keluar"),
                        rs.getString("payudara_merah"),
                        rs.getString("payudara_nyeri"),
                        rs.getString("payudara_aerola"),
                        rs.getString("payudara_puting_datar"),
                        rs.getString("posisi_kepala"),
                        rs.getString("posisi_dipegang"),
                        rs.getString("posisi_seluruh"),
                        rs.getString("posisi_hidung_berhadapan"),
                        rs.getString("posisi_leher"),
                        rs.getString("posisi_tak_dipegang"),
                        rs.getString("posisi_hanya"),
                        rs.getString("posisi_bibir_bawah"),
                        rs.getString("pelekatan_tampak"),
                        rs.getString("pelekatan_terbuka"),
                        rs.getString("pelekatan_terputar_keluar"),
                        rs.getString("pelekatan_menempel"),
                        rs.getString("pelekatan_lebih"),
                        rs.getString("pelekatan_tak_terbuka"),
                        rs.getString("pelekatan_terputar_kedalam"),
                        rs.getString("pelekatan_tidak_menempel"),
                        rs.getString("mengisap_lambat"),
                        rs.getString("mengisap_pipi_membulat"),
                        rs.getString("mengisap_melepaskan_selesai"),
                        rs.getString("mengisap_reflex"),
                        rs.getString("mengisap_dangkal"),
                        rs.getString("mengisap_pipi_tertarik"),
                        rs.getString("mengisap_melepaskan_payudara"),
                        rs.getString("mengisap_oksitosin"),
                        rs.getString("lama_menyusui"),
                        rs.getString("catatan"),
                        rs.getString("tanggal"),
                        rs.getString("jam"),
                        rs.getString("nip_perawat"),
                        rs.getString("waktu_simpan"),
                        rs.getString("lama_menyusui")
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
        cmbImd.setSelectedIndex(0);
        TlamaImd.setText("");
        TlamaImd.setEnabled(false);
        Talasan.setText("");
        cmbAsi.setSelectedIndex(0);
        
        chkIbuSehat.setSelected(false);
        chkIbuRileks.setSelected(false);
        chkIbuTerlihat.setSelected(false);
        chkIbuSakit.setSelected(false);
        chkIbuTegang.setSelected(false);
        chkIbuTidak.setSelected(false);
        
        chkBayiSehat.setSelected(false);
        chkBayiTenang.setSelected(false);        
        chkBayiLapar.setSelected(false);
        chkBayiMengantuk.setSelected(false);
        chkBayiGelisah.setSelected(false);
        chkBayiMencari.setSelected(false);
        
        chkPayudaraSehat.setSelected(false);
        chkPayudaraNyaman.setSelected(false);
        chkPayudaraDitopang.setSelected(false);
        chkPayudaraPutingKeluar.setSelected(false);
        chkPayudaraMerah.setSelected(false);
        chkPayudaraNyeri.setSelected(false);
        chkPayudaraAerola.setSelected(false);
        chkPayudaraPutingDatar.setSelected(false);
        
        chkPosisiKepala.setSelected(false);
        chkPosisiDipegang.setSelected(false);
        chkPosisiSeluruh.setSelected(false);
        chkPosisiHidung.setSelected(false);
        chkPosisiLeher.setSelected(false);
        chkPosisiTakDipegang.setSelected(false);
        chkPosisiHanya.setSelected(false);
        chkPosisiBibir.setSelected(false);
        
        chkPelekatanTampak.setSelected(false);
        chkPelekatanTerbuka.setSelected(false);
        chkPelekatanTerputarKeluar.setSelected(false);
        chkPelekatanMenempel.setSelected(false);
        chkPelekatanLebih.setSelected(false);
        chkPelekatanTakTerbuka.setSelected(false);
        chkPelekatanTerputarKedalam.setSelected(false);
        chkPelekatanTdkMenempel.setSelected(false);
        
        chkMengisapLambat.setSelected(false);
        chkMengisapPipiMembulat.setSelected(false);
        chkMengisapMelepaskanSelesai.setSelected(false);
        chkMengisapReflex.setSelected(false);
        chkMengisapDangkal.setSelected(false);
        chkMengisapPipiTertarik.setSelected(false);
        chkMengisapMelepaskanPayudara.setSelected(false);
        chkMengisapOksitosin.setSelected(false);
        
        TlamaWkt.setText("");
        Tcatatan.setText("");
        Ttgl.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
    }
    
    public void setData(String norwt, String rgrawat) {
        TNoRw.setText(norwt);
        TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norwt + "'"));
        TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        TrgRawat.setText(rgrawat);
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(new Date());        
        TCari.setText(norwt);
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnPrint.setEnabled(akses.getcppt());
        BtnEdit.setEnabled(akses.getcppt());
    }
    
    private void getData() {
        variabelBersih();
        if (tbPengamatan.getSelectedRow() != -1) {
            TNoRw.setText(tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 0).toString());
            TNoRM.setText(tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 1).toString());
            TPasien.setText(tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(),2).toString());
            TrgRawat.setText(tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(),5).toString());
            cmbImd.setSelectedItem(tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(),6).toString());
            TlamaImd.setText(tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(),7).toString());
            Talasan.setText(tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(),8).toString());
            cmbAsi.setSelectedItem(tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(),9).toString());
            
            ibuSehat = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 14).toString();
            ibuRileks = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 15).toString();
            ibuTerlihat = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 16).toString();
            ibuSakit = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 17).toString();
            ibuTegang = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 18).toString();
            ibuTidak = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 19).toString();
            
            bayiSehat = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 20).toString();
            bayiTenang = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 21).toString();
            bayiLapar = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 22).toString();
            bayiMengantuk = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 23).toString();
            bayiGelisah = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 24).toString();
            bayiMencari = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 25).toString();
            
            payudaraSehat = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 26).toString();
            payudaraNyaman = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 27).toString();
            payudaraDitopang = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 28).toString();
            payudaraPutingKeluar = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 29).toString();
            payudaraMerah = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 30).toString();
            payudaraNyeri = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 31).toString();
            payudaraAerola = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 32).toString();
            payudaraPutingDatar = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 33).toString();
            
            posisiKepala = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 34).toString();
            posisiDipegang = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 35).toString();
            posisiSeluruh = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 36).toString();
            posisiHidungBerhadapan = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 37).toString();
            posisiLeher = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 38).toString();
            posisiTakDipegang = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 39).toString();
            posisiHanya = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 40).toString();
            posisiBibirBawah = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 41).toString();
            
            pelekatanTampak = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 42).toString();
            pelekatanTerbuka = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 43).toString();
            pelekatanTerputarKeluar = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 44).toString();
            pelekatanMenempel = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 45).toString();
            pelekatanLebih = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 46).toString();
            pelekatanTakTerbuka = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 47).toString();
            pelekatanTerputarKedalam = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 48).toString();
            pelekatanTidakMenempel = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 49).toString();
            
            mengisapLambat = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 50).toString();
            mengisapPipiMembulat = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 51).toString();
            mengisapMelepaskanSelesai = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 52).toString();
            mengisapReflex = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 53).toString();
            mengisapDangkal = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 54).toString();
            mengisapPipiTertarik = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 55).toString();
            mengisapMelepaskanPayudara = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 56).toString();
            mengisapOksitosin = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 57).toString();
            
            TlamaWkt.setText(tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(),64).toString());            
            Tcatatan.setText(tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(),59).toString());
            Valid.SetTgl(Ttgl, tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 60).toString());
            cmbJam.setSelectedItem(tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 61).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 61).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 61).toString().substring(6, 8));            
            nip = tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(),62).toString();
            TnmPerawat.setText(tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(),13).toString());
            dataCek();
        }
    }
    
    private void cekData() {
        //umum ibu
        if (chkIbuSehat.isSelected() == true) {
            ibuSehat = "ya";
        } else {
            ibuSehat = "tidak";
        }
        
        if (chkIbuRileks.isSelected() == true) {
            ibuRileks = "ya";
        } else {
            ibuRileks = "tidak";
        }
        
        if (chkIbuTerlihat.isSelected() == true) {
            ibuTerlihat = "ya";
        } else {
            ibuTerlihat = "tidak";
        }
        
        if (chkIbuSakit.isSelected() == true) {
            ibuSakit = "ya";
        } else {
            ibuSakit = "tidak";
        }
        
        if (chkIbuTegang.isSelected() == true) {
            ibuTegang = "ya";
        } else {
            ibuTegang = "tidak";
        }
        
        if (chkIbuTidak.isSelected() == true) {
            ibuTidak = "ya";
        } else {
            ibuTidak = "tidak";
        }
        
        //umum bayi
        if (chkBayiSehat.isSelected() == true) {
            bayiSehat = "ya";
        } else {
            bayiSehat = "tidak";
        }
        
        if (chkBayiTenang.isSelected() == true) {
            bayiTenang = "ya";
        } else {
            bayiTenang = "tidak";
        }
        
        if (chkBayiLapar.isSelected() == true) {
            bayiLapar = "ya";
        } else {
            bayiLapar = "tidak";
        }
        
        if (chkBayiMengantuk.isSelected() == true) {
            bayiMengantuk = "ya";
        } else {
            bayiMengantuk = "tidak";
        }
        
        if (chkBayiGelisah.isSelected() == true) {
            bayiGelisah = "ya";
        } else {
            bayiGelisah = "tidak";
        }
        
        if (chkBayiMencari.isSelected() == true) {
            bayiMencari = "ya";
        } else {
            bayiMencari = "tidak";
        }
        
        //payudara
        if (chkPayudaraSehat.isSelected() == true) {
            payudaraSehat = "ya";
        } else {
            payudaraSehat = "tidak";
        }
        
        if (chkPayudaraNyaman.isSelected() == true) {
            payudaraNyaman = "ya";
        } else {
            payudaraNyaman = "tidak";
        }
        
        if (chkPayudaraDitopang.isSelected() == true) {
            payudaraDitopang = "ya";
        } else {
            payudaraDitopang = "tidak";
        }
        
        if (chkPayudaraPutingKeluar.isSelected() == true) {
            payudaraPutingKeluar = "ya";
        } else {
            payudaraPutingKeluar = "tidak";
        }
        
        if (chkPayudaraMerah.isSelected() == true) {
            payudaraMerah = "ya";
        } else {
            payudaraMerah = "tidak";
        }
        
        if (chkPayudaraNyeri.isSelected() == true) {
            payudaraNyeri = "ya";
        } else {
            payudaraNyeri = "tidak";
        }
        
        if (chkPayudaraAerola.isSelected() == true) {
            payudaraAerola = "ya";
        } else {
            payudaraAerola = "tidak";
        }
        
        if (chkPayudaraPutingDatar.isSelected() == true) {
            payudaraPutingDatar = "ya";
        } else {
            payudaraPutingDatar = "tidak";
        }
        
        //posisi bayi
        if (chkPosisiKepala.isSelected() == true) {
            posisiKepala = "ya";
        } else {
            posisiKepala = "tidak";
        }
        
        if (chkPosisiDipegang.isSelected() == true) {
            posisiDipegang = "ya";
        } else {
            posisiDipegang = "tidak";
        }
        
        if (chkPosisiSeluruh.isSelected() == true) {
            posisiSeluruh = "ya";
        } else {
            posisiSeluruh = "tidak";
        }
        
        if (chkPosisiHidung.isSelected() == true) {
            posisiHidungBerhadapan = "ya";
        } else {
            posisiHidungBerhadapan = "tidak";
        }
        
        if (chkPosisiLeher.isSelected() == true) {
            posisiLeher = "ya";
        } else {
            posisiLeher = "tidak";
        }
        
        if (chkPosisiTakDipegang.isSelected() == true) {
            posisiTakDipegang = "ya";
        } else {
            posisiTakDipegang = "tidak";
        }
        
        if (chkPosisiHanya.isSelected() == true) {
            posisiHanya = "ya";
        } else {
            posisiHanya = "tidak";
        }
        
        if (chkPosisiBibir.isSelected() == true) {
            posisiBibirBawah = "ya";
        } else {
            posisiBibirBawah = "tidak";
        }
        
        //pelekatan bayi
        if (chkPelekatanTampak.isSelected() == true) {
            pelekatanTampak = "ya";
        } else {
            pelekatanTampak = "tidak";
        }
        
        if (chkPelekatanTerbuka.isSelected() == true) {
            pelekatanTerbuka = "ya";
        } else {
            pelekatanTerbuka = "tidak";
        }
        
        if (chkPelekatanTerputarKeluar.isSelected() == true) {
            pelekatanTerputarKeluar = "ya";
        } else {
            pelekatanTerputarKeluar = "tidak";
        }
        
        if (chkPelekatanMenempel.isSelected() == true) {
            pelekatanMenempel = "ya";
        } else {
            pelekatanMenempel = "tidak";
        }
        
        if (chkPelekatanLebih.isSelected() == true) {
            pelekatanLebih = "ya";
        } else {
            pelekatanLebih = "tidak";
        }
        
        if (chkPelekatanTakTerbuka.isSelected() == true) {
            pelekatanTakTerbuka = "ya";
        } else {
            pelekatanTakTerbuka = "tidak";
        }
        
        if (chkPelekatanTerputarKedalam.isSelected() == true) {
            pelekatanTerputarKedalam = "ya";
        } else {
            pelekatanTerputarKedalam = "tidak";
        }
        
        if (chkPelekatanTdkMenempel.isSelected() == true) {
            pelekatanTidakMenempel = "ya";
        } else {
            pelekatanTidakMenempel = "tidak";
        }
        
        //mengisap
        if (chkMengisapLambat.isSelected() == true) {
            mengisapLambat = "ya";
        } else {
            mengisapLambat = "tidak";
        }
        
        if (chkMengisapPipiMembulat.isSelected() == true) {
            mengisapPipiMembulat = "ya";
        } else {
            mengisapPipiMembulat = "tidak";
        }
        
        if (chkMengisapMelepaskanSelesai.isSelected() == true) {
            mengisapMelepaskanSelesai = "ya";
        } else {
            mengisapMelepaskanSelesai = "tidak";
        }
        
        if (chkMengisapReflex.isSelected() == true) {
            mengisapReflex = "ya";
        } else {
            mengisapReflex = "tidak";
        }
        
        if (chkMengisapDangkal.isSelected() == true) {
            mengisapDangkal = "ya";
        } else {
            mengisapDangkal = "tidak";
        }
        
        if (chkMengisapPipiTertarik.isSelected() == true) {
            mengisapPipiTertarik = "ya";
        } else {
            mengisapPipiTertarik = "tidak";
        }
        
        if (chkMengisapMelepaskanPayudara.isSelected() == true) {
            mengisapMelepaskanPayudara = "ya";
        } else {
            mengisapMelepaskanPayudara = "tidak";
        }
        
        if (chkMengisapOksitosin.isSelected() == true) {
            mengisapOksitosin = "ya";
        } else {
            mengisapOksitosin = "tidak";
        }
    }
    
    private void dataCek() {        
        if (cmbImd.getSelectedIndex() == 2) {
            TlamaImd.setEnabled(true);
        } else {
            TlamaImd.setEnabled(false);
        }
        
        //umum ibu
        if (ibuSehat.equals("ya")) {
            chkIbuSehat.setSelected(true);
        } else {
            chkIbuSehat.setSelected(false);
        }
        
        if (ibuRileks.equals("ya")) {
            chkIbuRileks.setSelected(true);
        } else {
            chkIbuRileks.setSelected(false);
        }
        
        if (ibuTerlihat.equals("ya")) {
            chkIbuTerlihat.setSelected(true);
        } else {
            chkIbuTerlihat.setSelected(false);
        }
        
        if (ibuSakit.equals("ya")) {
            chkIbuSakit.setSelected(true);
        } else {
            chkIbuSakit.setSelected(false);
        }
        
        if (ibuTegang.equals("ya")) {
            chkIbuTegang.setSelected(true);
        } else {
            chkIbuTegang.setSelected(false);
        }
        
        if (ibuTidak.equals("ya")) {
            chkIbuTidak.setSelected(true);
        } else {
            chkIbuTidak.setSelected(false);
        }
        
        //umum bayi
        if (bayiSehat.equals("ya")) {
            chkBayiSehat.setSelected(true);
        } else {
            chkBayiSehat.setSelected(false);
        }
        
        if (bayiTenang.equals("ya")) {
            chkBayiTenang.setSelected(true);
        } else {
            chkBayiTenang.setSelected(false);
        }
        
        if (bayiLapar.equals("ya")) {
            chkBayiLapar.setSelected(true);
        } else {
            chkBayiLapar.setSelected(false);
        }
        
        if (bayiMengantuk.equals("ya")) {
            chkBayiMengantuk.setSelected(true);
        } else {
            chkBayiMengantuk.setSelected(false);
        }
        
        if (bayiGelisah.equals("ya")) {
            chkBayiGelisah.setSelected(true);
        } else {
            chkBayiGelisah.setSelected(false);
        }
        
        if (bayiMencari.equals("ya")) {
            chkBayiMencari.setSelected(true);
        } else {
            chkBayiMencari.setSelected(false);
        }
        
        //payudara
        if (payudaraSehat.equals("ya")) {
            chkPayudaraSehat.setSelected(true);
        } else {
            chkPayudaraSehat.setSelected(false);
        }
        
        if (payudaraNyaman.equals("ya")) {
            chkPayudaraNyaman.setSelected(true);
        } else {
            chkPayudaraNyaman.setSelected(false);
        }
        
        if (payudaraDitopang.equals("ya")) {
            chkPayudaraDitopang.setSelected(true);
        } else {
            chkPayudaraDitopang.setSelected(false);
        }
        
        if (payudaraPutingKeluar.equals("ya")) {
            chkPayudaraPutingKeluar.setSelected(true);
        } else {
            chkPayudaraPutingKeluar.setSelected(false);
        }
        
        if (payudaraMerah.equals("ya")) {
            chkPayudaraMerah.setSelected(true);
        } else {
            chkPayudaraMerah.setSelected(false);
        }
        
        if (payudaraNyeri.equals("ya")) {
            chkPayudaraNyeri.setSelected(true);
        } else {
            chkPayudaraNyeri.setSelected(false);
        }
        
        if (payudaraAerola.equals("ya")) {
            chkPayudaraAerola.setSelected(true);
        } else {
            chkPayudaraAerola.setSelected(false);
        }
        
        if (payudaraPutingDatar.equals("ya")) {
            chkPayudaraPutingDatar.setSelected(true);
        } else {
            chkPayudaraPutingDatar.setSelected(false);
        }
        
        //posisi bayi
        if (posisiKepala.equals("ya")) {
            chkPosisiKepala.setSelected(true);
        } else {
            chkPosisiKepala.setSelected(false);
        }
        
        if (posisiDipegang.equals("ya")) {
            chkPosisiDipegang.setSelected(true);
        } else {
            chkPosisiDipegang.setSelected(false);
        }
        
        if (posisiSeluruh.equals("ya")) {
            chkPosisiSeluruh.setSelected(true);
        } else {
            chkPosisiSeluruh.setSelected(false);
        }
        
        if (posisiHidungBerhadapan.equals("ya")) {
            chkPosisiHidung.setSelected(true);
        } else {
            chkPosisiHidung.setSelected(false);
        }
        
        if (posisiLeher.equals("ya")) {
            chkPosisiLeher.setSelected(true);
        } else {
            chkPosisiLeher.setSelected(false);
        }
        
        if (posisiTakDipegang.equals("ya")) {
            chkPosisiTakDipegang.setSelected(true);
        } else {
            chkPosisiTakDipegang.setSelected(false);
        }
        
        if (posisiHanya.equals("ya")) {
            chkPosisiHanya.setSelected(true);
        } else {
            chkPosisiHanya.setSelected(false);
        }
        
        if (posisiBibirBawah.equals("ya")) {
            chkPosisiBibir.setSelected(true);
        } else {
            chkPosisiBibir.setSelected(false);
        }
        
        //pelekatan bayi
        if (pelekatanTampak.equals("ya")) {
            chkPelekatanTampak.setSelected(true);
        } else {
            chkPelekatanTampak.setSelected(false);
        }
        
        if (pelekatanTerbuka.equals("ya")) {
            chkPelekatanTerbuka.setSelected(true);
        } else {
            chkPelekatanTerbuka.setSelected(false);
        }
        
        if (pelekatanTerputarKeluar.equals("ya")) {
            chkPelekatanTerputarKeluar.setSelected(true);
        } else {
            chkPelekatanTerputarKeluar.setSelected(false);
        }
        
        if (pelekatanMenempel.equals("ya")) {
            chkPelekatanMenempel.setSelected(true);
        } else {
            chkPelekatanMenempel.setSelected(false);
        }
        
        if (pelekatanLebih.equals("ya")) {
            chkPelekatanLebih.setSelected(true);
        } else {
            chkPelekatanLebih.setSelected(false);
        }
        
        if (pelekatanTakTerbuka.equals("ya")) {
            chkPelekatanTakTerbuka.setSelected(true);
        } else {
            chkPelekatanTakTerbuka.setSelected(false);
        }
        
        if (pelekatanTerputarKedalam.equals("ya")) {
            chkPelekatanTerputarKedalam.setSelected(true);
        } else {
            chkPelekatanTerputarKedalam.setSelected(false);
        }
        
        if (pelekatanTidakMenempel.equals("ya")) {
            chkPelekatanTdkMenempel.setSelected(true);
        } else {
            chkPelekatanTdkMenempel.setSelected(false);
        }
        
        //mengisap
        if (mengisapLambat.equals("ya")) {
            chkMengisapLambat.setSelected(true);
        } else {
            chkMengisapLambat.setSelected(false);
        }
        
        if (mengisapPipiMembulat.equals("ya")) {
            chkMengisapPipiMembulat.setSelected(true);
        } else {
            chkMengisapPipiMembulat.setSelected(false);
        }
        
        if (mengisapMelepaskanSelesai.equals("ya")) {
            chkMengisapMelepaskanSelesai.setSelected(true);
        } else {
            chkMengisapMelepaskanSelesai.setSelected(false);
        }
        
        if (mengisapReflex.equals("ya")) {
            chkMengisapReflex.setSelected(true);
        } else {
            chkMengisapReflex.setSelected(false);
        }
        
        if (mengisapDangkal.equals("ya")) {
            chkMengisapDangkal.setSelected(true);
        } else {
            chkMengisapDangkal.setSelected(false);
        }
        
        if (mengisapPipiTertarik.equals("ya")) {
            chkMengisapPipiTertarik.setSelected(true);
        } else {
            chkMengisapPipiTertarik.setSelected(false);
        }
        
        if (mengisapMelepaskanPayudara.equals("ya")) {
            chkMengisapMelepaskanPayudara.setSelected(true);
        } else {
            chkMengisapMelepaskanPayudara.setSelected(false);
        }
        
        if (mengisapOksitosin.equals("ya")) {
            chkMengisapOksitosin.setSelected(true);
        } else {
            chkMengisapOksitosin.setSelected(false);
        }
    }
    
    private void variabelBersih() {
        nip = "";
        ibuSehat = "";
        ibuRileks = "";
        ibuTerlihat = "";
        ibuSakit = "";
        ibuTegang = "";
        ibuTidak = "";
        bayiSehat = "";
        bayiTenang = "";
        bayiLapar = "";
        bayiMengantuk = "";
        bayiGelisah = "";
        bayiMencari = "";
        payudaraSehat = "";
        payudaraNyaman = "";
        payudaraDitopang = "";
        payudaraPutingKeluar = "";
        payudaraMerah = "";
        payudaraNyeri = "";
        payudaraAerola = "";
        payudaraPutingDatar = "";
        posisiKepala = "";
        posisiDipegang = "";
        posisiSeluruh = "";
        posisiHidungBerhadapan = "";
        posisiLeher = "";
        posisiTakDipegang = "";
        posisiHanya = "";
        posisiBibirBawah = "";
        pelekatanTampak = "";
        pelekatanTerbuka = "";
        pelekatanTerputarKeluar = "";
        pelekatanMenempel = "";
        pelekatanLebih = "";
        pelekatanTakTerbuka = "";
        pelekatanTerputarKedalam = "";
        pelekatanTidakMenempel = "";
        mengisapLambat = "";
        mengisapPipiMembulat = "";
        mengisapMelepaskanSelesai = "";
        mengisapReflex = "";
        mengisapDangkal = "";
        mengisapPipiTertarik = "";
        mengisapMelepaskanPayudara = "";
        mengisapOksitosin = "";
    }
    
    private void ganti() {
        cekData();
        if (Sequel.mengedittf("pengamatan_menyusui_perinatologi", "no_rawat=?", "imd=?, lama_imd=?, alasan=?, asi_eksklusif=?, ibu_sehat=?, ibu_rileks=?, "
                + "ibu_terlihat=?, ibu_sakit=?, ibu_tegang=?, ibu_tidak=?, bayi_sehat=?, bayi_tenang=?, bayi_lapar=?, bayi_mengantuk=?, bayi_gelisah=?, bayi_mencari=?, "
                + "payudara_sehat=?, payudara_nyaman=?, payudara_ditopang=?, payudara_puting_keluar=?, payudara_merah=?, payudara_nyeri=?, payudara_aerola=?, "
                + "payudara_puting_datar=?, posisi_kepala=?, posisi_dipegang=?, posisi_seluruh=?, posisi_hidung_berhadapan=?, posisi_leher=?, posisi_tak_dipegang=?, "
                + "posisi_hanya=?, posisi_bibir_bawah=?, pelekatan_tampak=?, pelekatan_terbuka=?, pelekatan_terputar_keluar=?, pelekatan_menempel=?, pelekatan_lebih=?, "
                + "pelekatan_tak_terbuka=?, pelekatan_terputar_kedalam=?, pelekatan_tidak_menempel=?, mengisap_lambat=?, mengisap_pipi_membulat=?, mengisap_melepaskan_selesai=?, "
                + "mengisap_reflex=?, mengisap_dangkal=?, mengisap_pipi_tertarik=?, mengisap_melepaskan_payudara=?, mengisap_oksitosin=?, lama_menyusui=?, catatan=?, tanggal=?, "
                + "jam=?, nip_perawat=?", 54, new String[]{
                    cmbImd.getSelectedItem().toString(), TlamaImd.getText(), Talasan.getText(), cmbAsi.getSelectedItem().toString(),
                    ibuSehat, ibuRileks, ibuTerlihat, ibuSakit, ibuTegang, ibuTidak, bayiSehat, bayiTenang, bayiLapar, bayiMengantuk, bayiGelisah, bayiMencari, payudaraSehat,
                    payudaraNyaman, payudaraDitopang, payudaraPutingKeluar, payudaraMerah, payudaraNyeri, payudaraAerola, payudaraPutingDatar, posisiKepala, posisiDipegang,
                    posisiSeluruh, posisiHidungBerhadapan, posisiLeher, posisiTakDipegang, posisiHanya, posisiBibirBawah, pelekatanTampak, pelekatanTerbuka, pelekatanTerputarKeluar,
                    pelekatanMenempel, pelekatanLebih, pelekatanTakTerbuka, pelekatanTerputarKedalam, pelekatanTidakMenempel, mengisapLambat, mengisapPipiMembulat,
                    mengisapMelepaskanSelesai, mengisapReflex, mengisapDangkal, mengisapPipiTertarik, mengisapMelepaskanPayudara, mengisapOksitosin, TlamaWkt.getText(),
                    Tcatatan.getText(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), nip,
                    tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 0).toString()
                }) == true) {

            Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Lembaran Bantuan Pengamatan Menyusui", "Ganti");
            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
        }
    }
   
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from pengamatan_menyusui_perinatologi where no_rawat=?", 1, new String[]{
                tbPengamatan.getValueAt(tbPengamatan.getSelectedRow(), 0).toString()
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
}
