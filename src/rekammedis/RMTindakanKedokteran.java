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
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgKabupaten;
import simrskhanza.DlgKecamatan;
import simrskhanza.DlgKelurahan;

/**
 *
 * @author perpustakaan
 */
public final class RMTindakanKedokteran extends javax.swing.JDialog {
    private DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private String kdkel = "", kdkec = "", kdkab = "";
    private DlgKabupaten kab = new DlgKabupaten(null, false);
    private DlgKecamatan kec = new DlgKecamatan(null, false);
    private DlgKelurahan kel = new DlgKelurahan(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMTindakanKedokteran(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.RM", "Nama Pasien", "Nama Png. Jawab", "Umur PJ", "Alamat PJ", "Hubungan Dg. Pasien", "Jns. Tindakan",
            "tgl_surat", "jam_surat", "nm_penjab", "umur_penjab", "jk_penjab", "alamat_penjab", "kd_kel", "kd_kec", "kd_kab", "hubungan_dg_pasien",
            "jns_surat", "kasus_tindakan", "nm_tindakan_kedokteran", "alasan_penolakan", "nip_dokter_pelaksana", "nip_pemberi_info",
            "penerima_info", "isi_info_diagnosis_kerja", "isi_info_dasar_diagnosis", "isi_info_tindakan", "isi_info_indikasi", "isi_info_tatacara",
            "isi_info_tujuan", "isi_info_resiko", "isi_info_komplikasi", "isi_info_prognosis", "isi_info_alternatif", "isi_info_lainlain",
            "waktu_simpan", "tgl_persetujuan", "jam_persetujuan", "tgl_penolakan", "jam_penolakan", "nmkel", "nmkec", "nmkab", "nmdokter", "nmpetugas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbTindakan.setModel(tabMode);
        tbTindakan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 46; i++) {
            TableColumn column = tbTindakan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setPreferredWidth(210);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(150);
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
            } 
        }
        tbTindakan.setDefaultRenderer(Object.class, new WarnaTable());
        
        nmPJ.setDocument(new batasInput((int) 150).getKata(nmPJ));
        umurPJ.setDocument(new batasInput((int) 3).getKata(umurPJ));
        alamatPJ.setDocument(new batasInput((int) 255).getKata(alamatPJ));
        tindakanDokter.setDocument(new batasInput((int) 255).getKata(tindakanDokter));
        alasanTolak.setDocument(new batasInput((int) 255).getKata(alasanTolak));
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
        
        kel.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMTindakanKedokteran")) {
                    kdkel = "";
                    if (kel.getTable().getSelectedRow() != -1) {
                        nmkel.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 0).toString());
                        kdkel = Sequel.cariIsi("select kd_kel from kelurahan where nm_kel='" + nmkel.getText() + "'");
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
        
        kec.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMTindakanKedokteran")) {
                    kdkec = "";
                    if (kec.getTable().getSelectedRow() != -1) {
                        nmkec.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 0).toString());
                        kdkec = Sequel.cariIsi("select kd_kec from kecamatan where nm_kec='" + nmkec.getText() + "'");
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
                if (akses.getform().equals("RMTindakanKedokteran")) {
                    kdkab = "";
                    if (kab.getTable().getSelectedRow() != -1) {
                        nmkab.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 0).toString());
                        kdkab = Sequel.cariIsi("select kd_kab from kabupaten where nm_kab='" + nmkab.getText() + "'");
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    nip_pemberi.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                    nmpemberi.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    BtnPemberi.requestFocus();
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
                if (akses.getform().equals("RMTindakanKedokteran")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        kddokter_pelaksana.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        nmdokter_pelaksana.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
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
        TabRawat = new javax.swing.JTabbedPane();
        FormTindakan = new widget.InternalFrame();
        ScrollTriase1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel46 = new widget.Label();
        nmPJ = new widget.TextBox();
        jLabel47 = new widget.Label();
        umurPJ = new widget.TextBox();
        jLabel48 = new widget.Label();
        jLabel49 = new widget.Label();
        jkPJ = new widget.ComboBox();
        jLabel50 = new widget.Label();
        alamatPJ = new widget.TextBox();
        jLabel51 = new widget.Label();
        nmkel = new widget.TextBox();
        BtnKelurahan = new widget.Button();
        jLabel52 = new widget.Label();
        nmkec = new widget.TextBox();
        BtnKecamatan = new widget.Button();
        jLabel53 = new widget.Label();
        nmkab = new widget.TextBox();
        BtnKabupaten = new widget.Button();
        jLabel54 = new widget.Label();
        hubungan = new widget.ComboBox();
        jLabel55 = new widget.Label();
        cmbTindakan = new widget.ComboBox();
        jLabel56 = new widget.Label();
        tindakanDokter = new widget.TextBox();
        jLabel57 = new widget.Label();
        alasanTolak = new widget.TextBox();
        jLabel58 = new widget.Label();
        TglBeriTindakan = new widget.Tanggal();
        jLabel12 = new widget.Label();
        CmbJam1 = new widget.ComboBox();
        CmbMenit1 = new widget.ComboBox();
        CmbDetik1 = new widget.ComboBox();
        jLabel59 = new widget.Label();
        kddokter_pelaksana = new widget.TextBox();
        nmdokter_pelaksana = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel60 = new widget.Label();
        nip_pemberi = new widget.TextBox();
        nmpemberi = new widget.TextBox();
        BtnPemberi = new widget.Button();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        TDiagKerja = new widget.TextArea();
        scrollPane4 = new widget.ScrollPane();
        TDasarDiag = new widget.TextArea();
        jLabel63 = new widget.Label();
        scrollPane5 = new widget.ScrollPane();
        Ttindakan = new widget.TextArea();
        jLabel64 = new widget.Label();
        scrollPane6 = new widget.ScrollPane();
        Tindikasi = new widget.TextArea();
        jLabel65 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        Ttatacara = new widget.TextArea();
        jLabel66 = new widget.Label();
        scrollPane8 = new widget.ScrollPane();
        Ttujuan = new widget.TextArea();
        jLabel67 = new widget.Label();
        jLabel68 = new widget.Label();
        scrollPane9 = new widget.ScrollPane();
        Tresiko = new widget.TextArea();
        jLabel69 = new widget.Label();
        scrollPane10 = new widget.ScrollPane();
        Tkomplikasi = new widget.TextArea();
        jLabel70 = new widget.Label();
        scrollPane11 = new widget.ScrollPane();
        Tprognosis = new widget.TextArea();
        jLabel71 = new widget.Label();
        scrollPane12 = new widget.ScrollPane();
        Talternatif = new widget.TextArea();
        jLabel72 = new widget.Label();
        scrollPane13 = new widget.ScrollPane();
        Tlain = new widget.TextArea();
        jLabel73 = new widget.Label();
        TglSetuju = new widget.Tanggal();
        jLabel74 = new widget.Label();
        CmbJam2 = new widget.ComboBox();
        CmbMenit2 = new widget.ComboBox();
        CmbDetik2 = new widget.ComboBox();
        jLabel75 = new widget.Label();
        TglTolak = new widget.Tanggal();
        jLabel76 = new widget.Label();
        CmbJam3 = new widget.ComboBox();
        CmbMenit3 = new widget.ComboBox();
        CmbDetik3 = new widget.ComboBox();
        internalFrame4 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbTindakan = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
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
        BtnEdit = new widget.Button();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Tindakan Kedokteran ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        FormTindakan.setBorder(null);
        FormTindakan.setName("FormTindakan"); // NOI18N
        FormTindakan.setLayout(new java.awt.BorderLayout(1, 1));

        ScrollTriase1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        ScrollTriase1.setName("ScrollTriase1"); // NOI18N
        ScrollTriase1.setOpaque(true);
        ScrollTriase1.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBorder(null);
        FormInput.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis");
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1324));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 130, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(136, 10, 122, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(332, 10, 360, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(260, 10, 70, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Nama Png. Jwb. :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(0, 38, 130, 23);

        nmPJ.setForeground(new java.awt.Color(0, 0, 0));
        nmPJ.setHighlighter(null);
        nmPJ.setName("nmPJ"); // NOI18N
        nmPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmPJKeyPressed(evt);
            }
        });
        FormInput.add(nmPJ);
        nmPJ.setBounds(136, 38, 420, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Umur :");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(0, 66, 130, 23);

        umurPJ.setForeground(new java.awt.Color(0, 0, 0));
        umurPJ.setHighlighter(null);
        umurPJ.setName("umurPJ"); // NOI18N
        umurPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                umurPJKeyPressed(evt);
            }
        });
        FormInput.add(umurPJ);
        umurPJ.setBounds(136, 66, 50, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("Tahun");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(193, 66, 40, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Jenis Kelamin :");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(260, 66, 80, 23);

        jkPJ.setForeground(new java.awt.Color(0, 0, 0));
        jkPJ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-laki", "Perempuan" }));
        jkPJ.setName("jkPJ"); // NOI18N
        jkPJ.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(jkPJ);
        jkPJ.setBounds(345, 66, 100, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Alamat Png. Jwb. :");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(0, 94, 130, 23);

        alamatPJ.setForeground(new java.awt.Color(0, 0, 0));
        alamatPJ.setHighlighter(null);
        alamatPJ.setName("alamatPJ"); // NOI18N
        alamatPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                alamatPJKeyPressed(evt);
            }
        });
        FormInput.add(alamatPJ);
        alamatPJ.setBounds(136, 94, 630, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Kelurahan :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(0, 122, 130, 23);

        nmkel.setEditable(false);
        nmkel.setForeground(new java.awt.Color(0, 0, 0));
        nmkel.setHighlighter(null);
        nmkel.setName("nmkel"); // NOI18N
        FormInput.add(nmkel);
        nmkel.setBounds(136, 122, 340, 23);

        BtnKelurahan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKelurahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnKelurahan.setMnemonic('2');
        BtnKelurahan.setToolTipText("ALt+2");
        BtnKelurahan.setName("BtnKelurahan"); // NOI18N
        BtnKelurahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKelurahanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKelurahan);
        BtnKelurahan.setBounds(480, 122, 28, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Kecamatan :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(0, 150, 130, 23);

        nmkec.setEditable(false);
        nmkec.setForeground(new java.awt.Color(0, 0, 0));
        nmkec.setHighlighter(null);
        nmkec.setName("nmkec"); // NOI18N
        FormInput.add(nmkec);
        nmkec.setBounds(136, 150, 340, 23);

        BtnKecamatan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKecamatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnKecamatan.setMnemonic('3');
        BtnKecamatan.setToolTipText("ALt+3");
        BtnKecamatan.setName("BtnKecamatan"); // NOI18N
        BtnKecamatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKecamatanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKecamatan);
        BtnKecamatan.setBounds(480, 150, 28, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Kabupaten :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(0, 178, 130, 23);

        nmkab.setEditable(false);
        nmkab.setForeground(new java.awt.Color(0, 0, 0));
        nmkab.setHighlighter(null);
        nmkab.setName("nmkab"); // NOI18N
        FormInput.add(nmkab);
        nmkab.setBounds(136, 178, 340, 23);

        BtnKabupaten.setForeground(new java.awt.Color(0, 0, 0));
        BtnKabupaten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnKabupaten.setMnemonic('4');
        BtnKabupaten.setToolTipText("ALt+4");
        BtnKabupaten.setName("BtnKabupaten"); // NOI18N
        BtnKabupaten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKabupatenActionPerformed(evt);
            }
        });
        FormInput.add(BtnKabupaten);
        BtnKabupaten.setBounds(480, 178, 28, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Hubungan dg. Pasien :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(0, 206, 130, 23);

        hubungan.setForeground(new java.awt.Color(0, 0, 0));
        hubungan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Saudara", "Ayah", "Ibu", "Anak", "Suami", "Istri", "Sepupu", "Pasien Sendiri", "Paman", "Bibi", "Kakek", "Nenek", "Teman", "Tetangga", "Ipar", "Besan", "Menantu", "Mertua", "Keponakan", "Kakak", "Adik", "Cucu" }));
        hubungan.setName("hubungan"); // NOI18N
        hubungan.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(hubungan);
        hubungan.setBounds(136, 206, 110, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Jenis Tindakan :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(250, 206, 90, 23);

        cmbTindakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbTindakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PERSETUJUAN", "PENOLAKAN" }));
        cmbTindakan.setName("cmbTindakan"); // NOI18N
        cmbTindakan.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTindakanActionPerformed(evt);
            }
        });
        FormInput.add(cmbTindakan);
        cmbTindakan.setBounds(345, 206, 110, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Tindakan Kedokteran :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(0, 234, 130, 23);

        tindakanDokter.setForeground(new java.awt.Color(0, 0, 0));
        tindakanDokter.setHighlighter(null);
        tindakanDokter.setName("tindakanDokter"); // NOI18N
        tindakanDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tindakanDokterKeyPressed(evt);
            }
        });
        FormInput.add(tindakanDokter);
        tindakanDokter.setBounds(136, 234, 630, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Alasan Penolakan :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(0, 262, 130, 23);

        alasanTolak.setForeground(new java.awt.Color(0, 0, 0));
        alasanTolak.setHighlighter(null);
        alasanTolak.setName("alasanTolak"); // NOI18N
        alasanTolak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                alasanTolakKeyPressed(evt);
            }
        });
        FormInput.add(alasanTolak);
        alasanTolak.setBounds(136, 262, 630, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Tgl. Beri Info Tndkn. :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(0, 290, 130, 23);

        TglBeriTindakan.setEditable(false);
        TglBeriTindakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-11-2023" }));
        TglBeriTindakan.setDisplayFormat("dd-MM-yyyy");
        TglBeriTindakan.setName("TglBeriTindakan"); // NOI18N
        TglBeriTindakan.setOpaque(false);
        TglBeriTindakan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglBeriTindakanItemStateChanged(evt);
            }
        });
        TglBeriTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglBeriTindakanKeyPressed(evt);
            }
        });
        FormInput.add(TglBeriTindakan);
        TglBeriTindakan.setBounds(136, 290, 100, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Jam :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(261, 290, 30, 23);

        CmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        CmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam1.setName("CmbJam1"); // NOI18N
        CmbJam1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJam1KeyPressed(evt);
            }
        });
        FormInput.add(CmbJam1);
        CmbJam1.setBounds(295, 290, 45, 23);

        CmbMenit1.setForeground(new java.awt.Color(0, 0, 0));
        CmbMenit1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit1.setName("CmbMenit1"); // NOI18N
        CmbMenit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbMenit1KeyPressed(evt);
            }
        });
        FormInput.add(CmbMenit1);
        CmbMenit1.setBounds(345, 290, 45, 23);

        CmbDetik1.setForeground(new java.awt.Color(0, 0, 0));
        CmbDetik1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik1.setName("CmbDetik1"); // NOI18N
        CmbDetik1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbDetik1ActionPerformed(evt);
            }
        });
        CmbDetik1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbDetik1KeyPressed(evt);
            }
        });
        FormInput.add(CmbDetik1);
        CmbDetik1.setBounds(395, 290, 45, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Dokter Pelaksana :");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(0, 318, 130, 23);

        kddokter_pelaksana.setEditable(false);
        kddokter_pelaksana.setForeground(new java.awt.Color(0, 0, 0));
        kddokter_pelaksana.setName("kddokter_pelaksana"); // NOI18N
        FormInput.add(kddokter_pelaksana);
        kddokter_pelaksana.setBounds(136, 318, 100, 23);

        nmdokter_pelaksana.setEditable(false);
        nmdokter_pelaksana.setForeground(new java.awt.Color(0, 0, 0));
        nmdokter_pelaksana.setName("nmdokter_pelaksana"); // NOI18N
        FormInput.add(nmdokter_pelaksana);
        nmdokter_pelaksana.setBounds(240, 318, 370, 23);

        BtnDokter.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnDokter.setMnemonic('4');
        BtnDokter.setToolTipText("ALt+4");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(615, 318, 28, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Pemberi Informasi :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(0, 346, 130, 23);

        nip_pemberi.setEditable(false);
        nip_pemberi.setForeground(new java.awt.Color(0, 0, 0));
        nip_pemberi.setName("nip_pemberi"); // NOI18N
        FormInput.add(nip_pemberi);
        nip_pemberi.setBounds(136, 346, 100, 23);

        nmpemberi.setEditable(false);
        nmpemberi.setForeground(new java.awt.Color(0, 0, 0));
        nmpemberi.setName("nmpemberi"); // NOI18N
        FormInput.add(nmpemberi);
        nmpemberi.setBounds(240, 346, 370, 23);

        BtnPemberi.setForeground(new java.awt.Color(0, 0, 0));
        BtnPemberi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnPemberi.setMnemonic('4');
        BtnPemberi.setToolTipText("ALt+4");
        BtnPemberi.setName("BtnPemberi"); // NOI18N
        BtnPemberi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPemberiActionPerformed(evt);
            }
        });
        FormInput.add(BtnPemberi);
        BtnPemberi.setBounds(615, 346, 28, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Diagnosis Kerja/ :");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(0, 374, 130, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Diagnosis Banding  ");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(0, 389, 130, 23);

        scrollPane3.setName("scrollPane3"); // NOI18N

        TDiagKerja.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TDiagKerja.setColumns(20);
        TDiagKerja.setRows(5);
        TDiagKerja.setName("TDiagKerja"); // NOI18N
        TDiagKerja.setPreferredSize(new java.awt.Dimension(162, 250));
        TDiagKerja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagKerjaKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(TDiagKerja);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(136, 374, 630, 80);

        scrollPane4.setName("scrollPane4"); // NOI18N

        TDasarDiag.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TDasarDiag.setColumns(20);
        TDasarDiag.setRows(5);
        TDasarDiag.setName("TDasarDiag"); // NOI18N
        TDasarDiag.setPreferredSize(new java.awt.Dimension(162, 250));
        TDasarDiag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDasarDiagKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(TDasarDiag);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(136, 460, 630, 80);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Dasar Diagnosis :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 460, 130, 23);

        scrollPane5.setName("scrollPane5"); // NOI18N

        Ttindakan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Ttindakan.setColumns(20);
        Ttindakan.setRows(5);
        Ttindakan.setName("Ttindakan"); // NOI18N
        Ttindakan.setPreferredSize(new java.awt.Dimension(162, 250));
        Ttindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtindakanKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(Ttindakan);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(136, 546, 630, 80);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Tindakan Kedokteran :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 546, 130, 23);

        scrollPane6.setName("scrollPane6"); // NOI18N

        Tindikasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tindikasi.setColumns(20);
        Tindikasi.setRows(5);
        Tindikasi.setName("Tindikasi"); // NOI18N
        Tindikasi.setPreferredSize(new java.awt.Dimension(162, 250));
        Tindikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindikasiKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(Tindikasi);

        FormInput.add(scrollPane6);
        scrollPane6.setBounds(136, 632, 630, 80);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Indikasi Tindakan :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 632, 130, 23);

        scrollPane7.setName("scrollPane7"); // NOI18N

        Ttatacara.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Ttatacara.setColumns(20);
        Ttatacara.setRows(5);
        Ttatacara.setName("Ttatacara"); // NOI18N
        Ttatacara.setPreferredSize(new java.awt.Dimension(162, 250));
        Ttatacara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtatacaraKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(Ttatacara);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(136, 718, 630, 80);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Tata Cara :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 718, 130, 23);

        scrollPane8.setName("scrollPane8"); // NOI18N

        Ttujuan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Ttujuan.setColumns(20);
        Ttujuan.setRows(5);
        Ttujuan.setName("Ttujuan"); // NOI18N
        Ttujuan.setPreferredSize(new java.awt.Dimension(162, 250));
        Ttujuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtujuanKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(Ttujuan);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(136, 804, 630, 80);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Tujuan :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 804, 130, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Resiko Tindakan :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 890, 130, 23);

        scrollPane9.setName("scrollPane9"); // NOI18N

        Tresiko.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tresiko.setColumns(20);
        Tresiko.setRows(5);
        Tresiko.setName("Tresiko"); // NOI18N
        Tresiko.setPreferredSize(new java.awt.Dimension(162, 250));
        Tresiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TresikoKeyPressed(evt);
            }
        });
        scrollPane9.setViewportView(Tresiko);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(136, 890, 630, 80);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Komplikasi Tindakan :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 976, 130, 23);

        scrollPane10.setName("scrollPane10"); // NOI18N

        Tkomplikasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkomplikasi.setColumns(20);
        Tkomplikasi.setRows(5);
        Tkomplikasi.setName("Tkomplikasi"); // NOI18N
        Tkomplikasi.setPreferredSize(new java.awt.Dimension(162, 250));
        Tkomplikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkomplikasiKeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(Tkomplikasi);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(136, 976, 630, 80);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Prognosis :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 1062, 130, 23);

        scrollPane11.setName("scrollPane11"); // NOI18N

        Tprognosis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tprognosis.setColumns(20);
        Tprognosis.setRows(5);
        Tprognosis.setName("Tprognosis"); // NOI18N
        Tprognosis.setPreferredSize(new java.awt.Dimension(162, 250));
        Tprognosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprognosisKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(Tprognosis);

        FormInput.add(scrollPane11);
        scrollPane11.setBounds(136, 1062, 630, 80);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Alternatif Tindakan :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 1148, 130, 23);

        scrollPane12.setName("scrollPane12"); // NOI18N

        Talternatif.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Talternatif.setColumns(20);
        Talternatif.setRows(5);
        Talternatif.setName("Talternatif"); // NOI18N
        Talternatif.setPreferredSize(new java.awt.Dimension(162, 250));
        Talternatif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalternatifKeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(Talternatif);

        FormInput.add(scrollPane12);
        scrollPane12.setBounds(136, 1148, 630, 80);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Lain - Lain :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 1234, 130, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N

        Tlain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tlain.setColumns(20);
        Tlain.setRows(5);
        Tlain.setName("Tlain"); // NOI18N
        Tlain.setPreferredSize(new java.awt.Dimension(162, 250));
        scrollPane13.setViewportView(Tlain);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(136, 1234, 630, 80);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Tgl. Persetujuan Tndkn. :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(520, 122, 130, 23);

        TglSetuju.setEditable(false);
        TglSetuju.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-11-2023" }));
        TglSetuju.setDisplayFormat("dd-MM-yyyy");
        TglSetuju.setName("TglSetuju"); // NOI18N
        TglSetuju.setOpaque(false);
        TglSetuju.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglSetujuItemStateChanged(evt);
            }
        });
        TglSetuju.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglSetujuKeyPressed(evt);
            }
        });
        FormInput.add(TglSetuju);
        TglSetuju.setBounds(658, 122, 100, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Jam Persetujuan Tndkn. :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(520, 150, 130, 23);

        CmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        CmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam2.setName("CmbJam2"); // NOI18N
        CmbJam2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJam2KeyPressed(evt);
            }
        });
        FormInput.add(CmbJam2);
        CmbJam2.setBounds(658, 150, 45, 23);

        CmbMenit2.setForeground(new java.awt.Color(0, 0, 0));
        CmbMenit2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit2.setName("CmbMenit2"); // NOI18N
        CmbMenit2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbMenit2KeyPressed(evt);
            }
        });
        FormInput.add(CmbMenit2);
        CmbMenit2.setBounds(708, 150, 45, 23);

        CmbDetik2.setForeground(new java.awt.Color(0, 0, 0));
        CmbDetik2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik2.setName("CmbDetik2"); // NOI18N
        CmbDetik2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbDetik2ActionPerformed(evt);
            }
        });
        CmbDetik2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbDetik2KeyPressed(evt);
            }
        });
        FormInput.add(CmbDetik2);
        CmbDetik2.setBounds(759, 150, 45, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Tgl. Penolakan Tndkn. :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(520, 178, 130, 23);

        TglTolak.setEditable(false);
        TglTolak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-11-2023" }));
        TglTolak.setDisplayFormat("dd-MM-yyyy");
        TglTolak.setName("TglTolak"); // NOI18N
        TglTolak.setOpaque(false);
        TglTolak.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglTolakItemStateChanged(evt);
            }
        });
        TglTolak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglTolakKeyPressed(evt);
            }
        });
        FormInput.add(TglTolak);
        TglTolak.setBounds(658, 178, 100, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Jam Penolakan Tndkn. :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(520, 206, 130, 23);

        CmbJam3.setForeground(new java.awt.Color(0, 0, 0));
        CmbJam3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam3.setName("CmbJam3"); // NOI18N
        CmbJam3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJam3KeyPressed(evt);
            }
        });
        FormInput.add(CmbJam3);
        CmbJam3.setBounds(658, 206, 45, 23);

        CmbMenit3.setForeground(new java.awt.Color(0, 0, 0));
        CmbMenit3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit3.setName("CmbMenit3"); // NOI18N
        CmbMenit3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbMenit3KeyPressed(evt);
            }
        });
        FormInput.add(CmbMenit3);
        CmbMenit3.setBounds(708, 206, 45, 23);

        CmbDetik3.setForeground(new java.awt.Color(0, 0, 0));
        CmbDetik3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik3.setName("CmbDetik3"); // NOI18N
        CmbDetik3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbDetik3ActionPerformed(evt);
            }
        });
        CmbDetik3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbDetik3KeyPressed(evt);
            }
        });
        FormInput.add(CmbDetik3);
        CmbDetik3.setBounds(759, 206, 45, 23);

        ScrollTriase1.setViewportView(FormInput);

        FormTindakan.add(ScrollTriase1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Tindakan", FormTindakan);

        internalFrame4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbTindakan.setAutoCreateRowSorter(true);
        tbTindakan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTindakan.setName("tbTindakan"); // NOI18N
        tbTindakan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTindakanMouseClicked(evt);
            }
        });
        tbTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTindakanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbTindakan);

        internalFrame4.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl.Triase :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-11-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-11-2023" }));
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

        internalFrame4.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Tindakan", internalFrame4);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
//            if (Sequel.menyimpantf("triase_igd", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 71, new String[]{
//                        TNoRw.getText(), Valid.SetTgl(tgl_kunjungan.getSelectedItem() + "") + " " + tgl_kunjungan.getSelectedItem().toString().substring(11, 19), cmbCaraMasuk.getSelectedItem().toString(),
//                        sdh_terpasang.getText(), cmbAlasanKedatangan.getSelectedItem().toString(), rujukan_dari.getText(), dijemput_oleh.getText(), cmbKendaraan.getSelectedItem().toString(), bkn_ambulan.getText(), 
//                        nm_pengantar.getText(), tlpn_pengantar.getText(), cmbKasus.getSelectedItem().toString(), kll_tunggal, tmpt_kejadian_tunggal.getText(), Valid.SetTgl(tgl_kejadian_tunggal.getSelectedItem() + "") + " " + tgl_kejadian_tunggal.getSelectedItem().toString().substring(11, 19),
//                        kll, versus1.getText(), versus2.getText(), tmpt_kejadian.getText(), Valid.SetTgl(tgl_kejadian.getSelectedItem() + "") + " " + tgl_kejadian.getSelectedItem().toString().substring(11, 19), 
//                        jatuh, ket_jatuh.getText(), luka, ket_luka.getText(), trauma_listrik, ket_trauma_listrik.getText(), trauma_zat, ket_trauma_zat.getText(), trauma_lain, ket_trauma_lain.getText(),
//                        Tkeluhan_utama.getText(), pacs1, pacs2, pacs3, pacs4, cmbKesadaran.getSelectedItem().toString(), tkann_darah.getText(), nadi.getText(), pernapasan.getText(),
//                        temperatur.getText(), saturasi.getText(), cmbNyeri.getSelectedItem().toString(), skor0_sadar, skor0_100, skor0_101, skor0_19, skor0_35, skor0_96, skor102, skor20, skor94,
//                        skor99, skor22, skor92, skor3_sadar, skor3_35, skor3_92, skortotal, Tcttn_khusus.getText(), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
//                        resus, nonresus, klinik, doa, nip, tgl_kejadian_tggl, tgl_kejadian_kll, Tvas.getText(), bb.getText(), tb.getText(), Sequel.cariIsi("select now()")
//                    }) == true) {
//                
//                TCari.setText(TNoRw.getText());
//                BtnBatalActionPerformed(null);
//                tampil();
//                TabRawat.setSelectedIndex(1);                
//            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbTindakan.getSelectedRow() > -1) {
            if (akses.getkode().equals("Admin Utama")) {
                hapus();
            } else {
//                if (nip.equals(tbTriase.getValueAt(tbTriase.getSelectedRow(), 67).toString())) {
//                    hapus();
//                } else {
//                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh Petugas Triase yang bersangkutan..!!");
//                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
//            if (tbTriase.getSelectedRow() > -1) {
//                if (akses.getkode().equals("Admin Utama")) {
//                    ganti();
//                } else {
//                    if (nip.equals(tbTriase.getValueAt(tbTriase.getSelectedRow(), 67).toString())) {
//                        ganti();
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh Petugas Triase yang bersangkutan..!!");
//                    }
//                }
//            } else {
//                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
//            }
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
        if (tbTindakan.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptTriaseIGD.jasper", "report", "::[ Laporan Data Triase IGD ]::",
                    "SELECT ti.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, date_format(ti.tanggal,'Tanggal : %d-%m-%Y    Pukul : %H:%i') kontak_awal, "
                    + "if(ti.cara_masuk='','-',ti.cara_masuk) cr_msk, ti.sudah_terpasang, concat('Nama : ',ti.nm_pengantar,'    No. Telp : ',ti.telp_pengantar) iden_pengntar, "
                    + "ti.kasus, ti.keluhan_utama, if(ti.kesadaran='','KESADARAN : -',concat('KESADARAN : ',ti.kesadaran)) kesadaran, ti.td, ti.nadi, ti.napas, ti.temperatur, "
                    + "ti.saturasi, ti.nyeri, ti.vas, if(ti.skor0_sadar_penuh='ya','V','') skor0_sadar, if(ti.skor0_100='ya','V','') skor0_100, if(ti.skor0_101='ya','V','') skor0_101, "
                    + "if(ti.skor0_19='ya','V','') skor0_19, if(ti.skor0_35_3='ya','V','') skor0_35, if(ti.skor0_96_100='ya','V','') skor0_96, if(ti.skor1_102='ya','V','') skor1_102, "
                    + "if(ti.skor1_20_21='ya','V','') skor1_20, if(ti.skor1_94_95='ya','V','') skor1_94, if(ti.skor2_99='ya','V','') skor2_99, if(ti.skor2_22='ya','V','') skor2_22, "
                    + "if(ti.skor2_92_93='ya','V','') skor2_92, if(ti.skor3_selain='ya','V','') skor3_selain, if(ti.skor3_35_3='ya','V','') skor3_35, if(ti.skor3_92='ya','V','') skor3_92, "
                    + "ti.catatan, ti.pukul, if(ti.triase_resusitasi='ya','V','') resus, if(ti.triase_non_resusitasi='ya','V','') nonresus, if(ti.triase_klinik='ya','V','') klinik, "
                    + "if(ti.triase_doa='ya','V','') doa, pg.nama petgas, if(ti.kll_tunggal='ya','V','') kll_tunggal, if(ti.kll_versus='ya','V','') kll_versus, if(ti.jatuh='ya','V','') jatuh, "
                    + "if(ti.luka_bakar='ya','V','') luka, if(ti.trauma_listrik='ya','V','') trauma_listrik, if(ti.trauma_zat_kimia='ya','V','') trauma_zat, if(ti.trauma_lain='ya','V','') trauma_lain, "
                    + "ti.bb, ti.tb from triase_igd ti inner join reg_periksa rp on rp.no_rawat=ti.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg on nik=ti.nip_petugas where ti.no_rawat='" + tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 0).toString() + "'", param);
            
            BtnBatalActionPerformed(null);
            TabRawat.setSelectedIndex(1);
            tampil();            
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data terlebih dahulu..!!!!");
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        
        if (Sequel.cariInteger("select count(-1) from triase_igd where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from triase_igd where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void tbTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTindakanKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTindakanKeyPressed

    private void tbTindakanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTindakanMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {                
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbTindakan.getSelectedColumn() == 0)) {
                TabRawat.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tbTindakanMouseClicked

    private void nmPJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmPJKeyPressed
        Valid.pindah(evt, nmPJ, umurPJ);
    }//GEN-LAST:event_nmPJKeyPressed

    private void umurPJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_umurPJKeyPressed
        Valid.pindah(evt, nmPJ, jkPJ);
    }//GEN-LAST:event_umurPJKeyPressed

    private void alamatPJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_alamatPJKeyPressed
        Valid.pindah(evt, jkPJ, BtnKelurahan);
    }//GEN-LAST:event_alamatPJKeyPressed

    private void BtnKelurahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKelurahanActionPerformed
        akses.setform("RMTindakanKedokteran");
        kel.setSize(703, 384);
        kel.setLocationRelativeTo(internalFrame1);
        kel.setVisible(true);
        kel.TCari.requestFocus();
    }//GEN-LAST:event_BtnKelurahanActionPerformed

    private void BtnKecamatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKecamatanActionPerformed
        akses.setform("RMTindakanKedokteran");
        kec.setSize(703, 384);
        kec.setLocationRelativeTo(internalFrame1);
        kec.setVisible(true);
        kec.TCari.requestFocus();
    }//GEN-LAST:event_BtnKecamatanActionPerformed

    private void BtnKabupatenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKabupatenActionPerformed
        akses.setform("RMTindakanKedokteran");
        kab.setSize(703, 384);
        kab.setLocationRelativeTo(internalFrame1);
        kab.setVisible(true);
        kab.TCari.requestFocus();
    }//GEN-LAST:event_BtnKabupatenActionPerformed

    private void cmbTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTindakanActionPerformed
        TglSetuju.setDate(new Date());
        CmbJam2.setSelectedIndex(0);
        CmbMenit2.setSelectedIndex(0);
        CmbMenit2.setSelectedIndex(0);
        
        TglTolak.setDate(new Date());
        CmbJam3.setSelectedIndex(0);
        CmbMenit3.setSelectedIndex(0);
        CmbMenit3.setSelectedIndex(0);
        
        if(cmbTindakan.getSelectedIndex() == 0) {
            TglSetuju.setEnabled(true);
            CmbJam2.setEnabled(true);
            CmbMenit2.setEnabled(true);
            CmbDetik2.setEnabled(true);
            
            TglTolak.setEnabled(false);
            CmbJam3.setEnabled(false);
            CmbMenit3.setEnabled(false);
            CmbDetik3.setEnabled(false);
        } else {
            TglSetuju.setEnabled(false);
            CmbJam2.setEnabled(false);
            CmbMenit2.setEnabled(false);
            CmbDetik2.setEnabled(false);
            
            TglTolak.setEnabled(true);
            CmbJam3.setEnabled(true);
            CmbMenit3.setEnabled(true);
            CmbDetik3.setEnabled(true);
        }
    }//GEN-LAST:event_cmbTindakanActionPerformed

    private void tindakanDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tindakanDokterKeyPressed
        Valid.pindah(evt, cmbTindakan, alasanTolak);
    }//GEN-LAST:event_tindakanDokterKeyPressed

    private void alasanTolakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_alasanTolakKeyPressed
        Valid.pindah(evt, tindakanDokter, CmbJam1);
    }//GEN-LAST:event_alasanTolakKeyPressed

    private void TglBeriTindakanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglBeriTindakanItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglBeriTindakanItemStateChanged

    private void TglBeriTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglBeriTindakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglBeriTindakanKeyPressed

    private void CmbJam1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJam1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbJam1KeyPressed

    private void CmbMenit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbMenit1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbMenit1KeyPressed

    private void CmbDetik1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbDetik1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbDetik1ActionPerformed

    private void CmbDetik1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbDetik1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbDetik1KeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        akses.setform("RMTindakanKedokteran");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnPemberiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPemberiActionPerformed
        akses.setform("RMTindakanKedokteran");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPemberiActionPerformed

    private void TDiagKerjaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagKerjaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TDasarDiag.requestFocus();
        }
    }//GEN-LAST:event_TDiagKerjaKeyPressed

    private void TDasarDiagKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDasarDiagKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Ttindakan.requestFocus();
        }
    }//GEN-LAST:event_TDasarDiagKeyPressed

    private void TtindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tindikasi.requestFocus();
        }
    }//GEN-LAST:event_TtindakanKeyPressed

    private void TindikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindikasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Ttatacara.requestFocus();
        }
    }//GEN-LAST:event_TindikasiKeyPressed

    private void TtatacaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtatacaraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Ttujuan.requestFocus();
        }
    }//GEN-LAST:event_TtatacaraKeyPressed

    private void TtujuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtujuanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tresiko.requestFocus();
        }
    }//GEN-LAST:event_TtujuanKeyPressed

    private void TresikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TresikoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tkomplikasi.requestFocus();
        }
    }//GEN-LAST:event_TresikoKeyPressed

    private void TkomplikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkomplikasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tprognosis.requestFocus();
        }
    }//GEN-LAST:event_TkomplikasiKeyPressed

    private void TprognosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprognosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Talternatif.requestFocus();
        }
    }//GEN-LAST:event_TprognosisKeyPressed

    private void TalternatifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalternatifKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tlain.requestFocus();
        }
    }//GEN-LAST:event_TalternatifKeyPressed

    private void TglSetujuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglSetujuItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglSetujuItemStateChanged

    private void TglSetujuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglSetujuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglSetujuKeyPressed

    private void CmbJam2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJam2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbJam2KeyPressed

    private void CmbMenit2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbMenit2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbMenit2KeyPressed

    private void CmbDetik2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbDetik2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbDetik2ActionPerformed

    private void CmbDetik2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbDetik2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbDetik2KeyPressed

    private void TglTolakItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglTolakItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglTolakItemStateChanged

    private void TglTolakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglTolakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglTolakKeyPressed

    private void CmbJam3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJam3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbJam3KeyPressed

    private void CmbMenit3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbMenit3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbMenit3KeyPressed

    private void CmbDetik3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbDetik3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbDetik3ActionPerformed

    private void CmbDetik3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbDetik3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbDetik3KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMTindakanKedokteran dialog = new RMTindakanKedokteran(new javax.swing.JFrame(), true);
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
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKabupaten;
    private widget.Button BtnKecamatan;
    private widget.Button BtnKeluar;
    private widget.Button BtnKelurahan;
    private widget.Button BtnPemberi;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.ComboBox CmbDetik1;
    private widget.ComboBox CmbDetik2;
    private widget.ComboBox CmbDetik3;
    private widget.ComboBox CmbJam1;
    private widget.ComboBox CmbJam2;
    private widget.ComboBox CmbJam3;
    private widget.ComboBox CmbMenit1;
    private widget.ComboBox CmbMenit2;
    private widget.ComboBox CmbMenit3;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.InternalFrame FormTindakan;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane ScrollTriase1;
    private widget.TextBox TCari;
    private widget.TextArea TDasarDiag;
    private widget.TextArea TDiagKerja;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextArea Talternatif;
    public widget.Tanggal TglBeriTindakan;
    public widget.Tanggal TglSetuju;
    public widget.Tanggal TglTolak;
    private widget.TextArea Tindikasi;
    private widget.TextArea Tkomplikasi;
    private widget.TextArea Tlain;
    private widget.TextArea Tprognosis;
    private widget.TextArea Tresiko;
    private widget.TextArea Ttatacara;
    private widget.TextArea Ttindakan;
    private widget.TextArea Ttujuan;
    private widget.TextBox alamatPJ;
    public widget.TextBox alasanTolak;
    public widget.ComboBox cmbTindakan;
    private widget.ComboBox hubungan;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel12;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel4;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
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
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.ComboBox jkPJ;
    public widget.TextBox kddokter_pelaksana;
    public widget.TextBox nip_pemberi;
    public widget.TextBox nmPJ;
    public widget.TextBox nmdokter_pelaksana;
    private widget.TextBox nmkab;
    private widget.TextBox nmkec;
    private widget.TextBox nmkel;
    public widget.TextBox nmpemberi;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbTindakan;
    private widget.TextBox tindakanDokter;
    private widget.TextBox umurPJ;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgl_lahir, ti.* from triase_igd ti "
                    + "inner join reg_periksa rp on rp.no_rawat=ti.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                    + "date(ti.tanggal) between ? and ? and ti.no_rawat like ? or "
                    + "date(ti.tanggal) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(ti.tanggal) between ? and ? and p.nm_pasien like ? or "
                    + "date(ti.tanggal) between ? and ? and ti.nm_pengantar like ? or "
                    + "date(ti.tanggal) between ? and ? and ti.telp_pengantar like ? or "
                    + "date(ti.tanggal) between ? and ? and ti.kasus like ? order by ti.tanggal desc");
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
                        rs.getString("tgl_lahir"),
                        rs.getString("tanggal"),
                        rs.getString("cara_masuk"),
                        rs.getString("sudah_terpasang"),
                        rs.getString("alasan_kedatangan"),
                        rs.getString("rujukan_dari"),
                        rs.getString("dijemput_oleh"),
                        rs.getString("kendaraan"),
                        rs.getString("bukan_ambulan"),
                        rs.getString("nm_pengantar"),
                        rs.getString("telp_pengantar"),
                        rs.getString("kasus"),
                        rs.getString("kll_tunggal"),
                        rs.getString("kll_tunggal_tmpt_kejadian"),
                        rs.getString("kll_tunggal_tanggal"),
                        rs.getString("kll_versus"),
                        rs.getString("versus1"),
                        rs.getString("versus2"),
                        rs.getString("kll_tmpt_kejadian"),
                        rs.getString("kll_tanggal"),
                        rs.getString("jatuh"),
                        rs.getString("ket_jatuh"),
                        rs.getString("luka_bakar"),
                        rs.getString("ket_luka_bakar"),
                        rs.getString("trauma_listrik"),
                        rs.getString("ket_trauma_listrik"),
                        rs.getString("trauma_zat_kimia"),
                        rs.getString("ket_trauma_zat_kimia"),
                        rs.getString("trauma_lain"),
                        rs.getString("ket_trauma_lain"),                        
                        rs.getString("keluhan_utama"),
                        rs.getString("pacs1"),
                        rs.getString("pacs2"),
                        rs.getString("pacs3"),
                        rs.getString("pacs4"),
                        rs.getString("kesadaran"),
                        rs.getString("td"),
                        rs.getString("nadi"),
                        rs.getString("napas"),
                        rs.getString("temperatur"),
                        rs.getString("saturasi"),
                        rs.getString("nyeri"),
                        rs.getString("skor0_sadar_penuh"),
                        rs.getString("skor0_100"),
                        rs.getString("skor0_101"),
                        rs.getString("skor0_19"),
                        rs.getString("skor0_35_3"),
                        rs.getString("skor0_96_100"),
                        rs.getString("skor1_102"),
                        rs.getString("skor1_20_21"),
                        rs.getString("skor1_94_95"),
                        rs.getString("skor2_99"),
                        rs.getString("skor2_22"),
                        rs.getString("skor2_92_93"),
                        rs.getString("skor3_selain"),
                        rs.getString("skor3_35_3"),
                        rs.getString("skor3_92"),
                        rs.getString("total_skor"),
                        rs.getString("catatan"),                        
                        rs.getString("pukul"),
                        rs.getString("triase_resusitasi"),
                        rs.getString("triase_non_resusitasi"),
                        rs.getString("triase_klinik"),
                        rs.getString("triase_doa"),
                        rs.getString("nip_petugas"),
                        rs.getString("tgl_kejadian_kll_tunggal"),
                        rs.getString("tgl_kejadian_kll"),
                        rs.getString("vas"),
                        rs.getString("bb"),
                        rs.getString("tb")
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
        nmPJ.setText("");
        umurPJ.setText("");
        jkPJ.setSelectedIndex(0);
        alamatPJ.setText("");
        kdkel = "";
        nmkel.setText("");
        kdkec = "";
        nmkec.setText("");
        kdkab = "";
        nmkab.setText("");
        hubungan.setSelectedIndex(0);
        cmbTindakan.setSelectedIndex(0);
        tindakanDokter.setText("");
        alasanTolak.setText("");
        TglBeriTindakan.setDate(new Date());
        CmbJam1.setSelectedIndex(0);
        CmbMenit1.setSelectedIndex(0);
        CmbDetik1.setSelectedIndex(0);
        TglSetuju.setDate(new Date());
        CmbJam2.setSelectedIndex(0);
        CmbMenit2.setSelectedIndex(0);
        CmbDetik2.setSelectedIndex(0);
        TglTolak.setDate(new Date());
        CmbJam3.setSelectedIndex(0);
        CmbMenit3.setSelectedIndex(0);
        CmbDetik3.setSelectedIndex(0);
        kddokter_pelaksana.setText("-");
        nmdokter_pelaksana.setText("-");
        nip_pemberi.setText("-");
        nmpemberi.setText("-");
        TDiagKerja.setText("");
        TDasarDiag.setText("");
        Ttindakan.setText("");
        Tindikasi.setText("");
        Ttatacara.setText("");
        Ttujuan.setText("");
        Tresiko.setText("");
        Tkomplikasi.setText("");
        Tprognosis.setText("");
        Talternatif.setText("");
        Tlain.setText("");
        TglSetuju.setEnabled(true);
        CmbJam2.setEnabled(true);
        CmbMenit2.setEnabled(true);
        CmbDetik2.setEnabled(true);

        TglTolak.setEnabled(false);
        CmbJam3.setEnabled(false);
        CmbMenit3.setEnabled(false);
        CmbDetik3.setEnabled(false);
    }
    
    public void setNoRm(String norwt) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(new Date());
        tampil();
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getdata_triase_igd());
        BtnHapus.setEnabled(akses.getdata_triase_igd());
        BtnPrint.setEnabled(akses.getdata_triase_igd());
        BtnEdit.setEnabled(akses.getdata_triase_igd());
    }
    
    private void getData() {
        if (tbTindakan.getSelectedRow() != -1) {
            TNoRw.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 0).toString());
            TNoRM.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 1).toString());
            TPasien.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 2).toString());
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from triase_igd where no_rawat=?", 1, new String[]{
                tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 0).toString()
            }) == true) {                             
                tampil();
                BtnBatalActionPerformed(null);               
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        }
    }
    
    private void ganti() {
//        if (Sequel.mengedittf("triase_igd", "no_rawat=?", "no_rawat=?, tanggal=?, cara_masuk=?, sudah_terpasang=?, alasan_kedatangan=?, rujukan_dari=?, dijemput_oleh=?, kendaraan=?, bukan_ambulan=?, nm_pengantar=?, "
//                + "telp_pengantar=?, kasus=?, kll_tunggal=?, kll_tunggal_tmpt_kejadian=?, kll_tunggal_tanggal=?, kll_versus=?, versus1=?, versus2=?, kll_tmpt_kejadian=?, "
//                + "kll_tanggal=?, jatuh=?, ket_jatuh=?, luka_bakar=?, ket_luka_bakar=?, trauma_listrik=?, ket_trauma_listrik=?, trauma_zat_kimia=?, ket_trauma_zat_kimia=?, "
//                + "trauma_lain=?, ket_trauma_lain=?, keluhan_utama=?, pacs1=?, pacs2=?, pacs3=?, pacs4=?, kesadaran=?, td=?, nadi=?, napas=?, temperatur=?, saturasi=?, "
//                + "nyeri=?, skor0_sadar_penuh=?, skor0_100=?, skor0_101=?, skor0_19=?, skor0_35_3=?, skor0_96_100=?, skor1_102=?, skor1_20_21=?, skor1_94_95=?, skor2_99=?, "
//                + "skor2_22=?, skor2_92_93=?, skor3_selain=?, skor3_35_3=?, skor3_92=?, total_skor=?, catatan=?, pukul=?, triase_resusitasi=?, triase_non_resusitasi=?, "
//                + "triase_klinik=?, triase_doa=?, nip_petugas=?, tgl_kejadian_kll_tunggal=?, tgl_kejadian_kll=?, vas=?, bb=?, tb=?", 71, new String[]{
//                    TNoRw.getText(), Valid.SetTgl(tgl_kunjungan.getSelectedItem() + "") + " " + tgl_kunjungan.getSelectedItem().toString().substring(11, 19), cmbCaraMasuk.getSelectedItem().toString(),
//                    sdh_terpasang.getText(), cmbAlasanKedatangan.getSelectedItem().toString(), rujukan_dari.getText(), dijemput_oleh.getText(), cmbKendaraan.getSelectedItem().toString(), bkn_ambulan.getText(),
//                    nm_pengantar.getText(), tlpn_pengantar.getText(), cmbKasus.getSelectedItem().toString(), kll_tunggal, tmpt_kejadian_tunggal.getText(), Valid.SetTgl(tgl_kejadian_tunggal.getSelectedItem() + "") + " " + tgl_kejadian_tunggal.getSelectedItem().toString().substring(11, 19),
//                    kll, versus1.getText(), versus2.getText(), tmpt_kejadian.getText(), Valid.SetTgl(tgl_kejadian.getSelectedItem() + "") + " " + tgl_kejadian.getSelectedItem().toString().substring(11, 19),
//                    jatuh, ket_jatuh.getText(), luka, ket_luka.getText(), trauma_listrik, ket_trauma_listrik.getText(), trauma_zat, ket_trauma_zat.getText(), trauma_lain, ket_trauma_lain.getText(),
//                    Tkeluhan_utama.getText(), pacs1, pacs2, pacs3, pacs4, cmbKesadaran.getSelectedItem().toString(), tkann_darah.getText(), nadi.getText(), pernapasan.getText(),
//                    temperatur.getText(), saturasi.getText(), cmbNyeri.getSelectedItem().toString(), skor0_sadar, skor0_100, skor0_101, skor0_19, skor0_35, skor0_96, skor102, skor20, skor94,
//                    skor99, skor22, skor92, skor3_sadar, skor3_35, skor3_92, skortotal, Tcttn_khusus.getText(), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
//                    resus, nonresus, klinik, doa, nip, tgl_kejadian_tggl, tgl_kejadian_kll, Tvas.getText(), bb.getText(), tb.getText(),
//                    tbTriase.getValueAt(tbTriase.getSelectedRow(), 0).toString()
//                }) == true) {
//
//            TCari.setText(TNoRw.getText());
//            tampil();
//            BtnBatalActionPerformed(null);
//            TabRawat.setSelectedIndex(1);
//        }
    }
}
