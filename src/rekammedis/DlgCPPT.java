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
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import laporan.DlgHasilLIS;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDiet;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgCariJumlahPemberianDiet;
import simrskhanza.DlgCariPeriksaRadiologi;
import simrskhanza.DlgPemberianDiet;

/**
 *
 * @author dosen
 */
public class DlgCPPT extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps, pps1, pps2, pspasien, psdiet;
    private ResultSet rs, rrs1, rrs2, rspasien, rsdiet;
    private int i = 0, x = 0, pilihan = 0, cekHasilRad = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDiet diet = new DlgCariDiet(null, false);
    private DlgCariJumlahPemberianDiet jlhberi = new DlgCariJumlahPemberianDiet(null, false);
    private String status = "", cekjam = "", statusOK = "", nipppa = "", kdItemrad = "", itemDipilih = "",
            tglRad = "", jamRad = "", jamkeluar = "", nipkonsulen = "", dapatobat = "", obatsesuai = "",
            obatefektif = "", obataman = "", waktuSimpanDiet = "", kemasan = "", dataDiet = "", jnsRawat = "",
            kdUnit = "", instruksiDiet = "", siftppa = "";

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public DlgCPPT(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Tgl. CPPT",
            "Jam CPPT", "Hasil Pemeriksaan", "Instruksi Nakes", "Verifikasi",
            "Nama DPJP", "Status", "tanggal", "nip_dpjp", "wkt_simpan", "cekjam", "jam_cppt",
            "Jenis PPA", "Nama PPA", "Jenis Bagian", "nipppa", "serah_terima_cppt", "nmkonsulen", 
            "nipkonsulen", "petugas_serah", "petugas_terima", "nip_petugas_serah", "nip_petugas_terima", 
            "Shift", "jam_serah_terima"
        };
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbCPPT.setModel(tabMode);
        tbCPPT.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbCPPT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 29; i++) {
            TableColumn column = tbCPPT.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
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
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(70);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(65);
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
                column.setPreferredWidth(90);
            } else if (i == 17) {
                column.setPreferredWidth(250);
            } else if (i == 18) {
                column.setPreferredWidth(80);
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
                column.setPreferredWidth(60);
            } else if (i == 28) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }

        tbCPPT.setDefaultRenderer(Object.class, new WarnaTable());
        
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
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "kd_diet", "Nama Diet", "Jns. Makanan", "Jlh. Pemberian", "waktusimpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbDiet.setModel(tabMode2);
        tbDiet.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbDiet.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(180);
            } else if (i == 2) {
                column.setPreferredWidth(140);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDiet.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));

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
        
        diet.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgCPPT")) {
                    if (diet.getTable().getSelectedRow() != -1) {
                        kddiet.setText(diet.getTable().getValueAt(diet.getTable().getSelectedRow(), 0).toString());
                        nmdiet.setText(diet.getTable().getValueAt(diet.getTable().getSelectedRow(), 1).toString());
                        jnsMakanan.setText(diet.getTable().getValueAt(diet.getTable().getSelectedRow(), 2).toString());
                        
                        if (nmdiet.getText().equals("F75") || nmdiet.getText().equals("F100") || nmdiet.getText().equals("F135")) {
                            kdberi.setText("-");
                            jumlahBeri.setText("");
                            jumlahBeri.setEditable(true);
                            cmbSatuan.setEnabled(true);
                            jumlahBeri.requestFocus();
                        } else {
                            jumlahBeri.setEditable(false);
                            cmbSatuan.setEnabled(false);
                            btnJumlahBeri.requestFocus();
                        }
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
        
        diet.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgCPPT")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        diet.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        jlhberi.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgCPPT")) {
                    if (jlhberi.getTable().getSelectedRow() != -1) {
                        kdberi.setText(jlhberi.getTable().getValueAt(jlhberi.getTable().getSelectedRow(), 0).toString());
                        jumlahBeri.setText(jlhberi.getTable().getValueAt(jlhberi.getTable().getSelectedRow(), 1).toString());
                        cmbSatuan.setSelectedItem(jlhberi.getTable().getValueAt(jlhberi.getTable().getSelectedRow(), 2).toString());
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
        
        jlhberi.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgCPPT")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        jlhberi.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (pilihan == 1) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipppa = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        nmppa.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPPA.requestFocus();
                    }
                } else if (pilihan == 2) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipSerah.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        nmSerah.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        ChkSamaPPA.setSelected(false);
                        ChkSamaPPA.setEnabled(true);
                        BtnSerah.requestFocus();
                    }
                }  else if (pilihan == 3) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipTerima.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        nmTerima.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnTerima.requestFocus();
                    }
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
                if (akses.getform().equals("DlgCPPT")) {
                    if (pilihan == 1) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            kddpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            nmdpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDPJP.requestFocus();
                        }
                    } else if (pilihan == 2) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nipkonsulen = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                            nmKonsulen.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnKonsulen.requestFocus();
                        }
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnHasilPemeriksaanLab = new javax.swing.JMenuItem();
        MnHasilPemeriksaanRad = new javax.swing.JMenuItem();
        MnDataPemberianDiet = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnCeklisFarmasi = new javax.swing.JMenuItem();
        MnPemberianDiet = new javax.swing.JMenuItem();
        MnTindakan = new javax.swing.JMenuItem();
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
        WindowCPPT = new javax.swing.JDialog();
        internalFrame10 = new widget.InternalFrame();
        BtnCloseIn6 = new widget.Button();
        jLabel42 = new widget.Label();
        cmbJnsCppt = new widget.ComboBox();
        jLabel47 = new widget.Label();
        cmbSiftPetugas = new widget.ComboBox();
        BtnBaca = new widget.Button();
        tglA = new widget.Tanggal();
        jLabel49 = new widget.Label();
        tglB = new widget.Tanggal();
        cmbTanggal = new widget.ComboBox();
        jLabel50 = new widget.Label();
        WindowFarmasi = new javax.swing.JDialog();
        internalFrame11 = new widget.InternalFrame();
        BtnCloseIn7 = new widget.Button();
        BtnSimpanCeklis = new widget.Button();
        ChkDapatObat = new widget.CekBox();
        ChkObatSesuai = new widget.CekBox();
        ChkObatEfektif = new widget.CekBox();
        ChkObatAman = new widget.CekBox();
        ChkSemua = new widget.CekBox();
        WindowDiet = new javax.swing.JDialog();
        internalFrame12 = new widget.InternalFrame();
        internalFrame15 = new widget.InternalFrame();
        BtnPilihDiet = new widget.Button();
        BtnHapusPilihan = new widget.Button();
        BtnGanti = new widget.Button();
        Scroll5 = new widget.ScrollPane();
        tbDiet = new widget.Table();
        internalFrame14 = new widget.InternalFrame();
        internalFrame16 = new widget.InternalFrame();
        BtnSimpan1 = new widget.Button();
        BtnBatal1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        FormInput1 = new widget.PanelBiasa();
        cekWaktu = new widget.CekBox();
        cekDietPagi = new widget.CekBox();
        cekDietSiang = new widget.CekBox();
        cekDietSore = new widget.CekBox();
        cekKemasan = new widget.CekBox();
        jLabel28 = new widget.Label();
        jLabel29 = new widget.Label();
        DTPTgl = new widget.Tanggal();
        WindowDataDiet = new javax.swing.JDialog();
        internalFrame9 = new widget.InternalFrame();
        panelGlass7 = new widget.panelisi();
        nmdiet = new widget.TextBox();
        kddiet = new widget.TextBox();
        jLabel46 = new widget.Label();
        jnsMakanan = new widget.TextBox();
        jLabel48 = new widget.Label();
        kdberi = new widget.TextBox();
        jumlahBeri = new widget.TextBox();
        cmbSatuan = new widget.ComboBox();
        btnDiet = new widget.Button();
        btnJumlahBeri = new widget.Button();
        jLabel51 = new widget.Label();
        panelGlass11 = new widget.panelisi();
        BtnSimpan2 = new widget.Button();
        BtnEdit1 = new widget.Button();
        BtnCloseIn3 = new widget.Button();
        TKd = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        TabCPPT = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        kddpjp = new widget.TextBox();
        nmdpjp = new widget.TextBox();
        jLabel5 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRm = new widget.TextBox();
        BtnDPJP = new widget.Button();
        TPasien = new widget.TextBox();
        jLabel8 = new widget.Label();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        THasil = new widget.TextArea();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        TInstruksi = new widget.TextArea();
        jLabel14 = new widget.Label();
        cmbVerifikasi = new widget.ComboBox();
        BtnHasil = new widget.Button();
        BtnInstruksi = new widget.Button();
        tglCppt = new widget.Tanggal();
        ChkJam = new widget.CekBox();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel4 = new widget.Label();
        cmbPPA = new widget.ComboBox();
        jLabel16 = new widget.Label();
        nmppa = new widget.TextBox();
        BtnPPA = new widget.Button();
        jLabel17 = new widget.Label();
        cmbBagian = new widget.ComboBox();
        jLabel18 = new widget.Label();
        nmKonsulen = new widget.TextBox();
        BtnKonsulen = new widget.Button();
        jLabel22 = new widget.Label();
        nipSerah = new widget.TextBox();
        nmSerah = new widget.TextBox();
        BtnSerah = new widget.Button();
        jLabel23 = new widget.Label();
        nipTerima = new widget.TextBox();
        nmTerima = new widget.TextBox();
        BtnTerima = new widget.Button();
        ChkSamaPPA = new widget.CekBox();
        jLabel24 = new widget.Label();
        cmbSift = new widget.ComboBox();
        cmbSertim = new widget.ComboBox();
        jLabel9 = new widget.Label();
        jLabel20 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        BtnUlangiHasil = new widget.Button();
        BtnUlangiInstruksi = new widget.Button();
        jLabel26 = new widget.Label();
        jLabel27 = new widget.Label();
        BtnHapusDPJP = new widget.Button();
        BtnHapusKonsulen = new widget.Button();
        BtnHapusPPA = new widget.Button();
        BtnHapusSerah = new widget.Button();
        BtnHapusTerima = new widget.Button();
        internalFrame3 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbCPPT = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel15 = new widget.Label();
        cmbRawat = new widget.ComboBox();
        jLabel25 = new widget.Label();
        cmbSiftCppt = new widget.ComboBox();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHasilPemeriksaanLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanLab.setText("Hasil Pemeriksaan Lab.");
        MnHasilPemeriksaanLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanLab.setIconTextGap(5);
        MnHasilPemeriksaanLab.setName("MnHasilPemeriksaanLab"); // NOI18N
        MnHasilPemeriksaanLab.setPreferredSize(new java.awt.Dimension(190, 26));
        MnHasilPemeriksaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPemeriksaanLabActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHasilPemeriksaanLab);

        MnHasilPemeriksaanRad.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanRad.setText("Hasil Pemeriksaan Radiologi");
        MnHasilPemeriksaanRad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanRad.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanRad.setIconTextGap(5);
        MnHasilPemeriksaanRad.setName("MnHasilPemeriksaanRad"); // NOI18N
        MnHasilPemeriksaanRad.setPreferredSize(new java.awt.Dimension(190, 26));
        MnHasilPemeriksaanRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPemeriksaanRadActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHasilPemeriksaanRad);

        MnDataPemberianDiet.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataPemberianDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataPemberianDiet.setText("Data Pemberian Diet");
        MnDataPemberianDiet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataPemberianDiet.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataPemberianDiet.setIconTextGap(5);
        MnDataPemberianDiet.setName("MnDataPemberianDiet"); // NOI18N
        MnDataPemberianDiet.setPreferredSize(new java.awt.Dimension(190, 26));
        MnDataPemberianDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataPemberianDietActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDataPemberianDiet);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnCeklisFarmasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCeklisFarmasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCeklisFarmasi.setText("Ceklist Farmasi");
        MnCeklisFarmasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCeklisFarmasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCeklisFarmasi.setIconTextGap(5);
        MnCeklisFarmasi.setName("MnCeklisFarmasi"); // NOI18N
        MnCeklisFarmasi.setPreferredSize(new java.awt.Dimension(140, 26));
        MnCeklisFarmasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCeklisFarmasiActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnCeklisFarmasi);

        MnPemberianDiet.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPemberianDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPemberianDiet.setText("Pemberian Diet Gizi");
        MnPemberianDiet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPemberianDiet.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPemberianDiet.setIconTextGap(5);
        MnPemberianDiet.setName("MnPemberianDiet"); // NOI18N
        MnPemberianDiet.setPreferredSize(new java.awt.Dimension(140, 26));
        MnPemberianDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemberianDietActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnPemberianDiet);

        MnTindakan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTindakan.setText("Tindakan");
        MnTindakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTindakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTindakan.setIconTextGap(5);
        MnTindakan.setName("MnTindakan"); // NOI18N
        MnTindakan.setPreferredSize(new java.awt.Dimension(140, 26));
        MnTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTindakanActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnTindakan);

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

        WindowCPPT.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCPPT.setName("WindowCPPT"); // NOI18N
        WindowCPPT.setUndecorated(true);
        WindowCPPT.setResizable(false);

        internalFrame10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Membaca & Lihat Laporan CPPT ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame10.setLayout(null);

        BtnCloseIn6.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn6.setMnemonic('U');
        BtnCloseIn6.setText("Tutup");
        BtnCloseIn6.setToolTipText("Alt+U");
        BtnCloseIn6.setName("BtnCloseIn6"); // NOI18N
        BtnCloseIn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn6ActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnCloseIn6);
        BtnCloseIn6.setBounds(300, 95, 100, 30);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Jenis CPPT :");
        jLabel42.setName("jLabel42"); // NOI18N
        internalFrame10.add(jLabel42);
        jLabel42.setBounds(0, 32, 90, 23);

        cmbJnsCppt.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsCppt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rawat Inap", "IGD", "Semua" }));
        cmbJnsCppt.setName("cmbJnsCppt"); // NOI18N
        cmbJnsCppt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJnsCpptActionPerformed(evt);
            }
        });
        internalFrame10.add(cmbJnsCppt);
        cmbJnsCppt.setBounds(97, 32, 90, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Shift Petugas :");
        jLabel47.setName("jLabel47"); // NOI18N
        internalFrame10.add(jLabel47);
        jLabel47.setBounds(190, 32, 90, 23);

        cmbSiftPetugas.setForeground(new java.awt.Color(0, 0, 0));
        cmbSiftPetugas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "Semua" }));
        cmbSiftPetugas.setName("cmbSiftPetugas"); // NOI18N
        internalFrame10.add(cmbSiftPetugas);
        cmbSiftPetugas.setBounds(287, 32, 65, 23);

        BtnBaca.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnBaca.setMnemonic('U');
        BtnBaca.setText("Cetak CPPT");
        BtnBaca.setToolTipText("Alt+U");
        BtnBaca.setName("BtnBaca"); // NOI18N
        BtnBaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBacaActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnBaca);
        BtnBaca.setBounds(97, 95, 140, 30);

        tglA.setEditable(false);
        tglA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-10-2023" }));
        tglA.setDisplayFormat("dd-MM-yyyy");
        tglA.setName("tglA"); // NOI18N
        tglA.setOpaque(false);
        tglA.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame10.add(tglA);
        tglA.setBounds(195, 60, 90, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("s.d");
        jLabel49.setName("jLabel49"); // NOI18N
        internalFrame10.add(jLabel49);
        jLabel49.setBounds(288, 60, 30, 23);

        tglB.setEditable(false);
        tglB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-10-2023" }));
        tglB.setDisplayFormat("dd-MM-yyyy");
        tglB.setName("tglB"); // NOI18N
        tglB.setOpaque(false);
        tglB.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame10.add(tglB);
        tglB.setBounds(322, 60, 90, 23);

        cmbTanggal.setForeground(new java.awt.Color(0, 0, 0));
        cmbTanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Periode", "Per Tanggal" }));
        cmbTanggal.setName("cmbTanggal"); // NOI18N
        cmbTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTanggalActionPerformed(evt);
            }
        });
        internalFrame10.add(cmbTanggal);
        cmbTanggal.setBounds(97, 60, 90, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Tgl. CPPT :");
        jLabel50.setName("jLabel50"); // NOI18N
        internalFrame10.add(jLabel50);
        jLabel50.setBounds(0, 60, 90, 23);

        WindowCPPT.getContentPane().add(internalFrame10, java.awt.BorderLayout.CENTER);

        WindowFarmasi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowFarmasi.setName("WindowFarmasi"); // NOI18N
        WindowFarmasi.setUndecorated(true);
        WindowFarmasi.setResizable(false);

        internalFrame11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ceklist Pemantauan Penggunaan Obat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame11.setLayout(null);

        BtnCloseIn7.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn7.setMnemonic('U');
        BtnCloseIn7.setText("Tutup");
        BtnCloseIn7.setToolTipText("Alt+U");
        BtnCloseIn7.setName("BtnCloseIn7"); // NOI18N
        BtnCloseIn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn7ActionPerformed(evt);
            }
        });
        internalFrame11.add(BtnCloseIn7);
        BtnCloseIn7.setBounds(150, 120, 90, 30);

        BtnSimpanCeklis.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanCeklis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanCeklis.setMnemonic('U');
        BtnSimpanCeklis.setText("Simpan");
        BtnSimpanCeklis.setToolTipText("Alt+U");
        BtnSimpanCeklis.setName("BtnSimpanCeklis"); // NOI18N
        BtnSimpanCeklis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanCeklisActionPerformed(evt);
            }
        });
        internalFrame11.add(BtnSimpanCeklis);
        BtnSimpanCeklis.setBounds(40, 120, 90, 30);

        ChkDapatObat.setBackground(new java.awt.Color(255, 255, 250));
        ChkDapatObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDapatObat.setForeground(new java.awt.Color(0, 0, 0));
        ChkDapatObat.setText("Dapat Obat ");
        ChkDapatObat.setBorderPainted(true);
        ChkDapatObat.setBorderPaintedFlat(true);
        ChkDapatObat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkDapatObat.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkDapatObat.setName("ChkDapatObat"); // NOI18N
        ChkDapatObat.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame11.add(ChkDapatObat);
        ChkDapatObat.setBounds(20, 27, 100, 23);

        ChkObatSesuai.setBackground(new java.awt.Color(255, 255, 250));
        ChkObatSesuai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObatSesuai.setForeground(new java.awt.Color(0, 0, 0));
        ChkObatSesuai.setText("Obat Sesuai");
        ChkObatSesuai.setBorderPainted(true);
        ChkObatSesuai.setBorderPaintedFlat(true);
        ChkObatSesuai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkObatSesuai.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkObatSesuai.setName("ChkObatSesuai"); // NOI18N
        ChkObatSesuai.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame11.add(ChkObatSesuai);
        ChkObatSesuai.setBounds(20, 55, 100, 23);

        ChkObatEfektif.setBackground(new java.awt.Color(255, 255, 250));
        ChkObatEfektif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObatEfektif.setForeground(new java.awt.Color(0, 0, 0));
        ChkObatEfektif.setText("Obat Efektif");
        ChkObatEfektif.setBorderPainted(true);
        ChkObatEfektif.setBorderPaintedFlat(true);
        ChkObatEfektif.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkObatEfektif.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkObatEfektif.setName("ChkObatEfektif"); // NOI18N
        ChkObatEfektif.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame11.add(ChkObatEfektif);
        ChkObatEfektif.setBounds(150, 27, 100, 23);

        ChkObatAman.setBackground(new java.awt.Color(255, 255, 250));
        ChkObatAman.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObatAman.setForeground(new java.awt.Color(0, 0, 0));
        ChkObatAman.setText("Obat Aman");
        ChkObatAman.setBorderPainted(true);
        ChkObatAman.setBorderPaintedFlat(true);
        ChkObatAman.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkObatAman.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkObatAman.setName("ChkObatAman"); // NOI18N
        ChkObatAman.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame11.add(ChkObatAman);
        ChkObatAman.setBounds(150, 55, 100, 23);

        ChkSemua.setBackground(new java.awt.Color(255, 255, 250));
        ChkSemua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSemua.setForeground(new java.awt.Color(0, 0, 0));
        ChkSemua.setText("Conteng Semua");
        ChkSemua.setBorderPainted(true);
        ChkSemua.setBorderPaintedFlat(true);
        ChkSemua.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkSemua.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkSemua.setName("ChkSemua"); // NOI18N
        ChkSemua.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSemuaActionPerformed(evt);
            }
        });
        internalFrame11.add(ChkSemua);
        ChkSemua.setBounds(20, 83, 100, 23);

        WindowFarmasi.getContentPane().add(internalFrame11, java.awt.BorderLayout.CENTER);

        WindowDiet.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDiet.setName("WindowDiet"); // NOI18N
        WindowDiet.setUndecorated(true);
        WindowDiet.setResizable(false);

        internalFrame12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pemberian Diet Harian Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame12.setName("internalFrame12"); // NOI18N
        internalFrame12.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame12.setLayout(new java.awt.BorderLayout());

        internalFrame15.setName("internalFrame15"); // NOI18N
        internalFrame15.setPreferredSize(new java.awt.Dimension(55, 45));
        internalFrame15.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        BtnPilihDiet.setForeground(new java.awt.Color(0, 0, 0));
        BtnPilihDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPilihDiet.setMnemonic('P');
        BtnPilihDiet.setText("Pilihan Diet");
        BtnPilihDiet.setToolTipText("Alt+P");
        BtnPilihDiet.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnPilihDiet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPilihDiet.setName("BtnPilihDiet"); // NOI18N
        BtnPilihDiet.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnPilihDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPilihDietActionPerformed(evt);
            }
        });
        internalFrame15.add(BtnPilihDiet);

        BtnHapusPilihan.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusPilihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusPilihan.setMnemonic('H');
        BtnHapusPilihan.setText("Hapus Dipilih");
        BtnHapusPilihan.setToolTipText("Alt+H");
        BtnHapusPilihan.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnHapusPilihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnHapusPilihan.setName("BtnHapusPilihan"); // NOI18N
        BtnHapusPilihan.setPreferredSize(new java.awt.Dimension(115, 30));
        BtnHapusPilihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusPilihanActionPerformed(evt);
            }
        });
        internalFrame15.add(BtnHapusPilihan);

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setMnemonic('G');
        BtnGanti.setText("Ganti Dipilih");
        BtnGanti.setToolTipText("Alt+G");
        BtnGanti.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnGanti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.setPreferredSize(new java.awt.Dimension(118, 30));
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        internalFrame15.add(BtnGanti);

        internalFrame12.add(internalFrame15, java.awt.BorderLayout.PAGE_START);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Diet yang dipilih/ditentukan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbDiet.setAutoCreateRowSorter(true);
        tbDiet.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/diperbaiki");
        tbDiet.setName("tbDiet"); // NOI18N
        tbDiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDietMouseClicked(evt);
            }
        });
        tbDiet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDietKeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbDiet);

        internalFrame12.add(Scroll5, java.awt.BorderLayout.CENTER);

        internalFrame14.setName("internalFrame14"); // NOI18N
        internalFrame14.setPreferredSize(new java.awt.Dimension(55, 120));
        internalFrame14.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame14.setLayout(new java.awt.BorderLayout());

        internalFrame16.setName("internalFrame16"); // NOI18N
        internalFrame16.setPreferredSize(new java.awt.Dimension(55, 45));
        internalFrame16.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        BtnSimpan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan1KeyPressed(evt);
            }
        });
        internalFrame16.add(BtnSimpan1);

        BtnBatal1.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal1.setMnemonic('B');
        BtnBatal1.setText("Baru");
        BtnBatal1.setToolTipText("Alt+B");
        BtnBatal1.setName("BtnBatal1"); // NOI18N
        BtnBatal1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatal1ActionPerformed(evt);
            }
        });
        BtnBatal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatal1KeyPressed(evt);
            }
        });
        internalFrame16.add(BtnBatal1);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        BtnKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar1KeyPressed(evt);
            }
        });
        internalFrame16.add(BtnKeluar1);

        internalFrame14.add(internalFrame16, java.awt.BorderLayout.PAGE_END);

        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(160, 70));
        FormInput1.setLayout(null);

        cekWaktu.setBackground(new java.awt.Color(242, 242, 242));
        cekWaktu.setBorder(null);
        cekWaktu.setForeground(new java.awt.Color(0, 0, 0));
        cekWaktu.setText("Semua Waktu Diet");
        cekWaktu.setBorderPainted(true);
        cekWaktu.setBorderPaintedFlat(true);
        cekWaktu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekWaktu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekWaktu.setName("cekWaktu"); // NOI18N
        cekWaktu.setOpaque(false);
        cekWaktu.setPreferredSize(new java.awt.Dimension(100, 23));
        cekWaktu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cekWaktuItemStateChanged(evt);
            }
        });
        cekWaktu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cekWaktuMouseClicked(evt);
            }
        });
        cekWaktu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekWaktuActionPerformed(evt);
            }
        });
        FormInput1.add(cekWaktu);
        cekWaktu.setBounds(325, 12, 125, 23);

        cekDietPagi.setBackground(new java.awt.Color(242, 242, 242));
        cekDietPagi.setBorder(null);
        cekDietPagi.setForeground(new java.awt.Color(0, 0, 0));
        cekDietPagi.setText("Diet Pagi");
        cekDietPagi.setBorderPainted(true);
        cekDietPagi.setBorderPaintedFlat(true);
        cekDietPagi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekDietPagi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekDietPagi.setName("cekDietPagi"); // NOI18N
        cekDietPagi.setOpaque(false);
        cekDietPagi.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput1.add(cekDietPagi);
        cekDietPagi.setBounds(85, 12, 70, 23);

        cekDietSiang.setBackground(new java.awt.Color(242, 242, 242));
        cekDietSiang.setBorder(null);
        cekDietSiang.setForeground(new java.awt.Color(0, 0, 0));
        cekDietSiang.setText("Diet Siang");
        cekDietSiang.setBorderPainted(true);
        cekDietSiang.setBorderPaintedFlat(true);
        cekDietSiang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekDietSiang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekDietSiang.setName("cekDietSiang"); // NOI18N
        cekDietSiang.setOpaque(false);
        cekDietSiang.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput1.add(cekDietSiang);
        cekDietSiang.setBounds(165, 12, 70, 23);

        cekDietSore.setBackground(new java.awt.Color(242, 242, 242));
        cekDietSore.setBorder(null);
        cekDietSore.setForeground(new java.awt.Color(0, 0, 0));
        cekDietSore.setText("Diet Sore");
        cekDietSore.setBorderPainted(true);
        cekDietSore.setBorderPaintedFlat(true);
        cekDietSore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekDietSore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekDietSore.setName("cekDietSore"); // NOI18N
        cekDietSore.setOpaque(false);
        cekDietSore.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput1.add(cekDietSore);
        cekDietSore.setBounds(245, 12, 70, 23);

        cekKemasan.setBackground(new java.awt.Color(242, 242, 242));
        cekKemasan.setBorder(null);
        cekKemasan.setForeground(new java.awt.Color(0, 0, 0));
        cekKemasan.setText("Disajikan Seperti Biasa");
        cekKemasan.setBorderPainted(true);
        cekKemasan.setBorderPaintedFlat(true);
        cekKemasan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekKemasan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekKemasan.setName("cekKemasan"); // NOI18N
        cekKemasan.setOpaque(false);
        cekKemasan.setPreferredSize(new java.awt.Dimension(100, 23));
        cekKemasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekKemasanActionPerformed(evt);
            }
        });
        FormInput1.add(cekKemasan);
        cekKemasan.setBounds(185, 40, 250, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Waktu Diet :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput1.add(jLabel28);
        jLabel28.setBounds(0, 12, 80, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Tanggal :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput1.add(jLabel29);
        jLabel29.setBounds(0, 40, 80, 23);

        DTPTgl.setEditable(false);
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-10-2023" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput1.add(DTPTgl);
        DTPTgl.setBounds(85, 40, 90, 23);

        internalFrame14.add(FormInput1, java.awt.BorderLayout.PAGE_START);

        internalFrame12.add(internalFrame14, java.awt.BorderLayout.PAGE_END);

        WindowDiet.getContentPane().add(internalFrame12, java.awt.BorderLayout.CENTER);

        WindowDataDiet.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDataDiet.setName("WindowDataDiet"); // NOI18N
        WindowDataDiet.setUndecorated(true);
        WindowDataDiet.setResizable(false);

        internalFrame9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pilihan Diet ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame9.setLayout(new java.awt.BorderLayout());

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 100));
        panelGlass7.setLayout(null);

        nmdiet.setEditable(false);
        nmdiet.setForeground(new java.awt.Color(0, 0, 0));
        nmdiet.setName("nmdiet"); // NOI18N
        panelGlass7.add(nmdiet);
        nmdiet.setBounds(197, 10, 200, 23);

        kddiet.setEditable(false);
        kddiet.setForeground(new java.awt.Color(0, 0, 0));
        kddiet.setName("kddiet"); // NOI18N
        panelGlass7.add(kddiet);
        kddiet.setBounds(113, 10, 80, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Jenis Makanan : ");
        jLabel46.setName("jLabel46"); // NOI18N
        panelGlass7.add(jLabel46);
        jLabel46.setBounds(0, 37, 110, 23);

        jnsMakanan.setEditable(false);
        jnsMakanan.setForeground(new java.awt.Color(0, 0, 0));
        jnsMakanan.setName("jnsMakanan"); // NOI18N
        panelGlass7.add(jnsMakanan);
        jnsMakanan.setBounds(113, 37, 283, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Jumlah Pemberian : ");
        jLabel48.setName("jLabel48"); // NOI18N
        panelGlass7.add(jLabel48);
        jLabel48.setBounds(0, 64, 110, 23);

        kdberi.setEditable(false);
        kdberi.setForeground(new java.awt.Color(0, 0, 0));
        kdberi.setName("kdberi"); // NOI18N
        panelGlass7.add(kdberi);
        kdberi.setBounds(113, 64, 80, 23);

        jumlahBeri.setEditable(false);
        jumlahBeri.setForeground(new java.awt.Color(0, 0, 0));
        jumlahBeri.setName("jumlahBeri"); // NOI18N
        panelGlass7.add(jumlahBeri);
        jumlahBeri.setBounds(197, 64, 130, 23);

        cmbSatuan.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbSatuan.setName("cmbSatuan"); // NOI18N
        cmbSatuan.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass7.add(cmbSatuan);
        cmbSatuan.setBounds(332, 64, 65, 23);

        btnDiet.setForeground(new java.awt.Color(0, 0, 0));
        btnDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiet.setMnemonic('X');
        btnDiet.setToolTipText("Alt+X");
        btnDiet.setName("btnDiet"); // NOI18N
        btnDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDietActionPerformed(evt);
            }
        });
        panelGlass7.add(btnDiet);
        btnDiet.setBounds(400, 10, 28, 23);

        btnJumlahBeri.setForeground(new java.awt.Color(0, 0, 0));
        btnJumlahBeri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnJumlahBeri.setMnemonic('X');
        btnJumlahBeri.setToolTipText("Alt+X");
        btnJumlahBeri.setName("btnJumlahBeri"); // NOI18N
        btnJumlahBeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJumlahBeriActionPerformed(evt);
            }
        });
        panelGlass7.add(btnJumlahBeri);
        btnJumlahBeri.setBounds(400, 64, 28, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Nama Diet : ");
        jLabel51.setName("jLabel51"); // NOI18N
        panelGlass7.add(jLabel51);
        jLabel51.setBounds(0, 10, 110, 23);

        internalFrame9.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 150));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 5));

        BtnSimpan2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan2.setMnemonic('S');
        BtnSimpan2.setText("Simpan");
        BtnSimpan2.setToolTipText("Alt+S");
        BtnSimpan2.setName("BtnSimpan2"); // NOI18N
        BtnSimpan2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan2ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnSimpan2);

        BtnEdit1.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit1.setMnemonic('G');
        BtnEdit1.setText("Ganti");
        BtnEdit1.setToolTipText("Alt+G");
        BtnEdit1.setName("BtnEdit1"); // NOI18N
        BtnEdit1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit1ActionPerformed(evt);
            }
        });
        BtnEdit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEdit1KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnEdit1);

        BtnCloseIn3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn3.setMnemonic('U');
        BtnCloseIn3.setText("Tutup");
        BtnCloseIn3.setToolTipText("Alt+U");
        BtnCloseIn3.setName("BtnCloseIn3"); // NOI18N
        BtnCloseIn3.setPreferredSize(new java.awt.Dimension(90, 30));
        BtnCloseIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn3ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnCloseIn3);

        internalFrame9.add(panelGlass11, java.awt.BorderLayout.CENTER);

        WindowDataDiet.getContentPane().add(internalFrame9, java.awt.BorderLayout.CENTER);

        TKd.setEditable(false);
        TKd.setForeground(new java.awt.Color(0, 0, 0));
        TKd.setName("TKd"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Catatan Perkembangan Pasien Terintegrasi (CPPT) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabCPPT.setBackground(new java.awt.Color(254, 255, 254));
        TabCPPT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabCPPT.setName("TabCPPT"); // NOI18N
        TabCPPT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabCPPTMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 760));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama DPJP Utama :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 577, 180, 23);

        kddpjp.setEditable(false);
        kddpjp.setForeground(new java.awt.Color(0, 0, 0));
        kddpjp.setName("kddpjp"); // NOI18N
        FormInput.add(kddpjp);
        kddpjp.setBounds(186, 577, 130, 23);

        nmdpjp.setEditable(false);
        nmdpjp.setForeground(new java.awt.Color(0, 0, 0));
        nmdpjp.setName("nmdpjp"); // NOI18N
        FormInput.add(nmdpjp);
        nmdpjp.setBounds(320, 577, 460, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 10, 180, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(185, 10, 122, 23);

        TNoRm.setEditable(false);
        TNoRm.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm.setName("TNoRm"); // NOI18N
        FormInput.add(TNoRm);
        TNoRm.setBounds(312, 10, 70, 23);

        BtnDPJP.setForeground(new java.awt.Color(0, 0, 0));
        BtnDPJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDPJP.setMnemonic('1');
        BtnDPJP.setToolTipText("Alt+1");
        BtnDPJP.setName("BtnDPJP"); // NOI18N
        BtnDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDPJPActionPerformed(evt);
            }
        });
        FormInput.add(BtnDPJP);
        BtnDPJP.setBounds(784, 577, 28, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(386, 10, 430, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. CPPT :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 38, 180, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Hasil Pemeriksaan, Analisa, :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 66, 180, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Rencana, Penatalaksanaan ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 80, 180, 23);

        scrollPane2.setName("scrollPane2"); // NOI18N

        THasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        THasil.setColumns(20);
        THasil.setRows(5);
        THasil.setComponentPopupMenu(jPopupMenu2);
        THasil.setName("THasil"); // NOI18N
        THasil.setPreferredSize(new java.awt.Dimension(162, 4000));
        THasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THasilKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(THasil);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(185, 66, 630, 319);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Instruksi Tenaga Kesehatan :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 390, 180, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Termasuk Pasca Bedah  ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 404, 180, 23);

        scrollPane3.setName("scrollPane3"); // NOI18N

        TInstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TInstruksi.setColumns(20);
        TInstruksi.setRows(5);
        TInstruksi.setComponentPopupMenu(jPopupMenu2);
        TInstruksi.setName("TInstruksi"); // NOI18N
        TInstruksi.setPreferredSize(new java.awt.Dimension(162, 2000));
        TInstruksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TInstruksiKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(TInstruksi);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(185, 390, 630, 182);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Verifikasi :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(505, 661, 60, 23);

        cmbVerifikasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbVerifikasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Belum", "Sudah" }));
        cmbVerifikasi.setName("cmbVerifikasi"); // NOI18N
        FormInput.add(cmbVerifikasi);
        cmbVerifikasi.setBounds(570, 661, 65, 23);

        BtnHasil.setForeground(new java.awt.Color(0, 0, 0));
        BtnHasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnHasil.setMnemonic('2');
        BtnHasil.setText("Template");
        BtnHasil.setToolTipText("Alt+2");
        BtnHasil.setName("BtnHasil"); // NOI18N
        BtnHasil.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHasilActionPerformed(evt);
            }
        });
        FormInput.add(BtnHasil);
        BtnHasil.setBounds(825, 66, 100, 23);

        BtnInstruksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnInstruksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnInstruksi.setMnemonic('2');
        BtnInstruksi.setText("Template");
        BtnInstruksi.setToolTipText("Alt+2");
        BtnInstruksi.setName("BtnInstruksi"); // NOI18N
        BtnInstruksi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnInstruksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInstruksiActionPerformed(evt);
            }
        });
        FormInput.add(BtnInstruksi);
        BtnInstruksi.setBounds(825, 390, 100, 23);

        tglCppt.setEditable(false);
        tglCppt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-10-2023" }));
        tglCppt.setDisplayFormat("dd-MM-yyyy");
        tglCppt.setName("tglCppt"); // NOI18N
        tglCppt.setOpaque(false);
        tglCppt.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(tglCppt);
        tglCppt.setBounds(185, 38, 90, 23);

        ChkJam.setBackground(new java.awt.Color(255, 255, 250));
        ChkJam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkJam.setForeground(new java.awt.Color(0, 0, 0));
        ChkJam.setText("Jam CPPT :");
        ChkJam.setBorderPainted(true);
        ChkJam.setBorderPaintedFlat(true);
        ChkJam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkJam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkJam.setName("ChkJam"); // NOI18N
        ChkJam.setOpaque(false);
        ChkJam.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkJam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJamActionPerformed(evt);
            }
        });
        FormInput.add(ChkJam);
        ChkJam.setBounds(275, 38, 90, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(371, 38, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(422, 38, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(474, 38, 45, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Jenis PPA :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 633, 180, 23);

        cmbPPA.setForeground(new java.awt.Color(0, 0, 0));
        cmbPPA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Perawat", "Bidan", "Apoteker", "Nutrisionis", "Fisioterapis", "Dokter IRNA" }));
        cmbPPA.setName("cmbPPA"); // NOI18N
        cmbPPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPPAActionPerformed(evt);
            }
        });
        FormInput.add(cmbPPA);
        cmbPPA.setBounds(186, 633, 95, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Nama PPA :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(280, 633, 70, 23);

        nmppa.setEditable(false);
        nmppa.setForeground(new java.awt.Color(0, 0, 0));
        nmppa.setName("nmppa"); // NOI18N
        FormInput.add(nmppa);
        nmppa.setBounds(355, 633, 425, 23);

        BtnPPA.setForeground(new java.awt.Color(0, 0, 0));
        BtnPPA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPPA.setMnemonic('1');
        BtnPPA.setToolTipText("Alt+1");
        BtnPPA.setName("BtnPPA"); // NOI18N
        BtnPPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPPAActionPerformed(evt);
            }
        });
        FormInput.add(BtnPPA);
        BtnPPA.setBounds(784, 633, 28, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Jenis Bagian :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 605, 180, 23);

        cmbBagian.setForeground(new java.awt.Color(0, 0, 0));
        cmbBagian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Dokter IGD", "DPJP (K)", "PPA", "DPJP" }));
        cmbBagian.setName("cmbBagian"); // NOI18N
        cmbBagian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBagianActionPerformed(evt);
            }
        });
        FormInput.add(cmbBagian);
        cmbBagian.setBounds(186, 605, 85, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("DPJP Konsulen :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(270, 605, 90, 23);

        nmKonsulen.setEditable(false);
        nmKonsulen.setForeground(new java.awt.Color(0, 0, 0));
        nmKonsulen.setName("nmKonsulen"); // NOI18N
        FormInput.add(nmKonsulen);
        nmKonsulen.setBounds(365, 605, 415, 23);

        BtnKonsulen.setForeground(new java.awt.Color(0, 0, 0));
        BtnKonsulen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKonsulen.setMnemonic('1');
        BtnKonsulen.setToolTipText("Alt+1");
        BtnKonsulen.setName("BtnKonsulen"); // NOI18N
        BtnKonsulen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKonsulenActionPerformed(evt);
            }
        });
        FormInput.add(BtnKonsulen);
        BtnKonsulen.setBounds(784, 605, 28, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Petugas Yang Menyerahkan :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 689, 180, 23);

        nipSerah.setEditable(false);
        nipSerah.setForeground(new java.awt.Color(0, 0, 0));
        nipSerah.setName("nipSerah"); // NOI18N
        FormInput.add(nipSerah);
        nipSerah.setBounds(186, 689, 130, 23);

        nmSerah.setEditable(false);
        nmSerah.setForeground(new java.awt.Color(0, 0, 0));
        nmSerah.setName("nmSerah"); // NOI18N
        FormInput.add(nmSerah);
        nmSerah.setBounds(320, 689, 460, 23);

        BtnSerah.setForeground(new java.awt.Color(0, 0, 0));
        BtnSerah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSerah.setMnemonic('1');
        BtnSerah.setToolTipText("Alt+1");
        BtnSerah.setName("BtnSerah"); // NOI18N
        BtnSerah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSerahActionPerformed(evt);
            }
        });
        FormInput.add(BtnSerah);
        BtnSerah.setBounds(784, 689, 28, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Petugas Yang Menerima :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 717, 180, 23);

        nipTerima.setEditable(false);
        nipTerima.setForeground(new java.awt.Color(0, 0, 0));
        nipTerima.setName("nipTerima"); // NOI18N
        FormInput.add(nipTerima);
        nipTerima.setBounds(186, 717, 130, 23);

        nmTerima.setEditable(false);
        nmTerima.setForeground(new java.awt.Color(0, 0, 0));
        nmTerima.setName("nmTerima"); // NOI18N
        FormInput.add(nmTerima);
        nmTerima.setBounds(320, 717, 460, 23);

        BtnTerima.setForeground(new java.awt.Color(0, 0, 0));
        BtnTerima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnTerima.setMnemonic('1');
        BtnTerima.setToolTipText("Alt+1");
        BtnTerima.setName("BtnTerima"); // NOI18N
        BtnTerima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTerimaActionPerformed(evt);
            }
        });
        FormInput.add(BtnTerima);
        BtnTerima.setBounds(784, 717, 28, 23);

        ChkSamaPPA.setBackground(new java.awt.Color(255, 255, 250));
        ChkSamaPPA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSamaPPA.setForeground(new java.awt.Color(0, 0, 0));
        ChkSamaPPA.setText("Sama Dengan Petugas PPA");
        ChkSamaPPA.setBorderPainted(true);
        ChkSamaPPA.setBorderPaintedFlat(true);
        ChkSamaPPA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSamaPPA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSamaPPA.setName("ChkSamaPPA"); // NOI18N
        ChkSamaPPA.setOpaque(false);
        ChkSamaPPA.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkSamaPPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSamaPPAActionPerformed(evt);
            }
        });
        FormInput.add(ChkSamaPPA);
        ChkSamaPPA.setBounds(640, 661, 160, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("CPPT Shift :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(525, 38, 80, 23);

        cmbSift.setForeground(new java.awt.Color(0, 0, 0));
        cmbSift.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3" }));
        cmbSift.setName("cmbSift"); // NOI18N
        FormInput.add(cmbSift);
        cmbSift.setBounds(612, 38, 40, 23);

        cmbSertim.setForeground(new java.awt.Color(0, 0, 0));
        cmbSertim.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbSertim.setName("cmbSertim"); // NOI18N
        cmbSertim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSertimActionPerformed(evt);
            }
        });
        FormInput.add(cmbSertim);
        cmbSertim.setBounds(186, 661, 60, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Serah Terima CPPT :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 661, 180, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jam Serah Terima :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(250, 661, 100, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(355, 661, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(406, 661, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(458, 661, 45, 23);

        BtnUlangiHasil.setForeground(new java.awt.Color(0, 0, 0));
        BtnUlangiHasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnUlangiHasil.setMnemonic('2');
        BtnUlangiHasil.setText("Ulangi");
        BtnUlangiHasil.setToolTipText("Alt+2");
        BtnUlangiHasil.setName("BtnUlangiHasil"); // NOI18N
        BtnUlangiHasil.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnUlangiHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUlangiHasilActionPerformed(evt);
            }
        });
        FormInput.add(BtnUlangiHasil);
        BtnUlangiHasil.setBounds(825, 96, 100, 23);

        BtnUlangiInstruksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnUlangiInstruksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnUlangiInstruksi.setMnemonic('2');
        BtnUlangiInstruksi.setText("Ulangi");
        BtnUlangiInstruksi.setToolTipText("Alt+2");
        BtnUlangiInstruksi.setName("BtnUlangiInstruksi"); // NOI18N
        BtnUlangiInstruksi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnUlangiInstruksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUlangiInstruksiActionPerformed(evt);
            }
        });
        FormInput.add(BtnUlangiInstruksi);
        BtnUlangiInstruksi.setBounds(825, 420, 100, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Pasien ");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(0, 94, 180, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("/Prosedur  ");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(0, 418, 180, 23);

        BtnHapusDPJP.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusDPJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapusDPJP.setToolTipText("Hapus DPJP Utama");
        BtnHapusDPJP.setName("BtnHapusDPJP"); // NOI18N
        BtnHapusDPJP.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHapusDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusDPJPActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusDPJP);
        BtnHapusDPJP.setBounds(825, 577, 28, 23);

        BtnHapusKonsulen.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusKonsulen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapusKonsulen.setToolTipText("Hapus DPJP Konsulen");
        BtnHapusKonsulen.setName("BtnHapusKonsulen"); // NOI18N
        BtnHapusKonsulen.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHapusKonsulen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusKonsulenActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusKonsulen);
        BtnHapusKonsulen.setBounds(825, 605, 28, 23);

        BtnHapusPPA.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusPPA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapusPPA.setToolTipText("Hapus Nama PPA");
        BtnHapusPPA.setName("BtnHapusPPA"); // NOI18N
        BtnHapusPPA.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHapusPPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusPPAActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusPPA);
        BtnHapusPPA.setBounds(825, 633, 28, 23);

        BtnHapusSerah.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusSerah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapusSerah.setToolTipText("Hapus Nama Petugas Yang Menyerahkan");
        BtnHapusSerah.setName("BtnHapusSerah"); // NOI18N
        BtnHapusSerah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHapusSerah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusSerahActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusSerah);
        BtnHapusSerah.setBounds(825, 689, 28, 23);

        BtnHapusTerima.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusTerima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapusTerima.setToolTipText("Hapus Nama Petugas Yang Menerima");
        BtnHapusTerima.setName("BtnHapusTerima"); // NOI18N
        BtnHapusTerima.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHapusTerima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusTerimaActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusTerima);
        BtnHapusTerima.setBounds(825, 717, 28, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabCPPT.addTab("Input Data CPPT", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 200));

        tbCPPT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbCPPT.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbCPPT.setComponentPopupMenu(jPopupMenu1);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbCPPTKeyReleased(evt);
            }
        });
        Scroll2.setViewportView(tbCPPT);

        internalFrame3.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. CPPT :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-10-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-10-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari2);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Jns. Rawat :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel15);

        cmbRawat.setForeground(new java.awt.Color(0, 0, 0));
        cmbRawat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "R. Jalan", "R. Inap", "Semua" }));
        cmbRawat.setName("cmbRawat"); // NOI18N
        cmbRawat.setPreferredSize(new java.awt.Dimension(77, 23));
        cmbRawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRawatActionPerformed(evt);
            }
        });
        panelGlass10.add(cmbRawat);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Shift :");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(45, 23));
        panelGlass10.add(jLabel25);

        cmbSiftCppt.setForeground(new java.awt.Color(0, 0, 0));
        cmbSiftCppt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "Semua" }));
        cmbSiftCppt.setName("cmbSiftCppt"); // NOI18N
        cmbSiftCppt.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(cmbSiftCppt);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

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
        panelGlass10.add(BtnCari);

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
        panelGlass10.add(BtnAll);

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

        internalFrame3.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        TabCPPT.addTab("Data CPPT", internalFrame3);

        internalFrame1.add(TabCPPT, java.awt.BorderLayout.CENTER);
        TabCPPT.getAccessibleContext().setAccessibleName("Input Data CPPT");

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
        panelGlass8.add(BtnPrint);

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
            if (kddpjp.getText().equals("")) {
                kddpjp.setText("-");
                nmdpjp.setText("-");
            } else {
                kddpjp.setText(kddpjp.getText());
                nmdpjp.setText(nmdpjp.getText());
            }
            
            if (ChkJam.isSelected() == true) {
                cekjam = "ya";
            } else {
                cekjam = "tidak";
            }
            
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
                statusOK = "Ralan";
            } else if (status.equals("ranap")) {
                statusOK = "Ranap";
            }
            
            if (cmbPPA.getSelectedIndex() == 3 || cmbPPA.getSelectedIndex() == 4 || cmbPPA.getSelectedIndex() == 5) {
                siftppa = "1";
            } else {
                siftppa = cmbSift.getSelectedItem().toString();
            }

            Sequel.menyimpan("cppt", "'" + TNoRw.getText() + "',"
                    + "'" + Valid.SetTgl(tglCppt.getSelectedItem() + "") + "',"
                    + "'-','" + THasil.getText() + "','" + TInstruksi.getText() + "',"
                    + "'" + cmbVerifikasi.getSelectedItem().toString() + "','" + kddpjp.getText() + "','" + statusOK + "',"
                    + "'" + Sequel.cariIsi("select now()") + "','" + cekjam + "',"
                    + "'" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                    + "'" + cmbPPA.getSelectedItem().toString() + "','" + nipppa + "','" + cmbBagian.getSelectedItem().toString() + "',"
                    + "'" + cmbSertim.getSelectedItem().toString() + "','" + nipkonsulen + "','" + nipSerah.getText() + "',"
                    + "'" + nipTerima.getText() + "','" + siftppa + "',"
                    + "'" + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "'", "CPPT Pasien");
            
            TCari.setText(TNoRw.getText());
            cmbSiftCppt.setSelectedItem(cmbSift.getSelectedItem());
            tampil();
            emptTeks();
            TabCPPT.setSelectedIndex(1);
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbCPPT.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from cppt where waktu_simpan=?", 1, new String[]{
                    tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 13).toString()
                }) == true) {
                    tampil();
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (kddpjp.getText().equals("")) {
                kddpjp.setText("-");
                nmdpjp.setText("-");
            } else {
                kddpjp.setText(kddpjp.getText());
                nmdpjp.setText(nmdpjp.getText());
            }
            
            if (ChkJam.isSelected() == true) {
                cekjam = "ya";
            } else {
                cekjam = "tidak";
            }
            
            if (tbCPPT.getSelectedRow() > -1) {
                Sequel.mengedit("cppt", "waktu_simpan=?", "tgl_cppt=?, hasil_pemeriksaan=?, "
                        + "instruksi_nakes=?, verifikasi=?, nip_dpjp=?, cek_jam=?, jam_cppt=?, jenis_ppa=?, nip_ppa=?, jenis_bagian=?, "
                        + "serah_terima_cppt=?, nip_konsulen=?, nip_petugas_serah=?, nip_petugas_terima=?, cppt_shift=?, jam_serah_terima=?", 17, new String[]{
                            Valid.SetTgl(tglCppt.getSelectedItem() + ""), THasil.getText(), TInstruksi.getText(),
                            cmbVerifikasi.getSelectedItem().toString(), kddpjp.getText(), cekjam,
                            cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            cmbPPA.getSelectedItem().toString(), nipppa, cmbBagian.getSelectedItem().toString(), 
                            cmbSertim.getSelectedItem().toString(), nipkonsulen, nipSerah.getText(), nipTerima.getText(), 
                            cmbSift.getSelectedItem().toString(), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                            tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 13).toString()
                        });
                
                TCari.setText(TNoRw.getText());
                cmbSiftCppt.setSelectedItem(cmbSift.getSelectedItem());
                tampil();
                emptTeks();
                TabCPPT.setSelectedIndex(1);
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowTemplate.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbCPPT.requestFocus();
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
        emptTeks();
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, kddpjp);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbCPPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCPPTMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbCPPTMouseClicked

    private void tbCPPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCPPTKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCari.setText("");
                TCari.requestFocus();
            }           
        }
}//GEN-LAST:event_tbCPPTKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        MnCeklisFarmasi.setEnabled(false);
        MnPemberianDiet.setEnabled(false);
        MnTindakan.setEnabled(false);
        Sequel.cariIsiComboDB("select satuan from diet_master_pemberian group by satuan", cmbSatuan);
    }//GEN-LAST:event_formWindowOpened

    private void tbCPPTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCPPTKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }          
        }
    }//GEN-LAST:event_tbCPPTKeyReleased

    private void BtnDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDPJPActionPerformed
        pilihan = 1;
        akses.setform("DlgCPPT");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDPJPActionPerformed

    private void THasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THasilKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TInstruksi.requestFocus();
        }
    }//GEN-LAST:event_THasilKeyPressed

    private void TInstruksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TInstruksiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnDPJP.requestFocus();
        }
    }//GEN-LAST:event_TInstruksiKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya sesuai tgl. cppt...!!!!");
        } else if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + TNoRw.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data cppt pasien tersebut belum disimpan...!!!!");
        } else {
            if (cmbRawat.getSelectedIndex() == 0) {
                cetakCPPTralan();
            } else {
                WindowCPPT.setSize(449, 147);
                WindowCPPT.setLocationRelativeTo(internalFrame1);
                WindowCPPT.setAlwaysOnTop(false);               
                WindowCPPT.setVisible(true);

                cmbJnsCppt.setSelectedIndex(0);
                cmbTanggal.setSelectedIndex(0);
                cmbSiftPetugas.setSelectedIndex(0);
                tglA.setDate(new Date());
                tglB.setDate(new Date());

                cmbTanggal.setEnabled(true);
                tglA.setEnabled(false);
                tglB.setEnabled(false);
                cmbSiftPetugas.setEnabled(false);
                cmbJnsCppt.requestFocus();
            }

            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHasilActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 1;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Hasil Pemeriksaan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnHasilActionPerformed

    private void BtnInstruksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInstruksiActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 2;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Instruksi Nakes ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnInstruksiActionPerformed

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

    private void ChkJamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJamActionPerformed
        if (ChkJam.isSelected() == true) {
            cmbJam.setEnabled(true);
            cmbMnt.setEnabled(true);
            cmbDtk.setEnabled(true);
        } else {
            cmbJam.setSelectedIndex(0);
            cmbMnt.setSelectedIndex(0);
            cmbDtk.setSelectedIndex(0);

            cmbJam.setEnabled(false);
            cmbMnt.setEnabled(false);
            cmbDtk.setEnabled(false);
        }
    }//GEN-LAST:event_ChkJamActionPerformed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void BtnPPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPPAActionPerformed
        pilihan = 1;
        akses.setform("DlgCPPT");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPPAActionPerformed

    private void MnHasilPemeriksaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanLabActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (Sequel.cariInteger("select count(1) cek from lis_reg where no_rawat='" + TNoRw.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(null, "Hasil pemeriksaan laboratorium (LIS) tidak ditemukan ...!!!!");
            } else {
                DlgHasilLIS lis = new DlgHasilLIS(null, false);
                lis.setSize(914, internalFrame1.getHeight() - 40);
                lis.setLocationRelativeTo(internalFrame1);
                lis.setData(TNoRw.getText(), TPasien.getText(), TNoRm.getText());
                lis.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnHasilPemeriksaanLabActionPerformed

    private void MnHasilPemeriksaanRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanRadActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            cekHasilRad = 0;
            kdItemrad = "";
            itemDipilih = "";
            tglRad = "";
            jamRad = "";

            tglRad = Sequel.cariIsi("select tgl_periksa from periksa_radiologi where no_rawat='" + TNoRw.getText() + "'");
            jamRad = Sequel.cariIsi("select jam from periksa_radiologi where no_rawat='" + TNoRw.getText() + "'");
            kdItemrad = Sequel.cariIsi("select kd_jenis_prw from periksa_radiologi where no_rawat='" + TNoRw.getText() + "' and "
                + "tgl_periksa='" + tglRad + "' and jam='" + jamRad + "'");
            itemDipilih = Sequel.cariIsi("select nm_perawatan from jns_perawatan_radiologi where kd_jenis_prw='" + kdItemrad + "'");
            cekHasilRad = Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + TNoRw.getText() + "' and "
                + "tgl_periksa='" + tglRad + "' and jam='" + jamRad + "'");

            if (cekHasilRad >= 1) {
                akses.setform("DlgCPPT");
                DlgCariPeriksaRadiologi form = new DlgCariPeriksaRadiologi(null, false);
                form.WindowHasil.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.WindowHasil.setLocationRelativeTo(internalFrame1);
                form.isCek();
             
                if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
                    form.setData(TNoRw.getText(), kdItemrad, itemDipilih, "OK", TNoRm.getText(), TPasien.getText(), tglRad, jamRad,
                            Sequel.cariIsi("select ifnull(diagnosa,'-') from pemeriksaan_ralan_petugas where no_rawat='" + TNoRw.getText() + "'"),
                            Sequel.cariIsi("select pl.nm_poli from poliklinik pl inner join reg_periksa rp on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + TNoRw.getText() + "'"),
                            Sequel.cariIsi("select p.nama from periksa_radiologi pr inner join pegawai p on p.nik=pr.kd_dokter where pr.no_rawat='" + TNoRw.getText() + "'"),
                            Sequel.cariIsi("select p.nama from periksa_radiologi pr inner join pegawai p on p.nik=pr.dokter_perujuk where pr.no_rawat='" + TNoRw.getText() + "'"),
                            Sequel.cariIsi("select concat('(',UPPER(tipe_faskes),') ',nama_rujukan) from master_nama_rujukan where "
                                    + "kd_rujukan='" + Sequel.cariIsi("select ifnull(kd_rujukan,'') from rujuk_masuk where no_rawat='" + TNoRw.getText() + "'") + "'"));
                } else if (status.equals("ranap")) {
                    form.setData(TNoRw.getText(), kdItemrad, itemDipilih, "OK", TNoRm.getText(), TPasien.getText(), tglRad, jamRad,
                            Sequel.cariIsi("select ifnull(diagnosa_awal,'-') from kamar_inap where no_rawat='" + TNoRw.getText() + "' order by tgl_masuk, jam_masuk limit 1"),
                            Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' order by tgl_masuk desc, jam_masuk desc limit 1"),
                            Sequel.cariIsi("select p.nama from periksa_radiologi pr inner join pegawai p on p.nik=pr.kd_dokter where pr.no_rawat='" + TNoRw.getText() + "'"),
                            Sequel.cariIsi("select p.nama from periksa_radiologi pr inner join pegawai p on p.nik=pr.dokter_perujuk where pr.no_rawat='" + TNoRw.getText() + "'"),
                            Sequel.cariIsi("select concat('(',UPPER(tipe_faskes),') ',nama_rujukan) from master_nama_rujukan where "
                                    + "kd_rujukan='" + Sequel.cariIsi("select ifnull(kd_rujukan,'') from rujuk_masuk where no_rawat='" + TNoRw.getText() + "'") + "'"));
                }

                form.WindowHasil.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Hasil pemeriksaan radiologi (expertise) tidak ditemukan ...!!!!");
            }
        }
    }//GEN-LAST:event_MnHasilPemeriksaanRadActionPerformed

    private void BtnKonsulenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKonsulenActionPerformed
        pilihan = 2;
        akses.setform("DlgCPPT");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnKonsulenActionPerformed

    private void BtnSerahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSerahActionPerformed
        pilihan = 2;
        akses.setform("DlgCPPT");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnSerahActionPerformed

    private void BtnTerimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTerimaActionPerformed
        pilihan = 3;
        akses.setform("DlgCPPT");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnTerimaActionPerformed

    private void ChkSamaPPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSamaPPAActionPerformed
        if (ChkSamaPPA.isSelected() == true) {
            nipSerah.setText(nipppa);
            nmSerah.setText(nmppa.getText());
        } else {
            nipSerah.setText("-");
            nmSerah.setText("-");
        }
    }//GEN-LAST:event_ChkSamaPPAActionPerformed

    private void cmbRawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRawatActionPerformed
        if (cmbRawat.getSelectedIndex() == 1) {
            cmbSiftCppt.setSelectedIndex(0);
            cmbSiftCppt.setEnabled(true);
        } else {
            cmbSiftCppt.setSelectedIndex(0);
            cmbSiftCppt.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRawatActionPerformed

    private void cmbBagianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBagianActionPerformed
        if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
            nipkonsulen = "-";
            nmKonsulen.setText("-");
            cmbPPA.setSelectedIndex(0);
            cmbSertim.setSelectedIndex(0);
            nipppa = "-";
            nmppa.setText("-");

            if (cmbBagian.getSelectedIndex() == 3) {
                JOptionPane.showMessageDialog(rootPane, "Jenis bagian PPA hanya untuk pengisian CPPT rawat inap..!!!");
                cmbBagian.setSelectedIndex(0);
                BtnKonsulen.setEnabled(false);
                cmbBagian.requestFocus();
            } else if (cmbBagian.getSelectedIndex() == 2 || cmbBagian.getSelectedIndex() == 4) {
                BtnKonsulen.setEnabled(true);
            } else {
                BtnKonsulen.setEnabled(false);
            }
        } 
        
//        else if (status.equals("ranap")) {
//            if (cmbBagian.getSelectedIndex() == 2) {                
//                BtnKonsulen.setEnabled(true);
//                cmbPPA.setEnabled(false);
//                cmbSertim.setEnabled(false);
//                BtnPPA.setEnabled(false);
//            } else if (cmbBagian.getSelectedIndex() == 3) {
//                BtnKonsulen.setEnabled(false);
//                cmbPPA.setEnabled(true);
//                cmbSertim.setEnabled(false);
//                BtnPPA.setEnabled(false);
//            } else {
//                BtnKonsulen.setEnabled(false);
//                cmbPPA.setEnabled(false);
//                cmbSertim.setEnabled(false);
//                BtnPPA.setEnabled(false);
//            }
//        }
    }//GEN-LAST:event_cmbBagianActionPerformed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowCPPT.dispose();
        cmbJnsCppt.setSelectedIndex(0);
        cmbSiftPetugas.setSelectedIndex(0);
        cmbTanggal.setSelectedIndex(0);
        tglA.setDate(new Date());
        tglB.setDate(new Date());
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void cmbJnsCpptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJnsCpptActionPerformed
        tglA.setDate(new Date());
        tglB.setDate(new Date());
        cmbSiftPetugas.setSelectedIndex(0);
        cmbTanggal.setSelectedIndex(0);

        if (cmbJnsCppt.getSelectedIndex() == 0) {
            cmbTanggal.setEnabled(true);
            cmbSiftPetugas.setEnabled(false);
            tglA.setEnabled(false);
            tglB.setEnabled(false);
        } else if (cmbJnsCppt.getSelectedIndex() == 1) {
            cmbTanggal.setEnabled(false);
            cmbSiftPetugas.setEnabled(false);
            tglA.setEnabled(false);
            tglB.setEnabled(false);
        } else if (cmbJnsCppt.getSelectedIndex() == 2) {
            cmbTanggal.setEnabled(true);
            cmbSiftPetugas.setEnabled(true);
            tglA.setEnabled(false);
            tglB.setEnabled(false);
        }
    }//GEN-LAST:event_cmbJnsCpptActionPerformed

    private void BtnBacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBacaActionPerformed
        if (cmbJnsCppt.getSelectedIndex() == 0) {
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + TNoRw.getText() + "' and STATUS='Ranap'") > 0) {
                cetakCPPTranap();
            } else {
                JOptionPane.showMessageDialog(null, "Data CPPT rawat inap tidak ditemukan...!!!");
            }
        } else if (cmbJnsCppt.getSelectedIndex() == 1) {
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + TNoRw.getText() + "' and STATUS='Ralan'") > 0) {
                cetakCPPTralan();
            } else {
                JOptionPane.showMessageDialog(null, "Data CPPT IGD tidak ditemukan...!!!");
            }
        } else if (cmbJnsCppt.getSelectedIndex() == 2) {
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + TNoRw.getText() + "'") > 0) {
                cetakCPPTsemua();
            } else {
                JOptionPane.showMessageDialog(null, "Data CPPT tidak ditemukan...!!!");
            }
        }
    }//GEN-LAST:event_BtnBacaActionPerformed

    private void cmbTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTanggalActionPerformed
        cmbSiftPetugas.setEnabled(true);
        if (cmbTanggal.getSelectedIndex() == 0) {
            tglA.setEnabled(false);
            tglB.setEnabled(false);
        } else if (cmbTanggal.getSelectedIndex() == 1) {
            tglA.setEnabled(true);
            tglB.setEnabled(true);
            tglA.requestFocus();
        } else if (cmbTanggal.getSelectedIndex() == 2) {
            tglA.setEnabled(true);
            tglB.setEnabled(false);
            tglA.requestFocus();
        }
    }//GEN-LAST:event_cmbTanggalActionPerformed

    private void cmbSertimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSertimActionPerformed
        if (cmbSertim.getSelectedIndex() == 1) {
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
            BtnSerah.setEnabled(true);
            BtnTerima.setEnabled(true);
            ChkSamaPPA.setEnabled(true);

            cmbJam1.setSelectedIndex(0);
            cmbMnt1.setSelectedIndex(0);
            cmbDtk1.setSelectedIndex(0);
            ChkSamaPPA.setSelected(false);
            nipSerah.setText("-");
            nmSerah.setText("-");
            nipTerima.setText("-");
            nmTerima.setText("-");
        } else {
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
            BtnSerah.setEnabled(false);
            BtnTerima.setEnabled(false);
            ChkSamaPPA.setEnabled(false);
            ChkSamaPPA.setSelected(false);
            cmbJam1.setSelectedIndex(0);
            cmbMnt1.setSelectedIndex(0);
            cmbDtk1.setSelectedIndex(0);
            nipSerah.setText("-");
            nmSerah.setText("-");
            nipTerima.setText("-");
            nmTerima.setText("-");
        }
    }//GEN-LAST:event_cmbSertimActionPerformed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void cmbPPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPPAActionPerformed
        nipppa = "-";
        nmppa.setText("-");
        cmbSertim.setSelectedIndex(0);
        
        if (cmbPPA.getSelectedIndex() == 0) {
            BtnPPA.setEnabled(false);
            cmbSertim.setEnabled(false);
            MnCeklisFarmasi.setEnabled(false);
            MnPemberianDiet.setEnabled(false);
            MnTindakan.setEnabled(false);
        } else if (cmbPPA.getSelectedIndex() == 1 || cmbPPA.getSelectedIndex() == 2) {
            BtnPPA.setEnabled(true);
            cmbSertim.setEnabled(true);
            MnCeklisFarmasi.setEnabled(false);
            MnPemberianDiet.setEnabled(false);
            MnTindakan.setEnabled(true);
        } else if (cmbPPA.getSelectedIndex() == 3) {
            BtnPPA.setEnabled(true);
            cmbSertim.setEnabled(true);
            MnCeklisFarmasi.setEnabled(true);
            MnPemberianDiet.setEnabled(false);
            MnTindakan.setEnabled(false);
        } else if (cmbPPA.getSelectedIndex() == 4) {
            BtnPPA.setEnabled(true);
            cmbSertim.setEnabled(true);
            MnCeklisFarmasi.setEnabled(false);
            MnPemberianDiet.setEnabled(true);
            MnTindakan.setEnabled(false);
        } else {
            BtnPPA.setEnabled(true);
            cmbSertim.setEnabled(true);
            MnCeklisFarmasi.setEnabled(false);
            MnPemberianDiet.setEnabled(false);
            MnTindakan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPPAActionPerformed

    private void TabCPPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabCPPTMouseClicked
        if (TabCPPT.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabCPPTMouseClicked

    private void BtnCloseIn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn7ActionPerformed
        WindowFarmasi.dispose();
        ChkDapatObat.setSelected(false);
        ChkObatSesuai.setSelected(false);
        ChkObatEfektif.setSelected(false);
        ChkObatAman.setSelected(false);
        ChkSemua.setSelected(false);
    }//GEN-LAST:event_BtnCloseIn7ActionPerformed

    private void BtnSimpanCeklisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanCeklisActionPerformed
        dapatobat = "";
        obatsesuai = "";
        obatefektif = "";
        obataman = "";
        
        if (ChkDapatObat.isSelected() == true) {
            dapatobat = "- Dapat Obat : YA\n";
        } else {
            dapatobat = "- Dapat Obat : TIDAK\n";
        }
        
        if (ChkObatSesuai.isSelected() == true) {
            obatsesuai = "- Obat Sesuai : YA\n";
        } else {
            obatsesuai = "- Obat Sesuai : TIDAK\n";
        }
        
        if (ChkObatEfektif.isSelected() == true) {
            obatefektif = "- Obat Efektif : YA\n";
        } else {
            obatefektif = "- Obat Efektif : TIDAK\n";
        }
        
        if (ChkObatAman.isSelected() == true) {
            obataman = "- Obat Aman : YA\n";
        } else {
            obataman = "- Obat Aman : TIDAK";
        }
        
        if (THasil.getText().equals("")) {
            THasil.setText("Pemantauan Penggunaan Obat :\n\n" + dapatobat + obatsesuai + obatefektif + obataman);
        } else {
            THasil.setText(THasil.getText() + "\n\nPemantauan Penggunaan Obat :\n" + dapatobat + obatsesuai + obatefektif + obataman);
        }
        BtnCloseIn7ActionPerformed(null);
        
    }//GEN-LAST:event_BtnSimpanCeklisActionPerformed

    private void MnCeklisFarmasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCeklisFarmasiActionPerformed
        ChkDapatObat.setSelected(false);
        ChkObatSesuai.setSelected(false);
        ChkObatEfektif.setSelected(false);
        ChkObatAman.setSelected(false);
        ChkSemua.setSelected(false);
        
        WindowFarmasi.setSize(300, 172);
        WindowFarmasi.setLocationRelativeTo(internalFrame1);
        WindowFarmasi.setAlwaysOnTop(false);
        WindowFarmasi.setVisible(true);
    }//GEN-LAST:event_MnCeklisFarmasiActionPerformed

    private void MnPemberianDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemberianDietActionPerformed
        BtnBatal1ActionPerformed(null);
        WindowDiet.setSize(500, 600);
        WindowDiet.setLocationRelativeTo(internalFrame1);
        WindowDiet.setAlwaysOnTop(false);
        WindowDiet.setVisible(true);
    }//GEN-LAST:event_MnPemberianDietActionPerformed

    private void BtnUlangiHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUlangiHasilActionPerformed
        if (THasil.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Hasil pemeriksaan masih kosong/belum terisi...!!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin hasil pemeriksaan akan hapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                THasil.setText("");
                THasil.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnUlangiHasilActionPerformed

    private void BtnUlangiInstruksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUlangiInstruksiActionPerformed
        if (TInstruksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Instruksi tenaga kesehatan masih kosong/belum terisi...!!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin instruksi tenaga kesehatan akan hapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                TInstruksi.setText("");
                TInstruksi.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnUlangiInstruksiActionPerformed

    private void ChkSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSemuaActionPerformed
        if (ChkSemua.isSelected() == true) {
            ChkDapatObat.setSelected(true);
            ChkObatSesuai.setSelected(true);
            ChkObatEfektif.setSelected(true);
            ChkObatAman.setSelected(true);
        } else {
            ChkDapatObat.setSelected(false);
            ChkObatSesuai.setSelected(false);
            ChkObatEfektif.setSelected(false);
            ChkObatAman.setSelected(false);
        }
    }//GEN-LAST:event_ChkSemuaActionPerformed

    private void MnTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTindakanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MnTindakanActionPerformed

    private void BtnHapusDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusDPJPActionPerformed
        kddpjp.setText("-");
        nmdpjp.setText("-");
    }//GEN-LAST:event_BtnHapusDPJPActionPerformed

    private void BtnHapusKonsulenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusKonsulenActionPerformed
        nipkonsulen = "-";
        nmKonsulen.setText("-");
    }//GEN-LAST:event_BtnHapusKonsulenActionPerformed

    private void BtnHapusPPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusPPAActionPerformed
        nipppa = "-";
        nmppa.setText("-");
    }//GEN-LAST:event_BtnHapusPPAActionPerformed

    private void BtnHapusSerahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusSerahActionPerformed
        nipSerah.setText("-");
        nmSerah.setText("-");
    }//GEN-LAST:event_BtnHapusSerahActionPerformed

    private void BtnHapusTerimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusTerimaActionPerformed
        nipTerima.setText("-");
        nmTerima.setText("-");
    }//GEN-LAST:event_BtnHapusTerimaActionPerformed

    private void cekWaktuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cekWaktuItemStateChanged
        if (cekWaktu.isSelected() == true) {
            cekWaktu.setText("Semua Waktu Diet");
            cekDietPagi.setSelected(true);
            cekDietSiang.setSelected(true);
            cekDietSore.setSelected(true);
        } else if (cekWaktu.isSelected() == false) {
            cekWaktu.setText("Waktu Diet BERBEDA");
            cekDietPagi.setSelected(false);
            cekDietSiang.setSelected(false);
            cekDietSore.setSelected(false);
        }
    }//GEN-LAST:event_cekWaktuItemStateChanged

    private void cekWaktuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cekWaktuMouseClicked
        if (cekWaktu.isSelected() == true) {
            cekWaktu.setText("Semua Waktu Diet");
            cekDietPagi.setSelected(true);
            cekDietSiang.setSelected(true);
            cekDietSore.setSelected(true);
        } else if (cekWaktu.isSelected() == false) {
            cekWaktu.setText("Waktu Diet BERBEDA");
            cekDietPagi.setSelected(false);
            cekDietSiang.setSelected(false);
            cekDietSore.setSelected(false);
        }
    }//GEN-LAST:event_cekWaktuMouseClicked

    private void cekWaktuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekWaktuActionPerformed
        if (cekWaktu.isSelected() == true) {
            cekWaktu.setText("Semua Waktu Diet");
            cekDietPagi.setSelected(true);
            cekDietSiang.setSelected(true);
            cekDietSore.setSelected(true);
        } else if (cekWaktu.isSelected() == false) {
            cekWaktu.setText("Waktu Diet BERBEDA");
            cekDietPagi.setSelected(false);
            cekDietSiang.setSelected(false);
            cekDietSore.setSelected(false);
        }
    }//GEN-LAST:event_cekWaktuActionPerformed

    private void cekKemasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekKemasanActionPerformed
        if (cekKemasan.isSelected() == true) {
            cekKemasan.setText("Dikemas Dengan KOTAKAN");
        } else if (cekKemasan.isSelected() == false) {
            cekKemasan.setText("Disajikan Seperti Biasa");
        }
    }//GEN-LAST:event_cekKemasanActionPerformed

    private void tbDietKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDietKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDiet();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDietKeyPressed

    private void tbDietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDietMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDiet();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDietMouseClicked

    private void BtnPilihDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPilihDietActionPerformed
        kddiet.setText("");
        nmdiet.setText("");
        jnsMakanan.setText("");
        kdberi.setText("");
        jumlahBeri.setText("");
        cmbSatuan.setSelectedIndex(0);
        jumlahBeri.setEditable(false);
        cmbSatuan.setEnabled(false);
        BtnSimpan2.setEnabled(true);
        BtnEdit1.setEnabled(false);

        WindowDataDiet.setSize(458, 168);
        WindowDataDiet.setLocationRelativeTo(internalFrame12);
        WindowDataDiet.setVisible(true);
        WindowDataDiet.requestFocus();
    }//GEN-LAST:event_BtnPilihDietActionPerformed

    private void BtnHapusPilihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusPilihanActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Diet yang dipilih/ditentukan belum ada...!!");
            BtnPilihDiet.requestFocus();
        } else if (waktuSimpanDiet.equals("")) {
            JOptionPane.showMessageDialog(null, "Belum ada data diet yang dipilih pada tabel...!!!!");
            tbDiet.requestFocus();
        } else if (tbDiet.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data dietnya pada tabel...!!!!");
            tbDiet.requestFocus();
        } else {
            tabMode2.removeRow(tbDiet.getSelectedRow());
            kddiet.setText("");
            nmdiet.setText("");
            jnsMakanan.setText("");
            kdberi.setText("");
            jumlahBeri.setText("");
            cmbSatuan.setSelectedIndex(0);
            jumlahBeri.setEditable(false);
            cmbSatuan.setEnabled(false);
            BtnHapusPilihan.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusPilihanActionPerformed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Diet yang dipilih/ditentukan belum ada...!!");
            BtnPilihDiet.requestFocus();
        } else if (waktuSimpanDiet.equals("")) {
            JOptionPane.showMessageDialog(null, "Belum ada data diet yang dipilih pada tabel...!!!!");
            tbDiet.requestFocus();
        } else if (tbDiet.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data dietnya pada tabel...!!!!");
            tbDiet.requestFocus();
        } else {
            jumlahBeri.setText("");
            cmbSatuan.setSelectedIndex(0);
            BtnSimpan2.setEnabled(false);
            BtnEdit1.setEnabled(true);

            WindowDataDiet.setSize(458, 168);
            WindowDataDiet.setLocationRelativeTo(internalFrame12);
            WindowDataDiet.setVisible(true);
            WindowDataDiet.requestFocus();

            if (nmdiet.getText().equals("F75") || nmdiet.getText().equals("F100") || nmdiet.getText().equals("F135")) {
                kdberi.setText("-");
                jumlahBeri.setEditable(true);
                cmbSatuan.setEnabled(true);
                jumlahBeri.requestFocus();
            } else {
                kdberi.setText("");
                jumlahBeri.setEditable(false);
                cmbSatuan.setEnabled(false);
                btnJumlahBeri.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiActionPerformed

    private void btnDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDietActionPerformed
        akses.setform("DlgCPPT");
        diet.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        diet.setLocationRelativeTo(internalFrame1);
        diet.setVisible(true);
        diet.TCari.requestFocus();
    }//GEN-LAST:event_btnDietActionPerformed

    private void btnJumlahBeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJumlahBeriActionPerformed
        akses.setform("DlgCPPT");
        jlhberi.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        jlhberi.setLocationRelativeTo(internalFrame1);
        jlhberi.tampil();
        jlhberi.setVisible(true);
        jlhberi.TCari.requestFocus();
    }//GEN-LAST:event_btnJumlahBeriActionPerformed

    private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
        if (kddiet.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis dietnya...!!!!");
        } else if (kdberi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis jlh. pemberian dietnya...!!!!");
        } else {
            if (nmdiet.getText().equals("F75") || nmdiet.getText().equals("F100") || nmdiet.getText().equals("F135")) {
                if (jumlahBeri.getText().trim().equals("-") || jumlahBeri.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Jlh. pemberian diet harus diisi dg. benar...!!!!");
                } else if (cmbSatuan.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Pilih salah satu satuan jlh. pemberian dietnya dg. benar...!!!!");
                } else {
                    if (Sequel.cariInteger("select count(-1) from diet_master_pemberian where nm_pemberian='" + jumlahBeri.getText() + "' and satuan='" + cmbSatuan.getSelectedItem().toString() + "'") == 0) {
                        kdberi.setText("");
                        Valid.autoNomer("diet_master_pemberian", "DP", 4, kdberi);
                        Sequel.menyimpan("diet_master_pemberian", "'" + kdberi.getText() + "','" + jumlahBeri.getText() + "','" + cmbSatuan.getSelectedItem() + "','1'", "Jumlah Pemberian Diet");
                        tabMode2.addRow(new Object[]{kddiet.getText(), nmdiet.getText(), jnsMakanan.getText(),
                            jumlahBeri.getText() + " " + cmbSatuan.getSelectedItem(), Sequel.cariIsi("select now()")});
                        BtnCloseIn3ActionPerformed(null);
                    } else {
                        tabMode2.addRow(new Object[]{kddiet.getText(), nmdiet.getText(), jnsMakanan.getText(),
                            jumlahBeri.getText() + " " + cmbSatuan.getSelectedItem(), Sequel.cariIsi("select now()")});
                        BtnCloseIn3ActionPerformed(null);
                    }
                }
            } else {
                tabMode2.addRow(new Object[]{kddiet.getText(), nmdiet.getText(), jnsMakanan.getText(),
                    jumlahBeri.getText() + " " + cmbSatuan.getSelectedItem(), Sequel.cariIsi("select now()")});
                BtnCloseIn3ActionPerformed(null);
            }
        }
    }//GEN-LAST:event_BtnSimpan2ActionPerformed

    private void BtnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit1ActionPerformed
        if (kdberi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis jlh. pemberian dietnya...!!!!");
        } else {
            if (nmdiet.getText().equals("F75") || nmdiet.getText().equals("F100") || nmdiet.getText().equals("F135")) {
                if (jumlahBeri.getText().trim().equals("-") || jumlahBeri.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Jlh. pemberian diet harus diisi dg. benar...!!!!");
                } else if (cmbSatuan.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Pilih salah satu satuan jlh. pemberian dietnya dg. benar...!!!!");
                } else {
                    tabMode2.removeRow(tbDiet.getSelectedRow());
                    tabMode2.addRow(new Object[]{kddiet.getText(), nmdiet.getText(), jnsMakanan.getText(),
                        jumlahBeri.getText() + " " + cmbSatuan.getSelectedItem(), Sequel.cariIsi("select now()")});
                    BtnCloseIn3ActionPerformed(null);
                }
            } else {
                tabMode2.removeRow(tbDiet.getSelectedRow());
                tabMode2.addRow(new Object[]{kddiet.getText(), nmdiet.getText(), jnsMakanan.getText(),
                    jumlahBeri.getText() + " " + cmbSatuan.getSelectedItem(), Sequel.cariIsi("select now()")});
                BtnCloseIn3ActionPerformed(null);
            }
        }
    }//GEN-LAST:event_BtnEdit1ActionPerformed

    private void BtnEdit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEdit1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnEditActionPerformed(null);
        }
    }//GEN-LAST:event_BtnEdit1KeyPressed

    private void BtnCloseIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn3ActionPerformed
        kddiet.setText("");
        nmdiet.setText("");
        jnsMakanan.setText("");
        kdberi.setText("");
        jumlahBeri.setText("");
        waktuSimpanDiet = "";
        cmbSatuan.setSelectedIndex(0);
        jumlahBeri.setEditable(false);
        cmbSatuan.setEnabled(false);
        WindowDataDiet.dispose();
    }//GEN-LAST:event_BtnCloseIn3ActionPerformed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "pasien");
        } else if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data diet belum terisi...!!");
        } else {
            kemasan = "";
            if (cekKemasan.isSelected() == true) {
                kemasan = "kotak";
            } else if (cekKemasan.isSelected() == false) {
                kemasan = "biasa";
            }
            
            if (jnsRawat.equals("Ranap")) {
                simpanDietINAP();
            } else if (jnsRawat.equals("Ralan")) {
                simpanDietJALAN();
            }
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, TPasien, BtnBatal);
        }
    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void BtnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal1ActionPerformed
        Valid.tabelKosong(tabMode2);
        DTPTgl.setDate(new Date());
        cekWaktu.setSelected(false);
        cekDietPagi.setSelected(false);
        cekDietSiang.setSelected(false);
        cekDietSore.setSelected(false);
        cekKemasan.setSelected(false);
        waktuSimpanDiet = "";
        
        if (cekKemasan.isSelected() == true) {
            cekKemasan.setText("Dikemas Dengan KOTAKAN");
        } else if (cekKemasan.isSelected() == false) {
            cekKemasan.setText("Disajikan Seperti Biasa");
        }
    }//GEN-LAST:event_BtnBatal1ActionPerformed

    private void BtnBatal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatal1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        }
    }//GEN-LAST:event_BtnBatal1KeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        WindowDiet.dispose();
        BtnBatal1ActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            WindowDiet.dispose();
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void MnDataPemberianDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataPemberianDietActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien belum dip[ilih..!!!!");
        } else {
            DlgPemberianDiet rawatinap = new DlgPemberianDiet(null, false);
            rawatinap.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            rawatinap.setLocationRelativeTo(internalFrame1);
            rawatinap.emptTeks();
            rawatinap.cekWaktu.setSelected(true);
            rawatinap.TCari.setText("");
            rawatinap.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
            rawatinap.isCek();
            rawatinap.setVisible(true);
        }
    }//GEN-LAST:event_MnDataPemberianDietActionPerformed

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
        Valid.pindah(evt, TCari, DTPTgl);
    }//GEN-LAST:event_DTPTglKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCPPT dialog = new DlgCPPT(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBaca;
    private widget.Button BtnBatal;
    private widget.Button BtnBatal1;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn3;
    private widget.Button BtnCloseIn6;
    private widget.Button BtnCloseIn7;
    private widget.Button BtnCopas;
    private widget.Button BtnDPJP;
    private widget.Button BtnEdit;
    private widget.Button BtnEdit1;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusDPJP;
    private widget.Button BtnHapusKonsulen;
    private widget.Button BtnHapusPPA;
    private widget.Button BtnHapusPilihan;
    private widget.Button BtnHapusSerah;
    private widget.Button BtnHapusTerima;
    private widget.Button BtnHasil;
    private widget.Button BtnInstruksi;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKonsulen;
    private widget.Button BtnPPA;
    private widget.Button BtnPilihDiet;
    private widget.Button BtnPrint;
    private widget.Button BtnSerah;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan2;
    private widget.Button BtnSimpanCeklis;
    private widget.Button BtnTerima;
    private widget.Button BtnUlangiHasil;
    private widget.Button BtnUlangiInstruksi;
    public widget.CekBox ChkDapatObat;
    public widget.CekBox ChkJam;
    public widget.CekBox ChkObatAman;
    public widget.CekBox ChkObatEfektif;
    public widget.CekBox ChkObatSesuai;
    public widget.CekBox ChkSamaPPA;
    public widget.CekBox ChkSemua;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTgl;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnCeklisFarmasi;
    private javax.swing.JMenuItem MnDataPemberianDiet;
    private javax.swing.JMenuItem MnHasilPemeriksaanLab;
    private javax.swing.JMenuItem MnHasilPemeriksaanRad;
    private javax.swing.JMenuItem MnPemberianDiet;
    private javax.swing.JMenuItem MnTindakan;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll5;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextArea THasil;
    private widget.TextArea TInstruksi;
    private widget.TextBox TKd;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabCPPT;
    private widget.TextArea Ttemplate;
    private javax.swing.JDialog WindowCPPT;
    private javax.swing.JDialog WindowDataDiet;
    private javax.swing.JDialog WindowDiet;
    private javax.swing.JDialog WindowFarmasi;
    private javax.swing.JDialog WindowTemplate;
    private widget.Button btnDiet;
    private widget.Button btnJumlahBeri;
    private widget.CekBox cekDietPagi;
    private widget.CekBox cekDietSiang;
    private widget.CekBox cekDietSore;
    private widget.CekBox cekKemasan;
    public widget.CekBox cekWaktu;
    private widget.ComboBox cmbBagian;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJnsCppt;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbPPA;
    private widget.ComboBox cmbRawat;
    private widget.ComboBox cmbSatuan;
    private widget.ComboBox cmbSertim;
    private widget.ComboBox cmbSift;
    private widget.ComboBox cmbSiftCppt;
    private widget.ComboBox cmbSiftPetugas;
    private widget.ComboBox cmbTanggal;
    private widget.ComboBox cmbVerifikasi;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame12;
    private widget.InternalFrame internalFrame14;
    private widget.InternalFrame internalFrame15;
    private widget.InternalFrame internalFrame16;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame9;
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
    private widget.Label jLabel3;
    private widget.Label jLabel36;
    private widget.Label jLabel4;
    private widget.Label jLabel42;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.TextBox jnsMakanan;
    private widget.TextBox jumlahBeri;
    private widget.TextBox kdberi;
    private widget.TextBox kddiet;
    private widget.TextBox kddpjp;
    private widget.TextBox nipSerah;
    private widget.TextBox nipTerima;
    private widget.TextBox nmKonsulen;
    private widget.TextBox nmSerah;
    private widget.TextBox nmTerima;
    private widget.TextBox nmdiet;
    private widget.TextBox nmdpjp;
    private widget.TextBox nmppa;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.Table tbCPPT;
    private widget.Table tbDiet;
    private widget.Table tbTemplate;
    private widget.Tanggal tglA;
    private widget.Tanggal tglB;
    private widget.Tanggal tglCppt;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if (cmbRawat.getSelectedIndex() == 0) {
                ps = koneksi.prepareStatement("SELECT c.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT( p.tgl_lahir, '%d-%m-%Y' ) tgllhr, "
                        + "DATE_FORMAT( c.tgl_cppt, '%d-%m-%Y' ) tglcppt, c.hasil_pemeriksaan, c.instruksi_nakes, c.verifikasi, pg.nama nmdpjp, c.STATUS, "
                        + "c.tgl_cppt, c.nip_dpjp, c.waktu_simpan, c.cek_jam, c.jam_cppt, IF(c.cek_jam = 'ya', c.jam_cppt, '-') jam_cppt_data, c.jenis_ppa, "
                        + "pg1.nama nmppa, c.jenis_bagian, c.nip_ppa, c.serah_terima_cppt, pg2.nama nmkonsulen, c.nip_konsulen, pg3.nama petugasSerah, "
                        + "pg4.nama petugasTerima, c.nip_petugas_serah, c.nip_petugas_terima, c.cppt_shift, c.jam_serah_terima FROM cppt c "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp INNER JOIN pegawai pg1 ON pg1.nik = c.nip_ppa "
                        + "INNER JOIN pegawai pg2 ON pg2.nik = c.nip_konsulen INNER JOIN pegawai pg3 ON pg3.nik = c.nip_petugas_serah "
                        + "INNER JOIN pegawai pg4 ON pg4.nik = c.nip_petugas_terima where "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and c.no_rawat like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and p.no_rkm_medis like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and p.nm_pasien like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and c.hasil_pemeriksaan like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and c.instruksi_nakes like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and c.verifikasi like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and c.jenis_bagian like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and pg.nama like ? order by c.tgl_cppt desc, c.jam_cppt desc");
            } else if (cmbRawat.getSelectedIndex() == 1 && cmbSiftCppt.getSelectedIndex() != 4) {
                ps = koneksi.prepareStatement("SELECT c.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT( p.tgl_lahir, '%d-%m-%Y' ) tgllhr, "
                        + "DATE_FORMAT( c.tgl_cppt, '%d-%m-%Y' ) tglcppt, c.hasil_pemeriksaan, c.instruksi_nakes, c.verifikasi, pg.nama nmdpjp, c.STATUS, "
                        + "c.tgl_cppt, c.nip_dpjp, c.waktu_simpan, c.cek_jam, c.jam_cppt, IF(c.cek_jam = 'ya', c.jam_cppt, '-') jam_cppt_data, c.jenis_ppa, "
                        + "pg1.nama nmppa, c.jenis_bagian, c.nip_ppa, c.serah_terima_cppt, pg2.nama nmkonsulen, c.nip_konsulen, pg3.nama petugasSerah, "
                        + "pg4.nama petugasTerima, c.nip_petugas_serah, c.nip_petugas_terima, c.cppt_shift, c.jam_serah_terima FROM cppt c "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp INNER JOIN pegawai pg1 ON pg1.nik = c.nip_ppa "
                        + "INNER JOIN pegawai pg2 ON pg2.nik = c.nip_konsulen INNER JOIN pegawai pg3 ON pg3.nik = c.nip_petugas_serah "
                        + "INNER JOIN pegawai pg4 ON pg4.nik = c.nip_petugas_terima where "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.no_rawat like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and p.no_rkm_medis like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and p.nm_pasien like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.hasil_pemeriksaan like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.instruksi_nakes like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.verifikasi like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.jenis_bagian like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.jenis_ppa like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and pg.nama like ? "
                        + "order by c.tgl_cppt desc, c.jam_cppt desc");
            } else if (cmbRawat.getSelectedIndex() == 1 && cmbSiftCppt.getSelectedIndex() == 4) {
                ps = koneksi.prepareStatement("SELECT c.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT( p.tgl_lahir, '%d-%m-%Y' ) tgllhr, "
                        + "DATE_FORMAT( c.tgl_cppt, '%d-%m-%Y' ) tglcppt, c.hasil_pemeriksaan, c.instruksi_nakes, c.verifikasi, pg.nama nmdpjp, c.STATUS, "
                        + "c.tgl_cppt, c.nip_dpjp, c.waktu_simpan, c.cek_jam, c.jam_cppt, IF(c.cek_jam = 'ya', c.jam_cppt, '-') jam_cppt_data, c.jenis_ppa, "
                        + "pg1.nama nmppa, c.jenis_bagian, c.nip_ppa, c.serah_terima_cppt, pg2.nama nmkonsulen, c.nip_konsulen, pg3.nama petugasSerah, "
                        + "pg4.nama petugasTerima, c.nip_petugas_serah, c.nip_petugas_terima, c.cppt_shift, c.jam_serah_terima FROM cppt c "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp INNER JOIN pegawai pg1 ON pg1.nik = c.nip_ppa "
                        + "INNER JOIN pegawai pg2 ON pg2.nik = c.nip_konsulen INNER JOIN pegawai pg3 ON pg3.nik = c.nip_petugas_serah "
                        + "INNER JOIN pegawai pg4 ON pg4.nik = c.nip_petugas_terima where "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and c.no_rawat like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and p.no_rkm_medis like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and p.nm_pasien like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and c.hasil_pemeriksaan like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and c.instruksi_nakes like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and c.verifikasi like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and c.jenis_bagian like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and c.jenis_ppa like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and pg.nama like ? order by c.tgl_cppt desc, c.jam_cppt desc");
            } else if (cmbRawat.getSelectedIndex() == 2) {
                ps = koneksi.prepareStatement("SELECT c.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT( p.tgl_lahir, '%d-%m-%Y' ) tgllhr, "
                        + "DATE_FORMAT( c.tgl_cppt, '%d-%m-%Y' ) tglcppt, c.hasil_pemeriksaan, c.instruksi_nakes, c.verifikasi, pg.nama nmdpjp, c.STATUS, "
                        + "c.tgl_cppt, c.nip_dpjp, c.waktu_simpan, c.cek_jam, c.jam_cppt, IF(c.cek_jam = 'ya', c.jam_cppt, '-') jam_cppt_data, c.jenis_ppa, "
                        + "pg1.nama nmppa, c.jenis_bagian, c.nip_ppa, c.serah_terima_cppt, pg2.nama nmkonsulen, c.nip_konsulen, pg3.nama petugasSerah, "
                        + "pg4.nama petugasTerima, c.nip_petugas_serah, c.nip_petugas_terima, c.cppt_shift, c.jam_serah_terima FROM cppt c "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp INNER JOIN pegawai pg1 ON pg1.nik = c.nip_ppa "
                        + "INNER JOIN pegawai pg2 ON pg2.nik = c.nip_konsulen INNER JOIN pegawai pg3 ON pg3.nik = c.nip_petugas_serah "
                        + "INNER JOIN pegawai pg4 ON pg4.nik = c.nip_petugas_terima where "
                        + "c.tgl_cppt between ? and ? and c.no_rawat like ? or "
                        + "c.tgl_cppt between ? and ? and p.no_rkm_medis like ? or "
                        + "c.tgl_cppt between ? and ? and p.nm_pasien like ? or "
                        + "c.tgl_cppt between ? and ? and c.hasil_pemeriksaan like ? or "
                        + "c.tgl_cppt between ? and ? and c.instruksi_nakes like ? or "
                        + "c.tgl_cppt between ? and ? and c.verifikasi like ? or "
                        + "c.tgl_cppt between ? and ? and c.jenis_bagian like ? or "
                        + "c.tgl_cppt between ? and ? and c.jenis_ppa like ? or "
                        + "c.tgl_cppt between ? and ? and pg.nama like ? order by c.tgl_cppt desc, c.jam_cppt desc");
            }
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"), 
                        rs.getString("no_rkm_medis"), 
                        rs.getString("nm_pasien"), 
                        rs.getString("tgllhr"),
                        rs.getString("tglcppt"),
                        rs.getString("jam_cppt_data"),
                        rs.getString("hasil_pemeriksaan"),
                        rs.getString("instruksi_nakes"),
                        rs.getString("verifikasi"),
                        rs.getString("nmdpjp"),
                        rs.getString("status"),
                        rs.getString("tgl_cppt"),
                        rs.getString("nip_dpjp"),
                        rs.getString("waktu_simpan"),
                        rs.getString("cek_jam"),
                        rs.getString("jam_cppt"),
                        rs.getString("jenis_ppa"),
                        rs.getString("nmppa"),
                        rs.getString("jenis_bagian"),
                        rs.getString("nip_ppa"),                        
                        rs.getString("serah_terima_cppt"),
                        rs.getString("nmkonsulen"),
                        rs.getString("nip_konsulen"),
                        rs.getString("petugasSerah"),
                        rs.getString("petugasTerima"),
                        rs.getString("nip_petugas_serah"),
                        rs.getString("nip_petugas_terima"),
                        rs.getString("cppt_shift"),
                        rs.getString("jam_serah_terima")
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
        tglCppt.setDate(new Date());
        ChkJam.setSelected(false);
        cmbJam.setSelectedIndex(0);
        cmbMnt.setSelectedIndex(0);
        cmbDtk.setSelectedIndex(0);
        cmbJam.setEnabled(false);
        cmbMnt.setEnabled(false);
        cmbDtk.setEnabled(false);
        THasil.setText("");
        TInstruksi.setText("");
        kddpjp.setText("");
        nmdpjp.setText("");
        cmbVerifikasi.setSelectedIndex(0);
        cmbPPA.setSelectedIndex(0);
        nmppa.setText("-");
        nipppa = "-";
        cmbBagian.setSelectedIndex(0);
        cmbSertim.setSelectedIndex(0);
        nipkonsulen = "-";
        nmKonsulen.setText("-");        
        nipSerah.setText("-");
        nmSerah.setText("-");
        BtnSerah.setEnabled(false);
        nipTerima.setText("-");
        nmTerima.setText("-");
        BtnTerima.setEnabled(false);
        ChkSamaPPA.setSelected(false);
        ChkSamaPPA.setEnabled(false);
        cmbSift.setSelectedIndex(0);
        cmbJam1.setSelectedIndex(0);
        cmbMnt1.setSelectedIndex(0);
        cmbDtk1.setSelectedIndex(0);
        MnCeklisFarmasi.setEnabled(false);
        MnPemberianDiet.setEnabled(false);
    }

    private void getData() {
        statusOK = "";
        cekjam = "";
        nipppa = "";
        nipkonsulen = "";
        
        if (tbCPPT.getSelectedRow() != -1) {
            TNoRw.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 0).toString());
            TNoRm.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 1).toString());
            TPasien.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 2).toString());
            Valid.SetTgl(tglCppt, tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString());
            THasil.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString());
            TInstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 7).toString());
            kddpjp.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 12).toString());
            nmdpjp.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString());
            cmbVerifikasi.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString());
            statusOK = tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString();
            cekjam = tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 14).toString();
            cmbJam.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 15).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 15).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 15).toString().substring(6, 8));
            cmbPPA.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 16).toString());            
            nmppa.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 17).toString());
            cmbBagian.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 18).toString());
            nipppa = tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 19).toString();
            cmbSertim.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 20).toString());                      
            nmKonsulen.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 21).toString());
            nipkonsulen = tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 22).toString();
            nmSerah.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 23).toString());
            nmTerima.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 24).toString());
            nipSerah.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 25).toString());
            nipTerima.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 26).toString());
            cmbSift.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 27).toString());            
            cmbJam1.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 28).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 28).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 28).toString().substring(6, 8));
            dataCek();
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getcppt());
       BtnHapus.setEnabled(akses.getcppt());
       BtnEdit.setEnabled(akses.getcppt());
    }
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode1);
        try {
            if (pilihan == 1) {
                pps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, c.* from cppt c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.hasil_pemeriksaan<>'' and p.no_rkm_medis like ? OR "
                        + "c.hasil_pemeriksaan<>'' and p.nm_pasien like ? OR "
                        + "c.hasil_pemeriksaan<>'' and c.hasil_pemeriksaan like ? ORDER BY c.waktu_simpan desc limit 20");
            } else if (pilihan == 2) {
                pps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, c.* from cppt c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.instruksi_nakes<>'' and p.no_rkm_medis like ? OR "
                        + "c.instruksi_nakes<>'' and p.nm_pasien like ? OR "
                        + "c.instruksi_nakes<>'' and c.instruksi_nakes like ? ORDER BY c.waktu_simpan desc limit 20");
            } 
            try {
                if (pilihan == 1) {
                    pps1.setString(1, "%" + TCari1.getText() + "%");
                    pps1.setString(2, "%" + TCari1.getText() + "%");
                    pps1.setString(3, "%" + TCari1.getText() + "%");
                    rrs1 = pps1.executeQuery();
                    while (rrs1.next()) {
                        tabMode1.addRow(new String[]{
                            rrs1.getString("no_rkm_medis"),
                            rrs1.getString("nm_pasien"),
                            rrs1.getString("hasil_pemeriksaan")
                        });
                    }
                } else if (pilihan == 2) {
                    pps2.setString(1, "%" + TCari1.getText() + "%");
                    pps2.setString(2, "%" + TCari1.getText() + "%");
                    pps2.setString(3, "%" + TCari1.getText() + "%");
                    rrs2 = pps2.executeQuery();
                    while (rrs2.next()) {
                        tabMode1.addRow(new String[]{
                            rrs2.getString("no_rkm_medis"),
                            rrs2.getString("nm_pasien"),
                            rrs2.getString("instruksi_nakes")
                        });
                    }
                } 
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rrs1 != null) {
                    rrs1.close();
                } else if (rrs2 != null) {
                    rrs2.close();
                } 

                if (pps1 != null) {
                    pps1.close();
                } else if (pps2 != null) {
                    pps2.close();
                } 
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copas() {
        if (pilihan == 1) {
            THasil.setText(Ttemplate.getText());
        } else if (pilihan == 2) {
            TInstruksi.setText(Ttemplate.getText());
        }
    }
    
    public void setData(String norw, String norm, String nmpasien, String sttsrawat) {
        TNoRw.setText(norw);
        TNoRm.setText(norm);
        TPasien.setText(nmpasien);
        TCari.setText(norw); 
        status = sttsrawat;
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'"));
        DTPCari2.setDate(new Date());
        isRawat();
        
        if (sttsrawat.equals("IGD (Ralan)") || sttsrawat.equals("IGD (Ranap)")) {
            cmbRawat.setSelectedIndex(0);
            cmbPPA.setSelectedIndex(0);
            cmbPPA.setEnabled(false);
            cmbSertim.setSelectedIndex(0);
            cmbSertim.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
            nipppa = "-";
            nmppa.setText("-");
            nmppa.setEnabled(false);
            BtnPPA.setEnabled(false);
            cmbSift.setEnabled(false);
            cmbSiftCppt.setEnabled(false);

            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + TNoRw.getText() + "' and status='Ralan'") > 0) {
                TabCPPT.setSelectedIndex(1);
                tampil();
            } else if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + TNoRw.getText() + "' and status='Ralan'") == 0) {
                TabCPPT.setSelectedIndex(0);
            }
        } else if (sttsrawat.equals("ranap")) {
            cmbRawat.setSelectedIndex(1);
            cmbPPA.setSelectedIndex(0);
            cmbPPA.setEnabled(true);
            cmbSertim.setSelectedIndex(0);
            cmbSertim.setEnabled(true);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
            nipppa = "-";
            nmppa.setText("-");
            nmppa.setEnabled(true);
            BtnPPA.setEnabled(true);
            cmbSift.setEnabled(true);
            cmbSiftCppt.setEnabled(true);
            
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + norw + "' and status='ranap'") > 0) {
                cmbSiftCppt.setSelectedIndex(4);
                TabCPPT.setSelectedIndex(1);
                tampil();
            } else if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + norw + "' and status='ranap'") == 0) {
                cmbSiftCppt.setSelectedIndex(0);
                TabCPPT.setSelectedIndex(0);
            }
            
            if (Sequel.cariInteger("select count(-1) from dpjp_ranap where no_rawat='" + norw + "'") > 0) {
                kddpjp.setText(Sequel.cariIsi("select kd_dokter from dpjp_ranap where no_rawat='" + norw + "'"));
                nmdpjp.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddpjp.getText() + "'"));
            } else {
                kddpjp.setText("-");
                nmdpjp.setText("-");
            }
        } else {
            cmbRawat.setSelectedIndex(2);
            cmbPPA.setSelectedIndex(0);
            cmbPPA.setEnabled(true);
            cmbSertim.setSelectedIndex(0);
            cmbSertim.setEnabled(true);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
            nipppa = "-";
            nmppa.setText("-");
            nmppa.setEnabled(true);
            BtnPPA.setEnabled(true);
            cmbSift.setEnabled(true);
            cmbSiftCppt.setEnabled(false);
        }
    }
    
    private void dataCek() {
        if (cekjam.equals("ya")) {
            ChkJam.setSelected(true);
            cmbJam.setEnabled(true);
            cmbMnt.setEnabled(true);
            cmbDtk.setEnabled(true);
        } else {
            ChkJam.setSelected(false);
            cmbJam.setSelectedIndex(0);
            cmbMnt.setSelectedIndex(0);
            cmbDtk.setSelectedIndex(0);

            cmbJam.setEnabled(false);
            cmbMnt.setEnabled(false);
            cmbDtk.setEnabled(false);
        }

        if (cmbSertim.getSelectedIndex() == 1) {
            cmbSertim.setEnabled(true);
            BtnSerah.setEnabled(true);
            BtnTerima.setEnabled(true);
            ChkSamaPPA.setSelected(false);
            ChkSamaPPA.setEnabled(true);
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
        } else {
            cmbSertim.setEnabled(false);
            BtnSerah.setEnabled(false);
            BtnTerima.setEnabled(false);
            ChkSamaPPA.setSelected(false);
            ChkSamaPPA.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
        }
        
//        if (cmbBagian.getSelectedIndex() == 2) {
//            BtnKonsulen.setEnabled(true);
//            BtnPPA.setEnabled(false);
//            cmbPPA.setEnabled(false);
//        } else if (cmbBagian.getSelectedIndex() == 3) {            
//            BtnKonsulen.setEnabled(false);
//            BtnPPA.setEnabled(true);
//            cmbPPA.setEnabled(true);
//        } else {
//            BtnKonsulen.setEnabled(false);
//            BtnPPA.setEnabled(false);
//            cmbPPA.setEnabled(false);
//        }
        
        //cek shift ranap
        if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
            cmbSift.setEnabled(false);
            cmbSiftCppt.setEnabled(false);
        } else if (status.equals("ranap")) {
            cmbSift.setEnabled(true);
            cmbSiftCppt.setEnabled(true);
        } else {
            cmbSift.setEnabled(true);
            cmbSiftCppt.setEnabled(true);
        }
    }
    
    private void cetakCPPTranap() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("logo", Sequel.cariGambar("select logo from setting"));

        if (cmbTanggal.getSelectedIndex() == 0 && cmbSiftPetugas.getSelectedIndex() != 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (Rawat Inap), Shift : " + cmbSiftPetugas.getSelectedItem().toString());
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' AND STATUS='Ranap' "
                    + "and cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Rawat Inap ]::",
                        "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, IF(c.cek_jam='ya',concat(date_format(c.tgl_cppt,'%d-%m-%Y'),', ',date_format(c.jam_cppt,'%H:%i')), "
                        + "date_format(c.tgl_cppt,'%d-%m-%Y')) tglcppt, c.bagian, "
                        + "ifnull(if(c.jenis_bagian='' or c.jenis_bagian='-','-',if(c.jenis_bagian='Dokter IGD' or c.jenis_bagian='DPJP',c.jenis_bagian,concat(c.jenis_bagian,' : ',c.jenis_ppa))),'-') bagian_cppt, "
                        + "c.hasil_pemeriksaan, "
                        + "concat(c.instruksi_nakes,if(c.jenis_bagian='DPJP',concat('\n\n(',pg1.nama,')'),if(c.jenis_bagian='PPA',concat('\n\n(',pg2.nama,')'),''))) instruksi_nakes, "
                        + "concat('(', c.verifikasi,') - ',pg.nama) verif, "
                        + "if(c.serah_terima_cppt='ya',concat('\n\nTgl. ',date_format(c.tgl_cppt,'%d-%m-%Y'),', Jam : ',ifnull(date_format(c.jam_serah_terima,'%H:%i'),'00:00'),'\n','Menyerahkan :\n',pg3.nama),'') ptgsSerah, "
                        + "if(c.serah_terima_cppt='ya',concat('Menerima :\n',pg4.nama),'') ptgsTerima "
                        + "FROM cppt c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp LEFT JOIN pegawai pg1 on pg1.nik=c.nip_konsulen  LEFT JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                        + "LEFT JOIN pegawai pg3 on pg3.nik=c.nip_petugas_serah LEFT JOIN pegawai pg4 on pg4.nik=c.nip_petugas_terima "
                        + "WHERE c.no_rawat = '" + TNoRw.getText() + "' AND c.STATUS='Ranap' "
                        + "and cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%' ORDER BY c.tgl_cppt, c.jam_cppt", param);
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }

            //periode    
        } else if (cmbTanggal.getSelectedIndex() == 1 && cmbSiftPetugas.getSelectedIndex() != 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (Rawat Inap)\n"
                    + "Periode Tgl. " + tglA.getSelectedItem() + " S.D Tgl. " + tglB.getSelectedItem() + ", Shift : " + cmbSiftPetugas.getSelectedItem().toString());
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' AND STATUS='Ranap' "
                    + "and tgl_cppt between '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' and '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "' "
                    + "and cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Periode Rawat Inap ]::",
                        "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, IF(c.cek_jam='ya',concat(date_format(c.tgl_cppt,'%d-%m-%Y'),', ',date_format(c.jam_cppt,'%H:%i')), "
                        + "date_format(c.tgl_cppt,'%d-%m-%Y')) tglcppt, c.bagian, "
                        + "ifnull(if(c.jenis_bagian='' or c.jenis_bagian='-','-',if(c.jenis_bagian='Dokter IGD' or c.jenis_bagian='DPJP',c.jenis_bagian,concat(c.jenis_bagian,' : ',c.jenis_ppa))),'-') bagian_cppt, "
                        + "c.hasil_pemeriksaan, "
                        + "concat(c.instruksi_nakes,if(c.jenis_bagian='DPJP',concat('\n\n(',pg1.nama,')'),if(c.jenis_bagian='PPA',concat('\n\n(',pg2.nama,')'),''))) instruksi_nakes, "
                        + "concat('(', c.verifikasi,') - ',pg.nama) verif, "
                        + "if(c.serah_terima_cppt='ya',concat('\n\nTgl. ',date_format(c.tgl_cppt,'%d-%m-%Y'),', Jam : ',ifnull(date_format(c.jam_serah_terima,'%H:%i'),'00:00'),'\n','Menyerahkan :\n',pg3.nama),'') ptgsSerah, "
                        + "if(c.serah_terima_cppt='ya',concat('Menerima :\n',pg4.nama),'') ptgsTerima "
                        + "FROM cppt c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp LEFT JOIN pegawai pg1 on pg1.nik=c.nip_konsulen  LEFT JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                        + "LEFT JOIN pegawai pg3 on pg3.nik=c.nip_petugas_serah LEFT JOIN pegawai pg4 on pg4.nik=c.nip_petugas_terima "
                        + "WHERE c.no_rawat = '" + TNoRw.getText() + "' AND c.STATUS='Ranap' "
                        + "and tgl_cppt between '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' and '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "' "
                        + "and cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%' ORDER BY c.tgl_cppt, c.jam_cppt", param);
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }

            //pertanggal
        } else if (cmbTanggal.getSelectedIndex() == 2 && cmbSiftPetugas.getSelectedIndex() != 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (Rawat Inap)\n"
                    + "Tgl. " + tglA.getSelectedItem() + ", Shift : " + cmbSiftPetugas.getSelectedItem().toString());
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' AND STATUS='Ranap' "
                    + "and tgl_cppt='" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' and cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT PerTanggal Rawat Inap ]::",
                        "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, IF(c.cek_jam='ya',concat(date_format(c.tgl_cppt,'%d-%m-%Y'),', ',date_format(c.jam_cppt,'%H:%i')), "
                        + "date_format(c.tgl_cppt,'%d-%m-%Y')) tglcppt, c.bagian, "
                        + "ifnull(if(c.jenis_bagian='' or c.jenis_bagian='-','-',if(c.jenis_bagian='Dokter IGD' or c.jenis_bagian='DPJP',c.jenis_bagian,concat(c.jenis_bagian,' : ',c.jenis_ppa))),'-') bagian_cppt, "
                        + "c.hasil_pemeriksaan, "
                        + "concat(c.instruksi_nakes,if(c.jenis_bagian='DPJP',concat('\n\n(',pg1.nama,')'),if(c.jenis_bagian='PPA',concat('\n\n(',pg2.nama,')'),''))) instruksi_nakes, "
                        + "concat('(', c.verifikasi,') - ',pg.nama) verif, "
                        + "if(c.serah_terima_cppt='ya',concat('\n\nTgl. ',date_format(c.tgl_cppt,'%d-%m-%Y'),', Jam : ',ifnull(date_format(c.jam_serah_terima,'%H:%i'),'00:00'),'\n','Menyerahkan :\n',pg3.nama),'') ptgsSerah, "
                        + "if(c.serah_terima_cppt='ya',concat('Menerima :\n',pg4.nama),'') ptgsTerima "
                        + "FROM cppt c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp LEFT JOIN pegawai pg1 on pg1.nik=c.nip_konsulen  LEFT JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                        + "LEFT JOIN pegawai pg3 on pg3.nik=c.nip_petugas_serah LEFT JOIN pegawai pg4 on pg4.nik=c.nip_petugas_terima "
                        + "WHERE c.no_rawat = '" + TNoRw.getText() + "' AND c.STATUS='Ranap' "
                        + "and tgl_cppt='" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' and cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%' "
                        + "ORDER BY c.tgl_cppt, c.jam_cppt", param);
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }
            
            //-----------------------------------------------------------------------------------------------------------
        } else if (cmbTanggal.getSelectedIndex() == 0 && cmbSiftPetugas.getSelectedIndex() == 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (Rawat Inap), Semua Shift Petugas");
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' AND STATUS='Ranap'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Rawat Inap ]::",
                        "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, IF(c.cek_jam='ya',concat(date_format(c.tgl_cppt,'%d-%m-%Y'),', ',date_format(c.jam_cppt,'%H:%i')), "
                        + "date_format(c.tgl_cppt,'%d-%m-%Y')) tglcppt, c.bagian, "
                        + "ifnull(if(c.jenis_bagian='' or c.jenis_bagian='-','-',if(c.jenis_bagian='Dokter IGD' or c.jenis_bagian='DPJP',c.jenis_bagian,concat(c.jenis_bagian,' : ',c.jenis_ppa))),'-') bagian_cppt, "
                        + "c.hasil_pemeriksaan, "
                        + "concat(c.instruksi_nakes,if(c.jenis_bagian='DPJP',concat('\n\n(',pg1.nama,')'),if(c.jenis_bagian='PPA',concat('\n\n(',pg2.nama,')'),''))) instruksi_nakes, "
                        + "concat('(', c.verifikasi,') - ',pg.nama) verif, "
                        + "if(c.serah_terima_cppt='ya',concat('\n\nTgl. ',date_format(c.tgl_cppt,'%d-%m-%Y'),', Jam : ',ifnull(date_format(c.jam_serah_terima,'%H:%i'),'00:00'),'\n','Menyerahkan :\n',pg3.nama),'') ptgsSerah, "
                        + "if(c.serah_terima_cppt='ya',concat('Menerima :\n',pg4.nama),'') ptgsTerima "
                        + "FROM cppt c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp LEFT JOIN pegawai pg1 on pg1.nik=c.nip_konsulen  LEFT JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                        + "LEFT JOIN pegawai pg3 on pg3.nik=c.nip_petugas_serah LEFT JOIN pegawai pg4 on pg4.nik=c.nip_petugas_terima "
                        + "WHERE c.no_rawat = '" + TNoRw.getText() + "' AND c.STATUS='Ranap' ORDER BY c.tgl_cppt, c.jam_cppt", param);
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }

            //periode    
        } else if (cmbTanggal.getSelectedIndex() == 1 && cmbSiftPetugas.getSelectedIndex() == 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (Rawat Inap)\n"
                    + "Periode Tgl. " + tglA.getSelectedItem() + " S.D Tgl. " + tglB.getSelectedItem() + ", Semua Shift Petugas");
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' AND STATUS='Ranap' "
                    + "and tgl_cppt between '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' and '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Periode Rawat Inap ]::",
                        "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, IF(c.cek_jam='ya',concat(date_format(c.tgl_cppt,'%d-%m-%Y'),', ',date_format(c.jam_cppt,'%H:%i')), "
                        + "date_format(c.tgl_cppt,'%d-%m-%Y')) tglcppt, c.bagian, "
                        + "ifnull(if(c.jenis_bagian='' or c.jenis_bagian='-','-',if(c.jenis_bagian='Dokter IGD' or c.jenis_bagian='DPJP',c.jenis_bagian,concat(c.jenis_bagian,' : ',c.jenis_ppa))),'-') bagian_cppt, "
                        + "c.hasil_pemeriksaan, "
                        + "concat(c.instruksi_nakes,if(c.jenis_bagian='DPJP',concat('\n\n(',pg1.nama,')'),if(c.jenis_bagian='PPA',concat('\n\n(',pg2.nama,')'),''))) instruksi_nakes, "
                        + "concat('(', c.verifikasi,') - ',pg.nama) verif, "
                        + "if(c.serah_terima_cppt='ya',concat('\n\nTgl. ',date_format(c.tgl_cppt,'%d-%m-%Y'),', Jam : ',ifnull(date_format(c.jam_serah_terima,'%H:%i'),'00:00'),'\n','Menyerahkan :\n',pg3.nama),'') ptgsSerah, "
                        + "if(c.serah_terima_cppt='ya',concat('Menerima :\n',pg4.nama),'') ptgsTerima "
                        + "FROM cppt c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp LEFT JOIN pegawai pg1 on pg1.nik=c.nip_konsulen  LEFT JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                        + "LEFT JOIN pegawai pg3 on pg3.nik=c.nip_petugas_serah LEFT JOIN pegawai pg4 on pg4.nik=c.nip_petugas_terima "
                        + "WHERE c.no_rawat = '" + TNoRw.getText() + "' AND c.STATUS='Ranap' "
                        + "and tgl_cppt between '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' and '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "' ORDER BY c.tgl_cppt, c.jam_cppt", param);
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }

            //pertanggal
        } else if (cmbTanggal.getSelectedIndex() == 2 && cmbSiftPetugas.getSelectedIndex() == 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (Rawat Inap)\n"
                    + "Tgl. " + tglA.getSelectedItem() + ", Semua Shift Petugas");
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' AND STATUS='Ranap' "
                    + "and tgl_cppt='" + Valid.SetTgl(tglA.getSelectedItem() + "") + "'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT PerTanggal Rawat Inap ]::",
                        "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, IF(c.cek_jam='ya',concat(date_format(c.tgl_cppt,'%d-%m-%Y'),', ',date_format(c.jam_cppt,'%H:%i')), "
                        + "date_format(c.tgl_cppt,'%d-%m-%Y')) tglcppt, c.bagian, "
                        + "ifnull(if(c.jenis_bagian='' or c.jenis_bagian='-','-',if(c.jenis_bagian='Dokter IGD' or c.jenis_bagian='DPJP',c.jenis_bagian,concat(c.jenis_bagian,' : ',c.jenis_ppa))),'-') bagian_cppt, "
                        + "c.hasil_pemeriksaan, "
                        + "concat(c.instruksi_nakes,if(c.jenis_bagian='DPJP',concat('\n\n(',pg1.nama,')'),if(c.jenis_bagian='PPA',concat('\n\n(',pg2.nama,')'),''))) instruksi_nakes, "
                        + "concat('(', c.verifikasi,') - ',pg.nama) verif, "
                        + "if(c.serah_terima_cppt='ya',concat('\n\nTgl. ',date_format(c.tgl_cppt,'%d-%m-%Y'),', Jam : ',ifnull(date_format(c.jam_serah_terima,'%H:%i'),'00:00'),'\n','Menyerahkan :\n',pg3.nama),'') ptgsSerah, "
                        + "if(c.serah_terima_cppt='ya',concat('Menerima :\n',pg4.nama),'') ptgsTerima "
                        + "FROM cppt c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp LEFT JOIN pegawai pg1 on pg1.nik=c.nip_konsulen  LEFT JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                        + "LEFT JOIN pegawai pg3 on pg3.nik=c.nip_petugas_serah LEFT JOIN pegawai pg4 on pg4.nik=c.nip_petugas_terima "
                        + "WHERE c.no_rawat = '" + TNoRw.getText() + "' AND c.STATUS='Ranap' and tgl_cppt='" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' "
                        + "ORDER BY c.tgl_cppt, c.jam_cppt", param);
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }
        }
    }

    private void cetakCPPTralan() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (IGD)");
        if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' AND STATUS='Ralan'") == 0) {
            JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
        } else {
            Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT IGD ]::",
                    "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, IF(c.cek_jam='ya',concat(date_format(c.tgl_cppt,'%d-%m-%Y'),', ',date_format(c.jam_cppt,'%H:%i')), "
                    + "date_format(c.tgl_cppt,'%d-%m-%Y')) tglcppt, c.bagian, "
                    + "ifnull(if(c.jenis_bagian='' or c.jenis_bagian='-','-',if(c.jenis_bagian='Dokter IGD' or c.jenis_bagian='DPJP',c.jenis_bagian,concat(c.jenis_bagian,' : ',c.jenis_ppa))),'-') bagian_cppt, "
                    + "c.hasil_pemeriksaan, "
                    + "concat(c.instruksi_nakes,if(c.jenis_bagian='DPJP',concat('\n\n(',pg1.nama,')'),if(c.jenis_bagian='PPA',concat('\n\n(',pg2.nama,')'),''))) instruksi_nakes, "
                    + "concat('(', c.verifikasi,') - ',pg.nama) verif, "
                    + "if(c.serah_terima_cppt='ya',concat('\n\nTgl. ',date_format(c.tgl_cppt,'%d-%m-%Y'),', Jam : ',ifnull(date_format(c.jam_serah_terima,'%H:%i'),'00:00'),'\n','Menyerahkan :\n',pg3.nama),'') ptgsSerah, "
                    + "if(c.serah_terima_cppt='ya',concat('Menerima :\n',pg4.nama),'') ptgsTerima "
                    + "FROM cppt c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp LEFT JOIN pegawai pg1 on pg1.nik=c.nip_konsulen  LEFT JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                    + "LEFT JOIN pegawai pg3 on pg3.nik=c.nip_petugas_serah LEFT JOIN pegawai pg4 on pg4.nik=c.nip_petugas_terima "
                    + "WHERE c.no_rawat = '" + TNoRw.getText() + "' AND c.STATUS='Ralan' ORDER BY c.tgl_cppt, c.jam_cppt", param);
            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
            BtnCloseIn6ActionPerformed(null);
        }
    }
    
    private void cetakCPPTsemua() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("logo", Sequel.cariGambar("select logo from setting"));

        if (cmbTanggal.getSelectedIndex() == 0 && cmbSiftPetugas.getSelectedIndex() != 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI Shift : " + cmbSiftPetugas.getSelectedItem().toString());
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' "
                    + "and cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Pasien ]::",
                        "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, IF(c.cek_jam='ya',concat(date_format(c.tgl_cppt,'%d-%m-%Y'),', ',date_format(c.jam_cppt,'%H:%i')), "
                        + "date_format(c.tgl_cppt,'%d-%m-%Y')) tglcppt, c.bagian, "
                        + "ifnull(if(c.jenis_bagian='' or c.jenis_bagian='-','-',if(c.jenis_bagian='Dokter IGD' or c.jenis_bagian='DPJP',c.jenis_bagian,concat(c.jenis_bagian,' : ',c.jenis_ppa))),'-') bagian_cppt, "
                        + "c.hasil_pemeriksaan, "
                        + "concat(c.instruksi_nakes,if(c.jenis_bagian='DPJP',concat('\n\n(',pg1.nama,')'),if(c.jenis_bagian='PPA',concat('\n\n(',pg2.nama,')'),''))) instruksi_nakes, "
                        + "concat('(', c.verifikasi,') - ',pg.nama) verif, "
                        + "if(c.serah_terima_cppt='ya',concat('\n\nTgl. ',date_format(c.tgl_cppt,'%d-%m-%Y'),', Jam : ',ifnull(date_format(c.jam_serah_terima,'%H:%i'),'00:00'),'\n','Menyerahkan :\n',pg3.nama),'') ptgsSerah, "
                        + "if(c.serah_terima_cppt='ya',concat('Menerima :\n',pg4.nama),'') ptgsTerima "
                        + "FROM cppt c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp LEFT JOIN pegawai pg1 on pg1.nik=c.nip_konsulen  LEFT JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                        + "LEFT JOIN pegawai pg3 on pg3.nik=c.nip_petugas_serah LEFT JOIN pegawai pg4 on pg4.nik=c.nip_petugas_terima "
                        + "WHERE c.no_rawat = '" + TNoRw.getText() + "' and cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%' "
                        + "ORDER BY c.tgl_cppt, c.jam_cppt", param);
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }

            //periode
        } else if (cmbTanggal.getSelectedIndex() == 1 && cmbSiftPetugas.getSelectedIndex() != 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI\n"
                    + "Periode Tgl. " + tglA.getSelectedItem() + " S.D Tgl. " + tglB.getSelectedItem() + ", Shift : " + cmbSiftPetugas.getSelectedItem().toString());
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' "
                    + "and tgl_cppt between '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' and '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "' "
                    + "and cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Pasien ]::",
                        "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, IF(c.cek_jam='ya',concat(date_format(c.tgl_cppt,'%d-%m-%Y'),', ',date_format(c.jam_cppt,'%H:%i')), "
                        + "date_format(c.tgl_cppt,'%d-%m-%Y')) tglcppt, c.bagian, "
                        + "ifnull(if(c.jenis_bagian='' or c.jenis_bagian='-','-',if(c.jenis_bagian='Dokter IGD' or c.jenis_bagian='DPJP',c.jenis_bagian,concat(c.jenis_bagian,' : ',c.jenis_ppa))),'-') bagian_cppt, "
                        + "c.hasil_pemeriksaan, "
                        + "concat(c.instruksi_nakes,if(c.jenis_bagian='DPJP',concat('\n\n(',pg1.nama,')'),if(c.jenis_bagian='PPA',concat('\n\n(',pg2.nama,')'),''))) instruksi_nakes, "
                        + "concat('(', c.verifikasi,') - ',pg.nama) verif, "
                        + "if(c.serah_terima_cppt='ya',concat('\n\nTgl. ',date_format(c.tgl_cppt,'%d-%m-%Y'),', Jam : ',ifnull(date_format(c.jam_serah_terima,'%H:%i'),'00:00'),'\n','Menyerahkan :\n',pg3.nama),'') ptgsSerah, "
                        + "if(c.serah_terima_cppt='ya',concat('Menerima :\n',pg4.nama),'') ptgsTerima "
                        + "FROM cppt c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp LEFT JOIN pegawai pg1 on pg1.nik=c.nip_konsulen  LEFT JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                        + "LEFT JOIN pegawai pg3 on pg3.nik=c.nip_petugas_serah LEFT JOIN pegawai pg4 on pg4.nik=c.nip_petugas_terima "
                        + "WHERE c.no_rawat = '" + TNoRw.getText() + "' "
                        + "and tgl_cppt between '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' and '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "' "
                        + "and cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%' ORDER BY c.tgl_cppt, c.jam_cppt", param);
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }

        //pertanggal
        } else if (cmbTanggal.getSelectedIndex() == 2 && cmbSiftPetugas.getSelectedIndex() != 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI\n"
                    + "Tgl. " + tglA.getSelectedItem() + ", Shift : " + cmbSiftPetugas.getSelectedItem().toString());
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' and tgl_cppt='" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' "
                    + "and cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Pasien ]::",
                        "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, IF(c.cek_jam='ya',concat(date_format(c.tgl_cppt,'%d-%m-%Y'),', ',date_format(c.jam_cppt,'%H:%i')), "
                        + "date_format(c.tgl_cppt,'%d-%m-%Y')) tglcppt, c.bagian, "
                        + "ifnull(if(c.jenis_bagian='' or c.jenis_bagian='-','-',if(c.jenis_bagian='Dokter IGD' or c.jenis_bagian='DPJP',c.jenis_bagian,concat(c.jenis_bagian,' : ',c.jenis_ppa))),'-') bagian_cppt, "
                        + "c.hasil_pemeriksaan, "
                        + "concat(c.instruksi_nakes,if(c.jenis_bagian='DPJP',concat('\n\n(',pg1.nama,')'),if(c.jenis_bagian='PPA',concat('\n\n(',pg2.nama,')'),''))) instruksi_nakes, "
                        + "concat('(', c.verifikasi,') - ',pg.nama) verif, "
                        + "if(c.serah_terima_cppt='ya',concat('\n\nTgl. ',date_format(c.tgl_cppt,'%d-%m-%Y'),', Jam : ',ifnull(date_format(c.jam_serah_terima,'%H:%i'),'00:00'),'\n','Menyerahkan :\n',pg3.nama),'') ptgsSerah, "
                        + "if(c.serah_terima_cppt='ya',concat('Menerima :\n',pg4.nama),'') ptgsTerima "
                        + "FROM cppt c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp LEFT JOIN pegawai pg1 on pg1.nik=c.nip_konsulen  LEFT JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                        + "LEFT JOIN pegawai pg3 on pg3.nik=c.nip_petugas_serah LEFT JOIN pegawai pg4 on pg4.nik=c.nip_petugas_terima "
                        + "WHERE c.no_rawat = '" + TNoRw.getText() + "' and tgl_cppt='" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' "
                        + "and cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%' ORDER BY c.tgl_cppt, c.jam_cppt", param);
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }
            
            //-----------------------------------------------------------------------------------------------------------
        }
        if (cmbTanggal.getSelectedIndex() == 0 && cmbSiftPetugas.getSelectedIndex() == 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI Semua Shift Petugas");
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Pasien ]::",
                        "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, IF(c.cek_jam='ya',concat(date_format(c.tgl_cppt,'%d-%m-%Y'),', ',date_format(c.jam_cppt,'%H:%i')), "
                        + "date_format(c.tgl_cppt,'%d-%m-%Y')) tglcppt, c.bagian, "
                        + "ifnull(if(c.jenis_bagian='' or c.jenis_bagian='-','-',if(c.jenis_bagian='Dokter IGD' or c.jenis_bagian='DPJP',c.jenis_bagian,concat(c.jenis_bagian,' : ',c.jenis_ppa))),'-') bagian_cppt, "
                        + "c.hasil_pemeriksaan, "
                        + "concat(c.instruksi_nakes,if(c.jenis_bagian='DPJP',concat('\n\n(',pg1.nama,')'),if(c.jenis_bagian='PPA',concat('\n\n(',pg2.nama,')'),''))) instruksi_nakes, "
                        + "concat('(', c.verifikasi,') - ',pg.nama) verif, "
                        + "if(c.serah_terima_cppt='ya',concat('\n\nTgl. ',date_format(c.tgl_cppt,'%d-%m-%Y'),', Jam : ',ifnull(date_format(c.jam_serah_terima,'%H:%i'),'00:00'),'\n','Menyerahkan :\n',pg3.nama),'') ptgsSerah, "
                        + "if(c.serah_terima_cppt='ya',concat('Menerima :\n',pg4.nama),'') ptgsTerima "
                        + "FROM cppt c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp LEFT JOIN pegawai pg1 on pg1.nik=c.nip_konsulen  LEFT JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                        + "LEFT JOIN pegawai pg3 on pg3.nik=c.nip_petugas_serah LEFT JOIN pegawai pg4 on pg4.nik=c.nip_petugas_terima "
                        + "WHERE c.no_rawat = '" + TNoRw.getText() + "' ORDER BY c.tgl_cppt, c.jam_cppt", param);
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }

        //periode
        } else if (cmbTanggal.getSelectedIndex() == 1 && cmbSiftPetugas.getSelectedIndex() == 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI\n"
                    + "Periode Tgl. " + tglA.getSelectedItem() + " S.D Tgl. " + tglB.getSelectedItem() + ", Semua Shift Petugas");
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' "
                    + "and tgl_cppt between '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' and '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Pasien ]::",
                        "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, IF(c.cek_jam='ya',concat(date_format(c.tgl_cppt,'%d-%m-%Y'),', ',date_format(c.jam_cppt,'%H:%i')), "
                        + "date_format(c.tgl_cppt,'%d-%m-%Y')) tglcppt, c.bagian, "
                        + "ifnull(if(c.jenis_bagian='' or c.jenis_bagian='-','-',if(c.jenis_bagian='Dokter IGD' or c.jenis_bagian='DPJP',c.jenis_bagian,concat(c.jenis_bagian,' : ',c.jenis_ppa))),'-') bagian_cppt, "
                        + "c.hasil_pemeriksaan, "
                        + "concat(c.instruksi_nakes,if(c.jenis_bagian='DPJP',concat('\n\n(',pg1.nama,')'),if(c.jenis_bagian='PPA',concat('\n\n(',pg2.nama,')'),''))) instruksi_nakes, "
                        + "concat('(', c.verifikasi,') - ',pg.nama) verif, "
                        + "if(c.serah_terima_cppt='ya',concat('\n\nTgl. ',date_format(c.tgl_cppt,'%d-%m-%Y'),', Jam : ',ifnull(date_format(c.jam_serah_terima,'%H:%i'),'00:00'),'\n','Menyerahkan :\n',pg3.nama),'') ptgsSerah, "
                        + "if(c.serah_terima_cppt='ya',concat('Menerima :\n',pg4.nama),'') ptgsTerima "
                        + "FROM cppt c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp LEFT JOIN pegawai pg1 on pg1.nik=c.nip_konsulen  LEFT JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                        + "LEFT JOIN pegawai pg3 on pg3.nik=c.nip_petugas_serah LEFT JOIN pegawai pg4 on pg4.nik=c.nip_petugas_terima "
                        + "WHERE c.no_rawat = '" + TNoRw.getText() + "' "
                        + "and tgl_cppt between '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' and '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "' "
                        + "ORDER BY c.tgl_cppt, c.jam_cppt", param);
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }

        //pertanggal
        } else if (cmbTanggal.getSelectedIndex() == 2 && cmbSiftPetugas.getSelectedIndex() == 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI\n"
                    + "Tgl. " + tglA.getSelectedItem() + ", Semua Shift Petugas");
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' "
                    + "and tgl_cppt='" + Valid.SetTgl(tglA.getSelectedItem() + "") + "'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Pasien ]::",
                        "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, IF(c.cek_jam='ya',concat(date_format(c.tgl_cppt,'%d-%m-%Y'),', ',date_format(c.jam_cppt,'%H:%i')), "
                        + "date_format(c.tgl_cppt,'%d-%m-%Y')) tglcppt, c.bagian, "
                        + "ifnull(if(c.jenis_bagian='' or c.jenis_bagian='-','-',if(c.jenis_bagian='Dokter IGD' or c.jenis_bagian='DPJP',c.jenis_bagian,concat(c.jenis_bagian,' : ',c.jenis_ppa))),'-') bagian_cppt, "
                        + "c.hasil_pemeriksaan, "
                        + "concat(c.instruksi_nakes,if(c.jenis_bagian='DPJP',concat('\n\n(',pg1.nama,')'),if(c.jenis_bagian='PPA',concat('\n\n(',pg2.nama,')'),''))) instruksi_nakes, "
                        + "concat('(', c.verifikasi,') - ',pg.nama) verif, "
                        + "if(c.serah_terima_cppt='ya',concat('\n\nTgl. ',date_format(c.tgl_cppt,'%d-%m-%Y'),', Jam : ',ifnull(date_format(c.jam_serah_terima,'%H:%i'),'00:00'),'\n','Menyerahkan :\n',pg3.nama),'') ptgsSerah, "
                        + "if(c.serah_terima_cppt='ya',concat('Menerima :\n',pg4.nama),'') ptgsTerima "
                        + "FROM cppt c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp LEFT JOIN pegawai pg1 on pg1.nik=c.nip_konsulen  LEFT JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                        + "LEFT JOIN pegawai pg3 on pg3.nik=c.nip_petugas_serah LEFT JOIN pegawai pg4 on pg4.nik=c.nip_petugas_terima "
                        + "WHERE c.no_rawat = '" + TNoRw.getText() + "' and tgl_cppt='" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' "
                        + "ORDER BY c.tgl_cppt, c.jam_cppt", param);
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }
        }
    }
    
    public void setUtama() {
        cmbRawat.setSelectedIndex(2);
        cmbSiftCppt.setSelectedIndex(0);
        cmbSiftCppt.setEnabled(true);
    }
    
    private void getDiet() {
        waktuSimpanDiet = "";
        if (tbDiet.getSelectedRow() != -1) {
            kddiet.setText(tbDiet.getValueAt(tbDiet.getSelectedRow(), 0).toString());
            nmdiet.setText(tbDiet.getValueAt(tbDiet.getSelectedRow(), 1).toString());
            jnsMakanan.setText(tbDiet.getValueAt(tbDiet.getSelectedRow(), 2).toString());
            waktuSimpanDiet = tbDiet.getValueAt(tbDiet.getSelectedRow(), 4).toString();
        }
    }
    
    private void simpanDietINAP() {
        if ((cekDietPagi.isSelected() == false) && (cekDietSiang.isSelected() == false) && (cekDietSore.isSelected() == false)) {
            JOptionPane.showMessageDialog(null, "Waktu diet harus dipilih...!!!!");
        } else {
            dietOK();
            if (cekDietPagi.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Pagi','"
                        + TKd.getText() + "','" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "data");

                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ranap_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Pagi','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 3).toString() + "',"
                            + "'" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "diet pagi");
                }
            }
            if (cekDietSiang.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Siang','"
                        + TKd.getText() + "','" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "data");

                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ranap_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Siang','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 3).toString() + "',"
                            + "'" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "diet siang");
                }
            }
            if (cekDietSore.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Sore','"
                        + TKd.getText() + "','" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "data");

                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ranap_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Sore','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 3).toString() + "',"
                            + "'" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "diet sore");
                }
            }
            dataDietnya();
            BtnKeluar1ActionPerformed(null);
        }  
    }
    
    private void dietOK() {
        dataDiet = "";
        TKd.setText("");
        
        for (i = 0; i < tbDiet.getRowCount(); i++) {
            if (dataDiet.equals("")) {
                dataDiet = tbDiet.getValueAt(i, 1).toString() + " " + tbDiet.getValueAt(i, 3).toString();
            } else {
                dataDiet = dataDiet + " + " + tbDiet.getValueAt(i, 1).toString() + " " + tbDiet.getValueAt(i, 3).toString();
            }
        }
        
        if (Sequel.cariInteger("select count(-1) from diet where nama_diet='" + dataDiet + "'") == 0) {
            Valid.autoNomer("diet", "DB", 4, TKd);
            Sequel.menyimpan("diet", "'" + TKd.getText() + "','" + dataDiet + "','UMUM','1'", "Data diet pasien");
        } else {
            TKd.setText(Sequel.cariIsi("select kd_diet from diet where nama_diet='" + dataDiet + "'"));
        }
    }
    
    private void isRawat() {
        try {
            pspasien = koneksi.prepareStatement("select date_format(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, rp.status_lanjut, rp.kd_poli from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where rp.no_rawat='" + TNoRw.getText() + "'");
            try {
                rspasien = pspasien.executeQuery();
                while (rspasien.next()) {
                    jnsRawat = rspasien.getString("status_lanjut");

                    if (jnsRawat.equals("Ralan")) {
                        kdUnit = rspasien.getString("kd_poli");
                    } else if (jnsRawat.equals("Ranap")) {
                        kdUnit = "";
                        kdUnit = Sequel.cariIsi("select kd_kamar from kamar_inap where no_rawat='" + TNoRw.getText() + "' order by tgl_masuk desc limit 1");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rspasien != null) {
                    rspasien.close();
                }
                if (pspasien != null) {
                    pspasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void simpanDietJALAN() {
        if ((cekDietPagi.isSelected() == false) && (cekDietSiang.isSelected() == false) && (cekDietSore.isSelected() == false)) {
            JOptionPane.showMessageDialog(null, "Waktu diet harus dipilih...!!!!");
        } else {
            dietOK();
            if (cekDietPagi.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet_ralan", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Pagi','"
                        + TKd.getText() + "','" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "data");

                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ralan_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Pagi','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 3).toString() + "',"
                            + "'" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "diet pagi");
                }
            }
            if (cekDietSiang.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet_ralan", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Siang','"
                        + TKd.getText() + "','" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "data");

                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ralan_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Siang','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 3).toString() + "',"
                            + "'" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "diet siang");
                }
            }
            if (cekDietSore.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet_ralan", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Sore','"
                        + TKd.getText() + "','" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "data");

                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ralan_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Sore','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 3).toString() + "',"
                            + "'" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "diet sore");
                }
            }
            dataDietnya();
            BtnKeluar1ActionPerformed(null);
        }
    }
    
    private void dataDietnya() {
        instruksiDiet = "";
        try {
            psdiet = koneksi.prepareStatement("SELECT concat('Waktu ',db.waktu,' : ') wkt, d.nama_diet FROM detail_beri_diet db "
                    + "INNER JOIN diet d on db.kd_diet = d.kd_diet WHERE "
                    + "db.tanggal='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "' AND db.no_rawat LIKE '%" + TNoRw.getText() + "%' "
                    + "ORDER BY db.tanggal, d.nama_diet");
            try {
                rsdiet = psdiet.executeQuery();
                while (rsdiet.next()) {
                    if (instruksiDiet.equals("")) {
                        instruksiDiet = rsdiet.getString("wkt") + " " + rsdiet.getString("nama_diet");
                    } else {
                        instruksiDiet = instruksiDiet + "\n" + rsdiet.getString("wkt") + " " + rsdiet.getString("nama_diet");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        if (TInstruksi.getText().equals("")) {
            TInstruksi.setText("Diet Harian Pasien :\n" + instruksiDiet);
        } else {
            TInstruksi.setText(TInstruksi.getText() + "\n\nDiet Harian Pasien :\n" + instruksiDiet);
        }
    }
}
