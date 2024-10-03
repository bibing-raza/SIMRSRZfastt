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
    private DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9, ps10, ps11, psCek;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9, rs10, rs11, rsCek;
    private int i = 0, x = 0, pilihan = 0;
    private String kdkel = "", kdkec = "", kdkab = "", jk = "", jnsrawat = "", wktSimpan = "";
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
            "Jns. Rawat", "Tgl. Persetujuan", "Tgl. Penolakan", "No.Rawat", "No.RM", "Nama Pasien", "Nama Png. Jawab", "Umur PJ", "Alamat PJ", 
            "Hub. Dg. Pasien", "Jns. Tindakan", "tgl_surat", "jam_surat", "nm_penjab", "umur_penjab", "jk_penjab", "alamat_penjab", "kd_kel", "kd_kec", 
            "kd_kab", "hubungan_dg_pasien", "jns_surat", "kasus_tindakan", "nm_tindakan_kedokteran", "alasan_penolakan", "nip_dokter_pelaksana", 
            "nip_pemberi_info", "penerima_info", "isi_info_diagnosis_kerja", "isi_info_dasar_diagnosis", "isi_info_tindakan", "isi_info_indikasi", 
            "isi_info_tatacara", "isi_info_tujuan", "isi_info_resiko", "isi_info_komplikasi", "isi_info_prognosis", "isi_info_alternatif", 
            "isi_info_lainlain", "waktu_simpan", "tgl_persetujuan", "jam_persetujuan", "tgl_penolakan", "jam_penolakan", "nmkel", "nmkec", 
            "nmkab", "nmdokter", "nmpetugas", "jenkel"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbTindakan.setModel(tabMode);
        tbTindakan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 50; i++) {
            TableColumn column = tbTindakan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(65);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(105);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            } else if (i == 5) {
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setPreferredWidth(55);
            } else if (i == 8) {
                column.setPreferredWidth(210);
            } else if (i == 9) {
                column.setPreferredWidth(110);
            } else if (i == 10) {
                column.setPreferredWidth(90);
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
            } 
        }
        tbTindakan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"No. RM", "Nama Pasien", "Data Template"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTemplate.setModel(tabMode1);
        tbTemplate.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTemplate.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbTemplate.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            }
        }
        tbTemplate.setDefaultRenderer(Object.class, new WarnaTable());
        
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
                        kdkel = Sequel.cariIsi("select if(count(-1)=0,'0',kd_kel) from kelurahan where nm_kel='" + nmkel.getText() + "'");
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
                        kdkec = Sequel.cariIsi("select if(count(-1)=0,'0',kd_kec) from kecamatan where nm_kec='" + nmkec.getText() + "'");
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
                        kdkab = Sequel.cariIsi("select if(count(-1)=0,'0',kd_kab) from kabupaten where nm_kab='" + nmkab.getText() + "'");
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

        WindowTemplate = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        jPanel1 = new javax.swing.JPanel();
        Scroll1 = new widget.ScrollPane();
        tbTemplate = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        Ttemplate = new widget.TextArea();
        panelisi4 = new widget.panelisi();
        jLabel36 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCopas = new widget.Button();
        BtnCloseIn1 = new widget.Button();
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
        jLabel46 = new widget.Label();
        nmPJ = new widget.TextBox();
        jLabel47 = new widget.Label();
        umurPJ = new widget.TextBox();
        jLabel48 = new widget.Label();
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
        jLabel77 = new widget.Label();
        TnmPenerima = new widget.TextBox();
        chkSamaNama = new widget.CekBox();
        BtnDiagKerja = new widget.Button();
        BtnDasarDiag = new widget.Button();
        BtnTindakan = new widget.Button();
        BtnIndikasi = new widget.Button();
        BtnTata = new widget.Button();
        BtnTujuan = new widget.Button();
        BtnResiko = new widget.Button();
        BtnKomplikasi = new widget.Button();
        BtnPrognosis = new widget.Button();
        BtnAlternatif = new widget.Button();
        BtnLain = new widget.Button();
        jLabel78 = new widget.Label();
        cmbKategori = new widget.ComboBox();
        chkSamaPelaksana = new widget.CekBox();
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

        WindowTemplate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTemplate.setName("WindowTemplate"); // NOI18N
        WindowTemplate.setUndecorated(true);
        WindowTemplate.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template CPPT ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 250));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTemplate.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTemplate.setName("tbTemplate"); // NOI18N
        tbTemplate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTemplateMouseClicked(evt);
            }
        });
        Scroll1.setViewportView(tbTemplate);

        jPanel1.add(Scroll1);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        Ttemplate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Baca Template Dipilih ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Ttemplate.setColumns(20);
        Ttemplate.setRows(5);
        Ttemplate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Ttemplate.setName("Ttemplate"); // NOI18N
        Ttemplate.setPreferredSize(new java.awt.Dimension(210, 4000));
        Scroll3.setViewportView(Ttemplate);

        jPanel1.add(Scroll3);

        internalFrame5.add(jPanel1, java.awt.BorderLayout.CENTER);

        panelisi4.setBackground(new java.awt.Color(255, 150, 255));
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Key Word :");
        jLabel36.setName("jLabel36"); // NOI18N
        jLabel36.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel36.setRequestFocusEnabled(false);
        panelisi4.add(jLabel36);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi4.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+1");
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
        panelisi4.add(BtnCari1);

        BtnCopas.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopas.setMnemonic('U');
        BtnCopas.setText("Copy & Paste");
        BtnCopas.setToolTipText("Alt+U");
        BtnCopas.setName("BtnCopas"); // NOI18N
        BtnCopas.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCopas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopasActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCopas);

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

        internalFrame5.add(panelisi4, java.awt.BorderLayout.PAGE_END);

        WindowTemplate.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

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

        FormInput.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Input Tindakan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        FormInput.setToolTipText("");
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1391));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 20, 130, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(136, 20, 122, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(332, 20, 430, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(260, 20, 70, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Nama Png. Jwb. :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(0, 48, 130, 23);

        nmPJ.setForeground(new java.awt.Color(0, 0, 0));
        nmPJ.setHighlighter(null);
        nmPJ.setName("nmPJ"); // NOI18N
        nmPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmPJKeyPressed(evt);
            }
        });
        FormInput.add(nmPJ);
        nmPJ.setBounds(136, 48, 260, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Umur :");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(395, 48, 50, 23);

        umurPJ.setForeground(new java.awt.Color(0, 0, 0));
        umurPJ.setHighlighter(null);
        umurPJ.setName("umurPJ"); // NOI18N
        umurPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                umurPJKeyPressed(evt);
            }
        });
        FormInput.add(umurPJ);
        umurPJ.setBounds(450, 48, 50, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("Tahun    Jns. Kel. Png. Jwb :");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(505, 48, 140, 23);

        jkPJ.setForeground(new java.awt.Color(0, 0, 0));
        jkPJ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-laki", "Perempuan" }));
        jkPJ.setName("jkPJ"); // NOI18N
        jkPJ.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(jkPJ);
        jkPJ.setBounds(647, 48, 90, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Alamat Png. Jwb. :");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(0, 76, 130, 23);

        alamatPJ.setForeground(new java.awt.Color(0, 0, 0));
        alamatPJ.setHighlighter(null);
        alamatPJ.setName("alamatPJ"); // NOI18N
        alamatPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                alamatPJKeyPressed(evt);
            }
        });
        FormInput.add(alamatPJ);
        alamatPJ.setBounds(136, 76, 630, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Kelurahan :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(0, 104, 130, 23);

        nmkel.setEditable(false);
        nmkel.setForeground(new java.awt.Color(0, 0, 0));
        nmkel.setHighlighter(null);
        nmkel.setName("nmkel"); // NOI18N
        FormInput.add(nmkel);
        nmkel.setBounds(136, 104, 340, 23);

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
        BtnKelurahan.setBounds(480, 104, 28, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Kecamatan :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(0, 132, 130, 23);

        nmkec.setEditable(false);
        nmkec.setForeground(new java.awt.Color(0, 0, 0));
        nmkec.setHighlighter(null);
        nmkec.setName("nmkec"); // NOI18N
        FormInput.add(nmkec);
        nmkec.setBounds(136, 132, 340, 23);

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
        BtnKecamatan.setBounds(480, 132, 28, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Kabupaten :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(0, 160, 130, 23);

        nmkab.setEditable(false);
        nmkab.setForeground(new java.awt.Color(0, 0, 0));
        nmkab.setHighlighter(null);
        nmkab.setName("nmkab"); // NOI18N
        FormInput.add(nmkab);
        nmkab.setBounds(136, 160, 340, 23);

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
        BtnKabupaten.setBounds(480, 160, 28, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Hubungan dg. Pasien :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(0, 188, 130, 23);

        hubungan.setForeground(new java.awt.Color(0, 0, 0));
        hubungan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Saudara", "Ayah", "Ibu", "Anak", "Suami", "Istri", "Sepupu", "Pasien Sendiri", "Paman", "Bibi", "Kakek", "Nenek", "Teman", "Tetangga", "Ipar", "Besan", "Menantu", "Mertua", "Keponakan", "Kakak", "Adik", "Cucu" }));
        hubungan.setName("hubungan"); // NOI18N
        hubungan.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(hubungan);
        hubungan.setBounds(136, 188, 110, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Jenis Tindakan :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(250, 188, 90, 23);

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
        cmbTindakan.setBounds(345, 188, 110, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Tindakan Kedokteran :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(0, 272, 130, 23);

        tindakanDokter.setForeground(new java.awt.Color(0, 0, 0));
        tindakanDokter.setHighlighter(null);
        tindakanDokter.setName("tindakanDokter"); // NOI18N
        tindakanDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tindakanDokterKeyPressed(evt);
            }
        });
        FormInput.add(tindakanDokter);
        tindakanDokter.setBounds(136, 272, 630, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Alasan Penolakan :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(0, 300, 130, 23);

        alasanTolak.setForeground(new java.awt.Color(0, 0, 0));
        alasanTolak.setHighlighter(null);
        alasanTolak.setName("alasanTolak"); // NOI18N
        alasanTolak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                alasanTolakKeyPressed(evt);
            }
        });
        FormInput.add(alasanTolak);
        alasanTolak.setBounds(136, 300, 630, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Tgl. Beri Info Tndkn. :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(0, 328, 130, 23);

        TglBeriTindakan.setEditable(false);
        TglBeriTindakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-09-2024" }));
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
        TglBeriTindakan.setBounds(136, 328, 100, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Jam Pemberian Informasi :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(236, 328, 150, 23);

        CmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        CmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam1.setName("CmbJam1"); // NOI18N
        CmbJam1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJam1KeyPressed(evt);
            }
        });
        FormInput.add(CmbJam1);
        CmbJam1.setBounds(392, 328, 45, 23);

        CmbMenit1.setForeground(new java.awt.Color(0, 0, 0));
        CmbMenit1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit1.setName("CmbMenit1"); // NOI18N
        CmbMenit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbMenit1KeyPressed(evt);
            }
        });
        FormInput.add(CmbMenit1);
        CmbMenit1.setBounds(443, 328, 45, 23);

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
        CmbDetik1.setBounds(494, 328, 45, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Dokter Pelaksana :");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(0, 356, 130, 23);

        kddokter_pelaksana.setEditable(false);
        kddokter_pelaksana.setForeground(new java.awt.Color(0, 0, 0));
        kddokter_pelaksana.setName("kddokter_pelaksana"); // NOI18N
        FormInput.add(kddokter_pelaksana);
        kddokter_pelaksana.setBounds(136, 356, 100, 23);

        nmdokter_pelaksana.setEditable(false);
        nmdokter_pelaksana.setForeground(new java.awt.Color(0, 0, 0));
        nmdokter_pelaksana.setName("nmdokter_pelaksana"); // NOI18N
        FormInput.add(nmdokter_pelaksana);
        nmdokter_pelaksana.setBounds(240, 356, 370, 23);

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
        BtnDokter.setBounds(615, 356, 28, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Pemberi Informasi :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(0, 384, 130, 23);

        nip_pemberi.setEditable(false);
        nip_pemberi.setForeground(new java.awt.Color(0, 0, 0));
        nip_pemberi.setName("nip_pemberi"); // NOI18N
        FormInput.add(nip_pemberi);
        nip_pemberi.setBounds(136, 384, 100, 23);

        nmpemberi.setEditable(false);
        nmpemberi.setForeground(new java.awt.Color(0, 0, 0));
        nmpemberi.setName("nmpemberi"); // NOI18N
        FormInput.add(nmpemberi);
        nmpemberi.setBounds(240, 384, 305, 23);

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
        BtnPemberi.setBounds(550, 384, 28, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Diagnosis Kerja/ :");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(0, 440, 130, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Diagnosis Banding  ");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(0, 455, 130, 23);

        scrollPane3.setName("scrollPane3"); // NOI18N

        TDiagKerja.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TDiagKerja.setColumns(20);
        TDiagKerja.setRows(5);
        TDiagKerja.setName("TDiagKerja"); // NOI18N
        TDiagKerja.setPreferredSize(new java.awt.Dimension(162, 1000));
        TDiagKerja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagKerjaKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(TDiagKerja);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(136, 440, 630, 80);

        scrollPane4.setName("scrollPane4"); // NOI18N

        TDasarDiag.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TDasarDiag.setColumns(20);
        TDasarDiag.setRows(5);
        TDasarDiag.setName("TDasarDiag"); // NOI18N
        TDasarDiag.setPreferredSize(new java.awt.Dimension(162, 1000));
        TDasarDiag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDasarDiagKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(TDasarDiag);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(136, 526, 630, 80);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Dasar Diagnosis :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 526, 130, 23);

        scrollPane5.setName("scrollPane5"); // NOI18N

        Ttindakan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Ttindakan.setColumns(20);
        Ttindakan.setRows(5);
        Ttindakan.setName("Ttindakan"); // NOI18N
        Ttindakan.setPreferredSize(new java.awt.Dimension(162, 1000));
        Ttindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtindakanKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(Ttindakan);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(136, 612, 630, 80);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Tindakan Kedokteran :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 612, 130, 23);

        scrollPane6.setName("scrollPane6"); // NOI18N

        Tindikasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tindikasi.setColumns(20);
        Tindikasi.setRows(5);
        Tindikasi.setName("Tindikasi"); // NOI18N
        Tindikasi.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tindikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindikasiKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(Tindikasi);

        FormInput.add(scrollPane6);
        scrollPane6.setBounds(136, 698, 630, 80);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Indikasi Tindakan :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 698, 130, 23);

        scrollPane7.setName("scrollPane7"); // NOI18N

        Ttatacara.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Ttatacara.setColumns(20);
        Ttatacara.setRows(5);
        Ttatacara.setName("Ttatacara"); // NOI18N
        Ttatacara.setPreferredSize(new java.awt.Dimension(162, 1000));
        Ttatacara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtatacaraKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(Ttatacara);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(136, 784, 630, 80);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Tata Cara :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 784, 130, 23);

        scrollPane8.setName("scrollPane8"); // NOI18N

        Ttujuan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Ttujuan.setColumns(20);
        Ttujuan.setRows(5);
        Ttujuan.setName("Ttujuan"); // NOI18N
        Ttujuan.setPreferredSize(new java.awt.Dimension(162, 1000));
        Ttujuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtujuanKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(Ttujuan);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(136, 870, 630, 80);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Tujuan :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 870, 130, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Resiko Tindakan :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 956, 130, 23);

        scrollPane9.setName("scrollPane9"); // NOI18N

        Tresiko.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tresiko.setColumns(20);
        Tresiko.setRows(5);
        Tresiko.setName("Tresiko"); // NOI18N
        Tresiko.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tresiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TresikoKeyPressed(evt);
            }
        });
        scrollPane9.setViewportView(Tresiko);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(136, 956, 630, 80);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Komplikasi Tindakan :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 1042, 130, 23);

        scrollPane10.setName("scrollPane10"); // NOI18N

        Tkomplikasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkomplikasi.setColumns(20);
        Tkomplikasi.setRows(5);
        Tkomplikasi.setName("Tkomplikasi"); // NOI18N
        Tkomplikasi.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tkomplikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkomplikasiKeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(Tkomplikasi);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(136, 1042, 630, 80);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Prognosis :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 1128, 130, 23);

        scrollPane11.setName("scrollPane11"); // NOI18N

        Tprognosis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tprognosis.setColumns(20);
        Tprognosis.setRows(5);
        Tprognosis.setName("Tprognosis"); // NOI18N
        Tprognosis.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tprognosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprognosisKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(Tprognosis);

        FormInput.add(scrollPane11);
        scrollPane11.setBounds(136, 1128, 630, 80);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Alternatif Tindakan :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 1214, 130, 23);

        scrollPane12.setName("scrollPane12"); // NOI18N

        Talternatif.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Talternatif.setColumns(20);
        Talternatif.setRows(5);
        Talternatif.setName("Talternatif"); // NOI18N
        Talternatif.setPreferredSize(new java.awt.Dimension(162, 1000));
        Talternatif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalternatifKeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(Talternatif);

        FormInput.add(scrollPane12);
        scrollPane12.setBounds(136, 1214, 630, 80);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Lain - Lain :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 1300, 130, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N

        Tlain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tlain.setColumns(20);
        Tlain.setRows(5);
        Tlain.setName("Tlain"); // NOI18N
        Tlain.setPreferredSize(new java.awt.Dimension(162, 1000));
        scrollPane13.setViewportView(Tlain);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(136, 1300, 630, 80);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Tgl. Persetujuan Tndkn. :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 216, 130, 23);

        TglSetuju.setEditable(false);
        TglSetuju.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-09-2024" }));
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
        TglSetuju.setBounds(136, 216, 100, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Jam Persetujuan Tndkn. :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(236, 216, 150, 23);

        CmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        CmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam2.setName("CmbJam2"); // NOI18N
        CmbJam2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJam2KeyPressed(evt);
            }
        });
        FormInput.add(CmbJam2);
        CmbJam2.setBounds(392, 216, 45, 23);

        CmbMenit2.setForeground(new java.awt.Color(0, 0, 0));
        CmbMenit2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit2.setName("CmbMenit2"); // NOI18N
        CmbMenit2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbMenit2KeyPressed(evt);
            }
        });
        FormInput.add(CmbMenit2);
        CmbMenit2.setBounds(443, 216, 45, 23);

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
        CmbDetik2.setBounds(494, 216, 45, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Tgl. Penolakan Tndkn. :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 244, 130, 23);

        TglTolak.setEditable(false);
        TglTolak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-09-2024" }));
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
        TglTolak.setBounds(136, 244, 100, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Jam Penolakan Tndkn. :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(236, 244, 150, 23);

        CmbJam3.setForeground(new java.awt.Color(0, 0, 0));
        CmbJam3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam3.setName("CmbJam3"); // NOI18N
        CmbJam3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJam3KeyPressed(evt);
            }
        });
        FormInput.add(CmbJam3);
        CmbJam3.setBounds(392, 244, 45, 23);

        CmbMenit3.setForeground(new java.awt.Color(0, 0, 0));
        CmbMenit3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit3.setName("CmbMenit3"); // NOI18N
        CmbMenit3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbMenit3KeyPressed(evt);
            }
        });
        FormInput.add(CmbMenit3);
        CmbMenit3.setBounds(443, 244, 45, 23);

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
        CmbDetik3.setBounds(494, 244, 45, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Penerima Informasi :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 412, 130, 23);

        TnmPenerima.setForeground(new java.awt.Color(0, 0, 0));
        TnmPenerima.setHighlighter(null);
        TnmPenerima.setName("TnmPenerima"); // NOI18N
        TnmPenerima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmPenerimaKeyPressed(evt);
            }
        });
        FormInput.add(TnmPenerima);
        TnmPenerima.setBounds(136, 412, 340, 23);

        chkSamaNama.setBackground(new java.awt.Color(242, 242, 242));
        chkSamaNama.setForeground(new java.awt.Color(0, 0, 0));
        chkSamaNama.setText("Sama Dengan Penanggung Jawab Pasien");
        chkSamaNama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSamaNama.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSamaNama.setName("chkSamaNama"); // NOI18N
        chkSamaNama.setOpaque(false);
        chkSamaNama.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSamaNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSamaNamaActionPerformed(evt);
            }
        });
        FormInput.add(chkSamaNama);
        chkSamaNama.setBounds(485, 412, 230, 23);

        BtnDiagKerja.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagKerja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagKerja.setMnemonic('2');
        BtnDiagKerja.setText("Template");
        BtnDiagKerja.setToolTipText("Alt+2");
        BtnDiagKerja.setName("BtnDiagKerja"); // NOI18N
        BtnDiagKerja.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDiagKerja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagKerjaActionPerformed(evt);
            }
        });
        FormInput.add(BtnDiagKerja);
        BtnDiagKerja.setBounds(27, 485, 100, 23);

        BtnDasarDiag.setForeground(new java.awt.Color(0, 0, 0));
        BtnDasarDiag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDasarDiag.setMnemonic('2');
        BtnDasarDiag.setText("Template");
        BtnDasarDiag.setToolTipText("Alt+2");
        BtnDasarDiag.setName("BtnDasarDiag"); // NOI18N
        BtnDasarDiag.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDasarDiag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDasarDiagActionPerformed(evt);
            }
        });
        FormInput.add(BtnDasarDiag);
        BtnDasarDiag.setBounds(27, 556, 100, 23);

        BtnTindakan.setForeground(new java.awt.Color(0, 0, 0));
        BtnTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnTindakan.setMnemonic('2');
        BtnTindakan.setText("Template");
        BtnTindakan.setToolTipText("Alt+2");
        BtnTindakan.setName("BtnTindakan"); // NOI18N
        BtnTindakan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTindakanActionPerformed(evt);
            }
        });
        FormInput.add(BtnTindakan);
        BtnTindakan.setBounds(27, 642, 100, 23);

        BtnIndikasi.setForeground(new java.awt.Color(0, 0, 0));
        BtnIndikasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnIndikasi.setMnemonic('2');
        BtnIndikasi.setText("Template");
        BtnIndikasi.setToolTipText("Alt+2");
        BtnIndikasi.setName("BtnIndikasi"); // NOI18N
        BtnIndikasi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnIndikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIndikasiActionPerformed(evt);
            }
        });
        FormInput.add(BtnIndikasi);
        BtnIndikasi.setBounds(27, 728, 100, 23);

        BtnTata.setForeground(new java.awt.Color(0, 0, 0));
        BtnTata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnTata.setMnemonic('2');
        BtnTata.setText("Template");
        BtnTata.setToolTipText("Alt+2");
        BtnTata.setName("BtnTata"); // NOI18N
        BtnTata.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTataActionPerformed(evt);
            }
        });
        FormInput.add(BtnTata);
        BtnTata.setBounds(27, 814, 100, 23);

        BtnTujuan.setForeground(new java.awt.Color(0, 0, 0));
        BtnTujuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnTujuan.setMnemonic('2');
        BtnTujuan.setText("Template");
        BtnTujuan.setToolTipText("Alt+2");
        BtnTujuan.setName("BtnTujuan"); // NOI18N
        BtnTujuan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTujuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTujuanActionPerformed(evt);
            }
        });
        FormInput.add(BtnTujuan);
        BtnTujuan.setBounds(27, 900, 100, 23);

        BtnResiko.setForeground(new java.awt.Color(0, 0, 0));
        BtnResiko.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnResiko.setMnemonic('2');
        BtnResiko.setText("Template");
        BtnResiko.setToolTipText("Alt+2");
        BtnResiko.setName("BtnResiko"); // NOI18N
        BtnResiko.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnResiko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResikoActionPerformed(evt);
            }
        });
        FormInput.add(BtnResiko);
        BtnResiko.setBounds(27, 986, 100, 23);

        BtnKomplikasi.setForeground(new java.awt.Color(0, 0, 0));
        BtnKomplikasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKomplikasi.setMnemonic('2');
        BtnKomplikasi.setText("Template");
        BtnKomplikasi.setToolTipText("Alt+2");
        BtnKomplikasi.setName("BtnKomplikasi"); // NOI18N
        BtnKomplikasi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnKomplikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKomplikasiActionPerformed(evt);
            }
        });
        FormInput.add(BtnKomplikasi);
        BtnKomplikasi.setBounds(27, 1072, 100, 23);

        BtnPrognosis.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrognosis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPrognosis.setMnemonic('2');
        BtnPrognosis.setText("Template");
        BtnPrognosis.setToolTipText("Alt+2");
        BtnPrognosis.setName("BtnPrognosis"); // NOI18N
        BtnPrognosis.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPrognosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrognosisActionPerformed(evt);
            }
        });
        FormInput.add(BtnPrognosis);
        BtnPrognosis.setBounds(27, 1158, 100, 23);

        BtnAlternatif.setForeground(new java.awt.Color(0, 0, 0));
        BtnAlternatif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAlternatif.setMnemonic('2');
        BtnAlternatif.setText("Template");
        BtnAlternatif.setToolTipText("Alt+2");
        BtnAlternatif.setName("BtnAlternatif"); // NOI18N
        BtnAlternatif.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAlternatif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlternatifActionPerformed(evt);
            }
        });
        FormInput.add(BtnAlternatif);
        BtnAlternatif.setBounds(27, 1244, 100, 23);

        BtnLain.setForeground(new java.awt.Color(0, 0, 0));
        BtnLain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnLain.setMnemonic('2');
        BtnLain.setText("Template");
        BtnLain.setToolTipText("Alt+2");
        BtnLain.setName("BtnLain"); // NOI18N
        BtnLain.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLainActionPerformed(evt);
            }
        });
        FormInput.add(BtnLain);
        BtnLain.setBounds(27, 1330, 100, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Kategori : ");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(455, 188, 70, 23);

        cmbKategori.setForeground(new java.awt.Color(0, 0, 0));
        cmbKategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Transfusi Darah", "Trombopharesis", "Restrain", "Leukopharesis", "BMP", "NGT", "DC", "Transfusi Zat Besi", "Biopsi", "Pengobatan Kemoterapi", "Punksi Cairan Pleura", "Punksi Ascites" }));
        cmbKategori.setName("cmbKategori"); // NOI18N
        cmbKategori.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKategoriActionPerformed(evt);
            }
        });
        FormInput.add(cmbKategori);
        cmbKategori.setBounds(527, 188, 152, 23);

        chkSamaPelaksana.setBackground(new java.awt.Color(242, 242, 242));
        chkSamaPelaksana.setForeground(new java.awt.Color(0, 0, 0));
        chkSamaPelaksana.setText("Sama Dokter Dengan Pelaksana");
        chkSamaPelaksana.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSamaPelaksana.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSamaPelaksana.setName("chkSamaPelaksana"); // NOI18N
        chkSamaPelaksana.setOpaque(false);
        chkSamaPelaksana.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSamaPelaksana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSamaPelaksanaActionPerformed(evt);
            }
        });
        FormInput.add(chkSamaPelaksana);
        chkSamaPelaksana.setBounds(585, 384, 180, 23);

        ScrollTriase1.setViewportView(FormInput);

        FormTindakan.add(ScrollTriase1, java.awt.BorderLayout.CENTER);

        panelGlass10.add(FormTindakan);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Data Tindakan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
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
        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-09-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-09-2024" }));
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
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (jkPJ.getSelectedIndex() == 0) {
                jk = "L";
            } else {
                jk = "P";
            }
            if (Sequel.menyimpantf("surat_tindakan_kedokteran", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 34, new String[]{
                        TNoRw.getText(), Valid.SetTgl(TglBeriTindakan.getSelectedItem() + ""), CmbJam1.getSelectedItem() + ":" + CmbMenit1.getSelectedItem() + ":" + CmbDetik1.getSelectedItem(),
                        nmPJ.getText(), umurPJ.getText(), jk, alamatPJ.getText(), kdkel, kdkec, kdkab, hubungan.getSelectedItem().toString(), cmbTindakan.getSelectedItem().toString(),
                        jnsrawat, tindakanDokter.getText(), alasanTolak.getText(), kddokter_pelaksana.getText(), nip_pemberi.getText(), TnmPenerima.getText(), TDiagKerja.getText(),
                        TDasarDiag.getText(), Ttindakan.getText(), Tindikasi.getText(), Ttatacara.getText(), Ttujuan.getText(), Tresiko.getText(), Tkomplikasi.getText(), Tprognosis.getText(),
                        Talternatif.getText(), Tlain.getText(), Sequel.cariIsi("select now()"), Valid.SetTgl(TglSetuju.getSelectedItem() + ""), 
                        CmbJam2.getSelectedItem() + ":" + CmbMenit2.getSelectedItem() + ":" + CmbDetik2.getSelectedItem(), Valid.SetTgl(TglTolak.getSelectedItem() + ""), 
                        CmbJam3.getSelectedItem() + ":" + CmbMenit3.getSelectedItem() + ":" + CmbDetik3.getSelectedItem()
                    }) == true) {
                
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
        if (tbTindakan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data pada tabel masih kosong..!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel..!!");
            tbTindakan.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from surat_tindakan_kedokteran where waktu_simpan=?", 1, new String[]{wktSimpan}) == true) {
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
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (tbTindakan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data pada tabel masih kosong..!!");
        }  else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel..!!");
            tbTindakan.requestFocus();
        } else {
            if (jkPJ.getSelectedIndex() == 0) {
                jk = "L";
            } else {
                jk = "P";
            }

            if (Sequel.mengedittf("surat_tindakan_kedokteran", "waktu_simpan=?", "no_rawat=?, tgl_surat=?, jam_surat=?, nm_penjab=?, umur_penjab=?, "
                    + "jk_penjab=?, alamat_penjab=?, kd_kel=?, kd_kec=?, kd_kab=?, hubungan_dg_pasien=?, jns_surat=?, nm_tindakan_kedokteran=?, "
                    + "alasan_penolakan=?, nip_dokter_pelaksana=?, nip_pemberi_info=?, penerima_info=?, isi_info_diagnosis_kerja=?, isi_info_dasar_diagnosis=?, "
                    + "isi_info_tindakan=?, isi_info_indikasi=?, isi_info_tatacara=?, isi_info_tujuan=?, isi_info_resiko=?, isi_info_komplikasi=?, isi_info_prognosis=?, "
                    + "isi_info_alternatif=?, isi_info_lainlain=?, waktu_simpan=?, tgl_persetujuan=?, jam_persetujuan=?, tgl_penolakan=?, jam_penolakan=?", 34, new String[]{
                        TNoRw.getText(), Valid.SetTgl(TglBeriTindakan.getSelectedItem() + ""), CmbJam1.getSelectedItem() + ":" + CmbMenit1.getSelectedItem() + ":" + CmbDetik1.getSelectedItem(),
                        nmPJ.getText(), umurPJ.getText(), jk, alamatPJ.getText(), kdkel, kdkec, kdkab, hubungan.getSelectedItem().toString(), cmbTindakan.getSelectedItem().toString(),
                        tindakanDokter.getText(), alasanTolak.getText(), kddokter_pelaksana.getText(), nip_pemberi.getText(), TnmPenerima.getText(), TDiagKerja.getText(),
                        TDasarDiag.getText(), Ttindakan.getText(), Tindikasi.getText(), Ttatacara.getText(), Ttujuan.getText(), Tresiko.getText(), Tkomplikasi.getText(), Tprognosis.getText(),
                        Talternatif.getText(), Tlain.getText(), Sequel.cariIsi("select now()"), Valid.SetTgl(TglSetuju.getSelectedItem() + ""),
                        CmbJam2.getSelectedItem() + ":" + CmbMenit2.getSelectedItem() + ":" + CmbDetik2.getSelectedItem(), Valid.SetTgl(TglTolak.getSelectedItem() + ""),
                        CmbJam3.getSelectedItem() + ":" + CmbMenit3.getSelectedItem() + ":" + CmbDetik3.getSelectedItem(),
                        wktSimpan
                    }) == true) {

                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
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
        if (tbTindakan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data pada tabel masih kosong..!!");
        }  else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel..!!");
            tbTindakan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tglsurat", "Martapura, " + Valid.SetTglINDONESIA(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 11).toString()) + " Pukul ");

            if (cmbTindakan.getSelectedIndex() == 0) {
                Valid.MyReport("rptSetujuTindakanDokter.jasper", "report", "::[ Lembar Formulir Persetujuan Tindakan Kedokteran ]::",
                        "SELECT s.nm_penjab, CONCAT(s.umur_penjab,' Thn. / ',IF(s.jk_penjab='L','Laki-laki','Perempuan')) umur_pj, "
                        + "CONCAT(s.alamat_penjab,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) alamat_pj, s.hubungan_dg_pasien selaku, s.jns_surat, "
                        + "s.nm_tindakan_kedokteran, s.alasan_penolakan, p.nm_pasien, CONCAT(rp.umurdaftar,' ',rp.sttsumur,'. / ',IF(p.jk='L','Laki-laki','Perempuan')) umur_px, "
                        + "CONCAT(p.alamat,', ',kl2.nm_kel,', ',kc2.nm_kec,', ',kb2.nm_kab) alamat_px, "
                        + "CONCAT('Martapura, ',DATE_FORMAT(s.tgl_surat,'%d-%m-%Y'),' Pukul ',DATE_FORMAT(s.jam_surat,'%H:%i'),' WITA') tgl_surat FROM surat_tindakan_kedokteran s "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=s.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kelurahan kl1 on kl1.kd_kel=s.kd_kel INNER JOIN kelurahan kl2 on kl2.kd_kel=p.kd_kel "
                        + "INNER JOIN kecamatan kc1 on kc1.kd_kec=s.kd_kec INNER JOIN kecamatan kc2 on kc2.kd_kec=p.kd_kec "
                        + "INNER JOIN kabupaten kb1 on kb1.kd_kab=s.kd_kab INNER JOIN kabupaten kb2 on kb2.kd_kab=p.kd_kab "
                        + "WHERE s.waktu_simpan='" + wktSimpan + "' and s.jns_surat='" + cmbTindakan.getSelectedItem() + "'", param);
            } else {
                Valid.MyReport("rptTolakTindakanDokter.jasper", "report", "::[ Lembar Formulir Penolakan Tindakan Kedokteran ]::",
                        "SELECT s.nm_penjab, CONCAT(s.umur_penjab,' Thn. / ',IF(s.jk_penjab='L','Laki-laki','Perempuan')) umur_pj, "
                        + "CONCAT(s.alamat_penjab,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) alamat_pj, s.hubungan_dg_pasien selaku, s.jns_surat, "
                        + "s.nm_tindakan_kedokteran, s.alasan_penolakan, p.nm_pasien, CONCAT(rp.umurdaftar,' ',rp.sttsumur,'. / ',IF(p.jk='L','Laki-laki','Perempuan')) umur_px, "
                        + "CONCAT(p.alamat,', ',kl2.nm_kel,', ',kc2.nm_kec,', ',kb2.nm_kab) alamat_px, "
                        + "CONCAT('Martapura, ',DATE_FORMAT(s.tgl_surat,'%d-%m-%Y'),' Pukul ',DATE_FORMAT(s.jam_surat,'%H:%i'),' WITA') tgl_surat FROM surat_tindakan_kedokteran s "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=s.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kelurahan kl1 on kl1.kd_kel=s.kd_kel INNER JOIN kelurahan kl2 on kl2.kd_kel=p.kd_kel "
                        + "INNER JOIN kecamatan kc1 on kc1.kd_kec=s.kd_kec INNER JOIN kecamatan kc2 on kc2.kd_kec=p.kd_kec "
                        + "INNER JOIN kabupaten kb1 on kb1.kd_kab=s.kd_kab INNER JOIN kabupaten kb2 on kb2.kd_kab=p.kd_kab "
                        + "WHERE s.waktu_simpan='" + wktSimpan + "' and s.jns_surat='" + cmbTindakan.getSelectedItem() + "'", param);
            }
            
            Valid.MyReport("rptBeriInfoTindakan.jasper", "report", "::[ Lembar Pemberian Informasi Tindakan ]::",
                    "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, d.nm_dokter, pg.nama pemberiInfo, "
                    + "s.penerima_info, s.isi_info_diagnosis_kerja, s.isi_info_dasar_diagnosis, s.isi_info_tindakan, s.isi_info_indikasi, s.isi_info_tatacara, "
                    + "s.isi_info_tujuan, s.isi_info_resiko, s.isi_info_komplikasi, s.isi_info_prognosis, s.isi_info_alternatif, s.isi_info_lainlain, "
                    + "time_format(s.jam_surat,'%H:%i WITA') jamBeriInfo FROM surat_tindakan_kedokteran s INNER JOIN reg_periksa rp ON rp.no_rawat = s.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN dokter d ON d.kd_dokter = s.nip_dokter_pelaksana "
                    + "INNER JOIN pegawai pg ON pg.nik = s.nip_pemberi_info where s.waktu_simpan='" + wktSimpan + "'", param);

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
        alasanTolak.setText("");
        
        if(cmbTindakan.getSelectedIndex() == 0) {
            TglSetuju.setEnabled(true);
            CmbJam2.setEnabled(true);
            CmbMenit2.setEnabled(true);
            CmbDetik2.setEnabled(true);
            
            TglTolak.setEnabled(false);
            CmbJam3.setEnabled(false);
            CmbMenit3.setEnabled(false);
            CmbDetik3.setEnabled(false);
            alasanTolak.setEnabled(false);
        } else {
            TglSetuju.setEnabled(false);
            CmbJam2.setEnabled(false);
            CmbMenit2.setEnabled(false);
            CmbDetik2.setEnabled(false);
            
            TglTolak.setEnabled(true);
            CmbJam3.setEnabled(true);
            CmbMenit3.setEnabled(true);
            CmbDetik3.setEnabled(true);
            alasanTolak.setEnabled(true);
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

    private void TnmPenerimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmPenerimaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TnmPenerimaKeyPressed

    private void chkSamaNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSamaNamaActionPerformed
        if (chkSamaNama.isSelected() == true) {
            TnmPenerima.setText(nmPJ.getText());
        } else {
            TnmPenerima.setText("");
        }
    }//GEN-LAST:event_chkSamaNamaActionPerformed

    private void tbTemplateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTemplateMouseClicked
        if(tabMode1.getRowCount() != 0) {
            try {
                if (tbTemplate.getSelectedRow() != -1) {
                    Ttemplate.setText(tbTemplate.getValueAt(tbTemplate.getSelectedRow(), 2).toString());
                }
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTemplateMouseClicked

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilTemplate();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnCopasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopasActionPerformed
        x = JOptionPane.showConfirmDialog(rootPane, "Apakah data ini akan dipakai..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            copas();
            WindowTemplate.dispose();
        }
    }//GEN-LAST:event_BtnCopasActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowTemplate.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void BtnDiagKerjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagKerjaActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 1;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Diagnosis Kerja/Diagnosis Banding ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnDiagKerjaActionPerformed

    private void BtnDasarDiagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDasarDiagActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 2;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Dasar Diagnosis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnDasarDiagActionPerformed

    private void BtnTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTindakanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 3;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Tindakan Kedokteran ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnTindakanActionPerformed

    private void BtnIndikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIndikasiActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 4;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Indikasi Tindakan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnIndikasiActionPerformed

    private void BtnTataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTataActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 5;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Tata Cara ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnTataActionPerformed

    private void BtnTujuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTujuanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 6;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Tujuan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnTujuanActionPerformed

    private void BtnResikoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResikoActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 7;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Resiko Tindakan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnResikoActionPerformed

    private void BtnKomplikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKomplikasiActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 8;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Komplikasi Tindakan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnKomplikasiActionPerformed

    private void BtnPrognosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrognosisActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 9;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Prognosis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnPrognosisActionPerformed

    private void BtnAlternatifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlternatifActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 10;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Alternatif Tindakan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnAlternatifActionPerformed

    private void BtnLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLainActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 11;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Lain-Lain ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnLainActionPerformed

    private void cmbKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKategoriActionPerformed
        if (cmbKategori.getSelectedIndex() == 0) {
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
            
            //Transfusi Darah
        } else if (cmbKategori.getSelectedIndex() == 1) {
            TDiagKerja.setText("Anemia\nTrombositopenia");
            TDasarDiag.setText("Badan lemah, letih, lesu, pucat, terdapat tanda-tanda perdarahan, demam");
            Ttindakan.setText("Darah lengkap, darah merah dicuci, darah merah pekat, trombosit konsentrat, "
                    + "plasma beku, kriopresipitat");
            Tindikasi.setText("Adanya perdarahan akut / kehilangan darah, meningkatkan massa eritrosit, meningkatkan trombosit, "
                    + "gangguan koagulasi, mengatasi defisiensi faktor-faktor pembekuan darah");
            Ttatacara.setText("Formulir permintaan darah/komponen darah ditandatangani dokter, pengambilan sampel darah, "
                    + "pemberian transfusi darah secara benar & cermat, darah dihangatkan dulu, saat transfusi 5-10 menit pertama "
                    + "harus diawasi, kecepatan jangan melebihi 100 cc/menit");
            Ttujuan.setText("Memberikan kebutuhan sel darah atau komponen darah");
            Tresiko.setText("Reaksi transfusi cepat : hemolitik kuat, hipervolemik, hemolisis non imun, sepsis\n"
                    + "Reaksi transfusi lambat : hemolitik lambat, infeksi, reaksi lambat lainnya");
            Tkomplikasi.setText("Reaksi transfusi cepat : hemolitik kuat, hipervolemik, hemolisis non imun, sepsis\n"
                    + "Reaksi transfusi lambat : hemolitik lambat, infeksi, reaksi lambat lainnya");
            Tprognosis.setText("Ad bonam");
            Talternatif.setText("");
            Tlain.setText("");
            
            //Trombopharesis
        } else if (cmbKategori.getSelectedIndex() == 2) {
            TDiagKerja.setText("Polisetemia vera / Trombosis Esensial");
            TDasarDiag.setText("Pemeriksaan Fisik dan Laboratorium");
            Ttindakan.setText("Trombopharesis");
            Tindikasi.setText("Terapeutik");
            Ttatacara.setText("Menggunakan Alat Haemonetic MCS");
            Ttujuan.setText("Menurunkan angka Trombosit");
            Tresiko.setText("Syock");
            Tkomplikasi.setText("Perdarahan");
            Tprognosis.setText("Dubia");
            Talternatif.setText("");
            Tlain.setText("");
            
            //Restrain
        } else if (cmbKategori.getSelectedIndex() == 3) {
            TDiagKerja.setText("");
            TDasarDiag.setText("");
            Ttindakan.setText("Pemasangan Restrain");
            Tindikasi.setText("Pasien gelisah atau delirium dan memberontak\n"
                    + "Pasien tidak kooperatif\n"
                    + "Ketidakmampuan dalam mengikuti perintah untuk tidak meninggalkan tempat tidur");
            Ttatacara.setText("Sesuai petunjuk pada Observasi Restrain");
            Ttujuan.setText("Mencegah jatuh\n"
                    + "Mencegah tercabutnya IV/ NGT/ Cateter urine");
            Tresiko.setText("Iritasi");
            Tkomplikasi.setText("Edema\n"
                    + "Terganggunya sirkulasi");
            Tprognosis.setText("");
            Talternatif.setText("");
            Tlain.setText("");
            
            //Leukopharesis
        } else if (cmbKategori.getSelectedIndex() == 4) {
            TDiagKerja.setText("CML / AML / ALL");
            TDasarDiag.setText("Pemeriksaan Fisik dan Laboratorium");
            Ttindakan.setText("Leukopharesis");
            Tindikasi.setText("Terapeutik");
            Ttatacara.setText("Menggunakan Alat Haemonetic MCS");
            Ttujuan.setText("Menurunkan angka Leukosit");
            Tresiko.setText("Syock");
            Tkomplikasi.setText("Perdarahan");
            Tprognosis.setText("Dubia");
            Talternatif.setText("");
            Tlain.setText("");
            
            //BMP
        } else if (cmbKategori.getSelectedIndex() == 5) {
            TDiagKerja.setText("Myelopoliperatif Neoplasma");
            TDasarDiag.setText("Pemeriksaan Fisik dan Laboratorium");
            Ttindakan.setText("Bone Marrow Aspirasi");
            Tindikasi.setText("Diagnosis");
            Ttatacara.setText("Lewat SIPS atau Sternum");
            Ttujuan.setText("Mengetahui jenis kelainan");
            Tresiko.setText("Perdarahan");
            Tkomplikasi.setText("Fraktur, Syock, Nyeri");
            Tprognosis.setText("Dubia");
            Talternatif.setText("");
            Tlain.setText("");
            
            //NGT
        } else if (cmbKategori.getSelectedIndex() == 6) {
            TDiagKerja.setText("");
            TDasarDiag.setText("Pemeriksaan Fisik");
            Ttindakan.setText("Pemasangan NGT (Nasogastric Tube)");
            Tindikasi.setText("Pasien tidak sadar, Pasien kesulitan menelan, Pasien keracunan, pasien yang muntah darah, dekompresi lambung");
            Ttatacara.setText("Sesuai SPO");
            Ttujuan.setText("Pemberian nutrisi (makanan cair) dan pemberian obat Monitor cairan lambung");
            Tresiko.setText("Nyeri, luka pada hidung, sinus, tenggorokan, kerongkongan, dan lambung");
            Tkomplikasi.setText("Erosi hidung, Epistaksis, Perforasi Tracheobronchial, edema laring, pneumonia aspirasi, pneumothorax");
            Tprognosis.setText("");
            Talternatif.setText("");
            Tlain.setText("");
            
            //DC
        } else if (cmbKategori.getSelectedIndex() == 7) {
            TDiagKerja.setText("");
            TDasarDiag.setText("Pemeriksaan Fisik");
            Ttindakan.setText("Pemasangan Kateter");
            Tindikasi.setText("Penurunan Kesadaran\n"
                    + "Inkontinensia urine\n"
                    + "Retensio urine\n"
                    + "Bladder outlet obstrukction dan tindakan bedah tertentu");
            Ttatacara.setText("Sesuai SPO");
            Ttujuan.setText("Mengeluarkan urine atau mengosongkan kandung kemih\n"
                    + "Monitor produksi urine");
            Tresiko.setText("Nyeri, Striktur uretra, perforasi, ruptur uretera, perdarahan");
            Tkomplikasi.setText("Infeksi, trauma");
            Tprognosis.setText("");
            Talternatif.setText("");
            Tlain.setText("");
            
            //Transfusi Zat Besi
        } else if (cmbKategori.getSelectedIndex() == 8) {
            TDiagKerja.setText("Anemia Defisiensi Besi");
            TDasarDiag.setText("Laboratorium dan Pemeriksaan Fisik");
            Ttindakan.setText("Tranfusi Zat Besi");
            Tindikasi.setText("Terapi");
            Ttatacara.setText("Sesuai Protokol Regimen Besi Sukrosa");
            Ttujuan.setText("Menaikkan kadar besi darah");
            Tresiko.setText("Nyeri");
            Tkomplikasi.setText("Phlebitis, Alergi");
            Tprognosis.setText("Baik");
            Talternatif.setText("-");
            Tlain.setText("-");
            
            //Biopsi
        } else if (cmbKategori.getSelectedIndex() == 9) {
            TDiagKerja.setText("Tumor                   dd Primer / Sekunder (metastasis)");
            TDasarDiag.setText("Pemeriksaan fisik, lab dan imaging");
            Ttindakan.setText("Biopsi / aspirasi / sitologi");
            Tindikasi.setText("Diagnosis / terapi");
            Ttatacara.setText("Percutaneous core biopsi / aspirasi / sitologi");
            Ttujuan.setText("Mengetahui jenis tumor");
            Tresiko.setText("Nyeri dan perdarahan");
            Tkomplikasi.setText("");
            Tprognosis.setText("Dubia");
            Talternatif.setText("-");
            Tlain.setText("-");
            
            //Pengobatan Kemoterapi
        } else if (cmbKategori.getSelectedIndex() == 10) {
            TDiagKerja.setText("");
            TDasarDiag.setText("- Histopatologi/Imunohistokimia dari jaringan\n"
                    + "- Sitopatologi/Imunohistokimia\n"
                    + "- Mutasi genetik\n"
                    + "- Pemeriksaan darah tepi\n"
                    + "- Pemeriksaan lain : ...\n");
            Ttindakan.setText("Kemoterapi ...\n"
                    + "Jumlah Siklus ...\n"
                    + "Tipe Kemoterapi : \n"
                    + "- Lini Pertama : ...\n"
                    + "- Lini Kedua : ...\n"
                    + "- Lain-lain ...\n");
            Tindikasi.setText("Pengobatan sistemis kanker");
            Ttatacara.setText("Sesuai protokol kemoterapi yang dibuat pada lembar infus pasien");
            Ttujuan.setText("Tujuan Tindakan :\n"
                    + "- Neoajuvan\n"
                    + "  Mengecilkan ukuran tumor sebelum operasi/radioterapi\n"
                    + "- Ajuvan\n"
                    + "  Menghabiskan sisa sel kanker pasca operasi/radioterapi\n"
                    + "- Primer\n"
                    + "  Membunuh sel kanker yang tersebar di seluruh tubuh tanpa didampingi\n"
                    + "  modalitas pengobatan lain (Operasi atau radioterapi)\n"
                    + "- Sensitizer\n"
                    + "  Diberikan dalam dosis kecil bersamaan dengan radioterapi untuk\n"
                    + "  memperkuat efek radioterapi\n"
                    + "- Concurent\n"
                    + "  Diberikan dalam dosis penuh bersamaan dengan radioterapi sebagai\n"
                    + "  terapi utama membunuh sel kanker\n\n"
                    + "Tujuan Pengobatan :\n"
                    + "- Cure/Sembuh\n"
                    + "- Prolonged Life/Perpanjang Umur\n"
                    + "- Quality Of Life/Meningkatkan Kualitas Hidup\n"
                    + "- Paliatif\n");
            Tresiko.setText("- Mual/muntah dan gangguan nafsu makan\n"
                    + "- Gangguan siklus buang air besar (dari sembelit sampai diare)\n"
                    + "- Rambut rontok sampai kebotakan\n"
                    + "- Sariawan/radang mulut\n"
                    + "- Mulut/kerongkongan kering\n"
                    + "- Penurunan kadar hemoglobin (anemia dan atau jumlah sel darah\n"
                    + "  (sel darah merah, sel darah putih, keping darah))\n"
                    + "- Perubahan warna kulit\n"
                    + "- Kesemutan pada ujung-ujung jari\n"
                    + "- Rasa lelah dan lemas seluruh tubuh\n"
                    + "- Gatal atau timbul lenting/bentol pada kulit\n"
                    + "- Gangguan siklus menstruasi/haid (khusus perempuan)\n"
                    + "- Gangguan ereksi (khusus pria)\n"
                    + "- Rasa panas/nyeri pada lokasi infus\n"
                    + "- Pengentalan darah berlebih\n"
                    + "- Resiko lain : ...\n");
            Tkomplikasi.setText("- Gangguan fungsi atau kerusakan organ vital\n"
                    + "  (ginjal, hati, paru, jantung, otak)\n"
                    + "- Infeksi\n"
                    + "- Perdarahan\n"
                    + "- Berat badan turun atau malnutrisi\n"
                    + "- Kejang\n"
                    + "- Dehidrasi\n"
                    + "- Kebocoran usus\n"
                    + "- Reaksi alergi (mulai dari gatal, bentol, hingga bengkak pada\n"
                    + "  bagian tubuh tertentu)\n"
                    + "- Gangguan kesuburan sampai kemandulan\n"
                    + "- Keguguran/kematian janin dalam rahim (bila pasien hamil)\n"
                    + "- Buang air kecil berdarah\n"
                    + "- Kulit melepuh\n"
                    + "- Penyumbatan pembuluh darah (trombosis)\n"
                    + "- Kecacatan\n"
                    + "- Kematian\n"
                    + "- Komplikasi lain : ...\n");
            Tprognosis.setText("Kualitatif\n"
                    + "- Baik\n"
                    + "- Sedang cenderung baik\n"
                    + "- Sedang\n"
                    + "- Sedang cenderung buruk\n"
                    + "- Buruk\n\n"
                    + "Kuantitatif\n"
                    + "- Harapan hidup ... tahun sekitar ... %\n"
                    + "Lain-lain : ...\n");
            Talternatif.setText("- Bila kemoterapi sebagai pendamping terapi utama (operasi/radioterapi)\n"
                    + "  tidak diberikan, maka keefektifan terapi utama akan berkurang dan\n"
                    + "  kanker menjadi tidak hilang di akhir pengobatan serta mudah terjadi\n"
                    + "  kekambuhan\n\n"
                    + "- Bila kemoterapi sebagai terapi utama tidak diberikan, stadium kanker\n"
                    + "  dapat bertambah dan dapat mengancam jiwa pasien. Modalitas lain (radioterapi/operasi)\n"
                    + "  mungkin dapat diberikan sesuai kondisi masing-masing pasien namun tidak seefektif\n"
                    + "  kemoterapi\n\n"
                    + "- Rejimen kemoterapi dapat diubah menjadi ...\n"
                    + "  untuk alasan ...\n"
                    + "  Namun efektifitas pengobatan mungkin dapat berkurang\n");
            Tlain.setText("- Selama kemoterapi, kondisi pasien akan dipantau oleh dokter dan perawat.\n"
                    + "  Pasien dapat melaporkan kepada perawat atau dokter kapan saja jika terdapat\n"
                    + "  keluhan selama proses kemoterapi berlangsung. Proses kemoterapi dapat dihentikan\n"
                    + "  sewaktu-waktu sebelum selesai sesuai atas pertimbangan tertentu dari dokter atau\n"
                    + "  permintaan pasien\n\n"
                    + "- Keadaan umum pasien akan dipantau selama ...\n"
                    + "  jam/hari setelah kemoterapi selesai, bila dinilai stabil maka pasien dapat\n"
                    + "  dipulangkan\n\n"
                    + "- Obat kemoterapi yang benbentuk obat minum (tablet/kapsul) dapat dilanjutkan\n"
                    + "  dirumah sesuai petunjuk dari dokter dan harus dihabiskan, pastikan untuk mendapatkan\n"
                    + "  informasi secara jelas sebelum pulang karena dapat mempengaruhi hasil pengobatan\n\n"
                    + "- Pasien akan dibekali obat anti mual dan beberapa obat pendukung lainnya bila\n"
                    + "  diperlukan untuk diminum dirumah dan harus diminum sesuai petunjuk walaupun rasa\n"
                    + "  mual belum muncul\n\n"
                    + "- Pasien harus kembali berobat ke dokter spesialis penyakit dalam konsultan\n"
                    + "  Hemato-Onkologi Medik sesuai jadwal yang telah ditentukan agar program kemoterapi\n"
                    + "  dapat diberikan tepat waktu\n\n"
                    + "- Bila timbul gejala-gejala efek samping/komplikasi (seperti yang tertera diatas pada\n"
                    + "  bagian resiko dan komplikasi) segera berobat ke poliklinik Onkologi Medik, IGD atau\n"
                    + "  tenaga medis terdekat untuk mendapatkan pertolongan medis.\n");
            
            //punksi cairan pleura
        } else if (cmbKategori.getSelectedIndex() == 11) {
            TDiagKerja.setText("Efusi Pleura Sinistra");
            TDasarDiag.setText("Ro Thorax, USG Thorak Marker");
            Ttindakan.setText("Punksi Cairan Pleura");
            Tindikasi.setText("Diagnosis dan Terapi");
            Ttatacara.setText("Sesuai SOP");
            Ttujuan.setText("Mengeluarkan cairan berlebih dari pleura dan diagnostik");
            Tresiko.setText("Trauma, Perdarahan");
            Tkomplikasi.setText("Perdarahan, Pneumuthorax");
            Tprognosis.setText("Dubia");
            Talternatif.setText("WSD");
            Tlain.setText("");
            
            //punksi ascites
        } else if (cmbKategori.getSelectedIndex() == 12) {
            TDiagKerja.setText("SIROSIS HEPATIS, HF");
            TDasarDiag.setText("PERUT MEMBESAR BERISI CAIRAN");
            Ttindakan.setText("PUNKSI ASCITES");
            Tindikasi.setText("ASCITES");
            Ttatacara.setText("BAGIAN PERUT DITUSUK DENGAN JARUM YANG TERSAMBUNG DENGAN SELANG UNTUK MENGALIRKAN CAIRAN YANG ADA DALAM PERUT");
            Ttujuan.setText("MENGURANGI CAIRAN DALAM RONGGA PERUT");
            Tresiko.setText("");
            Tkomplikasi.setText("");
            Tprognosis.setText("");
            Talternatif.setText("");
            Tlain.setText("");
        }
    }//GEN-LAST:event_cmbKategoriActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void chkSamaPelaksanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSamaPelaksanaActionPerformed
        if (chkSamaPelaksana.isSelected() == true) {
            nip_pemberi.setText(kddokter_pelaksana.getText());
            nmpemberi.setText(nmdokter_pelaksana.getText());
        } else {
            nip_pemberi.setText("");
            nmpemberi.setText("");
        }
    }//GEN-LAST:event_chkSamaPelaksanaActionPerformed

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
    private widget.Button BtnAlternatif;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCopas;
    private widget.Button BtnDasarDiag;
    private widget.Button BtnDiagKerja;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnIndikasi;
    private widget.Button BtnKabupaten;
    private widget.Button BtnKecamatan;
    private widget.Button BtnKeluar;
    private widget.Button BtnKelurahan;
    private widget.Button BtnKomplikasi;
    private widget.Button BtnLain;
    private widget.Button BtnPemberi;
    private widget.Button BtnPrint;
    private widget.Button BtnPrognosis;
    private widget.Button BtnResiko;
    private widget.Button BtnSimpan;
    private widget.Button BtnTata;
    private widget.Button BtnTindakan;
    private widget.Button BtnTujuan;
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
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane ScrollTriase1;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextArea TDasarDiag;
    private widget.TextArea TDiagKerja;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea Talternatif;
    public widget.Tanggal TglBeriTindakan;
    public widget.Tanggal TglSetuju;
    public widget.Tanggal TglTolak;
    private widget.TextArea Tindikasi;
    private widget.TextArea Tkomplikasi;
    private widget.TextArea Tlain;
    public widget.TextBox TnmPenerima;
    private widget.TextArea Tprognosis;
    private widget.TextArea Tresiko;
    private widget.TextArea Ttatacara;
    private widget.TextArea Ttemplate;
    private widget.TextArea Ttindakan;
    private widget.TextArea Ttujuan;
    private javax.swing.JDialog WindowTemplate;
    private widget.TextBox alamatPJ;
    public widget.TextBox alasanTolak;
    private widget.CekBox chkSamaNama;
    private widget.CekBox chkSamaPelaksana;
    public widget.ComboBox cmbKategori;
    public widget.ComboBox cmbTindakan;
    private widget.ComboBox hubungan;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel12;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel36;
    private widget.Label jLabel4;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
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
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private javax.swing.JPanel jPanel1;
    private widget.ComboBox jkPJ;
    public widget.TextBox kddokter_pelaksana;
    public widget.TextBox nip_pemberi;
    public widget.TextBox nmPJ;
    public widget.TextBox nmdokter_pelaksana;
    private widget.TextBox nmkab;
    private widget.TextBox nmkec;
    private widget.TextBox nmkel;
    public widget.TextBox nmpemberi;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi4;
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
    private widget.Table tbTemplate;
    private widget.Table tbTindakan;
    private widget.TextBox tindakanDokter;
    private widget.TextBox umurPJ;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT if(st.kasus_tindakan='Ralan','R. Jalan','R. Inap') jnsrawat, "
                    + "if(jns_surat='PERSETUJUAN',concat(date_format(tgl_persetujuan,'%d-%m-%Y'),' / ',time_format(jam_persetujuan,'%H:%i')),'-') tgl_setuju, "
                    + "if(jns_surat='PENOLAKAN',concat(date_format(tgl_penolakan,'%d-%m-%Y'),' / ',time_format(jam_penolakan,'%H:%i')),'-') tgl_tolak, "
                    + "st.no_rawat, p.no_rkm_medis, p.nm_pasien, st.nm_penjab, st.umur_penjab, st.alamat_penjab, "
                    + "st.hubungan_dg_pasien, st.jns_surat, st.tgl_surat, st.jam_surat, st.jk_penjab, st.kd_kel, st.kd_kec, "
                    + "st.kd_kab, st.kasus_tindakan, st.nm_tindakan_kedokteran, st.alasan_penolakan, st.nip_dokter_pelaksana, "
                    + "st.nip_pemberi_info, st.penerima_info, st.isi_info_diagnosis_kerja, st.isi_info_dasar_diagnosis, st.isi_info_tindakan, "
                    + "st.isi_info_indikasi, st.isi_info_tatacara, st.isi_info_tujuan, st.isi_info_resiko, st.isi_info_komplikasi, "
                    + "st.isi_info_prognosis, st.isi_info_alternatif, st.isi_info_lainlain, st.waktu_simpan, st.tgl_persetujuan, "
                    + "st.jam_persetujuan, st.tgl_penolakan, st.jam_penolakan, kl.nm_kel, kc.nm_kec, kb.nm_kab, d.nm_dokter, pg.nama petugas, "
                    + "if(st.jk_penjab='L','Laki-laki','Perempuan') jenkel from surat_tindakan_kedokteran st inner join reg_periksa rp on rp.no_rawat=st.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join kelurahan kl on kl.kd_kel=st.kd_kel "
                    + "inner join kecamatan kc on kc.kd_kec=st.kd_kec inner join kabupaten kb on kb.kd_kab=st.kd_kab "
                    + "inner join dokter d on d.kd_dokter=st.nip_dokter_pelaksana inner join pegawai pg on pg.nik=st.nip_pemberi_info where "
                    + "rp.tgl_registrasi between ? and ? and st.no_rawat like ? or "
                    + "rp.tgl_registrasi between ? and ? and p.no_rkm_medis like ? or "
                    + "rp.tgl_registrasi between ? and ? and p.nm_pasien like ? or "
                    + "rp.tgl_registrasi between ? and ? and st.nm_penjab like ? or "
                    + "rp.tgl_registrasi between ? and ? and st.alamat_penjab like ? or "
                    + "rp.tgl_registrasi between ? and ? and st.hubungan_dg_pasien like ? or "
                    + "rp.tgl_registrasi between ? and ? and st.jns_surat like ? or "
                    + "rp.tgl_registrasi between ? and ? and st.kasus_tindakan like ? or "
                    + "rp.tgl_registrasi between ? and ? and st.nm_tindakan_kedokteran like ? or "
                    + "rp.tgl_registrasi between ? and ? and d.nm_dokter like ? or "
                    + "rp.tgl_registrasi between ? and ? and pg.nama like ? order by st.no_rawat desc, rp.tgl_registrasi desc");
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
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText().trim() + "%");
                ps.setString(28, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(29, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(30, "%" + TCari.getText().trim() + "%");
                ps.setString(31, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(32, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(33, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("jnsrawat"),
                        rs.getString("tgl_setuju"),
                        rs.getString("tgl_tolak"),
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("nm_penjab"),
                        rs.getString("umur_penjab"),
                        rs.getString("alamat_penjab"),
                        rs.getString("hubungan_dg_pasien"),
                        rs.getString("jns_surat"),
                        rs.getString("tgl_surat"),
                        rs.getString("jam_surat"),
                        rs.getString("nm_penjab"),
                        rs.getString("umur_penjab"),                        
                        rs.getString("jk_penjab"),
                        rs.getString("alamat_penjab"),
                        rs.getString("kd_kel"),
                        rs.getString("kd_kec"),
                        rs.getString("kd_kab"),
                        rs.getString("hubungan_dg_pasien"),
                        rs.getString("jns_surat"),                        
                        rs.getString("kasus_tindakan"),
                        rs.getString("nm_tindakan_kedokteran"),
                        rs.getString("alasan_penolakan"),
                        rs.getString("nip_dokter_pelaksana"),
                        rs.getString("nip_pemberi_info"),
                        rs.getString("penerima_info"),
                        rs.getString("isi_info_diagnosis_kerja"),
                        rs.getString("isi_info_dasar_diagnosis"),
                        rs.getString("isi_info_tindakan"),
                        rs.getString("isi_info_indikasi"),
                        rs.getString("isi_info_tatacara"),
                        rs.getString("isi_info_tujuan"),
                        rs.getString("isi_info_resiko"),
                        rs.getString("isi_info_komplikasi"),
                        rs.getString("isi_info_prognosis"),
                        rs.getString("isi_info_alternatif"),
                        rs.getString("isi_info_lainlain"),
                        rs.getString("waktu_simpan"),
                        rs.getString("tgl_persetujuan"),                        
                        rs.getString("jam_persetujuan"),
                        rs.getString("tgl_penolakan"),
                        rs.getString("jam_penolakan"),
                        rs.getString("nm_kel"),
                        rs.getString("nm_kec"),                        
                        rs.getString("nm_kab"),
                        rs.getString("nm_dokter"),
                        rs.getString("petugas"),
                        rs.getString("jenkel")
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
        kdkel = "0";
        nmkel.setText("-");
        kdkec = "0";
        nmkec.setText("-");
        kdkab = "0";
        nmkab.setText("-");
        jk = "";
        wktSimpan = "";
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
        TnmPenerima.setText("");
        chkSamaNama.setSelected(false);
        cmbKategori.setSelectedIndex(0);
        cmbKategori.setEnabled(true);
        chkSamaPelaksana.setSelected(false);
    }
    
    public void setData(String norwt, String norm, String nmpasien, String jnsrwt) {
        TNoRw.setText(norwt);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        jnsrawat = jnsrwt;
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(new Date());
        TCari.setText(norwt);
        cekData();
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnPrint.setEnabled(akses.getcppt());
        BtnEdit.setEnabled(akses.getcppt());
    }
    
    private void getData() {
        kdkel = "";
        kdkec = "";
        kdkab = "";
        jk = "";
        wktSimpan = "";
        
        if (tbTindakan.getSelectedRow() != -1) {
            TNoRw.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 3).toString());
            TNoRM.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 4).toString());
            TPasien.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(),5).toString());
            nmPJ.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 6).toString());
            umurPJ.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(),7).toString());
            jkPJ.setSelectedItem(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 49).toString());
            alamatPJ.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 8).toString());
            jk = tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 15).toString();
            kdkel = tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 17).toString();
            kdkec = tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 18).toString();
            kdkab = tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 19).toString();
            nmkel.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 44).toString());
            nmkec.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 45).toString());
            nmkab.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 46).toString());
            hubungan.setSelectedItem(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 9).toString());
            cmbTindakan.setSelectedItem(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 10).toString());
            Valid.SetTgl(TglSetuju, tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 40).toString());
            CmbJam2.setSelectedItem(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 41).toString().substring(0, 2));
            CmbMenit2.setSelectedItem(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 41).toString().substring(3, 5));
            CmbDetik2.setSelectedItem(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 41).toString().substring(6, 8));
            Valid.SetTgl(TglTolak, tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 42).toString());
            CmbJam3.setSelectedItem(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 43).toString().substring(0, 2));
            CmbMenit3.setSelectedItem(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 43).toString().substring(3, 5));
            CmbDetik3.setSelectedItem(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 43).toString().substring(6, 8));
            tindakanDokter.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 23).toString());
            alasanTolak.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 24).toString());
            Valid.SetTgl(TglBeriTindakan, tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 11).toString());
            CmbJam1.setSelectedItem(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 12).toString().substring(0, 2));
            CmbMenit1.setSelectedItem(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 12).toString().substring(3, 5));
            CmbDetik1.setSelectedItem(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 12).toString().substring(6, 8));
            kddokter_pelaksana.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 25).toString());
            nmdokter_pelaksana.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 47).toString());
            nip_pemberi.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 26).toString());
            nmpemberi.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 48).toString());
            TnmPenerima.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 27).toString());
            TDiagKerja.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 28).toString());
            TDasarDiag.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 29).toString());
            Ttindakan.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 30).toString());
            Tindikasi.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 31).toString());
            Ttatacara.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 32).toString());
            Ttujuan.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 33).toString());
            Tresiko.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 34).toString());
            Tkomplikasi.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 35).toString());
            Tprognosis.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 36).toString());
            Talternatif.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 37).toString());
            Tlain.setText(tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 38).toString());
            wktSimpan = tbTindakan.getValueAt(tbTindakan.getSelectedRow(), 39).toString();
            
            if (jk.equals("L")) {
                jkPJ.setSelectedIndex(0);
            } else {
                jkPJ.setSelectedIndex(1);
            }
            
            if (cmbTindakan.getSelectedIndex() == 0) {
                alasanTolak.setEnabled(false);
            } else {
                alasanTolak.setEnabled(true);
            }
        }
    }
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode1);
        try {
            if (pilihan == 1) {
                ps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, c.* from surat_tindakan_kedokteran c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.isi_info_diagnosis_kerja<>'' and p.no_rkm_medis like ? OR "
                        + "c.isi_info_diagnosis_kerja<>'' and p.nm_pasien like ? OR "
                        + "c.isi_info_diagnosis_kerja<>'' and c.isi_info_diagnosis_kerja like ? ORDER BY c.waktu_simpan desc limit 20");
            } else if (pilihan == 2) {
                ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, c.* from surat_tindakan_kedokteran c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.isi_info_dasar_diagnosis<>'' and p.no_rkm_medis like ? OR "
                        + "c.isi_info_dasar_diagnosis<>'' and p.nm_pasien like ? OR "
                        + "c.isi_info_dasar_diagnosis<>'' and c.isi_info_dasar_diagnosis like ? ORDER BY c.waktu_simpan desc limit 20");
            } else if (pilihan == 3) {
                ps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, c.* from surat_tindakan_kedokteran c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.isi_info_tindakan<>'' and p.no_rkm_medis like ? OR "
                        + "c.isi_info_tindakan<>'' and p.nm_pasien like ? OR "
                        + "c.isi_info_tindakan<>'' and c.isi_info_tindakan like ? ORDER BY c.waktu_simpan desc limit 20");
            } else if (pilihan == 4) {
                ps4 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, c.* from surat_tindakan_kedokteran c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.isi_info_indikasi<>'' and p.no_rkm_medis like ? OR "
                        + "c.isi_info_indikasi<>'' and p.nm_pasien like ? OR "
                        + "c.isi_info_indikasi<>'' and c.isi_info_indikasi like ? ORDER BY c.waktu_simpan desc limit 20");
            } else if (pilihan == 5) {
                ps5 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, c.* from surat_tindakan_kedokteran c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.isi_info_tatacara<>'' and p.no_rkm_medis like ? OR "
                        + "c.isi_info_tatacara<>'' and p.nm_pasien like ? OR "
                        + "c.isi_info_tatacara<>'' and c.isi_info_tatacara like ? ORDER BY c.waktu_simpan desc limit 20");
            } else if (pilihan == 6) {
                ps6 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, c.* from surat_tindakan_kedokteran c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.isi_info_tujuan<>'' and p.no_rkm_medis like ? OR "
                        + "c.isi_info_tujuan<>'' and p.nm_pasien like ? OR "
                        + "c.isi_info_tujuan<>'' and c.isi_info_tujuan like ? ORDER BY c.waktu_simpan desc limit 20");
            } else if (pilihan == 7) {
                ps7 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, c.* from surat_tindakan_kedokteran c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.isi_info_resiko<>'' and p.no_rkm_medis like ? OR "
                        + "c.isi_info_resiko<>'' and p.nm_pasien like ? OR "
                        + "c.isi_info_resiko<>'' and c.isi_info_resiko like ? ORDER BY c.waktu_simpan desc limit 20");
            } else if (pilihan == 8) {
                ps8 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, c.* from surat_tindakan_kedokteran c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.isi_info_komplikasi<>'' and p.no_rkm_medis like ? OR "
                        + "c.isi_info_komplikasi<>'' and p.nm_pasien like ? OR "
                        + "c.isi_info_komplikasi<>'' and c.isi_info_komplikasi like ? ORDER BY c.waktu_simpan desc limit 20");
            } else if (pilihan == 9) {
                ps9 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, c.* from surat_tindakan_kedokteran c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.isi_info_prognosis<>'' and p.no_rkm_medis like ? OR "
                        + "c.isi_info_prognosis<>'' and p.nm_pasien like ? OR "
                        + "c.isi_info_prognosis<>'' and c.isi_info_prognosis like ? ORDER BY c.waktu_simpan desc limit 20");
            } else if (pilihan == 10) {
                ps10 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, c.* from surat_tindakan_kedokteran c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.isi_info_alternatif<>'' and p.no_rkm_medis like ? OR "
                        + "c.isi_info_alternatif<>'' and p.nm_pasien like ? OR "
                        + "c.isi_info_alternatif<>'' and c.isi_info_alternatif like ? ORDER BY c.waktu_simpan desc limit 20");
            } else if (pilihan == 11) {
                ps11 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, c.* from surat_tindakan_kedokteran c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.isi_info_lainlain<>'' and p.no_rkm_medis like ? OR "
                        + "c.isi_info_lainlain<>'' and p.nm_pasien like ? OR "
                        + "c.isi_info_lainlain<>'' and c.isi_info_lainlain like ? ORDER BY c.waktu_simpan desc limit 20");
            }
            
            try {
                if (pilihan == 1) {
                    ps1.setString(1, "%" + TCari1.getText() + "%");
                    ps1.setString(2, "%" + TCari1.getText() + "%");
                    ps1.setString(3, "%" + TCari1.getText() + "%");
                    rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        tabMode1.addRow(new String[]{
                            rs1.getString("no_rkm_medis"),
                            rs1.getString("nm_pasien"),
                            rs1.getString("isi_info_diagnosis_kerja")
                        });
                    }
                } else if (pilihan == 2) {
                    ps2.setString(1, "%" + TCari1.getText() + "%");
                    ps2.setString(2, "%" + TCari1.getText() + "%");
                    ps2.setString(3, "%" + TCari1.getText() + "%");
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        tabMode1.addRow(new String[]{
                            rs2.getString("no_rkm_medis"),
                            rs2.getString("nm_pasien"),
                            rs2.getString("isi_info_dasar_diagnosis")
                        });
                    }
                } else if (pilihan == 3) {
                    ps3.setString(1, "%" + TCari1.getText() + "%");
                    ps3.setString(2, "%" + TCari1.getText() + "%");
                    ps3.setString(3, "%" + TCari1.getText() + "%");
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        tabMode1.addRow(new String[]{
                            rs3.getString("no_rkm_medis"),
                            rs3.getString("nm_pasien"),
                            rs3.getString("isi_info_tindakan")
                        });
                    }
                } else if (pilihan == 4) {
                    ps4.setString(1, "%" + TCari1.getText() + "%");
                    ps4.setString(2, "%" + TCari1.getText() + "%");
                    ps4.setString(3, "%" + TCari1.getText() + "%");
                    rs4 = ps4.executeQuery();
                    while (rs4.next()) {
                        tabMode1.addRow(new String[]{
                            rs4.getString("no_rkm_medis"),
                            rs4.getString("nm_pasien"),
                            rs4.getString("isi_info_indikasi")
                        });
                    }
                } else if (pilihan == 5) {
                    ps5.setString(1, "%" + TCari1.getText() + "%");
                    ps5.setString(2, "%" + TCari1.getText() + "%");
                    ps5.setString(3, "%" + TCari1.getText() + "%");
                    rs5 = ps5.executeQuery();
                    while (rs5.next()) {
                        tabMode1.addRow(new String[]{
                            rs5.getString("no_rkm_medis"),
                            rs5.getString("nm_pasien"),
                            rs5.getString("isi_info_tatacara")
                        });
                    }
                } else if (pilihan == 6) {
                    ps6.setString(1, "%" + TCari1.getText() + "%");
                    ps6.setString(2, "%" + TCari1.getText() + "%");
                    ps6.setString(3, "%" + TCari1.getText() + "%");
                    rs6 = ps6.executeQuery();
                    while (rs6.next()) {
                        tabMode1.addRow(new String[]{
                            rs6.getString("no_rkm_medis"),
                            rs6.getString("nm_pasien"),
                            rs6.getString("isi_info_tujuan")
                        });
                    }
                } else if (pilihan == 7) {
                    ps7.setString(1, "%" + TCari1.getText() + "%");
                    ps7.setString(2, "%" + TCari1.getText() + "%");
                    ps7.setString(3, "%" + TCari1.getText() + "%");
                    rs7 = ps7.executeQuery();
                    while (rs7.next()) {
                        tabMode1.addRow(new String[]{
                            rs7.getString("no_rkm_medis"),
                            rs7.getString("nm_pasien"),
                            rs7.getString("isi_info_resiko")
                        });
                    }
                } else if (pilihan == 8) {
                    ps8.setString(1, "%" + TCari1.getText() + "%");
                    ps8.setString(2, "%" + TCari1.getText() + "%");
                    ps8.setString(3, "%" + TCari1.getText() + "%");
                    rs8 = ps8.executeQuery();
                    while (rs8.next()) {
                        tabMode1.addRow(new String[]{
                            rs8.getString("no_rkm_medis"),
                            rs8.getString("nm_pasien"),
                            rs8.getString("isi_info_komplikasi")
                        });
                    }
                } else if (pilihan == 9) {
                    ps9.setString(1, "%" + TCari1.getText() + "%");
                    ps9.setString(2, "%" + TCari1.getText() + "%");
                    ps9.setString(3, "%" + TCari1.getText() + "%");
                    rs9 = ps9.executeQuery();
                    while (rs9.next()) {
                        tabMode1.addRow(new String[]{
                            rs9.getString("no_rkm_medis"),
                            rs9.getString("nm_pasien"),
                            rs9.getString("isi_info_prognosis")
                        });
                    }
                } else if (pilihan == 10) {
                    ps10.setString(1, "%" + TCari1.getText() + "%");
                    ps10.setString(2, "%" + TCari1.getText() + "%");
                    ps10.setString(3, "%" + TCari1.getText() + "%");
                    rs10 = ps10.executeQuery();
                    while (rs10.next()) {
                        tabMode1.addRow(new String[]{
                            rs10.getString("no_rkm_medis"),
                            rs10.getString("nm_pasien"),
                            rs10.getString("isi_info_alternatif")
                        });
                    }
                } else if (pilihan == 11) {
                    ps11.setString(1, "%" + TCari1.getText() + "%");
                    ps11.setString(2, "%" + TCari1.getText() + "%");
                    ps11.setString(3, "%" + TCari1.getText() + "%");
                    rs11 = ps11.executeQuery();
                    while (rs11.next()) {
                        tabMode1.addRow(new String[]{
                            rs11.getString("no_rkm_medis"),
                            rs11.getString("nm_pasien"),
                            rs11.getString("isi_info_lainlain")
                        });
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                } else if (rs2 != null) {
                    rs2.close();
                } else if (rs3 != null) {
                    rs3.close();
                } else if (rs4 != null) {
                    rs4.close();
                } else if (rs5 != null) {
                    rs5.close();
                } else if (rs6 != null) {
                    rs6.close();
                } else if (rs7 != null) {
                    rs7.close();
                } else if (rs8 != null) {
                    rs8.close();
                } else if (rs9 != null) {
                    rs9.close();
                } else if (rs10 != null) {
                    rs10.close();
                } else if (rs11 != null) {
                    rs11.close();
                } 

                if (ps1 != null) {
                    ps1.close();
                } else if (ps2 != null) {
                    ps2.close();
                } else if (ps3 != null) {
                    ps3.close();
                } else if (ps4 != null) {
                    ps4.close();
                } else if (ps5 != null) {
                    ps5.close();
                } else if (ps6 != null) {
                    ps6.close();
                } else if (ps7 != null) {
                    ps7.close();
                } else if (ps8 != null) {
                    ps8.close();
                } else if (ps9 != null) {
                    ps9.close();
                } else if (ps10 != null) {
                    ps10.close();
                } else if (ps11 != null) {
                    ps11.close();
                } 
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copas() {
        if (pilihan == 1) {
            TDiagKerja.setText(Ttemplate.getText());
        } else if (pilihan == 2) {
            TDasarDiag.setText(Ttemplate.getText());
        } else if (pilihan == 3) {
            Ttindakan.setText(Ttemplate.getText());
        } else if (pilihan == 4) {
            Tindikasi.setText(Ttemplate.getText());
        } else if (pilihan == 5) {
            Ttatacara.setText(Ttemplate.getText());
        } else if (pilihan == 6) {
            Ttujuan.setText(Ttemplate.getText());
        } else if (pilihan == 7) {
            Tresiko.setText(Ttemplate.getText());
        } else if (pilihan == 8) {
            Tkomplikasi.setText(Ttemplate.getText());
        } else if (pilihan == 9) {
            Tprognosis.setText(Ttemplate.getText());
        } else if (pilihan == 10) {
            Talternatif.setText(Ttemplate.getText());
        } else if (pilihan == 11) {
            Tlain.setText(Ttemplate.getText());
        }
    }

    private void cekData() {
        try {
            psCek = koneksi.prepareStatement("select *, if(jk='L','Laki-laki','Perempuan') jenkel from pasien where no_rkm_medis='" + TNoRM.getText() + "'");
            try {
                rsCek = psCek.executeQuery();
                while (rsCek.next()) {
                    nmPJ.setText(rsCek.getString("namakeluarga"));
                    umurPJ.setText(rsCek.getString("umur_pj"));
                    jkPJ.setSelectedItem(rsCek.getString("jenkel"));                    
                    
                    if (rsCek.getString("alamatpj").equals("ALAMAT") || rsCek.getString("alamatpj").equals("alamat")) {
                        alamatPJ.setText("");
                    } else {
                        alamatPJ.setText(rsCek.getString("alamatpj"));
                    }
                    
                    if (rsCek.getString("kelurahanpj").equals("KELURAHAN") || rsCek.getString("kelurahanpj").equals("kelurahan")
                            || rsCek.getString("kelurahanpj").equals("SDA") || rsCek.getString("kelurahanpj").equals("sda")) {
                        nmkel.setText("-");
                        kdkel = "0";
                    } else {
                        nmkel.setText(rsCek.getString("kelurahanpj"));
                        kdkel = Sequel.cariIsi("select if(count(-1)=0,'0',kd_kel) from kelurahan where nm_kel='" + nmkel.getText() + "'");
                    }

                    if (rsCek.getString("kecamatanpj").equals("KECAMATAN") || rsCek.getString("kecamatanpj").equals("kecamatan")
                            || rsCek.getString("kecamatanpj").equals("SDA") || rsCek.getString("kecamatanpj").equals("sda")) {
                        nmkec.setText("-");
                        kdkec = "0";
                    } else {
                        nmkec.setText(rsCek.getString("kecamatanpj"));
                        kdkec = Sequel.cariIsi("select if(count(-1)=0,'0',kd_kec) from kecamatan where nm_kec='" + nmkec.getText() + "'");
                    }

                    if (rsCek.getString("kabupatenpj").equals("KABUPATEN") || rsCek.getString("kabupatenpj").equals("kabupaten")
                            || rsCek.getString("kabupatenpj").equals("SDA") || rsCek.getString("kabupatenpj").equals("sda")) {
                        nmkab.setText("-");
                        kdkab = "0";
                    } else {
                        nmkab.setText(rsCek.getString("kabupatenpj"));
                        kdkab = Sequel.cariIsi("select if(count(-1)=0,'0',kd_kab) from kabupaten where nm_kab='" + nmkab.getText() + "'");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsCek != null) {
                    rsCek.close();
                }
                if (psCek != null) {
                    psCek.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
