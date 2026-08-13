/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgResepObat.java
 *
 * Created on 31 Mei 10, 11:27:40
 */
package keuangan;

import kepegawaian.DlgCariPetugas;
import simrskhanza.*;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Stop;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import laporan.DlgKirimWhatsapp;
import org.apache.poi.hssf.record.PageBreakRecord;

/**
 *
 * @author perpustakaan
 */
public final class DlgPemasukanLain extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Jurnal jur = new Jurnal();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, psakun, psSEP, psTem, psTem1;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rsSEP, rsTem, rsTem1;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgKategoriPemasukan kategori = new DlgKategoriPemasukan(null, false);
    private double total = 0, totTagihan = 0, sdhByr = 0, sisaTagihan = 0, byrKe = 0, stlhByr = 0,
            rumus1 = 0, rumus2 = 0, rumus3 = 0, hasilrumus = 0, hasilmaksimal = 0, tarifAmbulan = 0;
    private int cekData = 0, x = 0;
    private String norw = "", ambulanDibayar = "", tte = "", isi = "", nmpetugas = "";

    /**
     * Creates new form DlgResepObat
     *
     * @param parent
     * @param modal
     */
    public DlgPemasukanLain(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        this.setLocation(8, 1);
        setSize(628, 674);

        Keterangan.setLineWrap(true);
        Keterangan.setWrapStyleWord(true);

        Object[] row = {"No. Transaksi", "Tanggal", "Jam", "Jenis Pemasukan Lain",
            "Nama Petugas", "Nominal/Hrg. Sewa", "Keterangan", "Telah terima dari",
            "no.sep", "no.rm", "no.kartu", "no.rawat", "tgl_msk", "tgl_pulang",
            "ruang_inap", "kode_inacbg", "trf_kls1", "trf_kls2", "trf_kls3",
            "hak_kls", "naik_kls", "lama_rwt", "persen", "rumus", "Total Byr. Selisih Tarif",
            "Nominal Pajak Sewa", "Total Byr. Sewa",
            "Sudah dibayar", "Sisa Tagihan", "Jumlh. Dibayar", "Sisa Stlah Bayar", "jlhnominal"
        };
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.Double.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class,
                java.lang.Double.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.Double.class, java.lang.String.class,
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,                
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
                java.lang.Double.class, java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbPemasukan.setModel(tabMode);
        tbPemasukan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPemasukan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 32; i++) {
            TableColumn column = tbPemasukan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
              column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(450);
            } else if (i == 7) {
                column.setPreferredWidth(200);
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
                column.setPreferredWidth(90);
            } else if (i == 28) {
                column.setPreferredWidth(90);
            } else if (i == 29) {
                column.setPreferredWidth(90);
            } else if (i == 30) {
                column.setPreferredWidth(90);
            } else if (i == 31) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPemasukan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Dilakukan Oleh", "No. Transaksi", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Hapus", "Jam Hapus", "Ket./Alasan Hapus", 
            "waktu_hapus", "Nama Kategori"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwayat.setModel(tabMode1);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 10; i++) {
            TableColumn column = tbRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(220);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setPreferredWidth(220);
            }
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbRiwayat.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbRiwayat.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbRiwayat.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbRiwayat.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbRiwayat.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "Dilakukan Oleh", "No. Transaksi", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Hapus", "Jam Hapus", "Ket./Alasan Hapus", "No. SEP", 
            "Total Tagihan", "Sudah Dibayar", "Sisa Tagihan", "Jml. Bayar", "waktu_hapus"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwayat1.setModel(tabMode2);
        tbRiwayat1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayat1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 14; i++) {
            TableColumn column = tbRiwayat1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(220);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(130);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(80);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRiwayat1.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbRiwayat1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbRiwayat1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbRiwayat1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbRiwayat1.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbRiwayat1.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbRiwayat1.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

        KdPtg.setDocument(new batasInput((byte) 20).getKata(KdPtg));
        pemasukan.setDocument(new batasInput((byte) 15).getOnlyAngka(pemasukan));
        Tjarak.setDocument(new batasInput((byte) 3).getOnlyAngka(Tjarak));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        Tnorm.setDocument(new batasInput((byte) 6).getKata(Tnorm));
        Tnmpasien.setDocument(new batasInput((int) 150).getKata(Tnmpasien));
        TjlhBiayaPihak3.setDocument(new batasInput((int) 10).getOnlyAngka(TjlhBiayaPihak3));
        TketHapus.setDocument(new batasInput((int) 200).getKata(TketHapus));
        
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampil();
                }
            });
        }
        jam();
        autoNomorTransaksi();

        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPemasukanLain")) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        KdPtg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        NmPtg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        pemasukan.requestFocus();
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

        kategori.getTabel().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    kategori.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        kategori.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPemasukanLain")) {
                    if (kategori.getTabel().getSelectedRow() != -1) {
                        KdKategori.setText(kategori.getTabel().getValueAt(kategori.getTabel().getSelectedRow(), 0).toString());
                        NmKategori.setText(kategori.getTabel().getValueAt(kategori.getTabel().getSelectedRow(), 1).toString());

                        if (KdKategori.getText().equals("SBPJS")) {
                            WindowSelisihTarif.setSize(813, 688);
                            WindowSelisihTarif.setLocationRelativeTo(internalFrame1);
                            WindowSelisihTarif.setVisible(true);
                            NoSEP.requestFocus();
                            BtnSimpan6.setEnabled(true);
                            BtnPrint.setEnabled(false);
                            label_pajak.setVisible(false);
                            labelSewa.setVisible(false);
                            nominalSewa.setVisible(false);
                            selisihBaru();
                            TnoPanjar.setText("-");
                            TtglPanjar.setText("-");
                            TnominalPanjar.setText("0");

                            if (WindowSelisihTarif.isVisible() == true) {
                                BtnSimpan.setVisible(false);
                                BtnBatal.setVisible(false);
                            } else if (WindowSelisihTarif.isVisible() == false) {
                                BtnSimpan.setVisible(true);
                                BtnBatal.setVisible(true);
                            }

                        } else if (KdKategori.getText().equals("SWKTN")) {
                            persenSewa.setText(Sequel.cariIsi("select pajak_sewa_tempat from set_tarif"));
                            label_pajak.setText("Sudah Ditambahkan Pajak " + persenSewa.getText() + " %");
                            label_pajak.setVisible(true);
                            labelSewa.setVisible(true);
                            nominalSewa.setVisible(true);

                        } else if (KdKategori.getText().equals("AMBLN")) {
                            WindowAmbulan.setSize(609, 347);
                            WindowAmbulan.setLocationRelativeTo(internalFrame1);
                            WindowAmbulan.setVisible(true);
                            label_pajak.setVisible(false);
                            labelSewa.setVisible(false);
                            nominalSewa.setVisible(false);
                            
                            Tnorm.setText("-");
                            Tnmpasien.setText("-");
                            Tjarak.setText("0");
                            TtujuanAlamat.setText("");
                            CmbTarif.setSelectedIndex(0);
                            Ttarif.setText("0");
                            TselisihJrk.setText("0");
                            TtarifSelisihJrk.setText("0");
                            TjlhBayar.setText("0");                            
                            TjlhBiayaPihak3.setText("0");
                            TtotBayar.setText("0");
                            Tnorm.requestFocus();
                        
                        } else if (!KdKategori.getText().equals("SBPJS") || !KdKategori.getText().equals("SWKTN") || !KdKategori.getText().equals("AMBLN")) {
                            pemasukan.requestFocus();
                            label_pajak.setVisible(false);
                            labelSewa.setVisible(false);
                            nominalSewa.setVisible(false);
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

        ChkInput.setSelected(false);
        
        try {
            psakun = koneksi.prepareStatement("select kd_rek,'Akun',kd_rek2,'Kontra Akun' from kategori_pemasukan_lain where kode_kategori=?");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        cetakNota = new javax.swing.JMenu();
        MnTTDcn = new javax.swing.JMenuItem();
        MnTTEcn = new javax.swing.JMenuItem();
        MnKirimKeWaNota = new javax.swing.JMenuItem();
        cetakNotaAmbulan = new javax.swing.JMenu();
        MnTTDcna = new javax.swing.JMenuItem();
        MnTTEcna = new javax.swing.JMenuItem();
        MnKirimKeWaNota1 = new javax.swing.JMenuItem();
        cetakNotaNaikKls = new javax.swing.JMenu();
        MnTTDcnnk = new javax.swing.JMenuItem();
        MnTTEcnnk = new javax.swing.JMenuItem();
        MnKirimKeWaNota2 = new javax.swing.JMenuItem();
        lihatSelisihTarif = new javax.swing.JMenuItem();
        MnDataSampah = new javax.swing.JMenuItem();
        MnLaporan = new javax.swing.JMenu();
        RekapSelisihLunas = new javax.swing.JMenuItem();
        RekapSelisihAngsur = new javax.swing.JMenuItem();
        RekapPemasukanLain = new javax.swing.JMenuItem();
        RekapSewaTempat = new javax.swing.JMenuItem();
        RekapAmbulan = new javax.swing.JMenuItem();
        WindowSelisihTarif = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        internalFrame8 = new widget.InternalFrame();
        jLabel35 = new widget.Label();
        NoSEP = new widget.TextBox();
        jLabel36 = new widget.Label();
        norm = new widget.TextBox();
        nmpasien = new widget.TextBox();
        jLabel37 = new widget.Label();
        nokartu = new widget.TextBox();
        jLabel38 = new widget.Label();
        norawat = new widget.TextBox();
        jLabel39 = new widget.Label();
        tglmsk = new widget.TextBox();
        jLabel40 = new widget.Label();
        tglklr = new widget.TextBox();
        jLabel41 = new widget.Label();
        rginap = new widget.TextBox();
        jLabel43 = new widget.Label();
        kdINACBG = new widget.TextBox();
        deskripsiKD = new widget.TextBox();
        jLabel44 = new widget.Label();
        tarifkls1 = new widget.TextBox();
        jLabel45 = new widget.Label();
        tarifkls2 = new widget.TextBox();
        jLabel46 = new widget.Label();
        tarifkls3 = new widget.TextBox();
        jLabel55 = new widget.Label();
        tarifrc = new widget.TextBox();
        internalFrame9 = new widget.InternalFrame();
        jLabel54 = new widget.Label();
        hakkelas = new widget.TextBox();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        lmrawat = new widget.TextBox();
        jLabel49 = new widget.Label();
        persenSELISIH = new widget.TextBox();
        jLabel50 = new widget.Label();
        labelbyr = new widget.Label();
        Totdibayar = new widget.TextBox();
        Sudahbyr = new widget.TextBox();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        SisaTagihan = new widget.TextBox();
        jlhdibayar = new widget.TextBox();
        jLabel53 = new widget.Label();
        ChkSesuaiTagihan = new widget.CekBox();
        lunas = new widget.Label();
        jLabel56 = new widget.Label();
        Scroll39 = new widget.ScrollPane();
        TKalkulasi = new widget.TextArea();
        jLabel57 = new widget.Label();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        TnoPanjar = new widget.TextBox();
        TtglPanjar = new widget.TextBox();
        TnominalPanjar = new widget.TextBox();
        BtnPanjar = new widget.Button();
        BtnJaminan = new widget.Button();
        cmbNaikKls = new widget.ComboBox();
        internalFrame10 = new widget.InternalFrame();
        BtnSelisihBaru = new widget.Button();
        BtnSimpan6 = new widget.Button();
        BtnPrint = new widget.Button();
        BtnCloseIn6 = new widget.Button();
        WindowAmbulan = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        internalFrame6 = new widget.InternalFrame();
        jLabel18 = new widget.Label();
        Tjarak = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel22 = new widget.Label();
        CmbTarif = new widget.ComboBox();
        jLabel23 = new widget.Label();
        Ttarif = new widget.TextBox();
        jLabel24 = new widget.Label();
        TjlhBayar = new widget.TextBox();
        jLabel25 = new widget.Label();
        TselisihJrk = new widget.TextBox();
        jLabel27 = new widget.Label();
        TtarifSelisihJrk = new widget.TextBox();
        jLabel28 = new widget.Label();
        TtotBayar = new widget.TextBox();
        jLabel30 = new widget.Label();
        jLabel26 = new widget.Label();
        Tnorm = new widget.TextBox();
        Tnmpasien = new widget.TextBox();
        TtujuanAlamat = new widget.TextBox();
        jLabel31 = new widget.Label();
        jLabel32 = new widget.Label();
        TjlhBiayaPihak3 = new widget.TextBox();
        BtnHitung = new widget.Button();
        internalFrame11 = new widget.InternalFrame();
        BtnSimpan4 = new widget.Button();
        BtnCloseIn4 = new widget.Button();
        WindowHapus = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        jLabel33 = new widget.Label();
        TketHapus = new widget.TextBox();
        panelisi4 = new widget.panelisi();
        BtnHapus1 = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        WindowRiwayat = new javax.swing.JDialog();
        internalFrame13 = new widget.InternalFrame();
        internalFrame18 = new widget.InternalFrame();
        internalFrame17 = new widget.InternalFrame();
        jLabel101 = new widget.Label();
        DTPCari3 = new widget.Tanggal();
        jLabel102 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel103 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        internalFrame19 = new widget.InternalFrame();
        BtnAll2 = new widget.Button();
        BtnRestor = new widget.Button();
        BtnCloseIn12 = new widget.Button();
        internalFrame20 = new widget.InternalFrame();
        Scroll6 = new widget.ScrollPane();
        tbRiwayat = new widget.Table();
        Scroll7 = new widget.ScrollPane();
        tbRiwayat1 = new widget.Table();
        cekNoSEP = new widget.TextBox();
        byrSimpan = new widget.TextBox();
        rumusbayar = new widget.TextBox();
        statusSELISIH = new widget.TextBox();
        persenSewa = new widget.TextBox();
        nominalPajakSewa = new widget.TextBox();
        totalbyrsewa = new widget.TextBox();
        cekNoTransaksi = new widget.TextBox();
        bayarKe = new widget.TextBox();
        jumlhBayar = new widget.TextBox();
        belumDibayar = new widget.TextBox();
        statusTran = new widget.TextBox();
        noTranAngsur = new widget.TextBox();
        dataSelisihTarif = new widget.TextBox();
        realcostRS = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPemasukan = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        jLabel29 = new widget.Label();
        tglNota = new widget.Tanggal();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        KdPtg = new widget.TextBox();
        NmPtg = new widget.TextBox();
        jLabel3 = new widget.Label();
        jLabel13 = new widget.Label();
        btnPetugas = new widget.Button();
        KdKategori = new widget.TextBox();
        NmKategori = new widget.TextBox();
        btnKategori = new widget.Button();
        Tanggal = new widget.Tanggal();
        jLabel8 = new widget.Label();
        jLabel11 = new widget.Label();
        pemasukan = new widget.TextBox();
        jLabel9 = new widget.Label();
        CmbJam = new widget.ComboBox();
        CmbMenit = new widget.ComboBox();
        CmbDetik = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        jLabel12 = new widget.Label();
        noTransaksi = new widget.TextBox();
        jLabel15 = new widget.Label();
        telahTerimaPAS = new widget.TextBox();
        Scroll3 = new widget.ScrollPane();
        Keterangan = new widget.TextArea();
        jLabel14 = new widget.Label();
        labelSewa = new widget.Label();
        nominalSewa = new widget.TextBox();
        jLabel16 = new widget.Label();
        telahTerimaAN = new widget.TextBox();
        label_pajak = new widget.Label();
        label_nominal = new widget.Label();
        ChkInput = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        cetakNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        cetakNota.setText("Cetak Nota/Kwitansi");
        cetakNota.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        cetakNota.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cetakNota.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cetakNota.setIconTextGap(5);
        cetakNota.setName("cetakNota"); // NOI18N
        cetakNota.setOpaque(true);
        cetakNota.setPreferredSize(new java.awt.Dimension(200, 26));

        MnTTDcn.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTTDcn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTTDcn.setText("TTD Basah");
        MnTTDcn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTTDcn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTTDcn.setIconTextGap(5);
        MnTTDcn.setName("MnTTDcn"); // NOI18N
        MnTTDcn.setPreferredSize(new java.awt.Dimension(160, 26));
        MnTTDcn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTTDcnActionPerformed(evt);
            }
        });
        cetakNota.add(MnTTDcn);

        MnTTEcn.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTTEcn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTTEcn.setText("TTE (QRCode)");
        MnTTEcn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTTEcn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTTEcn.setIconTextGap(5);
        MnTTEcn.setName("MnTTEcn"); // NOI18N
        MnTTEcn.setPreferredSize(new java.awt.Dimension(160, 26));
        MnTTEcn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTTEcnActionPerformed(evt);
            }
        });
        cetakNota.add(MnTTEcn);

        MnKirimKeWaNota.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKirimKeWaNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/whatsapp.png"))); // NOI18N
        MnKirimKeWaNota.setText("Kirim Nota Ke WhatsApp");
        MnKirimKeWaNota.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKirimKeWaNota.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKirimKeWaNota.setIconTextGap(5);
        MnKirimKeWaNota.setName("MnKirimKeWaNota"); // NOI18N
        MnKirimKeWaNota.setPreferredSize(new java.awt.Dimension(160, 26));
        MnKirimKeWaNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKirimKeWaNotaActionPerformed(evt);
            }
        });
        cetakNota.add(MnKirimKeWaNota);

        jPopupMenu1.add(cetakNota);

        cetakNotaAmbulan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        cetakNotaAmbulan.setText("Cetak Nota/Kwitansi Ambulan");
        cetakNotaAmbulan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        cetakNotaAmbulan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cetakNotaAmbulan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cetakNotaAmbulan.setIconTextGap(5);
        cetakNotaAmbulan.setName("cetakNotaAmbulan"); // NOI18N
        cetakNotaAmbulan.setOpaque(true);
        cetakNotaAmbulan.setPreferredSize(new java.awt.Dimension(200, 26));

        MnTTDcna.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTTDcna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTTDcna.setText("TTD Basah");
        MnTTDcna.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTTDcna.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTTDcna.setIconTextGap(5);
        MnTTDcna.setName("MnTTDcna"); // NOI18N
        MnTTDcna.setPreferredSize(new java.awt.Dimension(160, 26));
        MnTTDcna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTTDcnaActionPerformed(evt);
            }
        });
        cetakNotaAmbulan.add(MnTTDcna);

        MnTTEcna.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTTEcna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTTEcna.setText("TTE (QRCode)");
        MnTTEcna.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTTEcna.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTTEcna.setIconTextGap(5);
        MnTTEcna.setName("MnTTEcna"); // NOI18N
        MnTTEcna.setPreferredSize(new java.awt.Dimension(160, 26));
        MnTTEcna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTTEcnaActionPerformed(evt);
            }
        });
        cetakNotaAmbulan.add(MnTTEcna);

        MnKirimKeWaNota1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKirimKeWaNota1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/whatsapp.png"))); // NOI18N
        MnKirimKeWaNota1.setText("Kirim Nota Ke WhatsApp");
        MnKirimKeWaNota1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKirimKeWaNota1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKirimKeWaNota1.setIconTextGap(5);
        MnKirimKeWaNota1.setName("MnKirimKeWaNota1"); // NOI18N
        MnKirimKeWaNota1.setPreferredSize(new java.awt.Dimension(160, 26));
        MnKirimKeWaNota1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKirimKeWaNota1ActionPerformed(evt);
            }
        });
        cetakNotaAmbulan.add(MnKirimKeWaNota1);

        jPopupMenu1.add(cetakNotaAmbulan);

        cetakNotaNaikKls.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        cetakNotaNaikKls.setText("Nota/Kwitansi Naik Kelas");
        cetakNotaNaikKls.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        cetakNotaNaikKls.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cetakNotaNaikKls.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cetakNotaNaikKls.setIconTextGap(5);
        cetakNotaNaikKls.setName("cetakNotaNaikKls"); // NOI18N
        cetakNotaNaikKls.setOpaque(true);
        cetakNotaNaikKls.setPreferredSize(new java.awt.Dimension(200, 26));

        MnTTDcnnk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTTDcnnk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTTDcnnk.setText("TTD Basah");
        MnTTDcnnk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTTDcnnk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTTDcnnk.setIconTextGap(5);
        MnTTDcnnk.setName("MnTTDcnnk"); // NOI18N
        MnTTDcnnk.setPreferredSize(new java.awt.Dimension(160, 26));
        MnTTDcnnk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTTDcnnkActionPerformed(evt);
            }
        });
        cetakNotaNaikKls.add(MnTTDcnnk);

        MnTTEcnnk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTTEcnnk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTTEcnnk.setText("TTE (QRCode)");
        MnTTEcnnk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTTEcnnk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTTEcnnk.setIconTextGap(5);
        MnTTEcnnk.setName("MnTTEcnnk"); // NOI18N
        MnTTEcnnk.setPreferredSize(new java.awt.Dimension(160, 26));
        MnTTEcnnk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTTEcnnkActionPerformed(evt);
            }
        });
        cetakNotaNaikKls.add(MnTTEcnnk);

        MnKirimKeWaNota2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKirimKeWaNota2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/whatsapp.png"))); // NOI18N
        MnKirimKeWaNota2.setText("Kirim Nota Ke WhatsApp");
        MnKirimKeWaNota2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKirimKeWaNota2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKirimKeWaNota2.setIconTextGap(5);
        MnKirimKeWaNota2.setName("MnKirimKeWaNota2"); // NOI18N
        MnKirimKeWaNota2.setPreferredSize(new java.awt.Dimension(160, 26));
        MnKirimKeWaNota2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKirimKeWaNota2ActionPerformed(evt);
            }
        });
        cetakNotaNaikKls.add(MnKirimKeWaNota2);

        jPopupMenu1.add(cetakNotaNaikKls);

        lihatSelisihTarif.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lihatSelisihTarif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/peminjaman.png"))); // NOI18N
        lihatSelisihTarif.setText("Lihat Hitungan Selisih Tarif");
        lihatSelisihTarif.setName("lihatSelisihTarif"); // NOI18N
        lihatSelisihTarif.setPreferredSize(new java.awt.Dimension(200, 26));
        lihatSelisihTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lihatSelisihTarifActionPerformed(evt);
            }
        });
        jPopupMenu1.add(lihatSelisihTarif);

        MnDataSampah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataSampah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataSampah.setText("Data Sampah");
        MnDataSampah.setName("MnDataSampah"); // NOI18N
        MnDataSampah.setPreferredSize(new java.awt.Dimension(200, 26));
        MnDataSampah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataSampahActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDataSampah);

        MnLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporan.setText("Laporan");
        MnLaporan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporan.setName("MnLaporan"); // NOI18N
        MnLaporan.setPreferredSize(new java.awt.Dimension(200, 26));

        RekapSelisihLunas.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        RekapSelisihLunas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        RekapSelisihLunas.setText("Rekap Selisih Tarif BPJS (Pelunasan)");
        RekapSelisihLunas.setName("RekapSelisihLunas"); // NOI18N
        RekapSelisihLunas.setPreferredSize(new java.awt.Dimension(220, 26));
        RekapSelisihLunas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RekapSelisihLunasActionPerformed(evt);
            }
        });
        MnLaporan.add(RekapSelisihLunas);

        RekapSelisihAngsur.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        RekapSelisihAngsur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        RekapSelisihAngsur.setText("Rekap Selisih Tarif BPJS (Angsuran)");
        RekapSelisihAngsur.setName("RekapSelisihAngsur"); // NOI18N
        RekapSelisihAngsur.setPreferredSize(new java.awt.Dimension(220, 26));
        RekapSelisihAngsur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RekapSelisihAngsurActionPerformed(evt);
            }
        });
        MnLaporan.add(RekapSelisihAngsur);

        RekapPemasukanLain.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        RekapPemasukanLain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        RekapPemasukanLain.setText("Rekap Pemasukan Lain");
        RekapPemasukanLain.setName("RekapPemasukanLain"); // NOI18N
        RekapPemasukanLain.setPreferredSize(new java.awt.Dimension(220, 26));
        RekapPemasukanLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RekapPemasukanLainActionPerformed(evt);
            }
        });
        MnLaporan.add(RekapPemasukanLain);

        RekapSewaTempat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        RekapSewaTempat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        RekapSewaTempat.setText("Rekap Sewa Tempat");
        RekapSewaTempat.setName("RekapSewaTempat"); // NOI18N
        RekapSewaTempat.setPreferredSize(new java.awt.Dimension(220, 26));
        RekapSewaTempat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RekapSewaTempatActionPerformed(evt);
            }
        });
        MnLaporan.add(RekapSewaTempat);

        RekapAmbulan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        RekapAmbulan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        RekapAmbulan.setText("Rekap Pembayaran Ambulan");
        RekapAmbulan.setName("RekapAmbulan"); // NOI18N
        RekapAmbulan.setPreferredSize(new java.awt.Dimension(220, 26));
        RekapAmbulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RekapAmbulanActionPerformed(evt);
            }
        });
        MnLaporan.add(RekapAmbulan);

        jPopupMenu1.add(MnLaporan);

        WindowSelisihTarif.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowSelisihTarif.setName("WindowSelisihTarif"); // NOI18N
        WindowSelisihTarif.setUndecorated(true);
        WindowSelisihTarif.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Selisih Tarif Pasien R. Inap BPJS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame7.setLayout(new java.awt.BorderLayout());

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setPreferredSize(new java.awt.Dimension(0, 186));
        internalFrame8.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame8.setLayout(null);

        jLabel35.setForeground(new java.awt.Color(0, 0, 250));
        jLabel35.setText("No.SEP : 1704R0");
        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setName("jLabel35"); // NOI18N
        internalFrame8.add(jLabel35);
        jLabel35.setBounds(0, 10, 102, 23);

        NoSEP.setForeground(new java.awt.Color(0, 0, 0));
        NoSEP.setMaxLenth(18);
        NoSEP.setName("NoSEP"); // NOI18N
        NoSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSEPKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NoSEPKeyTyped(evt);
            }
        });
        internalFrame8.add(NoSEP);
        NoSEP.setBounds(106, 10, 120, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Pasien : ");
        jLabel36.setName("jLabel36"); // NOI18N
        internalFrame8.add(jLabel36);
        jLabel36.setBounds(230, 10, 50, 23);

        norm.setEditable(false);
        norm.setForeground(new java.awt.Color(0, 0, 0));
        norm.setName("norm"); // NOI18N
        internalFrame8.add(norm);
        norm.setBounds(282, 10, 80, 23);

        nmpasien.setEditable(false);
        nmpasien.setForeground(new java.awt.Color(0, 0, 0));
        nmpasien.setHighlighter(null);
        nmpasien.setName("nmpasien"); // NOI18N
        internalFrame8.add(nmpasien);
        nmpasien.setBounds(366, 10, 408, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Tgl. Masuk : ");
        jLabel37.setName("jLabel37"); // NOI18N
        internalFrame8.add(jLabel37);
        jLabel37.setBounds(0, 66, 102, 23);

        nokartu.setEditable(false);
        nokartu.setForeground(new java.awt.Color(0, 0, 0));
        nokartu.setName("nokartu"); // NOI18N
        internalFrame8.add(nokartu);
        nokartu.setBounds(106, 38, 120, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("No. Rawat : ");
        jLabel38.setName("jLabel38"); // NOI18N
        internalFrame8.add(jLabel38);
        jLabel38.setBounds(225, 38, 115, 23);

        norawat.setEditable(false);
        norawat.setForeground(new java.awt.Color(0, 0, 0));
        norawat.setName("norawat"); // NOI18N
        internalFrame8.add(norawat);
        norawat.setBounds(340, 38, 140, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("No. Kartu : ");
        jLabel39.setName("jLabel39"); // NOI18N
        internalFrame8.add(jLabel39);
        jLabel39.setBounds(0, 38, 102, 23);

        tglmsk.setEditable(false);
        tglmsk.setForeground(new java.awt.Color(0, 0, 0));
        tglmsk.setName("tglmsk"); // NOI18N
        internalFrame8.add(tglmsk);
        tglmsk.setBounds(106, 66, 120, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Tgl. Keluar/Pulang : ");
        jLabel40.setName("jLabel40"); // NOI18N
        internalFrame8.add(jLabel40);
        jLabel40.setBounds(225, 66, 115, 23);

        tglklr.setEditable(false);
        tglklr.setForeground(new java.awt.Color(0, 0, 0));
        tglklr.setName("tglklr"); // NOI18N
        internalFrame8.add(tglklr);
        tglklr.setBounds(340, 66, 140, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Ruang Rawat : ");
        jLabel41.setName("jLabel41"); // NOI18N
        internalFrame8.add(jLabel41);
        jLabel41.setBounds(0, 94, 102, 23);

        rginap.setEditable(false);
        rginap.setForeground(new java.awt.Color(0, 0, 0));
        rginap.setName("rginap"); // NOI18N
        internalFrame8.add(rginap);
        rginap.setBounds(106, 94, 668, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Tarif : Kelas 1 Rp.");
        jLabel43.setName("jLabel43"); // NOI18N
        internalFrame8.add(jLabel43);
        jLabel43.setBounds(0, 150, 102, 23);

        kdINACBG.setForeground(new java.awt.Color(0, 0, 0));
        kdINACBG.setMaxLenth(15);
        kdINACBG.setName("kdINACBG"); // NOI18N
        kdINACBG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdINACBGKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kdINACBGKeyTyped(evt);
            }
        });
        internalFrame8.add(kdINACBG);
        kdINACBG.setBounds(106, 122, 90, 23);

        deskripsiKD.setEditable(false);
        deskripsiKD.setForeground(new java.awt.Color(0, 0, 0));
        deskripsiKD.setName("deskripsiKD"); // NOI18N
        internalFrame8.add(deskripsiKD);
        deskripsiKD.setBounds(199, 122, 575, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Kode INACBG : ");
        jLabel44.setName("jLabel44"); // NOI18N
        internalFrame8.add(jLabel44);
        jLabel44.setBounds(0, 122, 102, 23);

        tarifkls1.setEditable(false);
        tarifkls1.setForeground(new java.awt.Color(0, 0, 0));
        tarifkls1.setText("0");
        tarifkls1.setName("tarifkls1"); // NOI18N
        internalFrame8.add(tarifkls1);
        tarifkls1.setBounds(106, 150, 100, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Kelas 2 Rp.");
        jLabel45.setName("jLabel45"); // NOI18N
        internalFrame8.add(jLabel45);
        jLabel45.setBounds(205, 150, 65, 23);

        tarifkls2.setEditable(false);
        tarifkls2.setForeground(new java.awt.Color(0, 0, 0));
        tarifkls2.setText("0");
        tarifkls2.setName("tarifkls2"); // NOI18N
        internalFrame8.add(tarifkls2);
        tarifkls2.setBounds(275, 150, 100, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Kelas 3 Rp.");
        jLabel46.setName("jLabel46"); // NOI18N
        internalFrame8.add(jLabel46);
        jLabel46.setBounds(380, 150, 60, 23);

        tarifkls3.setEditable(false);
        tarifkls3.setForeground(new java.awt.Color(0, 0, 0));
        tarifkls3.setText("0");
        tarifkls3.setName("tarifkls3"); // NOI18N
        internalFrame8.add(tarifkls3);
        tarifkls3.setBounds(445, 150, 100, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Real Cost RS Rp. ");
        jLabel55.setName("jLabel55"); // NOI18N
        internalFrame8.add(jLabel55);
        jLabel55.setBounds(546, 150, 100, 23);

        tarifrc.setEditable(false);
        tarifrc.setForeground(new java.awt.Color(0, 0, 0));
        tarifrc.setText("0");
        tarifrc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tarifrc.setName("tarifrc"); // NOI18N
        internalFrame8.add(tarifrc);
        tarifrc.setBounds(645, 150, 130, 23);

        internalFrame7.add(internalFrame8, java.awt.BorderLayout.PAGE_START);

        internalFrame9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Penghitungan selisih tarif (Permenkes RI No. 3 Tahun 2023) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setPreferredSize(new java.awt.Dimension(0, 480));
        internalFrame9.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame9.setLayout(null);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Hak Kelas BPJS : ");
        jLabel54.setName("jLabel54"); // NOI18N
        internalFrame9.add(jLabel54);
        jLabel54.setBounds(0, 25, 102, 23);

        hakkelas.setEditable(false);
        hakkelas.setForeground(new java.awt.Color(0, 0, 0));
        hakkelas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        hakkelas.setHighlighter(null);
        hakkelas.setName("hakkelas"); // NOI18N
        internalFrame9.add(hakkelas);
        hakkelas.setBounds(106, 25, 40, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Naik ke : ");
        jLabel47.setName("jLabel47"); // NOI18N
        internalFrame9.add(jLabel47);
        jLabel47.setBounds(152, 25, 46, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Lama rawat Rg. VIP : ");
        jLabel48.setName("jLabel48"); // NOI18N
        internalFrame9.add(jLabel48);
        jLabel48.setBounds(325, 25, 110, 23);

        lmrawat.setEditable(false);
        lmrawat.setForeground(new java.awt.Color(0, 0, 0));
        lmrawat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lmrawat.setText("0");
        lmrawat.setHighlighter(null);
        lmrawat.setName("lmrawat"); // NOI18N
        internalFrame9.add(lmrawat);
        lmrawat.setBounds(435, 25, 50, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("hari,  Persentase tambahan naik kelas : ");
        jLabel49.setName("jLabel49"); // NOI18N
        internalFrame9.add(jLabel49);
        jLabel49.setBounds(487, 25, 196, 23);

        persenSELISIH.setEditable(false);
        persenSELISIH.setForeground(new java.awt.Color(0, 0, 0));
        persenSELISIH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        persenSELISIH.setText("0");
        persenSELISIH.setHighlighter(null);
        persenSELISIH.setName("persenSELISIH"); // NOI18N
        internalFrame9.add(persenSELISIH);
        persenSELISIH.setBounds(685, 25, 45, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("%");
        jLabel50.setName("jLabel50"); // NOI18N
        internalFrame9.add(jLabel50);
        jLabel50.setBounds(735, 25, 20, 23);

        labelbyr.setForeground(new java.awt.Color(0, 0, 0));
        labelbyr.setText("Total bayar : Rp. ");
        labelbyr.setName("labelbyr"); // NOI18N
        internalFrame9.add(labelbyr);
        labelbyr.setBounds(480, 225, 160, 23);

        Totdibayar.setEditable(false);
        Totdibayar.setForeground(new java.awt.Color(0, 0, 0));
        Totdibayar.setText("0");
        Totdibayar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Totdibayar.setHighlighter(null);
        Totdibayar.setName("Totdibayar"); // NOI18N
        internalFrame9.add(Totdibayar);
        Totdibayar.setBounds(645, 225, 95, 23);

        Sudahbyr.setEditable(false);
        Sudahbyr.setForeground(new java.awt.Color(0, 0, 0));
        Sudahbyr.setText("0");
        Sudahbyr.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Sudahbyr.setHighlighter(null);
        Sudahbyr.setName("Sudahbyr"); // NOI18N
        internalFrame9.add(Sudahbyr);
        Sudahbyr.setBounds(645, 253, 95, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Sudah bayar : Rp. ");
        jLabel51.setName("jLabel51"); // NOI18N
        internalFrame9.add(jLabel51);
        jLabel51.setBounds(480, 253, 160, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Sisa tagihan : Rp. ");
        jLabel52.setName("jLabel52"); // NOI18N
        internalFrame9.add(jLabel52);
        jLabel52.setBounds(480, 281, 160, 23);

        SisaTagihan.setEditable(false);
        SisaTagihan.setForeground(new java.awt.Color(0, 0, 0));
        SisaTagihan.setText("0");
        SisaTagihan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        SisaTagihan.setHighlighter(null);
        SisaTagihan.setName("SisaTagihan"); // NOI18N
        internalFrame9.add(SisaTagihan);
        SisaTagihan.setBounds(645, 281, 95, 23);

        jlhdibayar.setForeground(new java.awt.Color(0, 0, 0));
        jlhdibayar.setText("0");
        jlhdibayar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlhdibayar.setHighlighter(null);
        jlhdibayar.setName("jlhdibayar"); // NOI18N
        internalFrame9.add(jlhdibayar);
        jlhdibayar.setBounds(645, 309, 95, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Jumlah dibayar : Rp. ");
        jLabel53.setName("jLabel53"); // NOI18N
        internalFrame9.add(jLabel53);
        jLabel53.setBounds(520, 309, 120, 23);

        ChkSesuaiTagihan.setBackground(new java.awt.Color(255, 255, 250));
        ChkSesuaiTagihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSesuaiTagihan.setForeground(new java.awt.Color(0, 0, 0));
        ChkSesuaiTagihan.setText("Pembayaran sesuai tagihan >>>");
        ChkSesuaiTagihan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkSesuaiTagihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSesuaiTagihan.setName("ChkSesuaiTagihan"); // NOI18N
        ChkSesuaiTagihan.setOpaque(false);
        ChkSesuaiTagihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSesuaiTagihanActionPerformed(evt);
            }
        });
        internalFrame9.add(ChkSesuaiTagihan);
        ChkSesuaiTagihan.setBounds(310, 309, 210, 23);

        lunas.setForeground(new java.awt.Color(153, 0, 153));
        lunas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lunas.setText("~ LUNAS ~");
        lunas.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        lunas.setName("lunas"); // NOI18N
        internalFrame9.add(lunas);
        lunas.setBounds(230, 225, 270, 80);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Kalkulasi Tarif : ");
        jLabel56.setName("jLabel56"); // NOI18N
        internalFrame9.add(jLabel56);
        jLabel56.setBounds(0, 53, 102, 23);

        Scroll39.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        Scroll39.setName("Scroll39"); // NOI18N

        TKalkulasi.setEditable(false);
        TKalkulasi.setColumns(20);
        TKalkulasi.setRows(5);
        TKalkulasi.setName("TKalkulasi"); // NOI18N
        TKalkulasi.setPreferredSize(new java.awt.Dimension(250, 200));
        Scroll39.setViewportView(TKalkulasi);

        internalFrame9.add(Scroll39);
        Scroll39.setBounds(106, 53, 670, 165);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("No. Panjar : ");
        jLabel57.setName("jLabel57"); // NOI18N
        internalFrame9.add(jLabel57);
        jLabel57.setBounds(320, 337, 110, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Tgl. Panjar : ");
        jLabel58.setName("jLabel58"); // NOI18N
        internalFrame9.add(jLabel58);
        jLabel58.setBounds(320, 365, 110, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Uang Panjar : Rp. ");
        jLabel59.setName("jLabel59"); // NOI18N
        internalFrame9.add(jLabel59);
        jLabel59.setBounds(320, 393, 110, 23);

        TnoPanjar.setEditable(false);
        TnoPanjar.setForeground(new java.awt.Color(0, 0, 0));
        TnoPanjar.setName("TnoPanjar"); // NOI18N
        internalFrame9.add(TnoPanjar);
        TnoPanjar.setBounds(430, 337, 131, 23);

        TtglPanjar.setEditable(false);
        TtglPanjar.setForeground(new java.awt.Color(0, 0, 0));
        TtglPanjar.setHighlighter(null);
        TtglPanjar.setName("TtglPanjar"); // NOI18N
        internalFrame9.add(TtglPanjar);
        TtglPanjar.setBounds(430, 365, 260, 23);

        TnominalPanjar.setEditable(false);
        TnominalPanjar.setForeground(new java.awt.Color(0, 0, 0));
        TnominalPanjar.setName("TnominalPanjar"); // NOI18N
        internalFrame9.add(TnominalPanjar);
        TnominalPanjar.setBounds(430, 393, 131, 23);

        BtnPanjar.setForeground(new java.awt.Color(0, 0, 0));
        BtnPanjar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPanjar.setText("Panjar");
        BtnPanjar.setName("BtnPanjar"); // NOI18N
        BtnPanjar.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPanjar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPanjarActionPerformed(evt);
            }
        });
        internalFrame9.add(BtnPanjar);
        BtnPanjar.setBounds(562, 337, 80, 23);

        BtnJaminan.setForeground(new java.awt.Color(0, 0, 0));
        BtnJaminan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnJaminan.setText("Jaminan");
        BtnJaminan.setName("BtnJaminan"); // NOI18N
        BtnJaminan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnJaminan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnJaminanActionPerformed(evt);
            }
        });
        internalFrame9.add(BtnJaminan);
        BtnJaminan.setBounds(650, 337, 90, 23);

        cmbNaikKls.setForeground(new java.awt.Color(0, 0, 0));
        cmbNaikKls.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbNaikKls.setName("cmbNaikKls"); // NOI18N
        cmbNaikKls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNaikKlsActionPerformed(evt);
            }
        });
        internalFrame9.add(cmbNaikKls);
        cmbNaikKls.setBounds(199, 25, 110, 23);

        internalFrame7.add(internalFrame9, java.awt.BorderLayout.CENTER);

        internalFrame10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setPreferredSize(new java.awt.Dimension(0, 44));
        internalFrame10.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 9));

        BtnSelisihBaru.setForeground(new java.awt.Color(0, 0, 0));
        BtnSelisihBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnSelisihBaru.setMnemonic('B');
        BtnSelisihBaru.setText("Baru");
        BtnSelisihBaru.setToolTipText("Alt+B");
        BtnSelisihBaru.setName("BtnSelisihBaru"); // NOI18N
        BtnSelisihBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSelisihBaruActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnSelisihBaru);

        BtnSimpan6.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan6.setMnemonic('S');
        BtnSimpan6.setText("Simpan");
        BtnSimpan6.setToolTipText("Alt+S");
        BtnSimpan6.setName("BtnSimpan6"); // NOI18N
        BtnSimpan6.setPreferredSize(new java.awt.Dimension(86, 26));
        BtnSimpan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan6ActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnSimpan6);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak Tarif");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(130, 26));
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
        internalFrame10.add(BtnPrint);

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

        internalFrame7.add(internalFrame10, java.awt.BorderLayout.PAGE_END);

        WindowSelisihTarif.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        WindowAmbulan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowAmbulan.setName("WindowAmbulan"); // NOI18N
        WindowAmbulan.setUndecorated(true);
        WindowAmbulan.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pembayaran Transportasi Ambulance ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame6.setLayout(null);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Alamat Tujuan : ");
        jLabel18.setName("jLabel18"); // NOI18N
        internalFrame6.add(jLabel18);
        jLabel18.setBounds(0, 38, 90, 23);

        Tjarak.setForeground(new java.awt.Color(0, 0, 0));
        Tjarak.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tjarak.setName("Tjarak"); // NOI18N
        Tjarak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjarakKeyPressed(evt);
            }
        });
        internalFrame6.add(Tjarak);
        Tjarak.setBounds(503, 38, 50, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jarak Tujuan : ");
        jLabel20.setName("jLabel20"); // NOI18N
        internalFrame6.add(jLabel20);
        jLabel20.setBounds(413, 38, 90, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Nama Tarif : ");
        jLabel22.setName("jLabel22"); // NOI18N
        internalFrame6.add(jLabel22);
        jLabel22.setBounds(0, 66, 90, 23);

        CmbTarif.setForeground(new java.awt.Color(0, 0, 0));
        CmbTarif.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        CmbTarif.setName("CmbTarif"); // NOI18N
        CmbTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbTarifActionPerformed(evt);
            }
        });
        internalFrame6.add(CmbTarif);
        CmbTarif.setBounds(92, 66, 485, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tarif PerBup : Rp.");
        jLabel23.setName("jLabel23"); // NOI18N
        internalFrame6.add(jLabel23);
        jLabel23.setBounds(373, 94, 100, 23);

        Ttarif.setEditable(false);
        Ttarif.setForeground(new java.awt.Color(0, 0, 0));
        Ttarif.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Ttarif.setName("Ttarif"); // NOI18N
        internalFrame6.add(Ttarif);
        Ttarif.setBounds(477, 94, 100, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Penghitungan Jumlah Bayar = Biaya Selisih Jarak + Tarif Standar PerBup : Rp.");
        jLabel24.setName("jLabel24"); // NOI18N
        internalFrame6.add(jLabel24);
        jLabel24.setBounds(0, 178, 473, 23);

        TjlhBayar.setEditable(false);
        TjlhBayar.setForeground(new java.awt.Color(0, 0, 0));
        TjlhBayar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TjlhBayar.setName("TjlhBayar"); // NOI18N
        internalFrame6.add(TjlhBayar);
        TjlhBayar.setBounds(477, 178, 100, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Selisih Jarak Dari Tarif Standar PerBup (Jarak < 15 km) : ");
        jLabel25.setName("jLabel25"); // NOI18N
        internalFrame6.add(jLabel25);
        jLabel25.setBounds(0, 122, 473, 23);

        TselisihJrk.setEditable(false);
        TselisihJrk.setForeground(new java.awt.Color(0, 0, 0));
        TselisihJrk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TselisihJrk.setName("TselisihJrk"); // NOI18N
        internalFrame6.add(TselisihJrk);
        TselisihJrk.setBounds(477, 122, 50, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Penghitungan Biaya Selisih Jarak = Selisih Jarak (Km.) X Tarif PerBup : Rp.");
        jLabel27.setName("jLabel27"); // NOI18N
        internalFrame6.add(jLabel27);
        jLabel27.setBounds(0, 150, 473, 23);

        TtarifSelisihJrk.setEditable(false);
        TtarifSelisihJrk.setForeground(new java.awt.Color(0, 0, 0));
        TtarifSelisihJrk.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TtarifSelisihJrk.setName("TtarifSelisihJrk"); // NOI18N
        internalFrame6.add(TtarifSelisihJrk);
        TtarifSelisihJrk.setBounds(477, 150, 100, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Rp.");
        jLabel28.setName("jLabel28"); // NOI18N
        internalFrame6.add(jLabel28);
        jLabel28.setBounds(443, 234, 30, 23);

        TtotBayar.setEditable(false);
        TtotBayar.setForeground(new java.awt.Color(0, 0, 0));
        TtotBayar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TtotBayar.setName("TtotBayar"); // NOI18N
        internalFrame6.add(TtotBayar);
        TtotBayar.setBounds(477, 234, 100, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Km.");
        jLabel30.setName("jLabel30"); // NOI18N
        internalFrame6.add(jLabel30);
        jLabel30.setBounds(532, 122, 30, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Pasien/An. : ");
        jLabel26.setName("jLabel26"); // NOI18N
        internalFrame6.add(jLabel26);
        jLabel26.setBounds(0, 10, 90, 23);

        Tnorm.setForeground(new java.awt.Color(0, 0, 0));
        Tnorm.setName("Tnorm"); // NOI18N
        Tnorm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnormKeyPressed(evt);
            }
        });
        internalFrame6.add(Tnorm);
        Tnorm.setBounds(92, 10, 75, 23);

        Tnmpasien.setForeground(new java.awt.Color(0, 0, 0));
        Tnmpasien.setName("Tnmpasien"); // NOI18N
        Tnmpasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmpasienKeyPressed(evt);
            }
        });
        internalFrame6.add(Tnmpasien);
        Tnmpasien.setBounds(170, 10, 408, 23);

        TtujuanAlamat.setForeground(new java.awt.Color(0, 0, 0));
        TtujuanAlamat.setName("TtujuanAlamat"); // NOI18N
        TtujuanAlamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtujuanAlamatKeyPressed(evt);
            }
        });
        internalFrame6.add(TtujuanAlamat);
        TtujuanAlamat.setBounds(92, 38, 320, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Km.");
        jLabel31.setName("jLabel31"); // NOI18N
        internalFrame6.add(jLabel31);
        jLabel31.setBounds(560, 38, 30, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Jumlah Biaya Dibayar Oleh Pihak Ke 3 : Rp.");
        jLabel32.setName("jLabel32"); // NOI18N
        internalFrame6.add(jLabel32);
        jLabel32.setBounds(0, 206, 473, 23);

        TjlhBiayaPihak3.setForeground(new java.awt.Color(0, 0, 0));
        TjlhBiayaPihak3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TjlhBiayaPihak3.setName("TjlhBiayaPihak3"); // NOI18N
        TjlhBiayaPihak3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjlhBiayaPihak3KeyPressed(evt);
            }
        });
        internalFrame6.add(TjlhBiayaPihak3);
        TjlhBiayaPihak3.setBounds(477, 206, 100, 23);

        BtnHitung.setForeground(new java.awt.Color(0, 0, 0));
        BtnHitung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnHitung.setMnemonic('H');
        BtnHitung.setText("Hitung Total Bayar");
        BtnHitung.setToolTipText("Alt+H");
        BtnHitung.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnHitung.setName("BtnHitung"); // NOI18N
        BtnHitung.setPreferredSize(new java.awt.Dimension(100, 26));
        BtnHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHitungActionPerformed(evt);
            }
        });
        internalFrame6.add(BtnHitung);
        BtnHitung.setBounds(300, 234, 150, 26);

        internalFrame5.add(internalFrame6, java.awt.BorderLayout.CENTER);

        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setPreferredSize(new java.awt.Dimension(0, 44));
        internalFrame11.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 9));

        BtnSimpan4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan4.setMnemonic('S');
        BtnSimpan4.setText("Simpan");
        BtnSimpan4.setToolTipText("Alt+S");
        BtnSimpan4.setName("BtnSimpan4"); // NOI18N
        BtnSimpan4.setPreferredSize(new java.awt.Dimension(100, 26));
        BtnSimpan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan4ActionPerformed(evt);
            }
        });
        internalFrame11.add(BtnSimpan4);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.setPreferredSize(new java.awt.Dimension(100, 26));
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame11.add(BtnCloseIn4);

        internalFrame5.add(internalFrame11, java.awt.BorderLayout.PAGE_END);

        WindowAmbulan.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        WindowHapus.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowHapus.setName("WindowHapus"); // NOI18N
        WindowHapus.setUndecorated(true);
        WindowHapus.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Hapus Pemasukan Lain-Lain ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 70));
        panelisi3.setLayout(null);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Keterangan / Alasan :");
        jLabel33.setName("jLabel33"); // NOI18N
        panelisi3.add(jLabel33);
        jLabel33.setBounds(0, 10, 130, 23);

        TketHapus.setForeground(new java.awt.Color(0, 0, 0));
        TketHapus.setName("TketHapus"); // NOI18N
        TketHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketHapusKeyPressed(evt);
            }
        });
        panelisi3.add(TketHapus);
        TketHapus.setBounds(135, 10, 500, 23);

        internalFrame3.add(panelisi3, java.awt.BorderLayout.CENTER);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 48));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 4, 9));

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapus1.setMnemonic('H');
        BtnHapus1.setText("Hapus");
        BtnHapus1.setToolTipText("Alt+H");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        BtnHapus1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapus1KeyPressed(evt);
            }
        });
        panelisi4.add(BtnHapus1);

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

        internalFrame3.add(panelisi4, java.awt.BorderLayout.PAGE_END);

        WindowHapus.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        WindowRiwayat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayat.setName("WindowRiwayat"); // NOI18N
        WindowRiwayat.setUndecorated(true);
        WindowRiwayat.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Transaksi Pemasukan Lain-lain Dihapus ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame13.setLayout(new java.awt.BorderLayout());

        internalFrame18.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame18.setName("internalFrame18"); // NOI18N
        internalFrame18.setPreferredSize(new java.awt.Dimension(400, 88));
        internalFrame18.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame18.setLayout(new java.awt.BorderLayout());

        internalFrame17.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame17.setName("internalFrame17"); // NOI18N
        internalFrame17.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame17.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 7, 9));

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Tgl. Dihapus :");
        jLabel101.setName("jLabel101"); // NOI18N
        jLabel101.setPreferredSize(new java.awt.Dimension(80, 23));
        internalFrame17.add(jLabel101);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2026" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari3);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setText("s.d.");
        jLabel102.setName("jLabel102"); // NOI18N
        jLabel102.setPreferredSize(new java.awt.Dimension(23, 23));
        internalFrame17.add(jLabel102);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2026" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari4);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Key Word :");
        jLabel103.setName("jLabel103"); // NOI18N
        jLabel103.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame17.add(jLabel103);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        internalFrame17.add(TCari2);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('1');
        BtnCari2.setText("Tampilkan Data");
        BtnCari2.setToolTipText("Alt+1");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        BtnCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari2KeyPressed(evt);
            }
        });
        internalFrame17.add(BtnCari2);

        internalFrame18.add(internalFrame17, java.awt.BorderLayout.CENTER);

        internalFrame19.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame19.setName("internalFrame19"); // NOI18N
        internalFrame19.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame19.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 7, 9));

        BtnAll2.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll2.setMnemonic('2');
        BtnAll2.setText("Semua Data");
        BtnAll2.setToolTipText("Alt+2");
        BtnAll2.setName("BtnAll2"); // NOI18N
        BtnAll2.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll2ActionPerformed(evt);
            }
        });
        BtnAll2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll2KeyPressed(evt);
            }
        });
        internalFrame19.add(BtnAll2);

        BtnRestor.setForeground(new java.awt.Color(0, 0, 0));
        BtnRestor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnRestor.setMnemonic('U');
        BtnRestor.setText("Restore");
        BtnRestor.setToolTipText("Alt+U");
        BtnRestor.setName("BtnRestor"); // NOI18N
        BtnRestor.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnRestor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRestorActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnRestor);

        BtnCloseIn12.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn12.setMnemonic('U');
        BtnCloseIn12.setText("Tutup");
        BtnCloseIn12.setToolTipText("Alt+U");
        BtnCloseIn12.setName("BtnCloseIn12"); // NOI18N
        BtnCloseIn12.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnCloseIn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn12ActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnCloseIn12);

        internalFrame18.add(internalFrame19, java.awt.BorderLayout.PAGE_END);

        internalFrame13.add(internalFrame18, java.awt.BorderLayout.PAGE_END);

        internalFrame20.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame20.setName("internalFrame20"); // NOI18N
        internalFrame20.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame20.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame20.setLayout(new java.awt.GridLayout(2, 1));

        Scroll6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Riwayat Pemasukan Lain-Lain ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbRiwayat.setToolTipText("Silahkan pilih salah satu data yang mau direstore");
        tbRiwayat.setName("tbRiwayat"); // NOI18N
        tbRiwayat.getTableHeader().setReorderingAllowed(false);
        tbRiwayat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatMouseClicked(evt);
            }
        });
        tbRiwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwayatKeyPressed(evt);
            }
        });
        Scroll6.setViewportView(tbRiwayat);

        internalFrame20.add(Scroll6);

        Scroll7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Riwayat Biaya Selisih Tarif Naik Kelas Rawat Inap ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbRiwayat1.setName("tbRiwayat1"); // NOI18N
        tbRiwayat1.getTableHeader().setReorderingAllowed(false);
        Scroll7.setViewportView(tbRiwayat1);

        internalFrame20.add(Scroll7);

        internalFrame13.add(internalFrame20, java.awt.BorderLayout.CENTER);

        WindowRiwayat.getContentPane().add(internalFrame13, java.awt.BorderLayout.CENTER);

        cekNoSEP.setForeground(new java.awt.Color(0, 0, 0));
        cekNoSEP.setHighlighter(null);
        cekNoSEP.setName("cekNoSEP"); // NOI18N

        byrSimpan.setForeground(new java.awt.Color(0, 0, 0));
        byrSimpan.setHighlighter(null);
        byrSimpan.setName("byrSimpan"); // NOI18N

        rumusbayar.setForeground(new java.awt.Color(0, 0, 0));
        rumusbayar.setHighlighter(null);
        rumusbayar.setName("rumusbayar"); // NOI18N

        statusSELISIH.setForeground(new java.awt.Color(0, 0, 0));
        statusSELISIH.setHighlighter(null);
        statusSELISIH.setName("statusSELISIH"); // NOI18N

        persenSewa.setForeground(new java.awt.Color(0, 0, 0));
        persenSewa.setHighlighter(null);
        persenSewa.setName("persenSewa"); // NOI18N

        nominalPajakSewa.setForeground(new java.awt.Color(0, 0, 0));
        nominalPajakSewa.setHighlighter(null);
        nominalPajakSewa.setName("nominalPajakSewa"); // NOI18N

        totalbyrsewa.setForeground(new java.awt.Color(0, 0, 0));
        totalbyrsewa.setHighlighter(null);
        totalbyrsewa.setName("totalbyrsewa"); // NOI18N

        cekNoTransaksi.setForeground(new java.awt.Color(0, 0, 0));
        cekNoTransaksi.setHighlighter(null);
        cekNoTransaksi.setName("cekNoTransaksi"); // NOI18N

        bayarKe.setForeground(new java.awt.Color(0, 0, 0));
        bayarKe.setHighlighter(null);
        bayarKe.setName("bayarKe"); // NOI18N

        jumlhBayar.setForeground(new java.awt.Color(0, 0, 0));
        jumlhBayar.setHighlighter(null);
        jumlhBayar.setName("jumlhBayar"); // NOI18N

        belumDibayar.setForeground(new java.awt.Color(0, 0, 0));
        belumDibayar.setHighlighter(null);
        belumDibayar.setName("belumDibayar"); // NOI18N

        statusTran.setForeground(new java.awt.Color(0, 0, 0));
        statusTran.setHighlighter(null);
        statusTran.setName("statusTran"); // NOI18N

        noTranAngsur.setForeground(new java.awt.Color(0, 0, 0));
        noTranAngsur.setHighlighter(null);
        noTranAngsur.setName("noTranAngsur"); // NOI18N

        dataSelisihTarif.setForeground(new java.awt.Color(0, 0, 0));
        dataSelisihTarif.setHighlighter(null);
        dataSelisihTarif.setName("dataSelisihTarif"); // NOI18N

        realcostRS.setForeground(new java.awt.Color(0, 0, 0));
        realcostRS.setHighlighter(null);
        realcostRS.setName("realcostRS"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pemasukan Lain-Lain ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPemasukan.setAutoCreateRowSorter(true);
        tbPemasukan.setToolTipText("Silahkan klik untuk memilih data yang mau dicetak nota/kwitansinya");
        tbPemasukan.setComponentPopupMenu(jPopupMenu1);
        tbPemasukan.setName("tbPemasukan"); // NOI18N
        tbPemasukan.getTableHeader().setReorderingAllowed(false);
        tbPemasukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemasukanMouseClicked(evt);
            }
        });
        tbPemasukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemasukanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPemasukan);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

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

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(55, 30));
        panelGlass8.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(52, 30));
        panelGlass8.add(LCount);

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

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Tgl. Nota/Kwitansi : ");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass8.add(jLabel29);

        tglNota.setEditable(false);
        tglNota.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2026" }));
        tglNota.setDisplayFormat("dd-MM-yyyy");
        tglNota.setName("tglNota"); // NOI18N
        tglNota.setOpaque(false);
        panelGlass8.add(tglNota);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(312, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('4');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+4");
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

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(440, 265));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(440, 77));
        FormInput.setLayout(null);

        KdPtg.setEditable(false);
        KdPtg.setForeground(new java.awt.Color(0, 0, 0));
        KdPtg.setName("KdPtg"); // NOI18N
        FormInput.add(KdPtg);
        KdPtg.setBounds(85, 70, 100, 23);

        NmPtg.setEditable(false);
        NmPtg.setForeground(new java.awt.Color(0, 0, 0));
        NmPtg.setName("NmPtg"); // NOI18N
        FormInput.add(NmPtg);
        NmPtg.setBounds(188, 70, 420, 23);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Keterangan :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 100, 80, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Petugas :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 70, 80, 23);

        btnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('3');
        btnPetugas.setToolTipText("Alt+3");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        btnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(btnPetugas);
        btnPetugas.setBounds(610, 70, 28, 23);

        KdKategori.setEditable(false);
        KdKategori.setForeground(new java.awt.Color(0, 0, 0));
        KdKategori.setName("KdKategori"); // NOI18N
        KdKategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdKategoriKeyPressed(evt);
            }
        });
        FormInput.add(KdKategori);
        KdKategori.setBounds(85, 40, 70, 23);

        NmKategori.setEditable(false);
        NmKategori.setForeground(new java.awt.Color(0, 0, 0));
        NmKategori.setHighlighter(null);
        NmKategori.setName("NmKategori"); // NOI18N
        FormInput.add(NmKategori);
        NmKategori.setBounds(158, 40, 451, 23);

        btnKategori.setForeground(new java.awt.Color(0, 0, 0));
        btnKategori.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKategori.setMnemonic('3');
        btnKategori.setToolTipText("Alt+3");
        btnKategori.setName("btnKategori"); // NOI18N
        btnKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKategoriActionPerformed(evt);
            }
        });
        btnKategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnKategoriKeyPressed(evt);
            }
        });
        FormInput.add(btnKategori);
        btnKategori.setBounds(610, 40, 28, 23);

        Tanggal.setEditable(false);
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2026" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.setPreferredSize(new java.awt.Dimension(100, 23));
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(255, 10, 90, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tanggal :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(200, 10, 50, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Telah terima dari (An./Pasien) :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(750, 100, 170, 23);

        pemasukan.setForeground(new java.awt.Color(0, 0, 0));
        pemasukan.setText("0");
        pemasukan.setToolTipText("Khusus utk. pembayaran sewa kantin isikan harga sewanya saja, pajaknya sdh dihitung otomatis...!!!");
        pemasukan.setName("pemasukan"); // NOI18N
        pemasukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pemasukanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pemasukanKeyReleased(evt);
            }
        });
        FormInput.add(pemasukan);
        pemasukan.setBounds(925, 40, 139, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jam :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(350, 10, 35, 23);

        CmbJam.setForeground(new java.awt.Color(0, 0, 0));
        CmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam.setName("CmbJam"); // NOI18N
        CmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJamKeyPressed(evt);
            }
        });
        FormInput.add(CmbJam);
        CmbJam.setBounds(390, 10, 48, 23);

        CmbMenit.setForeground(new java.awt.Color(0, 0, 0));
        CmbMenit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit.setName("CmbMenit"); // NOI18N
        CmbMenit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbMenitKeyPressed(evt);
            }
        });
        FormInput.add(CmbMenit);
        CmbMenit.setBounds(440, 10, 48, 23);

        CmbDetik.setForeground(new java.awt.Color(0, 0, 0));
        CmbDetik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik.setName("CmbDetik"); // NOI18N
        CmbDetik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbDetikKeyPressed(evt);
            }
        });
        FormInput.add(CmbDetik);
        CmbDetik.setBounds(490, 10, 48, 23);

        ChkJln.setBackground(new java.awt.Color(235, 255, 235));
        ChkJln.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkJln.setForeground(new java.awt.Color(0, 0, 0));
        ChkJln.setSelected(true);
        ChkJln.setBorderPainted(true);
        ChkJln.setBorderPaintedFlat(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        FormInput.add(ChkJln);
        ChkJln.setBounds(540, 10, 23, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("No. Transaksi :");
        jLabel12.setToolTipText("");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 10, 80, 23);

        noTransaksi.setEditable(false);
        noTransaksi.setForeground(new java.awt.Color(0, 0, 0));
        noTransaksi.setName("noTransaksi"); // NOI18N
        FormInput.add(noTransaksi);
        noTransaksi.setBounds(85, 10, 110, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Jumlah Nominal : Rp.");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(750, 40, 170, 23);

        telahTerimaPAS.setForeground(new java.awt.Color(0, 0, 0));
        telahTerimaPAS.setName("telahTerimaPAS"); // NOI18N
        telahTerimaPAS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                telahTerimaPASKeyPressed(evt);
            }
        });
        FormInput.add(telahTerimaPAS);
        telahTerimaPAS.setBounds(925, 100, 315, 23);

        Scroll3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        Keterangan.setColumns(20);
        Keterangan.setRows(5);
        Keterangan.setName("Keterangan"); // NOI18N
        Keterangan.setPreferredSize(new java.awt.Dimension(170, 500));
        Keterangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(Keterangan);

        FormInput.add(Scroll3);
        Scroll3.setBounds(85, 100, 660, 130);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Kategori : ");
        jLabel14.setToolTipText("");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 40, 80, 23);

        labelSewa.setForeground(new java.awt.Color(0, 0, 0));
        labelSewa.setText("Jumlah Bayar Sewa : Rp.");
        labelSewa.setName("labelSewa"); // NOI18N
        FormInput.add(labelSewa);
        labelSewa.setBounds(750, 70, 170, 23);

        nominalSewa.setEditable(false);
        nominalSewa.setForeground(new java.awt.Color(0, 0, 0));
        nominalSewa.setText("0");
        nominalSewa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nominalSewa.setName("nominalSewa"); // NOI18N
        FormInput.add(nominalSewa);
        nominalSewa.setBounds(925, 70, 139, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Telah terima (Atas Nama Lain) :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(750, 128, 170, 23);

        telahTerimaAN.setForeground(new java.awt.Color(0, 0, 0));
        telahTerimaAN.setName("telahTerimaAN"); // NOI18N
        FormInput.add(telahTerimaAN);
        telahTerimaAN.setBounds(925, 128, 315, 23);

        label_pajak.setForeground(new java.awt.Color(0, 0, 0));
        label_pajak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_pajak.setText("pajak");
        label_pajak.setName("label_pajak"); // NOI18N
        FormInput.add(label_pajak);
        label_pajak.setBounds(1070, 70, 280, 23);

        label_nominal.setForeground(new java.awt.Color(0, 0, 0));
        label_nominal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_nominal.setText("nominal");
        label_nominal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label_nominal.setName("label_nominal"); // NOI18N
        FormInput.add(label_nominal);
        label_nominal.setBounds(1070, 40, 280, 23);

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

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        Sequel.cariIsi("select no_transaksi from pemasukan_lain where no_transaksi=? ", cekNoTransaksi, noTransaksi.getText());

        if (Keterangan.getText().trim().equals("")) {
            Valid.textKosong(Keterangan, "Keterangan");
        } else if (KdPtg.getText().trim().equals("") || NmPtg.getText().trim().equals("")) {
            Valid.textKosong(KdPtg, "Petugas Kasir");
        } else if (KdKategori.getText().trim().equals("") || NmKategori.getText().trim().equals("")) {
            Valid.textKosong(KdKategori, "Kategori Pemasukan");
        } else if (pemasukan.getText().trim().equals("") || pemasukan.getText().trim().equals("0")) {
            Valid.textKosong(pemasukan, "Jumlah nominal penerimaan");
        } else if (telahTerimaPAS.getText().trim().equals("")) {
            Valid.textKosong(telahTerimaPAS, "Telah terima dari/Nama Pembayar");
        } else if (!cekNoTransaksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Transaksi tersebut sudah tersimpan dilaporan kasir..!!!!");
            emptTeks();
        } else {
            autoNomorTransaksi();
            Sequel.AutoComitFalse();
            if (KdKategori.getText().equals("SWKTN") || (cekNoTransaksi.getText().equals(""))) {
                hitungSewaTempat();
                simpanpemasukan();
                tampil();
                emptTeks();

                //kondisi menyimpan selain transaksi selisih tarif naik kls rawat
            } else if (!KdKategori.getText().equals("SWKTN") && (cekNoTransaksi.getText().equals(""))) {
                simpanpemasukan();
                tampil();
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, pemasukan, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            DTPCari1.requestFocus();
        } else {
            if (tbPemasukan.getSelectedRow() > -1) {
                WindowHapus.setSize(692, 121);
                WindowHapus.setLocationRelativeTo(internalFrame1);
                WindowHapus.setVisible(true);
                TketHapus.setText("");
                TketHapus.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
                tbPemasukan.requestFocus();
            }
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnAll);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowAmbulan.dispose();
        WindowSelisihTarif.dispose();
        WindowHapus.dispose();        
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnHapus, TCari);
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
        emptTeks();
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
        emptTeks();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnCari, NmPtg);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbPemasukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemasukanMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPemasukanMouseClicked

    private void tbPemasukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemasukanKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPemasukanKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt, Keterangan, pemasukan);
    }//GEN-LAST:event_TanggalKeyPressed

    private void pemasukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pemasukanKeyPressed
        if (pemasukan.getText().equals("0") || pemasukan.getText().equals("")) {
            nominalSewa.setText("0");
        } else {
            if (KdKategori.getText().equals("SWKTN")) {
                hitungSewaTempat();
                Valid.pindah(evt, Tanggal, telahTerimaPAS);
            } else {
                Valid.pindah(evt, Tanggal, telahTerimaPAS);
            }
        }
    }//GEN-LAST:event_pemasukanKeyPressed

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
        Valid.pindah(evt, KdPtg, BtnSimpan);
    }//GEN-LAST:event_btnPetugasKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        akses.setform("DlgPemasukanLain");
        petugas.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        petugas.isCek();
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        Sequel.cariIsiComboDB("SELECT nm_perawatan FROM jns_perawatan WHERE kd_kategori='ambl' AND STATUS='1'", CmbTarif);
        Sequel.cariIsiComboDB("select kelas from kamar group by kelas", cmbNaikKls);
    }//GEN-LAST:event_formWindowOpened

    private void KdKategoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdKategoriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nama_kategori from kategori_pemasukan_lain where kode_kategori=?", NmKategori, KdKategori.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnKategoriActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, KdPtg);
        }
    }//GEN-LAST:event_KdKategoriKeyPressed

    private void btnKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKategoriActionPerformed
        akses.setform("DlgPemasukanLain");
        kategori.emptTeks();
        kategori.tampil();
        kategori.isCek();
        kategori.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        kategori.setLocationRelativeTo(internalFrame1);
        kategori.setVisible(true);
        kategori.validasiUser();
    }//GEN-LAST:event_btnKategoriActionPerformed

    private void btnKategoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnKategoriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKategoriKeyPressed

    private void CmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJamKeyPressed
        Valid.pindah(evt, Tanggal, CmbMenit);
    }//GEN-LAST:event_CmbJamKeyPressed

    private void CmbMenitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbMenitKeyPressed
        Valid.pindah(evt, CmbJam, CmbDetik);
    }//GEN-LAST:event_CmbMenitKeyPressed

    private void CmbDetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbDetikKeyPressed
        Valid.pindah(evt, CmbMenit, pemasukan);
    }//GEN-LAST:event_CmbDetikKeyPressed

    private void telahTerimaPASKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telahTerimaPASKeyPressed
        Valid.pindah(evt, pemasukan, Keterangan);
    }//GEN-LAST:event_telahTerimaPASKeyPressed

    private void KeteranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKeyPressed
        Valid.pindah(evt, telahTerimaPAS, BtnSimpan);
    }//GEN-LAST:event_KeteranganKeyPressed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowSelisihTarif.dispose();
        selisihBaru();
        emptTeks();
        tampil();
        ChkInput.setSelected(true);
        isForm();
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void BtnSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan6ActionPerformed
        Sequel.cariIsi("select no_transaksi from pemasukan_lain where no_rawat=? ", cekNoTransaksi, norawat.getText());
        Sequel.cariIsi("select no_transaksi from biaya_naik_kelas_bpjs where no_transaksi=? ", noTranAngsur, cekNoTransaksi.getText());
        
        if (KdPtg.getText().trim().equals("") || NmPtg.getText().trim().equals("")) {
            Valid.textKosong(KdPtg, "Petugas Kasir");
        } else if (KdKategori.getText().trim().equals("") || NmKategori.getText().trim().equals("")) {
            Valid.textKosong(KdKategori, "Kategori Pemasukan");
        } else if ((norm.getText().trim().equals("")) && (nmpasien.getText().trim().equals(""))
                && (nokartu.getText().trim().equals("")) && (norawat.getText().trim().equals(""))) {
            Valid.textKosong(telahTerimaPAS, "Data Pasien");
            NoSEP.requestFocus();
        } else if (kdINACBG.getText().trim().equals("") && deskripsiKD.getText().trim().equals("")) {
            Valid.textKosong(kdINACBG, "Kode & deskripsi INACBG");
            NoSEP.requestFocus();            
        } else if (hakkelas.getText().equals("3")) {
            JOptionPane.showMessageDialog(null, "Sesuai Permenkes RI No. 3 Tahun 2023, utk. hak kelas 3 tdk. diperkenankan lagi naik kls. rawat..!!!");
            NoSEP.requestFocus();
        } else if (jlhdibayar.getText().equals("0") || (jlhdibayar.getText().equals("0.0") || (jlhdibayar.getText().equals("")))) {
            JOptionPane.showMessageDialog(null, "Nominal angka pembayaran harus diisi dengan benar,...!!!");
            ChkSesuaiTagihan.requestFocus();
        } else if (!cekNoTransaksi.getText().trim().equals("") && (noTranAngsur.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Ini adalah transaksi lama, data sudah tersimpan dilaporan kasir..!!!!");
            NoSEP.requestFocus();
            selisihBaru();
        } else if (jlhdibayar.getText().trim().equals(byrSimpan.getText()) && (ChkSesuaiTagihan.isSelected() == false)) {
            JOptionPane.showMessageDialog(null, "Nominal angka pembayaran sdh. sesuai tagihan, conteng dulu jenis pembayaranya..!!!!");
            ChkSesuaiTagihan.requestFocus();
        } else if ((cmbNaikKls.getSelectedItem().equals("Kelas VIP") || cmbNaikKls.getSelectedItem().equals("Kelas VVIP"))
                && Sequel.cariInteger("select count(-1) from piutang_pasien where no_rawat='" + norawat.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Transaksi gagal tersimpan,..!! \n"
                    + "Simpan dulu Nota/Kwitansinya utk. melanjutkan proses penghitungan selisih biaya perawatan..!!");
            NoSEP.requestFocus();
        } else {
            Sequel.cariIsi("select total_byr from pemasukan_lain where no_rawat=? ", statusSELISIH, norawat.getText());

            if (sisaTagihan >= Double.parseDouble(jlhdibayar.getText())) {
                autoNomorTransaksi();                
                Sequel.AutoComitFalse();
                try {
                    stlhByr = sisaTagihan - Double.parseDouble(jlhdibayar.getText());
                    
                    Sequel.menyimpan("pemasukan_lain", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Pemasukan", 27, new String[]{
                        Valid.SetTgl(Tanggal.getSelectedItem() + ""),
                        KdKategori.getText(), pemasukan.getText(), KdPtg.getText(), Keterangan.getText(),
                        CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem(),
                        noTransaksi.getText(), telahTerimaPAS.getText(),
                        NoSEP.getText(), norm.getText(), nokartu.getText(), norawat.getText(), tglmsk.getText(),
                        tglklr.getText(), rginap.getText(), kdINACBG.getText(), tarifkls1.getText(),
                        tarifkls2.getText(), tarifkls3.getText(), hakkelas.getText(), cmbNaikKls.getSelectedItem().toString(), lmrawat.getText(),
                        persenSELISIH.getText(), rumusbayar.getText(), jlhdibayar.getText(), nominalPajakSewa.getText(), totalbyrsewa.getText()
                    });

                    Sequel.menyimpan("biaya_naik_kelas_bpjs", "?,?,?,?,?,?,?,?,?,?,?,?", "Biaya", 12, new String[]{
                        Valid.SetTgl(Tanggal.getSelectedItem() + ""), CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem(),
                        norawat.getText(), NoSEP.getText(), byrSimpan.getText(), (String.valueOf(sdhByr)), (String.valueOf(sisaTagihan)),
                        jlhdibayar.getText(), (String.valueOf(stlhByr)), (String.valueOf(byrKe)), noTransaksi.getText(), statusTran.getText()
                    });
                    
                    Sequel.mengedit("bridging_sep", "no_sep='" + cekNoSEP.getText() + "' and jnspelayanan='1'", "kode_inacbg='" + kdINACBG.getText() + "' ");
                    Sequel.mengedit("bridging_sep_backup", "no_sep='" + cekNoSEP.getText() + "' and jnspelayanan='1'", "kode_inacbg='" + kdINACBG.getText() + "' ");

                    Sequel.queryu("delete from tampjurnal");
                    
                    dataSelisihTarif.setText(Sequel.cariIsi("SELECT COUNT(*) FROM biaya_naik_kelas_bpjs WHERE sisa_setelah_byr='0' AND status_transaksi='dicicil' "));
                    if (!dataSelisihTarif.getText().equals("0")) {
                        Sequel.mengedit("biaya_naik_kelas_bpjs", "no_transaksi='" + noTransaksi.getText() + "'", "status_transaksi='lunas' ");
                    }
                    psakun.setString(1, KdKategori.getText());
                    rs = psakun.executeQuery();
                    if (rs.next()) {
                        Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(1), rs.getString(2), "0", pemasukan.getText()});
                        Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(3), rs.getString(4), pemasukan.getText(), "0"});
                        jur.simpanJurnal("-", Valid.SetTgl(Tanggal.getSelectedItem() + ""), "U", "PEMASUKAN LAIN-LAIN, Petugas : " + NmPtg.getText());
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
                Sequel.AutoComitTrue();
                JOptionPane.showMessageDialog(null, "Biaya selisih tarif INACBG sudah tersimpan, berikutnya bisa dilanjutkan ke proses panjar atau jaminan...!!!");

//                WindowSelisihTarif.dispose();
                tampil();
//                selisihBaru();
//                emptTeks();                
                ChkInput.setSelected(true);
                isForm();
            } else {
                JOptionPane.showMessageDialog(null, "Pembayaran lebih besar dari sisa tagihan atau tagihan pasien tersebut sudah lunas...!!!");
                selisihBaru();
            }
        }
    }//GEN-LAST:event_BtnSimpan6ActionPerformed

    private void NoSEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSEPKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select no_sep from bridging_sep where no_sep=? ", cekNoSEP, "1704R0" + NoSEP.getText());

            if (cekNoSEP.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "No. SEP BPJS salah/tidak ditemukan..!!");
                NoSEP.requestFocus();
            } else if (NoSEP.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "No. SEP BPJS belum diisi..!!");
                NoSEP.requestFocus();
                selisihBaru();
            } else if (!cekNoSEP.getText().equals("")) {                
                cekSEP();
                cekINACBG();
                cekPanjar();
                telahTerimaPAS.setText(nmpasien.getText() + " (" + norm.getText() + ")");
                BtnSimpan6.requestFocus();

                if (deskripsiKD.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Kode INACBG salah atau belum terisi, ulangi lagi..!!!");
                    kdINACBG.requestFocus();
                    kdINACBG.setText("");
                } else if ((cmbNaikKls.getSelectedItem().equals("Kelas VIP") || cmbNaikKls.getSelectedItem().equals("Kelas VVIP"))
                        && Sequel.cariInteger("select count(-1) from piutang_pasien where no_rawat='" + norawat.getText() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Simpan dulu Nota/Kwitansinya utk. melanjutkan proses penghitungan selisih biaya perawatan..!!");
                    NoSEP.requestFocus();
                } else if (!deskripsiKD.getText().equals("")) {
                    if (hakkelas.getText().equals("1")) {
                        hitungSelisih();
                        cektagihan();
                        pemasukan.setText(byrSimpan.getText());
                        Keterangan.setText("Pembayaran biaya selisih tarif naik kelas rawat inap pasien BPJS");
                    } else if (hakkelas.getText().equals("2")) {
                        hitungSelisih();
                        cektagihan();
                        pemasukan.setText(byrSimpan.getText());
                        Keterangan.setText("Pembayaran biaya selisih tarif naik kelas rawat inap pasien BPJS");
                    } 
//                    else if (hakkelas.getText().equals("3")) {
//                        hitungSelisih();
//                        cektagihan();
//                        pemasukan.setText(byrSimpan.getText());
//                        Keterangan.setText("Pembayaran biaya selisih tarif naik kelas rawat inap pasien BPJS");
//                    }
                }
            }
        }
    }//GEN-LAST:event_NoSEPKeyPressed

    private void kdINACBGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdINACBGKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (kdINACBG.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Kode INACBG belum diisi..!!");
                kdINACBG.requestFocus();
            } else {
                cekINACBG();
                lmrawat.setText(Sequel.cariIsi("select if(sum(ki.lama) is null,'0',sum(ki.lama)) from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar where "
                        + "ki.no_rawat='" + norawat.getText() + "' and k.kelas='" + cmbNaikKls.getSelectedItem().toString() + "'"));

                if (deskripsiKD.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Kode INACBG salah, ulangi lagi..!!!");
                    kdINACBG.requestFocus();
                    kdINACBG.setText("");
                } else if ((cmbNaikKls.getSelectedItem().equals("Kelas VIP") || cmbNaikKls.getSelectedItem().equals("Kelas VVIP"))
                        && Sequel.cariInteger("select count(-1) from piutang_pasien where no_rawat='" + norawat.getText() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Simpan dulu Nota/Kwitansinya utk. melanjutkan proses penghitungan selisih biaya perawatan..!!");
                    NoSEP.requestFocus();
                } else if (!deskripsiKD.getText().equals("")) {
                    if (hakkelas.getText().equals("1")) {
                        hitungSelisih();
                        pemasukan.setText(byrSimpan.getText());
                        Keterangan.setText("Biaya tambahan tarif naik kelas ruang rawat inap pasien BPJS di ruang " + rginap.getText() + " sesuai kode INACBG " + kdINACBG.getText() + " dg. penghitungan " + TKalkulasi.getText());
                    } else if (hakkelas.getText().equals("2")) {
                        hitungSelisih();
                        pemasukan.setText(byrSimpan.getText());
                        Keterangan.setText("Biaya tambahan tarif naik kelas ruang rawat inap pasien BPJS di ruang " + rginap.getText() + " sesuai kode INACBG " + kdINACBG.getText() + " dg. penghitungan " + TKalkulasi.getText());
                    } else if (hakkelas.getText().equals("3")) {
                        JOptionPane.showMessageDialog(null, "Sesuai Permenkes RI No. 3 Tahun 2023, utk. hak kelas 3 tdk. diperkenankan lagi naik kls. rawat..!!!");
                        NoSEP.requestFocus();
                    }
//                    else if (hakkelas.getText().equals("3")) {
//                        hitungSelisih();
//                        pemasukan.setText(byrSimpan.getText());
//                        Keterangan.setText("Biaya tambahan tarif naik kelas ruang rawat inap pasien BPJS di ruang " + rginap.getText() + " sesuai kode INACBG " + kdINACBG.getText() + " dg. penghitungan " + TKalkulasi.getText());
//                    }
                }
            }
        }
    }//GEN-LAST:event_kdINACBGKeyPressed

    private void kdINACBGKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdINACBGKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_kdINACBGKeyTyped

    private void NoSEPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSEPKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_NoSEPKeyTyped

    private void BtnSelisihBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSelisihBaruActionPerformed
        selisihBaru();
    }//GEN-LAST:event_BtnSelisihBaruActionPerformed

    private void lihatSelisihTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lihatSelisihTarifActionPerformed
        Sequel.cariIsi("select no_transaksi from pemasukan_lain where no_rawat=? ", cekNoTransaksi, norawat.getText());
        Sequel.cariIsi("select no_transaksi from biaya_naik_kelas_bpjs where no_transaksi=? ", noTranAngsur, cekNoTransaksi.getText());
        
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pemasukan lain-lain tidak ada...!!!!");
            DTPCari1.requestFocus();
        } else if (noTransaksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (NoSEP.getText().equals("-")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu pemasukan lain yg. termasuk jenis selisih tarif INACBG dg. mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (KdKategori.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (!KdKategori.getText().trim().equals("SBPJS")) {
            JOptionPane.showMessageDialog(null, "Transaksi pembayaran tidak sesuai...!!!");
            tbPemasukan.requestFocus();
        } else {
            WindowSelisihTarif.setSize(813, 688);
            WindowSelisihTarif.setLocationRelativeTo(internalFrame1);
            WindowSelisihTarif.setVisible(true);

            BtnCloseIn6.requestFocus();
            BtnSimpan6.setEnabled(false);
            BtnPrint.setEnabled(true);
            cekSEP();
            cekINACBG();
            cekPanjar();
            hitungSelisih();
            cektagihan();

            if (WindowSelisihTarif.isVisible() == true) {
                BtnSimpan.setVisible(false);
                BtnBatal.setVisible(false);
            } else if (WindowSelisihTarif.isVisible() == false) {
                BtnSimpan.setVisible(true);
                BtnBatal.setVisible(true);
            }
            
            if (!cekNoTransaksi.getText().trim().equals("") && (noTranAngsur.getText().equals(""))) {
                jLabel51.setVisible(false);
                Sudahbyr.setVisible(false);
                jLabel52.setVisible(false);
                SisaTagihan.setVisible(false);
                jLabel53.setVisible(false);
                ChkSesuaiTagihan.setVisible(false);
                jlhdibayar.setVisible(false);
            } else {
                jLabel51.setVisible(true);
                Sudahbyr.setVisible(true);
                jLabel52.setVisible(true);
                SisaTagihan.setVisible(true);
                jLabel53.setVisible(true);
                ChkSesuaiTagihan.setVisible(true);
                jlhdibayar.setVisible(true);
            }
        }
    }//GEN-LAST:event_lihatSelisihTarifActionPerformed

    private void RekapSelisihLunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RekapSelisihLunasActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            dataSelisihTarif.setText(Sequel.cariIsi("SELECT COUNT(DISTINCT no_sep) cekpasien FROM pemasukan_lain WHERE tanggal BETWEEN "
                    + " '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "));

            if (dataSelisihTarif.getText().equals("0")) {
                JOptionPane.showMessageDialog(null, "Untuk pelunasan selisih tarif naik kls. rwt pasien BPJS Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem() + " datanya tidak ditemukan...!!!");
                emptTeks();
                selisihBaru();
            } else if (!dataSelisihTarif.getText().equals("0")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
                Valid.MyReport("rptRekapSelisihTarifBPJS.jasper", "report", "::[ Laporan Rekap Selisih Tarif Naik Kelas BPJS (Pelunasan) ]::",
                        " select DISTINCT i.no_rawat, CONCAT(r.no_rkm_medis,' - ',p.nm_pasien) noRM_pasien, DATE_FORMAT(pl.tanggal,'%d/%m/%Y') tgl_byr, "
                        + " concat('Kelas ',pl.hak_kelas) hak_kls, pl.naik_kelas naik_kekls, pl.kode_inacbg, format(IFNULL(sum(l.totalbiaya),''),0) ttl_rincian_RC, "
                        + " case when b.nm_bangsal like '%VIP%' then pl.besar else 0 end VIP, "
                        + " case when b.nm_bangsal like '%Al-Khaliq%' then pl.besar else 0 end Bersalin, "
                        + " case when b.nm_bangsal like '%Al-Hakim%' then pl.besar else 0 end Paru, "
                        + " case when b.nm_bangsal like '%Al-Muiz%' then pl.besar else 0 end Bedah, "
                        + " case when b.nm_bangsal like '%Ar-Rahman%' then pl.besar else 0 end Anak, "
                        + " case when b.nm_bangsal like '%Ar-Razaq%' then pl.besar else 0 end RKPD, "
                        + " case when b.nm_bangsal like '%As-Sami%' then pl.besar else 0 end Assami, "
                        + " case when b.nm_bangsal like '%Ar-Raudah/Syaraf%' then pl.besar else 0 end Arraudah_Syaraf, "
                        + " case when b.nm_bangsal like '%Ar-Raudah/Mata%' then pl.besar else 0 end Arraudah_MTKK, "
                        + " pl.besar total_selisih from pemasukan_lain pl "
                        + " left join kamar_inap i on i.no_rawat = pl.no_rawat "
                        + " left join reg_periksa r on i.no_rawat = r.no_rawat "
                        + " left join kamar k on k.kd_kamar = i.kd_kamar "
                        + " left join bangsal b on b.kd_bangsal = k.kd_bangsal "
                        + " left join billing l on i.no_rawat = l.no_rawat "
                        + " left join pasien p on p.no_rkm_medis=r.no_rkm_medis "
                        + " where pl.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + " and pl.kode_kategori='sbpjs' and i.stts_pulang not in ('-','Pindah Kamar') "
                        + " group by l.no_rawat, pl.no_transaksi ORDER BY pl.tanggal", param);
                this.setCursor(Cursor.getDefaultCursor());
                emptTeks();
                selisihBaru();
                tampil();
            }
        }
    }//GEN-LAST:event_RekapSelisihLunasActionPerformed

    private void RekapPemasukanLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RekapPemasukanLainActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if ((KdKategori.getText().equals("SBPJS")) || (KdKategori.getText().equals("SWKTN"))) {
            JOptionPane.showMessageDialog(null, "Untuk mencetak laporan selisih tarif naik kelas BPJS atau sewa tempat sudah ada pilihannya, cek lagi...!!!!");
            DTPCari1.requestFocus();
            emptTeks();
            tampil();
        } else if ((tabMode.getRowCount() != 0) && (!KdKategori.getText().equals("SBPJS"))) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            if (KdKategori.getText().equals("")) {
                Valid.MyReport("rptPemasukanLain.jasper", "report", "::[ Data Pemasukan Lain-Lain ]::",
                        " SELECT pl.no_transaksi, kpl.nama_kategori, p.nama petugas, pl.keterangan ket_byr, pl.besar "
                        + " FROM pemasukan_lain pl INNER JOIN petugas p on p.nip=pl.nip INNER JOIN kategori_pemasukan_lain kpl on kpl.kode_kategori=pl.kode_kategori "
                        + " WHERE pl.tanggal BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + " and pl.kode_kategori not in ('sbpjs','swktn') order by pl.tanggal ", param);
            } else if ((!KdKategori.getText().equals("")) || (!KdKategori.getText().equals("SBPJS"))) {
                Valid.MyReport("rptPemasukanLain.jasper", "report", "::[ Data Pemasukan Lain-Lain ]::",
                        " SELECT pl.no_transaksi, kpl.nama_kategori, p.nama petugas, pl.keterangan ket_byr, pl.besar "
                        + " FROM pemasukan_lain pl INNER JOIN petugas p on p.nip=pl.nip INNER JOIN kategori_pemasukan_lain kpl on kpl.kode_kategori=pl.kode_kategori "
                        + " WHERE pl.tanggal BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + " and pl.kode_kategori not in ('sbpjs','swktn') and pl.kode_kategori='" + KdKategori.getText() + "' order by pl.tanggal ", param);
            }
            this.setCursor(Cursor.getDefaultCursor());
            emptTeks();
            selisihBaru();
            tampil();
        }
    }//GEN-LAST:event_RekapPemasukanLainActionPerformed

    private void RekapSewaTempatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RekapSewaTempatActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            dataSelisihTarif.setText(Sequel.cariIsi("SELECT COUNT(no_transaksi) total FROM pemasukan_lain WHERE tanggal BETWEEN "
                    + " '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND kode_kategori='swktn' "));

            if (dataSelisihTarif.getText().equals("0")) {
                JOptionPane.showMessageDialog(null, "Untuk transaksi sewa tempat/kantin pada tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem() + " datanya tidak ditemukan...!!!");
                emptTeks();
                selisihBaru();
                tampil();
            } else if (!dataSelisihTarif.getText().equals("0")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
                Valid.MyReport("rptPemasukanSewa.jasper", "report", "::[ Laporan Rekap Pemasukan Sewa Tempat ]::",
                        " SELECT pl.no_transaksi, DATE_FORMAT(pl.tanggal,'%d/%m/%Y') tgl_byr, p.nama petugas, pl.keterangan ket_byr, "
                        + " pl.telah_terima_dari nm_pembayar, pl.besar hrg_sewa, convert(IFNULL(pl.nominal_pajak_sewa,'0'),int) as nominal_pajak_sewa,"
                        + " convert(IFNULL(pl.nominal_sewa,'0'),int) as ttl_byr "
                        + " FROM pemasukan_lain pl INNER JOIN petugas p on p.nip=pl.nip "
                        + " INNER JOIN kategori_pemasukan_lain kpl on kpl.kode_kategori=pl.kode_kategori "
                        + " WHERE pl.tanggal BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + " and pl.kode_kategori='swktn' ORDER BY pl.tanggal", param);
                this.setCursor(Cursor.getDefaultCursor());
                emptTeks();
                selisihBaru();
                tampil();
            }
        }
    }//GEN-LAST:event_RekapSewaTempatActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (kdINACBG.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kode INACBG masih kosong...!!!!");
            NoSEP.requestFocus();

        } else if (!kdINACBG.getText().equals("")) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));            
            Valid.MyReport("rptTarifINACBG.jasper", "report", "::[ Lampiran Kwitansi (Tarif INACBG) ]::",
                    "SELECT b.INACBG, a.description_pmk_59_2014, b.KELAS_RAWAT, convert((b.TARIFF),int) tarifnya FROM inacbg_unucbg_2016 a "
                    + "INNER JOIN inacbg_tariff_20230124 b on b.INACBG = a.code "
                    + "WHERE b.REGIONAL='reg4' AND b.KODE_TARIFF='bp' AND b.JENIS_PELAYANAN='1' "
                    + "AND b.INACBG='" + kdINACBG.getText() + "' order by b.KELAS_RAWAT asc", param);
            this.setCursor(Cursor.getDefaultCursor());

            WindowSelisihTarif.dispose();
            tampil();
            selisihBaru();
            emptTeks();
            ChkInput.setSelected(true);
            isForm();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void ChkSesuaiTagihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSesuaiTagihanActionPerformed
        cektagihan();
    }//GEN-LAST:event_ChkSesuaiTagihanActionPerformed

    private void RekapSelisihAngsurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RekapSelisihAngsurActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            dataSelisihTarif.setText(Sequel.cariIsi("SELECT COUNT(DISTINCT no_sep) cekpasien FROM biaya_naik_kelas_bpjs WHERE (total_tagihan <> jumlah_byr) and tgl_transaksi BETWEEN "
                                +" '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "));
            
            if (dataSelisihTarif.getText().equals("0")) {
                JOptionPane.showMessageDialog(null, "Untuk angsuran selisih tarif naik kls. rwt pasien BPJS Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem()+ " datanya tidak ditemukan...!!!");
                emptTeks();
                selisihBaru();
            } else if (!dataSelisihTarif.getText().equals("0")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
                Valid.MyReport("rptRekapSelisihTarifBPJSAngsur.jasper", "report", "::[ Laporan Rekap Selisih Tarif Naik Kelas BPJS (Angsuran) ]::",
                        " SELECT DISTINCT bn.no_rawat, bn.no_transaksi, p.nm_pasien, rp.no_rkm_medis no_rm, pl.ruang_inap, "
                        + " CONCAT(DATE_FORMAT(pl.tgl_masuk,'%d/%m/%Y'),' s.d ',DATE_FORMAT(pl.tgl_pulang,'%d/%m/%Y') ) tgl_msk_klr, "
                        + " CONCAT(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', ',kb.nm_kab) alamat, p.no_tlp, format(pl.besar,0) tot_tagihan, "
                        + " CONCAT(DATE_FORMAT(bn.tgl_transaksi,'%d/%m/%Y'),' Angs. Ke ',bn.pembayaran_ke) pembayaran, "
                        + " bn.jumlah_byr, bn.sisa_setelah_byr, UPPER(bn.status_transaksi) stts_tran, "
                        + " (SELECT COUNT(DISTINCT no_rawat) total FROM biaya_naik_kelas_bpjs where (total_tagihan <> jumlah_byr) "
                        + " and tgl_transaksi BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') total_px "
                        + " FROM pemasukan_lain pl INNER JOIN reg_periksa rp ON rp.no_rawat=pl.no_rawat "
                        + " INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                        + " INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel "
                        + " INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec "
                        + " INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab "
                        + " INNER JOIN kamar_inap ki ON ki.no_rawat=pl.no_rawat "
                        + " INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + " INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                        + " INNER JOIN biaya_naik_kelas_bpjs bn ON bn.no_transaksi=pl.no_transaksi "
                        + " INNER JOIN bridging_sep bs ON bs.no_rawat=pl.no_rawat "
                        + " WHERE pl.tanggal between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and "
                        + " pl.kode_kategori='sbpjs' and ki.stts_pulang not in ('-','Pindah Kamar') and (pl.besar <> bn.jumlah_byr) ORDER BY bn.no_rawat, bn.tgl_transaksi, bn.pembayaran_ke", param);
                this.setCursor(Cursor.getDefaultCursor());
                emptTeks();
                selisihBaru();
                tampil();
            }
        }
    }//GEN-LAST:event_RekapSelisihAngsurActionPerformed

    private void BtnSimpan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan4ActionPerformed
        if (Tjarak.getText().equals("") || Tjarak.getText().equals("0")) {
            Valid.textKosong(Tjarak, "Jarak Tujuan");
            Tjarak.requestFocus();
        } else if (TtujuanAlamat.getText().equals("")) {
            Valid.textKosong(TtujuanAlamat, "Alamat Tujuan");
            TtujuanAlamat.requestFocus();
        } else if ((Double.parseDouble(Tjarak.getText()) > 15 && CmbTarif.getSelectedIndex() == 4)
                || (Double.parseDouble(Tjarak.getText()) > 15 && CmbTarif.getSelectedIndex() == 0)) {
            JOptionPane.showMessageDialog(null, "Jenis pilihan tarif yg. dipilih salah, karena jarak tujuan > 15 Km. ..!!");
            CmbTarif.requestFocus();
        } else {
            autoNomorTransaksi();
            Sequel.AutoComitFalse();
            hitungAmbulanNew();
            simpanpemasukan();            
            tampil();
            emptTeks();
            ChkInput.setSelected(true);
            isForm();
        }
    }//GEN-LAST:event_BtnSimpan4ActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowAmbulan.dispose();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void CmbTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbTarifActionPerformed
        tarifAmbulan = 0;
        if (CmbTarif.getSelectedIndex() == 0) {
            Ttarif.setText("0");
            tarifAmbulan = 0;
            hitungAmbulanNew();
        } else {
            tarifAmbulan = Sequel.cariIsiAngka("select total_byrdrpr FROM jns_perawatan WHERE "
                    + "kd_kategori='ambl' AND STATUS='1' and nm_perawatan='" + CmbTarif.getSelectedItem().toString() + "'"); 
            Ttarif.setText(Valid.SetAngka(tarifAmbulan));
            hitungAmbulanNew();
        }
    }//GEN-LAST:event_CmbTarifActionPerformed

    private void TjarakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjarakKeyPressed
        Valid.pindah(evt, TtujuanAlamat, CmbTarif);
    }//GEN-LAST:event_TjarakKeyPressed

    private void TnormKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnormKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tnmpasien.setText(Sequel.cariIsi("select ifnull(nm_pasien,'-') from pasien where no_rkm_medis='" + Tnorm.getText() + "'"));
            TtujuanAlamat.requestFocus();
        }
    }//GEN-LAST:event_TnormKeyPressed

    private void TtujuanAlamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtujuanAlamatKeyPressed
        Valid.pindah(evt, Tnorm, Tjarak);
    }//GEN-LAST:event_TtujuanAlamatKeyPressed

    private void RekapAmbulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RekapAmbulanActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(-1) from pemasukan_lain where "
                    + "tanggal BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "and kode_kategori='AMBLN'") == 0) {
                JOptionPane.showMessageDialog(null, "Data pembayaran transportasi ambulan tdk. ditemukan pada periode tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem() + " ...!!!!");
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
                param.put("periode", "Periode Tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
                Valid.MyReport("rptPemasukanAmbulan.jasper", "report", "::[ Data Pembayaran Transportasi Ambulance ]::",
                        "SELECT pl.no_transaksi, date_format(pl.tanggal,'%d/%m/%Y') tgltransaksi, time_format(pl.jam_penerimaan,'%H:%i') jam, "
                        + "pl.telah_terima_dari, pl.keterangan ket_byr, p.nama petugas, pl.besar "
                        + "FROM pemasukan_lain pl INNER JOIN petugas p on p.nip=pl.nip "
                        + "INNER JOIN kategori_pemasukan_lain kpl on kpl.kode_kategori=pl.kode_kategori "
                        + "WHERE pl.tanggal BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + "and pl.kode_kategori='ambln' order by pl.tanggal, pl.jam_penerimaan", param);
                this.setCursor(Cursor.getDefaultCursor());
                emptTeks();
                selisihBaru();
                tampil();
            }
        }
    }//GEN-LAST:event_RekapAmbulanActionPerformed

    private void TnmpasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmpasienKeyPressed
        Valid.pindah(evt, Tnorm, TtujuanAlamat);
    }//GEN-LAST:event_TnmpasienKeyPressed

    private void BtnHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHitungActionPerformed
        CmbTarifActionPerformed(null);
    }//GEN-LAST:event_BtnHitungActionPerformed

    private void TjlhBiayaPihak3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjlhBiayaPihak3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            CmbTarifActionPerformed(null);
        }
    }//GEN-LAST:event_TjlhBiayaPihak3KeyPressed

    private void BtnPanjarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPanjarActionPerformed
        if (norm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (Sequel.cariSelisihTarifInacbg(norawat.getText()) > 0) {
                akses.setform("DlgPemasukanLain");
                DlgTransaksiPanjar panjar = new DlgTransaksiPanjar(null, false);
                panjar.emptTeks();
                panjar.isCek();
                panjar.setData(norawat.getText(), norm.getText(), nmpasien.getText(), rginap.getText(),
                        Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norawat.getText() + "'"),
                        Totdibayar.getText(), tarifrc.getText(), "0");
                panjar.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                panjar.setLocationRelativeTo(internalFrame1);
                panjar.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Hitungan biaya selisih tarif INACBG belum tersimpan, simpan dulu proses ini    \n"
                        + "kemudian bisa dilanjutkan ke panjar atau jaminan..!!");
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPanjarActionPerformed

    private void pemasukanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pemasukanKeyReleased
        if (pemasukan.getText().equals("0") || pemasukan.getText().equals("")) {
            label_nominal.setText("Rp. 0");
        } else {
            label_nominal.setText("Rp. " + Valid.SetAngka(Double.parseDouble(pemasukan.getText())));
        }
        pemasukanKeyPressed(null);
    }//GEN-LAST:event_pemasukanKeyReleased

    private void BtnJaminanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnJaminanActionPerformed
        if (norm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgPemasukanLain");
            DlgJaminanTransaksi jaminan = new DlgJaminanTransaksi(null, false);            
            jaminan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            jaminan.setLocationRelativeTo(internalFrame1);
            jaminan.isCek();
            jaminan.emptTeks();
            jaminan.setData(norawat.getText(), norm.getText(), nmpasien.getText(), rginap.getText());
            jaminan.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnJaminanActionPerformed

    private void TketHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnHapus1.requestFocus();
        }
    }//GEN-LAST:event_TketHapusKeyPressed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        if (TketHapus.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Keterangan/alasan menghapus harus diisi dengan benar..!!");
            TketHapus.requestFocus();
        } else if (TketHapus.getText().trim().length() < 3) {
            JOptionPane.showMessageDialog(null, "Keterangan/alasan menghapus harus diisi dengan keadaan yang sebenarnya..!!");
            TketHapus.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                simpanHistoriHapusPemasukan();
            } else {
                emptTeks();
                tampil();
                WindowHapus.dispose();
            }
        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnHapus1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapus1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnHapus1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnHapus1KeyPressed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowHapus.dispose();
        tampil();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCloseIn12.requestFocus();
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        Valid.tabelKosong(tabMode2);
        tampilRiwayat();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari2ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari2, BtnAll2);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void BtnAll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll2ActionPerformed
        Valid.tabelKosong(tabMode2);
        TCari2.setText("");
        tampilRiwayat();
    }//GEN-LAST:event_BtnAll2ActionPerformed

    private void BtnAll2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAll2ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari2, TCari2);
        }
    }//GEN-LAST:event_BtnAll2KeyPressed

    private void BtnRestorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRestorActionPerformed
        if (tbRiwayat.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data yang dipilih & telah dihapus akan dikembalikan/restore..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.cariInteger("select count(-1) from pemasukan_lain where "
                        + "no_transaksi='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString() + "'") > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Proses kembalikan/restore data gagal, krn. sudah ada datanya dg. no. transaksi yg. sama..!!");
                    Valid.tabelKosong(tabMode2);
                    tampilRiwayat();
                } else {
                    kembalikanData();
                    tampil();
                    emptTeks();
                }
            } else {
                Valid.tabelKosong(tabMode2);
                tampilRiwayat();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
            tbRiwayat.requestFocus();
        }
    }//GEN-LAST:event_BtnRestorActionPerformed

    private void BtnCloseIn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn12ActionPerformed
        WindowRiwayat.dispose();
        TCari2.setText("");
        tampil();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn12ActionPerformed

    private void tbRiwayatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRiwayat();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRiwayatKeyPressed

    private void tbRiwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataRiwayat();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwayatMouseClicked

    private void MnDataSampahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataSampahActionPerformed
        DTPCari3.setDate(new Date());
        DTPCari4.setDate(new Date());
        TCari2.setText("");
        BtnCari2ActionPerformed(null);
        WindowRiwayat.setSize(949, internalFrame1.getHeight() - 40);
        WindowRiwayat.setLocationRelativeTo(internalFrame1);
        WindowRiwayat.setAlwaysOnTop(false);
        WindowRiwayat.setVisible(true);
    }//GEN-LAST:event_MnDataSampahActionPerformed

    private void MnTTDcnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTTDcnActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pemasukan lain-lain tidak ada...!!!!");
            DTPCari1.requestFocus();
        } else if (noTransaksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (KdKategori.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else {
            tte = "";
            tte = "tidak";
            cetakNota();
        }
    }//GEN-LAST:event_MnTTDcnActionPerformed

    private void MnTTEcnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTTEcnActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pemasukan lain-lain tidak ada...!!!!");
            DTPCari1.requestFocus();
        } else if (noTransaksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (KdKategori.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else {
            tte = "";
            tte = "ya";
            cetakNota();
        }
    }//GEN-LAST:event_MnTTEcnActionPerformed

    private void MnTTDcnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTTDcnaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tabel masih kosong...!!!!");
            DTPCari1.requestFocus();
        } else if (noTransaksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (KdKategori.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (!KdKategori.getText().trim().equals("AMBLN")) {
            JOptionPane.showMessageDialog(null, "Pilihan cetak nota/kwitansi pembayaran salah...!!!");
            tbPemasukan.requestFocus();
        } else {
            tte = "";
            tte = "tidak";
            cetakNotaAmbulan();
        }
    }//GEN-LAST:event_MnTTDcnaActionPerformed

    private void MnTTEcnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTTEcnaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tabel masih kosong...!!!!");
            DTPCari1.requestFocus();
        } else if (noTransaksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (KdKategori.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (!KdKategori.getText().trim().equals("AMBLN")) {
            JOptionPane.showMessageDialog(null, "Pilihan cetak nota/kwitansi pembayaran salah...!!!");
            tbPemasukan.requestFocus();
        } else {
            tte = "";
            tte = "ya";
            cetakNotaAmbulan();
        }
    }//GEN-LAST:event_MnTTEcnaActionPerformed

    private void MnTTDcnnkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTTDcnnkActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tabel masih kosong...!!!!");
            DTPCari1.requestFocus();
        } else if (noTransaksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (KdKategori.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (!KdKategori.getText().trim().equals("SBPJS")) {
            JOptionPane.showMessageDialog(null, "Pilihan cetak nota/kwitansi pembayaran salah...!!!");
            tbPemasukan.requestFocus();
        } else {
            tte = "";
            tte = "tidak";            
            cetakNotaNaikKls();
        }
    }//GEN-LAST:event_MnTTDcnnkActionPerformed

    private void MnTTEcnnkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTTEcnnkActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tabel masih kosong...!!!!");
            DTPCari1.requestFocus();
        } else if (noTransaksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (KdKategori.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (!KdKategori.getText().trim().equals("SBPJS")) {
            JOptionPane.showMessageDialog(null, "Pilihan cetak nota/kwitansi pembayaran salah...!!!");
            tbPemasukan.requestFocus();
        } else {
            tte = "";
            tte = "ya";
            cetakNotaNaikKls();
        }
    }//GEN-LAST:event_MnTTEcnnkActionPerformed

    private void cmbNaikKlsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNaikKlsActionPerformed
        if (kdINACBG.getText().trim().equals("")) {
            kdINACBG.requestFocus();
        } else {
            cekINACBG();
            lmrawat.setText(Sequel.cariIsi("select if(sum(ki.lama) is null,'0',sum(ki.lama)) from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar where "
                    + "ki.no_rawat='" + norawat.getText() + "' and k.kelas='" + cmbNaikKls.getSelectedItem().toString() + "'"));

            if (deskripsiKD.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Kode INACBG salah, ulangi lagi..!!!");
                kdINACBG.requestFocus();
                kdINACBG.setText("");
            } else if (!deskripsiKD.getText().equals("")) {
                if (hakkelas.getText().equals("1")) {
                    hitungSelisih();
                    cektagihan();
                } else if (hakkelas.getText().equals("2")) {
                    hitungSelisih();
                    cektagihan();
                } else if (hakkelas.getText().equals("3")) {
                    JOptionPane.showMessageDialog(null, "Sesuai Permenkes RI No. 3 Tahun 2023, utk. hak kelas 3 tdk. diperkenankan lagi naik kls. rawat..!!!");
                    BtnCloseIn6.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_cmbNaikKlsActionPerformed

    private void MnKirimKeWaNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKirimKeWaNotaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pemasukan lain-lain tidak ada...!!!!");
            DTPCari1.requestFocus();
        } else if (noTransaksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (KdKategori.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else {
            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            akses.setform("DlgPemasukanLain");
            DlgKirimWhatsapp form = new DlgKirimWhatsapp(null, false);
            form.setSize(557, 148);
            form.setLocationRelativeTo(internalFrame1);
            form.emptTeks();
            form.setData("nota lain-lain", norw, tglNota.getSelectedItem().toString(), telahTerimaPAS.getText());
            form.jaminanTrans(KdKategori.getText(), noTransaksi.getText(), "", "", "", "", "", "", "", "", "");
            form.dataKirim();
            form.setVisible(true);
            form.toFront();
            form.requestFocus();
        }
    }//GEN-LAST:event_MnKirimKeWaNotaActionPerformed

    private void MnKirimKeWaNota1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKirimKeWaNota1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pemasukan lain-lain tidak ada...!!!!");
            DTPCari1.requestFocus();
        } else if (noTransaksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (KdKategori.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (!KdKategori.getText().trim().equals("AMBLN")) {
            JOptionPane.showMessageDialog(null, "Data yang dipilih bukan transaksi pembayaran ambulan...");
            tbPemasukan.requestFocus();
        } else {
            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            akses.setform("DlgPemasukanLain");
            DlgKirimWhatsapp form = new DlgKirimWhatsapp(null, false);
            form.setSize(557, 148);
            form.setLocationRelativeTo(internalFrame1);
            form.emptTeks();
            form.setData("nota ambulan", norw, tglNota.getSelectedItem().toString(), telahTerimaPAS.getText());
            form.jaminanTrans(KdKategori.getText(), noTransaksi.getText(), "", "", "", "", "", "", "", "", "");
            form.dataKirim();
            form.setVisible(true);
            form.toFront();
            form.requestFocus();
        }
    }//GEN-LAST:event_MnKirimKeWaNota1ActionPerformed

    private void MnKirimKeWaNota2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKirimKeWaNota2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tabel masih kosong...!!!!");
            DTPCari1.requestFocus();
        } else if (noTransaksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (KdKategori.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu pemasukan lain-lain dengan mengklik data pada tabel...!!!");
            tbPemasukan.requestFocus();
        } else if (!KdKategori.getText().trim().equals("SBPJS")) {
            JOptionPane.showMessageDialog(null, "Pilihan cetak nota/kwitansi pembayaran salah...!!!");
            tbPemasukan.requestFocus();
        } else {
            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            akses.setform("DlgPemasukanLain");
            DlgKirimWhatsapp form = new DlgKirimWhatsapp(null, false);
            form.setSize(557, 148);
            form.setLocationRelativeTo(internalFrame1);
            form.emptTeks();
            form.setData("nota naik kelas", norw, tglNota.getSelectedItem().toString(), telahTerimaPAS.getText());
            form.jaminanTrans(KdKategori.getText(), noTransaksi.getText(), Keterangan.getText(), bayarKe.getText(), "", "", "", "", "", "", "");
            form.dataKirim();
            form.setVisible(true);
            form.toFront();
            form.requestFocus();
        }
    }//GEN-LAST:event_MnKirimKeWaNota2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPemasukanLain dialog = new DlgPemasukanLain(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll2;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari2;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn12;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCloseIn6;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnHitung;
    private widget.Button BtnJaminan;
    private widget.Button BtnKeluar;
    private widget.Button BtnPanjar;
    private widget.Button BtnPrint;
    private widget.Button BtnRestor;
    private widget.Button BtnSelisihBaru;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan4;
    private widget.Button BtnSimpan6;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJln;
    private widget.CekBox ChkSesuaiTagihan;
    private widget.ComboBox CmbDetik;
    private widget.ComboBox CmbJam;
    private widget.ComboBox CmbMenit;
    private widget.ComboBox CmbTarif;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.PanelBiasa FormInput;
    private widget.TextBox KdKategori;
    private widget.TextBox KdPtg;
    private widget.TextArea Keterangan;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnDataSampah;
    private javax.swing.JMenuItem MnKirimKeWaNota;
    private javax.swing.JMenuItem MnKirimKeWaNota1;
    private javax.swing.JMenuItem MnKirimKeWaNota2;
    private javax.swing.JMenu MnLaporan;
    private javax.swing.JMenuItem MnTTDcn;
    private javax.swing.JMenuItem MnTTDcna;
    private javax.swing.JMenuItem MnTTDcnnk;
    private javax.swing.JMenuItem MnTTEcn;
    private javax.swing.JMenuItem MnTTEcna;
    private javax.swing.JMenuItem MnTTEcnnk;
    private widget.TextBox NmKategori;
    private widget.TextBox NmPtg;
    private widget.TextBox NoSEP;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JMenuItem RekapAmbulan;
    private javax.swing.JMenuItem RekapPemasukanLain;
    private javax.swing.JMenuItem RekapSelisihAngsur;
    private javax.swing.JMenuItem RekapSelisihLunas;
    private javax.swing.JMenuItem RekapSewaTempat;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll39;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.TextBox SisaTagihan;
    private widget.TextBox Sudahbyr;
    private widget.TextBox TCari;
    private widget.TextBox TCari2;
    private widget.TextArea TKalkulasi;
    private widget.Tanggal Tanggal;
    private widget.TextBox Tjarak;
    private widget.TextBox TjlhBayar;
    private widget.TextBox TjlhBiayaPihak3;
    private widget.TextBox TketHapus;
    private widget.TextBox Tnmpasien;
    private widget.TextBox TnoPanjar;
    private widget.TextBox TnominalPanjar;
    private widget.TextBox Tnorm;
    private widget.TextBox Totdibayar;
    private widget.TextBox TselisihJrk;
    private widget.TextBox Ttarif;
    private widget.TextBox TtarifSelisihJrk;
    private widget.TextBox TtglPanjar;
    private widget.TextBox TtotBayar;
    private widget.TextBox TtujuanAlamat;
    private javax.swing.JDialog WindowAmbulan;
    private javax.swing.JDialog WindowHapus;
    private javax.swing.JDialog WindowRiwayat;
    private javax.swing.JDialog WindowSelisihTarif;
    private widget.TextBox bayarKe;
    private widget.TextBox belumDibayar;
    private widget.Button btnKategori;
    private widget.Button btnPetugas;
    private widget.TextBox byrSimpan;
    private widget.TextBox cekNoSEP;
    private widget.TextBox cekNoTransaksi;
    private javax.swing.JMenu cetakNota;
    private javax.swing.JMenu cetakNotaAmbulan;
    private javax.swing.JMenu cetakNotaNaikKls;
    private widget.ComboBox cmbNaikKls;
    private widget.TextBox dataSelisihTarif;
    private widget.TextBox deskripsiKD;
    private widget.TextBox hakkelas;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame20;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
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
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
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
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox jlhdibayar;
    private widget.TextBox jumlhBayar;
    private widget.TextBox kdINACBG;
    private widget.Label labelSewa;
    private widget.Label label_nominal;
    private widget.Label label_pajak;
    private widget.Label labelbyr;
    private javax.swing.JMenuItem lihatSelisihTarif;
    private widget.TextBox lmrawat;
    private widget.Label lunas;
    private widget.TextBox nmpasien;
    private widget.TextBox noTranAngsur;
    private widget.TextBox noTransaksi;
    private widget.TextBox nokartu;
    private widget.TextBox nominalPajakSewa;
    private widget.TextBox nominalSewa;
    private widget.TextBox norawat;
    private widget.TextBox norm;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.TextBox pemasukan;
    private widget.TextBox persenSELISIH;
    private widget.TextBox persenSewa;
    private widget.TextBox realcostRS;
    private widget.TextBox rginap;
    private widget.TextBox rumusbayar;
    private widget.TextBox statusSELISIH;
    private widget.TextBox statusTran;
    private widget.TextBox tarifkls1;
    private widget.TextBox tarifkls2;
    private widget.TextBox tarifkls3;
    private widget.TextBox tarifrc;
    private widget.Table tbPemasukan;
    private widget.Table tbRiwayat;
    private widget.Table tbRiwayat1;
    private widget.TextBox telahTerimaAN;
    private widget.TextBox telahTerimaPAS;
    private widget.Tanggal tglNota;
    private widget.TextBox tglklr;
    private widget.TextBox tglmsk;
    private widget.TextBox totalbyrsewa;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(" SELECT pl.no_transaksi, pl.tanggal, pl.jam_penerimaan, kpl.nama_kategori, p.nama, pl.besar, "
                    + "if(pl.kode_kategori='AMBLN',pl.keterangan,concat(pl.keterangan,' angsuran ke ',IFNULL(b.pembayaran_ke,'-'),' (',UPPER(IFNULL(b.status_transaksi,'-')),')')) keterangan, "
                    + "IFNULL(pl.telah_terima_dari,'-') pembayar, pl.no_sep, pl.no_rkm_medis, pl.no_kartu, pl.no_rawat, pl.tgl_masuk, pl.tgl_pulang, "
                    + "pl.ruang_inap, pl.kode_inacbg, pl.trf_kls1, pl.trf_kls2, pl.trf_kls3, pl.hak_kelas, pl.naik_kelas, pl.lm_rawat, pl.persen_tambahan, pl.rumus_selisih_tarif, "
                    + "pl.total_byr, pl.nominal_pajak_sewa, pl.nominal_sewa, IFNULL(b.sudah_dibayar,'0') sdb_dibyr, IFNULL(b.sisa_tagihan,'0') sisa_tag, "
                    + "IFNULL(b.jumlah_byr,'0') jlh_byr, IFNULL(b.sisa_setelah_byr,'0') sisa_stlh_byr FROM pemasukan_lain pl INNER JOIN petugas p ON p.nip=pl.nip "
                    + "INNER JOIN kategori_pemasukan_lain kpl ON kpl.kode_kategori=pl.kode_kategori LEFT JOIN biaya_naik_kelas_bpjs b ON b.no_transaksi=pl.no_transaksi WHERE "
                    + "pl.tanggal between ? and ? and pl.keterangan like ? or "
                    + "pl.tanggal between ? and ? and pl.nip like ? or "
                    + "pl.tanggal between ? and ? and p.nama like ? or "
                    + "pl.tanggal between ? and ? and pl.kode_kategori like ? or "
                    + "pl.tanggal between ? and ? and pl.no_sep like ? or "
                    + "pl.tanggal between ? and ? and pl.no_rkm_medis like ? or "
                    + "pl.tanggal between ? and ? and pl.no_kartu like ? or "
                    + "pl.tanggal between ? and ? and pl.no_rawat like ? or "
                    + "pl.tanggal between ? and ? and pl.naik_kelas like ? or "
                    + "pl.tanggal between ? and ? and b.status_transaksi like ? or "
                    + "pl.tanggal between ? and ? and pl.persen_tambahan like ? or "
                    + "pl.tanggal between ? and ? and kpl.nama_kategori like ? order by pl.tanggal desc, pl.jam_penerimaan desc");
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
                ps.setString(34, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(35, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(36, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString(1), 
                        rs.getString(2), 
                        rs.getString(3),
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getDouble(6),
                        rs.getString(7), 
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getDouble(17),
                        rs.getDouble(18),
                        rs.getDouble(19),
                        rs.getString(20),
                        rs.getString(21),
                        rs.getString(22),
                        rs.getDouble(23),
                        rs.getString(24),
                        rs.getDouble(25),
                        rs.getDouble(26),
                        rs.getDouble(27),
                        rs.getDouble(28),
                        rs.getDouble(29),
                        rs.getDouble(30),
                        rs.getDouble(31),
                        rs.getString("besar")
                    });
                    total = total + rs.getDouble(6);
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
        LCount.setText(("" + (tabMode.getRowCount() - 1)).replaceAll("-1", "0"));
    }

    public void emptTeks() {
        norw = "";
        pemasukan.setText("0");
        label_nominal.setText("Rp. 0");
        Keterangan.setText("");
        KdKategori.setText("");
        NmKategori.setText("");
        telahTerimaPAS.setText("-");
        telahTerimaAN.setText("-");
        Tanggal.setDate(new Date());
        ChkJln.setSelected(true);
        ChkJln.setEnabled(false);
        autoNomorTransaksi();
        BtnSimpan.setVisible(true);
        BtnBatal.setVisible(true);
        persenSewa.setText("0");
        nominalPajakSewa.setText("0");
        nominalSewa.setText("0");
        labelSewa.setVisible(false);
        nominalSewa.setVisible(false);
        label_pajak.setVisible(false);
        totalbyrsewa.setText("0");
        cekNoTransaksi.setText("");
        bayarKe.setText("");
        jumlhBayar.setText("");
        belumDibayar.setText("");
        dataSelisihTarif.setText("");
        
        jLabel51.setVisible(true);
        Sudahbyr.setVisible(true);
        jLabel52.setVisible(true);
        SisaTagihan.setVisible(true);
        jLabel53.setVisible(true);
        ChkSesuaiTagihan.setVisible(true);
        jlhdibayar.setVisible(true);
    }

    private void getData() {
        ChkJln.setSelected(false);
        norw = "";
        if (tbPemasukan.getSelectedRow() != -1) {
            noTransaksi.setText(tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 0).toString());
            NmKategori.setText(tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 3).toString());
            NmPtg.setText(tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 4).toString());
            pemasukan.setText(Valid.SetAngka3(Double.parseDouble(tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 31).toString())));            
            Sequel.cariIsi("select kode_kategori from kategori_pemasukan_lain where nama_kategori=?", KdKategori, tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 3).toString());
            Sequel.cariIsi("select nip from petugas where nama=?", KdPtg, tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 4).toString());
            Valid.SetTgl(Tanggal, tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 1).toString());
            CmbJam.setSelectedItem(tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 2).toString().substring(0, 2));
            CmbMenit.setSelectedItem(tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 2).toString().substring(3, 5));
            CmbDetik.setSelectedItem(tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 2).toString().substring(6, 8));
            telahTerimaPAS.setText(tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 7).toString());

            NoSEP.setText(tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 8).toString());
            norawat.setText(tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 11).toString());
            Sequel.cariIsi("select no_sep from bridging_sep where no_sep=? ", cekNoSEP, "1704R0" + NoSEP.getText());
            kdINACBG.setText(tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 15).toString());
            rumusbayar.setText(tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 23).toString());
            Sequel.cariIsi("select no_rawat from biaya_naik_kelas_bpjs where no_transaksi=? ", dataSelisihTarif, noTransaksi.getText());
            norw = Sequel.cariIsi("select no_rawat from biaya_naik_kelas_bpjs where no_transaksi='" + noTransaksi.getText() + "'");
            telahTerimaAN.setText(Sequel.cariIsi("select p_jawab from reg_periksa where no_rawat='" + norw + "'"));

            if (pemasukan.getText().equals("0") || pemasukan.getText().equals("")) {
                label_nominal.setText("Rp. 0");
            } else {
                label_nominal.setText("Rp. " + Valid.SetAngka(Double.parseDouble(pemasukan.getText())));
            }

            if (KdKategori.getText().equals("SWKTN")) {
                persenSewa.setText(Sequel.cariIsi("select pajak_sewa_tempat from set_tarif"));
                label_pajak.setText("Sudah Ditambahkan Pajak " + persenSewa.getText() + " %");
                label_pajak.setVisible(true);
                hitungSewaTempat();
                labelSewa.setVisible(true);
                nominalSewa.setVisible(true);
            } else if (!KdKategori.getText().equals("SWKTN")) {
                labelSewa.setVisible(false);
                nominalSewa.setVisible(false);
            }
            
            if (KdKategori.getText().equals("SBPJS") && (!dataSelisihTarif.getText().equals(""))) {
                Sequel.cariIsi("select pembayaran_ke from biaya_naik_kelas_bpjs where no_transaksi=? ", bayarKe, noTransaksi.getText());
                Sequel.cariIsi("select jumlah_byr from biaya_naik_kelas_bpjs where no_transaksi=? ", jumlhBayar, noTransaksi.getText());
                Sequel.cariIsi("select sisa_setelah_byr from biaya_naik_kelas_bpjs where no_transaksi=? ", belumDibayar, noTransaksi.getText());                
                statusTran.setText(Sequel.cariIsi("select status_transaksi from biaya_naik_kelas_bpjs where no_transaksi='" + noTransaksi.getText() + "'"));
                Sequel.cariIsi("select total_tagihan from biaya_naik_kelas_bpjs where no_transaksi=? ", Totdibayar, noTransaksi.getText());
                
                DecimalFormat df4 = new DecimalFormat("####");
                double ii = Double.parseDouble(jumlhBayar.getText().trim());
                double jj = Double.parseDouble(belumDibayar.getText().trim());
                double kk = Double.parseDouble(Totdibayar.getText().trim());                
                
                cekNoSEP.setText(Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + norw + "'"));
                cekSEP();
                cekINACBG();
                hitungSelisih();
                
                if (statusTran.getText().equals("dicicil")) {
                    Keterangan.setText("Jumlah pembayaran angsuran ke " + bayarKe.getText() + " Rp. " + Valid.SetAngka3(ii) 
                            + ", dan sisa tagihan yg. belum dibayar adalah Rp. " + Valid.SetAngka3(jj));
                } else if (statusTran.getText().equals("lunas")) {
//                    Keterangan.setText("Jumlah pembayaran utk. pelunasan tagihan dgn. kalkulasi penghitungan sebagai beikut :\n"+ TKalkulasi.getText());
                    Keterangan.setText(tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 6).toString());
                }

            } else if (KdKategori.getText().equals("SBPJS") && (dataSelisihTarif.getText().equals(""))) {
                Keterangan.setText(tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 6).toString());
            } else {
                Keterangan.setText(tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 6).toString());
            }
        }
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 265));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void isCek() {
        tglNota.setDate(new Date());
        BtnSimpan.setEnabled(akses.getpemasukan_lain());
        BtnHapus.setEnabled(akses.getpic_keuangan());
        MnDataSampah.setEnabled(akses.getadmin());

        if (akses.getadmin() == true) {
            btnPetugas.setEnabled(true);
            KdPtg.setText("-");
            NmPtg.setText("-");
        } else {
            btnPetugas.setEnabled(false);
            KdPtg.setText(akses.getkode());
            Sequel.cariIsi("select nama from petugas where nip=?", NmPtg, KdPtg.getText());
        }
    }

    private void jam() {
        ActionListener taskPerformer = new ActionListener() {
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;

            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";

                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if (ChkJln.isSelected() == true) {
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                } else if (ChkJln.isSelected() == false) {
                    nilai_jam = CmbJam.getSelectedIndex();
                    nilai_menit = CmbMenit.getSelectedIndex();
                    nilai_detik = CmbDetik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                CmbJam.setSelectedItem(jam);
                CmbMenit.setSelectedItem(menit);
                CmbDetik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    public void autoNomorTransaksi() {
        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(no_transaksi,4),signed)),0) from pemasukan_lain where "
                + "tanggal like '%" + Valid.SetTgl(Tanggal.getSelectedItem() + "").substring(0, 7) + "%' ", "/PL/" + Valid.SetTgl(Tanggal.getSelectedItem() + "").substring(5, 7)
                + "/" + Valid.SetTgl(Tanggal.getSelectedItem() + "").substring(0, 4), 4, noTransaksi);
    }

    public void cekSEP() {        
        norw = "";
        try {
            psSEP = koneksi.prepareStatement("select nomr, kode_inacbg, nama_pasien, no_kartu, no_rawat, "
                    + "klsrawat from bridging_sep where jnspelayanan='1' and no_sep='" + cekNoSEP.getText() + "'");
            try {
                rsSEP = psSEP.executeQuery();
                while (rsSEP.next()) {
                    norm.setText(rsSEP.getString("nomr"));
                    kdINACBG.setText(rsSEP.getString("kode_inacbg"));
                    nmpasien.setText(rsSEP.getString("nama_pasien"));
                    nokartu.setText(rsSEP.getString("no_kartu"));
                    norawat.setText(rsSEP.getString("no_rawat"));
                    hakkelas.setText(rsSEP.getString("klsrawat"));
                    norw = rsSEP.getString("no_rawat");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsSEP != null) {
                    rsSEP.close();
                }
                if (psSEP != null) {
                    psSEP.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        
        Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=? ", tglmsk, norawat.getText());
        Sequel.cariIsi("select tgl_keluar from kamar_inap where stts_pulang not in ('-','Pindah Kamar') and no_rawat=? ", tglklr, norawat.getText());
        
        if (Sequel.cariIsi("select ifnull(totalpiutang,'') from piutang_pasien where no_rawat='" + norw + "'").equals("")) {
            realcostRS.setText("0");
        } else {
            realcostRS.setText(Sequel.cariIsi("select ifnull(totalpiutang,'0') from piutang_pasien where no_rawat='" + norw + "'"));
        }
        tarifrc.setText(Valid.SetAngka3(Double.parseDouble(realcostRS.getText())));
        
        cmbNaikKls.setSelectedItem(Sequel.cariIsi("SELECT k.kelas FROM kamar_inap ki INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE stts_pulang not in ('-','Pindah Kamar') and ki.no_rawat='" + norawat.getText() + "' "
                + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));
        lmrawat.setText(Sequel.cariIsi("select if(sum(ki.lama) is null,'0',sum(ki.lama)) from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar where "
                + "ki.no_rawat='" + norawat.getText() + "' and k.kelas='" + cmbNaikKls.getSelectedItem().toString() + "'"));
        
        Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar_inap ki INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE ki.stts_pulang not in ('-','Pindah Kamar') and ki.no_rawat=? ", rginap, norawat.getText());
        
        persenSELISIH.setText(Sequel.cariIsi("select selisih_tarif_bpjs2 from set_tarif"));
        
        if (cmbNaikKls.getSelectedItem().equals("Intensif")) {
            lmrawat.setText(Sequel.cariIsi("select if(sum(ki.lama) is null,'0',sum(ki.lama)) from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar where "
                + "ki.no_rawat='" + norawat.getText() + "' and k.kelas='" + cmbNaikKls.getSelectedItem().toString() + "'"));
        }

        if ((hakkelas.getText().equals("1")) && (cmbNaikKls.getSelectedItem().equals("Kelas 1"))
                || (hakkelas.getText().equals("2")) && (cmbNaikKls.getSelectedItem().equals("Kelas 2"))
                || (hakkelas.getText().equals("3")) && (cmbNaikKls.getSelectedItem().equals("Kelas 3"))) {
            BtnCloseIn6ActionPerformed(null);
            JOptionPane.showMessageDialog(null, "Pasien sudah sesuai hak kelasnya...!!!");
            selisihBaru();
        } else if (cmbNaikKls.getSelectedItem().equals("Intensif")) {
            BtnCloseIn6ActionPerformed(null);
            JOptionPane.showMessageDialog(null, "Ruang rawat ICU,ICCU atau NICU tidak dianggap naik kelas rawat..!!!");
            selisihBaru();
        } else if (cmbNaikKls.getSelectedItem().equals("")) {
            BtnCloseIn6ActionPerformed(null);
            JOptionPane.showMessageDialog(null, "Pasien belum pulang, petugas ruang inap harus memulangkan dulu...!!!");
            selisihBaru();
        }
    }

    public void cekINACBG() {
        Sequel.cariIsi("SELECT description FROM inacbg_unucbg_2016 WHERE code=? ", deskripsiKD, kdINACBG.getText());
        Sequel.cariIsi("SELECT TARIFF FROM inacbg_tariff_20230124 WHERE REGIONAL='reg4' and KODE_TARIFF='bp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='1' and INACBG=? ", tarifkls1, kdINACBG.getText());
        Sequel.cariIsi("SELECT TARIFF FROM inacbg_tariff_20230124 WHERE REGIONAL='reg4' and KODE_TARIFF='bp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='2' and INACBG=? ", tarifkls2, kdINACBG.getText());
        Sequel.cariIsi("SELECT TARIFF FROM inacbg_tariff_20230124 WHERE REGIONAL='reg4' and KODE_TARIFF='bp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='3' and INACBG=? ", tarifkls3, kdINACBG.getText());
    
        if (kdINACBG.getText().equals("") && deskripsiKD.getText().equals("")) {
            tarifkls1.setText("0");
            tarifkls2.setText("0");
            tarifkls3.setText("0");
        } else {
            Sequel.cariIsi("SELECT TARIFF FROM inacbg_tariff_20230124 WHERE REGIONAL='reg4' and KODE_TARIFF='bp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='1' and INACBG=? ", tarifkls1, kdINACBG.getText());
            Sequel.cariIsi("SELECT TARIFF FROM inacbg_tariff_20230124 WHERE REGIONAL='reg4' and KODE_TARIFF='bp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='2' and INACBG=? ", tarifkls2, kdINACBG.getText());
            Sequel.cariIsi("SELECT TARIFF FROM inacbg_tariff_20230124 WHERE REGIONAL='reg4' and KODE_TARIFF='bp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='3' and INACBG=? ", tarifkls3, kdINACBG.getText());
        }
    }

    public void selisihBaru() {
        cekNoSEP.setText("");
        NoSEP.setText("");
        norm.setText("");
        nmpasien.setText("");
        nokartu.setText("");
        norawat.setText("");
        hakkelas.setText("");
        tglmsk.setText("");
        tglklr.setText("");
        rginap.setText("");
        kdINACBG.setText("");
        deskripsiKD.setText("");
        statusTran.setText("");
        noTranAngsur.setText("");

        tarifkls1.setText("0");
        tarifkls2.setText("0");
        tarifkls3.setText("0");
        Totdibayar.setText("0");
        persenSELISIH.setText("0");
        labelbyr.setText("Total bayar : Rp. ");
        lmrawat.setText("0");
        cmbNaikKls.setSelectedIndex(0);
        byrSimpan.setText("");
        rumusbayar.setText("");
        statusSELISIH.setText("");
        Sudahbyr.setText("0");
        SisaTagihan.setText("0");   
        jlhdibayar.setText("");
        TKalkulasi.setText("-");
        tarifrc.setText("0");
//        realcostRS.setText("0");
        
        totTagihan = 0;
        sdhByr = 0;
        sisaTagihan = 0;
        byrKe = 0;
        
        lunas.setVisible(false);
        ChkSesuaiTagihan.setText("Pembayaran sesuai tagihan >>>");
        ChkSesuaiTagihan.setSelected(true);
        ChkSesuaiTagihan.setEnabled(true);        
    }

    public void hitungSelisih() {
        totTagihan = 0;
        sdhByr = 0;
        sisaTagihan = 0;
        byrKe = 0;
        rumus1 = 0;
        rumus2 = 0;
        rumus3 = 0;
        hasilrumus = 0;
        hasilmaksimal = 0;

        DecimalFormat df4 = new DecimalFormat("####");
        double a = Double.parseDouble(tarifkls1.getText().trim());
        double b = Double.parseDouble(tarifkls2.getText().trim());
        double c = Double.parseDouble(tarifkls3.getText().trim());
        double d = Double.parseDouble(persenSELISIH.getText());
        double e = Double.parseDouble(realcostRS.getText());
        cekData = Sequel.cariInteger("select count(1) from biaya_naik_kelas_bpjs where no_sep ='" + NoSEP.getText() + "'");

        if (cekData <= 0) {
            byrKe += 1;

            if (hakkelas.getText().equals("2") && cmbNaikKls.getSelectedItem().equals("Kelas 1")) {
                TKalkulasi.setText("tarif INACBG : Kelas 1 Rp. " + Valid.SetAngka3(a) + " - Kelas 2 Rp. " + Valid.SetAngka3(b) + " = Rp. " + Valid.SetAngka3(a - b) + "\n"
                        + "Jadi yang harus dibayar pasien adalah Rp. " + Valid.SetAngka3(a - b) + "");

                Totdibayar.setText(Valid.SetAngka3(a - b));
                rumusbayar.setText("Tarif INACBG kls. 1 Rp. " + Valid.SetAngka3(a) + " - Tarif INACBG kls. 2 Rp. " + Valid.SetAngka3(b) + "");
                byrSimpan.setText(df4.format(a - b));
                SisaTagihan.setText(Valid.SetAngka3(a - b));
                sisaTagihan = a - b;

            } else if (hakkelas.getText().equals("2") && (cmbNaikKls.getSelectedItem().equals("Kelas VIP") || cmbNaikKls.getSelectedItem().equals("Kelas VVIP"))) {
                //tarif inacbg kelas 1 - kelas 2
                rumus1 = a - b;
                //tarif inacbg kelas 1 x 75 %
                rumus2 = d / 100 * a;
                //hasil perhitunganya
                hasilrumus = rumus1 + rumus2;
                //hitung tarif inacbg kelas 2 + hasilrumus
                hasilmaksimal = b + hasilrumus;

                //jika tarif real cost RS < tarif inacbg maka GRATIS
                if (e < b) {
                    TKalkulasi.setText("Karena Real Cost RS kurang dari tarif INACBG kelas 2 maka tidak ada\n"
                            + "penambahan selisih biaya perawatan.");
                    Totdibayar.setText("0");
                    rumusbayar.setText("Tidak ada penambahan selisih biaya perawatan");
                    byrSimpan.setText("0");
                    SisaTagihan.setText("0");
                    sisaTagihan = 0;

                    //jika tarif real cost RS > dari tarif perhitungan permenkes no. 3 tahun 2023
                } else if (e > hasilmaksimal) {
                    TKalkulasi.setText("Rumus Tarif INACBG    #   (Kelas 1 - Kelas 2) + (" + persenSELISIH.getText() + " % x Kelas 1)\n"
                            + "A. Kelas 1 - Kelas 2      #   Rp. " + Valid.SetAngka3(a) + " - Rp. " + Valid.SetAngka3(b) + " = Rp. " + Valid.SetAngka3(rumus1) + "\n"
                            + "B. " + persenSELISIH.getText() + " % x Kelas 1        #   " + persenSELISIH.getText() + " % x Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus2) + "\n"
                            + "C. Jadi hasilnya adalah Rp. " + Valid.SetAngka3(hasilrumus) + "\n"
                            + "D. Tarif maksimal         #   Kelas 2 + hasil perhitungan rumus poin C.\n"
                            + "                                    #   Rp. " + Valid.SetAngka3(b) + " + Rp. " + Valid.SetAngka3(hasilrumus) + " = Rp. " + Valid.SetAngka3(hasilmaksimal) + "\n"
                            + "----------------------------------------------------------------------------------------------------------------------------------------\n"
                            + "Kesimpulan : Karena tarif Real Cost RS melebihi dari tarif maksimal penghitungan\n"
                            + "                     sesuai Permenkes No. 3 Tahun 2023 maka, yang harus dibayarkan\n"
                            + "                     sesuai dengan Permenkes No. 3 Tahun 2023 adalah Rp. " + Valid.SetAngka3(hasilrumus) + "");

                    Totdibayar.setText(Valid.SetAngka3(hasilrumus));
                    rumusbayar.setText("Sesuaikan dengan Permenkes No. 3 Tahun 2023 seperti rincian pada kalkulasi tarif");
                    byrSimpan.setText(df4.format(hasilrumus));
                    SisaTagihan.setText(Valid.SetAngka3(hasilrumus));
                    sisaTagihan = hasilrumus;

                    //jika tarif inacbg kls 2 < real cost RS sampai batas maksimal tarif perhitungan permenkes no. 3 tahun 2023
                } else if ((b < e) && (e <= hasilmaksimal)) {
                    rumus3 = e - b;
                    TKalkulasi.setText("Rumus Tarif INACBG    #   (Kelas 1 - Kelas 2) + (" + persenSELISIH.getText() + " % x Kelas 1)\n"
                            + "A. Kelas 1 - Kelas 2      #   Rp. " + Valid.SetAngka3(a) + " - Rp. " + Valid.SetAngka3(b) + " = Rp. " + Valid.SetAngka3(rumus1) + "\n"
                            + "B. " + persenSELISIH.getText() + " % x Kelas 1        #   " + persenSELISIH.getText() + " % x Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus2) + "\n"
                            + "C. Jadi hasilnya adalah Rp. " + Valid.SetAngka3(hasilrumus) + "\n"
                            + "D. Tarif maksimal         #   Kelas 2 + hasil perhitungan rumus poin C.\n"
                            + "                                    #   Rp. " + Valid.SetAngka3(b) + " + Rp. " + Valid.SetAngka3(hasilrumus) + " = Rp. " + Valid.SetAngka3(hasilmaksimal) + "\n"
                            + "----------------------------------------------------------------------------------------------------------------------------------------\n"
                            + "Kesimpulan : Karena tarif INACBG Kelas 2 kurang dari tarif Real Cost RS dan sampai batas tarif maksimal\n"
                            + "                     penghitungan sesuai Permenkes No. 3 Tahun 2023 maka biaya, yang harus dibayarkan adalah\n"
                            + "                     tarif real cost RS - tarif INACBG Kelas 2 sebagai berikut :\n"
                            + "                     Rp. " + Valid.SetAngka3(e) + " - Rp. " + Valid.SetAngka3(b) + " = Rp. " + Valid.SetAngka3(rumus3) + "\n"
                            + "                     Jadi yang harus dibayarkan sesuai dengan Permenkes No. 3 Tahun 2023 adalah Rp. " + Valid.SetAngka3(rumus3) + "");

                    Totdibayar.setText(Valid.SetAngka3(rumus3));
                    rumusbayar.setText("Sesuaikan dengan Permenkes No. 3 Tahun 2023 seperti rincian pada kalkulasi tarif");
                    byrSimpan.setText(df4.format(rumus3));
                    SisaTagihan.setText(Valid.SetAngka3(rumus3));
                    sisaTagihan = rumus3;
                }

            } else if (hakkelas.getText().equals("1") && (cmbNaikKls.getSelectedItem().equals("Kelas VIP") || cmbNaikKls.getSelectedItem().equals("Kelas VVIP"))) {
                rumus1 = d / 100 * a;
                rumus2 = rumus1 + a;
                //jika real cost RS <= dari tarif inacbg kelas 1 
                if (e <= a) {
                    TKalkulasi.setText("Karena Real Cost RS kurang dari tarif INACBG kelas 1 maka tidak ada\n"
                            + "penambahan selisih biaya perawatan.");
                    Totdibayar.setText("0");
                    rumusbayar.setText("Tidak ada penambahan selisih biaya perawatan");
                    byrSimpan.setText("0");
                    SisaTagihan.setText("0");
                    sisaTagihan = 0;

                    //jika real cost RS > dari tarif penghitungan permenkes no. 3 tahun 2023
                } else if (e > rumus2) {
                    TKalkulasi.setText("Rumus Tarif INACBG             #   (" + persenSELISIH.getText() + " % x Kelas 1) + Kelas 1\n"
                            + "A. " + persenSELISIH.getText() + " % x Kelas 1                 #   " + persenSELISIH.getText() + " % x Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus1) + "\n"
                            + "B. Rp. " + Valid.SetAngka3(rumus1) + " + Kelas 1  #   Rp. " + Valid.SetAngka3(rumus1) + " + Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus2) + "\n"
                            + "----------------------------------------------------------------------------------------------------------------------------------------\n"
                            + "Kesimpulan : Karena tarif Real Cost RS melebihi dari tarif maksimal penghitungan sesuai Permenkes No. 3\n"
                            + "                     Tahun 2023 maka, yang harus dibayarkan sesuai dengan Permenkes No. 3 Tahun 2023\n"
                            + "                     adalah Rp. " + Valid.SetAngka3(rumus1) + "");

                    Totdibayar.setText(Valid.SetAngka3(rumus1));
                    rumusbayar.setText("Sesuaikan dengan Permenkes No. 3 Tahun 2023 seperti rincian pada kalkulasi tarif");
                    byrSimpan.setText(df4.format(rumus1));
                    SisaTagihan.setText(Valid.SetAngka3(rumus1));
                    sisaTagihan = rumus1;

                    //jika tarif inacbg kelas 1 < real cost RS dan sampai batas maksimal tarif perhitungan permenkes no. 3 tahun 2023
                } else if ((a < e) && (e <= rumus2)) {
                    TKalkulasi.setText("Rumus Tarif INACBG             #   (" + persenSELISIH.getText() + " % x Kelas 1) + Kelas 1\n"
                            + "A. " + persenSELISIH.getText() + " % x Kelas 1                 #   " + persenSELISIH.getText() + " % x Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus1) + "\n"
                            + "B. Rp. " + Valid.SetAngka3(rumus1) + " + Kelas 1  #   Rp. " + Valid.SetAngka3(rumus1) + " + Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus2) + "\n"
                            + "----------------------------------------------------------------------------------------------------------------------------------------\n"
                            + "Kesimpulan : Karena tarif INACBG kelas 1 kurang dari tarif Real Cost RS dan sampai batas maksimal tarif\n"
                            + "                     penghitungan pada poin B/sesuai Permenkes No. 3 Tahun 2023 maka, yang dibayar adalah\n"
                            + "                     tarif Real Cost RS - tarif INACBG kelas 1, sebagai berikut :\n"
                            + "                     Rp. " + Valid.SetAngka3(e) + " - Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(e - a) + "\n"
                            + "                     Jadi yang harus dibayarkan sesuai dengan Permenkes No. 3 Tahun 2023 adalah Rp. " + Valid.SetAngka3(e - a) + "");

                    Totdibayar.setText(Valid.SetAngka3(e - a));
                    rumusbayar.setText("Sesuaikan dengan Permenkes No. 3 Tahun 2023 seperti rincian pada kalkulasi tarif");
                    byrSimpan.setText(df4.format(e - a));
                    SisaTagihan.setText(Valid.SetAngka3(e - a));
                    sisaTagihan = e - a;
                }
            } else {
                Keterangan.setText("");
                pemasukan.setText("0");
                
                TKalkulasi.setText("");
                Totdibayar.setText("0");
                rumusbayar.setText("");
                byrSimpan.setText("");
                SisaTagihan.setText("0");
                sisaTagihan = 0;
            }

        } else {
            totTagihan = Sequel.cariIsiAngka("select total_tagihan from biaya_naik_kelas_bpjs where no_sep ='" + NoSEP.getText() + "' order by tgl_transaksi,jam_transaksi desc limit 1 ");
            sdhByr = Sequel.cariIsiAngka("select jumlah_byr+sudah_dibayar from biaya_naik_kelas_bpjs where no_sep ='" + NoSEP.getText() + "' order by tgl_transaksi,jam_transaksi desc limit 1 ");
            sisaTagihan = Sequel.cariIsiAngka("select sisa_setelah_byr from biaya_naik_kelas_bpjs where no_sep ='" + NoSEP.getText() + "' order by tgl_transaksi,jam_transaksi desc limit 1 ");
            byrKe = Sequel.cariIsiAngka("select pembayaran_ke from biaya_naik_kelas_bpjs where no_sep ='" + NoSEP.getText() + "' order by tgl_transaksi,jam_transaksi desc limit 1 ") + 1;

            Totdibayar.setText(Valid.SetAngka3(totTagihan));
            Sudahbyr.setText(Valid.SetAngka3(sdhByr));
            SisaTagihan.setText(Valid.SetAngka3(sisaTagihan));
            byrSimpan.setText(String.valueOf(totTagihan));

            if (hakkelas.getText().equals("2") && cmbNaikKls.getSelectedItem().equals("Kelas 1")) {
                TKalkulasi.setText("tarif INACBG : Kelas 1 Rp. " + Valid.SetAngka3(a) + " - Kelas 2 Rp. " + Valid.SetAngka3(b) + " = Rp. " + Valid.SetAngka3(a - b) + "\n"
                        + "Jadi yang harus dibayar pasien adalah Rp. " + Valid.SetAngka3(a - b) + "");
                rumusbayar.setText("Tarif INACBG kls. 1 Rp. " + Valid.SetAngka3(a) + " - Tarif INACBG kls. 2 Rp. " + Valid.SetAngka3(b) + "");

            } else if (hakkelas.getText().equals("2") && (cmbNaikKls.getSelectedItem().equals("Kelas VIP") || cmbNaikKls.getSelectedItem().equals("Kelas VVIP"))) {
                //tarif inacbg kelas 1 - kelas 2
                rumus1 = a - b;
                //tarif inacbg kelas 1 x 75 %
                rumus2 = d / 100 * a;
                //hasil perhitunganya
                hasilrumus = rumus1 + rumus2;
                //hitung tarif inacbg kelas 2 + hasilrumus
                hasilmaksimal = b + hasilrumus;

                //jika tarif real cost RS < tarif inacbg maka GRATIS
                if (e < b) {
                    TKalkulasi.setText("Karena Real Cost RS kurang dari tarif INACBG kelas 2 maka tidak ada\n"
                            + "penambahan selisih biaya perawatan.");
                    rumusbayar.setText("Tidak ada penambahan selisih biaya perawatan");

                    //jika tarif real cost RS > dari tarif perhitungan permenkes no. 3 tahun 2023
                } else if (e > hasilmaksimal) {
                    TKalkulasi.setText("Rumus Tarif INACBG    #   (Kelas 1 - Kelas 2) + (" + persenSELISIH.getText() + " % x Kelas 1)\n"
                            + "A. Kelas 1 - Kelas 2      #   Rp. " + Valid.SetAngka3(a) + " - Rp. " + Valid.SetAngka3(b) + " = Rp. " + Valid.SetAngka3(rumus1) + "\n"
                            + "B. " + persenSELISIH.getText() + " % x Kelas 1        #   " + persenSELISIH.getText() + " % x Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus2) + "\n"
                            + "C. Jadi hasilnya adalah Rp. " + Valid.SetAngka3(hasilrumus) + "\n"
                            + "D. Tarif maksimal         #   Kelas 2 + hasil perhitungan rumus poin C.\n"
                            + "                                    #   Rp. " + Valid.SetAngka3(b) + " + Rp. " + Valid.SetAngka3(hasilrumus) + " = Rp. " + Valid.SetAngka3(hasilmaksimal) + "\n"
                            + "----------------------------------------------------------------------------------------------------------------------------------------\n"
                            + "Kesimpulan : Karena tarif Real Cost RS melebihi dari tarif maksimal penghitungan sesuai\n"
                            + "                     Permenkes No. 3 Tahun 2023 maka, yang harus dibayarkan sesuai dengan\n"
                            + "                     Permenkes No. 3 Tahun 2023 adalah Rp. " + Valid.SetAngka3(hasilrumus) + "");

                    rumusbayar.setText("Sesuaikan dengan Permenkes No. 3 Tahun 2023 seperti rincian pada kalkulasi tarif");

                    //jika tarif inacbg kls 2 < real cost RS sampai batas maksimal tarif perhitungan permenkes no. 3 tahun 2023
                } else if ((b < e) && (e <= hasilmaksimal)) {
                    rumus3 = e - b;
                    TKalkulasi.setText("Rumus Tarif INACBG    #   (Kelas 1 - Kelas 2) + (" + persenSELISIH.getText() + " % x Kelas 1)\n"
                            + "A. Kelas 1 - Kelas 2      #   Rp. " + Valid.SetAngka3(a) + " - Rp. " + Valid.SetAngka3(b) + " = Rp. " + Valid.SetAngka3(rumus1) + "\n"
                            + "B. " + persenSELISIH.getText() + " % x Kelas 1        #   " + persenSELISIH.getText() + " % x Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus2) + "\n"
                            + "C. Jadi hasilnya adalah Rp. " + Valid.SetAngka3(hasilrumus) + "\n"
                            + "D. Tarif maksimal         #   Kelas 2 + hasil perhitungan rumus poin C.\n"
                            + "                                    #   Rp. " + Valid.SetAngka3(b) + " + Rp. " + Valid.SetAngka3(hasilrumus) + " = Rp. " + Valid.SetAngka3(hasilmaksimal) + "\n"
                            + "----------------------------------------------------------------------------------------------------------------------------------------\n"
                            + "Kesimpulan : Karena tarif INACBG Kelas 2 kurang dari tarif Real Cost RS dan sampai batas tarif maksimal\n"
                            + "                     penghitungan sesuai Permenkes No. 3 Tahun 2023 maka biaya, yang harus dibayarkan adalah\n"
                            + "                     tarif real cost RS - tarif INACBG Kelas 2 sebagai berikut :\n"
                            + "                     Rp. " + Valid.SetAngka3(e) + " - Rp. " + Valid.SetAngka3(b) + " = Rp. " + Valid.SetAngka3(rumus3) + "\n"
                            + "                     Jadi yang harus dibayarkan sesuai dengan Permenkes No. 3 Tahun 2023 adalah Rp. " + Valid.SetAngka3(rumus3) + "");

                    rumusbayar.setText("Sesuaikan dengan Permenkes No. 3 Tahun 2023 seperti rincian pada kalkulasi tarif");
                }

            } else if (hakkelas.getText().equals("1") && (cmbNaikKls.getSelectedItem().equals("Kelas VIP") || cmbNaikKls.getSelectedItem().equals("Kelas VVIP"))) {
                rumus1 = d / 100 * a;
                rumus2 = rumus1 + a;
                //jika real cost RS <= dari tarif inacbg kelas 1 
                if (e <= a) {
                    TKalkulasi.setText("Karena Real Cost RS kurang dari tarif INACBG kelas 1 maka tidak ada\n"
                            + "penambahan selisih biaya perawatan.");
                    rumusbayar.setText("Tidak ada penambahan selisih biaya perawatan");

                    //jika real cost RS > dari tarif penghitungan permenkes no. 3 tahun 2023
                } else if (e > rumus2) {
                    TKalkulasi.setText("Rumus Tarif INACBG             #   (" + persenSELISIH.getText() + " % x Kelas 1) + Kelas 1\n"
                            + "A. " + persenSELISIH.getText() + " % x Kelas 1                 #   " + persenSELISIH.getText() + " % x Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus1) + "\n"
                            + "B. Rp. " + Valid.SetAngka3(rumus1) + " + Kelas 1  #   Rp. " + Valid.SetAngka3(rumus1) + " + Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus2) + "\n"
                            + "----------------------------------------------------------------------------------------------------------------------------------------\n"
                            + "Kesimpulan : Karena tarif Real Cost RS melebihi dari tarif maksimal penghitungan sesuai\n"
                            + "                     Permenkes No. 3 Tahun 2023 maka, yang harus dibayarkan sesuai dengan\n"
                            + "                     Permenkes No. 3 Tahun 2023 adalah Rp. " + Valid.SetAngka3(rumus1) + "");

                    rumusbayar.setText("Sesuaikan dengan Permenkes No. 3 Tahun 2023 seperti rincian pada kalkulasi tarif");

                    //jika tarif inacbg kelas 1 < real cost RS dan sampai batas maksimal tarif perhitungan permenkes no. 3 tahun 2023
                } else if ((a < e) && (e <= rumus2)) {
                    TKalkulasi.setText("Rumus Tarif INACBG             #   (" + persenSELISIH.getText() + " % x Kelas 1) + Kelas 1\n"
                            + "A. " + persenSELISIH.getText() + " % x Kelas 1                 #   " + persenSELISIH.getText() + " % x Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus1) + "\n"
                            + "B. Rp. " + Valid.SetAngka3(rumus1) + " + Kelas 1  #   Rp. " + Valid.SetAngka3(rumus1) + " + Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(rumus2) + "\n"
                            + "----------------------------------------------------------------------------------------------------------------------------------------\n"
                            + "Kesimpulan : Karena tarif INACBG kelas 1 kurang dari tarif Real Cost RS dan sampai batas\n"
                            + "                     maksimal tarif penghitungan pada poin B/sesuai Permenkes No. 3 Tahun 2023\n"
                            + "                     maka, yang dibayar adalah tarif Real Cost RS - tarif INACBG kelas 1, sebagai\n"
                            + "                     berikut : Rp. " + Valid.SetAngka3(e) + " - Rp. " + Valid.SetAngka3(a) + " = Rp. " + Valid.SetAngka3(e - a) + "\n"
                            + "                     Jadi yang harus dibayarkan sesuai dengan Permenkes No. 3 Tahun 2023 adalah Rp. " + Valid.SetAngka3(e - a) + "");

                    rumusbayar.setText("Sesuaikan dengan Permenkes No. 3 Tahun 2023 seperti rincian pada kalkulasi tarif");
                }

                ChkSesuaiTagihan.setSelected(false);
                ChkSesuaiTagihan.setText("Pembayaran diangsur >>>");
                jlhdibayar.setText((String.valueOf(sisaTagihan)));
                jlhdibayar.setEditable(true);
                jlhdibayar.requestFocus();
            } else {
                Keterangan.setText("");
                pemasukan.setText("0");

                TKalkulasi.setText("");
                Totdibayar.setText("0");
                rumusbayar.setText("");
                byrSimpan.setText("");
                SisaTagihan.setText("0");
                sisaTagihan = 0;
            }
        }
    }

    public void hitungSewaTempat() {
        try {
            double a, b, c, totbayar;
            a = Double.parseDouble(pemasukan.getText().trim());
            b = Double.parseDouble(persenSewa.getText().trim());
            c = a * b / 100;
            nominalPajakSewa.setText(Valid.SetAngka2(c));
            totbayar = a + c;
            totalbyrsewa.setText(Valid.SetAngka2(totbayar));
            nominalSewa.setText(Valid.SetAngka3(totbayar));
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void simpanpemasukan() {
        try {
            if (KdKategori.getText().equals("AMBLN")) {
                if (Sequel.menyimpantf("pemasukan_lain", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Pemasukan", 27, new String[]{
                    Valid.SetTgl(Tanggal.getSelectedItem() + ""),
                    KdKategori.getText(), ambulanDibayar, KdPtg.getText(), Keterangan.getText(),
                    CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem(),
                    noTransaksi.getText(), telahTerimaPAS.getText(), "-", Tnorm.getText(), "-", "-", "-", "-", "-", "-", "0", "0",
                    TjlhBiayaPihak3.getText(), "-", "-", "-", "0", "-", ambulanDibayar, "0", "0"
                }) == true) {
                    WindowAmbulan.dispose();
                }                
            } else {
                Sequel.menyimpan("pemasukan_lain", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Pemasukan", 27, new String[]{
                    Valid.SetTgl(Tanggal.getSelectedItem() + ""),
                    KdKategori.getText(), pemasukan.getText(), KdPtg.getText(), Keterangan.getText(),
                    CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem(),
                    noTransaksi.getText(), telahTerimaPAS.getText(), "-", "-", "-", "-", "-", "-", "-", "-", "0", "0", "0", "-", "-", "-", "0", "-", "0",
                    nominalPajakSewa.getText(), totalbyrsewa.getText()
                });
            }

            Sequel.queryu("delete from tampjurnal");
            psakun.setString(1, KdKategori.getText());
            rs = psakun.executeQuery();
            if (rs.next()) {
                if (KdKategori.getText().equals("AMBLN")) {
                    Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(1), rs.getString(2), "0", ambulanDibayar});
                    Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(3), rs.getString(4), ambulanDibayar, "0"});    
                } else {
                    Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(1), rs.getString(2), "0", pemasukan.getText()});
                    Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(3), rs.getString(4), pemasukan.getText(), "0"});                    
                }
                jur.simpanJurnal("-", Valid.SetTgl(Tanggal.getSelectedItem() + ""), "U", "PEMASUKAN LAIN-LAIN, Petugas : " + NmPtg.getText());
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        Sequel.AutoComitTrue();
    }
    
    public void cektagihan() {
        if (ChkSesuaiTagihan.isSelected() == true) {
            ChkSesuaiTagihan.setText("Pembayaran sesuai tagihan >>>");
            ChkSesuaiTagihan.setEnabled(true);
            statusTran.setText("lunas");
//            jlhdibayar.setText((String.valueOf(sisaTagihan)));
            jlhdibayar.setText(Totdibayar.getText().replaceAll(",", ""));
            pemasukan.setText(Totdibayar.getText().replaceAll(",", ""));
            jlhdibayar.setEditable(false);
            BtnSimpan6.requestFocus();
            
            if (Sudahbyr.getText().equals(Totdibayar.getText())) {
                if (NoSEP.getText().equals("") && (kdINACBG.getText().equals(""))) {
                    lunas.setVisible(false);
                } else if (!NoSEP.getText().equals("") && (kdINACBG.getText().equals(""))) {
                    lunas.setVisible(false);
                } else if (hakkelas.getText().equals("3")) {
                    lunas.setVisible(false);
                } else {
                    lunas.setVisible(true);
                }
            } else {
                lunas.setVisible(false);
            }
        } else if (ChkSesuaiTagihan.isSelected() == false) {
            ChkSesuaiTagihan.setText("Pembayaran diangsur >>>");
            ChkSesuaiTagihan.setEnabled(true);
            statusTran.setText("dicicil");
            jlhdibayar.setText("");
            jlhdibayar.setEditable(true);
            jlhdibayar.requestFocus();
            
            if (Sudahbyr.getText().equals(Totdibayar.getText())) {
                if (NoSEP.getText().equals("") && (kdINACBG.getText().equals(""))) {
                    lunas.setVisible(false);
                } else if (!NoSEP.getText().equals("") && (kdINACBG.getText().equals(""))) {
                    lunas.setVisible(false);
                } else if (hakkelas.getText().equals("3")) {
                    lunas.setVisible(false);
                } else {
                    lunas.setVisible(true);
                }
            } else {
                lunas.setVisible(false);
            }
        }
    }
    
    private void SimpanKwitansi() {
        try {
            koneksi.setAutoCommit(false);
            Sequel.queryu2("delete from temporary_bayar_ranap");

            psTem = koneksi.prepareStatement("SELECT DATE_FORMAT(pemasukan_lain.tanggal,'%d-%m-%Y') tanggal, pemasukan_lain.keterangan, "
                    + "format(pemasukan_lain.besar,0) besar, pemasukan_lain.nip, petugas.nama, pemasukan_lain.no_transaksi, pemasukan_lain.jam_penerimaan, "
                    + "kategori_pemasukan_lain.nama_kategori, IF(pemasukan_lain.telah_terima_dari IS NULL,'-',pemasukan_lain.telah_terima_dari) pasienya "
                    + "FROM pemasukan_lain INNER JOIN petugas INNER JOIN kategori_pemasukan_lain ON pemasukan_lain.nip = petugas.nip "
                    + "AND pemasukan_lain.kode_kategori = kategori_pemasukan_lain.kode_kategori "
                    + "WHERE pemasukan_lain.no_transaksi = '" + noTransaksi.getText() + "'");

            try {
                rsTem = psTem.executeQuery();
                while (rsTem.next()) {
                    nmpetugas = rsTem.getString("nama");
                    
                    Sequel.menyimpan("temporary_bayar_ranap",
                            "'0','"
                            + rsTem.getString("tanggal") + "','"
                            + rsTem.getString("keterangan") + "','"
                            + rsTem.getString("besar") + "','"
                            + rsTem.getString("nip") + "','"
                            + rsTem.getString("nama") + "','"
                            + rsTem.getString("no_transaksi") + "','"
                            + rsTem.getString("jam_penerimaan") + "','"
                            + rsTem.getString("nama_kategori") + "','"
                            + rsTem.getString("pasienya").replaceAll("'", "") + "','','','','','','','',''", "Kwitansi Pemasukan Lain-lain");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsTem != null) {
                    rsTem.close();
                }
                if (psTem != null) {
                    psTem.close();
                }
            }
            koneksi.setAutoCommit(true);
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void SimpanNotaNaikKelas() {
        selisihBaru();
        try {
            koneksi.setAutoCommit(false);
            Sequel.queryu2("delete from temporary_bayar_ranap");
            
            psTem1 = koneksi.prepareStatement("SELECT CONCAT(p.nm_pasien,' (No.RM : ', pl.no_rkm_medis,')') pasien, pl.no_kartu, CONCAT('1704R0', pl.no_sep) nosep, "
                    + "pl.ruang_inap, pl.lm_rawat, DATE_FORMAT(pl.tgl_masuk, '%d-%m-%Y') tgl_msk, DATE_FORMAT(pl.tgl_pulang, '%d-%m-%Y') tgl_plg, pl.hak_kelas, "
                    + "pl.kode_inacbg, ki.description_pmk_59_2014, pl.no_transaksi, pl.rumus_selisih_tarif, format(pl.besar,0) besar, pt.nama petugas "
                    + "FROM pemasukan_lain pl INNER JOIN petugas pt ON pt.nip = pl.nip INNER JOIN pasien p ON p.no_rkm_medis = pl.no_rkm_medis "
                    + "INNER JOIN inacbg_unucbg_2016 ki ON ki.code = pl.kode_inacbg WHERE pl.no_transaksi = '" + noTransaksi.getText() + "'");            
            try {
                rsTem1 = psTem1.executeQuery();
                while (rsTem1.next()) {
                    nmpetugas = rsTem1.getString("petugas");
                    cekNoSEP.setText(rsTem1.getString("nosep"));
                    cekSEP();
                    cekINACBG();
                    cmbNaikKls.setSelectedItem(Sequel.cariIsi("select naik_kelas from pemasukan_lain pl where pl.no_rawat='" + norawat.getText() + "'"));
                    hitungSelisih();
                    Sequel.menyimpan("temporary_bayar_ranap",
                            "'0','"
                            + rsTem1.getString("pasien").replaceAll("'", "") + "','"
                            + rsTem1.getString("no_kartu") + "','"
                            + rsTem1.getString("nosep") + "','"
                            + rsTem1.getString("ruang_inap") + "','"
                            + rsTem1.getString("lm_rawat") + "','"
                            + rsTem1.getString("tgl_msk") + "','"
                            + rsTem1.getString("tgl_plg") + "','"
                            + rsTem1.getString("hak_kelas") + "','"
                            + rsTem1.getString("kode_inacbg") + "','"
                            + rsTem1.getString("description_pmk_59_2014") + "','"
                            + rsTem1.getString("no_transaksi") + "','"
                            + TKalkulasi.getText() + "',' "
                            + rsTem1.getString("besar").replaceAll(",", ".") + "','"
                            + rsTem1.getString("petugas") + "','','',''", "Nota/Kwitansi Selisih Naik Kelas BPJS");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsTem1 != null) {
                    rsTem1.close();
                }
                if (psTem1 != null) {
                    psTem1.close();
                }
            }
            koneksi.setAutoCommit(true);
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private double getNilaiDouble(javax.swing.JTextField textField, double defaultValue) {
        try {
            if (textField.getText() == null || textField.getText().trim().equals("")) {
                textField.setText(String.valueOf((int) defaultValue));
                return defaultValue;
            }
            return Double.parseDouble(textField.getText().trim());
        } catch (Exception e) {
            textField.setText(String.valueOf((int) defaultValue));
            return defaultValue;
        }
    }
    
    private double getTarifAmbulan(String kdJenisPrw) {
        try {
            String tarif = Sequel.cariIsi("SELECT total_byrdrpr FROM jns_perawatan WHERE kd_jenis_prw='" + kdJenisPrw + "' AND status='1'");
            return Double.parseDouble(tarif);
        } catch (Exception e) {
            return 0;
        }
    }
    
    private String buildKeteranganAmbulan(String jenisTarif, double jarak, double tarif15KmPertama, double tarifPerKmLebih15, double selisihJarak,
            double biayaSelisihJarak, double jumlahBayar, double biayaPihak3, double totalBayar) {
        StringBuilder ket = new StringBuilder();
        ket.append("Alamat Tujuan : ").append(TtujuanAlamat.getText())
                .append(", Sejauh ").append(Valid.SetAngka2(jarak)).append(" Km.\n")
                .append("Uraian Penjelasan :\n");

        if (jarak > 15) {
            ket.append("a.) Tarif 15 Km Pertama untuk ")
                    .append(jenisTarif)
                    .append(" = Rp. ")
                    .append(Valid.SetAngka(tarif15KmPertama))
                    .append("\n");

            ket.append("b.) Selisih Jarak Dari 15 Km : ")
                    .append(Valid.SetAngka2(jarak))
                    .append(" Km - 15 Km = ")
                    .append(Valid.SetAngka2(selisihJarak))
                    .append(" Km\n");

            ket.append("c.) Tarif > 15 Km untuk ")
                    .append(jenisTarif)
                    .append(" = Rp. ")
                    .append(Valid.SetAngka(tarifPerKmLebih15))
                    .append(" / Km\n");

            ket.append("d.) Biaya Selisih Jarak : ")
                    .append(Valid.SetAngka2(selisihJarak))
                    .append(" Km x Rp. ")
                    .append(Valid.SetAngka(tarifPerKmLebih15))
                    .append(" = Rp. ")
                    .append(Valid.SetAngka(biayaSelisihJarak))
                    .append("\n");

            ket.append("e.) Jumlah Bayar : Rp. ")
                    .append(Valid.SetAngka(tarif15KmPertama))
                    .append(" + Rp. ")
                    .append(Valid.SetAngka(biayaSelisihJarak))
                    .append(" = Rp. ")
                    .append(Valid.SetAngka(jumlahBayar))
                    .append("\n");

            if (biayaPihak3 > 0) {
                ket.append("f.) Jumlah Biaya Dibayar Oleh Pihak Ke 3 : Rp. ")
                        .append(Valid.SetAngka(biayaPihak3))
                        .append("\n");

                ket.append("g.) Total Bayar : Rp. ")
                        .append(Valid.SetAngka(jumlahBayar))
                        .append(" - Rp. ")
                        .append(Valid.SetAngka(biayaPihak3))
                        .append(" = Rp. ")
                        .append(Valid.SetAngka(totalBayar))
                        .append("\n");
            }
        } else {
            ket.append("a.) Tarif ")
                    .append(jenisTarif)
                    .append(" untuk 15 Km Pertama = Rp. ")
                    .append(Valid.SetAngka(tarif15KmPertama))
                    .append("\n");

            if (biayaPihak3 > 0) {
                ket.append("b.) Jumlah Biaya Dibayar Oleh Pihak Ke 3 : Rp. ")
                        .append(Valid.SetAngka(biayaPihak3))
                        .append("\n");

                ket.append("c.) Total Bayar : Rp. ")
                        .append(Valid.SetAngka(tarif15KmPertama))
                        .append(" - Rp. ")
                        .append(Valid.SetAngka(biayaPihak3))
                        .append(" = Rp. ")
                        .append(Valid.SetAngka(totalBayar))
                        .append("\n");
            }
        }

        return ket.toString();
    }
    
    private void hitungAmbulanNew() {
        double jarak = getNilaiDouble(Tjarak, 0);
        double biayaPihak3 = getNilaiDouble(TjlhBiayaPihak3, 0);

        double tarif15KmPertama = 0;
        double tarifPerKmLebih15 = 0;

        String jenisTarif = CmbTarif.getSelectedItem() == null ? "" : CmbTarif.getSelectedItem().toString();

        // Mapping tarif berdasarkan jenis ambulans
        if (jenisTarif.toLowerCase().contains("non emergency")) {
            tarif15KmPertama = getTarifAmbulan("2026RJ30003"); // Non Emergency 15 km pertama
            tarifPerKmLebih15 = getTarifAmbulan("2026RJ30004"); // Non Emergency > 15 km
        } else if (jenisTarif.toLowerCase().contains("emergency")) {
            tarif15KmPertama = getTarifAmbulan("2026RJ30001"); // Emergency 15 km pertama
            tarifPerKmLebih15 = getTarifAmbulan("2026RJ30002"); // Emergency > 15 km
        } else {
            // Jika jenis tarif tidak dikenali
            tarif15KmPertama = 0;
            tarifPerKmLebih15 = 0;
        }

        double selisihJarak = 0;
        double biayaSelisihJarak = 0;
        double jumlahBayar = 0;
        double totalBayar = 0;

        if (jarak > 15) {
            selisihJarak = jarak - 15;
            biayaSelisihJarak = selisihJarak * tarifPerKmLebih15;
            jumlahBayar = tarif15KmPertama + biayaSelisihJarak;
        } else {
            jumlahBayar = tarif15KmPertama;
        }

        totalBayar = jumlahBayar - biayaPihak3;

        // Jika tidak boleh minus, aktifkan ini:
        // if (totalBayar < 0) totalBayar = 0;
        // Set nilai ke form
        TselisihJrk.setText(Valid.SetAngka2(selisihJarak));
        TtarifSelisihJrk.setText(Valid.SetAngka(biayaSelisihJarak));
        TjlhBayar.setText(Valid.SetAngka(jumlahBayar));
        TtotBayar.setText(Valid.SetAngka(totalBayar));
        pemasukan.setText(TtotBayar.getText());
        ambulanDibayar = Valid.SetAngka2(totalBayar);
        telahTerimaPAS.setText(Tnmpasien.getText() + " (" + Tnorm.getText() + ")");

        // Isi keterangan
        Keterangan.setText(buildKeteranganAmbulan(jenisTarif, jarak, tarif15KmPertama, tarifPerKmLebih15, selisihJarak,
                biayaSelisihJarak, jumlahBayar, biayaPihak3, totalBayar));
    }
    
    private void cekPanjar() {
        if (Sequel.cariInteger("select count(-1) from transaksi_panjar where no_rawat='" + norawat.getText() + "'") > 0) {
            TnoPanjar.setText(Sequel.cariIsi("select no_panjar from transaksi_panjar where no_rawat='" + norawat.getText() + "'"));
            TtglPanjar.setText(Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_panjar from transaksi_panjar where no_rawat='" + norawat.getText() + "'"))
                    + ", Jam : " + Sequel.cariIsi("select time_format(waktu_simpan,'%H:%i Wita') from transaksi_panjar where no_rawat='" + norawat.getText() + "'"));
            TnominalPanjar.setText(Sequel.cariIsi("select REPLACE(format(nominal_panjar,0),',','.') from transaksi_panjar where no_rawat='" + norawat.getText() + "'"));            
        } else {
            TnoPanjar.setText("-");
            TtglPanjar.setText("-");
            TnominalPanjar.setText("0");
        }
    }
    
    private void simpanHistoriHapusPemasukan() {
        try {
            ps1 = koneksi.prepareStatement("select * from pemasukan_lain where no_transaksi='" + tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 0).toString() + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    String nipHapus = "", waktusimpan = "";
                    waktusimpan = Sequel.cariIsi("select now()");
                    if (akses.getadmin() == true) {
                        nipHapus = "-";
                    } else {
                        nipHapus = akses.getkode();
                    }

                    if (Sequel.menyimpantf("pemasukan_lain_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Pemasukan Lain-lain Histori", 30, new String[]{
                        rs1.getString("tanggal"),
                        rs1.getString("kode_kategori"),
                        rs1.getString("besar"),
                        rs1.getString("nip"),
                        rs1.getString("keterangan"),
                        rs1.getString("jam_penerimaan"),
                        rs1.getString("no_transaksi"),
                        rs1.getString("telah_terima_dari"),
                        rs1.getString("no_sep"),
                        rs1.getString("no_rkm_medis"),
                        rs1.getString("no_kartu"),
                        rs1.getString("no_rawat"),
                        rs1.getString("tgl_masuk"),
                        rs1.getString("tgl_pulang"),
                        rs1.getString("ruang_inap"),
                        rs1.getString("kode_inacbg"),
                        rs1.getString("trf_kls1"),
                        rs1.getString("trf_kls2"),
                        rs1.getString("trf_kls3"),
                        rs1.getString("hak_kelas"),
                        rs1.getString("naik_kelas"),
                        rs1.getString("lm_rawat"),
                        rs1.getString("persen_tambahan"),
                        rs1.getString("rumus_selisih_tarif"),
                        rs1.getString("total_byr"),
                        rs1.getString("nominal_pajak_sewa"),
                        rs1.getString("nominal_sewa"),
                        TketHapus.getText(), nipHapus, waktusimpan
                    }) == true) {
                        if (Sequel.queryu2tf("delete from pemasukan_lain where no_transaksi=?", 1, new String[]{
                            tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 0).toString()
                        }) == true) {
                            if (rs1.getString("kode_kategori").equals("SBPJS")) {
                                simpanHistoriHapusBiayaBaikKelasRawat(waktusimpan, nipHapus, 
                                        rs1.getString("kode_kategori"), rs1.getString("tanggal"), 
                                        rs1.getString("besar"), rs1.getString("nip"));
                            } else {
                                Sequel.AutoComitFalse();
                                try {
                                    Sequel.queryu("delete from tampjurnal");
                                    psakun.setString(1, rs1.getString("kode_kategori"));
                                    rs = psakun.executeQuery();
                                    if (rs.next()) {
                                        Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(1), rs.getString(2), rs1.getString("besar"), "0"});
                                        Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(3), rs.getString(4), "0", rs1.getString("besar")});
                                        jur.simpanJurnal("-", rs1.getString("tanggal"), "U", "PEMBATALAN PEMASUKAN "
                                                + Sequel.cariIsi("select nama_kategori from kategori_pemasukan_lain where kode_kategori='" + rs1.getString("kode_kategori") + "'") + ", Petugas : "
                                                + Sequel.cariIsi("select nama from pegawai where nik='" + rs1.getString("nip") + "'"));
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                }
                                Sequel.AutoComitTrue();
                                emptTeks();
                                tampil();
                                WindowHapus.dispose();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                        }
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
    
    private void simpanHistoriHapusBiayaBaikKelasRawat(String wktSimpan, String niphapus, String kodekat, String tgl, String bsr, String nip) {
        try {
            ps2 = koneksi.prepareStatement("select * from biaya_naik_kelas_bpjs where no_transaksi='" + tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 0).toString() + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    if (Sequel.menyimpantf("biaya_naik_kelas_bpjs_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Biaya Naik Kelas Rawat Histori", 15, new String[]{
                        rs2.getString("tgl_transaksi"),
                        rs2.getString("jam_transaksi"),
                        rs2.getString("no_rawat"),
                        rs2.getString("no_sep"),
                        rs2.getString("total_tagihan"),
                        rs2.getString("sudah_dibayar"),
                        rs2.getString("sisa_tagihan"),
                        rs2.getString("jumlah_byr"),
                        rs2.getString("sisa_setelah_byr"),
                        rs2.getString("pembayaran_ke"),
                        rs2.getString("no_transaksi"),
                        rs2.getString("status_transaksi"),                        
                        TketHapus.getText(), niphapus, wktSimpan
                    }) == true) {
                        if (Sequel.queryu2tf("delete from biaya_naik_kelas_bpjs where no_transaksi=?", 1, new String[]{
                            tbPemasukan.getValueAt(tbPemasukan.getSelectedRow(), 0).toString()
                        }) == true) {
                            Sequel.AutoComitFalse();
                            try {
                                Sequel.queryu("delete from tampjurnal");
                                psakun.setString(1, kodekat);
                                rs = psakun.executeQuery();
                                if (rs.next()) {
                                    Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(1), rs.getString(2), bsr, "0"});
                                    Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(3), rs.getString(4), "0", bsr});
                                    jur.simpanJurnal("-", tgl, "U", "PEMBATALAN PEMASUKAN "
                                            + Sequel.cariIsi("select nama_kategori from kategori_pemasukan_lain where kode_kategori='" + kodekat + "'") + ", Petugas : "
                                            + Sequel.cariIsi("select nama from pegawai where nik='" + nip + "'"));
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            }
                            Sequel.AutoComitTrue();
                            emptTeks();
                            tampil();
                            WindowHapus.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabMode1);
        try {
            ps3 = koneksi.prepareStatement("SELECT IF(pg.nama='-','Admin Utama',pg.nama) pelaku, pl.no_transaksi, pl.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                    + "date_format(pl.waktu_hapus,'%d-%m-%Y') tglHapus, time(pl.waktu_hapus) jamHapus, pl.ket_hapus, date_format(pl.waktu_hapus,'%Y-%m-%d %H:%i:%s') waktu_hapus, "
                    + "concat(pl.kode_kategori,' - ',kp.nama_kategori) nmkategori FROM pemasukan_lain_histori pl "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = pl.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg ON pg.nik = pl.nip_penghapus inner join kategori_pemasukan_lain kp on kp.kode_kategori=pl.kode_kategori WHERE "
                    + "date(pl.waktu_hapus) between ? and ? and pg.nama like ? or "
                    + "date(pl.waktu_hapus) between ? and ? and pl.no_rawat like ? or "
                    + "date(pl.waktu_hapus) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(pl.waktu_hapus) between ? and ? and p.nm_pasien like ? order by pl.waktu_hapus desc");
            try {
                ps3.setString(1, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps3.setString(2, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps3.setString(3, "%" + TCari2.getText().trim() + "%");
                ps3.setString(4, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps3.setString(5, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps3.setString(6, "%" + TCari2.getText().trim() + "%");
                ps3.setString(7, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps3.setString(8, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps3.setString(9, "%" + TCari2.getText().trim() + "%");
                ps3.setString(10, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps3.setString(11, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps3.setString(12, "%" + TCari2.getText().trim() + "%");
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    tabMode1.addRow(new String[]{
                        rs3.getString("pelaku"),
                        rs3.getString("no_transaksi"),
                        rs3.getString("no_rawat"),
                        rs3.getString("no_rkm_medis"),
                        rs3.getString("nm_pasien"),
                        rs3.getString("tglHapus"),
                        rs3.getString("jamHapus"),
                        rs3.getString("ket_hapus"),
                        rs3.getString("waktu_hapus"),
                        rs3.getString("nmkategori")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs3 != null) {
                    rs3.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRiwayatBiayaNaikKelas(String wkthapus) {
        Valid.tabelKosong(tabMode2);
        try {
            ps4 = koneksi.prepareStatement("SELECT IF(pg.nama='-','Admin Utama',pg.nama) pelaku, bn.no_transaksi, bn.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                    + "date_format(bn.waktu_hapus,'%d-%m-%Y') tglHapus, time(bn.waktu_hapus) jamHapus, bn.ket_hapus, bn.no_sep, "
                    + "format(bn.total_tagihan,0) totTagihan, format(bn.sudah_dibayar,0) sdhBayar, format(bn.sisa_tagihan,0) sisaTagihan, "
                    + "format(bn.jumlah_byr,0) jmlBayar, bn.waktu_hapus FROM biaya_naik_kelas_bpjs_histori bn INNER JOIN reg_periksa rp ON rp.no_rawat = bn.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN pegawai pg ON pg.nik = bn.nip_penghapus WHERE bn.waktu_hapus like ?");
            try {                
                ps4.setString(1, "%" + wkthapus.trim() + "%");
                rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    tabMode2.addRow(new String[]{
                        rs4.getString("pelaku"),
                        rs4.getString("no_transaksi"),
                        rs4.getString("no_rawat"),
                        rs4.getString("no_rkm_medis"),
                        rs4.getString("nm_pasien"),
                        rs4.getString("tglHapus"),
                        rs4.getString("jamHapus"),
                        rs4.getString("ket_hapus"),
                        "1704R0" + rs4.getString("no_sep"),
                        rs4.getString("totTagihan"),
                        rs4.getString("sdhBayar"),
                        rs4.getString("sisaTagihan"),
                        rs4.getString("jmlBayar"),
                        rs4.getString("waktu_hapus")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataRiwayat() {
        if (tbRiwayat.getSelectedRow() != -1) {
            tampilRiwayatBiayaNaikKelas(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 8).toString());
        }
    }
    
    private void kembalikanData() {
        try {
            ps5 = koneksi.prepareStatement("select * from pemasukan_lain_histori where "
                    + "waktu_hapus='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 8).toString() + "'");
            try {
                rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    if (Sequel.menyimpantf("pemasukan_lain", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 27, new String[]{
                        rs5.getString("tanggal"),
                        rs5.getString("kode_kategori"),
                        rs5.getString("besar"),
                        rs5.getString("nip"),
                        rs5.getString("keterangan"),
                        rs5.getString("jam_penerimaan"),
                        rs5.getString("no_transaksi"),
                        rs5.getString("telah_terima_dari"),
                        rs5.getString("no_sep"),
                        rs5.getString("no_rkm_medis"),
                        rs5.getString("no_kartu"),
                        rs5.getString("no_rawat"),
                        rs5.getString("tgl_masuk"),
                        rs5.getString("tgl_pulang"),
                        rs5.getString("ruang_inap"),
                        rs5.getString("kode_inacbg"),
                        rs5.getString("trf_kls1"),
                        rs5.getString("trf_kls2"),
                        rs5.getString("trf_kls3"),
                        rs5.getString("hak_kelas"),
                        rs5.getString("naik_kelas"),
                        rs5.getString("lm_rawat"),
                        rs5.getString("persen_tambahan"),
                        rs5.getString("rumus_selisih_tarif"),
                        rs5.getString("total_byr"),
                        rs5.getString("nominal_pajak_sewa"),
                        rs5.getString("nominal_sewa")
                    }) == true) {
                        if (rs5.getString("kode_kategori").equals("SBPJS")) {
                            kembalikanDataNaikKelas();
                            Sequel.AutoComitFalse();
                            try {
                                Sequel.queryu("delete from tampjurnal");
                                psakun.setString(1, rs5.getString("kode_kategori"));
                                rs = psakun.executeQuery();
                                if (rs.next()) {
                                    Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(1), rs.getString(2), "0", rs5.getString("besar")});
                                    Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(3), rs.getString(4), rs5.getString("besar"), "0"});
                                    jur.simpanJurnal("-", rs5.getString("tanggal"), "U", "PEMASUKAN LAIN-LAIN, Petugas : " + Sequel.cariIsi("select nama from pegawai where nik='" + rs5.getString("nip") + "'"));
                                    System.out.println("Proses mengembalikan/restore data berhasil..!!");
                                    JOptionPane.showMessageDialog(rootPane, "Proses mengembalikan/restore data berhasil..!!");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            }
                            Sequel.AutoComitTrue();
                        } else {
                            Sequel.AutoComitFalse();
                            try {
                                Sequel.queryu("delete from tampjurnal");
                                psakun.setString(1, rs5.getString("kode_kategori"));
                                rs = psakun.executeQuery();
                                if (rs.next()) {
                                    Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(1), rs.getString(2), "0", rs5.getString("besar")});
                                    Sequel.menyimpan("tampjurnal", "?,?,?,?", 4, new String[]{rs.getString(3), rs.getString(4), rs5.getString("besar"), "0"});
                                    jur.simpanJurnal("-", rs5.getString("tanggal"), "U", "PEMASUKAN LAIN-LAIN, Petugas : " + Sequel.cariIsi("select nama from pegawai where nik='" + rs5.getString("nip") + "'"));
                                    System.out.println("Proses mengembalikan/restore data berhasil..!!");
                                    JOptionPane.showMessageDialog(rootPane, "Proses mengembalikan/restore data berhasil..!!");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            }
                            Sequel.AutoComitTrue();
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs5 != null) {
                    rs5.close();
                }
                if (ps5 != null) {
                    ps5.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void kembalikanDataNaikKelas() {
        try {
            ps6 = koneksi.prepareStatement("select * from biaya_naik_kelas_bpjs_histori where "
                    + "waktu_hapus='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 8).toString() + "'");
            try {
                rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    if (Sequel.menyimpantf("biaya_naik_kelas_bpjs", "?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 12, new String[]{
                        rs6.getString("tgl_transaksi"),
                        rs6.getString("jam_transaksi"),
                        rs6.getString("no_rawat"),
                        rs6.getString("no_sep"),
                        rs6.getString("total_tagihan"),
                        rs6.getString("sudah_dibayar"),
                        rs6.getString("sisa_tagihan"),
                        rs6.getString("jumlah_byr"),
                        rs6.getString("sisa_setelah_byr"),
                        rs6.getString("pembayaran_ke"),
                        rs6.getString("no_transaksi"),
                        rs6.getString("status_transaksi")
                    }) == true) {
                        System.out.println("Proses mengembalikan/restore data berhasil..!!");                        
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs6 != null) {
                    rs6.close();
                }
                if (ps6 != null) {
                    ps6.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void cetakNota() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isi = "";
        nmpetugas = "";
        
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tglNota", "Martapura, " + tglNota.getSelectedItem().toString());

        if (tte.equals("tidak")) {
            if (KdKategori.getText().equals("SWKTN")) {
                Valid.MyReport("rptNotaSewaTempat.jasper", "report", "::[ Kwitansi pembayaran Sewa Tempat ]::", " select pemasukan_lain.tanggal, "
                        + " pemasukan_lain.keterangan, pemasukan_lain.besar, pemasukan_lain.nip, "
                        + " petugas.nama,pemasukan_lain.no_transaksi,pemasukan_lain.jam_penerimaan, "
                        + " kategori_pemasukan_lain.nama_kategori, if(pemasukan_lain.telah_terima_dari is null,'-',"
                        + " pemasukan_lain.telah_terima_dari) pembayar, pemasukan_lain.nominal_sewa "
                        + " from pemasukan_lain inner join petugas inner join kategori_pemasukan_lain on pemasukan_lain.nip=petugas.nip "
                        + " and pemasukan_lain.kode_kategori=kategori_pemasukan_lain.kode_kategori "
                        + " where pemasukan_lain.no_transaksi='" + noTransaksi.getText() + "' ", param);
            } else if (KdKategori.getText().equals("SBPJS")) {
                SimpanKwitansi();
                param.put("telah_terimaAN", telahTerimaAN.getText());
                param.put("uang_sebanyak", Sequel.Terbilang(Sequel.cariIsiAngka("SELECT REPLACE(REPLACE(temp3,'.',''),',','') FROM temporary_bayar_ranap")) + " Rupiah.");
                param.put("terbilang", Sequel.cariIsi("SELECT concat('Terbilang Rp. ',REPLACE(REPLACE(temp3,'.','.'),',','.')) FROM temporary_bayar_ranap"));
                Valid.MyReport("rptNotaKwitansi.jasper", "report", "::[ Kwitansi pembayaran (Selisih Tarif BPJS) ]::", "SELECT * FROM temporary_bayar_ranap", param);
                Sequel.mengedit("reg_periksa", "no_rawat='" + norw + "'", "p_jawab='" + telahTerimaAN.getText() + "' ");
            } else {
                SimpanKwitansi();
                String kalimatBayar = "", bayar = "";
                kalimatBayar = Sequel.Terbilang(Sequel.cariIsiAngka("SELECT REPLACE(REPLACE(temp3,'.',''),',','') FROM temporary_bayar_ranap"));
                bayar = Sequel.cariIsi("SELECT temp3 FROM temporary_bayar_ranap");

                param.put("uang_sebanyak", kalimatBayar + " Rupiah.");
                param.put("terbilang", "Terbilang Rp. " + bayar);

                if (KdKategori.getText().equals("AMBLN")) {
                    param.put("sebesar", "");
                } else {
                    param.put("sebesar", " sebesar Rp. " + bayar + "\n");
                }
                Valid.MyReport("rptNotaKwitansiLain.jasper", "report", "::[ Kwitansi pembayaran lain-lain ]::", "SELECT * FROM temporary_bayar_ranap", param);
            }
            
            emptTeks();
            selisihBaru();
            tampil();
        } else if (tte.equals("ya")) {
            if (KdKategori.getText().equals("SWKTN")) {
                nmpetugas = Sequel.cariIsi("select pg.nama from pemasukan_lain pl inner join pegawai pg on pg.nik=pl.nip where pl.no_transaksi='" + noTransaksi.getText() + "'");
                
                isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                        + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='011'"),
                                "Kuitansi Pembayaran", nmpetugas,
                                Sequel.cariIsi("select date_format('" + Valid.SetTgl(tglNota.getSelectedItem() + "") + "','%d/%m/%Y')"),
                                Sequel.cariIsi("select time(now())")) + "') from kalimat_tte where kode='011'");
                
                Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Kuitansi", Sequel.cariFolderPrintTte());
                param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='011'"));
                
                Valid.MyReport("rptNotaSewaTempatQr.jasper", "report", "::[ Kwitansi pembayaran Sewa Tempat ]::", " select pemasukan_lain.tanggal, "
                        + " pemasukan_lain.keterangan, pemasukan_lain.besar, pemasukan_lain.nip, "
                        + " petugas.nama,pemasukan_lain.no_transaksi,pemasukan_lain.jam_penerimaan, "
                        + " kategori_pemasukan_lain.nama_kategori, if(pemasukan_lain.telah_terima_dari is null,'-',"
                        + " pemasukan_lain.telah_terima_dari) pembayar, pemasukan_lain.nominal_sewa "
                        + " from pemasukan_lain inner join petugas inner join kategori_pemasukan_lain on pemasukan_lain.nip=petugas.nip "
                        + " and pemasukan_lain.kode_kategori=kategori_pemasukan_lain.kode_kategori "
                        + " where pemasukan_lain.no_transaksi='" + noTransaksi.getText() + "' ", param);

                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            } else if (KdKategori.getText().equals("SBPJS")) {
                SimpanKwitansi();                
                isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                        + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='011'"),
                                "Kuitansi Pembayaran", nmpetugas,
                                Sequel.cariIsi("select date_format('" + Valid.SetTgl(tglNota.getSelectedItem() + "") + "','%d/%m/%Y')"),
                                Sequel.cariIsi("select time(now())")) + "') from kalimat_tte where kode='011'");
                
                Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Kuitansi", Sequel.cariFolderPrintTte());
                param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='011'"));
                
                param.put("telah_terimaAN", telahTerimaAN.getText());
                param.put("uang_sebanyak", Sequel.Terbilang(Sequel.cariIsiAngka("SELECT REPLACE(REPLACE(temp3,'.',''),',','') FROM temporary_bayar_ranap")) + " Rupiah.");
                param.put("terbilang", Sequel.cariIsi("SELECT concat('Terbilang Rp. ',REPLACE(REPLACE(temp3,'.','.'),',','.')) FROM temporary_bayar_ranap"));
                
                Valid.MyReport("rptNotaKwitansiQr.jasper", "report", "::[ Kwitansi pembayaran (Selisih Tarif BPJS) ]::", "SELECT * FROM temporary_bayar_ranap", param);
                Sequel.mengedit("reg_periksa", "no_rawat='" + norw + "'", "p_jawab='" + telahTerimaAN.getText() + "' ");
                
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            } else {
                SimpanKwitansi();
                String kalimatBayar = "", bayar = "";
                kalimatBayar = Sequel.Terbilang(Sequel.cariIsiAngka("SELECT REPLACE(REPLACE(temp3,'.',''),',','') FROM temporary_bayar_ranap"));
                bayar = Sequel.cariIsi("SELECT temp3 FROM temporary_bayar_ranap");

                param.put("uang_sebanyak", kalimatBayar + " Rupiah.");
                param.put("terbilang", "Terbilang Rp. " + bayar);

                if (KdKategori.getText().equals("AMBLN")) {
                    param.put("sebesar", "");
                } else {
                    param.put("sebesar", " sebesar Rp. " + bayar + "\n");
                }
                
                isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                        + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='011'"),
                                "Kuitansi Pembayaran", nmpetugas,
                                Sequel.cariIsi("select date_format('" + Valid.SetTgl(tglNota.getSelectedItem() + "") + "','%d/%m/%Y')"),
                                Sequel.cariIsi("select time(now())")) + "') from kalimat_tte where kode='011'");
                
                Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Kuitansi", Sequel.cariFolderPrintTte());
                param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='011'"));
                
                Valid.MyReport("rptNotaKwitansiLainQr.jasper", "report", "::[ Kwitansi pembayaran lain-lain ]::", "SELECT * FROM temporary_bayar_ranap", param);
                
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);                
            }
            
            emptTeks();
            selisihBaru();
            tampil();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void cetakNotaAmbulan() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isi = "";
        nmpetugas = "";
        
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tglNota", "Martapura, " + Valid.SetTglINDONESIA(Valid.SetTgl(tglNota.getSelectedItem() + "")));

        SimpanKwitansi();
        String kalimatBayar = "", bayar = "";
        kalimatBayar = Sequel.Terbilang(Sequel.cariIsiAngka("SELECT REPLACE(REPLACE(temp3,'.',''),',','') FROM temporary_bayar_ranap"));
        bayar = Sequel.cariIsi("SELECT temp3 FROM temporary_bayar_ranap");

        param.put("uang_sebanyak", kalimatBayar + " Rupiah.");
        param.put("terbilang", "Terbilang Rp. " + bayar);
        
        if (tte.equals("tidak")) {
            Valid.MyReport("rptNotaAmbulan.jasper", "report", "::[ Kwitansi pembayaran Ambulance ]::",
                    "SELECT * FROM temporary_bayar_ranap", param);
        } else if (tte.equals("ya")) {
            isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                    + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='011'"),
                            "Kuitansi Pembayaran Ambulance", nmpetugas,
                            Sequel.cariIsi("select date_format('" + Valid.SetTgl(tglNota.getSelectedItem() + "") + "','%d/%m/%Y')"),
                            Sequel.cariIsi("select time(now())")) + "') from kalimat_tte where kode='011'");

            Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
            Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
            Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Kuitansi", Sequel.cariFolderPrintTte());
            param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
            param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='011'"));

            Valid.MyReport("rptNotaAmbulanQr.jasper", "report", "::[ Kwitansi pembayaran Ambulance ]::",
                    "SELECT * FROM temporary_bayar_ranap", param);
            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);                       
        }
        
        emptTeks();
        selisihBaru();
        tampil();
        this.setCursor(Cursor.getDefaultCursor());        
    }
    
    private void cetakNotaNaikKls() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isi = "";
        nmpetugas = "";
        statusTran.setText(Sequel.cariIsi("select status_transaksi from biaya_naik_kelas_bpjs where no_transaksi='" + noTransaksi.getText() + "'"));
        
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tglNota", "Martapura, " + Valid.SetTglINDONESIA(Valid.SetTgl(tglNota.getSelectedItem() + "")));

        if (tte.equals("tidak")) {
            if (statusTran.getText().equals("dicicil")) {
                SimpanNotaNaikKelas();
                param.put("keterangan", Keterangan.getText());
                param.put("judul_kwitansi", "KUITANSI PEMBAYARAN (Angsuran Ke - " + bayarKe.getText() + ")");
                param.put("telah_terimaAN", telahTerimaAN.getText());
                param.put("uang_sebanyak", Sequel.Terbilang(Sequel.cariIsiAngka("SELECT REPLACE(REPLACE(temp13,'.',''),',','') FROM temporary_bayar_ranap")) + " Rupiah.");
                param.put("terbilang", Sequel.cariIsi("SELECT concat('Terbilang Rp. ',REPLACE(REPLACE(temp13,'.','.'),',','.')) FROM temporary_bayar_ranap"));

                Valid.MyReport("rptNotaSelisihTarifCicil.jasper", "report", "::[ Kwitansi pembayaran selisih tarif BPJS (Angsuran) ]::", "SELECT * FROM temporary_bayar_ranap", param);
                Sequel.mengedit("reg_periksa", "no_rawat='" + norw + "'", "p_jawab='" + telahTerimaAN.getText() + "' ");

            } else if (statusTran.getText().equals("lunas") || (statusTran.getText().equals(""))) {
                SimpanNotaNaikKelas();
                param.put("telah_terimaAN", telahTerimaAN.getText());
                param.put("uang_sebanyak", Sequel.Terbilang(Sequel.cariIsiAngka("SELECT REPLACE(REPLACE(temp13,'.',''),',','') FROM temporary_bayar_ranap")) + " Rupiah.");
                param.put("terbilang", Sequel.cariIsi("SELECT concat('Terbilang Rp. ',REPLACE(REPLACE(temp13,'.','.'),',','.')) FROM temporary_bayar_ranap"));

                Valid.MyReport("rptNotaSelisihTarif.jasper", "report", "::[ Kwitansi pembayaran selisih tarif BPJS (Pelunasan) ]::", "SELECT * FROM temporary_bayar_ranap", param);
                Sequel.mengedit("reg_periksa", "no_rawat='" + norw + "'", "p_jawab='" + telahTerimaAN.getText() + "' ");
            }
            emptTeks();
            selisihBaru();
            tampil();
        } else if (tte.equals("ya")) {
            if (statusTran.getText().equals("dicicil")) {
                SimpanNotaNaikKelas();
                param.put("keterangan", Keterangan.getText());
                param.put("judul_kwitansi", "KUITANSI PEMBAYARAN (Angsuran Ke - " + bayarKe.getText() + ")");
                param.put("telah_terimaAN", telahTerimaAN.getText());
                param.put("uang_sebanyak", Sequel.Terbilang(Sequel.cariIsiAngka("SELECT REPLACE(REPLACE(temp13,'.',''),',','') FROM temporary_bayar_ranap")) + " Rupiah.");
                param.put("terbilang", Sequel.cariIsi("SELECT concat('Terbilang Rp. ',REPLACE(REPLACE(temp13,'.','.'),',','.')) FROM temporary_bayar_ranap"));
                
                isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                        + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='011'"),
                                "Kuitansi Pembayaran (Angsuran Ke - " + bayarKe.getText() + ")", nmpetugas,
                                Sequel.cariIsi("select date_format('" + Valid.SetTgl(tglNota.getSelectedItem() + "") + "','%d/%m/%Y')"),
                                Sequel.cariIsi("select time(now())")) + "') from kalimat_tte where kode='011'");

                Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Kuitansi", Sequel.cariFolderPrintTte());
                param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='011'"));

                Valid.MyReport("rptNotaSelisihTarifCicilQr.jasper", "report", "::[ Kwitansi pembayaran selisih tarif BPJS (Angsuran) ]::", "SELECT * FROM temporary_bayar_ranap", param);
                Sequel.mengedit("reg_periksa", "no_rawat='" + norw + "'", "p_jawab='" + telahTerimaAN.getText() + "' ");
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);

            } else if (statusTran.getText().equals("lunas") || (statusTran.getText().equals(""))) {
                SimpanNotaNaikKelas();
                param.put("telah_terimaAN", telahTerimaAN.getText());
                param.put("uang_sebanyak", Sequel.Terbilang(Sequel.cariIsiAngka("SELECT REPLACE(REPLACE(temp13,'.',''),',','') FROM temporary_bayar_ranap")) + " Rupiah.");
                param.put("terbilang", Sequel.cariIsi("SELECT concat('Terbilang Rp. ',REPLACE(REPLACE(temp13,'.','.'),',','.')) FROM temporary_bayar_ranap"));

                isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                        + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='011'"),
                                "Kuitansi Pembayaran (Pelunasan)", nmpetugas,
                                Sequel.cariIsi("select date_format('" + Valid.SetTgl(tglNota.getSelectedItem() + "") + "','%d/%m/%Y')"),
                                Sequel.cariIsi("select time(now())")) + "') from kalimat_tte where kode='011'");

                Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Kuitansi", Sequel.cariFolderPrintTte());
                param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='011'"));

                Valid.MyReport("rptNotaSelisihTarifQr.jasper", "report", "::[ Kwitansi pembayaran selisih tarif BPJS (Pelunasan) ]::", "SELECT * FROM temporary_bayar_ranap", param);
                Sequel.mengedit("reg_periksa", "no_rawat='" + norw + "'", "p_jawab='" + telahTerimaAN.getText() + "' ");
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            }
            emptTeks();
            selisihBaru();
            tampil();
        }
        this.setCursor(Cursor.getDefaultCursor());        
    }
}
