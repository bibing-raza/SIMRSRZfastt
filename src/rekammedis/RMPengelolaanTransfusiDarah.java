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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPegawai;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author dosen
 */
public class RMPengelolaanTransfusiDarah extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, psrestor;
    private ResultSet rs, rs1, rsrestor;
    private int x = 0, pilihan = 0;
    private String nip1 = "", nip2 = "", nip3 = "", user = "", riwayatData = "";
    private DlgCariPegawai petugas = new DlgCariPegawai(null, false);

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public RMPengelolaanTransfusiDarah(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        Object[] row = {"No. Rawat", "No. RM", "Nama Pasien", "Ruang Rawat", "Tanggal", "Jam", "Jns. Darah Transfusi",
            "TD (15 Mnt. Sebelum)", "N (15 Mnt. Sebelum)", "RR (15 Mnt. Sebelum)", "S (15 Mnt. Sebelum)", "Petugas (15 Mnt. Sebelum)",
            "TD (15 Mnt. Masuk)", "N (15 Mnt. Masuk)", "RR (15 Mnt. Masuk)", "S (15 Mnt. Masuk)", "Petugas (15 Mnt. Masuk)",
            "TD (1 Jam)", "N (1 Jam)", "RR (1 Jam)", "S (1 Jam)", "Petugas (1 Jam)", "tanggal", "jam",
            "15_sebelum_nip_petugas", "15_masuk_nip_petugas", "1_masuk_nip_petugas", "waktu_simpan"
        };
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbTransfusi.setModel(tabMode);
        tbTransfusi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTransfusi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 28; i++) {
            TableColumn column = tbTransfusi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(55);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(120);
            } else if (i == 8) {
                column.setPreferredWidth(120);
            } else if (i == 9) {
                column.setPreferredWidth(120);
            } else if (i == 10) {
                column.setPreferredWidth(120);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setPreferredWidth(120);
            } else if (i == 13) {
                column.setPreferredWidth(120);
            } else if (i == 14) {
                column.setPreferredWidth(120);
            } else if (i == 15) {
                column.setPreferredWidth(120);
            } else if (i == 16) {
                column.setPreferredWidth(220);
            } else if (i == 17) {
                column.setPreferredWidth(75);
            } else if (i == 18) {
                column.setPreferredWidth(75);
            } else if (i == 19) {
                column.setPreferredWidth(75);
            } else if (i == 20) {
                column.setPreferredWidth(75);
            } else if (i == 21) {
                column.setPreferredWidth(220);
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
            } 
        }
        tbTransfusi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Dilakukan Oleh", "No. Rawat", "No. RM", "Nama Pasien", "Tanggal", "Jam", "Tgl. Eksekusi", 
            "Status Data", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwayat.setModel(tabMode1);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 9; i++) {
            TableColumn column = tbRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(220);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(55);
            } else if (i == 6) {
                column.setPreferredWidth(130);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());

        Tjenis.setDocument(new batasInput((int) 200).getKata(Tjenis));
        T15TDbelum.setDocument(new batasInput((int) 7).getKata(T15TDbelum));
        T15Nadibelum.setDocument(new batasInput((int) 7).getKata(T15Nadibelum));
        T15RRbelum.setDocument(new batasInput((int) 7).getKata(T15RRbelum));
        T15Suhubelum.setDocument(new batasInput((int) 7).getKata(T15Suhubelum));
        T15TDmasuk.setDocument(new batasInput((int) 7).getKata(T15TDmasuk));
        T15Nadimasuk.setDocument(new batasInput((int) 7).getKata(T15Nadimasuk));
        T15RRmasuk.setDocument(new batasInput((int) 7).getKata(T15RRmasuk));
        T15Suhumasuk.setDocument(new batasInput((int) 7).getKata(T15Suhumasuk));
        T1JamTD.setDocument(new batasInput((int) 7).getKata(T1JamTD));
        T1JamNadi.setDocument(new batasInput((int) 7).getKata(T1JamNadi));
        T1JamRR.setDocument(new batasInput((int) 7).getKata(T1JamRR));
        T1JamSuhu.setDocument(new batasInput((int) 7).getKata(T1JamSuhu));
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        
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
                if (pilihan == 1) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nip1 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugas1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPetugas1.requestFocus();
                    }
                } else if (pilihan == 2) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nip2 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugas2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPetugas2.requestFocus();
                    }
                } else if (pilihan == 3) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nip3 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugas3.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPetugas3.requestFocus();
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
        MnRiwayatData = new javax.swing.JMenuItem();
        WindowRiwayat = new javax.swing.JDialog();
        internalFrame13 = new widget.InternalFrame();
        internalFrame18 = new widget.InternalFrame();
        internalFrame17 = new widget.InternalFrame();
        jLabel37 = new widget.Label();
        DTPCari3 = new widget.Tanggal();
        jLabel38 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel39 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        jLabel40 = new widget.Label();
        LCount1 = new widget.Label();
        internalFrame19 = new widget.InternalFrame();
        BtnAll1 = new widget.Button();
        BtnRestor = new widget.Button();
        BtnCloseIn10 = new widget.Button();
        Scroll6 = new widget.ScrollPane();
        tbRiwayat = new widget.Table();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbTransfusi = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass9 = new widget.panelisi();
        jLabel21 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        panelGlass7 = new widget.panelisi();
        jLabel5 = new widget.Label();
        jLabel15 = new widget.Label();
        TruangRwt = new widget.TextBox();
        TNoRw = new widget.TextBox();
        TNoRm = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel8 = new widget.Label();
        Ttgl = new widget.Tanggal();
        jLabel9 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel10 = new widget.Label();
        Tjenis = new widget.TextBox();
        jLabel11 = new widget.Label();
        T15TDbelum = new widget.TextBox();
        jLabel12 = new widget.Label();
        T15Nadibelum = new widget.TextBox();
        jLabel29 = new widget.Label();
        T15RRbelum = new widget.TextBox();
        jLabel30 = new widget.Label();
        T15Suhubelum = new widget.TextBox();
        jLabel28 = new widget.Label();
        jLabel13 = new widget.Label();
        TnmPetugas1 = new widget.TextBox();
        jLabel14 = new widget.Label();
        T15TDmasuk = new widget.TextBox();
        jLabel16 = new widget.Label();
        T15Nadimasuk = new widget.TextBox();
        jLabel31 = new widget.Label();
        T15RRmasuk = new widget.TextBox();
        jLabel32 = new widget.Label();
        T15Suhumasuk = new widget.TextBox();
        jLabel33 = new widget.Label();
        jLabel17 = new widget.Label();
        TnmPetugas2 = new widget.TextBox();
        jLabel18 = new widget.Label();
        T1JamTD = new widget.TextBox();
        jLabel19 = new widget.Label();
        T1JamNadi = new widget.TextBox();
        jLabel34 = new widget.Label();
        T1JamRR = new widget.TextBox();
        jLabel35 = new widget.Label();
        T1JamSuhu = new widget.TextBox();
        jLabel36 = new widget.Label();
        jLabel20 = new widget.Label();
        TnmPetugas3 = new widget.TextBox();
        BtnPetugas1 = new widget.Button();
        BtnPetugas2 = new widget.Button();
        BtnPetugas3 = new widget.Button();
        chkSaya1 = new widget.CekBox();
        chkSaya2 = new widget.CekBox();
        chkSaya3 = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnRiwayatData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRiwayatData.setText("Riwayat Data Terhapus/Diganti");
        MnRiwayatData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRiwayatData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRiwayatData.setIconTextGap(5);
        MnRiwayatData.setName("MnRiwayatData"); // NOI18N
        MnRiwayatData.setPreferredSize(new java.awt.Dimension(195, 26));
        MnRiwayatData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatDataActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRiwayatData);

        WindowRiwayat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayat.setName("WindowRiwayat"); // NOI18N
        WindowRiwayat.setUndecorated(true);
        WindowRiwayat.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Pengelolaan Transfusi Darah Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Tanggal :");
        jLabel37.setName("jLabel37"); // NOI18N
        jLabel37.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame17.add(jLabel37);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-03-2024" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari3);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("s.d.");
        jLabel38.setName("jLabel38"); // NOI18N
        jLabel38.setPreferredSize(new java.awt.Dimension(23, 23));
        internalFrame17.add(jLabel38);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-03-2024" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari4);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Key Word :");
        jLabel39.setName("jLabel39"); // NOI18N
        jLabel39.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame17.add(jLabel39);

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

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Record :");
        jLabel40.setName("jLabel40"); // NOI18N
        jLabel40.setPreferredSize(new java.awt.Dimension(65, 23));
        internalFrame17.add(jLabel40);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        internalFrame17.add(LCount1);

        internalFrame18.add(internalFrame17, java.awt.BorderLayout.CENTER);

        internalFrame19.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame19.setName("internalFrame19"); // NOI18N
        internalFrame19.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame19.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 7, 9));

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('2');
        BtnAll1.setText("Semua Data");
        BtnAll1.setToolTipText("Alt+2");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll1ActionPerformed(evt);
            }
        });
        BtnAll1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll1KeyPressed(evt);
            }
        });
        internalFrame19.add(BtnAll1);

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

        BtnCloseIn10.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn10.setMnemonic('U');
        BtnCloseIn10.setText("Tutup");
        BtnCloseIn10.setToolTipText("Alt+U");
        BtnCloseIn10.setName("BtnCloseIn10"); // NOI18N
        BtnCloseIn10.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnCloseIn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn10ActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnCloseIn10);

        internalFrame18.add(internalFrame19, java.awt.BorderLayout.PAGE_END);

        internalFrame13.add(internalFrame18, java.awt.BorderLayout.PAGE_END);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbRiwayat.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/direstore");
        tbRiwayat.setName("tbRiwayat"); // NOI18N
        Scroll6.setViewportView(tbRiwayat);

        internalFrame13.add(Scroll6, java.awt.BorderLayout.CENTER);

        WindowRiwayat.getContentPane().add(internalFrame13, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pengelolaan Transfusi Darah Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbTransfusi.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTransfusi.setComponentPopupMenu(jPopupMenu1);
        tbTransfusi.setName("tbTransfusi"); // NOI18N
        tbTransfusi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTransfusiMouseClicked(evt);
            }
        });
        tbTransfusi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTransfusiKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbTransfusi);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Tanggal :");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel21);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-03-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d.");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel22);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-03-2024" }));
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
        BtnCari.setMnemonic('D');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+D");
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BtnCariKeyReleased(evt);
            }
        });
        panelGlass9.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('T');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+T");
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

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        panelGlass7.setComponentPopupMenu(jPopupMenu1);
        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass7.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(0, 10, 140, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Ruang Rawat :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass7.add(jLabel15);
        jLabel15.setBounds(0, 38, 140, 23);

        TruangRwt.setEditable(false);
        TruangRwt.setForeground(new java.awt.Color(0, 0, 0));
        TruangRwt.setName("TruangRwt"); // NOI18N
        panelGlass7.add(TruangRwt);
        TruangRwt.setBounds(145, 38, 570, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        panelGlass7.add(TNoRw);
        TNoRw.setBounds(145, 10, 122, 23);

        TNoRm.setEditable(false);
        TNoRm.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm.setName("TNoRm"); // NOI18N
        panelGlass7.add(TNoRm);
        TNoRm.setBounds(272, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        panelGlass7.add(TPasien);
        TPasien.setBounds(346, 10, 370, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tanggal :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(0, 66, 140, 23);

        Ttgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-03-2024" }));
        Ttgl.setDisplayFormat("dd-MM-yyyy");
        Ttgl.setName("Ttgl"); // NOI18N
        Ttgl.setOpaque(false);
        Ttgl.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(Ttgl);
        Ttgl.setBounds(145, 66, 90, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jam :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass7.add(jLabel9);
        jLabel9.setBounds(240, 66, 40, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbJam);
        cmbJam.setBounds(288, 66, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbMnt);
        cmbMnt.setBounds(339, 66, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbDtk);
        cmbDtk.setBounds(391, 66, 45, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Jns. Darah Transfusi :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass7.add(jLabel10);
        jLabel10.setBounds(0, 94, 140, 23);

        Tjenis.setForeground(new java.awt.Color(0, 0, 0));
        Tjenis.setName("Tjenis"); // NOI18N
        Tjenis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjenisKeyPressed(evt);
            }
        });
        panelGlass7.add(Tjenis);
        Tjenis.setBounds(145, 94, 570, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("15 Menit Sebelum Transfusi : TD :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass7.add(jLabel11);
        jLabel11.setBounds(0, 122, 200, 23);

        T15TDbelum.setForeground(new java.awt.Color(0, 0, 0));
        T15TDbelum.setName("T15TDbelum"); // NOI18N
        T15TDbelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                T15TDbelumKeyPressed(evt);
            }
        });
        panelGlass7.add(T15TDbelum);
        T15TDbelum.setBounds(208, 122, 70, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("mmHg     Nadi :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass7.add(jLabel12);
        jLabel12.setBounds(284, 122, 80, 23);

        T15Nadibelum.setForeground(new java.awt.Color(0, 0, 0));
        T15Nadibelum.setName("T15Nadibelum"); // NOI18N
        T15Nadibelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                T15NadibelumKeyPressed(evt);
            }
        });
        panelGlass7.add(T15Nadibelum);
        T15Nadibelum.setBounds(366, 122, 60, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("x/mnt      RR : ");
        jLabel29.setName("jLabel29"); // NOI18N
        panelGlass7.add(jLabel29);
        jLabel29.setBounds(430, 122, 70, 23);

        T15RRbelum.setForeground(new java.awt.Color(0, 0, 0));
        T15RRbelum.setName("T15RRbelum"); // NOI18N
        T15RRbelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                T15RRbelumKeyPressed(evt);
            }
        });
        panelGlass7.add(T15RRbelum);
        T15RRbelum.setBounds(504, 122, 60, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("x/mnt      Suhu :");
        jLabel30.setName("jLabel30"); // NOI18N
        panelGlass7.add(jLabel30);
        jLabel30.setBounds(570, 122, 80, 23);

        T15Suhubelum.setForeground(new java.awt.Color(0, 0, 0));
        T15Suhubelum.setName("T15Suhubelum"); // NOI18N
        T15Suhubelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                T15SuhubelumKeyPressed(evt);
            }
        });
        panelGlass7.add(T15Suhubelum);
        T15Suhubelum.setBounds(655, 122, 60, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("°C");
        jLabel28.setName("jLabel28"); // NOI18N
        panelGlass7.add(jLabel28);
        jLabel28.setBounds(720, 122, 30, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Nama Dokter Jaga/Perawat (15 Menit Sebelum) :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass7.add(jLabel13);
        jLabel13.setBounds(0, 150, 280, 23);

        TnmPetugas1.setEditable(false);
        TnmPetugas1.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas1.setName("TnmPetugas1"); // NOI18N
        panelGlass7.add(TnmPetugas1);
        TnmPetugas1.setBounds(285, 150, 430, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("15 Menit Darah Mulai Masuk : TD :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass7.add(jLabel14);
        jLabel14.setBounds(0, 178, 200, 23);

        T15TDmasuk.setForeground(new java.awt.Color(0, 0, 0));
        T15TDmasuk.setName("T15TDmasuk"); // NOI18N
        T15TDmasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                T15TDmasukKeyPressed(evt);
            }
        });
        panelGlass7.add(T15TDmasuk);
        T15TDmasuk.setBounds(208, 178, 70, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("mmHg     Nadi :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass7.add(jLabel16);
        jLabel16.setBounds(284, 178, 80, 23);

        T15Nadimasuk.setForeground(new java.awt.Color(0, 0, 0));
        T15Nadimasuk.setName("T15Nadimasuk"); // NOI18N
        T15Nadimasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                T15NadimasukKeyPressed(evt);
            }
        });
        panelGlass7.add(T15Nadimasuk);
        T15Nadimasuk.setBounds(366, 178, 60, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("x/mnt      RR : ");
        jLabel31.setName("jLabel31"); // NOI18N
        panelGlass7.add(jLabel31);
        jLabel31.setBounds(430, 178, 70, 23);

        T15RRmasuk.setForeground(new java.awt.Color(0, 0, 0));
        T15RRmasuk.setName("T15RRmasuk"); // NOI18N
        T15RRmasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                T15RRmasukKeyPressed(evt);
            }
        });
        panelGlass7.add(T15RRmasuk);
        T15RRmasuk.setBounds(504, 178, 60, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("x/mnt      Suhu :");
        jLabel32.setName("jLabel32"); // NOI18N
        panelGlass7.add(jLabel32);
        jLabel32.setBounds(570, 178, 80, 23);

        T15Suhumasuk.setForeground(new java.awt.Color(0, 0, 0));
        T15Suhumasuk.setName("T15Suhumasuk"); // NOI18N
        T15Suhumasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                T15SuhumasukKeyPressed(evt);
            }
        });
        panelGlass7.add(T15Suhumasuk);
        T15Suhumasuk.setBounds(655, 178, 60, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("°C");
        jLabel33.setName("jLabel33"); // NOI18N
        panelGlass7.add(jLabel33);
        jLabel33.setBounds(720, 178, 30, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Nama Dokter Jaga/Perawat (15 Menit Mulai Msk.) :");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass7.add(jLabel17);
        jLabel17.setBounds(0, 206, 280, 23);

        TnmPetugas2.setEditable(false);
        TnmPetugas2.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas2.setName("TnmPetugas2"); // NOI18N
        panelGlass7.add(TnmPetugas2);
        TnmPetugas2.setBounds(285, 206, 430, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("1 Jam Setelah Darah Masuk : TD :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelGlass7.add(jLabel18);
        jLabel18.setBounds(0, 234, 200, 23);

        T1JamTD.setForeground(new java.awt.Color(0, 0, 0));
        T1JamTD.setName("T1JamTD"); // NOI18N
        T1JamTD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                T1JamTDKeyPressed(evt);
            }
        });
        panelGlass7.add(T1JamTD);
        T1JamTD.setBounds(208, 234, 70, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("mmHg     Nadi :");
        jLabel19.setName("jLabel19"); // NOI18N
        panelGlass7.add(jLabel19);
        jLabel19.setBounds(284, 234, 80, 23);

        T1JamNadi.setForeground(new java.awt.Color(0, 0, 0));
        T1JamNadi.setName("T1JamNadi"); // NOI18N
        T1JamNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                T1JamNadiKeyPressed(evt);
            }
        });
        panelGlass7.add(T1JamNadi);
        T1JamNadi.setBounds(366, 234, 60, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("x/mnt      RR : ");
        jLabel34.setName("jLabel34"); // NOI18N
        panelGlass7.add(jLabel34);
        jLabel34.setBounds(430, 234, 70, 23);

        T1JamRR.setForeground(new java.awt.Color(0, 0, 0));
        T1JamRR.setName("T1JamRR"); // NOI18N
        T1JamRR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                T1JamRRKeyPressed(evt);
            }
        });
        panelGlass7.add(T1JamRR);
        T1JamRR.setBounds(504, 234, 60, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("x/mnt      Suhu :");
        jLabel35.setName("jLabel35"); // NOI18N
        panelGlass7.add(jLabel35);
        jLabel35.setBounds(570, 234, 80, 23);

        T1JamSuhu.setForeground(new java.awt.Color(0, 0, 0));
        T1JamSuhu.setName("T1JamSuhu"); // NOI18N
        T1JamSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                T1JamSuhuKeyPressed(evt);
            }
        });
        panelGlass7.add(T1JamSuhu);
        T1JamSuhu.setBounds(655, 234, 60, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("°C");
        jLabel36.setName("jLabel36"); // NOI18N
        panelGlass7.add(jLabel36);
        jLabel36.setBounds(720, 234, 30, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Nama Dokter Jaga/Perawat (1 Jam Setelah) :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelGlass7.add(jLabel20);
        jLabel20.setBounds(0, 262, 280, 23);

        TnmPetugas3.setEditable(false);
        TnmPetugas3.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas3.setName("TnmPetugas3"); // NOI18N
        panelGlass7.add(TnmPetugas3);
        TnmPetugas3.setBounds(285, 262, 430, 23);

        BtnPetugas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas1.setMnemonic('2');
        BtnPetugas1.setToolTipText("Alt+2");
        BtnPetugas1.setName("BtnPetugas1"); // NOI18N
        BtnPetugas1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas1ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPetugas1);
        BtnPetugas1.setBounds(715, 150, 28, 23);

        BtnPetugas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas2.setMnemonic('2');
        BtnPetugas2.setToolTipText("Alt+2");
        BtnPetugas2.setName("BtnPetugas2"); // NOI18N
        BtnPetugas2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas2ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPetugas2);
        BtnPetugas2.setBounds(715, 206, 28, 23);

        BtnPetugas3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas3.setMnemonic('2');
        BtnPetugas3.setToolTipText("Alt+2");
        BtnPetugas3.setName("BtnPetugas3"); // NOI18N
        BtnPetugas3.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas3ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPetugas3);
        BtnPetugas3.setBounds(715, 262, 28, 23);

        chkSaya1.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya1.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya1.setText("Saya Sendiri");
        chkSaya1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya1.setName("chkSaya1"); // NOI18N
        chkSaya1.setOpaque(false);
        chkSaya1.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya1ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkSaya1);
        chkSaya1.setBounds(752, 150, 100, 23);

        chkSaya2.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya2.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya2.setText("Saya Sendiri");
        chkSaya2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya2.setName("chkSaya2"); // NOI18N
        chkSaya2.setOpaque(false);
        chkSaya2.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya2ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkSaya2);
        chkSaya2.setBounds(752, 206, 100, 23);

        chkSaya3.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya3.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya3.setText("Saya Sendiri");
        chkSaya3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya3.setName("chkSaya3"); // NOI18N
        chkSaya3.setOpaque(false);
        chkSaya3.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya3ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkSaya3);
        chkSaya3.setBounds(752, 262, 100, 23);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (Sequel.menyimpantf("pengelolaan_transfusi_darah", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 21, new String[]{
                TNoRw.getText(), TruangRwt.getText(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                Tjenis.getText(), T15TDbelum.getText(), T15Nadibelum.getText(), T15RRbelum.getText(), T15Suhubelum.getText(), nip1,
                T15TDmasuk.getText(), T15Nadimasuk.getText(), T15RRmasuk.getText(), T15Suhumasuk.getText(), nip2,
                T1JamTD.getText(), T1JamNadi.getText(), T1JamRR.getText(), T1JamSuhu.getText(), nip3, Sequel.cariIsi("select now()")
            }) == true) {
                TCari.setText(TNoRw.getText());
                Valid.SetTgl(DTPCari1, Valid.SetTgl(Ttgl.getSelectedItem() + ""));
                emptTeks();
                BtnCariActionPerformed(null);
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
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (tbTransfusi.getSelectedRow() > -1) {
                riwayatData = "ganti";
                simpanHistori();
                if (Sequel.mengedittf("pengelolaan_transfusi_darah", "waktu_simpan=?", "tanggal=?, jam=?, jenis_darah_transfusi=?, 15_sebelum_td=?, 15_sebelum_nadi=?, "
                        + "15_sebelum_rr=?, 15_sebelum_suhu=?, 15_sebelum_nip_petugas=?, 15_masuk_td=?, 15_masuk_nadi=?, 15_masuk_rr=?, 15_masuk_suhu=?, 15_masuk_nip_petugas=?, "
                        + "1_masuk_td=?, 1_masuk_nadi=?, 1_masuk_rr=?, 1_masuk_suhu=?, 1_masuk_nip_petugas=?", 18, new String[]{
                            Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            Tjenis.getText(), T15TDbelum.getText(), T15Nadibelum.getText(), T15RRbelum.getText(), T15Suhubelum.getText(), nip1,
                            T15TDmasuk.getText(), T15Nadimasuk.getText(), T15RRmasuk.getText(), T15Suhumasuk.getText(), nip2,
                            T1JamTD.getText(), T1JamNadi.getText(), T1JamRR.getText(), T1JamSuhu.getText(), nip3,
                            tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(), 27).toString()
                        }) == true) {

                    TCari.setText(TNoRw.getText());
                    Valid.SetTgl(DTPCari1, Valid.SetTgl(Ttgl.getSelectedItem() + ""));
                    emptTeks();
                    BtnCariActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbTransfusi.requestFocus();
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

    private void BtnCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyReleased
        // TODO add your handling code here:
}//GEN-LAST:event_BtnCariKeyReleased

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
       emptTeks();
       tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbTransfusiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTransfusiMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbTransfusiMouseClicked

    private void tbTransfusiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTransfusiKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbTransfusiKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        TCari.requestFocus();
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        emptTeks();
    }//GEN-LAST:event_formWindowOpened

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void BtnPetugas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas1ActionPerformed
        pilihan = 1;
        akses.setform("RMPengelolaanTransfusiDarah");
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas1ActionPerformed

    private void BtnPetugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas2ActionPerformed
        pilihan = 2;
        akses.setform("RMPengelolaanTransfusiDarah");
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas2ActionPerformed

    private void BtnPetugas3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas3ActionPerformed
        pilihan = 3;
        akses.setform("RMPengelolaanTransfusiDarah");
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas3ActionPerformed

    private void TjenisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjenisKeyPressed
        Valid.pindah(evt, Tjenis, T15TDbelum);
    }//GEN-LAST:event_TjenisKeyPressed

    private void T15TDbelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T15TDbelumKeyPressed
        Valid.pindah(evt, Tjenis, T15Nadibelum);
    }//GEN-LAST:event_T15TDbelumKeyPressed

    private void T15NadibelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T15NadibelumKeyPressed
        Valid.pindah(evt, T15TDbelum, T15RRbelum);
    }//GEN-LAST:event_T15NadibelumKeyPressed

    private void T15RRbelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T15RRbelumKeyPressed
        Valid.pindah(evt, T15Nadibelum, T15Suhubelum);
    }//GEN-LAST:event_T15RRbelumKeyPressed

    private void T15SuhubelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T15SuhubelumKeyPressed
        Valid.pindah(evt, T15RRbelum, BtnPetugas1);
    }//GEN-LAST:event_T15SuhubelumKeyPressed

    private void T15TDmasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T15TDmasukKeyPressed
        Valid.pindah(evt, BtnPetugas1, T15Nadimasuk);
    }//GEN-LAST:event_T15TDmasukKeyPressed

    private void T15NadimasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T15NadimasukKeyPressed
        Valid.pindah(evt, T15TDmasuk, T15RRmasuk);
    }//GEN-LAST:event_T15NadimasukKeyPressed

    private void T15RRmasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T15RRmasukKeyPressed
        Valid.pindah(evt, T15Nadimasuk, T15Suhumasuk);
    }//GEN-LAST:event_T15RRmasukKeyPressed

    private void T15SuhumasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T15SuhumasukKeyPressed
        Valid.pindah(evt, T15RRmasuk, BtnPetugas2);
    }//GEN-LAST:event_T15SuhumasukKeyPressed

    private void T1JamTDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T1JamTDKeyPressed
        Valid.pindah(evt, BtnPetugas2, T1JamNadi);
    }//GEN-LAST:event_T1JamTDKeyPressed

    private void T1JamNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T1JamNadiKeyPressed
        Valid.pindah(evt, T1JamTD, T1JamRR);
    }//GEN-LAST:event_T1JamNadiKeyPressed

    private void T1JamRRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T1JamRRKeyPressed
        Valid.pindah(evt, T1JamNadi, T1JamSuhu);
    }//GEN-LAST:event_T1JamRRKeyPressed

    private void T1JamSuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T1JamSuhuKeyPressed
        Valid.pindah(evt, T1JamRR, BtnPetugas3);
    }//GEN-LAST:event_T1JamSuhuKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbTransfusi.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                riwayatData = "hapus";
                simpanHistori();
                if (Sequel.queryu2tf("delete from pengelolaan_transfusi_darah where waktu_simpan=?", 1, new String[]{
                    tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(), 27).toString()
                }) == true) {
                    TCari.setText(TNoRw.getText());
                    Valid.SetTgl(DTPCari1, Valid.SetTgl(Ttgl.getSelectedItem() + ""));
                    emptTeks();
                    BtnCariActionPerformed(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salh satu datanya terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbTransfusi.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRm.getText());
            param.put("nmpasien", TPasien.getText());
//            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRm.getText() + "'"));
//            param.put("tglasesmen", Valid.SetTglINDONESIA(Valid.SetTgl(tglAsesmen.getSelectedItem() + "")));
//            param.put("jamasesmen", cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " WITA");
//            param.put("totalskor", TtotSkor.getText());
//            param.put("tindakancegah", cmbTindakanCegah.getSelectedItem().toString());
//            param.put("nmpetugas", TnmPetugas.getText());
//            param.put("kategori", Tkategori.getText());
//
//            if (cmbTindakanCegah.getSelectedIndex() == 1) {
//                param.put("judultindakan", "Pencegahan Umum (A)");
//                param.put("isitindakan", cegahA.getText() + "\n\n"
//                    + "Tanggal : " + Valid.SetTglINDONESIA(Valid.SetTgl(tglAsesmen.getSelectedItem() + "")) + "\n"
//                    + "Tindakan Pencegahan Resiko Jatuh : " + cmbTindakanCegah.getSelectedItem().toString() + "\n");
//            } else if (cmbTindakanCegah.getSelectedIndex() == 2) {
//                param.put("judultindakan", "Pencegahan Resiko Sedang (B)");
//                param.put("isitindakan", cegahB.getText() + "\n\n"
//                    + "Tanggal : " + Valid.SetTglINDONESIA(Valid.SetTgl(tglAsesmen.getSelectedItem() + "")) + "\n"
//                    + "Tindakan Pencegahan Resiko Jatuh : " + cmbTindakanCegah.getSelectedItem().toString() + "\n");
//            } else if (cmbTindakanCegah.getSelectedIndex() == 3) {
//                param.put("judultindakan", "Pencegahan Resiko Tinggi (C)");
//                param.put("isitindakan", cegahC.getText() + "\n\n"
//                    + "Tanggal : " + Valid.SetTglINDONESIA(Valid.SetTgl(tglAsesmen.getSelectedItem() + "")) + "\n"
//                    + "Tindakan Pencegahan Resiko Jatuh : " + cmbTindakanCegah.getSelectedItem().toString() + "\n");
//            } else {
//                param.put("judultindakan", "-");
//                param.put("isitindakan", "-\n\n"
//                    + "Tanggal : " + Valid.SetTglINDONESIA(Valid.SetTgl(tglAsesmen.getSelectedItem() + "")) + "\n"
//                    + "Tindakan Pencegahan Resiko Jatuh : " + cmbTindakanCegah.getSelectedItem().toString() + "\n");
//            }
//
//            Valid.MyReport("rptAsesmenUlangRJ.jasper", "report", "::[ Asesmen Ulang Resiko Jatuh ]::",
//                "SELECT b.faktor_resiko, b.skala, b.skor from detail_asesmen_ulang_resiko_jatuh a "
//                + "inner join master_faktor_resiko_igd b on b.kode_resiko=a.kode_resiko WHERE "
//                + "a.kode_ulang_resiko='" + TkdFaktor.getText() + "' order by b.kode_resiko", param);
//
//            TCari.setText(TNoRw.getText());
//            Valid.SetTgl(DTPCari1, Valid.SetTgl(tglAsesmen.getSelectedItem() + ""));
//            emptTeks();
//            BtnCariActionPerformed(null);
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih salah satu datanya terlebih dulu..!!!!");
            tbTransfusi.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void chkSaya1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya1ActionPerformed
        if (chkSaya1.isSelected() == true) {
            if (akses.getadmin() == true) {
                nip1 = "-";
                TnmPetugas1.setText("-");
            } else {
                nip1 = akses.getkode();
                TnmPetugas1.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip1 + "'"));
            }
        } else {
            nip1 = "-";
            TnmPetugas1.setText("-");
        }
    }//GEN-LAST:event_chkSaya1ActionPerformed

    private void chkSaya2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya2ActionPerformed
        if (chkSaya2.isSelected() == true) {
            if (akses.getadmin() == true) {
                nip2 = "-";
                TnmPetugas2.setText("-");
            } else {
                nip2 = akses.getkode();
                TnmPetugas2.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip2 + "'"));
            }
        } else {
            nip2 = "-";
            TnmPetugas2.setText("-");
        }
    }//GEN-LAST:event_chkSaya2ActionPerformed

    private void chkSaya3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya3ActionPerformed
        if (chkSaya3.isSelected() == true) {
            if (akses.getadmin() == true) {
                nip3 = "-";
                TnmPetugas3.setText("-");
            } else {
                nip3 = akses.getkode();
                TnmPetugas3.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip3 + "'"));
            }
        } else {
            nip3 = "-";
            TnmPetugas3.setText("-");
        }
    }//GEN-LAST:event_chkSaya3ActionPerformed

    private void MnRiwayatDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatDataActionPerformed
        Valid.SetTgl(DTPCari3, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
        DTPCari4.setDate(new Date());
        TCari2.setText(TNoRm.getText());
        BtnCari2ActionPerformed(null);
        WindowRiwayat.setSize(985, internalFrame1.getHeight() - 40);
        WindowRiwayat.setLocationRelativeTo(internalFrame1);
        WindowRiwayat.setAlwaysOnTop(false);
        WindowRiwayat.setVisible(true);
    }//GEN-LAST:event_MnRiwayatDataActionPerformed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCloseIn10.requestFocus();
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilRiwayat();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari2ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari2, BtnAll1);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari2.setText("");
        tampilRiwayat();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAll1ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari2, TCari2);
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnRestorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRestorActionPerformed
        if (tbRiwayat.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data yang dipilih & telah " + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 7).toString()
                + " akan dikembalikan/restore..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 7).toString().equals("DIHAPUS")) {
                    if (Sequel.cariInteger("select count(-1) from pengelolaan_transfusi_darah where "
                        + "no_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString() + "'") > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Proses kembalikan/restore data gagal, krn. sudah ada data yg. sama..!!");
                } else {
                    kembalikanData();
                    TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                    Valid.SetTgl(DTPCari1, tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 4).toString());
                    BtnCloseIn10ActionPerformed(null);
                    tampil();
                    emptTeks();
                }
            } else {
                kembalikanDataDiganti();
                TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                Valid.SetTgl(DTPCari1, tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 4).toString());
                BtnCloseIn10ActionPerformed(null);
                tampil();
                emptTeks();
            }
        }
        } else {
            WindowRiwayat.setSize(1043, internalFrame1.getHeight() - 40);
            WindowRiwayat.setLocationRelativeTo(internalFrame1);
            WindowRiwayat.setAlwaysOnTop(false);
            WindowRiwayat.setVisible(true);
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnRestorActionPerformed

    private void BtnCloseIn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn10ActionPerformed
        WindowRiwayat.dispose();
        TCari2.setText("");
    }//GEN-LAST:event_BtnCloseIn10ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPengelolaanTransfusiDarah dialog = new RMPengelolaanTransfusiDarah(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll1;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari2;
    private widget.Button BtnCloseIn10;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPetugas1;
    private widget.Button BtnPetugas2;
    private widget.Button BtnPetugas3;
    private widget.Button BtnPrint;
    private widget.Button BtnRestor;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnRiwayatData;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll6;
    private widget.TextBox T15Nadibelum;
    private widget.TextBox T15Nadimasuk;
    private widget.TextBox T15RRbelum;
    private widget.TextBox T15RRmasuk;
    private widget.TextBox T15Suhubelum;
    private widget.TextBox T15Suhumasuk;
    private widget.TextBox T15TDbelum;
    private widget.TextBox T15TDmasuk;
    private widget.TextBox T1JamNadi;
    private widget.TextBox T1JamRR;
    private widget.TextBox T1JamSuhu;
    private widget.TextBox T1JamTD;
    private widget.TextBox TCari;
    private widget.TextBox TCari2;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tjenis;
    private widget.TextBox TnmPetugas1;
    private widget.TextBox TnmPetugas2;
    private widget.TextBox TnmPetugas3;
    private widget.TextBox TruangRwt;
    private widget.Tanggal Ttgl;
    private javax.swing.JDialog WindowRiwayat;
    private widget.CekBox chkSaya1;
    private widget.CekBox chkSaya2;
    private widget.CekBox chkSaya3;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
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
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbRiwayat;
    private widget.Table tbTransfusi;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT pt.*, p.no_rkm_medis, p.nm_pasien, date_format(pt.tanggal,'%d-%m-%Y') tgl, time_format(pt.jam,'%H:%i') jamTransfusi, "
                    + "pg1.nama ptgs1, pg2.nama ptgs2, pg3.nama ptgs3 from pengelolaan_transfusi_darah pt inner join reg_periksa rp on rp.no_rawat=pt.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg1 on pg1.nik=pt.15_sebelum_nip_petugas "
                    + "inner join pegawai pg2 on pg2.nik=pt.15_masuk_nip_petugas inner join pegawai pg3 on pg3.nik=pt.1_masuk_nip_petugas where "
                    + "pt.tanggal between ? and ? and pt.no_rawat like ? or "
                    + "pt.tanggal between ? and ? and p.no_rkm_medis like ? or "
                    + "pt.tanggal between ? and ? and p.nm_pasien like ? or "
                    + "pt.tanggal between ? and ? and pg1.nama like ? or "
                    + "pt.tanggal between ? and ? and pg2.nama like ? or "
                    + "pt.tanggal between ? and ? and pg3.nama like ? order by pt.tanggal desc, pt.jam desc");
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
                        rs.getString("ruang_rawat"),
                        rs.getString("tgl"),
                        rs.getString("jamTransfusi"), 
                        rs.getString("jenis_darah_transfusi"),
                        rs.getString("15_sebelum_td"),
                        rs.getString("15_sebelum_nadi"),
                        rs.getString("15_sebelum_rr"),
                        rs.getString("15_sebelum_suhu"), 
                        rs.getString("ptgs1"),
                        rs.getString("15_masuk_td"),
                        rs.getString("15_masuk_nadi"),
                        rs.getString("15_masuk_rr"),
                        rs.getString("15_masuk_suhu"), 
                        rs.getString("ptgs2"),
                        rs.getString("1_masuk_td"),
                        rs.getString("1_masuk_nadi"),
                        rs.getString("1_masuk_rr"),
                        rs.getString("1_masuk_suhu"), 
                        rs.getString("ptgs3"),
                        rs.getString("tanggal"),
                        rs.getString("jam"),
                        rs.getString("15_sebelum_nip_petugas"),
                        rs.getString("15_masuk_nip_petugas"),
                        rs.getString("1_masuk_nip_petugas"),
                        rs.getString("waktu_simpan")
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
        Ttgl.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        Tjenis.setText("");
        T15TDbelum.setText("");
        T15Nadibelum.setText("");
        T15RRbelum.setText("");
        T15Suhubelum.setText("");
        nip1 = "-";
        TnmPetugas1.setText("-");
        
        T15TDmasuk.setText("");
        T15Nadimasuk.setText("");
        T15RRmasuk.setText("");
        T15Suhumasuk.setText("");
        nip2 = "-";
        TnmPetugas2.setText("-");
        
        T1JamTD.setText("");
        T1JamNadi.setText("");
        T1JamRR.setText("");
        T1JamSuhu.setText("");
        nip3 = "-";
        TnmPetugas3.setText("-");
        
        chkSaya1.setSelected(false);
        chkSaya2.setSelected(false);
        chkSaya3.setSelected(false);
    }

    private void getData() {
        nip1 = "";
        nip2 = "";
        nip3 = "";
        
        if(tbTransfusi.getSelectedRow()!= -1){
            TNoRw.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),0).toString());
            TNoRm.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),1).toString());
            TPasien.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),2).toString());
            TruangRwt.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),3).toString());
            Valid.SetTgl(Ttgl, tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),22).toString());
            cmbJam.setSelectedItem(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),23).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),23).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),23).toString().substring(6, 8));
            Tjenis.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),6).toString());
            
            T15TDbelum.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),7).toString());
            T15Nadibelum.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),8).toString());
            T15RRbelum.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),9).toString());
            T15Suhubelum.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),10).toString());
            nip1 = tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),24).toString();
            TnmPetugas1.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),11).toString());
            
            T15TDmasuk.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),12).toString());
            T15Nadimasuk.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),13).toString());
            T15RRmasuk.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),14).toString());
            T15Suhumasuk.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),15).toString());
            nip2 = tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),25).toString();
            TnmPetugas2.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),16).toString());
            
            T1JamTD.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),17).toString());
            T1JamNadi.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),18).toString());
            T1JamRR.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),19).toString());
            T1JamSuhu.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),20).toString());
            nip3 = tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),26).toString();
            TnmPetugas3.setText(tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),21).toString());
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getcppt());       
       BtnEdit.setEnabled(akses.getcppt());
       BtnHapus.setEnabled(akses.getcppt());
       MnRiwayatData.setEnabled(akses.getadmin());
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan) {
        TNoRw.setText(norw);
        TNoRm.setText(norm);
        TPasien.setText(nmpasien);
        TruangRwt.setText(ruangan);
        TCari.setText(norw);
        
        if (Sequel.cariInteger("select count(-1) from pengelolaan_transfusi_darah where no_rawat='" + norw + "'") > 0) {
            Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tanggal from pengelolaan_transfusi_darah where "
                    + "no_rawat='" + norw + "' order by tanggal desc limit 1"));
        } else {
            DTPCari1.setDate(new Date());
        }
    }
    
    private void simpanHistori() {
        user = "";
        if (akses.getadmin() == true) {
            user = "-";
        } else {
            user = akses.getkode();
        }

        Sequel.menyimpanPesanGagalnyaDiTerminal("pengelolaan_transfusi_darah_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Pengelolaan Transfusi Darah Histori", 24, new String[]{
            TNoRw.getText(), TruangRwt.getText(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                Tjenis.getText(), T15TDbelum.getText(), T15Nadibelum.getText(), T15RRbelum.getText(), T15Suhubelum.getText(), nip1,
                T15TDmasuk.getText(), T15Nadimasuk.getText(), T15RRmasuk.getText(), T15Suhumasuk.getText(), nip2,
                T1JamTD.getText(), T1JamNadi.getText(), T1JamRR.getText(), T1JamSuhu.getText(), nip3, tbTransfusi.getValueAt(tbTransfusi.getSelectedRow(),27).toString(), 
                riwayatData, user, Sequel.cariIsi("select now()")
        });
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabMode1);
        try {
            psrestor = koneksi.prepareStatement("SELECT IF(pg.nama='-','Admin Utama',pg.nama) pelaku, a.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                    + "a.tanggal, a.jam, a.waktu_eksekusi, upper(concat('DI',a.status_data)) sttsdata, a.waktu_simpan "
                    + "FROM pengelolaan_transfusi_darah_histori a inner join reg_periksa rp on rp.no_rawat=a.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN pegawai pg ON pg.nik = a.nik_eksekutor WHERE "
                    + "a.tanggal between ? and ? and pg.nama like ? or "
                    + "a.tanggal between ? and ? and a.no_rawat like ? or "
                    + "a.tanggal between ? and ? and p.no_rkm_medis like ? or "
                    + "a.tanggal between ? and ? and a.status_data like ? or "
                    + "a.tanggal between ? and ? and p.nm_pasien like ? order by a.tanggal desc, a.jam desc");
            try {
                psrestor.setString(1, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(2, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(3, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(4, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(5, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(6, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(7, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(8, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(9, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(10, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(11, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(12, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(13, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(14, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(15, "%" + TCari2.getText().trim() + "%");
                rsrestor = psrestor.executeQuery();
                while (rsrestor.next()) {
                    tabMode1.addRow(new String[]{
                        rsrestor.getString("pelaku"),
                        rsrestor.getString("no_rawat"),
                        rsrestor.getString("no_rkm_medis"),
                        rsrestor.getString("nm_pasien"),
                        rsrestor.getString("tanggal"),
                        rsrestor.getString("jam"),
                        rsrestor.getString("waktu_eksekusi"),
                        rsrestor.getString("sttsdata"),
                        rsrestor.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rsrestor != null) {
                    rsrestor.close();
                }
                if (psrestor != null) {
                    psrestor.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode1.getRowCount());
    }
    
    private void kembalikanData() {
        try {
            ps1 = koneksi.prepareStatement("select * from pengelolaan_transfusi_darah_histori where "
                    + "waktu_eksekusi='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 6).toString() + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    try {
                        Sequel.menyimpan("pengelolaan_transfusi_darah",
                                "'" + rs1.getString("no_rawat") + "',"
                                + "'" + rs1.getString("ruang_rawat") + "',"
                                + "'" + rs1.getString("tanggal") + "',"
                                + "'" + rs1.getString("jam") + "',"
                                + "'" + rs1.getString("jenis_darah_transfusi") + "',"
                                + "'" + rs1.getString("15_sebelum_td") + "',"
                                + "'" + rs1.getString("15_sebelum_nadi") + "',"
                                + "'" + rs1.getString("15_sebelum_rr") + "',"
                                + "'" + rs1.getString("15_sebelum_suhu") + "',"
                                + "'" + rs1.getString("15_sebelum_nip_petugas") + "',"
                                + "'" + rs1.getString("15_masuk_td") + "',"
                                + "'" + rs1.getString("15_masuk_nadi") + "',"
                                + "'" + rs1.getString("15_masuk_rr") + "',"
                                + "'" + rs1.getString("15_masuk_suhu") + "',"
                                + "'" + rs1.getString("15_masuk_nip_petugas") + "',"
                                + "'" + rs1.getString("1_masuk_td") + "',"
                                + "'" + rs1.getString("1_masuk_nadi") + "',"
                                + "'" + rs1.getString("1_masuk_rr") + "',"
                                + "'" + rs1.getString("1_masuk_suhu") + "',"
                                + "'" + rs1.getString("1_masuk_nip_petugas") + "',"
                                + "'" + rs1.getString("waktu_simpan") + "'", "Pengelolaan Transfusi Darah");
                    } catch (Exception e) {
                        System.out.println("Simpan : " + e);
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void kembalikanDataDiganti() {
        if (Sequel.queryu2tf("delete from pengelolaan_transfusi_darah where waktu_simpan=?", 1, new String[]{
            tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 8).toString()
        }) == true) {
            kembalikanData();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal dikembalikan..!!");
        }
    }
}
