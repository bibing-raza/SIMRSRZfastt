/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgAdmin.java
 *
 * Created on 21 Jun 10, 20:53:44
 */

package keuangan;

import fungsi.WarnaTable;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariPoli;
import simrskhanza.DlgPasien;

/**
 *
 * @author perpustakaan
 */
public class DlgRekapPerShift extends javax.swing.JDialog {
    private final DefaultTableModel tabModeRalan,tabModeRanap,tabModePemasukan,tabModePengeluaran;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private validasi Valid=new validasi();
    private DlgPasien pasien = new DlgPasien(null, false);
    private PreparedStatement psjamshift,pspasienralan,psbilling,pspasienranap,pspemasukan,pspengeluaran;
    private ResultSet rs,rspasien,rsbilling;
    private String tanggal2="",
            sqlpsjamshift="select * from closing_kasir ",
            sqlpsbilling="select billing.nm_perawatan,billing.totalbiaya,billing.status from billing where billing.no_rawat=? and billing.no_nota=?",
            sqlpspasienranap="select reg_periksa.no_rawat,nota_inap.no_nota,pasien.no_rkm_medis,pasien.nm_pasien,nota_inap.tanggal,nota_inap.jam,penjab.png_jawab,pasien.no_rkm_medis "+
                        "from reg_periksa inner join pasien inner join penjab inner join nota_inap "+
                        "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_pj=penjab.kd_pj and "+
                        "reg_periksa.no_rawat=nota_inap.no_rawat where reg_periksa.status_lanjut='Ranap' and "+
                        "reg_periksa.no_rawat not in (select piutang_pasien.no_rawat from piutang_pasien where piutang_pasien.no_rawat=reg_periksa.no_rawat) and "+
                        "concat(nota_inap.tanggal,' ',nota_inap.jam) between ? and ? order by nota_inap.no_nota",
//            sqlpspasienralan="select reg_periksa.no_rawat,nota_jalan.no_nota,pasien.no_rkm_medis,pasien.nm_pasien,nota_jalan.tanggal,nota_jalan.jam,dokter.nm_dokter,penjab.png_jawab "+
//                        "from reg_periksa inner join pasien inner join penjab inner join dokter inner join nota_jalan "+
//                        "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_pj=penjab.kd_pj and "+
//                        "reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rawat=nota_jalan.no_rawat where reg_periksa.status_lanjut='Ralan' and "+
//                        "reg_periksa.no_rawat not in (select piutang_pasien.no_rawat from piutang_pasien where piutang_pasien.no_rawat=reg_periksa.no_rawat) and "+
//                        "concat(nota_jalan.tanggal,' ',nota_jalan.jam) between ? and ? order by nota_jalan.no_nota",
            sqlpspasienralan="select distinct reg_periksa.no_rawat,nota_jalan.no_nota,pasien.no_rkm_medis,pasien.nm_pasien,nota_jalan.tanggal,nota_jalan.jam,dokter.nm_dokter,penjab.png_jawab "+
                        "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis inner join penjab on reg_periksa.kd_pj=penjab.kd_pj inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter inner join nota_jalan on reg_periksa.no_rawat=nota_jalan.no_rawat "+
                        "inner join billing on billing.no_nota = nota_jalan.no_nota and billing.no_rawat = nota_jalan.no_rawat  "+
                        "where reg_periksa.status_lanjut='Ralan' and "+
                        "reg_periksa.no_rawat not in (select piutang_pasien.no_rawat from piutang_pasien where piutang_pasien.no_rawat=reg_periksa.no_rawat) and "+
                        "concat(nota_jalan.tanggal,' ',nota_jalan.jam) between ? and ? order by nota_jalan.no_nota",
            sqlpspemasukan="select pemasukan_lain.tanggal, pemasukan_lain.keterangan, pemasukan_lain.besar, kategori_pemasukan_lain.nama_kategori "+
                        "from pemasukan_lain inner join kategori_pemasukan_lain on pemasukan_lain.kode_kategori=kategori_pemasukan_lain.kode_kategori "+
                        "where pemasukan_lain.tanggal between ? and ? order by pemasukan_lain.tanggal",
            sqlpspengeluaran="select pengeluaran_harian.tanggal, pengeluaran_harian.keterangan, pengeluaran_harian.biaya,  "+
                        "kategori_pengeluaran_harian.nama_kategori from pengeluaran_harian inner join kategori_pengeluaran_harian "+
                        "on pengeluaran_harian.kode_kategori=kategori_pengeluaran_harian.kode_kategori "+
                        "where pengeluaran_harian.tanggal between ? and ? order by pengeluaran_harian.tanggal";
    private int i;
    private double all=0,Laborat=0,Radiologi=0,Obat=0,Ralan_Dokter=0,Ralan_Dokter_Paramedis=0,Ralan_Paramedis=0,Tambahan=0,Potongan=0,Registrasi=0,Service=0,
                    ttlLaborat=0,ttlRadiologi=0,ttlObat=0,ttlRalan_Dokter=0,ttlRalan_Paramedis=0,ttlTambahan=0,ttlPotongan=0,ttlRegistrasi=0,ttlOperasi=0,
                    ttlRanap_Dokter=0,ttlRanap_Paramedis=0,ttlKamar=0,ttlHarian=0,ttlRetur_Obat=0,ttlResep_Pulang=0,ttlService=0,
                    Retur_Obat=0,Resep_Pulang=0,Harian=0,Kamar=0,Operasi=0,Ranap_Dokter=0,Ranap_Dokter_Paramedis=0,Ranap_Paramedis=0;
    /** Creates new form DlgAdmin
     * @param parent
     * @param modal */
    public DlgRekapPerShift(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,10);
        setSize(457,249);
        tabModeRalan=new DefaultTableModel(null,new Object[]{
            "Tanggal","No.Nota","No. RM","Nama Pasien","Jenis Bayar","Perujuk","Registrasi","Obat+Emb+Tsl",
            "Paket Tindakan","Laborat","Radiologi","Tambahan","Potongan","Total","Dokter"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbRalan.setModel(tabModeRalan);
        tbRalan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRalan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbRalan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(130);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==3){
                column.setPreferredWidth(170);
            }else if(i==4){
                column.setPreferredWidth(85);
            }else if(i==5){
                column.setPreferredWidth(90);
            }else if(i==14){
                column.setPreferredWidth(250);                
            }else{
                column.setPreferredWidth(85);
            }
        }

        tbRalan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeRanap=new DefaultTableModel(null,new Object[]{
            "Tanggal","No.Nota","No. RM","Nama Pasien","Jenis Bayar","Perujuk","Registrasi","Tindakan","Obt+Emb+Tsl",
            "Retur Obat","Resep Pulang","Laborat","Radiologi","Potongan","Tambahan","Kamar+Service","Operasi","Harian","Total"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbRanap.setModel(tabModeRanap);        
        tbRanap.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRanap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbRanap.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(130);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==3){
                column.setPreferredWidth(170);
            }else if(i==4){
                column.setPreferredWidth(85);
            }else if(i==5){
                column.setPreferredWidth(90);
            }else if(i==18){
                column.setPreferredWidth(100);
            }else{
                column.setPreferredWidth(75);
            }
        }
        tbRanap.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModePemasukan=new DefaultTableModel(null,new Object[]{
            "Tanggal","Kategori","Pemasukan","Keterangan"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbPemasukan.setModel(tabModePemasukan);
        tbPemasukan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPemasukan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbPemasukan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(130);
            }else if(i==1){
                column.setPreferredWidth(230);
            }else if(i==2){
                column.setPreferredWidth(100);
            }else if(i==3){
                column.setPreferredWidth(190);
            }
        }

        tbPemasukan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModePengeluaran=new DefaultTableModel(null,new Object[]{
            "Tanggal","Kategori","Pengeluaran","Keterangan"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRekapPerShift")) {
                    if (poli.getTable().getSelectedRow() != -1) {
//                        if (pilihan == 1) {
                        kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                        TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
//                            switch (TStatus.getText()) {
//                                case "Baru":
//                                    TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 2).toString());
//                                    break;
//                                case "Lama":
//                                    TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 3).toString());
//                                    break;
//                                default:
//                                    TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 2).toString());
//                                    break;
//                            }
//                            isNumber();
                        kdpoli.requestFocus();
//                        } else if (pilihan == 2) {
//                            CrPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
//                            CrPoli.requestFocus();
//                            tampil();
                    }
                }
//                }
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
        
        pasien.penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRekapPerShift")) {
                    if (pasien.penjab.getTable().getSelectedRow() != -1) {
                        kdpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 1).toString());
                        nmpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 2).toString());
                    }
                    kdpnj.requestFocus();
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

        pasien.penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgRekapPerShift")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });        

        tbPengeluaran.setModel(tabModePengeluaran);
        tbPengeluaran.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPengeluaran.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbPengeluaran.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(130);
            }else if(i==1){
                column.setPreferredWidth(230);
            }else if(i==2){
                column.setPreferredWidth(100);
            }else if(i==3){
                column.setPreferredWidth(190);
            }
        }

        tbPengeluaran.setDefaultRenderer(Object.class, new WarnaTable());
        
        
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
        jMnDetailPecahan = new javax.swing.JMenu();
        MnDetailRincianAll = new javax.swing.JMenuItem();
        MnDetailRincianPerCB = new javax.swing.JMenuItem();
        MnDetailRincianAllPerPoli = new javax.swing.JMenuItem();
        MnDetailRincianPerCBPerPoli = new javax.swing.JMenuItem();
        jMnTotalPecahan = new javax.swing.JMenu();
        MnTotalRincianAll = new javax.swing.JMenuItem();
        MnTotalRincianPerCB = new javax.swing.JMenuItem();
        jMnTambahanBiaya = new javax.swing.JMenu();
        MnTambahanBiayaAll = new javax.swing.JMenuItem();
        MnTambahanBiayaPerCB = new javax.swing.JMenuItem();
        jMnTotalPoli = new javax.swing.JMenu();
        MnUnitPoliAll = new javax.swing.JMenuItem();
        MnUnitPoliPerCB = new javax.swing.JMenuItem();
        MnRekapTotalAllCB = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelGlass5 = new widget.panelisi();
        label12 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        BtnCari1 = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        label19 = new widget.Label();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbRalan = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbRanap = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        tbPemasukan = new widget.Table();
        internalFrame5 = new widget.InternalFrame();
        Scroll4 = new widget.ScrollPane();
        tbPengeluaran = new widget.Table();
        panelBiasa1 = new widget.PanelBiasa();
        label13 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        label14 = new widget.Label();
        Tgl3 = new widget.Tanggal();
        label15 = new widget.Label();
        kdpnj = new widget.TextBox();
        nmpnj = new widget.TextBox();
        btnPenjab = new widget.Button();
        label16 = new widget.Label();
        kdpoli = new widget.TextBox();
        TPoli = new widget.TextBox();
        BtnUnit = new widget.Button();
        BtnRefres = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        jMnDetailPecahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMnDetailPecahan.setText("Lap. Rekap Detail Pecahan Transaksi");
        jMnDetailPecahan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnDetailPecahan.setName("jMnDetailPecahan"); // NOI18N
        jMnDetailPecahan.setPreferredSize(new java.awt.Dimension(255, 25));

        MnDetailRincianAll.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDetailRincianAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnDetailRincianAll.setText("Cetak Semua Cara Bayar Semua Poli");
        MnDetailRincianAll.setName("MnDetailRincianAll"); // NOI18N
        MnDetailRincianAll.setPreferredSize(new java.awt.Dimension(240, 25));
        MnDetailRincianAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDetailRincianAllActionPerformed(evt);
            }
        });
        jMnDetailPecahan.add(MnDetailRincianAll);

        MnDetailRincianPerCB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDetailRincianPerCB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnDetailRincianPerCB.setText("Cetak Per Cara Bayar Semua Poli");
        MnDetailRincianPerCB.setName("MnDetailRincianPerCB"); // NOI18N
        MnDetailRincianPerCB.setPreferredSize(new java.awt.Dimension(240, 25));
        MnDetailRincianPerCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDetailRincianPerCBActionPerformed(evt);
            }
        });
        jMnDetailPecahan.add(MnDetailRincianPerCB);

        MnDetailRincianAllPerPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDetailRincianAllPerPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnDetailRincianAllPerPoli.setText("Cetak Semua Cara Bayar Per Poli");
        MnDetailRincianAllPerPoli.setName("MnDetailRincianAllPerPoli"); // NOI18N
        MnDetailRincianAllPerPoli.setPreferredSize(new java.awt.Dimension(240, 25));
        MnDetailRincianAllPerPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDetailRincianAllPerPoliActionPerformed(evt);
            }
        });
        jMnDetailPecahan.add(MnDetailRincianAllPerPoli);

        MnDetailRincianPerCBPerPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDetailRincianPerCBPerPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnDetailRincianPerCBPerPoli.setText("Cetak Per Cara Bayar Per Poli");
        MnDetailRincianPerCBPerPoli.setName("MnDetailRincianPerCBPerPoli"); // NOI18N
        MnDetailRincianPerCBPerPoli.setPreferredSize(new java.awt.Dimension(240, 25));
        MnDetailRincianPerCBPerPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDetailRincianPerCBPerPoliActionPerformed(evt);
            }
        });
        jMnDetailPecahan.add(MnDetailRincianPerCBPerPoli);

        jPopupMenu1.add(jMnDetailPecahan);

        jMnTotalPecahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMnTotalPecahan.setText("Lap. Rekap Total Pecahan Trans. Pasien");
        jMnTotalPecahan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnTotalPecahan.setName("jMnTotalPecahan"); // NOI18N
        jMnTotalPecahan.setPreferredSize(new java.awt.Dimension(255, 25));

        MnTotalRincianAll.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTotalRincianAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTotalRincianAll.setText("Cetak Semua Cara Bayar");
        MnTotalRincianAll.setName("MnTotalRincianAll"); // NOI18N
        MnTotalRincianAll.setPreferredSize(new java.awt.Dimension(190, 25));
        MnTotalRincianAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTotalRincianAllActionPerformed(evt);
            }
        });
        jMnTotalPecahan.add(MnTotalRincianAll);

        MnTotalRincianPerCB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTotalRincianPerCB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTotalRincianPerCB.setText("Cetak Per Cara Bayar");
        MnTotalRincianPerCB.setName("MnTotalRincianPerCB"); // NOI18N
        MnTotalRincianPerCB.setPreferredSize(new java.awt.Dimension(190, 25));
        MnTotalRincianPerCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTotalRincianPerCBActionPerformed(evt);
            }
        });
        jMnTotalPecahan.add(MnTotalRincianPerCB);

        jPopupMenu1.add(jMnTotalPecahan);

        jMnTambahanBiaya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMnTambahanBiaya.setText("Lap. Rekap Total Biaya Tambahan");
        jMnTambahanBiaya.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnTambahanBiaya.setName("jMnTambahanBiaya"); // NOI18N
        jMnTambahanBiaya.setPreferredSize(new java.awt.Dimension(255, 25));

        MnTambahanBiayaAll.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTambahanBiayaAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTambahanBiayaAll.setText("Cetak Semua Cara Bayar");
        MnTambahanBiayaAll.setName("MnTambahanBiayaAll"); // NOI18N
        MnTambahanBiayaAll.setPreferredSize(new java.awt.Dimension(190, 25));
        MnTambahanBiayaAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTambahanBiayaAllActionPerformed(evt);
            }
        });
        jMnTambahanBiaya.add(MnTambahanBiayaAll);

        MnTambahanBiayaPerCB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTambahanBiayaPerCB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTambahanBiayaPerCB.setText("Cetak Per Cara Bayar");
        MnTambahanBiayaPerCB.setName("MnTambahanBiayaPerCB"); // NOI18N
        MnTambahanBiayaPerCB.setPreferredSize(new java.awt.Dimension(190, 25));
        MnTambahanBiayaPerCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTambahanBiayaPerCBActionPerformed(evt);
            }
        });
        jMnTambahanBiaya.add(MnTambahanBiayaPerCB);

        jPopupMenu1.add(jMnTambahanBiaya);

        jMnTotalPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMnTotalPoli.setText("Lap. Rekap Total Pecahan Trans. Unit/Poli");
        jMnTotalPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnTotalPoli.setName("jMnTotalPoli"); // NOI18N
        jMnTotalPoli.setPreferredSize(new java.awt.Dimension(255, 25));

        MnUnitPoliAll.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUnitPoliAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnUnitPoliAll.setText("Cetak Semua Cara Bayar");
        MnUnitPoliAll.setName("MnUnitPoliAll"); // NOI18N
        MnUnitPoliAll.setPreferredSize(new java.awt.Dimension(190, 25));
        MnUnitPoliAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUnitPoliAllActionPerformed(evt);
            }
        });
        jMnTotalPoli.add(MnUnitPoliAll);

        MnUnitPoliPerCB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUnitPoliPerCB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnUnitPoliPerCB.setText("Cetak Per Cara Bayar");
        MnUnitPoliPerCB.setName("MnUnitPoliPerCB"); // NOI18N
        MnUnitPoliPerCB.setPreferredSize(new java.awt.Dimension(190, 25));
        MnUnitPoliPerCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUnitPoliPerCBActionPerformed(evt);
            }
        });
        jMnTotalPoli.add(MnUnitPoliPerCB);

        jPopupMenu1.add(jMnTotalPoli);

        MnRekapTotalAllCB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapTotalAllCB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnRekapTotalAllCB.setText("Lap. Rekap Total Semua Cara Bayar");
        MnRekapTotalAllCB.setName("MnRekapTotalAllCB"); // NOI18N
        MnRekapTotalAllCB.setPreferredSize(new java.awt.Dimension(190, 25));
        MnRekapTotalAllCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapTotalAllCBActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRekapTotalAllCB);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Rekap Uang Pershift ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("Tgl. Rekap :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass5.add(label12);

        Tgl1.setEditable(false);
        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass5.add(Tgl1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('2');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+2");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(145, 23));
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
        panelGlass5.add(BtnCari1);

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
        panelGlass5.add(BtnPrint);

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
        panelGlass5.add(BtnKeluar);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass5.add(label19);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(250, 255, 245));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRawat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbRalan.setToolTipText("");
        tbRalan.setComponentPopupMenu(jPopupMenu1);
        tbRalan.setName("tbRalan"); // NOI18N
        Scroll.setViewportView(tbRalan);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        TabRawat.addTab(".: Rekap Pendapatan Ralan ", internalFrame2);

        internalFrame3.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbRanap.setAutoCreateRowSorter(true);
        tbRanap.setToolTipText("");
        tbRanap.setName("tbRanap"); // NOI18N
        Scroll2.setViewportView(tbRanap);

        internalFrame3.add(Scroll2, java.awt.BorderLayout.CENTER);

        TabRawat.addTab(".: Rekap Pendapatan Ranap ", internalFrame3);

        internalFrame4.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbPemasukan.setAutoCreateRowSorter(true);
        tbPemasukan.setToolTipText("");
        tbPemasukan.setName("tbPemasukan"); // NOI18N
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
        Scroll3.setViewportView(tbPemasukan);

        internalFrame4.add(Scroll3, java.awt.BorderLayout.CENTER);

        TabRawat.addTab(".: Rekap Pemasukan Lain-Lain ", internalFrame4);

        internalFrame5.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame5.setBorder(null);
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbPengeluaran.setAutoCreateRowSorter(true);
        tbPengeluaran.setToolTipText("");
        tbPengeluaran.setName("tbPengeluaran"); // NOI18N
        tbPengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPengeluaranMouseClicked(evt);
            }
        });
        tbPengeluaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPengeluaranKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbPengeluaran);

        internalFrame5.add(Scroll4, java.awt.BorderLayout.CENTER);

        TabRawat.addTab(".: Rekap Pengeluaran Harian ", internalFrame5);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        panelBiasa1.setName("panelBiasa1"); // NOI18N
        panelBiasa1.setPreferredSize(new java.awt.Dimension(12, 40));
        panelBiasa1.setLayout(null);

        label13.setForeground(new java.awt.Color(0, 0, 0));
        label13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label13.setText("Tgl. Laporan : ");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(80, 23));
        panelBiasa1.add(label13);
        label13.setBounds(10, 10, 70, 23);

        Tgl2.setEditable(false);
        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(100, 23));
        panelBiasa1.add(Tgl2);
        Tgl2.setBounds(85, 10, 100, 23);

        label14.setForeground(new java.awt.Color(0, 0, 0));
        label14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label14.setText("s.d");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(30, 23));
        panelBiasa1.add(label14);
        label14.setBounds(190, 10, 30, 23);

        Tgl3.setEditable(false);
        Tgl3.setDisplayFormat("dd-MM-yyyy");
        Tgl3.setName("Tgl3"); // NOI18N
        Tgl3.setPreferredSize(new java.awt.Dimension(100, 23));
        panelBiasa1.add(Tgl3);
        Tgl3.setBounds(225, 10, 100, 23);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label15.setText("Cara Bayar :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(65, 23));
        panelBiasa1.add(label15);
        label15.setBounds(330, 10, 65, 23);

        kdpnj.setForeground(new java.awt.Color(0, 0, 0));
        kdpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpnj.setEnabled(false);
        kdpnj.setHighlighter(null);
        kdpnj.setName("kdpnj"); // NOI18N
        kdpnj.setPreferredSize(new java.awt.Dimension(60, 24));
        kdpnj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpnjKeyPressed(evt);
            }
        });
        panelBiasa1.add(kdpnj);
        kdpnj.setBounds(400, 10, 50, 24);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nmpnj.setEnabled(false);
        nmpnj.setName("nmpnj"); // NOI18N
        nmpnj.setPreferredSize(new java.awt.Dimension(180, 24));
        panelBiasa1.add(nmpnj);
        nmpnj.setBounds(455, 10, 190, 24);

        btnPenjab.setForeground(new java.awt.Color(0, 0, 0));
        btnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenjab.setMnemonic('2');
        btnPenjab.setToolTipText("ALt+2");
        btnPenjab.setName("btnPenjab"); // NOI18N
        btnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjabActionPerformed(evt);
            }
        });
        panelBiasa1.add(btnPenjab);
        btnPenjab.setBounds(650, 10, 36, 26);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label16.setText("Poli/Unit :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(50, 23));
        panelBiasa1.add(label16);
        label16.setBounds(690, 10, 50, 23);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpoli.setEnabled(false);
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.setPreferredSize(new java.awt.Dimension(55, 23));
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });
        panelBiasa1.add(kdpoli);
        kdpoli.setBounds(740, 10, 50, 23);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TPoli.setEnabled(false);
        TPoli.setName("TPoli"); // NOI18N
        TPoli.setPreferredSize(new java.awt.Dimension(160, 23));
        panelBiasa1.add(TPoli);
        TPoli.setBounds(795, 10, 180, 23);

        BtnUnit.setForeground(new java.awt.Color(0, 0, 0));
        BtnUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnUnit.setMnemonic('4');
        BtnUnit.setToolTipText("ALt+4");
        BtnUnit.setName("BtnUnit"); // NOI18N
        BtnUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnitActionPerformed(evt);
            }
        });
        panelBiasa1.add(BtnUnit);
        BtnUnit.setBounds(975, 10, 36, 26);

        BtnRefres.setForeground(new java.awt.Color(0, 0, 0));
        BtnRefres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnRefres.setMnemonic('3');
        BtnRefres.setText("Refresh");
        BtnRefres.setToolTipText("Alt+3");
        BtnRefres.setName("BtnRefres"); // NOI18N
        BtnRefres.setPreferredSize(new java.awt.Dimension(145, 23));
        BtnRefres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefresActionPerformed(evt);
            }
        });
        BtnRefres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnRefresKeyPressed(evt);
            }
        });
        panelBiasa1.add(BtnRefres);
        BtnRefres.setBounds(1020, 10, 90, 23);

        internalFrame1.add(panelBiasa1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();        
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,Tgl1);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(TabRawat.getSelectedIndex()==0){
           tampilralan();
        }else if(TabRawat.getSelectedIndex()==1){
           tampilranap();
        }else if(TabRawat.getSelectedIndex()==2){
           tampilpemasukan();
        }else if(TabRawat.getSelectedIndex()==3){
           tampilpengeluaran();
        }
    }//GEN-LAST:event_formWindowOpened

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==0){
           tampilralan();
        }else if(TabRawat.getSelectedIndex()==1){
           tampilranap();
        }else if(TabRawat.getSelectedIndex()==2){
           tampilpemasukan();
        }else if(TabRawat.getSelectedIndex()==3){
           tampilpengeluaran();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        //if(evt.getKeyCode()==KeyEvent.VK_SPACE){
        //    BtnPrintActionPerformed(null);
        //}else{
        //    Valid.pindah(evt,BtnAll,BtnKeluar);
       // }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if(TabRawat.getSelectedIndex()==0){
            if(tabModeRalan.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            }else if(tabModeRalan.getRowCount()!=0){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Sequel.AutoComitFalse();
                Sequel.queryu("delete from temporary");
                for(int r=0;r<tabModeRalan.getRowCount();r++){  
                        Sequel.menyimpan("temporary","'0','"+
                                        tabModeRalan.getValueAt(r,0).toString().replaceAll("'","`") +"','"+
                                        tabModeRalan.getValueAt(r,1).toString().replaceAll("'","`")+"','"+
                                        tabModeRalan.getValueAt(r,2).toString().replaceAll("'","`")+"','"+
                                        tabModeRalan.getValueAt(r,3).toString().replaceAll("'","`")+"','"+
                                        tabModeRalan.getValueAt(r,4).toString().replaceAll("'","`")+"','"+
                                        tabModeRalan.getValueAt(r,5).toString().replaceAll("'","`")+"','"+
                                        tabModeRalan.getValueAt(r,6).toString().replaceAll("'","`")+"','"+
                                        tabModeRalan.getValueAt(r,7).toString().replaceAll("'","`")+"','"+
                                        tabModeRalan.getValueAt(r,8).toString().replaceAll("'","`")+"','"+
                                        tabModeRalan.getValueAt(r,9).toString().replaceAll("'","`")+"','"+
                                        tabModeRalan.getValueAt(r,10).toString().replaceAll("'","`")+"','"+
                                        tabModeRalan.getValueAt(r,11).toString().replaceAll("'","`")+"','"+
                                        tabModeRalan.getValueAt(r,12).toString().replaceAll("'","`")+"','"+
                                        tabModeRalan.getValueAt(r,13).toString().replaceAll("'","`")+"','"+
                                        tabModeRalan.getValueAt(r,14).toString().replaceAll("'","`")+"','','','','','','','','','','','','','','','','','','','','','',''","data");
                }
                Sequel.AutoComitTrue();
                Map<String, Object> param = new HashMap<>();                 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select logo from setting")); 
                Valid.MyReport("rptRekapPendapatanRalan.jasper","report","::[ Rekap Pendapatan Ralan ]::",
                    "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14,temp15, temp16 from temporary order by no asc",param);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }else if(TabRawat.getSelectedIndex()==1){
           if(tabModeRanap.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            }else if(tabModeRanap.getRowCount()!=0){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Sequel.AutoComitFalse();
                Sequel.queryu("delete from temporary");
                for(int r=0;r<tabModeRanap.getRowCount();r++){  
                        Sequel.menyimpan("temporary","'0','"+
                                        tabModeRanap.getValueAt(r,0).toString().replaceAll("'","`") +"','"+
                                        tabModeRanap.getValueAt(r,1).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,2).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,3).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,4).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,5).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,6).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,7).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,8).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,9).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,10).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,11).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,12).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,13).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,14).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,15).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,16).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,17).toString().replaceAll("'","`")+"','"+
                                        tabModeRanap.getValueAt(r,18).toString().replaceAll("'","`")+"','','','','','','','','','','','','','','','','','',''","data");
                }
                Sequel.AutoComitTrue();
                Map<String, Object> param = new HashMap<>();                 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select logo from setting")); 
                Valid.MyReport("rptRekapPendapatanRanap.jasper","report","::[ Rekap Pendapatan Ranap ]::",
                    "select * from temporary order by no asc",param);
                this.setCursor(Cursor.getDefaultCursor());
            }
                
                
        }else if(TabRawat.getSelectedIndex()==2){
           if(tabModePemasukan.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            }else if(tabModePemasukan.getRowCount()!=0){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Sequel.AutoComitFalse();
                Sequel.queryu("delete from temporary");
                for(int r=0;r<tabModePemasukan.getRowCount();r++){  
                        Sequel.menyimpan("temporary","'0','"+
                                        tabModePemasukan.getValueAt(r,0).toString().replaceAll("'","`") +"','"+
                                        tabModePemasukan.getValueAt(r,1).toString().replaceAll("'","`")+"','"+
                                        tabModePemasukan.getValueAt(r,2).toString().replaceAll("'","`")+"','"+
                                        tabModePemasukan.getValueAt(r,3).toString().replaceAll("'","`")+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''","data");
                }
                Sequel.AutoComitTrue();
                Map<String, Object> param = new HashMap<>();                 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select logo from setting")); 
                Valid.MyReport("rptRekapPemasukanLain.jasper","report","::[ Rekap Pemasukan Lain ]::",
                    "select * from temporary order by no asc",param);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }else if(TabRawat.getSelectedIndex()==3){
           if(tabModePengeluaran.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            }else if(tabModePengeluaran.getRowCount()!=0){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Sequel.AutoComitFalse();
                Sequel.queryu("delete from temporary");
                for(int r=0;r<tabModePengeluaran.getRowCount();r++){  
                        Sequel.menyimpan("temporary","'0','"+
                                        tabModePengeluaran.getValueAt(r,0).toString().replaceAll("'","`") +"','"+
                                        tabModePengeluaran.getValueAt(r,1).toString().replaceAll("'","`")+"','"+
                                        tabModePengeluaran.getValueAt(r,2).toString().replaceAll("'","`")+"','"+
                                        tabModePengeluaran.getValueAt(r,3).toString().replaceAll("'","`")+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''","data");
                }
                Sequel.AutoComitTrue();
                Map<String, Object> param = new HashMap<>();                 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select logo from setting")); 
                Valid.MyReport("rptRekapPengeluaranHarian.jasper","report","::[ Rekap Pengeluaran Harian ]::",
                    "select * from temporary order by no asc",param);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }            
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        TabRawatMouseClicked(null);
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        /*if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            tampil();
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            Valid.pindah(evt, TKd, BtnPrint);
        }*/
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void tbPemasukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemasukanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPemasukanMouseClicked

    private void tbPemasukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemasukanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPemasukanKeyPressed

    private void tbPengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPengeluaranMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPengeluaranMouseClicked

    private void tbPengeluaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPengeluaranKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPengeluaranKeyPressed

    private void kdpnjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpnjKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpnj, kdpnj.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            btnPenjab.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnPenjab.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnPenjabActionPerformed(null);
        }
    }//GEN-LAST:event_kdpnjKeyPressed

    private void btnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjabActionPerformed
        akses.setform("DlgRekapPerShift");
        pasien.penjab.onCari();
        pasien.penjab.isCek();
        pasien.penjab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.penjab.setLocationRelativeTo(internalFrame1);
        pasien.penjab.setVisible(true);
    }//GEN-LAST:event_btnPenjabActionPerformed

    private void MnDetailRincianAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDetailRincianAllActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl2.getSelectedItem()+""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl3.getSelectedItem()+""));        
        Valid.MyReport("rptDetailRincianPecahanJalanAllCB.jasper", "report", "::[ Laporan Detail Rekap Rincian Pecahan Transaksi Semua Cara Bayar Semua Poli ]::",
                " SELECT rawat_jl_drpr.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, reg_periksa.tgl_registrasi, "
                + " penjab.png_jawab, jns_perawatan.nm_perawatan, rawat_jl_drpr.bhp AS bakhp, rawat_jl_drpr.tarif_tindakandr AS js_dokter, "
                + " rawat_jl_drpr.tarif_tindakanpr AS jp, rawat_jl_drpr.material AS js_rs, rawat_jl_drpr.biaya_rawat AS total, "
                + " poliklinik.nm_poli, dokter.nm_dokter "
                + " FROM rawat_jl_drpr INNER JOIN reg_periksa ON rawat_jl_drpr.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " INNER JOIN jns_perawatan ON rawat_jl_drpr.kd_jenis_prw = jns_perawatan.kd_jenis_prw "
                + " INNER JOIN dokter ON rawat_jl_drpr.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN petugas ON rawat_jl_drpr.nip = petugas.nip "
                + " where reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl3.getSelectedItem() + "") + "' "
                + " ORDER BY reg_periksa.kd_pj, reg_periksa.tgl_registrasi ASC", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDetailRincianAllActionPerformed

    private void MnTotalRincianAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTotalRincianAllActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl2.getSelectedItem()+""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl3.getSelectedItem()+""));        
        Valid.MyReport("rptTotalRincianPecahanJalanAllCB.jasper", "report", "::[ Laporan Rekap Total Rincian Pecahan Transaksi Semua Cara Bayar ]::",
                " SELECT rawat_jl_drpr.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, sum(rawat_jl_drpr.bhp) AS bakhp, "
                + " sum(rawat_jl_drpr.tarif_tindakandr) AS js_dokter, sum(rawat_jl_drpr.tarif_tindakanpr) AS jp, sum(rawat_jl_drpr.material) AS js_rs, "
                + " sum(rawat_jl_drpr.biaya_rawat) AS total, poliklinik.nm_poli, dokter.nm_dokter "
                + " FROM rawat_jl_drpr INNER JOIN reg_periksa ON rawat_jl_drpr.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " INNER JOIN jns_perawatan ON rawat_jl_drpr.kd_jenis_prw = jns_perawatan.kd_jenis_prw "
                + " INNER JOIN dokter ON rawat_jl_drpr.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN petugas ON rawat_jl_drpr.nip = petugas.nip "
                + " where reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl3.getSelectedItem() + "") + "' "
                + " GROUP BY rawat_jl_drpr.no_rawat ORDER BY reg_periksa.kd_pj, reg_periksa.kd_poli ASC", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTotalRincianAllActionPerformed

    private void MnTambahanBiayaAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTambahanBiayaAllActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl2.getSelectedItem()+""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl3.getSelectedItem()+""));        
        Valid.MyReport("rptTotalBiayaTambahanJalanAllCB.jasper", "report", "::[ Laporan Rekap Total Biaya Tambahan Semua Cara Bayar ]::",
                " SELECT tambahan_biaya.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, poliklinik.nm_poli, "
                + " dokter.nm_dokter, tambahan_biaya.nama_biaya, tambahan_biaya.besar_biaya "
                + " FROM tambahan_biaya INNER JOIN reg_periksa ON tambahan_biaya.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " INNER JOIN dokter ON reg_periksa.kd_dokter = dokter.kd_dokter "
                + " WHERE reg_periksa.status_lanjut='Ralan' and reg_periksa.tgl_registrasi "
                + " BETWEEN '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl3.getSelectedItem() + "") + "' "
                + " ORDER BY reg_periksa.kd_pj, reg_periksa.kd_poli, reg_periksa.tgl_registrasi ASC", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTambahanBiayaAllActionPerformed

    private void MnDetailRincianPerCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDetailRincianPerCBActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl2.getSelectedItem()+""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl3.getSelectedItem()+""));        
        Valid.MyReport("rptDetailRincianPecahanJalanPerCB.jasper", "report", "::[ Laporan Detail Rekap Rincian Pecahan Transaksi Per Cara Bayar Semua Poli ]::",              
                " SELECT rawat_jl_drpr.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, reg_periksa.tgl_registrasi, "
                + " penjab.png_jawab, jns_perawatan.nm_perawatan, rawat_jl_drpr.bhp AS bakhp, rawat_jl_drpr.tarif_tindakandr AS js_dokter, "
                + " rawat_jl_drpr.tarif_tindakanpr AS jp, rawat_jl_drpr.material AS js_rs, rawat_jl_drpr.biaya_rawat AS total, "
                + " poliklinik.nm_poli, dokter.nm_dokter "
                + " FROM rawat_jl_drpr INNER JOIN reg_periksa ON rawat_jl_drpr.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " INNER JOIN jns_perawatan ON rawat_jl_drpr.kd_jenis_prw = jns_perawatan.kd_jenis_prw "
                + " INNER JOIN dokter ON rawat_jl_drpr.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN petugas ON rawat_jl_drpr.nip = petugas.nip "
                + " where reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl3.getSelectedItem() + "") + "' "
                + " and reg_periksa.kd_pj='" + kdpnj.getText() + "' ORDER BY reg_periksa.kd_poli, reg_periksa.tgl_registrasi ASC", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDetailRincianPerCBActionPerformed

    private void MnTotalRincianPerCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTotalRincianPerCBActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl2.getSelectedItem()+""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl3.getSelectedItem()+""));        
        Valid.MyReport("rptTotalRincianPecahanJalanPerCB.jasper", "report", "::[ Laporan Rekap Total Rincian Pecahan Transaksi Per Cara Bayar ]::",
                " SELECT rawat_jl_drpr.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, sum(rawat_jl_drpr.bhp) AS bakhp, "
                + " sum(rawat_jl_drpr.tarif_tindakandr) AS js_dokter, sum(rawat_jl_drpr.tarif_tindakanpr) AS jp, sum(rawat_jl_drpr.material) AS js_rs, "
                + " sum(rawat_jl_drpr.biaya_rawat) AS total, poliklinik.nm_poli, dokter.nm_dokter "
                + " FROM rawat_jl_drpr INNER JOIN reg_periksa ON rawat_jl_drpr.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " INNER JOIN jns_perawatan ON rawat_jl_drpr.kd_jenis_prw = jns_perawatan.kd_jenis_prw "
                + " INNER JOIN dokter ON rawat_jl_drpr.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN petugas ON rawat_jl_drpr.nip = petugas.nip "
                + " where reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl3.getSelectedItem() + "") + "' "
                + " and reg_periksa.kd_pj='" + kdpnj.getText() + "' GROUP BY rawat_jl_drpr.no_rawat ORDER BY reg_periksa.kd_poli, reg_periksa.tgl_registrasi ASC", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTotalRincianPerCBActionPerformed

    private void MnUnitPoliAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUnitPoliAllActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl2.getSelectedItem()+""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl3.getSelectedItem()+""));        
        Valid.MyReport("rptTotalRincianPecahanPoliAllCB.jasper", "report", "::[ Laporan Rekap Total Rincian Pecahan Transaksi Unit/Poli Semua Cara Bayar ]::",
                " SELECT poliklinik.nm_poli, penjab.png_jawab, sum(rawat_jl_drpr.bhp) AS bakhp, sum(rawat_jl_drpr.tarif_tindakandr) AS js_dokter, "
                + " sum(rawat_jl_drpr.tarif_tindakanpr) AS jp, sum(rawat_jl_drpr.material) AS js_rs, sum(rawat_jl_drpr.biaya_rawat) AS total "
                + " FROM rawat_jl_drpr INNER JOIN reg_periksa ON rawat_jl_drpr.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " INNER JOIN jns_perawatan ON rawat_jl_drpr.kd_jenis_prw = jns_perawatan.kd_jenis_prw "
                + " INNER JOIN dokter ON rawat_jl_drpr.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN petugas ON rawat_jl_drpr.nip = petugas.nip "
                + " where reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl3.getSelectedItem() + "") + "' "
                + " GROUP BY reg_periksa.kd_poli, reg_periksa.kd_pj ORDER BY reg_periksa.kd_pj, reg_periksa.kd_poli ASC", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnUnitPoliAllActionPerformed

    private void MnUnitPoliPerCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUnitPoliPerCBActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl2.getSelectedItem()+""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl3.getSelectedItem()+""));        
        Valid.MyReport("rptTotalRincianPecahanPoliPerCB.jasper", "report", "::[ Laporan Rekap Total Rincian Pecahan Transaksi Unit/Poli Per Cara Bayar ]::",
                " SELECT poliklinik.nm_poli, penjab.png_jawab, sum(rawat_jl_drpr.bhp) AS bakhp, sum(rawat_jl_drpr.tarif_tindakandr) AS js_dokter, "
                + " sum(rawat_jl_drpr.tarif_tindakanpr) AS jp, sum(rawat_jl_drpr.material) AS js_rs, sum(rawat_jl_drpr.biaya_rawat) AS total "
                + " FROM rawat_jl_drpr INNER JOIN reg_periksa ON rawat_jl_drpr.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " INNER JOIN jns_perawatan ON rawat_jl_drpr.kd_jenis_prw = jns_perawatan.kd_jenis_prw "
                + " INNER JOIN dokter ON rawat_jl_drpr.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN petugas ON rawat_jl_drpr.nip = petugas.nip "
                + " where reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl3.getSelectedItem() + "") + "' "
                + " and reg_periksa.kd_pj='" + kdpnj.getText() + "' GROUP BY reg_periksa.kd_poli, reg_periksa.kd_pj ORDER BY reg_periksa.kd_poli ASC", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnUnitPoliPerCBActionPerformed

    private void MnRekapTotalAllCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapTotalAllCBActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl2.getSelectedItem()+""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl3.getSelectedItem()+""));        
        Valid.MyReport("rptTotalRincianPecahanCB.jasper", "report", "::[ Laporan Rekap Total Pecahan Transaksi Berdasarkan Cara Bayar ]::",
                " SELECT penjab.png_jawab, sum(rawat_jl_drpr.bhp) AS bakhp, sum(rawat_jl_drpr.tarif_tindakandr) AS js_dokter, "
                + " sum(rawat_jl_drpr.tarif_tindakanpr) AS jp, sum(rawat_jl_drpr.material) AS js_rs, sum(rawat_jl_drpr.biaya_rawat) AS total "
                + " FROM rawat_jl_drpr INNER JOIN reg_periksa ON rawat_jl_drpr.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " INNER JOIN jns_perawatan ON rawat_jl_drpr.kd_jenis_prw = jns_perawatan.kd_jenis_prw "
                + " INNER JOIN dokter ON rawat_jl_drpr.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN petugas ON rawat_jl_drpr.nip = petugas.nip "
                + " where reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl3.getSelectedItem() + "") + "' "
                + " GROUP BY reg_periksa.kd_pj ORDER BY reg_periksa.kd_pj ASC", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapTotalAllCBActionPerformed

    private void MnTambahanBiayaPerCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTambahanBiayaPerCBActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_awal", Valid.SetTgl(Tgl2.getSelectedItem()+""));
        param.put("tgl_akhir", Valid.SetTgl(Tgl3.getSelectedItem()+""));        
        Valid.MyReport("rptTotalBiayaTambahanJalanPerCB.jasper", "report", "::[ Laporan Rekap Total Biaya Tambahan Per Cara Bayar ]::",
                " SELECT tambahan_biaya.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, penjab.png_jawab, poliklinik.nm_poli, "
                + " dokter.nm_dokter, tambahan_biaya.nama_biaya, tambahan_biaya.besar_biaya "
                + " FROM tambahan_biaya INNER JOIN reg_periksa ON tambahan_biaya.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " INNER JOIN dokter ON reg_periksa.kd_dokter = dokter.kd_dokter "
                + " WHERE reg_periksa.status_lanjut='Ralan' and reg_periksa.tgl_registrasi "
                + " BETWEEN '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl3.getSelectedItem() + "") + "' and reg_periksa.kd_pj='" + kdpnj.getText() + "' "
                + " ORDER BY reg_periksa.kd_pj, reg_periksa.kd_poli, reg_periksa.tgl_registrasi ASC", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnTambahanBiayaPerCBActionPerformed

    private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            //            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", TPoli, kdpoli.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            //            BtnUnitActionPerformed(null);
        } else {
            //            Valid.pindah(evt, kddokter, TNoRM);
        }
    }//GEN-LAST:event_kdpoliKeyPressed

    private void BtnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnitActionPerformed
        TPoli.setText("");
        kdpoli.setText("");

        akses.setform("DlgRekapPerShift");

        poli.isCek();
        poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnUnitActionPerformed

    private void MnDetailRincianAllPerPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDetailRincianAllPerPoliActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));  
        param.put("periode",Tgl2.getSelectedItem()+" s.d "+Tgl3.getSelectedItem());  
        Valid.MyReport("rptDetailRincianPecahanJalanAllCBPerPoli.jasper", "report", "::[ Laporan Detail Rekap Rincian Pecahan Transaksi Semua Cara Bayar Per Poli ]::",
                " SELECT rawat_jl_drpr.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, reg_periksa.tgl_registrasi, "
                + " penjab.png_jawab, jns_perawatan.nm_perawatan, rawat_jl_drpr.bhp AS bakhp, rawat_jl_drpr.tarif_tindakandr AS js_dokter, "
                + " rawat_jl_drpr.tarif_tindakanpr AS jp, rawat_jl_drpr.material AS js_rs, rawat_jl_drpr.biaya_rawat AS total, "
                + " poliklinik.nm_poli, dokter.nm_dokter "
                + " FROM rawat_jl_drpr INNER JOIN reg_periksa ON rawat_jl_drpr.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " INNER JOIN jns_perawatan ON rawat_jl_drpr.kd_jenis_prw = jns_perawatan.kd_jenis_prw "
                + " INNER JOIN dokter ON rawat_jl_drpr.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN petugas ON rawat_jl_drpr.nip = petugas.nip "
                + " where reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl3.getSelectedItem() + "") + "' "
                + " AND reg_periksa.kd_poli='" + kdpoli.getText() + "' "
                + " ORDER BY penjab.png_jawab, reg_periksa.no_rawat, reg_periksa.tgl_registrasi ASC", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDetailRincianAllPerPoliActionPerformed

    private void MnDetailRincianPerCBPerPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDetailRincianPerCBPerPoliActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));  
        param.put("periode",Tgl2.getSelectedItem()+" s.d "+Tgl3.getSelectedItem());  
        Valid.MyReport("rptDetailRincianPecahanJalanPerCBPoli.jasper", "report", "::[ Laporan Detail Rekap Rincian Pecahan Transaksi Per Cara Bayar Per Poli ]::",
                " SELECT rawat_jl_drpr.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, reg_periksa.tgl_registrasi, "
                + " penjab.png_jawab, jns_perawatan.nm_perawatan, rawat_jl_drpr.bhp AS bakhp, rawat_jl_drpr.tarif_tindakandr AS js_dokter, "
                + " rawat_jl_drpr.tarif_tindakanpr AS jp, rawat_jl_drpr.material AS js_rs, rawat_jl_drpr.biaya_rawat AS total, "
                + " poliklinik.nm_poli, dokter.nm_dokter "
                + " FROM rawat_jl_drpr INNER JOIN reg_periksa ON rawat_jl_drpr.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                + " INNER JOIN jns_perawatan ON rawat_jl_drpr.kd_jenis_prw = jns_perawatan.kd_jenis_prw "
                + " INNER JOIN dokter ON rawat_jl_drpr.kd_dokter = dokter.kd_dokter "
                + " INNER JOIN petugas ON rawat_jl_drpr.nip = petugas.nip "
                + " where reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl3.getSelectedItem() + "") + "' "
                + " AND reg_periksa.kd_poli='" + kdpoli.getText() + "' and reg_periksa.kd_pj='" + kdpnj.getText() + "' "
                + " ORDER BY penjab.png_jawab, reg_periksa.no_rawat, reg_periksa.tgl_registrasi ASC", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnDetailRincianPerCBPerPoliActionPerformed

    private void BtnRefresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefresActionPerformed
        kdpoli.setText("");
        TPoli.setText("");
        kdpnj.setText("");
        nmpnj.setText("");
        Tgl1.setDate(new Date());       
        Tgl2.setDate(new Date());
        Tgl3.setDate(new Date());        
    }//GEN-LAST:event_BtnRefresActionPerformed

    private void BtnRefresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnRefresKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRefresKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRekapPerShift dialog = new DlgRekapPerShift(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnRefres;
    private widget.Button BtnUnit;
    private javax.swing.JMenuItem MnDetailRincianAll;
    private javax.swing.JMenuItem MnDetailRincianAllPerPoli;
    private javax.swing.JMenuItem MnDetailRincianPerCB;
    private javax.swing.JMenuItem MnDetailRincianPerCBPerPoli;
    private javax.swing.JMenuItem MnRekapTotalAllCB;
    private javax.swing.JMenuItem MnTambahanBiayaAll;
    private javax.swing.JMenuItem MnTambahanBiayaPerCB;
    private javax.swing.JMenuItem MnTotalRincianAll;
    private javax.swing.JMenuItem MnTotalRincianPerCB;
    private javax.swing.JMenuItem MnUnitPoliAll;
    private javax.swing.JMenuItem MnUnitPoliPerCB;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.TextBox TPoli;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.Tanggal Tgl3;
    private widget.Button btnPenjab;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private javax.swing.JMenu jMnDetailPecahan;
    private javax.swing.JMenu jMnTambahanBiaya;
    private javax.swing.JMenu jMnTotalPecahan;
    private javax.swing.JMenu jMnTotalPoli;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kdpnj;
    private widget.TextBox kdpoli;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label19;
    private widget.TextBox nmpnj;
    private widget.PanelBiasa panelBiasa1;
    private widget.panelisi panelGlass5;
    private widget.Table tbPemasukan;
    private widget.Table tbPengeluaran;
    private widget.Table tbRalan;
    private widget.Table tbRanap;
    // End of variables declaration//GEN-END:variables

    private void tampilralan() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
        Valid.tabelKosong(tabModeRalan);
        try{      
            psjamshift=koneksi.prepareStatement(sqlpsjamshift);
            try {
                rs=psjamshift.executeQuery();
                while(rs.next()){
                    tabModeRalan.addRow(new Object[]{
                        "Shift : "+rs.getString("shift"),rs.getString("jam_masuk")+" - "+rs.getString("jam_pulang"),"","","","","","","","","","","","",""
                    });
                    pspasienralan= koneksi.prepareStatement(sqlpspasienralan);
                    try {
                        pspasienralan.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+"")+" "+rs.getString("jam_masuk"));
                        if(rs.getString("shift").equals("Malam")){
                            tanggal2=Sequel.cariIsi("select DATE_ADD('"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" "+rs.getString("jam_pulang")+"',INTERVAL 1 DAY)");
                            pspasienralan.setString(2,tanggal2);
                        }else{
                            pspasienralan.setString(2,Valid.SetTgl(Tgl1.getSelectedItem()+"")+" "+rs.getString("jam_pulang"));
                        }
                        rspasien=pspasienralan.executeQuery();
                        all=0;ttlLaborat=0;ttlRadiologi=0;ttlObat=0;ttlRalan_Dokter=0;
                        ttlRalan_Paramedis=0;ttlTambahan=0;ttlPotongan=0;ttlRegistrasi=0;                
                        i=1;
                        while(rspasien.next()){
                            Laborat=0;Radiologi=0;Obat=0;Ralan_Dokter=0;Ralan_Dokter_Paramedis=0;Ralan_Paramedis=0;Tambahan=0;Potongan=0;Registrasi=0;
                            psbilling=koneksi.prepareStatement(sqlpsbilling);
                            try {
                                psbilling.setString(1,rspasien.getString("no_rawat"));
                                psbilling.setString(2,rspasien.getString("no_nota"));
                                rsbilling=psbilling.executeQuery(); 
                                while(rsbilling.next()){
                                    switch (rsbilling.getString("status")) {
                                        case "Laborat":
                                            ttlLaborat=ttlLaborat+rsbilling.getDouble("totalbiaya");
                                            Laborat=Laborat+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Radiologi":
                                            ttlRadiologi=ttlRadiologi+rsbilling.getDouble("totalbiaya");
                                            Radiologi=Radiologi+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Obat":
                                            ttlObat=ttlObat+rsbilling.getDouble("totalbiaya");
                                            Obat=Obat+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Ralan Dokter":
                                            ttlRalan_Dokter=ttlRalan_Dokter+rsbilling.getDouble("totalbiaya");
                                            Ralan_Dokter=Ralan_Dokter+rsbilling.getDouble("totalbiaya");
                                            break;     
                                        case "Ralan Dokter Paramedis":
                                            ttlRalan_Dokter=ttlRalan_Dokter+rsbilling.getDouble("totalbiaya");
                                            Ralan_Dokter_Paramedis=Ralan_Dokter_Paramedis+rsbilling.getDouble("totalbiaya");
                                            break;    
                                        case "Ralan Paramedis":
                                            ttlRalan_Paramedis=ttlRalan_Paramedis+rsbilling.getDouble("totalbiaya");
                                            Ralan_Paramedis=Ralan_Paramedis+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Tambahan":
                                            ttlTambahan=ttlTambahan+rsbilling.getDouble("totalbiaya");
                                            Tambahan=Tambahan+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Potongan":
                                            ttlPotongan=ttlPotongan+rsbilling.getDouble("totalbiaya");
                                            Potongan=Potongan+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Registrasi":
                                            ttlRegistrasi=ttlRegistrasi+rsbilling.getDouble("totalbiaya");
                                            Registrasi=Registrasi+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Operasi":
                                            ttlOperasi=ttlOperasi+rsbilling.getDouble("operasi");
                                            Operasi=Operasi+rsbilling.getDouble("operasi");
                                            break;  
                                    }                        
                                }
                                all=all+Laborat+Radiologi+Obat+Ralan_Dokter+Ralan_Dokter_Paramedis+Ralan_Paramedis+Tambahan+Potongan+Registrasi+Operasi;
                                tabModeRalan.addRow(new Object[]{
                                    i+". "+rspasien.getString("tanggal")+" "+rspasien.getString("jam"),rspasien.getString("no_nota"),rspasien.getString("no_rkm_medis"),
                                    rspasien.getString("nm_pasien"),rspasien.getString("png_jawab"),Sequel.cariIsi("select perujuk from rujuk_masuk where no_rawat=?",rspasien.getString("no_rawat")),
                                    Valid.SetAngka(Registrasi),Valid.SetAngka(Obat),Valid.SetAngka(Ralan_Dokter+Ralan_Paramedis+Ralan_Dokter_Paramedis+Operasi),
                                    Valid.SetAngka(Laborat),Valid.SetAngka(Radiologi),Valid.SetAngka(Tambahan),Valid.SetAngka(Potongan),
                                    Valid.SetAngka(Laborat+Radiologi+Obat+Ralan_Dokter+Ralan_Paramedis+Ralan_Dokter_Paramedis+Tambahan+Potongan+Registrasi),
                                    rspasien.getString("nm_dokter")                        
                                });
                                i++;
                            } catch (Exception e) {
                                System.out.println("Notifikasi : "+e);
                            } finally{
                                if(rsbilling!=null){
                                    rsbilling.close();
                                }
                                if(psbilling!=null){
                                    psbilling.close();
                                }
                            } 
                        }
                        tabModeRalan.addRow(new Object[] {
                            "   >> Total",":","","","","",
                            Valid.SetAngka(ttlRegistrasi),
                            Valid.SetAngka(ttlObat),
                            Valid.SetAngka(ttlRalan_Dokter+ttlRalan_Paramedis+ttlOperasi),
                            Valid.SetAngka(ttlLaborat),
                            Valid.SetAngka(ttlRadiologi),
                            Valid.SetAngka(ttlTambahan),
                            Valid.SetAngka(ttlPotongan),
                            Valid.SetAngka(ttlLaborat+ttlRadiologi+ttlObat+ttlRalan_Dokter+ttlRalan_Paramedis+
                                    ttlTambahan+ttlPotongan+ttlRegistrasi+ttlOperasi),"",""
                        });
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rspasien!=null){
                            rspasien.close();
                        }
                        if(pspasienralan!=null){
                            pspasienralan.close();
                        }
                    } 
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(psjamshift!=null){
                    psjamshift.close();
                }
            }  
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void tampilranap() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
        Valid.tabelKosong(tabModeRanap);
        try{
            psjamshift=koneksi.prepareStatement(sqlpsjamshift);
            try{
                rs=psjamshift.executeQuery();
                while(rs.next()){
                    tabModeRanap.addRow(new Object[]{
                        "Shift : "+rs.getString("shift"),rs.getString("jam_masuk")+" - "+rs.getString("jam_pulang"),"","","","","","","","","","","","","","","","",""
                    });
                    pspasienranap=koneksi.prepareStatement(sqlpspasienranap);
                    try{
                        pspasienranap.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+"")+" "+rs.getString("jam_masuk"));
                        if(rs.getString("shift").equals("Malam")){
                            tanggal2=Sequel.cariIsi("select DATE_ADD('"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" "+rs.getString("jam_pulang")+"',INTERVAL 1 DAY)");
                            pspasienranap.setString(2,tanggal2);
                        }else{
                            pspasienranap.setString(2,Valid.SetTgl(Tgl1.getSelectedItem()+"")+" "+rs.getString("jam_pulang"));
                        }
                        
                        rspasien=pspasienranap.executeQuery();
                        all=0;ttlLaborat=0;ttlRadiologi=0;ttlOperasi=0;ttlObat=0;
                        ttlRanap_Dokter=0;ttlRanap_Paramedis=0;ttlRalan_Dokter=0;
                        ttlRalan_Paramedis=0;ttlTambahan=0;ttlPotongan=0;ttlKamar=0;ttlService=0;
                        ttlRegistrasi=0;ttlHarian=0;ttlRetur_Obat=0;ttlResep_Pulang=0;
                        i=1;
                        while(rspasien.next()){
                            Ralan_Dokter=0;Laborat=0;Radiologi=0;Obat=0;Ralan_Paramedis=0;Tambahan=0;
                            Potongan=0;Registrasi=0;Retur_Obat=0;Resep_Pulang=0;Harian=0;Kamar=0;Service=0;
                            Ralan_Dokter_Paramedis=0;Operasi=0;Ranap_Dokter=0;Ranap_Dokter_Paramedis=0;
                            Ranap_Paramedis=0;
                            psbilling=koneksi.prepareStatement(sqlpsbilling);
                            try{
                                psbilling.setString(1,rspasien.getString("no_rawat"));
                                psbilling.setString(2,rspasien.getString("no_nota"));
                                rsbilling=psbilling.executeQuery(); 
                                while(rsbilling.next()){
                                    switch (rsbilling.getString("status")) {
                                        case "Laborat":                    
                                            ttlLaborat=ttlLaborat+rsbilling.getDouble("totalbiaya");
                                            Laborat=Laborat+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Radiologi":                    
                                            ttlRadiologi=ttlRadiologi+rsbilling.getDouble("totalbiaya");
                                            Radiologi=Radiologi+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Operasi":                    
                                            ttlOperasi=ttlOperasi+rsbilling.getDouble("totalbiaya");
                                            Operasi=Operasi+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Obat":                    
                                            ttlObat=ttlObat+rsbilling.getDouble("totalbiaya");
                                            Obat=Obat+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Ranap Dokter":
                                            ttlRanap_Dokter=ttlRanap_Dokter+rsbilling.getDouble("totalbiaya");
                                            Ranap_Dokter=Ranap_Dokter+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Ranap Dokter Paramedis":                    
                                            ttlRanap_Dokter=ttlRanap_Dokter+rsbilling.getDouble("totalbiaya");
                                            Ranap_Dokter_Paramedis=Ranap_Dokter_Paramedis+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Ranap Paramedis":                    
                                            ttlRanap_Paramedis=ttlRanap_Paramedis+rsbilling.getDouble("totalbiaya");
                                            Ranap_Paramedis=Ranap_Paramedis+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Ralan Dokter":                    
                                            ttlRalan_Dokter=ttlRalan_Dokter+rsbilling.getDouble("totalbiaya");
                                            Ralan_Dokter=Ralan_Dokter+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Ralan Dokter Paramedis":                    
                                            ttlRalan_Dokter=ttlRalan_Dokter+rsbilling.getDouble("totalbiaya");
                                            Ralan_Dokter_Paramedis=Ralan_Dokter_Paramedis+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Ralan Paramedis":
                                            ttlRalan_Paramedis=ttlRalan_Paramedis+rsbilling.getDouble("totalbiaya");
                                            Ralan_Paramedis=Ralan_Paramedis+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Tambahan":                    
                                            ttlTambahan=ttlTambahan+rsbilling.getDouble("totalbiaya");
                                            Tambahan=Tambahan+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Potongan":                    
                                            ttlPotongan=ttlPotongan+rsbilling.getDouble("totalbiaya");
                                            Potongan=Potongan+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Kamar":                    
                                            ttlKamar=ttlKamar+rsbilling.getDouble("totalbiaya");
                                            Kamar=Kamar+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Registrasi":                    
                                            ttlRegistrasi=ttlRegistrasi+rsbilling.getDouble("totalbiaya");
                                            Registrasi=Registrasi+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Harian":                    
                                            ttlHarian=ttlHarian+rsbilling.getDouble("totalbiaya");
                                            Harian=Harian+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Retur Obat":                    
                                            ttlRetur_Obat=ttlRetur_Obat+rsbilling.getDouble("totalbiaya");
                                            Retur_Obat=Retur_Obat+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Resep Pulang":                    
                                            ttlResep_Pulang=ttlResep_Pulang+rsbilling.getDouble("totalbiaya");
                                            Resep_Pulang=Resep_Pulang+rsbilling.getDouble("totalbiaya");
                                            break;
                                        case "Service":                    
                                            ttlService=ttlService+rsbilling.getDouble("totalbiaya");
                                            Service=Service+rsbilling.getDouble("totalbiaya");
                                            break;
                                    }                        
                                }
                                tabModeRanap.addRow(new Object[]{
                                    i+". "+rspasien.getString("tanggal")+" "+rspasien.getString("jam"),rspasien.getString("no_nota"),rspasien.getString("no_rkm_medis"),
                                    rspasien.getString("nm_pasien"),rspasien.getString("png_jawab"),
                                    Sequel.cariIsi("select perujuk from rujuk_masuk where no_rawat=?",rspasien.getString("no_rawat")),Valid.SetAngka(Registrasi),
                                    Valid.SetAngka(Ranap_Dokter+Ranap_Dokter_Paramedis+Ranap_Paramedis+Ralan_Dokter+Ralan_Dokter_Paramedis+Ralan_Paramedis),
                                    Valid.SetAngka(Obat),Valid.SetAngka(Retur_Obat),Valid.SetAngka(Resep_Pulang),Valid.SetAngka(Laborat),Valid.SetAngka(Radiologi),Valid.SetAngka(Potongan),
                                    Valid.SetAngka(Tambahan),Valid.SetAngka(Kamar+Service),Valid.SetAngka(Operasi),Valid.SetAngka(Harian),Valid.SetAngka(Laborat+Radiologi+Operasi+Obat+Ranap_Dokter+
                                            Ranap_Dokter_Paramedis+Ranap_Paramedis+Ralan_Dokter+Ralan_Dokter_Paramedis+Ralan_Paramedis+Tambahan+Potongan+Kamar+Registrasi+Harian+Retur_Obat+Resep_Pulang+Service)
                                });
                                all=all+Laborat+Radiologi+Operasi+Obat+Ranap_Dokter+Ranap_Dokter_Paramedis+Ranap_Paramedis+Ralan_Dokter+Ralan_Dokter_Paramedis+Ralan_Paramedis+Tambahan+Potongan+Kamar+Registrasi+Harian+Retur_Obat+Resep_Pulang+Service;
                                i++;
                            } catch (Exception e) {
                                System.out.println("Notifikasi : "+e);
                            } finally{
                                if(rsbilling!=null){
                                    rsbilling.close();
                                }
                                if(psbilling!=null){
                                    psbilling.close();
                                }
                            } 
                        }
                        tabModeRanap.addRow(new Object[]{
                            "   >> Total ",":","","","","",Valid.SetAngka(ttlRegistrasi),Valid.SetAngka(ttlRanap_Dokter+ttlRanap_Paramedis+ttlRalan_Dokter+ttlRalan_Paramedis),
                            Valid.SetAngka(ttlObat),Valid.SetAngka(ttlRetur_Obat),Valid.SetAngka(ttlResep_Pulang),Valid.SetAngka(ttlLaborat),Valid.SetAngka(ttlRadiologi),Valid.SetAngka(ttlPotongan),
                            Valid.SetAngka(ttlTambahan),Valid.SetAngka(ttlKamar+ttlService),Valid.SetAngka(ttlOperasi),Valid.SetAngka(ttlHarian),Valid.SetAngka(all),""
                        });
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rspasien!=null){
                            rspasien.close();
                        }
                        if(pspasienranap!=null){
                            pspasienranap.close();
                        }
                    }  
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(psjamshift!=null){
                    psjamshift.close();
                }
            } 
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        this.setCursor(Cursor.getDefaultCursor());        
    }
    
    private void tampilpemasukan() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
        Valid.tabelKosong(tabModePemasukan);
        try{
            psjamshift=koneksi.prepareStatement(sqlpsjamshift);
            try{
                rs=psjamshift.executeQuery();
                while(rs.next()){
                    tabModePemasukan.addRow(new Object[]{
                        "Shift : "+rs.getString("shift"),rs.getString("jam_masuk")+" - "+rs.getString("jam_pulang"),"",""
                    });
                    pspemasukan=koneksi.prepareStatement(sqlpspemasukan);
                    try{
                        pspemasukan.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+"")+" "+rs.getString("jam_masuk"));
                        if(rs.getString("shift").equals("Malam")){
                            tanggal2=Sequel.cariIsi("select DATE_ADD('"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" "+rs.getString("jam_pulang")+"',INTERVAL 1 DAY)");
                            pspemasukan.setString(2,tanggal2);
                        }else{
                            pspemasukan.setString(2,Valid.SetTgl(Tgl1.getSelectedItem()+"")+" "+rs.getString("jam_pulang"));
                        }
                        
                        rsbilling=pspemasukan.executeQuery();
                        i=1;
                        all=0;
                        while(rsbilling.next()){
                            all=all+rsbilling.getDouble("besar");
                            tabModePemasukan.addRow(new Object[]{
                                i+". "+rsbilling.getString("tanggal"),rsbilling.getString("nama_kategori"),
                                Valid.SetAngka(rsbilling.getDouble("besar")),rsbilling.getString("keterangan")
                            });
                            i++;
                        }
                        tabModePemasukan.addRow(new Object[]{
                            "   >> Total ",":",Valid.SetAngka(all),""
                        }); 
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rsbilling!=null){
                            rsbilling.close();
                        }
                        if(pspemasukan!=null){
                            pspemasukan.close();
                        }
                    }  
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(psjamshift!=null){
                    psjamshift.close();
                }
            } 
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        this.setCursor(Cursor.getDefaultCursor());        
    }   
    
    private void tampilpengeluaran() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
        Valid.tabelKosong(tabModePengeluaran);
        try{
            psjamshift=koneksi.prepareStatement(sqlpsjamshift);
            try{
                rs=psjamshift.executeQuery();
                while(rs.next()){
                    tabModePengeluaran.addRow(new Object[]{
                        "Shift : "+rs.getString("shift"),rs.getString("jam_masuk")+" - "+rs.getString("jam_pulang"),"",""
                    });
                    pspengeluaran=koneksi.prepareStatement(sqlpspengeluaran);
                    try{
                        pspengeluaran.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+"")+" "+rs.getString("jam_masuk"));
                        if(rs.getString("shift").equals("Malam")){
                            tanggal2=Sequel.cariIsi("select DATE_ADD('"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" "+rs.getString("jam_pulang")+"',INTERVAL 1 DAY)");
                            pspengeluaran.setString(2,tanggal2);
                        }else{
                            pspengeluaran.setString(2,Valid.SetTgl(Tgl1.getSelectedItem()+"")+" "+rs.getString("jam_pulang"));
                        }
                        
                        rsbilling=pspengeluaran.executeQuery();
                        i=1;
                        all=0;
                        while(rsbilling.next()){
                            all=all+rsbilling.getDouble("biaya");
                            tabModePengeluaran.addRow(new Object[]{
                                i+". "+rsbilling.getString("tanggal"),rsbilling.getString("nama_kategori"),
                                Valid.SetAngka(rsbilling.getDouble("biaya")),rsbilling.getString("keterangan")
                            });
                            i++;
                        }
                        tabModePengeluaran.addRow(new Object[]{
                            "   >> Total ",":",Valid.SetAngka(all),""
                        });
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rsbilling!=null){
                            rsbilling.close();
                        }
                        if(pspengeluaran!=null){
                            pspengeluaran.close();
                        }
                    } 
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(psjamshift!=null){
                    psjamshift.close();
                }
            } 
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        this.setCursor(Cursor.getDefaultCursor());        
    }

}
