    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgSpesialis.java
 *
 * Created on May 23, 2010, 1:25:13 AM
 */

package inventory;

import rekammedis.*;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;

/**
 *
 * @author dosen
 */
public class DlgPelaksanaPemberiObat extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private int x = 0, pilihan = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String nipPetugas1 = "", nipPetugas2 = "", nipPetugas3 = "", nipPetugas4 = "", nipPetugas5 = "",
            nipPetugas6 = "", nipPetugas7 = "", nipPetugas8 = "", wktSimpan = "";

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public DlgPelaksanaPemberiObat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No. Rawat", "No. RM", "Nama Pasien", "Rg. Rawat/Poli/Inst.", "Tgl. Pemberian", 
            "Petugas Pelaksana (Jam 1)", "Petugas Pelaksana (Jam 2)", "Petugas Pelaksana (Jam 3)", "Petugas Pelaksana (Jam 4)",
            "Petugas Pelaksana (Jam 5)", "Petugas Pelaksana (Jam 6)", "Petugas Pelaksana (Jam 7)", "Petugas Pelaksana (Jam 8)",
            "nip1", "nip2", "nip3", "nip4", "nip5", "nip6", "nip7", "nip8", "tgl_pemberian", "waktuSimpan"
        };
        
        tabMode=new DefaultTableModel(null,row){
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbPelaksana.setModel(tabMode);
        tbPelaksana.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPelaksana.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 23; i++) {
            TableColumn column = tbPelaksana.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setPreferredWidth(220);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setPreferredWidth(220);
            } else if (i == 10) {
                column.setPreferredWidth(220);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setPreferredWidth(220);
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
            }
        }
        tbPelaksana.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Nama Obat", "Jns. Obat", "Dosis", "Cara Pemberian/Rute", "Tgl. Beri",
            "Jam 1", "Jam 2", "Jam 3", "Jam 4", "Jam 5", "Jam 6", "Jam 7", "Jam 8"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbObat.setModel(tabMode1);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 13; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(130);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(50);
            } else if (i == 7) {
                column.setPreferredWidth(50);
            } else if (i == 8) {
                column.setPreferredWidth(50);
            } else if (i == 9) {
                column.setPreferredWidth(50);
            } else if (i == 10) {
                column.setPreferredWidth(50);
            } else if (i == 11) {
                column.setPreferredWidth(50);
            } else if (i == 12) {
                column.setPreferredWidth(50);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

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
                        nipPetugas1 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        Tpetugas1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        chkSaya1.setSelected(false);
                        BtnPetugas1.requestFocus();
                    }
                } else if (pilihan == 2) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipPetugas2 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        Tpetugas2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        chkSaya2.setSelected(false);
                        BtnPetugas2.requestFocus();
                    }
                } else if (pilihan == 3) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipPetugas3 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        Tpetugas3.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        chkSaya3.setSelected(false);
                        BtnPetugas3.requestFocus();
                    }
                } else if (pilihan == 4) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipPetugas4 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        Tpetugas4.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        chkSaya4.setSelected(false);
                        BtnPetugas4.requestFocus();
                    }
                } else if (pilihan == 5) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipPetugas5 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        Tpetugas5.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        chkSaya5.setSelected(false);
                        BtnPetugas5.requestFocus();
                    }
                } else if (pilihan == 6) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipPetugas6 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        Tpetugas6.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        chkSaya6.setSelected(false);
                        BtnPetugas6.requestFocus();
                    }
                } else if (pilihan == 7) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipPetugas7 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        Tpetugas7.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        chkSaya7.setSelected(false);
                        BtnPetugas7.requestFocus();
                    }
                } else if (pilihan == 8) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipPetugas8 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        Tpetugas8.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        chkSaya8.setSelected(false);
                        BtnPetugas8.requestFocus();
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

        WindowCetak = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        BtnCetakLap = new widget.Button();
        jLabel18 = new widget.Label();
        cmbJnsRawat = new widget.ComboBox();
        jLabel20 = new widget.Label();
        cmbJnsObat1 = new widget.ComboBox();
        jLabel29 = new widget.Label();
        tgl_beriCetak = new widget.Tanggal();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPelaksana = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
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
        panelGlass10 = new widget.panelisi();
        panelGlass7 = new widget.panelisi();
        jLabel5 = new widget.Label();
        TNoRW = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TNmPasien = new widget.TextBox();
        jLabel16 = new widget.Label();
        Tpetugas1 = new widget.TextBox();
        BtnPetugas1 = new widget.Button();
        BtnHapus1 = new widget.Button();
        chkSaya1 = new widget.CekBox();
        jLabel22 = new widget.Label();
        Tpetugas2 = new widget.TextBox();
        BtnPetugas2 = new widget.Button();
        BtnHapus2 = new widget.Button();
        chkSaya2 = new widget.CekBox();
        jLabel23 = new widget.Label();
        Tpetugas3 = new widget.TextBox();
        BtnPetugas3 = new widget.Button();
        BtnHapus3 = new widget.Button();
        chkSaya3 = new widget.CekBox();
        jLabel24 = new widget.Label();
        Tpetugas4 = new widget.TextBox();
        BtnPetugas4 = new widget.Button();
        BtnHapus4 = new widget.Button();
        chkSaya4 = new widget.CekBox();
        jLabel25 = new widget.Label();
        Tpetugas5 = new widget.TextBox();
        BtnPetugas5 = new widget.Button();
        BtnHapus5 = new widget.Button();
        chkSaya5 = new widget.CekBox();
        jLabel26 = new widget.Label();
        Tpetugas6 = new widget.TextBox();
        BtnPetugas6 = new widget.Button();
        BtnHapus6 = new widget.Button();
        chkSaya6 = new widget.CekBox();
        jLabel27 = new widget.Label();
        Tpetugas7 = new widget.TextBox();
        BtnPetugas7 = new widget.Button();
        BtnHapus7 = new widget.Button();
        chkSaya7 = new widget.CekBox();
        jLabel28 = new widget.Label();
        Tpetugas8 = new widget.TextBox();
        BtnPetugas8 = new widget.Button();
        BtnHapus8 = new widget.Button();
        chkSaya8 = new widget.CekBox();
        jLabel13 = new widget.Label();
        nmUnit = new widget.TextBox();
        jLabel12 = new widget.Label();
        tgl_beri = new widget.Tanggal();
        BtnObat = new widget.Button();
        Scroll1 = new widget.ScrollPane();
        tbObat = new widget.Table();

        WindowCetak.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCetak.setName("WindowCetak"); // NOI18N
        WindowCetak.setUndecorated(true);
        WindowCetak.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Cetak Catatan Laporan Pemberian Obat Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame5.setLayout(null);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(475, 60, 100, 26);

        BtnCetakLap.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetakLap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetakLap.setMnemonic('S');
        BtnCetakLap.setText("Cetak Laporan");
        BtnCetakLap.setToolTipText("Alt+S");
        BtnCetakLap.setName("BtnCetakLap"); // NOI18N
        BtnCetakLap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakLapActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCetakLap);
        BtnCetakLap.setBounds(320, 60, 140, 26);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Jenis Rawat : ");
        jLabel18.setName("jLabel18"); // NOI18N
        internalFrame5.add(jLabel18);
        jLabel18.setBounds(0, 25, 100, 23);

        cmbJnsRawat.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsRawat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "R. Jalan", "R. Inap", "Semua" }));
        cmbJnsRawat.setName("cmbJnsRawat"); // NOI18N
        cmbJnsRawat.setPreferredSize(new java.awt.Dimension(76, 23));
        internalFrame5.add(cmbJnsRawat);
        cmbJnsRawat.setBounds(103, 25, 76, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jns. Pemberian Obat :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(120, 23));
        internalFrame5.add(jLabel20);
        jLabel20.setBounds(375, 25, 120, 23);

        cmbJnsObat1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsObat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "-", "ORAL", "INJEKSI" }));
        cmbJnsObat1.setName("cmbJnsObat1"); // NOI18N
        cmbJnsObat1.setPreferredSize(new java.awt.Dimension(76, 23));
        internalFrame5.add(cmbJnsObat1);
        cmbJnsObat1.setBounds(500, 25, 76, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Tgl. Pemberian : ");
        jLabel29.setName("jLabel29"); // NOI18N
        internalFrame5.add(jLabel29);
        jLabel29.setBounds(180, 25, 90, 23);

        tgl_beriCetak.setEditable(false);
        tgl_beriCetak.setDisplayFormat("dd-MM-yyyy");
        tgl_beriCetak.setName("tgl_beriCetak"); // NOI18N
        internalFrame5.add(tgl_beriCetak);
        tgl_beriCetak.setBounds(273, 25, 100, 23);

        WindowCetak.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Petugas Pelaksana Pemberian Obat Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPelaksana.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPelaksana.setName("tbPelaksana"); // NOI18N
        tbPelaksana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPelaksanaMouseClicked(evt);
            }
        });
        tbPelaksana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPelaksanaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPelaksanaKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbPelaksana);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Pemberian :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-11-2023" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-11-2023" }));
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BtnCariKeyReleased(evt);
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

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass10.setLayout(new java.awt.GridLayout(1, 2));

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass7.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(0, 10, 130, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        panelGlass7.add(TNoRW);
        TNoRW.setBounds(134, 10, 122, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        panelGlass7.add(TNoRM);
        TNoRM.setBounds(260, 10, 70, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setName("TNmPasien"); // NOI18N
        panelGlass7.add(TNmPasien);
        TNmPasien.setBounds(333, 10, 300, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Petugas (Jam 1) :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass7.add(jLabel16);
        jLabel16.setBounds(0, 66, 130, 23);

        Tpetugas1.setEditable(false);
        Tpetugas1.setForeground(new java.awt.Color(0, 0, 0));
        Tpetugas1.setName("Tpetugas1"); // NOI18N
        panelGlass7.add(Tpetugas1);
        Tpetugas1.setBounds(134, 66, 300, 23);

        BtnPetugas1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas1.setMnemonic('1');
        BtnPetugas1.setToolTipText("Alt+1");
        BtnPetugas1.setName("BtnPetugas1"); // NOI18N
        BtnPetugas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas1ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPetugas1);
        BtnPetugas1.setBounds(438, 66, 28, 23);

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapus1.setMnemonic('1');
        BtnHapus1.setToolTipText("Alt+1");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnHapus1);
        BtnHapus1.setBounds(473, 66, 28, 23);

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
        chkSaya1.setBounds(507, 66, 100, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Petugas (Jam 2) :");
        jLabel22.setName("jLabel22"); // NOI18N
        panelGlass7.add(jLabel22);
        jLabel22.setBounds(0, 94, 130, 23);

        Tpetugas2.setEditable(false);
        Tpetugas2.setForeground(new java.awt.Color(0, 0, 0));
        Tpetugas2.setName("Tpetugas2"); // NOI18N
        panelGlass7.add(Tpetugas2);
        Tpetugas2.setBounds(134, 94, 300, 23);

        BtnPetugas2.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas2.setMnemonic('1');
        BtnPetugas2.setToolTipText("Alt+1");
        BtnPetugas2.setName("BtnPetugas2"); // NOI18N
        BtnPetugas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas2ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPetugas2);
        BtnPetugas2.setBounds(438, 94, 28, 23);

        BtnHapus2.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapus2.setMnemonic('1');
        BtnHapus2.setToolTipText("Alt+1");
        BtnHapus2.setName("BtnHapus2"); // NOI18N
        BtnHapus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus2ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnHapus2);
        BtnHapus2.setBounds(473, 94, 28, 23);

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
        chkSaya2.setBounds(507, 94, 100, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Petugas (Jam 3) :");
        jLabel23.setName("jLabel23"); // NOI18N
        panelGlass7.add(jLabel23);
        jLabel23.setBounds(0, 122, 130, 23);

        Tpetugas3.setEditable(false);
        Tpetugas3.setForeground(new java.awt.Color(0, 0, 0));
        Tpetugas3.setName("Tpetugas3"); // NOI18N
        panelGlass7.add(Tpetugas3);
        Tpetugas3.setBounds(134, 122, 300, 23);

        BtnPetugas3.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas3.setMnemonic('1');
        BtnPetugas3.setToolTipText("Alt+1");
        BtnPetugas3.setName("BtnPetugas3"); // NOI18N
        BtnPetugas3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas3ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPetugas3);
        BtnPetugas3.setBounds(438, 122, 28, 23);

        BtnHapus3.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapus3.setMnemonic('1');
        BtnHapus3.setToolTipText("Alt+1");
        BtnHapus3.setName("BtnHapus3"); // NOI18N
        BtnHapus3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus3ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnHapus3);
        BtnHapus3.setBounds(473, 122, 28, 23);

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
        chkSaya3.setBounds(507, 122, 100, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Petugas (Jam 4) :");
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass7.add(jLabel24);
        jLabel24.setBounds(0, 150, 130, 23);

        Tpetugas4.setEditable(false);
        Tpetugas4.setForeground(new java.awt.Color(0, 0, 0));
        Tpetugas4.setName("Tpetugas4"); // NOI18N
        panelGlass7.add(Tpetugas4);
        Tpetugas4.setBounds(134, 150, 300, 23);

        BtnPetugas4.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas4.setMnemonic('1');
        BtnPetugas4.setToolTipText("Alt+1");
        BtnPetugas4.setName("BtnPetugas4"); // NOI18N
        BtnPetugas4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas4ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPetugas4);
        BtnPetugas4.setBounds(438, 150, 28, 23);

        BtnHapus4.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapus4.setMnemonic('1');
        BtnHapus4.setToolTipText("Alt+1");
        BtnHapus4.setName("BtnHapus4"); // NOI18N
        BtnHapus4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus4ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnHapus4);
        BtnHapus4.setBounds(473, 150, 28, 23);

        chkSaya4.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya4.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya4.setText("Saya Sendiri");
        chkSaya4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya4.setName("chkSaya4"); // NOI18N
        chkSaya4.setOpaque(false);
        chkSaya4.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya4ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkSaya4);
        chkSaya4.setBounds(507, 150, 100, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Petugas (Jam 5) :");
        jLabel25.setName("jLabel25"); // NOI18N
        panelGlass7.add(jLabel25);
        jLabel25.setBounds(0, 178, 130, 23);

        Tpetugas5.setEditable(false);
        Tpetugas5.setForeground(new java.awt.Color(0, 0, 0));
        Tpetugas5.setName("Tpetugas5"); // NOI18N
        panelGlass7.add(Tpetugas5);
        Tpetugas5.setBounds(134, 178, 300, 23);

        BtnPetugas5.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas5.setMnemonic('1');
        BtnPetugas5.setToolTipText("Alt+1");
        BtnPetugas5.setName("BtnPetugas5"); // NOI18N
        BtnPetugas5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas5ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPetugas5);
        BtnPetugas5.setBounds(438, 178, 28, 23);

        BtnHapus5.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapus5.setMnemonic('1');
        BtnHapus5.setToolTipText("Alt+1");
        BtnHapus5.setName("BtnHapus5"); // NOI18N
        BtnHapus5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus5ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnHapus5);
        BtnHapus5.setBounds(473, 178, 28, 23);

        chkSaya5.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya5.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya5.setText("Saya Sendiri");
        chkSaya5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya5.setName("chkSaya5"); // NOI18N
        chkSaya5.setOpaque(false);
        chkSaya5.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya5ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkSaya5);
        chkSaya5.setBounds(507, 178, 100, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Petugas (Jam 6) :");
        jLabel26.setName("jLabel26"); // NOI18N
        panelGlass7.add(jLabel26);
        jLabel26.setBounds(0, 206, 130, 23);

        Tpetugas6.setEditable(false);
        Tpetugas6.setForeground(new java.awt.Color(0, 0, 0));
        Tpetugas6.setName("Tpetugas6"); // NOI18N
        panelGlass7.add(Tpetugas6);
        Tpetugas6.setBounds(134, 206, 300, 23);

        BtnPetugas6.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas6.setMnemonic('1');
        BtnPetugas6.setToolTipText("Alt+1");
        BtnPetugas6.setName("BtnPetugas6"); // NOI18N
        BtnPetugas6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas6ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPetugas6);
        BtnPetugas6.setBounds(438, 206, 28, 23);

        BtnHapus6.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapus6.setMnemonic('1');
        BtnHapus6.setToolTipText("Alt+1");
        BtnHapus6.setName("BtnHapus6"); // NOI18N
        BtnHapus6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus6ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnHapus6);
        BtnHapus6.setBounds(473, 206, 28, 23);

        chkSaya6.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya6.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya6.setText("Saya Sendiri");
        chkSaya6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya6.setName("chkSaya6"); // NOI18N
        chkSaya6.setOpaque(false);
        chkSaya6.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya6ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkSaya6);
        chkSaya6.setBounds(507, 206, 100, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Petugas (Jam 7) :");
        jLabel27.setName("jLabel27"); // NOI18N
        panelGlass7.add(jLabel27);
        jLabel27.setBounds(0, 234, 130, 23);

        Tpetugas7.setEditable(false);
        Tpetugas7.setForeground(new java.awt.Color(0, 0, 0));
        Tpetugas7.setName("Tpetugas7"); // NOI18N
        panelGlass7.add(Tpetugas7);
        Tpetugas7.setBounds(134, 234, 300, 23);

        BtnPetugas7.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas7.setMnemonic('1');
        BtnPetugas7.setToolTipText("Alt+1");
        BtnPetugas7.setName("BtnPetugas7"); // NOI18N
        BtnPetugas7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas7ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPetugas7);
        BtnPetugas7.setBounds(438, 234, 28, 23);

        BtnHapus7.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapus7.setMnemonic('1');
        BtnHapus7.setToolTipText("Alt+1");
        BtnHapus7.setName("BtnHapus7"); // NOI18N
        BtnHapus7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus7ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnHapus7);
        BtnHapus7.setBounds(473, 234, 28, 23);

        chkSaya7.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya7.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya7.setText("Saya Sendiri");
        chkSaya7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya7.setName("chkSaya7"); // NOI18N
        chkSaya7.setOpaque(false);
        chkSaya7.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya7ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkSaya7);
        chkSaya7.setBounds(507, 234, 100, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Petugas (Jam 8) :");
        jLabel28.setName("jLabel28"); // NOI18N
        panelGlass7.add(jLabel28);
        jLabel28.setBounds(0, 262, 130, 23);

        Tpetugas8.setEditable(false);
        Tpetugas8.setForeground(new java.awt.Color(0, 0, 0));
        Tpetugas8.setName("Tpetugas8"); // NOI18N
        panelGlass7.add(Tpetugas8);
        Tpetugas8.setBounds(134, 262, 300, 23);

        BtnPetugas8.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas8.setMnemonic('1');
        BtnPetugas8.setToolTipText("Alt+1");
        BtnPetugas8.setName("BtnPetugas8"); // NOI18N
        BtnPetugas8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas8ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPetugas8);
        BtnPetugas8.setBounds(438, 262, 28, 23);

        BtnHapus8.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapus8.setMnemonic('1');
        BtnHapus8.setToolTipText("Alt+1");
        BtnHapus8.setName("BtnHapus8"); // NOI18N
        BtnHapus8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus8ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnHapus8);
        BtnHapus8.setBounds(473, 262, 28, 23);

        chkSaya8.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya8.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya8.setText("Saya Sendiri");
        chkSaya8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya8.setName("chkSaya8"); // NOI18N
        chkSaya8.setOpaque(false);
        chkSaya8.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya8ActionPerformed(evt);
            }
        });
        panelGlass7.add(chkSaya8);
        chkSaya8.setBounds(507, 262, 100, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Rg. Rawat/Poli/Inst :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass7.add(jLabel13);
        jLabel13.setBounds(0, 38, 130, 23);

        nmUnit.setEditable(false);
        nmUnit.setForeground(new java.awt.Color(0, 0, 0));
        nmUnit.setName("nmUnit"); // NOI18N
        panelGlass7.add(nmUnit);
        nmUnit.setBounds(134, 38, 300, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Pemberian :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass7.add(jLabel12);
        jLabel12.setBounds(435, 38, 90, 23);

        tgl_beri.setEditable(false);
        tgl_beri.setDisplayFormat("dd-MM-yyyy");
        tgl_beri.setName("tgl_beri"); // NOI18N
        panelGlass7.add(tgl_beri);
        tgl_beri.setBounds(530, 38, 100, 23);

        BtnObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnObat.setMnemonic('L');
        BtnObat.setText("Lihat Obat");
        BtnObat.setToolTipText("Tampilkan Data Obat Pada Tabel Disebelah Ini");
        BtnObat.setGlassColor(new java.awt.Color(0, 204, 255));
        BtnObat.setName("BtnObat"); // NOI18N
        BtnObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnObatActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnObat);
        BtnObat.setBounds(635, 38, 110, 23);

        panelGlass10.add(panelGlass7);

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Daftar Item Obat Terjadwal Sesuai Tgl. Pemberian ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbObat.setToolTipText("");
        tbObat.setName("tbObat"); // NOI18N
        Scroll1.setViewportView(tbObat);

        panelGlass10.add(Scroll1);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (nipPetugas1.equals("-")) {
            Valid.textKosong(Tpetugas1, "Nama Petugas Pelaksana (Jam 1) ");
        } else if (Sequel.cariInteger("select count(-1) from pelaksana_pemberian_obat where no_rawat='" + TNoRW.getText() + "' "
                + "and tgl_pemberian='" + Valid.SetTgl(tgl_beri.getSelectedItem() + "") + "'") > 0) {
            JOptionPane.showMessageDialog(rootPane, "Petugas pelaksana pemberian obat utk. tgl. " + tgl_beri.getSelectedItem() + " An. Pasien " + TNmPasien.getText() + " sudah tersimpan..!!");
        } else {
            Sequel.menyimpan("pelaksana_pemberian_obat",
                    "'" + TNoRW.getText() + "','" + Sequel.cariIsi("select now()") + "','" + Valid.SetTgl(tgl_beri.getSelectedItem() + "") + "',"
                    + "'" + nipPetugas1 + "', '" + nipPetugas2 + "', '" + nipPetugas3 + "', '" + nipPetugas4 + "', "
                    + "'" + nipPetugas5 + "', '" + nipPetugas6 + "', '" + nipPetugas7 + "', '" + nipPetugas8 + "'", "Petugas Pelaksana Pemberian Obat");
            
            Valid.SetTgl(DTPCari1, Valid.SetTgl(tgl_beri.getSelectedItem() + ""));
            emptTeks();
            tampil();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

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
        if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datany pada tabel..!!");
            tbPelaksana.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from pelaksana_pemberian_obat where waktu_simpan=?", 1, new String[]{
                    wktSimpan
                }) == true) {
                    tampil();
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
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
        } else if (nipPetugas1.equals("-")) {
            Valid.textKosong(Tpetugas1, "Nama Petugas Pelaksana (Jam 1) ");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datany pada tabel..!!");
            tbPelaksana.requestFocus();
        } else {
            Sequel.mengedit("pelaksana_pemberian_obat", "waktu_simpan=?", "tgl_pemberian=?, nip_petugas_jam1=?, nip_petugas_jam2=?, "
                    + "nip_petugas_jam3=?, nip_petugas_jam4=?, nip_petugas_jam5=?, nip_petugas_jam6=?, nip_petugas_jam7=?, nip_petugas_jam8=?", 10, new String[]{
                        Valid.SetTgl(tgl_beri.getSelectedItem() + ""), nipPetugas1, nipPetugas2, nipPetugas3, 
                        nipPetugas4, nipPetugas5, nipPetugas6, nipPetugas7, nipPetugas8,
                        wktSimpan
                    });

            Valid.SetTgl(DTPCari1, Valid.SetTgl(tgl_beri.getSelectedItem() + ""));
            emptTeks();
            tampil();
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
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
            tbPelaksana.requestFocus();
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
       TCari.setText("");
       tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbPelaksanaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPelaksanaMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPelaksanaMouseClicked

    private void tbPelaksanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPelaksanaKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCari.setText("");
                TCari.requestFocus();
            }           
        }
}//GEN-LAST:event_tbPelaksanaKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void tbPelaksanaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPelaksanaKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }          
        }
    }//GEN-LAST:event_tbPelaksanaKeyReleased

    private void BtnPetugas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas1ActionPerformed
        pilihan = 1;
        akses.setform("DlgPelaksanaPemberiObat");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas1ActionPerformed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        nipPetugas1 = "-";
        Tpetugas1.setText("-");
        chkSaya1.setSelected(false);
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void chkSaya1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya1ActionPerformed
        if (chkSaya1.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipPetugas1 = "-";
                Tpetugas1.setText("-");
            } else {
                nipPetugas1 = akses.getkode();
                Tpetugas1.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPetugas1 + "'"));
            }
        } else {
            nipPetugas1 = "-";
            Tpetugas1.setText("-");
        }
    }//GEN-LAST:event_chkSaya1ActionPerformed

    private void BtnPetugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas2ActionPerformed
        pilihan = 2;
        akses.setform("DlgPelaksanaPemberiObat");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas2ActionPerformed

    private void BtnHapus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus2ActionPerformed
        nipPetugas2 = "-";
        Tpetugas2.setText("-");
        chkSaya2.setSelected(false);
    }//GEN-LAST:event_BtnHapus2ActionPerformed

    private void chkSaya2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya2ActionPerformed
        if (chkSaya2.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipPetugas2 = "-";
                Tpetugas2.setText("-");
            } else {
                nipPetugas2 = akses.getkode();
                Tpetugas2.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPetugas2 + "'"));
            }
        } else {
            nipPetugas2 = "-";
            Tpetugas2.setText("-");
        }
    }//GEN-LAST:event_chkSaya2ActionPerformed

    private void BtnPetugas3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas3ActionPerformed
        pilihan = 3;
        akses.setform("DlgPelaksanaPemberiObat");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas3ActionPerformed

    private void BtnHapus3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus3ActionPerformed
        nipPetugas3 = "-";
        Tpetugas3.setText("-");
        chkSaya3.setSelected(false);
    }//GEN-LAST:event_BtnHapus3ActionPerformed

    private void chkSaya3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya3ActionPerformed
        if (chkSaya3.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipPetugas3 = "-";
                Tpetugas3.setText("-");
            } else {
                nipPetugas3 = akses.getkode();
                Tpetugas3.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPetugas3 + "'"));
            }
        } else {
            nipPetugas3 = "-";
            Tpetugas3.setText("-");
        }
    }//GEN-LAST:event_chkSaya3ActionPerformed

    private void BtnPetugas4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas4ActionPerformed
        pilihan = 4;
        akses.setform("DlgPelaksanaPemberiObat");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas4ActionPerformed

    private void BtnHapus4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus4ActionPerformed
        nipPetugas4 = "-";
        Tpetugas4.setText("-");
        chkSaya4.setSelected(false);
    }//GEN-LAST:event_BtnHapus4ActionPerformed

    private void chkSaya4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya4ActionPerformed
        if (chkSaya4.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipPetugas4 = "-";
                Tpetugas4.setText("-");
            } else {
                nipPetugas4 = akses.getkode();
                Tpetugas4.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPetugas4 + "'"));
            }
        } else {
            nipPetugas4 = "-";
            Tpetugas4.setText("-");
        }
    }//GEN-LAST:event_chkSaya4ActionPerformed

    private void BtnPetugas5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas5ActionPerformed
        pilihan = 5;
        akses.setform("DlgPelaksanaPemberiObat");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas5ActionPerformed

    private void BtnHapus5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus5ActionPerformed
        nipPetugas5 = "-";
        Tpetugas5.setText("-");
        chkSaya5.setSelected(false);
    }//GEN-LAST:event_BtnHapus5ActionPerformed

    private void chkSaya5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya5ActionPerformed
        if (chkSaya5.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipPetugas5 = "-";
                Tpetugas5.setText("-");
            } else {
                nipPetugas5 = akses.getkode();
                Tpetugas5.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPetugas5 + "'"));
            }
        } else {
            nipPetugas5 = "-";
            Tpetugas5.setText("-");
        }
    }//GEN-LAST:event_chkSaya5ActionPerformed

    private void BtnPetugas6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas6ActionPerformed
        pilihan = 6;
        akses.setform("DlgPelaksanaPemberiObat");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas6ActionPerformed

    private void BtnHapus6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus6ActionPerformed
        nipPetugas6 = "-";
        Tpetugas6.setText("-");
        chkSaya6.setSelected(false);
    }//GEN-LAST:event_BtnHapus6ActionPerformed

    private void chkSaya6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya6ActionPerformed
        if (chkSaya6.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipPetugas6 = "-";
                Tpetugas6.setText("-");
            } else {
                nipPetugas6 = akses.getkode();
                Tpetugas6.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPetugas6 + "'"));
            }
        } else {
            nipPetugas6 = "-";
            Tpetugas6.setText("-");
        }
    }//GEN-LAST:event_chkSaya6ActionPerformed

    private void BtnPetugas7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas7ActionPerformed
        pilihan = 7;
        akses.setform("DlgPelaksanaPemberiObat");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas7ActionPerformed

    private void BtnHapus7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus7ActionPerformed
        nipPetugas7 = "-";
        Tpetugas7.setText("-");
        chkSaya7.setSelected(false);
    }//GEN-LAST:event_BtnHapus7ActionPerformed

    private void chkSaya7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya7ActionPerformed
        if (chkSaya7.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipPetugas7 = "-";
                Tpetugas7.setText("-");
            } else {
                nipPetugas7 = akses.getkode();
                Tpetugas7.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPetugas7 + "'"));
            }
        } else {
            nipPetugas7 = "-";
            Tpetugas7.setText("-");
        }
    }//GEN-LAST:event_chkSaya7ActionPerformed

    private void BtnPetugas8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas8ActionPerformed
        pilihan = 8;
        akses.setform("DlgPelaksanaPemberiObat");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas8ActionPerformed

    private void BtnHapus8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus8ActionPerformed
        nipPetugas8 = "-";
        Tpetugas8.setText("-");
        chkSaya8.setSelected(false);
    }//GEN-LAST:event_BtnHapus8ActionPerformed

    private void chkSaya8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya8ActionPerformed
        if (chkSaya8.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipPetugas8 = "-";
                Tpetugas8.setText("-");
            } else {
                nipPetugas8 = akses.getkode();
                Tpetugas8.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPetugas8 + "'"));
            }
        } else {
            nipPetugas8 = "-";
            Tpetugas8.setText("-");
        }
    }//GEN-LAST:event_chkSaya8ActionPerformed

    private void BtnObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnObatActionPerformed
        tampilObat();
        nmUnit.setText(Sequel.cariIsi("select nm_unit from pemberian_obat where no_rawat='" + TNoRW.getText() + "' and "
                + "tgl_pemberian='" + Valid.SetTgl(tgl_beri.getSelectedItem() + "") + "' order by waktu_simpan desc limit 1"));
    }//GEN-LAST:event_BtnObatActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (Sequel.cariInteger("select count(-1) from pemberian_obat where no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data pemberian obat dg. no. rawat pasien tersebut belum tersimpan...!!!!");
        } else if (Sequel.cariInteger("select count(-1) from pelaksana_pemberian_obat where no_rawat='" + TNoRW.getText() + "' "
                + "and tgl_pemberian='" + Valid.SetTgl(tgl_beri.getSelectedItem() + "") + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Cek lagi tgl. pemberiannya utk. memastikan petugas yang melaksanakan pemberian obat...!!!!");
        } else {
            if (tabMode.getRowCount() != 0) {
                WindowCetak.setSize(598, 105);
                WindowCetak.setLocationRelativeTo(internalFrame1);
                WindowCetak.setAlwaysOnTop(false);
                WindowCetak.setVisible(true);

                cmbJnsObat1.setSelectedIndex(0);
                tgl_beriCetak.setDate(tgl_beri.getDate());

                if (nmUnit.getText().equals("IGD")) {
                    cmbJnsRawat.setSelectedIndex(0);
                } else if (!nmUnit.getText().equals("IGD")) {
                    cmbJnsRawat.setSelectedIndex(1);
                }
            }
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowCetak.dispose();
        tampil();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnCetakLapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakLapActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("pelaksana", Sequel.cariIsi("SELECT concat('Keterangan : \n',"
            + "if(nip_petugas_jam1='-','',concat(' - Petugas Pelaksana (Jam 1) : ',pg1.nama)),'\n',"
            + "if(nip_petugas_jam2='-','',concat(' - Petugas Pelaksana (Jam 2) : ',pg2.nama)),'\n',"
            + "if(nip_petugas_jam3='-','',concat(' - Petugas Pelaksana (Jam 3) : ',pg3.nama)),'\n',"
            + "if(nip_petugas_jam4='-','',concat(' - Petugas Pelaksana (Jam 4) : ',pg4.nama)),'\n',"
            + "if(nip_petugas_jam5='-','',concat(' - Petugas Pelaksana (Jam 5) : ',pg5.nama)),'\n',"
            + "if(nip_petugas_jam6='-','',concat(' - Petugas Pelaksana (Jam 6) : ',pg6.nama)),'\n',"
            + "if(nip_petugas_jam7='-','',concat(' - Petugas Pelaksana (Jam 7) : ',pg7.nama)),'\n',"
            + "if(nip_petugas_jam8='-','',concat(' - Petugas Pelaksana (Jam 8) : ',pg8.nama))) petugas "
            + "FROM pelaksana_pemberian_obat pp inner join pegawai pg1 on pg1.nik=pp.nip_petugas_jam1 "
            + "inner join pegawai pg2 on pg2.nik=pp.nip_petugas_jam2 inner join pegawai pg3 on pg3.nik=pp.nip_petugas_jam3 "
            + "inner join pegawai pg4 on pg4.nik=pp.nip_petugas_jam4 inner join pegawai pg5 on pg5.nik=pp.nip_petugas_jam5 "
            + "inner join pegawai pg6 on pg6.nik=pp.nip_petugas_jam6 inner join pegawai pg7 on pg7.nik=pp.nip_petugas_jam7 "
            + "inner join pegawai pg8 on pg8.nik=pp.nip_petugas_jam8 WHERE pp.no_rawat='" + TNoRW.getText() + "' "
            + "and pp.tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' GROUP BY pp.no_rawat, pp.tgl_pemberian order by pp.waktu_simpan desc"));

    //semua rawat
    if (cmbJnsRawat.getSelectedIndex() == 2) {
        param.put("ruangan", "Semua");
        if (cmbJnsObat1.getSelectedIndex() == 0) {
            if (Sequel.cariInteger("select count(-1) from pemberian_obat where tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                + "and no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
        } else {
            param.put("pemberian", "SEMUA JENIS PEMBERIAN OBAT");
            Valid.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                "SELECT p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur, "
                + "po.jenis_obat, po.nama_obat, po.jlh_obat, po.dosis, po.cara_pemberian rute, concat('TANGGAL : ',date_format(po.tgl_pemberian, '%d-%m-%Y' )) tglberi, "
                + "if(po.cek_jam1='ya',time_format(po.jadwal_pemberian,'%H:%i'),'') jam1, if(po.cek_jam2='ya',time_format(po.jadwal_pemberian2,'%H:%i'),'') jam2, "
                + "if(po.cek_jam3='ya',time_format(po.jadwal_pemberian3,'%H:%i'),'') jam3, if(po.cek_jam4='ya',time_format(po.jadwal_pemberian4,'%H:%i'),'') jam4, "
                + "if(po.cek_jam5='ya',time_format(po.jadwal_pemberian5,'%H:%i'),'') jam5, if(po.cek_jam6='ya',time_format(po.jadwal_pemberian6,'%H:%i'),'') jam6, "
                + "if(po.cek_jam7='ya',time_format(po.jadwal_pemberian7,'%H:%i'),'') jam7, if(po.cek_jam8='ya',time_format(po.jadwal_pemberian8,'%H:%i'),'') jam8, "
                + "po.jlh_sisa_obat	FROM pemberian_obat po INNER JOIN reg_periksa rp ON rp.no_rawat=po.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                + "po.tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' and po.no_rawat='" + TNoRW.getText() + "' "
                + "order by po.tgl_pemberian, po.waktu_simpan, po.nama_obat", param);
            tampil();
            emptTeks();
            WindowCetak.dispose();
        }
        } else {
            if (Sequel.cariInteger("select count(-1) from pemberian_obat where tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                + "and jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' and no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
        } else {
            param.put("pemberian", "PEMBERIAN OBAT " + cmbJnsObat1.getSelectedItem());
            Valid.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                "SELECT p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur, "
                + "po.jenis_obat, po.nama_obat, po.jlh_obat, po.dosis, po.cara_pemberian rute, concat('TANGGAL : ',date_format(po.tgl_pemberian, '%d-%m-%Y' )) tglberi, "
                + "if(po.cek_jam1='ya',time_format(po.jadwal_pemberian,'%H:%i'),'') jam1, if(po.cek_jam2='ya',time_format(po.jadwal_pemberian2,'%H:%i'),'') jam2, "
                + "if(po.cek_jam3='ya',time_format(po.jadwal_pemberian3,'%H:%i'),'') jam3, if(po.cek_jam4='ya',time_format(po.jadwal_pemberian4,'%H:%i'),'') jam4, "
                + "if(po.cek_jam5='ya',time_format(po.jadwal_pemberian5,'%H:%i'),'') jam5, if(po.cek_jam6='ya',time_format(po.jadwal_pemberian6,'%H:%i'),'') jam6, "
                + "if(po.cek_jam7='ya',time_format(po.jadwal_pemberian7,'%H:%i'),'') jam7, if(po.cek_jam8='ya',time_format(po.jadwal_pemberian8,'%H:%i'),'') jam8, "
                + "po.jlh_sisa_obat	FROM pemberian_obat po INNER JOIN reg_periksa rp ON rp.no_rawat=po.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                + "po.tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' and po.jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' "
                + "and po.no_rawat='" + TNoRW.getText() + "' order by po.tgl_pemberian, po.waktu_simpan, po.nama_obat", param);
            tampil();
            emptTeks();
            WindowCetak.dispose();
        }
        }

        //rawat jalan
        } else if (cmbJnsRawat.getSelectedIndex() == 0) {
            param.put("ruangan", Sequel.cariIsi("select nm_unit from pemberian_obat where tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                + "and no_rawat='" + TNoRW.getText() + "' and nm_unit='IGD' order by waktu_simpan desc limit 1"));
        if (cmbJnsObat1.getSelectedIndex() == 0) {
            if (Sequel.cariInteger("select count(-1) from pemberian_obat where nm_unit='IGD' and tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                + "and no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
        } else {
            param.put("pemberian", "SEMUA JENIS PEMBERIAN OBAT");
            Valid.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                "SELECT p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur, "
                + "po.jenis_obat, po.nama_obat, po.jlh_obat, po.dosis, po.cara_pemberian rute, concat('TANGGAL : ',date_format(po.tgl_pemberian, '%d-%m-%Y' )) tglberi, "
                + "if(po.cek_jam1='ya',time_format(po.jadwal_pemberian,'%H:%i'),'') jam1, if(po.cek_jam2='ya',time_format(po.jadwal_pemberian2,'%H:%i'),'') jam2, "
                + "if(po.cek_jam3='ya',time_format(po.jadwal_pemberian3,'%H:%i'),'') jam3, if(po.cek_jam4='ya',time_format(po.jadwal_pemberian4,'%H:%i'),'') jam4, "
                + "if(po.cek_jam5='ya',time_format(po.jadwal_pemberian5,'%H:%i'),'') jam5, if(po.cek_jam6='ya',time_format(po.jadwal_pemberian6,'%H:%i'),'') jam6, "
                + "if(po.cek_jam7='ya',time_format(po.jadwal_pemberian7,'%H:%i'),'') jam7, if(po.cek_jam8='ya',time_format(po.jadwal_pemberian8,'%H:%i'),'') jam8, "
                + "po.jlh_sisa_obat	FROM pemberian_obat po INNER JOIN reg_periksa rp ON rp.no_rawat=po.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                + "po.nm_unit='IGD' and po.tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' and po.no_rawat='" + TNoRW.getText() + "' "
                + "order by po.tgl_pemberian, po.waktu_simpan, po.nama_obat", param);
            tampil();
            emptTeks();
            WindowCetak.dispose();
        }
        } else {
            if (Sequel.cariInteger("select count(-1) from pemberian_obat where nm_unit='IGD' and tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                + "and jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' and no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
        } else {
            param.put("pemberian", "PEMBERIAN OBAT " + cmbJnsObat1.getSelectedItem());
            Valid.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                "SELECT p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur, "
                + "po.jenis_obat, po.nama_obat, po.jlh_obat, po.dosis, po.cara_pemberian rute, concat('TANGGAL : ',date_format(po.tgl_pemberian, '%d-%m-%Y' )) tglberi, "
                + "if(po.cek_jam1='ya',time_format(po.jadwal_pemberian,'%H:%i'),'') jam1, if(po.cek_jam2='ya',time_format(po.jadwal_pemberian2,'%H:%i'),'') jam2, "
                + "if(po.cek_jam3='ya',time_format(po.jadwal_pemberian3,'%H:%i'),'') jam3, if(po.cek_jam4='ya',time_format(po.jadwal_pemberian4,'%H:%i'),'') jam4, "
                + "if(po.cek_jam5='ya',time_format(po.jadwal_pemberian5,'%H:%i'),'') jam5, if(po.cek_jam6='ya',time_format(po.jadwal_pemberian6,'%H:%i'),'') jam6, "
                + "if(po.cek_jam7='ya',time_format(po.jadwal_pemberian7,'%H:%i'),'') jam7, if(po.cek_jam8='ya',time_format(po.jadwal_pemberian8,'%H:%i'),'') jam8, "
                + "po.jlh_sisa_obat	FROM pemberian_obat po INNER JOIN reg_periksa rp ON rp.no_rawat=po.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                + "po.nm_unit='IGD' and po.tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                + "and po.jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' and po.no_rawat='" + TNoRW.getText() + "' "
                + "order by po.tgl_pemberian, po.waktu_simpan, po.nama_obat", param);
            tampil();
            emptTeks();
            WindowCetak.dispose();
        }
        }

        //rawat inap
        } else if (cmbJnsRawat.getSelectedIndex() == 1) {
            param.put("ruangan", Sequel.cariIsi("select nm_unit from pemberian_obat where tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                + "and no_rawat='" + TNoRW.getText() + "' and nm_unit<>'IGD' order by waktu_simpan desc limit 1"));
        if (cmbJnsObat1.getSelectedIndex() == 0) {
            if (Sequel.cariInteger("select count(-1) from pemberian_obat where nm_unit<>'IGD' and tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                + "and no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
        } else {
            param.put("pemberian", "SEMUA JENIS PEMBERIAN OBAT");
            Valid.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                "SELECT p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur, "
                + "po.jenis_obat, po.nama_obat, po.jlh_obat, po.dosis, po.cara_pemberian rute, concat('TANGGAL : ',date_format(po.tgl_pemberian, '%d-%m-%Y' )) tglberi, "
                + "if(po.cek_jam1='ya',time_format(po.jadwal_pemberian,'%H:%i'),'') jam1, if(po.cek_jam2='ya',time_format(po.jadwal_pemberian2,'%H:%i'),'') jam2, "
                + "if(po.cek_jam3='ya',time_format(po.jadwal_pemberian3,'%H:%i'),'') jam3, if(po.cek_jam4='ya',time_format(po.jadwal_pemberian4,'%H:%i'),'') jam4, "
                + "if(po.cek_jam5='ya',time_format(po.jadwal_pemberian5,'%H:%i'),'') jam5, if(po.cek_jam6='ya',time_format(po.jadwal_pemberian6,'%H:%i'),'') jam6, "
                + "if(po.cek_jam7='ya',time_format(po.jadwal_pemberian7,'%H:%i'),'') jam7, if(po.cek_jam8='ya',time_format(po.jadwal_pemberian8,'%H:%i'),'') jam8, "
                + "po.jlh_sisa_obat	FROM pemberian_obat po INNER JOIN reg_periksa rp ON rp.no_rawat=po.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                + "po.nm_unit<>'IGD' and po.tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' and po.no_rawat='" + TNoRW.getText() + "' "
                + "order by po.tgl_pemberian, po.waktu_simpan, po.nama_obat", param);
            tampil();
            emptTeks();
            WindowCetak.dispose();
        }
        } else {
            if (Sequel.cariInteger("select count(-1) from pemberian_obat where nm_unit<>'IGD' and tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' "
                + "and jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' and no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
        } else {
            param.put("pemberian", "PEMBERIAN OBAT " + cmbJnsObat1.getSelectedItem());
            Valid.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                "SELECT p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur, "
                + "po.jenis_obat, po.nama_obat, po.jlh_obat, po.dosis, po.cara_pemberian rute, concat('TANGGAL : ',date_format(po.tgl_pemberian, '%d-%m-%Y' )) tglberi, "
                + "if(po.cek_jam1='ya',time_format(po.jadwal_pemberian,'%H:%i'),'') jam1, if(po.cek_jam2='ya',time_format(po.jadwal_pemberian2,'%H:%i'),'') jam2, "
                + "if(po.cek_jam3='ya',time_format(po.jadwal_pemberian3,'%H:%i'),'') jam3, if(po.cek_jam4='ya',time_format(po.jadwal_pemberian4,'%H:%i'),'') jam4, "
                + "if(po.cek_jam5='ya',time_format(po.jadwal_pemberian5,'%H:%i'),'') jam5, if(po.cek_jam6='ya',time_format(po.jadwal_pemberian6,'%H:%i'),'') jam6, "
                + "if(po.cek_jam7='ya',time_format(po.jadwal_pemberian7,'%H:%i'),'') jam7, if(po.cek_jam8='ya',time_format(po.jadwal_pemberian8,'%H:%i'),'') jam8, "
                + "po.jlh_sisa_obat	FROM pemberian_obat po INNER JOIN reg_periksa rp ON rp.no_rawat=po.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                + "po.nm_unit<>'IGD' and po.tgl_pemberian='" + Valid.SetTgl(tgl_beriCetak.getSelectedItem() + "") + "' and po.jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' "
                + "and po.no_rawat='" + TNoRW.getText() + "' order by po.tgl_pemberian, po.waktu_simpan, po.nama_obat", param);
            tampil();
            emptTeks();
            WindowCetak.dispose();
        }
        }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCetakLapActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPelaksanaPemberiObat dialog = new DlgPelaksanaPemberiObat(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCetakLap;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnHapus2;
    private widget.Button BtnHapus3;
    private widget.Button BtnHapus4;
    private widget.Button BtnHapus5;
    private widget.Button BtnHapus6;
    private widget.Button BtnHapus7;
    private widget.Button BtnHapus8;
    private widget.Button BtnKeluar;
    private widget.Button BtnObat;
    private widget.Button BtnPetugas1;
    private widget.Button BtnPetugas2;
    private widget.Button BtnPetugas3;
    private widget.Button BtnPetugas4;
    private widget.Button BtnPetugas5;
    private widget.Button BtnPetugas6;
    private widget.Button BtnPetugas7;
    private widget.Button BtnPetugas8;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.TextBox TCari;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextBox Tpetugas1;
    private widget.TextBox Tpetugas2;
    private widget.TextBox Tpetugas3;
    private widget.TextBox Tpetugas4;
    private widget.TextBox Tpetugas5;
    private widget.TextBox Tpetugas6;
    private widget.TextBox Tpetugas7;
    private widget.TextBox Tpetugas8;
    private javax.swing.JDialog WindowCetak;
    private widget.CekBox chkSaya1;
    private widget.CekBox chkSaya2;
    private widget.CekBox chkSaya3;
    private widget.CekBox chkSaya4;
    private widget.CekBox chkSaya5;
    private widget.CekBox chkSaya6;
    private widget.CekBox chkSaya7;
    private widget.CekBox chkSaya8;
    private widget.ComboBox cmbJnsObat1;
    private widget.ComboBox cmbJnsRawat;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
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
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox nmUnit;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbObat;
    private widget.Table tbPelaksana;
    private widget.Tanggal tgl_beri;
    private widget.Tanggal tgl_beriCetak;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select pp.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(pp.tgl_pemberian,'%d-%m-%Y') tglberi, "
                    + "pg1.nama ptg1, pg2.nama ptg2, pg3.nama ptg3, pg4.nama ptg4, pg5.nama ptg5, pg6.nama ptg6, pg7.nama ptg7, pg8.nama ptg8, "
                    + "pp.nip_petugas_jam1, pp.nip_petugas_jam2, pp.nip_petugas_jam3, pp.nip_petugas_jam4, pp.nip_petugas_jam5, pp.nip_petugas_jam6, "
                    + "pp.nip_petugas_jam7, pp.nip_petugas_jam8, pp.tgl_pemberian, pp.waktu_simpan from pelaksana_pemberian_obat pp "
                    + "inner join reg_periksa rp on rp.no_rawat=pp.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg1 on pg1.nik=pp.nip_petugas_jam1 inner join pegawai pg2 on pg2.nik=pp.nip_petugas_jam2 "
                    + "inner join pegawai pg3 on pg3.nik=pp.nip_petugas_jam3 inner join pegawai pg4 on pg4.nik=pp.nip_petugas_jam4 "
                    + "inner join pegawai pg5 on pg5.nik=pp.nip_petugas_jam5 inner join pegawai pg6 on pg6.nik=pp.nip_petugas_jam6 "
                    + "inner join pegawai pg7 on pg7.nik=pp.nip_petugas_jam7 inner join pegawai pg8 on pg8.nik=pp.nip_petugas_jam8 where "
                    + "pp.tgl_pemberian between ? and ? and pp.no_rawat like ? or "
                    + "pp.tgl_pemberian between ? and ? and p.no_rkm_medis like ? or "
                    + "pp.tgl_pemberian between ? and ? and p.nm_pasien like ? or "                    
                    + "pp.tgl_pemberian between ? and ? and pg1.nama like ? or "
                    + "pp.tgl_pemberian between ? and ? and pg2.nama like ? or "
                    + "pp.tgl_pemberian between ? and ? and pg3.nama like ? or "
                    + "pp.tgl_pemberian between ? and ? and pg4.nama like ? or "
                    + "pp.tgl_pemberian between ? and ? and pg5.nama like ? or "
                    + "pp.tgl_pemberian between ? and ? and pg6.nama like ? or "
                    + "pp.tgl_pemberian between ? and ? and pg7.nama like ? or "
                    + "pp.tgl_pemberian between ? and ? and pg8.nama like ? order by pp.tgl_pemberian desc");
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
                        rs.getString("no_rawat"), 
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        Sequel.cariIsi("select nm_unit from pemberian_obat where no_rawat='" + rs.getString("no_rawat") + "' and tgl_pemberian='" + rs.getString("tgl_pemberian") + "'"),
                        rs.getString("tglberi"),
                        rs.getString("ptg1"),
                        rs.getString("ptg2"),
                        rs.getString("ptg3"),
                        rs.getString("ptg4"),
                        rs.getString("ptg5"),
                        rs.getString("ptg6"),
                        rs.getString("ptg7"),
                        rs.getString("ptg8"),
                        rs.getString("nip_petugas_jam1"),
                        rs.getString("nip_petugas_jam2"),
                        rs.getString("nip_petugas_jam3"),
                        rs.getString("nip_petugas_jam4"),
                        rs.getString("nip_petugas_jam5"),
                        rs.getString("nip_petugas_jam6"),
                        rs.getString("nip_petugas_jam7"),
                        rs.getString("nip_petugas_jam8"),                        
                        rs.getString("tgl_pemberian"),
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
        wktSimpan = "";
        chkSaya1.setSelected(false);
        chkSaya2.setSelected(false);
        chkSaya3.setSelected(false);
        chkSaya4.setSelected(false);
        chkSaya5.setSelected(false);
        chkSaya6.setSelected(false);
        chkSaya7.setSelected(false);
        chkSaya8.setSelected(false);
        
        nipPetugas1 = "-";
        nipPetugas2 = "-";
        nipPetugas3 = "-";
        nipPetugas4 = "-";
        nipPetugas5 = "-";
        nipPetugas6 = "-";
        nipPetugas7 = "-";
        nipPetugas8 = "-";
        
        Tpetugas1.setText("-");
        Tpetugas2.setText("-");
        Tpetugas3.setText("-");
        Tpetugas4.setText("-");
        Tpetugas5.setText("-");
        Tpetugas6.setText("-");
        Tpetugas7.setText("-");
        Tpetugas8.setText("-");
        Valid.SetTgl(DTPCari2, Sequel.cariIsi("select DATE_ADD(now(),interval 30 day)"));
    }

    private void getData() {
        nipPetugas1 = "";
        nipPetugas2 = "";
        nipPetugas3 = "";
        nipPetugas4 = "";
        nipPetugas5 = "";
        nipPetugas6 = "";
        nipPetugas7 = "";
        nipPetugas8 = "";
        wktSimpan = "";
        
        if (tbPelaksana.getSelectedRow() != -1) {
            TNoRW.setText(tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 0).toString());
            TNoRM.setText(tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 1).toString());
            TNmPasien.setText(tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 2).toString());
            nmUnit.setText(tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 3).toString());
            Valid.SetTgl(tgl_beri, tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 21).toString());
            Tpetugas1.setText(tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 5).toString());
            Tpetugas2.setText(tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 6).toString());
            Tpetugas3.setText(tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 7).toString());
            Tpetugas4.setText(tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 8).toString());
            Tpetugas5.setText(tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 9).toString());
            Tpetugas6.setText(tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 10).toString());
            Tpetugas7.setText(tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 11).toString());
            Tpetugas8.setText(tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 12).toString());
            nipPetugas1 = tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 13).toString();
            nipPetugas2 = tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 14).toString();
            nipPetugas3 = tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 15).toString();
            nipPetugas4 = tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 16).toString();
            nipPetugas5 = tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 17).toString();
            nipPetugas6 = tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 18).toString();
            nipPetugas7 = tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 19).toString();
            nipPetugas8 = tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 20).toString();
            wktSimpan = tbPelaksana.getValueAt(tbPelaksana.getSelectedRow(), 22).toString();
            dataCek();
            tampilObat();
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getpemberian_obat());
       BtnHapus.setEnabled(akses.getpemberian_obat());
       BtnEdit.setEnabled(akses.getpemberian_obat());
    }
    
    private void dataCek() {
        if (nipPetugas1.equals(akses.getkode())) {
            chkSaya1.setSelected(true);
        } else {
            chkSaya1.setSelected(false);
        }

        if (nipPetugas2.equals(akses.getkode())) {
            chkSaya2.setSelected(true);
        } else {
            chkSaya2.setSelected(false);
        }

        if (nipPetugas3.equals(akses.getkode())) {
            chkSaya3.setSelected(true);
        } else {
            chkSaya3.setSelected(false);
        }

        if (nipPetugas4.equals(akses.getkode())) {
            chkSaya4.setSelected(true);
        } else {
            chkSaya4.setSelected(false);
        }

        if (nipPetugas5.equals(akses.getkode())) {
            chkSaya5.setSelected(true);
        } else {
            chkSaya5.setSelected(false);
        }

        if (nipPetugas6.equals(akses.getkode())) {
            chkSaya6.setSelected(true);
        } else {
            chkSaya6.setSelected(false);
        }

        if (nipPetugas7.equals(akses.getkode())) {
            chkSaya7.setSelected(true);
        } else {
            chkSaya7.setSelected(false);
        }

        if (nipPetugas8.equals(akses.getkode())) {
            chkSaya8.setSelected(true);
        } else {
            chkSaya8.setSelected(false);
        }
    }
    
    public void setData(String norw, String norm, String nmpasien, Date tglPemberian, String sttsrwt) {
        TNoRW.setText(norw);
        TNoRM.setText(norm);
        TNmPasien.setText(nmpasien);        
        tgl_beri.setDate(tglPemberian);
        DTPCari1.setDate(tglPemberian);
        TCari.setText(norw);
        nmUnit.setText(Sequel.cariIsi("select nm_unit from pemberian_obat where no_rawat='" + norw + "' and "
                + "tgl_pemberian='" + Valid.SetTgl(tgl_beri.getSelectedItem() + "") + "' and status like '%" + sttsrwt + "%' "
                + "order by waktu_simpan desc limit 1"));
        tampilObat();
    }
    
    private void tampilObat() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT nama_obat, jenis_obat, dosis, cara_pemberian, date_format(tgl_pemberian,'%d-%m-%Y') tgl_beri, "
                    + "if(cek_jam1='ya',time_format(jadwal_pemberian,'%H:%i'),'') jam1, "
                    + "if(cek_jam2='ya',time_format(jadwal_pemberian2,'%H:%i'),'') jam2, "
                    + "if(cek_jam3='ya',time_format(jadwal_pemberian3,'%H:%i'),'') jam3, "
                    + "if(cek_jam4='ya',time_format(jadwal_pemberian4,'%H:%i'),'') jam4, "
                    + "if(cek_jam5='ya',time_format(jadwal_pemberian5,'%H:%i'),'') jam5, "
                    + "if(cek_jam6='ya',time_format(jadwal_pemberian6,'%H:%i'),'') jam6, "
                    + "if(cek_jam7='ya',time_format(jadwal_pemberian7,'%H:%i'),'') jam7, "
                    + "if(cek_jam8='ya',time_format(jadwal_pemberian8,'%H:%i'),'') jam8 FROM pemberian_obat where "
                    + "tgl_pemberian = '" + Valid.SetTgl(tgl_beri.getSelectedItem() + "") + "' and no_rawat='" + TNoRW.getText() + "' order by waktu_simpan");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        rs1.getString("nama_obat"),
                        rs1.getString("jenis_obat"),
                        rs1.getString("dosis"),
                        rs1.getString("cara_pemberian"),
                        rs1.getString("tgl_beri"),
                        rs1.getString("jam1"),
                        rs1.getString("jam2"),
                        rs1.getString("jam3"),
                        rs1.getString("jam4"),
                        rs1.getString("jam5"),
                        rs1.getString("jam6"),
                        rs1.getString("jam7"),
                        rs1.getString("jam8")
                    });
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
}
